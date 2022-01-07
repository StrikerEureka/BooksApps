package com.dino.bookmobileapps.data.model;

import com.dino.bookmobileapps.data.db.entity.Book;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {
    @Expose
    @SerializedName("data")
    private List<Book> books;

    @Expose
    @SerializedName("status")
    private boolean status;

    @Expose
    @SerializedName("message")
    private boolean message;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}
