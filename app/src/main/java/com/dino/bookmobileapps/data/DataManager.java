package com.dino.bookmobileapps.data;

import com.dino.bookmobileapps.data.api.BookApi;
import com.dino.bookmobileapps.data.db.BookDao;
import com.dino.bookmobileapps.data.db.BookDatabase;
import com.dino.bookmobileapps.data.repository.BookLocalDataSource;
import com.dino.bookmobileapps.data.repository.BookRemoteDataSource;
import com.dino.bookmobileapps.data.repository.BookRepository;
import com.dino.bookmobileapps.data.repository.BookRepositoryImpl;
import com.dino.bookmobileapps.data.service.BookService;
import com.preference.PowerPreference;
import com.preference.Preference;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public Preference getPrefs() {
        return PowerPreference.getDefaultFile();
    }

    public BookRepository getBookRepository() {
        BookApi bookApi = BookService.getInstance().getBookApi();
        BookRemoteDataSource bookRemote = BookRemoteDataSource.getInstance(bookApi);

        BookDao bookDao = BookDatabase.getsInstance().bookDao();
        BookLocalDataSource bookLocal = BookLocalDataSource.getInstance(bookDao);

        return BookRepositoryImpl.getInstance(bookRemote,bookLocal);
    }

}
