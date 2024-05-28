package com.alipay.sdk.tid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alipay.sdk.util.C2040c;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.tid.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2034a extends SQLiteOpenHelper {

    /* renamed from: a */
    private static final String f3830a = "msp.db";

    /* renamed from: b */
    private static final int f3831b = 1;

    /* renamed from: c */
    private WeakReference<Context> f3832c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2034a(Context context) {
        super(context, "msp.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.f3832c = new WeakReference<>(context);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
        } else {
            sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists tb_tid");
        } else {
            sQLiteDatabase.execSQL("drop table if exists tb_tid");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20763a() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = getWritableDatabase();
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists tb_tid");
                } else {
                    sQLiteDatabase.execSQL("drop table if exists tb_tid");
                }
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
            } catch (Exception e) {
                C2040c.m20715a(e);
                if (0 == 0 || !sQLiteDatabase.isOpen()) {
                    return;
                }
            }
            sQLiteDatabase.close();
        } catch (Throwable th) {
            if (0 != 0 && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
        if (r2.isOpen() != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0064, code lost:
        if (r2.isOpen() != false) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m20762a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L57
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            java.lang.String r5 = r4.m20760c(r5, r6)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            boolean r5 = r2 instanceof android.database.sqlite.SQLiteDatabase     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            if (r5 != 0) goto L1a
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            goto L21
        L1a:
            r5 = r2
            android.database.sqlite.SQLiteDatabase r5 = (android.database.sqlite.SQLiteDatabase) r5     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            android.database.Cursor r5 = com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation.rawQuery(r5, r0, r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
        L21:
            boolean r0 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L59
            if (r0 == 0) goto L2b
            java.lang.String r1 = r5.getString(r6)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L59
        L2b:
            if (r5 == 0) goto L30
            r5.close()
        L30:
            if (r2 == 0) goto L67
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L67
        L38:
            r2.close()
            goto L67
        L3c:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto L46
        L40:
            r5 = move-exception
            goto L46
        L42:
            r5 = r1
            goto L59
        L44:
            r5 = move-exception
            r2 = r1
        L46:
            if (r1 == 0) goto L4b
            r1.close()
        L4b:
            if (r2 == 0) goto L56
            boolean r6 = r2.isOpen()
            if (r6 == 0) goto L56
            r2.close()
        L56:
            throw r5
        L57:
            r5 = r1
            r2 = r5
        L59:
            if (r5 == 0) goto L5e
            r5.close()
        L5e:
            if (r2 == 0) goto L67
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L67
            goto L38
        L67:
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 != 0) goto L7d
            java.lang.ref.WeakReference<android.content.Context> r5 = r4.f3832c
            java.lang.Object r5 = r5.get()
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r5 = com.alipay.sdk.util.C2037a.m20722c(r5)
            java.lang.String r1 = com.alipay.sdk.encrypt.C2012b.m20836b(r1, r5)
        L7d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C2034a.m20762a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
        if (r2.isOpen() != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0064, code lost:
        if (r2.isOpen() != false) goto L19;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m20761b(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select key_tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L57
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            java.lang.String r5 = r4.m20760c(r5, r6)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            boolean r5 = r2 instanceof android.database.sqlite.SQLiteDatabase     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            if (r5 != 0) goto L1a
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            goto L21
        L1a:
            r5 = r2
            android.database.sqlite.SQLiteDatabase r5 = (android.database.sqlite.SQLiteDatabase) r5     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            android.database.Cursor r5 = com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation.rawQuery(r5, r0, r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
        L21:
            boolean r0 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L59
            if (r0 == 0) goto L2b
            java.lang.String r1 = r5.getString(r6)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L59
        L2b:
            if (r5 == 0) goto L30
            r5.close()
        L30:
            if (r2 == 0) goto L67
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L67
        L38:
            r2.close()
            goto L67
        L3c:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto L46
        L40:
            r5 = move-exception
            goto L46
        L42:
            r5 = r1
            goto L59
        L44:
            r5 = move-exception
            r2 = r1
        L46:
            if (r1 == 0) goto L4b
            r1.close()
        L4b:
            if (r2 == 0) goto L56
            boolean r6 = r2.isOpen()
            if (r6 == 0) goto L56
            r2.close()
        L56:
            throw r5
        L57:
            r5 = r1
            r2 = r5
        L59:
            if (r5 == 0) goto L5e
            r5.close()
        L5e:
            if (r2 == 0) goto L67
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L67
            goto L38
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C2034a.m20761b(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    private String m20760c(String str, String str2) {
        return str + str2;
    }
}
