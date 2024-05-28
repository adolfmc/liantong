package com.baidu.p120ar.auth;

import com.baidu.p120ar.libloader.ILibLoader;
import com.baidu.p120ar.libloader.LibLoader;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.UiThreadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.AuthJni */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthJni {
    private static volatile AuthJni sJni;
    private final List<Runnable> mReadyTasks = new ArrayList();
    private boolean mIsReady = false;

    native void nativeDestroy();

    native int nativeGetVersion();

    native void nativeInit();

    native void nativePutGrantedFeatures(int[] iArr);

    native void nativeSetGrantedFeatures(int[] iArr);

    private AuthJni() {
    }

    public static void init() {
        sJni = new AuthJni();
        LibLoader.require("BARDumix");
        LibLoader.setLibReadyListener("BARDumix", new ILibLoader.ReadyListener() { // from class: com.baidu.ar.auth.AuthJni.1
            @Override // com.baidu.p120ar.libloader.ILibLoader.ReadyListener
            public void onReady() {
                UiThreadUtils.runOnUiThread(new Runnable() { // from class: com.baidu.ar.auth.AuthJni.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (AuthJni.sJni != null) {
                            try {
                                AuthJni.sJni.nativeInit();
                                AuthJni.sJni.mIsReady = true;
                                AuthJni.sJni.executeReadyTasks();
                            } catch (Throwable th) {
                                th.printStackTrace();
                                AuthJni.reportException(AuthJni.sJni.getVersionStr(), th.getMessage());
                            }
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportException(String str, String str2) {
        String name = Thread.currentThread().getName();
        HashMap hashMap = new HashMap();
        hashMap.put("event_param", name + "_" + str + "_" + str2);
        hashMap.put("feature_code", str);
        StatisticApi.onEvent("event_authjni_error", hashMap);
    }

    public static void setGrantedFeatures(final int[] iArr) {
        if (sJni == null) {
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.baidu.ar.auth.AuthJni.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (AuthJni.sJni != null) {
                        AuthJni.sJni.nativeSetGrantedFeatures(iArr);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                try {
                    if (AuthJni.sJni != null) {
                        AuthJni.sJni.nativePutGrantedFeatures(iArr);
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        };
        if (sJni.mIsReady) {
            UiThreadUtils.runOnUiThread(runnable);
        } else {
            sJni.mReadyTasks.add(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeReadyTasks() {
        for (Runnable runnable : this.mReadyTasks) {
            runnable.run();
        }
        this.mReadyTasks.clear();
    }

    public static void release() {
        if (sJni != null) {
            sJni.mReadyTasks.clear();
            UiThreadUtils.runOnUiThread(new Runnable() { // from class: com.baidu.ar.auth.AuthJni.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AuthJni.sJni.nativeDestroy();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    AuthJni unused = AuthJni.sJni = null;
                }
            });
        }
    }

    public void sendAuthFailMessageFromNative(int i) {
        ARAuth.receiveAuthFailMessage(i);
    }

    public String getVersionStr() {
        try {
            int nativeGetVersion = nativeGetVersion();
            return "ver-" + nativeGetVersion;
        } catch (Throwable th) {
            th.printStackTrace();
            return "EXCEPTION-UNKNOWN";
        }
    }
}
