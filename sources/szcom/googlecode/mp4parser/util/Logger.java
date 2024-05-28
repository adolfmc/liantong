package szcom.googlecode.mp4parser.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Logger {
    public static Logger getLogger(Class cls) {
        return System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik") ? new AndroidLogger(cls.getSimpleName()) : new JuliLogger(cls.getSimpleName());
    }

    public abstract void logDebug(String str);

    public abstract void logError(String str);

    public abstract void logWarn(String str);
}
