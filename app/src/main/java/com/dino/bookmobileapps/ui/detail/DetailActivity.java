package com.dino.bookmobileapps.ui.detail;

import androidx.annotation.NonNull;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dino.bookmobileapps.R;
import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.databinding.ActivityDetailBinding;
import com.dino.bookmobileapps.ui.base.BaseBookActivity;
import com.dino.bookmobileapps.ui.base.BaseViewModel;
import com.dino.bookmobileapps.utils.Config;


public class DetailActivity extends BaseBookActivity<ActivityDetailBinding, BaseViewModel> {

    public static final String EXTRAS_BOOK = "extrasbook";
    private Book book;

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @NonNull
    @Override
    protected ActivityDetailBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityDetailBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = getIntent().getParcelableExtra(EXTRAS_BOOK);
        if (book==null) {
            Toast.makeText(this, "Invalid book data...", Toast.LENGTH_SHORT).show();
            finish();
        }
        setViews();
    }

    private void setViews(){
        Glide.with(this)
            .load(book.getCover().isEmpty() ? R.drawable.ic_default_images : Uri.parse(book.getCover()))
            .into(binding.image);

        binding.tvBookName.setText(getString(R.string.detail_input, book.getBook_name()));
        binding.tvPenulis.setText(getString(R.string.detail_input, book.getPenulis()));
        binding.tvPenerbit.setText(getString(R.string.detail_input, book.getPenerbit()));
        binding.tvSinopsis.setText(book.getSinopsis());
    }
}