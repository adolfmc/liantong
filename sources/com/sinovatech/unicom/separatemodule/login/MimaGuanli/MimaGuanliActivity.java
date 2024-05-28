package com.sinovatech.unicom.separatemodule.login.MimaGuanli;

import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil;
import io.reactivex.functions.Consumer;
import java.util.HashMap;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MimaGuanliActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;
    private UserManager userManager;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 92);
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

    public static /* synthetic */ void lambda$onCreate$1(MimaGuanliActivity mimaGuanliActivity, View view) {
        HashMap hashMap = new HashMap();
        String str = "03".equals(mimaGuanliActivity.userManager.getCurrentPhoneType()) ? "broad" : "";
        if ("02".equals(mimaGuanliActivity.userManager.getCurrentPhoneType())) {
            str = "fixedTel";
        }
        hashMap.put("accountId", mimaGuanliActivity.userManager.getCurrentPhoneNumber());
        hashMap.put("logintype", str);
        hashMap.put("cityCode", mimaGuanliActivity.userManager.getUserAreaid());
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
        hashMap.put("netWay", DeviceHelper.getNETType(App.getInstance()));
        hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
        hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
        hashMap.put("deviceOS", DeviceHelper.getDeviceOSVersion());
        hashMap.put("pip", SystemServiceUtils.getLocalIpAddress());
        hashMap.put("version", App.getInstance().getString(2131886969));
        hashMap.put("entrance", "setting");
        JianDanMiMaUtil.gotoWebiview(mimaGuanliActivity, hashMap, new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.login.MimaGuanli.MimaGuanliActivity.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
            }
        });
    }
}
