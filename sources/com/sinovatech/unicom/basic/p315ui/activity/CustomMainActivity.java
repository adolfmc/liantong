package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p083v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.CustomActivityEvent;
import com.sinovatech.unicom.basic.eventbus.FinishtEvent;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.KacaoPvLog;
import com.sinovatech.unicom.common.KeyBoardListener;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.YinSiDialogUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.CustomBaseActivity;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.CustomMainActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomMainActivity extends CustomBaseActivity {
    public NBSTraceUnit _nbs_trace;
    private WebFragment webFragment = null;
    private String currentPhoneNum = "0";

    @Override // com.sinovatech.unicom.p318ui.CustomBaseActivity, android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.CustomBaseActivity, com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
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

    @Override // com.sinovatech.unicom.p318ui.CustomBaseActivity, com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(2131492903);
        KeyBoardListener.getInstance(this).init();
        EventBusUtils.post(new FinishtEvent(0));
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setNeedTitle(false);
        webParamsEntity.setUrl(getIntent().getStringExtra("ms_unicom_url"));
        webParamsEntity.setType("care");
        webParamsEntity.setRequestType("get");
        this.webFragment = WebFragment.newIntence(webParamsEntity);
        beginTransaction.add(2131297037, this.webFragment).show(this.webFragment).commit();
        YinSiDialogUtils.changeYinSiDialog(this);
        LanguageUtil.getInstance().getLanguageData(LanguageUtil.getInstance().getLanguage(), null);
        App.realexit = false;
        if (!getIntent().getBooleanExtra("fromSetting", false)) {
            flag = true;
        }
        handleExternURL();
        this.currentPhoneNum = UserManager.getInstance().getCurrentPhoneNumber();
        new AnquanzhognxinCheckStart().welcomStart(this, UserManager.getInstance().getCurrentPhoneNumber(), new AnquanzhognxinCheckStart.StartCallBack() { // from class: com.sinovatech.unicom.basic.ui.activity.CustomMainActivity.1
            @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.StartCallBack
            public void complete() {
            }

            @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.StartCallBack
            public void onCancel() {
                CustomMainActivity.goMainActivity(CustomMainActivity.this);
            }
        });
        KacaoPvLog.log(this, "冷启动");
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        try {
            if (!TextUtils.equals(this.currentPhoneNum, UserManager.getInstance().getCurrentPhoneNumber())) {
                this.currentPhoneNum = UserManager.getInstance().getCurrentPhoneNumber();
                if (!App.hasLogined()) {
                    goMainActivity(this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleExternURL();
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x024c A[Catch: Exception -> 0x0304, TryCatch #1 {Exception -> 0x0304, blocks: (B:3:0x0002, B:5:0x000a, B:7:0x0014, B:11:0x005e, B:86:0x02f4, B:8:0x001f, B:10:0x0029, B:12:0x0068, B:14:0x0072, B:16:0x0083, B:21:0x00da, B:20:0x00d7, B:23:0x00df, B:25:0x00ed, B:28:0x00f7, B:30:0x00ff, B:32:0x0117, B:33:0x0127, B:35:0x012d, B:37:0x0135, B:39:0x0138, B:41:0x013b, B:43:0x0146, B:47:0x0166, B:44:0x0152, B:46:0x015c, B:48:0x0169, B:50:0x01b4, B:61:0x0239, B:65:0x0254, B:67:0x026d, B:69:0x027c, B:68:0x027a, B:64:0x024c, B:52:0x01cd, B:54:0x01d6, B:56:0x01f7, B:58:0x0203, B:57:0x01fc, B:60:0x0231, B:70:0x0288, B:72:0x028c, B:73:0x029e, B:75:0x02a8, B:78:0x02b3, B:80:0x02bb, B:81:0x02c5, B:83:0x02d4, B:85:0x02e7, B:84:0x02e3, B:17:0x0094), top: B:93:0x0002, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x026d A[Catch: Exception -> 0x0304, TryCatch #1 {Exception -> 0x0304, blocks: (B:3:0x0002, B:5:0x000a, B:7:0x0014, B:11:0x005e, B:86:0x02f4, B:8:0x001f, B:10:0x0029, B:12:0x0068, B:14:0x0072, B:16:0x0083, B:21:0x00da, B:20:0x00d7, B:23:0x00df, B:25:0x00ed, B:28:0x00f7, B:30:0x00ff, B:32:0x0117, B:33:0x0127, B:35:0x012d, B:37:0x0135, B:39:0x0138, B:41:0x013b, B:43:0x0146, B:47:0x0166, B:44:0x0152, B:46:0x015c, B:48:0x0169, B:50:0x01b4, B:61:0x0239, B:65:0x0254, B:67:0x026d, B:69:0x027c, B:68:0x027a, B:64:0x024c, B:52:0x01cd, B:54:0x01d6, B:56:0x01f7, B:58:0x0203, B:57:0x01fc, B:60:0x0231, B:70:0x0288, B:72:0x028c, B:73:0x029e, B:75:0x02a8, B:78:0x02b3, B:80:0x02bb, B:81:0x02c5, B:83:0x02d4, B:85:0x02e7, B:84:0x02e3, B:17:0x0094), top: B:93:0x0002, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x027a A[Catch: Exception -> 0x0304, TryCatch #1 {Exception -> 0x0304, blocks: (B:3:0x0002, B:5:0x000a, B:7:0x0014, B:11:0x005e, B:86:0x02f4, B:8:0x001f, B:10:0x0029, B:12:0x0068, B:14:0x0072, B:16:0x0083, B:21:0x00da, B:20:0x00d7, B:23:0x00df, B:25:0x00ed, B:28:0x00f7, B:30:0x00ff, B:32:0x0117, B:33:0x0127, B:35:0x012d, B:37:0x0135, B:39:0x0138, B:41:0x013b, B:43:0x0146, B:47:0x0166, B:44:0x0152, B:46:0x015c, B:48:0x0169, B:50:0x01b4, B:61:0x0239, B:65:0x0254, B:67:0x026d, B:69:0x027c, B:68:0x027a, B:64:0x024c, B:52:0x01cd, B:54:0x01d6, B:56:0x01f7, B:58:0x0203, B:57:0x01fc, B:60:0x0231, B:70:0x0288, B:72:0x028c, B:73:0x029e, B:75:0x02a8, B:78:0x02b3, B:80:0x02bb, B:81:0x02c5, B:83:0x02d4, B:85:0x02e7, B:84:0x02e3, B:17:0x0094), top: B:93:0x0002, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleExternURL() {
        /*
            Method dump skipped, instructions count: 777
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.CustomMainActivity.handleExternURL():void");
    }

    public static boolean isCustomMain() {
        String string = App.getSharePreferenceUtil().getString("unicom_app_main_type");
        return (TextUtils.isEmpty(string) || "0".equals(string)) ? false : true;
    }

    public static void goMainActivity(Activity activity) {
        App.getSharePreferenceUtil().putString("unicom_app_main_type", "0");
        App.getSharePreferenceUtil().putString("unicom_app_main_url", "");
        EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
        activity.startActivity(new Intent(activity, MainActivity.class));
        App.getSharePreferenceUtil().putBoolean("CareHome", false);
        activity.finish();
    }
}
