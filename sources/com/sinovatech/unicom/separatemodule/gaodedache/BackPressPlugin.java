package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import org.json.JSONException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BackPressPlugin extends YHXXJSPlugin {
    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) throws JSONException {
        ((ImageView) appCompatActivity.findViewById(2131299552)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gaodedache.BackPressPlugin.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }
}
