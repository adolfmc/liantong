package com.baidu.cloud.framecapture;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Logging {
    private static final Logger FALLBACK_LOGGER = createFallbackLogger();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum Severity {
        LS_VERBOSE,
        LS_INFO,
        LS_WARNING,
        LS_ERROR,
        LS_NONE
    }

    private static Logger createFallbackLogger() {
        Logger logger = Logger.getLogger("com.baidu.framecapture");
        logger.setLevel(Level.ALL);
        return logger;
    }

    public static void log(Severity severity, String str, String str2) {
        Level level;
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Logging tag or message may not be null.");
        }
        switch (severity) {
            case LS_ERROR:
                level = Level.SEVERE;
                break;
            case LS_WARNING:
                level = Level.WARNING;
                break;
            case LS_INFO:
                level = Level.INFO;
                break;
            default:
                level = Level.FINE;
                break;
        }
        Logger logger = FALLBACK_LOGGER;
        logger.log(level, str + ": " + str2);
    }

    /* renamed from: d */
    public static void m20092d(String str, String str2) {
        log(Severity.LS_INFO, str, str2);
    }

    /* renamed from: e */
    public static void m20091e(String str, String str2) {
        log(Severity.LS_ERROR, str, str2);
    }

    /* renamed from: e */
    public static void m20090e(String str, String str2, Throwable th) {
        log(Severity.LS_ERROR, str, str2);
        log(Severity.LS_ERROR, str, th.toString());
        log(Severity.LS_ERROR, str, getStackTraceString(th));
    }

    /* renamed from: w */
    public static void m20088w(String str, String str2) {
        log(Severity.LS_WARNING, str, str2);
    }

    /* renamed from: w */
    public static void m20087w(String str, String str2, Throwable th) {
        log(Severity.LS_WARNING, str, str2);
        log(Severity.LS_WARNING, str, th.toString());
        log(Severity.LS_WARNING, str, getStackTraceString(th));
    }

    /* renamed from: v */
    public static void m20089v(String str, String str2) {
        log(Severity.LS_VERBOSE, str, str2);
    }

    private static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
