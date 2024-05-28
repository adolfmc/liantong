package com.sinovatech.unicom.separatemodule.gaodedache;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.alipay.sdk.app.PayTask;
import com.sinovatech.unicom.basic.p314po.PayResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TradePay extends YHXXJSPlugin {
    private Activity activityContext;
    private Handler mHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.gaodedache.TradePay.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            try {
                PayResult payResult = new PayResult((String) message.obj);
                String resultStatus = payResult.getResultStatus();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("resultCode", resultStatus);
                    jSONObject.put("message", payResult.getMemo());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TradePay.this.callBack(TradePay.this.f18538wv, TradePay.this.originConfigJO, YHXXJSPlugin.STATUS_SUCCESS, jSONObject);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };
    private JSONObject originConfigJO;

    /* renamed from: wv */
    private WebView f18538wv;

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) throws JSONException {
        this.f18538wv = webView;
        this.activityContext = appCompatActivity;
        this.originConfigJO = new JSONObject(str);
        pay(this.originConfigJO.getJSONObject("parameter").optString("orderStr"));
    }

    public void pay(final String str) {
        new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.gaodedache.TradePay.2
            @Override // java.lang.Runnable
            public void run() {
                TradePay.this.mHandler.sendMessage(TradePay.this.mHandler.obtainMessage(1, new PayTask(TradePay.this.activityContext).pay(str, true)));
            }
        }).start();
    }
}
