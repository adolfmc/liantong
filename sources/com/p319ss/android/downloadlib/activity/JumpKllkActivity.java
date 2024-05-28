package com.p319ss.android.downloadlib.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.utils.C10053ko;
import com.p319ss.android.socialbase.appdownloader.C10085b;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.activity.JumpKllkActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JumpKllkActivity extends TTDelegateActivity {
    public NBSTraceUnit _nbs_trace;

    @Override // com.p319ss.android.downloadlib.activity.TTDelegateActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 396);
    }

    @Override // com.p319ss.android.downloadlib.activity.TTDelegateActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.p319ss.android.downloadlib.activity.TTDelegateActivity, android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.p319ss.android.downloadlib.activity.TTDelegateActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.p319ss.android.downloadlib.activity.TTDelegateActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.p319ss.android.downloadlib.activity.TTDelegateActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // com.p319ss.android.downloadlib.activity.TTDelegateActivity
    /* renamed from: mb */
    protected void mo7724mb() {
        if (getIntent() == null) {
            C9971b.m7285mb().m7284mb("handleIntent is null");
            C10085b.m6920mb((Activity) this);
        }
        String stringExtra = getIntent().getStringExtra("p");
        long longExtra = getIntent().getLongExtra("id", 0L);
        if (TextUtils.isEmpty(stringExtra) || longExtra == 0) {
            C9971b.m7285mb().m7284mb("getPackage or id is null");
            C10085b.m6920mb((Activity) this);
        }
        int optInt = C9940x.m7364lz().optInt("ab", 0);
        C10053ko.m7014mb(this, stringExtra, longExtra, optInt == 1);
        if (optInt != 1) {
            C10085b.m6920mb((Activity) this);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        C10085b.m6920mb((Activity) this);
    }
}
