package com.dueeeke.videoplayer.player;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.bytedance.applog.tracker.Tracker;
import com.dueeeke.videoplayer.C4202R;
import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.util.NetworkUtil;
import com.dueeeke.videoplayer.util.PlayerConstants;
import com.dueeeke.videoplayer.util.WindowUtil;
import com.dueeeke.videoplayer.widget.ResizeSurfaceView;
import com.dueeeke.videoplayer.widget.ResizeTextureView;
import com.dueeeke.videoplayer.widget.StatusView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IjkVideoView extends BaseIjkVideoView {
    public static final int SCREEN_SCALE_16_9 = 1;
    public static final int SCREEN_SCALE_4_3 = 2;
    public static final int SCREEN_SCALE_DEFAULT = 0;
    public static final int SCREEN_SCALE_MATCH_PARENT = 3;
    public static final int SCREEN_SCALE_ORIGINAL = 4;
    protected boolean isFullScreen;
    protected int mCurrentScreenScale;
    protected SurfaceTexture mSurfaceTexture;
    protected ResizeSurfaceView mSurfaceView;
    protected ResizeTextureView mTextureView;
    protected FrameLayout playerContainer;
    protected StatusView statusView;

    public IjkVideoView(@NonNull Context context) {
        this(context, null);
    }

    public IjkVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IjkVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentScreenScale = 0;
        initView();
    }

    protected void initView() {
        this.playerContainer = new FrameLayout(getContext());
        this.playerContainer.setBackgroundColor(-131587);
        addView(this.playerContainer, new FrameLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    public void initPlayer() {
        super.initPlayer();
        addDisplay();
    }

    protected void addDisplay() {
        if (this.mPlayerConfig.usingSurfaceView) {
            addSurfaceView();
        } else {
            addTextureView();
        }
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    protected void setPlayState(int i) {
        if (this.mVideoController != null) {
            this.mVideoController.setPlayState(i);
        }
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    protected void setPlayerState(int i) {
        if (this.mVideoController != null) {
            this.mVideoController.setPlayerState(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    public void startPlay() {
        if (this.mPlayerConfig.addToPlayerManager) {
            VideoViewManager.instance().releaseVideoPlayer();
            VideoViewManager.instance().setCurrentVideoPlayer(this);
        }
        super.startPlay();
    }

    private void addSurfaceView() {
        this.playerContainer.removeView(this.mSurfaceView);
        this.mSurfaceView = new ResizeSurfaceView(getContext());
        SurfaceHolder holder = this.mSurfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() { // from class: com.dueeeke.videoplayer.player.IjkVideoView.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                if (IjkVideoView.this.mMediaPlayer != null) {
                    IjkVideoView.this.mMediaPlayer.setDisplay(surfaceHolder);
                }
            }
        });
        holder.setFormat(1);
        this.playerContainer.addView(this.mSurfaceView, 0, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private void addTextureView() {
        this.playerContainer.removeView(this.mTextureView);
        this.mSurfaceTexture = null;
        this.mTextureView = new ResizeTextureView(getContext());
        this.mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.dueeeke.videoplayer.player.IjkVideoView.2
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                if (IjkVideoView.this.mSurfaceTexture != null) {
                    IjkVideoView.this.mTextureView.setSurfaceTexture(IjkVideoView.this.mSurfaceTexture);
                    return;
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.mSurfaceTexture = surfaceTexture;
                ijkVideoView.mMediaPlayer.setSurface(new Surface(surfaceTexture));
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return IjkVideoView.this.mSurfaceTexture == null;
            }
        });
        this.playerContainer.addView(this.mTextureView, 0, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    protected boolean checkNetwork() {
        if (NetworkUtil.getNetworkType(getContext()) != 4 || PlayerConstants.IS_PLAY_ON_MOBILE_NETWORK) {
            return false;
        }
        this.playerContainer.removeView(this.statusView);
        if (this.statusView == null) {
            this.statusView = new StatusView(getContext());
        }
        this.statusView.setMessage(getResources().getString(C4202R.string.wifi_tip));
        this.statusView.setButtonTextAndAction(getResources().getString(C4202R.string.continue_play), new View.OnClickListener() { // from class: com.dueeeke.videoplayer.player.IjkVideoView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                PlayerConstants.IS_PLAY_ON_MOBILE_NETWORK = true;
                IjkVideoView.this.playerContainer.removeView(IjkVideoView.this.statusView);
                IjkVideoView.this.initPlayer();
                IjkVideoView.this.startPrepare(true);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.playerContainer.addView(this.statusView);
        return true;
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    public void release() {
        super.release();
        this.playerContainer.removeView(this.mTextureView);
        this.playerContainer.removeView(this.mSurfaceView);
        this.playerContainer.removeView(this.statusView);
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        this.mCurrentScreenScale = 0;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public void startFullScreen() {
        Activity scanForActivity;
        if (this.mVideoController == null || (scanForActivity = WindowUtil.scanForActivity(this.mVideoController.getContext())) == null || this.isFullScreen) {
            return;
        }
        WindowUtil.hideSystemBar(this.mVideoController.getContext());
        removeView(this.playerContainer);
        ((ViewGroup) scanForActivity.findViewById(16908290)).addView(this.playerContainer, new FrameLayout.LayoutParams(-1, -1));
        this.orientationEventListener.enable();
        this.isFullScreen = true;
        this.mVideoController.setPlayerState(11);
        this.mCurrentPlayerState = 11;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public void stopFullScreen() {
        Activity scanForActivity;
        if (this.mVideoController == null || (scanForActivity = WindowUtil.scanForActivity(this.mVideoController.getContext())) == null || !this.isFullScreen) {
            return;
        }
        if (!this.mPlayerConfig.mAutoRotate) {
            this.orientationEventListener.disable();
        }
        WindowUtil.showSystemBar(this.mVideoController.getContext());
        ((ViewGroup) scanForActivity.findViewById(16908290)).removeView(this.playerContainer);
        addView(this.playerContainer, new FrameLayout.LayoutParams(-1, -1));
        this.isFullScreen = false;
        this.mVideoController.setPlayerState(10);
        this.mCurrentPlayerState = 10;
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView, com.dueeeke.videoplayer.listener.MediaPlayerControl
    public boolean isFullScreen() {
        return this.isFullScreen;
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView, com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onPrepared() {
        super.onPrepared();
        if (this.mPlayerConfig.usingAndroidMediaPlayer) {
            this.mMediaPlayer.start();
        }
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView, com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onError() {
        super.onError();
        this.playerContainer.removeView(this.statusView);
        if (this.statusView == null) {
            this.statusView = new StatusView(getContext());
        }
        this.statusView.setMessage(getResources().getString(C4202R.string.error_message));
        this.statusView.setButtonTextAndAction(getResources().getString(C4202R.string.retry), new View.OnClickListener() { // from class: com.dueeeke.videoplayer.player.IjkVideoView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IjkVideoView.this.playerContainer.removeView(IjkVideoView.this.statusView);
                IjkVideoView.this.addDisplay();
                IjkVideoView.this.mMediaPlayer.reset();
                IjkVideoView.this.startPrepare(true);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.playerContainer.addView(this.statusView);
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView, com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onInfo(int i, int i2) {
        ResizeTextureView resizeTextureView;
        super.onInfo(i, i2);
        if (i == 10001 && (resizeTextureView = this.mTextureView) != null) {
            resizeTextureView.setRotation(i2);
        }
    }

    @Override // com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onVideoSizeChanged(int i, int i2) {
        if (this.mPlayerConfig.usingSurfaceView) {
            this.mSurfaceView.setScreenScale(this.mCurrentScreenScale);
            this.mSurfaceView.setVideoSize(i, i2);
            return;
        }
        this.mTextureView.setScreenScale(this.mCurrentScreenScale);
        this.mTextureView.setVideoSize(i, i2);
    }

    public void setVideoController(@Nullable BaseVideoController baseVideoController) {
        this.playerContainer.removeView(this.mVideoController);
        if (baseVideoController != null) {
            baseVideoController.setMediaPlayer(this);
            this.mVideoController = baseVideoController;
            this.playerContainer.addView(this.mVideoController, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    public boolean onBackPressed() {
        return this.mVideoController != null && this.mVideoController.onBackPressed();
    }

    public void setUrl(String str) {
        this.mCurrentUrl = str;
    }

    public void skipPositionWhenPlay(String str, int i) {
        this.mCurrentUrl = str;
        this.mCurrentPosition = i;
    }

    public void setTitle(String str) {
        if (str != null) {
            this.mCurrentTitle = str;
        }
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public void setScreenScale(int i) {
        this.mCurrentScreenScale = i;
        ResizeSurfaceView resizeSurfaceView = this.mSurfaceView;
        if (resizeSurfaceView != null) {
            resizeSurfaceView.setScreenScale(i);
        }
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            resizeTextureView.setScreenScale(i);
        }
    }
}
