package com.huawei.hms.push;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.plugin.PushProxy;
import com.huawei.hms.push.utils.p223ha.PushAnalyticsCenter;
import com.huawei.hms.push.utils.p223ha.PushBaseAnalytics;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.j */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PushAnalyticsUtils {

    /* renamed from: a */
    private static final String f11630a = "j";

    /* renamed from: a */
    public static void m14262a(Context context, String str, String str2, String str3) {
        PushBaseAnalytics pushAnalytics = PushAnalyticsCenter.getInstance().getPushAnalytics();
        if (pushAnalytics == null) {
            return;
        }
        Bundle m14263a = m14263a(context, str, str2);
        String str4 = f11630a;
        HMSLog.m14110i(str4, "eventId:" + str3);
        pushAnalytics.report(context, str3, m14263a);
    }

    /* renamed from: a */
    private static Bundle m14263a(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("sdkVer", String.valueOf(61100300));
        bundle.putString("pkgName", context.getPackageName());
        bundle.putString("aaid", HmsInstanceId.getInstance(context).getId());
        PushProxy proxy = ProxyCenter.getProxy();
        if (proxy != null) {
            bundle.putString("proxyType", proxy.getProxyType());
        }
        bundle.putString("msgId", str);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("analyticInfo", str2);
        }
        return bundle;
    }

    /* renamed from: a */
    public static void m14264a(Context context, Bundle bundle, String str) {
        PushBaseAnalytics pushAnalytics;
        if (bundle == null || (pushAnalytics = PushAnalyticsCenter.getInstance().getPushAnalytics()) == null) {
            return;
        }
        bundle.putString("sdk_version", String.valueOf(61100300));
        String str2 = f11630a;
        HMSLog.m14110i(str2, "eventId:" + str);
        pushAnalytics.report(context, str, bundle);
    }
}
