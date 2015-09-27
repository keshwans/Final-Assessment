package nyc.c4q;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import nyc.c4q.models.Book;
import nyc.c4q.models.Member;

/**
 * Created by keshwans on 9/27/15.
 */
public class LibrarySQLiteOpenHelper extends OrmLiteSqliteOpenHelper {
    public static final String TAG = LibrarySQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "library_orm.db";
    public static final int DATABASE_VERSION = 2;

    public LibrarySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);

    }
}
