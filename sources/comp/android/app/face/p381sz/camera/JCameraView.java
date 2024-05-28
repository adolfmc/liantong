package comp.android.app.face.p381sz.camera;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
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
import android.widget.LinearLayout;
import android.widget.VideoView;
import com.bytedance.applog.tracker.Tracker;
import com.cjt2325.cameralibrary.CameraInterface;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import comp.android.app.face.p381sz.camera.C11753a;
import comp.android.app.face.p381sz.camera.listener.CaptureListener;
import comp.android.app.face.p381sz.camera.listener.ClickListener;
import comp.android.app.face.p381sz.camera.listener.ErrorListener;
import comp.android.app.face.p381sz.camera.listener.JCameraListener;
import comp.android.app.face.p381sz.camera.listener.OnPreviewCallback;
import comp.android.app.face.p381sz.camera.listener.TypeListener;
import comp.android.app.face.p381sz.camera.p382a.C11764c;
import comp.android.app.face.p381sz.camera.p383b.InterfaceC11777a;
import comp.android.app.face.p381sz.camera.util.C11788e;
import comp.android.app.face.p381sz.camera.util.C11791g;
import comp.android.app.face.p381sz.camera.util.ScreenUtils;
import java.io.IOException;

/* renamed from: comp.android.app.face.sz.camera.JCameraView */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class JCameraView extends FrameLayout implements SurfaceHolder.Callback, C11753a.InterfaceC11757a, InterfaceC11777a {
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
    private Handler autoFocusHandler;
    private C11753a cameraInterface;
    private int camera_direct;
    private byte[] captureBitmap;
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
    private boolean isCustomPhotograph;
    private boolean isOpenCameraSettings;
    private boolean isPlayVideo;
    private boolean isRecord;
    private JCameraListener jCameraLisenter;
    private int layout_width;
    private ClickListener leftClickListener;
    private long lo1;
    private LinearLayout mCameraSettings;
    private CaptureLayout mCaptureLayout;
    private Context mContext;
    private long mDelayMillis;
    private ImageView mFlashLamp;
    private FoucsView mFoucsView;
    private MediaPlayer mMediaPlayer;
    private ImageView mPhoto;
    private ImageView mSwitchCamera;
    private VideoView mVideoView;
    private View mView;
    private C11764c machine;
    private ClickListener rightClickListener;
    private float screenProp;
    private int type_flash;
    private String videoUrl;

    /* renamed from: x */
    private float f23866x;

    /* renamed from: y */
    private float f23867y;
    private int zoomGradient;

    public JCameraView(Context context) {
        this(context, null);
    }

    public JCameraView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JCameraView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.camera_direct = 0;
        this.type_flash = 35;
        this.isOpenCameraSettings = false;
        this.screenProp = 0.0f;
        this.isCustomPhotograph = false;
        this.iconSize = 0;
        this.iconMargin = 0;
        this.iconSrc = 0;
        this.iconLeft = 0;
        this.iconRight = 0;
        this.duration = 0;
        this.zoomGradient = 0;
        this.firstTouch = true;
        this.firstTouchLength = 0.0f;
        this.f23866x = 0.0f;
        this.f23867y = 0.0f;
        this.isPlayVideo = true;
        this.isRecord = false;
        this.mDelayMillis = 0L;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C11746R.styleable.JCameraView, i, 0);
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(C11746R.styleable.JCameraView_iconSize, (int) TypedValue.applyDimension(2, 35.0f, getResources().getDisplayMetrics()));
        this.iconMargin = obtainStyledAttributes.getDimensionPixelSize(C11746R.styleable.JCameraView_iconMargin, (int) TypedValue.applyDimension(2, 15.0f, getResources().getDisplayMetrics()));
        this.iconSrc = obtainStyledAttributes.getResourceId(C11746R.styleable.JCameraView_iconSrc, C11746R.C11748drawable.ic_camera);
        this.iconLeft = obtainStyledAttributes.getResourceId(C11746R.styleable.JCameraView_iconLeft, 0);
        this.iconRight = obtainStyledAttributes.getResourceId(C11746R.styleable.JCameraView_iconRight, 0);
        this.duration = obtainStyledAttributes.getInteger(C11746R.styleable.JCameraView_duration_max, 10000);
        obtainStyledAttributes.recycle();
    }

    static /* synthetic */ int access$1608(JCameraView jCameraView) {
        int i = jCameraView.type_flash;
        jCameraView.type_flash = i + 1;
        return i;
    }

    private void initData() {
        this.layout_width = ScreenUtils.getScreenWidth(this.mContext);
        this.zoomGradient = (int) (this.layout_width / 16.0f);
        C11791g.m2121a("zoom = " + this.zoomGradient);
        this.machine = new C11764c(getContext(), this, this);
    }

    private void initView() {
        setWillNotDraw(false);
        View inflate = LayoutInflater.from(this.mContext).inflate(C11746R.C11750layout.camera_view, this);
        this.mView = inflate.findViewById(C11746R.C11749id.view);
        this.mVideoView = (VideoView) inflate.findViewById(C11746R.C11749id.video_preview);
        this.mCameraSettings = (LinearLayout) inflate.findViewById(C11746R.C11749id.camera_settings);
        this.mPhoto = (ImageView) inflate.findViewById(C11746R.C11749id.image_photo);
        this.mSwitchCamera = (ImageView) inflate.findViewById(C11746R.C11749id.image_switch);
        this.mView.post(new Runnable() { // from class: comp.android.app.face.sz.camera.JCameraView.1
            @Override // java.lang.Runnable
            public void run() {
                JCameraView jCameraView = JCameraView.this;
                jCameraView.f23866x = jCameraView.mView.getLeft();
                JCameraView jCameraView2 = JCameraView.this;
                jCameraView2.f23867y = jCameraView2.mView.getTop();
            }
        });
        this.mCameraSettings.setVisibility(8);
        this.mFlashLamp = (ImageView) inflate.findViewById(C11746R.C11749id.image_flash);
        setFlashRes();
        this.mCaptureLayout = (CaptureLayout) inflate.findViewById(C11746R.C11749id.capture_layout);
        this.mFoucsView = (FoucsView) inflate.findViewById(C11746R.C11749id.fouce_view);
        this.mVideoView.getHolder().addCallback(this);
        this.mCaptureLayout.setVisibility(8);
        this.mCaptureLayout.setDuration(this.duration);
        this.mCaptureLayout.m2249a(this.iconLeft, this.iconRight);
        this.mCaptureLayout.setCaptureLisenter(new CaptureListener() { // from class: comp.android.app.face.sz.camera.JCameraView.4
            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordEnd(long j) {
                JCameraView.this.machine.mo2182a(false, j);
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordError() {
                if (JCameraView.this.errorLisenter != null) {
                    JCameraView.this.errorLisenter.AudioPermissionError();
                }
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordShort(final long j) {
                JCameraView.this.mCaptureLayout.setTextWithAnimation("录制时间过短");
                if (JCameraView.this.isOpenCameraSettings) {
                    JCameraView.this.mSwitchCamera.setVisibility(0);
                    JCameraView.this.mFlashLamp.setVisibility(0);
                }
                JCameraView.this.postDelayed(new Runnable() { // from class: comp.android.app.face.sz.camera.JCameraView.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JCameraView.this.machine.mo2182a(true, j);
                    }
                }, 1500 - j);
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordStart() {
                JCameraView.this.mSwitchCamera.setVisibility(4);
                JCameraView.this.mFlashLamp.setVisibility(4);
                JCameraView.this.machine.mo2185a(JCameraView.this.mVideoView.getHolder().getSurface(), JCameraView.this.screenProp);
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordZoom(float f) {
                C11791g.m2121a("recordZoom");
                JCameraView.this.machine.mo2186a(f, 144);
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void takePictures() {
                JCameraView.this.mSwitchCamera.setVisibility(4);
                JCameraView.this.mFlashLamp.setVisibility(4);
                JCameraView.this.machine.mo2188a();
            }
        });
        this.mCaptureLayout.setTypeLisenter(new TypeListener() { // from class: comp.android.app.face.sz.camera.JCameraView.5
            @Override // comp.android.app.face.p381sz.camera.listener.TypeListener
            public void cancel() {
                JCameraView.this.machine.mo2177c(JCameraView.this.mVideoView.getHolder(), JCameraView.this.screenProp);
                if (JCameraView.this.mDelayMillis > 0) {
                    JCameraView jCameraView = JCameraView.this;
                    jCameraView.setAutoFocus(jCameraView.mDelayMillis);
                }
            }

            @Override // comp.android.app.face.p381sz.camera.listener.TypeListener
            public void confirm() {
                JCameraView.this.machine.mo2174f();
            }
        });
        this.mCaptureLayout.setLeftClickListener(new ClickListener() { // from class: comp.android.app.face.sz.camera.JCameraView.6
            @Override // comp.android.app.face.p381sz.camera.listener.ClickListener
            public void onClick() {
                if (JCameraView.this.leftClickListener != null) {
                    JCameraView.this.leftClickListener.onClick();
                }
            }
        });
        this.mCaptureLayout.setRightClickListener(new ClickListener() { // from class: comp.android.app.face.sz.camera.JCameraView.7
            @Override // comp.android.app.face.p381sz.camera.listener.ClickListener
            public void onClick() {
                if (JCameraView.this.rightClickListener != null) {
                    JCameraView.this.rightClickListener.onClick();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFlashRes() {
        C11764c c11764c;
        String str;
        switch (this.type_flash) {
            case 33:
                this.mFlashLamp.setImageResource(C11746R.C11748drawable.ic_flash_auto);
                c11764c = this.machine;
                str = "auto";
                break;
            case 34:
                this.mFlashLamp.setImageResource(C11746R.C11748drawable.ic_flash_on);
                c11764c = this.machine;
                str = "on";
                break;
            case 35:
                this.mFlashLamp.setImageResource(C11746R.C11748drawable.ic_flash_off);
                c11764c = this.machine;
                str = "off";
                break;
            default:
                return;
        }
        c11764c.mo2183a(str);
    }

    private void setFocusViewWidthAnimation(float f, float f2) {
        this.machine.mo2187a(f, f2, new C11753a.InterfaceC11759c() { // from class: comp.android.app.face.sz.camera.JCameraView.9
            @Override // comp.android.app.face.p381sz.camera.C11753a.InterfaceC11759c
            /* renamed from: a */
            public void mo2238a() {
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

    @Override // comp.android.app.face.p381sz.camera.C11753a.InterfaceC11757a
    public void cameraHasOpened() {
        C11753a c11753a = this.cameraInterface;
        if (c11753a != null) {
            c11753a.m2213b(this.mVideoView.getHolder(), this.screenProp);
        }
    }

    public void capture() {
        if (this.isCustomPhotograph) {
            return;
        }
        this.isCustomPhotograph = true;
        this.machine.mo2188a();
    }

    @Override // comp.android.app.face.p381sz.camera.p383b.InterfaceC11777a
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
                this.machine.mo2184a(this.mVideoView.getHolder(), this.screenProp);
                JCameraListener jCameraListener2 = this.jCameraLisenter;
                if (jCameraListener2 != null) {
                    jCameraListener2.recordSuccess(this.videoUrl, this.firstFrame);
                    break;
                }
                break;
        }
        this.mCaptureLayout.m2245c();
    }

    public Camera.Size getPictureSize() {
        return this.cameraInterface.m2207d();
    }

    public Camera.Size getPreviewSize() {
        return this.cameraInterface.m2209c();
    }

    public Camera.Size getVideoSize() {
        return this.cameraInterface.m2206e();
    }

    @Override // comp.android.app.face.p381sz.camera.p383b.InterfaceC11777a
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

    public void onDestroy() {
        C11753a.m2216b();
        this.cameraInterface = null;
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

    public void onPause() {
        C11791g.m2121a("JCameraView onPause");
        stopVideo();
        Handler handler = this.autoFocusHandler;
        if (handler != null) {
            handler.removeMessages(0);
        }
        this.autoFocusHandler = null;
        resetState(1);
        C11753a c11753a = this.cameraInterface;
        if (c11753a != null) {
            c11753a.m2210b(false);
            this.cameraInterface.m2214b(this.mContext);
        }
    }

    public void onResume() {
        C11791g.m2121a("JCameraView onResume");
        resetState(4);
        C11753a c11753a = this.cameraInterface;
        if (c11753a != null) {
            c11753a.m2231a(this.mContext);
            this.cameraInterface.m2227a(this.mSwitchCamera, this.mFlashLamp);
        }
        this.machine.mo2184a(this.mVideoView.getHolder(), this.screenProp);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
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
                        this.machine.mo2186a(sqrt - f, CameraInterface.TYPE_CAPTURE);
                        break;
                    }
                }
                break;
        }
        return true;
    }

    public void pauseAndResume() {
        this.machine.mo2180b();
    }

    public void pauseRecordFrame() {
        this.machine.mo2175e();
    }

    @Override // comp.android.app.face.p381sz.camera.p383b.InterfaceC11777a
    public void playVideo(Bitmap bitmap, final String str) {
        this.videoUrl = str;
        if (this.isPlayVideo) {
            this.firstFrame = bitmap;
            new Thread(new Runnable() { // from class: comp.android.app.face.sz.camera.JCameraView.3
                @Override // java.lang.Runnable
                @RequiresApi(api = 16)
                public void run() {
                    try {
                        if (JCameraView.this.mMediaPlayer == null) {
                            JCameraView.this.mMediaPlayer = new MediaPlayer();
                        } else {
                            JCameraView.this.mMediaPlayer.reset();
                        }
                        JCameraView.this.mMediaPlayer.setDataSource(str);
                        JCameraView.this.mMediaPlayer.setSurface(JCameraView.this.mVideoView.getHolder().getSurface());
                        JCameraView.this.mMediaPlayer.setVideoScalingMode(1);
                        JCameraView.this.mMediaPlayer.setAudioStreamType(3);
                        JCameraView.this.mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: comp.android.app.face.sz.camera.JCameraView.3.1
                            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                                JCameraView.this.updateVideoViewSize(JCameraView.this.mMediaPlayer.getVideoWidth(), JCameraView.this.mMediaPlayer.getVideoHeight());
                            }
                        });
                        JCameraView.this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: comp.android.app.face.sz.camera.JCameraView.3.2
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
        JCameraListener jCameraListener = this.jCameraLisenter;
        if (jCameraListener != null) {
            jCameraListener.recordSuccess(this.videoUrl, bitmap);
        }
    }

    public void record() {
        if (this.isRecord) {
            return;
        }
        this.isRecord = true;
        this.lo1 = System.currentTimeMillis();
        this.machine.mo2185a(this.mVideoView.getHolder().getSurface(), this.screenProp);
    }

    public void recordFrame() {
        if (this.isRecord) {
            return;
        }
        this.isRecord = true;
        this.machine.mo2178c();
    }

    @Override // comp.android.app.face.p381sz.camera.p383b.InterfaceC11777a
    public void resetState(int i) {
        switch (i) {
            case 1:
                this.mPhoto.setVisibility(4);
                break;
            case 2:
                stopVideo();
                C11788e.m2138a(this.videoUrl);
                this.mVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                this.machine.mo2184a(this.mVideoView.getHolder(), this.screenProp);
                break;
            case 4:
                this.mVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                break;
        }
        if (this.isOpenCameraSettings) {
            this.mSwitchCamera.setVisibility(0);
            this.mFlashLamp.setVisibility(0);
        }
        this.mCaptureLayout.m2245c();
    }

    public void setAutoFocus(long j) {
        this.mDelayMillis = j;
        if (this.autoFocusHandler == null) {
            this.autoFocusHandler = new Handler() { // from class: comp.android.app.face.sz.camera.JCameraView.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    if (JCameraView.this.cameraInterface == null || !JCameraView.this.cameraInterface.m2205f()) {
                        return;
                    }
                    Log.e("setAutoFocus", JCameraView.this.f23866x + "  " + JCameraView.this.f23867y);
                    if (JCameraView.this.machine == null || JCameraView.this.autoFocusHandler == null) {
                        return;
                    }
                    JCameraView.this.cameraInterface.m2230a(JCameraView.this.machine.m2194h(), JCameraView.this.f23866x, JCameraView.this.f23867y, new C11753a.InterfaceC11759c() { // from class: comp.android.app.face.sz.camera.JCameraView.2.1
                        @Override // comp.android.app.face.p381sz.camera.C11753a.InterfaceC11759c
                        /* renamed from: a */
                        public void mo2238a() {
                        }
                    });
                    JCameraView.this.autoFocusHandler.sendEmptyMessageDelayed(0, JCameraView.this.mDelayMillis * 1000);
                }
            };
        }
        this.autoFocusHandler.sendEmptyMessageDelayed(0, this.mDelayMillis * 1000);
    }

    public void setCameraSettings(boolean z) {
        this.isOpenCameraSettings = z;
        if (!z) {
            this.mCameraSettings.setVisibility(8);
            return;
        }
        this.mCameraSettings.setVisibility(0);
        this.mSwitchCamera.setImageResource(this.iconSrc);
        this.mSwitchCamera.setOnClickListener(new View.OnClickListener() { // from class: comp.android.app.face.sz.camera.JCameraView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JCameraView.this.machine.mo2179b(JCameraView.this.mVideoView.getHolder(), JCameraView.this.screenProp);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mFlashLamp.setOnClickListener(new View.OnClickListener() { // from class: comp.android.app.face.sz.camera.JCameraView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JCameraView.access$1608(JCameraView.this);
                if (JCameraView.this.type_flash > 35) {
                    JCameraView.this.type_flash = 33;
                }
                JCameraView.this.setFlashRes();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public void setCaptureLayout(boolean z) {
        CaptureLayout captureLayout;
        int i;
        if (z) {
            captureLayout = this.mCaptureLayout;
            i = 0;
        } else {
            captureLayout = this.mCaptureLayout;
            i = 8;
        }
        captureLayout.setVisibility(i);
    }

    public void setErrorLisenter(ErrorListener errorListener) {
        this.errorLisenter = errorListener;
        C11753a c11753a = this.cameraInterface;
        if (c11753a != null) {
            c11753a.m2222a(errorListener);
        }
    }

    public void setFeatures(int i) {
        this.mCaptureLayout.setButtonFeatures(i);
    }

    public void setJCameraLisenter(JCameraListener jCameraListener) {
        this.jCameraLisenter = jCameraListener;
        this.cameraInterface.m2221a(jCameraListener);
    }

    public void setLeftClickListener(ClickListener clickListener) {
        this.leftClickListener = clickListener;
    }

    public void setMediaQuality(int i) {
        C11753a c11753a = this.cameraInterface;
        if (c11753a != null) {
            c11753a.m2215b(i);
        }
    }

    public void setOnPreviewCallback(OnPreviewCallback onPreviewCallback) {
        C11753a c11753a = this.cameraInterface;
        if (c11753a != null) {
            c11753a.m2220a(onPreviewCallback);
        }
    }

    public void setPlayVideo(boolean z) {
        this.isPlayVideo = z;
        this.cameraInterface.m2218a(this.isPlayVideo);
    }

    public void setRightClickListener(ClickListener clickListener) {
        this.rightClickListener = clickListener;
    }

    public void setSaveVideoPath(String str) {
        this.cameraInterface.m2219a(str);
    }

    public void setTip(String str) {
        this.mCaptureLayout.setTip(str);
    }

    @Override // comp.android.app.face.p381sz.camera.p383b.InterfaceC11777a
    public void showPicture(byte[] bArr, boolean z) {
        ImageView imageView;
        ImageView.ScaleType scaleType;
        if (z) {
            imageView = this.mPhoto;
            scaleType = ImageView.ScaleType.FIT_XY;
        } else {
            imageView = this.mPhoto;
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        imageView.setScaleType(scaleType);
        this.captureBitmap = bArr;
        this.mCaptureLayout.m2243d();
        this.mCaptureLayout.m2247b();
        JCameraListener jCameraListener = this.jCameraLisenter;
        if (jCameraListener == null || !this.isCustomPhotograph) {
            return;
        }
        this.isCustomPhotograph = false;
        jCameraListener.captureSuccess(this.captureBitmap);
    }

    public void start(int i) {
        this.camera_direct = i;
        this.cameraInterface = C11753a.m2233a(i);
        initData();
        initView();
    }

    public void startPreviewCallback() {
        C11791g.m2121a("startPreviewCallback");
        handlerFoucs(this.mFoucsView.getWidth() / 2, this.mFoucsView.getHeight() / 2);
    }

    public void stopRecord() {
        this.isRecord = false;
        this.machine.mo2182a(false, System.currentTimeMillis() - this.lo1);
    }

    public void stopRecordFrame() {
        this.isRecord = false;
        this.machine.mo2176d();
    }

    public void stopVideo() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mMediaPlayer.stop();
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [comp.android.app.face.sz.camera.JCameraView$8] */
    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        C11791g.m2121a("JCameraView SurfaceCreated");
        new Thread() { // from class: comp.android.app.face.sz.camera.JCameraView.8
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (JCameraView.this.cameraInterface != null) {
                    JCameraView.this.cameraInterface.m2226a(JCameraView.this);
                }
            }
        }.start();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        C11791g.m2121a("JCameraView SurfaceDestroyed");
        C11753a c11753a = this.cameraInterface;
        if (c11753a != null) {
            c11753a.m2203h();
        }
    }
}
