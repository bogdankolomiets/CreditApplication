package com.alina.creditapplication.presenter;

import com.alina.creditapplication.presenter.contracts.CommonPresenter;
import com.alina.creditapplication.presenter.contracts.CommonView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public abstract class BasePresenter<V extends CommonView> implements CommonPresenter {
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private V mView;

    protected BasePresenter() {

    }

    public void bindView(V view) {
        mView = view;
    }

    public void unbindView() {
        mView = null;
        mCompositeSubscription.unsubscribe();
        mCompositeSubscription.clear();
    }

    protected V getView() {
        return mView;
    }

    protected void addSubscription(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }
}
