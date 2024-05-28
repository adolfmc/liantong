package com.dueeeke.videoplayer.controller;

import android.content.IntentFilter;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.dueeeke.videoplayer.C4202R;
import com.dueeeke.videoplayer.util.BatteryReceiver;
import com.dueeeke.videoplayer.util.C4233L;
import com.dueeeke.videoplayer.util.WindowUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TMSVideoController extends GestureVideoController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private AppCompatActivity activityContext;
    protected ImageView backButton;
    private View.OnClickListener backListener;
    private ImageView batteryLevel;
    protected LinearLayout bottomContainer;
    private ProgressBar bottomProgress;
    private LinearLayout completeContainer;
    protected TextView currTime;
    protected ImageView fullScreenButton;
    private Animation hideAnim;
    protected TextView ijkControlSize;
    protected TextView ijkTitle;
    private boolean isDragging;
    private boolean isLive;
    private ProgressBar loadingProgress;
    protected ImageView lock;
    private BatteryReceiver mBatteryReceiver;
    private ImageView playButton;
    private Animation showAnim;
    private ImageView startPlayButton;
    private TextView sysTime;
    private ImageView thumb;
    protected TextView title;
    protected LinearLayout topContainer;
    protected TextView totalTime;
    protected SeekBar videoProgress;
    private View viewLayer;

    public TMSVideoController(@NonNull AppCompatActivity appCompatActivity) {
        this(appCompatActivity, null);
        this.activityContext = appCompatActivity;
    }

    public TMSVideoController(@NonNull AppCompatActivity appCompatActivity, @Nullable AttributeSet attributeSet) {
        this(appCompatActivity, attributeSet, 0);
        this.activityContext = appCompatActivity;
    }

    public TMSVideoController(@NonNull AppCompatActivity appCompatActivity, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(appCompatActivity, attributeSet, i);
        this.showAnim = AnimationUtils.loadAnimation(getContext(), C4202R.anim.anim_alpha_in);
        this.hideAnim = AnimationUtils.loadAnimation(getContext(), C4202R.anim.anim_alpha_out);
        this.activityContext = appCompatActivity;
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    protected int getLayoutId() {
        return C4202R.C4206layout.layout_tmsvideo_controller;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dueeeke.videoplayer.controller.GestureVideoController, com.dueeeke.videoplayer.controller.BaseVideoController
    public void initView() {
        super.initView();
        this.fullScreenButton = (ImageView) this.controllerView.findViewById(C4202R.C4205id.fullscreen);
        this.fullScreenButton.setVisibility(8);
        this.ijkTitle = (TextView) this.controllerView.findViewById(C4202R.C4205id.ijk_controls_title);
        this.ijkControlSize = (TextView) this.controllerView.findViewById(C4202R.C4205id.ijk_controls_size);
        this.viewLayer = this.controllerView.findViewById(C4202R.C4205id.view_layer);
        this.fullScreenButton.setOnClickListener(this);
        this.bottomContainer = (LinearLayout) this.controllerView.findViewById(C4202R.C4205id.bottom_container);
        this.topContainer = (LinearLayout) this.controllerView.findViewById(C4202R.C4205id.top_container);
        this.videoProgress = (SeekBar) this.controllerView.findViewById(C4202R.C4205id.seekBar);
        this.videoProgress.setOnSeekBarChangeListener(this);
        this.totalTime = (TextView) this.controllerView.findViewById(C4202R.C4205id.total_time);
        this.currTime = (TextView) this.controllerView.findViewById(C4202R.C4205id.curr_time);
        this.backButton = (ImageView) this.controllerView.findViewById(C4202R.C4205id.back);
        this.backButton.setOnClickListener(this);
        this.lock = (ImageView) this.controllerView.findViewById(C4202R.C4205id.lock);
        this.lock.setOnClickListener(this);
        this.thumb = (ImageView) this.controllerView.findViewById(C4202R.C4205id.thumb);
        this.thumb.setOnClickListener(this);
        this.playButton = (ImageView) this.controllerView.findViewById(C4202R.C4205id.iv_play);
        this.playButton.setOnClickListener(this);
        this.startPlayButton = (ImageView) this.controllerView.findViewById(C4202R.C4205id.start_play);
        this.loadingProgress = (ProgressBar) this.controllerView.findViewById(C4202R.C4205id.loading);
        this.bottomProgress = (ProgressBar) this.controllerView.findViewById(C4202R.C4205id.bottom_progress);
        ((ImageView) this.controllerView.findViewById(C4202R.C4205id.iv_replay)).setOnClickListener(this);
        this.completeContainer = (LinearLayout) this.controllerView.findViewById(C4202R.C4205id.complete_container);
        this.completeContainer.setOnClickListener(this);
        this.title = (TextView) this.controllerView.findViewById(C4202R.C4205id.title);
        this.sysTime = (TextView) this.controllerView.findViewById(C4202R.C4205id.sys_time);
        this.batteryLevel = (ImageView) this.controllerView.findViewById(C4202R.C4205id.iv_battery);
        this.mBatteryReceiver = new BatteryReceiver(this.batteryLevel);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(this.mBatteryReceiver);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dueeeke.videoplayer.controller.BaseVideoController, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getContext().registerReceiver(this.mBatteryReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == C4202R.C4205id.back) {
            View.OnClickListener onClickListener = this.backListener;
            if (onClickListener != null) {
                onClickListener.onClick(this.backButton);
            } else {
                this.activityContext.finish();
            }
            this.activityContext.finish();
        } else if (id == C4202R.C4205id.lock) {
            doLockUnlock();
        } else if (id == C4202R.C4205id.iv_play || id == C4202R.C4205id.thumb || id == C4202R.C4205id.complete_container) {
            doPauseResume();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    public void showTitle() {
        this.title.setVisibility(0);
    }

    public void setTitle(String str) {
        this.title.setText(str);
    }

    public void setOnClickBackListenenr(View.OnClickListener onClickListener) {
        this.backListener = onClickListener;
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    public void setPlayerState(int i) {
        switch (i) {
            case 10:
                C4233L.m16295e("PLAYER_NORMAL");
                if (this.isLocked) {
                    return;
                }
                setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                this.gestureEnabled = false;
                this.fullScreenButton.setSelected(false);
                this.lock.setVisibility(8);
                this.sysTime.setVisibility(8);
                this.batteryLevel.setVisibility(8);
                return;
            case 11:
                C4233L.m16295e("PLAYER_FULL_SCREEN");
                if (this.isLocked) {
                    return;
                }
                this.gestureEnabled = true;
                this.fullScreenButton.setSelected(true);
                this.sysTime.setVisibility(0);
                this.batteryLevel.setVisibility(0);
                if (this.mShowing) {
                    this.lock.setVisibility(0);
                    return;
                } else {
                    this.lock.setVisibility(8);
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    public void setPlayState(int i) {
        super.setPlayState(i);
        switch (i) {
            case -1:
                C4233L.m16295e("STATE_ERROR");
                this.startPlayButton.setVisibility(8);
                this.ijkControlSize.setVisibility(8);
                return;
            case 0:
                C4233L.m16295e("STATE_IDLE");
                hide();
                this.isLocked = false;
                this.lock.setSelected(false);
                this.mediaPlayer.setLock(false);
                this.completeContainer.setVisibility(8);
                this.bottomProgress.setVisibility(8);
                this.loadingProgress.setVisibility(8);
                this.startPlayButton.setVisibility(0);
                this.ijkControlSize.setVisibility(0);
                this.thumb.setVisibility(0);
                return;
            case 1:
                C4233L.m16295e("STATE_PREPARING");
                this.completeContainer.setVisibility(8);
                this.startPlayButton.setVisibility(8);
                this.ijkControlSize.setVisibility(8);
                this.loadingProgress.setVisibility(0);
                return;
            case 2:
                C4233L.m16295e("STATE_PREPARED");
                if (!this.isLive) {
                    this.bottomProgress.setVisibility(0);
                }
                this.loadingProgress.setVisibility(8);
                this.startPlayButton.setVisibility(8);
                this.ijkControlSize.setVisibility(8);
                return;
            case 3:
                C4233L.m16295e("STATE_PLAYING");
                post(this.mShowProgress);
                this.playButton.setSelected(true);
                this.completeContainer.setVisibility(8);
                this.thumb.setVisibility(8);
                this.startPlayButton.setVisibility(8);
                this.ijkControlSize.setVisibility(8);
                this.viewLayer.setVisibility(8);
                return;
            case 4:
                C4233L.m16295e("STATE_PAUSED");
                this.playButton.setSelected(false);
                this.startPlayButton.setVisibility(8);
                this.ijkControlSize.setVisibility(8);
                return;
            case 5:
                C4233L.m16295e("STATE_PLAYBACK_COMPLETED");
                hide();
                this.startPlayButton.setVisibility(8);
                this.ijkControlSize.setVisibility(8);
                this.thumb.setVisibility(0);
                this.completeContainer.setVisibility(0);
                this.bottomProgress.setProgress(0);
                this.bottomProgress.setSecondaryProgress(0);
                this.isLocked = false;
                this.mediaPlayer.setLock(false);
                return;
            case 6:
                C4233L.m16295e("STATE_BUFFERING");
                this.startPlayButton.setVisibility(8);
                this.ijkControlSize.setVisibility(8);
                this.loadingProgress.setVisibility(0);
                return;
            case 7:
                this.loadingProgress.setVisibility(8);
                this.startPlayButton.setVisibility(8);
                this.ijkControlSize.setVisibility(8);
                C4233L.m16295e("STATE_BUFFERED");
                return;
            default:
                return;
        }
    }

    private void doLockUnlock() {
        if (this.isLocked) {
            this.isLocked = false;
            this.mShowing = false;
            this.gestureEnabled = true;
            show();
            this.lock.setSelected(false);
            Toast.makeText(getContext(), C4202R.string.unlocked, 0).show();
        } else {
            hide();
            this.isLocked = true;
            this.gestureEnabled = false;
            this.lock.setSelected(true);
            Toast.makeText(getContext(), C4202R.string.locked, 0).show();
        }
        this.mediaPlayer.setLock(this.isLocked);
    }

    public void setLive() {
        this.isLive = true;
        this.bottomProgress.setVisibility(8);
        this.videoProgress.setVisibility(4);
        this.totalTime.setVisibility(4);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.isDragging = true;
        removeCallbacks(this.mShowProgress);
        removeCallbacks(this.mFadeOut);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        Tracker.onStopTrackingTouch(seekBar);
        this.mediaPlayer.seekTo((int) ((this.mediaPlayer.getDuration() * seekBar.getProgress()) / this.videoProgress.getMax()));
        this.isDragging = false;
        post(this.mShowProgress);
        show();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            long duration = (this.mediaPlayer.getDuration() * i) / this.videoProgress.getMax();
            TextView textView = this.currTime;
            if (textView != null) {
                textView.setText(stringForTime((int) duration));
            }
        }
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    public void hide() {
        if (this.mShowing) {
            if (this.mediaPlayer.isFullScreen()) {
                this.lock.setVisibility(8);
                if (!this.isLocked) {
                    hideAllViews();
                }
            } else {
                this.bottomContainer.setVisibility(8);
                this.playButton.setVisibility(8);
                this.viewLayer.setVisibility(8);
                this.bottomContainer.startAnimation(this.hideAnim);
            }
            if (!this.isLive && !this.isLocked) {
                this.bottomProgress.setVisibility(0);
                this.bottomProgress.startAnimation(this.showAnim);
            }
            this.mShowing = false;
        }
    }

    private void hideAllViews() {
        this.bottomContainer.setVisibility(8);
        this.bottomContainer.startAnimation(this.hideAnim);
        this.playButton.setVisibility(8);
        this.viewLayer.setVisibility(8);
    }

    private void show(int i) {
        if (!this.mShowing) {
            if (this.mediaPlayer.isFullScreen()) {
                this.lock.setVisibility(0);
                if (!this.isLocked) {
                    showAllViews();
                }
            } else {
                this.bottomContainer.setVisibility(0);
                this.bottomContainer.startAnimation(this.showAnim);
                this.playButton.setVisibility(0);
                this.viewLayer.setVisibility(0);
            }
            if (!this.isLocked && !this.isLive) {
                this.bottomProgress.setVisibility(8);
                this.bottomProgress.startAnimation(this.hideAnim);
            }
            this.mShowing = true;
        }
        removeCallbacks(this.mFadeOut);
        if (i != 0) {
            postDelayed(this.mFadeOut, i);
        }
    }

    private void showAllViews() {
        this.bottomContainer.setVisibility(0);
        this.bottomContainer.startAnimation(this.showAnim);
        this.playButton.setVisibility(0);
        this.viewLayer.setVisibility(0);
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    public void show() {
        show(this.sDefaultTimeout);
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    protected int setProgress() {
        if (this.mediaPlayer == null || this.isDragging) {
            return 0;
        }
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        SeekBar seekBar = this.videoProgress;
        if (seekBar != null) {
            if (duration > 0) {
                seekBar.setEnabled(true);
                int max = (int) (((currentPosition * 1.0d) / duration) * this.videoProgress.getMax());
                this.videoProgress.setProgress(max);
                this.bottomProgress.setProgress(max);
            } else {
                seekBar.setEnabled(false);
            }
            int bufferPercentage = this.mediaPlayer.getBufferPercentage();
            if (bufferPercentage >= 95) {
                SeekBar seekBar2 = this.videoProgress;
                seekBar2.setSecondaryProgress(seekBar2.getMax());
                ProgressBar progressBar = this.bottomProgress;
                progressBar.setSecondaryProgress(progressBar.getMax());
            } else {
                int i = bufferPercentage * 10;
                this.videoProgress.setSecondaryProgress(i);
                this.bottomProgress.setSecondaryProgress(i);
            }
        }
        TextView textView = this.totalTime;
        if (textView != null) {
            textView.setText(stringForTime(duration));
        }
        TextView textView2 = this.currTime;
        if (textView2 != null) {
            textView2.setText(stringForTime(currentPosition));
        }
        TextView textView3 = this.sysTime;
        if (textView3 != null) {
            textView3.setText(getCurrentSystemTime());
        }
        TextView textView4 = this.title;
        if (textView4 != null && TextUtils.isEmpty(textView4.getText())) {
            this.title.setText(this.mediaPlayer.getTitle());
        }
        return currentPosition;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dueeeke.videoplayer.controller.GestureVideoController
    public void slideToChangePosition(float f) {
        if (this.isLive) {
            this.mNeedSeek = false;
        } else {
            super.slideToChangePosition(f);
        }
    }

    public ImageView getThumb() {
        return this.thumb;
    }

    public TextView getIjkTitle() {
        return this.ijkTitle;
    }

    public TextView getIjkControlSize() {
        return this.ijkControlSize;
    }

    @Override // com.dueeeke.videoplayer.controller.BaseVideoController
    public boolean onBackPressed() {
        if (this.isLocked) {
            show();
            Toast.makeText(getContext(), C4202R.string.lock_tip, 0).show();
            return true;
        } else if (this.mediaPlayer.isFullScreen()) {
            WindowUtil.scanForActivity(getContext()).setRequestedOrientation(1);
            this.mediaPlayer.stopFullScreen();
            setPlayerState(10);
            return true;
        } else {
            return super.onBackPressed();
        }
    }
}
