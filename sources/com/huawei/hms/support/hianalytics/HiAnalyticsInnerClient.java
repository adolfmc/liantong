package com.huawei.hms.support.hianalytics;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.utils.Util;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HiAnalyticsInnerClient extends HiAnalyticsBase {
    public static void reportEntryClient(Context context, String str, String str2, String str3, String str4) {
        Map<String, String> mapForBi = HiAnalyticsBase.getMapForBi(context, str);
        mapForBi.put("appid", str2);
        if (TextUtils.isEmpty(str3)) {
            str3 = TransactionIdCreater.getId(str2, str);
        }
        mapForBi.put("transId", str3);
        mapForBi.put("direction", "req");
        if (!TextUtils.isEmpty(str4)) {
            mapForBi.put("version", HiAnalyticsUtil.versionCodeToName(str4));
        }
        mapForBi.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, "HMS_SDK_BASE_API_CALLED", mapForBi);
    }

    public static void reportEntryExit(Context context, ResponseHeader responseHeader, String str) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader);
        mapFromRequestHeader.put("direction", "rsp");
        if (!TextUtils.isEmpty(str)) {
            mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(str));
        }
        mapFromRequestHeader.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, "HMS_SDK_BASE_API_CALLED", mapFromRequestHeader);
    }
}
