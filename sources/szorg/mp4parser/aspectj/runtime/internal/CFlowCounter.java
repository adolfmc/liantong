package szorg.mp4parser.aspectj.runtime.internal;

import szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadCounter;
import szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactory;
import szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl;
import szorg.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl11;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CFlowCounter {
    private static ThreadStackFactory tsFactory;
    private ThreadCounter flowHeightHandler = tsFactory.getNewThreadCounter();

    static {
        selectFactoryForVMVersion();
    }

    private static String getSystemPropertyWithoutSecurityException(String str, String str2) {
        try {
            return System.getProperty(str, str2);
        } catch (SecurityException unused) {
            return str2;
        }
    }

    private static ThreadStackFactory getThreadLocalStackFactory() {
        return new ThreadStackFactoryImpl();
    }

    private static ThreadStackFactory getThreadLocalStackFactoryFor11() {
        return new ThreadStackFactoryImpl11();
    }

    public static String getThreadStackFactoryClassName() {
        return tsFactory.getClass().getName();
    }

    private static void selectFactoryForVMVersion() {
        String systemPropertyWithoutSecurityException = getSystemPropertyWithoutSecurityException("aspectj.runtime.cflowstack.usethreadlocal", "unspecified");
        boolean z = false;
        if (!systemPropertyWithoutSecurityException.equals("unspecified") ? systemPropertyWithoutSecurityException.equals("yes") || systemPropertyWithoutSecurityException.equals("true") : System.getProperty("java.class.version", "0.0").compareTo("46.0") >= 0) {
            z = true;
        }
        tsFactory = z ? getThreadLocalStackFactory() : getThreadLocalStackFactoryFor11();
    }

    public void dec() {
        this.flowHeightHandler.dec();
        if (this.flowHeightHandler.isNotZero()) {
            return;
        }
        this.flowHeightHandler.removeThreadCounter();
    }

    public void inc() {
        this.flowHeightHandler.inc();
    }

    public boolean isValid() {
        return this.flowHeightHandler.isNotZero();
    }
}
