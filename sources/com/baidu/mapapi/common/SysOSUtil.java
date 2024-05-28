package com.baidu.mapapi.common;

import android.text.TextUtils;
import com.baidu.mapsdkplatform.comapi.util.SysOSAPI;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SysOSUtil {
    public static int getDensityDpi() {
        return SysOSAPI.m18123n();
    }

    public static float getDensity() {
        return SysOSAPI.f7442b;
    }

    public static String getModuleFileName() {
        return SysOSAPI.m18120q();
    }

    public static String getDeviceID() {
        String m18119r = SysOSAPI.m18119r();
        return TextUtils.isEmpty(m18119r) ? m18119r : m18119r.substring(0, m18119r.indexOf("|"));
    }

    public static int getScreenSizeX() {
        return SysOSAPI.m18127j();
    }

    public static int getScreenSizeY() {
        return SysOSAPI.m18125l();
    }

    public static String getPhoneType() {
        return SysOSAPI.m18128i();
    }
}
