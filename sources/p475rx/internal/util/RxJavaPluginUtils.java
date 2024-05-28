package p475rx.internal.util;

import java.io.PrintStream;
import p475rx.plugins.RxJavaPlugins;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.RxJavaPluginUtils */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class RxJavaPluginUtils {
    public static void handleException(Throwable th) {
        try {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
        } catch (Throwable th2) {
            handlePluginException(th2);
        }
    }

    private static void handlePluginException(Throwable th) {
        PrintStream printStream = System.err;
        printStream.println("RxJavaErrorHandler threw an Exception. It shouldn't. => " + th.getMessage());
        th.printStackTrace();
    }
}
