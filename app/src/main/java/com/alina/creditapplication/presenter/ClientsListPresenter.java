package com.alina.creditapplication.presenter;

import com.alina.creditapplication.data.DatabaseManager;
import com.alina.creditapplication.presenter.contracts.ClientsListContract;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class ClientsListPresenter extends BasePresenter<ClientsListContract.View> implements ClientsListContract.Presenter {
    public static final String TAG = ClientsListPresenter.class.getName();

    private final DatabaseManager mDatabaseManager;

    public ClientsListPresenter(DatabaseManager databaseManager) {
        mDatabaseManager = databaseManager;
    }

    @Override
    public void onResume(String categoryId) {
        Subscription subscription = Observable.from(mDatabaseManager.getClientsInCategory(categoryId))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(clients -> {
                    getView().showClients(clients);
                }, throwable -> {
                    getView().showError(throwable.getMessage());
                });
        addSubscription(subscription);
    }
}
