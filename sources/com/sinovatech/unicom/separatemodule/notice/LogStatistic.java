package com.sinovatech.unicom.separatemodule.notice;

import android.app.Activity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LogStatistic {
    public static void sendPushClickLog(Activity activity, String str) {
        UserManager userManager = UserManager.getInstance();
        RequestParams requestParams = new RequestParams();
        requestParams.put("push_biz_type", "7");
        requestParams.put("prov_id", userManager.getCurrentProvinceCode());
        requestParams.put("status", "1");
        requestParams.put("channel_no", "113000005");
        requestParams.put("messageId", str);
        requestParams.put("deviceId", DeviceHelper.getDeviceID(true));
        requestParams.put("client_type", activity.getString(2131886969));
        requestParams.put("mobile", userManager.getCurrentPhoneNumber());
        App.getAsyncHttpClient().post(URLSet.getPush_statistic(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.notice.LogStatistic.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str2) {
                super.onSuccess(i, str2);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str2) {
                super.onFailure(th, str2);
            }
        });
    }
}
