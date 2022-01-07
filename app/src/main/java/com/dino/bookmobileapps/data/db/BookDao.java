package com.dino.bookmobileapps.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dino.bookmobileapps.data.db.entity.Book;

import java.util.List;

@Dao
public interface BookDao {
    /**
     * Select all books from the books table.
     *
     * @return all books.
     */
    @Query("SELECT * FROM books")
    List<Book> getBooks();

    /**
     * Insert all books.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveBooks(List<Book> books);

    /**
     * Insert single books.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveBook(Book book);

    /**
     * Update single books.
     */
    @Update
    void updateBook(Book book);

    /**
     * Delete all books.
     */
    @Query("DELETE FROM books")
    void deleteBooks();

    /**
     * Delete book with id.
     */
    @Query("DELETE FROM books WHERE book_id = :id")
    void deleteBook(int id);
}
