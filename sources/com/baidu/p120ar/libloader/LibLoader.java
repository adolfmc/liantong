package com.baidu.p120ar.libloader;

import android.content.Context;
import com.baidu.p120ar.ARType;
import com.baidu.p120ar.libloader.ILibLoader;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.libloader.LibLoader */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class LibLoader {
    private static volatile ILibLoader sLoader;
    private static Object sLock = new Object();
    private static boolean sIsRegistered = false;
    private static volatile boolean sIsReleased = false;

    private static ILibLoader getLoader() {
        if (sLoader == null) {
            synchronized (sLock) {
                if (sLoader == null) {
                    sLoader = new LocalLibLoader();
                }
            }
        }
        return sLoader;
    }

    public static void require(String str) {
        if (sIsReleased) {
            return;
        }
        getLoader().require(str);
    }

    public static void load(Context context, ILibLoader.ILoadListener iLoadListener) {
        if (sIsReleased) {
            return;
        }
        getLoader().load(context, iLoadListener);
    }

    public static void setLibReadyListener(String str, ILibLoader.ReadyListener readyListener) {
        getLoader().setLibReadyListener(str, readyListener);
    }

    public static void prepareCaseRes(ARType aRType, String str, String str2, ILibLoader.CaseReadyListener caseReadyListener) {
        if (sIsReleased) {
            return;
        }
        getLoader().prepareCaseRes(aRType, str, str2, caseReadyListener);
    }

    public static void registerLibLoader(ILibLoader iLibLoader) {
        synchronized (sLock) {
            sLoader = iLibLoader;
            sIsRegistered = true;
        }
        sIsReleased = false;
    }

    public static void useDefault() {
        sIsReleased = false;
    }

    public static boolean isRegistered() {
        boolean z;
        synchronized (sLock) {
            z = sIsRegistered;
        }
        return z;
    }

    public static void setLibLoadPlugin(ILibLoaderPlugin iLibLoaderPlugin) {
        getLoader().setLibLoadPlugin(iLibLoaderPlugin);
    }

    public static void release() {
        sIsReleased = true;
        sIsRegistered = false;
        if (sLoader != null) {
            sLoader.release();
            sLoader = null;
        }
    }
}
