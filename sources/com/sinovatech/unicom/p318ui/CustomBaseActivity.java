package com.sinovatech.unicom.p318ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import com.fort.andjni.JniLib;
import com.sinovatech.unicom.basic.eventbus.CustomActivityEvent;
import com.sinovatech.unicom.basic.eventbus.FinishActivityEvent;
import com.sinovatech.unicom.basic.eventbus.FinishtEvent;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.user.UserFragment;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* renamed from: com.sinovatech.unicom.ui.CustomBaseActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomBaseActivity extends BaseActivity {
    private long exitTime = 0;

    public void checkWoKouLing() {
        JniLib.m15918cV(this, 43);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        JniLib.m15918cV(this, 44);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinishEvent(FinishActivityEvent finishActivityEvent) {
        JniLib.m15918cV(this, finishActivityEvent, 45);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return JniLib.m15917cZ(this, Integer.valueOf(i), keyEvent, 46);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        JniLib.m15918cV(this, 47);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        EventBusUtils.register(this);
        UIUtils.setStatusBlack(this);
        EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CLOSE_MAIN_ACTIVITY));
        EventBusUtils.post(new FinishtEvent(0));
    }

    private void exit() {
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            UIUtils.toast("再按一次退出中国联通APP");
            this.exitTime = System.currentTimeMillis();
            return;
        }
        App.setLogined(LoginStateConst.UNLOGIN);
        try {
            App.realexit = true;
            App.homeCardBg = false;
            App.cardBgMap.clear();
            UserFragment.currentPhone = "";
            ManagerLocation.releaseManagerLocation();
            App.isCityToHome = false;
            App.isShowFingerdialog = false;
            App.getSharePreferenceUtil().putString("lat", "");
            App.getSharePreferenceUtil().putString("long", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void customActivityFinish(CustomActivityEvent customActivityEvent) {
        if (EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH == customActivityEvent.getCode()) {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFingerError(CustomActivityEvent customActivityEvent) {
        if (EventBusUtils.EVENT_CUSTOM_MAIN_FINGER_ERROR == customActivityEvent.getCode()) {
            App.getSharePreferenceUtil().putBoolean("CareHome", false);
            App.mainTagFromOtherActivity = MainActivity.Fragment_Home;
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
