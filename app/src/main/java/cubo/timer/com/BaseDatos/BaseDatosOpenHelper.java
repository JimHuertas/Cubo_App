package cubo.timer.com.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cubo.timer.com.BaseDatos.FeedReaderTimer.FeedEntry;

public class BaseDatosOpenHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES  =
            "CREATE TABLE IF NOT EXISTS " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedEntry.COLUMN_SCR + " VARCHAR(50), " +
                    FeedEntry.COLUMN_TIME + " VARCHAR(100)," +
                    FeedEntry.COLUMN_DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DATO.db";

    public BaseDatosOpenHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
