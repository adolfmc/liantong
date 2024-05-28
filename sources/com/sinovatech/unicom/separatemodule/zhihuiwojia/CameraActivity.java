package com.sinovatech.unicom.separatemodule.zhihuiwojia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;
import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CameraActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;
    private JCameraView jCameraView;
    private TextView readNumTv;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 121);
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

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.zhihuiwojia.CameraActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97041 implements ErrorListener {
        C97041() {
        }

        @Override // com.cjt2325.cameralibrary.listener.ErrorListener
        public void onError() {
            CameraActivity.this.setResult(103, new Intent());
            CameraActivity.this.finish();
        }

        @Override // com.cjt2325.cameralibrary.listener.ErrorListener
        public void AudioPermissionError() {
            CameraActivity.this.setResult(103, new Intent());
            CameraActivity.this.finish();
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.zhihuiwojia.CameraActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97052 implements JCameraListener {
        C97052() {
        }

        @Override // com.cjt2325.cameralibrary.listener.JCameraListener
        public void captureSuccess(Bitmap bitmap) {
            String saveBitmap = FileUtil.saveBitmap("JCamera", bitmap);
            Intent intent = new Intent();
            intent.putExtra("path", saveBitmap);
            CameraActivity.this.setResult(101, intent);
            CameraActivity.this.finish();
        }

        @Override // com.cjt2325.cameralibrary.listener.JCameraListener
        public void recordSuccess(String str, Bitmap bitmap) {
            Intent intent = new Intent();
            intent.putExtra("path", str);
            CameraActivity.this.setResult(102, intent);
            CameraActivity.this.finish();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.zhihuiwojia.CameraActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C97063 implements ClickListener {
        C97063() {
        }

        @Override // com.cjt2325.cameralibrary.listener.ClickListener
        public void onClick() {
            CameraActivity.this.finish();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.zhihuiwojia.CameraActivity$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C97074 implements ClickListener {
        C97074() {
        }

        @Override // com.cjt2325.cameralibrary.listener.ClickListener
        public void onClick() {
            UIUtils.toast("Right");
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(4);
        }
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.jCameraView.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.jCameraView.onPause();
    }
}
