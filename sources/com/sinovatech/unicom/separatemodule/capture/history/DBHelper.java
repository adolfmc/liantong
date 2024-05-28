package com.sinovatech.unicom.separatemodule.capture.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "barcode_scanner_history.db";
    private static final int DB_VERSION = 5;
    static final String DETAILS_COL = "details";
    static final String DISPLAY_COL = "display";
    static final String FORMAT_COL = "format";
    static final String ID_COL = "id";
    static final String TABLE_NAME = "history";
    static final String TEXT_COL = "text";
    static final String TIMESTAMP_COL = "timestamp";

    /* JADX INFO: Access modifiers changed from: package-private */
    public DBHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 5);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE history (id INTEGER PRIMARY KEY autoincrement, text TEXT, format TEXT, display TEXT, timestamp INTEGER, details TEXT);");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE history (id INTEGER PRIMARY KEY autoincrement, text TEXT, format TEXT, display TEXT, timestamp INTEGER, details TEXT);");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS history");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
        }
        onCreate(sQLiteDatabase);
    }
}
