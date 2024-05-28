package com.huawei.hms.framework.common;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.util.Arrays;
import java.util.IllegalFormatException;
import org.json.JSONException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Logger {
    private static final boolean DEBUG = false;
    private static final int MAX_STACK_DEEP_LENGTH = 20;
    private static final int MAX_STACK_DEEP_LENGTH_NORMAL = 8;
    private static final String SPLIT = "|";
    private static final String TAG = "NetworkKit_Logger";
    private static final String TAG_NETWORKKIT_PRE = "NetworkKit_";
    private static final String TAG_NETWORK_SDK_PRE = "NetworkSdk_";
    private static ExtLogger extLogger = null;
    private static boolean kitPrint = true;

    public static void setExtLogger(ExtLogger extLogger2, boolean z) {
        extLogger = extLogger2;
        kitPrint = z;
        m15049i("NetworkKit_Logger", "logger = " + extLogger2 + z);
    }

    /* renamed from: v */
    public static void m15046v(String str, String str2, Object... objArr) {
        println(2, str, str2, objArr);
    }

    /* renamed from: v */
    public static void m15047v(String str, Object obj) {
        println(2, str, obj);
    }

    @SuppressLint({"LogTagMismatch"})
    /* renamed from: d */
    public static void m15054d(String str, Object obj) {
        println(3, str, obj);
    }

    @SuppressLint({"LogTagMismatch"})
    /* renamed from: d */
    public static void m15053d(String str, String str2, Object... objArr) {
        println(3, str, str2, objArr);
    }

    @SuppressLint({"LogTagMismatch"})
    /* renamed from: i */
    public static void m15049i(String str, Object obj) {
        println(4, str, obj);
    }

    @SuppressLint({"LogTagMismatch"})
    /* renamed from: i */
    public static void m15048i(String str, String str2, Object... objArr) {
        println(4, str, str2, objArr);
    }

    /* renamed from: w */
    public static void m15045w(String str, Object obj) {
        println(5, str, obj);
    }

    /* renamed from: w */
    public static void m15043w(String str, String str2, Object... objArr) {
        println(5, str, str2, objArr);
    }

    /* renamed from: w */
    public static void m15044w(String str, String str2, Throwable th) {
        if (isAPPLoggable(5)) {
            extLogger.m15055w(complexAppTag(str), complexMsg(str2, 5), getNewThrowable(th));
        }
        if (kitPrint) {
            Log.w(complexTag(str), complexMsg(str2, 5), getNewThrowable(th));
        }
    }

    /* renamed from: e */
    public static void m15052e(String str, Object obj) {
        println(6, str, obj);
    }

    /* renamed from: e */
    public static void m15050e(String str, String str2, Object... objArr) {
        println(6, str, str2, objArr);
    }

    /* renamed from: e */
    public static void m15051e(String str, String str2, Throwable th) {
        if (isAPPLoggable(6)) {
            extLogger.m15059e(complexAppTag(str), complexMsg(str2, 5), getNewThrowable(th));
        }
        if (kitPrint) {
            Log.e(complexTag(str), complexMsg(str2, 5), getNewThrowable(th));
        }
    }

    private static boolean isKitLoggable(int i) {
        return kitPrint && isLoggable(i);
    }

    private static Throwable getNewThrowable(Throwable th) {
        if (isLoggable(3)) {
            return th;
        }
        if (th == null) {
            return null;
        }
        ThrowableWrapper throwableWrapper = new ThrowableWrapper(th);
        Throwable cause = th.getCause();
        ThrowableWrapper throwableWrapper2 = throwableWrapper;
        while (cause != null) {
            ThrowableWrapper throwableWrapper3 = new ThrowableWrapper(cause);
            throwableWrapper2.setCause(throwableWrapper3);
            cause = cause.getCause();
            throwableWrapper2 = throwableWrapper3;
        }
        return throwableWrapper;
    }

    private static String complexTag(String str) {
        return "NetworkKit_" + str;
    }

    private static String complexAppTag(String str) {
        return "NetworkSdk_" + str;
    }

    private static String complexMsg(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            return getCallMethodInfo(i) + "|" + str;
        }
        return getCallMethodInfo(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class ThrowableWrapper extends Throwable {
        private static final long serialVersionUID = 7129050843360571879L;
        private String message;
        private Throwable ownerThrowable;
        private Throwable thisCause;

        private ThrowableWrapper(Throwable th) {
            this.ownerThrowable = th;
            StackTraceElement[] stackTrace = th.getStackTrace();
            int i = ((th instanceof IOException) || (th instanceof JSONException)) ? 8 : 20;
            if (stackTrace.length > i) {
                setStackTrace((StackTraceElement[]) Arrays.copyOf(stackTrace, i));
            } else {
                setStackTrace(stackTrace);
            }
            setMessage(StringUtils.anonymizeMessage(th.getMessage()));
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            Throwable th = this.thisCause;
            if (th == this) {
                return null;
            }
            return th;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCause(Throwable th) {
            this.thisCause = th;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        @Override // java.lang.Throwable
        public String toString() {
            Throwable th = this.ownerThrowable;
            if (th == null) {
                return "";
            }
            String name = th.getClass().getName();
            if (this.message != null) {
                String str = name + ": ";
                if (this.message.startsWith(str)) {
                    return this.message;
                }
                return str + this.message;
            }
            return name;
        }
    }

    public static boolean isLoggable(int i) {
        return Log.isLoggable("NetworkKit_", i);
    }

    private static boolean isAPPLoggable(int i) {
        return extLogger != null && i >= 3;
    }

    private static String getCallMethodInfo(int i) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > i) {
            StackTraceElement stackTraceElement = stackTrace[i];
            return Thread.currentThread().getName() + "|" + stackTraceElement.getFileName() + "|" + stackTraceElement.getClassName() + "|" + stackTraceElement.getMethodName() + "|" + stackTraceElement.getLineNumber();
        }
        return "";
    }

    private static int logPrintln(int i, String str, String str2) {
        if (isAPPLoggable(i)) {
            extLogPrintln(i, complexAppTag(str), complexMsg(str2, 7));
        }
        if (isKitLoggable(i)) {
            return Log.println(i, complexTag(str), complexMsg(str2, 7));
        }
        return 1;
    }

    public static void println(int i, String str, Object obj) {
        if (i < 3) {
            return;
        }
        logPrintln(i, str, obj == null ? "null" : obj.toString());
    }

    private static void extLogPrintln(int i, String str, String str2) {
        switch (i) {
            case 2:
                extLogger.m15057v(str, str2);
                return;
            case 3:
                extLogger.m15061d(str, str2);
                return;
            case 4:
                extLogger.m15058i(str, str2);
                return;
            case 5:
                extLogger.m15056w(str, str2);
                return;
            case 6:
                extLogger.m15060e(str, str2);
                return;
            default:
                return;
        }
    }

    public static void println(int i, String str, String str2, Object... objArr) {
        if (i < 3) {
            return;
        }
        if (str2 == null) {
            Log.w("NetworkKit_Logger", "format is null, not log");
            return;
        }
        try {
            logPrintln(i, str, StringUtils.format(str2, objArr));
        } catch (IllegalFormatException e) {
            m15044w("NetworkKit_Logger", "log format error" + str2, e);
        }
    }
}
