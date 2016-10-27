package com.alina.creditapplication.data.database;

public interface Database {
    int VERSION = 1;
    String NAME = "creditDatabase";
    String DROP_TABLE = "drop table if exists ";

    interface CREDIT_OFFER_TABLE {
        String TABLE_NAME = "credit_offer";
        String COLUMN_ID = "id";
        String COLUMN_NAME = "name";
        String COLUMN_PERCENT = "percent";
        String COLUMN_MAX_PERIOD = "max_period";
        String COLUMN_CURRENCY = "currency";

        String CREATE = "create table " + TABLE_NAME
                + "("
                + COLUMN_ID + " text primary key, "
                + COLUMN_NAME + " text, "
                + COLUMN_PERCENT + " integer, "
                + COLUMN_MAX_PERIOD + " integer, "
                + COLUMN_CURRENCY + " text"
                + ")";
    }

    interface CLIENT {
        String TABLE_NAME = "client";
        String COLUMN_ID = "id";
        String COLUMN_CO_ID = "coid";
        String COLUMN_CURRENT_DAY = "current_day";
        String COLUMN_PAY_DAY = "pay_day";
        String COLUMN_FACT_PAY = "fact_pay_day";
        String COLUMN_NAME = "name";
        String COLUMN_SURNAME = "surname";
        String COLUMN_PHONE = "phone";
        String COLUMN_MOUNTH = "mounth";
        String COLUMN_AMOUNT = "amount";
        String COLUMN_RESULT = "result";

        String CREATE = "create table " + TABLE_NAME
                + "("
                + COLUMN_ID + " text primary key, "
                + COLUMN_CO_ID + " text, "
                + COLUMN_CURRENT_DAY + " text, "
                + COLUMN_PAY_DAY + " text, "
                + COLUMN_FACT_PAY + " text, "
                + COLUMN_NAME + " text, "
                + COLUMN_SURNAME + " text, "
                + COLUMN_PHONE + " text, "
                + COLUMN_MOUNTH + " integer, "
                + COLUMN_AMOUNT + " float, "
                + COLUMN_RESULT + " float"
                + ")";
    }
}
