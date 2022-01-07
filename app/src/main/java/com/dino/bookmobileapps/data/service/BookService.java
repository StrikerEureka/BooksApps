package com.dino.bookmobileapps.data.service;

import static com.dino.bookmobileapps.utils.Config.BASE_URL;

import com.dino.bookmobileapps.data.api.BookApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BookService {

    private final BookApi bookApi;

    private static BookService singleton;

    private BookService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        bookApi = mRetrofit.create(BookApi.class);
    }

    public static BookService getInstance() {
        if (singleton == null) {
            synchronized (BookService.class) {
                if (singleton == null) {
                    singleton = new BookService();
                }
            }
        }
        return singleton;
    }

    public BookApi getBookApi() {
        return bookApi;
    }
}
