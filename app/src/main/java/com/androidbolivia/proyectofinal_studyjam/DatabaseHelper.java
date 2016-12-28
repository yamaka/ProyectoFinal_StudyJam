package com.androidbolivia.proyectofinal_studyjam;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "PERSONS";

    // Table  person columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String STATE = "state";



    // Table Name Presents
    public static final String TABLE_NAME_PRESENT = "PRESENTS";

    // Table  present columns
    public static final String _ID_PRESENT = "_id";
    public static final String NAME_PRESENT = "name_present";
    public static final String STATE_PRESENT = "state_present";
    public static final String _ID_FOREIGN_PERSON = "_id_person";



    // Database Information
    static final String DB_NAME = "PERSON.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + STATE + " TEXT);";

    // Creating table presemt
    private static final String CREATE_TABLE_PRESENTS = "create table " + TABLE_NAME_PRESENT + "("
            + _ID_PRESENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_PRESENT + " TEXT, "
            + STATE_PRESENT + " INTEGER, "
            + _ID_FOREIGN_PERSON + " INTEGER );";


    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_PRESENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRESENT);
        onCreate(db);
    }
}
