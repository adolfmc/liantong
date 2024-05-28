package com.sinovatech.unicom.basic.p315ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.basic.eventbus.HomeBGEvent;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.JSInvokeHandler;
import com.sinovatech.unicom.basic.p315ui.home.fragment.LazyFragment;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMaidian;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.p315ui.view.HomeWebView;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout;
import com.sinovatech.unicom.basic.webview.ManagerCdnCacheTime;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLogManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.AndroidH5CaijiJSBrige;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJConfigUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJJSManager;
import com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeWebFragment extends LazyFragment {
    public static final String TAG = "HomeCumpLanucher";
    public static final int timeoutDefault = 5;
    private AppCompatActivity activityContext;
    private AndroidH5CaijiJSBrige androidTabJSBridge;
    private String cumpTransId;
    private Disposable disposable;
    private View fragmentCacheView;
    private UserAHeaderVH.IXiaoheitiaoCallBack iXiaoheitiaoCallBack;
    private boolean isPageStartCall;
    private JSInvokeHandler jsInvokeHandler;
    private ImageView loadingImage;
    private LinearLayout loadingLayout;
    private ManagerMaidian managerMaidian;
    private WebParamsEntity paramsEntity;
    private ConsecutiveScrollerLayout scrollerLayout;
    private ImageView sketetonImage;
    private RelativeLayout sketetonLayout;
    private TYCJBoxManager tYCJBoxManager;
    private int timeoutConfig;
    private UserManager userManager;

    /* renamed from: wv */
    private HomeWebView f18408wv;
    private int tabPosition = 0;
    private String fragmentTitle = "";
    private String webUrl = "";
    private String cumpUrl = "";
    private boolean urlPriorityConfig = false;
    private boolean isRevicedError = false;

    public static HomeWebFragment newIntence(WebParamsEntity webParamsEntity, int i, boolean z) {
        HomeWebFragment homeWebFragment = new HomeWebFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("WebFragment_0", webParamsEntity);
        bundle.putInt("tabPosition", i);
        bundle.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, z);
        homeWebFragment.setArguments(bundle);
        return homeWebFragment;
    }

    public static HomeWebFragment newIntence(WebParamsEntity webParamsEntity, int i) {
        return newIntence(webParamsEntity, i, true);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContext = (AppCompatActivity) context;
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.BaseLazyFragment, com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.paramsEntity = (WebParamsEntity) getArguments().getParcelable("WebFragment_0");
            this.tabPosition = getArguments().getInt("tabPosition", 0);
            if (this.tabPosition == 0) {
                this.cumpTransId = "S2ndpage1229";
            } else {
                this.cumpTransId = "S2ndpage1242";
            }
            initWebIntentParams();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initWebIntentParams() {
        try {
            if (this.paramsEntity != null) {
                this.fragmentTitle = this.paramsEntity.getTitle();
                this.webUrl = this.paramsEntity.getUrl();
                this.cumpUrl = this.paramsEntity.getMiniProgramUrl();
                this.urlPriorityConfig = this.paramsEntity.isMiniProgramOpenUrlFlag();
                this.timeoutConfig = this.paramsEntity.getIndexHeadTabTimeout();
                if (this.timeoutConfig < 5) {
                    this.timeoutConfig = 5;
                }
                String navigateParamsUUID = this.paramsEntity.getNavigateParamsUUID();
                if (TextUtils.isEmpty(navigateParamsUUID)) {
                    return;
                }
                this.navigateParams = App.navigateParamsCacheMap.get(navigateParamsUUID);
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化页面参数异常:" + e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.LazyFragment
    public void onCreateViewLazy(Bundle bundle) {
        super.onCreateViewLazy(bundle);
        setContentView(2131493188);
        MsLogUtil.m7979d(TAG, "HomeWebFragment onCreateViewLazy " + this.fragmentTitle);
        printLog("onCreateViewLazy()");
        this.fragmentCacheView = getContentView();
        this.userManager = UserManager.getInstance();
        this.managerMaidian = new ManagerMaidian(this.activityContext);
        this.tYCJBoxManager = TYCJBoxManager.getInstance();
        initView(this.fragmentCacheView);
        setGongJiRi();
        initBusiness();
    }

    public void scrollTop() {
        try {
            if (this.scrollerLayout != null) {
                this.scrollerLayout.scrollTo(0, 0);
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.LazyFragment
    public void onResumeLazy(boolean z) {
        super.onResumeLazy(z);
        HomeWebView homeWebView = this.f18408wv;
        if (homeWebView != null) {
            homeWebView.onResume();
        }
        try {
            if (this.tabPosition != -1 && CollectConfig.isShowHome) {
                String valueOf = String.valueOf(this.tabPosition + 1090100 + 1);
                UnicomCollectManager unicomCollectManager = UnicomCollectManager.getInstance();
                unicomCollectManager.setTransId(valueOf + CollectConfig.montageTag1 + this.fragmentTitle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        MsLogUtil.m7979d(TAG, "HomeWebFragment onResumeLazy " + this.fragmentTitle);
        printLog("onResumeLazy() isOnResume = " + z);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.LazyFragment
    public void onPauseLazy(boolean z) {
        super.onPauseLazy(z);
        HomeWebView homeWebView = this.f18408wv;
        if (homeWebView != null) {
            homeWebView.onPause();
        }
        MsLogUtil.m7979d(TAG, "HomeWebFragment onPauseLazy " + this.fragmentTitle);
        printLog("onPauseLazy() isOnPause = " + z);
    }

    private void setGongJiRi() {
        try {
            if (getContentView() != null) {
                UIUtils.setGJR(getContentView());
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("HomeFragment", "setGongJiRi() 异常:" + e.getMessage());
        }
    }

    public void reloadWeb() {
        try {
            if (NetworkUtils.isConnected()) {
                if (this.f18408wv != null) {
                    HomeWebView homeWebView = this.f18408wv;
                    if (homeWebView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) homeWebView, "about:blank");
                    } else {
                        homeWebView.loadUrl("about:blank");
                    }
                }
                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (HomeWebFragment.this.f18408wv != null) {
                                HomeWebFragment.this.initWebIntentParams();
                                HomeWebFragment.this.prepareLoadUrl(true);
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7977e(HomeWebFragment.TAG, "刷新webview异常:" + e.getMessage());
                        }
                    }
                }, 30L);
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "刷新webview异常:" + e.getMessage());
        }
    }

    public void forceFlush() {
        HomeWebView homeWebView = this.f18408wv;
        if (homeWebView != null) {
            homeWebView.loadURL("javascript:history.go(0)");
        }
    }

    private void initView(View view) {
        try {
            this.scrollerLayout = (ConsecutiveScrollerLayout) view.findViewById(2131297220);
            this.f18408wv = (HomeWebView) view.findViewById(2131297212);
            this.f18408wv.setVisibility(8);
            this.f18408wv.setHapticFeedbackEnabled(false);
            this.f18408wv.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment.2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    NBSActionInstrumentation.onLongClickEventEnter(view2, this);
                    NBSActionInstrumentation.onLongClickEventExit();
                    return true;
                }
            });
            this.f18408wv.setTabIndex(this.tabPosition);
            if (this.tabPosition == 99) {
                this.f18408wv.setLayerType(1, null);
                this.f18408wv.setVerticalScrollBarEnabled(false);
            } else {
                this.f18408wv.setLayerType(2, null);
            }
            this.loadingLayout = (LinearLayout) view.findViewById(2131297209);
            this.loadingImage = (ImageView) view.findViewById(2131297208);
            this.loadingLayout.setVisibility(8);
            this.sketetonLayout = (RelativeLayout) view.findViewById(2131297211);
            this.sketetonImage = (ImageView) view.findViewById(2131297210);
            this.sketetonLayout.setVisibility(8);
            this.f18408wv.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment.3
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    EventBusUtils.post(new HomeBGEvent(EventBusUtils.EVENT_HOME_CLEAR_CALLBACK));
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        EventBusUtils.post(new HomeBGEvent(EventBusUtils.EVENT_HOME_REST_TIMER));
                        return false;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBusiness() {
        initWebview();
        UnicomCookieManager.addLoginCookie();
        prepareLoadUrl(false);
    }

    private void initWebview() {
        this.jsInvokeHandler = new JSInvokeHandler(this.activityContext, this, this.f18408wv, new Handler(), new HashMap(), new HashMap());
        this.f18408wv.addJavascriptInterface(this.jsInvokeHandler, "js_invoke");
        this.androidTabJSBridge = new AndroidH5CaijiJSBrige(this, this.tYCJBoxManager);
        this.f18408wv.addJavascriptInterface(this.androidTabJSBridge, "AndroidUUNICOLMS");
        registerJSPluginService(this.f18408wv);
        this.f18408wv.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment.4
            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onRecevieError(int i, String str, String str2) {
                HomeWebFragment.this.isRevicedError = true;
                HomeWebFragment.this.hideLoadingAndShowWebview();
                HomeWebFragment.this.loadErrorHtml();
                try {
                    HomeWebFragment.this.errorLog(str, str2);
                    if (HomeWebFragment.this.iXiaoheitiaoCallBack != null) {
                        HomeWebFragment.this.iXiaoheitiaoCallBack.onFail();
                    }
                } catch (Exception e) {
                    MsLogUtil.m7977e(HomeWebFragment.TAG, e.getMessage());
                }
                return true;
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onPageStarted(final String str) {
                try {
                    HomeWebFragment.this.isPageStartCall = true;
                    HomeWebFragment.this.isRevicedError = false;
                    TYCJJSManager.registerJs(HomeWebFragment.this.f18408wv, new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment.4.1
                        @Override // io.reactivex.functions.Consumer
                        public void accept(String str2) throws Exception {
                            String uuid = RecentCustomManager.uuid();
                            HomeWebView homeWebView = HomeWebFragment.this.f18408wv;
                            homeWebView.loadURL("javascript:try{window.unicomUUNICOLH5ClickListen('+" + uuid + "+')}catch(err){}");
                            if (TYCJConfigUtil.isOpenAndWhiteUrl("imgLoading", str, "")) {
                                HomeWebFragment.this.f18408wv.loadURL("javascript:try{window.UUNICOLH5SystemMonitoring.start()}catch(e){console.log(e)}");
                            }
                        }
                    });
                    HomeWebFragment.this.managerMaidian.shagnchaunLog(HomeWebFragment.this.f18408wv.getUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onPageFinished() {
                if (HomeWebFragment.this.isPageStartCall) {
                    HomeWebFragment.this.isPageStartCall = false;
                    String str = MainTabCumpLauncher.TAG;
                    MsLogUtil.m7979d(str, "HomeWebFragment onPageFinished " + HomeWebFragment.this.f18408wv.getUrl());
                    if (HomeWebFragment.this.tabPosition != 0) {
                        HomeWebFragment.this.hideLoadingAndShowWebview();
                        if (HomeWebFragment.this.iXiaoheitiaoCallBack != null) {
                            HomeWebFragment.this.iXiaoheitiaoCallBack.onFinish();
                        }
                    }
                    TYCJJSManager.collectWebTimingData(HomeWebFragment.this.f18408wv, "");
                    if (!HomeWebFragment.this.isRevicedError) {
                        if (HomeWebFragment.this.currentCumpAppEntity == null || !HomeWebFragment.this.f18408wv.getUrl().startsWith("file:")) {
                            MainTabCumpLogManager.getInstance(HomeWebFragment.this.activityContext).log_SCE02_Sub03(HomeWebFragment.this.cumpTransId, "", HomeWebFragment.this.paramsEntity.getTitle(), HomeWebFragment.this.f18408wv.getUrl(), "", "");
                        } else {
                            MainTabCumpLogManager.getInstance(HomeWebFragment.this.activityContext).log_SCE02_Sub03(HomeWebFragment.this.cumpTransId, HomeWebFragment.this.currentCumpAppEntity.getAppId(), HomeWebFragment.this.paramsEntity.getTitle(), HomeWebFragment.this.f18408wv.getUrl(), "", HomeWebFragment.this.currentCumpAppEntity.getOfficialVersion() + "+" + HomeWebFragment.this.currentCumpAppEntity.getOfficialVersionNum());
                        }
                    }
                    return false;
                }
                return true;
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public void onProgressChanged(int i) {
                if ((i > 70) && (HomeWebFragment.this.tabPosition != 0)) {
                    HomeWebFragment.this.hideLoadingAndShowWebview();
                    if (HomeWebFragment.this.iXiaoheitiaoCallBack != null) {
                        HomeWebFragment.this.iXiaoheitiaoCallBack.onFinish();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareLoadUrl(boolean z) {
        if (!z) {
            if (this.tabPosition == 0) {
                setLoadingTimer();
                this.loadingLayout.setVisibility(8);
                this.sketetonLayout.setVisibility(0);
            } else {
                this.loadingLayout.setVisibility(0);
                this.sketetonLayout.setVisibility(8);
                Glide.with((FragmentActivity) this.activityContext).asGif().load((Integer) 2131231476).into(this.loadingImage);
            }
        }
        this.webUrl = ManagerCdnCacheTime.replaceCdnCacheTime(this.webUrl);
        this.cumpUrl = ManagerCdnCacheTime.replaceCdnCacheTime(this.cumpUrl);
        new MainTabCumpLauncher(this.activityContext, this.cumpTransId).prepareLoadUrl(this.activityContext, z, this.tabPosition, this.urlPriorityConfig, this.cumpUrl, new MainTabCumpLauncher.PrepareLoadUrlListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment.5
            @Override // com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.PrepareLoadUrlListener
            public void loadCumpUrl(CumpEntity cumpEntity, String str) {
                String str2 = MainTabCumpLauncher.TAG;
                MsLogUtil.m7979d(str2, "HomeWebFragment 准备加载 " + str);
                HomeWebFragment.this.currentCumpAppId = cumpEntity.getAppId();
                HomeWebFragment.this.currentCumpAppName = cumpEntity.getAppName();
                HomeWebFragment homeWebFragment = HomeWebFragment.this;
                homeWebFragment.currentCumpAppEntity = cumpEntity;
                HomeWebView homeWebView = homeWebFragment.f18408wv;
                if (homeWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) homeWebView, str);
                } else {
                    homeWebView.loadUrl(str);
                }
                ((LinearLayout) HomeWebFragment.this.fragmentCacheView.findViewById(2131297132)).setVisibility(8);
                MainTabCumpLogManager.getInstance(HomeWebFragment.this.activityContext).log_SCE02_Sub02(HomeWebFragment.this.cumpTransId, cumpEntity.getAppId(), HomeWebFragment.this.paramsEntity.getTitle(), str, HomeWebFragment.this.currentCumpAppEntity.getOfficialVersion() + "+" + HomeWebFragment.this.currentCumpAppEntity.getOfficialVersionNum());
            }

            @Override // com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.PrepareLoadUrlListener
            public void loadWebUrl() {
                String str = MainTabCumpLauncher.TAG;
                MsLogUtil.m7979d(str, "HomeWebFragment 准备加载 " + HomeWebFragment.this.webUrl);
                HomeWebFragment homeWebFragment = HomeWebFragment.this;
                homeWebFragment.currentCumpAppId = "";
                homeWebFragment.currentCumpAppName = "";
                homeWebFragment.currentCumpAppEntity = null;
                homeWebFragment.loadNetWebUrl();
                if (HomeWebFragment.this.tabPosition == 0) {
                    MainTabCumpLogManager.getInstance(HomeWebFragment.this.activityContext).log_SCE02_Sub01(HomeWebFragment.this.cumpTransId, HomeWebFragment.this.paramsEntity.getTitle(), HomeWebFragment.this.webUrl, "");
                }
            }
        });
    }

    private void setLoadingTimer() {
        try {
            if (this.disposable != null && !this.disposable.isDisposed()) {
                this.disposable.dispose();
                this.disposable = null;
            }
            this.disposable = Observable.timer(this.timeoutConfig, TimeUnit.SECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment.6
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    if (HomeWebFragment.this.sketetonLayout.getVisibility() == 0) {
                        MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "HomeWebFragment 定时器到时结束强制关闭骨架屏 加载兜底页面");
                        HomeWebFragment.this.hideLoadingAndShowWebview();
                        HomeWebFragment.this.loadErrorHtml();
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.HomeWebFragment.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) {
                    try {
                        HomeWebFragment.this.hideLoadingAndShowWebview();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            hideLoadingAndShowWebview();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadNetWebUrl() {
        try {
            if (TextUtils.isEmpty(this.webUrl)) {
                UIUtils.toast("您访问的地址为空");
                return;
            }
            this.webUrl = this.webUrl.trim();
            ManagerHuiDu.HuiDuWebViewURLConfig huiduURLConfig = ManagerHuiDu.getInstance().getHuiduURLConfig(this.webUrl);
            if (huiduURLConfig != null && huiduURLConfig.enableHuiDu && !TextUtils.isEmpty(huiduURLConfig.huiduURL)) {
                this.webUrl = huiduURLConfig.huiduURL;
                MsLogUtil.m7979d(TAG, "HomeWebFragment 切换灰度 " + this.webUrl);
            }
            if (this.webUrl.startsWith("file")) {
                this.f18408wv.loadURL(this.webUrl);
            } else {
                this.f18408wv.get(this.webUrl, null);
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "加载在线url异常:" + e.getMessage());
        }
    }

    public void errorLog(String str, String str2) {
        try {
            PvCurrencyLogUtils.pvLogMainDJ("S2ndpage1187", str2 + "", "", "首页", str + "", "", "");
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "上传异常信息错误:" + e.getMessage());
        }
        try {
            if (this.currentCumpAppEntity != null && str2.startsWith("file:")) {
                MainTabCumpLogManager.getInstance(this.activityContext).log_SCE02_Sub03(this.cumpTransId, this.currentCumpAppEntity.getAppId(), this.paramsEntity.getTitle(), str2, str, this.currentCumpAppEntity.getOfficialVersion() + "+" + this.currentCumpAppEntity.getOfficialVersionNum());
                return;
            }
            MainTabCumpLogManager.getInstance(this.activityContext).log_SCE02_Sub03(this.cumpTransId, "", this.paramsEntity.getTitle(), str2, str, "");
        } catch (Exception e2) {
            MsLogUtil.m7977e(TAG, "上传异常信息错误:" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadErrorHtml() {
        try {
            if (this.tabPosition == 0) {
                HomeWebView homeWebView = this.f18408wv;
                if (homeWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) homeWebView, "file:///android_asset/webview/hometuijiandoudi/index.html");
                } else {
                    homeWebView.loadUrl("file:///android_asset/webview/hometuijiandoudi/index.html");
                }
            } else {
                HomeWebView homeWebView2 = this.f18408wv;
                if (homeWebView2 instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) homeWebView2, "file:///android_asset/webview/homeweberror.html");
                } else {
                    homeWebView2.loadUrl("file:///android_asset/webview/homeweberror.html");
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "加载异常页面异常:" + e.getMessage());
        }
    }

    public void hideLoadingAndShowWebview() {
        try {
            if (this.f18408wv.getVisibility() != 0) {
                this.sketetonLayout.setVisibility(8);
                this.loadingLayout.setVisibility(8);
                this.f18408wv.setVisibility(0);
            }
            if (this.disposable == null || this.disposable.isDisposed()) {
                return;
            }
            this.disposable.dispose();
            this.disposable = null;
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "隐藏加载效果页面异常:" + e.getMessage());
        }
    }

    private void printLog(String str) {
        try {
            String str2 = UnicomHomeConstants.LAZY_TAG;
            MsLogUtil.m7979d(str2, this.paramsEntity.getTitle() + "  " + str + "  postion = " + this.tabPosition);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "打印日志日常:" + e.getMessage());
        }
    }

    public void removeWeb() {
        try {
            if (this.f18408wv != null) {
                this.f18408wv.getSettings().setBuiltInZoomControls(true);
                ViewGroup viewGroup = (ViewGroup) this.f18408wv.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                this.f18408wv.removeAllViews();
                this.f18408wv.setVisibility(8);
                this.f18408wv.destroy();
                this.f18408wv = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWebUrl() {
        try {
            return this.f18408wv != null ? this.f18408wv.getUrl() : "";
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "获取当前加载的url异常:" + e.getMessage());
            return "";
        }
    }

    public void setiXiaoheitiaoCallBack(UserAHeaderVH.IXiaoheitiaoCallBack iXiaoheitiaoCallBack) {
        this.iXiaoheitiaoCallBack = iXiaoheitiaoCallBack;
    }
}
