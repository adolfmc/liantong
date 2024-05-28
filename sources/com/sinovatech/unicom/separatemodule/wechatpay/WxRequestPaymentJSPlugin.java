package com.sinovatech.unicom.separatemodule.wechatpay;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/wxRequestPayment")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WxRequestPaymentJSPlugin extends BaseJSPlugin {
    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
            String optString = optJSONObject.optString("partnerId");
            String optString2 = optJSONObject.optString("prepayId");
            String optString3 = optJSONObject.optString("nonceStr");
            String optString4 = optJSONObject.optString(MenuDataCenter.timestamp);
            WechatPayManeger.wxPay(this.activityContext, optString, optString2, optJSONObject.optString("package"), optString3, optString4, optJSONObject.optString("sign"));
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", e.getMessage());
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setWxCallBack(WxPayMessage wxPayMessage) {
        try {
            MsLogUtil.m7979d("微信支付", wxPayMessage.getWechatCode() + "");
            String msg = wxPayMessage.getMsg();
            int wechatCode = wxPayMessage.getWechatCode();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", wechatCode);
            jSONObject.put("message", msg);
            callbackSuccess(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", e.getMessage());
        }
    }
}
