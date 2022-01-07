package com.dino.bookmobileapps.ui.form;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dino.bookmobileapps.R;
import com.dino.bookmobileapps.data.DataManager;
import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.data.repository.BookRepository;
import com.dino.bookmobileapps.databinding.ActivityFormBinding;
import com.dino.bookmobileapps.ui.base.BaseBookActivity;
import com.dino.bookmobileapps.utils.Config;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class FormActivity extends BaseBookActivity<ActivityFormBinding, FormViewModel> {

    public static String EXTRAS_FORM = "extrasform_update";
    public static String EXTRAS_DATA = "extrasform_data";

    private boolean isUpdate = false;
    private Book book;
    private Uri uri;

    @Override
    protected FormViewModel createViewModel() {
        BookRepository bookRepository = DataManager.getInstance().getBookRepository();
        FormViewModelFactory factory = new FormViewModelFactory(bookRepository);
        return ViewModelProviders.of(this, factory).get(FormViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityFormBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityFormBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = getIntent().getParcelableExtra(EXTRAS_DATA);
        if (book!=null){
            isUpdate = true;
            uri = Uri.parse(book.getCover());
            Glide.with(this)
                .load(uri.toString().isEmpty() ? R.drawable.ic_default_images : uri)
                .into(binding.ivPhoto);
            binding.etName.setText(book.getBook_name());
            binding.etPenulis.setText(book.getPenulis());
            binding.etPenerbit.setText(book.getPenerbit());
            binding.etSinopsis.setText(book.getSinopsis());
            binding.btnSave.setText("Update Buku");
            binding.btnDelete.setVisibility(View.VISIBLE);
        }else{
            binding.btnSave.setText("Tambah Buku");
            binding.btnDelete.setVisibility(View.GONE);
        }
        setListener();
        observer();
    }

    private void setListener(){
        binding.btnSave.setOnClickListener(v -> {
            setData();
            createOrUpdateBook();
        });
        binding.btnDelete.setOnClickListener(v -> {
            deleteBook();
        });
        binding.ivPhoto.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop()
                    .maxResultSize(1080, 1080)
                    .start();
        });

    }

    private void setData(){
        if (book!=null){
            book.setBook_name(binding.etName.getText().toString());
            book.setCover(uri.toString());
            book.setPenulis(binding.etPenulis.getText().toString());
            book.setPenerbit(binding.etPenerbit.getText().toString());
            book.setSinopsis(binding.etSinopsis.getText().toString());
        }else{
            book = new Book(0,
                    binding.etName.getText().toString(),
                    uri!=null? uri.toString() : "",
                    binding.etPenulis.getText().toString(),
                    binding.etPenerbit.getText().toString(),
                    binding.etSinopsis.getText().toString()
            );
        }
    }

    private void deleteBook(){
        viewModel.deleteBook(book);
    }

    private void createOrUpdateBook(){
        if (isUpdate){
            viewModel.updateBook(book);
        }else{
            viewModel.createBook(book);
        }
    }

    private void observer(){
        viewModel.getLoadingLiveData().observe(this, loading -> {

        });
        viewModel.getShowErrorMessageLiveData().observe(this, error -> {
            Toast.makeText(this, "error : "+error, Toast.LENGTH_SHORT).show();
        });
        viewModel.getBookLiveData().observe(this, book -> {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            uri = data.getData();
            Glide.with(this)
                    .load(uri)
                    .into(binding.ivPhoto);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}