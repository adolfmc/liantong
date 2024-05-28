package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.sinovatech.unicom.basic.server.IntentManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GoNewPagePlugin extends YHXXJSPlugin {
    private JSONObject originConfigJO;
    private String title;
    private String url;

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) throws JSONException {
        this.originConfigJO = new JSONObject(str);
        this.url = this.originConfigJO.getJSONObject("parameter").getString("url");
        this.title = this.originConfigJO.getJSONObject("parameter").getString("title");
        IntentManager.generateIntentAndGo(appCompatActivity, this.url, this.title, false, "get");
    }
}
