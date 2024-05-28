package com.bytedance.applog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.p1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3659p1 {

    /* renamed from: d */
    public static final HashMap<String, AbstractC3628m1> f8700d = new HashMap<>();

    /* renamed from: e */
    public static final AbstractC3628m1[] f8701e;

    /* renamed from: f */
    public static final C3661b[] f8702f;

    /* renamed from: a */
    public final C3591h f8703a;

    /* renamed from: b */
    public final C3660a f8704b;

    /* renamed from: c */
    public String f8705c;

    @NBSInstrumented
    /* renamed from: com.bytedance.applog.p1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3660a extends SQLiteOpenHelper {
        public C3660a(@Nullable Context context, @Nullable String str, @Nullable SQLiteDatabase.CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            onUpgrade(sQLiteDatabase, i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            C3704u2.m17108a("onUpgrade, " + i + ", " + i2, (Throwable) null);
            try {
                sQLiteDatabase.beginTransaction();
                Iterator<AbstractC3628m1> it = C3659p1.f8700d.values().iterator();
                while (it.hasNext()) {
                    String str = "DROP TABLE IF EXISTS " + it.next().mo17035e();
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                    } else {
                        sQLiteDatabase.execSQL(str);
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (Exception e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
            }
            onCreate(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.beginTransaction();
                for (AbstractC3628m1 abstractC3628m1 : C3659p1.f8700d.values()) {
                    String m17234a = abstractC3628m1.m17234a();
                    if (m17234a != null) {
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, m17234a);
                        } else {
                            sQLiteDatabase.execSQL(m17234a);
                        }
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (Exception e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
            }
        }
    }

    /* renamed from: com.bytedance.applog.p1$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3661b {

        /* renamed from: a */
        public String f8706a;

        /* renamed from: b */
        public int f8707b;

        /* renamed from: c */
        public int f8708c;

        /* renamed from: a */
        public final void m17165a(AbstractC3628m1 abstractC3628m1) {
            String mo17120c = abstractC3628m1.mo17120c();
            if (mo17120c == null || mo17120c.length() <= this.f8707b) {
                return;
            }
            this.f8706a = abstractC3628m1.mo17036d();
            this.f8707b = mo17120c.length();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(this.f8708c);
            sb.append("-");
            sb.append(this.f8706a);
            sb.append("-");
            sb.append(this.f8707b);
            return sb.toString();
        }
    }

    static {
        f8700d.put("page", new C3711v1());
        f8700d.put("launch", new C3694t1());
        f8700d.put("terminate", new C3729x1());
        f8700d.put("pack", new C3703u1());
        f8701e = new AbstractC3628m1[]{new C3673q1(), new C3687s1(null, false, null), new C3679r1("", new JSONObject())};
        for (AbstractC3628m1 abstractC3628m1 : f8701e) {
            m17178a(abstractC3628m1);
        }
        f8702f = new C3661b[]{new C3661b(), new C3661b(), new C3661b()};
    }

    public C3659p1(C3591h c3591h, String str) {
        this.f8704b = new C3660a(c3591h.f8464c, str, null, 36);
        this.f8703a = c3591h;
    }

    /* renamed from: a */
    public static void m17178a(AbstractC3628m1 abstractC3628m1) {
        f8700d.put(abstractC3628m1.mo17035e(), abstractC3628m1);
    }

    /* renamed from: a */
    public final String m17172a(String str, String str2, boolean z, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(str);
        sb.append(" WHERE ");
        sb.append("session_id");
        sb.append(z ? "='" : "!='");
        sb.append(str2);
        sb.append("' AND ");
        sb.append("_id");
        sb.append("<=");
        sb.append(j);
        return sb.toString();
    }

    /* renamed from: a */
    public final JSONObject m17176a(C3694t1 c3694t1, JSONObject jSONObject) {
        if (TextUtils.equals(c3694t1.f8817l, this.f8703a.f8469h.m17008f()) && c3694t1.f8816k == this.f8703a.f8469h.m17010e()) {
            return jSONObject;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            C3712v2.m17075a(jSONObject2, jSONObject);
            jSONObject2.put("app_version", c3694t1.f8817l);
            jSONObject2.put("version_code", c3694t1.f8816k);
            return jSONObject2;
        } catch (JSONException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            return jSONObject;
        }
    }

    /* renamed from: a */
    public final boolean m17166a(long[] jArr) {
        return jArr[0] > 0 || jArr[1] > 0 || jArr[2] > 0;
    }

    /* renamed from: a */
    public final JSONArray m17177a(C3694t1 c3694t1) {
        InterfaceC3674q2 interfaceC3674q2;
        JSONObject jSONObject = new JSONObject();
        try {
            C3688s2.m17118a().onSessionBatchEvent(c3694t1.f8575a, c3694t1.f8578d, jSONObject);
        } catch (Throwable th) {
            C3704u2.m17108a("U SHALL NOT PASS!", th);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("item_impression");
        if (optJSONArray != null && optJSONArray.length() == 0) {
            optJSONArray = null;
        }
        InterfaceC3674q2 interfaceC3674q22 = C3629m2.f8584a;
        if ((interfaceC3674q22 != null ? interfaceC3674q22.m17139a() : false) && (interfaceC3674q2 = C3629m2.f8584a) != null) {
            interfaceC3674q2.m17138a("item_impression", optJSONArray);
        }
        return optJSONArray;
    }

    /* renamed from: a */
    public void m17174a(C3703u1 c3703u1, boolean z, SQLiteDatabase sQLiteDatabase, boolean z2) {
        boolean z3;
        if (sQLiteDatabase == null) {
            sQLiteDatabase = this.f8704b.getWritableDatabase();
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            try {
                sQLiteDatabase.beginTransaction();
            } catch (Throwable th) {
                try {
                    C3704u2.m17108a("U SHALL NOT PASS!", th);
                    if (!z3) {
                        return;
                    }
                } catch (Throwable th2) {
                    if (z3) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Exception e) {
                            C3704u2.m17108a("U SHALL NOT PASS!", e);
                        }
                    }
                    throw th2;
                }
            }
        }
        if (z2) {
            ContentValues m17232a = c3703u1.m17232a((ContentValues) null);
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.insert(sQLiteDatabase, "pack", null, m17232a);
            } else {
                sQLiteDatabase.insert("pack", null, m17232a);
            }
        }
        if (c3703u1.f8834o > 0) {
            String m17172a = m17172a("event", c3703u1.f8578d, z, c3703u1.f8834o);
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, m17172a);
            } else {
                sQLiteDatabase.execSQL(m17172a);
            }
        }
        long j = c3703u1.f8836q;
        if (j > 0) {
            String m17172a2 = m17172a("eventv3", c3703u1.f8578d, z, j);
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, m17172a2);
            } else {
                sQLiteDatabase.execSQL(m17172a2);
            }
        }
        long j2 = c3703u1.f8842w;
        if (j2 > 0) {
            String m17172a3 = m17172a("event_misc", c3703u1.f8578d, z, j2);
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, m17172a3);
            } else {
                sQLiteDatabase.execSQL(m17172a3);
            }
        }
        if (z3) {
            sQLiteDatabase.setTransactionSuccessful();
        }
        if (!z3) {
            return;
        }
        try {
            sQLiteDatabase.endTransaction();
        } catch (Exception e2) {
            C3704u2.m17108a("U SHALL NOT PASS!", e2);
        }
    }

    /* renamed from: a */
    public final boolean m17173a(String str) {
        StringBuilder m17349a = C3535a.m17349a("needLaunch, ");
        m17349a.append(this.f8705c);
        m17349a.append(", ");
        m17349a.append(str);
        C3704u2.m17108a(m17349a.toString(), (Throwable) null);
        if (TextUtils.equals(str, this.f8705c)) {
            return false;
        }
        this.f8705c = str;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0198 A[Catch: all -> 0x019c, TRY_ENTER, TRY_LEAVE, TryCatch #12 {, blocks: (B:4:0x0003, B:111:0x01a5, B:116:0x01b0, B:115:0x01ab, B:96:0x0186, B:63:0x013e, B:99:0x018b, B:58:0x0133, B:105:0x0198, B:90:0x0179), top: B:127:0x0003, inners: #4, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0179 A[Catch: all -> 0x017d, TRY_ENTER, TRY_LEAVE, TryCatch #12 {, blocks: (B:4:0x0003, B:111:0x01a5, B:116:0x01b0, B:115:0x01ab, B:96:0x0186, B:63:0x013e, B:99:0x018b, B:58:0x0133, B:105:0x0198, B:90:0x0179), top: B:127:0x0003, inners: #4, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0186 A[Catch: Exception -> 0x018a, all -> 0x01b1, TRY_ENTER, TRY_LEAVE, TryCatch #4 {Exception -> 0x018a, blocks: (B:96:0x0186, B:63:0x013e), top: B:121:0x0031, outer: #12 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:99:0x018b -> B:120:0x0190). Please submit an issue!!! */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.util.ArrayList<com.bytedance.applog.C3703u1> m17170a(org.json.JSONObject r31) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3659p1.m17170a(org.json.JSONObject):java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x008c  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList<com.bytedance.applog.C3703u1> m17169a(org.json.JSONObject r20, com.bytedance.applog.C3694t1 r21, com.bytedance.applog.C3703u1 r22, android.database.sqlite.SQLiteDatabase r23, org.json.JSONArray[] r24, long[] r25) {
        /*
            Method dump skipped, instructions count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3659p1.m17169a(org.json.JSONObject, com.bytedance.applog.t1, com.bytedance.applog.u1, android.database.sqlite.SQLiteDatabase, org.json.JSONArray[], long[]):java.util.ArrayList");
    }

    /* renamed from: a */
    public final void m17168a(JSONObject jSONObject, C3694t1 c3694t1, C3703u1 c3703u1, C3711v1 c3711v1, C3729x1 c3729x1, SQLiteDatabase sQLiteDatabase, JSONArray[] jSONArrayArr, long[] jArr) {
        boolean z;
        SQLiteDatabase sQLiteDatabase2;
        C3703u1 c3703u12;
        C3694t1 c3694t12;
        C3703u1 c3703u13;
        JSONObject jSONObject2;
        C3729x1 c3729x12;
        JSONArray[] jSONArrayArr2;
        boolean z2;
        SQLiteDatabase sQLiteDatabase3;
        StringBuilder m17349a = C3535a.m17349a("packHistoryData, ");
        m17349a.append(c3694t1.f8578d);
        C3704u2.m17108a(m17349a.toString(), (Throwable) null);
        JSONArray m17175a = m17175a(c3694t1, true, c3729x1, c3711v1, sQLiteDatabase);
        c3694t1.f8818m = m17175a.length() == 0;
        int m17179a = m17179a(0, sQLiteDatabase, c3694t1.f8578d, true, jSONArrayArr, jArr);
        JSONArray m17177a = m17177a(c3694t1);
        if (c3694t1.f8818m) {
            c3694t12 = m17173a(c3694t1.f8578d) ? c3694t1 : null;
            m17175a = null;
            c3703u13 = c3703u1;
            jSONObject2 = jSONObject;
            z = true;
            c3729x12 = null;
            sQLiteDatabase2 = sQLiteDatabase;
            jSONArrayArr2 = jSONArrayArr;
            c3703u12 = c3703u1;
        } else {
            z = true;
            sQLiteDatabase2 = sQLiteDatabase;
            c3703u12 = c3703u1;
            c3694t12 = null;
            c3703u13 = c3703u1;
            jSONObject2 = jSONObject;
            c3729x12 = c3729x1;
            jSONArrayArr2 = jSONArrayArr;
        }
        c3703u13.m17111a(jSONObject2, c3694t12, c3729x12, m17175a, jSONArrayArr2, jArr, m17177a);
        m17174a(c3703u12, z, sQLiteDatabase2, z);
        int i = m17179a;
        while (i < f8701e.length) {
            C3703u1 c3703u14 = c3703u12;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase2;
            boolean z3 = z;
            i = m17179a(i, sQLiteDatabase, c3694t1.f8578d, true, jSONArrayArr, jArr);
            if (m17166a(jArr)) {
                z2 = z3;
                sQLiteDatabase3 = sQLiteDatabase4;
                c3703u12 = c3703u14;
                c3703u1.m17111a(jSONObject, null, null, null, jSONArrayArr, jArr, null);
                m17174a(c3703u12, z2, sQLiteDatabase3, z2);
            } else {
                z2 = z3;
                sQLiteDatabase3 = sQLiteDatabase4;
                c3703u12 = c3703u14;
            }
            sQLiteDatabase2 = sQLiteDatabase3;
            z = z2;
        }
    }

    /* renamed from: a */
    public final void m17167a(JSONObject jSONObject, C3694t1 c3694t1, C3729x1 c3729x1, C3711v1 c3711v1, C3703u1 c3703u1, SQLiteDatabase sQLiteDatabase, String str, JSONArray[] jSONArrayArr, long[] jArr) {
        long[] jArr2;
        SQLiteDatabase sQLiteDatabase2;
        C3703u1 c3703u12;
        boolean z;
        boolean z2;
        SQLiteDatabase sQLiteDatabase3;
        C3703u1 c3703u13;
        C3704u2.m17108a("packLostData, " + str, (Throwable) null);
        c3694t1.f8578d = str;
        c3703u1.f8578d = str;
        JSONArray m17175a = m17175a(c3694t1, false, c3729x1, c3711v1, sQLiteDatabase);
        int m17179a = m17179a(0, sQLiteDatabase, str, false, jSONArrayArr, jArr);
        boolean z3 = true;
        boolean z4 = false;
        c3694t1.f8818m = m17175a.length() == 0;
        if (m17166a(jArr) || !c3694t1.f8818m) {
            C3729x1 c3729x12 = !c3694t1.f8818m ? c3729x1 : null;
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            c3703u12 = c3703u1;
            c3703u1.m17111a(jSONObject, null, c3729x12, !c3694t1.f8818m ? m17175a : null, jSONArrayArr, jArr, null);
            m17174a(c3703u12, false, sQLiteDatabase2, true);
        } else {
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            c3703u12 = c3703u1;
        }
        int i = m17179a;
        while (i < f8701e.length) {
            C3703u1 c3703u14 = c3703u12;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase2;
            boolean z5 = z4;
            boolean z6 = z3;
            i = m17179a(i, sQLiteDatabase, str, false, jSONArrayArr, jArr);
            if (m17166a(jArr2)) {
                z = z6;
                z2 = z5;
                sQLiteDatabase3 = sQLiteDatabase4;
                c3703u13 = c3703u14;
                c3703u1.m17111a(jSONObject, null, null, null, jSONArrayArr, jArr, null);
                m17174a(c3703u13, z2, sQLiteDatabase3, z);
            } else {
                z = z6;
                z2 = z5;
                sQLiteDatabase3 = sQLiteDatabase4;
                c3703u13 = c3703u14;
            }
            sQLiteDatabase2 = sQLiteDatabase3;
            z3 = z;
            c3703u12 = c3703u13;
            jArr2 = jArr;
            z4 = z2;
        }
    }

    /* renamed from: a */
    public final int m17179a(int i, SQLiteDatabase sQLiteDatabase, String str, boolean z, JSONArray[] jSONArrayArr, long[] jArr) {
        C3661b[] c3661bArr;
        long j;
        Cursor cursor;
        long j2;
        StringBuilder sb;
        int i2 = 0;
        for (C3661b c3661b : f8702f) {
            c3661b.f8706a = "";
            c3661b.f8707b = 0;
            c3661b.f8708c = 0;
        }
        int i3 = 0;
        while (true) {
            j = 0;
            if (i3 >= i) {
                break;
            }
            jSONArrayArr[i3] = null;
            jArr[i3] = 0;
            i3++;
        }
        int i4 = 200;
        int i5 = i3;
        int i6 = 200;
        while (i6 > 0) {
            AbstractC3628m1[] abstractC3628m1Arr = f8701e;
            if (i5 >= abstractC3628m1Arr.length) {
                break;
            }
            AbstractC3628m1 abstractC3628m1 = abstractC3628m1Arr[i5];
            JSONArray jSONArray = new JSONArray();
            try {
                sb = new StringBuilder();
                sb.append("SELECT * FROM ");
                sb.append(abstractC3628m1.mo17035e());
                sb.append(" WHERE ");
                sb.append("session_id");
                sb.append(z ? "='" : "!='");
            } catch (Throwable th) {
                th = th;
            }
            try {
                sb.append(str);
                sb.append("' ORDER BY ");
                sb.append("_id");
                sb.append(" LIMIT ");
                sb.append(i6);
                String sb2 = sb.toString();
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    cursor = NBSSQLiteInstrumentation.rawQuery(sQLiteDatabase, sb2, null);
                } else {
                    try {
                        cursor = sQLiteDatabase.rawQuery(sb2, null);
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = null;
                        j2 = j;
                        try {
                            C3704u2.m17108a("U SHALL NOT PASS!", th);
                        } finally {
                            if (cursor != null) {
                                cursor.close();
                            }
                        }
                    }
                }
                int i7 = i2;
                j2 = j;
                while (cursor.moveToNext() && i7 <= i4) {
                    try {
                        abstractC3628m1.mo17041a(cursor);
                        f8702f[i5].m17165a(abstractC3628m1);
                        if (C3704u2.f8845b) {
                            C3704u2.m17108a("queryEvent, " + abstractC3628m1, (Throwable) null);
                        }
                        jSONArray.put(abstractC3628m1.m17229g());
                        long j3 = abstractC3628m1.f8575a;
                        if (j3 > j2) {
                            j2 = j3;
                        }
                        i7++;
                        i4 = 200;
                    } catch (Throwable th3) {
                        th = th3;
                        C3704u2.m17108a("U SHALL NOT PASS!", th);
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                cursor = null;
                j2 = 0;
                C3704u2.m17108a("U SHALL NOT PASS!", th);
            }
            jSONArrayArr[i5] = jSONArray;
            jArr[i5] = j2;
            int length = jSONArrayArr[i5].length();
            i6 -= length;
            f8702f[i5].f8708c = length;
            if (i6 > 0) {
                i5++;
            }
            i2 = 0;
            j = 0;
            i4 = 200;
        }
        for (int i8 = i5 + 1; i8 < jSONArrayArr.length; i8++) {
            jSONArrayArr[i8] = null;
            jArr[i8] = 0;
        }
        return i5;
    }

    @NonNull
    /* renamed from: a */
    public ArrayList<C3703u1> m17180a() {
        Cursor cursor;
        ArrayList<C3703u1> arrayList = new ArrayList<>();
        C3703u1 c3703u1 = (C3703u1) f8700d.get("pack");
        try {
            SQLiteDatabase writableDatabase = this.f8704b.getWritableDatabase();
            cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("SELECT * FROM pack ORDER BY _id DESC LIMIT 8", null) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "SELECT * FROM pack ORDER BY _id DESC LIMIT 8", null);
            while (cursor.moveToNext()) {
                try {
                    c3703u1 = (C3703u1) c3703u1.m24467clone();
                    c3703u1.mo17041a(cursor);
                    arrayList.add(c3703u1);
                } catch (Throwable th) {
                    th = th;
                    try {
                        C3704u2.m17108a("U SHALL NOT PASS!", th);
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
        C3704u2.m17108a("queryPack, " + arrayList, (Throwable) null);
        return arrayList;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0150  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.json.JSONArray m17175a(com.bytedance.applog.C3694t1 r20, boolean r21, com.bytedance.applog.C3729x1 r22, com.bytedance.applog.C3711v1 r23, android.database.sqlite.SQLiteDatabase r24) {
        /*
            Method dump skipped, instructions count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3659p1.m17175a(com.bytedance.applog.t1, boolean, com.bytedance.applog.x1, com.bytedance.applog.v1, android.database.sqlite.SQLiteDatabase):org.json.JSONArray");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00ab A[Catch: all -> 0x00f6, TRY_LEAVE, TryCatch #1 {all -> 0x00f6, blocks: (B:40:0x00a1, B:41:0x00a5, B:43:0x00ab, B:45:0x00b3, B:47:0x00bd, B:50:0x00d5, B:52:0x00df, B:54:0x00eb, B:56:0x00f2), top: B:77:0x00a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0106 A[Catch: all -> 0x0118, LOOP:2: B:61:0x0100->B:63:0x0106, LOOP_END, TRY_LEAVE, TryCatch #2 {all -> 0x0118, blocks: (B:60:0x00fc, B:61:0x0100, B:63:0x0106), top: B:79:0x00fc }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x009c -> B:77:0x00a1). Please submit an issue!!! */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m17171a(@androidx.annotation.NonNull java.util.ArrayList<com.bytedance.applog.AbstractC3628m1> r13) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3659p1.m17171a(java.util.ArrayList):void");
    }
}
