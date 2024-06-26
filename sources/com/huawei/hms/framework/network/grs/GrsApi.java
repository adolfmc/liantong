package com.huawei.hms.framework.network.grs;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class GrsApi {
    private static final String TAG = "GrsApi";
    private static GrsClient grsClient;

    @Deprecated
    public static void ayncGetGrsUrl(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack) {
        if (iQueryUrlCallBack == null) {
            Logger.m15045w("GrsApi", "IQueryUrlCallBack is must not null for process continue.");
            return;
        }
        GrsClient grsClient2 = grsClient;
        if (grsClient2 == null || str == null || str2 == null) {
            iQueryUrlCallBack.onCallBackFail(-6);
        } else {
            grsClient2.ayncGetGrsUrl(str, str2, iQueryUrlCallBack);
        }
    }

    @Deprecated
    public static void ayncGetGrsUrls(String str, IQueryUrlsCallBack iQueryUrlsCallBack) {
        if (iQueryUrlsCallBack == null) {
            Logger.m15045w("GrsApi", "IQueryUrlsCallBack is must not null for process continue.");
            return;
        }
        GrsClient grsClient2 = grsClient;
        if (grsClient2 == null || str == null) {
            iQueryUrlsCallBack.onCallBackFail(-6);
        } else {
            grsClient2.ayncGetGrsUrls(str, iQueryUrlsCallBack);
        }
    }

    @Deprecated
    public static void clearSp() {
        GrsClient grsClient2 = grsClient;
        if (grsClient2 == null) {
            Logger.m15045w("GrsApi", "GrsApi.clearSp return because grsClient is null.");
        } else {
            grsClient2.clearSp();
        }
    }

    @Deprecated
    public static boolean forceExpire() {
        GrsClient grsClient2 = grsClient;
        if (grsClient2 == null) {
            Logger.m15045w("GrsApi", "GrsApi.forceExpire return false because grsClient is null.");
            return false;
        }
        return grsClient2.forceExpire();
    }

    @Deprecated
    public static CountryCodeBean getCountryCode(Context context, boolean z) {
        return C4916a.m15040a(context, z);
    }

    @Deprecated
    public static int grsSdkInit(Context context, GrsBaseInfo grsBaseInfo) {
        Logger.m15045w("GrsApi", "GrsApi.grsSdkInit is @Deprecated,  And Only Application Can Call Once");
        grsClient = new GrsClient(context, grsBaseInfo);
        return 0;
    }

    @Deprecated
    public static String synGetGrsUrl(String str, String str2) {
        GrsClient grsClient2 = grsClient;
        if (grsClient2 == null || str == null || str2 == null) {
            Logger.m15045w("GrsApi", "GrsApi.synGetGrsUrl method maybe grsSdkInit has not completed and grsClient is null.");
            return null;
        }
        return grsClient2.synGetGrsUrl(str, str2);
    }

    @Deprecated
    public static Map<String, String> synGetGrsUrls(String str) {
        GrsClient grsClient2 = grsClient;
        if (grsClient2 == null || str == null) {
            Logger.m15045w("GrsApi", "GrsApi.synGetGrsUrls method maybe grsSdkInit has not completed and grsClient is null.");
            return new HashMap();
        }
        return grsClient2.synGetGrsUrls(str);
    }
}
