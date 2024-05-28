package com.sinovatech.unicom.hub.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MsLogUtil {
    /* renamed from: d */
    public static void m8000d(String str) {
    }

    /* renamed from: d */
    public static void m7999d(String str, String str2) {
    }

    /* renamed from: e */
    public static void m7998e(String str) {
    }

    /* renamed from: e */
    public static void m7997e(String str, String str2) {
    }

    private static String[] getTagAndDetailMessage(String str) {
        String[] strArr = new String[2];
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            if (!MsLogUtil.class.getName().equals(stackTraceElement.getClassName())) {
                strArr[0] = stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf(".") + 1);
                strArr[1] = str;
                break;
            }
            i++;
        }
        return strArr;
    }
}
