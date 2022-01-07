package com.dino.bookmobileapps.ui.form;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dino.bookmobileapps.data.repository.BookRepository;
import com.dino.bookmobileapps.ui.main.MainViewModel;


public class FormViewModelFactory implements ViewModelProvider.Factory {

    private final BookRepository booksRepository;

    public FormViewModelFactory(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FormViewModel.class)) {
            return (T) new FormViewModel(booksRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
