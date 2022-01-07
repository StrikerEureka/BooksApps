package com.dino.bookmobileapps.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.data.repository.BookRepository;
import com.dino.bookmobileapps.ui.base.BaseViewModel;

import java.util.Collections;
import java.util.List;

public class MainViewModel extends BaseViewModel {

    private final MutableLiveData<List<Book>> booksLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> showErrorMessageLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private final BookRepository booksRepository;

    private final BookCallback bookCallback = new BookCallback();

    MainViewModel(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void loadBooks() {
        setIsLoading(true);
        booksRepository.getBooks(bookCallback);
    }

    public void onEmptyClicked() {
        setBooksLiveData(Collections.<Book>emptyList());
    }

    private void setIsLoading(boolean loading) {
        loadingLiveData.postValue(loading);
    }

    private void setBooksLiveData(List<Book> booksLiveData) {
        setIsLoading(false);
        this.booksLiveData.postValue(booksLiveData);
    }

    /**
     * Callback
     **/
    private class BookCallback implements BookRepository.LoadBooksCallback {
        @Override
        public void onBooksLoaded(List<Book> books) {
            setBooksLiveData(books);
        }

        @Override
        public void onDataNotAvailable() {
            setIsLoading(false);
            showErrorMessageLiveData.postValue("There is not items!");
        }

        @Override
        public void onError() {
            setIsLoading(false);
            showErrorMessageLiveData.postValue("Something Went Wrong!");
        }
    }

    /**
     * LiveData
     **/
    public LiveData<List<Book>> getBooksLiveData() {
        return booksLiveData;
    }

    public LiveData<String> getShowErrorMessageLiveData() {
        return showErrorMessageLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }
}
