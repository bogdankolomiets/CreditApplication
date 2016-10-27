package com.alina.creditapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alina.creditapplication.R;
import com.alina.creditapplication.entity.Client;
import com.alina.creditapplication.presenter.AddClientPresenter;
import com.alina.creditapplication.presenter.PresenterFactory;
import com.alina.creditapplication.presenter.contracts.AddClientContract;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class AddClientActivity extends AppCompatActivity implements AddClientContract.View {
    public static final String CREDIT_OFFER_KEY = "credit_offer_key";
    public static final String CREDIT_OFFER_PERCENT_KEY = "credit_percent_key";

    @BindView(R.id.etPayDate)
    EditText etPayDate;
    @BindView(R.id.etFactPayDate)
    EditText etFactPatDate;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etSurname)
    EditText etSurname;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPeriod)
    EditText etPeriod;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.tvResult)
    TextView tvResult;

    private String mId = "";
    private int mPersent = 5;
    private double mResult;
    private AddClientPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_client_activity);
        ButterKnife.bind(this);
        mPresenter = (AddClientPresenter) PresenterFactory.getInstance().get(AddClientPresenter.TAG);
        mPresenter.bindView(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mId = extras.getString(CREDIT_OFFER_KEY);
            mPersent = extras.getInt(CREDIT_OFFER_PERCENT_KEY);
        }
    }

    @OnClick(R.id.btnAdd)
    public void add(Button button) {
        if (validateData()) {
            if (tvResult.getText().toString().isEmpty()) {
                mResult = Double.valueOf(etAmount.getText().toString()) + (Double.valueOf(etAmount.getText().toString()) * mPersent / 100);
                tvResult.setText(String.valueOf(mResult));
                button.setText("Зберегти");
            } else {
                mPresenter.onAddClick(
                        new Client(
                                mId,
                                new Date().toString(),
                                etPayDate.getText().toString(),
                                etFactPatDate.getText().toString(),
                                etName.getText().toString(),
                                etSurname.getText().toString(),
                                etPhone.getText().toString(),
                                Integer.valueOf(etPeriod.getText().toString()),
                                Double.valueOf(etAmount.getText().toString()),
                                Double.valueOf(tvResult.getText().toString())
                        )
                );
            }
        }
    }

    private boolean validateData() {
        boolean result = true;
        if (etPayDate.getText().toString().isEmpty()) {
            Toast.makeText(this, "не введено дата", Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(this, "не введено ім’я", Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etSurname.getText().toString().isEmpty()) {
            Toast.makeText(this, "не введено ім’я", Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etPhone.getText().toString().isEmpty()) {
            Toast.makeText(this, "не введений телефон", Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etPeriod.getText().toString().isEmpty()) {
            Toast.makeText(this, "не введений кредитний період", Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etAmount.getText().toString().isEmpty()) {
            Toast.makeText(this, "не введена сумма", Toast.LENGTH_LONG).show();
            result = false;
        }

        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
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
