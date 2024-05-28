package com.gmrz.fido.offline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;

@NBSInstrumented
/* renamed from: com.gmrz.fido.offline.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CostTimeDBHelper extends SQLiteOpenHelper {
    public CostTimeDBHelper(Context context) {
        super(context, m15739a(context), (SQLiteDatabase.CursorFactory) null, 1);
    }

    /* renamed from: a */
    private static String m15739a(Context context) {
        return context.getExternalFilesDir(null) + "/database/cost_time.db";
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS auto (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, OP_TYPE TEXT, CLIENT_TYPE TEXT,NET_FIRST REAL DEFAULT 0, SDK_DISCOVERY REAL DEFAULT 0, SDK_OPERATION REAL DEFAULT 0, PROCESS_IN_SDK REAL DEFAULT 0, NET_SECOND REAL DEFAULT 0, TOTAL REAL DEFAULT 0);");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS auto (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, OP_TYPE TEXT, CLIENT_TYPE TEXT,NET_FIRST REAL DEFAULT 0, SDK_DISCOVERY REAL DEFAULT 0, SDK_OPERATION REAL DEFAULT 0, PROCESS_IN_SDK REAL DEFAULT 0, NET_SECOND REAL DEFAULT 0, TOTAL REAL DEFAULT 0);");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS manual (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, OP_TYPE TEXT, CLIENT_TYPE TEXT,NET_FIRST REAL DEFAULT 0, SDK_DISCOVERY REAL DEFAULT 0, SDK_OPERATION REAL DEFAULT 0, PROCESS_IN_SDK REAL DEFAULT 0, NET_SECOND REAL DEFAULT 0, TOTAL REAL DEFAULT 0);");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS manual (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, OP_TYPE TEXT, CLIENT_TYPE TEXT,NET_FIRST REAL DEFAULT 0, SDK_DISCOVERY REAL DEFAULT 0, SDK_OPERATION REAL DEFAULT 0, PROCESS_IN_SDK REAL DEFAULT 0, NET_SECOND REAL DEFAULT 0, TOTAL REAL DEFAULT 0);");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
