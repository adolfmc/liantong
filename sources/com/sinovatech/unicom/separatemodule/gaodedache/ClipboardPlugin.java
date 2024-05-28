package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ClipboardPlugin extends YHXXJSPlugin {
    private JSONObject originConfigJO;

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) throws JSONException {
        this.originConfigJO = new JSONObject(str);
        ((ClipboardManager) appCompatActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Label", this.originConfigJO.getJSONObject("parameter").optString("text")));
    }
}
