package com.dino.bookmobileapps.ui.main;

import static com.dino.bookmobileapps.utils.Config.BASE_URL_IMAGE;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dino.bookmobileapps.R;
import com.dino.bookmobileapps.data.db.entity.Book;
import com.dino.bookmobileapps.databinding.ItemBookBinding;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    public interface BookListener {
        void onBookClicked(Book book);
        void onBookLongClicked(Book book);
    }

    private List<Book> items;
    private final BookListener listener;

    public BookAdapter(BookListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Book> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private Book getItem(int position) {
        return items.get(position);
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        ItemBookBinding binding;
        BookViewHolder(ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int position) {
            Book book = getItem(position);

            setClickListener(book);
            setTitle(book.getBook_name());
            setImage(book.getCover());
            setPenulis(book.getPenulis());
        }

        private void setTitle(String title) {
            binding.title.setText(title);
        }

        private void setImage(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl.isEmpty() ? R.drawable.ic_default_images : Uri.parse(imageUrl)).into(binding.image);
        }

        private void setPenulis(String penulis) {
            binding.desc.setText(penulis);
        }

        private void setClickListener(Book book) {
            itemView.setTag(book);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onBookClicked((Book) view.getTag());
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onBookLongClicked((Book) view.getTag());
            return false;
        }
    }
}