package com.sinovatech.unicom.hub.media.gallery;

import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.navigation.Navigation;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.hub.C8126R;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MediaStoreActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(C8126R.C8131layout.hub_media_store);
        int intExtra = getIntent().getIntExtra("count", 9);
        String stringExtra = getIntent().getStringExtra("mediaStoreType");
        int intExtra2 = getIntent().getIntExtra("compressSize", 300);
        String stringExtra2 = getIntent().getStringExtra("compress");
        String stringExtra3 = getIntent().getStringExtra("crop");
        if (intExtra <= 0) {
            intExtra = 9;
        } else if (intExtra > 20) {
            intExtra = 20;
        }
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "image";
        } else if (!"image".equals(stringExtra) && !"video".equals(stringExtra) && !"mix".equals(stringExtra)) {
            stringExtra = "image";
        }
        int i = 500;
        if (intExtra2 < 100) {
            i = 100;
        } else if (intExtra2 <= 500) {
            i = intExtra2;
        }
        if (TextUtils.isEmpty(stringExtra2)) {
            stringExtra2 = "yes";
        } else if (!"yes".equals(stringExtra2) && !"no".equals(stringExtra2)) {
            stringExtra2 = "yes";
        }
        if (TextUtils.isEmpty(stringExtra3)) {
            stringExtra3 = "yes";
        } else if (!"yes".equals(stringExtra3) && !"no".equals(stringExtra3)) {
            stringExtra3 = "yes";
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("count", intExtra);
        bundle2.putString("mediaStoreType", stringExtra);
        bundle2.putString("crop", stringExtra3);
        bundle2.putString("compress", stringExtra2);
        bundle2.putInt("compressSize", i);
        Navigation.findNavController(this, C8126R.C8129id.navHost).setGraph(C8126R.C8133navigation.hub_media_gallery_navigation, bundle2);
        NBSAppInstrumentation.activityCreateEndIns();
    }

    public void completeChoose(String str) {
        setResult(-1, getIntent().putExtra("result", str));
        finish();
    }
}
