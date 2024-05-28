package com.tydic.tydic_tracker.app;

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

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TYHTTP {
    private String prodDomain = "https://analytics2.10010.com/csc";
    private String testDomain = "http://157.148.81.20:8080/csc";
    private int testWebSite = 1;
    private int testActivity_id = 105;
    private int prodWebSite = 1;
    private int prodActivity_id = 105;

    public void sendRequest(String str) {
        String str2;
        int i;
        int i2;
        if (TYApplication.env == "test") {
            str2 = this.testDomain;
            i = this.testWebSite;
            i2 = this.testActivity_id;
        } else {
            str2 = this.prodDomain;
            i = this.prodWebSite;
            i2 = this.prodActivity_id;
        }
        OkHttpClient init = NBSOkHttp3Instrumentation.init();
        Request build = new Request.Builder().url(str2).post(new FormBody.Builder().add("idsite", String.valueOf(i)).add("uid", "13070153758").add("dimension1", String.valueOf(i2)).add("info", str).build()).build();
        Log.i("tyLog", "开始发送请求");
        init.newCall(build).enqueue(new Callback() { // from class: com.tydic.tydic_tracker.app.TYHTTP.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.i("tyLog", iOException.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("tyLog", response.body().string());
                TYApplication.clearTable();
            }
        });
    }
}
