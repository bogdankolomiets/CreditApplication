package com.alina.creditapplication.presenter;

import com.alina.creditapplication.data.DatabaseManager;
import com.alina.creditapplication.presenter.contracts.MainContract;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    public static final String TAG = MainPresenter.class.getName();

    private final DatabaseManager mDatabaseManager;

    public MainPresenter(DatabaseManager databaseManager) {
        mDatabaseManager = databaseManager;
    }

    @Override
    public void onResume() {
        Subscription subscription = Observable.from(mDatabaseManager.getCreditOffers())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(creditOffers -> {
                    getView().showCreditOffers(creditOffers);
                }, throwable -> {
                    getView().showError(throwable.getMessage());
                });
        addSubscription(subscription);
    }
}
