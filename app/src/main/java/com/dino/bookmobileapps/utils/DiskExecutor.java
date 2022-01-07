package com.dino.bookmobileapps.utils;


import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskExecutor implements Executor {

    private final Executor diskExecutor;

    public DiskExecutor() {
        this.diskExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        diskExecutor.execute(runnable);
    }
}
