package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.fort.andjni.JniLib;
import com.megvii.livenesslib.util.RealAndroidUtils;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.eventbus.ChangeMainTabIconEvent;
import com.sinovatech.unicom.basic.eventbus.CustomActivityEvent;
import com.sinovatech.unicom.basic.eventbus.HomeJumpTabEvent;
import com.sinovatech.unicom.basic.eventbus.MainTabEvent;
import com.sinovatech.unicom.basic.eventbus.PopWebViewEvent;
import com.sinovatech.unicom.basic.eventbus.ServiceMenuEvent;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.basic.eventbus.WelcomLoginEvent;
import com.sinovatech.unicom.basic.p314po.LoginConfigEntity;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.MyFragmentTabHost;
import com.sinovatech.unicom.basic.p315ui.NetworkConnectChangedReceiver;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment;
import com.sinovatech.unicom.basic.p315ui.fragment.UnicomHomeFragment;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.basic.p315ui.fuwu.view.FuWuSearchLayoutView;
import com.sinovatech.unicom.basic.p315ui.home.fragment.UnicomHomeTuiJianFragment;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainFuli;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainLogin;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainNotic;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerShareConfig;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerSomeOldConfig;
import com.sinovatech.unicom.basic.p315ui.manager.WoKouLingManager;
import com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.p315ui.view.MainTabView;
import com.sinovatech.unicom.basic.p315ui.view.SearchLayoutView;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.GDTLogUtils;
import com.sinovatech.unicom.common.InitUtils;
import com.sinovatech.unicom.common.KacaoPvLog;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.YinSiDialogUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.ManagerEdopYuyu;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.AuthH5JSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.AuthJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.LiuyanManager;
import com.sinovatech.unicom.separatemodule.notice.NoticManager;
import com.sinovatech.unicom.separatemodule.notice.PushMsgDao;
import com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJConfigUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJUploadManager;
import com.sinovatech.unicom.separatemodule.user.UserFragment;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUser;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.sinovatech.unicom.separatemodule.websocket.WebSocketManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.MainActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MainActivity extends BaseActivity implements BaseActivity.OnOpenHuaWeiZheDie {
    public static final String Fragment_Ad = "Fragment_Ad";
    public static final String Fragment_Home = "Fragment_Home";
    public static final String Fragment_Service = "Fragment_Service";
    public static final String Fragment_Shop = "Fragment_Shop";
    public static final String Fragment_User = "Fragment_User";
    public static final String Fragment_YiWang = "Fragment_YiWang";
    private boolean ADTAB;
    private boolean HOMETAB;
    private boolean SERVICETAB;
    private boolean SHOPTAB;
    private boolean USERTAB;
    public NBSTraceUnit _nbs_trace;
    private ConfigManager configManager;
    private FrameLayout contentFrameLayout;
    private RelativeLayout haokawvLayout;
    private LiuyanManager liuyanManager;
    private NetworkConnectChangedReceiver mNetWorkChangReceiver;
    private MyFragmentTabHost mTabHost;
    private ManagerMainConfig managerMainConfig;
    private ManagerMainFuli managerMainFuli;
    private ManagerMainLogin managerMainLogin;
    private ManagerSomeOldConfig managerOldConfig;
    private ManagerUser managerUser;

    /* renamed from: pd */
    private CustomePorgressDialog f18404pd;
    private MainTabView tabView;
    private WebSocketManager webSocketManager;
    private RelativeLayout wvlayout;
    private final MainActivity activityContext = this;
    private String currentPhone = "0";
    private long exitTime = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$loadConfig$1(LoginConfigEntity loginConfigEntity) throws Exception {
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 56);
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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        if (!TextUtils.isEmpty(App.mainTagFromOtherActivity)) {
            changeFragment(App.mainTagFromOtherActivity);
            App.mainTagFromOtherActivity = null;
        } else if (TextUtils.equals(Fragment_Service, this.mTabHost.getCurrentTabTag()) && UserManager.getInstance().isYiwang()) {
            changeFragment(Fragment_YiWang);
        } else {
            changeFragment(this.mTabHost.getCurrentTabTag());
        }
        this.tabView.changeTabUIMain(this.mTabHost.getCurrentTabTag());
        if (!this.currentPhone.equals(UserManager.getInstance().getCurrentPhoneNumber())) {
            this.currentPhone = UserManager.getInstance().getCurrentPhoneNumber();
            this.HOMETAB = true;
            this.SERVICETAB = true;
            this.SHOPTAB = true;
            this.ADTAB = true;
            this.USERTAB = true;
            loadFuliTanchuang();
            loadBlackList();
            uploadLanguageLog();
            setTabUserView(null);
            ManagerHuiDu.getInstance().loadHuiduConfig();
            ManagerShareConfig.getInstance().loadData();
            MsLogUtil.m7979d("菜单数据", "账号发生改变 加载导航数据");
            initDaoHang();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    public void initDaoHang() {
        MsLogUtil.m7979d("菜单数据", "更新金刚区数据");
        MenuDataCenter.getInstance().loadMenuJinGangQu(this.activityContext, new MenuDataCenter.LoadMenuInterface() { // from class: com.sinovatech.unicom.basic.ui.activity.MainActivity.1
            @Override // com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.LoadMenuInterface
            public void onSuccess() {
                UnicomHomeTuiJianFragment tuiJianFragment;
                ServiceFragment serviceFragment;
                if (!UserManager.getInstance().isYiwang() && (serviceFragment = (ServiceFragment) MainActivity.this.getSupportFragmentManager().findFragmentByTag(MainActivity.Fragment_Service)) != null) {
                    MsLogUtil.m7979d("菜单数据", "存在 更新金刚区数据");
                    FuWuConstant.topList = MenuDataCenter.getInstance().getHomeDataNew(true);
                    serviceFragment.refresh();
                }
                UnicomHomeFragment unicomHomeFragment = (UnicomHomeFragment) MainActivity.this.getSupportFragmentManager().findFragmentByTag(MainActivity.Fragment_Home);
                if (unicomHomeFragment == null || (tuiJianFragment = unicomHomeFragment.getTuiJianFragment()) == null) {
                    return;
                }
                tuiJianFragment.updateJinGangQu();
            }
        });
        MenuDataCenter.getInstance().loadData(2, new MenuDataCenter.LoadMenuInterface() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$MainActivity$3pOoKlXYghdZd19MKBQ15wzMXMo
            @Override // com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.LoadMenuInterface
            public final void onSuccess() {
                MainActivity.lambda$initDaoHang$0(MainActivity.this);
            }
        });
    }

    public static /* synthetic */ void lambda$initDaoHang$0(MainActivity mainActivity) {
        MsLogUtil.m7979d("菜单数据", "更新导航数据");
        ServiceFragment serviceFragment = (ServiceFragment) mainActivity.getSupportFragmentManager().findFragmentByTag(Fragment_Service);
        if (serviceFragment != null) {
            MsLogUtil.m7979d("菜单数据", "存在 更新导航数据");
            serviceFragment.initData();
            serviceFragment.refresh();
            FuWuConstant.isRefreshList = false;
        }
    }

    public void ruandnelgu() {
        try {
            MsLogUtil.m7979d("菜单数据", "原来的手机号:" + this.currentPhone);
            MsLogUtil.m7979d("菜单数据", "当前的手机号:" + UserManager.getInstance().getCurrentPhoneNumber());
            if (this.currentPhone.equals(UserManager.getInstance().getCurrentPhoneNumber())) {
                return;
            }
            this.currentPhone = UserManager.getInstance().getCurrentPhoneNumber();
            this.HOMETAB = true;
            this.SERVICETAB = true;
            this.SHOPTAB = true;
            this.ADTAB = true;
            this.USERTAB = true;
            loadFuliTanchuang();
            loadBlackList();
            uploadLanguageLog();
            setTabUserView(null);
            ManagerHuiDu.getInstance().loadHuiduConfig();
            ManagerShareConfig.getInstance().loadData();
            MsLogUtil.m7979d("菜单数据", "账号发生改变 加载导航数据");
            initDaoHang();
        } catch (Exception unused) {
            MsLogUtil.m7978e("软登录异常");
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AuthJSPlugin.clearAuthDialogMap();
        AuthH5JSPlugin.clearAuthDialogMap();
        if ("1".equals(intent.getStringExtra("needDialog"))) {
            String stringExtra = intent.getStringExtra("desc");
            if ("2".equals(intent.getStringExtra("errorCode")) && !TextUtils.isEmpty(stringExtra)) {
                CustomDialogManager.show(this.activityContext, "温馨提示", stringExtra);
                return;
            }
            Intent intent2 = new Intent(this.activityContext, LoginBindActivity.class);
            intent2.putExtra("needDialog", "1");
            intent2.putExtra("errorCode", "1");
            intent2.putExtra("desc", stringExtra);
            this.activityContext.startActivity(intent2);
        } else if ("fromWebShareHome".equals(intent.getStringExtra("from"))) {
        } else {
            checkAnquanzhognxin();
        }
    }

    private void checkAnquanzhognxin() {
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.activity.MainActivity.2
            @Override // java.lang.Runnable
            public void run() {
                new AnquanzhognxinCheckStart().welcomStart(MainActivity.this.activityContext, UserManager.getInstance().getCurrentPhoneNumber(), new AnquanzhognxinCheckStart.StartCallBack() { // from class: com.sinovatech.unicom.basic.ui.activity.MainActivity.2.1
                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.StartCallBack
                    public void onCancel() {
                    }

                    @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.StartCallBack
                    public void complete() {
                        MainActivity.this.handleExternURL();
                        new WoKouLingManager(MainActivity.this).woKouLingCommond();
                    }
                });
            }
        }, 500L);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            UnicomHomeFragment.currentPhone = "";
            if (this.webSocketManager != null) {
                this.webSocketManager.closeConnect();
            }
            unregisterReceiver(this.mNetWorkChangReceiver);
            EventBusUtils.unregister(this);
        } catch (Exception unused) {
            MsLogUtil.m7978e("webSocketManager onDestroy()中关闭连接异常");
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            exit();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void loadConfig() {
        this.managerMainConfig.loadConfigInfo(false, new ManagerMainConfig.ConfigListener() { // from class: com.sinovatech.unicom.basic.ui.activity.MainActivity.3
            @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig.ConfigListener
            public void onBack() {
            }

            @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig.ConfigListener
            public void onExit() {
                MainActivity.this.finish();
            }
        });
        this.managerOldConfig.loadCopyCommond();
        this.managerOldConfig.loadCityData();
        WebHostSafelyManager.getInstance().loadHostSafetyConfig();
        YinSiDialogUtils.changeYinSiDialog(this.activityContext);
        LanguageUtil.getInstance().loadLanuageData(this.activityContext);
        ShareLogUtil.getDefaultShare(this.activityContext);
        LoginConfigDataCenter.getInstance().queryLoginConfig(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$MainActivity$fcO6eZodpLaPhjyz-B5yXvT7fXQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MainActivity.lambda$loadConfig$1((LoginConfigEntity) obj);
            }
        });
        TYCJConfigUtil.requestTongyicaijiConfigDataAll();
        new ManagerEdopYuyu(this.activityContext).queryYuYue();
        new ManagerMainNotic(this.activityContext).chedkPush();
        new NoticManager().getNoticConfigData();
        new GDTLogUtils().getClickInfo(this);
        checkEmulator();
        setGJR();
        startWebSocket(new WebSocketEvent(EventBusUtils.EVENTCODE_START_WEBSOCKET));
        uploadLanguageLog();
        loadBlackList();
        loadFuliTanchuang();
        ManagerShareConfig.getInstance().loadData();
        MsLogUtil.m7979d("菜单数据", "导航大接口（启动的时候）");
        initDaoHang();
        checkAnquanzhognxin();
        KacaoPvLog.log(this.activityContext, "冷启动");
    }

    private void initSomeConfig() {
        handlerFilter();
        UIUtils.setStatusBarMode(this.activityContext, false, true);
        EventBusUtils.register(this);
        handlerLoginResult();
        InitUtils.init(App.getInstance());
        this.currentPhone = UserManager.getInstance().getDefaultPhoneNumber();
        App.realexit = false;
        this.HOMETAB = true;
        this.SERVICETAB = true;
        this.SHOPTAB = true;
        this.ADTAB = true;
        this.USERTAB = true;
        this.mNetWorkChangReceiver = new NetworkConnectChangedReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.mNetWorkChangReceiver, intentFilter);
        if ("HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) && getPackageManager().hasSystemFeature("com.huawei.hardware.sensor.posture") && (getResources().getConfiguration().screenLayout & 15) == 3 && Build.VERSION.SDK_INT >= 24 && !this.activityContext.isInMultiWindowMode()) {
            CustomDialogManager.show((Activity) this.activityContext, "温馨提示", "为了您更好地体验,请在设置内的平行视界打开本APP的开关", false, "", "我知道了", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.MainActivity.4
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                }
            });
        }
        OptionValveUtil.INSTENCE.getInstence().getOptionInfo(this);
        setOnOpenHuaWeiZheDie(this);
        if ("1".equals(getIntent().getStringExtra("needDialog"))) {
            String stringExtra = getIntent().getStringExtra("desc");
            if ("2".equals(getIntent().getStringExtra("errorCode")) && !TextUtils.isEmpty(stringExtra)) {
                CustomDialogManager.show(this.activityContext, "温馨提示", stringExtra);
                return;
            }
            Intent intent = new Intent(this.activityContext, LoginBindActivity.class);
            intent.putExtra("needDialog", "1");
            intent.putExtra("errorCode", "1");
            intent.putExtra("desc", stringExtra);
            this.activityContext.startActivity(intent);
        }
    }

    private void initFragment() {
        this.mTabHost = (MyFragmentTabHost) findViewById(16908306);
        this.mTabHost.setup(getApplicationContext(), getSupportFragmentManager(), 2131298258);
        MyFragmentTabHost myFragmentTabHost = this.mTabHost;
        myFragmentTabHost.addTab(myFragmentTabHost.newTabSpec(Fragment_Home).setIndicator(Fragment_Home), UnicomHomeFragment.class, null);
        MyFragmentTabHost myFragmentTabHost2 = this.mTabHost;
        myFragmentTabHost2.addTab(myFragmentTabHost2.newTabSpec(Fragment_Service).setIndicator(Fragment_Service), ServiceFragment.class, null);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setUrl(URLSet.getShopURL());
        webParamsEntity.setTitle("商城");
        webParamsEntity.setType("shop");
        Bundle bundle = new Bundle();
        bundle.putParcelable("WebFragment_0", webParamsEntity);
        MyFragmentTabHost myFragmentTabHost3 = this.mTabHost;
        myFragmentTabHost3.addTab(myFragmentTabHost3.newTabSpec(Fragment_Shop).setIndicator(Fragment_Shop), WebFragment.class, bundle);
        WebParamsEntity webParamsEntity2 = new WebParamsEntity();
        webParamsEntity2.setNeedTitle(false);
        webParamsEntity2.setUrl(URLSet.getHomeFindUrl());
        webParamsEntity2.setType("find");
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("WebFragment_0", webParamsEntity2);
        WebFragment.isRelogin = true;
        MyFragmentTabHost myFragmentTabHost4 = this.mTabHost;
        myFragmentTabHost4.addTab(myFragmentTabHost4.newTabSpec(Fragment_Ad).setIndicator(Fragment_Ad), WebFragment.class, bundle2);
        MyFragmentTabHost myFragmentTabHost5 = this.mTabHost;
        myFragmentTabHost5.addTab(myFragmentTabHost5.newTabSpec(Fragment_User).setIndicator(Fragment_User), UserFragment.class, null);
        WebParamsEntity webParamsEntity3 = new WebParamsEntity();
        webParamsEntity3.setNeedTitle(false);
        webParamsEntity3.setUrl(this.configManager.getExtranetActivityPageUrl());
        webParamsEntity3.setType("yw");
        Bundle bundle3 = new Bundle();
        bundle3.putParcelable("WebFragment_0", webParamsEntity3);
        MyFragmentTabHost myFragmentTabHost6 = this.mTabHost;
        myFragmentTabHost6.addTab(myFragmentTabHost6.newTabSpec(Fragment_YiWang).setIndicator(Fragment_YiWang), WebFragment.class, bundle3);
        changeFragment(Fragment_Home);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeFragment(String str) {
        this.tabView.setIsYW(UserManager.getInstance().isYiwang());
        UIUtils.setStatusBarMode(this.activityContext, !TextUtils.equals(Fragment_Home, str), true);
        if (!str.equals(this.mTabHost.getCurrentTabTag())) {
            this.mTabHost.setCurrentTabByTag(str);
            if (str.equals(Fragment_Home)) {
                this.tabView.changeTabUI(MainTabView.TABTAG_HOME);
            }
        }
        this.mTabHost.setAnimationDirection("");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.MainActivity$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C74635 implements MainTabView.TabClickListener {
        C74635() {
        }

        @Override // com.sinovatech.unicom.basic.p315ui.view.MainTabView.TabClickListener
        public void clickHome() {
            MainActivity.this.changeFragment(MainActivity.Fragment_Home);
            SearchLayoutView.location = "";
            PopWebViewEvent.currentType = 4;
            MainActivity.this.loadFuliTanchuang();
            StatisticsUploadUtils.upload("1", "首页", "按钮", "0", "首页", "");
        }

        @Override // com.sinovatech.unicom.basic.p315ui.view.MainTabView.TabClickListener
        public void clickService() {
            MainActivity.this.changeFragment(MainActivity.Fragment_Service);
            FuWuSearchLayoutView.location = "2";
            PopWebViewEvent.currentType = 5;
            MainActivity.this.loadFuliTanchuang();
            StatisticsUploadUtils.upload("2", "服务", "按钮", "0", "服务", "");
        }

        @Override // com.sinovatech.unicom.basic.p315ui.view.MainTabView.TabClickListener
        public void clickHuodong() {
            MainActivity.this.changeFragment(MainActivity.Fragment_YiWang);
            SearchLayoutView.location = "";
            StatisticsUploadUtils.upload("2", "活动", "按钮", "0", "活动", "");
        }

        @Override // com.sinovatech.unicom.basic.p315ui.view.MainTabView.TabClickListener
        public void clickShop() {
            MainActivity.this.changeFragment(MainActivity.Fragment_Shop);
            SearchLayoutView.location = "";
            PopWebViewEvent.currentType = 6;
            MainActivity.this.loadFuliTanchuang();
            StatisticsUploadUtils.upload("3", "商城", "按钮", "0", "商城", "");
            if (MainActivity.Fragment_Shop.equals(MainActivity.this.mTabHost.getCurrentTabTag())) {
                Observable.timer(200L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$MainActivity$5$bqIRCzcookzUg-qpQpHpCL_FElE
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        MainActivity.C74635.lambda$clickShop$0(MainActivity.C74635.this, (Long) obj);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$clickShop$0(C74635 c74635, Long l) throws Exception {
            WebFragment webFragment = (WebFragment) MainActivity.this.getSupportFragmentManager().findFragmentByTag(MainActivity.Fragment_Shop);
            if (webFragment != null) {
                webFragment.sccfTabChangeShow();
            }
        }

        @Override // com.sinovatech.unicom.basic.p315ui.view.MainTabView.TabClickListener
        public void clickFind() {
            MainActivity.this.changeFragment(MainActivity.Fragment_Ad);
            SearchLayoutView.location = "3";
            PopWebViewEvent.currentType = 7;
            MainActivity.this.loadFuliTanchuang();
            StatisticsUploadUtils.upload("4", "财富", "按钮", "0", "财富", "");
            if (MainActivity.Fragment_Ad.equals(MainActivity.this.mTabHost.getCurrentTabTag())) {
                Observable.timer(200L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$MainActivity$5$d2y-XkRblZo4YIjbbGaa95OiuCw
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        MainActivity.C74635.lambda$clickFind$1(MainActivity.C74635.this, (Long) obj);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$clickFind$1(C74635 c74635, Long l) throws Exception {
            WebFragment webFragment = (WebFragment) MainActivity.this.getSupportFragmentManager().findFragmentByTag(MainActivity.Fragment_Ad);
            if (webFragment != null) {
                webFragment.sccfTabChangeShow();
            }
        }

        @Override // com.sinovatech.unicom.basic.p315ui.view.MainTabView.TabClickListener
        public void clickUser() {
            MainActivity.this.changeFragment(MainActivity.Fragment_User);
            SearchLayoutView.location = "";
            PopWebViewEvent.currentType = 8;
            MainActivity.this.loadFuliTanchuang();
            StatisticsUploadUtils.upload("5", "我的", "按钮", "0", "我的", "");
            MainActivity.this.setTabUserView(null);
        }
    }

    private void initListener() {
        this.tabView.setOnClick(new C74635());
        this.tabView.showRefresh(new MainTabView.TriggerRefresh() { // from class: com.sinovatech.unicom.basic.ui.activity.MainActivity.6
            @Override // com.sinovatech.unicom.basic.p315ui.view.MainTabView.TriggerRefresh
            public void showRefresh() {
                UnicomHomeFragment unicomHomeFragment = (UnicomHomeFragment) MainActivity.this.getSupportFragmentManager().findFragmentByTag(MainActivity.Fragment_Home);
                if (unicomHomeFragment != null) {
                    unicomHomeFragment.doubleClickAutoRefresh();
                }
            }
        });
        setTabUserView(null);
    }

    public void setTabUserView(final Consumer<Integer> consumer) {
        LiuyanManager liuyanManager = this.liuyanManager;
        if (liuyanManager != null) {
            liuyanManager.queryLiuyan(new Consumer<Integer>() { // from class: com.sinovatech.unicom.basic.ui.activity.MainActivity.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Integer num) throws Exception {
                    try {
                        MainActivity.this.tabView.setUserNum(new PushMsgDao(MainActivity.this.activityContext).getPushMessageRecordSize("0", "0"), num.intValue());
                        if (consumer != null) {
                            consumer.accept(num);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void setTrainsId(String str) {
        UnicomCollectManager.getInstance().setTransId(str + CollectConfig.montageTag1 + "端外拉起");
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0294 A[Catch: Exception -> 0x0358, TryCatch #0 {Exception -> 0x0358, blocks: (B:3:0x0002, B:5:0x000a, B:7:0x0014, B:11:0x0062, B:102:0x0348, B:8:0x0021, B:10:0x002b, B:12:0x006c, B:14:0x0076, B:16:0x0087, B:21:0x00e5, B:20:0x00e2, B:23:0x00ea, B:25:0x00f8, B:28:0x0102, B:30:0x010a, B:32:0x0122, B:33:0x0132, B:35:0x0138, B:37:0x0140, B:40:0x0146, B:42:0x0149, B:44:0x0154, B:48:0x0174, B:45:0x0160, B:47:0x016a, B:50:0x0179, B:52:0x01bb, B:53:0x01bd, B:55:0x01c8, B:56:0x01d1, B:58:0x01d9, B:77:0x0281, B:81:0x029c, B:83:0x02b6, B:85:0x02c7, B:84:0x02c5, B:80:0x0294, B:60:0x01f3, B:62:0x01fc, B:64:0x021d, B:66:0x0229, B:68:0x0250, B:69:0x0252, B:71:0x025d, B:65:0x0222, B:73:0x026b, B:75:0x0276, B:86:0x02d6, B:88:0x02da, B:89:0x02de, B:91:0x02e8, B:94:0x02f3, B:96:0x02fb, B:97:0x030c, B:99:0x0324, B:101:0x0339, B:100:0x0335, B:17:0x0098), top: B:107:0x0002, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02b6 A[Catch: Exception -> 0x0358, TryCatch #0 {Exception -> 0x0358, blocks: (B:3:0x0002, B:5:0x000a, B:7:0x0014, B:11:0x0062, B:102:0x0348, B:8:0x0021, B:10:0x002b, B:12:0x006c, B:14:0x0076, B:16:0x0087, B:21:0x00e5, B:20:0x00e2, B:23:0x00ea, B:25:0x00f8, B:28:0x0102, B:30:0x010a, B:32:0x0122, B:33:0x0132, B:35:0x0138, B:37:0x0140, B:40:0x0146, B:42:0x0149, B:44:0x0154, B:48:0x0174, B:45:0x0160, B:47:0x016a, B:50:0x0179, B:52:0x01bb, B:53:0x01bd, B:55:0x01c8, B:56:0x01d1, B:58:0x01d9, B:77:0x0281, B:81:0x029c, B:83:0x02b6, B:85:0x02c7, B:84:0x02c5, B:80:0x0294, B:60:0x01f3, B:62:0x01fc, B:64:0x021d, B:66:0x0229, B:68:0x0250, B:69:0x0252, B:71:0x025d, B:65:0x0222, B:73:0x026b, B:75:0x0276, B:86:0x02d6, B:88:0x02da, B:89:0x02de, B:91:0x02e8, B:94:0x02f3, B:96:0x02fb, B:97:0x030c, B:99:0x0324, B:101:0x0339, B:100:0x0335, B:17:0x0098), top: B:107:0x0002, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02c5 A[Catch: Exception -> 0x0358, TryCatch #0 {Exception -> 0x0358, blocks: (B:3:0x0002, B:5:0x000a, B:7:0x0014, B:11:0x0062, B:102:0x0348, B:8:0x0021, B:10:0x002b, B:12:0x006c, B:14:0x0076, B:16:0x0087, B:21:0x00e5, B:20:0x00e2, B:23:0x00ea, B:25:0x00f8, B:28:0x0102, B:30:0x010a, B:32:0x0122, B:33:0x0132, B:35:0x0138, B:37:0x0140, B:40:0x0146, B:42:0x0149, B:44:0x0154, B:48:0x0174, B:45:0x0160, B:47:0x016a, B:50:0x0179, B:52:0x01bb, B:53:0x01bd, B:55:0x01c8, B:56:0x01d1, B:58:0x01d9, B:77:0x0281, B:81:0x029c, B:83:0x02b6, B:85:0x02c7, B:84:0x02c5, B:80:0x0294, B:60:0x01f3, B:62:0x01fc, B:64:0x021d, B:66:0x0229, B:68:0x0250, B:69:0x0252, B:71:0x025d, B:65:0x0222, B:73:0x026b, B:75:0x0276, B:86:0x02d6, B:88:0x02da, B:89:0x02de, B:91:0x02e8, B:94:0x02f3, B:96:0x02fb, B:97:0x030c, B:99:0x0324, B:101:0x0339, B:100:0x0335, B:17:0x0098), top: B:107:0x0002, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleExternURL() {
        /*
            Method dump skipped, instructions count: 861
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.MainActivity.handleExternURL():void");
    }

    private boolean dingweiTab(final String str) {
        if (TextUtils.isEmpty(str) || !str.contains("homeTabNavCode")) {
            return false;
        }
        try {
            final String str2 = getQueryParams(str).get("homeTabNavCode");
            if (TextUtils.isEmpty(str2)) {
                return false;
            }
            if (!isHOMETAB()) {
                changeFragment(Fragment_Home);
            }
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.activity.MainActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    HomeJumpTabEvent homeJumpTabEvent = new HomeJumpTabEvent(0);
                    homeJumpTabEvent.setNavCode(str2);
                    homeJumpTabEvent.setJsonObject(new JSONObject());
                    homeJumpTabEvent.setUrl(str);
                    EventBusUtils.post(homeJumpTabEvent);
                }
            }, 200L);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, String> getQueryParams(String str) {
        HashMap hashMap = new HashMap();
        try {
            int indexOf = str.indexOf(63);
            if (indexOf >= 0) {
                for (String str2 : str.substring(indexOf + 1).split("&")) {
                    String[] split = str2.split("=", 2);
                    String decode = URLDecoder.decode(split[0], "UTF-8");
                    String decode2 = split.length == 2 ? URLDecoder.decode(split[1], "UTF-8") : "";
                    if (decode2.startsWith("http")) {
                        hashMap.putAll(getQueryParams(decode2));
                    } else {
                        hashMap.put(decode, decode2);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    private void loadBlackList() {
        ManagerUser managerUser = this.managerUser;
        if (managerUser != null) {
            managerUser.getToutiaoBlack();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadFuliTanchuang() {
        try {
            switch (PopWebViewEvent.currentType) {
                case 4:
                    if (this.HOMETAB) {
                        this.managerMainFuli.initPopAdWebview(this.wvlayout, PopWebViewEvent.currentType);
                    }
                    this.HOMETAB = false;
                    return;
                case 5:
                    if (this.SERVICETAB) {
                        this.managerMainFuli.initPopAdWebview(this.wvlayout, PopWebViewEvent.currentType);
                    }
                    this.SERVICETAB = false;
                    return;
                case 6:
                    if (this.SHOPTAB) {
                        this.managerMainFuli.initPopAdWebview(this.wvlayout, PopWebViewEvent.currentType);
                    }
                    this.SHOPTAB = false;
                    return;
                case 7:
                    if (this.ADTAB) {
                        this.managerMainFuli.initPopAdWebview(this.wvlayout, PopWebViewEvent.currentType);
                    }
                    this.ADTAB = false;
                    return;
                case 8:
                    if (this.USERTAB) {
                        this.managerMainFuli.initPopAdWebview(this.wvlayout, PopWebViewEvent.currentType);
                    }
                    this.USERTAB = false;
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void startWebSocket(WebSocketEvent webSocketEvent) {
        try {
            if (webSocketEvent.getCode() == EventBusUtils.EVENTCODE_START_WEBSOCKET) {
                if (this.webSocketManager != null) {
                    this.webSocketManager.startConnect();
                    MsLogUtil.m7979d("WebSocketEvent", "---启动连接");
                }
            } else if (webSocketEvent.getCode() == EventBusUtils.EVENTCODE_REFRESH_WEBSOCKET) {
                if (!WebSocketManager.webSocketIsActive && this.webSocketManager != null) {
                    this.webSocketManager.startConnect();
                    MsLogUtil.m7979d("WebSocketEvent", "---刷新连接");
                }
            } else if (webSocketEvent.getCode() == EventBusUtils.EVENTCODE_CLOSE_WEBSOCKET && this.webSocketManager != null) {
                this.webSocketManager.closeConnect();
                MsLogUtil.m7979d("WebSocketEvent", "---关闭连接");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mainTabChange(MainTabEvent mainTabEvent) {
        try {
            if (this.tabView != null) {
                this.tabView.setTabText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setWvlayoutVisible(PopWebViewEvent popWebViewEvent) {
        this.managerMainFuli.setWvlayoutVisible(popWebViewEvent, this.wvlayout);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void closeHome(CustomActivityEvent customActivityEvent) {
        if (EventBusUtils.EVENT_MAIN_CLOSE_MAIN_ACTIVITY == customActivityEvent.getCode()) {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeMainTabIcon(ChangeMainTabIconEvent changeMainTabIconEvent) {
        if (changeMainTabIconEvent != null) {
            try {
                if (this.tabView != null && changeMainTabIconEvent.getTab_code() == ChangeMainTabIconEvent.TAB_HOME) {
                    this.tabView.changeTabIcon(changeMainTabIconEvent.getIconUrl(), changeMainTabIconEvent.getTextPictureColor());
                }
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void tiaoZhuanFuWu(ServiceMenuEvent serviceMenuEvent) {
        if (serviceMenuEvent.getCode() == EventBusUtils.EVENT_FUWU_TIAOZHUAN) {
            try {
                if (this.tabView != null) {
                    this.tabView.changeTabUI(MainTabView.TABTAG_SERVICE);
                }
                changeFragment(Fragment_Service);
                FuWuSearchLayoutView.location = "2";
                PopWebViewEvent.currentType = 5;
                loadFuliTanchuang();
                FuWuConstant.isShouYe = true;
                StatisticsUploadUtils.upload("2", "服务", "按钮", "0", "服务", "");
            } catch (Exception unused) {
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void tiaoZhuanShouYe(ServiceMenuEvent serviceMenuEvent) {
        if (serviceMenuEvent.getCode() == EventBusUtils.EVENT_SHOUYE_TIAOZHUAN) {
            try {
                if (this.tabView != null) {
                    this.tabView.changeTabUI(MainTabView.TABTAG_HOME);
                }
                changeFragment(Fragment_Home);
                SearchLayoutView.location = "";
                PopWebViewEvent.currentType = 4;
                loadFuliTanchuang();
                StatisticsUploadUtils.upload("1", "首页", "按钮", "0", "首页", "");
            } catch (Exception unused) {
            }
        }
    }

    public void handlerLoginResult() {
        WelcomLoginEvent welcomLoginEvent = (WelcomLoginEvent) EventBus.getDefault().getStickyEvent(WelcomLoginEvent.class);
        if (welcomLoginEvent != null) {
            ManagerMainLogin.getManagerMainLogin().handlerLoginResult(this, welcomLoginEvent.getData());
            EventBusUtils.remove(welcomLoginEvent);
        }
        ManagerMainLogin.getManagerMainLogin().refreshOnlineToken(this.activityContext);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity.OnOpenHuaWeiZheDie
    public void onZheDieChange() {
        try {
            UnicomHomeFragment unicomHomeFragment = (UnicomHomeFragment) getSupportFragmentManager().findFragmentByTag(Fragment_Home);
            if (unicomHomeFragment != null) {
                unicomHomeFragment.doubleClickAutoRefresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            UIUtils.toast("再按一次退出中国联通APP");
            this.exitTime = System.currentTimeMillis();
            return;
        }
        realExit();
    }

    private void realExit() {
        App.setLogined(LoginStateConst.UNLOGIN);
        try {
            UnicomHomeConstants.isShowLanguageWindow = true;
            App.realexit = true;
            App.homeCardBg = false;
            App.cardBgMap.clear();
            UserFragment.currentPhone = "";
            ManagerLocation.releaseManagerLocation();
            this.managerMainLogin.stopRefreshOnlineToken();
            App.isCityToHome = false;
            App.isShowFingerdialog = false;
            App.getSharePreferenceUtil().putString("lat", "");
            App.getSharePreferenceUtil().putString("long", "");
            TYCJUploadManager.releaseInterval();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MsLogUtil.m7979d("tongjishichang", "0结束计时：" + System.currentTimeMillis());
        finish();
    }

    public int getMainTabLayoutHeight() {
        return this.contentFrameLayout.getHeight();
    }

    private void handlerFilter() {
        try {
            IntentManager.intentFilter(this, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadLanguageLog() {
        try {
            String language = LanguageUtil.getInstance().getLanguage();
            StatisticsUploadUtils.uploadBeiDong(this.activityContext, "smulti0001", "多语言", "", "", TextUtils.isEmpty(language) ? LanguageUtil.CHN_CN : language, "", "", "", ",", "");
        } catch (Exception e) {
            UIUtils.logE("uploadLanguageLog", e.getMessage());
        }
    }

    private void setGJR() {
        MainTabView mainTabView = this.tabView;
        if (mainTabView != null) {
            mainTabView.setGJR();
        }
    }

    private void checkEmulator() {
        try {
            if (RealAndroidUtils.isEmulator(this)) {
                StatisticsUploadUtils.upload("S2ndpage1050", "模拟器", DeviceHelper.getDeviceBrand(), DeviceHelper.getDeviceModel(), RealAndroidUtils.realMsg, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isHOMETAB() {
        return Fragment_Home.equals(this.mTabHost.getCurrentTabTag());
    }

    public boolean isTuijian() {
        UnicomHomeFragment unicomHomeFragment = (UnicomHomeFragment) getSupportFragmentManager().findFragmentByTag(Fragment_Home);
        if (unicomHomeFragment == null) {
            return false;
        }
        return unicomHomeFragment.isHomeTabCurrentItemIndex();
    }

    public boolean isUSERTAB() {
        return Fragment_User.equals(this.mTabHost.getCurrentTabTag());
    }

    public void showOrDismisspd(boolean z) {
        if (z) {
            if (this.f18404pd == null) {
                this.f18404pd = new CustomePorgressDialog(this);
                this.f18404pd.setCanceledOnTouchOutside(false);
                this.f18404pd.setCancelable(false);
            }
            this.f18404pd.show();
            return;
        }
        CustomePorgressDialog customePorgressDialog = this.f18404pd;
        if (customePorgressDialog != null) {
            customePorgressDialog.dismiss();
        }
    }

    public void showHomeAlertVisiable(int i, String str) {
        try {
            if (i == 0) {
                if (!isHOMETAB()) {
                    this.haokawvLayout.setVisibility(8);
                    MsLogUtil.m7979d("showHomeAlertVisiable", "切换到别的Tab");
                    return;
                }
                UnicomHomeFragment unicomHomeFragment = (UnicomHomeFragment) getSupportFragmentManager().findFragmentByTag(Fragment_Home);
                if (unicomHomeFragment == null) {
                    this.haokawvLayout.setVisibility(8);
                    MsLogUtil.m7979d("showHomeAlertVisiable", "获取不到fragment");
                    return;
                }
                String homeTabCurrentItemUrl = unicomHomeFragment.getHomeTabCurrentItemUrl();
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(homeTabCurrentItemUrl)) {
                    if (!TextUtils.equals(homeTabCurrentItemUrl, str)) {
                        this.haokawvLayout.setVisibility(8);
                        MsLogUtil.m7979d("showHomeAlertVisiable", "url不相等");
                        MsLogUtil.m7979d("showHomeAlertVisiable", "currentUrl：" + homeTabCurrentItemUrl);
                        MsLogUtil.m7979d("showHomeAlertVisiable", "lastUrl：" + str);
                        return;
                    }
                    this.haokawvLayout.setVisibility(0);
                    return;
                }
                this.haokawvLayout.setVisibility(8);
                return;
            }
            this.haokawvLayout.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
