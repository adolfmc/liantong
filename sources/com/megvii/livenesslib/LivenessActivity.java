package com.megvii.livenesslib;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
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
import com.megvii.livenesslib.kas.encoder.MediaAudioEncoder;
import com.megvii.livenesslib.kas.encoder.MediaEncoder;
import com.megvii.livenesslib.kas.encoder.MediaMuxerWrapper;
import com.megvii.livenesslib.kas.encoder.MediaVideoEncoder;
import com.megvii.livenesslib.kas.view.CameraGLView;
import com.megvii.livenesslib.util.ConUtil;
import com.megvii.livenesslib.util.Constant;
import com.megvii.livenesslib.util.DialogUtil;
import com.megvii.livenesslib.util.FlodDialogManager;
import com.megvii.livenesslib.util.FoldUtils;
import com.megvii.livenesslib.util.IDetection;
import com.megvii.livenesslib.util.IFile;
import com.megvii.livenesslib.util.IMediaPlayer;
import com.megvii.livenesslib.util.RealAndroidUtils;
import com.megvii.livenesslib.util.Screen;
import com.megvii.livenesslib.util.SensorUtil;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LivenessActivity extends Activity implements Camera.PreviewCallback, Detector.DetectionListener {
    private static final String TAG = "face++";
    public static String faceProCid;
    public static String imageCode;
    public NBSTraceUnit _nbs_trace;
    private FaceEntity faceEntity;
    private String hasImgCode;
    private ImageView headMask;
    private LinearLayout headViewLinear;
    private boolean isHandleStart;
    private ImageView ivFaceBack;
    private JSONObject jsonObject;
    private CameraGLView mCameraView;
    private Detector mDetector;
    private DialogUtil mDialogUtil;
    private FaceMask mFaceMask;
    private FaceQualityManager mFaceQualityManager;
    private IDetection mIDetection;
    private IFile mIFile;
    private IMediaPlayer mIMediaPlayer;
    private MediaMuxerWrapper mMuxer;
    private ProgressBar mProgressBar;
    private String mSession;
    private Handler mainHandler;
    private boolean needNei;
    private boolean needVideo;
    private TextView promptText;
    private RelativeLayout rootView;
    private SensorUtil sensorUtil;
    private LinearLayout timeOutLinear;
    private TextView timeOutText;
    private String videoPath;
    private boolean addCodeSuccess = false;
    private String actionNumber = "";
    private String showNodHead = "";
    private List<byte[]> bytelist = new ArrayList();
    private int otherEnvImgCount = 2;
    private int mFailFrame = 0;
    private int qulityOk = 0;
    private boolean isStartSavePhoto = false;
    private boolean isCustomCheckLive = false;
    private String result = "";
    private Runnable mTimeoutRunnable = new Runnable() { // from class: com.megvii.livenesslib.LivenessActivity.5
        @Override // java.lang.Runnable
        public void run() {
            LivenessActivity.this.initDetecteSession();
            if (LivenessActivity.this.mIDetection.mDetectionSteps != null) {
                LivenessActivity livenessActivity = LivenessActivity.this;
                livenessActivity.changeType(livenessActivity.mIDetection.mDetectionSteps.get(0), 15000L);
            }
        }
    };
    private int mCurStep = 0;
    private final MediaEncoder.MediaEncoderListener mMediaEncoderListener = new MediaEncoder.MediaEncoderListener() { // from class: com.megvii.livenesslib.LivenessActivity.8
        @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder.MediaEncoderListener
        public void onPrepared(MediaEncoder mediaEncoder) {
            if (mediaEncoder instanceof MediaVideoEncoder) {
                LivenessActivity.this.mCameraView.setVideoEncoder((MediaVideoEncoder) mediaEncoder);
            }
        }

        @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder.MediaEncoderListener
        public void onStopped(MediaEncoder mediaEncoder) {
            if (mediaEncoder instanceof MediaVideoEncoder) {
                LivenessActivity.this.mCameraView.setVideoEncoder(null);
            }
        }
    };

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

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(C5351R.C5354layout.liveness_layout);
        Intent intent = getIntent();
        if (intent != null) {
            this.actionNumber = intent.getStringExtra("actionNumber");
            this.showNodHead = intent.getStringExtra("showNodHead");
            this.needVideo = TextUtils.equals("true", intent.getStringExtra("needVideo"));
            this.needNei = TextUtils.equals("true", intent.getStringExtra("needNei"));
            this.faceEntity = (FaceEntity) intent.getParcelableExtra("faceEntity");
            this.isCustomCheckLive = TextUtils.equals("true", intent.getStringExtra("isCustomCheckLive"));
            FaceEntity faceEntity = this.faceEntity;
            if (faceEntity != null) {
                try {
                    this.otherEnvImgCount = Integer.parseInt(faceEntity.getOtherEnvImgCount());
                } catch (Exception unused) {
                    this.otherEnvImgCount = 2;
                }
            }
        }
        init();
        initData();
        updateHeight();
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void updateHeight() {
        try {
            this.headMask.post(new Runnable() { // from class: com.megvii.livenesslib.LivenessActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    int i;
                    try {
                        Screen.initialize(LivenessActivity.this);
                        if (Screen.mScreenWidth / Screen.mScreenHeight > 0.75d) {
                            i = (int) (Screen.mScreenHeight * 0.5d);
                            LivenessActivity.this.headMask.setBackgroundResource(C5351R.C5352drawable.liveness_layout_head_mask_fenping);
                        } else {
                            i = Screen.mScreenWidth;
                            LivenessActivity.this.headMask.setBackgroundResource(C5351R.C5352drawable.liveness_layout_head_mask);
                        }
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) LivenessActivity.this.headMask.getLayoutParams();
                        layoutParams.height = i;
                        LivenessActivity.this.headMask.setLayoutParams(layoutParams);
                    } catch (Exception unused) {
                    }
                    try {
                        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) LivenessActivity.this.mCameraView.getLayoutParams();
                        if (Screen.mScreenWidth / Screen.mScreenHeight > 0.75d) {
                            layoutParams2.width = (int) ((Screen.mScreenHeight / 2) * CameraGLView.biLi);
                            layoutParams2.height = Screen.mScreenHeight / 2;
                            layoutParams2.leftMargin = (int) ((Screen.mScreenWidth - ((Screen.mScreenHeight / 2) * CameraGLView.biLi)) / 2.0d);
                        } else {
                            layoutParams2.width = (int) (Screen.mScreenWidth * CameraGLView.biLi);
                            layoutParams2.height = Screen.mScreenWidth;
                            layoutParams2.leftMargin = (int) ((Screen.mScreenWidth - (Screen.mScreenWidth * CameraGLView.biLi)) / 2.0d);
                        }
                        LivenessActivity.this.mFaceMask.setLayoutParams(layoutParams2);
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
        this.ivFaceBack.setOnClickListener(new View.OnClickListener() { // from class: com.megvii.livenesslib.LivenessActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                LivenessActivity.this.handleResult(C5351R.string.liveness_detection_failed_cancle, false, "");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.rootView = (RelativeLayout) findViewById(C5351R.C5353id.liveness_layout_rootRel);
        this.mIDetection = new IDetection(this, this.rootView, this.actionNumber, this.showNodHead, this.faceEntity);
        this.mFaceMask = (FaceMask) findViewById(C5351R.C5353id.liveness_layout_facemask);
        this.promptText = (TextView) findViewById(C5351R.C5353id.liveness_layout_promptText);
        this.mCameraView = (CameraGLView) findViewById(C5351R.C5353id.liveness_layout_camera_gl_view);
        this.mCameraView.setVideoSize(640, 480);
        this.mCameraView.setPreviewCallback(this);
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
        if (this.needNei) {
            this.result = RealAndroidUtils.getSimulatorMark(this);
        }
        this.videoPath = "";
        DetectionConfig.Builder builder = new DetectionConfig.Builder();
        builder.setDetectionTimeout(15000);
        this.mDetector = new Detector(this, builder.build());
        if (!this.mDetector.init(this, ConUtil.readModel(this), "")) {
            this.mDialogUtil.showDialog("检测器初始化失败");
        }
        new Thread(new Runnable() { // from class: com.megvii.livenesslib.LivenessActivity.3
            @Override // java.lang.Runnable
            public void run() {
                LivenessActivity.this.mIDetection.animationInit();
            }
        }).start();
        this.mDetector.setDetectionListener(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        try {
            this.isHandleStart = false;
            this.mCameraView.onResume();
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(1, cameraInfo);
            this.mFaceMask.setFrontal(cameraInfo.facing == 1);
            this.mFaceQualityManager = new FaceQualityManager(0.5f, 0.5f);
            this.mFaceQualityManager.faceMaxSizeRatioThreshold = 0.5f;
            this.mIDetection.mCurShowIndex = -1;
            updateHeight();
        } catch (Exception e) {
            Log.e("onResume", e.getMessage());
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void handleStart() {
        if (this.isHandleStart) {
            return;
        }
        this.isHandleStart = true;
        startRecording();
        Animation loadAnimation = AnimationUtils.loadAnimation(this, C5351R.anim.liveness_rightin);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, C5351R.anim.liveness_leftout);
        this.headViewLinear.startAnimation(loadAnimation2);
        this.mIDetection.mAnimViews[0].setVisibility(0);
        this.mIDetection.mAnimViews[0].startAnimation(loadAnimation);
        loadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.megvii.livenesslib.LivenessActivity.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                LivenessActivity.this.timeOutLinear.setVisibility(0);
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
        this.mProgressBar.setVisibility(4);
        this.mIDetection.detectionTypeInit();
        this.mCurStep = 0;
        this.mDetector.reset();
        this.mDetector.changeDetectionType(this.mIDetection.mDetectionSteps.get(0));
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        this.mDetector.doDetection(bArr, previewSize.width, previewSize.height, 360 - this.mCameraView.getmRotation());
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00d0 A[Catch: Exception -> 0x01e9, TryCatch #0 {Exception -> 0x01e9, blocks: (B:3:0x0004, B:4:0x0015, B:6:0x001c, B:8:0x0032, B:10:0x0040, B:11:0x004d, B:12:0x0057, B:23:0x00cc, B:25:0x00d0, B:26:0x00dd, B:22:0x00b4, B:27:0x00e8, B:29:0x0107, B:30:0x0123, B:31:0x013d, B:32:0x0143, B:34:0x0149, B:36:0x016c, B:37:0x0188, B:38:0x01a3, B:40:0x01a7, B:42:0x01ab, B:43:0x01bc, B:44:0x01c4, B:46:0x01c8, B:47:0x01dd, B:49:0x01e1, B:15:0x0061, B:17:0x0065, B:19:0x006d, B:20:0x009b), top: B:54:0x0004, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject getBestImageAndDelta(com.megvii.kas.livenessdetection.bean.FaceIDDataStruct r18, com.megvii.kas.livenessdetection.DetectionFrame r19) {
        /*
            Method dump skipped, instructions count: 519
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.livenesslib.LivenessActivity.getBestImageAndDelta(com.megvii.kas.livenessdetection.bean.FaceIDDataStruct, com.megvii.kas.livenessdetection.DetectionFrame):org.json.JSONObject");
    }

    @Override // com.megvii.kas.livenessdetection.Detector.DetectionListener
    public Detector.DetectionType onDetectionSuccess(DetectionFrame detectionFrame) {
        this.mIMediaPlayer.reset();
        this.mCurStep++;
        DetectionFrame detectionFrame2 = null;
        this.mFaceMask.setFaceInfo(null);
        if (this.mCurStep >= this.mIDetection.mDetectionSteps.size()) {
            this.mProgressBar.setVisibility(0);
            try {
                FaceIDDataStruct faceIDDataStruct = this.mDetector.getFaceIDDataStruct();
                ArrayList<DetectionFrame> validFrame = this.mDetector.getValidFrame();
                if (validFrame != null && validFrame.size() > 0) {
                    detectionFrame2 = validFrame.get(0);
                }
                JSONObject bestImageAndDelta = getBestImageAndDelta(faceIDDataStruct, detectionFrame2);
                if (bestImageAndDelta != null && !TextUtils.isEmpty(bestImageAndDelta.optString("image_env")) && !TextUtils.isEmpty(bestImageAndDelta.optString("image_best"))) {
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
        new Thread(new Runnable() { // from class: com.megvii.livenesslib.LivenessActivity.6
            @Override // java.lang.Runnable
            public void run() {
                LivenessActivity.this.mIFile.saveLog(LivenessActivity.this.mSession, detectionFailedType.name());
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
            case MASK:
                i = C5351R.string.liveness_detection_failed_TYPE_MASK;
                break;
            case FACENOTCONTINUOUS:
                i = C5351R.string.liveness_detection_failed_FACE_NOT_CONTINUOUS;
                break;
            case TOOMANYFACELOST:
                i = C5351R.string.liveness_detection_failed_TOO_MANY_FACE_LOST;
                break;
            case FACELOSTNOTCONTINUOUS:
                i = C5351R.string.liveness_detection_failed_FACE_LOST_NOT_CONTINUOUS;
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
            if (this.qulityOk >= this.otherEnvImgCount) {
                handleStart();
            } else {
                this.bytelist.add(detectionFrame.getImageData(new Rect(), false, 75, this.mCameraView.getHeight(), false, false, 0));
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
        Constant.message = str;
        stopRecording();
        String string = getResources().getString(i);
        Intent intent = new Intent();
        if (z && !TextUtils.isEmpty(str)) {
            intent.putExtra("videoPath", this.videoPath);
            intent.putExtra("resultString", string);
            if (this.addCodeSuccess) {
                intent.putExtra("addCodeSuccess", "Y");
            } else {
                intent.putExtra("addCodeSuccess", "N");
            }
            setResult(-1, intent);
        } else if (z && TextUtils.isEmpty(str)) {
            intent.putExtra("resultString", string);
            setResult(0, intent);
            FaceEntity faceEntity = this.faceEntity;
            if (faceEntity == null || TextUtils.equals("true", faceEntity.getShowFailToast())) {
                Toast.makeText(this, string + "", 1).show();
            }
        } else if (!z) {
            intent.putExtra("resultString", string);
            setResult(0, intent);
            FaceEntity faceEntity2 = this.faceEntity;
            if (faceEntity2 == null || TextUtils.equals("true", faceEntity2.getShowFailToast())) {
                Toast.makeText(this, string + "", 1).show();
            }
        } else {
            Log.d(TAG, "handleResult: 不满足任何条件");
        }
        finish();
    }

    public void changeType(Detector.DetectionType detectionType, long j) {
        try {
            this.mIDetection.changeType(detectionType, j);
            this.mFaceMask.setFaceInfo(null);
            if (this.mCurStep == 0) {
                if (this.mIMediaPlayer != null) {
                    this.mIMediaPlayer.doPlay(this.mIMediaPlayer.getSoundRes(detectionType));
                }
            } else if (this.mIMediaPlayer != null) {
                this.mIMediaPlayer.doPlay(C5351R.C5355raw.meglive_well_done);
                this.mIMediaPlayer.setOnCompletionListener(detectionType);
            }
        } catch (Exception e) {
            Log.e(TAG, "changeType: ", e);
        }
    }

    public void handleNotPass(final long j) {
        if (j > 0) {
            this.mainHandler.post(new Runnable() { // from class: com.megvii.livenesslib.LivenessActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    TextView textView = LivenessActivity.this.timeOutText;
                    textView.setText((j / 1000) + "");
                }
            });
        }
    }

    private void startRecording() {
        if (this.needVideo) {
            MediaMuxerWrapper mediaMuxerWrapper = this.mMuxer;
            if (mediaMuxerWrapper != null) {
                mediaMuxerWrapper.stopRecording();
                this.mMuxer = null;
            }
            Log.w("FIL_MESSAGE", "___startRecording()___");
            try {
                this.mMuxer = new MediaMuxerWrapper(".mp4");
                new MediaVideoEncoder(this.mMuxer, this.mMediaEncoderListener, this.mCameraView.getVideoWidth(), this.mCameraView.getVideoHeight());
                new MediaAudioEncoder(this.mMuxer, this.mMediaEncoderListener);
                this.mMuxer.prepare();
                this.mMuxer.startRecording();
            } catch (IOException e) {
                Log.e(TAG, "异常startCapture:", e);
            }
        }
    }

    private void stopRecording() {
        MediaMuxerWrapper mediaMuxerWrapper;
        if (!this.needVideo || (mediaMuxerWrapper = this.mMuxer) == null) {
            return;
        }
        mediaMuxerWrapper.stopRecording();
        this.mMuxer = null;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mCameraView.onPause();
        stopRecording();
        this.mainHandler.removeCallbacksAndMessages(null);
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
