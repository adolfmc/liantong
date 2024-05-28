package com.sinovatech.unicom.basic.p315ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerOneKeyLogin;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.BaseActivity;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.OneKeyLoginActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OneKeyLoginActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private String accessCode;
    private ImageButton backButton;
    private AppCompatActivity context;
    private String fakeMobile;
    private Button loginButton;
    private TextView numberText;

    /* renamed from: pd */
    private CustomePorgressDialog f18405pd;
    private SharePreferenceUtil preference;
    private TextView titleView;
    private UserManager userManager;
    private CheckBox yinsiCheckBox;
    private TextView yinsiText;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 58);
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
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.OneKeyLoginActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC74681 implements View.OnClickListener {
        View$OnClickListenerC74681() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            OneKeyLoginActivity.this.context.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.OneKeyLoginActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC74692 implements View.OnClickListener {
        View$OnClickListenerC74692() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            OneKeyLoginActivity.this.gotoYinsi();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.activity.OneKeyLoginActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnTouchListenerC74703 implements View.OnTouchListener {
        View$OnTouchListenerC74703() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 3 || motionEvent.getAction() == 1) {
                OneKeyLoginActivity.this.gotoYinsi();
            }
            return true;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.OneKeyLoginActivity$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C74714 implements CompoundButton.OnCheckedChangeListener {
        C74714() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Tracker.onCheckedChanged(compoundButton, z);
            if (z) {
                OneKeyLoginActivity.this.loginButton.setEnabled(true);
                OneKeyLoginActivity.this.loginButton.setBackgroundResource(2131231879);
                return;
            }
            OneKeyLoginActivity.this.loginButton.setEnabled(false);
            OneKeyLoginActivity.this.loginButton.setBackgroundResource(2131231880);
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.OneKeyLoginActivity$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC74725 implements View.OnClickListener {
        View$OnClickListenerC74725() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            OneKeyLoginActivity oneKeyLoginActivity = OneKeyLoginActivity.this;
            ManagerOneKeyLogin.mianmiSDKLogin(oneKeyLoginActivity, oneKeyLoginActivity.accessCode, OneKeyLoginActivity.this.f18405pd);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoYinsi() {
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(URLSet.getOneKeyLoginYinSiUrl());
        webParamsEntity.setTitle("一键登录");
        webParamsEntity.setBackMode("0");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        Intent intent = new Intent(this.context, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        new AvoidOnResult(this.context).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.activity.OneKeyLoginActivity.6
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent2) {
                if (i == 10) {
                    OneKeyLoginActivity.this.yinsiCheckBox.setChecked(false);
                } else if (i == 11) {
                    OneKeyLoginActivity.this.yinsiCheckBox.setChecked(true);
                } else {
                    OneKeyLoginActivity.this.yinsiCheckBox.setChecked(false);
                }
            }
        });
    }
}
