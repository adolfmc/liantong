package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.sinovatech.unicom.common.UIUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GoBackPlugin extends YHXXJSPlugin {
    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) {
        try {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                appCompatActivity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast("Native接口层异常：" + e.getMessage());
        }
    }
}
