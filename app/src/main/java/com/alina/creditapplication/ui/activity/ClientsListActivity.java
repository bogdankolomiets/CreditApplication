package com.alina.creditapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alina.creditapplication.R;
import com.alina.creditapplication.entity.Client;
import com.alina.creditapplication.presenter.ClientsListPresenter;
import com.alina.creditapplication.presenter.PresenterFactory;
import com.alina.creditapplication.presenter.contracts.ClientsListContract;
import com.alina.creditapplication.ui.ClientsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class ClientsListActivity extends AppCompatActivity implements ClientsListContract.View {
    public static final String CREDIT_OFFER_KEY = "credit_offer_key";
    public static final String CREDIT_PERSENT_OFFER_KEY = "credit_persent_offer_key";

    @BindView(R.id.rvClients)
    RecyclerView rvClients;

    private ClientsAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ClientsListPresenter mPresenter;
    private String mId = "";
    private int mPersent = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list_activity);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mId = extras.getString(CREDIT_OFFER_KEY);
            mPersent = extras .getInt(CREDIT_PERSENT_OFFER_KEY);
        }
        mPresenter = (ClientsListPresenter) PresenterFactory.getInstance().get(ClientsListPresenter.TAG);
        mPresenter.bindView(this);
        init();
    }

    private void init() {
        mAdapter = new ClientsAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        rvClients.setAdapter(mAdapter);
        rvClients.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume(mId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    @OnClick(R.id.fabAddClient)
    public void addClient() {
        Intent intent = new Intent(this, AddClientActivity.class);
        intent.putExtra(AddClientActivity.CREDIT_OFFER_KEY, mId);
        intent.putExtra(AddClientActivity.CREDIT_OFFER_PERCENT_KEY, mPersent);
        startActivity(intent);
    }

    @Override
    public void showClients(List<Client> clients) {
        mAdapter.addDataWithClear(clients);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
