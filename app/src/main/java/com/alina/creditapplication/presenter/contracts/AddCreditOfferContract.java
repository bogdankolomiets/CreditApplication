package com.alina.creditapplication.presenter.contracts;

import com.alina.creditapplication.entity.CreditOffer;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class AddCreditOfferContract {

    public interface View extends CommonView {
        void success();
    }

    public interface Presenter extends CommonPresenter {
        void onSaveClick(CreditOffer creditOffer);
        void delete(CreditOffer creditOffer);
    }
}
