package cn.finalteam.toolsfinal.logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LoggerFactory {
    public static LoggerPrinter getFactory(String str, boolean z) {
        LoggerPrinter loggerPrinter = new LoggerPrinter();
        loggerPrinter.init(str);
        LogLevel logLevel = LogLevel.NONE;
        if (z) {
            logLevel = LogLevel.FULL;
        }
        loggerPrinter.getSettings().methodCount(3).logLevel(logLevel);
        return loggerPrinter;
    }
}
