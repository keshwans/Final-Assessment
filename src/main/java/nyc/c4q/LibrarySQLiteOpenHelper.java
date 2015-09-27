package nyc.c4q;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;

import nyc.c4q.models.Book;
import nyc.c4q.models.Member;

/**
 * Created by keshwans on 9/27/15.
 */
public class LibrarySQLiteOpenHelper extends OrmLiteSqliteOpenHelper {
    public static final String TAG = LibrarySQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "library_orm.db";
    public static final int DATABASE_VERSION = 2;

    private static LibrarySQLiteOpenHelper mDBHelper;
    private Context mContext;

    protected LibrarySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public static synchronized LibrarySQLiteOpenHelper getInstance(Context context) {
        if (mDBHelper == null) {
            mDBHelper = new LibrarySQLiteOpenHelper(context.getApplicationContext());
        }

        return mDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Book.class);
            TableUtils.createTable(connectionSource, Member.class);
        } catch (SQLException e) {
            Log.e(TAG, "Cannot create database. Error: ", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Member.class, false);
            TableUtils.dropTable(connectionSource, Book.class, false);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);

    }

        /* helper methods for initial loading of database.
       json files for each table (members and books) is included in res/raw folder */

    public boolean hasData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM BOOKS", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        return count > 0;
    }

    public void populateData() {
        populateBooksData();
        populateMemberData();

    }

    private void populateBooksData() {
        ArrayList<Book> bookList = JsonHelper.loadBookJsonRawUsingGson(mContext, R.raw.books);
        for (int i = 0; i < bookList.size(); i++) {

            Book book = bookList.get(i);
            Integer id = book.getId();
            String title = book.getTitle();
            String author = book.getAuthor();
            String isbn = book.getIsbn();
            String isbn13 = book.getIsbn13();
            String publisher = book.getPublisher();
            int publishyear = book.getPublishYear();
            Boolean checkedout = book.getCheckedOut();
            if (checkedout != null) {
                int checkedoutby = book.getCheckedoutBy();
                int checkoutdateyear = book.getCheckoutdateYear();
                int checkoutdatemonth = book.getCheckoutdateMonth();
                int checkoutdateday = book.getCheckoutdateDay();
                int duedateyear = book.getDuedateYear();
                int duedatemonth = book.getDuedateMonth();
                int duedateday = book.getDuedateDay();

                mDBHelper.insertBookCheckedOutData(id, title, author, isbn, isbn13, publisher, publishyear, checkedout,
                        checkedoutby, checkoutdateyear, checkoutdatemonth, checkoutdateday, duedateyear, duedatemonth, duedateday);
            } else {
                mDBHelper.insertBookData(id, title, author, isbn, isbn13, publisher, publishyear);
            }
        }
    }

    private void insertBookData(int id, String title, String author, String isbn,
                                String isbn13, String publisher, int publishyear) throws SQLException {

        Book book = new Book(id, title, author, isbn, isbn13, publisher, publishyear);
        Dao<Book, Integer> dao = null;
        try {
            dao = getDao(Book.class);
            dao.create(book);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    private void insertBookCheckedOutData(int id, String title, String author, String isbn,
                                          String isbn13, String publisher, int publishyear,
                                          Boolean checkedout, int checkedoutby, int checkoutdateyear,
                                          int checkoutdatemonth, int checkoutdateday, int duedateyear,
                                          int duedatemonth, int duedateday) throws SQLException {

        Book book = new Book(id, title, author, isbn, isbn13, publisher, publishyear, checkedout,
                checkedoutby, checkoutdateyear, checkoutdatemonth, checkoutdateday, duedateyear, duedatemonth, duedateday);
        Dao<Book, Integer> dao = null;
        try {
            dao = getDao(Book.class);
            dao.create(book);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    /* populate member data from json file */
    private void populateMemberData() {
        ArrayList<Member> memberList = JsonHelper.loadMemberJsonRawUsingGson(mContext, R.raw.members);
        for (int i = 0; i < memberList.size(); i++) {

            Member member = memberList.get(i);
            String name = member.getName();
            Integer memberId = member.getId();
            String city = member.getCity();
            String state = member.getState();
            int dobDay = member.getDobDay();
            int dobMonth = member.getDobMonth();
            int dobYear = member.getDobYear();

            mDBHelper.insertMemberData(memberId, name, dobMonth, dobDay, dobYear, city, state);
        }
    }

    private void insertMemberData(Integer id, String name, int dobMonth, int dobDay, int dobYear, String city, String state) throws SQLException {

        Member member = new Member(id, name, dobMonth, dobDay, dobYear, city, state);
        Dao<Member, Integer> dao = null;
        try {
            dao = getDao(Member.class);
            dao.create(member);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Member loadMemberData(String name) throws SQLException, java.sql.SQLException {
        return getDao(Member.class)
                .queryBuilder()
                .where().eq("name", name)
                .queryForFirst();
    }

    public Book loadBookData(String isbn) throws SQLException, java.sql.SQLException {
        return getDao(Book.class)
                .queryBuilder()
                .where().eq("isbn", isbn)
                .queryForFirst();
    }

}

