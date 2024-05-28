package com.bytedance.sdk.openadsdk.stub.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import com.bytedance.pangle.activity.GenerateProxyActivity;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Stub_Standard_Landscape_Activity extends GenerateProxyActivity {
    public NBSTraceUnit _nbs_trace;

    @Override // com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
    public String getPluginPkgName() {
        return "com.byted.pangle";
    }

    @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 12);
    }

    @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }
}
