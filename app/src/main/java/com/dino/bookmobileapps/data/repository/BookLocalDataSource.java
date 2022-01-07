package com.dino.bookmobileapps.data.repository;
import com.dino.bookmobileapps.data.db.BookDao;
import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.utils.DiskExecutor;

import java.util.List;
import java.util.concurrent.Executor;


public class BookLocalDataSource implements BooksDataSource.Local {

    private final Executor executor;
    private final BookDao bookDao;

    private static BookLocalDataSource instance;

    private BookLocalDataSource(Executor executor, BookDao bookDao) {
        this.executor = executor;
        this.bookDao = bookDao;
    }

    public static BookLocalDataSource getInstance(BookDao bookDao) {
        if (instance == null) {
            instance = new BookLocalDataSource(new DiskExecutor(), bookDao);
        }
        return instance;
    }

    @Override
    public void getBooks(final BookRepository.LoadBooksCallback callback) {
        Runnable runnable = () -> {
            List<Book> books = bookDao.getBooks();
            if (!books.isEmpty()) {
                callback.onBooksLoaded(books);
            } else {
                callback.onDataNotAvailable();
            }
        };
        executor.execute(runnable);
    }

    @Override
    public void createBook(Book book, BookRepository.ApiCallback callback) {
        addBook(book);
    }

    @Override
    public void updateBook(Book book, BookRepository.ApiCallback callback) {
        updateBook(book);
    }

    @Override
    public void deleteBook(Book book, BookRepository.ApiCallback callback) {
        deleteBook(book);
    }

    @Override
    public void saveBooks(final List<Book> books) {
        Runnable runnable = () -> {
            bookDao.deleteBooks();
            bookDao.saveBooks(books);
        };
        executor.execute(runnable);
    }

    @Override
    public void addBook(Book book) {
        Runnable runnable = () -> bookDao.saveBook(book);
        executor.execute(runnable);
    }

    @Override
    public void updateBook(Book book) {
        Runnable runnable = () -> bookDao.updateBook(book);
        executor.execute(runnable);
    }

    @Override
    public void deleteBook(Book book) {
        Runnable runnable = () -> bookDao.deleteBook(book.getBook_id());
        executor.execute(runnable);
    }
}
