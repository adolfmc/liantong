package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.p318ui.BaseActivity;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EnvironmentActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private ImageButton backButton;
    Button button12;
    private ToggleButton homeCumpPublishTypeButton;
    Button shengchanHuanjingBtn;
    Button shengchanHuanjingBtn1;
    Button shengchanHuanjingBtn2;
    Button shengchanHuanjingBtn3;
    Button shengchanHuanjingBtn4;
    Button shengchanHuanjingBtn5;
    Button shengchanHuanjingBtn6;
    Button shengchanHuanjingBtn8;

    /* renamed from: sp */
    SharePreferenceUtil f18401sp;
    private TextView title_textview;
    private Activity context = this;
    String key = "switch_environment";

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 48);
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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC73051 implements View.OnClickListener {
        View$OnClickListenerC73051() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.context.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C73072 implements CompoundButton.OnCheckedChangeListener {
        C73072() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Tracker.onCheckedChanged(compoundButton, z);
            EnvironmentActivity.this.f18401sp.putBoolean("HomeCumpPublishType", Boolean.valueOf(z));
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC73083 implements View.OnClickListener {
        View$OnClickListenerC73083() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.f18401sp.putString(EnvironmentActivity.this.key, URLEnvironmentConfig.ProductionEnvironment);
            EnvironmentActivity.this.title_textview.setText(EnvironmentActivity.this.shengchanHuanjingBtn.getText().toString());
            EnvironmentActivity.this.showToast();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC73094 implements View.OnClickListener {
        View$OnClickListenerC73094() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.f18401sp.putString(EnvironmentActivity.this.key, URLEnvironmentConfig.PrepubEnvironment);
            EnvironmentActivity.this.title_textview.setText(EnvironmentActivity.this.shengchanHuanjingBtn1.getText().toString());
            EnvironmentActivity.this.showToast();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    public static /* synthetic */ void lambda$onCreate$0(EnvironmentActivity environmentActivity, View view) {
        environmentActivity.f18401sp.putString(environmentActivity.key, URLEnvironmentConfig.PrepubEnvironment2);
        environmentActivity.title_textview.setText(environmentActivity.button12.getText().toString());
        environmentActivity.showToast();
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC73105 implements View.OnClickListener {
        View$OnClickListenerC73105() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.f18401sp.putString(EnvironmentActivity.this.key, URLEnvironmentConfig.Development144000);
            EnvironmentActivity.this.title_textview.setText(EnvironmentActivity.this.shengchanHuanjingBtn2.getText().toString());
            EnvironmentActivity.this.showToast();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$6 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC73116 implements View.OnClickListener {
        View$OnClickListenerC73116() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.f18401sp.putString(EnvironmentActivity.this.key, URLEnvironmentConfig.Development146000);
            EnvironmentActivity.this.title_textview.setText(EnvironmentActivity.this.shengchanHuanjingBtn3.getText().toString());
            EnvironmentActivity.this.showToast();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$7 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC73127 implements View.OnClickListener {
        View$OnClickListenerC73127() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.f18401sp.putString(EnvironmentActivity.this.key, URLEnvironmentConfig.Development114000);
            EnvironmentActivity.this.title_textview.setText(EnvironmentActivity.this.shengchanHuanjingBtn4.getText().toString());
            EnvironmentActivity.this.showToast();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$8 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC73138 implements View.OnClickListener {
        View$OnClickListenerC73138() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.f18401sp.putString(EnvironmentActivity.this.key, URLEnvironmentConfig.Development116000);
            EnvironmentActivity.this.title_textview.setText(EnvironmentActivity.this.shengchanHuanjingBtn5.getText().toString());
            EnvironmentActivity.this.showToast();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$9 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC73149 implements View.OnClickListener {
        View$OnClickListenerC73149() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.f18401sp.putString(EnvironmentActivity.this.key, URLEnvironmentConfig.PrepubEnvironment_shengfen);
            EnvironmentActivity.this.title_textview.setText(EnvironmentActivity.this.shengchanHuanjingBtn6.getText().toString());
            EnvironmentActivity.this.showToast();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.EnvironmentActivity$10 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC730610 implements View.OnClickListener {
        View$OnClickListenerC730610() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            EnvironmentActivity.this.f18401sp.putString(EnvironmentActivity.this.key, URLEnvironmentConfig.DevelopmentTest);
            EnvironmentActivity.this.title_textview.setText(EnvironmentActivity.this.shengchanHuanjingBtn8.getText().toString());
            EnvironmentActivity.this.showToast();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToast() {
        Process.killProcess(Process.myPid());
        UIUtils.toastLong("切换成功，请杀进程重新启动APP，并重新登录");
    }
}
