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
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "library.db";

    private static final String SQL_CREATE_TABLE_MEMBERS =
            "CREATE TABLE " + DatabaseContract.Members.TABLE_NAME + " (" +
                    DatabaseContract.Members._ID + " INTEGER PRIMARY KEY, " +
                    DatabaseContract.Members.COLUMN_NAME_NAME      + " TEXT, " +
                    DatabaseContract.Members.COLUMN_NAME_DOB_MONTH + " INTEGER, " +
                    DatabaseContract.Members.COLUMN_NAME_DOB_DAY   + " INTEGER, " +
                    DatabaseContract.Members.COLUMN_NAME_DOB_YEAR  + " INTEGER, " +
                    DatabaseContract.Members.COLUMN_NAME_CITY      + " TEXT, " +
                    DatabaseContract.Members.COLUMN_NAME_STATE     + " TEXT)";

    private static final String SQL_CREATE_TABLE_BOOKS =
            "CREATE TABLE " + DatabaseContract.Books.TABLE_NAME + " (" +
                    DatabaseContract.Books._ID + " INTEGER PRIMARY KEY, " +
                    DatabaseContract.Books.COLUMN_NAME_TITLE          + " TEXT, " +
                    DatabaseContract.Books.COLUMN_NAME_AUTHOR         + " TEXT, " +
                    DatabaseContract.Books.COLUMN_NAME_ISBN           + " TEXT, " +
                    DatabaseContract.Books.COLUMN_NAME_ISBN13         + " TEXT, " +
                    DatabaseContract.Books.COLUMN_NAME_PUBLISHER      + " TEXT, " +
                    DatabaseContract.Books.COLUMN_NAME_PUBLISH_YEAR   + " INTEGER, " +
                    DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT    + " INTEGER, " +
                    DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT_BY + " TEXT, " +
                    DatabaseContract.Books.COLUMN_NAME_CHECKOUT_MONTH + " INTEGER, " +
                    DatabaseContract.Books.COLUMN_NAME_CHECKOUT_DAY   + " INTEGER, " +
                    DatabaseContract.Books.COLUMN_NAME_CHECKOUT_YEAR  + " INTEGER, " +
                    DatabaseContract.Books.COLUMN_NAME_DUE_MONTH      + " INTEGER, " +
                    DatabaseContract.Books.COLUMN_NAME_DUE_DAY        + " INTEGER, " +
                    DatabaseContract.Books.COLUMN_NAME_DUE_YEAR       + " INTEGER)";

    private static final String SQL_DROP_TABLE_MEMBERS =
            "DROP TABLE IF EXISTS " + DatabaseContract.Members.TABLE_NAME;

    private static final String SQL_DROP_TABLE_BOOKS =
            "DROP TABLE IF EXISTS " + DatabaseContract.Books.TABLE_NAME;


    private Resources resources;
    private String packageName;

    public DatabaseHelper(Context context, Resources resources, String packageName) {
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

    public boolean hasData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM BOOKS", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();
        return count > 0;
    }

    public void populateData() {
        SQLiteDatabase db = getWritableDatabase();
        populateMembersData(db);
        populateBooksData(db);
        db.close();
    }

    private void populateMembersData(SQLiteDatabase db) {
        JSONArray members = getJsonArray("members");
        for (int i = 0, length = members.length(); i != length; ++i) {
            try {
                JSONObject member = members.getJSONObject(i);

                ContentValues values = new ContentValues();
                values.put(DatabaseContract.Members._ID,                   member.getInt("id"));
                values.put(DatabaseContract.Members.COLUMN_NAME_NAME,      member.getString("name"));
                values.put(DatabaseContract.Members.COLUMN_NAME_DOB_MONTH, member.getInt("dob_month"));
                values.put(DatabaseContract.Members.COLUMN_NAME_DOB_DAY,   member.getInt("dob_day"));
                values.put(DatabaseContract.Members.COLUMN_NAME_DOB_YEAR,  member.getInt("dob_year"));
                values.put(DatabaseContract.Members.COLUMN_NAME_CITY,      member.getString("city"));
                values.put(DatabaseContract.Members.COLUMN_NAME_STATE,     member.getString("state"));

                db.insert(DatabaseContract.Members.TABLE_NAME, null, values);
            }
            catch (JSONException e) {
                Log.e("JSON EXCEPTION", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void populateBooksData(SQLiteDatabase db) {
        JSONArray books = getJsonArray("books");
        for (int i = 0, length = books.length(); i != length; ++i) {
            try {
                JSONObject book = books.getJSONObject(i);

                ContentValues values = new ContentValues();
                values.put(DatabaseContract.Books._ID,                      book.getInt("id"));
                values.put(DatabaseContract.Books.COLUMN_NAME_TITLE,        book.getString("title"));
                values.put(DatabaseContract.Books.COLUMN_NAME_AUTHOR,       book.getString("author"));
                values.put(DatabaseContract.Books.COLUMN_NAME_ISBN,         book.getString("isbn"));
                values.put(DatabaseContract.Books.COLUMN_NAME_ISBN13,       book.getString("isbn13"));
                values.put(DatabaseContract.Books.COLUMN_NAME_PUBLISHER,    book.getString("publisher"));
                values.put(DatabaseContract.Books.COLUMN_NAME_PUBLISH_YEAR, book.getInt("publishyear"));

                if (book.has("checkedout") && book.getBoolean("checkedout")) {
                    values.put(DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT,    book.getBoolean("checkedout") ? 1 : 0);
                    values.put(DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT_BY, book.getInt("checkedoutby"));
                    values.put(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_MONTH, book.getInt("checkoutdatemonth"));
                    values.put(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_DAY,   book.getInt("checkoutdateday"));
                    values.put(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_YEAR,  book.getInt("checkoutdateyear"));
                    values.put(DatabaseContract.Books.COLUMN_NAME_DUE_MONTH,      book.getInt("duedatemonth"));
                    values.put(DatabaseContract.Books.COLUMN_NAME_DUE_DAY,        book.getInt("duedateday"));
                    values.put(DatabaseContract.Books.COLUMN_NAME_DUE_YEAR,       book.getInt("duedateyear"));
                }

                db.insert(DatabaseContract.Books.TABLE_NAME, null, values);
            }
            catch (JSONException e) {
                Log.e("JSON EXCEPTION", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private JSONArray getJsonArray(String file) {
        int resId = resources.getIdentifier(file, "raw", packageName);
        InputStream is = resources.openRawResource(resId);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (IOException e) {
            Log.e("DATABASE EXCEPTION", e.getMessage());
            e.printStackTrace();
        }

        String json = sb.toString();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        }
        catch (JSONException e) {
            Log.e("JSON EXCEPTION", e.getMessage());
            e.printStackTrace();
        }

        return jsonArray;
    }

    public String getMember(String name) {
        SQLiteDatabase db = getReadableDatabase();

        final String[] projection = {
                DatabaseContract.Members._ID,
                DatabaseContract.Members.COLUMN_NAME_NAME,
                DatabaseContract.Members.COLUMN_NAME_DOB_MONTH,
                DatabaseContract.Members.COLUMN_NAME_DOB_DAY,
                DatabaseContract.Members.COLUMN_NAME_DOB_YEAR,
                DatabaseContract.Members.COLUMN_NAME_CITY,
                DatabaseContract.Members.COLUMN_NAME_STATE
        };

        final String selection = DatabaseContract.Members.COLUMN_NAME_NAME + "=?";

        final String[] selectionArgs = { name };

        Cursor cursor = db.query(DatabaseContract.Members.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() < 1) { return null; }

        cursor.moveToFirst();
        String result =
                "id: " + cursor.getInt(0) + "\n" +
                "name: " + cursor.getString(1) + "\n" +
                "dob: " + cursor.getInt(2) + "/" + cursor.getInt(3) + "/" + cursor.getInt(4) + "\n" +
                "location: " + cursor.getString(5) + ", " + cursor.getString(6);

        db.close();
        return result;
    }

    public String getBook(String isbn) {
        SQLiteDatabase db = getReadableDatabase();

        final String[] projection = {
                DatabaseContract.Books._ID,
                DatabaseContract.Books.COLUMN_NAME_TITLE,
                DatabaseContract.Books.COLUMN_NAME_AUTHOR,
                DatabaseContract.Books.COLUMN_NAME_ISBN,
                DatabaseContract.Books.COLUMN_NAME_ISBN13,
                DatabaseContract.Books.COLUMN_NAME_PUBLISHER,
                DatabaseContract.Books.COLUMN_NAME_PUBLISH_YEAR
        };

        final String selection = DatabaseContract.Books.COLUMN_NAME_ISBN + "=?";

        final String[] selectionArgs = { isbn };

        Cursor cursor = db.query(DatabaseContract.Books.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() < 1) { return null; }

        cursor.moveToFirst();
        String result =
                "id: " + cursor.getInt(0) + "\n" +
                "title: " + cursor.getString(1) + "\n" +
                "author: " + cursor.getString(2) + "\n" +
                "isbn: " + cursor.getString(3) + "\n" +
                "isbn13: " + cursor.getString(4) + "\n" +
                "publisher: " + cursor.getString(5) + "\n" +
                "publication year: " + cursor.getInt(6);

        db.close();
        return result;
    }

    public String getCheckedOut(String name) {
        SQLiteDatabase db = getReadableDatabase();

        final String query =
                "SELECT b." + DatabaseContract.Books.COLUMN_NAME_TITLE +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_AUTHOR +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_CHECKOUT_MONTH +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_CHECKOUT_DAY +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_CHECKOUT_YEAR +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_DUE_MONTH +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_DUE_DAY +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_DUE_YEAR +
                        " FROM " + DatabaseContract.Members.TABLE_NAME + " m" +
                        " JOIN " + DatabaseContract.Books.TABLE_NAME + " b" +
                        " ON m." + DatabaseContract.Members._ID + "=b." + DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT_BY +
                        " WHERE m." + DatabaseContract.Members.COLUMN_NAME_NAME + "=?" +
                        " ORDER BY b." + DatabaseContract.Books.COLUMN_NAME_DUE_YEAR + " ASC" +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_DUE_MONTH + " ASC" +
                        ", b." + DatabaseContract.Books.COLUMN_NAME_DUE_DAY + " ASC";

        final String[] selectionArgs = { name };

        Cursor cursor = db.rawQuery(query, selectionArgs);

        StringBuilder sb = new StringBuilder("name: " + name);

        while (cursor.moveToNext()) {
            sb.append(
                    "\n-----\n" +
                    "title: " + cursor.getString(0) + "\n" +
                    "author: " + cursor.getString(1) + "\n" +
                    "checkout date: " + cursor.getInt(2) + "/" + cursor.getInt(3) + "/" + cursor.getInt(4) + "\n" +
                    "due date: " + cursor.getInt(5) + "/" + cursor.getInt(6) + "/" + cursor.getInt(7)
            );
        }

        db.close();
        return sb.toString();
    }

    public void checkOut(int memberId, int bookId) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT, 1);
        values.put(DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT_BY, memberId);

        Calendar calendar = Calendar.getInstance();
        values.put(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_YEAR,  calendar.get(Calendar.YEAR));
        values.put(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_MONTH, calendar.get(Calendar.MONTH) + 1);
        values.put(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_DAY,   calendar.get(Calendar.DATE));

        calendar.add(Calendar.DATE, 14);
        values.put(DatabaseContract.Books.COLUMN_NAME_DUE_YEAR,  calendar.get(Calendar.YEAR));
        values.put(DatabaseContract.Books.COLUMN_NAME_DUE_MONTH, calendar.get(Calendar.MONTH) + 1);
        values.put(DatabaseContract.Books.COLUMN_NAME_DUE_DAY,   calendar.get(Calendar.DATE));

        final String where = DatabaseContract.Books._ID + "=?";
        final String[] whereArgs = { String.valueOf(bookId) };

        db.update(DatabaseContract.Books.TABLE_NAME, values, where, whereArgs);
        db.close();
    }

    public boolean checkIn(int memberId, int bookId) {
        boolean isLate = checkLate(memberId, bookId);

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT, 0);
        values.putNull(DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT_BY);
        values.putNull(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_YEAR);
        values.putNull(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_MONTH);
        values.putNull(DatabaseContract.Books.COLUMN_NAME_CHECKOUT_DAY);
        values.putNull(DatabaseContract.Books.COLUMN_NAME_DUE_YEAR);
        values.putNull(DatabaseContract.Books.COLUMN_NAME_DUE_MONTH);
        values.putNull(DatabaseContract.Books.COLUMN_NAME_DUE_DAY);

        final String where = DatabaseContract.Books._ID + "=?";
        final String[] whereArgs = { String.valueOf(bookId) };

        db.update(DatabaseContract.Books.TABLE_NAME, values, where, whereArgs);
        db.close();

        return isLate;
    }

    private boolean checkLate(int memberId, int bookId) {
        SQLiteDatabase db = getReadableDatabase();

        final String[] projection = {
                DatabaseContract.Books.COLUMN_NAME_DUE_YEAR,
                DatabaseContract.Books.COLUMN_NAME_DUE_MONTH,
                DatabaseContract.Books.COLUMN_NAME_DUE_DAY
        };

        final String selection = DatabaseContract.Books._ID + "=? AND " +
                DatabaseContract.Books.COLUMN_NAME_CHECKED_OUT_BY + "=?";

        final String[] selectionArgs = {
                String.valueOf(bookId),
                String.valueOf(memberId)
        };

        Cursor cursor = db.query(DatabaseContract.Books.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() < 1) { return false; }

        cursor.moveToFirst();
        Calendar dueDate = Calendar.getInstance();
        dueDate.set(cursor.getInt(0), cursor.getInt(1) - 1, cursor.getInt(2));

        db.close();
        return dueDate.after(Calendar.getInstance());
    }
}
