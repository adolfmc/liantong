package com.mabeijianxi.smallvideorecord2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.mabeijianxi.smallvideorecord2.MediaRecorderBase;
import com.mabeijianxi.smallvideorecord2.model.MediaObject;
import com.mabeijianxi.smallvideorecord2.model.MediaRecorderConfig;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.File;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MediaRecorderActivity extends Activity implements View.OnClickListener, MediaRecorderBase.OnEncodeListener, MediaRecorderBase.OnErrorListener, MediaRecorderBase.OnPreparedListener {
    private static final int HANDLE_INVALIDATE_PROGRESS = 0;
    private static final int HANDLE_STOP_RECORD = 1;
    public static final String MEDIA_RECORDER_CONFIG_KEY = "media_recorder_config_key";
    public static final String MEDIA_RECORDER_MAX_TIME_KEY = "media_recorder_max_time_key";
    public static final String MEDIA_RECORDER_MIN_TIME_KEY = "media_recorder_min_time_key";
    public static final String OUTPUT_DIRECTORY = "output_directory";
    public static final String OVER_ACTIVITY_NAME = "over_activity_name";
    public static final String VIDEO_SCREENSHOT = "video_screenshot";
    public static final String VIDEO_URI = "video_uri";
    private boolean GO_HOME;
    private RelativeLayout mBottomLayout;
    private CheckBox mCameraSwitch;
    private MediaObject mMediaObject;
    private MediaRecorderBase mMediaRecorder;
    private volatile boolean mPressedStatus;
    protected ProgressDialog mProgressDialog;
    private ProgressView mProgressView;
    private TextView mRecordController;
    private CheckedTextView mRecordDelete;
    private CheckBox mRecordLed;
    private volatile boolean mReleased;
    private SurfaceView mSurfaceView;
    private ImageView mTitleNext;
    private boolean startState;
    private RelativeLayout title_layout;
    private int RECORD_TIME_MIN = 1500;
    private int RECORD_TIME_MAX = 6000;
    private boolean NEED_FULL_SCREEN = false;
    private View.OnTouchListener mOnVideoControllerTouchListener = new View.OnTouchListener() { // from class: com.mabeijianxi.smallvideorecord2.MediaRecorderActivity.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (MediaRecorderActivity.this.mMediaRecorder == null) {
                return false;
            }
            switch (motionEvent.getAction()) {
                case 0:
                    if (MediaRecorderActivity.this.mMediaObject.getDuration() < MediaRecorderActivity.this.RECORD_TIME_MAX && !MediaRecorderActivity.this.cancelDelete()) {
                        if (!MediaRecorderActivity.this.startState) {
                            MediaRecorderActivity.this.startState = true;
                            MediaRecorderActivity.this.startRecord();
                            break;
                        } else {
                            MediaRecorderActivity.this.mMediaObject.buildMediaPart(MediaRecorderActivity.this.mMediaRecorder.mCameraId);
                            MediaRecorderActivity.this.mProgressView.setData(MediaRecorderActivity.this.mMediaObject);
                            MediaRecorderActivity.this.setStartUI();
                            MediaRecorderActivity.this.mMediaRecorder.setRecordState(true);
                            break;
                        }
                    } else {
                        return true;
                    }
                    break;
                case 1:
                    MediaRecorderActivity.this.mMediaRecorder.setRecordState(false);
                    if (MediaRecorderActivity.this.mMediaObject.getDuration() >= MediaRecorderActivity.this.RECORD_TIME_MAX) {
                        MediaRecorderActivity.this.mTitleNext.performClick();
                        break;
                    } else {
                        MediaRecorderActivity.this.mMediaRecorder.setStopDate();
                        MediaRecorderActivity.this.setStopUI();
                        break;
                    }
            }
            return true;
        }
    };
    private Handler mHandler = new Handler() { // from class: com.mabeijianxi.smallvideorecord2.MediaRecorderActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0 || MediaRecorderActivity.this.mMediaRecorder == null || MediaRecorderActivity.this.isFinishing()) {
                return;
            }
            if (MediaRecorderActivity.this.mMediaObject == null || MediaRecorderActivity.this.mMediaObject.getMedaParts() == null || MediaRecorderActivity.this.mMediaObject.getDuration() < MediaRecorderActivity.this.RECORD_TIME_MAX) {
                if (MediaRecorderActivity.this.mProgressView != null) {
                    MediaRecorderActivity.this.mProgressView.invalidate();
                }
                if (MediaRecorderActivity.this.mPressedStatus) {
                    sendEmptyMessageDelayed(0, 30L);
                    return;
                }
                return;
            }
            MediaRecorderActivity.this.mTitleNext.performClick();
        }
    };

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase.OnErrorListener
    public void onAudioError(int i, String str) {
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase.OnEncodeListener
    public void onEncodeProgress(int i) {
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase.OnErrorListener
    public void onVideoError(int i, int i2) {
    }

    public static void goSmallVideoRecorder(Activity activity, String str, MediaRecorderConfig mediaRecorderConfig) {
        activity.startActivity(new Intent(activity, MediaRecorderActivity.class).putExtra(OVER_ACTIVITY_NAME, str).putExtra(MEDIA_RECORDER_CONFIG_KEY, mediaRecorderConfig));
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        initData();
        loadViews();
    }

    private void initData() {
        MediaRecorderConfig mediaRecorderConfig = (MediaRecorderConfig) getIntent().getParcelableExtra(MEDIA_RECORDER_CONFIG_KEY);
        if (mediaRecorderConfig == null) {
            return;
        }
        this.NEED_FULL_SCREEN = mediaRecorderConfig.getFullScreen();
        this.RECORD_TIME_MAX = mediaRecorderConfig.getRecordTimeMax();
        this.RECORD_TIME_MIN = mediaRecorderConfig.getRecordTimeMin();
        MediaRecorderBase.MAX_FRAME_RATE = mediaRecorderConfig.getMaxFrameRate();
        MediaRecorderBase.NEED_FULL_SCREEN = this.NEED_FULL_SCREEN;
        MediaRecorderBase.MIN_FRAME_RATE = mediaRecorderConfig.getMinFrameRate();
        MediaRecorderBase.SMALL_VIDEO_HEIGHT = mediaRecorderConfig.getSmallVideoHeight();
        MediaRecorderBase.SMALL_VIDEO_WIDTH = mediaRecorderConfig.getSmallVideoWidth();
        MediaRecorderBase.mVideoBitrate = mediaRecorderConfig.getVideoBitrate();
        MediaRecorderBase.CAPTURE_THUMBNAILS_TIME = mediaRecorderConfig.getCaptureThumbnailsTime();
        this.GO_HOME = mediaRecorderConfig.isGO_HOME();
    }

    private void loadViews() {
        setContentView(C5263R.C5267layout.activity_media_recorder);
        this.mSurfaceView = (SurfaceView) findViewById(C5263R.C5266id.record_preview);
        this.title_layout = (RelativeLayout) findViewById(C5263R.C5266id.title_layout);
        this.mCameraSwitch = (CheckBox) findViewById(C5263R.C5266id.record_camera_switcher);
        this.mTitleNext = (ImageView) findViewById(C5263R.C5266id.title_next);
        this.mProgressView = (ProgressView) findViewById(C5263R.C5266id.record_progress);
        this.mRecordDelete = (CheckedTextView) findViewById(C5263R.C5266id.record_delete);
        this.mRecordController = (TextView) findViewById(C5263R.C5266id.record_controller);
        this.mBottomLayout = (RelativeLayout) findViewById(C5263R.C5266id.bottom_layout);
        this.mRecordLed = (CheckBox) findViewById(C5263R.C5266id.record_camera_led);
        this.mTitleNext.setOnClickListener(this);
        findViewById(C5263R.C5266id.title_back).setOnClickListener(this);
        this.mRecordController.setOnTouchListener(this.mOnVideoControllerTouchListener);
        if (MediaRecorderBase.isSupportFrontCamera()) {
            this.mCameraSwitch.setOnClickListener(this);
        } else {
            this.mCameraSwitch.setVisibility(8);
        }
        if (DeviceUtils.isSupportCameraLedFlash(getPackageManager())) {
            this.mRecordLed.setOnClickListener(this);
        } else {
            this.mRecordLed.setVisibility(8);
        }
        this.mProgressView.setMaxDuration(this.RECORD_TIME_MAX);
        this.mProgressView.setMinTime(this.RECORD_TIME_MIN);
    }

    private void initSurfaceView() {
        if (this.NEED_FULL_SCREEN) {
            this.mBottomLayout.setBackgroundColor(0);
            this.title_layout.setBackgroundColor(getResources().getColor(C5263R.C5264color.full_title_color));
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mSurfaceView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            this.mSurfaceView.setLayoutParams(layoutParams);
            this.mProgressView.setBackgroundColor(getResources().getColor(C5263R.C5264color.full_progress_color));
            return;
        }
        int screenWidth = DeviceUtils.getScreenWidth(this);
        float f = screenWidth;
        ((RelativeLayout.LayoutParams) this.mBottomLayout.getLayoutParams()).topMargin = (int) (f / (MediaRecorderBase.SMALL_VIDEO_HEIGHT / (MediaRecorderBase.SMALL_VIDEO_WIDTH * 1.0f)));
        int i = (int) (f * ((MediaRecorderBase.mSupportedPreviewWidth * 1.0f) / MediaRecorderBase.SMALL_VIDEO_HEIGHT));
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mSurfaceView.getLayoutParams();
        layoutParams2.width = screenWidth;
        layoutParams2.height = i;
        this.mSurfaceView.setLayoutParams(layoutParams2);
    }

    private void initMediaRecorder() {
        this.mMediaRecorder = new MediaRecorderNative();
        this.mMediaRecorder.setOnErrorListener(this);
        this.mMediaRecorder.setOnEncodeListener(this);
        this.mMediaRecorder.setOnPreparedListener(this);
        File file = new File(JianXiCamera.getVideoCachePath());
        if (!FileUtils.checkFile(file)) {
            file.mkdirs();
        }
        String valueOf = String.valueOf(System.currentTimeMillis());
        MediaRecorderBase mediaRecorderBase = this.mMediaRecorder;
        this.mMediaObject = mediaRecorderBase.setOutputDirectory(valueOf, JianXiCamera.getVideoCachePath() + valueOf);
        this.mMediaRecorder.setSurfaceHolder(this.mSurfaceView.getHolder());
        this.mMediaRecorder.prepare();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mMediaRecorder == null) {
            initMediaRecorder();
            return;
        }
        this.mRecordLed.setChecked(false);
        this.mMediaRecorder.prepare();
        this.mProgressView.setData(this.mMediaObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecord() {
        MediaRecorderBase mediaRecorderBase = this.mMediaRecorder;
        if (mediaRecorderBase != null) {
            if (mediaRecorderBase.startRecord() == null) {
                return;
            }
            this.mProgressView.setData(this.mMediaObject);
        }
        setStartUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartUI() {
        this.mPressedStatus = true;
        this.mRecordController.animate().scaleX(0.8f).scaleY(0.8f).setDuration(500L).start();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(0);
            this.mHandler.sendEmptyMessage(0);
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, this.RECORD_TIME_MAX - this.mMediaObject.getDuration());
        }
        this.mCameraSwitch.setEnabled(false);
        this.mRecordLed.setEnabled(false);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        MediaObject mediaObject = this.mMediaObject;
        if (mediaObject != null && mediaObject.getDuration() > 1) {
            new AlertDialog.Builder(this).setTitle(C5263R.string.hint).setMessage(C5263R.string.record_camera_exit_dialog_message).setNegativeButton(C5263R.string.record_camera_cancel_dialog_yes, new DialogInterface.OnClickListener() { // from class: com.mabeijianxi.smallvideorecord2.MediaRecorderActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    Tracker.onClick(dialogInterface, i);
                    MediaRecorderActivity.this.mMediaObject.delete();
                    MediaRecorderActivity.this.finish();
                }
            }).setPositiveButton(C5263R.string.record_camera_cancel_dialog_no, (DialogInterface.OnClickListener) null).setCancelable(false).show();
            return;
        }
        MediaObject mediaObject2 = this.mMediaObject;
        if (mediaObject2 != null) {
            mediaObject2.delete();
        }
        finish();
    }

    private void stopRecord() {
        MediaRecorderBase mediaRecorderBase = this.mMediaRecorder;
        if (mediaRecorderBase != null) {
            mediaRecorderBase.stopRecord();
        }
        setStopUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStopUI() {
        this.mPressedStatus = false;
        this.mRecordController.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500L).start();
        this.mCameraSwitch.setEnabled(true);
        this.mRecordLed.setEnabled(true);
        this.mHandler.removeMessages(1);
        checkStatus();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MediaObject mediaObject;
        MediaObject mediaObject2;
        MediaObject.MediaPart currentPart;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (this.mHandler.hasMessages(1)) {
            this.mHandler.removeMessages(1);
        }
        if (id != C5263R.C5266id.record_delete && (mediaObject2 = this.mMediaObject) != null && (currentPart = mediaObject2.getCurrentPart()) != null && currentPart.remove) {
            currentPart.remove = false;
            ProgressView progressView = this.mProgressView;
            if (progressView != null) {
                progressView.invalidate();
            }
        }
        if (id == C5263R.C5266id.title_back) {
            onBackPressed();
        } else if (id == C5263R.C5266id.record_camera_switcher) {
            if (this.mRecordLed.isChecked()) {
                MediaRecorderBase mediaRecorderBase = this.mMediaRecorder;
                if (mediaRecorderBase != null) {
                    mediaRecorderBase.toggleFlashMode();
                }
                this.mRecordLed.setChecked(false);
            }
            MediaRecorderBase mediaRecorderBase2 = this.mMediaRecorder;
            if (mediaRecorderBase2 != null) {
                mediaRecorderBase2.switchCamera();
            }
            if (this.mMediaRecorder.isFrontCamera()) {
                this.mRecordLed.setEnabled(false);
            } else {
                this.mRecordLed.setEnabled(true);
            }
        } else if (id == C5263R.C5266id.record_camera_led) {
            MediaRecorderBase mediaRecorderBase3 = this.mMediaRecorder;
            if (mediaRecorderBase3 != null && mediaRecorderBase3.isFrontCamera()) {
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
            MediaRecorderBase mediaRecorderBase4 = this.mMediaRecorder;
            if (mediaRecorderBase4 != null) {
                mediaRecorderBase4.toggleFlashMode();
            }
        } else if (id == C5263R.C5266id.title_next) {
            stopRecord();
        } else if (id == C5263R.C5266id.record_delete && (mediaObject = this.mMediaObject) != null) {
            MediaObject.MediaPart currentPart2 = mediaObject.getCurrentPart();
            if (currentPart2 != null) {
                if (currentPart2.remove) {
                    currentPart2.remove = false;
                    this.mMediaObject.removePart(currentPart2, true);
                } else {
                    currentPart2.remove = true;
                }
            }
            ProgressView progressView2 = this.mProgressView;
            if (progressView2 != null) {
                progressView2.invalidate();
            }
            checkStatus();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean cancelDelete() {
        MediaObject.MediaPart currentPart;
        MediaObject mediaObject = this.mMediaObject;
        if (mediaObject == null || (currentPart = mediaObject.getCurrentPart()) == null || !currentPart.remove) {
            return false;
        }
        currentPart.remove = false;
        ProgressView progressView = this.mProgressView;
        if (progressView != null) {
            progressView.invalidate();
            return true;
        }
        return true;
    }

    private int checkStatus() {
        MediaObject mediaObject;
        if (!isFinishing() && (mediaObject = this.mMediaObject) != null) {
            int duration = mediaObject.getDuration();
            if (duration < this.RECORD_TIME_MIN) {
                if (duration == 0) {
                    this.mCameraSwitch.setVisibility(0);
                } else {
                    this.mCameraSwitch.setVisibility(8);
                }
                if (this.mTitleNext.getVisibility() != 4) {
                    this.mTitleNext.setVisibility(4);
                }
            } else if (this.mTitleNext.getVisibility() != 0) {
                this.mTitleNext.setVisibility(0);
            }
        }
        return 0;
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase.OnEncodeListener
    public void onEncodeStart() {
        showProgress("", getString(C5263R.string.record_camera_progress_message));
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase.OnEncodeListener
    public void onEncodeComplete() {
        hideProgress();
        try {
            Intent intent = new Intent(this, Class.forName(getIntent().getStringExtra(OVER_ACTIVITY_NAME)));
            intent.putExtra(OUTPUT_DIRECTORY, this.mMediaObject.getOutputDirectory());
            intent.putExtra(VIDEO_URI, this.mMediaObject.getOutputTempTranscodingVideoPath());
            intent.putExtra(VIDEO_SCREENSHOT, this.mMediaObject.getOutputVideoThumbPath());
            intent.putExtra("go_home", this.GO_HOME);
            startActivity(intent);
            finish();
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException("需要传入录制完成后跳转的Activity的全类名");
        }
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase.OnEncodeListener
    public void onEncodeError() {
        hideProgress();
        Toast.makeText(this, C5263R.string.record_video_transcoding_faild, 0).show();
        finish();
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase.OnPreparedListener
    public void onPrepared() {
        initSurfaceView();
    }

    public void onFinished() {
        finish();
    }

    public ProgressDialog showProgress(String str, String str2) {
        return showProgress(str, str2, -1);
    }

    public ProgressDialog showProgress(String str, String str2, int i) {
        if (this.mProgressDialog == null) {
            if (i > 0) {
                this.mProgressDialog = new ProgressDialog(this, i);
            } else {
                this.mProgressDialog = new ProgressDialog(this);
            }
            this.mProgressDialog.setProgressStyle(0);
            this.mProgressDialog.requestWindowFeature(1);
            this.mProgressDialog.setCanceledOnTouchOutside(false);
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.setIndeterminate(true);
        }
        if (!StringUtils.isEmpty(str)) {
            this.mProgressDialog.setTitle(str);
        }
        this.mProgressDialog.setMessage(str2);
        this.mProgressDialog.show();
        return this.mProgressDialog;
    }

    public void hideProgress() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mMediaRecorder.release();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        MediaRecorderBase mediaRecorderBase = this.mMediaRecorder;
        if (mediaRecorderBase instanceof MediaRecorderNative) {
            ((MediaRecorderNative) mediaRecorderBase).activityStop();
        }
        hideProgress();
        this.mProgressDialog = null;
    }
}
