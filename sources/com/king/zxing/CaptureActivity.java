package com.king.zxing;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import com.fort.andjni.JniLib;
import com.king.zxing.camera.CameraManager;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CaptureActivity extends AppCompatActivity implements OnCaptureCallback {
    public static final String KEY_RESULT = "SCAN_RESULT";
    public NBSTraceUnit _nbs_trace;
    private View ivTorch;
    private CaptureHelper mCaptureHelper;
    public SurfaceView surfaceView;
    public ViewfinderView viewfinderView;

    public boolean isContentView(@LayoutRes int i) {
        return true;
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 16);
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

    @Override // com.king.zxing.OnCaptureCallback
    public boolean onResultCallback(String str) {
        return false;
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

    public void initUI() {
        this.surfaceView = (SurfaceView) findViewById(getSurfaceViewId());
        this.viewfinderView = (ViewfinderView) findViewById(getViewfinderViewId());
        this.ivTorch = findViewById(getIvTorchId());
        this.ivTorch.setVisibility(4);
        this.mCaptureHelper = new CaptureHelper(this, this.surfaceView, this.viewfinderView, this.ivTorch);
        this.mCaptureHelper.setOnCaptureCallback(this);
        this.mCaptureHelper.onCreate();
    }

    public int getLayoutId() {
        return C5188R.C5192layout.zxl_capture;
    }

    public int getViewfinderViewId() {
        return C5188R.C5191id.viewfinderView;
    }

    public int getSurfaceViewId() {
        return C5188R.C5191id.surfaceView;
    }

    public int getIvTorchId() {
        return C5188R.C5191id.ivTorch;
    }

    public CaptureHelper getCaptureHelper() {
        return this.mCaptureHelper;
    }

    @Deprecated
    public CameraManager getCameraManager() {
        return this.mCaptureHelper.getCameraManager();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.mCaptureHelper.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mCaptureHelper.onPause();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mCaptureHelper.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mCaptureHelper.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
