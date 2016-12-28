package com.androidbolivia.proyectofinal_studyjam;


/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {

        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {

        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.STATE, desc);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.STATE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.STATE, desc);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
    public void insertPresent(String name, int state, int foreign_key) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME_PRESENT, name);
        contentValue.put(DatabaseHelper.STATE_PRESENT, state);
        contentValue.put(DatabaseHelper._ID_FOREIGN_PERSON, foreign_key);
        database.insert(DatabaseHelper.TABLE_NAME_PRESENT, null, contentValue);
    }

    public Cursor fetchPresents(Long _id) {
        String[] columns = new String[] {
                DatabaseHelper._ID_PRESENT, DatabaseHelper.NAME_PRESENT,
                DatabaseHelper.STATE_PRESENT, DatabaseHelper._ID_FOREIGN_PERSON
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_PRESENT, columns, DatabaseHelper._ID_FOREIGN_PERSON + "="+_id, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updatePresent(long _id, String name, int state, int foreign_key) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME_PRESENT, name);
        contentValues.put(DatabaseHelper.STATE_PRESENT, state);
        contentValues.put(DatabaseHelper._ID_FOREIGN_PERSON, foreign_key);
        int i = database.update(DatabaseHelper.TABLE_NAME_PRESENT, contentValues, DatabaseHelper._ID_PRESENT + " = " + _id, null);
        return i;
    }

    public void deletePresent(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME_PRESENT, DatabaseHelper._ID_PRESENT + "=" + _id, null);
    }


}
