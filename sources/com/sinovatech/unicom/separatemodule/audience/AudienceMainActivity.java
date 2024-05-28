package com.sinovatech.unicom.separatemodule.audience;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.RecyclerView;
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
import android.widget.RelativeLayout;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.baidu.cloud.videocache.IVideoCache;
import com.baidu.cloud.videocache.ProxyCacheManager;
import com.fort.andjni.JniLib;
import com.jakewharton.rxbinding2.view.RxView;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.adpter.FmPagerAdapter;
import com.sinovatech.unicom.separatemodule.audience.adpter.LiveTabsAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionAnchorVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.HelpBtnInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveAudienceTabEntity;
import com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment;
import com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment;
import com.sinovatech.unicom.separatemodule.audience.fragment.LiveFragment;
import com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment;
import com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment;
import com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.util.CenterLayoutManager;
import com.sinovatech.unicom.separatemodule.audience.view.LiveTabItemDecoration;
import com.sinovatech.unicom.separatemodule.audience.view.NoScrollViewPager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceMainActivity extends BaseActivity implements ISmallVideo {
    public static final String RING_FLAG = "RingFlag";
    public static final String RING_INDEX = "RingIndex";
    public static final String RING_LIST = "RingList";
    public static final String RING_TAB = "RingTabTitle";
    private static final String TAG = "AudienceMainActivity";
    public static boolean bdCloudIsWebPlayer;
    public static BDCloudVideoView bdCloudVideoView;
    public static boolean isLock;
    public static boolean isNeedPay;
    public NBSTraceUnit _nbs_trace;
    private AttentionAnchorVideoEntity anchors;
    private AttentionFragment attentionFragment;
    private LiveTabsAdapter audienceTabAdapter;
    private long bufferEndTime;
    private long bufferStartTime;
    private long bufferTime;
    private String currentTabName;
    private Disposable delayedSender;
    private Disposable errorResetDely;
    private Disposable freeTips;
    private String isFromSlowLive;
    private boolean isPause;
    private boolean isPreparing;
    private boolean isShowSameCity;
    private String isToOneCity;
    private ImageView ivClose;
    private ImageView ivSearch;
    private ImageView ivSmallVideoBack;
    private Disposable kadunSub;
    private String liveChannel;
    private LiveFragment liveFragment;
    private LinearLayout llMainTitle;
    private CenterLayoutManager mLayoutManager;
    private IVideoCache mProxyCacheManager;
    private RecyclerView mTablayout;
    private NoScrollViewPager mViewPager;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private boolean mianLiuFlag;
    private FrameLayout mianLiuTipLayout;
    private RelativeLayout rlMainTop;
    private String singleVideo;
    private SlowLiveFragment slowLiveFragment;
    private SmallVideoFragment smallFragment;
    private long streamEndTime;
    private long streamStartTime;
    private Disposable subscribe;
    private TheSameCityLiveFragment theSameCityFragment;
    private String type;
    private BDCloudVideoView videoCachePlayer;
    private long videoStartTime;
    private AppCompatActivity activityContext = this;
    private boolean isPlaying = false;
    private Map<String, String> videoIds = new HashMap();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<LiveAudienceTabEntity> tabs = new ArrayList();
    private String defaultIndex = "直播";
    private LinkedHashMap<String, Fragment> tabsMap = new LinkedHashMap<>();
    public Handler viewSizeHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$HpP4xJfffDkolHvNZ-wNTjOE5Vk
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return AudienceMainActivity.lambda$new$8(AudienceMainActivity.this, message);
        }
    });

    private void upKadunLog(LogFlag logFlag) {
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    @SuppressLint({"CheckResult"})
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 67);
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

    public static /* synthetic */ void lambda$onCreate$0(AudienceMainActivity audienceMainActivity, int i, Intent intent) {
        try {
            if (App.hasLogined()) {
                audienceMainActivity.loadSameCity(audienceMainActivity.getIntent());
            } else {
                audienceMainActivity.activityContext.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSameCity(final Intent intent) {
        if (this.managerAudienceLoadData == null) {
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this);
        }
        this.managerAudienceLoadData.loadSameCityLiveList(UserManager.getInstance().getLocateCityCode(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$-smiLiPAl9wZgQG8mqF-L3n4ci8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMainActivity.lambda$loadSameCity$1(AudienceMainActivity.this, intent, (AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$bxP6-iwl9MoSH4bzkKokZUJkS9U
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMainActivity.lambda$loadSameCity$2(AudienceMainActivity.this, intent, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$loadSameCity$1(AudienceMainActivity audienceMainActivity, Intent intent, AudienceDataEntity audienceDataEntity) throws Exception {
        audienceMainActivity.isShowSameCity = "0000".equals(audienceDataEntity.getStatusCode()) && audienceDataEntity.getList() != null && audienceDataEntity.getList().size() > 0;
        audienceMainActivity.initAction(intent);
    }

    public static /* synthetic */ void lambda$loadSameCity$2(AudienceMainActivity audienceMainActivity, Intent intent, Throwable th) throws Exception {
        audienceMainActivity.isShowSameCity = false;
        audienceMainActivity.initAction(intent);
    }

    @SuppressLint({"CheckResult"})
    private void initAction(Intent intent) {
        this.rlMainTop = (RelativeLayout) findViewById(2131298360);
        this.ivSearch = (ImageView) findViewById(2131297430);
        this.ivSearch.setVisibility(OptionValveUtil.INSTENCE.isShowSearchBtn() ? 0 : 8);
        this.llMainTitle = (LinearLayout) findViewById(2131297740);
        this.llMainTitle.setVisibility(8);
        this.ivSmallVideoBack = (ImageView) findViewById(2131297500);
        RxView.clicks(this.ivSmallVideoBack).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$yWLUmaC8eHbEoqOvmyIHbN_S-Jg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMainActivity.this.finish();
            }
        });
        if (!intent.hasExtra(RING_FLAG)) {
            this.type = intent.getStringExtra("type");
            this.singleVideo = intent.getStringExtra("singleVideo");
            String stringExtra = intent.getStringExtra("userId");
            intent.getStringExtra("from");
            intent.getStringExtra("listStr");
            intent.getStringExtra("index");
            String stringExtra2 = intent.getStringExtra("shareUserNumSc");
            if (!TextUtils.isEmpty(stringExtra2)) {
                URLSet.setShareUserNumSc(stringExtra2);
            }
            this.isFromSlowLive = intent.getStringExtra("isToSlowLive");
            if (this.slowLiveFragment == null) {
                this.slowLiveFragment = new SlowLiveFragment();
            }
            this.slowLiveFragment.setUserId(stringExtra);
            if ("Y".equals(this.isFromSlowLive)) {
                this.defaultIndex = "慢直播";
            }
            if (this.liveFragment == null) {
                this.liveFragment = new LiveFragment();
            }
            this.liveFragment.setUserId(stringExtra);
            this.isToOneCity = intent.getStringExtra("isToOneCity");
            if ("Y".equals(this.isToOneCity)) {
                if (this.theSameCityFragment == null) {
                    this.theSameCityFragment = new TheSameCityLiveFragment();
                }
                this.theSameCityFragment.setUserId(stringExtra);
            }
            if (intent.hasExtra("shareInfo")) {
                String stringExtra3 = intent.getStringExtra("shareInfo");
                String stringExtra4 = intent.getStringExtra("shareProvince");
                String stringExtra5 = intent.getStringExtra("shareCity");
                String stringExtra6 = intent.getStringExtra("shareChannel");
                if (!TextUtils.isEmpty(stringExtra3.trim()) && !"null".equals(stringExtra3)) {
                    shareJoin(stringExtra, stringExtra3, stringExtra4, stringExtra5, stringExtra6);
                }
            }
        }
        this.liveChannel = intent.getStringExtra("liveChannel");
        controlTabCount();
        initView();
        initPlayer();
        if (intent.hasExtra(RING_FLAG)) {
            String stringExtra7 = intent.getStringExtra(RING_FLAG);
            String stringExtra8 = intent.getStringExtra(RING_TAB);
            String stringExtra9 = intent.getStringExtra("videoList");
            int intExtra = intent.getIntExtra("videoIndex", 0);
            int intExtra2 = intent.getIntExtra("videoPageNum", 1);
            this.liveChannel = intent.getStringExtra("liveChannel");
            this.smallFragment.setInfo(stringExtra7, stringExtra9, intExtra, intExtra2, stringExtra8);
            tabsMoveTo(this.fragments.indexOf(this.smallFragment));
            setTabVisibility(8);
        } else if (!TextUtils.isEmpty(this.type) && "smallVideo".equals(this.type)) {
            if (intent.hasExtra("videoList")) {
                this.smallFragment.setVideoInfo(intent.getStringExtra("videoList"));
                setTabVisibility(8);
                this.llMainTitle.setVisibility(0);
            }
            if (!TextUtils.isEmpty(this.singleVideo)) {
                this.smallFragment.setSingleVideo(this.singleVideo);
            }
            tabsMoveTo(this.fragments.indexOf(this.smallFragment));
        } else if ("customVideo".equals(this.type)) {
            setTabVisibility(0);
            this.llMainTitle.setVisibility(8);
            this.smallFragment.setSteamVideo(intent.getStringExtra("customVideo"));
            tabsMoveTo(this.fragments.indexOf(this.smallFragment));
        } else {
            tabsMoveTo(this.fragments.indexOf(this.liveFragment));
        }
        getAttentionListNew("1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"RestrictedApi"})
    public void tabsMoveTo(int i) {
        NoScrollViewPager noScrollViewPager = this.mViewPager;
        if (noScrollViewPager != null) {
            noScrollViewPager.setCurrentItem(i);
            List<LiveAudienceTabEntity> list = this.tabs;
            if (list == null || list.size() <= i) {
                return;
            }
            this.currentTabName = this.tabs.get(i).getTitle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tabsScroll2Position(int i) {
        try {
            this.mLayoutManager.smoothScrollToPosition(this.mTablayout, new RecyclerView.State(), i);
            int i2 = 0;
            for (int i3 = 0; i3 < this.tabs.size(); i3++) {
                LiveAudienceTabEntity liveAudienceTabEntity = this.tabs.get(i3);
                if (liveAudienceTabEntity.isSelected()) {
                    i2 = i3;
                }
                liveAudienceTabEntity.setSelected(false);
            }
            this.tabs.get(i).setSelected(true);
            this.tabs.get(i).setShowRedDot(false);
            this.audienceTabAdapter.notifyItemChanged(i2);
            this.audienceTabAdapter.notifyItemChanged(i);
        } catch (Exception unused) {
            MsLogUtil.m7979d(TAG, "tabsScroll2Position");
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        loadSameCity(intent);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public String getLiveChannel() {
        String str = this.liveChannel;
        return str == null ? "" : str;
    }

    private void controlTabCount() {
        this.tabsMap.clear();
        if (OptionValveUtil.INSTENCE.isShowTheSameCity() && this.isShowSameCity) {
            String trim = App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_SELECT_KEY).trim();
            if (this.theSameCityFragment == null) {
                this.theSameCityFragment = new TheSameCityLiveFragment();
            }
            this.tabsMap.put(trim, this.theSameCityFragment);
            if ("Y".equals(this.isToOneCity)) {
                this.defaultIndex = trim;
            }
        }
        if (OptionValveUtil.INSTENCE.isShowSlowLive()) {
            this.tabsMap.put("慢直播", this.slowLiveFragment);
            if ("Y".equals(this.isFromSlowLive)) {
                this.defaultIndex = "慢直播";
            }
        }
        if (this.attentionFragment == null) {
            this.attentionFragment = new AttentionFragment();
        }
        this.tabsMap.put("关注", this.attentionFragment);
        this.tabsMap.put("直播", this.liveFragment);
        if (this.smallFragment == null) {
            this.smallFragment = new SmallVideoFragment();
        }
        this.tabsMap.put("视频", this.smallFragment);
        this.tabs.clear();
        this.fragments = new ArrayList<>(this.tabsMap.size());
        for (Map.Entry<String, Fragment> entry : this.tabsMap.entrySet()) {
            LiveAudienceTabEntity liveAudienceTabEntity = new LiveAudienceTabEntity();
            liveAudienceTabEntity.setTitle(entry.getKey());
            liveAudienceTabEntity.setSelected(this.defaultIndex.equals(entry.getKey()));
            this.tabs.add(liveAudienceTabEntity);
            this.fragments.add(entry.getValue());
        }
    }

    @SuppressLint({"CheckResult"})
    private void initView() {
        this.mianLiuTipLayout = (FrameLayout) findViewById(2131296437);
        this.ivClose = (ImageView) findViewById(2131297429);
        this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$Z4Ri48eA5Vr49Extj3vZ5zVvNoM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMainActivity.this.returnRingList();
            }
        });
        RxView.clicks(this.ivSearch).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$v8HQUyqdEBu6b3jjdI7ZeIZMZTM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMainActivity.lambda$initView$5(AudienceMainActivity.this, obj);
            }
        });
        this.mTablayout = (RecyclerView) findViewById(2131298733);
        this.mLayoutManager = new CenterLayoutManager(this.activityContext, 0, false);
        this.mLayoutManager.setStackFromEnd(true);
        this.mTablayout.setLayoutManager(this.mLayoutManager);
        int i = this.tabs.size() < 4 ? 20 : 10;
        this.audienceTabAdapter = new LiveTabsAdapter(2131493221, this.tabs);
        this.mTablayout.setAdapter(this.audienceTabAdapter);
        this.audienceTabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$Ay_naUXNJMjnKNkTy2OZiIqkz_8
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                AudienceMainActivity.this.tabsMoveTo(i2);
            }
        });
        if (this.mTablayout.getItemDecorationCount() == 0) {
            this.mTablayout.addItemDecoration(new LiveTabItemDecoration(i, 0, i, 0, true));
        }
        this.mViewPager = (NoScrollViewPager) findViewById(2131299546);
        this.mViewPager.setAdapter(new FmPagerAdapter(this.fragments, getSupportFragmentManager()));
        this.mViewPager.addOnPageChangeListener(new C83041());
    }

    public static /* synthetic */ void lambda$initView$5(AudienceMainActivity audienceMainActivity, Object obj) throws Exception {
        AppCompatActivity appCompatActivity = audienceMainActivity.activityContext;
        IntentManager.gotoWebViewActivity(appCompatActivity, URLSet.getLiveSearch() + "&source_page=1", "搜索");
        PvCurrencyLogUtils.pvLogLive("搜索", 2, "搜索", "搜索", "直播预览页", "搜索", "搜索");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83041 implements ViewPager.OnPageChangeListener {
        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        C83041() {
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(final int i) {
            NBSActionInstrumentation.onPageSelectedEnter(i, this);
            try {
                AudienceMainActivity.this.tabsScroll2Position(i);
                AudienceMainActivity.isNeedPay = false;
                AudienceMainActivity.isLock = false;
                AudienceMainActivity.this.stopPlay();
                AudienceMainActivity.this.stopAutoJump();
                AudienceMainActivity.this.smallFragment.playEndSetLog();
                if (AudienceMainActivity.this.fragments.size() > 0) {
                    for (int i2 = 0; i2 < AudienceMainActivity.this.fragments.size(); i2++) {
                        ((BaseFragment) AudienceMainActivity.this.fragments.get(i2)).clearData();
                    }
                }
                if (AudienceMainActivity.this.delayedSender != null) {
                    AudienceMainActivity.this.delayedSender.dispose();
                    AudienceMainActivity.this.delayedSender = null;
                }
                AudienceMainActivity.this.delayedSender = Observable.timer(800L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$1$4FTN68ygLrXN-WxDbe4u1gnfJOs
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceMainActivity.C83041.lambda$onPageSelected$0(AudienceMainActivity.C83041.this, i, (Long) obj);
                    }
                });
            } catch (Exception unused) {
                MsLogUtil.m7979d(AudienceMainActivity.TAG, "滑动出问题");
            }
            NBSActionInstrumentation.onPageSelectedExit();
        }

        public static /* synthetic */ void lambda$onPageSelected$0(C83041 c83041, int i, Long l) throws Exception {
            AudienceMainActivity.this.delayedLoad(i);
            if (AudienceMainActivity.this.delayedSender != null) {
                AudienceMainActivity.this.delayedSender.dispose();
                AudienceMainActivity.this.delayedSender = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAutoJump() {
        try {
            if (this.fragments.size() > 0) {
                if (this.attentionFragment != null) {
                    this.attentionFragment.stopJumpInfo();
                }
                if (this.liveFragment != null) {
                    this.liveFragment.stopJumpInfo();
                }
                if (this.slowLiveFragment != null) {
                    this.slowLiveFragment.stopJumpInfo();
                }
                if (this.theSameCityFragment != null) {
                    this.theSameCityFragment.stopJumpInfo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delayedLoad(int i) {
        try {
            Fragment fragment = this.fragments.get(i);
            if (fragment instanceof AttentionFragment) {
                ((AttentionFragment) fragment).playLive("1");
            } else if (fragment instanceof LiveFragment) {
                ((LiveFragment) fragment).playLive("0");
            } else if (fragment instanceof SmallVideoFragment) {
                this.mianLiuFlag = false;
                if (!getIntent().hasExtra(RING_FLAG)) {
                    ((SmallVideoFragment) fragment).setInfo("", "", 0, 1, "");
                }
                ((SmallVideoFragment) fragment).playVideo();
            } else if (fragment instanceof SlowLiveFragment) {
                ((SlowLiveFragment) fragment).playLive("0");
            } else if (fragment instanceof TheSameCityLiveFragment) {
                ((TheSameCityLiveFragment) fragment).playLive("0");
            }
        } catch (Exception unused) {
            MsLogUtil.m7979d(TAG, "延时加载数据");
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
        bdCloudVideoView.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$IAD0lmzz1i5kaSBMorQ9x-fP97A
            @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
            public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                AudienceMainActivity.lambda$initPlayer$7(AudienceMainActivity.this, playerState);
            }
        });
        TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "直播", "百度播放器", "", "com.baidu.cloud.cloudplayer", "0");
        this.mProxyCacheManager = ProxyCacheManager.getInstance(this);
        bdCloudVideoView.setVideoProxy(this.mProxyCacheManager);
    }

    public static /* synthetic */ void lambda$initPlayer$7(AudienceMainActivity audienceMainActivity, BDCloudVideoView.PlayerState playerState) {
        Disposable disposable;
        UIUtils.logD("小窗测试", "播放状态---->" + playerState);
        switch (playerState) {
            case STATE_ERROR:
                if (audienceMainActivity.isPlaying) {
                    audienceMainActivity.upKadunLog(LogFlag.STREAMERROR);
                    if (audienceMainActivity.fragments.get(audienceMainActivity.mViewPager.getCurrentItem()) instanceof SmallVideoFragment) {
                        ((SmallVideoFragment) audienceMainActivity.fragments.get(audienceMainActivity.mViewPager.getCurrentItem())).upLoadVideoErrorLog("1", "播放中报错，加载失败");
                    }
                } else {
                    audienceMainActivity.upKadunLog(LogFlag.STARTERROR);
                    if (audienceMainActivity.fragments.get(audienceMainActivity.mViewPager.getCurrentItem()) instanceof SmallVideoFragment) {
                        ((SmallVideoFragment) audienceMainActivity.fragments.get(audienceMainActivity.mViewPager.getCurrentItem())).upLoadVideoErrorLog("2", "加载视频连接失败");
                    }
                }
                if (audienceMainActivity.videoIds.containsKey(bdCloudVideoView.getTag().toString())) {
                    audienceMainActivity.hiBoardLogFailed(audienceMainActivity.videoIds.get(bdCloudVideoView.getTag().toString()), bdCloudVideoView.getTag().toString(), "-1", "STATE_ERROR");
                }
                audienceMainActivity.stopSmallVideoTimer();
                return;
            case STATE_IDLE:
                Disposable disposable2 = audienceMainActivity.kadunSub;
                if (disposable2 != null && !disposable2.isDisposed()) {
                    audienceMainActivity.kadunSub.dispose();
                }
                audienceMainActivity.isPlaying = false;
                audienceMainActivity.streamStartTime = 0L;
                audienceMainActivity.bufferStartTime = 0L;
                audienceMainActivity.stopSmallVideoTimer();
                return;
            case STATE_PREPARING:
                audienceMainActivity.isPreparing = true;
                audienceMainActivity.showLoading();
                return;
            case STATE_PREPARED:
                audienceMainActivity.bufferEndTime = System.currentTimeMillis();
                if (audienceMainActivity.bufferEndTime - audienceMainActivity.bufferStartTime < 5000 && (disposable = audienceMainActivity.kadunSub) != null && !disposable.isDisposed()) {
                    audienceMainActivity.kadunSub.dispose();
                }
                audienceMainActivity.isPreparing = false;
                return;
            case STATE_PLAYING:
                if (audienceMainActivity.isPause && bdCloudVideoView != null && !bdCloudIsWebPlayer) {
                    UIUtils.logD("小窗测试", "播放拦截");
                    bdCloudVideoView.pause();
                    return;
                }
                audienceMainActivity.isPreparing = false;
                audienceMainActivity.videoStartTime = 0L;
                audienceMainActivity.showFreeTips();
                if (isNeedPay || flag || isLock) {
                    audienceMainActivity.stopPlay();
                }
                audienceMainActivity.startSmallVideoTimer();
                return;
            case STATE_PAUSED:
                audienceMainActivity.pauseSmallVideoTimer();
                return;
            case STATE_PLAYBACK_COMPLETED:
            default:
                return;
            case STATE_VIDEOSIZE_CHANGED:
                MsLogUtil.m7979d("小窗测试", "isInWeb=" + bdCloudVideoView.isInWebView + "|player=" + bdCloudIsWebPlayer);
                if (bdCloudVideoView.isInWebView) {
                    return;
                }
                audienceMainActivity.viewSizeHandler.removeCallbacksAndMessages(null);
                audienceMainActivity.viewSizeHandler.sendEmptyMessageDelayed(0, 200L);
                return;
        }
    }

    public static /* synthetic */ boolean lambda$new$8(AudienceMainActivity audienceMainActivity, Message message) {
        audienceMainActivity.setViewSizeByVideo();
        audienceMainActivity.dismissLoading();
        return false;
    }

    private void startSmallVideoTimer() {
        if (this.fragments.get(this.mViewPager.getCurrentItem()) instanceof SmallVideoFragment) {
            ((SmallVideoFragment) this.fragments.get(this.mViewPager.getCurrentItem())).timerStart();
        }
    }

    private void pauseSmallVideoTimer() {
        if (this.fragments.get(this.mViewPager.getCurrentItem()) instanceof SmallVideoFragment) {
            ((SmallVideoFragment) this.fragments.get(this.mViewPager.getCurrentItem())).timerPause();
        }
    }

    private void stopSmallVideoTimer() {
        if (this.fragments.get(this.mViewPager.getCurrentItem()) instanceof SmallVideoFragment) {
            ((SmallVideoFragment) this.fragments.get(this.mViewPager.getCurrentItem())).timerStop();
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

    private void setViewSizeByVideo() {
        try {
            MsLogUtil.m7979d("小窗测试", "直播预览，setViewSizeByVideo");
            Fragment fragment = this.fragments.get(this.mViewPager.getCurrentItem());
            if (fragment instanceof AttentionFragment) {
                ((AttentionFragment) fragment).setVideoSizeView();
            } else if (fragment instanceof LiveFragment) {
                ((LiveFragment) fragment).setVideoSizeView();
            } else if (fragment instanceof SmallVideoFragment) {
                ((SmallVideoFragment) fragment).setVideoSizeView();
            } else if (fragment instanceof SlowLiveFragment) {
                ((SlowLiveFragment) fragment).setVideoSizeView();
            } else if (fragment instanceof TheSameCityLiveFragment) {
                ((TheSameCityLiveFragment) fragment).setVideoSizeView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoading() {
        Fragment fragment = this.fragments.get(this.mViewPager.getCurrentItem());
        if (fragment instanceof AttentionFragment) {
            ((AttentionFragment) fragment).showLoading();
        } else if (fragment instanceof LiveFragment) {
            ((LiveFragment) fragment).showLoading();
        } else if (fragment instanceof SmallVideoFragment) {
            ((SmallVideoFragment) fragment).showLoading();
        } else if (fragment instanceof SlowLiveFragment) {
            ((SlowLiveFragment) fragment).showLoading();
        } else if (fragment instanceof TheSameCityLiveFragment) {
            ((TheSameCityLiveFragment) fragment).showLoading();
        }
    }

    private void dismissLoading() {
        Fragment fragment = this.fragments.get(this.mViewPager.getCurrentItem());
        if (fragment instanceof AttentionFragment) {
            AttentionFragment attentionFragment = (AttentionFragment) fragment;
            attentionFragment.dismissLoading();
            attentionFragment.dismissImg();
        } else if (fragment instanceof LiveFragment) {
            LiveFragment liveFragment = (LiveFragment) fragment;
            liveFragment.dismissLoading();
            liveFragment.dismissImg();
        } else if (fragment instanceof SmallVideoFragment) {
            SmallVideoFragment smallVideoFragment = (SmallVideoFragment) fragment;
            smallVideoFragment.dismissLoading();
            smallVideoFragment.dismissImg();
        } else if (fragment instanceof SlowLiveFragment) {
            SlowLiveFragment slowLiveFragment = (SlowLiveFragment) fragment;
            slowLiveFragment.dismissLoading();
            slowLiveFragment.dismissImg();
        } else if (fragment instanceof TheSameCityLiveFragment) {
            TheSameCityLiveFragment theSameCityLiveFragment = (TheSameCityLiveFragment) fragment;
            theSameCityLiveFragment.dismissLoading();
            theSameCityLiveFragment.dismissImg();
        }
    }

    private void xiaBo() {
        Fragment fragment = this.fragments.get(this.mViewPager.getCurrentItem());
        if (fragment instanceof AttentionFragment) {
            ((AttentionFragment) fragment).dismissLoading();
        } else if (fragment instanceof LiveFragment) {
            ((LiveFragment) fragment).dismissLoading();
        } else if (fragment instanceof SmallVideoFragment) {
            ((SmallVideoFragment) fragment).dismissLoading();
        } else if (fragment instanceof SlowLiveFragment) {
            ((SlowLiveFragment) fragment).dismissLoading();
        } else if (fragment instanceof TheSameCityLiveFragment) {
            ((TheSameCityLiveFragment) fragment).dismissLoading();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public BDCloudVideoView playVideo(String str) {
        if (TextUtils.isEmpty(str)) {
            UIUtils.logD("bdCloudVideoView", "url为空");
            xiaBo();
            return bdCloudVideoView;
        }
        paly(str);
        return bdCloudVideoView;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public BDCloudVideoView playVideo(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.videoIds.put(str, str2);
        }
        return playVideo(str);
    }

    private void paly(final String str) {
        Disposable disposable = this.subscribe;
        if (disposable != null) {
            disposable.dispose();
            this.subscribe = null;
        }
        bdCloudVideoView.stopPlayback();
        bdCloudVideoView.reSetRender();
        bdCloudVideoView.setVideoPath(str);
        bdCloudVideoView.setTag(str);
        this.subscribe = Observable.timer(400L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$BOQAOJLgAqe9IJUVSoLT5NScuY4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMainActivity.lambda$paly$9(AudienceMainActivity.this, str, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$paly$9(AudienceMainActivity audienceMainActivity, String str, Long l) throws Exception {
        UIUtils.logD("bdCloudVideoView", "当前播放：" + str);
        if (!isNeedPay) {
            bdCloudVideoView.start();
        }
        Disposable disposable = audienceMainActivity.subscribe;
        if (disposable != null) {
            disposable.dispose();
            audienceMainActivity.subscribe = null;
        }
    }

    public void stopPlay() {
        Disposable disposable = this.subscribe;
        if (disposable != null) {
            disposable.dispose();
            this.subscribe = null;
        }
        BDCloudVideoView bDCloudVideoView = bdCloudVideoView;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.stopPlayback();
            bdCloudVideoView.reSetRender();
            bdCloudVideoView.setTag("");
        }
    }

    private String getShortUrl(String str) {
        return (!str.contains(".mp4") || str.endsWith(".mp4")) ? str : str.substring(0, str.indexOf(".mp4") + 4);
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

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        UIUtils.logD("bdCloudVideoView", "onResume");
        this.isPause = false;
        BDCloudVideoView bDCloudVideoView = bdCloudVideoView;
        if (bDCloudVideoView != null) {
            boolean isPlaying = bDCloudVideoView.isPlaying();
            boolean z = bdCloudVideoView.getTag() != null;
            String str = z ? (String) bdCloudVideoView.getTag() : null;
            if (bdCloudVideoView != null && z && !TextUtils.isEmpty(str)) {
                setViewSizeByVideo();
                if (!isPlaying) {
                    if (bdCloudVideoView.getCurrentPlayerState() == BDCloudVideoView.PlayerState.STATE_PAUSED) {
                        if (!isNeedPay && !isLock) {
                            bdCloudVideoView.start();
                        }
                    } else {
                        paly(str);
                    }
                }
            }
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        UIUtils.logD("bdCloudVideoView", "onStop");
        BDCloudVideoView bDCloudVideoView = bdCloudVideoView;
        if (bDCloudVideoView != null && !bdCloudIsWebPlayer) {
            bDCloudVideoView.pause();
        }
        this.isPreparing = false;
        this.isPause = true;
        Disposable disposable = this.subscribe;
        if (disposable != null) {
            disposable.dispose();
            this.subscribe = null;
        }
        stopAutoJump();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        UIUtils.logD("bdCloudVideoView", "onDestroy");
        try {
            releaseVideo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Disposable disposable = this.freeTips;
        if (disposable != null) {
            disposable.dispose();
            this.freeTips = null;
        }
        Disposable disposable2 = this.kadunSub;
        if (disposable2 != null) {
            disposable2.dispose();
            this.kadunSub = null;
        }
        Disposable disposable3 = this.errorResetDely;
        if (disposable3 != null) {
            disposable3.dispose();
            this.errorResetDely = null;
        }
        Handler handler = this.viewSizeHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        returnRingList();
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

    public void toLiveInfo(String str, String str2) {
        Intent intent = new Intent(this, AudienceActivity.class);
        intent.putExtra("listStr", str);
        intent.putExtra("from", "fromJS");
        intent.putExtra("shareUserNumSc", "");
        intent.putExtra("index", str2);
        startActivity(intent);
    }

    private void showFreeTips() {
        if (this.mianLiuFlag || "Wifi".equals(DeviceHelper.getNETType(this.activityContext)) || UserManager.getInstance().isYiwang()) {
            return;
        }
        this.mianLiuFlag = true;
        showFreeTips(1500L);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void showFreeTips(long j) {
        this.mianLiuTipLayout.setVisibility(0);
        Disposable disposable = this.freeTips;
        if (disposable != null) {
            disposable.dispose();
        }
        this.freeTips = Observable.timer(j, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$OGAm6HoNy127u3sd6xGlnD32yG0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMainActivity.lambda$showFreeTips$10(AudienceMainActivity.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$showFreeTips$10(AudienceMainActivity audienceMainActivity, Long l) throws Exception {
        audienceMainActivity.mianLiuTipLayout.setVisibility(8);
        audienceMainActivity.freeTips.dispose();
        audienceMainActivity.freeTips = null;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void fullScreen() {
        if (getScreenOrientation(this.activityContext) == 0) {
            this.rlMainTop.setVisibility(0);
            this.mViewPager.setNoScroll(true);
            setRequestedOrientation(1);
            return;
        }
        this.rlMainTop.setVisibility(8);
        this.mViewPager.setNoScroll(false);
        setRequestedOrientation(0);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void setTabVisibility(int i) {
        this.mTablayout.setVisibility(i);
        this.ivClose.setVisibility(i);
        this.mViewPager.setNoScroll(i != 8);
        if (OptionValveUtil.INSTENCE.isShowSearchBtn()) {
            this.ivSearch.setVisibility(i);
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.ISmallVideo
    public void returnRingList() {
        if (getIntent().hasExtra(RING_FLAG)) {
            String videoListJson = this.smallFragment.getVideoListJson();
            int currentIndex = this.smallFragment.getCurrentIndex();
            Intent intent = new Intent();
            intent.putExtra(RING_LIST, videoListJson);
            intent.putExtra(RING_INDEX, currentIndex);
            setResult(3222, intent);
        }
        releaseVideo();
        finish();
    }

    private void hiBoardLogFailed(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoId", str);
        hashMap.put("videoUrl", str2);
        hashMap.put("code", str3);
        hashMap.put("msg", str4);
        this.managerAudienceLoadData.hiBoardLogFailed(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$axbkBZlgRzQlUTqAJisx6mx-duA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("lln", (String) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    private void shareJoin(String str, String str2, String str3, String str4, String str5) {
        String shareJoin = URLSet.getShareJoin(str);
        HashMap hashMap = new HashMap();
        hashMap.put("shareInfo", str2);
        hashMap.put("shareProvince", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("shareCity", UserManager.getInstance().getCurrentCityCode());
        hashMap.put("shareChannel", str5);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(shareJoin, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$ILwuQyfkvDZ362i2l0DI1Dj508k
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AudienceMainActivity.lambda$shareJoin$12((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$i_KQq_byvcYEKmxpaw1XX21kUOY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("xcyTest", (String) ((BaseVideoEntity) obj).getData());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$shareJoin$12(String str) throws Exception {
        UIUtils.logD("xcyTest", "分享引流返回==>" + str);
        JSONObject jSONObject = new JSONObject(str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        new HelpBtnInfoEntity();
        baseVideoEntity.setData(jSONObject.optString("data"));
        return baseVideoEntity;
    }

    private void getAttentionListNew(final String str) {
        if (this.managerAudienceLoadData == null) {
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this);
        }
        this.managerAudienceLoadData.getTheAnchorAvatarInfo(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMainActivity$p9KEAJI_AjxfPgNhcjVXazFrhK4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMainActivity.lambda$getAttentionListNew$14(AudienceMainActivity.this, str, (BaseVideoEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getAttentionListNew$14(AudienceMainActivity audienceMainActivity, String str, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            if ("1".equals(str)) {
                audienceMainActivity.anchors = (AttentionAnchorVideoEntity) baseVideoEntity.getData();
                int i = 0;
                while (true) {
                    if (i >= audienceMainActivity.audienceTabAdapter.getItemCount()) {
                        break;
                    }
                    LiveAudienceTabEntity item = audienceMainActivity.audienceTabAdapter.getItem(i);
                    if ("关注".equals(item.getTitle())) {
                        item.setShowRedDot("Y".equals(baseVideoEntity.getRedDotState()));
                        audienceMainActivity.audienceTabAdapter.notifyItemChanged(i);
                        break;
                    }
                    i++;
                }
            } else {
                audienceMainActivity.anchors.getAnchors().addAll(((AttentionAnchorVideoEntity) baseVideoEntity.getData()).getAnchors());
            }
            if ("-1".equals(baseVideoEntity.getNextPageNum())) {
                if (audienceMainActivity.attentionFragment != null) {
                    audienceMainActivity.anchors.setNextPageNum("-1");
                    audienceMainActivity.attentionFragment.setAttentionAnchors(audienceMainActivity.anchors);
                    return;
                }
                return;
            }
            audienceMainActivity.getAttentionListNew(baseVideoEntity.getNextPageNum());
        }
    }

    public ManagerAudienceLoadData getLoadDataManager() {
        if (this.managerAudienceLoadData == null) {
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this);
        }
        return this.managerAudienceLoadData;
    }

    public String getCurrentTabName() {
        return this.currentTabName;
    }
}
