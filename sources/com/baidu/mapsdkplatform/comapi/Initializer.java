package com.baidu.mapsdkplatform.comapi;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapsdkplatform.comapi.p142b.p143a.NativeCrashUtil;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.platform.comapi.util.SysOSUtil;
import com.baidu.platform.comapi.util.p156a.DpiInfo;
import com.baidu.platform.comapi.util.p156a.PathInfo;
import java.io.File;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Initializer {

    /* renamed from: a */
    private static boolean f7133a;

    /* renamed from: b */
    private static boolean f7134b;

    /* renamed from: a */
    public static void m18477a(Context context, boolean z, String str, String str2, String str3) {
        if (f7133a) {
            return;
        }
        if (context == null) {
            throw new IllegalArgumentException("BDMapSDKException: context can not be null");
        }
        if (!(context instanceof Application)) {
            throw new RuntimeException("BDMapSDKException: context must be an ApplicationContext");
        }
        NativeLoader.setContext(context);
        NativeLoader.m18551a(z, str);
        JNIInitializer.setContext((Application) context);
        SysOSUtil.getInstance().init(new PathInfo(), new DpiInfo());
        BMapManagerInternal.m18539a().m18538a(context);
        BMapManagerInternal.m18539a().m18534c();
        if (m18476a(str2)) {
            EnvironmentUtilities.setSDCardPath(str2);
        }
        EnvironmentUtilities.initAppDirectory(context);
        if (OpenLogUtil.isNativeLogAnalysisEnable()) {
            NativeCrashUtil.m18496a().m18495a(context);
        }
        f7133a = true;
    }

    /* renamed from: a */
    public static boolean m18479a() {
        return f7133a;
    }

    /* renamed from: a */
    public static void m18478a(Context context, boolean z) {
        if (context == null) {
            throw new IllegalArgumentException("BDMapSDKException: context can not be null");
        }
        if (!(context instanceof Application)) {
            throw new RuntimeException("BDMapSDKException: context must be an ApplicationContext");
        }
        f7134b = z;
        PermissionCheck.setPrivacyMode(z);
        LBSAuthManager.getInstance(context).setPrivacyMode(z);
    }

    /* renamed from: b */
    public static boolean m18475b() {
        return f7134b;
    }

    /* renamed from: a */
    private static boolean m18476a(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            File file = new File(str + "/check.0");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            if (file.exists()) {
                file.delete();
                return true;
            }
            return true;
        } catch (IOException e) {
            Log.e("SDKInitializer", "SDCard cache path invalid", e);
            throw new IllegalArgumentException("BDMapSDKException: Provided sdcard cache path invalid can not used.");
        }
    }
}
