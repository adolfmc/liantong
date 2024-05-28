package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.audience.util.ScreenUtil;
import com.sinovatech.unicom.separatemodule.audience.view.LikeView;
import java.util.Locale;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyGestureView extends FrameLayout implements Handler.Callback {
    private final int DEFAULT_TIME;
    private final int DIALOG_TIME;
    private final int FADE_DIALOG;
    private final int FADE_OUT;
    private final int SEEK_MAX;
    private AudioManager audioManager;
    private LinearLayout container;
    private Activity context;
    private int currentDuration;
    private long doubleEnd;
    private GestureDetector gestureDetector;
    private Handler handler;
    protected boolean isFullScreen;
    private ImageView ivIcon;
    private LikeView.TouchCallBack listener;
    private float mBrightness;
    private boolean mChangeBrightness;
    private boolean mChangePosition;
    private boolean mChangeVolume;
    private boolean mFirstTouch;
    private int mSeekPosition;
    private int nowVolume;
    private ProgressBar pbPercent;
    private TextView tvPercent;
    private WindowManager windowManager;

    public MyGestureView(Context context) {
        super(context);
        this.nowVolume = -1;
        this.isFullScreen = false;
        this.currentDuration = 0;
        this.SEEK_MAX = 100;
        this.DEFAULT_TIME = 5000;
        this.FADE_OUT = 1;
        this.DIALOG_TIME = 1000;
        this.FADE_DIALOG = 2;
        initView(context);
    }

    public MyGestureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.nowVolume = -1;
        this.isFullScreen = false;
        this.currentDuration = 0;
        this.SEEK_MAX = 100;
        this.DEFAULT_TIME = 5000;
        this.FADE_OUT = 1;
        this.DIALOG_TIME = 1000;
        this.FADE_DIALOG = 2;
        initView(context);
    }

    public MyGestureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.nowVolume = -1;
        this.isFullScreen = false;
        this.currentDuration = 0;
        this.SEEK_MAX = 100;
        this.DEFAULT_TIME = 5000;
        this.FADE_OUT = 1;
        this.DIALOG_TIME = 1000;
        this.FADE_DIALOG = 2;
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater.from(context).inflate(2131493143, this);
        this.container = (LinearLayout) findViewById(2131296598);
        this.tvPercent = (TextView) findViewById(2131299044);
        this.ivIcon = (ImageView) findViewById(2131297398);
        this.ivIcon.setTag(0);
        this.pbPercent = (ProgressBar) findViewById(2131298230);
        this.handler = new Handler(this);
        this.pbPercent.setMax(100);
    }

    public void setActivity(Activity activity) {
        this.context = activity;
        this.windowManager = (WindowManager) this.context.getSystemService("window");
        this.audioManager = (AudioManager) this.context.getSystemService("audio");
        this.gestureDetector = new GestureDetector(activity, new VideoPlayGestureListener());
    }

    public void setListener(LikeView.TouchCallBack touchCallBack) {
        this.listener = touchCallBack;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
            case 2:
                this.container.setVisibility(8);
                return false;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class VideoPlayGestureListener extends GestureDetector.SimpleOnGestureListener {

        /* renamed from: fx */
        private float f18487fx;

        /* renamed from: fy */
        private float f18488fy;

        VideoPlayGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (MyGestureView.this.listener != null) {
                MyGestureView.this.doubleEnd = System.currentTimeMillis();
                MyGestureView.this.listener.doubleTapCallback();
                return true;
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (MyGestureView.this.listener != null) {
                MyGestureView.this.listener.onClick(System.currentTimeMillis() - MyGestureView.this.doubleEnd);
                return true;
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            this.f18487fx = motionEvent.getX();
            this.f18488fy = motionEvent.getY();
            MyGestureView myGestureView = MyGestureView.this;
            myGestureView.nowVolume = myGestureView.audioManager.getStreamVolume(3);
            MyGestureView myGestureView2 = MyGestureView.this;
            myGestureView2.mBrightness = myGestureView2.context.getWindow().getAttributes().screenBrightness;
            MyGestureView.this.mFirstTouch = true;
            MyGestureView.this.mChangePosition = false;
            MyGestureView.this.mChangeBrightness = false;
            MyGestureView.this.mChangeVolume = false;
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            float x = motionEvent.getX() - motionEvent2.getX();
            float y = motionEvent.getY() - motionEvent2.getY();
            MyGestureView.this.container.setVisibility(0);
            if (MyGestureView.this.mFirstTouch) {
                if (!MyGestureView.this.mChangePosition) {
                    if (motionEvent2.getX() > Math.max(ScreenUtil.getScreenWidth(), ScreenUtil.getScreenHeight()) / 2) {
                        MyGestureView.this.mChangeVolume = true;
                    } else {
                        MyGestureView.this.mChangeBrightness = true;
                    }
                }
                if (MyGestureView.this.mChangePosition) {
                    MyGestureView.this.mChangePosition = true;
                }
                MyGestureView.this.mFirstTouch = false;
            }
            if (!MyGestureView.this.mChangePosition) {
                if (!MyGestureView.this.mChangeBrightness) {
                    if (MyGestureView.this.mChangeVolume) {
                        MyGestureView.this.slideToChangeVolume(y);
                    }
                } else {
                    MyGestureView.this.slideToChangeBrightness(y);
                }
            } else {
                MyGestureView.this.slideToChangePosition(x);
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }
    }

    private void changeIcon(int i) {
        this.ivIcon.setTag(Integer.valueOf(i));
        switch (i) {
            case 1:
                this.ivIcon.setImageResource(2131231791);
                return;
            case 2:
                this.ivIcon.setImageResource(2131231789);
                return;
            default:
                this.ivIcon.setImageResource(2131231792);
                return;
        }
    }

    protected void slideToChangeVolume(float f) {
        float streamMaxVolume = this.audioManager.getStreamMaxVolume(3);
        float measuredHeight = (((f * 2.0f) / getMeasuredHeight()) * streamMaxVolume) + this.nowVolume;
        if (measuredHeight > streamMaxVolume) {
            measuredHeight = streamMaxVolume;
        }
        if (measuredHeight < 0.0f) {
            measuredHeight = 0.0f;
        }
        int i = (int) ((measuredHeight / streamMaxVolume) * 100.0f);
        this.audioManager.setStreamVolume(3, (int) measuredHeight, 0);
        this.pbPercent.setProgress(i);
        this.tvPercent.setText(i + "%");
        int intValue = ((Integer) this.ivIcon.getTag()).intValue();
        if (measuredHeight == 0.0f) {
            changeIcon(1);
        } else if (intValue != 0) {
            changeIcon(0);
        }
    }

    protected void slideToChangeBrightness(float f) {
        Window window = this.context.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        int measuredHeight = getMeasuredHeight();
        if (this.mBrightness == -1.0f) {
            this.mBrightness = 0.5f;
        }
        float f2 = ((f / measuredHeight) * 1.0f) + this.mBrightness;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        int i = (int) (100.0f * f2);
        attributes.screenBrightness = f2;
        window.setAttributes(attributes);
        this.pbPercent.setProgress(i);
        this.tvPercent.setText(i + "%");
        if (((Integer) this.ivIcon.getTag()).intValue() != 2) {
            changeIcon(2);
        }
    }

    protected void slideToChangePosition(float f) {
        int measuredWidth = (int) ((((-f) / getMeasuredWidth()) * 120000.0f) + 123456);
        if (measuredWidth > 645241) {
            measuredWidth = 645241;
        }
        if (measuredWidth < 0) {
            measuredWidth = 0;
        }
        this.mSeekPosition = measuredWidth;
        this.pbPercent.setProgress((measuredWidth / 645241) * 100);
        this.tvPercent.setText(String.format("%s/%s", stringForTime(measuredWidth), stringForTime(645241)));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        GestureDetector gestureDetector = this.gestureDetector;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 1) {
            if (this.mSeekPosition > 0) {
                this.mSeekPosition = 0;
            }
            this.handler.sendEmptyMessageDelayed(2, 1000L);
        } else if (action == 3) {
            this.mSeekPosition = 0;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public static String stringForTime(int i) {
        int i2 = i / 1000;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / 3600;
        return i5 > 0 ? String.format(Locale.getDefault(), "%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)) : String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3));
    }
}
