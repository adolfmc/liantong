package com.megvii.lv5;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.f4 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5440f4 {

    /* renamed from: a */
    public static boolean f12600a = Log.isLoggable("Volley", 2);

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f4$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5441a {

        /* renamed from: c */
        public static final boolean f12601c = C5440f4.f12600a;

        /* renamed from: a */
        public final List<C5442a> f12602a = new ArrayList();

        /* renamed from: b */
        public boolean f12603b = false;

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.f4$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class C5442a {

            /* renamed from: a */
            public final String f12604a;

            /* renamed from: b */
            public final long f12605b;

            /* renamed from: c */
            public final long f12606c;

            public C5442a(String str, long j, long j2) {
                this.f12604a = str;
                this.f12605b = j;
                this.f12606c = j2;
            }
        }

        /* renamed from: a */
        public synchronized void m13534a(String str) {
            long j;
            this.f12603b = true;
            if (this.f12602a.size() == 0) {
                j = 0;
            } else {
                long j2 = this.f12602a.get(0).f12606c;
                List<C5442a> list = this.f12602a;
                j = list.get(list.size() - 1).f12606c - j2;
            }
            if (j <= 0) {
                return;
            }
            long j3 = this.f12602a.get(0).f12606c;
            C5440f4.m13536a("(%-4d ms) %s", Long.valueOf(j), str);
            for (C5442a c5442a : this.f12602a) {
                long j4 = c5442a.f12606c;
                C5440f4.m13536a("(+%-4d) [%2d] %s", Long.valueOf(j4 - j3), Long.valueOf(c5442a.f12605b), c5442a.f12604a);
                j3 = j4;
            }
        }

        /* renamed from: a */
        public synchronized void m13533a(String str, long j) {
            if (this.f12603b) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.f12602a.add(new C5442a(str, j, SystemClock.elapsedRealtime()));
        }

        public void finalize() {
            if (this.f12603b) {
                return;
            }
            m13534a("Request on the loose");
            C5440f4.m13536a("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }

    /* renamed from: a */
    public static String m13536a(String str, Object... objArr) {
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str2 = "<unknown>";
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                break;
            } else if (!stackTrace[i].getClass().equals(C5440f4.class)) {
                String className = stackTrace[i].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                str2 = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            } else {
                i++;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str2, str);
    }

    /* renamed from: b */
    public static void m13535b(String str, Object... objArr) {
        if (f12600a) {
            m13536a(str, objArr);
        }
    }
}
