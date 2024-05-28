package com.baidu.p120ar.arplay.util;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.util.LogUtil */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LogUtil {
    private static final boolean ENGINE_DEBUG_ENABLE = true;
    public static final int LEVEL_DBG = 1;
    public static final int LEVEL_ERROR = 3;
    public static final int LEVEL_INFO = 0;
    public static final int LEVEL_UNKOWN = 4;
    public static final int LEVEL_WARN = 2;
    public static final int MODULE_DEFAULT = 0;
    public static final int MODULE_FILTERFRAMEWORK = 2;
    public static final int MODULE_INTERFACE = 1;
    public static final int MODULE_MAKEUP = 3;
    public static final int MODULE_PROGRAM = 6;
    public static final int MODULE_SHADER = 5;
    public static final int MODULE_THREEDENGINE = 4;

    static native void nativeEnableEngineModuleLog(int i, boolean z);

    static native void nativeSetEngineLogLevel(int i, boolean z);

    /* renamed from: e */
    public static void m20423e(String str, String str2) {
        Log.e(str, str2);
    }

    /* renamed from: d */
    public static void m20424d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void setEngineLogLevel(int i, boolean z) {
        nativeSetEngineLogLevel(i, z);
    }

    public static void enableEnableEngineModuleLog(int i, boolean z) {
        nativeEnableEngineModuleLog(i, z);
    }
}
