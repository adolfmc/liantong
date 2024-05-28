package com.p281qq.p282e.comm.managers.status;

import com.p281qq.p282e.comm.managers.C6873b;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.managers.status.SDKStatus */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SDKStatus {
    public static final int SDK_CHANNEL = 1;
    public static final String SDK_EX1 = "";
    public static final String SDK_EX2 = "";
    public static final int STUB_IDENTITY = 1;
    public static final boolean isNoPlugin = false;

    public static final int getBuildInPluginVersion() {
        return 1384;
    }

    public static final String getIntegrationSDKVersion() {
        return "4.514." + getBuildInPluginVersion();
    }

    public static final int getPluginVersion() {
        if (C6873b.m8276b().m8274d()) {
            return C6873b.m8276b().m8275c().getPluginVersion();
        }
        return 0;
    }

    public static final String getSDKVersion() {
        return "4.514";
    }
}
