package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SQLiteHelper implements PublicMemberKeeper {
    public static SingleTableDB getDatabase(Context context, String str) {
        return getDatabase(context != null ? context.getDatabasePath(str).getPath() : null, str);
    }

    public static SingleTableDB getDatabase(String str, String str2) {
        return new SingleTableDB(str, str2);
    }

    public static long insert(SingleTableDB singleTableDB, ContentValues contentValues) throws Throwable {
        singleTableDB.m11122a();
        SQLiteDatabase sQLiteDatabase = singleTableDB.f15271c;
        String m11118c = singleTableDB.m11118c();
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.replace(m11118c, null, contentValues) : NBSSQLiteInstrumentation.replace(sQLiteDatabase, m11118c, null, contentValues);
    }

    public static int delete(SingleTableDB singleTableDB, String str, String[] strArr) throws Throwable {
        singleTableDB.m11122a();
        SQLiteDatabase sQLiteDatabase = singleTableDB.f15271c;
        String m11118c = singleTableDB.m11118c();
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.delete(m11118c, str, strArr) : NBSSQLiteInstrumentation.delete(sQLiteDatabase, m11118c, str, strArr);
    }

    public static int update(SingleTableDB singleTableDB, ContentValues contentValues, String str, String[] strArr) throws Throwable {
        singleTableDB.m11122a();
        SQLiteDatabase sQLiteDatabase = singleTableDB.f15271c;
        String m11118c = singleTableDB.m11118c();
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.update(m11118c, contentValues, str, strArr) : NBSSQLiteInstrumentation.update(sQLiteDatabase, m11118c, contentValues, str, strArr);
    }

    public static Cursor query(SingleTableDB singleTableDB, String[] strArr, String str, String[] strArr2, String str2) throws Throwable {
        singleTableDB.m11122a();
        SQLiteDatabase sQLiteDatabase = singleTableDB.f15271c;
        String m11118c = singleTableDB.m11118c();
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query(m11118c, strArr, str, strArr2, null, null, str2) : NBSSQLiteInstrumentation.query(sQLiteDatabase, m11118c, strArr, str, strArr2, null, null, str2);
    }

    public static void close(SingleTableDB singleTableDB) {
        singleTableDB.m11120b();
    }

    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class SingleTableDB implements PublicMemberKeeper {

        /* renamed from: a */
        private String f15269a;

        /* renamed from: b */
        private String f15270b;

        /* renamed from: c */
        private SQLiteDatabase f15271c;

        /* renamed from: d */
        private LinkedHashMap<String, String> f15272d;

        /* renamed from: e */
        private HashMap<String, Boolean> f15273e;

        /* renamed from: f */
        private String f15274f;

        /* renamed from: g */
        private boolean f15275g;

        private SingleTableDB(String str, String str2) {
            this.f15269a = str;
            this.f15270b = str2;
            this.f15272d = new LinkedHashMap<>();
            this.f15273e = new HashMap<>();
        }

        public void addField(String str, String str2, boolean z) {
            if (this.f15271c == null) {
                this.f15272d.put(str, str2);
                this.f15273e.put(str, Boolean.valueOf(z));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00b0 A[DONT_GENERATE] */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m11122a() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 372
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.SQLiteHelper.SingleTableDB.m11122a():void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m11120b() {
            SQLiteDatabase sQLiteDatabase = this.f15271c;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
                this.f15271c = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public String m11118c() {
            return this.f15270b;
        }
    }
}
