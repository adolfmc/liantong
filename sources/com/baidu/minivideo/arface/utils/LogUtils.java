package com.baidu.minivideo.arface.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class LogUtils {
    public static final String AR_PRE_TAG = "DuAr_";
    public static final boolean DEBUG = false;
    private static final int LENGTH = 2001;

    /* renamed from: d */
    public static void m18108d(String str, String str2) {
        if (str2.length() > 2001) {
            int i = 0;
            while (i < str2.length()) {
                int i2 = i + 2001;
                if (i2 < str2.length()) {
                    log(3, str, str2.substring(i, i2));
                } else {
                    log(3, str, str2.substring(i));
                }
                i = i2;
            }
            return;
        }
        log(3, str, str2);
    }

    private static void log(int i, String str, String str2) {
        switch (i) {
            case 2:
                Log.v(str, str2);
                return;
            case 3:
                Log.d(str, str2);
                return;
            case 4:
                Log.i(str, str2);
                return;
            case 5:
                Log.w(str, str2);
                return;
            case 6:
                Log.e(str, str2);
                return;
            default:
                Log.d(str, str2);
                return;
        }
    }
}
