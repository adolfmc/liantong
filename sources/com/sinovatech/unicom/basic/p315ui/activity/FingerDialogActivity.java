package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.FingerDialogManager;
import com.sinovatech.unicom.common.FingerPrintUtils;
import com.sinovatech.unicom.common.UIUtils;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.FingerDialogActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FingerDialogActivity extends Activity {
    public NBSTraceUnit _nbs_trace;
    private FingerCompleteInterface fingerCompleteInterface;
    private int fingerCount;
    private FingerDialogManager fingerDialogManager;
    private FingerPrintUtils mFingerUtils;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.FingerDialogActivity$FingerCompleteInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface FingerCompleteInterface {
        void cancel();

        void fail();

        void reset();

        void success();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 50);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    static /* synthetic */ int access$208(FingerDialogActivity fingerDialogActivity) {
        int i = fingerDialogActivity.fingerCount;
        fingerDialogActivity.fingerCount = i + 1;
        return i;
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.activity.FingerDialogActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C73181 implements FingerCompleteInterface {
        C73181() {
        }

        @Override // com.sinovatech.unicom.basic.p315ui.activity.FingerDialogActivity.FingerCompleteInterface
        public void success() {
            Intent intent = new Intent();
            intent.putExtra("method", 1);
            FingerDialogActivity.this.setResult(-1, intent);
            FingerDialogActivity.this.finish();
        }

        @Override // com.sinovatech.unicom.basic.p315ui.activity.FingerDialogActivity.FingerCompleteInterface
        public void fail() {
            Intent intent = new Intent();
            intent.putExtra("method", 2);
            FingerDialogActivity.this.setResult(-1, intent);
            FingerDialogActivity.this.finish();
        }

        @Override // com.sinovatech.unicom.basic.p315ui.activity.FingerDialogActivity.FingerCompleteInterface
        public void reset() {
            Intent intent = new Intent();
            intent.putExtra("method", 3);
            FingerDialogActivity.this.setResult(-1, intent);
            FingerDialogActivity.this.finish();
        }

        @Override // com.sinovatech.unicom.basic.p315ui.activity.FingerDialogActivity.FingerCompleteInterface
        public void cancel() {
            Intent intent = new Intent();
            intent.putExtra("method", 4);
            FingerDialogActivity.this.setResult(-1, intent);
            FingerDialogActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        show();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.FingerDialogActivity$FingerCallBack */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class FingerCallBack extends FingerprintManagerCompat.AuthenticationCallback {
        private FingerCompleteInterface anInterface;

        public FingerCallBack(FingerCompleteInterface fingerCompleteInterface) {
            this.anInterface = fingerCompleteInterface;
        }

        @Override // android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
        public void onAuthenticationError(int i, CharSequence charSequence) {
            super.onAuthenticationError(i, charSequence);
            String charSequence2 = TextUtils.isEmpty(charSequence) ? "失败次数过多，短时间内不能调用指纹验证!" : charSequence.toString();
            FingerDialogActivity.this.fingerDialogManager.startShake();
            FingerDialogActivity.this.fingerDialogManager.setTip(charSequence2);
            FingerDialogActivity.this.mFingerUtils.stopsFingerPrintListener();
        }

        @Override // android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
        public void onAuthenticationHelp(int i, CharSequence charSequence) {
            super.onAuthenticationHelp(i, charSequence);
        }

        @Override // android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult authenticationResult) {
            super.onAuthenticationSucceeded(authenticationResult);
            FingerDialogActivity.this.mFingerUtils.stopsFingerPrintListener();
            FingerDialogActivity.this.fingerDialogManager.cancel();
            this.anInterface.success();
        }

        @Override // android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            FingerDialogActivity.this.fingerDialogManager.startShake();
            FingerDialogActivity.this.fingerDialogManager.setTip("再试一次!");
            FingerDialogActivity.access$208(FingerDialogActivity.this);
            if (FingerDialogActivity.this.fingerCount >= 4) {
                FingerDialogActivity.this.mFingerUtils.stopsFingerPrintListener();
                this.anInterface.fail();
                FingerDialogActivity.this.fingerDialogManager.cancel();
                UIUtils.toast("尝试次数过多，请稍后再试！");
            }
        }
    }

    private void show() {
        FingerDialogManager fingerDialogManager = this.fingerDialogManager;
        if (fingerDialogManager == null) {
            return;
        }
        fingerDialogManager.cancel();
        this.mFingerUtils.setFingerPrintListener(new FingerCallBack(this.fingerCompleteInterface));
        this.fingerDialogManager.show("温馨提示", "请验证已有的指纹", true, new FingerDialogManager.FingerDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.FingerDialogActivity.2
            @Override // com.sinovatech.unicom.basic.p315ui.FingerDialogManager.FingerDialogListener
            public void onCancel() {
                FingerDialogActivity.this.mFingerUtils.stopsFingerPrintListener();
                FingerDialogActivity.this.fingerDialogManager.cancel();
                FingerDialogActivity.this.fingerCompleteInterface.cancel();
            }

            @Override // com.sinovatech.unicom.basic.p315ui.FingerDialogManager.FingerDialogListener
            public void onReset() {
                FingerDialogActivity.this.mFingerUtils.stopsFingerPrintListener();
                FingerDialogActivity.this.fingerDialogManager.cancel();
                FingerDialogActivity.this.fingerCompleteInterface.reset();
            }
        });
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
