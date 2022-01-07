package com.dino.bookmobileapps.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dino.bookmobileapps.data.repository.BookRepository;


public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final BookRepository booksRepository;

    public MainViewModelFactory(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(booksRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
