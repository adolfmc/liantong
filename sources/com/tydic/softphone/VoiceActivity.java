package com.tydic.softphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class VoiceActivity extends Activity {
    public NBSTraceUnit _nbs_trace;
    ImageView iv_close;
    ImageView iv_dismiss;
    private Boolean mIsClose = false;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
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

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(C10458R.C10462layout.tydic_softphone_activity_voice);
        this.iv_close = (ImageView) findViewById(C10458R.C10461id.iv_close);
        this.iv_close.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.VoiceActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                VoiceActivity.this.mIsClose = true;
                VoiceActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        NBSAppInstrumentation.activityCreateEndIns();
    }

    public void showFloatingView() {
        if (VoiceFloatingService.isStart.booleanValue()) {
            Intent intent = new Intent();
            intent.setAction(VoiceFloatingService.ACTION_SHOW_FLOATING);
            sendBroadcast(intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setClass(this, VoiceFloatingService.class);
        startService(intent2);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.tydic.softphone.VoiceActivity$2] */
    public void startTalk() {
        new Thread() { // from class: com.tydic.softphone.VoiceActivity.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000L);
                    Log.i("tydic", "thread -->");
                    Intent intent = new Intent();
                    intent.setAction(VoiceFloatingService.ACTION_SHOW_TALK);
                    VoiceActivity.this.sendBroadcast(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tydic", "onDestroy-->");
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    public void dismissFloatingView() {
        if (VoiceFloatingService.isStart.booleanValue()) {
            Intent intent = new Intent();
            intent.setAction(VoiceFloatingService.ACTION_DISMISS_FLOATING);
            sendBroadcast(intent);
        }
    }
}
