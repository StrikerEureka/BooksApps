package com.dino.bookmobileapps.data.api;

import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.data.model.BookResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface BookApi {

    @GET("get-all-books.php")
    Call<BookResponse> getBooks();

    @POST("create.php")
    Call<BookResponse> createBook(@Body Book book);

    @POST("update-book.php")
    Call<BookResponse> updateBook(@Query("book_id") int id, @Body Book book);

    @DELETE("delete-book.php")
    Call<BookResponse> deleteBook(@Query("book_id") int id);

}
