package com.tydic.softphone.tydic_tracker.app;

import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TYHTTP {
    private String prodDomain = "https://analytics2.10010.com/csc";
    private String testDomain = "http://157.148.81.20:45591/csc";
    private int testWebSite = 3;
    private int testActivity_id = 34;
    private int prodWebSite = 1;
    private int prodActivity_id = 156;

    public void sendRequest(JSONObject jSONObject) throws JSONException {
        String str;
        int i;
        int i2;
        if (TYApplication.env == "test") {
            str = this.testDomain;
            i = this.testWebSite;
            i2 = this.testActivity_id;
        } else {
            str = this.prodDomain;
            i = this.prodWebSite;
            i2 = this.prodActivity_id;
        }
        OkHttpClient init = NBSOkHttp3Instrumentation.init();
        FormBody formBody = null;
        if (jSONObject.getInt("type") == 1) {
            formBody = new FormBody.Builder().add("idsite", String.valueOf(i)).add("dimension1", String.valueOf(i2)).add("dimension3", "浏览页面").add("dimension22", "1").add("dimension2", "7").add("cid", jSONObject.getString("cid")).add("dimension35", jSONObject.getString("pageCode")).add("dimension62", jSONObject.getString("phoneNumber")).add("dimension63", jSONObject.getString("fouseCall")).add("dimension64", jSONObject.getString("callMode")).add("dimension10", jSONObject.getString("pageName")).build();
        } else if (jSONObject.getInt("type") == 2) {
            Log.i("tydic111", "点击按钮");
            formBody = new FormBody.Builder().add("idsite", String.valueOf(i)).add("dimension1", String.valueOf(i2)).add("dimension3", "点击按钮").add("dimension22", "2").add("dimension2", "9").add("cid", jSONObject.getString("cid")).add("dimension10", "通话页面").add("dimension62", jSONObject.getString("phoneNumber")).add("dimension63", jSONObject.getString("fouseCall")).add("dimension64", jSONObject.getString("callMode")).add("dimension20", jSONObject.getString("pbName")).add("dimension65", jSONObject.getString("reportErrors")).add("dimension38", jSONObject.getString("triggerCodeBtn")).build();
        }
        Log.i("tydic111", "点击按钮1");
        Request build = new Request.Builder().url(str).post(formBody).build();
        Log.i("tyLog", "开始发送请求");
        Log.i("tydic111", "点击按钮2");
        init.newCall(build).enqueue(new Callback() { // from class: com.tydic.softphone.tydic_tracker.app.TYHTTP.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                String message = iOException.getMessage();
                Log.i("tydic111", "点击按钮4");
                Log.i("tyLog", message);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("tyLog", response.body().string());
                Log.i("tydic111", "点击按钮3");
                TYApplication.clearTable();
            }
        });
    }
}
