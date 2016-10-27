package com.alina.creditapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alina.creditapplication.R;
import com.alina.creditapplication.entity.CreditOffer;
import com.alina.creditapplication.presenter.AddCreditOfferPresenter;
import com.alina.creditapplication.presenter.PresenterFactory;
import com.alina.creditapplication.presenter.contracts.AddCreditOfferContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.10.16
 */

public class AddCreditActivity extends AppCompatActivity implements AddCreditOfferContract.View {
    public static final String EDIT_MODE = "edit_mode";
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPercent)
    EditText etPercent;
    @BindView(R.id.etMaxPeriod)
    EditText etMaxPeriod;
    @BindView(R.id.etCurrency)
    EditText etCurrency;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.btnSave)
    Button btnSave;

    private AddCreditOfferPresenter mPresenter;
    private CreditOffer mCreditOffer;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_credit_activity_layout);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCreditOffer = (CreditOffer) extras.getSerializable(EDIT_MODE);
        }
        mPresenter = (AddCreditOfferPresenter) PresenterFactory.getInstance().get(AddCreditOfferPresenter.TAG);
        mPresenter.bindView(this);
        if (mCreditOffer != null) {
            btnSave.setText("Змінити");
            btnDelete.setVisibility(View.VISIBLE);
            etName.setText(mCreditOffer.getName());
            etPercent.setText(String.valueOf(mCreditOffer.getPercent()));
            etMaxPeriod.setText(String.valueOf(mCreditOffer.getMaxPeriod()));
            etCurrency.setText(mCreditOffer.getCurrency());
            btnDelete.setOnClickListener(click -> mPresenter.delete(mCreditOffer));
        }
    }

    @OnClick(R.id.btnSave)
    public void save() {
        if (validateData()) {
            mPresenter.onSaveClick(new CreditOffer(
                    etName.getText().toString(),
                    Integer.valueOf(etPercent.getText().toString()),
                    Integer.valueOf(etMaxPeriod.getText().toString()),
                    etCurrency.getText().toString()
            ));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    private boolean validateData() {
        boolean result = true;
        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ви не вказали назву", Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etPercent.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ви не вказали відсотки", Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etMaxPeriod.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ви не вказали максимальний період кредиту", Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etCurrency.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ви не вказали валюту", Toast.LENGTH_LONG).show();
            result = false;
        }

        return result;
    }

    @Override
    public void success() {
        Toast.makeText(this, "Успішно", Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
