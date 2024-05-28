package com.mob.mcl.p236d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.mob.mcl.d.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5956a extends SQLiteOpenHelper {
    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public C5956a(Context context) {
        super(context, "elp_msg.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            SQLiteDatabase.class.getMethod("execSQL", String.class).invoke(sQLiteDatabase, "CREATE TABLE msg (workId TEXT PRIMARY KEY,expireTime INTEGER )");
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
        }
    }
}
