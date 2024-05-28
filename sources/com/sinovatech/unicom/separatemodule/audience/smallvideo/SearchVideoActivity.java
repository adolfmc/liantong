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
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchVideoActivity extends BaseActivity implements ISmallVideo {
    public static final String SP_PARAM_KEY = "SearchVideoActivity-param";
    private static final String TAG = "SearchVideoActivity";
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
    private Handler fragmentHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.SearchVideoActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            SearchVideoActivity.this.getSupportFragmentManager().beginTransaction().add(2131297035, SearchVideoActivity.this.fragment).commit();
            SearchVideoActivity.this.isCommit = true;
            MsLogUtil.m7979d(SearchVideoActivity.TAG, "加载完成小视频fragment");
            return false;
        }
    });

    private void upKadunLog(LogFlag logFlag) {
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 71);
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

    public static /* synthetic */ void lambda$onCreate$0(SearchVideoActivity searchVideoActivity, int i, Intent intent) {
        try {
            if (App.hasLogined()) {
                UIUtils.logD("bdCloudVideoView", "登录后返回");
                searchVideoActivity.loadFragment();
            } else {
                searchVideoActivity.activityContext.finish();
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
            MsLogUtil.m7980d(e.getMessage());
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
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            UIUtils.logD(TAG, "onDestroy");
            if (this.fragmentHandler != null) {
                this.fragmentHandler.removeCallbacksAndMessages(null);
            }
            try {
                releaseVideo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.kadunSub != null) {
                this.kadunSub.dispose();
                this.kadunSub = null;
            }
            if (this.errorResetDely != null) {
                this.errorResetDely.dispose();
                this.errorResetDely = null;
            }
        } catch (Exception e2) {
            MsLogUtil.m7980d(e2.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006d A[Catch: Exception -> 0x00ad, TryCatch #0 {Exception -> 0x00ad, blocks: (B:2:0x0000, B:15:0x006a, B:16:0x006d, B:22:0x00a4, B:17:0x0070, B:18:0x007c, B:20:0x0099, B:21:0x009d, B:7:0x0053, B:10:0x005e), top: B:27:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0070 A[Catch: Exception -> 0x00ad, TryCatch #0 {Exception -> 0x00ad, blocks: (B:2:0x0000, B:15:0x006a, B:16:0x006d, B:22:0x00a4, B:17:0x0070, B:18:0x007c, B:20:0x0099, B:21:0x009d, B:7:0x0053, B:10:0x005e), top: B:27:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007c A[Catch: Exception -> 0x00ad, TryCatch #0 {Exception -> 0x00ad, blocks: (B:2:0x0000, B:15:0x006a, B:16:0x006d, B:22:0x00a4, B:17:0x0070, B:18:0x007c, B:20:0x0099, B:21:0x009d, B:7:0x0053, B:10:0x005e), top: B:27:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initData(android.content.Intent r12) {
        /*
            r11 = this;
            java.lang.String r0 = "type"
            java.lang.String r0 = r12.getStringExtra(r0)     // Catch: java.lang.Exception -> Lad
            java.lang.String r1 = "RingFlag"
            java.lang.String r3 = r12.getStringExtra(r1)     // Catch: java.lang.Exception -> Lad
            java.lang.String r1 = "RingTabTitle"
            java.lang.String r7 = r12.getStringExtra(r1)     // Catch: java.lang.Exception -> Lad
            java.lang.String r1 = "videoList"
            java.lang.String r1 = r12.getStringExtra(r1)     // Catch: java.lang.Exception -> Lad
            com.sinovatech.unicom.common.SharePreferenceUtil r2 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()     // Catch: java.lang.Exception -> Lad
            java.lang.String r4 = r2.getString(r1)     // Catch: java.lang.Exception -> Lad
            java.lang.String r1 = "videoIndex"
            r2 = 0
            int r5 = r12.getIntExtra(r1, r2)     // Catch: java.lang.Exception -> Lad
            java.lang.String r1 = "videoPageNum"
            r6 = 1
            int r1 = r12.getIntExtra(r1, r6)     // Catch: java.lang.Exception -> Lad
            java.lang.String r8 = "liveChannel"
            java.lang.String r8 = r12.getStringExtra(r8)     // Catch: java.lang.Exception -> Lad
            r11.liveChannel = r8     // Catch: java.lang.Exception -> Lad
            com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r8 = r11.fragment     // Catch: java.lang.Exception -> Lad
            java.lang.String r9 = "searchVideo"
            r8.setFrom(r9)     // Catch: java.lang.Exception -> Lad
            r11.fromType = r0     // Catch: java.lang.Exception -> Lad
            r8 = -1
            int r9 = r0.hashCode()     // Catch: java.lang.Exception -> Lad
            r10 = -1173313060(0xffffffffba10a9dc, float:-5.518475E-4)
            if (r9 == r10) goto L5e
            r6 = 3536774(0x35f786, float:4.956076E-39)
            if (r9 == r6) goto L53
            goto L67
        L53:
            java.lang.String r6 = "spcl"
            boolean r0 = r0.equals(r6)     // Catch: java.lang.Exception -> Lad
            if (r0 == 0) goto L67
            r6 = r2
            goto L68
        L5e:
            java.lang.String r9 = "RingFlag"
            boolean r0 = r0.equals(r9)     // Catch: java.lang.Exception -> Lad
            if (r0 == 0) goto L67
            goto L68
        L67:
            r6 = r8
        L68:
            r0 = 8
            switch(r6) {
                case 0: goto L7c;
                case 1: goto L70;
                default: goto L6d;
            }     // Catch: java.lang.Exception -> Lad
        L6d:
            android.widget.LinearLayout r12 = r11.llTitleBar     // Catch: java.lang.Exception -> Lad
            goto La4
        L70:
            android.widget.LinearLayout r12 = r11.llTitleBar     // Catch: java.lang.Exception -> Lad
            r12.setVisibility(r0)     // Catch: java.lang.Exception -> Lad
            com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r2 = r11.fragment     // Catch: java.lang.Exception -> Lad
            r6 = r1
            r2.setInfo(r3, r4, r5, r6, r7)     // Catch: java.lang.Exception -> Lad
            goto Lb5
        L7c:
            java.lang.String r2 = "RingFlag"
            r11.fromType = r2     // Catch: java.lang.Exception -> Lad
            android.widget.LinearLayout r2 = r11.llTitleBar     // Catch: java.lang.Exception -> Lad
            r2.setVisibility(r0)     // Catch: java.lang.Exception -> Lad
            java.lang.String r0 = "params"
            java.lang.String r12 = r12.getStringExtra(r0)     // Catch: java.lang.Exception -> Lad
            com.sinovatech.unicom.common.SharePreferenceUtil r0 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()     // Catch: java.lang.Exception -> Lad
            java.lang.String r12 = r0.getString(r12)     // Catch: java.lang.Exception -> Lad
            boolean r0 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Exception -> Lad
            if (r0 != 0) goto L9d
            r11.analysisParams(r12)     // Catch: java.lang.Exception -> Lad
            goto Lb5
        L9d:
            com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r2 = r11.fragment     // Catch: java.lang.Exception -> Lad
            r6 = r1
            r2.setInfo(r3, r4, r5, r6, r7)     // Catch: java.lang.Exception -> Lad
            goto Lb5
        La4:
            r12.setVisibility(r2)     // Catch: java.lang.Exception -> Lad
            com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r12 = r11.fragment     // Catch: java.lang.Exception -> Lad
            r12.setVideoInfo(r4)     // Catch: java.lang.Exception -> Lad
            goto Lb5
        Lad:
            r12 = move-exception
            java.lang.String r12 = r12.getMessage()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7980d(r12)
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.smallvideo.SearchVideoActivity.initData(android.content.Intent):void");
    }

    private void analysisParams(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.optString("type");
            String optString = jSONObject.optString("accessName");
            HashMap hashMap = new HashMap();
            JSONObject optJSONObject = jSONObject.optJSONObject("customParams");
            if (optJSONObject != null) {
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, optJSONObject.optString(next));
                }
            }
            String optString2 = jSONObject.optString("index");
            String optString3 = jSONObject.optString("nextPageNum");
            String optString4 = jSONObject.optString("videos");
            this.fragment.setFrom(optString);
            this.fragment.setCustomParams(optString, hashMap);
            this.fragment.setInfo(optString, optString4, Integer.parseInt(optString2), Integer.parseInt(optString3), "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        returnRingList();
    }

    private void initView() {
        try {
            this.llTitleBar = (LinearLayout) findViewById(2131297787);
            this.ivClose = (ImageView) findViewById(2131297500);
            this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$Tv2_1SvbNZlU1lBaw6RGW4nrR7s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SearchVideoActivity.this.returnRingList();
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
        bdCloudVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$rJUF_OjEdzOFKjtzbsubxPOm4a0
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
            public final void onPrepared(IMediaPlayer iMediaPlayer) {
                SearchVideoActivity.lambda$initPlayer$2(SearchVideoActivity.this, iMediaPlayer);
            }
        });
        bdCloudVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$6pja1zZQ7X8cThw1dBArdQnBZfA
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
            public final boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                return SearchVideoActivity.lambda$initPlayer$4(SearchVideoActivity.this, iMediaPlayer, i, i2);
            }
        });
        bdCloudVideoView.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$AL__1L-tUoiElAbHz835zBQvs8g
            @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
            public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                SearchVideoActivity.lambda$initPlayer$5(SearchVideoActivity.this, playerState);
            }
        });
        bdCloudVideoView.setOnBufferingUpdateListener(new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$yNlVv1TL9LjHEPTGnPtiJGOnxdo
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener
            public final void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                UIUtils.logD(SearchVideoActivity.TAG, "55555555----" + i);
            }
        });
        bdCloudVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$yQ6vC1b5FBVeBlXGmZGHAfvZta0
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener
            public final void onCompletion(IMediaPlayer iMediaPlayer) {
                SearchVideoActivity.lambda$initPlayer$8(SearchVideoActivity.this, iMediaPlayer);
            }
        });
        TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "搜索小视频详情", "百度播放器", "", "com.baidu.cloud.cloudplayer", "0");
        this.mProxyCacheManager = ProxyCacheManager.getInstance(this);
        bdCloudVideoView.setVideoProxy(this.mProxyCacheManager);
    }

    public static /* synthetic */ void lambda$initPlayer$2(SearchVideoActivity searchVideoActivity, IMediaPlayer iMediaPlayer) {
        searchVideoActivity.isPlaying = true;
        searchVideoActivity.streamEndTime = System.currentTimeMillis();
        int i = searchVideoActivity.streamStartTime;
        if (i != 0) {
            searchVideoActivity.bufferTime = searchVideoActivity.streamEndTime - i;
            searchVideoActivity.streamStartTime = 0;
            searchVideoActivity.upKadunLog(LogFlag.TIME);
        }
        if (bdCloudIsWebPlayer) {
            return;
        }
        searchVideoActivity.setViewSizeByVideo();
    }

    public static /* synthetic */ boolean lambda$initPlayer$4(SearchVideoActivity searchVideoActivity, IMediaPlayer iMediaPlayer, int i, int i2) {
        Disposable disposable = searchVideoActivity.errorResetDely;
        if (disposable != null && !disposable.isDisposed()) {
            searchVideoActivity.errorResetDely.dispose();
        }
        searchVideoActivity.errorResetDely = Observable.timer(2000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$ScbiYLaM_KSKNtVuKRvvQujqFPc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Long l = (Long) obj;
                SearchVideoActivity.bdCloudVideoView.start();
            }
        });
        return false;
    }

    public static /* synthetic */ void lambda$initPlayer$5(SearchVideoActivity searchVideoActivity, BDCloudVideoView.PlayerState playerState) {
        Disposable disposable;
        UIUtils.logD(TAG, "播放状态---->" + playerState);
        switch (playerState) {
            case STATE_ERROR:
                if (searchVideoActivity.isPlaying) {
                    searchVideoActivity.upKadunLog(LogFlag.STREAMERROR);
                    SmallVideoFragment smallVideoFragment = searchVideoActivity.fragment;
                    if (smallVideoFragment != null) {
                        smallVideoFragment.upLoadVideoErrorLog("1", "播放中报错，加载失败");
                    }
                } else {
                    searchVideoActivity.upKadunLog(LogFlag.STARTERROR);
                    SmallVideoFragment smallVideoFragment2 = searchVideoActivity.fragment;
                    if (smallVideoFragment2 != null) {
                        smallVideoFragment2.upLoadVideoErrorLog("2", "加载视频连接失败");
                    }
                }
                if (searchVideoActivity.videoIds.containsKey(bdCloudVideoView.getTag().toString())) {
                    searchVideoActivity.hiBoardLogFailed(searchVideoActivity.videoIds.get(bdCloudVideoView.getTag().toString()), bdCloudVideoView.getTag().toString(), "-1", "STATE_ERROR");
                }
                searchVideoActivity.stopSmallVideoTimer();
                return;
            case STATE_IDLE:
                Disposable disposable2 = searchVideoActivity.kadunSub;
                if (disposable2 != null && !disposable2.isDisposed()) {
                    searchVideoActivity.kadunSub.dispose();
                }
                searchVideoActivity.isPlaying = false;
                searchVideoActivity.streamStartTime = 0;
                searchVideoActivity.bufferStartTime = 0;
                searchVideoActivity.stopSmallVideoTimer();
                return;
            case STATE_PREPARING:
                searchVideoActivity.isPreparing = true;
                SmallVideoFragment smallVideoFragment3 = searchVideoActivity.fragment;
                if (smallVideoFragment3 != null) {
                    smallVideoFragment3.showLoading();
                    return;
                }
                return;
            case STATE_PREPARED:
                searchVideoActivity.bufferEndTime = System.currentTimeMillis();
                if (searchVideoActivity.bufferEndTime - searchVideoActivity.bufferStartTime < 5000 && (disposable = searchVideoActivity.kadunSub) != null && !disposable.isDisposed()) {
                    searchVideoActivity.kadunSub.dispose();
                }
                searchVideoActivity.isPreparing = false;
                return;
            case STATE_PLAYING:
                if ((searchVideoActivity.isPause && bdCloudVideoView != null) || flag) {
                    bdCloudVideoView.pause();
                }
                searchVideoActivity.isPreparing = false;
                searchVideoActivity.videoStartTime = 0;
                searchVideoActivity.dismissLoading();
                searchVideoActivity.showFreeTips();
                searchVideoActivity.startSmallVideoTimer();
                return;
            case STATE_PAUSED:
                searchVideoActivity.pauseSmallVideoTimer();
                return;
            case STATE_PLAYBACK_COMPLETED:
                return;
            case STATE_VIDEOSIZE_CHANGED:
                if (bdCloudIsWebPlayer) {
                    return;
                }
                searchVideoActivity.setViewSizeByVideo();
                return;
            default:
                UIUtils.logD(TAG, "default---->" + playerState);
                return;
        }
    }

    public static /* synthetic */ void lambda$initPlayer$8(SearchVideoActivity searchVideoActivity, IMediaPlayer iMediaPlayer) {
        Disposable disposable = searchVideoActivity.errorResetDely;
        if (disposable != null && !disposable.isDisposed()) {
            searchVideoActivity.errorResetDely.dispose();
        }
        searchVideoActivity.errorResetDely = Observable.timer(200L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$BcOhP0RTCCmtLtvl0ALVNIss45U
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Long l = (Long) obj;
                SearchVideoActivity.bdCloudVideoView.start();
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
        this.managerAudienceLoadData.hiBoardLogFailed(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$Jg1XN-OHsQq482C2vIptObWB3k8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD(SearchVideoActivity.TAG, (String) obj);
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
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
        finish();
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
        if (list == null || list.size() == 0) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            UIUtils.logD("videoCache", "缓存连接：" + it.next());
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
        try {
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
        if (TextUtils.isEmpty(str)) {
            UIUtils.logD("bdCloudVideoView", "url为空");
            bdCloudVideoView.stopPlayback();
            bdCloudVideoView.reSetRender();
            return bdCloudVideoView;
        }
        bdCloudVideoView.stopPlayback();
        bdCloudVideoView.reSetRender();
        MsLogUtil.m7979d("SmallVideoFragment", "ac-playVideo-0");
        paly(str);
        MsLogUtil.m7979d("SmallVideoFragment", "ac-playVideo-10");
        return bdCloudVideoView;
    }

    private void paly(String str) {
        try {
            MsLogUtil.m7979d("SmallVideoFragment", "ac-play-0");
            if (this.subscribe != null) {
                this.subscribe.dispose();
                this.subscribe = null;
            }
            bdCloudVideoView.setVideoPath(str);
            bdCloudVideoView.setTag(str);
            this.subscribe = Observable.timer(400L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$IzEZ4o7FES_Rv-BWJmQXwm1ofmM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SearchVideoActivity.lambda$paly$10(SearchVideoActivity.this, (Long) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.smallvideo.-$$Lambda$SearchVideoActivity$xLRWbj75L9_GfHGUKkkaFFx9gIc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Throwable th = (Throwable) obj;
                    MsLogUtil.m7979d("SmallVideoFragment", "ac-play-e" + th.getMessage());
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$paly$10(SearchVideoActivity searchVideoActivity, Long l) throws Exception {
        MsLogUtil.m7979d("SmallVideoFragment", "ac-play-1");
        bdCloudVideoView.start();
        MsLogUtil.m7979d("SmallVideoFragment", "ac-play-2");
        Disposable disposable = searchVideoActivity.subscribe;
        if (disposable != null) {
            disposable.dispose();
            searchVideoActivity.subscribe = null;
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
