package com.sinovatech.unicom.separatemodule.login.esim;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ESIMXeiyiActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private ESIMXeiyiActivity activityContext;
    private CheckBox checkBox;
    private FrameLayout checkFrameLayout;
    private TextView xieyiTextView;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 94);
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

    public static /* synthetic */ void lambda$onCreate$0(ESIMXeiyiActivity eSIMXeiyiActivity, View view) {
        eSIMXeiyiActivity.setResult(0);
        eSIMXeiyiActivity.finish();
        eSIMXeiyiActivity.log("返回ESIM登录");
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.login.esim.ESIMXeiyiActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC89601 implements View.OnClickListener {
        View$OnClickListenerC89601() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            ESIMXeiyiActivity.this.checkBox.setChecked(!ESIMXeiyiActivity.this.checkBox.isChecked());
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.login.esim.ESIMXeiyiActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C89612 implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ Button val$loginButton;

        C89612(Button button) {
            this.val$loginButton = button;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Tracker.onCheckedChanged(compoundButton, z);
            if (z) {
                this.val$loginButton.setEnabled(true);
                this.val$loginButton.setBackgroundResource(2131231879);
                ESIMXeiyiActivity.this.log("同意刷脸隐私协议");
                return;
            }
            this.val$loginButton.setEnabled(false);
            this.val$loginButton.setBackgroundResource(2131231880);
            ESIMXeiyiActivity.this.log("取消刷脸隐私协议");
        }
    }

    public static /* synthetic */ void lambda$onCreate$2(final ESIMXeiyiActivity eSIMXeiyiActivity, View view) {
        if (!eSIMXeiyiActivity.checkBox.isChecked()) {
            UIUtils.toastCenter("请同意协议");
            return;
        }
        PermissionDialog.show("人脸登录为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        new RxPermissions(eSIMXeiyiActivity.activityContext).request("android.permission.CAMERA").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMXeiyiActivity$3solPUwzOaKbDL_i777nS7nHhss
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ESIMXeiyiActivity.lambda$onCreate$1(ESIMXeiyiActivity.this, (Boolean) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$onCreate$1(ESIMXeiyiActivity eSIMXeiyiActivity, Boolean bool) throws Exception {
        PermissionDialog.dimissDialog();
        if (bool.booleanValue()) {
            eSIMXeiyiActivity.log("开始炫彩活体验证");
            ManagerFaceLogin.starFaceLogin(eSIMXeiyiActivity.activityContext, new ManagerFaceLogin.IFaceResult() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMXeiyiActivity.3
                @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin.IFaceResult
                public void onResult(String str) {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    ESIMXeiyiActivity.this.setResult(-1);
                    ESIMXeiyiActivity.this.finish();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        PvCurrencyLogUtils.pvCurrencyLog("", 2, "S2ndpage1232", "", "", str, "", "", "", "");
    }
}
