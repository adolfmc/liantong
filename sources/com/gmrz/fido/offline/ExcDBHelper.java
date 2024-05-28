package com.gmrz.fido.offline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;

@NBSInstrumented
/* renamed from: com.gmrz.fido.offline.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ExcDBHelper extends SQLiteOpenHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ExcDBHelper(Context context) {
        super(context, "fidosdkexc.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS sdkrecord (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, TIMESTAMP REAL NOT NULL, OPERATION TEXT NOT NULL, EXC_TYPE TEXT, DESCRIPTION TEXT NOT NULL,MESSAGE TEXT,SN TEXT NOT NULL);");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS sdkrecord (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, TIMESTAMP REAL NOT NULL, OPERATION TEXT NOT NULL, EXC_TYPE TEXT, DESCRIPTION TEXT NOT NULL,MESSAGE TEXT,SN TEXT NOT NULL);");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
