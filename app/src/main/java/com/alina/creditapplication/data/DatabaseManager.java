package com.alina.creditapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.alina.creditapplication.data.database.Database;
import com.alina.creditapplication.entity.Client;
import com.alina.creditapplication.entity.CreditOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.10.16
 */

public class DatabaseManager extends SQLiteOpenHelper {
    private volatile static DatabaseManager sInstance;

    private DatabaseManager(Context context) {
        super(context, Database.NAME, null, Database.VERSION);
    }

    public static synchronized DatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseManager(context);
        }

        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Database.CREDIT_OFFER_TABLE.CREATE);
        sqLiteDatabase.execSQL(Database.CLIENT.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL(Database.CREDIT_OFFER_TABLE.TABLE_NAME);
            sqLiteDatabase.execSQL(Database.CLIENT.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    public void saveCreditOffer(CreditOffer creditOffer) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(Database.CREDIT_OFFER_TABLE.COLUMN_ID, creditOffer.getId());
            cv.put(Database.CREDIT_OFFER_TABLE.COLUMN_NAME, creditOffer.getName());
            cv.put(Database.CREDIT_OFFER_TABLE.COLUMN_PERCENT, creditOffer.getPercent());
            cv.put(Database.CREDIT_OFFER_TABLE.COLUMN_MAX_PERIOD, creditOffer.getMaxPeriod());
            cv.put(Database.CREDIT_OFFER_TABLE.COLUMN_CURRENCY, creditOffer.getCurrency());

            int rows = db.update(
                    Database.CREDIT_OFFER_TABLE.TABLE_NAME,
                    cv,
                    Database.CREDIT_OFFER_TABLE.COLUMN_ID + "= ?",
                    new String[]{creditOffer.getId()});

            if (rows == 1) {

            } else {
                db.insertOrThrow(Database.CREDIT_OFFER_TABLE.TABLE_NAME, null, cv);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {

        } finally {
            db.endTransaction();
        }
    }

    public List<CreditOffer> getCreditOffers() {
        List<CreditOffer> creditOffers = new ArrayList<>();

        String creditOffersSelect = String.format(
                "SELECT * FROM %s",
                Database.CREDIT_OFFER_TABLE.TABLE_NAME
        );

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(creditOffersSelect, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex(Database.CREDIT_OFFER_TABLE.COLUMN_ID));
                    String name = cursor.getString(cursor.getColumnIndex(Database.CREDIT_OFFER_TABLE.COLUMN_NAME));
                    int percent = cursor.getInt(cursor.getColumnIndex(Database.CREDIT_OFFER_TABLE.COLUMN_PERCENT));
                    int maxPeriod = cursor.getInt(cursor.getColumnIndex(Database.CREDIT_OFFER_TABLE.COLUMN_MAX_PERIOD));
                    String currency = cursor.getString(cursor.getColumnIndex(Database.CREDIT_OFFER_TABLE.COLUMN_CURRENCY));
                    creditOffers.add(new CreditOffer(id, name, percent, maxPeriod, currency));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return creditOffers;
    }

    public void deleteCreditOffer(CreditOffer creditOffer) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(
                    Database.CREDIT_OFFER_TABLE.TABLE_NAME,
                    Database.CREDIT_OFFER_TABLE.COLUMN_ID + "= ?",
                    new String[]{creditOffer.getId()}
            );
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public List<Client> getClientsInCategory(String coid) {
        List<Client> clients = new ArrayList<>();
        String clientsSelect = String.format(
                "SELECT * FROM %s where %s = ?",
                Database.CLIENT.TABLE_NAME,
                Database.CLIENT.COLUMN_CO_ID
        );
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(clientsSelect, new String[]{coid});
        try{
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex(Database.CLIENT.COLUMN_ID));
                    String categoryId = cursor.getString(cursor.getColumnIndex(Database.CLIENT.COLUMN_CO_ID));
                    String currentDay = cursor.getString(cursor.getColumnIndex(Database.CLIENT.COLUMN_CURRENT_DAY));
                    String payDay = cursor.getString(cursor.getColumnIndex(Database.CLIENT.COLUMN_PAY_DAY));
                    String factPayDay = cursor.getString(cursor.getColumnIndex(Database.CLIENT.COLUMN_FACT_PAY));
                    String name = cursor.getString(cursor.getColumnIndex(Database.CLIENT.COLUMN_NAME));
                    String surname = cursor.getString(cursor.getColumnIndex(Database.CLIENT.COLUMN_SURNAME));
                    String phone = cursor.getString(cursor.getColumnIndex(Database.CLIENT.COLUMN_PHONE));
                    int mounth = cursor.getInt(cursor.getColumnIndex(Database.CLIENT.COLUMN_MOUNTH));
                    double amount = cursor.getDouble(cursor.getColumnIndex(Database.CLIENT.COLUMN_AMOUNT));
                    double result = cursor.getDouble(cursor.getColumnIndex(Database.CLIENT.COLUMN_RESULT));
                    clients.add(new Client(id, categoryId, currentDay, payDay, factPayDay, name, surname, phone, mounth, amount, result));
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return clients;
    }

    public void saveClient(Client client) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(Database.CLIENT.COLUMN_ID, client.getId());
            cv.put(Database.CLIENT.COLUMN_CO_ID, client.getCategoryId());
            cv.put(Database.CLIENT.COLUMN_CURRENT_DAY, client.getCurrentDay());
            cv.put(Database.CLIENT.COLUMN_PAY_DAY, client.getPayDay());
            cv.put(Database.CLIENT.COLUMN_FACT_PAY, client.getFactPayDay());
            cv.put(Database.CLIENT.COLUMN_NAME, client.getName());
            cv.put(Database.CLIENT.COLUMN_SURNAME, client.getSurname());
            cv.put(Database.CLIENT.COLUMN_PHONE, client.getPhone());
            cv.put(Database.CLIENT.COLUMN_AMOUNT, client.getAmount());
            cv.put(Database.CLIENT.COLUMN_MOUNTH, client.getMounth());
            cv.put(Database.CLIENT.COLUMN_RESULT, client.getResult());

            int rows = db.update(
                    Database.CLIENT.TABLE_NAME,
                    cv,
                    Database.CLIENT.COLUMN_ID + "= ?",
                    new String[] {client.getId()}
            );
            if (rows == 1) {

            } else {
                db.insertOrThrow(Database.CLIENT.TABLE_NAME, null, cv);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {

        } finally {
            db.endTransaction();
        }
    }

}
