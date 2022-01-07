package com.dino.bookmobileapps.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dino.bookmobileapps.R;
import com.dino.bookmobileapps.data.DataManager;
import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.data.repository.BookRepository;
import com.dino.bookmobileapps.databinding.ActivityMainBinding;
import com.dino.bookmobileapps.ui.base.BaseBookActivity;
import com.dino.bookmobileapps.ui.detail.DetailActivity;
import com.dino.bookmobileapps.ui.form.FormActivity;
import com.dino.bookmobileapps.utils.Config;

public class MainActivity extends BaseBookActivity<ActivityMainBinding, MainViewModel>
implements BookAdapter.BookListener{

    private BookAdapter adapter;

    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        BookRepository bookRepository = DataManager.getInstance().getBookRepository();
        MainViewModelFactory factory = new MainViewModelFactory(bookRepository);
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BookAdapter(this);
        binding.rvBook.setAdapter(adapter);
        binding.rvBook.setLayoutManager(new LinearLayoutManager(this));
        setListerner();
        observer();
        viewModel.loadBooks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadBooks();
    }

    private void setListerner(){
        binding.srLoading.setOnRefreshListener(() -> {
            viewModel.loadBooks();
        });
    }

    private void observer(){
        viewModel.getLoadingLiveData().observe(this, this::isLoading);
        viewModel.getShowErrorMessageLiveData().observe(this, s -> {
            Log.d(Config.TAG, "observer: ");
            Toast.makeText(MainActivity.this, "error : "+s, Toast.LENGTH_SHORT).show();
        });
        viewModel.getBooksLiveData().observe(this, books -> {
            Log.d(Config.TAG, "observer: "+books);
            adapter.setItems(books);
        });
    }

    @Override
    public void onBookClicked(Book book) {
        Intent detail = new Intent(MainActivity.this, DetailActivity.class);
        detail.putExtra(DetailActivity.EXTRAS_BOOK, book);
        startActivity(detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent form = new Intent(MainActivity.this, FormActivity.class);
            startActivity(form);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBookLongClicked(Book book) {
        Intent form = new Intent(MainActivity.this, FormActivity.class);
        form.putExtra(FormActivity.EXTRAS_DATA, book);
        startActivity(form);
    }

    private void isLoading(boolean loading){
        binding.srLoading.setRefreshing(loading);
    }
}