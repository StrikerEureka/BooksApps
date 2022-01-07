package com.dino.bookmobileapps.data.repository;

import com.dino.bookmobileapps.data.db.entity.Book;

import java.util.List;

public interface BookRepository {
    interface LoadBooksCallback {
        void onBooksLoaded(List<Book> books);
        void onDataNotAvailable();
        void onError();
    }

    interface ApiCallback{
        void onApiSuccess();
        void onApiError();
    }

    void getBooks(LoadBooksCallback callback);
    void saveBooks(List<Book> books);
    void createBook(Book book, ApiCallback callback);
    void updateBook(Book book, ApiCallback callback);
    void deleteBook(Book book, ApiCallback callback);
}
