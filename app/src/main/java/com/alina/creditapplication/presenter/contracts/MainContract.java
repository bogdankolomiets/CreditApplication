package com.alina.creditapplication.presenter.contracts;

import com.alina.creditapplication.entity.CreditOffer;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class MainContract {

    public interface View extends CommonView {
        void showCreditOffers(List<CreditOffer> creditOffers);
    }

    public interface Presenter extends CommonPresenter {
        void onResume();
    }
}
