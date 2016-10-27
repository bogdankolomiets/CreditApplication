package com.alina.creditapplication.presenter;

import com.alina.creditapplication.data.DatabaseManager;
import com.alina.creditapplication.entity.CreditOffer;
import com.alina.creditapplication.presenter.contracts.AddCreditOfferContract;
import com.alina.creditapplication.presenter.contracts.AddCreditOfferContract.Presenter;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class AddCreditOfferPresenter extends BasePresenter<AddCreditOfferContract.View> implements Presenter {
    public static final String TAG = AddCreditOfferPresenter.class.getName();

    private final DatabaseManager mDatabaseManager;

    public AddCreditOfferPresenter(DatabaseManager databaseManager) {
        mDatabaseManager = databaseManager;
    }

    @Override
    public void onSaveClick(CreditOffer creditOffer) {
        mDatabaseManager.saveCreditOffer(creditOffer);
        getView().success();
    }

    @Override
    public void delete(CreditOffer creditOffer) {
        mDatabaseManager.deleteCreditOffer(creditOffer);
        getView().success();
    }
}
