package com.sdk.mobile.manager.oauth.cucc;

import android.content.Context;
import com.sdk.base.api.CallBack;
import com.sdk.base.framework.utils.log.LiveDataBus3;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.mobile.manager.login.cucc.UiOauthManager;
import com.sdk.p293i.C7007a;
import com.sdk.p294j.C7008a;
import com.sdk.p298n.C7014a;
import com.sdk.p308x.C7048a;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OauthManager extends SDKManager {
    private static volatile OauthManager manager;
    public C7048a mHandler;

    private OauthManager() {
    }

    public static OauthManager getInstance(Context context) {
        if (manager == null) {
            synchronized (OauthManager.class) {
                if (manager == null) {
                    manager = new OauthManager();
                }
            }
        }
        return manager;
    }

    public <T> void getAuthoriseCode(int i, CallBack<T> callBack) {
        int i2;
        String str;
        LiveDataBus3.init();
        LogUtils.d_yl("TAG", "Demo 点击触发 开始", 0);
        try {
            if (SDKManager.getInitFlag()) {
                callBack.onFailed(0, 101004, "ApiKey或PublicKey不能为空", "seqAndroidEmpty");
                return;
            }
            C7008a.m8154b(SDKManager.mContext, "qcandroid", "qcandroidabc000");
            C7008a.m8154b(SDKManager.getContext(), "seq", null);
            int m8163a = C7007a.m8163a();
            int mo8144a = C7014a.m8145b(SDKManager.mContext).mo8144a();
            UiOauthManager.lognetType = mo8144a;
            if (m8163a < 23 && mo8144a == 2) {
                callBack.onFailed(1, 102001, "选择流量通道失败", "qcandroidabc000");
                return;
            }
            LogUtils.d_yl("TAG", "-1  没网  ; 0  wifi  ; 1  流量  ;  2 wifi + 流量 网络状态:" + mo8144a, 0);
            if (mo8144a != 2 && mo8144a != 1) {
                if (mo8144a == 0) {
                    i2 = 102003;
                    str = "未开启流量";
                } else if (mo8144a != -1) {
                    return;
                } else {
                    i2 = 102002;
                    str = "无网络连接";
                }
                callBack.onFailed(1, i2, str, "");
                return;
            }
            C7048a c7048a = new C7048a(SDKManager.mContext, i, callBack);
            this.mHandler = c7048a;
            c7048a.m8113a(1);
        } catch (Exception unused) {
        }
    }

    public <T> void getMobileForCode(String str, int i, CallBack<T> callBack) {
    }

    public <T> void getMobileForCode(String str, String str2, int i, CallBack<T> callBack) {
    }

    public C7048a getmHandler() {
        return this.mHandler;
    }

    public void setmHandler(C7048a c7048a) {
        this.mHandler = c7048a;
    }
}
