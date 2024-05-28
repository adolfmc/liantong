package com.baidu.cloud.videocache.sourcestorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.baidu.cloud.videocache.C2571g;
import com.baidu.cloud.videocache.C2577m;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
class oia extends SQLiteOpenHelper implements SourceInfoStorage {

    /* renamed from: a */
    private static final String[] f4944a = {"_id", "url", "length", "mime"};

    /* JADX INFO: Access modifiers changed from: package-private */
    public oia(Context context) {
        super(context, "AndroidVideoCache.db", (SQLiteDatabase.CursorFactory) null, 1);
        C2571g.m19807a(context);
    }

    /* renamed from: a */
    private ContentValues m19728a(C2577m c2577m) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", c2577m.f4896a);
        contentValues.put("length", Long.valueOf(c2577m.f4897b));
        contentValues.put("mime", c2577m.f4898c);
        return contentValues;
    }

    /* renamed from: a */
    private C2577m m19729a(Cursor cursor) {
        return new C2577m(cursor.getString(cursor.getColumnIndexOrThrow("url")), cursor.getLong(cursor.getColumnIndexOrThrow("length")), cursor.getString(cursor.getColumnIndexOrThrow("mime")));
    }

    @Override // com.baidu.cloud.videocache.sourcestorage.SourceInfoStorage
    public C2577m get(String str) {
        C2571g.m19807a(str);
        Cursor cursor = null;
        r0 = null;
        C2577m m19729a = null;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            String[] strArr = f4944a;
            String[] strArr2 = {str};
            Cursor query = !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.query("SourceInfo", strArr, "url=?", strArr2, null, null, null) : NBSSQLiteInstrumentation.query(readableDatabase, "SourceInfo", strArr, "url=?", strArr2, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        m19729a = m19729a(query);
                    }
                } catch (Throwable th) {
                    cursor = query;
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return m19729a;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        C2571g.m19807a(sQLiteDatabase);
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER);");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER);");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new IllegalStateException("Should not be called. There is no any migration");
    }

    @Override // com.baidu.cloud.videocache.sourcestorage.SourceInfoStorage
    public void put(String str, C2577m c2577m) {
        C2571g.m19803a(str, c2577m);
        boolean z = get(str) != null;
        ContentValues m19728a = m19728a(c2577m);
        if (!z) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.insert(writableDatabase, "SourceInfo", null, m19728a);
                return;
            } else {
                writableDatabase.insert("SourceInfo", null, m19728a);
                return;
            }
        }
        SQLiteDatabase writableDatabase2 = getWritableDatabase();
        String[] strArr = {str};
        if (writableDatabase2 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.update(writableDatabase2, "SourceInfo", m19728a, "url=?", strArr);
        } else {
            writableDatabase2.update("SourceInfo", m19728a, "url=?", strArr);
        }
    }

    @Override // com.baidu.cloud.videocache.sourcestorage.SourceInfoStorage
    public void release() {
        close();
    }
}
