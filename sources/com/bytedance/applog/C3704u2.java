package com.bytedance.applog;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.u2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3704u2 {

    /* renamed from: a */
    public static ILogger f8844a;

    /* renamed from: b */
    public static boolean f8845b;

    /* renamed from: c */
    public static final int f8846c;

    static {
        f8846c = String.valueOf(5030090).charAt(0) >= '4' ? 15029689 : 5030090;
    }

    /* renamed from: a */
    public static void m17109a(Context context, @NonNull ILogger iLogger) {
        try {
            f8845b = (context.getApplicationInfo().flags & 2) != 0;
        } catch (Throwable unused) {
            f8845b = true;
        }
        f8844a = iLogger;
    }

    /* renamed from: a */
    public static void m17108a(String str, Throwable th) {
        ILogger iLogger = f8844a;
        if (iLogger != null) {
            iLogger.log(str, th);
        } else if (f8845b) {
            Log.d("AppLog", str, th);
        }
    }
}
