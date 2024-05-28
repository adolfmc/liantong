package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentTransaction;
import android.support.p086v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class KeyboardActivity extends AppCompatActivity implements KeyboardFragment.OnSendMsgListener {
    private String TAG = "KeyboardActivity";
    public NBSTraceUnit _nbs_trace;
    private KeyboardFragment keyboardFragment;
    private RelativeLayout rlRootView;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 99);
    }

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

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC91731 implements View.OnClickListener {
        View$OnClickListenerC91731() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            KeyboardActivity.this.hideKeyBoard();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(this.TAG, "onNewIntent");
        changeData(intent);
    }

    public void initEmotionMainFragment(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("msg", str);
        this.keyboardFragment = (KeyboardFragment) KeyboardFragment.newInstance(KeyboardFragment.class, bundle);
        this.keyboardFragment.setSendMsgListener(this);
        this.keyboardFragment.bindToContentView(this.rlRootView);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(2131296990, this.keyboardFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commitAllowingStateLoss();
    }

    public void showKeyBoard(final String str) {
        KeyboardFragment keyboardFragment = this.keyboardFragment;
        if (keyboardFragment == null) {
            initEmotionMainFragment(str);
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    KeyboardActivity.this.keyboardFragment.showSoftIntut(str);
                }
            }, 300L);
            return;
        }
        keyboardFragment.showSoftIntut(str);
    }

    public void hideKeyBoard() {
        KeyboardFragment keyboardFragment = this.keyboardFragment;
        if (keyboardFragment != null) {
            keyboardFragment.hideSoftIntut();
        }
        finish();
    }

    private void changeData(Intent intent) {
        String stringExtra = intent.getStringExtra("action");
        String stringExtra2 = intent.getStringExtra("msg");
        if ("open".equals(stringExtra)) {
            showKeyBoard(stringExtra2);
        } else {
            hideKeyBoard();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyBoard();
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment.OnSendMsgListener
    public void sendText(String str) {
        this.keyboardFragment.clearContent();
        this.keyboardFragment.hideSoftIntut();
        Intent intent = new Intent();
        intent.putExtra("text", str);
        setResult(-1, intent);
        finish();
    }
}
