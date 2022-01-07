package com.dino.bookmobileapps.data.repository;

import com.dino.bookmobileapps.data.db.entity.Book;

import java.util.List;

public class BooksDataSource {

    interface Remote {
        void getBooks(BookRepository.LoadBooksCallback callback);
        void createBook(Book book, BookRepository.ApiCallback callback);
        void updateBook(Book book, BookRepository.ApiCallback callback);
        void deleteBook(Book book, BookRepository.ApiCallback callback);
    }

    interface Local extends Remote {
        void saveBooks(List<Book> Books);
        void addBook(Book book);
        void updateBook(Book book);
        void deleteBook(Book book);
    }
}
