package com.alina.creditapplication.presenter;

import com.alina.creditapplication.data.DatabaseManager;
import com.alina.creditapplication.entity.Client;
import com.alina.creditapplication.presenter.contracts.AddClientContract;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class AddClientPresenter extends BasePresenter<AddClientContract.View> implements AddClientContract.Presenter {
    public static final String TAG = AddClientPresenter.class.getName();

    private final DatabaseManager mDatabaseManager;

    public AddClientPresenter(DatabaseManager databaseManager) {
        mDatabaseManager = databaseManager;
    }

    @Override
    public void onAddClick(Client client) {
        mDatabaseManager.saveClient(client);
        getView().success();
    }
}
