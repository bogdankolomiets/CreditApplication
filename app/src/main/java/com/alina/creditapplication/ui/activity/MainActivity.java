package com.alina.creditapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alina.creditapplication.R;
import com.alina.creditapplication.entity.CreditOffer;
import com.alina.creditapplication.presenter.MainPresenter;
import com.alina.creditapplication.presenter.PresenterFactory;
import com.alina.creditapplication.presenter.contracts.MainContract;
import com.alina.creditapplication.ui.CreditOffersAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.10.16
 */

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.btnEdit)
    RelativeLayout btnEdit;
    @BindView(R.id.btnAdd)
    RelativeLayout btnAdd;
    @BindView(R.id.btnAddIcon)
    ImageView btnAddIcon;

    @BindView(R.id.rvCreditsOffers)
    RecyclerView rvCreditsOffers;

    private MainPresenter mPresenter;
    private CreditOffersAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean mIsEditMode = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        ButterKnife.bind(this);
        mPresenter = (MainPresenter) PresenterFactory.getInstance().get(MainPresenter.TAG);
        mPresenter.bindView(this);
        initView();
    }

    private void initView() {
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CreditOffersAdapter();
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, mLayoutManager.getOrientation());
        rvCreditsOffers.setLayoutManager(mLayoutManager);
        rvCreditsOffers.setAdapter(mAdapter);
        rvCreditsOffers.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    @OnClick(R.id.btnEdit)
    public void edit() {
        mIsEditMode = true;
        mAdapter.editable(true);
        btnEdit.setVisibility(View.GONE);
        btnAddIcon.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_close_white_24dp));
        rvCreditsOffers.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
    }

    @OnClick(R.id.btnAdd)
    public void add() {
        if (mIsEditMode) {
            mIsEditMode = false;
            mAdapter.editable(false);
            rvCreditsOffers.setBackgroundColor(0);
            btnEdit.setVisibility(View.VISIBLE);
            btnAddIcon.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_add_white_24dp));
        } else {
            startActivity(new Intent(this, AddCreditActivity.class));
        }
    }

    @Override
    public void showCreditOffers(List<CreditOffer> creditOffers) {
        mAdapter.addDataWithClear(creditOffers);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
