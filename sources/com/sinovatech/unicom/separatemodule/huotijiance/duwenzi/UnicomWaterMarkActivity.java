package com.sinovatech.unicom.separatemodule.huotijiance.duwenzi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.bytedance.applog.tracker.Tracker;
import com.cjt2325.cameralibrary.util.CameraParamUtil;
import com.dueeeke.videoplayer.listener.VideoListener;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.fort.andjni.JniLib;
import com.megvii.livenesslib.util.FileSizeUtil;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.huotijiance.HuoTiEntity;
import com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil;
import com.sinovatech.unicom.separatemodule.huotijiance.util.AudioRecorder;
import com.sinovatech.unicom.separatemodule.huotijiance.util.FileUtils;
import com.sinovatech.unicom.separatemodule.huotijiance.util.HWRecorderWrapper;
import com.sinovatech.unicom.separatemodule.huotijiance.view.LineProgressView;
import com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomWaterMarkActivity extends BaseActivity implements Camera.PreviewCallback, SurfaceHolder.Callback, AudioRecorder.AudioRecordCallback {
    private static final int DEFAULT_BITRATE = 3000000;
    public static float MAX_VIDEO_TIME = 30000.0f;
    public NBSTraceUnit _nbs_trace;
    private int cameraHeight;
    private int cameraWidth;
    private RelativeLayout center_rl;
    private TextView ht_anzhupai;
    private ImageView ht_bottom_back;
    private TextView ht_daojishi;
    private RelativeLayout ht_luzhi_layout;
    private TextView ht_number;
    private RecordButtonView ht_recordVideo;
    private TextView ht_tishi_text;
    private ImageView ht_wancheng;
    private HuoTiEntity huoTiEntity;
    private Camera mCamera;
    private LineProgressView mCustomProgressView;
    private LinearLayout mRl_WanChengLayout;
    private SurfaceHolder mSurfaceHolder;
    private VideoView mSurfaceView;
    private Camera.Parameters myParameters;
    private long recordTime;
    private CountDownTimer timer2;
    private TextView tv_dTime;
    private IjkVideoView video_preview;
    private String waterText;
    private final String TAG = "UnicomWaterMarkActivity";
    private HWRecorderWrapper mHWrecord = new HWRecorderWrapper();
    private AudioRecorder mAudioRecorder = new AudioRecorder();
    private boolean mRecorderStarted = false;
    private String mFileName = "watervedio.mp4";
    private String mOutPath = "";
    private int cameraID = 1;
    private boolean isView = false;
    private int mSampleRate = 48000;
    private int width = 720;
    private int heigth = 960;
    private int countTime = 0;
    private int mChannels = 1;
    private boolean isBackCamera = true;
    private boolean btnClick = true;
    private int maxTime = 30;
    private int minTime = 1;
    private int frame_rate = 30;
    private Runnable autoFocusRunnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.7
        @Override // java.lang.Runnable
        public void run() {
            if (UnicomWaterMarkActivity.this.mCamera != null) {
                MsLogUtil.m7979d("UnicomWaterMarkActivity", "autofocus");
                UnicomWaterMarkActivity.this.autofocus();
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    private Handler mainHandler = new Handler();
    private CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.8
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            try {
                String valueOf = String.valueOf((int) (j / 1000));
                UnicomWaterMarkActivity.this.tv_dTime.setTextSize(1, 96.0f);
                if ("4".equals(valueOf)) {
                    UnicomWaterMarkActivity.this.tv_dTime.setText("3");
                } else if ("3".equals(valueOf)) {
                    UnicomWaterMarkActivity.this.tv_dTime.setText("2");
                } else if ("2".equals(valueOf)) {
                    UnicomWaterMarkActivity.this.tv_dTime.setText("1");
                } else if ("1".equals(valueOf)) {
                    UnicomWaterMarkActivity.this.tv_dTime.setTextSize(1, 61.0f);
                    UnicomWaterMarkActivity.this.tv_dTime.setText("开始");
                    UnicomWaterMarkActivity.this.startRecord();
                } else {
                    UnicomWaterMarkActivity.this.tv_dTime.setTextSize(1, 96.0f);
                    UnicomWaterMarkActivity.this.tv_dTime.setText("3");
                    UnicomWaterMarkActivity.this.tv_dTime.setVisibility(8);
                }
            } catch (Exception e) {
                MsLogUtil.m7980d(e.getMessage());
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            UnicomWaterMarkActivity.this.btnClick = true;
        }
    };

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 85);
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

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
    }

    private void initView() {
        ((RelativeLayout) findViewById(2131299519)).setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
        this.mSurfaceView = (VideoView) findViewById(2131296566);
        this.video_preview = (IjkVideoView) findViewById(2131299516);
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder.addCallback(this);
        ((ImageView) findViewById(2131297232)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (UnicomWaterMarkActivity.this.mRecorderStarted) {
                    Toast.makeText(UnicomWaterMarkActivity.this, "录制过程中不支持切换摄像头", 0).show();
                } else {
                    UnicomWaterMarkActivity.this.stopPreview();
                    if (UnicomWaterMarkActivity.this.cameraID == 0) {
                        UnicomWaterMarkActivity.this.cameraID = 1;
                    } else {
                        UnicomWaterMarkActivity.this.cameraID = 0;
                    }
                    UnicomWaterMarkActivity.this.initCamera();
                    if (UnicomWaterMarkActivity.this.isBackCamera) {
                        UnicomWaterMarkActivity.this.isBackCamera = false;
                    } else {
                        UnicomWaterMarkActivity.this.isBackCamera = true;
                    }
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((ImageView) findViewById(2131297223)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                UnicomWaterMarkActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.ht_tishi_text = (TextView) findViewById(2131297233);
        this.ht_anzhupai = (TextView) findViewById(2131297222);
        this.tv_dTime = (TextView) findViewById(2131298923);
        this.tv_dTime.setTextSize(1, 96.0f);
        this.tv_dTime.setText("3");
        this.tv_dTime.setVisibility(8);
        this.ht_daojishi = (TextView) findViewById(2131297226);
        this.ht_number = (TextView) findViewById(2131297230);
        this.center_rl = (RelativeLayout) findViewById(2131296602);
        this.ht_number.setText(this.huoTiEntity.getReadNumber());
        this.ht_recordVideo = (RecordButtonView) findViewById(2131297231);
        this.mCustomProgressView = (LineProgressView) findViewById(2131297228);
        this.mRl_WanChengLayout = (LinearLayout) findViewById(2131297235);
        this.ht_luzhi_layout = (RelativeLayout) findViewById(2131297229);
        this.ht_wancheng = (ImageView) findViewById(2131297234);
        this.ht_bottom_back = (ImageView) findViewById(2131297224);
        this.ht_bottom_back.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                UnicomWaterMarkActivity.this.stopPreview();
                UnicomWaterMarkActivity.this.releasePreView();
                UnicomWaterMarkActivity.this.initCamera();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.ht_wancheng.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                UnicomWaterMarkActivity.this.setResult();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.ht_recordVideo.setMaxTime(this.maxTime);
        this.ht_recordVideo.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (UnicomWaterMarkActivity.this.btnClick) {
                    UnicomWaterMarkActivity.this.btnClick = false;
                    if (!UnicomWaterMarkActivity.this.mRecorderStarted) {
                        UnicomWaterMarkActivity.this.mRecorderStarted = false;
                        UnicomWaterMarkActivity.this.tv_dTime.setVisibility(0);
                        UnicomWaterMarkActivity.this.countDownTimer.start();
                    } else {
                        UnicomWaterMarkActivity.this.ht_anzhupai.setVisibility(0);
                        UnicomWaterMarkActivity.this.ht_recordVideo.doStopAnim();
                        if (UnicomWaterMarkActivity.this.mRecorderStarted) {
                            UnicomWaterMarkActivity.this.stopRecord();
                        }
                    }
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public void stopPreview() {
        this.isView = false;
        this.mainHandler.removeCallbacks(this.autoFocusRunnable);
        if (this.mCamera != null) {
            this.mSurfaceHolder.removeCallback(this);
            this.mCamera.setPreviewCallback(null);
            this.mCamera.stopPreview();
            this.mCamera.lock();
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecord() {
        boolean z;
        if (this.mRecorderStarted) {
            return;
        }
        File file = new File(FileUtils.getSaveDir(this), this.mFileName);
        this.mOutPath = new File(FileUtils.getSaveDir(this), this.mFileName).getAbsolutePath();
        if (file.exists()) {
            file.delete();
        }
        Camera camera = this.mCamera;
        if (camera != null) {
            z = this.mHWrecord.init(this.cameraWidth, this.cameraHeight, camera.getParameters().getPreviewFormat(), DEFAULT_BITRATE, this.mSampleRate, this.mChannels, this.mOutPath, this.cameraID == 1, this.frame_rate);
        } else {
            z = false;
        }
        if (z) {
            this.recordTime = System.currentTimeMillis();
            this.mRecorderStarted = true;
            this.mAudioRecorder.start();
            runLoopPro();
            this.ht_anzhupai.setText("停止录制");
            this.ht_anzhupai.setVisibility(8);
            this.ht_daojishi.setVisibility(0);
            this.ht_daojishi.setText("1s");
            this.ht_recordVideo.doStartAnim();
            return;
        }
        MsLogUtil.m7979d("UnicomWaterMarkActivity", "--------------1-----------------------------------------------------------video recorder start faild");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopRecord() {
        if (this.mRecorderStarted) {
            if (this.countTime <= this.minTime) {
                CountDownTimer countDownTimer = this.timer2;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                this.mRecorderStarted = false;
                UIUtils.toast("您的录制时长太短，需要重新录制");
                destoryCamera();
                return;
            }
            this.mAudioRecorder.stop();
            this.mHWrecord.stop();
            this.ht_daojishi.setText("1s");
            this.ht_daojishi.setVisibility(8);
            this.ht_luzhi_layout.setVisibility(8);
            this.mRl_WanChengLayout.setVisibility(0);
            this.ht_recordVideo.doStopAnim();
            this.ht_luzhi_layout.setVisibility(8);
            this.mRl_WanChengLayout.setVisibility(0);
            this.btnClick = true;
            this.ht_anzhupai.setText("点击录制");
            showPreView();
        }
        this.mRecorderStarted = false;
    }

    private void getParams() {
        try {
            this.huoTiEntity = (HuoTiEntity) getIntent().getParcelableExtra("entity");
            if (this.huoTiEntity == null) {
                return;
            }
            this.waterText = this.huoTiEntity.getWater();
            this.maxTime = Integer.parseInt(this.huoTiEntity.getMaxDuration());
            this.minTime = Integer.parseInt(this.huoTiEntity.getMinDuration());
            MAX_VIDEO_TIME = Float.valueOf(this.maxTime).floatValue() * 1000.0f;
            String frame = this.huoTiEntity.getFrame();
            this.frame_rate = TextUtils.isEmpty(frame) ? 30 : Integer.parseInt(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        byte[] addWatermark;
        if (!this.mRecorderStarted || (addWatermark = addWatermark(bArr)) == null) {
            return;
        }
        this.mHWrecord.recordImage(addWatermark);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        initCamera();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mSurfaceHolder = surfaceHolder;
    }

    @Override // com.sinovatech.unicom.separatemodule.huotijiance.util.AudioRecorder.AudioRecordCallback
    public void onRecordSample(byte[] bArr) {
        if (this.mRecorderStarted) {
            this.mHWrecord.recordSample(bArr);
        }
    }

    public int dip2px(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public Bitmap getPriviewPic(byte[] bArr) {
        new BitmapFactory.Options().inJustDecodeBounds = true;
        YuvImage yuvImage = new YuvImage(bArr, 17, this.cameraWidth, this.cameraHeight, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, this.cameraWidth, this.cameraHeight), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inMutable = true;
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    private byte[] addWatermark(byte[] bArr) {
        try {
            Bitmap priviewPic = getPriviewPic(bArr);
            new Canvas(priviewPic).drawBitmap(getBitmap(), 0.0f, 0.0f, (Paint) null);
            return bitmapToNV12(this.cameraWidth, this.cameraHeight, priviewPic);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap getBitmap() {
        Layout.Alignment alignment;
        float f;
        String str = "仅限办理联通业务使用 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + this.waterText;
        Bitmap createBitmap = Bitmap.createBitmap(this.cameraHeight, this.cameraWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setColor(Color.parseColor("#0056FF"));
        paint.setTextSize(dip2px(6.0f));
        int dip2px = dip2px(7.0f);
        int dip2px2 = dip2px(5.0f);
        float measureText = paint.measureText(str);
        TextPaint textPaint = new TextPaint(paint);
        int i = dip2px * 2;
        int i2 = this.cameraHeight - i;
        Layout.Alignment alignment2 = Layout.Alignment.ALIGN_CENTER;
        float f2 = dip2px;
        int i3 = this.cameraHeight;
        if ((i3 - measureText) - i > 0.0f) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
            f = (i3 - measureText) / 2.0f;
        } else {
            alignment = alignment2;
            f = f2;
        }
        StaticLayout staticLayout = new StaticLayout(str, textPaint, i2, alignment, 1.0f, 0.0f, false);
        canvas.save();
        canvas.translate(f, dip2px2);
        staticLayout.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.translate(f, (this.cameraWidth - dip2px2) - staticLayout.getHeight());
        staticLayout.draw(canvas);
        canvas.restore();
        Matrix matrix = new Matrix();
        if (this.cameraID == 1) {
            matrix.postRotate(90.0f, createBitmap.getWidth() / 2.0f, createBitmap.getHeight() / 2.0f);
            matrix.postScale(1.0f, -1.0f, createBitmap.getWidth() / 2.0f, createBitmap.getHeight() / 2.0f);
        } else {
            matrix.setRotate(-90.0f, createBitmap.getWidth() / 2.0f, createBitmap.getHeight() / 2.0f);
        }
        return Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, true);
    }

    private byte[] bitmapToNV12(int i, int i2, Bitmap bitmap) {
        int i3 = i * i2;
        int[] iArr = new int[i3];
        bitmap.getPixels(iArr, 0, i, 0, 0, i, i2);
        byte[] bArr = new byte[(i3 * 3) / 2];
        encodeYUV420SP(bArr, iArr, i, i2);
        bitmap.recycle();
        return bArr;
    }

    public void encodeYUV420SP(byte[] bArr, int[] iArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i2) {
            int i7 = i3;
            int i8 = i5;
            int i9 = 0;
            while (i9 < i) {
                int i10 = i6 + 1;
                int i11 = iArr[i6];
                int i12 = (16711680 & i11) >> 16;
                int i13 = (65280 & i11) >> 8;
                int i14 = i11 & 255;
                int i15 = (((((i12 * (-38)) - (i13 * 74)) + (i14 * 112)) + 128) >> 8) + 128;
                int i16 = (((((i12 * 112) - (i13 * 94)) - (i14 * 18)) + 128) >> 8) + 128;
                int i17 = i8 + 1;
                bArr[i8] = (byte) Math.max(0, Math.min((((((i12 * 66) + (i13 * 129)) + (i14 * 25)) + 128) >> 8) + 16, 255));
                if (i4 % 2 == 0 && i10 % 2 == 0) {
                    int i18 = i7 + 1;
                    bArr[i7] = (byte) Math.max(0, Math.min(i15, 255));
                    i7 = i18 + 1;
                    bArr[i18] = (byte) Math.max(0, Math.min(i16, 255));
                }
                i9++;
                i6 = i10;
                i8 = i17;
            }
            i4++;
            i5 = i8;
            i3 = i7;
        }
    }

    public void initCamera() {
        if (this.mCamera == null && !this.isView) {
            MsLogUtil.m7979d("UnicomWaterMarkActivity", "camera:" + this.cameraID);
            this.mCamera = Camera.open(this.cameraID);
            Log.e("UnicomWaterMarkActivity", "camera OPEN");
        }
        this.mSurfaceView.setVisibility(0);
        this.video_preview.setVisibility(8);
        this.ht_luzhi_layout.setVisibility(0);
        this.mRl_WanChengLayout.setVisibility(8);
        this.mCustomProgressView.setProgress(0.0f);
        this.ht_daojishi.setText("1s");
        this.ht_daojishi.setVisibility(8);
        this.ht_anzhupai.setVisibility(0);
        if (this.mCamera == null || this.isView) {
            return;
        }
        MsLogUtil.m7979d("UnicomWaterMarkActivity", "camera preview");
        try {
            this.myParameters = this.mCamera.getParameters();
            Camera.Size previewSize = CameraParamUtil.getInstance().getPreviewSize(this.myParameters.getSupportedPreviewSizes(), 1000, this.mSurfaceView.getMeasuredHeight() / this.mSurfaceView.getMeasuredWidth());
            this.cameraWidth = previewSize.width;
            this.cameraHeight = previewSize.height;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mSurfaceView.getLayoutParams();
            layoutParams.width = this.mSurfaceView.getMeasuredWidth();
            layoutParams.height = this.mSurfaceView.getMeasuredHeight();
            this.mSurfaceView.setLayoutParams(layoutParams);
            this.myParameters.setPreviewSize(previewSize.width, previewSize.height);
            if (this.myParameters.getSupportedFocusModes().contains("continuous-video")) {
                this.myParameters.setFocusMode("continuous-video");
            }
            this.mCamera.setParameters(this.myParameters);
            this.mCamera.setPreviewDisplay(this.mSurfaceHolder);
            this.mCamera.setDisplayOrientation(90);
            this.mCamera.setPreviewCallback(this);
            this.mCamera.startPreview();
            this.isView = true;
            initRecordData();
            autofocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRecordData() {
        this.mAudioRecorder.setSampleRate(this.mSampleRate);
        this.mAudioRecorder.setRecordCallback(this);
        this.mChannels = this.mAudioRecorder.getChannels();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autofocus() {
        try {
            if (this.mCamera != null) {
                this.mCamera.cancelAutoFocus();
                this.mCamera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.6
                    @Override // android.hardware.Camera.AutoFocusCallback
                    public void onAutoFocus(boolean z, Camera camera) {
                        UnicomWaterMarkActivity.this.mainHandler.postDelayed(UnicomWaterMarkActivity.this.autoFocusRunnable, 2000L);
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResult() {
        stopPreview();
        MsLogUtil.m7979d("UnicomWaterMarkActivity", this.mOutPath);
        successHuoTi(this.mOutPath);
    }

    private void runLoopPro() {
        RxJavaUtil.loop(20L, new RxJavaUtil.OnRxLoopListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.9
            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public void onFinish() {
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public Boolean takeWhile() {
                return Boolean.valueOf(UnicomWaterMarkActivity.this.mRecorderStarted);
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public void onExecute() {
                float currentTimeMillis = (float) (System.currentTimeMillis() - UnicomWaterMarkActivity.this.recordTime);
                if (currentTimeMillis > UnicomWaterMarkActivity.MAX_VIDEO_TIME || !UnicomWaterMarkActivity.this.mRecorderStarted) {
                    UnicomWaterMarkActivity.this.mCustomProgressView.setProgress(0.0f);
                } else {
                    UnicomWaterMarkActivity.this.mCustomProgressView.setProgress(currentTimeMillis / UnicomWaterMarkActivity.MAX_VIDEO_TIME);
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.OnRxLoopListener
            public void onError(Throwable th) {
                th.printStackTrace();
            }
        });
        countDown3();
    }

    private void countDown3() {
        if (this.timer2 == null) {
            this.timer2 = new CountDownTimer((this.maxTime * 1000) + 700, 1000L) { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.10
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                    UnicomWaterMarkActivity unicomWaterMarkActivity = UnicomWaterMarkActivity.this;
                    unicomWaterMarkActivity.countTime = ((int) ((((unicomWaterMarkActivity.maxTime * 1000) + 700) - j) / 1000)) + 1;
                    if (UnicomWaterMarkActivity.this.countTime > UnicomWaterMarkActivity.this.maxTime) {
                        UnicomWaterMarkActivity unicomWaterMarkActivity2 = UnicomWaterMarkActivity.this;
                        unicomWaterMarkActivity2.countTime = unicomWaterMarkActivity2.maxTime;
                    }
                    TextView textView = UnicomWaterMarkActivity.this.ht_daojishi;
                    textView.setText(UnicomWaterMarkActivity.this.countTime + "s");
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    UnicomWaterMarkActivity.this.stopRecord();
                }
            };
        }
        this.timer2.start();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        destoryCamera();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        destoryCamera();
    }

    private void destoryCamera() {
        try {
            if (this.countDownTimer != null) {
                this.countDownTimer.cancel();
            }
            if (this.timer2 != null) {
                this.timer2.cancel();
                this.timer2 = null;
            }
            if (this.video_preview != null) {
                this.video_preview.release();
                this.video_preview = null;
            }
            stopPreview();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void successHuoTi(String str) {
        double fileOrFilesSize = !TextUtils.isEmpty(str) ? FileSizeUtil.getFileOrFilesSize(str, 2) : 0.0d;
        HuoTiEntity huoTiEntity = this.huoTiEntity;
        if (huoTiEntity != null) {
            String url = TextUtils.isEmpty(huoTiEntity.getUrl()) ? "" : this.huoTiEntity.getUrl();
            PvCurrencyLogUtils.pvHuoTiFaceVideoZDLog("录制视频成功", url, fileOrFilesSize + "kb", "成功");
        }
        Intent intent = new Intent();
        intent.putExtra("videoUrl", str);
        intent.putExtra("entity", this.huoTiEntity);
        intent.putExtra("isBackCamera", this.isBackCamera);
        intent.putExtra("videoSize", fileOrFilesSize + "kb");
        setResult(-1, intent);
        finish();
    }

    private void showPreView() {
        this.video_preview.setPlayerConfig(new PlayerConfig.Builder().setLooping().build());
        this.video_preview.setScreenScale(3);
        this.video_preview.setUrl(this.mOutPath);
        this.video_preview.setVideoListener(new VideoListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.11
            @Override // com.dueeeke.videoplayer.listener.VideoListener
            public void onComplete() {
            }

            @Override // com.dueeeke.videoplayer.listener.VideoListener
            public void onError() {
            }

            @Override // com.dueeeke.videoplayer.listener.VideoListener
            public void onInfo(int i, int i2) {
            }

            @Override // com.dueeeke.videoplayer.listener.VideoListener
            public void onPrepared() {
                UnicomWaterMarkActivity.this.video_preview.setVisibility(0);
            }
        });
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.UnicomWaterMarkActivity.12
            @Override // java.lang.Runnable
            public void run() {
                UnicomWaterMarkActivity.this.video_preview.start();
            }
        }, 400L);
        this.mSurfaceView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releasePreView() {
        IjkVideoView ijkVideoView = this.video_preview;
        if (ijkVideoView != null) {
            ijkVideoView.pause();
            this.video_preview.release();
        }
    }
}
