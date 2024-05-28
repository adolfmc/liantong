package com.sdk.mobile.manager.login.cucc;

import android.content.Context;
import android.util.Log;
import com.sdk.base.api.CallBack;
import com.sdk.base.framework.bean.OauthResultMode;
import com.sdk.base.framework.utils.log.LiveDataBus3;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.p290f.C6998d;
import com.sdk.p293i.C7007a;
import com.sdk.p294j.C7008a;
import com.sdk.p298n.C7014a;
import com.sdk.p308x.C7048a;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UiOauthManager extends SDKManager {
    private static Boolean isDebug = Boolean.valueOf(C6998d.f18135a);
    public static int lognetType;
    private static volatile UiOauthManager manager;
    public static int timeOut1;
    private String TAG = UiOauthManager.class.getSimpleName();
    private boolean cancel;
    public C7048a mHandler;
    private OauthResultMode resultMode;

    private UiOauthManager() {
    }

    private <T> void dispatchHandler(int i, final CallBack<T> callBack) {
        Log.e("ZJW_LOG", "dispatchHandler");
        new C7048a(SDKManager.mContext, i, new CallBack<T>() { // from class: com.sdk.mobile.manager.login.cucc.UiOauthManager.1
            @Override // com.sdk.base.api.CallBack
            public void onFailed(int i2, int i3, String str, String str2) {
                Log.e("ZJW_LOG", "onFailed code--->" + i2);
                Log.e("ZJW_LOG", "onFailed status--->" + i3);
                callBack.onFailed(i2, i3, str, str2);
            }

            @Override // com.sdk.base.api.CallBack
            public void onSuccess(int i2, String str, int i3, T t, String str2) {
                Log.e("ZJW_LOG", "onSuccess code--->" + i2);
                Log.e("ZJW_LOG", "onSuccess msg--->" + str);
                Log.e("ZJW_LOG", "onSuccess status--->" + i3);
                Log.e("ZJW_LOG", "onSuccess response--->" + t);
                if (i2 == 0) {
                    callBack.onSuccess(i2, str, i3, t, str2);
                }
                if (i2 == 1) {
                    callBack.onFailed(i2, i3, str, str2);
                }
            }
        }).m8113a(0);
    }

    public static UiOauthManager getInstance(Context context) {
        if (manager == null) {
            synchronized (UiOauthManager.class) {
                if (manager == null) {
                    manager = new UiOauthManager();
                }
            }
        }
        return manager;
    }

    public void cancel() {
        this.cancel = true;
    }

    public C7048a getmHandler() {
        return this.mHandler;
    }

    public <T> void login(int i, CallBack<T> callBack) {
        int i2;
        String str;
        String str2;
        LiveDataBus3.init();
        LogUtils.d_yl(this.TAG, "Demo 点击触发 开始", 0);
        try {
        } catch (Exception e) {
            LogUtils.m8186e(this.TAG, e.getMessage(), isDebug);
        }
        if (SDKManager.getInitFlag()) {
            callBack.onFailed(0, 101004, "ApiKey或PublicKey不能为空", "seqAndroidEmpty");
            return;
        }
        C7008a.m8154b(SDKManager.mContext, "qcandroid", "qcandroidabc000");
        C7008a.m8154b(SDKManager.getContext(), "seq", null);
        int m8163a = C7007a.m8163a();
        int mo8144a = C7014a.m8145b(SDKManager.mContext).mo8144a();
        lognetType = mo8144a;
        if (m8163a >= 23 || mo8144a != 2) {
            String str3 = this.TAG;
            LogUtils.d_yl(str3, "-1:没网; 0:wifi; 1:流量; 2:双开; 网络状态:" + lognetType, 0);
            if (lognetType != 2 && lognetType != 1) {
                if (lognetType == 0) {
                    i2 = 102003;
                    str = "未开启流量";
                    str2 = "";
                } else {
                    if (lognetType == -1) {
                        i2 = 102002;
                        str = "无网络连接";
                        str2 = "";
                    }
                    timeOut1 = i;
                }
                callBack.onFailed(1, i2, str, str2);
                timeOut1 = i;
            }
            C7048a c7048a = new C7048a(SDKManager.mContext, i, callBack);
            this.mHandler = c7048a;
            c7048a.m8113a(0);
            timeOut1 = i;
        } else {
            callBack.onFailed(1, 102001, "选择流量通道失败", "qcandroidabc000");
        }
        String str4 = this.TAG;
        LogUtils.d_yl(str4, "public <T> void login:结束" + lognetType, 0);
    }

    public void setmHandler(C7048a c7048a) {
        this.mHandler = c7048a;
    }
}
