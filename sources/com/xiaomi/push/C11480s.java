package com.xiaomi.push;

import android.os.Looper;
import android.text.TextUtils;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.s */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11480s {
    /* renamed from: a */
    public static int m2923a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    /* renamed from: a */
    public static long m2922a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return j;
        }
    }

    /* renamed from: a */
    public static boolean m2921a(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /* renamed from: com.xiaomi.push.s$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11481a {

        /* renamed from: a */
        private final String f23373a;

        /* renamed from: a */
        private final StringBuilder f23374a;

        /* renamed from: b */
        private final String f23375b;

        public C11481a() {
            this(":", ",");
        }

        public C11481a(String str, String str2) {
            this.f23374a = new StringBuilder();
            this.f23373a = str;
            this.f23375b = str2;
        }

        /* renamed from: a */
        public C11481a m2919a(String str, Object obj) {
            if (!TextUtils.isEmpty(str)) {
                if (this.f23374a.length() > 0) {
                    this.f23374a.append(this.f23375b);
                }
                StringBuilder sb = this.f23374a;
                sb.append(str);
                sb.append(this.f23373a);
                sb.append(obj);
            }
            return this;
        }

        public String toString() {
            return this.f23374a.toString();
        }
    }

    /* renamed from: b */
    public static int m2920b(String str, int i) {
        return !TextUtils.isEmpty(str) ? ((str.hashCode() / 10) * 10) + i : i;
    }

    /* renamed from: a */
    public static boolean m2924a() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
