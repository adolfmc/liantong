package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.widget.AbstractC2065g;
import com.alipay.sdk.widget.C2066h;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class H5PayActivity extends Activity {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    private AbstractC2065g f3532a;

    /* renamed from: b */
    private String f3533b;

    /* renamed from: c */
    private String f3534c;

    /* renamed from: d */
    private String f3535d;

    /* renamed from: e */
    private String f3536e;

    /* renamed from: f */
    private boolean f3537f;

    /* renamed from: g */
    private String f3538g;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 1);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* renamed from: b */
    private void m20949b() {
        try {
            super.requestWindowFeature(1);
        } catch (Throwable th) {
            C2040c.m20715a(th);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        AbstractC2065g abstractC2065g = this.f3532a;
        if (abstractC2065g instanceof C2066h) {
            abstractC2065g.mo20593b();
            return;
        }
        if (!abstractC2065g.mo20593b()) {
            super.onBackPressed();
        }
        C1998j.m20913a(C1998j.m20910c());
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        mo20950a();
        super.finish();
    }

    /* renamed from: a */
    public void mo20950a() {
        Object obj = PayTask.f3550a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AbstractC2065g abstractC2065g = this.f3532a;
        if (abstractC2065g != null) {
            abstractC2065g.mo20605a();
        }
    }
}
