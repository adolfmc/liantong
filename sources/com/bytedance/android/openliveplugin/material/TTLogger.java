package com.bytedance.android.openliveplugin.material;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TTLogger {
    private static boolean DEBUG = false;
    private static final String TAG = "TTLiveLogger";
    private static int sLevel = 4;

    public static void setLogLevel(int i) {
        sLevel = i;
    }

    public static int getLogLevel() {
        return sLevel;
    }

    public static boolean debug() {
        return sLevel <= 3;
    }

    public static void openDebugMode() {
        DEBUG = true;
        setLogLevel(3);
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    /* renamed from: v */
    public static void m17360v(String str) {
        m17359v("TTLiveLogger", str);
    }

    /* renamed from: v */
    public static void m17359v(String str, String str2) {
        if (DEBUG && str2 != null && sLevel <= 2) {
            Log.v(str, str2);
        }
    }

    /* renamed from: v */
    public static void m17358v(String str, String str2, Throwable th) {
        if (DEBUG) {
            if (!(str2 == null && th == null) && sLevel <= 2) {
                Log.v(str, str2, th);
            }
        }
    }

    /* renamed from: v */
    public static void m17357v(String str, Object... objArr) {
        if (DEBUG && objArr != null && sLevel <= 2) {
            Log.v(str, formatMsgs(objArr));
        }
    }

    /* renamed from: d */
    public static void m17373d(String str) {
        if (DEBUG) {
            m17372d("TTLiveLogger", str);
        }
    }

    /* renamed from: d */
    public static void m17372d(String str, String str2) {
        if (DEBUG && str2 != null && sLevel <= 3) {
            Log.d(str, str2);
        }
    }

    /* renamed from: d */
    public static void m17371d(String str, String str2, Throwable th) {
        if (DEBUG) {
            if (!(str2 == null && th == null) && sLevel <= 3) {
                Log.d(str, str2, th);
            }
        }
    }

    /* renamed from: d */
    public static void m17370d(String str, Object... objArr) {
        if (DEBUG && objArr != null && sLevel <= 3) {
            Log.d(str, formatMsgs(objArr));
        }
    }

    /* renamed from: i */
    public static void m17365i(String str) {
        if (DEBUG) {
            m17364i("TTLiveLogger", str);
        }
    }

    /* renamed from: i */
    public static void m17364i(String str, String str2) {
        if (DEBUG && str2 != null && sLevel <= 4) {
            Log.i(str, str2);
        }
    }

    /* renamed from: i */
    public static void m17363i(String str, String str2, Throwable th) {
        if (DEBUG) {
            if (!(str2 == null && th == null) && sLevel <= 4) {
                Log.i(str, str2, th);
            }
        }
    }

    /* renamed from: i */
    public static void m17362i(String str, Object... objArr) {
        if (DEBUG && objArr != null && sLevel <= 4) {
            Log.v(str, formatMsgs(objArr));
        }
    }

    /* renamed from: w */
    public static void m17356w(String str) {
        if (DEBUG) {
            m17355w("TTLiveLogger", str);
        }
    }

    /* renamed from: w */
    public static void m17355w(String str, String str2) {
        if (DEBUG && str2 != null && sLevel <= 5) {
            Log.w(str, str2);
        }
    }

    /* renamed from: w */
    public static void m17354w(String str, String str2, Throwable th) {
        if (DEBUG) {
            if (!(str2 == null && th == null) && sLevel <= 5) {
                Log.w(str, str2, th);
            }
        }
    }

    /* renamed from: w */
    public static void m17353w(String str, Object... objArr) {
        if (DEBUG && objArr != null && sLevel <= 5) {
            Log.v(str, formatMsgs(objArr));
        }
    }

    public static void logDirect(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (str == null) {
            str = "TTLiveLogger";
        }
        Log.i(str, str2);
    }

    /* renamed from: e */
    public static void m17369e(String str) {
        if (DEBUG) {
            m17368e("TTLiveLogger", str);
        }
    }

    /* renamed from: e */
    public static void m17368e(String str, String str2) {
        if (DEBUG && str2 != null && sLevel <= 6) {
            Log.e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m17367e(String str, String str2, Throwable th) {
        if (DEBUG) {
            if (!(str2 == null && th == null) && sLevel <= 6) {
                Log.e(str, str2, th);
            }
        }
    }

    /* renamed from: e */
    public static void m17366e(String str, Object... objArr) {
        if (DEBUG && objArr != null && sLevel <= 6) {
            Log.v(str, formatMsgs(objArr));
        }
    }

    /* renamed from: st */
    public static void m17361st(String str, int i) {
        try {
            throw new Exception();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 1; i2 < Math.min(i, stackTrace.length); i2++) {
                if (i2 > 1) {
                    sb.append("\n");
                }
                sb.append(getSimpleClassName(stackTrace[i2].getClassName()));
                sb.append(".");
                sb.append(stackTrace[i2].getMethodName());
            }
            m17359v(str, sb.toString());
        }
    }

    private static String getSimpleClassName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf < 0 ? str : str.substring(lastIndexOf + 1);
    }

    private static String formatMsgs(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            if (obj != null) {
                sb.append(obj.toString());
            } else {
                sb.append(" null ");
            }
            sb.append(" ");
        }
        return sb.toString();
    }
}
