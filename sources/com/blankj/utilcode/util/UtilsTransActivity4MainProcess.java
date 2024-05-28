package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.blankj.utilcode.util.Utils;
import com.blankj.utilcode.util.UtilsTransActivity;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UtilsTransActivity4MainProcess extends UtilsTransActivity {
    public NBSTraceUnit _nbs_trace;

    @Override // com.blankj.utilcode.util.UtilsTransActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // com.blankj.utilcode.util.UtilsTransActivity, android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.blankj.utilcode.util.UtilsTransActivity, android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.blankj.utilcode.util.UtilsTransActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.blankj.utilcode.util.UtilsTransActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.blankj.utilcode.util.UtilsTransActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    public static void start(UtilsTransActivity.TransActivityDelegate transActivityDelegate) {
        start(null, null, transActivityDelegate, UtilsTransActivity4MainProcess.class);
    }

    public static void start(Utils.Consumer<Intent> consumer, UtilsTransActivity.TransActivityDelegate transActivityDelegate) {
        start(null, consumer, transActivityDelegate, UtilsTransActivity4MainProcess.class);
    }

    public static void start(Activity activity, UtilsTransActivity.TransActivityDelegate transActivityDelegate) {
        start(activity, null, transActivityDelegate, UtilsTransActivity4MainProcess.class);
    }

    public static void start(Activity activity, Utils.Consumer<Intent> consumer, UtilsTransActivity.TransActivityDelegate transActivityDelegate) {
        start(activity, consumer, transActivityDelegate, UtilsTransActivity4MainProcess.class);
    }
}
