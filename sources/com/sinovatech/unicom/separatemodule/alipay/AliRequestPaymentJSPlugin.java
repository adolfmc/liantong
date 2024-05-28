package com.sinovatech.unicom.separatemodule.alipay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.PayTask;
import com.sinovatech.unicom.basic.p314po.PayResult;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/aliRequestPayment")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AliRequestPaymentJSPlugin extends BaseJSPlugin {
    private static String TAG = "AliRequestPaymentJSPlugin";
    private final int JS_PAY_FLAG = 200;
    private Handler mHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.alipay.AliRequestPaymentJSPlugin.1
        @Override // android.os.Handler
        @SuppressLint({"HandlerLeak"})
        public void handleMessage(Message message) {
            if (message.what != 200) {
                return;
            }
            try {
                PayResult payResult = new PayResult((String) message.obj);
                String result = payResult.getResult();
                String memo = payResult.getMemo();
                String resultStatus = payResult.getResultStatus();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("resultStatus", resultStatus);
                jSONObject.put("result", result);
                jSONObject.put("memo", memo);
                AliRequestPaymentJSPlugin.this.callbackSuccess(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                AliRequestPaymentJSPlugin.this.callbackFail("10", e.getMessage());
                MsLogUtil.m7979d(AliRequestPaymentJSPlugin.TAG, e.getMessage());
            }
        }
    };

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            final String optString = this.parameterJO.optJSONObject("params").optString("orderStr");
            if (optString != null && !optString.isEmpty()) {
                new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.alipay.AliRequestPaymentJSPlugin.2
                    @Override // java.lang.Runnable
                    public void run() {
                        String pay = new PayTask(AliRequestPaymentJSPlugin.this.activityContext).pay(optString, true);
                        Message message = new Message();
                        message.what = 200;
                        message.obj = pay;
                        AliRequestPaymentJSPlugin.this.mHandler.sendMessage(message);
                    }
                }).start();
                return;
            }
            callbackFail("11", "支付参数为空");
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", e.getMessage());
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }
}
