package com.alipay.apmobilesecuritysdk.otherid;

import android.content.Context;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.security.mobile.module.p113c.C2093d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class UmidSdkWrapper {
    private static final String UMIDTOKEN_FILE_NAME = "xxxwww_v2";
    private static final String UMIDTOKEN_KEY_NAME = "umidtk";
    private static volatile String cachedUmidToken = "";
    private static volatile boolean initUmidFinished;

    private static String compatUmidBug(Context context, String str) {
        if (C2081a.m20577a(str) || C2081a.m20576a(str, "000000000000000000000000")) {
            String utdid = UtdidWrapper.getUtdid(context);
            if (utdid != null && utdid.contains("?")) {
                utdid = "";
            }
            return C2081a.m20577a(utdid) ? "" : utdid;
        }
        return str;
    }

    public static synchronized String getSecurityToken(Context context) {
        String str;
        synchronized (UmidSdkWrapper.class) {
            str = cachedUmidToken;
        }
        return str;
    }

    public static String startUmidTaskSync(Context context, int i) {
        return "";
    }

    private static synchronized void updateLocalUmidToken(Context context, String str) {
        synchronized (UmidSdkWrapper.class) {
            if (C2081a.m20573b(str)) {
                C2093d.m20488a(context, "xxxwww_v2", "umidtk", str);
                cachedUmidToken = str;
            }
        }
    }
}
