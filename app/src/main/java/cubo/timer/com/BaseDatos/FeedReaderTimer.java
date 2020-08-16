package cubo.timer.com.BaseDatos;

import android.provider.BaseColumns;

    public class FeedReaderTimer {
        private FeedReaderTimer() {}

        /* Inner class that defines the table contents */
        public static class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "REGISTROS";
            public static final String COLUMN_ID = "ID";
            public static final String COLUMN_SCR = "SCRAMBLE";
            public static final String COLUMN_TIME = "TIEMPO";
            public static final String COLUMN_DATETIME = "DATETIME";
        }
}
