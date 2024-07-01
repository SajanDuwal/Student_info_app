package com.sajiman.jasonapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "_student_info_db";
    public static final String TABLE_NAME = "_info_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMAGE_PATH = "_image_path";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_STUDENT_ID = "_student_id";
    public static final String COLUMN_ORGANIZATION = "_organization";
    public static final String COLUMN_FACULTY = "_faculty";
    public static final String COLUMN_ROLL = "_roll";


    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STUDENT_ID + " TEXT," +
                COLUMN_IMAGE_PATH + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ORGANIZATION + " TEXT, " +
                COLUMN_FACULTY + " TEXT, " +
                COLUMN_ROLL + " INEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
