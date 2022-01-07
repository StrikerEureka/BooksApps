package com.dino.bookmobileapps.data.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dino.bookmobileapps.App;
import com.dino.bookmobileapps.data.db.entity.Book;


@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    private static BookDatabase sInstance;

    public static BookDatabase getsInstance() {
        if (sInstance == null){
            sInstance = Room.databaseBuilder(App.getInstance(), BookDatabase.class, "bookapps.db").build();
        }
        return sInstance;
    }
}
