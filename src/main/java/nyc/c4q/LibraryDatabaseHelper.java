package nyc.c4q;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * Created by robert on 9/26/15.
 */
public class LibraryDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "library.db";

    private static final String SQL_CREATE_TABLE_MEMBERS =
            "CREATE TABLE " + LibraryDatabaseContract.Members.TABLE_NAME + " (" +
                    LibraryDatabaseContract.Members._ID + " INTEGER PRIMARY KEY, " +
                    LibraryDatabaseContract.Members.COLUMN_NAME_NAME      + " TEXT, " +
                    LibraryDatabaseContract.Members.COLUMN_NAME_DOB_MONTH + " INTEGER, " +
                    LibraryDatabaseContract.Members.COLUMN_NAME_DOB_DAY   + " INTEGER, " +
                    LibraryDatabaseContract.Members.COLUMN_NAME_DOB_YEAR  + " INTEGER, " +
                    LibraryDatabaseContract.Members.COLUMN_NAME_CITY      + " TEXT, " +
                    LibraryDatabaseContract.Members.COLUMN_NAME_STATE     + " TEXT)";

    private static final String SQL_CREATE_TABLE_BOOKS =
            "CREATE TABLE " + LibraryDatabaseContract.Books.TABLE_NAME + " (" +
                    LibraryDatabaseContract.Books._ID + " INTEGER PRIMARY KEY, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_TITLE          + " TEXT, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_AUTHOR         + " TEXT, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_ISBN           + " TEXT, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_ISBN13         + " TEXT, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_PUBLISHER      + " TEXT, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_PUBLISH_YEAR   + " INTEGER, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_CHECKED_OUT    + " INTEGER, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_CHECKED_OUT_BY + " TEXT, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_CHECKOUT_MONTH + " INTEGER, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_CHECKOUT_DAY   + " INTEGER, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_CHECKOUT_YEAR  + " INTEGER, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_DUE_MONTH      + " INTEGER, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_DUE_DAY        + " INTEGER, " +
                    LibraryDatabaseContract.Books.COLUMN_NAME_DUE_YEAR       + " INTEGER)";

    private static final String SQL_DROP_TABLE_MEMBERS =
            "DROP TABLE IF EXISTS " + LibraryDatabaseContract.Members.TABLE_NAME;

    private static final String SQL_DROP_TABLE_BOOKS =
            "DROP TABLE IF EXISTS " + LibraryDatabaseContract.Books.TABLE_NAME;


    private Resources resources;
    private String packageName;

    public LibraryDatabaseHelper(Context context, Resources resources, String packageName) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.resources = resources;
        this.packageName = packageName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MEMBERS);
        db.execSQL(SQL_CREATE_TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_MEMBERS);
        db.execSQL(SQL_DROP_TABLE_BOOKS);
        onCreate(db);
    }


}
