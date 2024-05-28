package com.xiaomi.push.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.xiaomi.push.C11392fz;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TrafficProvider extends ContentProvider {

    /* renamed from: a */
    private SQLiteOpenHelper f23367a;

    /* renamed from: a */
    public static final Uri f23366a = Uri.parse("content://com.xiaomi.push.providers.TrafficProvider/traffic");

    /* renamed from: a */
    private static final UriMatcher f23365a = new UriMatcher(-1);

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    static {
        f23365a.addURI("com.xiaomi.push.providers.TrafficProvider", "traffic", 1);
        f23365a.addURI("com.xiaomi.push.providers.TrafficProvider", "update_imsi", 2);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f23367a = new C11477a(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor query;
        synchronized (C11477a.f23369a) {
            if (f23365a.match(uri) == 1) {
                SQLiteDatabase readableDatabase = this.f23367a.getReadableDatabase();
                query = !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.query("traffic", strArr, str, strArr2, null, null, str2) : NBSSQLiteInstrumentation.query(readableDatabase, "traffic", strArr, str, strArr2, null, null, str2);
            } else {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
        return query;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        if (f23365a.match(uri) == 1) {
            return "vnd.android.cursor.dir/vnd.xiaomi.push.traffic";
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (f23365a.match(uri) == 2 && contentValues != null && contentValues.containsKey("imsi")) {
            C11392fz.m3729a(contentValues.getAsString("imsi"));
            return 0;
        }
        return 0;
    }
}
