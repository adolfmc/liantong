package com.huawei.hms.support.hianalytics;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HiAnalyticsUtil {

    /* renamed from: a */
    private static final Object f11735a = new Object();

    /* renamed from: b */
    private static HiAnalyticsUtil f11736b;

    /* renamed from: c */
    private static HiAnalyticsUtils f11737c;

    /* renamed from: a */
    private String m14133a(Context context, String str) {
        String packageName = context.getPackageName();
        return "01||" + packageName + "|" + Util.getAppId(context) + "|60900300|" + str;
    }

    public static HiAnalyticsUtil getInstance() {
        HiAnalyticsUtil hiAnalyticsUtil;
        synchronized (f11735a) {
            if (f11736b == null) {
                f11736b = new HiAnalyticsUtil();
                f11737c = HiAnalyticsUtils.getInstance();
            }
            hiAnalyticsUtil = f11736b;
        }
        return hiAnalyticsUtil;
    }

    public static Map<String, String> getMapFromForegroundResponseHeader(ResponseHeader responseHeader) {
        HashMap hashMap = new HashMap();
        if (responseHeader == null) {
            return hashMap;
        }
        hashMap.put("transId", responseHeader.getTransactionId());
        hashMap.put("appid", responseHeader.getActualAppID());
        hashMap.put("service", responseHeader.getSrvName());
        hashMap.put("apiName", responseHeader.getApiName());
        hashMap.put("package", responseHeader.getPkgName());
        hashMap.put("statusCode", String.valueOf(responseHeader.getStatusCode()));
        hashMap.put("result", String.valueOf(responseHeader.getErrorCode()));
        hashMap.put("errorReason", responseHeader.getErrorReason());
        hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        hashMap.put("baseVersion", "6.9.0.300");
        return hashMap;
    }

    public static Map<String, String> getMapFromRequestHeader(ResponseHeader responseHeader) {
        HashMap hashMap = new HashMap();
        if (responseHeader == null) {
            return hashMap;
        }
        hashMap.put("transId", responseHeader.getTransactionId());
        hashMap.put("appid", responseHeader.getActualAppID());
        hashMap.put("service", responseHeader.getSrvName());
        String apiName = responseHeader.getApiName();
        if (!TextUtils.isEmpty(apiName)) {
            String[] split = apiName.split("\\.");
            if (split.length >= 2) {
                hashMap.put("apiName", split[1]);
            }
        }
        hashMap.put("package", responseHeader.getPkgName());
        hashMap.put("statusCode", String.valueOf(responseHeader.getStatusCode()));
        hashMap.put("result", String.valueOf(responseHeader.getErrorCode()));
        hashMap.put("errorReason", responseHeader.getErrorReason());
        hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        hashMap.put("baseVersion", "6.9.0.300");
        return hashMap;
    }

    public static String versionCodeToName(String str) {
        return HiAnalyticsUtils.versionCodeToName(str);
    }

    @Deprecated
    public Map<String, String> getMapForBi(Context context, String str) {
        HashMap hashMap = new HashMap();
        String[] split = str.split("\\.");
        if (split.length >= 2) {
            String str2 = split[0];
            String str3 = split[1];
            String appId = Util.getAppId(context);
            hashMap.put("transId", TransactionIdCreater.getId(appId, str));
            hashMap.put("appid", appId);
            hashMap.put("service", str2);
            hashMap.put("apiName", str3);
            hashMap.put("package", context.getPackageName());
            hashMap.put("version", "6.9.0.300");
            hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        }
        return hashMap;
    }

    public Map<String, String> getMapFromForegroundRequestHeader(RequestHeader requestHeader) {
        HashMap hashMap = new HashMap();
        if (requestHeader == null) {
            return hashMap;
        }
        hashMap.put("transId", requestHeader.getTransactionId());
        hashMap.put("appid", requestHeader.getActualAppID());
        hashMap.put("service", requestHeader.getSrvName());
        hashMap.put("apiName", requestHeader.getApiName());
        hashMap.put("package", requestHeader.getPkgName());
        hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        hashMap.put("baseVersion", "6.9.0.300");
        return hashMap;
    }

    @Deprecated
    public boolean hasError() {
        if (SystemUtils.isChinaROM()) {
            return false;
        }
        HMSLog.m14110i("HiAnalyticsUtil", "not ChinaROM ");
        return true;
    }

    public void onBuoyEvent(Context context, String str, String str2) {
        f11737c.onBuoyEvent(context, str, str2);
    }

    public void onEvent(Context context, String str, Map<String, String> map) {
        f11737c.onEvent(context, str, map);
    }

    public void onEvent2(Context context, String str, String str2) {
        f11737c.onEvent2(context, str, str2);
    }

    public void onNewEvent(Context context, String str, Map map) {
        f11737c.onNewEvent(context, str, map);
    }

    public void onEvent(Context context, String str, String str2) {
        if (context != null) {
            onEvent2(context, str, m14133a(context, str2));
        }
    }

    public boolean hasError(Context context) {
        return f11737c.hasError(context);
    }

    public Map<String, String> getMapFromRequestHeader(RequestHeader requestHeader) {
        HashMap hashMap = new HashMap();
        if (requestHeader == null) {
            return hashMap;
        }
        hashMap.put("transId", requestHeader.getTransactionId());
        hashMap.put("appid", requestHeader.getActualAppID());
        hashMap.put("service", requestHeader.getSrvName());
        String apiName = requestHeader.getApiName();
        if (!TextUtils.isEmpty(apiName)) {
            String[] split = apiName.split("\\.");
            if (split.length >= 2) {
                hashMap.put("apiName", split[1]);
            }
        }
        hashMap.put("package", requestHeader.getPkgName());
        hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        hashMap.put("baseVersion", "6.9.0.300");
        return hashMap;
    }
}
