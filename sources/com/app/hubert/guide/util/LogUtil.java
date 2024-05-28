package com.app.hubert.guide.util;

import android.text.TextUtils;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LogUtil {
    private static final int NONE = 8;
    public static final int level = 8;
    private static final String tagPrefix = "NewbieGuide";

    /* renamed from: d */
    public static void m20455d(String str) {
    }

    /* renamed from: d */
    public static void m20454d(String str, Throwable th) {
    }

    /* renamed from: e */
    public static void m20453e(String str) {
    }

    /* renamed from: e */
    public static void m20452e(String str, Throwable th) {
    }

    /* renamed from: i */
    public static void m20451i(String str) {
    }

    /* renamed from: i */
    public static void m20450i(String str, Throwable th) {
    }

    /* renamed from: v */
    public static void m20449v(String str) {
    }

    /* renamed from: v */
    public static void m20448v(String str, Throwable th) {
    }

    /* renamed from: w */
    public static void m20447w(String str) {
    }

    /* renamed from: w */
    public static void m20446w(String str, Throwable th) {
    }

    private static String generateTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String className = stackTraceElement.getClassName();
        String format = String.format(Locale.CHINA, "%s.%s(L:%d)", className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()));
        if (TextUtils.isEmpty("NewbieGuide")) {
            return format;
        }
        return "NewbieGuide:" + format;
    }
}
