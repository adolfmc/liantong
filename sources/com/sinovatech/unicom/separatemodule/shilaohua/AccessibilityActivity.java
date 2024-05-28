package com.sinovatech.unicom.separatemodule.shilaohua;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.sinovatech.unicom.separatemodule.user.UserFragment;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AccessibilityActivity extends BaseActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private LinearLayout colorHighLayout;
    private ImageView colorHigh_iv;
    private LinearLayout colorNormalLayout;
    private ImageView colorNormal_iv;
    private LinearLayout fastLayout;
    private ImageView fast_iv;
    private LinearLayout fontBigLayout;
    private ImageView fontBig_iv;
    private LinearLayout fontNormalLayout;
    private ImageView fontNormal_iv;
    private LinearLayout noOpenLayout;
    private ImageView noOpen_iv;
    private LinearLayout slowLayout;
    private ImageView slow_iv;
    private LinearLayout voiceNormalLayout;
    private ImageView voiceNormal_iv;
    private LinearLayout zihao_setting_li;
    private String shilaohuaVoiceKey = "shilaohua_bobao_sudu";
    private String shilaohuaVoiceIsOpenKey = "shilaohua_bobao_isOpen";
    private String shilaohuaColorSettingKey = "shilaohua_color_setting";

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 112);
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

    private void initView() {
        ((TextView) findViewById(2131298800)).setText("无障碍设置");
        findViewById(2131296473).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.shilaohua.AccessibilityActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                AccessibilityActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.noOpenLayout = (LinearLayout) findViewById(2131298119);
        this.noOpenLayout.setOnClickListener(this);
        this.noOpen_iv = (ImageView) findViewById(2131298118);
        this.slowLayout = (LinearLayout) findViewById(2131298633);
        this.slowLayout.setOnClickListener(this);
        this.slow_iv = (ImageView) findViewById(2131298634);
        this.voiceNormalLayout = (LinearLayout) findViewById(2131299536);
        this.voiceNormalLayout.setOnClickListener(this);
        this.voiceNormal_iv = (ImageView) findViewById(2131299537);
        this.fastLayout = (LinearLayout) findViewById(2131296961);
        this.fastLayout.setOnClickListener(this);
        this.fast_iv = (ImageView) findViewById(2131296962);
        this.fontNormalLayout = (LinearLayout) findViewById(2131297020);
        this.fontNormalLayout.setOnClickListener(this);
        this.fontNormal_iv = (ImageView) findViewById(2131297021);
        this.fontBigLayout = (LinearLayout) findViewById(2131297017);
        this.fontBigLayout.setOnClickListener(this);
        this.fontBig_iv = (ImageView) findViewById(2131297019);
        this.colorNormalLayout = (LinearLayout) findViewById(2131296687);
        this.colorNormalLayout.setOnClickListener(this);
        this.colorNormal_iv = (ImageView) findViewById(2131296688);
        this.colorHighLayout = (LinearLayout) findViewById(2131296685);
        this.colorHighLayout.setOnClickListener(this);
        this.colorHigh_iv = (ImageView) findViewById(2131296686);
        this.zihao_setting_li = (LinearLayout) findViewById(2131299886);
        String string = App.getSharePreferenceUtil().getString(ConfigManager.config_font_Size);
        if (string != null && !string.isEmpty()) {
            if (string.equals("config_font_standard")) {
                setFontState(0);
            } else {
                setFontState(1);
            }
        }
        String string2 = App.getSharePreferenceUtil().getString(this.shilaohuaVoiceIsOpenKey);
        String string3 = App.getSharePreferenceUtil().getString(this.shilaohuaVoiceKey);
        if (string2 != null && !string2.isEmpty()) {
            if (string2.equals("false") || string2.isEmpty()) {
                setVoiceState(0);
            } else if (string3 != null && !string3.isEmpty()) {
                if (string3.equals("0.5")) {
                    setVoiceState(1);
                } else if (string3.equals("1")) {
                    setVoiceState(2);
                } else if (string3.equals("1.5")) {
                    setVoiceState(3);
                }
            } else {
                App.getSharePreferenceUtil().putString(this.shilaohuaVoiceKey, "0.5");
                setVoiceState(1);
            }
        }
        String string4 = App.getSharePreferenceUtil().getString(this.shilaohuaColorSettingKey);
        if (string4 != null && !string4.isEmpty()) {
            if (string4.equals("gaoduibi")) {
                setColorState(1);
                return;
            } else {
                setColorState(0);
                return;
            }
        }
        setColorState(0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        switch (view.getId()) {
            case 2131296685:
                App.getSharePreferenceUtil().putString(this.shilaohuaColorSettingKey, "gaoduibi");
                setColorState(1);
                break;
            case 2131296687:
                setColorState(0);
                App.getSharePreferenceUtil().putString(this.shilaohuaColorSettingKey, "null");
                break;
            case 2131296961:
                App.getSharePreferenceUtil().putString(this.shilaohuaVoiceIsOpenKey, "true");
                App.getSharePreferenceUtil().putString(this.shilaohuaVoiceKey, "1.5");
                setVoiceState(3);
                break;
            case 2131297017:
                App.getSharePreferenceUtil().putString(ConfigManager.config_font_Size, "config_font_big");
                UnicomHomeFragment.currentPhone = "";
                UserFragment.currentPhone = "";
                WebFragment.isReloginShop = true;
                WebFragment.isRelogin = true;
                WebFragment.isReloginHuodong = true;
                setFontState(1);
                break;
            case 2131297020:
                App.getSharePreferenceUtil().putString(ConfigManager.config_font_Size, "config_font_standard");
                UnicomHomeFragment.currentPhone = "";
                UserFragment.currentPhone = "";
                WebFragment.isReloginShop = true;
                WebFragment.isRelogin = true;
                WebFragment.isReloginHuodong = true;
                setFontState(0);
                break;
            case 2131298119:
                App.getSharePreferenceUtil().putString(this.shilaohuaVoiceIsOpenKey, "false");
                setVoiceState(0);
                break;
            case 2131298633:
                App.getSharePreferenceUtil().putString(this.shilaohuaVoiceIsOpenKey, "true");
                App.getSharePreferenceUtil().putString(this.shilaohuaVoiceKey, "0.5");
                setVoiceState(1);
                break;
            case 2131299536:
                App.getSharePreferenceUtil().putString(this.shilaohuaVoiceIsOpenKey, "true");
                App.getSharePreferenceUtil().putString(this.shilaohuaVoiceKey, "1");
                setVoiceState(2);
                break;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private void setVoiceState(int i) {
        switch (i) {
            case 0:
                this.noOpen_iv.setImageResource(2131230812);
                this.slow_iv.setImageResource(2131230811);
                this.voiceNormal_iv.setImageResource(2131230811);
                this.fast_iv.setImageResource(2131230811);
                return;
            case 1:
                this.noOpen_iv.setImageResource(2131230811);
                this.slow_iv.setImageResource(2131230812);
                this.voiceNormal_iv.setImageResource(2131230811);
                this.fast_iv.setImageResource(2131230811);
                return;
            case 2:
                this.noOpen_iv.setImageResource(2131230811);
                this.slow_iv.setImageResource(2131230811);
                this.voiceNormal_iv.setImageResource(2131230812);
                this.fast_iv.setImageResource(2131230811);
                return;
            case 3:
                this.noOpen_iv.setImageResource(2131230811);
                this.slow_iv.setImageResource(2131230811);
                this.voiceNormal_iv.setImageResource(2131230811);
                this.fast_iv.setImageResource(2131230812);
                return;
            default:
                return;
        }
    }

    private void setFontState(int i) {
        if (i == 0) {
            this.fontNormal_iv.setImageResource(2131230812);
            this.fontBig_iv.setImageResource(2131230811);
            return;
        }
        this.fontNormal_iv.setImageResource(2131230811);
        this.fontBig_iv.setImageResource(2131230812);
    }

    private void setColorState(int i) {
        if (i == 0) {
            this.colorNormal_iv.setImageResource(2131230812);
            this.colorHigh_iv.setImageResource(2131230811);
            return;
        }
        this.colorNormal_iv.setImageResource(2131230811);
        this.colorHigh_iv.setImageResource(2131230812);
    }
}
