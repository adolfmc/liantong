package com.huawei.hms.opendevice;

import android.content.Context;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.opendevice.i */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class PushBiUtil {
    /* renamed from: a */
    public static String m14373a(Context context, String str) {
        return HiAnalyticsClient.reportEntry(context, str, 61100100);
    }

    /* renamed from: a */
    public static void m14372a(Context context, String str, ResponseErrorCode responseErrorCode) {
        HiAnalyticsClient.reportExit(context, str, responseErrorCode.getTransactionId(), responseErrorCode.getStatusCode(), responseErrorCode.getErrorCode(), 61100100);
    }

    /* renamed from: a */
    public static void m14370a(Context context, String str, String str2, ErrorEnum errorEnum) {
        HiAnalyticsClient.reportExit(context, str, str2, Status.SUCCESS.getStatusCode(), errorEnum.getExternalCode(), 61100100);
    }

    /* renamed from: a */
    public static void m14371a(Context context, String str, String str2, int i) {
        HiAnalyticsClient.reportExit(context, str, str2, Status.SUCCESS.getStatusCode(), i, 61100100);
    }
}
