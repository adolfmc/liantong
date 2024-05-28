package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class YHXXJSPlugin {
    public static String STATUS_FAIL = "fail";
    public static String STATUS_SUCCESS = "success";

    public abstract void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) throws JSONException;

    public abstract void onActivityResult(int i, int i2, Intent intent);

    public void callBack(WebView webView, JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put("parameter", obj);
            StringBuilder sb = new StringBuilder();
            sb.append("javascript:JSBridge.execCallBackAction('");
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            sb.append("')");
            String sb2 = sb.toString();
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, sb2);
            } else {
                webView.loadUrl(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postEventToJS(WebView webView, String str, Object obj) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", str);
            jSONObject.put("parameter", obj);
            StringBuilder sb = new StringBuilder();
            sb.append("javascript:JSBridge.eventListenerAction('");
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            sb.append("')");
            String sb2 = sb.toString();
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, sb2);
            } else {
                webView.loadUrl(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
