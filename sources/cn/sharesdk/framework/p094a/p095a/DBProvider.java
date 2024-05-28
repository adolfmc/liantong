package cn.sharesdk.framework.p094a.p095a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.sharesdk.framework.utils.SSDKLog;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: cn.sharesdk.framework.a.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DBProvider {

    /* renamed from: b */
    private static DBProvider f2772b;

    /* renamed from: a */
    private DBHelp f2773a = new DBHelp();

    private DBProvider() {
    }

    /* renamed from: a */
    public static synchronized DBProvider m21970a() {
        DBProvider dBProvider;
        synchronized (DBProvider.class) {
            if (f2772b == null) {
                f2772b = new DBProvider();
            }
            dBProvider = f2772b;
        }
        return dBProvider;
    }

    /* renamed from: a */
    public Cursor m21966a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        SQLiteDatabase writableDatabase = this.f2773a.getWritableDatabase();
        SSDKLog.m21740b().m21744a("Query table: %s", str);
        try {
            return !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.query(str, strArr, str2, strArr2, null, null, str3) : NBSSQLiteInstrumentation.query(writableDatabase, str, strArr, str2, strArr2, null, null, str3);
        } catch (Exception e) {
            SSDKLog.m21740b().m21736b(e, "when query database occur error table:%s,", str);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [cn.sharesdk.framework.utils.SSDKLog] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v4, types: [long] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* renamed from: a */
    public long m21968a(String str, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.f2773a.getWritableDatabase();
        try {
            str = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.replace(str, null, contentValues) : NBSSQLiteInstrumentation.replace(writableDatabase, str, null, contentValues);
            return str;
        } catch (Exception e) {
            SSDKLog.m21740b().m21736b(e, "when insert database occur error table:%s,", new Object[]{str});
            return -1L;
        }
    }

    /* renamed from: a */
    public int m21967a(String str, String str2, String[] strArr) {
        Exception e;
        int i;
        SQLiteDatabase writableDatabase = this.f2773a.getWritableDatabase();
        try {
            i = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str, str2, strArr) : NBSSQLiteInstrumentation.delete(writableDatabase, str, str2, strArr);
            try {
                SSDKLog.m21740b().m21744a("Deleted %d rows from table: %s", Integer.valueOf(i), str);
            } catch (Exception e2) {
                e = e2;
                SSDKLog.m21740b().m21736b(e, "when delete database occur error table:%s,", str);
                return i;
            }
        } catch (Exception e3) {
            e = e3;
            i = 0;
        }
        return i;
    }

    /* renamed from: a */
    public int m21969a(String str) {
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) this.f2773a.getClass().getMethod("getWritableDatabase", new Class[0]).invoke(this.f2773a, new Object[0]);
                Cursor cursor2 = (Cursor) sQLiteDatabase.getClass().getDeclaredMethod("rawQuery", String.class, String[].class).invoke(sQLiteDatabase, "select count(*) from " + str, null);
                try {
                    r1 = cursor2.moveToNext() ? cursor2.getInt(0) : 0;
                    cursor2.close();
                } catch (Exception e) {
                    e = e;
                    cursor = cursor2;
                    SSDKLog.m21740b().m21737b(e);
                    cursor.close();
                    return r1;
                } catch (Throwable th) {
                    th = th;
                    cursor = cursor2;
                    cursor.close();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return r1;
    }
}
