package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GestureBackPlugin extends YHXXJSPlugin {
    private JSONObject originConfigJO;

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) throws JSONException {
        this.originConfigJO = new JSONObject(str);
        WebDetailActivity.gaodedache = this.originConfigJO.getJSONObject("parameter").optBoolean("val");
        WebDetailActivity.isgaodedache = true;
    }
}
