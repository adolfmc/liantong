package com.huawei.secure.android.common.util;

import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class LogsUtil {

    /* renamed from: a */
    private static final Pattern f12136a = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");

    /* renamed from: b */
    private static final char f12137b = '*';

    /* renamed from: c */
    private static final int f12138c = 2;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.secure.android.common.util.LogsUtil$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5122a extends Throwable {

        /* renamed from: d */
        private static final long f12139d = 7129050843360571879L;

        /* renamed from: a */
        private String f12140a;

        /* renamed from: b */
        private Throwable f12141b;

        /* renamed from: c */
        private Throwable f12142c;

        public C5122a(Throwable th) {
            this.f12142c = th;
        }

        /* renamed from: a */
        public void m13800a(Throwable th) {
            this.f12141b = th;
        }

        @Override // java.lang.Throwable
        public synchronized Throwable getCause() {
            Throwable th;
            th = this.f12141b;
            if (th == this) {
                th = null;
            }
            return th;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.f12140a;
        }

        @Override // java.lang.Throwable
        public String toString() {
            Throwable th = this.f12142c;
            if (th == null) {
                return "";
            }
            String name = th.getClass().getName();
            if (this.f12140a != null) {
                String str = name + ": ";
                if (this.f12140a.startsWith(str)) {
                    return this.f12140a;
                }
                return str + this.f12140a;
            }
            return name;
        }

        /* renamed from: a */
        public void m13801a(String str) {
            this.f12140a = str;
        }
    }

    /* renamed from: a */
    private static String m13827a(String str, boolean z) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                sb.append(m13829a(str));
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static String m13826b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i % 2 == 0) {
                charArray[i] = '*';
            }
        }
        return new String(charArray);
    }

    /* renamed from: d */
    public static void m13820d(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, m13827a(str2, z));
    }

    /* renamed from: e */
    public static void m13814e(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, m13827a(str2, z));
    }

    public static Throwable getNewThrowable(Throwable th) {
        if (th == null) {
            return null;
        }
        C5122a c5122a = new C5122a(th);
        c5122a.setStackTrace(th.getStackTrace());
        c5122a.m13801a(m13826b(th.getMessage()));
        Throwable cause = th.getCause();
        C5122a c5122a2 = c5122a;
        while (cause != null) {
            C5122a c5122a3 = new C5122a(cause);
            c5122a3.setStackTrace(cause.getStackTrace());
            c5122a3.m13801a(m13826b(cause.getMessage()));
            c5122a2.m13800a(c5122a3);
            cause = cause.getCause();
            c5122a2 = c5122a3;
        }
        return c5122a;
    }

    /* renamed from: i */
    public static void m13808i(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, m13827a(str2, z));
    }

    /* renamed from: w */
    public static void m13802w(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, m13827a(str2, z));
    }

    /* renamed from: d */
    public static void m13824d(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.d(str, m13828a(str2, str3));
    }

    /* renamed from: e */
    public static void m13818e(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.e(str, m13828a(str2, str3));
    }

    /* renamed from: i */
    public static void m13812i(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.i(str, m13828a(str2, str3));
    }

    /* renamed from: w */
    public static void m13806w(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.w(str, m13828a(str2, str3));
    }

    /* renamed from: d */
    public static void m13823d(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.d(str, m13828a(str2, str3), getNewThrowable(th));
    }

    /* renamed from: e */
    public static void m13817e(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.e(str, m13828a(str2, str3), getNewThrowable(th));
    }

    /* renamed from: i */
    public static void m13811i(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.i(str, m13828a(str2, str3), getNewThrowable(th));
    }

    /* renamed from: w */
    public static void m13805w(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.w(str, m13828a(str2, str3), getNewThrowable(th));
    }

    /* renamed from: a */
    private static String m13828a(String str, String str2) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(m13829a(str2));
        }
        return sb.toString();
    }

    /* renamed from: d */
    public static void m13825d(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, m13827a(str2, false));
    }

    /* renamed from: e */
    public static void m13819e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, m13827a(str2, false));
    }

    /* renamed from: i */
    public static void m13813i(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, m13827a(str2, false));
    }

    /* renamed from: w */
    public static void m13807w(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, m13827a(str2, false));
    }

    /* renamed from: d */
    public static void m13821d(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, m13827a(str2, z), getNewThrowable(th));
    }

    /* renamed from: e */
    public static void m13815e(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.e(str, m13827a(str2, z), getNewThrowable(th));
    }

    /* renamed from: i */
    public static void m13809i(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.i(str, m13827a(str2, z), getNewThrowable(th));
    }

    /* renamed from: w */
    public static void m13803w(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.w(str, m13827a(str2, z), getNewThrowable(th));
    }

    /* renamed from: a */
    private static String m13829a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        int i = 1;
        if (1 == length) {
            return String.valueOf('*');
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (f12136a.matcher(String.valueOf(charAt)).matches()) {
                if (i % 2 == 0) {
                    charAt = '*';
                }
                i++;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    /* renamed from: d */
    public static void m13822d(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.d(str, m13827a(str2, false), getNewThrowable(th));
    }

    /* renamed from: e */
    public static void m13816e(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.e(str, m13827a(str2, false), getNewThrowable(th));
    }

    /* renamed from: i */
    public static void m13810i(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.i(str, m13827a(str2, false), getNewThrowable(th));
    }

    /* renamed from: w */
    public static void m13804w(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.w(str, m13827a(str2, false), getNewThrowable(th));
    }
}
