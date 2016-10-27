package com.alina.creditapplication.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class CreditOffer implements Serializable {
    private String mId;
    private String mName;
    private int mPercent;
    private int mMaxPeriod;
    private String mCurrency;

    public CreditOffer(String name, int percent, int maxPeriod, String currency) {
        this(UUID.randomUUID().toString(), name, percent, maxPeriod, currency);
    }

    public CreditOffer(String id, String name, int percent, int maxPeriod, String currency) {
        mId = id;
        mName = name;
        mPercent = percent;
        mMaxPeriod = maxPeriod;
        mCurrency = currency;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getPercent() {
        return mPercent;
    }

    public int getMaxPeriod() {
        return mMaxPeriod;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPercent(int percent) {
        mPercent = percent;
    }

    public void setMaxPeriod(int maxPeriod) {
        mMaxPeriod = maxPeriod;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }
}
