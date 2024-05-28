package com.sinovatech.unicom.basic.p315ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnTwoLevelListener;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.header.TwoLevelHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.sinovatech.unicom.basic.eventbus.FragmentOnResumeEvent;
import com.sinovatech.unicom.basic.eventbus.HomeBGEvent;
import com.sinovatech.unicom.basic.eventbus.HomeGuidEvent;
import com.sinovatech.unicom.basic.eventbus.HomeJumpTabEvent;
import com.sinovatech.unicom.basic.eventbus.NetEvent;
import com.sinovatech.unicom.basic.eventbus.QiandaoEvent;
import com.sinovatech.unicom.basic.eventbus.SearchXinRenLiBaoEvent;
import com.sinovatech.unicom.basic.p314po.HomeTabEntity;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.home.adapter.UnicomMainHomePagerAdapter;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeEntity;
import com.sinovatech.unicom.basic.p315ui.home.fragment.UnicomHomeTuiJianFragment;
import com.sinovatech.unicom.basic.p315ui.home.manager.HomeDropDownManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.HomeMergeInfoManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.HomeTabManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeTopBgManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomToolBarManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomXuanFuChuangManager;
import com.sinovatech.unicom.basic.p315ui.manager.HomeSearchNewUserManager;
import com.sinovatech.unicom.basic.p315ui.manager.MultilingualManager;
import com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil;
import com.sinovatech.unicom.basic.p315ui.utils.ListEqualsUtils;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginMemberManager;
import com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout;
import com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout2;
import com.sinovatech.unicom.basic.view.decklayout.ConsecutiveViewPager;
import com.sinovatech.unicom.basic.view.decklayout.RecyclerTabLayout;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.view.MyDragView;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.dialog.popup.HomeTabMenuWindow;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity;
import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntity;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import com.sinovatech.unicom.separatemodule.skin.event.RefreshSkinEvent;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomHomeFragment extends BaseFragment {
    private static final String TAG = "UnicomHomeFragment";
    public static String currentPhone = "";
    public static boolean isBackGround;
    private HomeWebFragment YingYeTingWebFragment;
    public NBSTraceUnit _nbs_trace;
    private HomeDropDownManager dropDownManager;
    private HomeTabEntity.DataDTO homeOldTabEntity;
    private HomeTabEntity.DataDTO homeTabEntity;
    private HomeTabManager homeTabManager;
    private Handler logHandler;
    private ConsecutiveScrollerLayout mCSLYingYeTingLayout;
    private View mCacheView;
    private ImageView mImgRefeshImg;
    private ImageView mImgScrollTop;
    private ImageView mImgSecond;
    private LinearLayout mLinToolBarLayout;
    private UnicomMainHomePagerAdapter<BaseWebFragment> mPagerAdapter;
    private SmartRefreshLayout mSmartRefresh;
    private LinearLayout mTabLayout;
    private RecyclerTabLayout mTabView;
    private TextView mTvRefreshText;
    private TwoLevelHeader mTwoLevelHeader;
    private ConsecutiveViewPager mViewPager;
    private View mViewTopBg;
    private MainActivity mainActivity;
    private HomeMergeInfoManager mergeInfoManager;
    private MyDragView myDragView;
    private ConsecutiveScrollerLayout2 scrollerLayout;
    private UnicomToolBarManager toolBarManager;
    private UnicomHomeTopBgManager topBgManager;
    private UnicomHomeTuiJianFragment tuiJianFragment;
    private UnicomXuanFuChuangManager xuanFuChuangManager;
    private int dropDownState = 0;
    private String dropDownToWebUrl = "";
    private boolean isShowHome = true;
    private boolean isFrontAndRearSwitching = false;
    private boolean isRefesh = false;
    private int currentIndex = 0;
    private int topMargin = UIUtils.dip2px(8.0f);
    public boolean isRefreshTab = false;
    public boolean isBigFont = false;
    private Runnable logRunnable = new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.25
        @Override // java.lang.Runnable
        public void run() {
            UnicomHomeFragment.this.upLoadExposureLog();
        }
    };

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        this.logHandler = new Handler();
        new ConfigManager().getActivityDomainString();
        EventBusUtils.register(this);
        this.mainActivity = (MainActivity) getActivity();
        if (TextUtils.equals("0", currentPhone)) {
            currentPhone = "";
        }
        isBackGround = true;
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment", this);
        super.onStart();
        MsLogUtil.m7979d("1qidong ", "UnicomHomeFragment >>> onStart");
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment", viewGroup);
        MsLogUtil.m7979d("1qidong ", "UnicomHomeFragment >>> onCreateView");
        View view = this.mCacheView;
        if (view != null) {
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment");
            return view;
        }
        this.mCacheView = layoutInflater.inflate(2131493488, viewGroup, false);
        initView();
        setGongJiRi();
        initManager();
        initListener();
        ShareLogUtil.getDefaultShare(this.mainActivity);
        View view2 = this.mCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment");
        return view2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:1|(2:2|3)|4|(4:5|6|(2:8|(2:10|(1:14)))|15)|(13:20|(1:22)(1:44)|23|24|(1:27)|28|(1:32)|33|34|35|36|37|38)|45|(1:47)|48|(1:52)|53|24|(1:27)|28|(2:30|32)|33|34|35|36|37|38) */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0172, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0173, code lost:
        com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7978e(r0.getMessage());
     */
    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onResume() {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.fragment.UnicomHomeFragment.onResume():void");
    }

    public void checkBottomToastStatus(boolean z) {
        UnicomToolBarManager unicomToolBarManager = this.toolBarManager;
        if (unicomToolBarManager != null) {
            unicomToolBarManager.checkCityAndToastStatus(z);
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
        ConsecutiveViewPager consecutiveViewPager = this.mViewPager;
        if (consecutiveViewPager != null) {
            consecutiveViewPager.setAdapter(null);
        }
    }

    public void initView() {
        this.mSmartRefresh = (SmartRefreshLayout) this.mCacheView.findViewById(2131299193);
        this.mTwoLevelHeader = (TwoLevelHeader) this.mCacheView.findViewById(2131299194);
        this.mImgSecond = (ImageView) this.mCacheView.findViewById(2131299192);
        this.mTvRefreshText = (TextView) this.mCacheView.findViewById(2131299186);
        this.mImgRefeshImg = (ImageView) this.mCacheView.findViewById(2131299185);
        this.scrollerLayout = (ConsecutiveScrollerLayout2) this.mCacheView.findViewById(2131298478);
        this.mViewPager = (ConsecutiveViewPager) this.mCacheView.findViewById(2131299187);
        this.mViewPager.setSaveEnabled(true);
        this.mViewPager.setPagingEnabled(false);
        this.mViewTopBg = this.mCacheView.findViewById(2131299191);
        this.mLinToolBarLayout = (LinearLayout) this.mCacheView.findViewById(2131299181);
        this.mLinToolBarLayout.setEnabled(false);
        this.mTabView = (RecyclerTabLayout) this.mCacheView.findViewById(2131299178);
        this.mTabLayout = (LinearLayout) this.mCacheView.findViewById(2131297201);
        this.mCSLYingYeTingLayout = (ConsecutiveScrollerLayout) this.mCacheView.findViewById(2131299190);
        this.mImgScrollTop = (ImageView) this.mCacheView.findViewById(2131297192);
        this.mImgScrollTop.setVisibility(8);
        this.myDragView = (MyDragView) this.mCacheView.findViewById(2131297215);
        this.isShowHome = true;
        CollectConfig.isShowHome = this.isShowHome;
        this.mLinToolBarLayout.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.1
            @Override // java.lang.Runnable
            public void run() {
                ViewGroup.LayoutParams layoutParams = UnicomHomeFragment.this.mViewPager.getLayoutParams();
                layoutParams.height = UnicomHomeFragment.this.mainActivity.getMainTabLayoutHeight() - UnicomHomeFragment.this.mLinToolBarLayout.getHeight();
                UnicomHomeFragment.this.mViewPager.setLayoutParams(layoutParams);
            }
        }, 10L);
    }

    private void initManager() {
        try {
            this.mergeInfoManager = new HomeMergeInfoManager(this.mainActivity);
            this.toolBarManager = new UnicomToolBarManager(getActivity(), this.mLinToolBarLayout, this.mCacheView);
            this.toolBarManager.setScrollerLayout(this.scrollerLayout);
            this.topBgManager = new UnicomHomeTopBgManager(this.mViewTopBg, getActivity());
            this.topBgManager.setBarChangeColorListener(new UnicomHomeTopBgManager.OnToolBarBackgroundColorListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.2
                @Override // com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeTopBgManager.OnToolBarBackgroundColorListener
                public void toolBarBackgroundColor(String str) {
                    if (TextUtils.isEmpty(str)) {
                        str = "#E60027";
                    }
                    UnicomHomeFragment.this.toolBarManager.setToolBarBackgroundColor(str);
                }
            });
            this.xuanFuChuangManager = new UnicomXuanFuChuangManager();
            this.xuanFuChuangManager.initView(this.myDragView);
            this.homeTabManager = new HomeTabManager(this.mainActivity);
            this.dropDownManager = new HomeDropDownManager();
            this.dropDownManager.setStateListener(new HomeDropDownManager.OnDropDownStateListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.3
                @Override // com.sinovatech.unicom.basic.p315ui.home.manager.HomeDropDownManager.OnDropDownStateListener
                public void onState(int i, String str) {
                    UnicomHomeFragment.this.dropDownToWebUrl = str;
                    UnicomHomeFragment.this.dropDownState = i;
                    UnicomHomeFragment.this.setDropDownType(i);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化监听异常:" + e.getMessage());
        }
    }

    public void setDropDownType(int i) {
        if (i == 1) {
            dropDownGoToWeb();
        } else if (i == 3) {
            dropDownOpenTwoLevel();
        } else {
            dropDownCloseTwoLevel();
        }
    }

    public void dropDownCloseTwoLevel() {
        this.mImgSecond.setVisibility(8);
        this.mTwoLevelHeader.setEnableTwoLevel(false);
    }

    private void dropDownGoToWeb() {
        this.mImgSecond.setVisibility(0);
        this.mTwoLevelHeader.setEnableTwoLevel(true);
        if (TextUtils.isEmpty(this.dropDownToWebUrl)) {
            this.mTwoLevelHeader.setEnableTwoLevel(false);
        }
    }

    private void dropDownOpenTwoLevel() {
        this.mImgSecond.setVisibility(0);
        this.mTwoLevelHeader.setEnableTwoLevel(true);
    }

    public void changeYingYeTing(WebParamsEntity webParamsEntity, boolean z) {
        try {
            this.isShowHome = false;
            CollectConfig.isShowHome = this.isShowHome;
            this.topBgManager.changeTopBgStatus();
            this.scrollerLayout.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    UnicomHomeFragment.this.scrollerLayout.scrollTo(0, 0);
                }
            }, 10L);
            if (z) {
                this.YingYeTingWebFragment = HomeWebFragment.newIntence(webParamsEntity, -1);
                getChildFragmentManager().beginTransaction().replace(2131299190, this.YingYeTingWebFragment).commitAllowingStateLoss();
            } else if (this.YingYeTingWebFragment != null) {
                this.YingYeTingWebFragment.setUserVisibleHint(true);
            }
            try {
                PvCurrencyLogUtils.pvLogMainDJ("1010102", webParamsEntity.getUrl() + "", webParamsEntity.getMiniProgramUrl() + "", webParamsEntity.getTitle() + "", "首页Tab");
                UnicomHomeLogUtils.getInstance().clickLog("1010102", "首页附近营业厅");
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
            dropDownCloseTwoLevel();
            try {
                if (this.mViewPager != null && this.mViewPager.getCurrentItem() == 0) {
                    this.tuiJianFragment.setUserVisibleHint(false);
                } else if (this.mPagerAdapter != null) {
                    ((HomeWebFragment) this.mPagerAdapter.getShowFragment()).onPauseLazy(false);
                }
            } catch (Exception e2) {
                MsLogUtil.m7977e(TAG, "营业厅设置首页是否可见时异常:" + e2.getMessage());
            }
            this.mTabLayout.setVisibility(8);
            this.mViewPager.setVisibility(8);
            this.mLinToolBarLayout.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ViewGroup.LayoutParams layoutParams = UnicomHomeFragment.this.mCSLYingYeTingLayout.getLayoutParams();
                        layoutParams.height = UnicomHomeFragment.this.mainActivity.getMainTabLayoutHeight() - UnicomHomeFragment.this.mLinToolBarLayout.getHeight();
                        UnicomHomeFragment.this.mCSLYingYeTingLayout.setLayoutParams(layoutParams);
                    } catch (Exception e3) {
                        MsLogUtil.m7977e(UnicomHomeFragment.TAG, "获取营业厅高度异常:" + e3.getMessage());
                    }
                }
            });
            this.mCSLYingYeTingLayout.setVisibility(0);
            resetLogHandlerCallback();
            PermissionDialog.show("附近营业厅需要您授予中国联通APP位置权限，以开启与位置相关的推荐/搜索/安全保障服务。");
            final boolean checkPermissions = UIUtils.checkPermissions("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
            new RxPermissions(this.mainActivity).request("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.6
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (checkPermissions || !bool.booleanValue() || UnicomHomeFragment.this.YingYeTingWebFragment == null) {
                        return;
                    }
                    UnicomHomeFragment.this.YingYeTingWebFragment.reloadWeb();
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    PermissionDialog.dimissDialog();
                }
            });
            this.xuanFuChuangManager.setXuanfuchuangHideOrVisable(this.isShowHome);
        } catch (Exception e3) {
            MsLogUtil.m7977e(TAG, "顶部切换营业厅异常:" + e3.getMessage());
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:1|2|(4:3|4|(1:43)(1:8)|9)|10|(3:36|37|(11:41|15|16|(1:18)|19|(1:21)(1:32)|22|23|24|25|27))|12|(1:14)|15|16|(0)|19|(0)(0)|22|23|24|25|27) */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d3, code lost:
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00d4, code lost:
        com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7978e(r6.getMessage());
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a4 A[Catch: Exception -> 0x00df, TryCatch #0 {Exception -> 0x00df, blocks: (B:2:0x0000, B:12:0x0049, B:25:0x00a0, B:27:0x00a4, B:28:0x00a9, B:32:0x00b5, B:37:0x00db, B:36:0x00d4, B:24:0x0085, B:11:0x002e, B:3:0x0013, B:5:0x0017, B:7:0x001f, B:8:0x0027, B:14:0x0062, B:16:0x0066, B:18:0x006e, B:19:0x0074, B:21:0x0078, B:33:0x00b8), top: B:42:0x0000, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void changeHome(boolean r6) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.fragment.UnicomHomeFragment.changeHome(boolean):void");
    }

    public void initListener() {
        try {
            this.toolBarManager.setHeadTabChangeListener(new UnicomToolBarManager.OnHeadTabChangeListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.9
                @Override // com.sinovatech.unicom.basic.p315ui.home.manager.UnicomToolBarManager.OnHeadTabChangeListener
                public void onHome(boolean z) {
                    UnicomHomeFragment.this.changeHome(z);
                }

                @Override // com.sinovatech.unicom.basic.p315ui.home.manager.UnicomToolBarManager.OnHeadTabChangeListener
                public void onFuJin(WebParamsEntity webParamsEntity, boolean z) {
                    UnicomHomeFragment.this.changeYingYeTing(webParamsEntity, z);
                }
            });
            this.toolBarManager.setChangeCityListener(new UnicomToolBarManager.OnChangeCityListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.10
                @Override // com.sinovatech.unicom.basic.p315ui.home.manager.UnicomToolBarManager.OnChangeCityListener
                public void changeCity() {
                    UnicomHomeFragment.this.loadHomeTab("1", true, "HF 切换左上角地市");
                    UnicomHomeFragment.this.loadMergeInfo();
                }
            });
            this.mTwoLevelHeader.setOnTwoLevelListener(new OnTwoLevelListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.11
                @Override // com.scwang.smartrefresh.layout.api.OnTwoLevelListener
                public boolean onTwoLevel(@NonNull RefreshLayout refreshLayout) {
                    String str = "";
                    if (UnicomHomeFragment.this.dropDownState != 3) {
                        if (UnicomHomeFragment.this.dropDownState == 1) {
                            str = "下拉进入H5";
                            IntentManager.generateIntentAndGo(UnicomHomeFragment.this.mainActivity, UnicomHomeFragment.this.dropDownToWebUrl, "中国联通", false, "get");
                        }
                    } else {
                        str = "下拉进入小程序";
                        UnicomHomeFragment.this.startActivity(new Intent(UnicomHomeFragment.this.getActivity(), CustomRecentActivity.class));
                    }
                    if (!TextUtils.isEmpty(str)) {
                        PvCurrencyLogUtils.pvLogMainDJ("1010409", "", str, "", "首页-下拉刷新");
                        UnicomHomeLogUtils.getInstance().clickLog("1010409", str);
                    }
                    return false;
                }
            });
            this.mSmartRefresh.setHeaderHeight(60.0f);
            this.mSmartRefresh.setDragRate(0.7f);
            this.mSmartRefresh.setOnMultiPurposeListener(new SimpleMultiPurposeListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.12
                @Override // com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener, com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    refreshLayout.finishLoadMore(1000);
                }

                @Override // com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener, com.scwang.smartrefresh.layout.listener.OnRefreshListener
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refreshLayout.finishRefresh(2000);
                    UnicomHomeFragment.this.pullToRefresh();
                }

                @Override // com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener, com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
                public void onHeaderMoving(RefreshHeader refreshHeader, boolean z, float f, int i, int i2, int i3) {
                    Log.d("onHeaderMoving", "onHeaderMoving() isDragging = [" + z + "], percent = [" + f + "], offset = [" + i + "], headerHeight = [" + i2 + "], maxDragHeight = [" + i3 + "]");
                    if (UnicomHomeFragment.this.isRefesh) {
                        UnicomHomeFragment.this.mTvRefreshText.setVisibility(0);
                        UnicomHomeFragment.this.mImgRefeshImg.setVisibility(0);
                    }
                    UnicomHomeFragment.this.mImgSecond.setTranslationY(Math.min(i - UnicomHomeFragment.this.mImgSecond.getHeight(), UnicomHomeFragment.this.mSmartRefresh.getLayout().getHeight() - UnicomHomeFragment.this.mImgSecond.getHeight()));
                }

                @Override // com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener, com.scwang.smartrefresh.layout.listener.OnStateChangedListener
                public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
                    UnicomHomeFragment.this.isRefesh = true;
                    MsLogUtil.m7979d("onStateChanged", refreshState2 + "");
                    if (refreshState2 == RefreshState.PullDownToRefresh) {
                        UnicomHomeFragment.this.mTvRefreshText.setText("下拉刷新");
                        UnicomHomeFragment.this.mTvRefreshText.setVisibility(0);
                        UnicomHomeFragment.this.mImgRefeshImg.setVisibility(0);
                        UnicomHomeFragment.this.mImgRefeshImg.setRotation(0.0f);
                    } else if (refreshState2 == RefreshState.ReleaseToRefresh) {
                        UnicomHomeFragment.this.mTvRefreshText.setText("松开刷新");
                        UnicomHomeFragment.this.mTvRefreshText.setVisibility(0);
                        UnicomHomeFragment.this.mImgRefeshImg.setVisibility(0);
                        UnicomHomeFragment.this.mImgRefeshImg.setRotation(180.0f);
                    } else if (refreshState2 == RefreshState.ReleaseToTwoLevel) {
                        UnicomHomeFragment.this.mTvRefreshText.setText("释放有惊喜");
                        UnicomHomeFragment.this.mTvRefreshText.setVisibility(0);
                        UnicomHomeFragment.this.mImgRefeshImg.setVisibility(0);
                    } else if (refreshState2 == RefreshState.Refreshing) {
                        UnicomHomeFragment.this.mTvRefreshText.setText("刷新中");
                        UnicomHomeFragment.this.mTvRefreshText.setVisibility(0);
                        UnicomHomeFragment.this.mImgRefeshImg.setVisibility(8);
                    } else if (refreshState2 == RefreshState.None || refreshState2 == RefreshState.RefreshFinish) {
                        UnicomHomeFragment.this.isRefesh = false;
                        UnicomHomeFragment.this.mTvRefreshText.setVisibility(8);
                        UnicomHomeFragment.this.mImgRefeshImg.setVisibility(8);
                        UnicomHomeFragment.this.mTvRefreshText.setText("");
                        UnicomHomeFragment.this.mImgRefeshImg.setRotation(0.0f);
                    }
                }
            });
            this.scrollerLayout.setOnVerticalScrollChangeListener(new ConsecutiveScrollerLayout2.OnScrollChangeListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.13
                @Override // com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout2.OnScrollChangeListener
                public void onScrollChange(View view, int i, int i2, int i3) {
                    try {
                        if (UnicomHomeFragment.this.isBigFont || !UnicomHomeFragment.this.isShowHome || i <= UIUtils.getScreenHeight((Activity) UnicomHomeFragment.this.mainActivity)) {
                            UnicomHomeFragment.this.mImgScrollTop.setVisibility(8);
                        } else {
                            UnicomHomeFragment.this.mImgScrollTop.setVisibility(0);
                        }
                        UnicomHomeFragment.this.toolBarManager.changeToolBackground();
                        UnicomHomeFragment.this.toolBarManager.changeTabStatus(i, i2);
                        if (UnicomHomeFragment.this.isRefesh) {
                            UnicomHomeFragment.this.mTvRefreshText.setVisibility(8);
                            UnicomHomeFragment.this.mImgRefeshImg.setVisibility(8);
                            UnicomHomeFragment.this.mImgRefeshImg.setRotation(0.0f);
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7977e(UnicomHomeFragment.TAG, "滑动异常:" + e.getMessage());
                    }
                }
            });
            this.mImgScrollTop.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (UnicomHomeFragment.this.scrollerLayout != null) {
                        UnicomHomeFragment.this.scrollerLayout.smoothScrollToChild(UnicomHomeFragment.this.mViewTopBg);
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.scrollerLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.15
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    UnicomHomeFragment.this.removeHandlerCallback();
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        UnicomHomeFragment.this.resetLogHandlerCallback();
                        return false;
                    }
                    return false;
                }
            });
            this.mTabView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.16
                @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0) {
                        UnicomHomeFragment.this.addTabBaoGuangLog();
                    }
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化监听异常:" + e.getMessage());
        }
    }

    public void addTabBaoGuangLog() {
        try {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mTabView.getLayoutManager();
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            List<CharSequence> list = this.mPagerAdapter.getmFragmentTitle();
            if (findFirstVisibleItemPosition < 0) {
                findFirstVisibleItemPosition = 0;
            }
            if (findLastVisibleItemPosition < 5) {
                findLastVisibleItemPosition = list.size() > 5 ? 5 : list.size() - 1;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new HomeLogEntity(String.valueOf(1090101), this.mPagerAdapter.getCurrentTabTitle(0)));
            while (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
                if (findFirstVisibleItemPosition != 0) {
                    arrayList.add(new HomeLogEntity(String.valueOf(1090100 + findFirstVisibleItemPosition + 1), list.get(findFirstVisibleItemPosition).toString()));
                }
                findFirstVisibleItemPosition++;
            }
            arrayList.add(new HomeLogEntity(String.valueOf(1090201), "首页瀑布流标签-更多"));
            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_TAB, arrayList);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "添加曝光日志异常:" + e.getMessage());
        }
    }

    private void initViewPager(HomeTabEntity.DataDTO.IndexSelectedTabDTO indexSelectedTabDTO, boolean z, boolean z2) {
        int i;
        boolean z3;
        try {
            MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "初始化ViewPager 是否强制刷新 = " + z2);
            final List<HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO> tabCfgArray = indexSelectedTabDTO.getTabCfgArray();
            if (tabCfgArray != null && tabCfgArray.size() != 0) {
                boolean z4 = true;
                UnicomHomeConstants.isTabVisiable = true;
                this.toolBarManager.setIndexSelectedTabOpenFlag();
                if (this.homeTabEntity != null && this.homeOldTabEntity != null && !z2 && this.homeOldTabEntity.getMiniProgramOpenUrlFlag() == this.homeTabEntity.getMiniProgramOpenUrlFlag() && ListEqualsUtils.isEquals(tabCfgArray, this.homeOldTabEntity.getIndexSelectedTab().getTabCfgArray())) {
                    MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "初始化ViewPager 缓存数据和接口数据相同 不执行初始化操作 是否强制刷新 = " + z2);
                    return;
                }
                this.toolBarManager.resetHeadTabState();
                UnicomHomeConstants.isInitViewPager = true;
                if (this.homeTabEntity != null) {
                    z3 = this.homeTabEntity.getMiniProgramOpenUrlFlag();
                    i = this.homeTabEntity.getIndexHeadTabTimeout();
                } else {
                    i = 5;
                    z3 = false;
                }
                ArrayList arrayList = new ArrayList();
                this.mPagerAdapter = new UnicomMainHomePagerAdapter<>(this);
                for (int i2 = 0; i2 < tabCfgArray.size(); i2++) {
                    HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO tabCfgArrayDTO = tabCfgArray.get(i2);
                    WebParamsEntity webParamsEntity = new WebParamsEntity();
                    webParamsEntity.setTitle(tabCfgArrayDTO.getNavName());
                    webParamsEntity.setUrl(tabCfgArrayDTO.getH5Url());
                    webParamsEntity.setMiniProgramUrl(tabCfgArrayDTO.getAppletUrl());
                    webParamsEntity.setNavCode(tabCfgArrayDTO.getNavCode());
                    webParamsEntity.setMiniProgramOpenUrlFlag(z3);
                    webParamsEntity.setIndexHeadTabTimeout(i);
                    arrayList.add(tabCfgArrayDTO.getNavCode());
                    if (i2 == 0) {
                        addTJFragment(webParamsEntity);
                    } else {
                        this.mPagerAdapter.addFragment(HomeWebFragment.newIntence(webParamsEntity, i2), tabCfgArrayDTO.getNavName());
                    }
                }
                int currentItem = this.mViewPager.getCurrentItem();
                this.mViewPager.setAdapter(this.mPagerAdapter);
                if (App.hasLogined() && UnicomHomeConstants.isJsStartLogin2 && currentItem != 0) {
                    this.topBgManager.loadTabDefaultBg();
                    this.mTabView.choseItem(currentItem);
                    UnicomHomeConstants.isJsStartLogin2 = false;
                    z4 = false;
                }
                if (z4) {
                    this.topBgManager.restBgState(101);
                }
                UnicomHomeConstants.isInitViewPager = false;
                this.mTabView.setUpWithViewPager(this.mCacheView.findViewById(2131299180), this.mViewPager);
                this.mTabView.setIPagerSelect(new RecyclerTabLayout.IPagerSelect() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.17
                    @Override // com.sinovatech.unicom.basic.view.decklayout.RecyclerTabLayout.IPagerSelect
                    public void onSeclect(int i3) {
                        HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO tabCfgArrayDTO2;
                        MsLogUtil.m7979d("TUIJIAN_TAB", i3 + "");
                        try {
                            UnicomHomeFragment.this.currentIndex = i3;
                        } catch (Exception e) {
                            e.printStackTrace();
                            MsLogUtil.m7979d("TUIJIAN_TAB", "tab栏点击异常\n" + e.getMessage());
                        }
                        if (i3 == 0) {
                            UnicomHomeFragment.this.topBgManager.restBgState(102);
                            if (UnicomHomeFragment.this.tuiJianFragment != null) {
                                UnicomHomeFragment.this.tuiJianFragment.scrollTop();
                            }
                        } else {
                            UnicomHomeFragment.this.topBgManager.loadTabDefaultBg();
                            try {
                                ((HomeWebFragment) UnicomHomeFragment.this.mPagerAdapter.getItem(i3)).scrollTop();
                            } catch (Exception unused) {
                            }
                        }
                        UnicomHomeFragment.this.xuanFuChuangManager.setXuanfuchuangHideOrVisable(i3 == 0);
                        List list = tabCfgArray;
                        if (list != null && list.size() > 0 && (tabCfgArrayDTO2 = (HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO) tabCfgArray.get(i3)) != null) {
                            String valueOf = String.valueOf(1090100 + i3 + 1);
                            PvCurrencyLogUtils.pvLogMainDJ(valueOf + "", tabCfgArrayDTO2.getH5Url(), tabCfgArrayDTO2.getAppletUrl(), tabCfgArrayDTO2.getNavName() + "", "首页瀑布流标签位置" + (i3 + 1));
                            UnicomHomeLogUtils.getInstance().clickLog(valueOf, tabCfgArrayDTO2.getNavName());
                        }
                        UnicomHomeFragment.this.resetLogHandlerCallback();
                        UnicomHomeFragment.this.scrollerLayout.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.17.1
                            @Override // java.lang.Runnable
                            public void run() {
                                UnicomHomeFragment.this.scrollerLayout.scrollTo(0, 0);
                            }
                        }, 50L);
                    }
                });
                addTabBaoGuangLog();
                final View findViewById = this.mCacheView.findViewById(2131299179);
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$UnicomHomeFragment$d_g0yk6BZg9G2K_NY20M2tNIMIs
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UnicomHomeFragment.lambda$initViewPager$0(UnicomHomeFragment.this, tabCfgArray, findViewById, view);
                    }
                });
                this.mTabLayout.setVisibility(0);
                resetLogHandlerCallback();
                return;
            }
            MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "初始化ViewPager tab数据为空 不执行初始化操作 是否强制刷新 = " + z2);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化viewpager异常:" + e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initViewPager$0(UnicomHomeFragment unicomHomeFragment, List list, View view, View view2) {
        new HomeTabMenuWindow.Builder(unicomHomeFragment.getActivity()).setList(list).setTabView(unicomHomeFragment.mTabView).showAsDropDown(view);
        PvCurrencyLogUtils.pvLogMainDJ("1090201", "", "", "", "首页瀑布流标签位置-更多");
        UnicomHomeLogUtils.getInstance().clickLog("1090201", "首页瀑布流标签位置-更多");
    }

    public void addTJFragment(WebParamsEntity webParamsEntity) {
        try {
            if (TextUtils.isEmpty(webParamsEntity.getUrl())) {
                webParamsEntity.setUrl(URLSet.getHomeTuiJianDefUrlAndCumpUrl("h5Url"));
            }
            MsLogUtil.m7979d("推荐页面", "创建推荐页面");
            if (this.tuiJianFragment != null) {
                this.tuiJianFragment.removeAllView();
                this.tuiJianFragment = null;
            }
            UnicomHomeConstants.tuijian_refresh_type = 1;
            MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "添加推荐页面");
            this.tuiJianFragment = UnicomHomeTuiJianFragment.getInstance(webParamsEntity);
            this.mPagerAdapter.addFragment(this.tuiJianFragment, webParamsEntity.getTitle());
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化推荐页面异常:" + e.getMessage());
        }
    }

    public void loadMergeInfo() {
        try {
            MsLogUtil.m7979d(UnicomHomeConstants.LAZY_TAG_DATA, "刷新动态感知和悬浮窗");
            this.mergeInfoManager.getHomeMerge().subscribe(new Consumer<HomeMergeEntity>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.18
                @Override // io.reactivex.functions.Consumer
                public void accept(HomeMergeEntity homeMergeEntity) throws Exception {
                    if (homeMergeEntity != null) {
                        UnicomHomeFragment.this.topBgManager.setBgCacheAndShow(homeMergeEntity.getCardBgEntity());
                        UnicomHomeFragment.this.xuanFuChuangManager.setXuanFuChuangCacheAndShow(homeMergeEntity.getAdvertiseEntity());
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.19
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    UnicomHomeFragment.this.topBgManager.loadCardCacheData();
                    UnicomHomeFragment.this.xuanFuChuangManager.loadCacheData();
                    String str = HomeMergeInfoManager.TAG;
                    MsLogUtil.m7977e(str, "融合接口请求异常" + th.getMessage());
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "加载动态感知数据异常:" + e.getMessage());
        }
    }

    public void loadHomeTab(final String str, final boolean z, String str2) {
        try {
            this.isRefreshTab = true;
            String str3 = UnicomHomeConstants.JIA_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("加载腰部Tab数据 是否强制刷新 = ");
            sb.append(z);
            sb.append(" 加载类型 = ");
            sb.append(TextUtils.equals(str, "1") ? "网络" : "缓存");
            sb.append(" ====> ");
            sb.append(str2);
            MsLogUtil.m7979d(str3, sb.toString());
            if (z && this.toolBarManager != null) {
                this.toolBarManager.removeTopTabData();
            }
            this.homeTabManager.getHomeTab(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$UnicomHomeFragment$eRlEOjn0jQ8JT1qny8Ng1HBvqGM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    UnicomHomeFragment.lambda$loadHomeTab$1(UnicomHomeFragment.this, z, str, (HomeTabEntity) obj);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.20
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    boolean z2;
                    if ("1".equals(str) && (z2 = z)) {
                        UnicomHomeFragment.this.loadHomeTab("2", z2, "HF loadHomeTab 接口异常");
                    }
                    MsLogUtil.m7979d("loadHomeTab", "请求结果：" + th.getMessage());
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "加载tab接口异常:" + e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$loadHomeTab$1(UnicomHomeFragment unicomHomeFragment, boolean z, String str, HomeTabEntity homeTabEntity) throws Exception {
        if ("0000".equals(homeTabEntity.getRespCode())) {
            HomeTabEntity.DataDTO data = homeTabEntity.getData();
            unicomHomeFragment.homeTabEntity = data;
            App.getSharePreferenceUtil().putString("unicom_unlogin_freeurl", data.getNoLoginBallFeeUrl());
            App.getSharePreferenceUtil().putString("unicom_unlogin_flowurl", data.getNoLoginBallFlowUrl());
            App.getSharePreferenceUtil().putString("unicom_unlogin_voiceurl", data.getNoLoginBallVoiceUrl());
            App.getSharePreferenceUtil().putString("unicom_unlogin_integral", data.getNoLoginBallIntegral());
            boolean indexSelectedTabOpenFlag = data.getIndexSelectedTabOpenFlag();
            boolean indexHeadTabOpenFlag = data.getIndexHeadTabOpenFlag();
            boolean miniProgramOpenUrlFlag = data.getMiniProgramOpenUrlFlag();
            int indexHeadTabTimeout = data.getIndexHeadTabTimeout();
            App.getSharePreferenceUtil().putString("unicom_esim_vpnBlackDomain", data.getVpnBlackDomain());
            App.getSharePreferenceUtil().putString("unicom_esim_jsWhiteDoman", data.getJsWhiteDoman());
            App.getSharePreferenceUtil().putString("video_water_is_open", data.getWatermarkFlag());
            App.getSharePreferenceUtil().putBoolean("xiaoheitiao_xiaochngxu", Boolean.valueOf(data.isBlackBarOpenFlag()));
            App.getSharePreferenceUtil().putString("xiaoheitiao_xiaochngxu_url", data.getBlackBarUrl());
            App.getSharePreferenceUtil().putString("home_city_switch_yuanshen", data.getCityPageSwitch());
            HomeTabEntity.DataDTO.IndexSelectedTabDTO indexSelectedTab = data.getIndexSelectedTab();
            List<HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO> tabCfgArray = indexSelectedTab.getTabCfgArray();
            if (!indexSelectedTabOpenFlag || tabCfgArray == null || tabCfgArray.size() == 0) {
                ArrayList arrayList = new ArrayList();
                HomeTabEntity.DataDTO.IndexSelectedTabDTO indexSelectedTabDTO = new HomeTabEntity.DataDTO.IndexSelectedTabDTO();
                HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO tabCfgArrayDTO = new HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO();
                tabCfgArrayDTO.setH5Url(URLSet.getHomeTuiJianDefUrlAndCumpUrl("h5Url"));
                tabCfgArrayDTO.setAppletUrl(URLSet.getHomeTuiJianDefUrlAndCumpUrl("cumpUrl"));
                tabCfgArrayDTO.setNavName("精选");
                arrayList.add(tabCfgArrayDTO);
                indexSelectedTabDTO.setTabCfgArray(arrayList);
                indexSelectedTab = indexSelectedTabDTO;
            }
            unicomHomeFragment.initViewPager(indexSelectedTab, indexSelectedTabOpenFlag, z);
            unicomHomeFragment.mViewPager.setPagingEnabled(data.isSlipperyFlag());
            HomeTabEntity.DataDTO.IndexHeadTabDTO indexHeadTab = data.getIndexHeadTab();
            try {
                String miniProgramUrl = indexHeadTab.getTabCfgArray().get(0).getMiniProgramUrl();
                if (miniProgramUrl.startsWith("https://edop_unicom?") || miniProgramUrl.startsWith("http://edop_unicom?")) {
                    String queryParameter = Uri.parse(miniProgramUrl).getQueryParameter("appid");
                    if (queryParameter.startsWith("edop_unicom_")) {
                        String str2 = MainTabCumpLauncher.TAG;
                        MsLogUtil.m7979d(str2, "使用网络配置替换首页小程序ID：" + queryParameter);
                        App.getSharePreferenceUtil().putString("HomeCumpAppIdConfig", queryParameter);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                App.getSharePreferenceUtil().putString("MainTabPrefetchAppId", data.getEdopAppidList());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            unicomHomeFragment.toolBarManager.initTopTab(miniProgramOpenUrlFlag, indexHeadTabOpenFlag, indexHeadTabTimeout, indexHeadTab, z);
            String headTitle = data.getServicePageCfgCW().getHeadTitle();
            if (!TextUtils.isEmpty(headTitle)) {
                App.getSharePreferenceUtil().putString("HOME_JGQ_TITLE", headTitle);
            }
            UnicomHomeTuiJianFragment unicomHomeTuiJianFragment = unicomHomeFragment.tuiJianFragment;
            if (unicomHomeTuiJianFragment != null) {
                unicomHomeTuiJianFragment.updateJinGangQuMoreText();
            }
            UnicomToolBarManager unicomToolBarManager = unicomHomeFragment.toolBarManager;
            if (unicomToolBarManager != null) {
                unicomToolBarManager.setQiandao(data);
            }
            unicomHomeFragment.homeOldTabEntity = data;
            if ("1".equals(str)) {
                ConfigManager.setH5CdnCacheTime(data.getH5CDNCacheTime());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pullToRefresh() {
        HomeWebFragment homeWebFragment;
        try {
            if (this.isShowHome) {
                if (this.mViewPager.getCurrentItem() == 0) {
                    getPullBackgroudData();
                    if (this.toolBarManager != null) {
                        this.toolBarManager.startLocation(false);
                    }
                    loadMergeInfo();
                    LanguageUtil.getInstance().getLanguageData(LanguageUtil.getInstance().getLanguage(), null);
                    if (this.dropDownManager != null) {
                        this.dropDownManager.loadDropDownState(this.mainActivity);
                    }
                    loadHomeTab("1", true, "HF 下拉刷新");
                    MsLogUtil.m7979d("菜单数据", "下拉刷新更新菜单数据");
                    if (this.mainActivity != null) {
                        this.mainActivity.initDaoHang();
                    }
                } else if (this.mPagerAdapter != null && (homeWebFragment = (HomeWebFragment) this.mPagerAdapter.getShowFragment()) != null) {
                    homeWebFragment.reloadWeb();
                }
            } else if (this.YingYeTingWebFragment != null) {
                this.YingYeTingWebFragment.reloadWeb();
            }
            PvCurrencyLogUtils.pvLogMainDJ("1010409", "", "下拉刷新", "", "首页-下拉刷新");
            UnicomHomeLogUtils.getInstance().clickLog("1010409", "下拉刷新");
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "下拉刷新异常:" + e.getMessage());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void homeJumpTabSwitch(HomeJumpTabEvent homeJumpTabEvent) {
        try {
            String navCode = homeJumpTabEvent.getNavCode();
            JSONObject jsonObject = homeJumpTabEvent.getJsonObject();
            if (TextUtils.isEmpty(navCode)) {
                IntentManager.gotoWebViewActivityWithParams(this.mainActivity, URLSet.getHomeJumpTabSwitchUrl(), "", transformJSONObjectToMap(jsonObject));
                return;
            }
            List<HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO> tabCfgArray = this.homeTabEntity.getIndexSelectedTab().getTabCfgArray();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= tabCfgArray.size()) {
                    break;
                } else if (navCode.equals(tabCfgArray.get(i).getNavCode())) {
                    BaseWebFragment item = this.mPagerAdapter.getItem(i);
                    if (item instanceof HomeWebFragment) {
                        ((HomeWebFragment) item).navigateParams = transformJSONObjectToMap(jsonObject);
                        this.mTabView.choseItem(i);
                        z = true;
                    }
                } else {
                    i++;
                }
            }
            if (z) {
                return;
            }
            if (TextUtils.isEmpty(homeJumpTabEvent.getUrl())) {
                IntentManager.gotoWebViewActivityWithParams(this.mainActivity, URLSet.getHomeJumpTabSwitchUrl(), "", transformJSONObjectToMap(jsonObject));
            } else {
                IntentManager.gotoWebViewActivity(this.mainActivity, homeJumpTabEvent.getUrl(), "");
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "根据navCode跳转Tab异常:" + e.getMessage());
        }
    }

    private Map<String, Object> transformJSONObjectToMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.get(next));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }

    public String getHomeTabCurrentItemUrl() {
        BaseWebFragment showFragment = this.mPagerAdapter.getShowFragment();
        if (showFragment instanceof HomeWebFragment) {
            return ((HomeWebFragment) showFragment).getWebUrl();
        }
        return showFragment instanceof UnicomHomeTuiJianFragment ? ((UnicomHomeTuiJianFragment) showFragment).getWebUrl() : "";
    }

    public boolean isHomeTabCurrentItemIndex() {
        RecyclerTabLayout recyclerTabLayout = this.mTabView;
        return recyclerTabLayout != null && recyclerTabLayout.getCurrentIndex() == 0 && this.isShowHome;
    }

    public UnicomHomeTuiJianFragment getTuiJianFragment() {
        return this.tuiJianFragment;
    }

    @SuppressLint({"CheckResult"})
    public void checkMultilingualPopup() {
        Observable.fromCallable(new Callable<Integer>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.22
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(MultilingualManager.getInstance().checkDialog());
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.21
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) throws Exception {
                if (num.intValue() == 0) {
                    return;
                }
                MultilingualManager.getInstance().showDialog(UnicomHomeFragment.this.mainActivity, num.intValue());
            }
        });
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
        removeHandlerCallback();
        LoginMemberManager.dismissDialog();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setQiandaoImage(QiandaoEvent qiandaoEvent) {
        UnicomToolBarManager unicomToolBarManager = this.toolBarManager;
        if (unicomToolBarManager != null) {
            unicomToolBarManager.setQiandao(this.homeTabEntity);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshBackgroundSkin(RefreshSkinEvent refreshSkinEvent) {
        try {
            if (this.topBgManager != null) {
                this.topBgManager.updateHomeSkin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeMemberInfo(FragmentOnResumeEvent fragmentOnResumeEvent) {
        if (fragmentOnResumeEvent.getType() == FragmentOnResumeEvent.HOME && isResumed()) {
            onResume();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void recevierNet(NetEvent netEvent) {
        try {
            if (SystemServiceUtils.netIsAvailable()) {
                return;
            }
            UIUtils.toastCenter("网络连接已断开");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void homeRestTimer(HomeBGEvent homeBGEvent) {
        try {
            int code = homeBGEvent.getCode();
            if (EventBusUtils.EVENT_HOME_REST_TIMER == code) {
                resetLogHandlerCallback();
            } else if (EventBusUtils.EVENT_HOME_CLEAR_CALLBACK == code) {
                removeHandlerCallback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void homeGuidEvent(HomeGuidEvent homeGuidEvent) {
        try {
            int code = homeGuidEvent.getCode();
            if (EventBusUtils.EVENT_MAIN_HOME_GUID == code) {
                if (this.tuiJianFragment != null) {
                    this.tuiJianFragment.showGuidView();
                }
            } else if (EventBusUtils.EVENT_MAIN_HOME_GUID_RESET_BG == code) {
                if (this.topBgManager != null) {
                    if ((this.mViewPager != null ? this.mViewPager.getCurrentItem() : 0) == 0 && this.isShowHome) {
                        this.topBgManager.restBgState(103);
                    }
                }
            } else if (EventBusUtils.EVENT_MAIN_HOME_GUID_SHOW_DEF_BG != code || this.topBgManager == null) {
            } else {
                this.topBgManager.loadTabDefaultBg();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void searchEvent(SearchXinRenLiBaoEvent searchXinRenLiBaoEvent) {
        if (this.toolBarManager != null) {
            SearchHuoDongEntity searchHuoDongEntity = new SearchHuoDongEntity();
            searchHuoDongEntity.setLinkUrl(searchXinRenLiBaoEvent.getUrl());
            searchHuoDongEntity.setSearchWordName(searchXinRenLiBaoEvent.getTitle());
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(searchHuoDongEntity);
            if (HomeSearchNewUserManager.isNewUser()) {
                this.toolBarManager.setSearchData(arrayList, arrayList2);
            }
        }
    }

    public void setGongJiRi() {
        try {
            if (this.mCacheView != null) {
                UIUtils.setGJR(this.mCacheView);
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("HomeFragment", "setGongJiRi() 异常:" + e.getMessage());
        }
    }

    public void getPullBackgroudData() {
        try {
            if (App.hasLogined()) {
                this.dropDownManager.getHaiBao(this.mainActivity).subscribe(new Consumer<Map<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.23
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Map<String, String> map) throws Exception {
                        ShowImageUtils.showImageView(UnicomHomeFragment.this.mainActivity, map.get("shareImgUrl"), 2131231450, UnicomHomeFragment.this.mImgSecond);
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.24
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        UnicomHomeFragment.this.mImgSecond.setImageResource(2131231450);
                    }
                });
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "获取下拉海报异常:" + e.getMessage());
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStop() {
        super.onStop();
        if (!App.isTopProcess) {
            this.isFrontAndRearSwitching = true;
        }
        MsLogUtil.m7979d("qianhoutai", "onStop isFrontAndRearSwitching" + this.isFrontAndRearSwitching);
    }

    public void upLoadExposureLog() {
        try {
            if (this.mTabView.getCurrentIndex() == 0) {
                this.tuiJianFragment.checkViewVisiable();
            }
            UnicomHomeLogUtils.isBGVisiable = this.topBgManager.isBgVisialbe();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new HomeLogEntity("1010103", "首页签到"));
            arrayList.add(new HomeLogEntity("1010104", "首页客服"));
            arrayList.add(new HomeLogEntity("1010105", "首页扫码"));
            arrayList.add(new HomeLogEntity("1010101", "首页地市专区"));
            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_TITLE, arrayList);
            UnicomHomeLogUtils.getInstance().uploadBGLog(this.mTabView.getCurrentIndex());
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "上传曝光数据异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetLogHandlerCallback() {
        try {
            removeHandlerCallback();
            this.logHandler.postDelayed(this.logRunnable, 3000L);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeHandlerCallback() {
        Handler handler = this.logHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void doubleClickAutoRefresh() {
        try {
            this.scrollerLayout.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.UnicomHomeFragment.26
                @Override // java.lang.Runnable
                public void run() {
                    if (UnicomHomeFragment.this.isShowHome || UnicomHomeFragment.this.toolBarManager == null) {
                        if (UnicomHomeFragment.this.mViewPager == null || UnicomHomeFragment.this.mTabView == null || UnicomHomeFragment.this.mViewPager.getCurrentItem() == 0) {
                            if (UnicomHomeFragment.this.scrollerLayout != null) {
                                UnicomHomeFragment.this.scrollerLayout.scrollTo(0, 0);
                            }
                            MsLogUtil.m7979d("clickHome", "刷新数据");
                            UnicomHomeFragment.this.mSmartRefresh.autoRefresh();
                            return;
                        }
                        MsLogUtil.m7979d("clickHome", "中部tab回到 推荐");
                        UnicomHomeFragment.this.mTabView.choseItem(0);
                        return;
                    }
                    UnicomHomeFragment.this.toolBarManager.onTopTabBackHome();
                    MsLogUtil.m7979d("clickHome", "顶部tab回到 首页");
                }
            }, 50L);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "双击首页下拉异常:" + e.getMessage());
        }
    }
}
