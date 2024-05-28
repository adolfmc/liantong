package com.zhpan.bannerview.utils;

import android.content.res.Resources;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class BannerUtils {
    private static final boolean DEBUG = true;
    public float density = Resources.getSystem().getDisplayMetrics().density;

    public static int dp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static float px2dp(float f) {
        return f / Resources.getSystem().getDisplayMetrics().density;
    }

    public int dip2px(float f) {
        return (int) ((f * this.density) + 0.5f);
    }

    public float px2dip(int i) {
        return i / this.density;
    }

    /* renamed from: e */
    public static void m2251e(String str, String str2) {
        Log.e(str, str2);
    }

    /* renamed from: e */
    public static void m2252e(String str) {
        Log.e("BannerView", str);
    }
}
