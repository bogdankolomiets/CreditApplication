package com.alina.creditapplication.presenter.contracts;

import com.alina.creditapplication.entity.Client;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class ClientsListContract {

    public interface View extends CommonView {
        void showClients(List<Client> clients);
    }

    public interface Presenter extends CommonPresenter {
        void onResume(String categoryId);
    }
}
