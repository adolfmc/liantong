package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11139af;
import com.xiaomi.push.providers.C11477a;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.xiaomi.push.fz */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11392fz {

    /* renamed from: a */
    private static C11139af f22401a = new C11139af(true);

    /* renamed from: a */
    private static volatile int f22399a = -1;

    /* renamed from: a */
    private static long f22400a = System.currentTimeMillis();

    /* renamed from: a */
    private static final Object f22403a = new Object();

    /* renamed from: a */
    private static List<C11394a> f22405a = Collections.synchronizedList(new ArrayList());

    /* renamed from: a */
    private static String f22404a = "";

    /* renamed from: a */
    private static C11477a f22402a = null;

    /* renamed from: a */
    public static void m3735a(Context context) {
        f22399a = m3728b(context);
    }

    /* renamed from: a */
    public static int m3738a(Context context) {
        if (f22399a == -1) {
            f22399a = m3728b(context);
        }
        return f22399a;
    }

    /* renamed from: b */
    private static int m3728b(Context context) {
        C11175av m4855a = C11169au.m4855a();
        if (m4855a == null) {
            return -1;
        }
        return m4855a.m4826a();
    }

    /* renamed from: a */
    private static synchronized String m3736a(Context context) {
        synchronized (C11392fz.class) {
            if (TextUtils.isEmpty(f22404a)) {
                return "";
            }
            return f22404a;
        }
    }

    /* renamed from: a */
    public static synchronized void m3729a(String str) {
        synchronized (C11392fz.class) {
            if (!C11469j.m2958d() && !TextUtils.isEmpty(str)) {
                f22404a = str;
            }
        }
    }

    /* renamed from: a */
    public static void m3733a(Context context, String str, long j, boolean z, boolean z2, long j2) {
        m3734a(context, str, m3739a(m3738a(context), j, z, j2, z2), z, j2);
    }

    /* renamed from: a */
    private static void m3734a(final Context context, String str, long j, boolean z, long j2) {
        int m3738a;
        boolean isEmpty;
        if (context == null || TextUtils.isEmpty(str) || !"com.xiaomi.xmsf".equals(context.getPackageName()) || "com.xiaomi.xmsf".equals(str) || -1 == (m3738a = m3738a(context))) {
            return;
        }
        synchronized (f22403a) {
            isEmpty = f22405a.isEmpty();
            m3731a(new C11394a(str, j2, m3738a, z ? 1 : 0, m3738a == 0 ? m3736a(context) : "", j));
        }
        if (isEmpty) {
            f22401a.m4919a(new C11139af.AbstractC11143b() { // from class: com.xiaomi.push.fz.1
                @Override // com.xiaomi.push.C11139af.AbstractC11143b
                /* renamed from: b */
                public void mo2611b() {
                    ArrayList arrayList;
                    synchronized (C11392fz.f22403a) {
                        arrayList = new ArrayList(C11392fz.f22405a);
                        C11392fz.f22405a.clear();
                    }
                    C11392fz.m3727b(context, arrayList);
                }
            }, 5000L);
        }
    }

    /* renamed from: a */
    private static long m3739a(int i, long j, boolean z, long j2, boolean z2) {
        if (z && z2) {
            long j3 = f22400a;
            f22400a = j2;
            if (j2 - j3 > 30000 && j > 1024) {
                return j * 2;
            }
        }
        return (j * (i == 0 ? 13 : 11)) / 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m3727b(Context context, List<C11394a> list) {
        try {
            synchronized (C11477a.f23369a) {
                SQLiteDatabase writableDatabase = m3737a(context).getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    for (C11394a c11394a : list) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("package_name", c11394a.f22409a);
                        contentValues.put("message_ts", Long.valueOf(c11394a.f22408a));
                        contentValues.put("network_type", Integer.valueOf(c11394a.f22407a));
                        contentValues.put("bytes", Long.valueOf(c11394a.f22411b));
                        contentValues.put("rcv", Integer.valueOf(c11394a.f22410b));
                        contentValues.put("imsi", c11394a.f22412b);
                        if (writableDatabase instanceof SQLiteDatabase) {
                            NBSSQLiteInstrumentation.insert(writableDatabase, "traffic", null, contentValues);
                        } else {
                            writableDatabase.insert("traffic", null, contentValues);
                        }
                    }
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (Throwable th) {
            AbstractC11049b.m5276a(th);
        }
    }

    /* renamed from: a */
    private static C11477a m3737a(Context context) {
        C11477a c11477a = f22402a;
        if (c11477a != null) {
            return c11477a;
        }
        f22402a = new C11477a(context);
        return f22402a;
    }

    /* renamed from: a */
    public static int m3730a(String str) {
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes().length;
        }
    }

    /* renamed from: a */
    private static void m3731a(C11394a c11394a) {
        for (C11394a c11394a2 : f22405a) {
            if (c11394a2.m3726a(c11394a)) {
                c11394a2.f22411b += c11394a.f22411b;
                return;
            }
        }
        f22405a.add(c11394a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.fz$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11394a {

        /* renamed from: a */
        public int f22407a;

        /* renamed from: a */
        public long f22408a;

        /* renamed from: a */
        public String f22409a;

        /* renamed from: b */
        public int f22410b;

        /* renamed from: b */
        public long f22411b;

        /* renamed from: b */
        public String f22412b;

        public C11394a(String str, long j, int i, int i2, String str2, long j2) {
            this.f22409a = "";
            this.f22408a = 0L;
            this.f22407a = -1;
            this.f22410b = -1;
            this.f22412b = "";
            this.f22411b = 0L;
            this.f22409a = str;
            this.f22408a = j;
            this.f22407a = i;
            this.f22410b = i2;
            this.f22412b = str2;
            this.f22411b = j2;
        }

        /* renamed from: a */
        public boolean m3726a(C11394a c11394a) {
            return TextUtils.equals(c11394a.f22409a, this.f22409a) && TextUtils.equals(c11394a.f22412b, this.f22412b) && c11394a.f22407a == this.f22407a && c11394a.f22410b == this.f22410b && Math.abs(c11394a.f22408a - this.f22408a) <= 5000;
        }
    }
}
