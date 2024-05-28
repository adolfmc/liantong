package com.mabeijianxi.smallvideorecord2;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mabeijianxi.smallvideorecord2.Log */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5256Log {
    private static final String TAG = "jianxi_ffmpeg";
    private static boolean gIsLog;

    public static void setLog(boolean z) {
        gIsLog = z;
    }

    public static boolean getIsLog() {
        return gIsLog;
    }

    /* renamed from: d */
    public static void m13732d(String str, String str2) {
        if (gIsLog) {
            android.util.Log.d(str, str2);
        }
    }

    /* renamed from: d */
    public static void m13733d(String str) {
        if (gIsLog) {
            android.util.Log.d("jianxi_ffmpeg", str);
        }
    }

    /* renamed from: d */
    public static void m13731d(String str, String str2, Throwable th) {
        if (gIsLog) {
            android.util.Log.d(str, str2, th);
        }
    }

    /* renamed from: i */
    public static void m13726i(String str, String str2) {
        if (gIsLog) {
            android.util.Log.i(str, str2);
        }
    }

    /* renamed from: i */
    public static void m13725i(String str, String str2, Throwable th) {
        if (gIsLog) {
            android.util.Log.i(str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m13729e(String str, String str2) {
        if (gIsLog) {
            android.util.Log.e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m13730e(String str) {
        if (gIsLog) {
            android.util.Log.e("jianxi_ffmpeg", str);
        }
    }

    /* renamed from: e */
    public static void m13728e(String str, String str2, Throwable th) {
        if (gIsLog) {
            android.util.Log.e(str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m13727e(String str, Throwable th) {
        if (gIsLog) {
            android.util.Log.e("jianxi_ffmpeg", str, th);
        }
    }
}
