package com.sinovatech.unicom.separatemodule.huotijiance.duwenzi;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;
import com.cjt2325.cameralibrary.C4156R;
import com.cjt2325.cameralibrary.CameraInterface;
import com.cjt2325.cameralibrary.FoucsView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.state.CameraMachine;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.cjt2325.cameralibrary.util.LogUtil;
import com.cjt2325.cameralibrary.util.ScreenUtils;
import com.cjt2325.cameralibrary.view.CameraView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyWenZiJCameraView extends FrameLayout implements SurfaceHolder.Callback, CameraInterface.CameraOpenOverCallback, CameraView {
    private static final String TAG = "JCameraView";
    public static final int TYPE_DEFAULT = 4;
    private static final int TYPE_FLASH_AUTO = 33;
    private static final int TYPE_FLASH_OFF = 35;
    private static final int TYPE_FLASH_ON = 34;
    public static final int TYPE_PICTURE = 1;
    public static final int TYPE_SHORT = 3;
    public static final int TYPE_VIDEO = 2;
    private Bitmap captureBitmap;
    private int duration;
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
    private Context mContext;
    private FoucsView mFoucsView;
    private MediaPlayer mMediaPlayer;
    private VideoView mVideoView;
    private CameraMachine machine;
    private ShowPic picListener;
    private PlayVideo playVideo;
    private ClickListener rightClickListener;
    private float screenProp;
    private int type_flash;
    private String videoUrl;
    private int zoomGradient;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface PlayVideo {
        void onPlay(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ShowPic {
        void onPic(Bitmap bitmap, boolean z);
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public boolean handlerFoucs(float f, float f2) {
        return true;
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void setTip(String str) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public MyWenZiJCameraView(Context context) {
        this(context, null);
    }

    public MyWenZiJCameraView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyWenZiJCameraView(Context context, AttributeSet attributeSet, int i) {
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
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(4, (int) TypedValue.applyDimension(2, 35.0f, getResources().getDisplayMetrics()));
        this.iconMargin = obtainStyledAttributes.getDimensionPixelSize(2, (int) TypedValue.applyDimension(2, 15.0f, getResources().getDisplayMetrics()));
        this.iconSrc = obtainStyledAttributes.getResourceId(5, 2131231552);
        this.iconLeft = obtainStyledAttributes.getResourceId(1, 0);
        this.iconRight = obtainStyledAttributes.getResourceId(3, 0);
        this.duration = obtainStyledAttributes.getInteger(0, 10000);
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
        View inflate = LayoutInflater.from(this.mContext).inflate(2131493364, this);
        this.mVideoView = (VideoView) inflate.findViewById(2131299516);
        setFlashRes();
        this.mFoucsView = (FoucsView) inflate.findViewById(2131297027);
        this.mVideoView.getHolder().addCallback(this);
    }

    public void playVideo(long j, boolean z) {
        this.machine.stopRecord(false, j);
        if (z) {
            this.machine.getView().playVideo(this.firstFrame, this.videoUrl);
        }
    }

    public void timeShort(long j) {
        this.machine.stopRecord(true, j);
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
        if (CameraInterface.getInstance().getSELECTED_CAMERA() != 1) {
            switchCamera();
        }
        CameraInterface.getInstance().registerSensorManager(this.mContext);
        this.machine.start(this.mVideoView.getHolder(), this.screenProp);
    }

    public void onPause() {
        LogUtil.m16331i("JCameraView onPause");
        stopVideo();
        Message.obtain().what = 3;
        resetState(1);
        CameraInterface.getInstance().isPreview(false);
        CameraInterface.getInstance().unregisterSensorManager(this.mContext);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.MyWenZiJCameraView$1] */
    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        LogUtil.m16331i("JCameraView SurfaceCreated");
        new Thread() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.MyWenZiJCameraView.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                CameraInterface.getInstance().doOpenCamera(MyWenZiJCameraView.this);
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
        this.machine.foucs(f, f2, new CameraInterface.FocusCallback() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.MyWenZiJCameraView.2
            @Override // com.cjt2325.cameralibrary.CameraInterface.FocusCallback
            public void focusSuccess() {
                MyWenZiJCameraView.this.mFoucsView.setVisibility(4);
            }
        });
    }

    public void setSaveVideoPath(String str) {
        CameraInterface.getInstance().setSaveVideoPath(str);
    }

    public void setMediaQuality(int i) {
        CameraInterface.getInstance().setMediaQuality(i);
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void resetState(int i) {
        switch (i) {
            case 1:
            case 3:
            default:
                return;
            case 2:
                stopVideo();
                FileUtil.deleteFile(this.videoUrl);
                this.mVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                this.machine.start(this.mVideoView.getHolder(), this.screenProp);
                return;
            case 4:
                this.mVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                return;
        }
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void confirmState(int i) {
        switch (i) {
            case 1:
                JCameraListener jCameraListener = this.jCameraLisenter;
                if (jCameraListener != null) {
                    jCameraListener.captureSuccess(this.captureBitmap);
                    return;
                }
                return;
            case 2:
                stopVideo();
                this.mVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                this.machine.start(this.mVideoView.getHolder(), this.screenProp);
                JCameraListener jCameraListener2 = this.jCameraLisenter;
                if (jCameraListener2 != null) {
                    jCameraListener2.recordSuccess(this.videoUrl, this.firstFrame);
                    return;
                }
                return;
            case 3:
            default:
                return;
        }
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void showPicture(Bitmap bitmap, boolean z) {
        ShowPic showPic = this.picListener;
        if (showPic != null) {
            showPic.onPic(bitmap, z);
        }
    }

    public void setPicListener(ShowPic showPic) {
        this.picListener = showPic;
    }

    public void setPlayVideo(PlayVideo playVideo) {
        this.playVideo = playVideo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVideoViewSize(float f, float f2) {
        if (f > f2) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, (int) ((f2 / f) * getWidth()));
            layoutParams.gravity = 17;
            this.mVideoView.setLayoutParams(layoutParams);
        }
    }

    @Override // com.cjt2325.cameralibrary.view.CameraView
    public void playVideo(Bitmap bitmap, String str) {
        this.videoUrl = str;
        initVideoSize();
        PlayVideo playVideo = this.playVideo;
        if (playVideo != null) {
            playVideo.onPlay(str);
        }
        try {
            if (this.mMediaPlayer == null) {
                this.mMediaPlayer = new MediaPlayer();
            } else {
                this.mMediaPlayer.reset();
            }
            this.mMediaPlayer.setDataSource(str);
            this.mMediaPlayer.setSurface(this.mVideoView.getHolder().getSurface());
            this.mMediaPlayer.setVideoScalingMode(1);
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.MyWenZiJCameraView.3
                @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                    MyWenZiJCameraView myWenZiJCameraView = MyWenZiJCameraView.this;
                    myWenZiJCameraView.updateVideoViewSize(myWenZiJCameraView.mMediaPlayer.getVideoWidth(), MyWenZiJCameraView.this.mMediaPlayer.getVideoHeight());
                }
            });
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.MyWenZiJCameraView.4
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer) {
                    MyWenZiJCameraView.this.mMediaPlayer.start();
                }
            });
            this.mMediaPlayer.setLooping(true);
            this.mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "handleMessage: ", e);
        }
        this.firstFrame = bitmap;
    }

    public void takePhoto() {
        this.machine.capture();
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
    public void startPreviewCallback() {
        LogUtil.m16331i("startPreviewCallback");
        handlerFoucs(this.mFoucsView.getWidth() / 2, this.mFoucsView.getHeight() / 2);
    }

    private void setFlashRes() {
        switch (this.type_flash) {
            case 33:
                this.machine.flash("auto");
                return;
            case 34:
                this.machine.flash("on");
                return;
            case 35:
                this.machine.flash("off");
                return;
            default:
                return;
        }
    }

    public void startVideo() {
        this.machine.record(this.mVideoView.getHolder().getSurface(), this.screenProp);
    }

    public void cancel() {
        this.machine.cancle(this.mVideoView.getHolder(), this.screenProp);
    }

    public void switchCamera() {
        this.machine.swtich(this.mVideoView.getHolder(), this.screenProp);
    }

    private void initVideoSize() {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(this.videoUrl);
            float parseInt = (Integer.parseInt(mediaMetadataRetriever.extractMetadata(19)) * 1.0f) / Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            ViewGroup.LayoutParams layoutParams = this.mVideoView.getLayoutParams();
            layoutParams.width = ((Activity) this.mContext).getWindowManager().getDefaultDisplay().getWidth();
            layoutParams.height = (int) (layoutParams.width / parseInt);
            this.mVideoView.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
