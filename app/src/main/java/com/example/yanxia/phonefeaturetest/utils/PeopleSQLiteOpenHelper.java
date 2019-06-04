package com.example.yanxia.phonefeaturetest.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;

import com.example.yanxia.phonefeaturetest.MyApplication;
import com.example.yanxia.phonefeaturetest.dataModel.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleSQLiteOpenHelper extends SQLiteOpenHelper {

    private static PeopleSQLiteOpenHelper instance;
    private SQLiteDatabase database;

    private static final String DB_NAME = "people_data.db";
    private static final int VERSION = 1;

    private static final String TABLE_PEOPLE_NAME = "people";

    private static final String ID = "_id";
    private static final String PEOPLE_ID = "people_id";
    private static final String PEOPLE_NAME = "people_name";
    private static final String PEOPLE_BIRTH_PLACE = "people_birth_place";
    private static final String PEOPLE_RACE = "people_race";

    private static final String PEOPLE_TABLE_CREATE_STRING =
            "CREATE TABLE " + TABLE_PEOPLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY, "
                    + PEOPLE_ID + " INTEGER, "
                    + PEOPLE_NAME + " TEXT, "
                    + PEOPLE_BIRTH_PLACE + " TEXT, "
                    + PEOPLE_RACE + " TEXT)";

    private PeopleSQLiteOpenHelper() {
        super(MyApplication.getContext(), DB_NAME, null, VERSION);
        try {
            database = getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized PeopleSQLiteOpenHelper getInstance() {
        if (instance == null) {
            synchronized (PeopleSQLiteOpenHelper.class) {
                if (null == instance) {
                    instance = new PeopleSQLiteOpenHelper();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(PEOPLE_TABLE_CREATE_STRING);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<People> queryPeopleList() {
        if (database == null) {
            return new ArrayList<>();
        }
        ArrayList<People> peopleArrayList = new ArrayList<>();
        String orderBy = ID + " DESC";//倒序排列，最新的在最下面

        Cursor cursor = database.query(TABLE_PEOPLE_NAME,
                null, null, null, null, null, orderBy);
        while (cursor.moveToNext()) {
            long peopleId = cursor.getLong(cursor.getColumnIndex(PEOPLE_ID));
            String name = cursor.getString(cursor.getColumnIndex(PEOPLE_NAME));
            String birthPlace = cursor.getString(cursor.getColumnIndex(PEOPLE_BIRTH_PLACE));
            String race = cursor.getString(cursor.getColumnIndex(PEOPLE_RACE));
            People people = new People(peopleId, name, birthPlace, race);
            peopleArrayList.add(people);
        }
        cursor.close();
        return peopleArrayList;
    }

    public boolean insertPeople(@NonNull People people) {
        try {
            return database.insert(TABLE_PEOPLE_NAME, null, getInsertPeopleContentValues(people)) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertPeopleList(List<People> peopleList) {
        if (database == null) {
            return false;
        }

        boolean result;
        database.beginTransaction();
        try {
            for (People people : peopleList) {
                database.insert(TABLE_PEOPLE_NAME, null, getInsertPeopleContentValues(people));
            }
            database.setTransactionSuccessful();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            database.endTransaction();
        }
        return result;
    }

    @NonNull
    private ContentValues getInsertPeopleContentValues(@NonNull People people) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PEOPLE_ID, people.getId());
        contentValues.put(PEOPLE_NAME, people.getName());
        contentValues.put(PEOPLE_BIRTH_PLACE, people.getBirthPlace());
        contentValues.put(PEOPLE_RACE, people.getRace());
        return contentValues;
    }

    public boolean deletePeople(People people) {
        try {
            return database.delete(TABLE_PEOPLE_NAME,
                    PEOPLE_ID + "= ?", new String[]{String.valueOf(people.getId())}) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
