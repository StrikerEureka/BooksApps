package com.dino.bookmobileapps.data.repository;

import android.util.Log;

import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.utils.Config;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private final BooksDataSource.Remote bookRemote;
    private final BooksDataSource.Local bookLocal;

    private static BookRepositoryImpl instance;

    private BookRepositoryImpl(BookRemoteDataSource bookRemote,
                               BookLocalDataSource bookLocal) {
        this.bookRemote = bookRemote;
        this.bookLocal = bookLocal;
    }

    public static BookRepositoryImpl getInstance(BookRemoteDataSource bookRemote,
                                                  BookLocalDataSource bookLocal) {
        if (instance == null) {
            instance = new BookRepositoryImpl(bookRemote, bookLocal);
        }
        return instance;
    }

    @Override
    public void getBooks(final BookRepository.LoadBooksCallback callback) {
        if (callback == null) return;
        getBooksFromLocalDataSource(callback);
    }

    @Override
    public void saveBooks(List<Book> books) {
        bookLocal.saveBooks(books);
    }

    @Override
    public void createBook(Book book, ApiCallback callback) {
        if (callback == null) return;
        bookRemote.createBook(book, new ApiCallback() {
            @Override
            public void onApiSuccess() {
                callback.onApiSuccess();
            }

            @Override
            public void onApiError() {
                callback.onApiError();
            }
        });
    }

    @Override
    public void updateBook(Book book, ApiCallback callback) {
        if (callback == null) return;
        updateBookFromRemoteDataSource(book, callback);
    }

    @Override
    public void deleteBook(Book book, ApiCallback callback) {
        if (callback == null) return;
        deleteBookFromRemoteDataSource(book, callback);
    }

    private void getBooksFromLocalDataSource(final BookRepository.LoadBooksCallback callback) {
        bookLocal.getBooks(new BookRepository.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(List<Book> books) {
                Log.d(Config.TAG, "local onBooksLoaded: "+books.size());
                callback.onBooksLoaded(books);
                getBooksFromRemoteDataSource(callback);
            }

            @Override
            public void onDataNotAvailable() {
                getBooksFromRemoteDataSource(callback);
            }

            @Override
            public void onError() {
                //not implemented in local data source
            }
        });
    }

    private void getBooksFromRemoteDataSource(final BookRepository.LoadBooksCallback callback) {
        bookRemote.getBooks(new BookRepository.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(List<Book> books) {
                Log.d(Config.TAG, "remote onBooksLoaded: "+books.size());
                callback.onBooksLoaded(books);
                saveBooks(books);
            }

            @Override
            public void onDataNotAvailable() {
                Log.d(Config.TAG, "remote onDataNotAvailable: ");
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                Log.d(Config.TAG, "remote onError: ");
                callback.onError();
            }
        });
    }

    private void updateBookFromRemoteDataSource(final Book book, final BookRepository.ApiCallback callback){
        bookRemote.updateBook(book, new BookRepository.ApiCallback() {
            @Override
            public void onApiSuccess() {
                Log.d(Config.TAG, "remote updateBook: ");
                callback.onApiSuccess();
                bookLocal.updateBook(book);
            }

            @Override
            public void onApiError() {
                callback.onApiError();
            }
        });
    }

    private void deleteBookFromRemoteDataSource(final Book book, final BookRepository.ApiCallback callback){
        bookRemote.deleteBook(book, new BookRepository.ApiCallback() {
            @Override
            public void onApiSuccess() {
                Log.d(Config.TAG, "remote deleteBook: ");
                callback.onApiSuccess();
                bookLocal.deleteBook(book);
            }

            @Override
            public void onApiError() {
                callback.onApiError();
            }
        });
    }
}
