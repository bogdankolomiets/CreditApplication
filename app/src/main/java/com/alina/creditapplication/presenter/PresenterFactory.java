package com.alina.creditapplication.presenter;

import com.alina.creditapplication.data.DatabaseManager;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class PresenterFactory {
    private static PresenterFactory sInstance;
    private final DatabaseManager mDatabaseManager;

    private PresenterFactory(DatabaseManager databaseManager) {
        mDatabaseManager = databaseManager;
    }

    public static void create(DatabaseManager databaseManager) {
        if (sInstance == null) {
            sInstance = new PresenterFactory(databaseManager);
        }
    }

    public static PresenterFactory getInstance() {
        return sInstance;
    }

    public BasePresenter get(String tag) {
        if (tag.equals(MainPresenter.TAG)) {
            return new MainPresenter(mDatabaseManager);
        }
        if (tag.equals(AddCreditOfferPresenter.TAG)) {
            return new AddCreditOfferPresenter(mDatabaseManager);
        }
        if (tag.equals(ClientsListPresenter.TAG)) {
            return new ClientsListPresenter(mDatabaseManager);
        }
        if (tag.equals(AddClientPresenter.TAG)) {
            return new AddClientPresenter(mDatabaseManager);
        }

        return null;
    }
}
