package com.example.asus.detectionandalign.ocr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bytedance.applog.tracker.Tracker;
import com.example.asus.detectionandalign.C4243R;
import com.example.asus.detectionandalign.DetectionAuthentic;
import com.example.asus.detectionandalign.VerticalTextView;
import com.example.asus.detectionandalign.utils.C4293e;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import comp.android.app.face.p381sz.camera.BaseActivity;
import comp.android.app.face.p381sz.camera.JCameraView;
import comp.android.app.face.p381sz.camera.listener.ErrorListener;
import comp.android.app.face.p381sz.camera.listener.JCameraListener;
import comp.android.app.face.p381sz.camera.listener.OnPreviewCallback;
import comp.android.app.face.p381sz.camera.util.ScreenUtils;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ManualOcrActivity extends BaseActivity implements View.OnClickListener, ErrorListener, JCameraListener, OnPreviewCallback {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    private JCameraView f10071a;

    /* renamed from: b */
    private ImageView f10072b;

    /* renamed from: c */
    private ImageView f10073c;

    /* renamed from: d */
    private ImageView f10074d;

    /* renamed from: g */
    private VerticalTextView f10077g;

    /* renamed from: j */
    private DetectionAuthentic f10080j;

    /* renamed from: e */
    private int f10075e = 1;

    /* renamed from: f */
    private int f10076f = 0;

    /* renamed from: h */
    private double f10078h = 0.0d;

    /* renamed from: i */
    private double f10079i = 0.0d;

    @Override // comp.android.app.face.p381sz.camera.listener.ErrorListener
    public void AudioPermissionError() {
    }

    /* renamed from: a */
    public Bitmap m15968a(Bitmap bitmap, int i, int i2, boolean z) {
        int i3;
        int i4;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height < i) {
            return bitmap;
        }
        int i5 = i / 2;
        int i6 = i2 / 2;
        int i7 = width / 2;
        int i8 = height / 2;
        if (width < i2) {
            i4 = 0;
            i3 = width;
        } else {
            i3 = i2;
            i4 = i7 - i6;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i4, i8 - i5, i3, i, (Matrix) null, false);
        if (z && bitmap != null && !bitmap.equals(createBitmap) && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    @Override // comp.android.app.face.p381sz.camera.listener.JCameraListener
    public void captureSuccess(byte[] bArr) {
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(this.f10076f == 0 ? 90.0f : -90.0f);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeByteArray, 1280, (decodeByteArray.getHeight() * 1280) / decodeByteArray.getWidth(), false);
        Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap, 0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight(), matrix, true);
        Bitmap m15968a = m15968a(createBitmap, (int) (this.f10073c.getHeight() * (createBitmap.getWidth() / this.f10078h)), (int) (this.f10073c.getWidth() * (createBitmap.getHeight() / this.f10079i)), false);
        DetectionAuthentic detectionAuthentic = this.f10080j;
        if (detectionAuthentic != null) {
            detectionAuthentic.captureSuccess(m15968a);
        }
        finish();
    }

    @Override // comp.android.app.face.p381sz.camera.BaseActivity
    public String getDiskCacheDir(Context context) {
        return (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir() : context.getCacheDir()).getPath();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view.getId() == C4243R.C4246id.photograph_img && C4293e.m15951a(C4243R.C4246id.photograph_img)) {
            this.f10071a.capture();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // comp.android.app.face.p381sz.camera.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        VerticalTextView verticalTextView;
        int i;
        NBSTraceEngine.startTracing(getClass().getName());
        requestWindowFeature(1);
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(C4243R.C4247layout.sz_ocr_activity);
        this.f10071a = (JCameraView) findViewById(C4243R.C4246id.surfaceView);
        this.f10072b = (ImageView) findViewById(C4243R.C4246id.photograph_img);
        this.f10073c = (ImageView) findViewById(C4243R.C4246id.img_background);
        this.f10077g = (VerticalTextView) findViewById(C4243R.C4246id.oliveapp_face_hintTextView);
        this.f10074d = (ImageView) findViewById(C4243R.C4246id.img);
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        this.f10078h = defaultDisplay.getWidth();
        this.f10079i = defaultDisplay.getHeight();
        setLayoutParams((RelativeLayout) findViewById(C4243R.C4246id.relative));
        setLayoutParams1((RelativeLayout) findViewById(C4243R.C4246id.prompt_message));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f10073c.getLayoutParams();
        int screenHeight = ScreenUtils.getScreenHeight(this);
        layoutParams.width = (int) (ScreenUtils.getScreenWidth(this) * 0.8f);
        layoutParams.height = (int) (screenHeight * 0.7f);
        this.f10073c.setLayoutParams(layoutParams);
        this.f10080j = DetectionAuthentic.getInstance(this, null);
        Intent intent = getIntent();
        if (intent != null) {
            this.f10075e = intent.getIntExtra(DetectionAuthentic.FRAME, 1);
        }
        int i2 = this.f10075e;
        if (i2 == 1) {
            this.f10073c.setImageResource(C4243R.C4245drawable.oliveapp_face_idcard_shade_skeleton_minimum2);
            verticalTextView = this.f10077g;
            i = C4243R.string.oliveapp_face_database_image_hint_case_default_front;
        } else if (i2 != 2) {
            if (i2 == 3) {
                this.f10073c.setImageResource(C4243R.C4245drawable.oliveapp_face_idcard_shade_skeleton_minimum3);
            }
            this.f10071a.start(this.f10076f);
            JCameraView jCameraView = this.f10071a;
            jCameraView.setSaveVideoPath(getDiskCacheDir(this) + "/Media");
            this.f10071a.setPlayVideo(false);
            this.f10071a.setFeatures(259);
            this.f10071a.setMediaQuality(1600000);
            this.f10071a.setCameraSettings(false);
            this.f10071a.setCaptureLayout(false);
            this.f10071a.setOnPreviewCallback(this);
            this.f10071a.setErrorLisenter(this);
            this.f10071a.setJCameraLisenter(this);
            this.f10072b.setOnClickListener(this);
            NBSAppInstrumentation.activityCreateEndIns();
        } else {
            this.f10073c.setImageResource(C4243R.C4245drawable.oliveapp_face_idcard_shade_skeleton_minimum1);
            verticalTextView = this.f10077g;
            i = C4243R.string.oliveapp_face_database_image_hint_case_default_back;
        }
        verticalTextView.setText(i);
        this.f10071a.start(this.f10076f);
        JCameraView jCameraView2 = this.f10071a;
        jCameraView2.setSaveVideoPath(getDiskCacheDir(this) + "/Media");
        this.f10071a.setPlayVideo(false);
        this.f10071a.setFeatures(259);
        this.f10071a.setMediaQuality(1600000);
        this.f10071a.setCameraSettings(false);
        this.f10071a.setCaptureLayout(false);
        this.f10071a.setOnPreviewCallback(this);
        this.f10071a.setErrorLisenter(this);
        this.f10071a.setJCameraLisenter(this);
        this.f10072b.setOnClickListener(this);
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // comp.android.app.face.p381sz.camera.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        C4293e.m15951a(19501634);
        this.f10071a.onDestroy();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.ErrorListener
    public void onError() {
        DetectionAuthentic detectionAuthentic = this.f10080j;
        if (detectionAuthentic != null) {
            detectionAuthentic.onSDKUsingFail("相机启动异常", "2009");
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // comp.android.app.face.p381sz.camera.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f10071a.onPause();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.OnPreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        DetectionAuthentic detectionAuthentic = this.f10080j;
        if (detectionAuthentic != null) {
            detectionAuthentic.onPreviewFrame(bArr, camera);
        }
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // comp.android.app.face.p381sz.camera.BaseActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.f10071a.onResume();
        this.f10071a.setAutoFocus(1L);
        NBSAppInstrumentation.activityResumeEndIns();
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

    @Override // comp.android.app.face.p381sz.camera.listener.JCameraListener
    public void recordSuccess(String str, Bitmap bitmap) {
    }
}
