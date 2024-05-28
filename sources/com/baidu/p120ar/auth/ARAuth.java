package com.baidu.p120ar.auth;

import android.content.Context;
import android.graphics.Bitmap;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.utils.DynamicPluginLoader;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.ARAuth */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARAuth {
    private static final String AUTH_IMPL_CLASS = "com.baidu.ar.auth.ARAuthFacade";
    private static DynamicPluginLoader<IAuthFacade> sLoader = new DynamicPluginLoader<>("com.baidu.ar.auth.ARAuthFacade");

    public static void setAuthLicense(byte[] bArr, String str, String str2, String str3) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            plugin.setAuthLicense(bArr, str, str2, str3);
        }
    }

    public static void loadAuthInfo(Context context) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            plugin.loadAuthInfo(context);
        }
    }

    public static boolean checkOfflineLicenseAuth(Context context, byte[] bArr) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            return plugin.checkOfflineLicenseAuth(context, bArr);
        }
        return true;
    }

    public static List<Integer> checkAuth(Context context, byte[] bArr, ICallbackWith<List<Integer>> iCallbackWith, ICallbackWith<Integer> iCallbackWith2) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            return plugin.checkAuth(context, bArr, iCallbackWith, iCallbackWith2);
        }
        return new ArrayList();
    }

    public static List<Integer> checkAuth(Context context, byte[] bArr, IDuMixAuthCallback iDuMixAuthCallback) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            return plugin.checkAuth(context, bArr, iDuMixAuthCallback);
        }
        return new ArrayList();
    }

    public static void doAuth(Context context, IAuthCallback iAuthCallback) {
        AuthJni.init();
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            plugin.doAuth(context, iAuthCallback);
        }
    }

    public static boolean checkFeatureAuth(int i) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            return plugin.checkFeatureAuth(i);
        }
        return true;
    }

    public static boolean enableFeature(int i) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            return plugin.enableFeature(i);
        }
        return true;
    }

    public static boolean isShowAuthTip() {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            return plugin.isShowAuthTip();
        }
        return true;
    }

    public static Bitmap createTipBitmap(Context context) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            return plugin.createTipBitmap(context);
        }
        return null;
    }

    public static void release() {
        IAuthFacade currentInstance = sLoader.getCurrentInstance();
        if (currentInstance != null) {
            currentInstance.release();
        }
        sLoader.release();
        AuthJni.release();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void receiveAuthFailMessage(int i) {
        IAuthFacade plugin = sLoader.getPlugin();
        if (plugin != null) {
            plugin.receiveAuthFailMessage(i);
        }
    }
}
