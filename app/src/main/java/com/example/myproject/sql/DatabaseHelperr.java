package com.example.myproject.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myproject.model.Beneficiary;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class DatabaseHelperr extends SQLiteOpenHelper {

    // Database Name
    private static final String DB_NAME = "Recorddb";
    // Database Version
    private static final int DB_VERSION = 1;
    // below variable is for our table name.
    private static final String TABLE_NAME = "myrecords";
    // below variable is for our id column.
    private static final String COLUMN_BENEFICIARY_ID = "ID";
    private static final String COLUMN_BENEFICIARY_EMAIL = "EMAIL";
    private static final String COLUMN_BENEFICIARY_ENROLLMENT = "ENROLLMENT";
    private static final String COLUMN_BENEFICIARY_SEMESTER = "SEMESTER";
    private static final String COLUMN_BENEFICIARY_SUBJECT1 = "SUBJECT1";
    private static final String COLUMN_BENEFICIARY_SUBJECT2 = "SUBJECT2";
    private static final String COLUMN_BENEFICIARY_SUBJECT3= "SUBJECT3";
    private static final String COLUMN_BENEFICIARY_SUBJECT4 = "SUBJECT4";
    private static final String COLUMN_BENEFICIARY_SUBJECT5 = "SUBJECT5";
    private static final String COLUMN_BENEFICIARY_MARKS1 = "MARKS1";
    private static final String COLUMN_BENEFICIARY_MARKS2 = "MARKS2";
    private static final String COLUMN_BENEFICIARY_MARKS3 = "MARKS3";
    private static final String COLUMN_BENEFICIARY_MARKS4 = "MARKS4";
    private static final String COLUMN_BENEFICIARY_MARKS5 = "MARKS5";
    private static final String COLUMN_BENEFICIARY_MARKSPRACT = "PRACTICAL";
    private static final String COLUMN_BENEFICIARY_MARKSATTD = "ATTENDANCE";
    private static final String COLUMN_BENEFICIARY_PERCENT = "PERCENT";
    private static final String COLUMN_BENEFICIARY_GRADE = "GRADE";

    public DatabaseHelperr(Context context) { super(context,DB_NAME,null,DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " +TABLE_NAME + " (" +
                COLUMN_BENEFICIARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_BENEFICIARY_EMAIL + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_ENROLLMENT + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_SEMESTER + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_SUBJECT1 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_SUBJECT2 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_SUBJECT3 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_SUBJECT4 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_SUBJECT5 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_MARKS1 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_MARKS2 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_MARKS3 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_MARKS4 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_MARKS5 + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_MARKSPRACT  + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_MARKSATTD  + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_PERCENT  + " TEXT NOT NULL, " +
                COLUMN_BENEFICIARY_GRADE  + " TEXT NOT NULL " +
                "); ";

        db.execSQL(query);
    }


    public void addNewBeneficiary(String email, String enroll, String sem,
                                  String sub1, String sub2, String sub3,
                                  String sub4, String sub5, String mar1,
                                  String mar2, String mar3, String mar4, String mar5,
                                  String marprac, String marattd, String perc, String grad) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(COLUMN_BENEFICIARY_EMAIL, email);
        values.put(COLUMN_BENEFICIARY_ENROLLMENT, enroll);
        values.put(COLUMN_BENEFICIARY_SEMESTER, sem);
        values.put(COLUMN_BENEFICIARY_SUBJECT1, sub1);
        values.put(COLUMN_BENEFICIARY_SUBJECT2, sub2);
        values.put(COLUMN_BENEFICIARY_SUBJECT3, sub3);
        values.put(COLUMN_BENEFICIARY_SUBJECT4, sub4);
        values.put(COLUMN_BENEFICIARY_SUBJECT5, sub5);
        values.put(COLUMN_BENEFICIARY_MARKS1, mar1);
        values.put(COLUMN_BENEFICIARY_MARKS2, mar2);
        values.put(COLUMN_BENEFICIARY_MARKS3, mar3);
        values.put(COLUMN_BENEFICIARY_MARKS4, mar4);
        values.put(COLUMN_BENEFICIARY_MARKS5, mar5);
        values.put(COLUMN_BENEFICIARY_MARKSPRACT, marprac);
        values.put(COLUMN_BENEFICIARY_MARKSATTD, marattd);
        values.put(COLUMN_BENEFICIARY_PERCENT, perc);
        values.put(COLUMN_BENEFICIARY_GRADE, grad);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);
        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<Beneficiary> readBeneficiary() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();
// on below line we are creating a cursor with query to read data from database.
        Cursor cursorRecords = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Beneficiary> recordModalArrayList = new ArrayList<>();
        // moving our cursor to first position.
        if (cursorRecords.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                recordModalArrayList.add(new Beneficiary(
                        cursorRecords.getString(1),
                        cursorRecords.getString(2),
                        cursorRecords.getString(3),
                        cursorRecords.getString(4),
                        cursorRecords.getString(5),
                        cursorRecords.getString(6),
                        cursorRecords.getString(7),
                        cursorRecords.getString(8),
                        cursorRecords.getString(9),
                        cursorRecords.getString(10),
                        cursorRecords.getString(11),
                        cursorRecords.getString(12),
                        cursorRecords.getString(13),
                        cursorRecords.getString(14),
                        cursorRecords.getString(15),
                        cursorRecords.getString(16),
                        cursorRecords.getString(17)));
            } while (cursorRecords.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorRecords.close();
        return recordModalArrayList;
    }

    // we have created a new method for reading all the courses.
    public ArrayList<Beneficiary> readSingleBeneficiary(String email) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        String[] params = new String[]{ email };

// on below line we are creating a cursor with query to read data from database.
        Cursor cursorRecords = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL=?",params);


        // on below line we are creating a new array list.
        ArrayList<Beneficiary> studentrecordArraylist = new ArrayList<>();
        // moving our cursor to first position.
        if (cursorRecords.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                studentrecordArraylist.add(new Beneficiary(
                        cursorRecords.getString(1),
                        cursorRecords.getString(2),
                        cursorRecords.getString(3),
                        cursorRecords.getString(4),
                        cursorRecords.getString(5),
                        cursorRecords.getString(6),
                        cursorRecords.getString(7),
                        cursorRecords.getString(8),
                        cursorRecords.getString(9),
                        cursorRecords.getString(10),
                        cursorRecords.getString(11),
                        cursorRecords.getString(12),
                        cursorRecords.getString(13),
                        cursorRecords.getString(14),
                        cursorRecords.getString(15),
                        cursorRecords.getString(16),
                        cursorRecords.getString(17)));
            } while (cursorRecords.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorRecords.close();
        return studentrecordArraylist;
    }


    // below is the method for updating our courses
    public void updateBeneficiary( String originalEmail, String originalSem, String email, String enroll, String sem,
                                   String sub1, String sub2, String sub3,
                                   String sub4, String sub5, String mar1,
                                   String mar2, String mar3, String mar4, String mar5,
                                   String marprac, String marattd, String perc, String grad) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(COLUMN_BENEFICIARY_EMAIL, email);
        values.put(COLUMN_BENEFICIARY_ENROLLMENT, enroll);
        values.put(COLUMN_BENEFICIARY_SEMESTER, sem);
        values.put(COLUMN_BENEFICIARY_SUBJECT1, sub1);
        values.put(COLUMN_BENEFICIARY_SUBJECT2, sub2);
        values.put(COLUMN_BENEFICIARY_SUBJECT3, sub3);
        values.put(COLUMN_BENEFICIARY_SUBJECT4, sub4);
        values.put(COLUMN_BENEFICIARY_SUBJECT5, sub5);
        values.put(COLUMN_BENEFICIARY_MARKS1, mar1);
        values.put(COLUMN_BENEFICIARY_MARKS2, mar2);
        values.put(COLUMN_BENEFICIARY_MARKS3, mar3);
        values.put(COLUMN_BENEFICIARY_MARKS4, mar4);
        values.put(COLUMN_BENEFICIARY_MARKS5, mar5);
        values.put(COLUMN_BENEFICIARY_MARKSPRACT, marprac);
        values.put(COLUMN_BENEFICIARY_MARKSATTD, marattd);
        values.put(COLUMN_BENEFICIARY_PERCENT, perc);
        values.put(COLUMN_BENEFICIARY_GRADE, grad);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "EMAIL=? and SEMESTER=?", new String[]{originalEmail,originalSem});
        db.close();
    }

    // below is the method for deleting our course.
    public void deleteBeneficiary(String originalEmail, String originalSem) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "EMAIL=? and SEMESTER=?", new String[]{originalEmail, originalSem});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
