package com.p281qq.p282e.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p281qq.p282e.comm.p283pi.ACTD;

@NBSInstrumented
/* renamed from: com.qq.e.ads.ADActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ADActivity extends Activity {
    public static final String NOTCH_CONTAINER_TAG = "NOTCH_CONTAINER";
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    protected ACTD f17736a;

    /* renamed from: b */
    private FrameLayout f17737b;

    /* renamed from: a */
    private void m8347a() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        super.setContentView(linearLayout);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setTag(NOTCH_CONTAINER_TAG);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.addView(frameLayout);
        this.f17737b = new FrameLayout(this);
        this.f17737b.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.addView(this.f17737b);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ACTD actd = this.f17736a;
        if (actd != null) {
            actd.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        ACTD actd = this.f17736a;
        if (actd != null) {
            actd.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ACTD actd = this.f17736a;
        if (actd != null) {
            actd.onConfigurationChanged(configuration);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 24);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ACTD actd = this.f17736a;
        if (actd != null) {
            actd.onDestroy();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onPause() {
        ACTD actd = this.f17736a;
        if (actd != null) {
            actd.onPause();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        ACTD actd = this.f17736a;
        if (actd != null) {
            actd.onResume();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        ACTD actd = this.f17736a;
        if (actd != null) {
            actd.onStop();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        m8347a();
        LayoutInflater.from(this).inflate(i, (ViewGroup) this.f17737b, true);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        m8347a();
        this.f17737b.addView(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        m8347a();
        this.f17737b.addView(view, layoutParams);
    }
}
