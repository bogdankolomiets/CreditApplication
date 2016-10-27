package com.alina.creditapplication;

import android.app.Application;

import com.alina.creditapplication.data.DatabaseManager;
import com.alina.creditapplication.presenter.PresenterFactory;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.10.16
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PresenterFactory.create(DatabaseManager.getInstance(this));
    }
}
