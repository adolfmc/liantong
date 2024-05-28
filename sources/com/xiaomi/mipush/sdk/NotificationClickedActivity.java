package com.xiaomi.mipush.sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class NotificationClickedActivity extends Activity {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    private BroadcastReceiver f21341a;

    /* renamed from: a */
    private Handler f21342a;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 410);
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

    /* renamed from: com.xiaomi.mipush.sdk.NotificationClickedActivity$1 */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class RunnableC110811 implements Runnable {
        RunnableC110811() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC11049b.m5266e("clicked activity finish by timeout.");
            NotificationClickedActivity.this.finish();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.mipush.sdk.NotificationClickedActivity$2 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C110822 extends BroadcastReceiver {
        C110822() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AbstractC11049b.m5274b("clicked activity finish by normal.");
            NotificationClickedActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        m5180a(getIntent());
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.f21342a.removeCallbacksAndMessages(null);
        try {
            unregisterReceiver(this.f21341a);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    private void m5180a(Intent intent) {
        try {
            if (intent != null) {
                Intent intent2 = (Intent) intent.getParcelableExtra("mipush_serviceIntent");
                if (intent2 != null) {
                    intent2.setComponent(new ComponentName(getPackageName(), "com.xiaomi.mipush.sdk.PushMessageHandler"));
                    intent2.putExtra("is_clicked_activity_call", true);
                    AbstractC11049b.m5280a("PushClickedActivity", "clicked activity start service.");
                    startService(intent2);
                } else {
                    AbstractC11049b.m5267d("PushClickedActivity", "clicked activity start service, newIntent is null");
                }
            } else {
                AbstractC11049b.m5267d("PushClickedActivity", "clicked activity start service, missing intent");
            }
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
        }
    }
}
