package com.webrtc;

import android.support.annotation.Nullable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Logging {
    private static final Logger fallbackLogger = createFallbackLogger();
    @Nullable
    private static Loggable loggable;
    private static Severity loggableSeverity;
    private static volatile boolean loggingEnabled;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum Severity {
        LS_VERBOSE,
        LS_INFO,
        LS_WARNING,
        LS_ERROR,
        LS_NONE
    }

    @Deprecated
    public static void enableTracing(String str, EnumSet<TraceLevel> enumSet) {
    }

    private static native void nativeEnableLogThreads();

    private static native void nativeEnableLogTimeStamps();

    private static native void nativeEnableLogToDebugOutput(int i);

    private static native void nativeLog(int i, String str, String str2);

    private static Logger createFallbackLogger() {
        Logger logger = Logger.getLogger("com.webrtc.Logging");
        logger.setLevel(Level.ALL);
        return logger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void injectLoggable(Loggable loggable2, Severity severity) {
        if (loggable2 != null) {
            loggable = loggable2;
            loggableSeverity = severity;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteInjectedLoggable() {
        loggable = null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum TraceLevel {
        TRACE_NONE(0),
        TRACE_STATEINFO(1),
        TRACE_WARNING(2),
        TRACE_ERROR(4),
        TRACE_CRITICAL(8),
        TRACE_APICALL(16),
        TRACE_DEFAULT(255),
        TRACE_MODULECALL(32),
        TRACE_MEMORY(256),
        TRACE_TIMER(512),
        TRACE_STREAM(1024),
        TRACE_DEBUG(2048),
        TRACE_INFO(4096),
        TRACE_TERSEINFO(8192),
        TRACE_ALL(65535);
        
        public final int level;

        TraceLevel(int i) {
            this.level = i;
        }
    }

    public static void enableLogThreads() {
        nativeEnableLogThreads();
    }

    public static void enableLogTimeStamps() {
        nativeEnableLogTimeStamps();
    }

    public static synchronized void enableLogToDebugOutput(Severity severity) {
        synchronized (Logging.class) {
            if (loggable != null) {
                throw new IllegalStateException("Logging to native debug output not supported while Loggable is injected. Delete the Loggable before calling this method.");
            }
            nativeEnableLogToDebugOutput(severity.ordinal());
            loggingEnabled = true;
        }
    }

    public static void log(Severity severity, String str, String str2) {
        Level level;
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Logging tag or message may not be null.");
        }
        if (loggable != null) {
            if (severity.ordinal() < loggableSeverity.ordinal()) {
                return;
            }
            loggable.onLogMessage(str2, severity, str);
        } else if (loggingEnabled) {
            nativeLog(severity.ordinal(), str, str2);
        } else {
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
            Logger logger = fallbackLogger;
            logger.log(level, str + ": " + str2);
        }
    }

    /* renamed from: d */
    public static void m5305d(String str, String str2) {
        log(Severity.LS_INFO, str, str2);
    }

    /* renamed from: e */
    public static void m5304e(String str, String str2) {
        log(Severity.LS_ERROR, str, str2);
    }

    /* renamed from: w */
    public static void m5301w(String str, String str2) {
        log(Severity.LS_WARNING, str, str2);
    }

    /* renamed from: e */
    public static void m5303e(String str, String str2, Throwable th) {
        log(Severity.LS_ERROR, str, str2);
        log(Severity.LS_ERROR, str, th.toString());
        log(Severity.LS_ERROR, str, getStackTraceString(th));
    }

    /* renamed from: w */
    public static void m5300w(String str, String str2, Throwable th) {
        log(Severity.LS_WARNING, str, str2);
        log(Severity.LS_WARNING, str, th.toString());
        log(Severity.LS_WARNING, str, getStackTraceString(th));
    }

    /* renamed from: v */
    public static void m5302v(String str, String str2) {
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
