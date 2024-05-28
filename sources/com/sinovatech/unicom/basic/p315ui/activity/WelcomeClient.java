package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.fort.andjni.JniLib;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.megvii.livenesslib.util.RootUtil;
import com.mob.MobSDK;
import com.mob.OperationCallback;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.eventbus.FinishtEvent;
import com.sinovatech.unicom.basic.p314po.HeaderEntity;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import com.sinovatech.unicom.basic.p315ui.home.util.DialogUtils;
import com.sinovatech.unicom.basic.p315ui.manager.HomeCardDataManager;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainLogin;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerWelcome;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginMemberManager;
import com.sinovatech.unicom.basic.server.ManagerBitmapCache;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.CustomDensityHandler;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.InitUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SharePreferenceForOverlaySteup;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SignUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.bean.SplashAdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.ManagerTianyan;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.user.UserFragment;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.WelcomeClient */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WelcomeClient extends AppCompatActivity {
    private static final String TAG = "WelcomeClient";
    public static long appOnCreate;
    public static boolean pictureIsReady;
    public NBSTraceUnit _nbs_trace;
    public long adDataStartRequestTime;
    public long adFinishTime;
    public long adStartShowTime;
    public long adUrlStartRequestTime;
    public long appHotFlag;
    public long configFinishTime;
    private ConfigManager configManager;
    public long configStartTime;
    private String externUrl;
    private boolean hasToMain;
    private IjkVideoView ijkVideoView;
    private ImageView mImgBottomLogo;
    private ManagerMainLogin managerMainLogin;
    private ManagerWelcome managerWelcome;
    public long onlineFinishTime;
    public long onlineStartTime;
    private SharePreferenceUtil preference;
    private int shortcutInfoImg;
    private FrameLayout splash_container;
    private UserManager userManager;
    private long welComeOnCreate;
    public long welComeOnCreateTime;
    private long ziyuanTime;
    private int[] quickResources = {2131231190, 2131230819, 2131232040, 2131232039, 2131231975, 2131232425, 2131232041, 2131231273, 2131232225, 2131232433};
    private AppCompatActivity context = this;
    private boolean isOutside = false;
    private boolean isLoadAd = false;
    private boolean isWaring = false;
    public boolean configSuccess = false;
    public boolean refreshOnlineSuccess = false;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 63);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.WelcomeClient$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C75221 implements IAdInterface.ISplashAdCallBack {
        C75221() {
        }

        @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.ISplashAdCallBack
        public void onResult(int i, String str) {
            WelcomeClient.this.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.1.1
                @Override // java.lang.Runnable
                public void run() {
                    WelcomeClient.this.checkYinsiDialog();
                }
            });
        }
    }

    private void initView() {
        this.splash_container = (FrameLayout) findViewById(2131298650);
        this.mImgBottomLogo = (ImageView) findViewById(2131299603);
        if (!this.isLoadAd && UIUtils.isFoldScreen(this)) {
            getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(2131100060)));
            this.splash_container.removeAllViews();
            this.splash_container.addView(LayoutInflater.from(this).inflate(2131493558, (ViewGroup) null));
        }
        this.ijkVideoView = (IjkVideoView) findViewById(2131298651);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        MsLogUtil.m7979d("welcomBgShowTime", "welcomBgShowTime:onResume");
        if (this.hasToMain) {
            this.hasToMain = false;
            goMain();
        }
        if (App.getSharePreferenceUtil().getBoolean("hasShowYinsi")) {
            ManagerTianyan.collectLunchTime();
            App.qidongFlag = "1";
        }
        this.ziyuanTime = System.currentTimeMillis() - BaseActivity.appOnCreateTime;
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
        IjkVideoView ijkVideoView = this.ijkVideoView;
        if (ijkVideoView != null) {
            ijkVideoView.release();
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (App.getSharePreferenceUtil().getBoolean("hasShowYinsi")) {
            initConfig();
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    public void checkYinsiDialog() {
        try {
            if (App.getSharePreferenceUtil().getBoolean("BasicCustom")) {
                MsLogUtil.m7979d("APPSESSIONID", "进入简版页面");
                App.setPvLogSessionId();
                App.reEnterAfterCloseApplication_ForGesturePassword = true;
                App.reEnterAfterCloseApplication_ForCitySelected = true;
                this.context.startActivity(new Intent(this, JianBanActivity.class));
                finish();
                return;
            }
            checkLoginStatus();
            if (App.getSharePreferenceUtil().getBoolean("hasShowYinsi")) {
                init();
                return;
            }
            final Dialog dialog = new Dialog(SoulPermission.getInstance().getTopActivity(), 2131952273);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(17);
            View inflate = this.context.getLayoutInflater().inflate(2131493030, (ViewGroup) null);
            final EditText editText = (EditText) inflate.findViewById(2131296869);
            TextView textView = (TextView) inflate.findViewById(2131296868);
            TextView textView2 = (TextView) inflate.findViewById(2131296866);
            inflate.findViewById(2131296871).setVisibility(8);
            ((LinearLayout) inflate.findViewById(2131296867)).setVisibility(0);
            ((LinearLayout) inflate.findViewById(2131296883)).setVisibility(8);
            textView2.setText("不同意");
            editText.setText(Html.fromHtml("为了让您更好地了解我们是如何保护用户的个人信息，请您在使用我们软件之前，仔细阅读<font color='#e60028'><a href='yinsi'>《中国联通APP用户隐私政策》</a></font>、<font color='#e60028'><a href='liantongyinsi'>《中国联通用户隐私政策》</a></font>及<font color='#e60028'><a href='xieyi'>《中国联通APP用户服务协议》</a></font>。<br/>您可以通过【我的】-【右上角设置】-【协议与条款】查看<font color='#e60028'><a href='yinsi'>《中国联通APP用户隐私政策》</a></font>、<font color='#e60028'><a href='liantongyinsi'>《中国联通用户隐私政策》</a></font>及<font color='#e60028'><a href='xieyi'>《中国联通APP用户服务协议》</a></font>。"));
            editText.setHighlightColor(Color.parseColor("#00000000"));
            editText.setMovementMethod(LinkMovementMethod.getInstance());
            Editable text = editText.getText();
            if (text instanceof Spannable) {
                int length = text.length();
                Editable text2 = editText.getText();
                URLSpan[] uRLSpanArr = (URLSpan[]) text2.getSpans(0, length, URLSpan.class);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
                spannableStringBuilder.clearSpans();
                for (final URLSpan uRLSpan : uRLSpanArr) {
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.2
                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor("#ffe60028"));
                            textPaint.setUnderlineText(false);
                        }

                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            if ("yinsi".equals(uRLSpan.getURL())) {
                                WelcomeClient.this.toXieyi(URLSet.getUserPrivacy());
                            } else if ("xieyi".equals(uRLSpan.getURL())) {
                                WelcomeClient.this.toXieyi(URLSet.getUserserver());
                            } else if ("liantongyinsi".equals(uRLSpan.getURL())) {
                                WelcomeClient.this.toXieyi(URLSet.getunicom_yinsizhengce());
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    }, text2.getSpanStart(uRLSpan), text2.getSpanEnd(uRLSpan), 33);
                }
                editText.setText(spannableStringBuilder);
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
                editText.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.3
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        NBSActionInstrumentation.onLongClickEventEnter(view, this);
                        editText.setVisibility(0);
                        NBSActionInstrumentation.onLongClickEventExit();
                        return true;
                    }
                });
            }
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$WelcomeClient$YAzCBW4SNPyG7w2NkGgPZygMVX4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WelcomeClient.lambda$checkYinsiDialog$0(WelcomeClient.this, dialog, view);
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (r2) {
                        dialog.cancel();
                        DialogUtils.checkYinsiTiShiDialog(WelcomeClient.this.context, "1");
                    } else {
                        dialog.cancel();
                        WelcomeClient.this.checkYinsiConfirmDialog();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.show();
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = (int) (UIUtils.getScreenWidth((Activity) this.context) * 0.8d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$checkYinsiDialog$0(WelcomeClient welcomeClient, Dialog dialog, View view) {
        App.isClickWelcomeXieyi = true;
        dialog.cancel();
        new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.4
            @Override // java.lang.Runnable
            public void run() {
                InitUtils.initApp(WelcomeClient.this.getApplication());
            }
        });
        welcomeClient.init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkYinsiConfirmDialog() {
        try {
            final Dialog dialog = new Dialog(SoulPermission.getInstance().getTopActivity(), 2131952273);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(17);
            View inflate = this.context.getLayoutInflater().inflate(2131493029, (ViewGroup) null);
            ((TextView) inflate.findViewById(2131296873)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$WelcomeClient$ag-leDu-g-mC-b7Wpj5KQ09_Ut8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WelcomeClient.lambda$checkYinsiConfirmDialog$1(WelcomeClient.this, dialog, view);
                }
            });
            ((TextView) inflate.findViewById(2131296864)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    WelcomeClient.this.context.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.show();
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = (int) (UIUtils.getScreenWidth((Activity) this.context) * 0.8d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$checkYinsiConfirmDialog$1(WelcomeClient welcomeClient, Dialog dialog, View view) {
        dialog.cancel();
        welcomeClient.checkYinsiDialog();
    }

    public void init() {
        MsLogUtil.m7979d("APPSESSIONID", "同意隐私协议");
        App.setPvLogSessionId();
        this.isWaring = false;
        this.managerWelcome = new ManagerWelcome(this);
        this.managerMainLogin = ManagerMainLogin.getManagerMainLogin();
        this.preference = App.getSharePreferenceUtil();
        this.userManager = UserManager.getInstance();
        this.configManager = new ConfigManager(getApplicationContext());
        this.configSuccess = false;
        this.refreshOnlineSuccess = false;
        App.setLogined(LoginStateConst.UNLOGIN);
        if (!TextUtils.isEmpty(this.userManager.getUserAccountName()) && !TextUtils.isEmpty(this.userManager.getUserPassword())) {
            App.setLogined(LoginStateConst.DID_LOGIN);
        }
        MsLogUtil.m7979d("LockScreenManager", "====回到weilcome,开始执行initConfig---");
        MsLogUtil.m7979d("LockScreenManager", "====initConfig执行完毕---");
        this.onlineStartTime = System.currentTimeMillis();
        App.isWelcomeStartApp = true;
        new ManagerMainConfig(this).loadConfigInfo(true, new ManagerMainConfig.ConfigListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.7
            @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig.ConfigListener
            public void onBack() {
                WelcomeClient.this.configFinishTime = System.currentTimeMillis() - WelcomeClient.this.configStartTime;
                MsLogUtil.m7979d(WelcomeClient.TAG, "+++++++++启动日志：开始加载config：" + WelcomeClient.this.configFinishTime);
                if (!TextUtils.isEmpty(WelcomeClient.this.configManager.getActivityDomainString())) {
                    App.getAsyncHttpClient().get(WelcomeClient.this.configManager.getActivityDomainString(), new AsyncHttpResponseHandler());
                }
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "配置项接口逻辑执行完毕");
                WelcomeClient welcomeClient = WelcomeClient.this;
                welcomeClient.configSuccess = true;
                welcomeClient.runUnciomApp();
            }

            @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig.ConfigListener
            public void onExit() {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "配置项接口执行完毕  退出应用");
                WelcomeClient.this.finish();
            }
        });
        this.managerMainLogin.welcomRefreshToken(this.context, "1", new ManagerMainLogin.LoginCompleteInterface() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$WelcomeClient$5O9woXfuxixntTfim-sQrx__Vro
            @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMainLogin.LoginCompleteInterface
            public final void complete() {
                WelcomeClient.lambda$init$2(WelcomeClient.this);
            }
        });
        String string = App.getSharePreferenceUtil().getString("HomeCumpAppIdConfig");
        MainTabCumpLauncher mainTabCumpLauncher = new MainTabCumpLauncher(this.context, "S2ndpage1229");
        if (TextUtils.isEmpty(string)) {
            string = URLSet.getHomeTuiJianDefUrlAndCumpUrl("appId");
        }
        mainTabCumpLauncher.loadCumpConfig(string);
        String string2 = App.getSharePreferenceUtil().getString("WoDeXiaoHeiTiaoCumpAppIdConfig");
        MainTabCumpLauncher mainTabCumpLauncher2 = new MainTabCumpLauncher(this.context, "S2ndpage1249");
        if (TextUtils.isEmpty(string2)) {
            string2 = URLSet.getWodexiaoheitiaoCumpAppId();
        }
        mainTabCumpLauncher2.loadCumpConfig(string2);
        PrefetchCumpLauncher.getInstance(this.context).prefetchCumpConfig(App.getSharePreferenceUtil().getString("MainTabPrefetchAppId"));
        ManagerHuiDu.getInstance().loadHuiduConfig();
    }

    public static /* synthetic */ void lambda$init$2(WelcomeClient welcomeClient) {
        MsLogUtil.m7979d("LockScreenManager", "====online执行完毕---");
        welcomeClient.onlineFinishTime = System.currentTimeMillis() - welcomeClient.onlineStartTime;
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：开始加载online：" + welcomeClient.onlineFinishTime);
        welcomeClient.refreshOnlineSuccess = true;
        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "刷新token接口逻辑执行完毕");
        welcomeClient.runUnciomApp();
    }

    public void runUnciomApp() {
        if (this.configSuccess && this.refreshOnlineSuccess) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "两个接口都执行成功 启动应用");
            initConfig();
            prepareLaunchClient();
            App.getSharePreferenceUtil().putBoolean("tanyan_is_new", true);
            ManagerTianyan.collectInfo(DeviceHelper.getDeviceID(true), DeviceHelper.getDeviceID(true), !App.getSharePreferenceUtil().getBoolean("tanyan_is_new"), App.getPvLogSessionId());
            this.configSuccess = false;
            this.refreshOnlineSuccess = false;
        }
    }

    private void initConfig() {
        try {
            this.isOutside = false;
            pictureIsReady = false;
            App.refreshXiaoxiOnUserResume = true;
            UserFragment.currentPhone = "";
            App.getSharePreferenceUtil().putBoolean("hasShowYinsi", true);
            EventBusUtils.register(this);
            MobSDK.submitPolicyGrantResult(true, new OperationCallback<Void>() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.8
                @Override // com.mob.OperationCallback
                public void onComplete(Void r2) {
                    UIUtils.logD("jia-yinsi", "隐私协议回调成功");
                }

                @Override // com.mob.OperationCallback
                public void onFailure(Throwable th) {
                    UIUtils.logD("jia-yinsi", "隐私协议回调失败" + th.getMessage());
                }
            });
            try {
                Intent intent = getIntent();
                if (App.formWechat) {
                    App.isHandleWoKouLing = false;
                    UIUtils.clearUnicomClipText(this);
                    App.formWechat = false;
                }
                if (getIntent() != null && getIntent().getData() != null) {
                    App.externURLFromBrowser = "";
                    App.externURLQQMiniGame = null;
                    App.externURL = "";
                    Uri data = intent.getData();
                    String uri = data.toString();
                    String str = "";
                    if (!TextUtils.isEmpty(uri) && uri.contains("GDTEncode=")) {
                        String[] split = uri.split("GDTEncode=");
                        if (split != null && split.length > 1) {
                            String str2 = split[1];
                            if (!TextUtils.isEmpty(str2)) {
                                uri = new String(Base64.decode(str2, 2));
                            }
                        }
                    } else {
                        str = data.getQueryParameter("link");
                    }
                    if (!TextUtils.isEmpty(str)) {
                        App.externURLQQMiniGame = data;
                    } else {
                        if (!uri.startsWith("https://u.10010.cn") && !uri.startsWith("http://u.10010.cn")) {
                            this.externUrl = uri;
                            this.externUrl = URLDecoder.decode(this.externUrl, "utf-8");
                            App.externURLFromBrowser = this.externUrl;
                        }
                        App.externURL = data.toString();
                    }
                    UIUtils.logD("WelcomeClient2", this.externUrl);
                }
                if (getIntent() != null) {
                    String stringExtra = getIntent().getStringExtra("enterType");
                    if (!TextUtils.isEmpty(stringExtra) && "quickAccessType".equals(stringExtra)) {
                        App.quickAccessURL = getIntent().getStringExtra("url");
                        App.quickAccessTitle = getIntent().getStringExtra("title");
                    }
                }
            } catch (Exception e) {
                MsLogUtil.m7978e("解析端外打开手厅时解析数据异常:" + e.getMessage());
            }
            if ((getIntent().getFlags() & 4194304) != 0) {
                if (TextUtils.isEmpty(this.externUrl)) {
                    return;
                }
                gotoMainActivity();
                return;
            }
            UnicomCookieManager.addDevicedCookie();
            UnicomCookieManager.addCVersionCookie();
            if (this.configManager.getHttpsSwitchKey().equals("0")) {
                URLEnvironmentConfig.switchHttps(false);
            } else {
                URLEnvironmentConfig.switchHttps(true);
            }
            if (!URLSet.Debug_mode && !SignUtils.isEquals(this)) {
                CustomDialogManager.show((Activity) this, "温馨提示", "当前安装包存在安全风险，请从正规渠道重新下载安装", false, "", "关闭", false, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.9
                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                    public void onClickOk() {
                        WelcomeClient.this.finish();
                    }
                });
                return;
            }
            getMenu();
            initProvinceCode();
            initSkin();
            checkRoot();
            LoginMemberManager.clearMemberData();
            LoginMemberManager.clearTuiJianData();
            this.configStartTime = System.currentTimeMillis();
            shangbaoShiyongshichang();
            initClientsStructure();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void prepareLaunchClient() {
        try {
            this.isWaring = false;
            if (!URLSet.Debug_mode) {
                if (getPackageName().endsWith("beta")) {
                    CustomDialogManager.show((Activity) this, "", "感谢您使用中国联通APP体验版。\n版本号:" + getResources().getString(2131886225) + "\n\n说明:体验版和正式版是两个独立的APP，分享、支付功能需要单独申请密钥，暂不支持使用。", false, "", "确定", false, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.10
                        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                        public void onClickOk() {
                            WelcomeClient.this.qidong();
                        }
                    });
                    return;
                }
                qidong();
                return;
            }
            String string = getPackageManager().getActivityInfo(getComponentName(), 128).metaData.getString("expires");
            final boolean after = new SimpleDateFormat(JtDateUtil.dateFormatYMDHM).parse(string).after(new Date());
            String string2 = App.getSharePreferenceUtil().getString("switch_environment");
            if (TextUtils.isEmpty(string2)) {
                string2 = URLEnvironmentConfig.getDevelopmentEnvironmentSubType();
            }
            String str = App.getSharePreferenceUtil().getBoolean("HomeCumpPublishType") ? "体验版" : "正式版";
            CustomDialogManager.show((Activity) this, "警告:切换环境后需需杀进程并重新登录", "当前环境：" + string2 + "\n首页小程序类型：" + str + "\n是否采集专用测试环境包：" + URLEnvironmentConfig.isForTYCJTest() + "\n地址：" + URLEnvironmentConfig.getAppServerURLNoProtocol() + "\n截止日期：" + string, "NO".equals("NO"), "切换环境", "确定", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.11
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onBackKeyDown() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onCancel() {
                    WelcomeClient.this.isWaring = false;
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onShow() {
                    WelcomeClient.this.isWaring = true;
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickOk() {
                    WelcomeClient.this.isWaring = false;
                    if (after) {
                        WelcomeClient.this.qidong();
                    } else {
                        WelcomeClient.this.finish();
                    }
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickCancel() {
                    WelcomeClient.this.isWaring = false;
                    WelcomeClient welcomeClient = WelcomeClient.this;
                    welcomeClient.startActivity(new Intent(welcomeClient.context, EnvironmentActivity.class));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            qidong();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qidong() {
        enter();
    }

    public void enter() {
        if (!this.preference.getBoolean("isShowWelcomeGuide")) {
            try {
                new Thread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.12
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            MenuDataCenter.getInstance().updateData(FileTools.readInputStream(WelcomeClient.this.getResources().getAssets().open("default_menu.json")), "0", "098");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            MobSDK.submitPolicyGrantResult(true, (OperationCallback<Void>) null);
            startActivity(new Intent(this, GuideActivity.class));
            finish();
        } else if (!"0".equals(App.getSharePreferenceUtil().getString("unicom_app_main_type"))) {
            goMain();
        } else {
            loadKaiPingData();
        }
    }

    private void handleAdView(int i, String str) {
        if (i == 11) {
            goMain();
            return;
        }
        CustomDensityHandler.setCustomDensity(this, getApplication());
        this.mImgBottomLogo.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mImgBottomLogo.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = UIUtils.dip2px(this, 100.0f);
        this.mImgBottomLogo.setLayoutParams(layoutParams);
        pictureIsReady = true;
        TextView textView = (TextView) findViewById(2131298652);
        if (TextUtils.equals("UNIOCMPIC", str)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private void loadKaiPingData() {
        this.isLoadAd = true;
        if (!TextUtils.isEmpty(App.externURLFromBrowser) || !TextUtils.isEmpty(App.externURL) || !TextUtils.isEmpty(App.quickAccessURL)) {
            this.isOutside = true;
            gotoMainActivity();
            return;
        }
        this.isOutside = false;
        ((ObservableSubscribeProxy) Observable.timer(5000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.context))).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.13
            @Override // io.reactivex.functions.Consumer
            public void accept(Long l) throws Exception {
                if (WelcomeClient.pictureIsReady) {
                    return;
                }
                WelcomeClient.this.gotoMainActivity();
            }
        });
        this.adUrlStartRequestTime = System.currentTimeMillis() - BaseActivity.appOnCreateTime;
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：广告url开始请求：" + this.adUrlStartRequestTime);
        this.managerWelcome.loadAdvertise().subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$WelcomeClient$FN7a1MqK2x7jcHe1iKvkSlCU-ks
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                WelcomeClient.lambda$loadKaiPingData$5(WelcomeClient.this, (SplashAdConfigEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$WelcomeClient$dRLwFlgTs_3qoipK9j11KoRf1H4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                WelcomeClient.this.goMain();
            }
        });
    }

    public static /* synthetic */ void lambda$loadKaiPingData$5(final WelcomeClient welcomeClient, SplashAdConfigEntity splashAdConfigEntity) throws Exception {
        long j;
        long j2 = appOnCreate;
        if (j2 != 0) {
            j = welcomeClient.welComeOnCreate - j2;
            appOnCreate = 0L;
        } else {
            j = 0;
        }
        long currentTimeMillis = 1500 - ((System.currentTimeMillis() - welcomeClient.welComeOnCreate) + j);
        long j3 = (currentTimeMillis < 0 || currentTimeMillis > 1500) ? 0L : currentTimeMillis;
        AdConfigEntity successConfigEntity = splashAdConfigEntity.getSuccessConfigEntity();
        final AdConfigEntity failConfigEntity = splashAdConfigEntity.getFailConfigEntity();
        StatisticsUploadUtils.uploadRealTime(welcomeClient.context, "kaipingShow", "启动页-广告", successConfigEntity.getWelcomeType(), successConfigEntity.getAdvertiseId(), successConfigEntity.getAdvertiseTitle(), successConfigEntity.getAdvertiseTargetURL());
        boolean z = !TextUtils.isEmpty(successConfigEntity.getAdType());
        final boolean z2 = !TextUtils.isEmpty(failConfigEntity.getAdType());
        if (!z) {
            new Handler(welcomeClient.context.getMainLooper()).postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.14
                @Override // java.lang.Runnable
                public void run() {
                    WelcomeClient.this.goMain();
                }
            }, j3);
            return;
        }
        IAdInterface ad = AdFactory.getAd(welcomeClient.context, successConfigEntity);
        welcomeClient.getLifecycle().addObserver(ad);
        welcomeClient.adDataStartRequestTime = System.currentTimeMillis() - BaseActivity.appOnCreateTime;
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：广告资源开始加载：" + welcomeClient.adDataStartRequestTime);
        ad.loadSplash(welcomeClient.splash_container, j3, new IAdInterface.ISplashAdCallBack() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$WelcomeClient$5uhBQDCXW3pebEf9DhbI42lRVic
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.ISplashAdCallBack
            public final void onResult(int i, String str) {
                WelcomeClient.lambda$loadKaiPingData$4(WelcomeClient.this, z2, failConfigEntity, i, str);
            }
        }, new IAdInterface.IAdClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.15
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IAdClickListener
            public void onAdClick() {
                WelcomeClient.this.goMain();
            }
        });
    }

    public static /* synthetic */ void lambda$loadKaiPingData$4(final WelcomeClient welcomeClient, boolean z, AdConfigEntity adConfigEntity, int i, String str) {
        welcomeClient.adStartShowTime = System.currentTimeMillis() - BaseActivity.appOnCreateTime;
        MsLogUtil.m7979d(TAG, "+++++++++启动日志：广告开始展示：" + welcomeClient.adStartShowTime);
        if (i == 11) {
            StatisticsUploadUtils.uploadRealTime(welcomeClient.context, "10", "启动页-广告", "广告", "", "", "error-try-1");
        }
        if (!z || i == 10) {
            welcomeClient.handleAdView(i, str);
            return;
        }
        welcomeClient.splash_container.removeAllViews();
        IAdInterface ad = AdFactory.getAd(welcomeClient.context, adConfigEntity);
        welcomeClient.getLifecycle().addObserver(ad);
        ad.loadSplash(welcomeClient.splash_container, 0L, new IAdInterface.ISplashAdCallBack() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$WelcomeClient$rxKTWlH4z7jnNBzzWIUZTrmsAA4
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.ISplashAdCallBack
            public final void onResult(int i2, String str2) {
                WelcomeClient.lambda$loadKaiPingData$3(WelcomeClient.this, i2, str2);
            }
        }, new IAdInterface.IAdClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.16
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IAdClickListener
            public void onAdClick() {
                WelcomeClient.this.goMain();
            }
        });
    }

    public static /* synthetic */ void lambda$loadKaiPingData$3(WelcomeClient welcomeClient, int i, String str) {
        if (i == 11) {
            StatisticsUploadUtils.uploadRealTime(welcomeClient.context, "10", "启动页-广告", "广告", "", "", "error-try-2");
        }
        welcomeClient.handleAdView(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoMainActivity() {
        if (this.isOutside) {
            ((ObservableSubscribeProxy) Observable.timer(1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.context))).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.17
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    WelcomeClient.this.goMain();
                }
            });
        } else {
            goMain();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishWelcomActivity(FinishtEvent finishtEvent) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goMain() {
        if (this.hasToMain) {
            return;
        }
        this.adFinishTime = System.currentTimeMillis() - BaseActivity.appOnCreateTime;
        TYCJBoxManager.getInstance().collectAppIn("0", BaseActivity.appOnCreateTime + "", this.ziyuanTime + "", this.onlineFinishTime + "", this.configFinishTime + "", this.adUrlStartRequestTime + "", this.adDataStartRequestTime + "", this.adStartShowTime + "", this.adFinishTime + "", System.currentTimeMillis() + "");
        this.hasToMain = true;
        App.reEnterAfterCloseApplication_ForGesturePassword = true;
        App.reEnterAfterCloseApplication_ForCitySelected = true;
        IntentManager.intentFilter(this.context, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toXieyi(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(Html.fromHtml(str).toString()));
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        try {
            this.context.startActivity(intent);
        } catch (Exception unused) {
            intent.setComponent(null);
            this.context.startActivity(intent);
        }
    }

    private void initProvinceCode() {
        if (TextUtils.isEmpty(this.userManager.getUserAreaname())) {
            this.userManager.saveLocateCityName(CityChangeManager.DEFAULT_SELECT_CITY);
            this.userManager.saveCurrentProvinceName(CityChangeManager.DEFAULT_SELECT_CITY);
            this.userManager.saveUserAreaame(CityChangeManager.DEFAULT_SELECT_CITY);
            this.userManager.saveCurrentProvinceCode(CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE);
            this.userManager.saveUserAreaid(CityChangeManager.DEFAULT_SELECT_CITY_CODE);
            this.userManager.saveLocateProvinceCode(CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE);
            this.userManager.saveLocateCityCode(CityChangeManager.DEFAULT_SELECT_CITY_CODE);
            this.userManager.saveAutoCity(true);
            return;
        }
        this.userManager.saveAutoCity(false);
    }

    private void initSkin() {
        try {
            final HeaderEntity headerEntity = HomeCardDataManager.getInstance().getHeaderEntity();
            if (TextUtils.isEmpty(headerEntity.getBackImg())) {
                return;
            }
            final Map<String, Drawable> map = ManagerBitmapCache.getInstance().getMap();
            GlideApp.with((FragmentActivity) this.context).load(headerEntity.getBackImg()).into((RequestBuilder<Drawable>) new SimpleTarget<Drawable>() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.18
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                    onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                    map.put(headerEntity.getBackImg(), drawable);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkRoot() {
        try {
            if (RootUtil.isDeviceRooted()) {
                UIUtils.toast("您的设备处于root环境");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shangbaoShiyongshichang() {
        String str;
        String localIpAddress = SystemServiceUtils.getLocalIpAddress();
        DeviceHelper.getDeviceBrand();
        String deviceModel = DeviceHelper.getDeviceModel();
        String deviceOSVersion = DeviceHelper.getDeviceOSVersion();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userIp", localIpAddress);
            jSONObject.put("model", deviceModel);
            jSONObject.put("version", "Android " + deviceOSVersion.replace("android", ""));
            if (TextUtils.isEmpty(this.externUrl)) {
                str = "";
            } else {
                String str2 = this.externUrl;
                str = new JSONObject(str2.substring(str2.indexOf("{"))).getString("openUrl");
            }
            StatisticsUploadUtils.uploadBeiDong(this.context, "jrst0001", "", "", "", "启动客户端", str, "", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "", "");
            UIUtils.logD("welcomeClient", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initClientsStructure() {
        try {
            SharePreferenceForOverlaySteup sharePreferenceForOverlaySteup = new SharePreferenceForOverlaySteup(getApplicationContext());
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String str = packageInfo.versionName;
            if (packageInfo.versionCode >= 20 && !"".equals(sharePreferenceForOverlaySteup.getString("versionCodeForOverlaySteup")) && !str.equals(sharePreferenceForOverlaySteup.getString("versionCodeForOverlaySteup"))) {
                this.preference.remove("isShowWelcomeGuide");
                this.preference.remove("isShowHomeGuide");
                this.preference.remove("isShowInfoDetailGuide");
                this.preference.remove("versionupdate_desc");
                this.preference.clear(SharePreferenceUtil.FILE_LoginInfo);
                this.preference.putBoolean("user_is_fugai", true);
            }
            sharePreferenceForOverlaySteup.putString("versionCodeForOverlaySteup", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMenu() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", getString(2131886969));
        App.getAsyncHttpClient(5, 5, 5).post(URLSet.getQuickAccessUrl(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.19
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str) {
                super.onSuccess(i, str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    MsLogUtil.m7979d("快捷入口数据", "onSuccess: " + jSONObject);
                    if (!"0000".equals(jSONObject.getString("code"))) {
                        WelcomeClient.this.upLoadPvLog();
                    } else {
                        CacheDataCenter.getInstance().setQuickAccessData(str);
                        WelcomeClient.filterData(jSONObject.optJSONArray("data"));
                        WelcomeClient.this.addDefaultQuick();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str) {
                super.onFailure(th, str);
                WelcomeClient.this.upLoadPvLog();
            }
        });
    }

    public static void filterData(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        String useQuickData = CacheDataCenter.getInstance().getUseQuickData();
        if (useQuickData.isEmpty()) {
            return;
        }
        try {
            JSONArray jSONArray3 = new JSONObject(useQuickData).getJSONArray("data");
            if (jSONArray3.length() == 0) {
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                    if (jSONArray.getJSONObject(i).getString("title").equals(jSONArray3.getJSONObject(i2).getString("title"))) {
                        jSONArray2.put(jSONArray.getJSONObject(i));
                    }
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data", jSONArray2);
            CacheDataCenter.getInstance().setUseQuickData(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upLoadPvLog() {
        StatisticsUploadUtils.upload(this, "S2ndpage1024", "启动图标快捷入口", "快捷入口", "", "", "", "", "2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007a A[Catch: Exception -> 0x00c4, TryCatch #1 {Exception -> 0x00c4, blocks: (B:5:0x0012, B:7:0x0024, B:11:0x002e, B:13:0x0034, B:30:0x0076, B:35:0x0091, B:32:0x007a, B:33:0x0082, B:34:0x008a, B:20:0x0054, B:23:0x005f, B:26:0x006a, B:36:0x0094, B:38:0x00a6, B:40:0x00b1, B:46:0x00bf, B:39:0x00ab, B:43:0x00ba), top: B:54:0x0012, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0082 A[Catch: Exception -> 0x00c4, TryCatch #1 {Exception -> 0x00c4, blocks: (B:5:0x0012, B:7:0x0024, B:11:0x002e, B:13:0x0034, B:30:0x0076, B:35:0x0091, B:32:0x007a, B:33:0x0082, B:34:0x008a, B:20:0x0054, B:23:0x005f, B:26:0x006a, B:36:0x0094, B:38:0x00a6, B:40:0x00b1, B:46:0x00bf, B:39:0x00ab, B:43:0x00ba), top: B:54:0x0012, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a A[Catch: Exception -> 0x00c4, TryCatch #1 {Exception -> 0x00c4, blocks: (B:5:0x0012, B:7:0x0024, B:11:0x002e, B:13:0x0034, B:30:0x0076, B:35:0x0091, B:32:0x007a, B:33:0x0082, B:34:0x008a, B:20:0x0054, B:23:0x005f, B:26:0x006a, B:36:0x0094, B:38:0x00a6, B:40:0x00b1, B:46:0x00bf, B:39:0x00ab, B:43:0x00ba), top: B:54:0x0012, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addDefaultQuick() {
        /*
            r8 = this;
            com.sinovatech.unicom.basic.boxcenter.CacheDataCenter r0 = com.sinovatech.unicom.basic.boxcenter.CacheDataCenter.getInstance()
            boolean r0 = r0.getFirstOpen()
            if (r0 == 0) goto Lc8
            com.sinovatech.unicom.basic.boxcenter.CacheDataCenter r0 = com.sinovatech.unicom.basic.boxcenter.CacheDataCenter.getInstance()
            java.lang.String r0 = r0.getQuickAccessData()
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lc4
            r1.<init>(r0)     // Catch: java.lang.Exception -> Lc4
            java.lang.String r0 = "data"
            org.json.JSONArray r0 = r1.getJSONArray(r0)     // Catch: java.lang.Exception -> Lc4
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Exception -> Lc4
            r1.<init>()     // Catch: java.lang.Exception -> Lc4
            if (r0 == 0) goto Lc3
            int r2 = r0.length()     // Catch: java.lang.Exception -> Lc4
            if (r2 != 0) goto L2c
            goto Lc3
        L2c:
            r2 = 0
            r3 = r2
        L2e:
            int r4 = r0.length()     // Catch: java.lang.Exception -> Lc4
            if (r3 >= r4) goto L94
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch: java.lang.Exception -> Lc4
            java.lang.String r5 = "title"
            java.lang.String r4 = r4.optString(r5)     // Catch: java.lang.Exception -> Lc4
            r5 = -1
            int r6 = r4.hashCode()     // Catch: java.lang.Exception -> Lc4
            r7 = -195690751(0xfffffffff455ff01, float:-6.7818074E31)
            if (r6 == r7) goto L6a
            r7 = 635160940(0x25dbc96c, float:3.8126933E-16)
            if (r6 == r7) goto L5f
            r7 = 641876147(0x264240b3, float:6.7394955E-16)
            if (r6 == r7) goto L54
            goto L75
        L54:
            java.lang.String r6 = "余量查询"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> Lc4
            if (r4 == 0) goto L75
            r4 = r2
            goto L76
        L5f:
            java.lang.String r6 = "交费充值"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> Lc4
            if (r4 == 0) goto L75
            r4 = 2
            goto L76
        L6a:
            java.lang.String r6 = "话费与账单"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> Lc4
            if (r4 == 0) goto L75
            r4 = 1
            goto L76
        L75:
            r4 = r5
        L76:
            switch(r4) {
                case 0: goto L8a;
                case 1: goto L82;
                case 2: goto L7a;
                default: goto L79;
            }     // Catch: java.lang.Exception -> Lc4
        L79:
            goto L91
        L7a:
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch: java.lang.Exception -> Lc4
            r1.put(r4)     // Catch: java.lang.Exception -> Lc4
            goto L91
        L82:
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch: java.lang.Exception -> Lc4
            r1.put(r4)     // Catch: java.lang.Exception -> Lc4
            goto L91
        L8a:
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch: java.lang.Exception -> Lc4
            r1.put(r4)     // Catch: java.lang.Exception -> Lc4
        L91:
            int r3 = r3 + 1
            goto L2e
        L94:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lc4
            r0.<init>()     // Catch: java.lang.Exception -> Lc4
            java.lang.String r2 = "data"
            r0.put(r2, r1)     // Catch: java.lang.Exception -> Lc4
            com.sinovatech.unicom.basic.boxcenter.CacheDataCenter r2 = com.sinovatech.unicom.basic.boxcenter.CacheDataCenter.getInstance()     // Catch: java.lang.Exception -> Lc4
            boolean r3 = r0 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> Lc4
            if (r3 != 0) goto Lab
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Lc4
            goto Lb1
        Lab:
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch: java.lang.Exception -> Lc4
            java.lang.String r0 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r0)     // Catch: java.lang.Exception -> Lc4
        Lb1:
            r2.setUseQuickData(r0)     // Catch: java.lang.Exception -> Lc4
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> Lc4
            r2 = 25
            if (r0 < r2) goto Lc8
            r8.saveQuickData(r1)     // Catch: java.lang.Exception -> Lbe
            goto Lc8
        Lbe:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Exception -> Lc4
            goto Lc8
        Lc3:
            return
        Lc4:
            r0 = move-exception
            r0.printStackTrace()
        Lc8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.WelcomeClient.addDefaultQuick():void");
    }

    private void saveQuickData(JSONArray jSONArray) {
        if (Build.VERSION.SDK_INT >= 25) {
            try {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    Intent intent = new Intent();
                    intent.setAction("");
                    intent.putExtra("url", jSONArray.optJSONObject(i).optString("linkUrl"));
                    intent.putExtra("title", jSONArray.optJSONObject(i).optString("title"));
                    intent.putExtra("enterType", "quickAccessType");
                    intent.setClassName("com.sinovatech.unicom.ui", "com.sinovatech.unicom.basic.ui.activity.WelcomeClient");
                    if (Integer.parseInt(jSONArray.optJSONObject(i).optString("iconType")) <= this.quickResources.length) {
                        this.shortcutInfoImg = this.quickResources[Integer.parseInt(jSONArray.optJSONObject(i).optString("iconType")) - 1];
                    } else {
                        this.shortcutInfoImg = this.quickResources[0];
                    }
                    arrayList.add(new ShortcutInfo.Builder(this, jSONArray.optJSONObject(i).optString("title")).setIntent(intent).setRank(i).setShortLabel(jSONArray.optJSONObject(i).optString("title")).setIcon(Icon.createWithResource(this, this.shortcutInfoImg)).build());
                }
                Intent intent2 = new Intent();
                intent2.setAction("");
                intent2.putExtra("url", "QuickAccessActivity");
                intent2.putExtra("enterType", "quickAccessType");
                intent2.setClassName("com.sinovatech.unicom.ui", "com.sinovatech.unicom.basic.ui.activity.WelcomeClient");
                arrayList.add(new ShortcutInfo.Builder(this, "setting").setIntent(intent2).setShortLabel("更多设置").setRank(3).setIcon(Icon.createWithResource(this, 2131232081)).build());
                if (arrayList.size() > 0) {
                    ShortcutManager shortcutManager = (ShortcutManager) getSystemService(ShortcutManager.class);
                    shortcutManager.removeAllDynamicShortcuts();
                    shortcutManager.addDynamicShortcuts(arrayList);
                    CacheDataCenter.getInstance().setFirstOpen("1");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showEmulatorDialog() {
        CustomDialogManager.show((Activity) this, "警告", "当前设备为模拟器,禁止使用中国联通APP", false, "", "退出", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WelcomeClient.20
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                Process.killProcess(Process.myPid());
                System.exit(0);
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        });
    }

    public void checkLoginStatus() {
        String string = App.getSharePreferenceUtil().getString("accountName");
        String string2 = App.getSharePreferenceUtil().getString("password");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return;
        }
        App.setLogined(LoginStateConst.DID_LOGIN);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.isWaring) {
            finish();
        }
    }
}
