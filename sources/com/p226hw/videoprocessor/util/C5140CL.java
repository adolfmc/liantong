package com.p226hw.videoprocessor.util;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hw.videoprocessor.util.CL */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5140CL {
    private static int sCount;
    private static boolean sLogEnable;

    private C5140CL() {
    }

    public static boolean isLogEnable() {
        return sLogEnable;
    }

    public static void setLogEnable(boolean z) {
        int i = sCount + 1;
        sCount = i;
        if (i > 1) {
            Log.e("L", "setLogEnable() could only be called once");
        } else {
            sLogEnable = z;
        }
    }

    private static String createLog(TagInfo tagInfo, String str, Object... objArr) {
        return "[" + tagInfo.fileName + "." + tagInfo.methodName + "():" + tagInfo.lineNumber + "]" + formatString(str, objArr);
    }

    private static String createLogWithoutFileName(TagInfo tagInfo, String str, Object... objArr) {
        return "[" + tagInfo.methodName + "():" + tagInfo.lineNumber + "]" + formatString(str, objArr);
    }

    private static TagInfo getMethodNames(StackTraceElement[] stackTraceElementArr) {
        TagInfo tagInfo = new TagInfo();
        if (stackTraceElementArr.length > 1) {
            tagInfo.fileName = stackTraceElementArr[1].getFileName();
            if (tagInfo.fileName.endsWith(".java")) {
                tagInfo.fileName = tagInfo.fileName.substring(0, tagInfo.fileName.length() - 5);
            }
            tagInfo.methodName = stackTraceElementArr[1].getMethodName();
            tagInfo.lineNumber = stackTraceElementArr[1].getLineNumber();
        }
        return tagInfo;
    }

    private static String formatString(String str, Object... objArr) {
        return objArr.length == 0 ? str : String.format(str, objArr);
    }

    /* renamed from: v */
    public static void m13752v(String str, Object... objArr) {
        if (sLogEnable) {
            TagInfo methodNames = getMethodNames(new Throwable().getStackTrace());
            Log.v(methodNames.fileName, createLogWithoutFileName(methodNames, str, objArr));
        }
    }

    /* renamed from: v */
    public static void m13751v(Throwable th) {
        if (sLogEnable) {
            Log.v(getMethodNames(th.getStackTrace()).fileName, "", th);
        }
    }

    /* renamed from: vt */
    public static void m13750vt(String str, String str2, Object... objArr) {
        if (sLogEnable) {
            Log.v(str, createLog(getMethodNames(new Throwable().getStackTrace()), str2, objArr));
        }
    }

    /* renamed from: d */
    public static void m13761d(String str, Object... objArr) {
        if (sLogEnable) {
            TagInfo methodNames = getMethodNames(new Throwable().getStackTrace());
            Log.d(methodNames.fileName, createLogWithoutFileName(methodNames, str, objArr));
        }
    }

    /* renamed from: d */
    public static void m13760d(Throwable th) {
        if (sLogEnable) {
            Log.d(getMethodNames(th.getStackTrace()).fileName, "", th);
        }
    }

    /* renamed from: dt */
    public static void m13759dt(String str, String str2, Object... objArr) {
        if (sLogEnable) {
            Log.d(str, createLog(getMethodNames(new Throwable().getStackTrace()), str2, objArr));
        }
    }

    /* renamed from: i */
    public static void m13755i(String str, Object... objArr) {
        if (sLogEnable) {
            TagInfo methodNames = getMethodNames(new Throwable().getStackTrace());
            Log.i(methodNames.fileName, createLogWithoutFileName(methodNames, str, objArr));
        }
    }

    /* renamed from: i */
    public static void m13754i(Throwable th) {
        if (sLogEnable) {
            Log.i(getMethodNames(th.getStackTrace()).fileName, "", th);
        }
    }

    /* renamed from: it */
    public static void m13753it(String str, String str2, Object... objArr) {
        if (sLogEnable) {
            Log.i(str, createLog(getMethodNames(new Throwable().getStackTrace()), str2, objArr));
        }
    }

    /* renamed from: w */
    public static void m13749w(String str, Object... objArr) {
        if (sLogEnable) {
            TagInfo methodNames = getMethodNames(new Throwable().getStackTrace());
            Log.w(methodNames.fileName, createLogWithoutFileName(methodNames, str, objArr));
        }
    }

    /* renamed from: w */
    public static void m13748w(Throwable th) {
        if (sLogEnable) {
            Log.v(getMethodNames(th.getStackTrace()).fileName, "", th);
        }
    }

    /* renamed from: wt */
    public static void m13747wt(String str, String str2, Object... objArr) {
        if (sLogEnable) {
            Log.w(str, createLog(getMethodNames(new Throwable().getStackTrace()), str2, objArr));
        }
    }

    /* renamed from: e */
    public static void m13758e(String str, Object... objArr) {
        if (sLogEnable) {
            TagInfo methodNames = getMethodNames(new Throwable().getStackTrace());
            Log.e(methodNames.fileName, createLogWithoutFileName(methodNames, str, objArr));
        }
    }

    /* renamed from: e */
    public static void m13757e(Throwable th) {
        if (sLogEnable) {
            Log.e(getMethodNames(th.getStackTrace()).fileName, "", th);
        }
    }

    /* renamed from: et */
    public static void m13756et(String str, String str2, Object... objArr) {
        if (sLogEnable) {
            Log.e(str, createLog(getMethodNames(new Throwable().getStackTrace()), str2, objArr));
        }
    }

    public static void wtf(String str, Object... objArr) {
        if (sLogEnable) {
            TagInfo methodNames = getMethodNames(new Throwable().getStackTrace());
            Log.wtf(methodNames.fileName, createLogWithoutFileName(methodNames, str, objArr));
        }
    }

    public static void printStackTrace(String str) {
        if (sLogEnable) {
            Log.d(str, Log.getStackTraceString(new Throwable()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.hw.videoprocessor.util.CL$TagInfo */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class TagInfo {
        String fileName;
        int lineNumber;
        String methodName;

        TagInfo() {
        }
    }
}
