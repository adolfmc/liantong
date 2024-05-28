package com.alipay.sdk.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2040c {

    /* renamed from: a */
    private static final String f3854a = "alipaysdk";

    /* renamed from: a */
    public static void m20718a(String str, String str2) {
    }

    /* renamed from: a */
    public static void m20717a(String str, String str2, Throwable th) {
    }

    /* renamed from: a */
    public static void m20716a(String str, Throwable th) {
    }

    /* renamed from: a */
    public static void m20715a(Throwable th) {
        if (th == null) {
        }
    }

    /* renamed from: b */
    public static void m20714b(String str, String str2) {
    }

    /* renamed from: c */
    public static void m20712c(String str, String str2) {
    }

    /* renamed from: d */
    public static void m20711d(String str, String str2) {
    }

    /* renamed from: e */
    public static void m20710e(String str, String str2) {
    }

    /* renamed from: f */
    private static String m20709f(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return String.format("[%s][%s]", str, str2);
    }

    /* renamed from: b */
    private static String m20713b(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
