package com.baidu.mapsdkplatform.comapi.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SyncSysInfo {
    public static String getAuthToken() {
        return SysOSAPI.f7443c;
    }

    public static String getPhoneInfo() {
        return SysOSAPI.m18133e();
    }

    public static String initPhoneInfo() {
        return SysOSAPI.m18137c();
    }

    public static String getSoftWareVer() {
        return SysOSAPI.m18126k();
    }

    public static String getCid() {
        return SysOSAPI.m18119r();
    }

    public static String getPhoneInfoCache() {
        return SysOSAPI.m18135d();
    }
}
