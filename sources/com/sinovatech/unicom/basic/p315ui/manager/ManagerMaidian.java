package com.sinovatech.unicom.basic.p315ui.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMaidian */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerMaidian {
    private AppCompatActivity activityContext;

    public ManagerMaidian(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void shagnchaunLog(String str) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("mobile", EncodeHelper.encodeByAESLog(UserManager.getInstance().getCurrentPhoneNumber()));
        requestParams.put("url_app", str);
        requestParams.put("up_type", "1");
        requestParams.put("version", this.activityContext.getString(2131886969));
        requestParams.put("deviceCode", EncodeHelper.encodeByAESLog(DeviceHelper.getDeviceID(true)));
        requestParams.put("netWay", DeviceHelper.getNETType(this.activityContext.getApplicationContext()));
        requestParams.put("deviceBrand", DeviceHelper.getDeviceBrand());
        requestParams.put("deviceModel", DeviceHelper.getDeviceModel());
        requestParams.put("deviceOS", DeviceHelper.getDeviceOSVersion());
        requestParams.put("up_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        App.getAsyncHttpClient().post(URLSet.toBurialSiteLog(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMaidian.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str2) {
                super.onSuccess(i, str2);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
            }
        });
    }
}
