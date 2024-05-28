package com.huawei.hms.support.hianalytics;

import android.content.Context;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HiAnalyticsClient extends HiAnalyticsBase {
    private static final String TAG = "HiAnalyticsClient";

    @Deprecated
    public static String reportEntry(Context context, String str) {
        if (context == null) {
            HMSLog.m14112e("HiAnalyticsClient", "<reportEntry 2 param> context is null.");
            return "";
        }
        return reportEntry(context, str, Util.getAppId(context), 0);
    }

    public static void reportExit(Context context, String str, String str2, int i, int i2) {
        if (context == null) {
            HMSLog.m14112e("HiAnalyticsClient", "<reportExit 5 param> context is null.");
        } else {
            reportExit(context, str, str2, Util.getAppId(context), i, i2, 0);
        }
    }

    public static String reportEntry(Context context, String str, int i) {
        if (context == null) {
            HMSLog.m14112e("HiAnalyticsClient", "<reportEntry 3 param> context is null.");
            return "";
        }
        return reportEntry(context, str, Util.getAppId(context), i);
    }

    public static void reportExit(Context context, String str, String str2, int i, int i2, int i3) {
        if (context == null) {
            HMSLog.m14112e("HiAnalyticsClient", "<reportExit 6 param> context is null.");
        } else {
            reportExit(context, str, str2, Util.getAppId(context), i, i2, i3);
        }
    }

    public static String reportEntry(Context context, String str, String str2, int i) {
        if (context == null) {
            HMSLog.m14112e("HiAnalyticsClient", "<reportEntry 4 param> context is null.");
            return "";
        }
        String id = TransactionIdCreater.getId(str2, str);
        Map<String, String> mapForBi = HiAnalyticsBase.getMapForBi(context, str);
        mapForBi.put("appid", str2);
        mapForBi.put("transId", id);
        mapForBi.put("direction", "req");
        mapForBi.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(i)));
        mapForBi.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, "HMS_SDK_KIT_API_CALLED", mapForBi);
        return id;
    }

    public static void reportExit(Context context, String str, String str2, String str3, int i, int i2, int i3) {
        if (context == null) {
            HMSLog.m14112e("HiAnalyticsClient", "<reportExit 7 param> context is null.");
            return;
        }
        Map<String, String> mapForBi = HiAnalyticsBase.getMapForBi(context, str);
        mapForBi.put("appid", str3);
        mapForBi.put("transId", str2);
        mapForBi.put("direction", "rsp");
        mapForBi.put("statusCode", String.valueOf(i));
        mapForBi.put("result", String.valueOf(i2));
        mapForBi.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(i3)));
        mapForBi.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, "HMS_SDK_KIT_API_CALLED", mapForBi);
    }
}
