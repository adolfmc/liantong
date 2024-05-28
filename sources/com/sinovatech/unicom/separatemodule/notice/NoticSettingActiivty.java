package com.sinovatech.unicom.separatemodule.notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.sinovatech.unicom.p318ui.BaseActivity;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoticSettingActiivty extends BaseActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private ImageView backImage;
    private TextView settingTipText;
    boolean systemSwitch;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 101);
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

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.systemSwitch = SoulPermission.getInstance().checkSpecialPermission(Special.NOTIFICATION);
        if (this.systemSwitch) {
            this.settingTipText.setText("已开启");
        } else {
            this.settingTipText.setText("已关闭-前往设置");
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id != 2131298136) {
            if (id == 2131298138) {
                finish();
            }
        } else if (!this.systemSwitch) {
            SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() { // from class: com.sinovatech.unicom.separatemodule.notice.NoticSettingActiivty.1
                @Override // com.p284qw.soul.permission.callbcak.GoAppDetailCallBack
                public void onBackFromAppDetail(Intent intent) {
                }
            });
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
