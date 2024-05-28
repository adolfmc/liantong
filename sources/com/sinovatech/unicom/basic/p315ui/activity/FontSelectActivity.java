package com.sinovatech.unicom.basic.p315ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.fragment.UnicomHomeFragment;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.user.UserFragment;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.FontSelectActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FontSelectActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private ImageButton backButton;
    private String font_now = "";
    private TextView mTv_BigFont;
    private TextView mTv_StandardFont;
    private TextView titleView;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 51);
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

    private void initUI() {
        this.backButton = (ImageButton) findViewById(2131296473);
        this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.FontSelectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                FontSelectActivity.this.onBackPressed();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.titleView = (TextView) findViewById(2131298800);
        this.titleView.setText("设置字体大小");
        this.mTv_StandardFont = (TextView) findViewById(2131297022);
        this.mTv_StandardFont.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.FontSelectActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                App.getSharePreferenceUtil().putString(ConfigManager.config_font_Size, "config_font_standard");
                FontSelectActivity fontSelectActivity = FontSelectActivity.this;
                fontSelectActivity.setDrawableRight(fontSelectActivity.mTv_StandardFont);
                UnicomHomeFragment.currentPhone = "";
                UserFragment.currentPhone = "";
                WebFragment.isReloginShop = true;
                WebFragment.isRelogin = true;
                WebFragment.isReloginHuodong = true;
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mTv_BigFont = (TextView) findViewById(2131297018);
        this.mTv_BigFont.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.FontSelectActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                App.getSharePreferenceUtil().putString(ConfigManager.config_font_Size, "config_font_big");
                FontSelectActivity fontSelectActivity = FontSelectActivity.this;
                fontSelectActivity.setDrawableRight(fontSelectActivity.mTv_BigFont);
                UnicomHomeFragment.currentPhone = "";
                UserFragment.currentPhone = "";
                WebFragment.isReloginShop = true;
                WebFragment.isRelogin = true;
                WebFragment.isReloginHuodong = true;
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        String string = App.getSharePreferenceUtil().getString(ConfigManager.config_font_Size);
        this.font_now = string;
        setDrawableRight(TextUtils.equals("config_font_big", string) ? this.mTv_BigFont : this.mTv_StandardFont);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        String string = App.getSharePreferenceUtil().getString(ConfigManager.config_font_Size);
        if (TextUtils.equals(this.font_now, string)) {
            return;
        }
        StatisticsUploadUtils.upload("7", "设置-大字版", "按钮", "0", TextUtils.equals("config_font_big", string) ? "大号字" : "标准字", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDrawableRight(TextView textView) {
        this.mTv_BigFont.setCompoundDrawables(null, null, null, null);
        this.mTv_StandardFont.setCompoundDrawables(null, null, null, null);
        Drawable drawable = getResources().getDrawable(2131231958);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }
}
