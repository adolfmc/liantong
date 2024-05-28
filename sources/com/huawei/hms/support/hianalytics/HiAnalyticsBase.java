package com.huawei.hms.support.hianalytics;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HiAnalyticsBase {
    /* JADX INFO: Access modifiers changed from: protected */
    public static Map<String, String> getMapForBi(Context context, String str) {
        HashMap hashMap = new HashMap();
        if (context != null && !TextUtils.isEmpty(str)) {
            String[] split = str.split("\\.");
            if (split.length >= 2) {
                String str2 = split[0];
                String str3 = split[1];
                hashMap.put("service", str2);
                hashMap.put("apiName", str3);
                hashMap.put("package", context.getPackageName());
                hashMap.put("baseVersion", "6.9.0.300");
                hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
            }
        }
        return hashMap;
    }
}
