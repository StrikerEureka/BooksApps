package com.dino.bookmobileapps.data.repository;

import androidx.annotation.NonNull;

import com.dino.bookmobileapps.data.api.BookApi;
import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.data.model.BookResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRemoteDataSource implements BooksDataSource.Remote {

    private static BookRemoteDataSource instance;

    private final BookApi bookApi;

    private BookRemoteDataSource(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    public static BookRemoteDataSource getInstance(BookApi bookApi) {
        if (instance == null) {
            instance = new BookRemoteDataSource(bookApi);
        }
        return instance;
    }

    @Override
    public void getBooks(final BookRepository.LoadBooksCallback callback) {
        bookApi.getBooks().enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookResponse> call, @NonNull Response<BookResponse> response) {
                if (response.body() != null){
                    List<Book> books = response.body().getBooks();
                    if (books != null && !books.isEmpty()) {
                        final List<Book> bookDomain = new ArrayList<>(books);
                        callback.onBooksLoaded(bookDomain);
                    } else {
                        callback.onDataNotAvailable();
                    }
                }else{
                    callback.onError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BookResponse> call, @NonNull Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void createBook(Book book, BookRepository.ApiCallback callback) {
        bookApi.createBook(book).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.body() != null && response.body().isStatus()){
                    callback.onApiSuccess();
                }else{
                    callback.onApiError();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                callback.onApiError();
            }
        });
    }

    @Override
    public void updateBook(Book book, BookRepository.ApiCallback callback) {
        bookApi.updateBook(book.getBook_id(), book).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.body() != null && response.body().isStatus()){
                    callback.onApiSuccess();
                }else{
                    callback.onApiError();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                callback.onApiError();
            }
        });
    }

    @Override
    public void deleteBook(Book book, BookRepository.ApiCallback callback) {
        bookApi.deleteBook(book.getBook_id()).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.body() != null && response.body().isStatus()){
                    callback.onApiSuccess();
                }else{
                    callback.onApiError();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                callback.onApiError();
            }
        });
    }
}
