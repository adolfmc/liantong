package com.sinovatech.unicom.basic.p315ui.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.app.FragmentTransaction;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alipay.sdk.app.H5PayCallback;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.basic.eventbus.EdopXunJianEvent;
import com.sinovatech.unicom.basic.eventbus.JiePinEvent;
import com.sinovatech.unicom.basic.eventbus.ReloadWebviewEvent;
import com.sinovatech.unicom.basic.eventbus.ShareEvent;
import com.sinovatech.unicom.basic.eventbus.ShareEvent2;
import com.sinovatech.unicom.basic.eventbus.ShareOnClickEventBus;
import com.sinovatech.unicom.basic.eventbus.WebTitleEvent;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.JSInvokeHandler;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.home.view.MarqueeTextView;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMaidian;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerShareConfig;
import com.sinovatech.unicom.basic.p315ui.share.ManagerShare;
import com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil;
import com.sinovatech.unicom.basic.p315ui.share.WebMenuManager;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LocationManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomLoadingView;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.basic.webview.ManagerCdnCacheTime;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.GallaryUtils;
import com.sinovatech.unicom.common.KeyBoardListener;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.appdownload.AppDownloadManager;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.smallvideo.SearchVideoActivity;
import com.sinovatech.unicom.separatemodule.audience.smallvideo.SmallVideoActivity;
import com.sinovatech.unicom.separatemodule.audience.util.PlayerUtil;
import com.sinovatech.unicom.separatemodule.audience.view.MyDragView;
import com.sinovatech.unicom.separatemodule.chuanshanjia.ToutiaoEvent;
import com.sinovatech.unicom.separatemodule.fuchuang.ManagerFuchuang;
import com.sinovatech.unicom.separatemodule.gaodedache.JSInterfaceConfigParser;
import com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin;
import com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu;
import com.sinovatech.unicom.separatemodule.keyboard.EmojiEvent;
import com.sinovatech.unicom.separatemodule.keyboard.EmojiHideEvent;
import com.sinovatech.unicom.separatemodule.keyboard.EmotionMainFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpNavigationBar;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpTheme;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.aes.utils.AesEncryptUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogEnu;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentBoxManager;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.safetydetection.SafelyHostEntity;
import com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.AndroidH5CaijiJSBrige;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJConfigUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJJSManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJPVParamsUtil;
import com.sinovatech.unicom.separatemodule.unionpay.YunShanFuManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.fragment.WebFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WebFragment extends BaseWebFragment {
    public static String findder = "";
    public static boolean isRelogin = true;
    public static boolean isReloginHuodong = true;
    public static boolean isReloginShop = true;
    public static int pageClickCount = 0;
    public static long pageLeftTime = 0;
    public static long pageLoadTime = 0;
    public static long pageStartTime = 0;
    public static String tycjCollectData = null;
    public static String webParams = "WebFragment_0";
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private ImageView back;
    private ImageView close;
    private ConfigManager configManager;
    private RelativeLayout contentRootLayout;
    private LinearLayout cumpLauncherBusinessiconLayout;
    private LinearLayout cumpLauncherErrorLayout;
    private TextView cumpLauncherErrorText;
    private LinearLayout cumpLauncherNavigationiBarLayout;
    private LinearLayout cumpNavigationBarLayout;
    private ImageView cumpNavigationClose;
    private ImageView cumpNavigationMore;
    private LinearLayout cumpPublishTypeLayout;
    private String currentPhoneNumber;
    private EmotionMainFragment emotionMainFragment;
    private View fragmentCacheView;
    private ImageView imgYYTtitle;
    private boolean isActivityCreated;
    private boolean isNoAppendParams;
    private boolean isOnTop;
    private JSInvokeHandler jsInvokeHandler;
    private FrameLayout keyFragmLayout;
    private CustomLoadingView loadingView;
    private String logLoadErrorMsg;
    private Date logStartTime;
    private String logUrlTitle;
    private ManagerMaidian managerMaidian;
    private ManagerShare managerShare;
    private String miniAppid;
    private ImageView miniClose;
    private RelativeLayout miniLauncherLayout;
    private ImageView miniMenuIcon;
    private ImageView miniMore;
    private TextView miniTitle;
    private TextView mobileText;
    private ImageView more;
    private RelativeLayout normalLayout;
    private WebParamsEntity paramsEntity;
    private Map<String, YHXXJSPlugin> pluginMap;
    private Map<String, Integer> pluginRequestCodeMap;
    private ProgressBar progressBar;
    private String referer;
    private String requestType;
    private LinearLayout safelyHostGrayHintLayout;
    private TextView searchText;
    private LinearLayout searchlayout;
    private ImageView serachBackImage;
    private ImageView serachCloseImage;
    private String shopProvinceCode;
    public RelativeLayout titleLayout;
    private TextView titleText;
    private RelativeLayout toutiaoBanner;
    private TYCJBoxManager tycjBoxManager;
    public ValueCallback<Uri[]> uploadMessageAboveL;
    private String url;
    private UserManager userManager;
    private String webyyt;

    /* renamed from: wv */
    private PBWebView f18409wv;
    private RelativeLayout xinxiliuLayout;
    private LinearLayout yinsiLayout;
    private String TAG = "WebFragment";
    public final String INAPP_PAY_SCHEMA = "&inapp-alipay";
    public boolean receivedError = false;
    private boolean isSuccessOpen = false;
    private boolean initFuchuang = true;
    private String locateProvence = "";
    private boolean isResolved = false;
    private boolean isUnicomNewShareLog = false;
    private boolean isPageStartCall = false;
    private int webStatus = 0;
    private Handler addPlayerHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.18
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            if (message.what < 3) {
                WebFragment webFragment = WebFragment.this;
                webFragment.initAudience(webFragment.fragmentCacheView, message.what + 1);
                return false;
            }
            return false;
        }
    });
    private List<WebMenuManager.WebMenuEntity> moreWebMenuList = new ArrayList();

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    static /* synthetic */ PBWebView access$000(WebFragment webFragment) {
        return webFragment.f18409wv;
    }

    static /* synthetic */ WebParamsEntity access$300(WebFragment webFragment) {
        return webFragment.paramsEntity;
    }

    static /* synthetic */ void access$3900(WebFragment webFragment, String str) {
        webFragment.method(str);
    }

    static /* synthetic */ AppCompatActivity access$400(WebFragment webFragment) {
        return webFragment.activityContext;
    }

    static /* synthetic */ void access$4000(WebFragment webFragment, String str) {
        webFragment.inAppAliPayUrlHandle(str);
    }

    static /* synthetic */ void access$4100(WebFragment webFragment, String str) {
        webFragment.startInAppAliPay(str);
    }

    static /* synthetic */ void access$4200(WebFragment webFragment, ArrayList arrayList) {
        webFragment.choosePicture(arrayList);
    }

    public static WebFragment newIntence(WebParamsEntity webParamsEntity) {
        WebFragment webFragment = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(webParams, webParamsEntity);
        webFragment.setArguments(bundle);
        return webFragment;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        EventBusUtils.register(this);
        this.currentPhoneNumber = UserManager.getInstance().getDefaultPhoneNumber();
        pageLeftTime = 0L;
        this.activityContext = (AppCompatActivity) getActivity();
        if (getArguments() != null) {
            this.paramsEntity = (WebParamsEntity) getArguments().getParcelable(webParams);
            WebParamsEntity webParamsEntity = this.paramsEntity;
            if (webParamsEntity != null) {
                webParamsEntity.setUrl(ManagerCdnCacheTime.replaceCdnCacheTime(webParamsEntity.getUrl()));
                this.url = this.paramsEntity.getUrl();
                this.referer = this.paramsEntity.getReferer();
                this.isNoAppendParams = this.paramsEntity.isNoAppendParams();
                App.originalUrl = this.url;
                String navigateParamsUUID = this.paramsEntity.getNavigateParamsUUID();
                if (!TextUtils.isEmpty(navigateParamsUUID)) {
                    this.navigateParams = App.navigateParamsCacheMap.get(navigateParamsUUID);
                }
                if (!TextUtils.isEmpty(this.url) && this.url.contains("webViewNavIsHidden")) {
                    this.paramsEntity.setType("webViewNavIsHidden");
                }
                if (!TextUtils.isEmpty(this.url)) {
                    if (this.url.contains("unicomClientMethod=unicomClientGet")) {
                        this.paramsEntity.setRequestType("get");
                    }
                    if (this.url.contains("unicomClientMethod=unicomClientPost")) {
                        this.paramsEntity.setRequestType("post");
                    }
                }
                this.requestType = this.paramsEntity.getRequestType();
            }
        }
        this.userManager = UserManager.getInstance();
        this.configManager = new ConfigManager(this.activityContext);
        this.isOnTop = true;
        this.managerMaidian = new ManagerMaidian(this.activityContext);
        JSInterfaceConfigParser jSInterfaceConfigParser = new JSInterfaceConfigParser();
        jSInterfaceConfigParser.parse(getActivity());
        this.pluginMap = jSInterfaceConfigParser.getPluginMap();
        this.pluginRequestCodeMap = jSInterfaceConfigParser.getPluginRequestCodeMap();
        this.tycjBoxManager = TYCJBoxManager.getInstance();
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.WebFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.endViewTransition(this.fragmentCacheView);
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.WebFragment");
            return view2;
        }
        this.fragmentCacheView = layoutInflater.inflate(2131493550, viewGroup, false);
        initView(this.fragmentCacheView);
        initWebview();
        initUnicomMiniProgram(this.fragmentCacheView);
        initHezuoYinliu(this.fragmentCacheView);
        initAudience(this.fragmentCacheView, 0);
        this.managerShare = new ManagerShare(this.activityContext, this.f18409wv);
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.WebFragment");
        return view3;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        initJSPluginResource();
        if (!this.isActivityCreated) {
            this.isActivityCreated = true;
            UnicomCookieManager.addLoginCookie();
            initListener();
            if ("find".equals(this.paramsEntity.getType())) {
                return;
            }
            String url = this.paramsEntity.getUrl();
            if (!TextUtils.isEmpty(url) && ("UnicomMiniProgram".equals(this.paramsEntity.getType()) || url.startsWith("ms_unicom") || url.startsWith("https://ms_unicom") || url.startsWith("http://ms_unicom") || url.startsWith("edop_unicom") || url.startsWith("https://edop_unicom") || url.startsWith("http://edop_unicom"))) {
                loadUnicomMiniProgram(null);
            } else {
                loadUrl();
            }
        } else if ("shop".equals(this.paramsEntity.getType())) {
            if (TextUtils.isEmpty(this.locateProvence) || !UserManager.getInstance().getLocateProvinceCode().equals(this.locateProvence)) {
                this.locateProvence = UserManager.getInstance().getLocateProvinceCode();
                loadUrl();
            }
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        this.f18409wv.onPause();
        super.onPause();
        UIUtils.logD("closeMusic", "onPause");
        try {
            if (isPlayerPlaying()) {
                PlayerUtil.getPlayerByType(this.paramsEntity.getType()).pause();
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private boolean isPlayerPlaying() {
        try {
            if (hasDrayView()) {
                if (PlayerUtil.getPlayerByType(this.paramsEntity.getType()).isPlaying()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
            return false;
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.WebFragment");
        this.f18409wv.onResume();
        super.onResume();
        this.isOnTop = true;
        if (App.hasLogined() && !"一键登录".equals(this.paramsEntity.getTitle())) {
            this.titleText.setVisibility(0);
            this.mobileText.setVisibility(0);
        } else {
            this.titleText.setVisibility(0);
            this.mobileText.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.titleText.getText())) {
            this.titleText.setText(this.paramsEntity.getTitle());
            App.webviewTitle = this.titleText.getText().toString();
        }
        String currentPhoneNumber = this.userManager.getCurrentPhoneNumber();
        if (!TextUtils.isEmpty(currentPhoneNumber)) {
            StringBuilder sb = new StringBuilder(currentPhoneNumber);
            if (currentPhoneNumber.length() >= 11) {
                int length = (currentPhoneNumber.length() - 4) / 2;
                sb.replace(length, length + 4, "****");
            } else if (currentPhoneNumber.length() >= 6) {
                int length2 = (currentPhoneNumber.length() - 3) / 2;
                sb.replace(length2, length2 + 3, "***");
            }
            this.mobileText.setText(sb.toString());
        }
        if (!TextUtils.isEmpty(App.callbackWXURL)) {
            this.f18409wv.loadURL(App.callbackWXURL);
            App.callbackWXURL = "";
        }
        if (!TextUtils.isEmpty(App.weixinMsg)) {
            PBWebView pBWebView = this.f18409wv;
            pBWebView.loadURL("javascript:weixinMsg(" + App.weixinMsg + ")");
            App.weixinMsg = "";
        }
        if ((!this.currentPhoneNumber.equals(UserManager.getInstance().getCurrentPhoneNumber()) || ((isRelogin && "find".equals(this.paramsEntity.getType())) || ((isReloginHuodong && "yw".equals(this.paramsEntity.getType())) || (isReloginShop && "shop".equals(this.paramsEntity.getType()))))) && ("find".equals(this.paramsEntity.getType()) || "shop".equals(this.paramsEntity.getType()) || "yw".equals(this.paramsEntity.getType()))) {
            if ("find".equals(this.paramsEntity.getType()) && !TextUtils.isEmpty(findder)) {
                this.loadingView.setVisibility(0);
                this.loadingView.showLoading();
                this.titleLayout.setVisibility(0);
            }
            if (App.hasLogined()) {
                UnicomCookieManager.addLoginCookie();
            }
            this.currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
            loadUrl();
        }
        try {
            if (hasDrayView()) {
                if ("audienceMain".equals(this.paramsEntity.getType())) {
                    UIUtils.logD("liveDebug", "直播预览播放器");
                    if (AudienceMainActivity.bdCloudVideoView != null && !AudienceMainActivity.bdCloudVideoView.isPlaying()) {
                        UIUtils.logD("liveDebug", "直播预览播放器恢复播放");
                        AudienceMainActivity.bdCloudVideoView.start();
                    }
                }
                if ("audience".equals(this.paramsEntity.getType())) {
                    UIUtils.logD("liveDebug", "直播详情播放器");
                    if (AudienceActivity.bdCloudVideoView != null && !AudienceActivity.bdCloudVideoView.isPlaying()) {
                        AudienceActivity.bdCloudVideoView.start();
                    }
                }
                if ("smallVideo".equals(this.paramsEntity.getType())) {
                    UIUtils.logD("liveDebug", "小视频播放器");
                    if (SmallVideoActivity.bdCloudVideoView != null && !SmallVideoActivity.bdCloudVideoView.isPlaying()) {
                        UIUtils.logD("liveDebug", "小视频播放器恢复播放");
                        SmallVideoActivity.bdCloudVideoView.start();
                    }
                }
                if ("searchVideo".equals(this.paramsEntity.getType())) {
                    UIUtils.logD("liveDebug", "小视频播放器");
                    if (SearchVideoActivity.bdCloudVideoView != null && !SearchVideoActivity.bdCloudVideoView.isPlaying()) {
                        UIUtils.logD("liveDebug", "小视频播放器恢复播放");
                        SearchVideoActivity.bdCloudVideoView.start();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sccfPageShow();
        try {
            if (App.appNeedRefreshList != null && App.appNeedRefreshList.size() > 0) {
                Iterator<String> it = App.appNeedRefreshList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (this.f18409wv.getUrl().startsWith(it.next())) {
                        it.remove();
                        this.f18409wv.reload();
                        break;
                    }
                }
            }
            if (App.hasLogined() && !TextUtils.isEmpty(App.xinLaoUserOpenUrl)) {
                StatisticsUploadUtils.uploadXinLao(this.activityContext, "S2ndpage1172", "用户登录", App.xinLaoUserOpenUrl, DeviceHelper.getDeviceID(true));
                App.xinLaoUserOpenUrl = "";
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.WebFragment");
    }

    private boolean hasDrayView() {
        View findViewById;
        try {
            if (this.fragmentCacheView == null || (findViewById = this.fragmentCacheView.findViewById(2131296398)) == null) {
                return false;
            }
            return findViewById.getVisibility() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sccfPageShow() {
        if (("shop".equals(this.paramsEntity.getType()) || "find".equals(this.paramsEntity.getType())) && this.isSuccessOpen) {
            PBWebView pBWebView = this.f18409wv;
            if (pBWebView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:try{sccfPageShow()}catch(e){}");
            } else {
                pBWebView.loadUrl("javascript:try{sccfPageShow()}catch(e){}");
            }
        }
    }

    public void sccfTabChangeShow() {
        if (("shop".equals(this.paramsEntity.getType()) || "find".equals(this.paramsEntity.getType())) && this.isSuccessOpen) {
            PBWebView pBWebView = this.f18409wv;
            if (pBWebView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:try{sccfTabChangeShow()}catch(e){}");
            } else {
                pBWebView.loadUrl("javascript:try{sccfTabChangeShow()}catch(e){}");
            }
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.WebFragment", this);
        super.onStart();
        pageStartTime = System.currentTimeMillis();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.WebFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStop() {
        super.onStop();
        this.isOnTop = false;
        PBWebView pBWebView = this.f18409wv;
        if (pBWebView != null) {
            pBWebView.loadURL("javascript:try{closeMusic()}catch(error){}");
            UIUtils.logD("closeMusic", "onStop");
        }
        try {
            pageLeftTime = System.currentTimeMillis() - pageStartTime;
            collectLeftH5info();
            pageLeftTime = 0L;
            pageClickCount = 0;
            pageStartTime = 0L;
            pageLoadTime = 0L;
            tycjCollectData = "";
            if (hasDrayView()) {
                if ("audienceMain".equals(this.paramsEntity.getType()) && AudienceMainActivity.bdCloudVideoView != null && AudienceMainActivity.bdCloudVideoView.isPlaying() && !this.activityContext.isFinishing()) {
                    AudienceMainActivity.bdCloudVideoView.pause();
                }
                if ("audience".equals(this.paramsEntity.getType())) {
                    UIUtils.logD("liveDebug", "直播详情播放器");
                    if (AudienceActivity.bdCloudVideoView != null && AudienceActivity.bdCloudVideoView.isPlaying() && !this.activityContext.isFinishing()) {
                        AudienceActivity.bdCloudVideoView.pause();
                    }
                }
                if ("smallVideo".equals(this.paramsEntity.getType()) && SmallVideoActivity.bdCloudVideoView != null && SmallVideoActivity.bdCloudVideoView.isPlaying() && !this.activityContext.isFinishing()) {
                    SmallVideoActivity.bdCloudVideoView.pause();
                }
                if ("searchVideo".equals(this.paramsEntity.getType()) && SearchVideoActivity.bdCloudVideoView != null && SearchVideoActivity.bdCloudVideoView.isPlaying() && !this.activityContext.isFinishing()) {
                    SearchVideoActivity.bdCloudVideoView.pause();
                }
            }
            if (this.addPlayerHandler != null) {
                this.addPlayerHandler.removeCallbacksAndMessages(null);
            }
            PlayerUtil.setWebPlayStatus(this.paramsEntity.getType(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        try {
            EventBusUtils.unregister(this);
            PlayerUtil.setWebPlayStatus(this.paramsEntity.getType(), false);
            LocationManager.stopLocaiton();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.f18409wv != null) {
                this.f18409wv.getSettings().setBuiltInZoomControls(true);
                ViewGroup viewGroup = (ViewGroup) this.f18409wv.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                this.f18409wv.setVisibility(8);
                this.f18409wv.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WebFragment.this.f18409wv.destroy();
                        WebFragment.this.f18409wv = null;
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void initView(View view) {
        this.f18409wv = (PBWebView) view.findViewById(2131299580);
        this.loadingView = (CustomLoadingView) view.findViewById(2131299567);
        this.loadingView.setReloadInterface(new CustomLoadingView.WebReload() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.2
            @Override // com.sinovatech.unicom.basic.view.CustomLoadingView.WebReload
            public void reload() {
                if (!NetworkUtils.isConnected()) {
                    WebFragment.this.progressBar.setVisibility(0);
                    WebFragment.this.progressBar.setProgress(100);
                    WebFragment.this.progressBar.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            WebFragment.this.progressBar.setVisibility(8);
                        }
                    }, 200L);
                    return;
                }
                if (TextUtils.isEmpty(WebFragment.this.f18409wv.getUrl())) {
                    WebFragment.this.f18409wv.loadURL(WebFragment.this.url);
                } else if (WebFragment.this.receivedError) {
                    WebFragment.this.f18409wv.loadURL(WebFragment.this.f18409wv.getUrl());
                    WebFragment.this.f18409wv.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (WebFragment.this.f18409wv != null) {
                                    WebFragment.this.f18409wv.clearHistory();
                                }
                            } catch (Exception e) {
                                MsLogUtil.m7978e(e.getMessage());
                            }
                        }
                    }, 1000L);
                } else {
                    WebFragment.this.f18409wv.reload();
                }
                if ("find".equals(WebFragment.this.paramsEntity.getType())) {
                    WebFragment.this.titleLayout.setVisibility(0);
                }
            }
        });
        this.progressBar = (ProgressBar) view.findViewById(2131299573);
        this.contentRootLayout = (RelativeLayout) view.findViewById(2131299564);
        this.titleLayout = (RelativeLayout) view.findViewById(2131299578);
        this.normalLayout = (RelativeLayout) view.findViewById(2131299572);
        this.back = (ImageView) view.findViewById(2131299552);
        this.close = (ImageView) view.findViewById(2131299554);
        this.more = (ImageView) view.findViewById(2131299559);
        this.titleText = (TextView) view.findViewById(2131299579);
        this.mobileText = (TextView) view.findViewById(2131299565);
        this.imgYYTtitle = (ImageView) view.findViewById(2131297290);
        this.searchlayout = (LinearLayout) view.findViewById(2131299576);
        this.serachBackImage = (ImageView) view.findViewById(2131299574);
        this.serachCloseImage = (ImageView) view.findViewById(2131299575);
        this.searchText = (TextView) view.findViewById(2131299577);
        this.keyFragmLayout = (FrameLayout) view.findViewById(2131296991);
        if (this.paramsEntity.isNeedTitle()) {
            this.titleLayout.setVisibility(0);
            this.normalLayout.setVisibility(0);
        } else {
            this.titleLayout.setVisibility(8);
        }
        this.toutiaoBanner = (RelativeLayout) view.findViewById(2131298823);
        this.xinxiliuLayout = (RelativeLayout) view.findViewById(2131298824);
        this.yinsiLayout = (LinearLayout) view.findViewById(2131297965);
        try {
            if (this.paramsEntity != null && !TextUtils.isEmpty(this.paramsEntity.getUrl()) && this.paramsEntity.getUrl().startsWith(URLSet.getOneKeyLoginYinSiUrl())) {
                this.yinsiLayout.setVisibility(0);
                initYinsiButton(view);
            } else {
                this.yinsiLayout.setVisibility(8);
            }
        } catch (Exception unused) {
            this.yinsiLayout.setVisibility(8);
        }
        if ("find".equals(this.paramsEntity.getType()) || "yw".equals(this.paramsEntity.getType()) || "shop".equals(this.paramsEntity.getType()) || "city".equals(this.paramsEntity.getType()) || "webViewNavIsHidden".equals(this.paramsEntity.getType())) {
            this.titleLayout.setVisibility(0);
            this.normalLayout.setVisibility(8);
            this.titleLayout.setPadding(0, UIUtils.getStatusBarHeight(this.activityContext), 0, 0);
            this.searchlayout.setVisibility(0);
            this.serachBackImage.setVisibility(8);
            this.serachCloseImage.setVisibility(8);
            if ("yw".equals(this.paramsEntity.getType())) {
                this.searchText.setText("活动");
            } else if ("find".equals(this.paramsEntity.getType())) {
                this.searchText.setText("财富");
            } else if ("shop".equals(this.paramsEntity.getType())) {
                this.searchText.setText("商城");
                this.titleLayout.setVisibility(8);
            } else if ("city".equals(this.paramsEntity.getType())) {
                this.titleLayout.setVisibility(8);
            } else if ("webViewNavIsHidden".equals(this.paramsEntity.getType())) {
                this.titleLayout.setVisibility(8);
            }
        }
        this.safelyHostGrayHintLayout = (LinearLayout) view.findViewById(2131298437);
        this.safelyHostGrayHintLayout.setVisibility(8);
    }

    public void reloadSlove405() {
        if (this.isResolved) {
            return;
        }
        this.isResolved = true;
        HashMap hashMap = new HashMap();
        if ("get".equalsIgnoreCase(this.requestType)) {
            this.f18409wv.post(this.url, hashMap);
        } else {
            this.f18409wv.get(this.url, hashMap, this.referer);
        }
    }

    private void initHezuoYinliu(View view) {
        if (TextUtils.isEmpty(this.url) || !this.url.contains("androidId")) {
            return;
        }
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(2131299556);
        this.paramsEntity.setType("appDownLoad");
        String valueByName = IntentManager.getValueByName(this.url, "androidId");
        String valueByName2 = IntentManager.getValueByName(this.url, "downloadUrl");
        String valueByName3 = IntentManager.getValueByName(this.url, "appName");
        String valueByName4 = IntentManager.getValueByName(this.url, "pvType");
        try {
            if (TextUtils.isEmpty(valueByName4)) {
                StatisticsUploadUtils.upload(this.activityContext, "hzyl0001", "首页弹窗 ", " 入口PV", "", valueByName3, this.url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("appDownLoad".equals(this.paramsEntity.getType())) {
            relativeLayout.setVisibility(0);
            new AppDownloadManager(this.activityContext, relativeLayout, valueByName2, valueByName, valueByName3, valueByName4);
            return;
        }
        relativeLayout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFuchuang(View view) {
        if (this.initFuchuang) {
            if (TextUtils.isEmpty(this.paramsEntity.getAcId()) && TextUtils.isEmpty(this.paramsEntity.getTaskId())) {
                return;
            }
            new ManagerFuchuang(this.activityContext, (RelativeLayout) view.findViewById(2131297045), this.url, this.paramsEntity.getAcId(), this.paramsEntity.getTaskId());
            this.initFuchuang = false;
        }
    }

    private void initUnicomMiniProgram(View view) {
        this.miniLauncherLayout = (RelativeLayout) view.findViewById(2131298073);
        this.miniLauncherLayout.setPadding(0, UIUtils.getStatusBarHeight(this.activityContext), 0, 0);
        this.miniLauncherLayout.setVisibility(8);
        this.miniClose = (ImageView) view.findViewById(2131298069);
        this.miniMore = (ImageView) view.findViewById(2131298074);
        this.miniMenuIcon = (ImageView) view.findViewById(2131298072);
        this.miniTitle = (TextView) view.findViewById(2131298076);
        this.cumpLauncherNavigationiBarLayout = (LinearLayout) view.findViewById(2131298075);
        this.cumpLauncherBusinessiconLayout = (LinearLayout) view.findViewById(2131298068);
        this.cumpLauncherBusinessiconLayout.setVisibility(0);
        this.cumpLauncherErrorLayout = (LinearLayout) view.findViewById(2131298070);
        this.cumpLauncherErrorLayout.setVisibility(8);
        this.cumpLauncherErrorText = (TextView) view.findViewById(2131298071);
        this.cumpNavigationBarLayout = (LinearLayout) view.findViewById(2131296757);
        this.cumpNavigationBarLayout.setVisibility(8);
        this.cumpNavigationClose = (ImageView) view.findViewById(2131296754);
        this.cumpNavigationMore = (ImageView) view.findViewById(2131296760);
        this.cumpPublishTypeLayout = (LinearLayout) view.findViewById(2131296768);
        this.cumpPublishTypeLayout.setVisibility(8);
        setNavigationBarColor(new CumpTheme());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.cumpNavigationBarLayout.getLayoutParams();
        layoutParams.topMargin = UIUtils.getStatusBarHeight(this.activityContext) + UIUtils.dip2px(this.activityContext, 10.0f);
        this.cumpNavigationBarLayout.setLayoutParams(layoutParams);
        if (!TextUtils.isEmpty(this.paramsEntity.getUrl()) && this.paramsEntity.getUrl().contains("edopDebug=edopDebug") && this.paramsEntity.getUrl().startsWith("http")) {
            this.cumpNavigationBarLayout.setVisibility(0);
            this.cumpNavigationClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    WebFragment.this.activityContext.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.cumpNavigationMore.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    UIUtils.toastLong("该功能仅支持体验版和正式版使用");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadUnicomMiniProgram(String str) {
        this.miniLauncherLayout.setVisibility(0);
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    WebFragment.this.cumpLauncherNavigationiBarLayout.setVisibility(0);
                    WebFragment.this.cumpLauncherBusinessiconLayout.setVisibility(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 300L);
        navigationBarHidden();
        this.miniClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                WebFragment.this.closeWebView();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        try {
            String replace = this.paramsEntity.getUrl().replace("http://edop_unicom/?", "http://edop_unicom?").replace("https://edop_unicom/?", "https://edop_unicom?");
            String str2 = "";
            if (!replace.startsWith("ms_unicom_") && !replace.startsWith("https://ms_unicom_") && !replace.startsWith("http://ms_unicom_")) {
                if (!replace.startsWith("ms_unicom?") && !replace.startsWith("https://ms_unicom?") && !replace.startsWith("http://ms_unicom?")) {
                    if (!replace.startsWith("edop_unicom_") && !replace.startsWith("https://edop_unicom_") && !replace.startsWith("http://edop_unicom_")) {
                        if (!replace.startsWith("edop_unicom?{") && !replace.startsWith("https://edop_unicom?{") && !replace.startsWith("http://edop_unicom?{")) {
                            if (replace.startsWith("https://edop_unicom?") || replace.startsWith("http://edop_unicom?")) {
                                if (replace.contains("&path=")) {
                                    str2 = replace.split("&path=")[1];
                                    replace = replace.split("&path=")[0];
                                }
                                String queryParameter = Uri.parse(replace).getQueryParameter("appid");
                                if (TextUtils.isEmpty(str)) {
                                    str = Uri.parse(replace).getQueryParameter("publishType");
                                    if (TextUtils.isEmpty(str)) {
                                        str = "2";
                                    }
                                }
                                loadEdopCumpCheckLogin(queryParameter, str2, str);
                                return;
                            }
                            return;
                        }
                        JSONObject jSONObject = new JSONObject(replace.split("edop_unicom\\?")[1]);
                        String string = jSONObject.getString("appid");
                        String optString = jSONObject.optString("path");
                        if (TextUtils.isEmpty(str)) {
                            str = jSONObject.optString("publishType", "2");
                        }
                        loadEdopCumpCheckLogin(string, optString, str);
                        return;
                    }
                    replace.replace("https://", "");
                    loadEdopCumpCheckLogin(replace.replace("http://", ""), "", "2");
                    return;
                }
                loadOldMiniProgram(new JSONObject(replace.split("ms_unicom\\?")[1]).getString("appid"));
                return;
            }
            replace.replace("https://", "");
            loadOldMiniProgram(replace.replace("http://", ""));
        } catch (Exception e) {
            e.printStackTrace();
            showLanuchMiniProgramError("小程序启动失败,请重新进入[" + e.getMessage() + "]");
        }
    }

    private void loadOldMiniProgram(String str) {
        try {
            this.miniLauncherLayout.setVisibility(8);
            if (str.contains("ms_unicom_search2020")) {
                this.f18409wv.loadURL(URLSet.getSearchH5Url());
            } else if (str.contains("ms_unicom_homefuwu2020")) {
                this.f18409wv.loadURL(URLSet.getHomeFuwuH5Url());
            } else if (str.contains("ms_unicom_kuaibaoxiaoxironghe")) {
                this.f18409wv.loadURL(URLSet.getKuaibaoAndMessageUrl());
            } else if (str.contains("ms_unicom_guanhuai")) {
                this.f18409wv.loadURL(URLSet.getCareHomeH5Url());
            } else if (str.contains("ms_unicom_weiwen")) {
                this.f18409wv.loadURL(URLSet.getWeiWenH5Url());
            } else if (str.contains("ms_unicom_zangwen")) {
                this.f18409wv.loadURL(URLSet.getZangWenUrl());
            } else if (str.contains("ms_unicom_english")) {
                this.f18409wv.loadURL(URLSet.getYingWenUrl());
            } else if (str.contains("ms_unicom_hawen")) {
                this.f18409wv.loadURL(URLSet.getHaWenUrl());
            } else if (str.contains("ms_unicom_mengwen")) {
                this.f18409wv.loadURL(URLSet.getMengWenUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEdopCumpCheckLogin(final String str, final String str2, final String str3) {
        if (!App.hasLogined()) {
            new AvoidOnResult(this.activityContext).startForResult(new Intent(this.activityContext, LoginBindActivity.class), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.7
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    if (App.hasLogined()) {
                        WebFragment.this.loadEdopCump(str, str2, str3);
                    } else {
                        WebFragment.this.showLanuchMiniProgramError("请先登录中国联通APP");
                    }
                }
            });
        } else {
            loadEdopCump(str, str2, str3);
        }
        this.miniAppid = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadEdopCump(String str, String str2, String str3) {
        currentPublishType = str3;
        CumpEntity appInfoFromBox = CumpLanucher.getInstance(this.activityContext).getAppInfoFromBox(str);
        if (appInfoFromBox == null) {
            appInfoFromBox = new CumpEntity();
            appInfoFromBox.setAppId(str);
            if (this.navigateParams != null && this.navigateParams.get("appName") != null && this.navigateParams.get("appHighImg") != null) {
                appInfoFromBox.setAppName((String) this.navigateParams.get("appName"));
                appInfoFromBox.setAppHighImg((String) this.navigateParams.get("appHighImg"));
            }
        }
        String str4 = CumpLanucher.TAG;
        MsLogUtil.m7979d(str4, "CumpLanucher 预启动：" + appInfoFromBox.getAppId() + " " + appInfoFromBox.getAppName());
        Glide.with((FragmentActivity) this.activityContext).load(appInfoFromBox.getAppHighImg()).error(2131231164).placeholder(2131231164).fallback(2131231164).into(this.miniMenuIcon);
        this.miniTitle.setText(appInfoFromBox.getAppName());
        CumpLanucher.getInstance(this.activityContext).lanuchCump(appInfoFromBox, currentPublishType, new C76578(str2, str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.fragment.WebFragment$8 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C76578 implements CumpLanucher.lanuchListener {
        CumpLanucher.lanuchListener tempLaunchListener = this;
        final /* synthetic */ String val$appConfigPath;
        final /* synthetic */ String val$appid;

        C76578(String str, String str2) {
            this.val$appConfigPath = str;
            this.val$appid = str2;
        }

        @Override // com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.lanuchListener
        public void onLanuchStart(CumpEntity cumpEntity) {
            WebFragment.this.miniLauncherLayout.setTag("onLanuchStart");
        }

        @Override // com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.lanuchListener
        public void onLanuchSuccess(CumpEntity cumpEntity) {
            String str;
            try {
                if ("2".equals(cumpEntity.getPublishMethod())) {
                    if (!TextUtils.isEmpty(this.val$appConfigPath) && this.val$appConfigPath.contains("?")) {
                        String substring = this.val$appConfigPath.substring(this.val$appConfigPath.indexOf("?"));
                        if (substring.contains("key")) {
                            str = CumpLanucher.getInstance(WebFragment.this.activityContext).createLowcodeRenderDirs() + File.separator + "index.html?time=" + System.currentTimeMillis() + "#/?" + substring;
                        } else {
                            str = CumpLanucher.getInstance(WebFragment.this.activityContext).createLowcodeRenderDirs() + File.separator + "index.html?time=" + System.currentTimeMillis() + "#/?key=" + cumpEntity.getHomePageKey() + "&" + substring;
                        }
                    } else {
                        str = CumpLanucher.getInstance(WebFragment.this.activityContext).createLowcodeRenderDirs() + File.separator + "index.html?time=" + System.currentTimeMillis() + "#/?key=" + cumpEntity.getHomePageKey();
                    }
                } else if (TextUtils.isEmpty(this.val$appConfigPath)) {
                    str = CumpLanucher.getInstance(WebFragment.this.activityContext).createAPPDirs(cumpEntity.getAppId()) + File.separator + "index.html?time=" + System.currentTimeMillis();
                } else {
                    str = CumpLanucher.getInstance(WebFragment.this.activityContext).createAPPDirs(cumpEntity.getAppId()) + File.separator + this.val$appConfigPath;
                }
                MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher 运行时启动路径：" + str);
                WebFragment.this.currentCumpAppId = this.val$appid;
                WebFragment.this.currentCumpAppName = cumpEntity.getAppName();
                WebFragment.this.currentCumpAppEntity = cumpEntity;
                PBWebView pBWebView = WebFragment.this.f18409wv;
                String str2 = "file:///" + str;
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str2);
                } else {
                    pBWebView.loadUrl(str2);
                }
                WebFragment.this.cumpNavigationBarLayout.setVisibility(0);
                WebFragment.this.cumpNavigationClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.8.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        WebFragment.this.activityContext.finish();
                        PvCurrencyLogUtils.pvLogLL("", "S2ndpage1151", WebFragment.this.currentCumpAppId, "", "", "小程序操作页", "胶囊按钮", "", "关闭");
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                final String str3 = "file:///" + str;
                WebFragment.this.cumpNavigationMore.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.8.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        CumpNavigationBar.getInstance(WebFragment.this.activityContext).showCumpDetail(WebFragment.this.activityContext, WebFragment.this, WebFragment.this.f18409wv, str3, C76578.this.tempLaunchListener);
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                if (BaseWebFragment.currentPublishType.equals("1")) {
                    WebFragment.this.cumpPublishTypeLayout.setVisibility(0);
                    ((TextView) WebFragment.this.cumpPublishTypeLayout.findViewById(2131296769)).setText("当前小程序为体验版");
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(WebFragment.this.cumpPublishTypeLayout, "alpha", 1.0f, 0.0f);
                    ofFloat.setDuration(5000L);
                    ofFloat.setRepeatMode(2);
                    ofFloat.setRepeatCount(100);
                    ofFloat.start();
                } else {
                    WebFragment.this.cumpPublishTypeLayout.setVisibility(8);
                }
                CumpNavigationBar.getInstance(WebFragment.this.activityContext).afterLanuchSuccess(WebFragment.this.activityContext, WebFragment.this, WebFragment.this.fragmentCacheView, WebFragment.this.f18409wv);
                try {
                    RecentMiniEntity recentMiniEntity = new RecentMiniEntity();
                    recentMiniEntity.setAppName(cumpEntity.getAppName());
                    recentMiniEntity.setAppletUrl(WebFragment.this.paramsEntity.getUrl());
                    recentMiniEntity.setH5(false);
                    recentMiniEntity.setAppImg(cumpEntity.getAppImg());
                    recentMiniEntity.setAppId(this.val$appid);
                    recentMiniEntity.setProductId(this.val$appid);
                    recentMiniEntity.setAppDesc(cumpEntity.getAppDesc());
                    RecentBoxManager.getInstance().put(recentMiniEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.lanuchListener
        public void onLanuchError(String str, String str2) {
            if ("1111".equals(str)) {
                WebFragment.this.miniLauncherLayout.setVisibility(0);
                WebFragment.this.miniLauncherLayout.setTag("onLanuchError");
                WebFragment.this.cumpLauncherErrorLayout.setVisibility(0);
                WebFragment.this.cumpLauncherErrorText.setText(str2);
                return;
            }
            WebFragment.this.showLanuchMiniProgramError(str2);
        }

        @Override // com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.lanuchListener
        public void onLanuchRetry(String str, boolean z) {
            WebFragment.this.showLanuchMiniProgramRetry(str, z);
        }

        @Override // com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.lanuchListener
        public void onLaunchVersionUpdate(CumpEntity cumpEntity, String str) {
            WebFragment.this.showLanuchMiniProgramVersionUpdate(str);
        }

        @Override // com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.lanuchListener
        public void onLaunchDataAsyncUpdate(CumpEntity cumpEntity) {
            if (cumpEntity != null) {
                try {
                    WebFragment.this.currentCumpAppEntity = cumpEntity;
                    String str = CumpLanucher.TAG;
                    MsLogUtil.m7979d(str, "小程序基础配置信息异步更新 onLaunchDataAsyncUpdate " + WebFragment.this.currentCumpAppEntity.getAppId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initWebview() {
        this.jsInvokeHandler = new JSInvokeHandler(this.activityContext, this, this.f18409wv, new Handler(), this.pluginMap, this.pluginRequestCodeMap);
        this.f18409wv.addJavascriptInterface(this.jsInvokeHandler, "js_invoke");
        this.f18409wv.addJavascriptInterface(new AndroidH5CaijiJSBrige(this, this.tycjBoxManager), "AndroidUUNICOLMS");
        registerJSPluginService(this.f18409wv);
        if (("find".equals(this.paramsEntity.getType()) || "yw".equals(this.paramsEntity.getType())) && "find".equals(this.paramsEntity.getType())) {
            this.loadingView.dissMiss();
        }
        this.f18409wv.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.9
            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                return true;
            }

            /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
                jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: ARITH  (r92 I:??[int, boolean]) = (r186 I:??[int, boolean, short, byte, char]) | (r0 I:??[int, boolean, short, byte, char]), expected to be less than 16
                	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
                	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
                */
            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onShouldOverrideUrlLoading(java.lang.String r15) {
                /*
                    Method dump skipped, instructions count: 1362
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.fragment.WebFragment.C76609.onShouldOverrideUrlLoading(java.lang.String):boolean");
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onReceivedTitle(String str) {
                WebFragment.this.logUrlTitle = str;
                if (TextUtils.isEmpty(str)) {
                    return true;
                }
                if (!str.contains("405 Not Allowed")) {
                    if (WebFragment.this.f18409wv.getUrl().contains(str) || str.contains(".jpg") || str.contains(".png")) {
                        return true;
                    }
                    WebFragment.this.titleText.setText(str);
                    App.webviewTitle = WebFragment.this.titleText.getText().toString();
                    return true;
                }
                WebFragment.this.reloadSlove405();
                return true;
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onRecevieError(int i, String str, String str2) {
                if (!TextUtils.isEmpty(str) && !str.contains("ERR_UNKNOWN_URL_SCHEME")) {
                    WebFragment.this.receivedError = true;
                }
                WebFragment.this.logLoadErrorMsg = str;
                return true;
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onPageStarted(final String str) {
                MsLogUtil.m7979d(ManagerHuiDu.TAG, "onPageStarted场景 " + str);
                WebFragment.this.moreWebMenuList.clear();
                if (!"about:blank".equals(str)) {
                    WebFragment.pageStartTime = System.currentTimeMillis();
                }
                WebFragment.this.jsInvokeHandler.resetBackUrlSet();
                WebFragment.pageClickCount = 0;
                TYCJJSManager.registerJs(WebFragment.this.f18409wv, new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.9.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(String str2) throws Exception {
                        String uuid = RecentCustomManager.uuid();
                        PBWebView pBWebView = WebFragment.this.f18409wv;
                        pBWebView.loadURL("javascript:try{window.unicomUUNICOLH5ClickListen('+" + uuid + "+')}catch(err){}");
                        if (TYCJConfigUtil.isOpenAndWhiteUrl("imgLoading", str, WebFragment.this.miniAppid)) {
                            WebFragment.this.f18409wv.loadURL("javascript:try{window.UUNICOLH5SystemMonitoring.start()}catch(e){console.log(e)}");
                        }
                    }
                });
                WebFragment.this.isPageStartCall = true;
                WebFragment webFragment = WebFragment.this;
                webFragment.currentURLForPlugin = str;
                webFragment.receivedError = false;
                webFragment.logLoadErrorMsg = "";
                WebFragment.this.logStartTime = new Date();
                if (!WebFragment.this.isUnicomNewShareLog && !TextUtils.isEmpty(App.originalUrl) && App.originalUrl.contains("linkType=unicomNewShare")) {
                    WebFragment.this.isUnicomNewShareLog = true;
                    try {
                        if (App.hasLogined()) {
                            String str2 = App.originalUrl.split("linkType=unicomNewShare")[1];
                            HashMap hashMap = new HashMap();
                            hashMap.put("step", "2");
                            hashMap.put("shareCompleteUrls", App.originalUrl);
                            for (String str3 : str2.split("&")) {
                                String[] split = str3.split("=");
                                String decode = URLDecoder.decode(split[0], "UTF-8");
                                String str4 = "";
                                if (split.length > 1) {
                                    str4 = URLDecoder.decode(split[1], "UTF-8");
                                }
                                hashMap.put(decode, str4);
                                UIUtils.logD("日志参数", String.format("key = %s || value = %s", decode, str4));
                            }
                            if (!hashMap.containsKey("shareUrl")) {
                                hashMap.put("shareUrl", App.originalUrl);
                            }
                            ShareLogUtil.upLoadSharedByLog(hashMap);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if ("city".equals(WebFragment.this.paramsEntity.getType())) {
                    WebFragment.this.loadingView.showLoading();
                    WebFragment.this.progressBar.setVisibility(8);
                } else {
                    WebFragment.this.progressBar.setVisibility(0);
                    WebFragment.this.loadingView.dissMiss();
                }
                if (WebFragment.this.f18409wv != null) {
                    try {
                        WebFragment.this.managerMaidian.shagnchaunLog(WebFragment.this.f18409wv.getUrl());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (WebFragment.this.safelyHostGrayHintLayout != null && WebFragment.this.safelyHostGrayHintLayout.getVisibility() == 0) {
                    WebFragment.this.safelyHostGrayHintLayout.setVisibility(8);
                }
                return false;
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onPageFinished() {
                if (WebFragment.this.isPageStartCall) {
                    WebFragment.this.isPageStartCall = false;
                    if (!"about:blank".equals(WebFragment.this.url)) {
                        WebFragment.pageLoadTime = System.currentTimeMillis() - WebFragment.pageStartTime;
                    }
                    WebFragment.this.f18409wv.loadURL("javascript:try{var result = clientGetJsTitle();js_invoke.handleJSTitle(result);}catch(err){js_invoke.handleJSTitle(err);}");
                    if (!WebFragment.this.isSuccessOpen) {
                        WebFragment.this.isSuccessOpen = true;
                        WebFragment.this.sccfPageShow();
                        WebFragment.this.sccfTabChangeShow();
                    }
                    WebFragment webFragment = WebFragment.this;
                    webFragment.initFuchuang(webFragment.fragmentCacheView);
                    WebFragment.this.hideEdopMiniLauncher();
                    if (WebFragment.this.receivedError || (!SystemServiceUtils.netIsAvailable() && !WebFragment.this.f18409wv.getUrl().startsWith("file:///"))) {
                        if ("city".equals(WebFragment.this.paramsEntity.getType()) || "webViewNavIsHidden".equals(WebFragment.this.paramsEntity.getType())) {
                            WebFragment.this.loadingView.showError(true);
                        } else {
                            WebFragment.this.loadingView.showError();
                        }
                        WebFragment webFragment2 = WebFragment.this;
                        webFragment2.errorLog(webFragment2.logLoadErrorMsg, WebFragment.this.f18409wv.getUrl());
                    } else {
                        WebFragment.this.loadingView.dissMiss();
                        WebFragment.this.progressBar.setVisibility(8);
                        if ("yw".equals(WebFragment.this.paramsEntity.getType()) || "shop".equals(WebFragment.this.paramsEntity.getType())) {
                            WebFragment.this.titleLayout.setVisibility(8);
                        }
                    }
                    WebFragment.this.managerShare.getThirdShareData();
                    WebFragment.this.webyyt = App.getSharePreferenceUtil().getString("webyyt");
                    if (!TextUtils.isEmpty(WebFragment.this.webyyt)) {
                        WebFragment.this.imgYYTtitle.setVisibility(0);
                    } else {
                        WebFragment.this.imgYYTtitle.setVisibility(8);
                    }
                    App.getSharePreferenceUtil().putString("webyyt", "");
                    try {
                        if (TextUtils.isEmpty(WebFragment.this.currentCumpAppId)) {
                            CumpLogManager.getInstance(WebFragment.this.activityContext).log_SCE04(CumpLogEnu.APP_SCE_04_01, WebFragment.this.logStartTime, new Date(), "", "", WebFragment.this.currentURLForPlugin, WebFragment.this.logUrlTitle, WebFragment.this.logLoadErrorMsg, TextUtils.isEmpty(WebFragment.this.logLoadErrorMsg) ? CumpLogEnu.urlStatus_Success : CumpLogEnu.urlStatus_Fail, "");
                        } else {
                            CumpLogManager.getInstance(WebFragment.this.activityContext).log_SCE01(CumpLogEnu.APP_PRO_03, WebFragment.this.logStartTime, new Date(), WebFragment.this.currentCumpAppId, WebFragment.this.currentCumpAppName, WebFragment.this.currentURLForPlugin, WebFragment.this.logLoadErrorMsg, TextUtils.isEmpty(WebFragment.this.logLoadErrorMsg) ? CumpLogEnu.urlStatus_Success : CumpLogEnu.urlStatus_Fail, "", WebFragment.this.currentCumpAppEntity != null ? WebFragment.this.currentCumpAppEntity.getOfficialVersion() : "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    TYCJJSManager.collectWebTimingData(WebFragment.this.f18409wv, WebFragment.this.miniAppid);
                    WebFragment.this.loadRiskControlPage();
                    WebFragment.this.loadCollectData();
                    WebHostSafelyManager.getInstance().shouldInterceptWebOverride(WebFragment.this.activityContext, WebFragment.this.f18409wv.getUrl(), new WebHostSafelyManager.WebHostSafelyListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.9.2
                        @Override // com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.WebHostSafelyListener
                        public void allow() {
                        }

                        @Override // com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.WebHostSafelyListener
                        public void refuse() {
                            WebFragment.this.activityContext.finish();
                        }

                        @Override // com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.WebHostSafelyListener
                        public void warn(SafelyHostEntity safelyHostEntity) {
                            WebFragment.this.showSafelyHostGrayHint(safelyHostEntity);
                        }
                    });
                    return false;
                }
                return true;
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public void onProgressChanged(int i) {
                if (i >= 75) {
                    WebFragment.this.progressBar.setVisibility(8);
                    if ("find".equals(WebFragment.this.paramsEntity.getType())) {
                        WebFragment.this.titleLayout.setVisibility(8);
                    }
                    WebFragment.this.hideEdopMiniLauncher();
                } else if ("city".equals(WebFragment.this.paramsEntity.getType())) {
                } else {
                    if (i > 40) {
                        WebFragment.this.progressBar.setProgress(i + 20);
                    } else {
                        WebFragment.this.progressBar.setProgress(i);
                    }
                }
            }

            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public void openFileChooserFor5(ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                WebFragment.this.uploadMessageAboveL = valueCallback;
                String[] acceptTypes = fileChooserParams.getAcceptTypes();
                ArrayList arrayList = new ArrayList();
                for (String str : acceptTypes) {
                    arrayList.add(str);
                }
                WebFragment.this.choosePicture(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInAppAliPay(String str) {
        new PayTask(this.activityContext).payInterceptorWithUrl(str, false, new H5PayCallback() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.10
            @Override // com.alipay.sdk.app.H5PayCallback
            public void onPayResult(H5PayResultModel h5PayResultModel) {
                final String returnUrl = h5PayResultModel.getReturnUrl();
                final String resultCode = h5PayResultModel.getResultCode();
                WebFragment.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (resultCode.equals("9000")) {
                            MsLogUtil.m7979d("支付宝回调", "onPayResult: " + resultCode + "===" + returnUrl);
                            PBWebView pBWebView = WebFragment.this.f18409wv;
                            String str2 = returnUrl;
                            if (pBWebView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) pBWebView, str2);
                                return;
                            } else {
                                pBWebView.loadUrl(str2);
                                return;
                            }
                        }
                        MsLogUtil.m7979d("支付宝回调", "onPayResult: " + resultCode + "===" + App.quitUrl);
                        PBWebView pBWebView2 = WebFragment.this.f18409wv;
                        String str3 = App.quitUrl;
                        if (pBWebView2 instanceof Object) {
                            NBSWebLoadInstrument.loadUrl((Object) pBWebView2, str3);
                        } else {
                            pBWebView2.loadUrl(str3);
                        }
                    }
                });
                App.isAliPay = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inAppAliPayUrlHandle(String str) {
        try {
            String[] split = str.split("&quitUrl=");
            String str2 = split[0];
            App.quitUrl = URLDecoder.decode(split[1].split("&inapp-alipay")[0], "UTF-8");
            IntentManager.gotoWebViewActivity((Activity) this.activityContext, str2, "", true);
            this.activityContext.finish();
            App.isAliPay = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUrl() {
        if (TextUtils.isEmpty(this.url) || TextUtils.isEmpty(this.url.trim())) {
            UIUtils.toast("您访问的地址为空");
            return;
        }
        this.url = this.url.trim();
        WebHostSafelyManager.getInstance().shouldInterceptWebLanuch(this.activityContext, this.url, new WebHostSafelyManager.WebHostSafelyListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.11
            @Override // com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.WebHostSafelyListener
            public void warn(SafelyHostEntity safelyHostEntity) {
            }

            @Override // com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.WebHostSafelyListener
            public void allow() {
                WebFragment.this.realLoadUrl();
            }

            @Override // com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.WebHostSafelyListener
            public void refuse() {
                WebFragment.this.activityContext.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realLoadUrl() {
        pageStartTime = System.currentTimeMillis();
        checkAndOpenEdopDebug(this.url);
        ManagerHuiDu.HuiDuWebViewURLConfig huiduURLConfig = ManagerHuiDu.getInstance().getHuiduURLConfig(this.url);
        if (huiduURLConfig != null && huiduURLConfig.enableHuiDu && !TextUtils.isEmpty(huiduURLConfig.huiduURL)) {
            this.url = huiduURLConfig.huiduURL;
            String str = ManagerHuiDu.TAG;
            MsLogUtil.m7979d(str, "启动场景 切换灰度 " + this.url);
        }
        if (this.url.startsWith("file")) {
            this.f18409wv.loadURL(this.url);
            return;
        }
        if (this.url.contains("openType=openInSystemBrower")) {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(this.url));
                startActivity(intent);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HashMap hashMap = new HashMap();
        if (this.url.contains("type=WebViewNavNewStyle")) {
            this.normalLayout.setVisibility(8);
            this.searchlayout.setVisibility(0);
            hashMap.put("first", "true");
        }
        if (this.paramsEntity.isYule() && !this.url.contains("first=true")) {
            hashMap.put("first", "true");
        }
        if ("shop".equals(this.paramsEntity.getType())) {
            this.shopProvinceCode = this.userManager.getLocateProvinceCode();
            hashMap.put("provinceCode", this.shopProvinceCode);
            hashMap.put("first", "true");
            this.f18409wv.get(this.url, hashMap, this.referer);
            isReloginShop = false;
        } else if ("find".equals(this.paramsEntity.getType())) {
            hashMap.put("first", "true");
            this.f18409wv.get(this.url, hashMap, this.referer);
            isRelogin = false;
            findder = "";
        } else if ("yw".equals(this.paramsEntity.getType())) {
            hashMap.put("first", "true");
            this.f18409wv.get(this.url, hashMap, this.referer);
            isReloginHuodong = false;
        } else if ("city".equals(this.paramsEntity.getTitle())) {
            hashMap.put("first", "true");
            this.f18409wv.post(this.url, hashMap);
        } else if ("get".equalsIgnoreCase(this.requestType)) {
            this.f18409wv.get(this.url, hashMap, this.referer);
        } else {
            this.f18409wv.post(this.url, hashMap);
        }
    }

    private void initListener() {
        this.back.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (WebFragment.this.emotionMainFragment != null && WebFragment.this.emotionMainFragment.getEmotionIsshown()) {
                    WebFragment.this.emotionMainFragment.hideSoftIntut();
                }
                if (WebFragment.this.toutiaoBanner.getVisibility() == 0) {
                    WebFragment.this.toutiaoBanner.setVisibility(8);
                }
                if (WebFragment.this.xinxiliuLayout.getVisibility() == 0) {
                    WebFragment.this.xinxiliuLayout.setVisibility(0);
                }
                JiePinEvent jiePinEvent = new JiePinEvent(EventBusUtils.EVENT_JIEPIN);
                jiePinEvent.setSecure(false);
                EventBusUtils.post(jiePinEvent);
                if (!SystemServiceUtils.netIsAvailable() || TextUtils.isEmpty(WebFragment.this.f18409wv.getUrl()) || "0".equals(WebFragment.this.paramsEntity.getBackMode()) || !WebFragment.this.isSuccessOpen || WebFragment.this.receivedError) {
                    PBWebView pBWebView = WebFragment.this.f18409wv;
                    if (pBWebView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) pBWebView, "about:blank");
                    } else {
                        pBWebView.loadUrl("about:blank");
                    }
                    WebFragment.this.activityContext.finish();
                } else {
                    WebFragment.this.f18409wv.loadURL("javascript:try{var result = clientDirectorCallBack();js_invoke.handleJSBack(result);}catch(err){js_invoke.handleJSBack(err);}");
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                WebFragment.this.closeWebView();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.more.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                WebFragment.this.managerShare.showWindow(view);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.serachBackImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                WebFragment.this.back.performClick();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.serachCloseImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                PBWebView pBWebView = WebFragment.this.f18409wv;
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, "about:blank");
                } else {
                    pBWebView.loadUrl("about:blank");
                }
                WebFragment.this.activityContext.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.imgYYTtitle.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IntentManager.generateIntentAndGo(WebFragment.this.activityContext, WebFragment.this.webyyt, "", false, "get");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    private void goMain() {
        IntentManager.intentFilter(this.activityContext, true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeWebView() {
        if (!SystemServiceUtils.netIsAvailable() || TextUtils.isEmpty(this.f18409wv.getUrl()) || "0".equals(this.paramsEntity.getBackMode()) || !this.isSuccessOpen || this.receivedError) {
            this.activityContext.finish();
            JiePinEvent jiePinEvent = new JiePinEvent(EventBusUtils.EVENT_JIEPIN);
            jiePinEvent.setSecure(false);
            EventBusUtils.post(jiePinEvent);
            return;
        }
        this.f18409wv.loadURL("javascript:try{var result = webviewClose();js_invoke.handleJSClose(result);}catch(err){js_invoke.handleJSClose(err);}");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAudience(View view, int i) {
        try {
            if (PlayerUtil.isNotPlayerType(this.paramsEntity.getType())) {
                MsLogUtil.m7979d("小窗测试", "不展示小窗,type=" + this.paramsEntity.getType());
                return;
            }
            final BDCloudVideoView playerByType = PlayerUtil.getPlayerByType(this.paramsEntity.getType());
            if (playerByType == null) {
                MsLogUtil.m7979d("小窗测试", "播放器未空");
                return;
            }
            PlayerUtil.setWebPlayStatus(this.paramsEntity.getType(), true);
            int videoWidth = playerByType.getVideoWidth();
            int videoHeight = playerByType.getVideoHeight();
            if (videoWidth != 0 && videoHeight != 0) {
                final MyDragView myDragView = (MyDragView) view.findViewById(2131296398);
                myDragView.setVisibility(0);
                FrameLayout frameLayout = (FrameLayout) view.findViewById(2131296440);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();
                if (videoWidth > videoHeight) {
                    layoutParams.width = UIUtils.dip2px(this.activityContext, 180) + UIUtils.dip2px(10.0f);
                    layoutParams.height = UIUtils.dip2px(this.activityContext, (videoHeight * 180) / videoWidth) + UIUtils.dip2px(10.0f);
                } else {
                    layoutParams.width = UIUtils.dip2px(this.activityContext, (videoWidth * 180) / videoHeight) + UIUtils.dip2px(10.0f);
                    layoutParams.height = UIUtils.dip2px(this.activityContext, 180) + UIUtils.dip2px(10.0f);
                }
                frameLayout.setLayoutParams(layoutParams);
                MsLogUtil.m7979d("小窗测试", "小窗已创建w=" + layoutParams.width + "|h=" + layoutParams.height);
                ViewGroup viewGroup = (ViewGroup) playerByType.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                frameLayout.addView(playerByType);
                MsLogUtil.m7979d("小窗测试", "播放器加入小窗布局");
                ImageView imageView = new ImageView(this.activityContext);
                imageView.setImageResource(2131232348);
                imageView.setPadding(20, 20, 20, 20);
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(80, 80);
                layoutParams2.gravity = 5;
                imageView.setLayoutParams(layoutParams2);
                frameLayout.addView(imageView);
                frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$WebFragment$WwFa6DL3mjFivSy5fk5C-mVVv3c
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        WebFragment.this.activityContext.finish();
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$WebFragment$8DQc6kIjrdyK5pHwnntpwuolzAc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        WebFragment.lambda$initAudience$1(WebFragment.this, playerByType, myDragView, view2);
                    }
                });
                MsLogUtil.m7979d("小窗测试", "播放器加入小窗");
                playerByType.start();
                return;
            }
            MsLogUtil.m7979d("小窗测试", "视频未加载");
            if (this.addPlayerHandler != null) {
                this.addPlayerHandler.removeCallbacksAndMessages(null);
                this.addPlayerHandler.sendEmptyMessageDelayed(i, 500L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initAudience$1(WebFragment webFragment, BDCloudVideoView bDCloudVideoView, MyDragView myDragView, View view) {
        bDCloudVideoView.stopPlayback();
        myDragView.setVisibility(8);
        PlayerUtil.setWebPlayStatus(webFragment.paramsEntity.getType(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void choosePicture(ArrayList<String> arrayList) {
        try {
            if (arrayList.contains("video/*")) {
                showSelectAccountDialog();
            } else {
                new GallaryUtils(this.activityContext).openXiangce(new GallaryUtils.IxiangceInterface() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.19
                    @Override // com.sinovatech.unicom.common.GallaryUtils.IxiangceInterface
                    public void getPhotoPath(String str) {
                        if (!TextUtils.isEmpty(str)) {
                            if (WebFragment.this.uploadMessageAboveL != null) {
                                if (Build.VERSION.SDK_INT <= 28) {
                                    WebFragment.this.compressImage(str);
                                    return;
                                }
                                WebFragment.this.uploadMessageAboveL.onReceiveValue(new Uri[]{Uri.fromFile(new File(str))});
                                WebFragment.this.uploadMessageAboveL = null;
                            }
                        } else if (WebFragment.this.uploadMessageAboveL != null) {
                            WebFragment.this.uploadMessageAboveL.onReceiveValue(new Uri[0]);
                            WebFragment.this.uploadMessageAboveL = null;
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compressImage(String str) {
        try {
            final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(this.activityContext);
            customePorgressDialog.setMessage("图片处理中...");
            Luban.compress(this.activityContext, new File(str)).setCompressFormat(Bitmap.CompressFormat.JPEG).launch(new OnCompressListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.20
                @Override // me.shaohui.advancedluban.OnCompressListener
                public void onStart() {
                    UIUtils.showDialog(WebFragment.this.activityContext, customePorgressDialog);
                }

                @Override // me.shaohui.advancedluban.OnCompressListener
                public void onSuccess(File file) {
                    try {
                        WebFragment.this.uploadMessageAboveL.onReceiveValue(new Uri[]{Uri.fromFile(file)});
                        WebFragment.this.uploadMessageAboveL = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    UIUtils.dismissDialog(WebFragment.this.activityContext, customePorgressDialog);
                }

                @Override // me.shaohui.advancedluban.OnCompressListener
                public void onError(Throwable th) {
                    UIUtils.dismissDialog(WebFragment.this.activityContext, customePorgressDialog);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSelectAccountDialog() {
        final Dialog dialog = new Dialog(this.activityContext, 2131952224);
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(2131493398, (ViewGroup) null);
        Button button = (Button) linearLayout.findViewById(2131298197);
        button.setTextColor(getResources().getColor(2131099978));
        button.setBackgroundColor(getResources().getColor(2131099745));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                WebFragment.this.exMessage();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        Button button2 = (Button) linearLayout.findViewById(2131298199);
        button2.setText("录制");
        button2.setBackgroundColor(getResources().getColor(2131099745));
        button2.setTextColor(getResources().getColor(2131099977));
        button2.setOnClickListener(new View$OnClickListenerC762822(dialog));
        Button button3 = (Button) linearLayout.findViewById(2131298198);
        button3.setText("选择视频");
        button3.setBackgroundColor(getResources().getColor(2131099745));
        button3.setTextColor(getResources().getColor(2131099977));
        button3.setOnClickListener(new View$OnClickListenerC763223(dialog));
        dialog.setContentView(linearLayout);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        Window window = dialog.getWindow();
        window.setGravity(80);
        window.setAttributes(attributes);
        window.setWindowAnimations(2131952235);
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.fragment.WebFragment$22 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC762822 implements View.OnClickListener {
        final /* synthetic */ Dialog val$shareDialog;

        View$OnClickListenerC762822(Dialog dialog) {
            this.val$shareDialog = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            this.val$shareDialog.cancel();
            try {
                RxPermissions rxPermissions = new RxPermissions(WebFragment.this.activityContext);
                PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                rxPermissions.request("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.22.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        PermissionDialog.dimissDialog();
                        if (bool.booleanValue()) {
                            new AvoidOnResult(WebFragment.this.activityContext).startForResult(new Intent("android.media.action.VIDEO_CAPTURE"), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.22.1.1
                                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                                public void onActivityResult(int i, Intent intent) {
                                    if (i != -1) {
                                        WebFragment.this.exMessage();
                                    } else if (WebFragment.this.uploadMessageAboveL != null) {
                                        WebFragment.this.uploadMessageAboveL.onReceiveValue(new Uri[]{intent.getData()});
                                        WebFragment.this.uploadMessageAboveL = null;
                                    }
                                }
                            });
                            return;
                        }
                        UIUtils.toast("请检查相机、存储权限");
                        WebFragment.this.exMessage();
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.22.2
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        PermissionDialog.dimissDialog();
                        WebFragment.this.exMessage();
                    }
                });
            } catch (Exception e) {
                WebFragment.this.exMessage();
                UIUtils.logE("打开录制视频异常：" + e.getMessage());
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.fragment.WebFragment$23 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC763223 implements View.OnClickListener {
        final /* synthetic */ Dialog val$shareDialog;

        View$OnClickListenerC763223(Dialog dialog) {
            this.val$shareDialog = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            this.val$shareDialog.cancel();
            try {
                PermissionDialog.show("选择相册为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
                new RxPermissions(WebFragment.this.activityContext).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.23.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        PermissionDialog.dimissDialog();
                        if (bool.booleanValue()) {
                            Intent intent = new Intent();
                            intent.addCategory("android.intent.category.OPENABLE");
                            intent.setType("video/*");
                            if (Build.VERSION.SDK_INT < 19) {
                                intent.setAction("android.intent.action.GET_CONTENT");
                            } else {
                                intent.setAction("android.intent.action.OPEN_DOCUMENT");
                            }
                            new AvoidOnResult(WebFragment.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.23.1.1
                                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                                public void onActivityResult(int i, Intent intent2) {
                                    if (i != -1) {
                                        WebFragment.this.exMessage();
                                    } else if (WebFragment.this.uploadMessageAboveL != null) {
                                        WebFragment.this.uploadMessageAboveL.onReceiveValue(new Uri[]{intent2.getData()});
                                        WebFragment.this.uploadMessageAboveL = null;
                                    }
                                }
                            });
                            return;
                        }
                        UIUtils.toast("请开启您的存储权限");
                        WebFragment.this.exMessage();
                    }
                });
            } catch (Exception e) {
                WebFragment.this.exMessage();
                UIUtils.logE("打开选择视频异常：" + e.getMessage());
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exMessage() {
        ValueCallback<Uri[]> valueCallback = this.uploadMessageAboveL;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(new Uri[0]);
            this.uploadMessageAboveL = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShareContent(ShareEvent shareEvent) {
        String str = this.TAG;
        MsLogUtil.m7979d(str, "shareList: ==>\n" + shareEvent.getData());
        if (shareEvent.getCode() == EventBusUtils.EVENT_SHARE) {
            this.managerShare.handlerConfig(shareEvent.getData(), this.moreWebMenuList);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShareContent2(ShareEvent2 shareEvent2) {
        if (shareEvent2.getCode() == EventBusUtils.EVENT_SHARE2 && this.isOnTop) {
            this.managerShare.showShareDialog(shareEvent2.getShareList(), shareEvent2.getShareContent());
            ManagerShareConfig.getInstance().getWeworkConfig(this.activityContext).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.24
                @Override // io.reactivex.functions.Consumer
                public void accept(String str) throws Exception {
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTitle(WebTitleEvent webTitleEvent) {
        if (isResumed()) {
            String data = webTitleEvent.getData();
            if (TextUtils.isEmpty(data) || data.trim().equals("null") || data.trim().equals("undefined")) {
                return;
            }
            App.webviewTitle = data;
            TextView textView = this.titleText;
            if (textView != null) {
                textView.setText(data);
            }
        }
    }

    public void onBackPressed() {
        this.back.performClick();
    }

    public void initEmotionMainFragment(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("msg", str);
        this.emotionMainFragment = (EmotionMainFragment) EmotionMainFragment.newInstance(EmotionMainFragment.class, bundle);
        this.emotionMainFragment.bindToContentView(this.contentRootLayout);
        FragmentTransaction beginTransaction = this.activityContext.getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(2131296991, this.emotionMainFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commitAllowingStateLoss();
        this.f18409wv.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$WebFragment$-6kt4PTNu_XRdkovTCmlAc0PnXA
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return WebFragment.lambda$initEmotionMainFragment$2(WebFragment.this, view, motionEvent);
            }
        });
    }

    public static /* synthetic */ boolean lambda$initEmotionMainFragment$2(WebFragment webFragment, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && webFragment.emotionMainFragment.getEmotionIsshown()) {
            webFragment.emotionMainFragment.hideSoftIntut();
            return true;
        }
        return false;
    }

    public void showKeyBoard(String str) {
        EmotionMainFragment emotionMainFragment = this.emotionMainFragment;
        if (emotionMainFragment == null) {
            initEmotionMainFragment(str);
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.25
                @Override // java.lang.Runnable
                public void run() {
                    WebFragment.this.emotionMainFragment.showSoftIntut();
                }
            }, 300L);
            return;
        }
        emotionMainFragment.showSoftIntut();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusEvent(EmojiEvent emojiEvent) {
        if (TextUtils.isEmpty(emojiEvent.getContent())) {
            return;
        }
        PBWebView pBWebView = this.f18409wv;
        String str = "javascript:try{systemBoardCallBack('" + emojiEvent.getContent() + "');}catch(e){}";
        if (pBWebView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
        } else {
            pBWebView.loadUrl(str);
        }
        this.emotionMainFragment.clearContent();
        this.emotionMainFragment.hideSoftIntut();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hideeventBus(EmojiHideEvent emojiHideEvent) {
        PBWebView pBWebView = this.f18409wv;
        if (pBWebView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:try{hideBoardCallBack('true');}catch(e){}");
        } else {
            pBWebView.loadUrl("javascript:try{hideBoardCallBack('true');}catch(e){}");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShareOnclick(ShareOnClickEventBus shareOnClickEventBus) {
        try {
            String shareName = shareOnClickEventBus.getShareName();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", shareName);
            postEventToJS("shareActionListen", jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        switch (configuration.orientation) {
            case 1:
                getActivity().getWindow().clearFlags(1024);
                getActivity().getWindow().addFlags(2048);
                return;
            case 2:
                getActivity().getWindow().clearFlags(2048);
                getActivity().getWindow().addFlags(1024);
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reloadCurrentWebivew(ReloadWebviewEvent reloadWebviewEvent) {
        this.f18409wv.reload();
    }

    public void getToutiaoEvent(ToutiaoEvent toutiaoEvent) {
        if (toutiaoEvent.getCode() == EventBusUtils.EVENT_toutiao_Banner_Show) {
            if (!"showBannerAd".equals(toutiaoEvent.getAction())) {
                this.toutiaoBanner.setVisibility(8);
                this.toutiaoBanner.removeAllViews();
                return;
            }
            this.toutiaoBanner.setVisibility(0);
            this.toutiaoBanner.removeAllViews();
            this.toutiaoBanner.addView((View) toutiaoEvent.getData());
            int height = toutiaoEvent.getHeight();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.xinxiliuLayout.getLayoutParams();
            layoutParams.topMargin = UIUtils.dip2px2(this.activityContext, (height * UIUtils.px2dp2(UIUtils.getScreenWidth((Activity) this.activityContext))) / 360);
            layoutParams.addRule(10);
            this.toutiaoBanner.setLayoutParams(layoutParams);
        } else if (toutiaoEvent.getCode() == EventBusUtils.EVENT_toutiao_Banner_Hide) {
            this.toutiaoBanner.removeAllViews();
            this.toutiaoBanner.setVisibility(8);
        } else if (toutiaoEvent.getCode() == EventBusUtils.EVENT_toutiao_xinxiliu_Show) {
            if (!"showXXLAd".equals(toutiaoEvent.getAction())) {
                this.xinxiliuLayout.setVisibility(8);
                this.xinxiliuLayout.removeAllViews();
                return;
            }
            this.xinxiliuLayout.setVisibility(0);
            this.xinxiliuLayout.removeAllViews();
            this.xinxiliuLayout.addView((View) toutiaoEvent.getData());
            int height2 = toutiaoEvent.getHeight();
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.xinxiliuLayout.getLayoutParams();
            layoutParams2.topMargin = UIUtils.dip2px2(this.activityContext, (height2 * UIUtils.px2dp2(UIUtils.getScreenWidth((Activity) this.activityContext))) / 360);
            layoutParams2.addRule(10);
            this.xinxiliuLayout.setLayoutParams(layoutParams2);
        } else if (toutiaoEvent.getCode() == EventBusUtils.EVENT_toutiao_xinxiliu_Hide) {
            this.xinxiliuLayout.removeAllViews();
            this.xinxiliuLayout.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void method(final String str) {
        try {
            new AvoidOnResult(this.activityContext).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$WebFragment$mN-xHO0yiYfKWCeQRVORFjl6ZpU
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public final void onActivityResult(int i, Intent intent) {
                    WebFragment.lambda$method$4(WebFragment.this, str, i, intent);
                }
            });
        } catch (Exception e) {
            UIUtils.logE(this.TAG, e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$method$4(final WebFragment webFragment, final String str, int i, Intent intent) {
        if (App.hasLogined()) {
            new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$WebFragment$CcAZc2_a5NOCmwQLTqfUfxBc30g
                @Override // java.lang.Runnable
                public final void run() {
                    WebFragment.lambda$method$3(WebFragment.this, str);
                }
            });
            return;
        }
        UIUtils.toast("请登录后重新进入");
        webFragment.activityContext.finish();
    }

    public static /* synthetic */ void lambda$method$3(WebFragment webFragment, String str) {
        try {
            if (App.hasLogined() && !webFragment.isUnicomNewShareLog && str.contains(",")) {
                try {
                    str = URLDecoder.decode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            IntentManager.gotoWebViewActivity(webFragment.activityContext, str, "");
            webFragment.activityContext.finish();
        } catch (Exception e2) {
            UIUtils.logE(webFragment.TAG, e2.getMessage());
        }
    }

    private void initYinsiButton(View view) {
        ((Button) view.findViewById(2131299857)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                WebFragment.this.activityContext.setResult(10);
                WebFragment.this.activityContext.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((Button) view.findViewById(2131299858)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                WebFragment.this.activityContext.setResult(11);
                WebFragment.this.activityContext.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public void setNavigationBarColor(CumpTheme cumpTheme) {
        if (cumpTheme != null) {
            try {
                this.currentCumpTheme = cumpTheme;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        CumpNavigationBar.getInstance(this.activityContext).setNavigationBarColor(this.activityContext, this, this.fragmentCacheView);
    }

    public void navigationBarHidden() {
        UIUtils.setStatusBlack(this.activityContext);
        KeyBoardListener.getInstance(this.activityContext).init();
        this.titleLayout.setVisibility(8);
    }

    private void initJSPluginResource() {
        this.currentCumpAppId = "";
        this.currentCumpAppName = "";
        this.currentCumpAppEntity = null;
        this.currentURLForPlugin = "";
        currentPublishType = "2";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLanuchMiniProgramError(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "小程序启动失败，请重新启动";
        }
        CustomDialogManager.show((Activity) this.activityContext, "温馨提示", str, false, "", "确定", false, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.28
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
            public void onClickOk() {
                WebFragment.this.activityContext.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLanuchMiniProgramVersionUpdate(final String str) {
        CustomDialogManager.show((Activity) this.activityContext, "新版本升级", "点击确定将自动重启小程序，体验新功能", false, "", "确定", false, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.29
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
            public void onClickOk() {
                WebFragment.this.loadUnicomMiniProgram(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLanuchMiniProgramRetry(final String str, boolean z) {
        if (z) {
            CustomDialogManager.show((Activity) this.activityContext, "温馨提示", "小程序启动失败，请重新启动", true, "退出", "重新启动", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.30
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
                    WebFragment.this.loadUnicomMiniProgram(str);
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickCancel() {
                    WebFragment.this.activityContext.finish();
                }
            });
        } else {
            loadUnicomMiniProgram(str);
        }
    }

    private void checkAndOpenEdopDebug(final String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.contains("edopDebug=edopDebug") && str.startsWith("http")) {
                this.currentCumpDebugMode = true;
                CumpEntity cumpEntity = new CumpEntity();
                cumpEntity.setAppId("edop_unicom_debug");
                cumpEntity.setAppName("调试小程序");
                cumpEntity.setAppImg("http://client.10010.com/unicomappdocs/assets/hero.png");
                cumpEntity.setAppHighImg("http://client.10010.com/unicomappdocs/assets/hero.png");
                this.currentCumpAppId = cumpEntity.getAppId();
                this.currentCumpAppName = cumpEntity.getAppName();
                this.currentCumpAppEntity = cumpEntity;
                CumpLanucher.getInstance(this.activityContext).insertDebugAppInfo(cumpEntity);
                navigationBarHidden();
                this.cumpPublishTypeLayout.setVisibility(0);
                ((TextView) this.cumpPublishTypeLayout.findViewById(2131296769)).setText("小程序调试模式");
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.cumpPublishTypeLayout, "alpha", 1.0f, 0.0f);
                ofFloat.setDuration(5000L);
                ofFloat.setRepeatMode(2);
                ofFloat.setRepeatCount(100);
                ofFloat.start();
                this.cumpNavigationBarLayout.setVisibility(0);
                this.cumpNavigationClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.31
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        WebFragment.this.activityContext.finish();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                this.cumpNavigationMore.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.32
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        CumpNavigationBar cumpNavigationBar = CumpNavigationBar.getInstance(WebFragment.this.activityContext);
                        AppCompatActivity appCompatActivity = WebFragment.this.activityContext;
                        WebFragment webFragment = WebFragment.this;
                        cumpNavigationBar.showCumpDetail(appCompatActivity, webFragment, webFragment.f18409wv, str, null);
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                CumpNavigationBar.getInstance(this.activityContext).afterLanuchSuccess(this.activityContext, this, this.fragmentCacheView, this.f18409wv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkClipData(final String str) {
        try {
            final ClipboardManager clipboardManager = (ClipboardManager) this.activityContext.getSystemService("clipboard");
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.33
                @Override // java.lang.Runnable
                public void run() {
                    ClipData primaryClip = clipboardManager.getPrimaryClip();
                    if (primaryClip == null || primaryClip.getItemCount() <= 0 || primaryClip.getItemAt(0).getText().toString().contains("8.0 chinaunicom:/!")) {
                        return;
                    }
                    App.getSharePreferenceUtil().putString("wokouling_url", str);
                }
            }, 200L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            return;
        }
        try {
            String string = intent.getExtras().getString("pay_result");
            MsLogUtil.m7979d("支付回调", "onActivityResult: " + string);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            YunShanFuManager.getYunShanFuManager().getPayResult(string);
        } catch (Exception e) {
            String str = this.TAG;
            MsLogUtil.m7977e(str, "onActivityResult 异常:" + e.getMessage());
        }
    }

    public void errorLog(String str, String str2) {
        try {
            String str3 = this.logUrlTitle;
            if ("shop".equals(this.paramsEntity.getType())) {
                str3 = "商城";
            } else if ("find".equals(this.paramsEntity.getType())) {
                str3 = "财富";
            } else if ("yw".equals(this.paramsEntity.getType())) {
                str3 = "异网";
            } else if (TextUtils.isEmpty(this.logUrlTitle)) {
                str3 = "其他";
            }
            PvCurrencyLogUtils.pvLogMainDJ("S2ndpage1187", str2 + "", "", str3 + "", str + "", "", "");
        } catch (Exception e) {
            MsLogUtil.m7977e(this.TAG, "上传异常信息错误:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideEdopMiniLauncher() {
        if (this.miniLauncherLayout.getVisibility() == 0) {
            MsLogUtil.m7979d(CumpLanucher.TAG, "【联通小程序】加载离线地址完成");
            if (this.f18409wv.canGoBack()) {
                try {
                    if (this.f18409wv != null) {
                        this.f18409wv.clearHistory();
                    }
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                }
            }
        }
        if ("onLanuchError".equals(this.miniLauncherLayout.getTag())) {
            return;
        }
        this.miniLauncherLayout.setVisibility(8);
    }

    public void loadRiskControlPage() {
        AppCompatActivity appCompatActivity = this.activityContext;
        if (appCompatActivity instanceof WebDetailActivity) {
            final WebDetailActivity webDetailActivity = (WebDetailActivity) appCompatActivity;
            if (webDetailActivity.shouLoadJS()) {
                this.f18409wv.evaluateJavascript("javascript:try{isRiskControlPage();}catch(err){}", new ValueCallback<String>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.34
                    @Override // android.webkit.ValueCallback
                    public void onReceiveValue(String str) {
                        try {
                            webDetailActivity.setIskControlPage("true".equals(str));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        MsLogUtil.m7979d("isRiskControlPage", "value:" + str);
                    }
                });
            }
        }
    }

    public void loadCollectData() {
        if (App.hasLogined() && this.configManager.getCollectionSwitch()) {
            MsLogUtil.m7980d("加载右上角的数据");
            this.moreWebMenuList.clear();
            this.f18409wv.evaluateJavascript("javascript:try{window.clientGetCollectionInfoFromWeb&&window.clientGetCollectionInfoFromWeb();}catch(err){}", new C764735());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.fragment.WebFragment$35 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C764735 implements ValueCallback<String> {
        C764735() {
        }

        @Override // android.webkit.ValueCallback
        public void onReceiveValue(String str) {
            try {
                MsLogUtil.m7980d("获取到右上角的返回值");
                if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, "null") && !TextUtils.equals(str, "undefined")) {
                    JSONObject jSONObject = new JSONObject(str);
                    final String optString = jSONObject.optString("appId");
                    final String optString2 = jSONObject.optString("productId");
                    if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                        final RecentCustomManager recentCustomManager = new RecentCustomManager(WebFragment.this.activityContext);
                        MsLogUtil.m7980d("检测是否开通收藏权限");
                        recentCustomManager.abilityVerify(optString).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.35.1
                            @Override // io.reactivex.functions.Consumer
                            public void accept(Boolean bool) throws Exception {
                                MsLogUtil.m7980d("检测权限成功 = " + bool);
                                if (bool.booleanValue()) {
                                    recentCustomManager.queryCollectMenu(optString, optString2, new Consumer<JSONObject>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.35.1.1
                                        @Override // io.reactivex.functions.Consumer
                                        public void accept(JSONObject jSONObject2) throws Exception {
                                            WebMenuManager.WebMenuEntity webMenuEntity = new WebMenuManager.WebMenuEntity("shoucang", "收藏", "", "", "", "");
                                            WebMenuManager.WebMenuEntity webMenuEntity2 = new WebMenuManager.WebMenuEntity("wodeshoucang", "我的收藏", "", "", "", "");
                                            if ("1".equals(jSONObject2.optString("isCollected"))) {
                                                webMenuEntity.setCollect(true);
                                            }
                                            webMenuEntity.setAppId(optString);
                                            webMenuEntity.setProductId(optString2);
                                            MsLogUtil.m7980d("添加右上角收藏数据");
                                            WebFragment.this.moreWebMenuList.add(webMenuEntity);
                                            WebFragment.this.moreWebMenuList.add(webMenuEntity2);
                                        }
                                    }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.35.1.2
                                        @Override // io.reactivex.functions.Consumer
                                        public void accept(Throwable th) throws Exception {
                                            MsLogUtil.m7980d("查询H5/商品收藏状态失败");
                                        }
                                    });
                                }
                            }
                        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.35.2
                            @Override // io.reactivex.functions.Consumer
                            public void accept(Throwable th) throws Exception {
                                MsLogUtil.m7980d("查询H5/商品校验能力状态失败");
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                MsLogUtil.m7980d("查询H5/商品收藏状态异常");
            }
        }
    }

    public String getWebUrl() {
        return this.f18409wv.getUrl();
    }

    public String getWebTitle() {
        return this.titleText.getText().toString();
    }

    public void collectLeftH5info() {
        if (TYCJConfigUtil.isWhiteUrl("H5Info", this.url, "")) {
            Map<String, String> params = TYCJPVParamsUtil.getParams("", "", this.url, "", "", "1", "", "", "");
            JSONObject jSONObject = new JSONObject();
            try {
                if (!TextUtils.isEmpty(tycjCollectData)) {
                    jSONObject = new JSONObject(tycjCollectData);
                } else {
                    pageLoadTime = 0L;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            TYCJBoxManager.getInstance().collectH5Info(jSONObject, params);
        }
        tycjCollectData = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSafelyHostGrayHint(final SafelyHostEntity safelyHostEntity) {
        try {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.safelyHostGrayHintLayout.getLayoutParams();
            if (this.titleLayout != null && this.titleLayout.getVisibility() == 0) {
                layoutParams.topMargin = UIUtils.dip2px(this.activityContext, 6.0f);
            } else {
                layoutParams.topMargin = UIUtils.getStatusBarHeight(this.activityContext) + UIUtils.dip2px(this.activityContext, 40.0f) + UIUtils.dip2px(this.activityContext, 6.0f);
            }
            this.safelyHostGrayHintLayout.setLayoutParams(layoutParams);
            MarqueeTextView marqueeTextView = (MarqueeTextView) this.safelyHostGrayHintLayout.findViewById(2131298438);
            marqueeTextView.setFocus(true);
            marqueeTextView.setFocusableInTouchMode(true);
            if (safelyHostEntity != null) {
                if (!TextUtils.isEmpty(safelyHostEntity.getGrayHint())) {
                    marqueeTextView.setText(safelyHostEntity.getGrayHint());
                }
                if (!TextUtils.isEmpty(safelyHostEntity.getGrayHintLink()) && !TextUtils.equals("null", safelyHostEntity.getGrayHintLink())) {
                    marqueeTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.WebFragment.36
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            IntentManager.gotoWebViewActivity(WebFragment.this.activityContext, safelyHostEntity.getGrayHintLink(), "", "0", "get");
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                }
            }
            this.safelyHostGrayHintLayout.setVisibility(0);
        } catch (Exception e) {
            e.printStackTrace();
            this.safelyHostGrayHintLayout.setVisibility(8);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEdopParameter(EdopXunJianEvent edopXunJianEvent) {
        try {
            String edopAppid = edopXunJianEvent.getEdopAppid();
            String decrypt = edopXunJianEvent.getDecrypt();
            String batchId = edopXunJianEvent.getBatchId();
            long timeMillis = edopXunJianEvent.getTimeMillis();
            String decrypt2 = AesEncryptUtil.decrypt(decrypt, "123xunjian45edop", "123xunjian45edop");
            if (TextUtils.equals(edopAppid, this.currentCumpAppId) && !TextUtils.isEmpty(decrypt2)) {
                String replace = decrypt2.replace("replaceSeq", DeviceHelper.getDeviceID(true)).replace("replaceReqTime", String.valueOf(timeMillis)).replace("replaceAppid", edopAppid).replace("replaceBatchId", batchId).replace("replaceUrl", URLSet.getEdopXunJianUrl());
                PBWebView pBWebView = this.f18409wv;
                String str = "javascript:" + replace;
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                } else {
                    pBWebView.loadUrl(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
