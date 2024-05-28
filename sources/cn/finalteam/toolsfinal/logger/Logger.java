package cn.finalteam.toolsfinal.logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Logger {
    public static final String DEFAULT_TAG = "Logger";
    private static boolean debug;
    private static LoggerPrinter loggerPrinter;

    public static void setDebug(boolean z) {
        debug = z;
    }

    public static LoggerPrinter getDefaultLogger() {
        if (loggerPrinter == null) {
            loggerPrinter = LoggerFactory.getFactory("Logger", debug);
        }
        return loggerPrinter;
    }
}
