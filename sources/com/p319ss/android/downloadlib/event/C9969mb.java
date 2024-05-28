package com.p319ss.android.downloadlib.event;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.event.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9969mb extends SQLiteOpenHelper {

    /* renamed from: mb */
    static final String[] f19214mb = {"_id", "ad_id", "req_id", "time"};

    public C9969mb(@Nullable Context context) {
        super(context, "click_event", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS click_event(_id INTEGER PRIMARY KEY AUTOINCREMENT,ad_id INTEGER,req_id TEXT,time INTEGER)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS click_event(_id INTEGER PRIMARY KEY AUTOINCREMENT,ad_id INTEGER,req_id TEXT,time INTEGER)");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS click_event");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS click_event");
        }
        onCreate(sQLiteDatabase);
    }
}
