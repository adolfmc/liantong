package com.dueeeke.videoplayer.controller;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.dueeeke.videoplayer.listener.ControllerListener;
import com.dueeeke.videoplayer.listener.MediaPlayerControl;
import com.dueeeke.videoplayer.util.WindowUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class BaseVideoController extends FrameLayout {
    protected View controllerView;
    protected int currentPlayState;
    protected boolean isLocked;
    protected ControllerListener listener;
    protected final Runnable mFadeOut;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    protected Runnable mShowProgress;
    protected boolean mShowing;
    protected MediaPlayerControl mediaPlayer;
    protected int sDefaultTimeout;

    protected abstract int getLayoutId();

    public void hide() {
    }

    public boolean onBackPressed() {
        return false;
    }

    public void setPlayerState(int i) {
    }

    protected int setProgress() {
        return 0;
    }

    public void show() {
    }

    public BaseVideoController(@NonNull Context context) {
        this(context, null);
    }

    public BaseVideoController(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseVideoController(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        this.sDefaultTimeout = 3000;
        this.mShowProgress = new Runnable() { // from class: com.dueeeke.videoplayer.controller.BaseVideoController.1
            @Override // java.lang.Runnable
            public void run() {
                int progress = BaseVideoController.this.setProgress();
                if (BaseVideoController.this.mediaPlayer.isPlaying()) {
                    BaseVideoController baseVideoController = BaseVideoController.this;
                    baseVideoController.postDelayed(baseVideoController.mShowProgress, 1000 - (progress % 1000));
                }
            }
        };
        this.mFadeOut = new Runnable() { // from class: com.dueeeke.videoplayer.controller.BaseVideoController.2
            @Override // java.lang.Runnable
            public void run() {
                BaseVideoController.this.hide();
            }
        };
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initView() {
        this.controllerView = LayoutInflater.from(getContext()).inflate(getLayoutId(), this);
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        setClickable(true);
        setFocusable(true);
    }

    public void setPlayState(int i) {
        this.currentPlayState = i;
    }

    public void setControllerListener(ControllerListener controllerListener) {
        this.listener = controllerListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doPauseResume() {
        if (this.currentPlayState == 6) {
            return;
        }
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
        } else {
            this.mediaPlayer.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doStartStopFullScreen() {
        if (this.mediaPlayer.isFullScreen()) {
            WindowUtil.scanForActivity(getContext()).setRequestedOrientation(1);
            this.mediaPlayer.stopFullScreen();
            return;
        }
        WindowUtil.scanForActivity(getContext()).setRequestedOrientation(0);
        this.mediaPlayer.startFullScreen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCurrentSystemTime() {
        return new SimpleDateFormat(JtDateUtil.dateFormatHM, Locale.CHINA).format(new Date());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String stringForTime(int i) {
        int i2 = i / 1000;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / 3600;
        this.mFormatBuilder.setLength(0);
        return i5 > 0 ? this.mFormatter.format("%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)).toString() : this.mFormatter.format("%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3)).toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(this.mShowProgress);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            post(this.mShowProgress);
        }
    }

    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        this.mediaPlayer = mediaPlayerControl;
    }
}
