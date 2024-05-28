package com.baidu.platform.comjni.engine;

import android.content.Context;
import android.os.Bundle;
import com.baidu.platform.comapi.exception.ComInitException;
import com.baidu.platform.comapi.longlink.LongLinkClient;
import com.baidu.platform.comjni.NativeComponent;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NAEngine extends NativeComponent {

    /* renamed from: a */
    private static LongLinkClient f8076a;

    /* renamed from: b */
    private static boolean f8077b;

    public NAEngine() {
        create();
    }

    /* renamed from: a */
    public static void m17676a() {
        nativeInitClass(new Bundle(), 0);
        f8077b = true;
    }

    /* renamed from: a */
    public static void m17675a(int i) {
        nativeMonitorSetOutPutType(i);
    }

    /* renamed from: a */
    public static void m17674a(int i, String str, String str2) {
        nativeMonitorAddLog(i, str, str2);
    }

    /* renamed from: a */
    public static void m17672a(String str) {
        nativeInitMonitor(str);
    }

    /* renamed from: a */
    public static void m17671a(boolean z) {
        nativeEnableMonitor(z);
    }

    /* renamed from: a */
    public static void m17670a(String[] strArr) {
        nativeSetMonitorLogFilter(strArr);
    }

    /* renamed from: a */
    public static boolean m17673a(Context context, String str) {
        if (!f8077b) {
            m17676a();
        }
        try {
            return nativeInitEngine(context, str);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static void m17668b(int i) {
        nativeMonitorSetLogPriority(i);
    }

    /* renamed from: b */
    public static boolean m17669b() {
        try {
            if (f8076a != null) {
                f8076a.unRegister(null);
                f8076a.release();
                f8076a = null;
            }
            return nativeUninitEngine();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m17667c() {
        return nativeStartSocketProc();
    }

    public static String getIP(String str) {
        return nativeGetIP(str);
    }

    public static boolean initLongLinkClient() {
        if (f8076a == null) {
            try {
                f8076a = LongLinkClient.create();
            } catch (ComInitException unused) {
            }
        }
        return f8076a != null;
    }

    private native long nativeCreate();

    private static native void nativeEnableMonitor(boolean z);

    private static native boolean nativeGetFlaxLength(Bundle bundle);

    private static native String nativeGetIP(String str);

    private static native void nativeInitCVLogFilePath(String str);

    private static native int nativeInitClass(Object obj, int i);

    private static native boolean nativeInitEngine(Object obj, String str);

    private static native void nativeInitMonitor(String str);

    private static native void nativeMonitorAddLog(int i, String str, String str2);

    private static native void nativeMonitorSetLogPriority(int i);

    private static native void nativeMonitorSetOutPutType(int i);

    private native int nativeRelease(long j);

    private static native void nativeSetHttpsEnable(boolean z);

    private static native void nativeSetMonitorLogFilter(String[] strArr);

    private static native void nativeSetNewDomainEnable(boolean z);

    private static native void nativeSetProxyInfo(String str, int i);

    private static native void nativeStartRunningRequest();

    private static native boolean nativeStartSocketProc();

    private static native boolean nativeStartSocketProcByCache(String str);

    private static native void nativeSyncAppRuntime(String str);

    private static native void nativeUninitCVLogFilePath();

    private static native boolean nativeUninitEngine();

    public static void restartLongLink() {
        LongLinkClient longLinkClient = f8076a;
        if (longLinkClient != null) {
            try {
                longLinkClient.start();
            } catch (Exception unused) {
            }
        }
    }

    public static void startRunningRequest() {
        nativeStartRunningRequest();
    }

    public static void stopLongLink() {
        LongLinkClient longLinkClient = f8076a;
        if (longLinkClient != null) {
            try {
                longLinkClient.stop();
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.baidu.platform.comjni.NativeComponent
    public long create() {
        this.mNativePointer = nativeCreate();
        return this.mNativePointer;
    }

    @Override // com.baidu.platform.comjni.NativeComponent
    public int dispose() {
        return nativeRelease(this.mNativePointer);
    }
}
