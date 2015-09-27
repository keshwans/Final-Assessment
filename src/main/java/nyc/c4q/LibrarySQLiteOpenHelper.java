package nyc.c4q;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by keshwans on 9/27/15.
 */
public class LibrarySQLiteOpenHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "library_orm.db";
    public static final int DATABASE_VERSION = 1;

    public LibrarySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
