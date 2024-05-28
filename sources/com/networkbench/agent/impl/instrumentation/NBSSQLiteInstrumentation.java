package com.networkbench.agent.impl.instrumentation;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.CancellationSignal;
import com.networkbench.agent.impl.harvest.type.MetricCategory;
import com.networkbench.agent.impl.util.C6653u;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSSQLiteInstrumentation {
    private static final ArrayList<String> categoryParams = new ArrayList<>(Arrays.asList("category", MetricCategory.class.getName(), "DATABASE"));

    @Deprecated
    /* renamed from: a */
    void m9876a() {
    }

    @NBSReplaceCallSite
    public static Cursor query(SQLiteDatabase sQLiteDatabase, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#query", categoryParams);
        Cursor query = sQLiteDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
        NBSTraceEngine.exitMethod();
        return query;
    }

    @NBSReplaceCallSite
    public static Cursor query(SQLiteDatabase sQLiteDatabase, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#query", categoryParams);
        Cursor query = sQLiteDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6, cancellationSignal);
        NBSTraceEngine.exitMethod();
        return query;
    }

    @NBSReplaceCallSite
    public static Cursor query(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#query", categoryParams);
        Cursor query = sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5);
        NBSTraceEngine.exitMethod();
        return query;
    }

    @NBSReplaceCallSite
    public static Cursor query(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#query", categoryParams);
        Cursor query = sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        NBSTraceEngine.exitMethod();
        return query;
    }

    @NBSReplaceCallSite
    public static Cursor queryWithFactory(SQLiteDatabase sQLiteDatabase, SQLiteDatabase.CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#queryWithFactory", categoryParams);
        Cursor queryWithFactory = sQLiteDatabase.queryWithFactory(cursorFactory, z, str, strArr, str2, strArr2, str3, str4, str5, str6);
        NBSTraceEngine.exitMethod();
        return queryWithFactory;
    }

    @NBSReplaceCallSite
    public static Cursor queryWithFactory(SQLiteDatabase sQLiteDatabase, SQLiteDatabase.CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#queryWithFactory", categoryParams);
        Cursor queryWithFactory = sQLiteDatabase.queryWithFactory(cursorFactory, z, str, strArr, str2, strArr2, str3, str4, str5, str6, cancellationSignal);
        NBSTraceEngine.exitMethod();
        return queryWithFactory;
    }

    @NBSReplaceCallSite
    public static Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#rawQuery", categoryParams);
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, strArr);
        NBSTraceEngine.exitMethod();
        return rawQuery;
    }

    @NBSReplaceCallSite
    public static Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, CancellationSignal cancellationSignal) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#rawQuery", categoryParams);
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, strArr, cancellationSignal);
        NBSTraceEngine.exitMethod();
        return rawQuery;
    }

    @NBSReplaceCallSite
    public static Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, SQLiteDatabase.CursorFactory cursorFactory, String str, String[] strArr, String str2) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#rawQueryWithFactory", categoryParams);
        Cursor rawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory, str, strArr, str2);
        NBSTraceEngine.exitMethod();
        return rawQueryWithFactory;
    }

    @NBSReplaceCallSite
    public static Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, SQLiteDatabase.CursorFactory cursorFactory, String str, String[] strArr, String str2, CancellationSignal cancellationSignal) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#rawQueryWithFactory", categoryParams);
        Cursor rawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory, str, strArr, str2, cancellationSignal);
        NBSTraceEngine.exitMethod();
        return rawQueryWithFactory;
    }

    @NBSReplaceCallSite
    public static long insert(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#insert", categoryParams);
        long insert = sQLiteDatabase.insert(str, str2, contentValues);
        NBSTraceEngine.exitMethod();
        return insert;
    }

    @NBSReplaceCallSite
    public static long insertOrThrow(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) throws SQLException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#insertOrThrow", categoryParams);
        long insertOrThrow = sQLiteDatabase.insertOrThrow(str, str2, contentValues);
        NBSTraceEngine.exitMethod();
        return insertOrThrow;
    }

    @NBSReplaceCallSite
    public static long insertWithOnConflict(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues, int i) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#insertWithOnConflict", categoryParams);
        long insertWithOnConflict = sQLiteDatabase.insertWithOnConflict(str, str2, contentValues, i);
        NBSTraceEngine.exitMethod();
        return insertWithOnConflict;
    }

    @NBSReplaceCallSite
    public static long replace(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#replace", categoryParams);
        long replace = sQLiteDatabase.replace(str, str2, contentValues);
        NBSTraceEngine.exitMethod();
        return replace;
    }

    @NBSReplaceCallSite
    public static long replaceOrThrow(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) throws SQLException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#replaceOrThrow", categoryParams);
        long replaceOrThrow = sQLiteDatabase.replaceOrThrow(str, str2, contentValues);
        NBSTraceEngine.exitMethod();
        return replaceOrThrow;
    }

    @NBSReplaceCallSite
    public static int delete(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#delete", categoryParams);
        int delete = sQLiteDatabase.delete(str, str2, strArr);
        NBSTraceEngine.exitMethod();
        return delete;
    }

    @NBSReplaceCallSite
    public static int update(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String str2, String[] strArr) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#update", categoryParams);
        int update = sQLiteDatabase.update(str, contentValues, str2, strArr);
        NBSTraceEngine.exitMethod();
        return update;
    }

    @NBSReplaceCallSite
    public static int updateWithOnConflict(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String str2, String[] strArr, int i) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#updateWithOnConflict", categoryParams);
        int updateWithOnConflict = sQLiteDatabase.updateWithOnConflict(str, contentValues, str2, strArr, i);
        NBSTraceEngine.exitMethod();
        return updateWithOnConflict;
    }

    @NBSReplaceCallSite
    public static void execSQL(SQLiteDatabase sQLiteDatabase, String str) throws SQLException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#execSQL", categoryParams);
        sQLiteDatabase.execSQL(str);
        NBSTraceEngine.exitMethod();
    }

    @NBSReplaceCallSite
    public static void execSQL(SQLiteDatabase sQLiteDatabase, String str, Object[] objArr) throws SQLException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "SQLiteDatabase#execSQL", categoryParams);
        sQLiteDatabase.execSQL(str, objArr);
        NBSTraceEngine.exitMethod();
    }
}
