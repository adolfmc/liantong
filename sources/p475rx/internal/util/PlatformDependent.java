package p475rx.internal.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.PlatformDependent */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class PlatformDependent {
    private static final int ANDROID_API_VERSION = resolveAndroidApiVersion();
    public static final int ANDROID_API_VERSION_IS_NOT_ANDROID = 0;
    private static final boolean IS_ANDROID;

    static {
        IS_ANDROID = ANDROID_API_VERSION != 0;
    }

    public static boolean isAndroid() {
        return IS_ANDROID;
    }

    public static int getAndroidApiVersion() {
        return ANDROID_API_VERSION;
    }

    private static int resolveAndroidApiVersion() {
        try {
            return ((Integer) Class.forName("android.os.Build$VERSION", true, getSystemClassLoader()).getField("SDK_INT").get(null)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() { // from class: rx.internal.util.PlatformDependent.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public ClassLoader run() {
                return ClassLoader.getSystemClassLoader();
            }
        });
    }
}
