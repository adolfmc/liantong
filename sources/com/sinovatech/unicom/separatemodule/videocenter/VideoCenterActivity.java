package com.sinovatech.unicom.separatemodule.videocenter;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.baidu.cloud.media.player.IMediaPlayer;
import com.billy.android.swipe.SwipeConsumer;
import com.bumptech.glide.Glide;
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
import com.sinovatech.unicom.basic.eventbus.VideoCenterOpenedEvent;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.p315ui.view.HomeMengcengView;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.RecommendEntity;
import com.sinovatech.unicom.separatemodule.audience.smallvideo.SmallVideoActivity;
import com.sinovatech.unicom.separatemodule.audience.view.MyGestureView;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.playdetails.channel.ChannelActivity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.videocenter.adapter.VpAdapter;
import com.sinovatech.unicom.separatemodule.videocenter.entity.NewsInfoEntity;
import com.sinovatech.unicom.separatemodule.videocenter.entity.TabEntity;
import com.sinovatech.unicom.separatemodule.videocenter.entity.VideoUserEntity;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment;
import com.sinovatech.unicom.separatemodule.videocenter.utils.HiBoardLog;
import com.sinovatech.unicom.separatemodule.videocenter.utils.LiuZPTLog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoCenterActivity extends BaseActivity {
    public static final int CHANNEL_CODE = 2111;

    /* renamed from: ak */
    private static final String f18628ak = "3927c43912004909bf897578e93bc5f9";
    public static BDCloudVideoView bdCloudVideoView = null;
    public static HiBoardLog hiBoardLog = null;
    public static boolean isShow = true;
    public static LiuZPTLog liuZPTLog;
    public static View videoPlayer;
    private String VIDEO_USER_FILE;
    public NBSTraceUnit _nbs_trace;
    private VideoCenterActivity activityContext;
    private FrameLayout adv_fl;
    private FrameLayout adv_group_fl;
    private View btnReplay;
    private View btnSharePYQ;
    private View btnShareQQ;
    private View btnShareWX;
    private long bufferStartTime;
    private long bufferTime;
    private ImageView close_ad_iv;
    private NewsInfoEntity currentNews;
    private String currentTabIndex;
    private Disposable errorResetDely;
    private FrameLayout flTemp;
    private String groupId;
    private MyGestureView gvControl;
    private boolean hasLogined;
    private ImageView iVRefresh;
    public boolean isFollowInfoUpdated;
    private boolean isPlayEnd;
    public boolean isPlaying;
    private boolean isPreparing;
    private String isShowPop;
    public boolean isTabsRefresh;
    private boolean isTrackingTouch;
    private ImageView ivBackMain;
    private View ivFullBtn;
    private View ivLoading;
    private ImageView ivMoreTabs;
    private ImageView ivPlayerStatus;
    private ImageView ivShowSpeed;
    private ImageView ivVoiceStatus;
    private String liveChannel;
    private RelativeLayout llLandscapeView;
    private View llPlayEndBg;
    private View llPlayEndView;
    private View llRefresh;
    private View llSpeedDismiss;
    private LinearLayout llVideoSpeed;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private int marginRight;
    private int maxPercent;
    private OnPausePlayListener pausePlayListener;
    private long playAtTime;
    private SeekBar progressBar;
    private ValueAnimator refreshBtnIn;
    private ValueAnimator refreshBtnOut;
    private View rlPlayerModule;
    private RotateAnimation rotateAnim;
    private SwipeConsumer smartConsumer;
    private SlidingTabLayout stlMain;
    public String stopItemId;
    private long streamEndTime;
    private long streamStartTime;
    private Disposable subscribe;
    private Disposable timer;
    private TextView tvDuration;
    private TextView tvProgressTime;
    private String typeIndex;
    private Disposable updateSeek;
    private View vPlayEndTop;
    private View vPlayer;
    private View vSpeedDismiss;
    private TextView videoSpeed075;
    private TextView videoSpeed100;
    private TextView videoSpeed125;
    private TextView videoSpeed150;
    private TextView videoSpeed200;
    private VpAdapter vpAdapter;
    private ViewPager vpContent;
    public static List<VideoUserEntity> attentionUsers = new ArrayList();
    public static int playSpeed = 100;
    public static boolean isFull = false;
    public static Map<String, String> dingCountMap = new HashMap();
    public static boolean isFlag = true;
    public static boolean isPausedByOnPause = false;
    private final String TAG = "VideoCenterActivity";
    private final int requestCodeForFull = 2816;
    private final String titleGz = "关注";
    private final String titleTj = "热门";
    private final String titleSpCl = "视频彩铃";
    private final String titleZhiBo = "直播";
    private String currentShowChannelCode = "spcl";
    private String cursor = "0";
    private boolean isNotAuto = false;
    private Gson gson = new Gson();
    private boolean showAd = false;
    private Handler btnAnimHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.5
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 0) {
                if (VideoCenterActivity.this.refreshBtnIn == null || !VideoCenterActivity.this.refreshBtnIn.isRunning()) {
                    if (VideoCenterActivity.this.refreshBtnOut != null && VideoCenterActivity.this.refreshBtnOut.isRunning()) {
                        VideoCenterActivity.this.refreshBtnOut.cancel();
                    }
                    VideoCenterActivity.this.marginRight = ((ViewGroup.MarginLayoutParams) VideoCenterActivity.this.llRefresh.getLayoutParams()).getMarginEnd();
                    if (VideoCenterActivity.this.marginRight != 0) {
                        VideoCenterActivity videoCenterActivity = VideoCenterActivity.this;
                        videoCenterActivity.refreshBtnIn = videoCenterActivity.setRefreshAnim(videoCenterActivity.marginRight, 0);
                        VideoCenterActivity.this.refreshBtnIn.start();
                    } else {
                        MsLogUtil.m7979d("VideoCenterActivity", "当前边距与目标相同，无需执行动画");
                    }
                } else {
                    MsLogUtil.m7979d("VideoCenterActivity", "入屏动画已在执行中");
                }
            } else if (VideoCenterActivity.this.refreshBtnOut == null || !VideoCenterActivity.this.refreshBtnOut.isRunning()) {
                if (VideoCenterActivity.this.refreshBtnIn != null && VideoCenterActivity.this.refreshBtnIn.isRunning()) {
                    VideoCenterActivity.this.refreshBtnIn.cancel();
                }
                VideoCenterActivity.this.marginRight = ((ViewGroup.MarginLayoutParams) VideoCenterActivity.this.llRefresh.getLayoutParams()).getMarginEnd();
                int i = -UIUtils.dip2px(51.0f);
                if (VideoCenterActivity.this.marginRight != i) {
                    VideoCenterActivity videoCenterActivity2 = VideoCenterActivity.this;
                    videoCenterActivity2.refreshBtnOut = videoCenterActivity2.setRefreshAnim(videoCenterActivity2.marginRight, i);
                    VideoCenterActivity.this.refreshBtnOut.start();
                } else {
                    MsLogUtil.m7979d("VideoCenterActivity", "当前边距与目标相同，无需执行动画");
                }
            } else {
                MsLogUtil.m7979d("VideoCenterActivity", "出屏动画已在执行中");
            }
            return false;
        }
    });
    private final String VIDEO_CENTER_TABS_CACHE = "video_center_tabs_cache";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum LogFlag {
        STARTERROR,
        PLAYINGBUFFER,
        STREAMERROR,
        TIME,
        HOMEINFO
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLog$10(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setLog$9(String str) throws Exception {
    }

    private void upKadunLog(LogFlag logFlag) {
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 116);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    public void setTempGone() {
    }

    public String getLiveChannel() {
        String str = this.liveChannel;
        return str == null ? "" : str;
    }

    public static /* synthetic */ void lambda$onCreate$0(VideoCenterActivity videoCenterActivity, int i, Intent intent) {
        if (App.hasLogined()) {
            videoCenterActivity.entrence();
            videoCenterActivity.doResume();
            return;
        }
        videoCenterActivity.activityContext.finish();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        EventBusUtils.register(this);
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        EventBusUtils.unregister(this);
        Handler handler = this.btnAnimHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void initTest() {
        this.gvControl = (MyGestureView) findViewById(2131297098);
        this.gvControl.setActivity(this);
    }

    private void entrence() {
        this.hasLogined = true;
        this.VIDEO_USER_FILE = "video_center_info_" + UserManager.getInstance().getCurrentPhoneNumber() + getResources().getString(2131886249);
        this.typeIndex = getIntent().getStringExtra("typeIndex");
        this.liveChannel = getIntent().getStringExtra("liveChannel");
        if (TextUtils.isEmpty(this.liveChannel)) {
            UIUtils.logD("VideoCenterActivity", "liveChannel为空");
        } else {
            StatisticsUploadUtils.uploadSmallVideoPVLog(this.activityContext, "S2ndpage1105", "duannei", "进入负一屏列表页", "负一屏", this.liveChannel);
        }
        this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
        hiBoardLog = new HiBoardLog(this.activityContext);
        liuZPTLog = new LiuZPTLog(this.activityContext);
        this.llLandscapeView = (RelativeLayout) findViewById(2131298352);
        this.llLandscapeView.setVisibility(8);
        initView();
        this.flTemp = (FrameLayout) findViewById(2131297010);
        getPopuWindow();
        initVideoPlayer();
        loadTabList();
        hiBoardLog();
    }

    private void getPopuWindow() {
        this.managerAudienceLoadData.isShowPopuWindow().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$95rla7mYyZ7nQtegFxHxvVfHblI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$getPopuWindow$1(VideoCenterActivity.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$sSTD3CqSr5i33LDTMqQycGEhTqY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity videoCenterActivity = VideoCenterActivity.this;
                MsLogUtil.m7979d("VideoCenterActivity", ((Throwable) obj).getMessage());
            }
        });
    }

    public static /* synthetic */ void lambda$getPopuWindow$1(VideoCenterActivity videoCenterActivity, BaseVideoEntity baseVideoEntity) throws Exception {
        videoCenterActivity.isShowPop = (String) baseVideoEntity.getData();
        if (videoCenterActivity.isShowPop.equals("Y")) {
            videoCenterActivity.getDiaLog();
        }
    }

    private void getDiaLog() {
        this.managerAudienceLoadData.getPopupWindow().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$zPII0W23Zw9THh9ZPUT-Gd1xkFA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$getDiaLog$3(VideoCenterActivity.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$KPMZw-1aiLl5H_sNmuu0NYaV2fY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity videoCenterActivity = VideoCenterActivity.this;
                MsLogUtil.m7979d("VideoCenterActivity", ((Throwable) obj).getMessage());
            }
        });
    }

    public static /* synthetic */ void lambda$getDiaLog$3(VideoCenterActivity videoCenterActivity, BaseVideoEntity baseVideoEntity) throws Exception {
        if (baseVideoEntity.getData() != null) {
            videoCenterActivity.showDailog((RecommendEntity) baseVideoEntity.getData());
        }
    }

    private void showDailog(final RecommendEntity recommendEntity) {
        final Dialog dialog = new Dialog(this, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = (int) (displayMetrics.widthPixels * 0.8d);
        Window window = dialog.getWindow();
        window.setAttributes(attributes);
        window.setDimAmount(0.6f);
        window.addFlags(2);
        View inflate = LayoutInflater.from(this).inflate(2131493310, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(2131297260);
        Glide.with((FragmentActivity) this).load(recommendEntity.getActivityImg()).error(2131232585).into(imageView);
        ((ImageView) inflate.findViewById(2131297269)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IntentManager.generateIntentAndGo(VideoCenterActivity.this, recommendEntity.getActivityJumpUrl());
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void loadTabList() {
        getTabList(getDetailsTab());
    }

    public String getDetailsTab() {
        if (this.typeIndex == null) {
            this.typeIndex = "1";
        }
        String str = this.typeIndex;
        char c = 65535;
        if (str.hashCode() == 50 && str.equals("2")) {
            c = 0;
        }
        return !TextUtils.isEmpty(this.currentTabIndex) ? this.currentTabIndex : c != 0 ? "直播" : "视频彩铃";
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable @org.jetbrains.annotations.Nullable Intent intent) {
        if (i != 2111) {
            if (i == 2333) {
                try {
                    MsLogUtil.m7979d("yunCeWenTi", "Activity-->onActivityResult");
                    ((VideoRingFragment) this.vpAdapter.getRegisteredFragment(this.vpContent.getCurrentItem())).onActivityResult(i, i2, intent);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            } else if (i != 2816) {
                return;
            } else {
                try {
                    ((VideoCommonFragment) this.vpAdapter.getRegisteredFragment(this.vpContent.getCurrentItem())).back2List(videoPlayer);
                    if (this.ivFullBtn != null) {
                        this.ivFullBtn.setVisibility(0);
                    }
                    this.adv_group_fl.setVisibility(8);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
        try {
            UIUtils.logD("xcy", "选择频道返回");
            play(null);
            if (i2 == 2112) {
                if (intent != null && intent.hasExtra("channelName")) {
                    String stringExtra = intent.getStringExtra("channelName");
                    UIUtils.logD("xcy", "选择频道返回：" + stringExtra);
                    getTabList(stringExtra);
                    return;
                }
                UIUtils.logD("xcy", "选择频道返回：刷新");
                getTabList(this.vpAdapter.getPageTitle(this.vpContent.getCurrentItem()).toString());
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void gotoVideInfo(String str, String str2, int i, int i2, String str3) {
        Intent intent = new Intent(this.activityContext, SmallVideoActivity.class);
        intent.putExtra(AudienceMainActivity.RING_FLAG, str);
        intent.putExtra(AudienceMainActivity.RING_TAB, str3);
        intent.putExtra("type", AudienceMainActivity.RING_FLAG);
        App.getSharePreferenceUtil().putString(SmallVideoActivity.SP_KEY, str2);
        intent.putExtra("videoList", SmallVideoActivity.SP_KEY);
        intent.putExtra("videoIndex", i);
        intent.putExtra("videoPageNum", i2);
        intent.putExtra("liveChannel", this.liveChannel);
        startActivityForResult(intent, 2333);
    }

    @SuppressLint({"CheckResult"})
    private void initView() {
        this.ivBackMain = (ImageView) findViewById(2131297345);
        this.ivMoreTabs = (ImageView) findViewById(2131297442);
        this.iVRefresh = (ImageView) findViewById(2131297468);
        this.llRefresh = findViewById(2131297768);
        initRefreshAnim();
        showFypMengceng();
        this.stlMain = (SlidingTabLayout) findViewById(2131298704);
        this.vpContent = (ViewPager) findViewById(2131299544);
        this.vpContent.setOffscreenPageLimit(1);
        this.vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.3
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
                    UIUtils.logD("xcy", "onPageSelected=" + i);
                    VideoCenterActivity.this.play(null);
                    VideoCenterActivity.this.currentShowChannelCode = VideoCenterActivity.this.vpAdapter.getData().get(i).getSubCode();
                    boolean z = true;
                    if (i == VideoCenterActivity.this.vpAdapter.getCount() - 1 || VideoCenterActivity.this.smartConsumer == null) {
                        boolean z2 = i == VideoCenterActivity.this.vpAdapter.getCount() - 1;
                        if (VideoCenterActivity.this.smartConsumer == null) {
                            z = false;
                        }
                        if (z2 & z) {
                            VideoCenterActivity.this.smartConsumer.enableRight();
                        }
                    } else {
                        VideoCenterActivity.this.smartConsumer.disableRight();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
        RxView.clicks(this.ivBackMain).throttleFirst(1500L, TimeUnit.MILLISECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$Nxrl4jwrAL0tPCw-JfZo7AKgwrQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.this.goMain();
            }
        });
        RxView.clicks(this.iVRefresh).throttleFirst(1500L, TimeUnit.MILLISECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$J9uX8ImswXBkVLYmMTgOr63q7Tg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$initView$6(VideoCenterActivity.this, obj);
            }
        });
        this.ivMoreTabs.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$9DpQVh5lMMB6mdZD8Dr29qhLz5Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoCenterActivity.lambda$initView$7(VideoCenterActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initView$6(VideoCenterActivity videoCenterActivity, Object obj) throws Exception {
        ImageView imageView;
        if (isFlag) {
            RotateAnimation rotateAnimation = videoCenterActivity.rotateAnim;
            if (rotateAnimation != null && (imageView = videoCenterActivity.iVRefresh) != null) {
                imageView.startAnimation(rotateAnimation);
            }
            isFlag = false;
            videoCenterActivity.refresh();
        }
    }

    public static /* synthetic */ void lambda$initView$7(VideoCenterActivity videoCenterActivity, View view) {
        videoCenterActivity.startActivityForResult(new Intent(videoCenterActivity.activityContext, ChannelActivity.class), CHANNEL_CODE);
        OnPausePlayListener onPausePlayListener = videoCenterActivity.pausePlayListener;
        if (onPausePlayListener != null) {
            onPausePlayListener.onAllTabsClicked();
        }
        try {
            if (videoCenterActivity.vpAdapter != null && videoCenterActivity.vpContent != null) {
                Fragment registeredFragment = videoCenterActivity.vpAdapter.getRegisteredFragment(videoCenterActivity.vpContent.getCurrentItem());
                if (registeredFragment instanceof VideoCommonFragment) {
                    return;
                }
                if (registeredFragment instanceof VideoRingFragment) {
                    videoCenterActivity.setLog("点击按钮", "19", "全部频道", "", "", "", "1", "2", "负一屏视频彩铃首页", "", "", "", "", "", "");
                    return;
                } else if (registeredFragment instanceof ZhiBoFragment) {
                    ((ZhiBoFragment) registeredFragment).refresh();
                    videoCenterActivity.setLog("点击按钮", "19", "全部频道", "", "", "", "1", "2", "", "", "", "", "", "", "");
                    return;
                } else {
                    MsLogUtil.m7980d("没有点击任何 Fragment");
                    return;
                }
            }
            MsLogUtil.m7980d("当前未正常初始化");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFypMengceng() {
        this.ivBackMain.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.4
            @Override // java.lang.Runnable
            public void run() {
                HomeMengcengView.showVideoCenterSmegma(VideoCenterActivity.this.activityContext, VideoCenterActivity.this.ivBackMain);
            }
        }, 1000L);
    }

    private void initRefreshAnim() {
        this.rotateAnim = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.rotateAnim.setDuration(500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ValueAnimator setRefreshAnim(int i, int i2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.setDuration(250L);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$_6EZVthCEAl9e8qHeVNc1Injy-o
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                VideoCenterActivity.lambda$setRefreshAnim$8(VideoCenterActivity.this, valueAnimator);
            }
        });
        return ofInt;
    }

    public static /* synthetic */ void lambda$setRefreshAnim$8(VideoCenterActivity videoCenterActivity, ValueAnimator valueAnimator) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i;
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        ViewGroup.LayoutParams layoutParams = videoCenterActivity.llRefresh.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            i = marginLayoutParams.bottomMargin;
        } else {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
            i = 0;
        }
        marginLayoutParams.setMargins(0, 0, intValue, i);
        videoCenterActivity.llRefresh.setLayoutParams(marginLayoutParams);
    }

    public void showRefreshBtnOut() {
        Handler handler = this.btnAnimHandler;
        if (handler != null) {
            handler.sendEmptyMessage(1);
        }
    }

    public void showRefreshBtnIn() {
        Handler handler = this.btnAnimHandler;
        if (handler != null) {
            handler.sendEmptyMessage(0);
        }
    }

    private void refresh() {
        try {
            if (this.vpAdapter != null && this.vpContent != null) {
                Fragment registeredFragment = this.vpAdapter.getRegisteredFragment(this.vpContent.getCurrentItem());
                if (registeredFragment instanceof VideoCommonFragment) {
                    ((VideoCommonFragment) registeredFragment).doRefresh();
                } else if (registeredFragment instanceof VideoRingFragment) {
                    ((VideoRingFragment) registeredFragment).refresh();
                } else if (registeredFragment instanceof ZhiBoFragment) {
                    ((ZhiBoFragment) registeredFragment).refresh();
                } else {
                    MsLogUtil.m7980d("没有点击任何 Fragment");
                }
            } else {
                MsLogUtil.m7980d("当前未正常初始化");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        goMain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goMain() {
        ViewPager viewPager;
        if (!isFlag) {
            MsLogUtil.m7979d("VideoCenterActivity", "正在刷新，无法返回");
            return;
        }
        VpAdapter vpAdapter = this.vpAdapter;
        if (vpAdapter != null && (viewPager = this.vpContent) != null) {
            Fragment registeredFragment = vpAdapter.getRegisteredFragment(viewPager.getCurrentItem());
            if (!(registeredFragment instanceof VideoCommonFragment)) {
                if (registeredFragment instanceof VideoRingFragment) {
                    setLog("点击按钮", "19", "回到首页", "", "", "", "1", "2", "负一屏视频彩铃首页", "", "", "", "", "", "");
                } else if (registeredFragment instanceof ZhiBoFragment) {
                    ((ZhiBoFragment) registeredFragment).refresh();
                    setLog("点击按钮", "19", "回到首页", "", "", "", "1", "2", "", "", "", "", "", "", "");
                } else {
                    MsLogUtil.m7980d("没有点击任何 Fragment");
                }
            }
        } else {
            MsLogUtil.m7980d("当前未正常初始化");
        }
        startActivity(new Intent(this, MainActivity.class));
    }

    private void setLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            HashMap hashMap = new HashMap();
            hashMap.put("action_id", str2);
            hashMap.put("action_type", str8);
            hashMap.put("action_name", str);
            hashMap.put("url", str4);
            hashMap.put("urlref", str5);
            hashMap.put("last_page_name", str6);
            hashMap.put("activity_type", str7);
            hashMap.put("storey", str10);
            hashMap.put("page_name", str9);
            hashMap.put("pb_name", str3);
            hashMap.put("time_spent", str11);
            hashMap.put("content_id", str12);
            hashMap.put("position", str13);
            hashMap.put("realposition", str14);
            hashMap.put("duration", str15);
            hashMap.put("ip", SystemServiceUtils.getLocalIpAddress());
            hashMap.put("os", "AND");
            hashMap.put("device_model", DeviceHelper.getDeviceModel());
            hashMap.put("os_version", DeviceHelper.getDeviceOSVersion().replace("android", ""));
            hashMap.put("channel_id", "3000011091");
            hashMap.put("content_position", "1");
            hashMap.put("system_time", simpleDateFormat.format(new Date(SystemTimeUtil.currentTimeMillis())));
            hashMap.put("local_time", simpleDateFormat.format(new Date(SystemTimeUtil.currentTimeMillis())));
            hashMap.put("client_time", simpleDateFormat.format(new Date(SystemTimeUtil.currentTimeMillis())));
            String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
            if (currentPhoneNumber.equals("0")) {
                currentPhoneNumber = "";
            }
            hashMap.put("uid", currentPhoneNumber);
            if (currentPhoneNumber.length() < 11) {
                currentPhoneNumber = RandomStringUtils.randomAlphanumeric(11);
            }
            String substring = currentPhoneNumber.substring(1);
            hashMap.put("cid", "user" + substring);
            hashMap.put("province_code", UserManager.getInstance().getCurrentProvinceCode());
            hashMap.put("city_code", UserManager.getInstance().getCurrentCityCode());
            hashMap.put("app_version", getResources().getString(2131886969));
            hashMap.put("tab_id", "spcl");
            hashMap.put("tab_name", "视频彩铃");
            hashMap.put("operateDesc", "");
            hashMap.put("tjpara", "");
            try {
                this.managerAudienceLoadData.setLogPoit(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$f4GTbvJTKrVLsEjx7ct3n3UiO1E
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        VideoCenterActivity.lambda$setLog$9((String) obj);
                    }
                });
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private void hiBoardLog() {
        try {
            this.managerAudienceLoadData.hiBoardLog("1", "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$yuS1IekADdf6zJCTJ2elYKfHwBg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCenterActivity.lambda$hiBoardLog$10((String) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTabList(String str) {
        try {
            this.currentShowChannelCode = str;
            BaseVideoEntity<List<TabEntity>> baseVideoEntity = new BaseVideoEntity<>();
            baseVideoEntity.setData(new ArrayList());
            setTabView(baseVideoEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTabView(BaseVideoEntity<List<TabEntity>> baseVideoEntity) {
        int i = 0;
        if (OptionValveUtil.INSTENCE.isShowZhiBoTab()) {
            TabEntity tabEntity = new TabEntity();
            tabEntity.setSubCode("zhibo");
            tabEntity.setPageName("直播");
            tabEntity.setWhetherShow("Y");
            baseVideoEntity.getData().add(0, tabEntity);
        }
        if (OptionValveUtil.INSTENCE.isShowRingtoneTab()) {
            TabEntity tabEntity2 = new TabEntity();
            tabEntity2.setSubCode("spcl");
            tabEntity2.setPageName("视频彩铃");
            tabEntity2.setWhetherShow("Y");
            baseVideoEntity.getData().add(0, tabEntity2);
        }
        VpAdapter vpAdapter = this.vpAdapter;
        if (vpAdapter == null) {
            ViewPager viewPager = this.vpContent;
            VpAdapter vpAdapter2 = new VpAdapter(getSupportFragmentManager(), baseVideoEntity.getData());
            this.vpAdapter = vpAdapter2;
            viewPager.setAdapter(vpAdapter2);
            this.stlMain.setViewPager(this.vpContent);
        } else {
            vpAdapter.updateList(baseVideoEntity.getData());
            this.vpAdapter.notifyDataSetChanged();
            this.stlMain.notifyDataSetChanged();
        }
        int i2 = baseVideoEntity.getData().size() > 0 ? 1 : 0;
        while (true) {
            if (i >= baseVideoEntity.getData().size()) {
                break;
            }
            TabEntity tabEntity3 = baseVideoEntity.getData().get(i);
            if (this.currentShowChannelCode.equals(tabEntity3.getPageName())) {
                this.currentShowChannelCode = tabEntity3.getSubCode();
                i2 = i;
                break;
            }
            i++;
        }
        this.stlMain.setCurrentTab(i2);
        this.isTabsRefresh = true;
        saveCache();
        this.stlMain.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$mMC0GFmtycmQmnHe5g1teXR9EmI
            @Override // java.lang.Runnable
            public final void run() {
                EventBusUtils.post(new VideoCenterOpenedEvent(0));
            }
        }, 1000L);
    }

    public void getAttentionList() {
        try {
            this.isFollowInfoUpdated = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initVideoPlayer() {
        BDCloudVideoView.setAK("3927c43912004909bf897578e93bc5f9");
        videoPlayer = LayoutInflater.from(this.activityContext).inflate(2131493293, (ViewGroup) null);
        this.adv_group_fl = (FrameLayout) videoPlayer.findViewById(2131296328);
        this.adv_fl = (FrameLayout) videoPlayer.findViewById(2131296327);
        this.close_ad_iv = (ImageView) videoPlayer.findViewById(2131296675);
        this.close_ad_iv.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                VideoCenterActivity.this.adv_group_fl.setVisibility(8);
                VideoCenterActivity.this.showAd = false;
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        bdCloudVideoView = (BDCloudVideoView) videoPlayer.findViewById(2131299506);
        bdCloudVideoView.setMaxProbeTime(200);
        bdCloudVideoView.setCachingHintViewVisibility(false);
        bdCloudVideoView.setLooping(false);
        bdCloudVideoView.setBufferTimeInMs(200);
        bdCloudVideoView.setWakeMode(this.activityContext, 1);
        bdCloudVideoView.toggleFrameChasing(false);
        bdCloudVideoView.setVideoScalingMode(1);
        bdCloudVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$JGWdI1DlADgqs5A8jmcQatZy7qE
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
            public final void onPrepared(IMediaPlayer iMediaPlayer) {
                VideoCenterActivity.lambda$initVideoPlayer$12(VideoCenterActivity.this, iMediaPlayer);
            }
        });
        bdCloudVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$kVNfFKTF2YaOid2jQFgc6tDiW6U
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
            public final boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                return VideoCenterActivity.lambda$initVideoPlayer$14(VideoCenterActivity.this, iMediaPlayer, i, i2);
            }
        });
        bdCloudVideoView.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$WpxUSIDBjkRWjcmJQdCJXhpAt5o
            @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
            public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                VideoCenterActivity.lambda$initVideoPlayer$15(VideoCenterActivity.this, playerState);
            }
        });
        bdCloudVideoView.setOnBufferingUpdateListener(new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$kMtWlGr56F8VakCyM2p-Meh4-kY
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener
            public final void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                UIUtils.logD("playerViewLog", "55555555----" + i);
            }
        });
        TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "负一屏", "百度播放器", "", "com.baidu.cloud.cloudplayer", "0");
        initVideoPlayerView();
    }

    public static /* synthetic */ void lambda$initVideoPlayer$12(VideoCenterActivity videoCenterActivity, IMediaPlayer iMediaPlayer) {
        videoCenterActivity.isPlaying = true;
        videoCenterActivity.streamEndTime = System.currentTimeMillis();
        long j = videoCenterActivity.streamStartTime;
        if (j != 0) {
            videoCenterActivity.bufferTime = videoCenterActivity.streamEndTime - j;
            videoCenterActivity.streamStartTime = 0L;
            videoCenterActivity.upKadunLog(LogFlag.TIME);
        }
    }

    public static /* synthetic */ boolean lambda$initVideoPlayer$14(VideoCenterActivity videoCenterActivity, IMediaPlayer iMediaPlayer, int i, int i2) {
        Disposable disposable = videoCenterActivity.errorResetDely;
        if (disposable != null && !disposable.isDisposed()) {
            videoCenterActivity.errorResetDely.dispose();
        }
        videoCenterActivity.errorResetDely = Observable.timer(2000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$q5-xOlZlzyrRCTkwiiAu0epR8Y4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Long l = (Long) obj;
                VideoCenterActivity.bdCloudVideoView.start();
            }
        });
        return false;
    }

    public static /* synthetic */ void lambda$initVideoPlayer$15(VideoCenterActivity videoCenterActivity, BDCloudVideoView.PlayerState playerState) {
        try {
            UIUtils.logD("playerViewLog", "播放状态---->" + playerState);
            switch (playerState) {
                case STATE_ERROR:
                    if (videoCenterActivity.isPlaying) {
                        videoCenterActivity.upKadunLog(LogFlag.STREAMERROR);
                        return;
                    } else {
                        videoCenterActivity.upKadunLog(LogFlag.STARTERROR);
                        return;
                    }
                case STATE_IDLE:
                    videoCenterActivity.isPlaying = false;
                    videoCenterActivity.streamStartTime = 0L;
                    videoCenterActivity.bufferStartTime = 0L;
                    videoCenterActivity.stopUpdateProgress();
                    return;
                case STATE_PREPARING:
                    videoCenterActivity.isPreparing = true;
                    return;
                case STATE_PREPARED:
                    videoCenterActivity.isPreparing = false;
                    return;
                case STATE_PLAYING:
                    videoCenterActivity.isPlayEnd = false;
                    videoCenterActivity.isPlaying = true;
                    videoCenterActivity.playAtTime = System.currentTimeMillis();
                    if (isPausedByOnPause || flag) {
                        UIUtils.logD("playerViewLog", "isPausedByOnPause=true|STATE_PLAYING");
                        bdCloudVideoView.pause();
                    }
                    if (videoCenterActivity.currentShowChannelCode.equals("spcl")) {
                        bdCloudVideoView.pause();
                    }
                    videoCenterActivity.startUpdateProgress();
                    videoCenterActivity.isPreparing = false;
                    if (!(videoCenterActivity.vpAdapter.getItem(videoCenterActivity.stlMain.getCurrentTab()) instanceof VideoCommonFragment)) {
                        bdCloudVideoView.stopPlayback();
                    }
                    videoCenterActivity.adv_group_fl.setVisibility(8);
                    return;
                case STATE_PAUSED:
                    videoCenterActivity.stopUpdateProgress();
                    videoCenterActivity.pausePlay();
                    if (isFull) {
                        videoCenterActivity.loadPauseAd();
                        return;
                    }
                    return;
                case STATE_PLAYBACK_COMPLETED:
                    videoCenterActivity.stopUpdateProgress();
                    videoCenterActivity.progressBar.setProgress(bdCloudVideoView.getDuration());
                    videoCenterActivity.isPlayEnd = true;
                    videoCenterActivity.llPlayEndView.setVisibility(0);
                    if (videoCenterActivity.isNotAuto) {
                        hiBoardLog.hiBoardLogOver(videoCenterActivity.groupId, "", videoCenterActivity.currentShowChannelCode, "100", videoCenterActivity.getDuration(), "list");
                    } else {
                        hiBoardLog.hiBoardLogAutoOver(videoCenterActivity.groupId, "", videoCenterActivity.currentShowChannelCode, "100", videoCenterActivity.getDuration(), "list");
                    }
                    videoCenterActivity.maxPercent = 0;
                    return;
                case STATE_VIDEOSIZE_CHANGED:
                    if (isPausedByOnPause) {
                        UIUtils.logD("playerViewLog", "isPausedByOnPause=true|STATE_VIDEOSIZE_CHANGED");
                        bdCloudVideoView.pause();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initVideoPlayerView() {
        initVideoSpeedViews();
        initPlayEndView();
        this.vPlayer = videoPlayer.findViewById(2131299500);
        this.vPlayer.setKeepScreenOn(true);
        this.rlPlayerModule = videoPlayer.findViewById(2131298369);
        this.ivPlayerStatus = (ImageView) videoPlayer.findViewById(2131297454);
        this.ivLoading = videoPlayer.findViewById(2131297370);
        this.tvProgressTime = (TextView) videoPlayer.findViewById(2131299049);
        this.progressBar = (SeekBar) videoPlayer.findViewById(2131298452);
        this.tvDuration = (TextView) videoPlayer.findViewById(2131299051);
        this.ivVoiceStatus = (ImageView) videoPlayer.findViewById(2131297452);
        this.ivFullBtn = videoPlayer.findViewById(2131297453);
        this.vPlayer.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$3F4ltlrR5MNwKIPEku36X12H-k8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoCenterActivity.lambda$initVideoPlayerView$17(VideoCenterActivity.this, view);
            }
        });
        this.ivVoiceStatus.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$6dB6IjO_1Al2ML5l48ap91fpypA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoCenterActivity.lambda$initVideoPlayerView$18(VideoCenterActivity.this, view);
            }
        });
        this.ivFullBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$xifalZrzGBfNbB4jER-v2OyA4GQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoCenterActivity.lambda$initVideoPlayerView$19(VideoCenterActivity.this, view);
            }
        });
        this.progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                VideoCenterActivity videoCenterActivity = VideoCenterActivity.this;
                videoCenterActivity.maxPercent = Math.max(i, videoCenterActivity.maxPercent);
                if (VideoCenterActivity.this.tvProgressTime != null) {
                    VideoCenterActivity.this.tvProgressTime.setText(NumUtils.stringForTime(i));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                try {
                    VideoCenterActivity.this.isTrackingTouch = true;
                    VideoCenterActivity.this.rlPlayerModule.clearAnimation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Tracker.onStopTrackingTouch(seekBar);
                try {
                    if (VideoCenterActivity.bdCloudVideoView == null || VideoCenterActivity.bdCloudVideoView.getDuration() <= 0) {
                        return;
                    }
                    VideoCenterActivity.bdCloudVideoView.seekTo(seekBar.getProgress());
                    VideoCenterActivity.this.playAtTime = System.currentTimeMillis();
                    VideoCenterActivity.this.isTrackingTouch = false;
                    VideoCenterActivity.this.dismissStatus(VideoCenterActivity.this.rlPlayerModule);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        resetView();
    }

    public static /* synthetic */ void lambda$initVideoPlayerView$17(VideoCenterActivity videoCenterActivity, View view) {
        try {
            if (videoCenterActivity.rlPlayerModule.getVisibility() == 0) {
                if (videoCenterActivity.isPlayEnd) {
                    videoCenterActivity.play(bdCloudVideoView.getCurrentPlayingUrl());
                } else {
                    videoCenterActivity.playOrPause();
                }
            } else {
                videoCenterActivity.rlPlayerModule.setVisibility(0);
                videoCenterActivity.ivPlayerStatus.setVisibility(0);
                if (bdCloudVideoView.isPlaying()) {
                    videoCenterActivity.dismissStatus(videoCenterActivity.rlPlayerModule);
                    UIUtils.logD("dismissStatus", "vPlayer.setOnClickListener");
                }
            }
            if (videoCenterActivity.showAd) {
                videoCenterActivity.adv_group_fl.setVisibility(8);
                videoCenterActivity.showAd = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initVideoPlayerView$18(VideoCenterActivity videoCenterActivity, View view) {
        try {
            if (view.getTag() == null) {
                videoCenterActivity.setVolumeType(0);
            } else {
                videoCenterActivity.setVolumeType(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initVideoPlayerView$19(VideoCenterActivity videoCenterActivity, View view) {
        videoCenterActivity.toFull();
        OnPausePlayListener onPausePlayListener = videoCenterActivity.pausePlayListener;
        if (onPausePlayListener != null) {
            onPausePlayListener.onFullScreenClicked();
        }
    }

    private void setVolumeType(int i) {
        int i2 = this.ivVoiceStatus.getTag() == null ? 1 : 0;
        if (i != i2) {
            float f = i;
            bdCloudVideoView.setVolume(f, f);
            this.ivVoiceStatus.setImageResource(i == 1 ? 2131231658 : 2131231657);
            this.ivVoiceStatus.setTag(i == 1 ? null : "0");
            return;
        }
        MsLogUtil.m7979d("VideoCenterActivity", i2 + "|" + i);
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

    private void startUpdateProgress() {
        try {
            UIUtils.logD("playerViewLog", "开始更新播放进度");
            if (this.ivLoading != null) {
                this.ivLoading.clearAnimation();
                this.ivLoading.setVisibility(8);
            }
            this.progressBar.setMax(bdCloudVideoView.getDuration());
            this.tvDuration.setText(NumUtils.stringForTime(this.progressBar.getMax()));
            this.rlPlayerModule.setVisibility(0);
            this.rlPlayerModule.clearAnimation();
            this.ivPlayerStatus.setImageResource(2131231659);
            this.ivPlayerStatus.setVisibility(0);
            this.rlPlayerModule.setTag(null);
            dismissStatus(this.rlPlayerModule);
            UIUtils.logD("playerViewLog", "startUpdateProgress");
            stopUpdateProgress();
            UIUtils.logD("playerViewLog", "startUpdateProgress");
            this.updateSeek = Observable.interval(800L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$I9EPbG5_YfPV5M64xrXSc9fHVT8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCenterActivity.lambda$startUpdateProgress$20(VideoCenterActivity.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$startUpdateProgress$20(VideoCenterActivity videoCenterActivity, Long l) throws Exception {
        UIUtils.logD("playerViewLog", "progress=" + bdCloudVideoView.getCurrentPosition());
        videoCenterActivity.progressBar.setProgress(bdCloudVideoView.getCurrentPosition());
    }

    private void stopUpdateProgress() {
        Disposable disposable = this.updateSeek;
        if (disposable != null) {
            disposable.dispose();
            this.updateSeek = null;
        }
    }

    private void pausePlay() {
        try {
            this.ivPlayerStatus.setVisibility(0);
            this.ivPlayerStatus.setImageResource(2131231660);
            this.rlPlayerModule.setTag("pause");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playOrPause() {
        try {
            if (this.rlPlayerModule.getTag() == null) {
                UIUtils.logD("playerViewLog", "暂停");
                bdCloudVideoView.pause();
                if (this.pausePlayListener != null) {
                    this.pausePlayListener.onPauseClicked();
                }
            } else {
                UIUtils.logD("playerViewLog", "播放");
                bdCloudVideoView.start();
                if (this.pausePlayListener != null) {
                    this.pausePlayListener.onPlayClicked();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPercent() {
        try {
            int max = this.progressBar.getMax();
            if (max > 0) {
                int i = (int) (((this.maxPercent * 1.0f) / max) * 1.0f * 100.0f);
                this.maxPercent = 0;
                return "" + i;
            }
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "1";
        }
    }

    public String getDuration() {
        try {
            return (System.currentTimeMillis() - this.playAtTime) + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    private void startAnim(View view) {
        view.setAnimation(AnimationUtils.loadAnimation(this.activityContext, 2130772001));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissStatus(final View view) {
        try {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.activityContext, 2130772148);
            loadAnimation.setStartOffset(2500L);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.8
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    try {
                        if (VideoCenterActivity.this.isTrackingTouch) {
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

    public String getCurrentShowCode() {
        try {
            UIUtils.logD("xcy", "当前码=" + this.currentShowChannelCode);
            return this.currentShowChannelCode;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void interceptorRequest(String str) {
        this.currentShowChannelCode = "拦截" + str;
    }

    public String getCurrentTab() {
        try {
            return this.vpAdapter.getData().get(this.vpContent.getCurrentItem()).getSubCode();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getCurrentTabName() {
        try {
            return this.vpAdapter.getData().get(this.vpContent.getCurrentItem()).getPageName();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void play(String str, String str2, boolean z, String str3) {
        this.groupId = str2;
        this.isNotAuto = z;
        try {
            JSONObject jSONObject = new JSONObject(str3);
            NewsInfoEntity newsInfoEntity = new NewsInfoEntity();
            newsInfoEntity.setName(jSONObject.optString("name"));
            newsInfoEntity.setTitle(jSONObject.optString("title"));
            newsInfoEntity.setCoverImageUrl(jSONObject.optString("coverImageUrl"));
            newsInfoEntity.setShare_url(jSONObject.optString("share_url"));
            newsInfoEntity.setGroup_id(jSONObject.optLong("group_id"));
            this.currentNews = newsInfoEntity;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        play(str);
    }

    public void play(final String str) {
        try {
            if (this.subscribe != null) {
                this.subscribe.dispose();
                this.subscribe = null;
            }
            if (TextUtils.isEmpty(str)) {
                if (this.isNotAuto) {
                    hiBoardLog.hiBoardLogOver(this.groupId, "", this.currentShowChannelCode, getPercent(), getDuration(), "list");
                } else {
                    hiBoardLog.hiBoardLogAutoOver(this.groupId, "", this.currentShowChannelCode, getPercent(), getDuration(), "list");
                }
            }
            bdCloudVideoView.stopPlayback();
            bdCloudVideoView.reSetRender();
            if (TextUtils.isEmpty(str)) {
                ViewGroup viewGroup = (ViewGroup) videoPlayer.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                this.stopItemId = this.groupId;
                return;
            }
            resetView();
            bdCloudVideoView.setVideoPath(str, true);
            bdCloudVideoView.setTag(str);
            this.subscribe = Observable.timer(500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$2mv35XpnKnAevwyOcN1ivArIQTc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCenterActivity.lambda$play$21(VideoCenterActivity.this, str, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$play$21(VideoCenterActivity videoCenterActivity, String str, Long l) throws Exception {
        UIUtils.logD("playerViewLog", "当前播放：" + str);
        bdCloudVideoView.start();
        Disposable disposable = videoCenterActivity.subscribe;
        if (disposable != null) {
            disposable.dispose();
            videoCenterActivity.subscribe = null;
        }
    }

    public void hiBoardLogPlay(String str) {
        try {
            if (this.vpAdapter != null && this.vpContent != null && hiBoardLog != null) {
                hiBoardLog.hiBoardLogPlay(str, "", this.vpAdapter.getData().get(this.vpContent.getCurrentItem()).getSubCode(), "list");
            }
            this.isNotAuto = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toFull() {
        Intent intent = new Intent(this.activityContext, VideoLandscapeActivity.class);
        intent.putExtra(VideoLandscapeActivity.AC_TYPE, "video_center");
        startActivityForResult(intent, 2816);
    }

    private void loadPauseAd() {
        MsLogUtil.m7979d("暂停广告加载", "开始");
        try {
            if (OptionValveUtil.INSTENCE.isShowPauseAdv()) {
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setCodeId("947099755");
                adConfigEntity.setBannerWidth(250);
                AdFactory.getAd(this.activityContext, adConfigEntity).loadInteraction(new IAdInterface.IInteractionCallBack() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.9
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onResult(int i, View view) {
                        MsLogUtil.m7979d("暂停广告加载", "成功");
                        if (view != null) {
                            try {
                                if (VideoCenterActivity.this.adv_group_fl == null || VideoCenterActivity.this.adv_fl == null) {
                                    return;
                                }
                                if (VideoCenterActivity.bdCloudVideoView != null) {
                                    FrameLayout frameLayout = VideoCenterActivity.this.adv_group_fl;
                                    int i2 = 8;
                                    if (!VideoCenterActivity.bdCloudVideoView.isPlaying() && VideoCenterActivity.isFull) {
                                        i2 = 0;
                                    }
                                    frameLayout.setVisibility(i2);
                                }
                                if (VideoCenterActivity.this.adv_fl.getChildCount() > 0) {
                                    VideoCenterActivity.this.adv_fl.removeAllViews();
                                }
                                VideoCenterActivity.this.adv_fl.addView(view);
                                VideoCenterActivity.this.showAd = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onClose() {
                        if (VideoCenterActivity.this.adv_group_fl != null) {
                            VideoCenterActivity.this.adv_group_fl.setVisibility(8);
                        }
                        VideoCenterActivity.this.showAd = false;
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

    private void resetView() {
        try {
            this.ivPlayerStatus.setVisibility(8);
            this.ivPlayerStatus.setImageResource(2131231659);
            this.rlPlayerModule.setTag(null);
            this.ivLoading.setVisibility(0);
            startAnim(this.ivLoading);
            this.tvProgressTime.setText("00:00");
            this.progressBar.setProgress(0);
            this.tvDuration.setText("00:00");
            bdCloudVideoView.setVolume(1.0f, 1.0f);
            this.ivVoiceStatus.setImageResource(2131231658);
            this.ivVoiceStatus.setTag(null);
            this.ivFullBtn.setVisibility(0);
            this.llPlayEndView.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public View getVideoPlayer() {
        ImageView imageView = this.ivShowSpeed;
        if (imageView != null && this.llVideoSpeed != null) {
            imageView.setVisibility(0);
            this.llVideoSpeed.setVisibility(8);
        }
        return videoPlayer;
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        try {
            if (this.hasLogined) {
                doResume();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void doResume() {
        setPlaySpeed(playSpeed, false);
        getAttentionList();
        UIUtils.logD("xcyLoadData", "onResume");
        if (isPausedByOnPause) {
            isPausedByOnPause = false;
            if (bdCloudVideoView != null && !this.currentShowChannelCode.replace("拦截", "").equals("spcl")) {
                bdCloudVideoView.start();
            }
            this.rlPlayerModule.setTag(null);
        }
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.vpAdapter != null) {
                bundle.putString("currentTabName", this.vpAdapter.getPageTitle(this.vpContent.getCurrentItem()).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            UIUtils.logD("playerViewLog", "onPause");
            isPausedByOnPause = true;
            if (bdCloudVideoView != null) {
                bdCloudVideoView.pause();
            }
            if (isFinishing()) {
                UIUtils.logD("playerViewLog", "onDestroy");
                saveCache();
                stopUpdateProgress();
                play(null);
                if (bdCloudVideoView != null) {
                    bdCloudVideoView.stopPlayback();
                    bdCloudVideoView.release();
                }
                bdCloudVideoView = null;
                playSpeed = 100;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isAttention(String str) {
        try {
            for (VideoUserEntity videoUserEntity : attentionUsers) {
                if (videoUserEntity.getUserId().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void attentionUser(VideoUserEntity videoUserEntity, boolean z) {
        try {
            if (z) {
                attentionUsers.add(0, videoUserEntity);
                return;
            }
            Iterator<VideoUserEntity> it = attentionUsers.iterator();
            while (it.hasNext()) {
                if (it.next().getUserId().equals(videoUserEntity.getUserId())) {
                    it.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnPausePlayListener(OnPausePlayListener onPausePlayListener) {
        if (onPausePlayListener != null) {
            this.pausePlayListener = onPausePlayListener;
        }
    }

    private void saveCache() {
        try {
            if (this.vpAdapter == null || this.vpAdapter.getData().size() <= 0) {
                return;
            }
            Gson gson = this.gson;
            List<TabEntity> data = this.vpAdapter.getData();
            App.getSharePreferenceUtil().putString(this.VIDEO_USER_FILE, "video_center_tabs_cache", !(gson instanceof Gson) ? gson.toJson(data) : NBSGsonInstrumentation.toJson(gson, data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<TabEntity> loadTabsCache() {
        try {
            String string = App.getSharePreferenceUtil().getString(this.VIDEO_USER_FILE, "video_center_tabs_cache");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            new ArrayList();
            Gson gson = this.gson;
            Type type = new TypeToken<List<TabEntity>>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity.10
            }.getType();
            return (List) (!(gson instanceof Gson) ? gson.fromJson(string, type) : NBSGsonInstrumentation.fromJson(gson, string, type));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint({"CheckResult"})
    private void initVideoSpeedViews() {
        this.llVideoSpeed = (LinearLayout) videoPlayer.findViewById(2131297800);
        this.videoSpeed075 = (TextView) videoPlayer.findViewById(2131299081);
        this.videoSpeed100 = (TextView) videoPlayer.findViewById(2131299082);
        this.videoSpeed125 = (TextView) videoPlayer.findViewById(2131299083);
        this.videoSpeed150 = (TextView) videoPlayer.findViewById(2131299084);
        this.videoSpeed200 = (TextView) videoPlayer.findViewById(2131299085);
        this.ivShowSpeed = (ImageView) videoPlayer.findViewById(2131297495);
        this.vSpeedDismiss = videoPlayer.findViewById(2131299497);
        this.llSpeedDismiss = videoPlayer.findViewById(2131297782);
        RxView.clicks(this.ivShowSpeed).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$GcepHmDesaZlErLDZT1wekT7rVc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$initVideoSpeedViews$22(VideoCenterActivity.this, obj);
            }
        });
        RxView.clicks(this.vSpeedDismiss).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$2w_lUttPPUspO4_T9YLJs5C2dA0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$initVideoSpeedViews$23(VideoCenterActivity.this, obj);
            }
        });
        RxView.clicks(this.llSpeedDismiss).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$s1OtESquEdWw2miidqx6uW2z0DU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$initVideoSpeedViews$24(VideoCenterActivity.this, obj);
            }
        });
        RxView.clicks(this.videoSpeed075).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$7ILYtiz4giJOW7p_wSHZ2-pwxwE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.this.setPlaySpeed(75);
            }
        });
        RxView.clicks(this.videoSpeed100).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$FM62-7qPCEc4r5Ik0SZ0NwQKN7A
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.this.setPlaySpeed(100);
            }
        });
        RxView.clicks(this.videoSpeed125).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$SQnNrQfkiDawjhjZzMXCP__IYuc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.this.setPlaySpeed(125);
            }
        });
        RxView.clicks(this.videoSpeed150).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$yICW-vv4ARofjUdW1-vBJ5GcbvE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.this.setPlaySpeed(150);
            }
        });
        RxView.clicks(this.videoSpeed200).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$R-SHOdivdKB61acJvFgEGg-0AZQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.this.setPlaySpeed(200);
            }
        });
    }

    public static /* synthetic */ void lambda$initVideoSpeedViews$22(VideoCenterActivity videoCenterActivity, Object obj) throws Exception {
        videoCenterActivity.ivShowSpeed.setVisibility(8);
        videoCenterActivity.llVideoSpeed.setVisibility(0);
    }

    public static /* synthetic */ void lambda$initVideoSpeedViews$23(VideoCenterActivity videoCenterActivity, Object obj) throws Exception {
        videoCenterActivity.ivShowSpeed.setVisibility(0);
        videoCenterActivity.llVideoSpeed.setVisibility(8);
    }

    public static /* synthetic */ void lambda$initVideoSpeedViews$24(VideoCenterActivity videoCenterActivity, Object obj) throws Exception {
        videoCenterActivity.ivShowSpeed.setVisibility(0);
        videoCenterActivity.llVideoSpeed.setVisibility(8);
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
            if (bdCloudVideoView != null) {
                bdCloudVideoView.setSpeed(f);
                if (z) {
                    UIUtils.toast("已为你切换至" + str + "速度播放");
                }
            }
            playSpeed = i;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"CheckResult"})
    private void initPlayEndView() {
        this.llPlayEndView = videoPlayer.findViewById(2131297756);
        this.vPlayEndTop = videoPlayer.findViewById(2131299490);
        this.llPlayEndBg = videoPlayer.findViewById(2131297757);
        this.btnReplay = videoPlayer.findViewById(2131297697);
        this.btnShareWX = videoPlayer.findViewById(2131297700);
        this.btnSharePYQ = videoPlayer.findViewById(2131297698);
        this.btnShareQQ = videoPlayer.findViewById(2131297699);
        RxView.clicks(this.btnReplay).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$4BelccKGBCmjrk2TV2N2C3mC9Vo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$initPlayEndView$30(VideoCenterActivity.this, obj);
            }
        });
        RxView.clicks(this.btnShareWX).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$AT0EdFwecqo2GJ1ydOTeg7Ovv1k
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$initPlayEndView$31(VideoCenterActivity.this, obj);
            }
        });
        RxView.clicks(this.btnSharePYQ).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$gaYZ00qxSe4kX6yUS06P5FjKVWo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$initPlayEndView$32(VideoCenterActivity.this, obj);
            }
        });
        RxView.clicks(this.btnShareQQ).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$E0ZH1_H070mHrfYPlmwfbceuvJM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCenterActivity.lambda$initPlayEndView$33(VideoCenterActivity.this, obj);
            }
        });
        this.llPlayEndView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoCenterActivity$asSdn_3cFflIYMd-ovC4qNAcV7Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UIUtils.logD("xcy", "点击了背景");
            }
        });
    }

    public static /* synthetic */ void lambda$initPlayEndView$30(VideoCenterActivity videoCenterActivity, Object obj) throws Exception {
        videoCenterActivity.llPlayEndView.setVisibility(8);
        BDCloudVideoView bDCloudVideoView = bdCloudVideoView;
        if (bDCloudVideoView == null || bDCloudVideoView.getTag() == null || !(bdCloudVideoView.getTag() instanceof String)) {
            return;
        }
        videoCenterActivity.play((String) bdCloudVideoView.getTag());
        videoCenterActivity.hiBoardLogPlay(videoCenterActivity.groupId);
    }

    public static /* synthetic */ void lambda$initPlayEndView$31(VideoCenterActivity videoCenterActivity, Object obj) throws Exception {
        videoCenterActivity.llPlayEndView.setVisibility(8);
        videoCenterActivity.shareVideo("wechat");
    }

    public static /* synthetic */ void lambda$initPlayEndView$32(VideoCenterActivity videoCenterActivity, Object obj) throws Exception {
        videoCenterActivity.llPlayEndView.setVisibility(8);
        videoCenterActivity.shareVideo("wechatmoments");
    }

    public static /* synthetic */ void lambda$initPlayEndView$33(VideoCenterActivity videoCenterActivity, Object obj) throws Exception {
        videoCenterActivity.llPlayEndView.setVisibility(8);
        videoCenterActivity.shareVideo("qq");
    }

    public void shareVideo(String str) {
        ShareManager.share(this.activityContext, ShareManager.getShareSDKKey(str), "url", "来自中国联通APP-" + this.currentNews.getName(), this.currentNews.getTitle(), this.currentNews.getCoverImageUrl(), this.currentNews.getShare_url(), "", "1", "0", "gh_2bab3e2deed1", "pages/videoshare/videoshare?groupId= =" + this.currentNews.getGroup_id(), false, "10010", "");
    }
}
