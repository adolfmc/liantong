package com.megvii.livenesslib;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.megvii.kas.livenessdetection.DetectionConfig;
import com.megvii.kas.livenessdetection.DetectionFrame;
import com.megvii.kas.livenessdetection.Detector;
import com.megvii.kas.livenessdetection.FaceQualityManager;
import com.megvii.kas.livenessdetection.bean.FaceIDDataStruct;
import com.megvii.kas.livenessdetection.bean.FaceInfo;
import com.megvii.livenesslib.kas.view.CameraGLView;
import com.megvii.livenesslib.util.ConUtil;
import com.megvii.livenesslib.util.DialogUtil;
import com.megvii.livenesslib.util.FlodDialogManager;
import com.megvii.livenesslib.util.FoldUtils;
import com.megvii.livenesslib.util.ICamera;
import com.megvii.livenesslib.util.IDetection;
import com.megvii.livenesslib.util.IFile;
import com.megvii.livenesslib.util.IMediaPlayer;
import com.megvii.livenesslib.util.Screen;
import com.megvii.livenesslib.util.SensorUtil;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LoginFaceLivenessActivity extends Activity implements Camera.PreviewCallback, TextureView.SurfaceTextureListener, Detector.DetectionListener {
    public NBSTraceUnit _nbs_trace;
    private TextureView camerapreview;
    private ImageView headMask;
    private LinearLayout headViewLinear;
    private boolean isHandleStart;
    private ImageView ivFaceBack;
    private JSONObject jsonObject;
    private Camera mCamera;
    private Detector mDetector;
    private DialogUtil mDialogUtil;
    private FaceMask mFaceMask;
    private FaceQualityManager mFaceQualityManager;
    private ICamera mICamera;
    private IDetection mIDetection;
    private IFile mIFile;
    private IMediaPlayer mIMediaPlayer;
    private ProgressBar mProgressBar;
    private String mSession;
    private Handler mainHandler;
    private TextView promptText;
    private RelativeLayout rootView;
    private SensorUtil sensorUtil;
    private LinearLayout timeOutLinear;
    private TextView timeOutText;
    private List<byte[]> bytelist = new ArrayList();
    private Runnable mTimeoutRunnable = new Runnable() { // from class: com.megvii.livenesslib.LoginFaceLivenessActivity.5
        @Override // java.lang.Runnable
        public void run() {
            LoginFaceLivenessActivity.this.initDetecteSession();
            if (LoginFaceLivenessActivity.this.mIDetection.mDetectionSteps != null) {
                LoginFaceLivenessActivity loginFaceLivenessActivity = LoginFaceLivenessActivity.this;
                loginFaceLivenessActivity.changeType(loginFaceLivenessActivity.mIDetection.mDetectionSteps.get(0), 15000L);
            }
        }
    };
    private int mFailFrame = 0;
    private int qulityOk = 0;
    private int mCurStep = 0;
    private boolean mHasSurface = false;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
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

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(C5351R.C5354layout.liveness_login_layout);
        init();
        initData();
        updateHeight();
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void updateHeight() {
        try {
            this.headMask.post(new Runnable() { // from class: com.megvii.livenesslib.LoginFaceLivenessActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    int i;
                    double d = Screen.mScreenWidth / Screen.mScreenHeight;
                    try {
                        Screen.initialize(LoginFaceLivenessActivity.this);
                        if (d > 0.75d) {
                            i = (int) (Screen.mScreenHeight * 0.5d);
                            LoginFaceLivenessActivity.this.headMask.setBackgroundResource(C5351R.C5352drawable.liveness_layout_head_mask_fenping);
                        } else {
                            i = Screen.mScreenWidth;
                            LoginFaceLivenessActivity.this.headMask.setBackgroundResource(C5351R.C5352drawable.liveness_layout_head_mask);
                        }
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) LoginFaceLivenessActivity.this.headMask.getLayoutParams();
                        layoutParams.height = i;
                        LoginFaceLivenessActivity.this.headMask.setLayoutParams(layoutParams);
                    } catch (Exception unused) {
                    }
                    try {
                        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) LoginFaceLivenessActivity.this.camerapreview.getLayoutParams();
                        if (d > 0.75d) {
                            layoutParams2.width = (int) ((Screen.mScreenHeight / 2) * CameraGLView.biLi);
                            layoutParams2.height = Screen.mScreenHeight / 2;
                            layoutParams2.leftMargin = (int) ((Screen.mScreenWidth - ((Screen.mScreenHeight / 2) * CameraGLView.biLi)) / 2.0d);
                        } else {
                            layoutParams2.width = (int) (Screen.mScreenWidth * CameraGLView.biLi);
                            layoutParams2.height = Screen.mScreenWidth;
                            layoutParams2.leftMargin = (int) ((Screen.mScreenWidth - (Screen.mScreenWidth * CameraGLView.biLi)) / 2.0d);
                        }
                        LoginFaceLivenessActivity.this.camerapreview.setLayoutParams(layoutParams2);
                        LoginFaceLivenessActivity.this.mFaceMask.setLayoutParams(layoutParams2);
                    } catch (Exception unused2) {
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    private void checkFlod() {
        try {
            if (FoldUtils.isFold(this)) {
                FlodDialogManager.show(this, "温馨提示", "暂不支持全屏使用扫脸验证，请您使用单屏模式后重新启动中国联通APP进行验证。", "确定");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        this.bytelist.clear();
        this.sensorUtil = new SensorUtil(this);
        Screen.initialize(this);
        this.mSession = ConUtil.getFormatterTime(System.currentTimeMillis());
        this.mainHandler = new Handler();
        this.mIMediaPlayer = new IMediaPlayer(this);
        this.mIFile = new IFile();
        this.mDialogUtil = new DialogUtil(this);
        this.ivFaceBack = (ImageView) findViewById(C5351R.C5353id.iv_face_back);
        this.ivFaceBack.setOnClickListener(new View.OnClickListener() { // from class: com.megvii.livenesslib.LoginFaceLivenessActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                LoginFaceLivenessActivity.this.handleResult(C5351R.string.liveness_detection_failed_cancle, false, "");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.rootView = (RelativeLayout) findViewById(C5351R.C5353id.liveness_layout_rootRel);
        this.mIDetection = new IDetection(this, this.rootView);
        this.mFaceMask = (FaceMask) findViewById(C5351R.C5353id.liveness_layout_facemask);
        this.mICamera = new ICamera();
        this.promptText = (TextView) findViewById(C5351R.C5353id.liveness_layout_promptText);
        this.camerapreview = (TextureView) findViewById(C5351R.C5353id.liveness_layout_textureview);
        this.camerapreview.setSurfaceTextureListener(this);
        this.mProgressBar = (ProgressBar) findViewById(C5351R.C5353id.liveness_layout_progressbar);
        this.mProgressBar.setVisibility(4);
        this.headViewLinear = (LinearLayout) findViewById(C5351R.C5353id.liveness_layout_bottom_tips_head);
        this.headViewLinear.setVisibility(0);
        this.timeOutLinear = (LinearLayout) findViewById(C5351R.C5353id.detection_step_timeoutLinear);
        this.timeOutText = (TextView) findViewById(C5351R.C5353id.detection_step_timeout);
        this.mIDetection.viewsInit();
        this.headMask = (ImageView) findViewById(C5351R.C5353id.liveness_layout_head_mask);
    }

    private void initData() {
        DetectionConfig.Builder builder = new DetectionConfig.Builder();
        builder.setDetectionTimeout(15000);
        this.mDetector = new Detector(this, builder.build());
        if (!this.mDetector.init(this, ConUtil.readModel(this), "")) {
            this.mDialogUtil.showDialog("检测器初始化失败");
        }
        new Thread(new Runnable() { // from class: com.megvii.livenesslib.LoginFaceLivenessActivity.3
            @Override // java.lang.Runnable
            public void run() {
                LoginFaceLivenessActivity.this.mIDetection.animationInit();
            }
        }).start();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.isHandleStart = false;
        this.mCamera = this.mICamera.openCamera(this);
        if (this.mCamera != null) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(1, cameraInfo);
            this.mFaceMask.setFrontal(cameraInfo.facing == 1);
            this.mFaceQualityManager = new FaceQualityManager(0.5f, 0.5f);
            this.mFaceQualityManager.faceMaxSizeRatioThreshold = 0.5f;
            this.mIDetection.mCurShowIndex = -1;
            updateHeight();
        } else {
            this.mDialogUtil.showDialog("打开前置摄像头失败");
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void handleStart() {
        if (this.isHandleStart) {
            return;
        }
        this.isHandleStart = true;
        Animation loadAnimation = AnimationUtils.loadAnimation(this, C5351R.anim.liveness_rightin);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, C5351R.anim.liveness_leftout);
        this.headViewLinear.startAnimation(loadAnimation2);
        this.mIDetection.mAnimViews[0].setVisibility(0);
        this.mIDetection.mAnimViews[0].startAnimation(loadAnimation);
        loadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.megvii.livenesslib.LoginFaceLivenessActivity.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                LoginFaceLivenessActivity.this.timeOutLinear.setVisibility(0);
            }
        });
        this.mainHandler.post(this.mTimeoutRunnable);
        try {
            this.jsonObject = new JSONObject();
            this.jsonObject.put("imgs", new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDetecteSession() {
        if (this.mICamera.mCamera == null) {
            return;
        }
        this.mProgressBar.setVisibility(4);
        this.mIDetection.detectionTypeInit2();
        this.mCurStep = 0;
        this.mDetector.reset();
        this.mDetector.changeDetectionType(this.mIDetection.mDetectionSteps.get(0));
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        this.mDetector.doDetection(bArr, previewSize.width, previewSize.height, 360 - this.mICamera.getCameraAngle(this));
    }

    public JSONObject getBestImageAndDelta(FaceIDDataStruct faceIDDataStruct) throws Exception {
        String str = faceIDDataStruct.delta;
        HashMap hashMap = (HashMap) faceIDDataStruct.images;
        JSONObject jSONObject = new JSONObject();
        for (String str2 : faceIDDataStruct.images.keySet()) {
            String encodeToString = Base64.encodeToString(faceIDDataStruct.images.get(str2), 0);
            if (str2.equals("image_best")) {
                jSONObject.put("image_best", encodeToString);
            }
        }
        int i = 1;
        for (byte[] bArr : this.bytelist) {
            jSONObject.put("image_env_" + i, Base64.encodeToString(bArr, 0));
            i++;
        }
        return jSONObject;
    }

    @Override // com.megvii.kas.livenessdetection.Detector.DetectionListener
    public Detector.DetectionType onDetectionSuccess(DetectionFrame detectionFrame) {
        this.mIMediaPlayer.reset();
        this.mCurStep++;
        this.mFaceMask.setFaceInfo(null);
        if (this.mCurStep >= this.mIDetection.mDetectionSteps.size()) {
            this.mProgressBar.setVisibility(0);
            try {
                JSONObject bestImageAndDelta = getBestImageAndDelta(this.mDetector.getFaceIDDataStruct());
                if (bestImageAndDelta != null && !TextUtils.isEmpty(bestImageAndDelta.optString("image_best"))) {
                    handleResult(C5351R.string.verify_success, true, !(bestImageAndDelta instanceof JSONObject) ? bestImageAndDelta.toString() : NBSJSONObjectInstrumentation.toString(bestImageAndDelta));
                } else {
                    handleResult(C5351R.string.liveness_detection_failed_null, false, "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                handleResult(C5351R.string.liveness_detection_failed, false, "");
            }
        } else {
            changeType(this.mIDetection.mDetectionSteps.get(this.mCurStep), 10L);
        }
        return this.mCurStep >= this.mIDetection.mDetectionSteps.size() ? Detector.DetectionType.DONE : this.mIDetection.mDetectionSteps.get(this.mCurStep);
    }

    @Override // com.megvii.kas.livenessdetection.Detector.DetectionListener
    public void onDetectionFailed(final Detector.DetectionFailedType detectionFailedType) {
        new Thread(new Runnable() { // from class: com.megvii.livenesslib.LoginFaceLivenessActivity.6
            @Override // java.lang.Runnable
            public void run() {
                LoginFaceLivenessActivity.this.mIFile.saveLog(LoginFaceLivenessActivity.this.mSession, detectionFailedType.name());
            }
        }).start();
        int i = C5351R.string.liveness_detection_failed;
        switch (detectionFailedType) {
            case ACTIONBLEND:
                i = C5351R.string.liveness_detection_failed_action_blend;
                break;
            case NOTVIDEO:
                i = C5351R.string.liveness_detection_failed_not_video;
                break;
            case TIMEOUT:
                i = C5351R.string.liveness_detection_failed_timeout;
                break;
        }
        handleResult(i, false, "");
    }

    @Override // com.megvii.kas.livenessdetection.Detector.DetectionListener
    public void onFrameDetected(long j, DetectionFrame detectionFrame) {
        if (this.sensorUtil.isVertical()) {
            faceOcclusion(detectionFrame);
            handleNotPass(j);
            this.mFaceMask.setFaceInfo(detectionFrame);
            return;
        }
        this.promptText.setText("请竖直握紧手机");
    }

    private void faceOcclusion(DetectionFrame detectionFrame) {
        FaceInfo faceInfo;
        this.mFailFrame++;
        if (detectionFrame != null && (faceInfo = detectionFrame.getFaceInfo()) != null) {
            if (faceInfo.eyeLeftOcclusion > 0.5d || faceInfo.eyeRightOcclusion > 0.5d) {
                if (this.mFailFrame > 10) {
                    this.mFailFrame = 0;
                    this.promptText.setText("请勿用手遮挡眼睛");
                    return;
                }
                return;
            } else if (faceInfo.mouthOcclusion > 0.5d) {
                if (this.mFailFrame > 10) {
                    this.mFailFrame = 0;
                    this.promptText.setText("请勿用手遮挡嘴巴");
                    return;
                }
                return;
            }
        }
        faceInfoChecker(this.mFaceQualityManager.feedFrame(detectionFrame), detectionFrame);
    }

    public void faceInfoChecker(List<FaceQualityManager.FaceQualityErrorType> list, DetectionFrame detectionFrame) {
        if (list == null || list.size() == 0) {
            if (this.qulityOk >= 2) {
                handleStart();
            } else {
                this.bytelist.add(detectionFrame.getImageData(new Rect(), false, 75, this.mICamera.cameraHeight, false, false, 0));
            }
            this.qulityOk++;
            return;
        }
        String str = "";
        FaceQualityManager.FaceQualityErrorType faceQualityErrorType = list.get(0);
        if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_NOT_FOUND) {
            str = "请让我看到您的正脸";
        } else if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_POS_DEVIATED) {
            str = "请让我看到您的正脸";
        } else if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_NONINTEGRITY) {
            str = "请让我看到您的正脸";
        } else if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_TOO_DARK) {
            str = "请让光线再亮点";
        } else if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_TOO_BRIGHT) {
            str = "请让光线再暗点";
        } else if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_TOO_SMALL) {
            str = "请再靠近一些";
        } else if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_TOO_LARGE) {
            str = "请再离远一些";
        } else if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_TOO_BLURRY) {
            str = "请避免侧光和背光";
        } else if (faceQualityErrorType == FaceQualityManager.FaceQualityErrorType.FACE_OUT_OF_RECT) {
            str = "请保持脸在人脸框中";
        }
        if (this.mFailFrame > 10) {
            this.mFailFrame = 0;
            this.promptText.setText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(int i, boolean z, String str) {
        String string = getResources().getString(i);
        if (z && !TextUtils.isEmpty(str)) {
            Intent intent = new Intent();
            intent.putExtra("result", str);
            setResult(-1, intent);
        } else {
            Toast.makeText(this, string + "", 1).show();
        }
        finish();
    }

    public void changeType(Detector.DetectionType detectionType, long j) {
        this.mIDetection.changeType(detectionType, j);
        this.mFaceMask.setFaceInfo(null);
        if (this.mCurStep == 0) {
            IMediaPlayer iMediaPlayer = this.mIMediaPlayer;
            iMediaPlayer.doPlay(iMediaPlayer.getSoundRes(detectionType));
            return;
        }
        this.mIMediaPlayer.doPlay(C5351R.C5355raw.meglive_well_done);
        this.mIMediaPlayer.setOnCompletionListener(detectionType);
    }

    public void handleNotPass(final long j) {
        if (j > 0) {
            this.mainHandler.post(new Runnable() { // from class: com.megvii.livenesslib.LoginFaceLivenessActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    TextView textView = LoginFaceLivenessActivity.this.timeOutText;
                    textView.setText((j / 1000) + "");
                }
            });
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mHasSurface = true;
        doPreview();
        this.mDetector.setDetectionListener(this);
        this.mICamera.actionDetect(this);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mHasSurface = false;
        return false;
    }

    private void doPreview() {
        if (this.mHasSurface) {
            Log.d("face_doPreview:", "width:" + this.camerapreview.getMeasuredWidth() + ",height:" + this.camerapreview.getMeasuredHeight());
            this.mICamera.startPreview(this.camerapreview.getSurfaceTexture());
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mainHandler.removeCallbacksAndMessages(null);
        this.mICamera.closeCamera();
        this.mCamera = null;
        this.mIMediaPlayer.close();
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Detector detector = this.mDetector;
        if (detector != null) {
            detector.release();
        }
        this.mDialogUtil.onDestory();
        this.mIDetection.onDestroy();
        this.sensorUtil.release();
        FlodDialogManager.hideDialog();
    }
}
