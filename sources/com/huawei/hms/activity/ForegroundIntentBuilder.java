package com.huawei.hms.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.activity.internal.BusResponseCallback;
import com.huawei.hms.activity.internal.ForegroundBusResponseMgr;
import com.huawei.hms.activity.internal.ForegroundInnerHeader;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.utils.Util;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ForegroundIntentBuilder {

    /* renamed from: a */
    private Activity f10901a;

    /* renamed from: b */
    private RequestHeader f10902b;

    /* renamed from: c */
    private String f10903c;

    /* renamed from: d */
    private ForegroundInnerHeader f10904d;

    /* renamed from: e */
    private String f10905e;

    /* renamed from: f */
    private Context f10906f;

    public ForegroundIntentBuilder(Activity activity) throws IllegalArgumentException {
        if (activity != null) {
            this.f10901a = activity;
            RequestHeader requestHeader = new RequestHeader();
            this.f10902b = requestHeader;
            requestHeader.setSdkVersion(60900300);
            this.f10903c = "";
            ForegroundInnerHeader foregroundInnerHeader = new ForegroundInnerHeader();
            this.f10904d = foregroundInnerHeader;
            foregroundInnerHeader.setApkVersion(30000000);
            return;
        }
        throw new IllegalArgumentException("listener must not be null.");
    }

    public static void registerResponseCallback(String str, BusResponseCallback busResponseCallback) {
        ForegroundBusResponseMgr.getInstance().registerObserver(str, busResponseCallback);
    }

    public static void unregisterResponseCallback(String str) {
        ForegroundBusResponseMgr.getInstance().unRegisterObserver(str);
    }

    public Intent build() {
        String packageName;
        String appId;
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(this.f10901a, ForegroundBusDelegate.class.getName());
        Context context = this.f10906f;
        if (context != null) {
            packageName = context.getPackageName();
            appId = Util.getAppId(this.f10906f);
        } else {
            packageName = this.f10901a.getPackageName();
            appId = Util.getAppId(this.f10901a);
        }
        if (this.f10902b.getAppID() == null) {
            RequestHeader requestHeader = this.f10902b;
            requestHeader.setAppID(appId + "|");
        } else {
            RequestHeader requestHeader2 = this.f10902b;
            requestHeader2.setAppID(appId + "|" + this.f10902b.getAppID());
        }
        if (TextUtils.isEmpty(this.f10902b.getTransactionId())) {
            RequestHeader requestHeader3 = this.f10902b;
            requestHeader3.setTransactionId(TransactionIdCreater.getId(requestHeader3.getAppID(), "hub.request"));
        }
        this.f10902b.setPkgName(packageName);
        intentStartBridgeActivity.putExtra(ForegroundBusDelegate.HMS_FOREGROUND_REQ_HEADER, this.f10902b.toJson());
        intentStartBridgeActivity.putExtra(ForegroundBusDelegate.HMS_FOREGROUND_REQ_BODY, this.f10903c);
        intentStartBridgeActivity.putExtra(ForegroundBusDelegate.HMS_FOREGROUND_REQ_INNER, this.f10904d.toJson());
        if (!TextUtils.isEmpty(this.f10905e)) {
            intentStartBridgeActivity.putExtra(ForegroundBusDelegate.INNER_PKG_NAME, this.f10905e);
        }
        return intentStartBridgeActivity;
    }

    public ForegroundIntentBuilder setAction(String str) {
        this.f10902b.setApiName(str);
        return this;
    }

    public ForegroundIntentBuilder setApiLevel(int i) {
        this.f10902b.setApiLevel(i);
        return this;
    }

    public ForegroundIntentBuilder setApplicationContext(Context context) {
        this.f10906f = context;
        return this;
    }

    public ForegroundIntentBuilder setInnerHms() {
        this.f10905e = this.f10901a.getPackageName();
        return this;
    }

    public ForegroundIntentBuilder setKitSdkVersion(int i) {
        this.f10902b.setKitSdkVersion(i);
        return this;
    }

    public ForegroundIntentBuilder setMinApkVersion(int i) {
        this.f10904d.setApkVersion(i);
        return this;
    }

    public ForegroundIntentBuilder setRequestBody(String str) {
        this.f10903c = str;
        return this;
    }

    public ForegroundIntentBuilder setResponseCallback(String str, BusResponseCallback busResponseCallback) {
        this.f10904d.setResponseCallbackKey(str);
        ForegroundBusResponseMgr.getInstance().registerObserver(str, busResponseCallback);
        return this;
    }

    public ForegroundIntentBuilder setServiceName(String str) {
        this.f10902b.setSrvName(str);
        return this;
    }

    public ForegroundIntentBuilder setSubAppId(String str) {
        this.f10902b.setAppID(str);
        return this;
    }

    public ForegroundIntentBuilder setTransactionId(String str) {
        this.f10902b.setTransactionId(str);
        return this;
    }

    public ForegroundIntentBuilder setResponseCallback(String str) {
        this.f10904d.setResponseCallbackKey(str);
        return this;
    }
}
