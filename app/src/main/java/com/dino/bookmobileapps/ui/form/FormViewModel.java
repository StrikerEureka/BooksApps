package com.dino.bookmobileapps.ui.form;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.data.repository.BookRepository;
import com.dino.bookmobileapps.ui.base.BaseViewModel;

import java.util.Collections;
import java.util.List;

public class FormViewModel extends BaseViewModel {

    private final MutableLiveData<Book> bookLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> showErrorMessageLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private final BookRepository booksRepository;
    private Book book;

    private final BookCallback bookCallback = new BookCallback();

    FormViewModel(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    private void setIsLoading(boolean loading) {
        loadingLiveData.postValue(loading);
    }

    private void setBooksLiveData(Book book) {
        setIsLoading(false);
        this.bookLiveData.postValue(book);
    }

    public void createBook(Book book){
        setIsLoading(true);
        this.book = book;
        booksRepository.createBook(book, bookCallback);
    }

    public void updateBook(Book book){
        setIsLoading(true);
        this.book = book;
        booksRepository.updateBook(book, bookCallback);
    }

    public void deleteBook(Book book){
        setIsLoading(true);
        this.book = book;
        booksRepository.deleteBook(book, bookCallback);
    }

    /**
     * Callback
     **/
    private class BookCallback implements BookRepository.ApiCallback {
        @Override
        public void onApiSuccess() {
            setBooksLiveData(book);
        }

        @Override
        public void onApiError() {
            setIsLoading(false);
            showErrorMessageLiveData.postValue("Error!");
        }
    }

    /**
     * LiveData
     **/
    public LiveData<Book> getBookLiveData() {
        return bookLiveData;
    }

    public LiveData<String> getShowErrorMessageLiveData() {
        return showErrorMessageLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }
}
