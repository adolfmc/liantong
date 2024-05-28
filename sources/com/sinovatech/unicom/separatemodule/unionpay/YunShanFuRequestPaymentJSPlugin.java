package com.sinovatech.unicom.separatemodule.unionpay;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.sinovatech.unicom.separatemodule.unionpay.YunShanFuManager;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/yunShanFuRequestPayment")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YunShanFuRequestPaymentJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.parameterJO != null) {
                String optString = this.parameterJO.optString("tn");
                String optString2 = this.parameterJO.optString("mode");
                App.yunshanfuPayType = 1;
                YunShanFuManager yunShanFuManager = YunShanFuManager.getYunShanFuManager();
                yunShanFuManager.setData(this.activityContext, this.f18589wv);
                yunShanFuManager.collectData(this.activityContext, getCurrentURL(), this.f18589wv.getTitle());
                yunShanFuManager.startPay(optString, optString2);
                yunShanFuManager.addPayResultLister(new YunShanFuManager.PayResultLister() { // from class: com.sinovatech.unicom.separatemodule.unionpay.YunShanFuRequestPaymentJSPlugin.1
                    @Override // com.sinovatech.unicom.separatemodule.unionpay.YunShanFuManager.PayResultLister
                    public void payResult(String str, String str2) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("code", str);
                            jSONObject.put("msg", str2);
                            YunShanFuRequestPaymentJSPlugin.this.callbackSuccess(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                        } catch (Exception e) {
                            e.printStackTrace();
                            YunShanFuRequestPaymentJSPlugin.this.callbackFail("10", e.getMessage());
                        }
                    }
                });
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", "03");
                jSONObject.put("msg", "参数异常");
                callbackSuccess(jSONObject);
            }
            this.webFragment.addLifeListener("/MsJSPlugin/yunShanFuRequestPayment", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.unionpay.YunShanFuRequestPaymentJSPlugin.2
                @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                public void onDestory() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", e.getMessage());
        }
    }
}
