package com.baidu.mapapi;

import com.baidu.mapsdkplatform.comjni.tools.AppTools;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OpenLogUtil {

    /* renamed from: a */
    private static ModuleName f5840a = null;

    /* renamed from: b */
    private static boolean f5841b = true;

    /* renamed from: c */
    private static boolean f5842c;

    /* renamed from: d */
    private static String f5843d;

    public static void setModuleLogEnable(ModuleName moduleName, boolean z) {
        f5840a = moduleName;
        AppTools.m18114a(z, f5840a.ordinal());
    }

    public static void setNativeLogAnalysisEnable(boolean z) {
        f5841b = z;
    }

    public static boolean isNativeLogAnalysisEnable() {
        return f5841b;
    }

    public static void setMapLogFilePath(String str) {
        f5843d = str;
    }

    public static String getMapLogFilePath() {
        return f5843d;
    }

    public static void setMapLogEnable(boolean z) {
        f5842c = z;
    }

    public static boolean isMapLogEnable() {
        return f5842c;
    }
}
