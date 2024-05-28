package com.sinovatech.unicom.separatemodule.audience.smallvideo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.baidu.cloud.media.player.IMediaPlayer;
import com.baidu.cloud.videocache.ProxyCacheManager;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ISmallVideo;
import com.sinovatech.unicom.separatemodule.audience.LogFlag;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SmallVideoActivity extends BaseActivity implements ISmallVideo {
    public static final String SP_KEY = "SmallVideoActivity-videoList";
    private static final String TAG = "SmallVideoActivity";
    public static boolean bdCloudIsWebPlayer;
    public static BDCloudVideoView bdCloudVideoView;
    public NBSTraceUnit _nbs_trace;
    private long bufferEndTime;
    private int bufferStartTime;
    private long bufferTime;
    private Disposable errorResetDely;
    private SmallVideoFragment fragment;
    private boolean freeFlag;
    private String fromType;
    private boolean isCommit;
    private boolean isPause;
    private boolean isPlaying;
    private boolean isPreparing;
    private ImageView ivClose;
    private Disposable kadunSub;
    private String liveChannel;
    private LinearLayout llTitleBar;
    private ProxyCacheManager mProxyCacheManager;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private long streamEndTime;
    private int streamStartTime;
    private Disposable subscribe;
    private int videoStartTime;
    private final AppCompatActivity activityContext = this;
    private final Map<String, String> videoIds = new HashMap();
    private Handler fragmentHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.SmallVideoActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            SmallVideoActivity.this.getSupportFragmentManager().beginTransaction().add(2131297035, SmallVideoActivity.this.fragment).commit();
            SmallVideoActivity.this.isCommit = true;
            MsLogUtil.m7979d(SmallVideoActivity.TAG, "加载完成小视频fragment");
            return false;
        }
    });

    private void upKadunLog(LogFlag logFlag) {
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 72);
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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    public static /* synthetic */ void lambda$onCreate$0(SmallVideoActivity smallVideoActivity, int i, Intent intent) {
        try {
            if (App.hasLogined()) {
                UIUtils.logD("bdCloudVideoView", "登录后返回");
                smallVideoActivity.loadFragment();
            } else {
                smallVideoActivity.activityContext.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"CheckResult"})
    private void loadFragment() {
        try {
            this.fragment = new SmallVideoFragment();
            initData(getIntent());
            this.fragmentHandler.sendEmptyMessageDelayed(0, 1000L);
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        try {
            UIUtils.logD(TAG, "onResume");
            boolean z = false;
            if (!this.isCommit && this.fragmentHandler != null) {
                this.fragmentHandler.removeCallbacksAndMessages(null);
                this.fragmentHandler.sendEmptyMessageDelayed(0, 1000L);
            }
            this.isPause = false;
            boolean isPlaying = bdCloudVideoView.isPlaying();
            boolean z2 = bdCloudVideoView.getTag() != null;
            if (z2 && !TextUtils.isEmpty(bdCloudVideoView.getTag().toString())) {
                z = true;
            }
            if (bdCloudVideoView != null && z2 && z) {
                setViewSizeByVideo();
                if (!isPlaying) {
                    if (bdCloudVideoView.getCurrentPlayerState() == BDCloudVideoView.PlayerState.STATE_PAUSED) {
                        bdCloudVideoView.start();
                    } else {
                        paly(bdCloudVideoView.getTag().toString());
                    }
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        try {
            UIUtils.logD(TAG, "onStop");
            if (this.fragmentHandler != null) {
                this.fragmentHandler.removeCallbacksAndMessages(null);
                MsLogUtil.m7979d(TAG, "移除未加载的延时任务");
            }
            if (bdCloudVideoView != null && !bdCloudIsWebPlayer) {
                bdCloudVideoView.pause();
            }
            this.isPreparing = false;
            this.isPause = true;
            if (this.subscribe != null) {
                this.subscribe.dispose();
                this.subscribe = null;
            }
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        UIUtils.logD(TAG, "onDestroy");
        try {
            if (this.fragmentHandler != null) {
                this.fragmentHandler.removeCallbacksAndMessages(null);
            }
            releaseVideo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Disposable disposable = this.kadunSub;
        if (disposable != null) {
            disposable.dispose();
            this.kadunSub = null;
        }
        Disposable disposable2 = this.errorResetDely;
        if (disposable2 != null) {
            disposable2.dispose();
            this.errorResetDely = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0054, code lost:
        r9.llTitleBar.setVisibility(0);
        r9.fragment.setVideoInfo(r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initData(android.content.Intent r10) {
        /*
            r9 = this;
            java.lang.String r0 = "type"
            java.lang.String r0 = r10.getStringExtra(r0)     // Catch: java.lang.Exception -> L74
            r9.fromType = r0     // Catch: java.lang.Exception -> L74
            java.lang.String r0 = "RingFlag"
            java.lang.String r2 = r10.getStringExtra(r0)     // Catch: java.lang.Exception -> L74
            java.lang.String r0 = "RingTabTitle"
            java.lang.String r6 = r10.getStringExtra(r0)     // Catch: java.lang.Exception -> L74
            java.lang.String r0 = "videoList"
            java.lang.String r0 = r10.getStringExtra(r0)     // Catch: java.lang.Exception -> L74
            com.sinovatech.unicom.common.SharePreferenceUtil r1 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()     // Catch: java.lang.Exception -> L74
            java.lang.String r3 = r1.getString(r0)     // Catch: java.lang.Exception -> L74
            java.lang.String r0 = "videoIndex"
            r1 = 0
            int r4 = r10.getIntExtra(r0, r1)     // Catch: java.lang.Exception -> L74
            java.lang.String r0 = "videoPageNum"
            r5 = 1
            int r5 = r10.getIntExtra(r0, r5)     // Catch: java.lang.Exception -> L74
            java.lang.String r0 = "liveChannel"
            java.lang.String r10 = r10.getStringExtra(r0)     // Catch: java.lang.Exception -> L74
            r9.liveChannel = r10     // Catch: java.lang.Exception -> L74
            java.lang.String r10 = r9.fromType     // Catch: java.lang.Exception -> L74
            r0 = -1
            int r7 = r10.hashCode()     // Catch: java.lang.Exception -> L74
            r8 = -1173313060(0xffffffffba10a9dc, float:-5.518475E-4)
            if (r7 == r8) goto L49
            goto L52
        L49:
            java.lang.String r7 = "RingFlag"
            boolean r10 = r10.equals(r7)     // Catch: java.lang.Exception -> L74
            if (r10 == 0) goto L52
            r0 = r1
        L52:
            if (r0 == 0) goto L5f
            android.widget.LinearLayout r10 = r9.llTitleBar     // Catch: java.lang.Exception -> L74
            r10.setVisibility(r1)     // Catch: java.lang.Exception -> L74
            com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r10 = r9.fragment     // Catch: java.lang.Exception -> L74
            r10.setVideoInfo(r3)     // Catch: java.lang.Exception -> L74
            goto L6b
        L5f:
            android.widget.LinearLayout r10 = r9.llTitleBar     // Catch: java.lang.Exception -> L74
            r0 = 8
            r10.setVisibility(r0)     // Catch: java.lang.Exception -> L74
            com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r1 = r9.fragment     // Catch: java.lang.Exception -> L74
            r1.setInfo(r2, r3, r4, r5, r6)     // Catch: java.lang.Exception -> L74
        L6b:
            com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r10 = r9.fragment     // Catch: java.lang.Exception -> L74
            java.lang.String r0 = "smallVideo"
            r10.setFrom(r0)     // Catch: java.lang.Exception -> L74
            goto L7c
        L74:
            r10 = move-exception
            java.lang.String r10 = r10.getMessage()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7980d(r10)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.smallvideo.SmallVideoActivity.initData(android.content.Intent):void");
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        returnRingList();
    }

    private void initView() {
        try {
            this.llTitleBar = (LinearLayout) findViewById(2131297787);
            this.ivClose = (ImageView) findViewById(2131297500);
            this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$8ExTy32-vQWl9-IyFNQDOzDC8As
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SmallVideoActivity.this.returnRingList();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void initPlayer() {
        BDCloudVideoView.setAK("3927c43912004909bf897578e93bc5f9");
        bdCloudVideoView = (BDCloudVideoView) LayoutInflater.from(this).inflate(2131492974, (ViewGroup) null);
        bdCloudVideoView.setMaxProbeTime(200);
        bdCloudVideoView.setCachingHintViewVisibility(false);
        bdCloudVideoView.setLooping(true);
        bdCloudVideoView.setBufferTimeInMs(200);
        bdCloudVideoView.toggleFrameChasing(false);
        bdCloudVideoView.setVideoScalingMode(1);
        bdCloudVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$V84l2gtGmWK24HFqGZNDPdybM9k
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
            public final void onPrepared(IMediaPlayer iMediaPlayer) {
                SmallVideoActivity.lambda$initPlayer$2(SmallVideoActivity.this, iMediaPlayer);
            }
        });
        bdCloudVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$5MK1bR4RKYGy15Kp2kHeprqLqtc
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
            public final boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                return SmallVideoActivity.lambda$initPlayer$4(SmallVideoActivity.this, iMediaPlayer, i, i2);
            }
        });
        bdCloudVideoView.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$K2D8Sj9n4n8xOGWx6HBTVDfyz1A
            @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
            public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                SmallVideoActivity.lambda$initPlayer$5(SmallVideoActivity.this, playerState);
            }
        });
        bdCloudVideoView.setOnBufferingUpdateListener(new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$2obUPXJbGD6-B4ofbMEn_mcp3pw
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener
            public final void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                UIUtils.logD(SmallVideoActivity.TAG, "55555555----" + i);
            }
        });
        bdCloudVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$ypjYD1UTfklMP6XuKJPbsQP22Oo
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener
            public final void onCompletion(IMediaPlayer iMediaPlayer) {
                SmallVideoActivity.lambda$initPlayer$8(SmallVideoActivity.this, iMediaPlayer);
            }
        });
        TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "小视频", "百度播放器", "", "com.baidu.cloud.cloudplayer", "0");
        this.mProxyCacheManager = ProxyCacheManager.getInstance(this);
        bdCloudVideoView.setVideoProxy(this.mProxyCacheManager);
    }

    public static /* synthetic */ void lambda$initPlayer$2(SmallVideoActivity smallVideoActivity, IMediaPlayer iMediaPlayer) {
        smallVideoActivity.isPlaying = true;
        smallVideoActivity.streamEndTime = System.currentTimeMillis();
        int i = smallVideoActivity.streamStartTime;
        if (i != 0) {
            smallVideoActivity.bufferTime = smallVideoActivity.streamEndTime - i;
            smallVideoActivity.streamStartTime = 0;
            smallVideoActivity.upKadunLog(LogFlag.TIME);
        }
        if (bdCloudIsWebPlayer) {
            return;
        }
        smallVideoActivity.setViewSizeByVideo();
    }

    public static /* synthetic */ boolean lambda$initPlayer$4(SmallVideoActivity smallVideoActivity, IMediaPlayer iMediaPlayer, int i, int i2) {
        Disposable disposable = smallVideoActivity.errorResetDely;
        if (disposable != null && !disposable.isDisposed()) {
            smallVideoActivity.errorResetDely.dispose();
        }
        smallVideoActivity.errorResetDely = Observable.timer(2000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$3HcPhtusfXKt_b1uNmAxbGE9BBg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Long l = (Long) obj;
                SmallVideoActivity.bdCloudVideoView.start();
            }
        });
        return false;
    }

    public static /* synthetic */ void lambda$initPlayer$5(SmallVideoActivity smallVideoActivity, BDCloudVideoView.PlayerState playerState) {
        Disposable disposable;
        UIUtils.logD(TAG, "播放状态---->" + playerState);
        switch (playerState) {
            case STATE_ERROR:
                if (smallVideoActivity.isPlaying) {
                    smallVideoActivity.upKadunLog(LogFlag.STREAMERROR);
                    SmallVideoFragment smallVideoFragment = smallVideoActivity.fragment;
                    if (smallVideoFragment != null) {
                        smallVideoFragment.upLoadVideoErrorLog("1", "播放中报错，加载失败");
                    }
                } else {
                    smallVideoActivity.upKadunLog(LogFlag.STARTERROR);
                    SmallVideoFragment smallVideoFragment2 = smallVideoActivity.fragment;
                    if (smallVideoFragment2 != null) {
                        smallVideoFragment2.upLoadVideoErrorLog("2", "加载视频连接失败");
                    }
                }
                if (smallVideoActivity.videoIds.containsKey(bdCloudVideoView.getTag().toString())) {
                    smallVideoActivity.hiBoardLogFailed(smallVideoActivity.videoIds.get(bdCloudVideoView.getTag().toString()), bdCloudVideoView.getTag().toString(), "-1", "STATE_ERROR");
                }
                smallVideoActivity.stopSmallVideoTimer();
                return;
            case STATE_IDLE:
                Disposable disposable2 = smallVideoActivity.kadunSub;
                if (disposable2 != null && !disposable2.isDisposed()) {
                    smallVideoActivity.kadunSub.dispose();
                }
                smallVideoActivity.isPlaying = false;
                smallVideoActivity.streamStartTime = 0;
                smallVideoActivity.bufferStartTime = 0;
                smallVideoActivity.stopSmallVideoTimer();
                return;
            case STATE_PREPARING:
                smallVideoActivity.isPreparing = true;
                SmallVideoFragment smallVideoFragment3 = smallVideoActivity.fragment;
                if (smallVideoFragment3 != null) {
                    smallVideoFragment3.showLoading();
                    return;
                }
                return;
            case STATE_PREPARED:
                smallVideoActivity.bufferEndTime = System.currentTimeMillis();
                if (smallVideoActivity.bufferEndTime - smallVideoActivity.bufferStartTime < 5000 && (disposable = smallVideoActivity.kadunSub) != null && !disposable.isDisposed()) {
                    smallVideoActivity.kadunSub.dispose();
                }
                smallVideoActivity.isPreparing = false;
                return;
            case STATE_PLAYING:
                if ((smallVideoActivity.isPause && bdCloudVideoView != null) || flag) {
                    bdCloudVideoView.pause();
                }
                smallVideoActivity.isPreparing = false;
                smallVideoActivity.videoStartTime = 0;
                smallVideoActivity.dismissLoading();
                smallVideoActivity.showFreeTips();
                smallVideoActivity.startSmallVideoTimer();
                return;
            case STATE_PAUSED:
                smallVideoActivity.pauseSmallVideoTimer();
                return;
            case STATE_PLAYBACK_COMPLETED:
                return;
            case STATE_VIDEOSIZE_CHANGED:
                if (bdCloudIsWebPlayer) {
                    return;
                }
                smallVideoActivity.setViewSizeByVideo();
                return;
            default:
                UIUtils.logD(TAG, "default---->" + playerState);
                return;
        }
    }

    public static /* synthetic */ void lambda$initPlayer$8(SmallVideoActivity smallVideoActivity, IMediaPlayer iMediaPlayer) {
        Disposable disposable = smallVideoActivity.errorResetDely;
        if (disposable != null && !disposable.isDisposed()) {
            smallVideoActivity.errorResetDely.dispose();
        }
        smallVideoActivity.errorResetDely = Observable.timer(200L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$4lL9igI5H_kfI4XkW_mOUOnqomo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Long l = (Long) obj;
                SmallVideoActivity.bdCloudVideoView.start();
            }
        });
        UIUtils.logD(TAG, "44444444----");
    }

    private void dismissLoading() {
        SmallVideoFragment smallVideoFragment = this.fragment;
        if (smallVideoFragment != null) {
            smallVideoFragment.dismissLoading();
            this.fragment.dismissImg();
        }
    }

    private void startSmallVideoTimer() {
        SmallVideoFragment smallVideoFragment = this.fragment;
        if (smallVideoFragment != null) {
            smallVideoFragment.timerStart();
        }
    }

    private void pauseSmallVideoTimer() {
        SmallVideoFragment smallVideoFragment = this.fragment;
        if (smallVideoFragment != null) {
            smallVideoFragment.timerPause();
        }
    }

    private void stopSmallVideoTimer() {
        SmallVideoFragment smallVideoFragment = this.fragment;
        if (smallVideoFragment != null) {
            smallVideoFragment.timerStop();
        }
    }

    private void hiBoardLogFailed(String str, String str2, String str3, String str4) {
        if (this.managerAudienceLoadData == null) {
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("videoId", str);
        hashMap.put("videoUrl", str2);
        hashMap.put("code", str3);
        hashMap.put("msg", str4);
        this.managerAudienceLoadData.hiBoardLogFailed(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$7pqXX_hmgWRn7CLM9DVy4jnVcIk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD(SmallVideoActivity.TAG, (String) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    private void setViewSizeByVideo() {
        try {
            UIUtils.logD(TAG, "H:" + bdCloudVideoView.getVideoHeight() + "|W:" + bdCloudVideoView.getVideoWidth());
            this.fragment.setVideoSizeView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFreeTips() {
        try {
            if (this.freeFlag || "Wifi".equals(DeviceHelper.getNETType(this.activityContext)) || UserManager.getInstance().isYiwang()) {
                return;
            }
            this.freeFlag = true;
            showFreeTips(1500L);
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void releaseVideo() {
        try {
            if (bdCloudVideoView != null) {
                bdCloudVideoView.stopPlayback();
                bdCloudVideoView.release();
                ViewParent parent = bdCloudVideoView.getParent();
                if (parent instanceof FrameLayout) {
                    ((FrameLayout) parent).removeView(bdCloudVideoView);
                }
            }
            if (this.mProxyCacheManager != null) {
                this.mProxyCacheManager.release();
                this.mProxyCacheManager = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void returnRingList() {
        try {
            if (getIntent().hasExtra(AudienceMainActivity.RING_FLAG)) {
                String videoListJson = this.fragment.getVideoListJson();
                int currentIndex = this.fragment.getCurrentIndex();
                Intent intent = new Intent();
                intent.putExtra(AudienceMainActivity.RING_LIST, videoListJson);
                intent.putExtra(AudienceMainActivity.RING_INDEX, currentIndex);
                setResult(3222, intent);
            }
            releaseVideo();
            finish();
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void setTabVisibility(int i) {
        LinearLayout linearLayout = this.llTitleBar;
        if (linearLayout != null) {
            linearLayout.setVisibility(i);
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void preload(List<String> list) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    UIUtils.logD("videoCache", "缓存连接：" + it.next());
                }
            } catch (Exception e) {
                MsLogUtil.m7980d(e.getMessage());
            }
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public int getScreenOrientation(Context context) {
        Activity activity = (Activity) context;
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (((rotation != 0 && rotation != 2) || i2 <= i) && ((rotation != 1 && rotation != 3) || i <= i2)) {
            switch (rotation) {
                case 0:
                default:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 8;
                case 3:
                    return 9;
            }
        }
        switch (rotation) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 9;
            case 3:
                return 8;
            default:
                return 1;
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public BDCloudVideoView playVideo(String str) {
        if (TextUtils.isEmpty(str)) {
            UIUtils.logD("bdCloudVideoView", "url为空");
            bdCloudVideoView.stopPlayback();
            bdCloudVideoView.reSetRender();
            return bdCloudVideoView;
        }
        bdCloudVideoView.stopPlayback();
        bdCloudVideoView.reSetRender();
        paly(str);
        return bdCloudVideoView;
    }

    private void paly(String str) {
        try {
            if (this.subscribe != null) {
                this.subscribe.dispose();
                this.subscribe = null;
            }
            bdCloudVideoView.setVideoPath(str);
            bdCloudVideoView.setTag(str);
            this.subscribe = Observable.timer(400L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SmallVideoActivity$2f-Oo1iFhAYrF2jFjx_bWc_zajY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoActivity.lambda$paly$10(SmallVideoActivity.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$paly$10(SmallVideoActivity smallVideoActivity, Long l) throws Exception {
        bdCloudVideoView.start();
        Disposable disposable = smallVideoActivity.subscribe;
        if (disposable != null) {
            disposable.dispose();
            smallVideoActivity.subscribe = null;
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public BDCloudVideoView playVideo(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            bdCloudVideoView.stopPlayback();
            bdCloudVideoView.reSetRender();
            return bdCloudVideoView;
        }
        this.videoIds.put(str, str2);
        bdCloudVideoView.stopPlayback();
        bdCloudVideoView.reSetRender();
        paly(str);
        return bdCloudVideoView;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void fullScreen() {
        try {
            if (getScreenOrientation(this.activityContext) == 0) {
                this.llTitleBar.setVisibility(AudienceMainActivity.RING_FLAG.equals(this.fromType) ? 8 : 0);
                setRequestedOrientation(1);
            } else {
                this.llTitleBar.setVisibility(8);
                setRequestedOrientation(0);
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public String getLiveChannel() {
        return this.liveChannel;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void showFreeTips(long j) {
        UIUtils.logD(TAG, "免流提示");
    }
}
