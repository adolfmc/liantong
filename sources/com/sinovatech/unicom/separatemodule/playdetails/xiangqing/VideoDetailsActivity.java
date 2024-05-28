package com.sinovatech.unicom.separatemodule.playdetails.xiangqing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.bytedance.applog.tracker.Tracker;
import com.flyco.tablayout.SlidingTabLayout;
import com.fort.andjni.JniLib;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxbinding2.view.RxView;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity;
import com.sinovatech.unicom.separatemodule.videocenter.VideoLandscapeActivity;
import com.sinovatech.unicom.separatemodule.videocenter.utils.HiBoardLog;
import com.sinovatech.unicom.separatemodule.videocenter.utils.LiuZPTLog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoDetailsActivity extends BaseActivity implements View.OnClickListener {
    private static final long POSITION_REFRESH_TIME = 500;
    public static BDCloudVideoView bdVideo;
    public static boolean isFull;
    public static boolean isHuifu;
    public static boolean isPausedByOnPause;
    public static RelativeLayout playerView;
    public static FrameLayout playerViewForVideo;
    public NBSTraceUnit _nbs_trace;
    private FrameLayout adv_fl;
    private FrameLayout adv_group_fl;
    private View btnReplay;
    private View btnSharePYQ;
    private View btnShareQQ;
    private View btnShareWX;
    private String category;
    private ImageView close_ad_iv;
    private CommentFragment commentFragment;
    private VideoDetailsEntity.DataDTO data;
    private String from;
    private String fromGid;
    private String group_id_str;
    private HiBoardLog hiBoardLog;
    private String imageUrl;
    private boolean isFollow;
    private boolean isPause;
    private boolean isPlayEnd;
    private boolean isShowComment;
    private boolean isShowFull;
    private boolean isTrackingTouch;
    private View ivLoading;
    private ImageView ivShowSpeed;
    private View llPlayEndView;
    private LinearLayout llSeekBarTime;
    private View llSpeedDismiss;
    private LinearLayout llVideoSpeed;
    private String logJson;
    private LiuZPTLog logUpdater;
    private FrameLayout mFlPlayer;
    private ImageView mIvBack;
    private ImageView mIvMore;
    private ImageView mIvScreen;
    private ImageView mIvSound;
    private LinearLayout mLlSeekbarController;
    private SeekBar mSeekBar;
    private SlidingTabLayout mStlVideoDetails;
    private TextView mTvEndSeekBarTime;
    private TextView mTvEndTime;
    private TextView mTvStartSeekBarTime;
    private TextView mTvStartTime;
    private ImageView mVideoIV;
    private ImageView mVideoIvPlay;
    private ViewPager mVpVideoDetails;
    private Map<String, String> map;
    private int maxPercent;

    /* renamed from: pd */
    private CustomePorgressDialog f18594pd;
    private long playAtTime;
    private Timer positionTimer;
    private View rlVideoController;
    private long startTime;
    private String[] tabNames;
    private View vSpeedDismiss;
    private VideoDetailsFragment videoDetailsFragment;
    private TextView videoSpeed075;
    private TextView videoSpeed100;
    private TextView videoSpeed125;
    private TextView videoSpeed150;
    private TextView videoSpeed200;
    private Handler handler = new Handler();
    private VideoDetailsActivity activityContext = this;
    private long currentPositionInMilliSeconds = 0;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private boolean isjingyin = false;
    private boolean showAd = false;
    private boolean isMaxSetted = false;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 105);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    private void initData() {
        this.f18594pd.show();
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        String videoPlayList = URLSet.getVideoPlayList(this.group_id_str);
        UIUtils.logD("视频详情activity", "initData: " + videoPlayList);
        try {
            App.getAsyncHttpClient().rxPost(videoPlayList, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.1
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str) {
                    UIUtils.logD("视频详情activity", "onNext: " + str);
                    Gson gson = new Gson();
                    VideoDetailsEntity videoDetailsEntity = (VideoDetailsEntity) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) VideoDetailsEntity.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) VideoDetailsEntity.class));
                    if (TextUtils.equals("0000", videoDetailsEntity.getStatusCode()) && videoDetailsEntity.getData() != null) {
                        VideoDetailsActivity.this.data = videoDetailsEntity.getData();
                        VideoDetailsActivity.this.data.getUser_info().setFollow(VideoDetailsActivity.this.isFollow);
                        int i = 1;
                        VideoDetailsActivity.bdVideo.setVideoPath(VideoDetailsActivity.this.data.getVideo().getVideo_list().get(0).getMain_url(), true);
                        VideoDetailsActivity.bdVideo.setTag(VideoDetailsActivity.this.data.getVideo().getVideo_list().get(0).getMain_url());
                        VideoDetailsActivity.this.tabNames[1] = "热门评论(" + VideoDetailsActivity.this.data.getComment_count() + ")";
                        VideoDetailsActivity.this.videoDetailsFragment.setData(VideoDetailsActivity.this.data, VideoDetailsActivity.this.data.getVideo().getVideo_list().get(0).getMain_url(), VideoDetailsActivity.this.logJson, VideoDetailsActivity.this.category, VideoDetailsActivity.this.fromGid);
                        VideoDetailsActivity.this.commentFragment.setData(VideoDetailsActivity.this.data, VideoDetailsActivity.this.data.getVideo().getVideo_list().get(0).getMain_url(), VideoDetailsActivity.this.logJson);
                        SlidingTabLayout slidingTabLayout = VideoDetailsActivity.this.mStlVideoDetails;
                        if (!VideoDetailsActivity.this.isShowComment || VideoDetailsActivity.this.data.getComment_count().equals("0")) {
                            i = 0;
                        }
                        slidingTabLayout.setCurrentTab(i);
                        VideoDetailsActivity.this.mStlVideoDetails.notifyDataSetChanged();
                        VideoDetailsActivity videoDetailsActivity = VideoDetailsActivity.this;
                        videoDetailsActivity.updateLog(videoDetailsActivity.data.getVideo().getVideo_list().get(0).getMain_url(), VideoDetailsActivity.this.data.getVideo().getVideo_duration() + "");
                        if (VideoDetailsActivity.this.isShowComment) {
                            if (VideoDetailsActivity.this.map == null) {
                                VideoDetailsActivity videoDetailsActivity2 = VideoDetailsActivity.this;
                                Gson gson2 = new Gson();
                                String str2 = VideoDetailsActivity.this.logJson;
                                Type type = new TypeToken<Map<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.1.1
                                }.getType();
                                videoDetailsActivity2.map = (Map) (!(gson2 instanceof Gson) ? gson2.fromJson(str2, type) : NBSGsonInstrumentation.fromJson(gson2, str2, type));
                            }
                            String str3 = VideoDetailsActivity.bdVideo.getCurrentPosition() + "";
                            VideoDetailsActivity.this.logUpdater.setLogByCommon(VideoDetailsActivity.this.data.getVideo().getVideo_list().get(0).getMain_url(), "", "视频详情", "2", "点击按钮", "19", "视频详情", "点击热门评论", VideoDetailsActivity.this.category, VideoDetailsActivity.this.data.getVideo_id(), str3, str3, VideoDetailsActivity.this.data.getDuration(), (String) VideoDetailsActivity.this.map.get("tab_id"), (String) VideoDetailsActivity.this.map.get("tab_name"), "");
                        }
                    }
                    VideoDetailsActivity.this.f18594pd.dismiss();
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    if (VideoDetailsActivity.this.f18594pd != null && VideoDetailsActivity.this.f18594pd.isShowing()) {
                        VideoDetailsActivity.this.f18594pd.dismiss();
                    }
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        this.adv_group_fl = (FrameLayout) findViewById(2131296328);
        this.adv_fl = (FrameLayout) findViewById(2131296327);
        this.close_ad_iv = (ImageView) findViewById(2131296675);
        this.close_ad_iv.setOnClickListener(this);
        initVideoSpeedViews();
        initPlayEndView();
        playerViewForVideo = (FrameLayout) findViewById(2131297011);
        playerView = (RelativeLayout) findViewById(2131296999);
        this.rlVideoController = findViewById(2131297104);
        this.mLlSeekbarController = (LinearLayout) findViewById(2131297775);
        playerView.setOnClickListener(this);
        bdVideo = (BDCloudVideoView) findViewById(2131299502);
        this.mIvBack = (ImageView) findViewById(2131297344);
        this.mIvMore = (ImageView) findViewById(2131297441);
        this.mVideoIvPlay = (ImageView) findViewById(2131299511);
        this.mVideoIV = (ImageView) findViewById(2131299510);
        GlideApp.with((FragmentActivity) this.activityContext).load(this.imageUrl).placeholder(2131231109).error(2131231109).into(this.mVideoIV);
        this.mVideoIV.setVisibility(0);
        this.mTvStartTime = (TextView) findViewById(2131299086);
        this.llSeekBarTime = (LinearLayout) findViewById(2131297776);
        this.mTvStartSeekBarTime = (TextView) findViewById(2131299087);
        this.mTvEndSeekBarTime = (TextView) findViewById(2131298936);
        this.mSeekBar = (SeekBar) findViewById(2131298510);
        this.mTvEndTime = (TextView) findViewById(2131298935);
        this.mIvSound = (ImageView) findViewById(2131297501);
        this.mIvScreen = (ImageView) findViewById(2131297490);
        this.ivLoading = findViewById(2131297370);
        this.mStlVideoDetails = (SlidingTabLayout) findViewById(2131298705);
        this.mVpVideoDetails = (ViewPager) findViewById(2131299548);
        this.mIvBack.setOnClickListener(this);
        this.mIvMore.setOnClickListener(this);
        this.mVideoIvPlay.setOnClickListener(this);
        this.mIvSound.setOnClickListener(this);
        this.mIvScreen.setOnClickListener(this);
        this.mSeekBar.setMax(0);
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                VideoDetailsActivity videoDetailsActivity = VideoDetailsActivity.this;
                videoDetailsActivity.maxPercent = Math.max(i, videoDetailsActivity.maxPercent);
                VideoDetailsActivity.this.updatePostion(i);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                try {
                    VideoDetailsActivity.this.isTrackingTouch = true;
                    VideoDetailsActivity.this.rlVideoController.clearAnimation();
                    VideoDetailsActivity.this.llSeekBarTime.setVisibility(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Tracker.onStopTrackingTouch(seekBar);
                if (VideoDetailsActivity.bdVideo.getDuration() > 0) {
                    VideoDetailsActivity.this.currentPositionInMilliSeconds = seekBar.getProgress();
                    if (VideoDetailsActivity.bdVideo != null) {
                        VideoDetailsActivity.this.playAtTime = System.currentTimeMillis();
                        VideoDetailsActivity.bdVideo.seekTo(seekBar.getProgress());
                        VideoDetailsActivity.this.isTrackingTouch = false;
                        VideoDetailsActivity videoDetailsActivity = VideoDetailsActivity.this;
                        videoDetailsActivity.dismissStatus(videoDetailsActivity.rlVideoController);
                    }
                }
                VideoDetailsActivity.this.llSeekBarTime.setVisibility(8);
            }
        });
        this.tabNames = new String[]{"视频详情", "热门评论"};
        this.videoDetailsFragment = new VideoDetailsFragment();
        this.mFragments.add(this.videoDetailsFragment);
        this.commentFragment = new CommentFragment();
        this.mFragments.add(this.commentFragment);
        this.mStlVideoDetails.setViewPager(this.mVpVideoDetails, this.tabNames, this.activityContext, this.mFragments);
        this.mVpVideoDetails.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.3
            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                NBSActionInstrumentation.onPageSelectedEnter(i, this);
                try {
                    String str = VideoDetailsActivity.bdVideo.getCurrentPosition() + "";
                    if (VideoDetailsActivity.this.map == null) {
                        VideoDetailsActivity videoDetailsActivity = VideoDetailsActivity.this;
                        Gson gson = new Gson();
                        String str2 = VideoDetailsActivity.this.logJson;
                        Type type = new TypeToken<Map<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.3.1
                        }.getType();
                        videoDetailsActivity.map = (Map) (!(gson instanceof Gson) ? gson.fromJson(str2, type) : NBSGsonInstrumentation.fromJson(gson, str2, type));
                    }
                    if (VideoDetailsActivity.this.data != null) {
                        if (i == 1) {
                            VideoDetailsActivity.this.logUpdater.setLogByCommon(VideoDetailsActivity.this.data.getVideo().getVideo_list().get(0).getMain_url(), "", "视频详情", "2", "点击按钮", "19", "视频详情", "点击热门评论", VideoDetailsActivity.this.category, VideoDetailsActivity.this.data.getVideo_id(), str, str, VideoDetailsActivity.this.data.getDuration(), (String) VideoDetailsActivity.this.map.get("tab_id"), (String) VideoDetailsActivity.this.map.get("tab_name"), "");
                        } else {
                            VideoDetailsActivity.this.logUpdater.setLogByCommon(VideoDetailsActivity.this.data.getVideo().getVideo_list().get(0).getMain_url(), "", "视频详情", "2", "点击按钮", "19", "视频详情", "点击视频详情", VideoDetailsActivity.this.category, VideoDetailsActivity.this.data.getVideo_id(), str, str, VideoDetailsActivity.this.data.getDuration(), (String) VideoDetailsActivity.this.map.get("tab_id"), (String) VideoDetailsActivity.this.map.get("tab_name"), "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
    }

    private void initPlayer() {
        bdVideo.setVideoScalingMode(1);
        bdVideo.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$MVam5LuHQgj95afQJz71zKH0R94
            @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
            public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                VideoDetailsActivity.lambda$initPlayer$0(VideoDetailsActivity.this, playerState);
            }
        });
        setPlaySpeed(VideoCenterActivity.playSpeed, false);
        bdVideo.start();
    }

    public static /* synthetic */ void lambda$initPlayer$0(VideoDetailsActivity videoDetailsActivity, BDCloudVideoView.PlayerState playerState) {
        UIUtils.logD("xcyTest", "视频详情activity--播放状态" + playerState.toString());
        switch (playerState) {
            case STATE_PLAYING:
                videoDetailsActivity.dismissStatus(videoDetailsActivity.rlVideoController);
                videoDetailsActivity.startPositionTimer();
                videoDetailsActivity.mSeekBar.setEnabled(true);
                videoDetailsActivity.mVideoIvPlay.setEnabled(true);
                videoDetailsActivity.mVideoIvPlay.setImageResource(2131232593);
                videoDetailsActivity.mVideoIV.setVisibility(8);
                videoDetailsActivity.playAtTime = System.currentTimeMillis();
                videoDetailsActivity.hiBoardLog.hiBoardLogPlay(videoDetailsActivity.group_id_str, videoDetailsActivity.fromGid, videoDetailsActivity.category, "detail");
                if (videoDetailsActivity.isPause) {
                    UIUtils.logD("xcyTest", "关闭");
                    BDCloudVideoView bDCloudVideoView = bdVideo;
                    if (bDCloudVideoView != null && !isFull) {
                        bDCloudVideoView.pause();
                    }
                }
                if (bdVideo != null && flag) {
                    bdVideo.pause();
                }
                videoDetailsActivity.adv_group_fl.setVisibility(8);
                return;
            case STATE_PAUSED:
                videoDetailsActivity.stopPositionTimer();
                videoDetailsActivity.mVideoIvPlay.setEnabled(true);
                videoDetailsActivity.mVideoIvPlay.setImageResource(2131232592);
                videoDetailsActivity.loadPauseAd();
                return;
            case STATE_IDLE:
                videoDetailsActivity.stopPositionTimer();
                videoDetailsActivity.mVideoIvPlay.setImageResource(2131232592);
                BDCloudVideoView bDCloudVideoView2 = bdVideo;
                videoDetailsActivity.updatePostion(bDCloudVideoView2 == null ? 0 : bDCloudVideoView2.getCurrentPosition());
                BDCloudVideoView bDCloudVideoView3 = bdVideo;
                videoDetailsActivity.updateDuration(bDCloudVideoView3 != null ? bDCloudVideoView3.getDuration() : 0);
                videoDetailsActivity.adv_group_fl.setVisibility(8);
                return;
            case STATE_ERROR:
            case STATE_VIDEOSIZE_CHANGED:
            default:
                return;
            case STATE_PLAYBACK_COMPLETED:
                videoDetailsActivity.hiBoardLog.hiBoardLogOver(videoDetailsActivity.group_id_str, videoDetailsActivity.fromGid, videoDetailsActivity.category, "100", videoDetailsActivity.getDuration(), "detail");
                videoDetailsActivity.stopPositionTimer();
                SeekBar seekBar = videoDetailsActivity.mSeekBar;
                seekBar.setProgress(seekBar.getMax());
                videoDetailsActivity.mSeekBar.setEnabled(false);
                videoDetailsActivity.mVideoIvPlay.setEnabled(true);
                videoDetailsActivity.mVideoIvPlay.setImageResource(2131232592);
                videoDetailsActivity.isPlayEnd = true;
                videoDetailsActivity.maxPercent = 0;
                videoDetailsActivity.llPlayEndView.setVisibility(0);
                return;
            case STATE_PREPARING:
                videoDetailsActivity.ivLoading.setVisibility(0);
                videoDetailsActivity.mVideoIvPlay.setVisibility(8);
                videoDetailsActivity.startAnim(videoDetailsActivity.ivLoading);
                return;
            case STATE_PREPARED:
                if (bdVideo != null) {
                    videoDetailsActivity.mVideoIvPlay.setEnabled(true);
                    videoDetailsActivity.ivLoading.clearAnimation();
                    videoDetailsActivity.ivLoading.setVisibility(8);
                    videoDetailsActivity.mVideoIvPlay.setVisibility(0);
                    videoDetailsActivity.mVideoIvPlay.setImageResource(2131232592);
                    BDCloudVideoView bDCloudVideoView4 = bdVideo;
                    videoDetailsActivity.updateDuration(bDCloudVideoView4 != null ? bDCloudVideoView4.getDuration() : 0);
                    videoDetailsActivity.mSeekBar.setMax(bdVideo.getDuration());
                    return;
                }
                return;
        }
    }

    private void loadPauseAd() {
        MsLogUtil.m7979d("暂停广告加载", "开始");
        try {
            if (OptionValveUtil.INSTENCE.isShowPauseAdv()) {
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setCodeId("947099755");
                adConfigEntity.setBannerWidth(250);
                AdFactory.getAd(this.activityContext, adConfigEntity).loadInteraction(new IAdInterface.IInteractionCallBack() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.4
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onResult(int i, View view) {
                        MsLogUtil.m7979d("暂停广告加载", "成功");
                        if (view != null) {
                            try {
                                if (VideoDetailsActivity.this.adv_group_fl == null || VideoDetailsActivity.this.adv_fl == null) {
                                    return;
                                }
                                if (VideoDetailsActivity.bdVideo != null) {
                                    VideoDetailsActivity.this.adv_group_fl.setVisibility(!VideoDetailsActivity.bdVideo.isPlaying() ? 0 : 8);
                                }
                                if (VideoDetailsActivity.this.adv_fl.getChildCount() > 0) {
                                    VideoDetailsActivity.this.adv_fl.removeAllViews();
                                }
                                VideoDetailsActivity.this.showAd = true;
                                VideoDetailsActivity.this.adv_fl.addView(view);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onClose() {
                        if (VideoDetailsActivity.this.adv_group_fl != null) {
                            VideoDetailsActivity.this.adv_group_fl.setVisibility(8);
                        }
                        VideoDetailsActivity.this.showAd = false;
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            FrameLayout frameLayout = this.adv_group_fl;
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            this.showAd = false;
            MsLogUtil.m7979d("暂停广告加载", e.getMessage());
        }
    }

    private void startAnim(View view) {
        view.setAnimation(AnimationUtils.loadAnimation(this.activityContext, 2130772001));
    }

    private void updateDuration(int i) {
        TextView textView = this.mTvEndTime;
        if (textView != null) {
            textView.setText(NumUtils.stringForTime(i));
            this.mTvEndSeekBarTime.setText(NumUtils.stringForTime(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePostion(int i) {
        TextView textView = this.mTvStartTime;
        if (textView != null) {
            textView.setText(NumUtils.stringForTime(i));
            TextView textView2 = this.mTvStartSeekBarTime;
            textView2.setText(NumUtils.stringForTime(i) + "/");
        }
    }

    private void startPositionTimer() {
        Timer timer = this.positionTimer;
        if (timer != null) {
            timer.cancel();
            this.positionTimer = null;
        }
        this.positionTimer = new Timer();
        this.positionTimer.schedule(new C93265(), 0L, 500L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C93265 extends TimerTask {
        C93265() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            VideoDetailsActivity.this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$5$gaslq4ZiNlCLVPJ6hnYDlNn4Fe4
                @Override // java.lang.Runnable
                public final void run() {
                    VideoDetailsActivity.this.onPositionUpdate();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPositionUpdate() {
        BDCloudVideoView bDCloudVideoView = bdVideo;
        if (bDCloudVideoView == null) {
            return;
        }
        long currentPosition = bDCloudVideoView.getCurrentPosition();
        long j = this.currentPositionInMilliSeconds;
        if (currentPosition > 0) {
            this.currentPositionInMilliSeconds = currentPosition;
        }
        int duration = bdVideo.getDuration();
        if (duration > 0) {
            setMax(duration);
            if (j != currentPosition) {
                this.mSeekBar.setProgress((int) currentPosition);
            }
        }
    }

    private void setMax(int i) {
        if (this.isMaxSetted) {
            return;
        }
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            seekBar.setMax(i);
        }
        updateDuration(i);
        if (i > 0) {
            this.isMaxSetted = true;
        }
    }

    private void stopPositionTimer() {
        Timer timer = this.positionTimer;
        if (timer != null) {
            timer.cancel();
            this.positionTimer = null;
        }
    }

    private void goBackMain() {
        if (!TextUtils.isEmpty(this.from)) {
            finish();
            return;
        }
        Intent intent = new Intent(this, VideoCenterActivity.class);
        intent.setFlags(603979776);
        startActivity(intent);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        goBackMain();
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view != null) {
            switch (view.getId()) {
                case 2131296675:
                    this.adv_group_fl.setVisibility(8);
                    this.showAd = false;
                    break;
                case 2131296999:
                    if (this.showAd) {
                        this.adv_group_fl.setVisibility(8);
                        this.showAd = false;
                    }
                    this.rlVideoController.setVisibility(0);
                    dismissStatus(this.rlVideoController);
                    if (this.map != null) {
                        String str = bdVideo.getCurrentPosition() + "";
                        this.logUpdater.setLogByCommon(this.data.getVideo().getVideo_list().get(0).getMain_url(), "", "视频详情", "2", "点击按钮", "19", "视频详情", "点击全屏", this.category, this.data.getVideo_id(), str, str, this.data.getDuration(), this.map.get("tab_id"), this.map.get("tab_name"), "");
                        break;
                    }
                    break;
                case 2131297344:
                    BDCloudVideoView bDCloudVideoView = bdVideo;
                    if (bDCloudVideoView != null) {
                        bDCloudVideoView.pause();
                        bdVideo.stopPlayback();
                    }
                    goBackMain();
                    break;
                case 2131297441:
                    doCapture(this.data);
                    break;
                case 2131297490:
                    Intent intent = new Intent(this, VideoLandscapeActivity.class);
                    isFull = true;
                    this.mIvScreen.setVisibility(8);
                    startActivityForResult(intent, 2021);
                    this.isShowFull = true;
                    break;
                case 2131297501:
                    if (this.isjingyin) {
                        setVolumeType(1);
                        break;
                    } else {
                        setVolumeType(0);
                        break;
                    }
                case 2131299511:
                    if (bdVideo.isPlaying()) {
                        this.mVideoIvPlay.setImageResource(2131232592);
                        bdVideo.pause();
                        this.rlVideoController.setVisibility(0);
                        break;
                    } else {
                        this.mVideoIvPlay.setImageResource(2131232593);
                        if (this.isPlayEnd) {
                            bdVideo.reSetRender();
                            this.isPlayEnd = false;
                        }
                        bdVideo.start();
                        dismissStatus(this.rlVideoController);
                        break;
                    }
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private void setVolumeType(int i) {
        if (i != (!this.isjingyin)) {
            float f = i;
            bdVideo.setVolume(f, f);
            this.mIvSound.setImageResource(i == 1 ? 2131232598 : 2131232597);
            this.isjingyin = i == 0;
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 164) {
            switch (i) {
            }
            return super.onKeyDown(i, keyEvent);
        }
        setVolumeType(1);
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable @org.jetbrains.annotations.Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2021) {
            try {
                this.mIvScreen.setVisibility(0);
                playerView.findViewById(2131298388).setVisibility(0);
                ViewGroup viewGroup = (ViewGroup) playerView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                playerViewForVideo.addView(playerView, new FrameLayout.LayoutParams(-1, -1));
                this.isShowFull = false;
                UIUtils.logD("xcyTest", "关闭");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:8:0x008e
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private void doCapture(com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity.DataDTO r9) {
        /*
            r8 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r9.getTitle()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "来自中国联通APP-"
            r2.append(r3)
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO$UserInfoDTO r3 = r9.getUser_info()
            java.lang.String r3 = r3.getName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO$VideoDTO r3 = r9.getVideo()
            java.util.List r3 = r3.getVideo_list()
            r4 = 0
            java.lang.Object r3 = r3.get(r4)
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO$VideoDTO$VideoListDTO r3 = (com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity.DataDTO.VideoDTO.VideoListDTO) r3
            java.lang.String r3 = r3.getMain_url()
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO$VideoDTO r4 = r9.getVideo()
            java.lang.String r4 = r4.getPoster_url()
            java.lang.String r5 = "shortmessage,wechat,wechatmoments,qq,qzone,sinaweibo"
            java.lang.String r6 = "shareType"
            java.lang.String r7 = "url"
            r0.put(r6, r7)     // Catch: java.lang.Exception -> L8c
            java.lang.String r6 = "shareTitle"
            r0.put(r6, r1)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "shareContent"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "shareURL"
            r0.put(r1, r3)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "shareIconURL"
            r0.put(r1, r4)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "miniProgramShare"
            java.lang.String r2 = "1"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "miniProgramType"
            java.lang.String r2 = "0"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "miniProgramUserName"
            java.lang.String r2 = "gh_2bab3e2deed1"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "miniProgramPath"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8c
            r2.<init>()     // Catch: java.lang.Exception -> L8c
            java.lang.String r3 = "pages/videoshare/videoshare?groupId= ="
            r2.append(r3)     // Catch: java.lang.Exception -> L8c
            long r3 = r9.getGroup_id()     // Catch: java.lang.Exception -> L8c
            r2.append(r3)     // Catch: java.lang.Exception -> L8c
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Exception -> L8c
            r0.put(r1, r9)     // Catch: java.lang.Exception -> L8c
            goto L93
        L8c:
            r9 = move-exception
            goto L90
        L8e:
            r9 = move-exception
            r5 = 0
        L90:
            r9.printStackTrace()
        L93:
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity r9 = r8.activityContext
            boolean r1 = r0 instanceof org.json.JSONObject
            if (r1 != 0) goto L9e
            java.lang.String r0 = r0.toString()
            goto La4
        L9e:
            org.json.JSONObject r0 = (org.json.JSONObject) r0
            java.lang.String r0 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r0)
        La4:
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity$6 r1 = new com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity$6
            r1.<init>()
            com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShowShareDialog(r9, r5, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.doCapture(com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO):void");
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        BDCloudVideoView bDCloudVideoView;
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.isPause = false;
        if (isPausedByOnPause && (bDCloudVideoView = bdVideo) != null) {
            isPausedByOnPause = false;
            bDCloudVideoView.start();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.isPause = true;
        if (isHuifu) {
            isHuifu = false;
            return;
        }
        BDCloudVideoView bDCloudVideoView = bdVideo;
        if (bDCloudVideoView != null && bDCloudVideoView.isPlaying()) {
            isPausedByOnPause = true;
            bdVideo.pause();
        }
        if (isFinishing()) {
            long currentTimeMillis = System.currentTimeMillis() - this.startTime;
            this.hiBoardLog.hiBoardLogOver(this.group_id_str, this.fromGid, this.category, getPercent(), getDuration(), "detail");
            HiBoardLog hiBoardLog = this.hiBoardLog;
            String str = this.group_id_str;
            String str2 = this.fromGid;
            String str3 = this.category;
            String percent = getPercent();
            hiBoardLog.hiBoardLogStay(str, str2, str3, percent, "" + currentTimeMillis);
            this.maxPercent = 0;
            BDCloudVideoView bDCloudVideoView2 = bdVideo;
            if (bDCloudVideoView2 != null) {
                bDCloudVideoView2.stopPlayback();
                bdVideo.release();
            }
            bdVideo = null;
            stopPositionTimer();
        }
    }

    public String getPercent() {
        try {
            int max = this.mSeekBar.getMax();
            if (max > 0) {
                int i = (int) (((this.maxPercent * 1.0f) / max) * 1.0f * 100.0f);
                return "" + i;
            }
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "1";
        }
    }

    private String getDuration() {
        try {
            return "" + (System.currentTimeMillis() - this.playAtTime);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        BDCloudVideoView bDCloudVideoView = bdVideo;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.enterForeground();
        }
        NBSAppInstrumentation.activityRestartEndIns();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLog(String str, String str2) {
        if (this.logUpdater == null) {
            this.logUpdater = new LiuZPTLog(this);
        }
        Gson gson = new Gson();
        String str3 = this.logJson;
        Type type = new TypeToken<Map<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.7
        }.getType();
        this.map = (Map) (!(gson instanceof Gson) ? gson.fromJson(str3, type) : NBSGsonInstrumentation.fromJson(gson, str3, type));
        if (this.map != null) {
            String str4 = bdVideo.getCurrentPosition() + "";
            this.logUpdater.setLogByCommon(str, "", "视频详情", "2", "浏览页面", "1", "视频详情", "浏览页面", this.map.get("storey"), this.map.get("content_id"), str4, str4, str2, this.map.get("tab_id"), this.map.get("tab_name"), "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissStatus(final View view) {
        try {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.activityContext, 2130772148);
            loadAnimation.setStartOffset(2500L);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity.8
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    try {
                        if (VideoDetailsActivity.this.isTrackingTouch) {
                            view.setVisibility(0);
                        } else {
                            view.clearAnimation();
                            view.setVisibility(8);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            view.setAnimation(loadAnimation);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startVideoDetail(AppCompatActivity appCompatActivity, String str, String str2, boolean z, boolean z2, String str3, String str4) {
        Intent intent = new Intent(appCompatActivity, VideoDetailsActivity.class);
        intent.putExtra("group_id_str", str);
        intent.putExtra("isShowComment", z2);
        intent.putExtra("isFollow", z);
        intent.putExtra("category", str2);
        intent.putExtra("logJson", str3);
        intent.putExtra("imageUrl", str4);
        appCompatActivity.startActivity(intent);
    }

    @SuppressLint({"CheckResult"})
    private void initVideoSpeedViews() {
        this.llVideoSpeed = (LinearLayout) findViewById(2131297800);
        this.videoSpeed075 = (TextView) findViewById(2131299081);
        this.videoSpeed100 = (TextView) findViewById(2131299082);
        this.videoSpeed125 = (TextView) findViewById(2131299083);
        this.videoSpeed150 = (TextView) findViewById(2131299084);
        this.videoSpeed200 = (TextView) findViewById(2131299085);
        this.ivShowSpeed = (ImageView) findViewById(2131297495);
        this.vSpeedDismiss = findViewById(2131299497);
        this.llSpeedDismiss = findViewById(2131297782);
        RxView.clicks(this.ivShowSpeed).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$iRx8SyrgXHSCGmc45VJGsF27iV4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.lambda$initVideoSpeedViews$1(VideoDetailsActivity.this, obj);
            }
        });
        RxView.clicks(this.vSpeedDismiss).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$TtHnScQnueEYewpPSjp12_vCA10
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.lambda$initVideoSpeedViews$2(VideoDetailsActivity.this, obj);
            }
        });
        RxView.clicks(this.llSpeedDismiss).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$GQwkZxNs2T3MTIYW8CvPRMbzKzg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.lambda$initVideoSpeedViews$3(VideoDetailsActivity.this, obj);
            }
        });
        RxView.clicks(this.videoSpeed075).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$qQRPhy1JQUq9Fv5VQi6BAnuXuGE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.this.setPlaySpeed(75);
            }
        });
        RxView.clicks(this.videoSpeed100).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$4PNkIM6lGSYKpfSLmiQkUx8O1n4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.this.setPlaySpeed(100);
            }
        });
        RxView.clicks(this.videoSpeed125).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$h1gtjzBw26x7YSXr2aPOJGxwPmE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.this.setPlaySpeed(125);
            }
        });
        RxView.clicks(this.videoSpeed150).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$OC7sPw7qO2UaSWcV3n0yon9aUyg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.this.setPlaySpeed(150);
            }
        });
        RxView.clicks(this.videoSpeed200).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$AW4xZjPHpnUtP69a1KCfaihh35Q
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.this.setPlaySpeed(200);
            }
        });
    }

    public static /* synthetic */ void lambda$initVideoSpeedViews$1(VideoDetailsActivity videoDetailsActivity, Object obj) throws Exception {
        videoDetailsActivity.ivShowSpeed.setVisibility(8);
        videoDetailsActivity.llVideoSpeed.setVisibility(0);
    }

    public static /* synthetic */ void lambda$initVideoSpeedViews$2(VideoDetailsActivity videoDetailsActivity, Object obj) throws Exception {
        videoDetailsActivity.ivShowSpeed.setVisibility(0);
        videoDetailsActivity.llVideoSpeed.setVisibility(8);
    }

    public static /* synthetic */ void lambda$initVideoSpeedViews$3(VideoDetailsActivity videoDetailsActivity, Object obj) throws Exception {
        videoDetailsActivity.ivShowSpeed.setVisibility(0);
        videoDetailsActivity.llVideoSpeed.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlaySpeed(int i) {
        setPlaySpeed(i, true);
    }

    private void setPlaySpeed(int i, boolean z) {
        try {
            this.videoSpeed075.setTextColor(-1);
            this.videoSpeed100.setTextColor(-1);
            this.videoSpeed125.setTextColor(-1);
            this.videoSpeed150.setTextColor(-1);
            this.videoSpeed200.setTextColor(-1);
            String str = "1.0X";
            float f = 1.0f;
            if (i == 75) {
                this.videoSpeed075.setTextColor(-1703897);
                f = 0.75f;
                str = "0.75X";
            } else if (i == 100) {
                this.videoSpeed100.setTextColor(-1703897);
                str = "1.0X";
            } else if (i == 125) {
                this.videoSpeed125.setTextColor(-1703897);
                f = 1.25f;
                str = "1.25X";
            } else if (i == 150) {
                this.videoSpeed150.setTextColor(-1703897);
                f = 1.5f;
                str = "1.5X";
            } else if (i == 200) {
                this.videoSpeed200.setTextColor(-1703897);
                f = 1.9f;
                str = "2.0X";
            }
            if (bdVideo != null) {
                bdVideo.setSpeed(f);
                if (z) {
                    UIUtils.toast("已为你切换至" + str + "速度播放");
                }
            }
            VideoCenterActivity.playSpeed = i;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"CheckResult"})
    private void initPlayEndView() {
        this.llPlayEndView = findViewById(2131297756);
        this.btnReplay = findViewById(2131297697);
        this.btnShareWX = findViewById(2131297700);
        this.btnSharePYQ = findViewById(2131297698);
        this.btnShareQQ = findViewById(2131297699);
        RxView.clicks(this.btnReplay).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$-TNWJyb55jkep-Ex2g-OZ_YcmOE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.lambda$initPlayEndView$9(VideoDetailsActivity.this, obj);
            }
        });
        RxView.clicks(this.btnShareWX).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$ChxXNsfB4Ykuaeu6e47XgVz5vbA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.lambda$initPlayEndView$10(VideoDetailsActivity.this, obj);
            }
        });
        RxView.clicks(this.btnSharePYQ).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$JFugFawd6AQoR9mYpyLZJ1jCsRE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.lambda$initPlayEndView$11(VideoDetailsActivity.this, obj);
            }
        });
        RxView.clicks(this.btnShareQQ).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$nCq7v0Me0vM1v37jfm3CRh7IWaI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoDetailsActivity.lambda$initPlayEndView$12(VideoDetailsActivity.this, obj);
            }
        });
        this.llPlayEndView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.-$$Lambda$VideoDetailsActivity$0JkwNe59jxJafzb0wCu2B0wxGNA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UIUtils.logD("test", "点击空白处");
            }
        });
    }

    public static /* synthetic */ void lambda$initPlayEndView$9(VideoDetailsActivity videoDetailsActivity, Object obj) throws Exception {
        videoDetailsActivity.llPlayEndView.setVisibility(8);
        BDCloudVideoView bDCloudVideoView = bdVideo;
        if (bDCloudVideoView == null || bDCloudVideoView.getTag() == null || !(bdVideo.getTag() instanceof String)) {
            return;
        }
        String str = (String) bdVideo.getTag();
        bdVideo.stopPlayback();
        bdVideo.reSetRender();
        bdVideo.setVideoPath(str, true);
        bdVideo.setTag(str);
        bdVideo.start();
    }

    public static /* synthetic */ void lambda$initPlayEndView$10(VideoDetailsActivity videoDetailsActivity, Object obj) throws Exception {
        videoDetailsActivity.llPlayEndView.setVisibility(8);
        videoDetailsActivity.shareVideo("wechat");
    }

    public static /* synthetic */ void lambda$initPlayEndView$11(VideoDetailsActivity videoDetailsActivity, Object obj) throws Exception {
        videoDetailsActivity.llPlayEndView.setVisibility(8);
        videoDetailsActivity.shareVideo("wechatmoments");
    }

    public static /* synthetic */ void lambda$initPlayEndView$12(VideoDetailsActivity videoDetailsActivity, Object obj) throws Exception {
        videoDetailsActivity.llPlayEndView.setVisibility(8);
        videoDetailsActivity.shareVideo("qq");
    }

    public void shareVideo(String str) {
        String title = this.data.getTitle();
        String share_url = this.data.getShare_url();
        ShareManager.share(this.activityContext, ShareManager.getShareSDKKey(str), "url", "来自中国联通APP-" + this.data.getUser_info().getName(), title, this.data.getVideo().getPoster_url(), share_url, "", "1", "0", "gh_2bab3e2deed1", "pages/videoshare/videoshare?groupId= =" + this.data.getGroup_id(), false, "10010", "");
    }
}
