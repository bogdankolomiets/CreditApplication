package com.alina.creditapplication.presenter.contracts;

import com.alina.creditapplication.entity.Client;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class AddClientContract {

    public interface View extends CommonView {
        void success();
    }

    public interface Presenter extends CommonPresenter {
        void onAddClick(Client client);
    }
}
