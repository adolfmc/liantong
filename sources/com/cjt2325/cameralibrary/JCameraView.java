package com.cjt2325.cameralibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;
import com.bytedance.applog.tracker.Tracker;
import com.cjt2325.cameralibrary.CameraInterface;
import com.cjt2325.cameralibrary.listener.CaptureListener;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.listener.TypeListener;
import com.cjt2325.cameralibrary.state.CameraMachine;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.cjt2325.cameralibrary.util.LogUtil;
import com.cjt2325.cameralibrary.util.ScreenUtils;
import com.cjt2325.cameralibrary.view.CameraView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JCameraView extends FrameLayout implements SurfaceHolder.Callback, CameraInterface.CameraOpenOverCallback, CameraView {
    public static final int BUTTON_STATE_BOTH = 259;
    public static final int BUTTON_STATE_ONLY_CAPTURE = 257;
    public static final int BUTTON_STATE_ONLY_RECORDER = 258;
    public static final int MEDIA_QUALITY_DESPAIR = 200000;
    public static final int MEDIA_QUALITY_FUNNY = 400000;
    public static final int MEDIA_QUALITY_HIGH = 2000000;
    public static final int MEDIA_QUALITY_LOW = 1200000;
    public static final int MEDIA_QUALITY_MIDDLE = 1600000;
    public static final int MEDIA_QUALITY_POOR = 800000;
    public static final int MEDIA_QUALITY_SORRY = 80000;
    public static final int TYPE_DEFAULT = 4;
    private static final int TYPE_FLASH_AUTO = 33;
    private static final int TYPE_FLASH_OFF = 35;
    private static final int TYPE_FLASH_ON = 34;
    public static final int TYPE_PICTURE = 1;
    public static final int TYPE_SHORT = 3;
    public static final int TYPE_VIDEO = 2;
    private Bitmap captureBitmap;
    private int duration;
    private ErrorListener errorLisenter;
    private Bitmap firstFrame;
    private boolean firstTouch;
    private float firstTouchLength;
    private int iconLeft;
    private int iconMargin;
    private int iconRight;
    private int iconSize;
    private int iconSrc;
    private JCameraListener jCameraLisenter;
    private int layout_width;
    private ClickListener leftClickListener;
    private CaptureLayout mCaptureLayout;
    private Context mContext;
    private ImageView mFlashLamp;
    private FoucsView mFoucsView;
    private MediaPlayer mMediaPlayer;
    private ImageView mPhoto;
    private ImageView mSwitchCamera;
    private VideoView mVideoView;
    private CameraMachine machine;
    private ClickListener rightClickListener;
    private float screenProp;
    private int type_flash;
    private String videoUrl;
    private int zoomGradient;

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    static /* synthetic */ int access$008(JCameraView jCameraView) {
        int i = jCameraView.type_flash;
        jCameraView.type_flash = i + 1;
        return i;
    }

    public JCameraView(Context context) {
        this(context, null);
    }

    public JCameraView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JCameraView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.type_flash = 35;
        this.screenProp = 0.0f;
        this.iconSize = 0;
        this.iconMargin = 0;
        this.iconSrc = 0;
        this.iconLeft = 0;
        this.iconRight = 0;
        this.duration = 0;
        this.zoomGradient = 0;
        this.firstTouch = true;
        this.firstTouchLength = 0.0f;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C4156R.styleable.JCameraView, i, 0);
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(C4156R.styleable.JCameraView_iconSize, (int) TypedValue.applyDimension(2, 35.0f, getResources().getDisplayMetrics()));
        this.iconMargin = obtainStyledAttributes.getDimensionPixelSize(C4156R.styleable.JCameraView_iconMargin, (int) TypedValue.applyDimension(2, 15.0f, getResources().getDisplayMetrics()));
        this.iconSrc = obtainStyledAttributes.getResourceId(C4156R.styleable.JCameraView_iconSrc, C4156R.C4158drawable.ic_camera2);
        this.iconLeft = obtainStyledAttributes.getResourceId(C4156R.styleable.JCameraView_iconLeft, 0);
        this.iconRight = obtainStyledAttributes.getResourceId(C4156R.styleable.JCameraView_iconRight, 0);
        this.duration = obtainStyledAttributes.getInteger(C4156R.styleable.JCameraView_duration_max, 10000);
        obtainStyledAttributes.recycle();
        initData();
        initView();
    }

    private void initData() {
        this.layout_width = ScreenUtils.getScreenWidth(this.mContext);
        this.zoomGradient = (int) (this.layout_width / 16.0f);
        LogUtil.m16331i("zoom = " + this.zoomGradient);
        this.machine = new CameraMachine(getContext(), this, this);
    }

    private void initView() {
        setWillNotDraw(false);
        View inflate = LayoutInflater.from(this.mContext).inflate(C4156R.C4160layout.camera_view2, this);
        this.mVideoView = (VideoView) inflate.findViewById(C4156R.C4159id.video_preview);
        this.mPhoto = (ImageView) inflate.findViewById(C4156R.C4159id.image_photo);
        this.mSwitchCamera = (ImageView) inflate.findViewById(C4156R.C4159id.image_switch);
        this.mSwitchCamera.setImageResource(this.iconSrc);
        this.mFlashLamp = (ImageView) inflate.findViewById(C4156R.C4159id.image_flash);
        setFlashRes();
        this.mFlashLamp.setOnClickListener(new View.OnClickListener() { // from class: com.cjt2325.cameralibrary.JCameraView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JCameraView.access$008(JCameraView.this);
                if (JCameraView.this.type_flash > 35) {
                    JCameraView.this.type_flash = 33;
                }
                JCameraView.this.setFlashRes();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mCaptureLayout = (CaptureLayout) inflate.findViewById(C4156R.C4159id.capture_layout);
        this.mCaptureLayout.setDuration(this.duration);
        this.mCaptureLayout.setIconSrc(this.iconLeft, this.iconRight);
        this.mFoucsView = (FoucsView) inflate.findViewById(C4156R.C4159id.fouce_view);
        this.mVideoView.getHolder().addCallback(this);
        this.mSwitchCamera.setOnClickListener(new View.OnClickListener() { // from class: com.cjt2325.cameralibrary.JCameraView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JCameraView.this.machine.swtich(JCameraView.this.mVideoView.getHolder(), JCameraView.this.screenProp);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mCaptureLayout.setCaptureLisenter(new CaptureListener() { // from class: com.cjt2325.cameralibrary.JCameraView.3
            @Override // com.cjt2325.cameralibrary.listener.CaptureListener
            public void takePictures() {
                JCameraView.this.mSwitchCamera.setVisibility(4);
                JCameraView.this.mFlashLamp.setVisibility(4);
                JCameraView.this.machine.capture();
            }

            @Override // com.cjt2325.cameralibrary.listener.CaptureListener
            public void recordStart() {
                JCameraView.this.mSwitchCamera.setVisibility(4);
                JCameraView.this.mFlashLamp.setVisibility(4);
                JCameraView.this.machine.record(JCameraView.this.mVideoView.getHolder().getSurface(), JCameraView.this.screenProp);
            }

            @Override // com.cjt2325.cameralibrary.listener.CaptureListener
            public void recordShort(final long j) {
                JCameraView.this.mCaptureLayout.setTextWithAnimation("录制时间过短");
                JCameraView.this.mSwitchCamera.setVisibility(0);
                JCameraView.this.mFlashLamp.setVisibility(4);
                JCameraView.this.postDelayed(new Runnable() { // from class: com.cjt2325.cameralibrary.JCameraView.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JCameraView.this.machine.stopRecord(true, j);
                    }
                }, 1500 - j);
            }

            @Override // com.cjt2325.cameralibrary.listener.CaptureListener
            public void recordEnd(long j) {
                JCameraView.this.machine.stopRecord(false, j);
            }

            @Override // com.cjt2325.cameralibrary.listener.CaptureListener
            public void recordZoom(float f) {
                LogUtil.m16331i("recordZoom");
                JCameraView.this.machine.zoom(f, 144);
            }

            @Override // com.cjt2325.cameralibrary.listener.CaptureListener
            public void recordError() {
                if (JCameraView.this.errorLisenter != null) {
                    JCameraView.this.errorLisenter.AudioPermissionError();
                }
            }
        });
        this.mCaptureLayout.setTypeLisenter(new TypeListener() { // from class: com.cjt2325.cameralibrary.JCameraView.4
            @Override // com.cjt2325.cameralibrary.listener.TypeListener
            public void cancel() {
                JCameraView.this.machine.cancle(JCameraView.this.mVideoView.getHolder(), JCameraView.this.screenProp);
            }

            @Override // com.cjt2325.cameralibrary.listener.TypeListener
            public void confirm() {
                JCameraView.this.machine.confirm();
            }
        });
        this.mCaptureLayout.setLeftClickListener(new ClickListener() { // from class: com.cjt2325.cameralibrary.JCameraView.5
            @Override // com.cjt2325.cameralibrary.listener.ClickListener
            public void onClick() {
                if (JCameraView.this.leftClickListener != null) {
                    JCameraView.this.leftClickListener.onClick();
                }
            }
        });
        this.mCaptureLayout.setRightClickListener(new ClickListener() { // from class: com.cjt2325.cameralibrary.JCameraView.6
            @Override // com.cjt2325.cameralibrary.listener.ClickListener
            public void onClick() {
                if (JCameraView.this.rightClickListener != null) {
                    JCameraView.this.rightClickListener.onClick();
                }
            }
        });
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        float measuredWidth = this.mVideoView.getMeasuredWidth();
        float measuredHeight = this.mVideoView.getMeasuredHeight();
        if (this.screenProp == 0.0f) {
            this.screenProp = measuredHeight / measuredWidth;
        }
    }

    @Override // com.cjt2325.cameralibrary.CameraInterface.CameraOpenOverCallback
    public void cameraHasOpened() {
        CameraInterface.getInstance().doStartPreview(this.mVideoView.getHolder(), this.screenProp);
    }

    public void onResume() {
        LogUtil.m16331i("JCameraView onResume");
        resetState(4);
        CameraInterface.getInstance().registerSensorManager(this.mContext);
        CameraInterface.getInstance().setSwitchView(this.mSwitchCamera, this.mFlashLamp);
        this.machine.start(this.mVideoView.getHolder(), this.screenProp);
    }

    public void onPause() {
        LogUtil.m16331i("JCameraView onPause");
        stopVideo();
        resetState(1);
        CameraInterface.getInstance().isPreview(false);
        CameraInterface.getInstance().unregisterSensorManager(this.mContext);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.cjt2325.cameralibrary.JCameraView$7] */
    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        LogUtil.m16331i("JCameraView SurfaceCreated");
        new Thread() { // from class: com.cjt2325.cameralibrary.JCameraView.7
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                CameraInterface.getInstance().doOpenCamera(JCameraView.this);
            }
        }.start();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        LogUtil.m16331i("JCameraView SurfaceDestroyed");
        CameraInterface.getInstance().doDestroyCamera();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (motionEvent.getPointerCount() == 1) {
                    setFocusViewWidthAnimation(motionEvent.getX(), motionEvent.getY());
                }
                if (motionEvent.getPointerCount() == 2) {
                    Log.i("CJT", "ACTION_DOWN = 2");
                    break;
                }
                break;
            case 1:
                this.firstTouch = true;
                break;
            case 2:
                if (motionEvent.getPointerCount() == 1) {
                    this.firstTouch = true;
                }
                if (motionEvent.getPointerCount() == 2) {
                    float x = motionEvent.getX(0);
                    float y = motionEvent.getY(0);
                    float sqrt = (float) Math.sqrt(Math.pow(x - motionEvent.getX(1), 2.0d) + Math.pow(y - motionEvent.getY(1), 2.0d));
                    if (this.firstTouch) {
                        this.firstTouchLength = sqrt;
                        this.firstTouch = false;
                    }
                    float f = this.firstTouchLength;
                    if (((int) (sqrt - f)) / this.zoomGradient != 0) {
                        this.firstTouch = true;
                        this.machine.zoom(sqrt - f, CameraInterface.TYPE_CAPTURE);
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private void setFocusViewWidthAnimation(float f, float f2) {
        this.machine.foucs(f, f2, new CameraInterface.FocusCallback() { // from class: com.cjt2325.cameralibrary.JCameraView.8
            @Override // com.cjt2325.cameralibrary.CameraInterface.FocusCallback
            public void focusSuccess() {
                JCameraView.this.mFoucsView.setVisibility(4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVideoViewSize(float f, float f2) {
        if (f > f2) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, (int) ((f2 / f) * getWidth()));
            layoutParams.gravity = 17;
            this.mVideoView.setLayoutParams(layoutParams);
        }
    }

    public void setSaveVideoPath(String str) {
        CameraInterface.getInstance().setSaveVideoPath(str);
    }

    public void setJCameraLisenter(JCameraListener jCameraListener) {
        this.jCameraLisenter = jCameraListener;
    }

    public void setErrorLisenter(ErrorListener errorListener) {
        this.errorLisenter = errorListener;
        CameraInterface.getInstance().setErrorLinsenter(errorListener);
    }

    public void setFeatures(int i) {
        this.mCaptureLayout.setButtonFeatures(i);
    }

    public void setMediaQuality(int i) {
        CameraInterface.getInstance().setMediaQuality(i);
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void resetState(int i) {
        switch (i) {
            case 1:
                this.mPhoto.setVisibility(4);
                break;
            case 2:
                stopVideo();
                FileUtil.deleteFile(this.videoUrl);
                this.mVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                this.machine.start(this.mVideoView.getHolder(), this.screenProp);
                break;
            case 4:
                this.mVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                break;
        }
        this.mSwitchCamera.setVisibility(0);
        this.mFlashLamp.setVisibility(4);
        this.mCaptureLayout.resetCaptureLayout();
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void confirmState(int i) {
        switch (i) {
            case 1:
                this.mPhoto.setVisibility(4);
                JCameraListener jCameraListener = this.jCameraLisenter;
                if (jCameraListener != null) {
                    jCameraListener.captureSuccess(this.captureBitmap);
                    break;
                }
                break;
            case 2:
                stopVideo();
                this.mVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                this.machine.start(this.mVideoView.getHolder(), this.screenProp);
                JCameraListener jCameraListener2 = this.jCameraLisenter;
                if (jCameraListener2 != null) {
                    jCameraListener2.recordSuccess(this.videoUrl, this.firstFrame);
                    break;
                }
                break;
        }
        this.mCaptureLayout.resetCaptureLayout();
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void showPicture(Bitmap bitmap, boolean z) {
        if (z) {
            this.mPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            this.mPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        this.captureBitmap = bitmap;
        this.mPhoto.setImageBitmap(bitmap);
        this.mPhoto.setVisibility(0);
        this.mCaptureLayout.startAlphaAnimation();
        this.mCaptureLayout.startTypeBtnAnimator();
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void playVideo(Bitmap bitmap, final String str) {
        this.videoUrl = str;
        this.firstFrame = bitmap;
        new Thread(new Runnable() { // from class: com.cjt2325.cameralibrary.JCameraView.9
            @Override // java.lang.Runnable
            @RequiresApi(api = 16)
            public void run() {
                try {
                    if (JCameraView.this.mMediaPlayer != null) {
                        JCameraView.this.mMediaPlayer.reset();
                    } else {
                        JCameraView.this.mMediaPlayer = new MediaPlayer();
                    }
                    JCameraView.this.mMediaPlayer.setDataSource(str);
                    JCameraView.this.mMediaPlayer.setSurface(JCameraView.this.mVideoView.getHolder().getSurface());
                    JCameraView.this.mMediaPlayer.setVideoScalingMode(1);
                    JCameraView.this.mMediaPlayer.setAudioStreamType(3);
                    JCameraView.this.mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.cjt2325.cameralibrary.JCameraView.9.1
                        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                            JCameraView.this.updateVideoViewSize(JCameraView.this.mMediaPlayer.getVideoWidth(), JCameraView.this.mMediaPlayer.getVideoHeight());
                        }
                    });
                    JCameraView.this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.cjt2325.cameralibrary.JCameraView.9.2
                        @Override // android.media.MediaPlayer.OnPreparedListener
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            JCameraView.this.mMediaPlayer.start();
                        }
                    });
                    JCameraView.this.mMediaPlayer.setLooping(true);
                    JCameraView.this.mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void stopVideo() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mMediaPlayer.stop();
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void setTip(String str) {
        this.mCaptureLayout.setTip(str);
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void startPreviewCallback() {
        LogUtil.m16331i("startPreviewCallback");
        handlerFoucs(this.mFoucsView.getWidth() / 2, this.mFoucsView.getHeight() / 2);
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public boolean handlerFoucs(float f, float f2) {
        FoucsView foucsView;
        FoucsView foucsView2;
        if (f2 > this.mCaptureLayout.getTop()) {
            return false;
        }
        this.mFoucsView.setVisibility(0);
        if (f < this.mFoucsView.getWidth() / 2) {
            f = this.mFoucsView.getWidth() / 2;
        }
        if (f > this.layout_width - (this.mFoucsView.getWidth() / 2)) {
            f = this.layout_width - (this.mFoucsView.getWidth() / 2);
        }
        if (f2 < this.mFoucsView.getWidth() / 2) {
            f2 = this.mFoucsView.getWidth() / 2;
        }
        if (f2 > this.mCaptureLayout.getTop() - (this.mFoucsView.getWidth() / 2)) {
            f2 = this.mCaptureLayout.getTop() - (this.mFoucsView.getWidth() / 2);
        }
        this.mFoucsView.setX(f - (foucsView.getWidth() / 2));
        this.mFoucsView.setY(f2 - (foucsView2.getHeight() / 2));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mFoucsView, "scaleX", 1.0f, 0.6f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mFoucsView, "scaleY", 1.0f, 0.6f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.mFoucsView, "alpha", 1.0f, 0.4f, 1.0f, 0.4f, 1.0f, 0.4f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).before(ofFloat3);
        animatorSet.setDuration(400L);
        animatorSet.start();
        return true;
    }

    public void setLeftClickListener(ClickListener clickListener) {
        this.leftClickListener = clickListener;
    }

    public void setRightClickListener(ClickListener clickListener) {
        this.rightClickListener = clickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFlashRes() {
        switch (this.type_flash) {
            case 33:
                this.mFlashLamp.setImageResource(C4156R.C4158drawable.ic_flash_auto2);
                this.machine.flash("auto");
                return;
            case 34:
                this.mFlashLamp.setImageResource(C4156R.C4158drawable.ic_flash_on2);
                this.machine.flash("on");
                return;
            case 35:
                this.mFlashLamp.setImageResource(C4156R.C4158drawable.ic_flash_off2);
                this.machine.flash("off");
                return;
            default:
                return;
        }
    }
}
