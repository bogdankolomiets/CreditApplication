package com.alina.creditapplication.entity;

import java.util.UUID;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class Client {
    private String mId;
    private String mCategoryId;
    private String mCurrentDay;
    private String mPayDay;
    private String mFactPayDay;
    private String mName;
    private String mSurname;
    private String mPhone;
    private int mMounth;
    private double mAmount;
    private double mResult;

    public Client(String categoryId, String currentDay, String payDay, String factPayDay, String name, String surname, String phone, int mounth, double amount, double result) {
        this(UUID.randomUUID().toString(), categoryId, currentDay, payDay, factPayDay, name, surname, phone, mounth, amount, result);
    }

    public Client(String id, String categoryId, String currentDay, String payDay, String factPayDay, String name, String surname, String phone, int mounth, double amount, double result) {
        mId = id;
        mCategoryId = categoryId;
        mCurrentDay = currentDay;
        mPayDay = payDay;
        mFactPayDay = factPayDay;
        mName = name;
        mSurname = surname;
        mPhone = phone;
        mMounth = mounth;
        mAmount = amount;
        mResult = result;
    }

    public String getId() {
        return mId;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public String getCurrentDay() {
        return mCurrentDay;
    }

    public String getPayDay() {
        return mPayDay;
    }

    public String getFactPayDay() {
        return mFactPayDay;
    }

    public String getName() {
        return mName;
    }

    public String getSurname() {
        return mSurname;
    }

    public String getPhone() {
        return mPhone;
    }

    public int getMounth() {
        return mMounth;
    }

    public double getAmount() {
        return mAmount;
    }

    public double getResult() {
        return mResult;
    }
}
