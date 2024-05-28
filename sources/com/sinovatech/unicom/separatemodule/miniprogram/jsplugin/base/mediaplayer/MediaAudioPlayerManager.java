package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.baidu.cloud.media.player.BDCloudMediaPlayer;
import com.baidu.cloud.media.player.IMediaPlayer;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MediaAudioPlayerManager implements IMediaPlayer.OnCompletionListener {
    private static MediaAudioPlayerManager getInstance;
    public static BDCloudMediaPlayer mMediaPlayer;
    private Activity activityContext;
    private JSONArray audioList;
    private Disposable disposable;
    private boolean isReady;
    private WindowManager mWindowManager;
    private ImageView mediaClose;
    private ImageView mediaIcon;
    private ImageView mediaPlay;
    private OnMediaPlayerCode onMediaPlayerState;
    public MediaPlayerEntity playerStatus;
    private SetOnMediaPlayerSates setOnMediaPlayerSates;
    private View view;
    private WindowManager.LayoutParams windowLayoutParams;
    private String TAG = "AudioMediaPlayer";

    /* renamed from: i */
    private int f18576i = 0;
    IMediaPlayer.OnPreparedListener mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.6
        @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            MediaAudioPlayerManager.mMediaPlayer.start();
            MediaAudioPlayerManager.this.isReady = true;
        }
    };
    IMediaPlayer.OnErrorListener mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.7
        @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            MediaAudioPlayerManager.this.setOnMediaPlayerSates.onMediaPlayerState("fail", MediaAudioPlayerManager.mMediaPlayer.getDataSource());
            String str = MediaAudioPlayerManager.this.TAG;
            MsLogUtil.m7979d(str, "Media Error what::" + i + "extra" + i2);
            return false;
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnMediaPlayerCode {
        void onMusicState(String str, String str2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface SetOnMediaPlayerSates {
        void onMediaPlayerState(String str, String str2);
    }

    public static MediaAudioPlayerManager getInstance(Activity activity) {
        synchronized (MediaAudioPlayerManager.class) {
            if (getInstance == null) {
                getInstance = new MediaAudioPlayerManager(activity);
            }
        }
        return getInstance;
    }

    private MediaAudioPlayerManager(Activity activity) {
        this.activityContext = activity;
    }

    public void createMedia(SetOnMediaPlayerSates setOnMediaPlayerSates) {
        this.setOnMediaPlayerSates = setOnMediaPlayerSates;
        if (mMediaPlayer == null) {
            mMediaPlayer = new BDCloudMediaPlayer(this.activityContext);
        }
        MsLogUtil.m7979d(this.TAG, "创建播放器");
    }

    public void setMediaPlayerData(JSONArray jSONArray, MediaPlayerEntity mediaPlayerEntity, OnMediaPlayerCode onMediaPlayerCode) {
        this.onMediaPlayerState = onMediaPlayerCode;
        this.playerStatus = mediaPlayerEntity;
        this.audioList = jSONArray;
        try {
            if (this.f18576i != 0) {
                onMediaPlayerCode.onMusicState("02", "播放列表未播完不可添加");
                return;
            }
            this.playerStatus.setFinish(true);
            if (this.f18576i < jSONArray.length()) {
                String audioList = getAudioList(jSONArray, this.f18576i);
                this.f18576i++;
                mMediaPlayer.setDataSource(this.activityContext, Uri.parse(audioList));
                mMediaPlayer.prepareAsync();
            }
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setOnErrorListener(this.mErrorListener);
            initView();
            setWindows();
            onMediaPlayerCode.onMusicState("00", "success");
            MsLogUtil.m7979d(this.TAG, "设置播放音源");
        } catch (Exception e) {
            MsLogUtil.m7979d(this.TAG, e.getMessage());
            onMediaPlayerCode.onMusicState("03", "音频列表为空");
            e.printStackTrace();
        }
    }

    public void setWindows() {
        if (this.windowLayoutParams == null) {
            this.windowLayoutParams = new WindowManager.LayoutParams();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.windowLayoutParams.type = 2038;
        }
        WindowManager.LayoutParams layoutParams = this.windowLayoutParams;
        layoutParams.flags = 40;
        layoutParams.width = UIUtils.dip2px(123.0f);
        this.windowLayoutParams.height = UIUtils.dip2px(45.0f);
        WindowManager.LayoutParams layoutParams2 = this.windowLayoutParams;
        layoutParams2.format = 1;
        layoutParams2.gravity = 53;
        layoutParams2.x = 0;
        layoutParams2.y = UIUtils.dip2px(175.0f);
        if (this.mWindowManager == null) {
            this.mWindowManager = (WindowManager) this.activityContext.getSystemService("window");
            this.mWindowManager.addView(this.view, this.windowLayoutParams);
        }
    }

    public void initView() {
        if (this.view == null) {
            this.view = LayoutInflater.from(this.activityContext.getApplicationContext()).inflate(2131493346, (ViewGroup) null);
        }
        this.mediaIcon = (ImageView) this.view.findViewById(2131298041);
        this.mediaClose = (ImageView) this.view.findViewById(2131298040);
        this.mediaPlay = (ImageView) this.view.findViewById(2131298042);
        try {
            GlideApp.with(this.activityContext.getApplicationContext()).load(this.playerStatus.getAudioIconUrl()).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new CircleCrop())).into(this.mediaIcon);
            GlideApp.with(this.activityContext.getApplicationContext()).load(this.playerStatus.getPlayIconUrl()).error(2131231643).fallback(2131231643).into(this.mediaPlay);
            if (this.playerStatus.isStart()) {
                GlideApp.with(this.activityContext.getApplicationContext()).load((Integer) 2131231644).into(this.mediaPlay);
            } else {
                GlideApp.with(this.activityContext.getApplicationContext()).load((Integer) 2131231643).into(this.mediaPlay);
            }
            if (!this.playerStatus.isFinish()) {
                if (this.disposable != null && !this.disposable.isDisposed()) {
                    this.disposable.dispose();
                }
                int i = 5;
                try {
                    if (!TextUtils.isEmpty(this.playerStatus.getCloseDuration())) {
                        i = Integer.parseInt(this.playerStatus.getCloseDuration());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.disposable = Observable.timer(i, TimeUnit.SECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Long l) throws Exception {
                        MediaAudioPlayerManager.this.destroyMediaPlayer();
                        MediaAudioPlayerManager.this.disposable.dispose();
                    }
                });
            } else if (this.disposable != null) {
                this.disposable.dispose();
            }
            this.mediaIcon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (!MediaAudioPlayerManager.this.activityContext.isDestroyed() && !MediaAudioPlayerManager.this.activityContext.isFinishing()) {
                        MediaAudioPlayerManager.this.setOnMediaPlayerSates.onMediaPlayerState("icon点击", MediaAudioPlayerManager.this.playerStatus.getPlaySong());
                    } else if (!TextUtils.isEmpty(MediaAudioPlayerManager.this.playerStatus.getIconTargetUrl())) {
                        IntentManager.generateIntentAndGo(MediaAudioPlayerManager.this.activityContext, MediaAudioPlayerManager.this.playerStatus.getIconTargetUrl());
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.mediaPlay.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (MediaAudioPlayerManager.this.playerStatus.isStart()) {
                        MediaAudioPlayerManager.this.stopMediaPlayer();
                    } else {
                        MediaAudioPlayerManager.this.startMediaPlayer();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.mediaClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    MediaAudioPlayerManager.this.destroyMediaPlayer();
                    MediaAudioPlayerManager.this.setOnMediaPlayerSates.onMediaPlayerState("closeClick", MediaAudioPlayerManager.this.playerStatus.getPlaySong());
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.mWindowManager.updateViewLayout(this.view, this.windowLayoutParams);
        } catch (Exception e2) {
            e2.printStackTrace();
            String str = this.TAG;
            MsLogUtil.m7979d(str, "initView" + e2.getMessage());
        }
        this.view.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.5
            private int mMoveX;
            private int mMoveY;
            private int mTouchCurrentX;
            private int mTouchCurrentY;
            private int mTouchStartX;
            private int mTouchStartY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.mTouchStartX = (int) motionEvent.getRawX();
                    this.mTouchStartY = (int) motionEvent.getRawY();
                    this.mMoveX = 0;
                    this.mMoveY = 0;
                } else if (motionEvent.getAction() == 2) {
                    this.mTouchCurrentX = (int) motionEvent.getRawX();
                    this.mTouchCurrentY = (int) motionEvent.getRawY();
                    int i2 = this.mTouchCurrentX;
                    this.mMoveX = i2 - this.mTouchStartX;
                    int i3 = this.mTouchCurrentY;
                    this.mMoveY = i3 - this.mTouchStartY;
                    this.mTouchStartX = i2;
                    this.mTouchStartY = i3;
                    MediaAudioPlayerManager.this.windowLayoutParams.y += this.mMoveY;
                    MediaAudioPlayerManager.this.mWindowManager.updateViewLayout(view, MediaAudioPlayerManager.this.windowLayoutParams);
                } else if (motionEvent.getAction() == 1) {
                    MediaAudioPlayerManager.this.mWindowManager.updateViewLayout(view, MediaAudioPlayerManager.this.windowLayoutParams);
                    if (motionEvent.getEventTime() - motionEvent.getDownTime() < 150) {
                        Log.d(MediaAudioPlayerManager.this.TAG, "按下抬起小于100 按点击事件处理");
                        view.performClick();
                    }
                }
                return true;
            }
        });
    }

    private String getAudioList(JSONArray jSONArray, int i) {
        JSONObject optJSONObject = jSONArray.optJSONObject(i);
        String optString = optJSONObject.optString("audioUrl");
        this.playerStatus.setAudioIconUrl(optJSONObject.optString("audioIconUrl"));
        this.playerStatus.setPlaySong(optString);
        return optString;
    }

    public void startMediaPlayer() {
        try {
            if (mMediaPlayer.isPlayable()) {
                mMediaPlayer.start();
            }
            mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
            this.playerStatus.setStart(true);
            this.setOnMediaPlayerSates.onMediaPlayerState("playing", mMediaPlayer.getDataSource());
            initView();
            MsLogUtil.m7979d(this.TAG, "开始播放");
        } catch (Exception e) {
            MsLogUtil.m7979d(this.TAG, e.getMessage());
            this.onMediaPlayerState.onMusicState("04", "播放列表为空");
        }
    }

    public void stopMediaPlayer() {
        if (mMediaPlayer != null) {
            this.playerStatus.setStart(false);
            mMediaPlayer.pause();
            this.setOnMediaPlayerSates.onMediaPlayerState("pause", mMediaPlayer.getDataSource());
            initView();
            MsLogUtil.m7979d(this.TAG, "停止播放器");
        }
    }

    public void destroyMediaPlayer() {
        BDCloudMediaPlayer bDCloudMediaPlayer = mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.stop();
            mMediaPlayer.release();
            this.isReady = false;
            mMediaPlayer = null;
            this.playerStatus.setFinish(true);
            this.f18576i = 0;
            WindowManager windowManager = this.mWindowManager;
            if (windowManager != null) {
                View view = this.view;
                if (view != null) {
                    windowManager.removeView(view);
                }
                this.mWindowManager = null;
            }
            this.setOnMediaPlayerSates.onMediaPlayerState("mediaListEnd", "列表加载完毕");
            MsLogUtil.m7979d(this.TAG, "销毁播放器");
        }
    }

    public void resetMediaDataSource() {
        BDCloudMediaPlayer bDCloudMediaPlayer = mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            this.f18576i = 0;
            bDCloudMediaPlayer.stop();
            mMediaPlayer.reset();
            this.playerStatus.setFinish(false);
            this.playerStatus.setStart(false);
            this.setOnMediaPlayerSates.onMediaPlayerState("mediaListEnd", "列表加载完毕");
            initView();
        }
        MsLogUtil.m7979d(this.TAG, "重置播放音乐源");
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        try {
            if (this.f18576i < this.audioList.length()) {
                mMediaPlayer.stop();
                Uri parse = Uri.parse(getAudioList(this.audioList, this.f18576i));
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(this.activityContext.getApplicationContext(), parse);
                mMediaPlayer.prepareAsync();
                startMediaPlayer();
                this.setOnMediaPlayerSates.onMediaPlayerState("playEnd", mMediaPlayer.getDataSource());
            } else {
                this.playerStatus.setFinish(false);
                this.f18576i = 0;
                this.setOnMediaPlayerSates.onMediaPlayerState("mediaListEnd", "列表加载完毕");
                MsLogUtil.m7979d(this.TAG, "播放完成");
            }
            initView();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d(this.TAG, e.getMessage());
        }
    }
}
