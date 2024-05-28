package com.sinovatech.unicom.basic.p315ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.content.ContextCompat;
import android.support.p083v4.content.FileProvider;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.LinearLayout;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import com.alipay.sdk.app.AuthTask;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.euicc.server.IOpenEUICC;
import com.euicc.server.IServiceBinder;
import com.euicc.server.model.EUICCDeviceInfo;
import com.euicc.server.model.EUICCInfo;
import com.euicc.server.model.IOpenEUICCCalbcak;
import com.huawei.multisimservice.IOpenMultiSim;
import com.huawei.multisimservice.IServiceBinder;
import com.huawei.multisimservice.model.IOpenMultiSimCalbcak;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.megvii.livenesslib.LoginFaceLivenessActivity;
import com.megvii.livenesslib.util.SDCardUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.adapter.SimplePermissionAdapter;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.p284qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.datacenter.PhoneDataCenter;
import com.sinovatech.unicom.basic.eventbus.JiePinEvent;
import com.sinovatech.unicom.basic.eventbus.LoadInfoEvent;
import com.sinovatech.unicom.basic.eventbus.PopWebViewEvent;
import com.sinovatech.unicom.basic.eventbus.ShareEvent;
import com.sinovatech.unicom.basic.eventbus.WebTitleEvent;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p314po.OEMInfoEntity;
import com.sinovatech.unicom.basic.p315ui.JSInvokeHandler;
import com.sinovatech.unicom.basic.p315ui.KuandaiDialogManager;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerGZT;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerOneKeyLogin;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.AuthResult;
import com.sinovatech.unicom.common.ContectUtil;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.ImageCompressUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.baidumap.BaiduCollectPopWindow;
import com.sinovatech.unicom.separatemodule.baidumap.BaiduH5BusinessDetails;
import com.sinovatech.unicom.separatemodule.capture.CapuActivity2;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch;
import com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome;
import com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch;
import com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin;
import com.sinovatech.unicom.separatemodule.huotijiance.HuoTiActivity;
import com.sinovatech.unicom.separatemodule.huotijiance.HuoTiEntity;
import com.sinovatech.unicom.separatemodule.huotijiance.HuoTiUpload;
import com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpClient;
import com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler;
import com.sinovatech.unicom.separatemodule.huotijiance.util.FileUtils;
import com.sinovatech.unicom.separatemodule.huotijiance.util.FlymePermissionUtil;
import com.sinovatech.unicom.separatemodule.huotijiance.util.RomUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.LoginParamsEntity;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.ManagerTianyan;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.zhihuiwojia.CameraActivity;
import com.sinovatech.unicom.separatemodule.zhihuiwojia.CommonUtils;
import com.taisys.oti.Card;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;
import okhttp3.Cookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JSInvokeHandler {
    private static final String BONE_PACKAGE_NAME = "com.huawei.bone";
    private static final String CLASS_NAME_MULTI_SIM_SERVICE = "com.huawei.multisimservice.MultiSimService";
    private static final String HEALTH_PACKEAGE_NAME = "com.huawei.health";
    public static String yuleBackUrl;
    private File PHOTO_DIR;
    private final String TAG;
    private Handler alipayAuthHandler;
    private final HashSet<String> backUrlSet;
    private BaseWebFragment baseWebFragment;
    private boolean binRet1;
    private boolean binRet2;
    private ServiceConnection conn;
    public AppCompatActivity context;
    private int deviceType1_2;
    private int deviceType2_2;
    private String entranceUrl;
    private String fileName;
    private Handler handler;
    private Handler handlerOpen;
    private boolean hasShared;
    private IOpenEUICC iOpenMultiSim;
    private IServiceBinder iServiceBinder;
    private boolean isDownLoadESMI;
    private boolean isJsDirected;
    private int linkCode;
    private IOpenMultiSimCalbcak.Stub mCallback;
    private IOpenMultiSimCalbcak.Stub mCallback2;
    private Card mCard;
    private ServiceConnection mConnection;
    private ServiceConnection mConnection2;
    private File mCurrentPhotoFile;
    private IOpenEUICCCalbcak.Stub mEuiccCallback;
    public LocationClient mLocClient;
    private IOpenMultiSim mService;
    private IOpenMultiSim mService2;
    private ManagerCommonWatch managerCommonWatch;
    private ManagerHuaweiSmarthome managerDongfengWatch;
    private ManagerGZT managerGZT;
    private ManagerHuaweiWatch managerHuaweiWatch;
    private String mobileUserInfoJson;
    public BDLocationListener myListener;
    private PhoneDataCenter phoneDataCenter;
    private Map<String, YHXXJSPlugin> pluginMap;
    private Map<String, Integer> pluginRequestCodeMap;
    private CustomePorgressDialog porgressDialog;
    private SharePreferenceUtil preference;
    private String prilCode_2;
    private RequestParams requestParams;
    private int resultCode1_2;
    private int resultCode2_2;
    private RxPermissions rxPermissions;
    private String urlInfo;
    private UserManager userManager;
    private String videoPath;
    private PBWebView webView;
    private Handler webViewHandler;

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$OnJSInvokeHandlerListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnJSInvokeHandlerListener {
        void onJSBack(boolean z, boolean z2);

        void onJSBackgroundLogin(String str);

        void onJSLogin(String str, String str2);

        void onJsTitle(String str);

        void onMenuConfig(String str);

        void onQrpath(String str);

        void onShare(String str, String str2);

        void onShopLogin();
    }

    public static /* synthetic */ String lambda$broadCompare$0(String str) throws Exception {
        return str;
    }

    static /* synthetic */ PBWebView access$100(JSInvokeHandler jSInvokeHandler) {
        return jSInvokeHandler.webView;
    }

    static /* synthetic */ void access$1000(JSInvokeHandler jSInvokeHandler, String str, JSONObject jSONObject) {
        jSInvokeHandler.shareDirectly(str, jSONObject);
    }

    static /* synthetic */ RxPermissions access$1200(JSInvokeHandler jSInvokeHandler) {
        return jSInvokeHandler.rxPermissions;
    }

    static /* synthetic */ void access$1900(JSInvokeHandler jSInvokeHandler) {
        jSInvokeHandler.doTakePhoto();
    }

    static /* synthetic */ void access$2000(JSInvokeHandler jSInvokeHandler) {
        jSInvokeHandler.doPickPhotoFromGallery();
    }

    static /* synthetic */ void access$3000(JSInvokeHandler jSInvokeHandler) {
        jSInvokeHandler.goBack();
    }

    static /* synthetic */ HashSet access$3100(JSInvokeHandler jSInvokeHandler) {
        return jSInvokeHandler.backUrlSet;
    }

    static /* synthetic */ void access$3200(JSInvokeHandler jSInvokeHandler) {
        jSInvokeHandler.closeChahao();
    }

    static /* synthetic */ BaseWebFragment access$400(JSInvokeHandler jSInvokeHandler) {
        return jSInvokeHandler.baseWebFragment;
    }

    static /* synthetic */ boolean access$6902(JSInvokeHandler jSInvokeHandler, boolean z) {
        jSInvokeHandler.hasShared = z;
        return z;
    }

    static /* synthetic */ String access$700(JSInvokeHandler jSInvokeHandler) {
        return jSInvokeHandler.urlInfo;
    }

    static /* synthetic */ void access$7000(JSInvokeHandler jSInvokeHandler, String str, JSONObject jSONObject, Bitmap bitmap) {
        jSInvokeHandler.shareDirectly2(str, jSONObject, bitmap);
    }

    static /* synthetic */ String access$702(JSInvokeHandler jSInvokeHandler, String str) {
        jSInvokeHandler.urlInfo = str;
        return str;
    }

    static /* synthetic */ void access$7200(JSInvokeHandler jSInvokeHandler, String str, String str2) {
        jSInvokeHandler.setShareStatusToServer(str, str2);
    }

    static /* synthetic */ Card access$7300(JSInvokeHandler jSInvokeHandler) {
        return jSInvokeHandler.mCard;
    }

    static /* synthetic */ Handler access$800(JSInvokeHandler jSInvokeHandler) {
        return jSInvokeHandler.webViewHandler;
    }

    static /* synthetic */ void access$900(JSInvokeHandler jSInvokeHandler, String str, String str2, String str3) {
        jSInvokeHandler.gotoRedirect(str, str2, str3);
    }

    public JSInvokeHandler(AppCompatActivity appCompatActivity, BaseWebFragment baseWebFragment, PBWebView pBWebView, Handler handler, Map<String, YHXXJSPlugin> map, Map<String, Integer> map2) {
        this.TAG = "JSInvokeHandler";
        this.mLocClient = null;
        this.myListener = new MyLocationListenner();
        this.isJsDirected = false;
        this.videoPath = "";
        this.pluginMap = new HashMap();
        this.pluginRequestCodeMap = new HashMap();
        this.handlerOpen = new Handler(Looper.getMainLooper());
        this.handler = new HandlerC72826();
        this.backUrlSet = new HashSet<>();
        this.conn = new ServiceConnectionC720328();
        this.mEuiccCallback = new BinderC720429();
        this.mCallback = new BinderC724834();
        this.mConnection = new ServiceConnectionC725035();
        this.linkCode = 1;
        this.mCallback2 = new BinderC725237();
        this.mConnection2 = new ServiceConnectionC725638();
        this.context = appCompatActivity;
        this.webView = pBWebView;
        this.webViewHandler = handler;
        this.pluginMap = map;
        this.pluginRequestCodeMap = map2;
        this.baseWebFragment = baseWebFragment;
        this.rxPermissions = new RxPermissions(appCompatActivity);
        this.preference = App.getSharePreferenceUtil();
        this.userManager = UserManager.getInstance();
        this.phoneDataCenter = new PhoneDataCenter(appCompatActivity);
        initAlipayAuthHandler();
    }

    public JSInvokeHandler(AppCompatActivity appCompatActivity, PBWebView pBWebView, Handler handler) {
        this.TAG = "JSInvokeHandler";
        this.mLocClient = null;
        this.myListener = new MyLocationListenner();
        this.isJsDirected = false;
        this.videoPath = "";
        this.pluginMap = new HashMap();
        this.pluginRequestCodeMap = new HashMap();
        this.handlerOpen = new Handler(Looper.getMainLooper());
        this.handler = new HandlerC72826();
        this.backUrlSet = new HashSet<>();
        this.conn = new ServiceConnectionC720328();
        this.mEuiccCallback = new BinderC720429();
        this.mCallback = new BinderC724834();
        this.mConnection = new ServiceConnectionC725035();
        this.linkCode = 1;
        this.mCallback2 = new BinderC725237();
        this.mConnection2 = new ServiceConnectionC725638();
        this.context = appCompatActivity;
        this.webView = pBWebView;
        this.webViewHandler = handler;
        this.rxPermissions = new RxPermissions(appCompatActivity);
        this.preference = App.getSharePreferenceUtil();
        this.userManager = UserManager.getInstance();
        this.phoneDataCenter = new PhoneDataCenter(appCompatActivity);
        initAlipayAuthHandler();
    }

    public JSInvokeHandler(AppCompatActivity appCompatActivity, PBWebView pBWebView, Handler handler, OnJSInvokeHandlerListener onJSInvokeHandlerListener) {
        this(appCompatActivity, pBWebView, handler);
        this.rxPermissions = new RxPermissions(appCompatActivity);
        initAlipayAuthHandler();
    }

    public JSInvokeHandler(AppCompatActivity appCompatActivity, String str, PBWebView pBWebView, Handler handler, OnJSInvokeHandlerListener onJSInvokeHandlerListener) {
        this(appCompatActivity, pBWebView, handler, onJSInvokeHandlerListener);
        this.entranceUrl = str;
        this.rxPermissions = new RxPermissions(appCompatActivity);
        initAlipayAuthHandler();
    }

    private void initAlipayAuthHandler() {
        this.alipayAuthHandler = new Handler() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.1
            {
                JSInvokeHandler.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                try {
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    String str = "javascript:callbackAlipayAccount('" + ((String) message.obj) + "');";
                    if (pBWebView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                    } else {
                        pBWebView.loadUrl(str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @JavascriptInterface
    public void exec(final String str) {
        this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.2
            {
                JSInvokeHandler.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    String string = new JSONObject(str).getString("action");
                    YHXXJSPlugin yHXXJSPlugin = (YHXXJSPlugin) JSInvokeHandler.this.pluginMap.get(string);
                    int intValue = ((Integer) JSInvokeHandler.this.pluginRequestCodeMap.get(string)).intValue();
                    if (yHXXJSPlugin != null) {
                        yHXXJSPlugin.call(JSInvokeHandler.this.context, JSInvokeHandler.this.webView, intValue, str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC72063 implements Runnable {
        final /* synthetic */ String val$jsonData;

        /*  JADX ERROR: Failed to decode insn: 0x0415: UNKNOWN(0x54F6), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0415: UNKNOWN(0x54F6)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x04A4: UNKNOWN(0x8FE9), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04A4: UNKNOWN(0x8FE9)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x04E2: UNKNOWN(0x8FEE), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04E2: UNKNOWN(0x8FEE)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0580: UNKNOWN(0x003F), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0580: UNKNOWN(0x003F)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x059C: FILLED_NEW_ARRAY , method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            java.lang.NullPointerException
            	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x05D6: CONST_STRING r144, method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            java.lang.IllegalArgumentException: newPosition > limit: (16908546 > 14732120)
            	at java.nio.Buffer.createPositionException(Buffer.java:269)
            	at java.nio.Buffer.position(Buffer.java:244)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:177)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0646: UNKNOWN(0x0043), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0646: UNKNOWN(0x0043)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0670: UNKNOWN(0x9043), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0670: UNKNOWN(0x9043)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0862: UNKNOWN(0x0041), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0862: UNKNOWN(0x0041)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x08FE: UNKNOWN(0x0041), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x08FE: UNKNOWN(0x0041)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0922: UNKNOWN(0x90E8), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0922: UNKNOWN(0x90E8)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0B94: CONST_STRING r0, method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            java.lang.IllegalArgumentException: newPosition < 0: (-2125463440 < 0)
            	at java.nio.Buffer.createPositionException(Buffer.java:269)
            	at java.nio.Buffer.position(Buffer.java:244)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0BF3: UNKNOWN(0x2A41), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0BF3: UNKNOWN(0x2A41)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0C1E: UNKNOWN(0x91E4), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0C1E: UNKNOWN(0x91E4)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0C2A: UNKNOWN(0x91EB), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0C2A: UNKNOWN(0x91EB)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0C36: UNKNOWN(0x91F1), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0C36: UNKNOWN(0x91F1)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0C42: UNKNOWN(0x91F8), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0C42: UNKNOWN(0x91F8)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0C4E: CONST_METHOD_TYPE r145, method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0C4E: CONST_METHOD_TYPE r145'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0C96: FILLED_NEW_ARRAY r3, r0, r0, r0, r2, r4214, r4215, r4216, r4217, method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            java.lang.NullPointerException
            	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0CC6: UNKNOWN(0x923E), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0CC6: UNKNOWN(0x923E)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0CCB: UNKNOWN(0x2A43), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0CCB: UNKNOWN(0x2A43)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0D26: UNKNOWN(0x9273), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0D26: UNKNOWN(0x9273)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0D32: UNKNOWN(0x9279), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0D32: UNKNOWN(0x9279)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0DDA: UNKNOWN(0x92E4), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0DDA: UNKNOWN(0x92E4)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0DE6: UNKNOWN(0x92EA), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0DE6: UNKNOWN(0x92EA)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0DF2: UNKNOWN(0x92F0), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0DF2: UNKNOWN(0x92F0)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0DFE: UNKNOWN(0x92F7), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0DFE: UNKNOWN(0x92F7)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0E0A: INVOKE_CUSTOM_RANGE r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143, r144, r145, r146, r147, r148, r149, method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0xa
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
            	... 12 more
            */
        /*  JADX ERROR: Failed to decode insn: 0x0E8E: UNKNOWN(0x9343), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0E8E: UNKNOWN(0x9343)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0EE2: UNKNOWN(0x9373), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0EE2: UNKNOWN(0x9373)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0EEE: UNKNOWN(0x9379), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0EEE: UNKNOWN(0x9379)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0EFF: UNKNOWN(0x2A3F), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0EFF: UNKNOWN(0x2A3F)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0F0B: UNKNOWN(0x2A3F), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0F0B: UNKNOWN(0x2A3F)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0F2F: UNKNOWN(0x2A40), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0F2F: UNKNOWN(0x2A40)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0F3B: UNKNOWN(0x2A40), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0F3B: UNKNOWN(0x2A40)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0FBA: UNKNOWN(0x93E8), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0FBA: UNKNOWN(0x93E8)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0FC6: UNKNOWN(0x93EF), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0FC6: UNKNOWN(0x93EF)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x1013: UNKNOWN(0x2A42), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x1013: UNKNOWN(0x2A42)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x101F: UNKNOWN(0x2A42), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x101F: UNKNOWN(0x2A42)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x103E: UNKNOWN(0x9441), method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x103E: UNKNOWN(0x9441)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x10AD: INVOKE_CUSTOM r10, r1, r1, r0, r8, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, method: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.run():void
            jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: newPosition > limit: (175662436 > 14732120)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:443)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            Caused by: java.lang.IllegalArgumentException: newPosition > limit: (175662436 > 14732120)
            	at java.nio.Buffer.createPositionException(Buffer.java:269)
            	at java.nio.Buffer.position(Buffer.java:244)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:208)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
            	... 12 more
            */
        @Override // java.lang.Runnable
        public void run() {
            /*
                Method dump skipped, instructions count: 5026
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.JSInvokeHandler.RunnableC72063.run():void");
        }

        RunnableC72063(String str) {
            JSInvokeHandler.this = r1;
            this.val$jsonData = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$3 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C72333 implements CustomDialogManager.CustomeDialogListener {
            final /* synthetic */ String val$needHandler;

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            C72333(String str) {
                RunnableC72063.this = r1;
                this.val$needHandler = str;
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                new AvoidOnResult(JSInvokeHandler.this.context).startForResult(new Intent(JSInvokeHandler.this.context, LoginBindActivity.class), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.3.1
                    {
                        C72333.this = this;
                    }

                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i, Intent intent) {
                        if (App.hasLogined()) {
                            UnicomCookieManager.addLoginCookie();
                            if (TextUtils.isEmpty(JSInvokeHandler.this.urlInfo)) {
                                JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.3.1.1
                                    {
                                        C72341.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        JSInvokeHandler.this.webView.loadURL("javascript:history.go(0)");
                                    }
                                });
                                return;
                            } else if ("close".equals(JSInvokeHandler.this.urlInfo.trim())) {
                                JSInvokeHandler.this.context.finish();
                                return;
                            } else {
                                JSInvokeHandler.this.initLoginbackUrl(JSInvokeHandler.this.urlInfo, new HashMap(), C72333.this.val$needHandler);
                                return;
                            }
                        }
                        JSInvokeHandler.this.context.finish();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$12 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C721112 extends SimplePermissionAdapter {
            C721112() {
                RunnableC72063.this = r1;
            }

            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionOk(Permission permission) {
                super.onPermissionOk(permission);
                PermissionDialog.dimissDialog();
                new AvoidOnResult(JSInvokeHandler.this.context).startForResult(CapuActivity2.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.12.1
                    {
                        C721112.this = this;
                    }

                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i, Intent intent) {
                        if (i != -1) {
                            JSInvokeHandler.this.webView.loadURL("javascript:getScancodeContent()");
                            return;
                        }
                        String stringExtra = intent.getStringExtra("SCAN_RESULT");
                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                        pBWebView.loadURL("javascript:getScancodeContent('" + stringExtra + "')");
                    }
                });
            }

            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionDenied(Permission permission) {
                super.onPermissionDenied(permission);
                PermissionDialog.dimissDialog();
                UIUtils.toast("未开启权限");
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$13 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class RunnableC721313 implements Runnable {
            RunnableC721313() {
                RunnableC72063.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                pBWebView.loadURL("javascript:setSharedBikeInfo(" + JSInvokeHandler.this.mobileUserInfoJson + ")");
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$14 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class RunnableC721414 implements Runnable {
            final /* synthetic */ String val$msg;

            RunnableC721414(String str) {
                RunnableC72063.this = r1;
                this.val$msg = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (JSInvokeHandler.this.context instanceof MainActivity) {
                    ((MainActivity) JSInvokeHandler.this.context).setWvlayoutVisible(new PopWebViewEvent(2));
                    EventBusUtils.post(new LoadInfoEvent(0));
                    StatisticsUploadUtils.upload(JSInvokeHandler.this.context, "fulitanchuang", this.val$msg, "关闭", "", "福利弹窗", JSInvokeHandler.this.urlInfo);
                }
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$15 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class RunnableC721515 implements Runnable {
            final /* synthetic */ String val$msg;

            RunnableC721515(String str) {
                RunnableC72063.this = r1;
                this.val$msg = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!TextUtils.isEmpty(JSInvokeHandler.this.urlInfo)) {
                    UnicomCollectManager unicomCollectManager = UnicomCollectManager.getInstance();
                    unicomCollectManager.setTransId("S2ndpage1238" + CollectConfig.montageTag1 + "福利弹窗");
                    IntentManager.generateIntentAndGo(JSInvokeHandler.this.context, JSInvokeHandler.this.urlInfo, "", false, "get");
                    if (JSInvokeHandler.this.urlInfo.contains("androidId")) {
                        StatisticsUploadUtils.upload(JSInvokeHandler.this.context, "hzyl0001", "首页弹窗", "入口PV", "", "福利弹窗", JSInvokeHandler.this.urlInfo);
                    } else {
                        StatisticsUploadUtils.upload(JSInvokeHandler.this.context, "fulitanchuang", this.val$msg, "点击", "", "福利弹窗", JSInvokeHandler.this.urlInfo);
                    }
                }
                if (JSInvokeHandler.this.context instanceof MainActivity) {
                    ((MainActivity) JSInvokeHandler.this.context).setWvlayoutVisible(new PopWebViewEvent(3));
                }
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$16 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class RunnableC721616 implements Runnable {
            final /* synthetic */ String val$msg;

            RunnableC721616(String str) {
                RunnableC72063.this = r1;
                this.val$msg = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (JSInvokeHandler.this.context instanceof MainActivity) {
                    ((MainActivity) JSInvokeHandler.this.context).setWvlayoutVisible(new PopWebViewEvent(1, this.val$msg));
                    StatisticsUploadUtils.upload(JSInvokeHandler.this.context, "fulitanchuang", this.val$msg, "展示", "", "福利弹窗", JSInvokeHandler.this.urlInfo);
                }
            }
        }

        public static /* synthetic */ void lambda$run$0(RunnableC72063 runnableC72063, String str, String str2, int i, Intent intent) {
            if (App.hasLogined() && LoginManager.isKuandaiOrGuhua()) {
                UnicomCookieManager.addLoginCookie();
                if (TextUtils.isEmpty(JSInvokeHandler.this.urlInfo) || "LOCAL-BROADBAND".equals(JSInvokeHandler.this.urlInfo)) {
                    JSInvokeHandler.this.webView.reload();
                    return;
                }
                JSInvokeHandler jSInvokeHandler = JSInvokeHandler.this;
                jSInvokeHandler.initLoginbackUrl(jSInvokeHandler.urlInfo, new HashMap(), str);
            } else if ("retainWebview".equals(str2)) {
            } else {
                JSInvokeHandler.this.context.finish();
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$17 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C721717 extends SimplePermissionAdapter {
            C721717() {
                RunnableC72063.this = r1;
            }

            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionOk(Permission permission) {
                super.onPermissionOk(permission);
                PermissionDialog.dimissDialog();
                ((ObservableSubscribeProxy) Observable.create(new ObservableOnSubscribe<String>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.17.3
                    {
                        C721717.this = this;
                    }

                    @Override // io.reactivex.ObservableOnSubscribe
                    public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                        observableEmitter.onNext(new ContectUtil(JSInvokeHandler.this.context).getJson());
                        observableEmitter.onComplete();
                    }
                }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(JSInvokeHandler.this.context))).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.17.1
                    {
                        C721717.this = this;
                    }

                    @Override // io.reactivex.functions.Consumer
                    public void accept(String str) throws Exception {
                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                        pBWebView.loadURL("javascript:setAllContent('" + str + "')");
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.17.2
                    {
                        C721717.this = this;
                    }

                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        JSInvokeHandler.this.webView.loadURL("javascript:setAllContent('[]')");
                    }
                });
            }

            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionDenied(Permission permission) {
                super.onPermissionDenied(permission);
                JSInvokeHandler.this.webView.loadURL("javascript:setAllContent('[]')");
                PermissionDialog.dimissDialog();
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$18 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class RunnableC722118 implements Runnable {
            final /* synthetic */ String val$authInfo;

            RunnableC722118(String str) {
                RunnableC72063.this = r1;
                this.val$authInfo = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                String convertJSON = AuthResult.convertJSON(new AuthTask(JSInvokeHandler.this.context).authV2(this.val$authInfo, true));
                Message message = new Message();
                message.what = 0;
                message.obj = convertJSON;
                JSInvokeHandler.this.alipayAuthHandler.sendMessage(message);
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$19 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C722219 implements BaiduCollectPopWindow.OnClickListener {
            final /* synthetic */ BaiduH5BusinessDetails val$details;

            C722219(BaiduH5BusinessDetails baiduH5BusinessDetails) {
                RunnableC72063.this = r1;
                this.val$details = baiduH5BusinessDetails;
            }

            @Override // com.sinovatech.unicom.separatemodule.baidumap.BaiduCollectPopWindow.OnClickListener
            public void onClick(String str) {
                if ("百度地图".equals(str)) {
                    this.val$details.setUpBaiduAPPByMine(true, null);
                } else if ("高德地图".equals(str)) {
                    this.val$details.setUpGaodeAppByMine(true, null);
                }
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$20 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C722420 extends SimplePermissionAdapter {
            C722420() {
                RunnableC72063.this = r1;
            }

            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionOk(Permission permission) {
                super.onPermissionOk(permission);
                PermissionDialog.dimissDialog();
                JSInvokeHandler.this.webView.loadURL("javascript:setContentStatus('YES')");
            }

            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionDenied(Permission permission) {
                super.onPermissionDenied(permission);
                PermissionDialog.dimissDialog();
                JSInvokeHandler.this.webView.loadURL("javascript:setContentStatus('NO')");
            }
        }

        @NBSInstrumented
        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$21 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C722521 implements AvoidOnResult.Callback {
            final /* synthetic */ String val$acID;
            final /* synthetic */ String val$backUrl2;
            final /* synthetic */ String val$taskID;

            C722521(String str, String str2, String str3) {
                RunnableC72063.this = r1;
                this.val$acID = str;
                this.val$taskID = str2;
                this.val$backUrl2 = str3;
            }

            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent) {
                if (TextUtils.isEmpty(this.val$acID) && TextUtils.isEmpty(this.val$taskID)) {
                    if (!TextUtils.isEmpty(JSInvokeHandler.yuleBackUrl)) {
                        JSInvokeHandler.this.webView.loadURL(JSInvokeHandler.yuleBackUrl);
                        JSInvokeHandler.yuleBackUrl = "";
                        return;
                    } else if (TextUtils.isEmpty(this.val$backUrl2)) {
                        return;
                    } else {
                        JSInvokeHandler.this.webView.loadURL(this.val$backUrl2);
                        return;
                    }
                }
                JSONObject jSONObject = new JSONObject();
                if (intent != null) {
                    try {
                        jSONObject.put("code", intent.getStringExtra("result"));
                        jSONObject.put("msg", "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    StringBuilder sb = new StringBuilder();
                    sb.append("javascript:fuchuangResult(");
                    sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                    sb.append(")");
                    pBWebView.loadURL(sb.toString());
                    return;
                }
                try {
                    jSONObject.put("code", "9999");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("javascript:fuchuangResult(");
                sb2.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                sb2.append(")");
                pBWebView2.loadURL(sb2.toString());
            }
        }

        public static /* synthetic */ void lambda$run$1(RunnableC72063 runnableC72063) {
            if (App.hasLogined()) {
                UnicomCookieManager.addLoginCookie();
                JSInvokeHandler.this.webView.loadURL(JSInvokeHandler.this.urlInfo);
            }
        }

        public static /* synthetic */ void lambda$run$3(RunnableC72063 runnableC72063) {
            if (!App.hasLogined()) {
                JSInvokeHandler.this.webView.loadURL("javascript:callbackNoper()");
                return;
            }
            UnicomCookieManager.addLoginCookie();
            JSInvokeHandler.this.webView.loadURL("javascript:history.go(0)");
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$24 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class CallableC722824 implements Callable<JSONArray> {
            CallableC722824() {
                RunnableC72063.this = r1;
            }

            @Override // java.util.concurrent.Callable
            public JSONArray call() throws Exception {
                List<MenuEntity> allMenuList = MenuDataCenter.getInstance().getAllMenuList();
                JSONArray jSONArray = new JSONArray();
                for (MenuEntity menuEntity : MenuDataCenter.getInstance().getServiceByTag(MenuDataCenter.chagnyong)) {
                    Iterator<MenuEntity> it = allMenuList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            MenuEntity next = it.next();
                            if (TextUtils.equals(menuEntity.getMenuTitle(), next.getMenuTitle())) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("title", next.getMenuTitle());
                                jSONObject.put("itemID", next.getMenuId());
                                jSONArray.put(jSONObject);
                                break;
                            }
                        }
                    }
                }
                return jSONArray;
            }
        }

        @NBSInstrumented
        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$22 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C722622 implements Consumer<JSONArray> {
            C722622() {
                RunnableC72063.this = r1;
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(JSONArray jSONArray) throws Exception {
                if (App.hasLogined()) {
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    StringBuilder sb = new StringBuilder();
                    sb.append("javascript:setCommonUseData('");
                    sb.append(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
                    sb.append("')");
                    pBWebView.loadURL(sb.toString());
                    return;
                }
                JSInvokeHandler.this.webView.loadURL("javascript:setCommonUseData('[]')");
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$23 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C722723 implements Consumer<Throwable> {
            C722723() {
                RunnableC72063.this = r1;
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                JSInvokeHandler.this.webView.loadURL("javascript:setCommonUseData('[]')");
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$25 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C722925 implements AvoidOnResult.Callback {
            final /* synthetic */ String val$backUrl;

            C722925(String str) {
                RunnableC72063.this = r1;
                this.val$backUrl = str;
            }

            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent) {
                if (TextUtils.isEmpty(this.val$backUrl)) {
                    return;
                }
                JSInvokeHandler.this.webView.loadURL(this.val$backUrl);
                JSInvokeHandler.this.handler.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.3.25.1
                    {
                        C722925.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JSInvokeHandler.this.webView.clearHistory();
                    }
                }, 1000L);
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$26 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C723126 implements CustomDialogManager.CustomeDialogListener {
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            C723126() {
                RunnableC72063.this = r1;
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                JSInvokeHandler.this.webView.loadURL("javascript:dialogOk()");
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
                JSInvokeHandler.this.webView.loadURL("javascript:dialogCancel()");
            }
        }

        @NBSInstrumented
        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$3$27 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C723227 implements AvoidOnResult.Callback {
            C723227() {
                RunnableC72063.this = r1;
            }

            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent) {
                if (i == -1 && intent != null) {
                    String stringExtra = intent.getStringExtra("signPic");
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("code", "0");
                        jSONObject.put("data", stringExtra);
                        StringBuilder sb = new StringBuilder();
                        sb.append("onActivityResult: result0 = ");
                        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                        Log.d("JSInvokeHandler", sb.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("javascript:setSignPicture(");
                        sb2.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                        sb2.append(")");
                        JSInvokeHandler.this.webView.evaluateJavascript(sb2.toString(), null);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIUtils.toast("获取签名失败");
                        return;
                    }
                }
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("code", "1");
                    jSONObject2.put("data", "");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("onActivityResult: result1 = ");
                    sb3.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    Log.e("JSInvokeHandler", sb3.toString());
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("javascript:setSignPicture(");
                    sb4.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    sb4.append(")");
                    JSInvokeHandler.this.webView.evaluateJavascript(sb4.toString(), null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @JavascriptInterface
    public void interact(String str) {
        this.webViewHandler.post(new RunnableC72063(str));
    }

    public void youTuVideo(JSONObject jSONObject) {
        try {
            HuoTiEntity huoTiEntity = new HuoTiEntity();
            String optString = jSONObject.optString("readNumber", "");
            String optString2 = jSONObject.optString("minDuration", "1");
            String optString3 = jSONObject.optString("maxDuration", "6");
            String optString4 = jSONObject.optString("name");
            String optString5 = jSONObject.optString("url", "");
            JSONObject jSONObject2 = jSONObject.getJSONObject("formData");
            String optString6 = jSONObject2.optString("token", "");
            String optString7 = jSONObject2.optString("no", "");
            String optString8 = jSONObject2.optString("province", "");
            huoTiEntity.setReadNumber(optString);
            huoTiEntity.setMinDuration(optString2);
            huoTiEntity.setMaxDuration(optString3);
            huoTiEntity.setName(optString4);
            huoTiEntity.setUrl(optString5);
            huoTiEntity.setToken(optString6);
            huoTiEntity.setNo(optString7);
            huoTiEntity.setProvince(optString8);
            huoTiEntity.setFormData(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
            PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.RECORD_AUDIO"), new C72594(huoTiEntity));
        } catch (Exception e) {
            this.webView.loadURL("javascript:onFail('数据解析失败')");
            UIUtils.toast("数据解析错误" + e.getMessage());
            UIUtils.logE("JSInvokeHandler", "onJsAlert: " + e.getMessage());
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C72594 implements CheckRequestPermissionsListener {
        final /* synthetic */ HuoTiEntity val$entity;

        C72594(HuoTiEntity huoTiEntity) {
            JSInvokeHandler.this = r1;
            this.val$entity = huoTiEntity;
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onAllPermissionOk(Permission[] permissionArr) {
            PermissionDialog.dimissDialog();
            if (!FlymePermissionUtil.isCameraCanUse() || !FlymePermissionUtil.isHasPermission(JSInvokeHandler.this.context)) {
                UIUtils.logD("JSInvokeHandler", "onAllPermissionOk: camera = " + FlymePermissionUtil.isCameraCanUse());
                UIUtils.logD("JSInvokeHandler", "onAllPermissionOk: audio = " + FlymePermissionUtil.isHasPermission(JSInvokeHandler.this.context));
                JSInvokeHandler.this.webView.loadURL("javascript:onFail('用户取消授权')");
                UIUtils.toast("为保证您正常、安全的使用中国联通APP，需要获取您的相机权限，请您到 手机－设置－应用管理－权限管理 中开启。");
                return;
            }
            Intent intent = new Intent(JSInvokeHandler.this.context, HuoTiActivity.class);
            intent.putExtra("entity", this.val$entity);
            new AvoidOnResult(JSInvokeHandler.this.context).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.4.1
                {
                    C72594.this = this;
                }

                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent2) {
                    if (i != -1 || intent2 == null) {
                        JSInvokeHandler.this.webView.loadURL("javascript:onFail('取消上传')");
                        return;
                    }
                    String stringExtra = intent2.getStringExtra("videoUrl");
                    boolean booleanExtra = intent2.getBooleanExtra("isBackCamera", false);
                    new HuoTiUpload(JSInvokeHandler.this.context, (HuoTiEntity) intent2.getParcelableExtra("entity"), stringExtra, booleanExtra, new MyAsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.4.1.1
                        {
                            C72601.this = this;
                        }

                        @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
                        public void onStart() {
                            super.onStart();
                        }

                        @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
                        public void onSuccess(int i2, String str) {
                            super.onSuccess(i2, str);
                            UIUtils.logD("lln", "content==" + str + "----statusCode" + i2);
                            if (200 == i2) {
                                PBWebView pBWebView = JSInvokeHandler.this.webView;
                                pBWebView.loadURL("javascript:onSuccess('" + str + "')");
                                UIUtils.toast("上传成功");
                            }
                            if (413 == i2) {
                                JSInvokeHandler.this.webView.loadURL("javascript:onFail('文件过大')");
                            }
                        }

                        @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
                        public void onFailure(Throwable th, String str) {
                            super.onFailure(th, str);
                            th.printStackTrace();
                            PBWebView pBWebView = JSInvokeHandler.this.webView;
                            pBWebView.loadURL("javascript:onFail('" + th.getLocalizedMessage() + "')");
                            StringBuilder sb = new StringBuilder();
                            sb.append("上传失败");
                            sb.append(str);
                            UIUtils.toast(sb.toString());
                            UIUtils.logE("lln", "content333==" + str + "----error" + th);
                        }

                        @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
                        public void onFinish() {
                            super.onFinish();
                        }
                    }).compressionOne();
                }
            });
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onPermissionDenied(Permission[] permissionArr) {
            PermissionDialog.dimissDialog();
            JSInvokeHandler.this.webView.loadURL("javascript:onFail('用户取消授权')");
            UIUtils.toast("为保证您正常、安全的使用中国联通APP，需要获取您的相机权限，请您到 手机－设置－应用管理－权限管理 中开启。");
        }
    }

    public void upLoadZlVideoFile(JSONObject jSONObject) {
        try {
            File file = new File(this.videoPath);
            if (!file.exists()) {
                UIUtils.toast("视频不存在");
                return;
            }
            String optString = jSONObject.optString("formData");
            final String optString2 = jSONObject.optString("onSuccess", "onUploadSuccess");
            final String optString3 = jSONObject.optString("onFail", "onUploadFail");
            String optString4 = jSONObject.optString("url", "");
            String optString5 = jSONObject.optString("name", "");
            JSONObject jSONObject2 = new JSONObject(optString);
            String optString6 = jSONObject2.optString("reqEnvParams", "");
            String optString7 = jSONObject2.optString("data", "");
            HashMap hashMap = new HashMap();
            hashMap.put(optString5, file);
            hashMap.put("reqEnvParams", optString6);
            hashMap.put("data", optString7);
            MyAsyncHttpClient myAsyncHttpClient = new MyAsyncHttpClient(this.context);
            myAsyncHttpClient.setTimeout(20, 20, 20);
            myAsyncHttpClient.uploadFile(optString4, hashMap, new MyAsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.5
                {
                    JSInvokeHandler.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    UIUtils.toast("开始上传");
                }

                @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    if (200 == i) {
                        String str2 = "javascript:" + optString2 + "('" + str + "')";
                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                        if (pBWebView instanceof Object) {
                            NBSWebLoadInstrument.loadUrl((Object) pBWebView, str2);
                            return;
                        } else {
                            pBWebView.loadUrl(str2);
                            return;
                        }
                    }
                    String str3 = "javascript:" + optString3 + "('" + str + "')";
                    PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                    if (pBWebView2 instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) pBWebView2, str3);
                    } else {
                        pBWebView2.loadUrl(str3);
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    th.printStackTrace();
                    String str2 = "javascript:" + optString3 + "('" + str + "')";
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    if (pBWebView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) pBWebView, str2);
                    } else {
                        pBWebView.loadUrl(str2);
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
                public void onFinish() {
                    super.onFinish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            String str = "javascript:onUploadFail('" + e.getLocalizedMessage() + "')";
            PBWebView pBWebView = this.webView;
            if (pBWebView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
            } else {
                pBWebView.loadUrl(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$6 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class HandlerC72826 extends Handler {
        HandlerC72826() {
            JSInvokeHandler.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 101) {
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                String str = "javascript:huahenghuidiao('" + ((JSONObject) message.obj) + "')";
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                } else {
                    pBWebView.loadUrl(str);
                }
            }
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$7 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC72837 implements Runnable {
        final /* synthetic */ Map val$params;
        final /* synthetic */ String val$urlInfo;

        RunnableC72837(String str, Map map) {
            JSInvokeHandler.this = r1;
            this.val$urlInfo = str;
            this.val$params = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSInvokeHandler.this.webView.get(this.val$urlInfo, this.val$params);
        }
    }

    public void initLoginbackUrl(String str, Map<String, String> map, String str2) {
        if (TextUtils.isEmpty(str2)) {
            this.webViewHandler.post(new RunnableC72837(str, map));
        } else {
            this.webView.get(str, map);
        }
    }

    private boolean isInstallByread(String str) {
        return new File("/data/data/" + str).exists();
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$8 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class RunnableC72848 implements Runnable {
        final /* synthetic */ String val$backResult;

        RunnableC72848(String str) {
            JSInvokeHandler.this = r1;
            this.val$backResult = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TextUtils.isEmpty(this.val$backResult) || TextUtils.equals("undefined", this.val$backResult)) {
                JSInvokeHandler.this.goBack();
            } else if (!"true".equals(this.val$backResult) && !"1".equals(this.val$backResult)) {
                JSInvokeHandler.this.goBack();
            } else {
                try {
                    URL url = new URL(JSInvokeHandler.this.webView.getUrl());
                    String str = url.getHost() + url.getPath();
                    if (JSInvokeHandler.this.backUrlSet.contains(str)) {
                        JSInvokeHandler.this.closeChahao();
                    } else {
                        JSInvokeHandler.this.backUrlSet.add(str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @JavascriptInterface
    public void handleJSBack(String str) {
        this.webViewHandler.post(new RunnableC72848(str));
    }

    public void goBack() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            closeChahao();
        }
    }

    public void closeChahao() {
        JiePinEvent jiePinEvent = new JiePinEvent(EventBusUtils.EVENT_JIEPIN);
        jiePinEvent.setSecure(false);
        EventBusUtils.post(jiePinEvent);
        PBWebView pBWebView = this.webView;
        if (pBWebView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) pBWebView, "about:blank");
        } else {
            pBWebView.loadUrl("about:blank");
        }
        this.context.finish();
    }

    public void resetBackUrlSet() {
        this.backUrlSet.clear();
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$9 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class RunnableC72859 implements Runnable {
        final /* synthetic */ String val$backResult;

        RunnableC72859(String str) {
            JSInvokeHandler.this = r1;
            this.val$backResult = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TextUtils.isEmpty(this.val$backResult) || TextUtils.equals("undefined", this.val$backResult)) {
                JSInvokeHandler.this.closeChahao();
            } else if (!"true".equals(this.val$backResult) && !"1".equals(this.val$backResult)) {
                JSInvokeHandler.this.closeChahao();
            } else {
                try {
                    URL url = new URL(JSInvokeHandler.this.webView.getUrl());
                    String str = url.getHost() + url.getPath();
                    if (JSInvokeHandler.this.backUrlSet.contains(str)) {
                        JSInvokeHandler.this.closeChahao();
                    } else {
                        JSInvokeHandler.this.backUrlSet.add(str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @JavascriptInterface
    public void handleJSClose(String str) {
        this.webViewHandler.post(new RunnableC72859(str));
    }

    @JavascriptInterface
    public void handleJSTitle(String str) {
        WebTitleEvent webTitleEvent = new WebTitleEvent(EventBusUtils.EVENT_WEB_TITLE);
        webTitleEvent.setData(str);
        EventBusUtils.post(webTitleEvent);
    }

    @JavascriptInterface
    public void close() {
        this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.10
            {
                JSInvokeHandler.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JSInvokeHandler.this.context.finish();
            }
        });
    }

    @JavascriptInterface
    public void handleMenuConfig(String str) {
        UIUtils.logD("JSInvokeHandler", str);
        if ("undefined".equals(str)) {
            str = "";
        }
        EventBusUtils.post(new ShareEvent(EventBusUtils.EVENT_SHARE, str));
    }

    @JavascriptInterface
    public void handleH5data(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !"undefined".equals(str)) {
                ManagerTianyan.collectH5info(str);
                WebFragment.tycjCollectData = str;
            }
            MsLogUtil.m7979d("handleH5data", "---" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public String getContactNameByJS(String str) {
        return this.phoneDataCenter.getContactJsonData(str);
    }

    @JavascriptInterface
    public String getClientInfoByJS() {
        BaseWebFragment baseWebFragment = this.baseWebFragment;
        String str = baseWebFragment instanceof BaseWebFragment ? baseWebFragment.currentCumpAppId : "";
        if (TextUtils.isEmpty(str) || !str.contains("edop_unicom")) {
            try {
                String currentPhoneNumber = this.userManager.getCurrentPhoneNumber();
                String deviceID = DeviceHelper.getDeviceID(true);
                String string = this.context.getString(2131886969);
                String deviceOSVersion = DeviceHelper.getDeviceOSVersion();
                String valueOf = String.valueOf(DeviceHelper.getDeviceOSVersionCode());
                String localIpAddress = SystemServiceUtils.getLocalIpAddress();
                String str2 = Build.VERSION.SDK_INT >= 19 ? UIUtils.getStatusBarHeight(this.context) + "" : "0";
                JSONArray jSONArray = new JSONArray();
                for (Cookie cookie : App.getPersistentCookiesList()) {
                    if ("u_account".equals(cookie.name()) || "c_version".equals(cookie.name()) || "ecs_token".equals(cookie.name()) || "random_login".equals(cookie.name()) || "enc_acc".equals(cookie.name())) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("name", cookie.name());
                        jSONObject.put("value", cookie.value());
                        jSONObject.put("domain", cookie.domain());
                        jSONArray.put(jSONObject);
                    }
                }
                JSONObject jSONObject2 = new JSONObject();
                if ((this.baseWebFragment instanceof BaseWebFragment) && WebHostSafelyManager.Strategy_WhiteOrPass.equals(WebHostSafelyManager.getInstance().interceptEcsTokenHost(this.baseWebFragment.currentURLForPlugin))) {
                    jSONObject2.put("cookies", jSONArray);
                    jSONObject2.put("platformToken", this.preference.getString("platformToken"));
                }
                jSONObject2.put("currentPhoneNumber", currentPhoneNumber);
                jSONObject2.put("imei", deviceID);
                jSONObject2.put("isCanOneKeyLogin", ManagerOneKeyLogin.checkISCanOneKeyLogin(this.context));
                jSONObject2.put("clientVersion", string);
                jSONObject2.put("osVersion", deviceOSVersion);
                jSONObject2.put("osVersionCode", valueOf);
                jSONObject2.put("pip", localIpAddress);
                jSONObject2.put("netWay", DeviceHelper.getNETType(this.context));
                jSONObject2.put("deviceBrand", DeviceHelper.getDeviceBrand());
                jSONObject2.put("deviceModel", DeviceHelper.getDeviceModel());
                jSONObject2.put("statusBar", str2);
                if (Build.VERSION.SDK_INT >= 19) {
                    jSONObject2.put("statusBarHeight", ((UIUtils.getStatusBarHeight(this.context) * 750) / UIUtils.getScreenWidth((Activity) this.context)) + "");
                }
                jSONObject2.put("yw_code", UserManager.getInstance().getYwCodeDefault());
                jSONObject2.put("provinceCode", UserManager.getInstance().getCurrentProvinceCode());
                jSONObject2.put("cityCode", UserManager.getInstance().getCurrentCityCode());
                jSONObject2.put("locateProvinceCode", UserManager.getInstance().getLocateProvinceCode());
                jSONObject2.put("locateCityCode", UserManager.getInstance().getLocateCityCode());
                jSONObject2.put("locateCityName", App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_SELECT_KEY));
                return !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        UIUtils.toast("edop小程序不允许使用getClientInfo接口，请申请getSystemInfo或者getUserInfo接口的使用权限");
        return "";
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$1MyThread */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C1MyThread implements Runnable {
        final /* synthetic */ String val$recognitionNum;

        C1MyThread(String str) {
            JSInvokeHandler.this = r1;
            this.val$recognitionNum = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            JSInvokeHandler.this.rxPermissions.request("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.1MyThread.1
                {
                    C1MyThread.this = this;
                }

                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (bool.booleanValue()) {
                        Intent intent = new Intent(JSInvokeHandler.this.context, CameraActivity.class);
                        intent.putExtra("recognitionNum", C1MyThread.this.val$recognitionNum);
                        new AvoidOnResult(JSInvokeHandler.this.context).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.1MyThread.1.1
                            {
                                C71841.this = this;
                            }

                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                if (i == 102) {
                                    String fileToBase64 = CommonUtils.fileToBase64(intent2.getStringExtra("path"));
                                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                                    String str = "javascript:captureCallbackAndoid('" + fileToBase64 + "')";
                                    if (pBWebView instanceof Object) {
                                        NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                                    } else {
                                        pBWebView.loadUrl(str);
                                    }
                                }
                                if (i == 103) {
                                    UIUtils.toast("请检查相机权限");
                                }
                            }
                        });
                        return;
                    }
                    UIUtils.toast("请检查相机权限");
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.1MyThread.2
                {
                    C1MyThread.this = this;
                }

                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    PermissionDialog.dimissDialog();
                }
            });
        }
    }

    @JavascriptInterface
    public void openCapture(String str) {
        this.handlerOpen.post(new C1MyThread(str));
    }

    public void tel(final String str) {
        try {
            PermissionDialog.show("为您提供APP内快速联系业务人员服务，需要获取您拨打电话权限，对于您授权提供的信息，我们会竭尽全力提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermission("android.permission.CALL_PHONE", new SimplePermissionAdapter() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.11
                {
                    JSInvokeHandler.this = this;
                }

                @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                public void onPermissionOk(Permission permission) {
                    super.onPermissionOk(permission);
                    PermissionDialog.dimissDialog();
                    JSInvokeHandler.this.context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + str)));
                }

                @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                public void onPermissionDenied(Permission permission) {
                    super.onPermissionDenied(permission);
                    PermissionDialog.dimissDialog();
                    UIUtils.toastLong("为保证您正常、安全的使用中国联通APP，需要获取您的拨打电话权限，请您到 手机－设置－应用管理－权限管理 中开启。");
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
            PermissionDialog.dimissDialog();
            UIUtils.toastLong("为保证您正常、安全的使用中国联通APP，需要获取您的拨打电话权限，请您到 手机－设置－应用管理－权限管理 中开启。");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void sms(final String str) {
        try {
            PermissionDialog.show("为了第一时间将关键信息通知到您，需要获取您的短信读/写权限，对于您授权的信息我们竭尽提供安全保护");
            SoulPermission.getInstance().checkAndRequestPermission("android.permission.SEND_SMS", new SimplePermissionAdapter() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.12
                {
                    JSInvokeHandler.this = this;
                }

                @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                public void onPermissionOk(Permission permission) {
                    super.onPermissionOk(permission);
                    PermissionDialog.dimissDialog();
                    JSInvokeHandler.this.context.startActivity(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str)));
                }

                @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                public void onPermissionDenied(Permission permission) {
                    super.onPermissionDenied(permission);
                    PermissionDialog.dimissDialog();
                    UIUtils.toastLong("为保证您正常、安全的使用中国联通APP，需要获取您的发送短信权限，请您到 手机－设置－应用管理－权限管理 中开启。");
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
            PermissionDialog.dimissDialog();
            UIUtils.toastLong("为保证您正常、安全的使用中国联通APP，需要获取您的发送短信权限，请您到 手机－设置－应用管理－权限管理 中开启。");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void showTelAndSMSDialog(final String str) {
        final Dialog dialog = new Dialog(this.context, 2131952236);
        LinearLayout linearLayout = (LinearLayout) this.context.getLayoutInflater().inflate(2131493398, (ViewGroup) null);
        Button button = (Button) linearLayout.findViewById(2131298199);
        button.setText("呼叫" + str);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.13
            {
                JSInvokeHandler.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JSInvokeHandler.this.tel(str);
                dialog.cancel();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        Button button2 = (Button) linearLayout.findViewById(2131298198);
        button2.setText("发送短信");
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.14
            {
                JSInvokeHandler.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JSInvokeHandler.this.sms(str);
                dialog.cancel();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        linearLayout.findViewById(2131298197).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.15
            {
                JSInvokeHandler.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
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

    public void gotoRedirect(String str, String str2, String str3) {
        if ("true".equalsIgnoreCase(str.trim()) && str2.startsWith("LOCAL-") && !App.hasLogined()) {
            Intent intent = new Intent(this.context, LoginBindActivity.class);
            intent.putExtra("directAccess", true);
            intent.putExtra("title", str3);
            intent.putExtra("url", str2);
            this.context.startActivity(intent);
            return;
        }
        if ("LOCAL_SEARCH".equals(str2.trim())) {
            StatisticsUploadUtils.upload("41", "发现-搜索", "按钮", "0", "搜索", "");
        }
        if (IntentManager.handleLocal(this.context, str3, str2)) {
            return;
        }
        this.webView.get(str2, new HashMap());
    }

    @JavascriptInterface
    public void getGeoLocation() {
        this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.16
            {
                JSInvokeHandler.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    boolean z = true;
                    boolean z2 = ContextCompat.checkSelfPermission(JSInvokeHandler.this.context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
                    if (ContextCompat.checkSelfPermission(JSInvokeHandler.this.context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                        z = false;
                    }
                    if (z2 && z) {
                        startLocation();
                    } else if (!TextUtils.isEmpty(App.getSharePreferenceUtil().getString("GetLocationJSPlugin-hasAuth"))) {
                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                        if (pBWebView instanceof Object) {
                            NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:bridge.callbackFromNative('lbsData', '{\"status\":12,\"message\":\"用户拒绝\"}');");
                        } else {
                            pBWebView.loadUrl("javascript:bridge.callbackFromNative('lbsData', '{\"status\":12,\"message\":\"用户拒绝\"}');");
                        }
                    } else {
                        App.getSharePreferenceUtil().putString("GetLocationJSPlugin-hasAuth", "yes");
                        startLocation();
                    }
                } catch (Exception e) {
                    PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                    if (pBWebView2 instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) pBWebView2, "javascript:bridge.callbackFromNative('lbsData', '{\"status\":10,\"message\":\"程序错误\"}');");
                    } else {
                        pBWebView2.loadUrl("javascript:bridge.callbackFromNative('lbsData', '{\"status\":10,\"message\":\"程序错误\"}');");
                    }
                    e.printStackTrace();
                }
            }

            private void startLocation() {
                PermissionDialog.show("需要您授予中国联通APP位置权限，以开启与位置相关的推荐/搜索/安全保障服务。");
                new RxPermissions(JSInvokeHandler.this.context).request("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.16.1
                    {
                        RunnableC717316.this = this;
                    }

                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        PermissionDialog.dimissDialog();
                        if (!bool.booleanValue()) {
                            PBWebView pBWebView = JSInvokeHandler.this.webView;
                            if (pBWebView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:bridge.callbackFromNative('lbsData', '{\"status\":12,\"message\":\"用户拒绝\"}');");
                                return;
                            } else {
                                pBWebView.loadUrl("javascript:bridge.callbackFromNative('lbsData', '{\"status\":12,\"message\":\"用户拒绝\"}');");
                                return;
                            }
                        }
                        JSInvokeHandler.this.mLocClient = new LocationClient(JSInvokeHandler.this.context);
                        JSInvokeHandler.this.mLocClient.registerLocationListener(JSInvokeHandler.this.myListener);
                        LocationClientOption locationClientOption = new LocationClientOption();
                        locationClientOption.setOpenGps(true);
                        locationClientOption.setCoorType("gcj02");
                        locationClientOption.setScanSpan(3000);
                        locationClientOption.setIsNeedAddress(true);
                        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
                        JSInvokeHandler.this.mLocClient.setLocOption(locationClientOption);
                        JSInvokeHandler.this.mLocClient.start();
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.16.2
                    {
                        RunnableC717316.this = this;
                    }

                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        PermissionDialog.dimissDialog();
                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                        if (pBWebView instanceof Object) {
                            NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:bridge.callbackFromNative('lbsData', '{\"status\":10,\"message\":\"程序错误\"}');");
                        } else {
                            pBWebView.loadUrl("javascript:bridge.callbackFromNative('lbsData', '{\"status\":10,\"message\":\"程序错误\"}');");
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$17 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC717617 implements Runnable {
        RunnableC717617() {
            JSInvokeHandler.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                SoulPermission.getInstance().checkAndRequestPermission("android.permission.CAMERA", new CheckRequestPermissionListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.17.1
                    {
                        RunnableC717617.this = this;
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                    public void onPermissionOk(Permission permission) {
                        PermissionDialog.dimissDialog();
                        if (TextUtils.isEmpty(App.cacheImageFilePath)) {
                            PBWebView pBWebView = JSInvokeHandler.this.webView;
                            if (pBWebView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:bridge.callbackFromNative('photoData', '{\"status\":10,\"message\":\"未发现SD卡，无法进行拍照\"}');");
                                return;
                            } else {
                                pBWebView.loadUrl("javascript:bridge.callbackFromNative('photoData', '{\"status\":10,\"message\":\"未发现SD卡，无法进行拍照\"}');");
                                return;
                            }
                        }
                        File file = new File(App.cacheImageFilePath);
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        File file2 = new File(file, "localTempImg.jpg");
                        Uri fromFile = Uri.fromFile(file2);
                        intent.putExtra("orientation", 0);
                        if (Build.VERSION.SDK_INT >= 24) {
                            intent.putExtra("output", FileProvider.getUriForFile(JSInvokeHandler.this.context, "com.sinovatech.unicom.ui.fileprovider", file2));
                        } else {
                            intent.putExtra("output", fromFile);
                        }
                        new AvoidOnResult(JSInvokeHandler.this.context).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.17.1.1
                            {
                                C71771.this = this;
                            }

                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                if (i != -1) {
                                    PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                                    if (pBWebView2 instanceof Object) {
                                        NBSWebLoadInstrument.loadUrl((Object) pBWebView2, "javascript:bridge.callbackFromNative('photoData', '{\"status\":0,\"message\":\"无结果\"}');");
                                        return;
                                    } else {
                                        pBWebView2.loadUrl("javascript:bridge.callbackFromNative('photoData', '{\"status\":0,\"message\":\"无结果\"}');");
                                        return;
                                    }
                                }
                                String encodeToString = Base64.encodeToString(ImageCompressUtils.compress(App.cacheImageFilePath + File.separator + "localTempImg.jpg", 1024.0f, 1024.0f).toByteArray(), 2);
                                PBWebView pBWebView3 = JSInvokeHandler.this.webView;
                                String str = "javascript:bridge.callbackFromNative('photoData', '{\"status\":1,\"photo\":\"" + encodeToString + "\"}');";
                                if (pBWebView3 instanceof Object) {
                                    NBSWebLoadInstrument.loadUrl((Object) pBWebView3, str);
                                } else {
                                    pBWebView3.loadUrl(str);
                                }
                            }
                        });
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                    public void onPermissionDenied(Permission permission) {
                        PermissionDialog.dimissDialog();
                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                        if (pBWebView instanceof Object) {
                            NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:bridge.callbackFromNative('photoData', '{\"status\":12,\"message\":\"用户拒绝\"}');");
                        } else {
                            pBWebView.loadUrl("javascript:bridge.callbackFromNative('photoData', '{\"status\":12,\"message\":\"用户拒绝\"}');");
                        }
                    }
                });
            } catch (ActivityNotFoundException e) {
                PermissionDialog.dimissDialog();
                e.printStackTrace();
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:bridge.callbackFromNative('photoData', '{\"status\":10,\"message\":\"启动摄像头失败\"}');");
                } else {
                    pBWebView.loadUrl("javascript:bridge.callbackFromNative('photoData', '{\"status\":10,\"message\":\"启动摄像头失败\"}');");
                }
            } catch (SecurityException e2) {
                PermissionDialog.dimissDialog();
                e2.printStackTrace();
                PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                if (pBWebView2 instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView2, "javascript:bridge.callbackFromNative('photoData', '{\"status\":12,\"message\":\"用户拒绝\"}');");
                } else {
                    pBWebView2.loadUrl("javascript:bridge.callbackFromNative('photoData', '{\"status\":12,\"message\":\"用户拒绝\"}');");
                }
            } catch (Exception e3) {
                PermissionDialog.dimissDialog();
                e3.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    public void takePhoto() {
        this.webViewHandler.post(new RunnableC717617());
    }

    public void takeVideo(JSONObject jSONObject) {
        int i;
        try {
            String optString = jSONObject.optString("authorTitle", "");
            String optString2 = jSONObject.optString("authorMsg", "");
            String optString3 = jSONObject.optString("cancelTitle", "取消");
            String optString4 = jSONObject.optString("sureTitle", "去设置");
            String optString5 = jSONObject.optString("onSuccess", "");
            String optString6 = jSONObject.optString("onFail", "");
            String optString7 = jSONObject.optString("cameraSide", "");
            String optString8 = jSONObject.optString("photoQuality", "");
            try {
                i = Integer.parseInt(jSONObject.optString("maxDuration", "5"));
            } catch (Exception e) {
                e.printStackTrace();
                i = 5;
            }
            this.webViewHandler.post(new RunnableC717918(i, optString7, optString8, optString5, optString6, optString, optString2, optString3, optString4));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$18 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC717918 implements Runnable {
        final /* synthetic */ String val$authorMsg;
        final /* synthetic */ String val$authorTitle;
        final /* synthetic */ String val$cameraSide;
        final /* synthetic */ String val$cancelTitle;
        final /* synthetic */ int val$finalMaxTime;
        final /* synthetic */ String val$onFail;
        final /* synthetic */ String val$onSuccess;
        final /* synthetic */ String val$photoQuality;
        final /* synthetic */ String val$sureTitle;

        RunnableC717918(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            JSInvokeHandler.this = r1;
            this.val$finalMaxTime = i;
            this.val$cameraSide = str;
            this.val$photoQuality = str2;
            this.val$onSuccess = str3;
            this.val$onFail = str4;
            this.val$authorTitle = str5;
            this.val$authorMsg = str6;
            this.val$cancelTitle = str7;
            this.val$sureTitle = str8;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.18.1
                    {
                        RunnableC717918.this = this;
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                    public void onAllPermissionOk(Permission[] permissionArr) {
                        PermissionDialog.dimissDialog();
                        Date date = new Date(System.currentTimeMillis());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'ZL'_yyyyMMdd_HHmmss");
                        String str = simpleDateFormat.format(date) + ".mp4";
                        File file = new File(FileUtils.getVideoSaveDir(JSInvokeHandler.this.context));
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File file2 = new File(file, str);
                        JSInvokeHandler.this.videoPath = FileUtils.getVideoSaveDir(JSInvokeHandler.this.context) + str;
                        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
                        intent.putExtra("android.intent.extra.durationLimit", RunnableC717918.this.val$finalMaxTime);
                        if (Build.VERSION.SDK_INT >= 24) {
                            intent.putExtra("output", FileProvider.getUriForFile(JSInvokeHandler.this.context, "com.sinovatech.unicom.ui.fileprovider", file2));
                        } else {
                            intent.putExtra("output", Uri.fromFile(file2));
                        }
                        if (TextUtils.equals("front", RunnableC717918.this.val$cameraSide)) {
                            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                        } else {
                            intent.putExtra("android.intent.extras.CAMERA_FACING", 0);
                        }
                        if (TextUtils.equals("high", RunnableC717918.this.val$photoQuality)) {
                            intent.putExtra("android.intent.extra.videoQuality", 1);
                        } else if (TextUtils.equals("medium", RunnableC717918.this.val$photoQuality)) {
                            intent.putExtra("android.intent.extra.videoQuality", 0.5d);
                        } else if (TextUtils.equals("low", RunnableC717918.this.val$photoQuality)) {
                            intent.putExtra("android.intent.extra.videoQuality", 0.3d);
                        } else {
                            intent.putExtra("android.intent.extra.videoQuality", 0.3d);
                        }
                        new AvoidOnResult(JSInvokeHandler.this.context).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.18.1.1
                            {
                                C71801.this = this;
                            }

                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                try {
                                    if (i != -1) {
                                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                                        String str2 = "javascript:" + RunnableC717918.this.val$onFail + "('用户取消')";
                                        if (pBWebView instanceof Object) {
                                            NBSWebLoadInstrument.loadUrl((Object) pBWebView, str2);
                                        } else {
                                            pBWebView.loadUrl(str2);
                                        }
                                    } else {
                                        String str3 = "javascript:" + RunnableC717918.this.val$onSuccess + "('')";
                                        UIUtils.logD("JSInvokeHandler", "onActivityResult: js = " + str3);
                                        PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                                        if (pBWebView2 instanceof Object) {
                                            NBSWebLoadInstrument.loadUrl((Object) pBWebView2, str3);
                                        } else {
                                            pBWebView2.loadUrl(str3);
                                        }
                                    }
                                } catch (Exception unused) {
                                }
                            }
                        });
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                    public void onPermissionDenied(Permission[] permissionArr) {
                        PermissionDialog.dimissDialog();
                        JSInvokeHandler.this.showTakeVideoDialog(RunnableC717918.this.val$authorTitle, RunnableC717918.this.val$authorMsg, RunnableC717918.this.val$cancelTitle, RunnableC717918.this.val$sureTitle);
                    }
                });
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                String str = "javascript:" + this.val$onFail + "('启动摄像头失败')";
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                } else {
                    pBWebView.loadUrl(str);
                }
            } catch (SecurityException e2) {
                e2.printStackTrace();
                PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                String str2 = "javascript:" + this.val$onFail + "('用户拒绝')";
                if (pBWebView2 instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView2, str2);
                } else {
                    pBWebView2.loadUrl(str2);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void showTakeVideoDialog(String str, String str2, String str3, String str4) {
        CustomDialogManager.show((Activity) this.context, str, str2, true, str3, str4, false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.19
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            {
                JSInvokeHandler.this = this;
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.19.1
                    @Override // com.p284qw.soul.permission.callbcak.GoAppDetailCallBack
                    public void onBackFromAppDetail(Intent intent) {
                    }

                    {
                        C718219.this = this;
                    }
                });
            }
        });
    }

    @JavascriptInterface
    public void takePhoto(String str) {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("msg");
            String optString = optJSONObject.optString("authorTitle", "");
            String optString2 = optJSONObject.optString("authorMsg", "");
            String optString3 = optJSONObject.optString("cancelTitle", "");
            String optString4 = optJSONObject.optString("sureTitle", "");
            String optString5 = optJSONObject.optString("onSuccess", "");
            optJSONObject.optString("onFail", "");
            this.webViewHandler.post(new RunnableC718820(optString5, optJSONObject.optString("cameraSide", ""), optJSONObject.optString("photoQuality", ""), optString, optString2, optString3, optString4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$20 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC718820 implements Runnable {
        final /* synthetic */ String val$authorMsg;
        final /* synthetic */ String val$authorTitle;
        final /* synthetic */ String val$cameraSide;
        final /* synthetic */ String val$cancelTitle;
        final /* synthetic */ String val$onSuccess;
        final /* synthetic */ String val$photoQuality;
        final /* synthetic */ String val$sureTitle;

        RunnableC718820(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            JSInvokeHandler.this = r1;
            this.val$onSuccess = str;
            this.val$cameraSide = str2;
            this.val$photoQuality = str3;
            this.val$authorTitle = str4;
            this.val$authorMsg = str5;
            this.val$cancelTitle = str6;
            this.val$sureTitle = str7;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"), new C71891());
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                String str = "javascript:" + this.val$onSuccess + "('photoData', '{\"status\":10,\"message\":\"启动摄像头失败\"}');";
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                } else {
                    pBWebView.loadUrl(str);
                }
            } catch (SecurityException e2) {
                e2.printStackTrace();
                PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                String str2 = "javascript:" + this.val$onSuccess + "('photoData', '{\"status\":12,\"message\":\"用户拒绝\"}');";
                if (pBWebView2 instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView2, str2);
                } else {
                    pBWebView2.loadUrl(str2);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NBSInstrumented
        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$20$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C71891 implements CheckRequestPermissionsListener {
            C71891() {
                RunnableC718820.this = r1;
            }

            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onAllPermissionOk(Permission[] permissionArr) {
                PermissionDialog.dimissDialog();
                if (TextUtils.isEmpty(App.cacheImageFilePath)) {
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    String str = "javascript:" + RunnableC718820.this.val$onSuccess + "('photoData', '{\"status\":10,\"message\":\"未发现SD卡，无法进行拍照\"}');";
                    if (pBWebView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                        return;
                    } else {
                        pBWebView.loadUrl(str);
                        return;
                    }
                }
                File file = new File(App.cacheImageFilePath);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File file2 = new File(file, "localTempImg.jpg");
                Uri fromFile = Uri.fromFile(file2);
                intent.putExtra("orientation", 0);
                if (TextUtils.equals("front", RunnableC718820.this.val$cameraSide)) {
                    intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                } else {
                    intent.putExtra("android.intent.extras.CAMERA_FACING", 0);
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    intent.putExtra("output", FileProvider.getUriForFile(JSInvokeHandler.this.context, "com.sinovatech.unicom.ui.fileprovider", file2));
                } else {
                    intent.putExtra("output", fromFile);
                }
                new AvoidOnResult(JSInvokeHandler.this.context).startForResult(intent, new C71901());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @NBSInstrumented
            /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$20$1$1 */
            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            public class C71901 implements AvoidOnResult.Callback {
                C71901() {
                    C71891.this = r1;
                }

                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    try {
                        if (i != -1) {
                            PBWebView pBWebView = JSInvokeHandler.this.webView;
                            String str = "javascript:" + RunnableC718820.this.val$onSuccess + "('photoData', '{\"status\":0,\"message\":\"无结果\"}');";
                            if (pBWebView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                            } else {
                                pBWebView.loadUrl(str);
                            }
                        } else {
                            new Thread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.20.1.1.1
                                {
                                    C71901.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    final String str2 = "";
                                    if (TextUtils.equals("high", RunnableC718820.this.val$photoQuality)) {
                                        str2 = Base64.encodeToString(ImageCompressUtils.compress(App.cacheImageFilePath + File.separator + "localTempImg.jpg", 0.8f).toByteArray(), 2);
                                    } else if (TextUtils.equals("medium", RunnableC718820.this.val$photoQuality)) {
                                        str2 = Base64.encodeToString(ImageCompressUtils.compress(App.cacheImageFilePath + File.separator + "localTempImg.jpg", 0.5f).toByteArray(), 2);
                                    } else if (TextUtils.equals("low", RunnableC718820.this.val$photoQuality)) {
                                        str2 = Base64.encodeToString(ImageCompressUtils.compress(App.cacheImageFilePath + File.separator + "localTempImg.jpg", 0.2f).toByteArray(), 2);
                                    }
                                    JSInvokeHandler.this.context.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.20.1.1.1.1
                                        {
                                            RunnableC71911.this = this;
                                        }

                                        @Override // java.lang.Runnable
                                        public void run() {
                                            PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                                            String str3 = "javascript:" + RunnableC718820.this.val$onSuccess + "('photoData', '{\"status\":1,\"photo\":\"" + str2 + "\"}');";
                                            if (pBWebView2 instanceof Object) {
                                                NBSWebLoadInstrument.loadUrl((Object) pBWebView2, str3);
                                            } else {
                                                pBWebView2.loadUrl(str3);
                                            }
                                        }
                                    });
                                }
                            }).start();
                        }
                    } catch (Exception unused) {
                    }
                }
            }

            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onPermissionDenied(Permission[] permissionArr) {
                PermissionDialog.dimissDialog();
                JSInvokeHandler.this.showTakePhotoDialog(RunnableC718820.this.val$onSuccess, RunnableC718820.this.val$authorTitle, RunnableC718820.this.val$authorMsg, RunnableC718820.this.val$cancelTitle, RunnableC718820.this.val$sureTitle);
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                String str = "javascript:" + RunnableC718820.this.val$onSuccess + "('photoData', '{\"status\":12,\"message\":\"未授权\"}');";
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                } else {
                    pBWebView.loadUrl(str);
                }
            }
        }
    }

    public void showTakePhotoDialog(final String str, String str2, String str3, String str4, String str5) {
        CustomDialogManager.show((Activity) this.context, str2, str3, true, str4, str5, false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.21
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            {
                JSInvokeHandler.this = this;
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.21.1
                    @Override // com.p284qw.soul.permission.callbcak.GoAppDetailCallBack
                    public void onBackFromAppDetail(Intent intent) {
                    }

                    {
                        C719321.this = this;
                    }
                });
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                String str6 = "javascript:" + str + "('photoData', '{\"status\":12,\"message\":\"用户拒绝相机授权\"}');";
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str6);
                } else {
                    pBWebView.loadUrl(str6);
                }
            }
        });
    }

    public void loginHttp(final RequestParams requestParams) {
        String loginURL = URLSet.getLoginURL();
        final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(this.context);
        customePorgressDialog.setMessage("正在登录 请稍候");
        customePorgressDialog.setCanceledOnTouchOutside(false);
        customePorgressDialog.setCancelable(false);
        String str = LoginActivity.idCardBoradLogin;
        if (TextUtils.isEmpty(str)) {
            str = UserManager.getInstance().getIdCardKey();
        }
        requestParams.put("sig", ConfigManager.getIdCardKey(str));
        requestParams.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
        MsLogUtil.m7979d("LoginActivityBraod", "登录接口拼接sig:" + ConfigManager.getIdCardKey(str));
        if (!TextUtils.isEmpty(LoginFilterUtil.resultToken)) {
            requestParams.put("resultToken", LoginFilterUtil.resultToken);
        }
        LoginParamsEntity headerAndBody = DongtaiMiyaoUtils.getHeaderAndBody("0", requestParams.getRealParams());
        App.getAsyncHttpClient(20, 20, 20).post(loginURL, headerAndBody.getBodyMap(), headerAndBody.getHeaderMap(), new AsyncHttpResponseHandler(1) { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.22
            {
                JSInvokeHandler.this = this;
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                App.setLogined(LoginStateConst.DOING_NORMAL_LOGIN);
                customePorgressDialog.setMessage("正在登录 请稍候");
                try {
                    if (JSInvokeHandler.this.context == null || JSInvokeHandler.this.context.isFinishing() || customePorgressDialog == null) {
                        return;
                    }
                    customePorgressDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:182:0x0292 A[Catch: Exception -> 0x0298, TRY_LEAVE, TryCatch #3 {Exception -> 0x0298, blocks: (B:180:0x0286, B:182:0x0292), top: B:197:0x0286 }] */
            /* JADX WARN: Removed duplicated region for block: B:188:0x02ab  */
            /* JADX WARN: Removed duplicated region for block: B:189:0x02e6  */
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onSuccess(int r13, java.lang.String r14) {
                /*
                    Method dump skipped, instructions count: 775
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.JSInvokeHandler.C719522.onSuccess(int, java.lang.String):void");
            }

            @NBSInstrumented
            /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$22$2 */
            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            public class C71972 implements CustomDialogManager.CustomeDialogListener {
                final /* synthetic */ JSONObject val$jo;

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onBackKeyDown() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onShow() {
                }

                C71972(JSONObject jSONObject) {
                    C719522.this = r1;
                    this.val$jo = jSONObject;
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickOk() {
                    String optString = this.val$jo.optString("sigCode");
                    MsLogUtil.m7979d("LoginActivityBraod", "Face0001的sigCode:" + optString);
                    try {
                        requestParams.put("password", URLDecoder.decode(optString, "UTF-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    JSInvokeHandler jSInvokeHandler = JSInvokeHandler.this;
                    final CustomePorgressDialog customePorgressDialog = customePorgressDialog;
                    final RequestParams requestParams = requestParams;
                    jSInvokeHandler.starActivieLive(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.-$$Lambda$JSInvokeHandler$22$2$XHoxqiaLTBNKFAfE6uEg3IweQv8
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            JSInvokeHandler.C719522.C71972.lambda$onClickOk$1(JSInvokeHandler.C719522.C71972.this, customePorgressDialog, requestParams, (String) obj);
                        }
                    });
                }

                public static /* synthetic */ void lambda$onClickOk$1(C71972 c71972, final CustomePorgressDialog customePorgressDialog, final RequestParams requestParams, String str) throws Exception {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("resultCode", "0");
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("image_best", str);
                    jSONObject.put("resultInfo", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    String str2 = LoginActivity.idCardBoradLoginJiami;
                    String idCardKeyJiaMi = TextUtils.isEmpty(str2) ? UserManager.getInstance().getIdCardKeyJiaMi() : str2;
                    String str3 = LoginActivity.idCardBoradLogin;
                    if (TextUtils.isEmpty(str3)) {
                        str3 = UserManager.getInstance().getIdCardKey();
                    }
                    String idcardKeyIsFirst = ConfigManager.getIdcardKeyIsFirst(str3);
                    try {
                        if (UIUtils.isShowDialog(JSInvokeHandler.this.context, customePorgressDialog)) {
                            customePorgressDialog.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    JSInvokeHandler.this.broadCompare(idCardKeyJiaMi, requestParams.get("mobile"), requestParams.get("areaCode"), !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), idcardKeyIsFirst, new Consumer() { // from class: com.sinovatech.unicom.basic.ui.-$$Lambda$JSInvokeHandler$22$2$BC6faA5c0-xZzzE7dLb71_eqLYI
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            JSInvokeHandler.C719522.C71972.lambda$onClickOk$0(JSInvokeHandler.C719522.C71972.this, customePorgressDialog, requestParams, (String) obj);
                        }
                    });
                }

                public static /* synthetic */ void lambda$onClickOk$0(C71972 c71972, CustomePorgressDialog customePorgressDialog, RequestParams requestParams, String str) throws Exception {
                    MsLogUtil.m7979d("LoginActivityBraod", "人脸对比返回结果" + str);
                    if (UIUtils.isDismissDialog(JSInvokeHandler.this.context, customePorgressDialog)) {
                        customePorgressDialog.dismiss();
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String optString = jSONObject.optString("code");
                        String optString2 = jSONObject.optString("msg");
                        if ("0000".equals(optString)) {
                            JSInvokeHandler.this.loginHttp(requestParams);
                        } else {
                            CustomDialogManager.show(JSInvokeHandler.this.context, "温馨提示", optString2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CustomDialogManager.show(JSInvokeHandler.this.context, "温馨提示", "人脸对比失败");
                    }
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickCancel() {
                    JSInvokeHandler.this.context.finish();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str2) {
                super.onFailure(th, str2);
                th.printStackTrace();
                App.setLogined(LoginStateConst.UNLOGIN);
                MsLogUtil.m7979d("loginHttp", th.getMessage() + "----" + str2);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                try {
                    if (UIUtils.isDismissDialog(JSInvokeHandler.this.context, customePorgressDialog)) {
                        customePorgressDialog.dismiss();
                    }
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                }
            }
        });
    }

    public void showFreezeDialog(String str, String str2, String str3) {
        CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "号码已注销", str, false, "", "立即激活", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.23
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            {
                JSInvokeHandler.this = this;
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "03");
                IntentManager.gotoWebViewActivityWithParams(JSInvokeHandler.this.context, URLSet.getFreezeHtml(), "", hashMap);
            }
        });
    }

    public void starActivieLive(Consumer<String> consumer) {
        PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA"), new C719924(consumer));
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$24 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C719924 implements CheckRequestPermissionsListener {
        final /* synthetic */ Consumer val$consumer;

        C719924(Consumer consumer) {
            JSInvokeHandler.this = r1;
            this.val$consumer = consumer;
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onAllPermissionOk(Permission[] permissionArr) {
            String str;
            PermissionDialog.dimissDialog();
            String str2 = "";
            try {
                if (JSInvokeHandler.this.webView != null && !TextUtils.isEmpty(JSInvokeHandler.this.webView.getUrl())) {
                    str2 = JSInvokeHandler.this.webView.getUrl();
                }
                str = str2;
            } catch (Exception e) {
                MsLogUtil.m7978e("SDK调用软电话获取参数异常：" + e.getMessage());
                str = "";
            }
            TYCJBoxManager.getInstance().collectClickSdk(JSInvokeHandler.this.context, "S2ndpage1214", "登录", "人脸识别", str, "com.megvii.kas.livenessdetection", "1");
            AvoidOnResult avoidOnResult = new AvoidOnResult(JSInvokeHandler.this.context);
            Intent intent = new Intent(JSInvokeHandler.this.context, LoginFaceLivenessActivity.class);
            final Consumer consumer = this.val$consumer;
            avoidOnResult.startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.-$$Lambda$JSInvokeHandler$24$IQwnw2CsMS3iGqdvBoyxn2ZR0XI
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public final void onActivityResult(int i, Intent intent2) {
                    JSInvokeHandler.C719924.lambda$onAllPermissionOk$0(Consumer.this, i, intent2);
                }
            });
        }

        public static /* synthetic */ void lambda$onAllPermissionOk$0(Consumer consumer, int i, Intent intent) {
            if (i == -1) {
                try {
                    consumer.accept(new JSONObject(intent.getStringExtra("result")).optString("image_best"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onPermissionDenied(Permission[] permissionArr) {
            try {
                PermissionDialog.dimissDialog();
                if (permissionArr.length <= 0 || !TextUtils.equals("android.permission.CAMERA", permissionArr[0].permissionName)) {
                    return;
                }
                UIUtils.toast("需要开启摄像头权限");
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
                UIUtils.toast("需要开启摄像头权限");
            }
        }
    }

    public void broadCompare(String str, String str2, String str3, String str4, String str5, Consumer<String> consumer) {
        HashMap hashMap = new HashMap();
        hashMap.put("idCard", str);
        try {
            hashMap.put("mobile", URLEncoder.encode(str2, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        hashMap.put("cityCode", str3);
        hashMap.put("data", str4);
        hashMap.put("firstLogin", str5);
        hashMap.put("version", this.context.getString(2131886969));
        MsLogUtil.m7979d("LoginActivityBraod", "开始人脸对比");
        App.getAsyncHttpClient().rxPost(URLSet.getBroadCompare(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map($$Lambda$JSInvokeHandler$kgZSNfYOSM8wqZtR8La6t3Sjx0.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new $$Lambda$JSInvokeHandler$hQW6D4_KIxKmaxJnRn54pg6oeLY(consumer), new $$Lambda$JSInvokeHandler$KN88wQtbAvRGxMg46ik2bLKr4Lk(consumer));
    }

    public static /* synthetic */ void lambda$broadCompare$2(Consumer consumer, Throwable th) throws Exception {
        th.printStackTrace();
        consumer.accept("");
    }

    public void handleTuxingma(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("placehoderText");
            String optString3 = jSONObject.optString("dsc");
            String optString4 = jSONObject.optString("imageContent");
            final String optString5 = jSONObject.optString("imageID");
            if (!TextUtils.isEmpty(optString3)) {
                UIUtils.toast(optString3);
            }
            if ("7".equals(optString)) {
                final KuandaiDialogManager kuandaiDialogManager = new KuandaiDialogManager(this.context);
                kuandaiDialogManager.show(optString2, FileTools.stringToBitmap(optString4), new KuandaiDialogManager.KuandaiDialogListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.25
                    @Override // com.sinovatech.unicom.basic.p315ui.KuandaiDialogManager.KuandaiDialogListener
                    public void onBackKeyDown() {
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.KuandaiDialogManager.KuandaiDialogListener
                    public void onCancel() {
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.KuandaiDialogManager.KuandaiDialogListener
                    public void onClickCancel() {
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.KuandaiDialogManager.KuandaiDialogListener
                    public void onShow() {
                    }

                    {
                        JSInvokeHandler.this = this;
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.KuandaiDialogManager.KuandaiDialogListener
                    public void onClickOk() {
                        JSInvokeHandler.this.requestParams.put("code", "7");
                        JSInvokeHandler.this.requestParams.put("userContent", kuandaiDialogManager.getCode());
                        JSInvokeHandler.this.requestParams.put("imageID", optString5);
                        JSInvokeHandler jSInvokeHandler = JSInvokeHandler.this;
                        jSInvokeHandler.loginHttp(jSInvokeHandler.requestParams);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$MyLocationListenner */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyLocationListenner implements BDLocationListener {
        private MyLocationListenner() {
            JSInvokeHandler.this = r1;
        }

        @Override // com.baidu.location.BDLocationListener
        public void onReceiveLocation(BDLocation bDLocation) {
            if (JSInvokeHandler.this.mLocClient != null) {
                JSInvokeHandler.this.mLocClient.stop();
            }
            if (bDLocation == null) {
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, "javascript:bridge.callbackFromNative('lbsData', '{\"status\":0,\"message\":\"无结果\"}');");
                } else {
                    pBWebView.loadUrl("javascript:bridge.callbackFromNative('lbsData', '{\"status\":0,\"message\":\"无结果\"}');");
                }
            } else if (bDLocation.getLatitude() < 10.0d) {
                PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                if (pBWebView2 instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView2, "javascript:bridge.callbackFromNative('lbsData', '{\"status\":0,\"message\":\"无结果\"}')");
                } else {
                    pBWebView2.loadUrl("javascript:bridge.callbackFromNative('lbsData', '{\"status\":0,\"message\":\"无结果\"}')");
                }
            } else if (bDLocation.getLatitude() == 0.0d || bDLocation.getLongitude() == 0.0d) {
                PBWebView pBWebView3 = JSInvokeHandler.this.webView;
                if (pBWebView3 instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView3, "javascript:bridge.callbackFromNative('lbsData', '{\"status\":0,\"message\":\"无结果\"}')");
                } else {
                    pBWebView3.loadUrl("javascript:bridge.callbackFromNative('lbsData', '{\"status\":0,\"message\":\"无结果\"}')");
                }
            } else {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("status", 1);
                    jSONObject.put("longitude", String.valueOf(bDLocation.getLongitude()));
                    jSONObject.put("latitude", String.valueOf(bDLocation.getLatitude()));
                    jSONObject.put("city", bDLocation.getCity());
                    jSONObject.put("province", bDLocation.getProvince());
                    if (JSInvokeHandler.this.mLocClient != null) {
                        JSInvokeHandler.this.mLocClient.stop();
                    }
                    PBWebView pBWebView4 = JSInvokeHandler.this.webView;
                    StringBuilder sb = new StringBuilder();
                    sb.append("javascript:bridge.callbackFromNative('lbsData', '");
                    sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                    sb.append("')");
                    String sb2 = sb.toString();
                    if (pBWebView4 instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) pBWebView4, sb2);
                    } else {
                        pBWebView4.loadUrl(sb2);
                    }
                } catch (Exception e) {
                    PBWebView pBWebView5 = JSInvokeHandler.this.webView;
                    if (pBWebView5 instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) pBWebView5, "javascript:bridge.callbackFromNative('lbsData', '{\"status\":5,\"message\":\"JSON_EXCEPTION\"}')");
                    } else {
                        pBWebView5.loadUrl("javascript:bridge.callbackFromNative('lbsData', '{\"status\":5,\"message\":\"JSON_EXCEPTION\"}')");
                    }
                    e.printStackTrace();
                }
            }
        }
    }

    @JavascriptInterface
    public void getHWService(final String str, final String str2) {
        OEMInfoEntity infoEntity = App.getInfoEntity();
        if (infoEntity == null) {
            infoEntity = new OEMInfoEntity();
        }
        final String pakeageName = infoEntity.getPakeageName();
        if ("yes".equalsIgnoreCase(infoEntity.getIshuawei())) {
            this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.26
                {
                    JSInvokeHandler.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    if (str.equals("connectHWService")) {
                        JSInvokeHandler.this.connectHWService();
                    } else if (str.equals("getMultiSimInfo")) {
                        JSInvokeHandler.this.getMultiSimInfo();
                    } else if (str.equals("getDownloadEsim")) {
                        String str3 = "";
                        if (!TextUtils.isEmpty(str2)) {
                            try {
                                str3 = new JSONObject(str2).optString("arg1");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        JSInvokeHandler.this.getDownloadEsim(str3);
                    } else if (str.equals("disconnectHWService")) {
                        JSInvokeHandler.this.disconnectHWService();
                    } else if (str.equals("openHWWatchConnection")) {
                        JSInvokeHandler.this.openHWWatchConnection();
                    }
                }
            });
        } else {
            this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.27
                {
                    JSInvokeHandler.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    if (str.equals("connectHWService")) {
                        JSInvokeHandler.this.bindService(pakeageName);
                    } else if (str.equals("getMultiSimInfo")) {
                        JSInvokeHandler.this.getEUICCInfo();
                    } else if (str.equals("getDownloadEsim")) {
                        String str3 = "";
                        if (!TextUtils.isEmpty(str2)) {
                            try {
                                str3 = new JSONObject(str2).optString("arg1");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        JSInvokeHandler.this.getDownloadEUICC(str3);
                    } else if (str.equals("disconnectHWService")) {
                        JSInvokeHandler.this.disconnectEUICCervice();
                    } else if (str.equals("openHWWatchConnection")) {
                        JSInvokeHandler.this.openHWWatchConnection();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$28 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC720328 implements ServiceConnection {
        ServiceConnectionC720328() {
            JSInvokeHandler.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean z;
            try {
                JSInvokeHandler.this.iServiceBinder = IServiceBinder.Stub.asInterface(iBinder);
                JSInvokeHandler.this.iOpenMultiSim = IOpenEUICC.Stub.asInterface(JSInvokeHandler.this.iServiceBinder.getServiceBinder("com.sinovatech.unicom.ui"));
                JSInvokeHandler.this.iOpenMultiSim.registerCallback(JSInvokeHandler.this.mEuiccCallback);
                z = true;
                UIUtils.logD("onServiceConnected", "通用手表连接成功true");
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
            if (z) {
                JSInvokeHandler.this.webView.loadURL("javascript:backConnectResult('1')");
            } else {
                JSInvokeHandler.this.webView.loadURL("javascript:backConnectResult('0')");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                try {
                    JSInvokeHandler.this.iOpenMultiSim.unRegisterCallback(JSInvokeHandler.this.mEuiccCallback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                JSInvokeHandler.this.iOpenMultiSim = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$29 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BinderC720429 extends IOpenEUICCCalbcak.Stub {
        BinderC720429() {
            JSInvokeHandler.this = r1;
        }

        @Override // com.euicc.server.model.IOpenEUICCCalbcak
        public void getDeviceEUICCInfo(EUICCDeviceInfo eUICCDeviceInfo) throws RemoteException {
            final JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("ResultCode", eUICCDeviceInfo.getResultCode());
                jSONObject.put("DeviceType", eUICCDeviceInfo.getDeviceType());
                jSONObject.put("DeviceID", eUICCDeviceInfo.getDeviceID());
                jSONObject.put("DeviceIMEI", eUICCDeviceInfo.getDeviceIMEI());
                jSONObject.put("DeviceSerialNumber", eUICCDeviceInfo.getDeviceSerialNumber());
                jSONObject.put("ProductName", eUICCDeviceInfo.getProductName());
                jSONObject.put("EID", eUICCDeviceInfo.getEID());
                if (eUICCDeviceInfo.getDeviceType() == 1) {
                    List<EUICCInfo> simInfoList = eUICCDeviceInfo.getSimInfoList();
                    if (simInfoList != null && simInfoList.size() > 0) {
                        jSONObject.put("IMSI", simInfoList.get(0).getIMSI());
                        jSONObject.put("ICCID", simInfoList.get(0).getICCID());
                    } else {
                        jSONObject.put("IMSI", "");
                        jSONObject.put("ICCID", "");
                    }
                } else if (eUICCDeviceInfo.getDeviceType() == 2) {
                    jSONObject.put("IMSI", eUICCDeviceInfo.getActiveSimProfileInfo().getIMSI());
                    jSONObject.put("ICCID", eUICCDeviceInfo.getActiveSimProfileInfo().getICCID());
                } else {
                    jSONObject.put("IMSI", "");
                    jSONObject.put("ICCID", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.29.1
                {
                    BinderC720429.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    StringBuilder sb = new StringBuilder();
                    sb.append("javascript:backMultiSimInfo(");
                    JSONObject jSONObject2 = jSONObject;
                    sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    sb.append(")");
                    pBWebView.loadURL(sb.toString());
                }
            });
            JSInvokeHandler.this.disconnectEUICCervice();
        }
    }

    public void bindService(String str) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, "com.euicc.server.IRemoteService"));
            if (this.context.bindService(intent, this.conn, 1)) {
                return;
            }
            this.webView.loadURL("javascript:backConnectResult('0')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getEUICCInfo() {
        try {
            this.iOpenMultiSim.getAttachedDeviceEUICCInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDownloadEUICC(String str) {
        try {
            try {
                UIUtils.logD("onServiceConnected", "通用手表开始下载");
                this.iOpenMultiSim.downloadEUICCProfile(str);
                StatisticsUploadUtils.uoloadPVWatch("common", "通用手表下载成功");
                this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.30
                    {
                        JSInvokeHandler.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        UIUtils.toastCenterLong("开始下载卡数据");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.31
                    {
                        JSInvokeHandler.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        UIUtils.toastCenterLong("卡数据下载异常");
                    }
                });
                StatisticsUploadUtils.uoloadPVWatch("common", "通用手表下载失败");
            }
        } finally {
            disconnectEUICCervice();
        }
    }

    @JavascriptInterface
    public void getDownloadEsimCommon(final String str) {
        try {
            try {
                this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.32
                    {
                        JSInvokeHandler.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        UIUtils.toastCenterLong("开始下载卡数据");
                    }
                });
                StatisticsUploadUtils.uoloadPVWatch("common", "通用手表下载开始");
                OEMInfoEntity infoEntity = App.getInfoEntity();
                if (infoEntity == null) {
                    infoEntity = new OEMInfoEntity();
                }
                bindService(infoEntity.getPakeageName());
                this.webViewHandler.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.33
                    {
                        JSInvokeHandler.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            JSInvokeHandler.this.getDownloadEUICC(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            UIUtils.logD("onServiceConnected", "通用手表断开连接");
        }
    }

    public void disconnectEUICCervice() {
        try {
            this.iOpenMultiSim.unRegisterCallback(this.mEuiccCallback);
            this.context.unbindService(this.conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$34 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BinderC724834 extends IOpenMultiSimCalbcak.Stub {
        BinderC724834() {
            JSInvokeHandler.this = r1;
        }

        @Override // com.huawei.multisimservice.model.IOpenMultiSimCalbcak
        public void getDeviceMultiSimInfo(MultiSimDeviceInfo multiSimDeviceInfo) {
            final JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("ResultCode", multiSimDeviceInfo.getResultCode());
                jSONObject.put("DeviceType", multiSimDeviceInfo.getDeviceType());
                jSONObject.put("DeviceID", multiSimDeviceInfo.getDeviceID());
                jSONObject.put("DeviceIMEI", multiSimDeviceInfo.getDeviceIMEI());
                jSONObject.put("DeviceSerialNumber", multiSimDeviceInfo.getDeviceSerialNumber());
                jSONObject.put("ProductName", multiSimDeviceInfo.getProductName());
                jSONObject.put("EID", multiSimDeviceInfo.getEID());
                if (multiSimDeviceInfo.getDeviceType() == 1) {
                    List<SimInfo> simInfoList = multiSimDeviceInfo.getSimInfoList();
                    if (simInfoList != null && simInfoList.size() > 0) {
                        jSONObject.put("IMSI", simInfoList.get(0).getIMSI());
                        jSONObject.put("ICCID", simInfoList.get(0).getICCID());
                    } else {
                        jSONObject.put("IMSI", "");
                        jSONObject.put("ICCID", "");
                    }
                } else if (multiSimDeviceInfo.getDeviceType() == 2) {
                    jSONObject.put("IMSI", multiSimDeviceInfo.getActiveSimProfileInfo().getIMSI());
                    jSONObject.put("ICCID", multiSimDeviceInfo.getActiveSimProfileInfo().getICCID());
                } else {
                    jSONObject.put("IMSI", "");
                    jSONObject.put("ICCID", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.34.1
                {
                    BinderC724834.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    StringBuilder sb = new StringBuilder();
                    sb.append("javascript:backMultiSimInfo(");
                    JSONObject jSONObject2 = jSONObject;
                    sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    sb.append(")");
                    pBWebView.loadURL(sb.toString());
                }
            });
            JSInvokeHandler.this.disconnectHWService();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$35 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC725035 implements ServiceConnection {
        ServiceConnectionC725035() {
            JSInvokeHandler.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean z;
            try {
                com.huawei.multisimservice.IServiceBinder asInterface = IServiceBinder.Stub.asInterface(iBinder);
                JSInvokeHandler.this.mService = IOpenMultiSim.Stub.asInterface(asInterface.getServiceBinder(JSInvokeHandler.this.context.getPackageName()));
                JSInvokeHandler.this.mService.registerCallback(JSInvokeHandler.this.mCallback);
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
            if (z) {
                JSInvokeHandler.this.webView.loadURL("javascript:backConnectResult('1')");
            } else {
                JSInvokeHandler.this.webView.loadURL("javascript:backConnectResult('0')");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JSInvokeHandler.this.mService = null;
        }
    }

    public void connectHWService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BONE_PACKAGE_NAME, CLASS_NAME_MULTI_SIM_SERVICE));
        intent.putExtras(new Bundle());
        if (this.context.bindService(intent, this.mConnection, 1)) {
            return;
        }
        this.webView.loadURL("javascript:backConnectResult('0')");
    }

    public void getMultiSimInfo() {
        try {
            this.mService.getAttachedDeviceMultiSimInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDownloadEsim(String str) {
        try {
            try {
                this.mService.downloadESimProfile(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            disconnectHWService();
        }
    }

    public void disconnectHWService() {
        try {
            this.mService.unRegisterCallback(this.mCallback);
            this.context.unbindService(this.mConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openHWWatchConnection() {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setPackage(BONE_PACKAGE_NAME);
        intent.setAction("com.huawei.bone.ADD_MULTI_SIM_DEVICE");
        this.context.startActivity(intent);
    }

    @JavascriptInterface
    public void getHWService2(final String str, final String str2) {
        this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.36
            {
                JSInvokeHandler.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                if (str.equals("connectHWService")) {
                    try {
                        JSInvokeHandler.this.linkCode = Integer.parseInt(str2);
                    } catch (Exception unused) {
                        if ("1".equals(str2)) {
                            JSInvokeHandler.this.linkCode = 1;
                        } else if ("2".equals(str2)) {
                            JSInvokeHandler.this.linkCode = 2;
                        }
                    }
                    JSInvokeHandler jSInvokeHandler = JSInvokeHandler.this;
                    jSInvokeHandler.connectHWService2(jSInvokeHandler.linkCode);
                } else if (str.equals("getMultiSimInfo")) {
                    try {
                        JSInvokeHandler.this.linkCode = Integer.parseInt(str2);
                    } catch (Exception unused2) {
                        if ("1".equals(str2)) {
                            JSInvokeHandler.this.linkCode = 1;
                        } else if ("2".equals(str2)) {
                            JSInvokeHandler.this.linkCode = 2;
                        }
                    }
                    JSInvokeHandler.this.getMultiSimInfo2();
                } else if (str.equals("getDownloadEsim")) {
                    try {
                        JSInvokeHandler.this.linkCode = Integer.parseInt(str2);
                    } catch (Exception unused3) {
                        if ("1".equals(str2)) {
                            JSInvokeHandler.this.linkCode = 1;
                        } else if ("2".equals(str2)) {
                            JSInvokeHandler.this.linkCode = 2;
                        }
                    }
                    String str3 = "";
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            str3 = new JSONObject(str2).optString("arg1");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    JSInvokeHandler.this.getDownloadEsim2(str3);
                } else if (str.equals("getDownloadEsimUnicom")) {
                    JSInvokeHandler.this.getDownloadEsimUnicom(str2);
                } else if (str.equals("disconnectHWService")) {
                    JSInvokeHandler.this.disconnectHWService2();
                } else {
                    str.equals("openHWWatchConnection");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$37 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BinderC725237 extends IOpenMultiSimCalbcak.Stub {
        BinderC725237() {
            JSInvokeHandler.this = r1;
        }

        @Override // com.huawei.multisimservice.model.IOpenMultiSimCalbcak
        public void getDeviceMultiSimInfo(MultiSimDeviceInfo multiSimDeviceInfo) {
            Handler handler;
            Runnable runnable;
            Handler handler2;
            Runnable runnable2;
            final JSONObject jSONObject = new JSONObject();
            try {
                try {
                    try {
                        if (JSInvokeHandler.this.linkCode != 1) {
                            if (JSInvokeHandler.this.linkCode == 2) {
                                JSInvokeHandler.this.resultCode2_2 = multiSimDeviceInfo.getResultCode();
                                JSInvokeHandler.this.deviceType2_2 = multiSimDeviceInfo.getDeviceType();
                            }
                        } else {
                            JSInvokeHandler.this.resultCode1_2 = multiSimDeviceInfo.getResultCode();
                            JSInvokeHandler.this.deviceType1_2 = multiSimDeviceInfo.getDeviceType();
                        }
                        jSONObject.put("ResultCode1", JSInvokeHandler.this.resultCode1_2);
                        jSONObject.put("DeviceType1", JSInvokeHandler.this.deviceType1_2);
                        jSONObject.put("ResultCode2", JSInvokeHandler.this.resultCode2_2);
                        jSONObject.put("DeviceType2", JSInvokeHandler.this.deviceType2_2);
                        jSONObject.put("linkCode", JSInvokeHandler.this.linkCode);
                        jSONObject.put("DeviceID", multiSimDeviceInfo.getDeviceID());
                        jSONObject.put("DeviceIMEI", multiSimDeviceInfo.getDeviceIMEI());
                        jSONObject.put("DeviceSerialNumber", multiSimDeviceInfo.getDeviceSerialNumber());
                        jSONObject.put("ProductName", multiSimDeviceInfo.getProductName());
                        jSONObject.put("EID", multiSimDeviceInfo.getEID());
                        if (multiSimDeviceInfo.getDeviceType() == 1) {
                            List<SimInfo> simInfoList = multiSimDeviceInfo.getSimInfoList();
                            if (simInfoList != null && simInfoList.size() > 0) {
                                jSONObject.put("IMSI", simInfoList.get(0).getIMSI());
                                jSONObject.put("ICCID", simInfoList.get(0).getICCID());
                            } else {
                                jSONObject.put("IMSI", "");
                                jSONObject.put("ICCID", "");
                            }
                        } else if (multiSimDeviceInfo.getDeviceType() == 2) {
                            jSONObject.put("IMSI", multiSimDeviceInfo.getActiveSimProfileInfo().getIMSI());
                            jSONObject.put("ICCID", multiSimDeviceInfo.getActiveSimProfileInfo().getICCID());
                        } else {
                            jSONObject.put("IMSI", "");
                            jSONObject.put("ICCID", "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (JSInvokeHandler.this.isDownLoadESMI) {
                            if (JSInvokeHandler.this.resultCode1_2 == 1) {
                                handler2 = JSInvokeHandler.this.webViewHandler;
                                runnable2 = new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.1
                                    {
                                        BinderC725237.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        JSInvokeHandler.this.successDwonLoadEsmi();
                                    }
                                };
                            } else {
                                JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.2
                                    {
                                        BinderC725237.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        JSInvokeHandler.this.disconnectHWService2();
                                        try {
                                            JSONObject jSONObject2 = new JSONObject();
                                            jSONObject2.put("linkCode", JSInvokeHandler.this.linkCode);
                                            jSONObject2.put("binRet", false);
                                            PBWebView pBWebView = JSInvokeHandler.this.webView;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("javascript:backConnectResult2(");
                                            sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                                            sb.append(")");
                                            pBWebView.loadURL(sb.toString());
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                });
                                return;
                            }
                        } else {
                            handler = JSInvokeHandler.this.webViewHandler;
                            runnable = new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.3
                                {
                                    BinderC725237.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("javascript:backMultiSimInfo2(");
                                    JSONObject jSONObject2 = jSONObject;
                                    sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                                    sb.append(")");
                                    pBWebView.loadURL(sb.toString());
                                }
                            };
                        }
                    }
                    if (JSInvokeHandler.this.isDownLoadESMI) {
                        if (JSInvokeHandler.this.resultCode1_2 == 1) {
                            handler2 = JSInvokeHandler.this.webViewHandler;
                            runnable2 = new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.1
                                {
                                    BinderC725237.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    JSInvokeHandler.this.successDwonLoadEsmi();
                                }
                            };
                            handler2.post(runnable2);
                            return;
                        }
                        JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.2
                            {
                                BinderC725237.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                JSInvokeHandler.this.disconnectHWService2();
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("linkCode", JSInvokeHandler.this.linkCode);
                                    jSONObject2.put("binRet", false);
                                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("javascript:backConnectResult2(");
                                    sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                                    sb.append(")");
                                    pBWebView.loadURL(sb.toString());
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        });
                        return;
                    }
                    handler = JSInvokeHandler.this.webViewHandler;
                    runnable = new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.3
                        {
                            BinderC725237.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            PBWebView pBWebView = JSInvokeHandler.this.webView;
                            StringBuilder sb = new StringBuilder();
                            sb.append("javascript:backMultiSimInfo2(");
                            JSONObject jSONObject2 = jSONObject;
                            sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                            sb.append(")");
                            pBWebView.loadURL(sb.toString());
                        }
                    };
                    handler.post(runnable);
                    JSInvokeHandler.this.disconnectHWService2();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th) {
                if (JSInvokeHandler.this.isDownLoadESMI) {
                    if (JSInvokeHandler.this.resultCode1_2 == 1) {
                        JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.1
                            {
                                BinderC725237.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                JSInvokeHandler.this.successDwonLoadEsmi();
                            }
                        });
                    } else {
                        try {
                            JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.2
                                {
                                    BinderC725237.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    JSInvokeHandler.this.disconnectHWService2();
                                    try {
                                        JSONObject jSONObject2 = new JSONObject();
                                        jSONObject2.put("linkCode", JSInvokeHandler.this.linkCode);
                                        jSONObject2.put("binRet", false);
                                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("javascript:backConnectResult2(");
                                        sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                                        sb.append(")");
                                        pBWebView.loadURL(sb.toString());
                                    } catch (Exception e22) {
                                        e22.printStackTrace();
                                    }
                                }
                            });
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                } else {
                    JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.37.3
                        {
                            BinderC725237.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            PBWebView pBWebView = JSInvokeHandler.this.webView;
                            StringBuilder sb = new StringBuilder();
                            sb.append("javascript:backMultiSimInfo2(");
                            JSONObject jSONObject2 = jSONObject;
                            sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                            sb.append(")");
                            pBWebView.loadURL(sb.toString());
                        }
                    });
                    JSInvokeHandler.this.disconnectHWService2();
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$38 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC725638 implements ServiceConnection {
        ServiceConnectionC725638() {
            JSInvokeHandler.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean z;
            try {
                JSInvokeHandler.this.mService2 = IOpenMultiSim.Stub.asInterface(IServiceBinder.Stub.asInterface(iBinder).getServiceBinder("com.sinovatech.unicom.ui"));
                JSInvokeHandler.this.mService2.registerCallback(JSInvokeHandler.this.mCallback2);
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
            if (JSInvokeHandler.this.linkCode != 1) {
                if (JSInvokeHandler.this.linkCode == 2) {
                    if (z) {
                        if (JSInvokeHandler.this.isDownLoadESMI) {
                            JSInvokeHandler.this.successDwonLoadEsmi();
                            return;
                        }
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("linkCode", JSInvokeHandler.this.linkCode);
                            jSONObject.put("binRet", true);
                            PBWebView pBWebView = JSInvokeHandler.this.webView;
                            StringBuilder sb = new StringBuilder();
                            sb.append("javascript:backConnectResult2(");
                            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                            sb.append(")");
                            pBWebView.loadURL(sb.toString());
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else if (JSInvokeHandler.this.isDownLoadESMI) {
                        JSInvokeHandler.this.FailDwonLoadEsmi();
                    } else {
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("linkCode", JSInvokeHandler.this.linkCode);
                            jSONObject2.put("binRet", false);
                            PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("javascript:backConnectResult2(");
                            sb2.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                            sb2.append(")");
                            pBWebView2.loadURL(sb2.toString());
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            } else if (z) {
                if (JSInvokeHandler.this.isDownLoadESMI) {
                    try {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("linkCode", JSInvokeHandler.this.linkCode);
                        jSONObject3.put("binRet", true);
                        PBWebView pBWebView3 = JSInvokeHandler.this.webView;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("javascript:backConnectResult2(");
                        sb3.append(!(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3));
                        sb3.append(")");
                        pBWebView3.loadURL(sb3.toString());
                        return;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return;
                    }
                }
                try {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("linkCode", JSInvokeHandler.this.linkCode);
                    jSONObject4.put("binRet", true);
                    PBWebView pBWebView4 = JSInvokeHandler.this.webView;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("javascript:backConnectResult2(");
                    sb4.append(!(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
                    sb4.append(")");
                    pBWebView4.loadURL(sb4.toString());
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            } else {
                try {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("linkCode", JSInvokeHandler.this.linkCode);
                    jSONObject5.put("binRet", false);
                    PBWebView pBWebView5 = JSInvokeHandler.this.webView;
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("javascript:backConnectResult2(");
                    sb5.append(!(jSONObject5 instanceof JSONObject) ? jSONObject5.toString() : NBSJSONObjectInstrumentation.toString(jSONObject5));
                    sb5.append(")");
                    pBWebView5.loadURL(sb5.toString());
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JSInvokeHandler.this.mService2 = null;
        }
    }

    public void connectHWService2(int i) {
        if (i == 1) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(HEALTH_PACKEAGE_NAME, CLASS_NAME_MULTI_SIM_SERVICE));
            intent.putExtras(new Bundle());
            if (this.context.bindService(intent, this.mConnection2, 1)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("linkCode", i);
                jSONObject.put("binRet", false);
                disconnectHWService2();
                PBWebView pBWebView = this.webView;
                StringBuilder sb = new StringBuilder();
                sb.append("javascript:backConnectResult2(");
                sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                sb.append(")");
                pBWebView.loadURL(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i == 2) {
            disconnectHWService2();
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName(BONE_PACKAGE_NAME, CLASS_NAME_MULTI_SIM_SERVICE));
            intent2.putExtras(new Bundle());
            if (this.context.bindService(intent2, this.mConnection2, 1)) {
                return;
            }
            if (!this.isDownLoadESMI) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("linkCode", i);
                    jSONObject2.put("binRet", false);
                    PBWebView pBWebView2 = this.webView;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("javascript:backConnectResult2(");
                    sb2.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    sb2.append(")");
                    pBWebView2.loadURL(sb2.toString());
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            FailDwonLoadEsmi();
        }
    }

    public void getMultiSimInfo2() {
        try {
            this.mService2.getAttachedDeviceMultiSimInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDownloadEsim2(String str) {
        try {
            try {
                this.mService2.downloadESimProfile(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            disconnectHWService2();
        }
    }

    @JavascriptInterface
    public void getDownloadEsimUnicom(String str) {
        try {
            if (this.mService2 != null) {
                disconnectHWService2();
            }
            StatisticsUploadUtils.uoloadPVWatch("huawei", "华为手表回调下载开始");
            this.isDownLoadESMI = true;
            this.prilCode_2 = str;
            getHWService2("connectHWService", "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void successDwonLoadEsmi() {
        try {
            this.mService2.downloadESimProfile(this.prilCode_2);
            this.webView.loadURL("javascript:backDownloadEsimResult('1')");
            this.isDownLoadESMI = false;
            StatisticsUploadUtils.uoloadPVWatch("huawei", "华为手表回调下载成功");
        } catch (Exception e) {
            e.printStackTrace();
            disconnectHWService2();
        }
    }

    public void FailDwonLoadEsmi() {
        try {
            this.webView.loadURL("javascript:backDownloadEsimResult('0')");
            this.isDownLoadESMI = false;
            StatisticsUploadUtils.uoloadPVWatch("huawei", "华为手表回调下载失败");
        } catch (Exception e) {
            e.printStackTrace();
            disconnectHWService2();
        }
    }

    public void disconnectHWService2() {
        try {
            this.mService2.unRegisterCallback(this.mCallback2);
            this.context.unbindService(this.mConnection2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shareDirectly(final String str, final JSONObject jSONObject) {
        try {
            String[] split = str.split(",");
            if (split != null && split.length > 0) {
                String str2 = split[0];
                String optString = jSONObject.optString("shareIconURL", "");
                if (!"wechat".equalsIgnoreCase(str2) && !"wechatmoments".equalsIgnoreCase(str2)) {
                    shareDirectly2(str, jSONObject, null);
                }
                this.hasShared = false;
                Observable.timer(1500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.39
                    {
                        JSInvokeHandler.this = this;
                    }

                    @Override // io.reactivex.functions.Consumer
                    public void accept(Long l) throws Exception {
                        Glide.with((FragmentActivity) JSInvokeHandler.this.context).asBitmap().load(ShareManager.shareIconDefault).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.39.1
                            {
                                C725739.this = this;
                            }

                            @Override // com.bumptech.glide.request.target.Target
                            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                            }

                            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                if (JSInvokeHandler.this.hasShared) {
                                    return;
                                }
                                JSInvokeHandler.this.hasShared = true;
                                JSInvokeHandler.this.shareDirectly2(str, jSONObject, bitmap);
                            }

                            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                            public void onLoadFailed(@Nullable Drawable drawable) {
                                super.onLoadFailed(drawable);
                                UIUtils.toast("分享图片链接错误，无法正确分享");
                            }
                        });
                    }
                });
                Glide.with((FragmentActivity) this.context).asBitmap().load(optString).into((RequestBuilder<Bitmap>) new C726240(str, jSONObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$40 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C726240 extends SimpleTarget<Bitmap> {
        final /* synthetic */ JSONObject val$jo;
        final /* synthetic */ String val$shareListStr;

        C726240(String str, JSONObject jSONObject) {
            JSInvokeHandler.this = r1;
            this.val$shareListStr = str;
            this.val$jo = jSONObject;
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            if (JSInvokeHandler.this.hasShared) {
                return;
            }
            JSInvokeHandler.this.hasShared = true;
            JSInvokeHandler.this.shareDirectly2(this.val$shareListStr, this.val$jo, bitmap);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0121 A[Catch: Exception -> 0x030f, TryCatch #0 {Exception -> 0x030f, blocks: (B:81:0x0006, B:83:0x000e, B:85:0x0011, B:87:0x004b, B:89:0x0051, B:90:0x005b, B:93:0x0065, B:95:0x006b, B:97:0x0071, B:99:0x0077, B:104:0x00cb, B:106:0x0121, B:108:0x0128, B:114:0x0210, B:116:0x021c, B:118:0x022f, B:119:0x0245, B:120:0x024d, B:122:0x0255, B:136:0x02e7, B:137:0x02eb, B:113:0x0204, B:103:0x0086, B:124:0x025d, B:126:0x0265, B:128:0x026e, B:130:0x0276, B:132:0x0289, B:133:0x02a9, B:110:0x0151), top: B:142:0x0006, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0210 A[Catch: Exception -> 0x030f, TryCatch #0 {Exception -> 0x030f, blocks: (B:81:0x0006, B:83:0x000e, B:85:0x0011, B:87:0x004b, B:89:0x0051, B:90:0x005b, B:93:0x0065, B:95:0x006b, B:97:0x0071, B:99:0x0077, B:104:0x00cb, B:106:0x0121, B:108:0x0128, B:114:0x0210, B:116:0x021c, B:118:0x022f, B:119:0x0245, B:120:0x024d, B:122:0x0255, B:136:0x02e7, B:137:0x02eb, B:113:0x0204, B:103:0x0086, B:124:0x025d, B:126:0x0265, B:128:0x026e, B:130:0x0276, B:132:0x0289, B:133:0x02a9, B:110:0x0151), top: B:142:0x0006, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0151 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void shareDirectly2(java.lang.String r29, org.json.JSONObject r30, final android.graphics.Bitmap r31) {
        /*
            Method dump skipped, instructions count: 788
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.JSInvokeHandler.shareDirectly2(java.lang.String, org.json.JSONObject, android.graphics.Bitmap):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:290:0x02ab A[Catch: Exception -> 0x0307, TryCatch #1 {Exception -> 0x0307, blocks: (B:158:0x000c, B:162:0x0036, B:164:0x0048, B:166:0x0052, B:173:0x006c, B:175:0x007b, B:178:0x0085, B:180:0x008e, B:182:0x0092, B:201:0x00f5, B:288:0x02a5, B:290:0x02ab, B:292:0x02c2, B:299:0x02e9, B:295:0x02d6, B:177:0x0083, B:172:0x0069, B:183:0x0096, B:185:0x00a3, B:186:0x00aa, B:188:0x00b7, B:190:0x00c6, B:193:0x00d0, B:195:0x00d9, B:197:0x00dd, B:192:0x00ce, B:198:0x00e1, B:200:0x00ed, B:202:0x00fe, B:204:0x0107, B:206:0x0114, B:222:0x0166, B:207:0x011b, B:209:0x0128, B:211:0x0137, B:214:0x0141, B:216:0x014a, B:218:0x014e, B:213:0x013f, B:219:0x0152, B:221:0x015e, B:223:0x016f, B:225:0x0178, B:227:0x0180, B:235:0x01a6, B:237:0x01ac, B:239:0x01b4, B:243:0x01bf, B:245:0x01c8, B:247:0x01cc, B:249:0x01dc, B:256:0x020b, B:250:0x01e0, B:252:0x01ed, B:253:0x01f4, B:255:0x0200, B:257:0x0214, B:259:0x021c, B:261:0x0228, B:275:0x026a, B:262:0x0234, B:266:0x0242, B:268:0x024b, B:270:0x024f, B:274:0x0267, B:276:0x0272, B:278:0x027a, B:280:0x0286, B:286:0x029c, B:281:0x028c, B:285:0x0299, B:168:0x0062, B:229:0x0187, B:231:0x0199, B:232:0x019e), top: B:306:0x000c, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x02d6 A[Catch: Exception -> 0x0307, TryCatch #1 {Exception -> 0x0307, blocks: (B:158:0x000c, B:162:0x0036, B:164:0x0048, B:166:0x0052, B:173:0x006c, B:175:0x007b, B:178:0x0085, B:180:0x008e, B:182:0x0092, B:201:0x00f5, B:288:0x02a5, B:290:0x02ab, B:292:0x02c2, B:299:0x02e9, B:295:0x02d6, B:177:0x0083, B:172:0x0069, B:183:0x0096, B:185:0x00a3, B:186:0x00aa, B:188:0x00b7, B:190:0x00c6, B:193:0x00d0, B:195:0x00d9, B:197:0x00dd, B:192:0x00ce, B:198:0x00e1, B:200:0x00ed, B:202:0x00fe, B:204:0x0107, B:206:0x0114, B:222:0x0166, B:207:0x011b, B:209:0x0128, B:211:0x0137, B:214:0x0141, B:216:0x014a, B:218:0x014e, B:213:0x013f, B:219:0x0152, B:221:0x015e, B:223:0x016f, B:225:0x0178, B:227:0x0180, B:235:0x01a6, B:237:0x01ac, B:239:0x01b4, B:243:0x01bf, B:245:0x01c8, B:247:0x01cc, B:249:0x01dc, B:256:0x020b, B:250:0x01e0, B:252:0x01ed, B:253:0x01f4, B:255:0x0200, B:257:0x0214, B:259:0x021c, B:261:0x0228, B:275:0x026a, B:262:0x0234, B:266:0x0242, B:268:0x024b, B:270:0x024f, B:274:0x0267, B:276:0x0272, B:278:0x027a, B:280:0x0286, B:286:0x029c, B:281:0x028c, B:285:0x0299, B:168:0x0062, B:229:0x0187, B:231:0x0199, B:232:0x019e), top: B:306:0x000c, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void share(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, android.graphics.Bitmap r29, boolean r30, java.lang.String r31, java.lang.String r32) {
        /*
            Method dump skipped, instructions count: 784
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.JSInvokeHandler.share(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, android.graphics.Bitmap, boolean, java.lang.String, java.lang.String):void");
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$43 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C726543 implements PlatformActionListener {
        C726543() {
            JSInvokeHandler.this = r1;
        }

        @Override // cn.sharesdk.framework.PlatformActionListener
        public void onError(Platform platform, int i, Throwable th) {
            JSInvokeHandler.this.setShareStatusToServer("fail", ShareManager.getShareSDKKey(platform.getName()));
        }

        @Override // cn.sharesdk.framework.PlatformActionListener
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            JSInvokeHandler.this.setShareStatusToServer("success", ShareManager.getShareSDKKey(platform.getName()));
        }

        @Override // cn.sharesdk.framework.PlatformActionListener
        public void onCancel(Platform platform, int i) {
            JSInvokeHandler.this.setShareStatusToServer("fail", ShareManager.getShareSDKKey(platform.getName()));
        }
    }

    public void setShareStatusToServer(String str, String str2) {
        try {
            String str3 = "{\"status\":\"" + str + "\",\"target\":\"" + str2 + "\",\"target\":\"" + this.userManager.getUserToken() + "\"}";
            this.webView.loadURL("javascript:try{setShareStatus_Local('" + str3 + "');}catch(err){}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$44 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C726644 implements CheckRequestPermissionsListener {
        C726644() {
            JSInvokeHandler.this = r1;
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onAllPermissionOk(Permission[] permissionArr) {
            JSInvokeHandler.this.mCard = new Card();
            ProgressDialog progressDialog = new ProgressDialog(JSInvokeHandler.this.context);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("正在检查是否支持写卡，请稍后");
            progressDialog.show();
            TYCJBoxManager.getInstance().collectClickSdk(JSInvokeHandler.this.context, "S2ndpage1214", App.webviewTitle, "空中网写卡", "", "com.taisys.oti", "1");
            JSInvokeHandler.this.mCard.isSimReady(JSInvokeHandler.this.context, new C72671(progressDialog));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$44$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C72671 implements Card.SimReady {
            final /* synthetic */ ProgressDialog val$pg;

            C72671(ProgressDialog progressDialog) {
                C726644.this = r1;
                this.val$pg = progressDialog;
            }

            @Override // com.taisys.oti.Card.SimReady
            public void simReady(boolean z) {
                this.val$pg.dismiss();
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("isSimReady", z);
                    if (z) {
                        jSONObject.put("status", JSInvokeHandler.this.mCard.getStatus());
                        jSONObject.put("ICCID", JSInvokeHandler.this.mCard.getIccid());
                    }
                    JSInvokeHandler.this.webViewHandler.post(new RunnableC72681(jSONObject));
                } catch (Exception e) {
                    e.printStackTrace();
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    pBWebView.loadURL("javascript:getICCIDStateApp('" + e.getMessage() + "')");
                    StringBuilder sb = new StringBuilder();
                    sb.append("接口异常：");
                    sb.append(e.getMessage());
                    UIUtils.toast(sb.toString());
                }
            }

            @NBSInstrumented
            /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$44$1$1 */
            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            class RunnableC72681 implements Runnable {
                final /* synthetic */ JSONObject val$jo;

                RunnableC72681(JSONObject jSONObject) {
                    C72671.this = r1;
                    this.val$jo = jSONObject;
                }

                @Override // java.lang.Runnable
                public void run() {
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    StringBuilder sb = new StringBuilder();
                    sb.append("javascript:getICCIDStateApp('");
                    JSONObject jSONObject = this.val$jo;
                    sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                    sb.append("')");
                    pBWebView.loadURL(sb.toString());
                }
            }
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onPermissionDenied(Permission[] permissionArr) {
            StringBuilder sb = new StringBuilder();
            sb.append("请授予");
            for (Permission permission : permissionArr) {
                sb.append(permission.permissionName);
            }
            sb.append("权限");
            UIUtils.toast(sb.toString());
        }
    }

    @JavascriptInterface
    public void getICCIDState() {
        SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.READ_SMS", "android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE"), new C726644());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$45 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C726945 implements CheckRequestPermissionsListener {
        final /* synthetic */ String val$content;

        C726945(String str) {
            JSInvokeHandler.this = r1;
            this.val$content = str;
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onAllPermissionOk(Permission[] permissionArr) {
            try {
                PermissionDialog.dimissDialog();
                if (JSInvokeHandler.this.mCard == null) {
                    JSInvokeHandler.this.mCard = new Card();
                }
                JSInvokeHandler.this.webViewHandler.post(new RunnableC72701(JSInvokeHandler.this.mCard.WriteCard(this.val$content)));
            } catch (Exception e) {
                e.printStackTrace();
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                pBWebView.loadURL("javascript:getWriteICCIDStateApp('" + e.getMessage() + "')");
                StringBuilder sb = new StringBuilder();
                sb.append("接口异常：");
                sb.append(e.getMessage());
                UIUtils.toast(sb.toString());
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$45$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class RunnableC72701 implements Runnable {
            final /* synthetic */ String val$res;

            RunnableC72701(String str) {
                C726945.this = r1;
                this.val$res = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                pBWebView.loadURL("javascript:getWriteICCIDStateApp('" + this.val$res + "')");
            }
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onPermissionDenied(Permission[] permissionArr) {
            PermissionDialog.dimissDialog();
            StringBuilder sb = new StringBuilder();
            sb.append("请授予");
            for (Permission permission : permissionArr) {
                sb.append(permission.permissionName);
            }
            sb.append("权限");
            UIUtils.toast(sb.toString());
        }
    }

    @JavascriptInterface
    public void writeICCID(String str) {
        PermissionDialog.show("为了给您带来更好的服务，需要获取您的短信读/写权限、通讯录读/写权限、电话权限。用于将关键信息通知到您、通讯录好友互动服务、安全验证等。对于您授权的信息，我们会竭尽全力提供安全保护。");
        SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.READ_SMS", "android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE"), new C726945(str));
    }

    public void doTakePhoto() {
        try {
            PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.46
                {
                    JSInvokeHandler.this = this;
                }

                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onAllPermissionOk(Permission[] permissionArr) {
                    PermissionDialog.dimissDialog();
                    try {
                        JSInvokeHandler.this.PHOTO_DIR = new File(SDCardUtil.getMediaFileUrl("Pic"));
                        if (!JSInvokeHandler.this.PHOTO_DIR.exists()) {
                            JSInvokeHandler.this.PHOTO_DIR.mkdirs();
                        }
                        Date date = new Date(System.currentTimeMillis());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'IM_IMG'_yyyyMMdd_HHmmss");
                        JSInvokeHandler jSInvokeHandler = JSInvokeHandler.this;
                        jSInvokeHandler.fileName = simpleDateFormat.format(date) + ".jpg";
                        JSInvokeHandler.this.mCurrentPhotoFile = new File(JSInvokeHandler.this.PHOTO_DIR, JSInvokeHandler.this.fileName);
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        if (Build.VERSION.SDK_INT < 24) {
                            intent.putExtra("output", Uri.fromFile(JSInvokeHandler.this.mCurrentPhotoFile));
                        } else {
                            intent.putExtra("output", FileProvider.getUriForFile(JSInvokeHandler.this.context, "com.sinovatech.unicom.ui.fileprovider", JSInvokeHandler.this.mCurrentPhotoFile));
                        }
                        new AvoidOnResult(JSInvokeHandler.this.context).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.46.1
                            {
                                C727146.this = this;
                            }

                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                if (i == -1) {
                                    try {
                                        if (Build.VERSION.SDK_INT <= 28) {
                                            JSInvokeHandler.this.compressCameraImage(JSInvokeHandler.this.mCurrentPhotoFile);
                                            return;
                                        }
                                        Bitmap decodeStream = BitmapFactory.decodeStream(JSInvokeHandler.this.context.getContentResolver().openInputStream(Uri.fromFile(JSInvokeHandler.this.mCurrentPhotoFile)));
                                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                                        pBWebView.evaluateJavascript("javascript:callbackWithImage('" + FileTools.bitmapToBase64(decodeStream, 50)[0] + "')", null);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        UIUtils.toastLong("获取图片异常【" + e.getMessage() + "】");
                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIUtils.toastLong("启动摄像头错误【" + e.getMessage() + "】");
                    }
                }

                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onPermissionDenied(Permission[] permissionArr) {
                    PermissionDialog.dimissDialog();
                    StringBuilder sb = new StringBuilder();
                    sb.append("请授予");
                    for (Permission permission : permissionArr) {
                        sb.append(permission.permissionName);
                    }
                    sb.append("权限");
                    UIUtils.toast(sb.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toastLong("启动摄像头错误【" + e.getMessage() + "】");
        }
    }

    private void compressPhotoImage(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(this.context);
            customePorgressDialog.setMessage("图片处理中...");
            Luban.compress(this.context, new File(str)).setCompressFormat(Bitmap.CompressFormat.JPEG).launch(new C727347(customePorgressDialog));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$47 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C727347 implements OnCompressListener {
        final /* synthetic */ CustomePorgressDialog val$pd;

        C727347(CustomePorgressDialog customePorgressDialog) {
            JSInvokeHandler.this = r1;
            this.val$pd = customePorgressDialog;
        }

        @Override // me.shaohui.advancedluban.OnCompressListener
        public void onStart() {
            CustomePorgressDialog customePorgressDialog = this.val$pd;
            if (customePorgressDialog != null) {
                customePorgressDialog.show();
            }
        }

        @Override // me.shaohui.advancedluban.OnCompressListener
        public void onSuccess(File file) {
            try {
                Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (decodeFile != null) {
                    PBWebView pBWebView = JSInvokeHandler.this.webView;
                    pBWebView.evaluateJavascript("javascript:callbackWithImage('" + FileTools.bitmapToBase64(decodeFile, 50)[0] + "')", null);
                } else {
                    UIUtils.toast("未获取到图片");
                }
                if (this.val$pd != null) {
                    this.val$pd.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // me.shaohui.advancedluban.OnCompressListener
        public void onError(Throwable th) {
            CustomePorgressDialog customePorgressDialog = this.val$pd;
            if (customePorgressDialog != null) {
                customePorgressDialog.dismiss();
            }
        }
    }

    public Uri getUriFromFile(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
    }

    public void compressCameraImage(File file) {
        try {
            CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(this.context);
            customePorgressDialog.setMessage("图片处理中...");
            Luban.compress(this.context, file).setCompressFormat(Bitmap.CompressFormat.JPEG).launch(new C727448(customePorgressDialog));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$48 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C727448 implements OnCompressListener {
        final /* synthetic */ CustomePorgressDialog val$pd;

        C727448(CustomePorgressDialog customePorgressDialog) {
            JSInvokeHandler.this = r1;
            this.val$pd = customePorgressDialog;
        }

        @Override // me.shaohui.advancedluban.OnCompressListener
        public void onStart() {
            CustomePorgressDialog customePorgressDialog = this.val$pd;
            if (customePorgressDialog != null) {
                customePorgressDialog.show();
            }
        }

        @Override // me.shaohui.advancedluban.OnCompressListener
        public void onSuccess(File file) {
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(JSInvokeHandler.this.context.getContentResolver().openInputStream(Uri.fromFile(file)));
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                pBWebView.evaluateJavascript("javascript:callbackWithImage('" + FileTools.bitmapToBase64(decodeStream, 50)[0] + "')", null);
                if (this.val$pd != null) {
                    this.val$pd.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // me.shaohui.advancedluban.OnCompressListener
        public void onError(Throwable th) {
            CustomePorgressDialog customePorgressDialog = this.val$pd;
            if (customePorgressDialog != null) {
                customePorgressDialog.dismiss();
            }
        }
    }

    public void doPickPhotoFromGallery() {
        try {
            Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            new AvoidOnResult(this.context).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.-$$Lambda$JSInvokeHandler$8D45BITub2YB5O4eWWPWBSwH3s4
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public final void onActivityResult(int i, Intent intent2) {
                    JSInvokeHandler.lambda$doPickPhotoFromGallery$3(JSInvokeHandler.this, i, intent2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast("未获取到图片");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void lambda$doPickPhotoFromGallery$3(com.sinovatech.unicom.basic.p315ui.JSInvokeHandler r3, int r4, android.content.Intent r5) {
        /*
            r0 = 0
            r1 = -1
            if (r4 != r1) goto L39
            android.net.Uri r4 = r5.getData()
            if (r4 != 0) goto L10
            android.os.Bundle r4 = r5.getExtras()
            if (r4 == 0) goto L39
        L10:
            android.net.Uri r4 = r5.getData()
            if (r4 == 0) goto L39
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.io.FileNotFoundException -> L35
            r1 = 28
            if (r5 > r1) goto L26
            android.support.v7.app.AppCompatActivity r5 = r3.context     // Catch: java.io.FileNotFoundException -> L35
            java.lang.String r4 = r3.getRealFilePath(r5, r4)     // Catch: java.io.FileNotFoundException -> L35
            r3.compressPhotoImage(r4)     // Catch: java.io.FileNotFoundException -> L35
            return
        L26:
            android.support.v7.app.AppCompatActivity r5 = r3.context     // Catch: java.io.FileNotFoundException -> L35
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.io.FileNotFoundException -> L35
            java.io.InputStream r4 = r5.openInputStream(r4)     // Catch: java.io.FileNotFoundException -> L35
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r4)     // Catch: java.io.FileNotFoundException -> L35
            goto L3a
        L35:
            r4 = move-exception
            r4.printStackTrace()
        L39:
            r4 = r0
        L3a:
            if (r4 == 0) goto L61
            com.sinovatech.unicom.basic.webview.PBWebView r5 = r3.webView
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "javascript:callbackWithImage('"
            r1.append(r2)
            r2 = 50
            java.lang.String[] r4 = com.sinovatech.unicom.common.FileTools.bitmapToBase64(r4, r2)
            r2 = 0
            r4 = r4[r2]
            r1.append(r4)
            java.lang.String r4 = "')"
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r5.evaluateJavascript(r4, r0)
            goto L67
        L61:
            java.lang.String r4 = "未获取到图片"
            com.sinovatech.unicom.common.UIUtils.toast(r4)
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.JSInvokeHandler.lambda$doPickPhotoFromGallery$3(com.sinovatech.unicom.basic.ui.JSInvokeHandler, int, android.content.Intent):void");
    }

    private String getRealFilePath(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }
        Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (query != null) {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
            query.moveToFirst();
            String string = query.getString(columnIndexOrThrow);
            query.close();
            return string;
        }
        return null;
    }

    public void showGetPhotoDialog() {
        Dialog dialog = new Dialog(this.context, 2131952236);
        LinearLayout linearLayout = (LinearLayout) this.context.getLayoutInflater().inflate(2131493398, (ViewGroup) null);
        linearLayout.findViewById(2131298197).setOnClickListener(new View$OnClickListenerC727549(dialog));
        linearLayout.findViewById(2131298199).setOnClickListener(new View$OnClickListenerC727750(dialog));
        linearLayout.findViewById(2131298198).setOnClickListener(new View$OnClickListenerC727851(dialog));
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

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$49 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC727549 implements View.OnClickListener {
        final /* synthetic */ Dialog val$shareDialog;

        View$OnClickListenerC727549(Dialog dialog) {
            JSInvokeHandler.this = r1;
            this.val$shareDialog = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            this.val$shareDialog.cancel();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$50 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC727750 implements View.OnClickListener {
        final /* synthetic */ Dialog val$shareDialog;

        View$OnClickListenerC727750(Dialog dialog) {
            JSInvokeHandler.this = r1;
            this.val$shareDialog = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            JSInvokeHandler.this.doTakePhoto();
            this.val$shareDialog.cancel();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$51 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC727851 implements View.OnClickListener {
        final /* synthetic */ Dialog val$shareDialog;

        View$OnClickListenerC727851(Dialog dialog) {
            JSInvokeHandler.this = r1;
            this.val$shareDialog = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            JSInvokeHandler.this.doPickPhotoFromGallery();
            this.val$shareDialog.cancel();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    public void checkPermission(JSONObject jSONObject) {
        Permissions build;
        String str;
        try {
            String optString = jSONObject.optString("checkType", "");
            String optString2 = jSONObject.optString("showAlert", "");
            String optString3 = jSONObject.optString("authorTitle", "");
            String optString4 = jSONObject.optString("authorMsg", "");
            String optString5 = jSONObject.optString("cancelTitle", "");
            String optString6 = jSONObject.optString("sureTitle", "");
            String optString7 = jSONObject.optString("onSuccess", "");
            jSONObject.optString("onFail", "");
            new JSONObject().put("type", optString);
            if (TextUtils.equals("all", optString)) {
                build = Permissions.build("android.permission.CAMERA", "android.permission.RECORD_AUDIO");
                str = "为了给您带来更好的服务，需要获取您的相机权限、音频权限，用于扫码、拍照、刷脸验证、视频通话，对于您授权的信息我们竭尽提供安全保护。";
            } else if (TextUtils.equals("camera", optString)) {
                build = Permissions.build("android.permission.CAMERA");
                str = "为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。";
            } else if (TextUtils.equals("mic", optString)) {
                build = Permissions.build("android.permission.RECORD_AUDIO");
                str = "为了给您带来更好的服务，需要获取您音频权限，用于刷脸验证、软电话、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。";
            } else {
                build = Permissions.build("android.permission.CAMERA", "android.permission.RECORD_AUDIO");
                str = "为了给您带来更好的服务，需要获取您的相机权限、音频权限，用于扫码、拍照、刷脸验证、视频通话，对于您授权的信息我们竭尽提供安全保护。";
            }
            PermissionDialog.show(str);
            SoulPermission.getInstance().checkAndRequestPermissions(build, new C727952(optString, optString7, optString2, optString3, optString4, optString5, optString6));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.JSInvokeHandler$52 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C727952 implements CheckRequestPermissionsListener {
        final /* synthetic */ String val$authorMsg;
        final /* synthetic */ String val$authorTitle;
        final /* synthetic */ String val$cancelTitle;
        final /* synthetic */ String val$checkType;
        final /* synthetic */ String val$onSuccess;
        final /* synthetic */ String val$showAlert;
        final /* synthetic */ String val$sureTitle;

        C727952(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            JSInvokeHandler.this = r1;
            this.val$checkType = str;
            this.val$onSuccess = str2;
            this.val$showAlert = str3;
            this.val$authorTitle = str4;
            this.val$authorMsg = str5;
            this.val$cancelTitle = str6;
            this.val$sureTitle = str7;
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onAllPermissionOk(Permission[] permissionArr) {
            try {
                PermissionDialog.dimissDialog();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", this.val$checkType);
                if (RomUtils.isFlyme()) {
                    boolean z = false;
                    boolean z2 = false;
                    for (Permission permission : permissionArr) {
                        if (TextUtils.equals("android.permission.CAMERA", permission.permissionName)) {
                            z2 = FlymePermissionUtil.isCameraCanUse();
                        } else if (TextUtils.equals("android.permission.RECORD_AUDIO", permission.permissionName)) {
                            z = FlymePermissionUtil.isHasPermission(JSInvokeHandler.this.context);
                        }
                    }
                    if (z && z2) {
                        jSONObject.put("authed", "yes");
                        PBWebView pBWebView = JSInvokeHandler.this.webView;
                        StringBuilder sb = new StringBuilder();
                        sb.append("javascript:");
                        sb.append(this.val$onSuccess);
                        sb.append("('");
                        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                        sb.append("')");
                        pBWebView.loadURL(sb.toString());
                        return;
                    }
                    jSONObject.put("authed", "no");
                    PBWebView pBWebView2 = JSInvokeHandler.this.webView;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("javascript:");
                    sb2.append(this.val$onSuccess);
                    sb2.append("('");
                    sb2.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                    sb2.append("')");
                    pBWebView2.loadURL(sb2.toString());
                    if (TextUtils.equals("yes", this.val$showAlert)) {
                        JSInvokeHandler.this.showPermissionDialog(this.val$authorTitle, this.val$authorMsg, this.val$cancelTitle, this.val$sureTitle);
                        return;
                    }
                    return;
                }
                jSONObject.put("authed", "yes");
                PBWebView pBWebView3 = JSInvokeHandler.this.webView;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("javascript:");
                sb3.append(this.val$onSuccess);
                sb3.append("('");
                sb3.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                sb3.append("')");
                pBWebView3.loadURL(sb3.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onPermissionDenied(Permission[] permissionArr) {
            try {
                PermissionDialog.dimissDialog();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("authed", "no");
                jSONObject.put("type", this.val$checkType);
                PBWebView pBWebView = JSInvokeHandler.this.webView;
                StringBuilder sb = new StringBuilder();
                sb.append("javascript:");
                sb.append(this.val$onSuccess);
                sb.append("('");
                sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                sb.append("')");
                pBWebView.loadURL(sb.toString());
                if (TextUtils.equals("yes", this.val$showAlert)) {
                    JSInvokeHandler.this.showPermissionDialog(this.val$authorTitle, this.val$authorMsg, this.val$cancelTitle, this.val$sureTitle);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void showPermissionDialog(String str, String str2, String str3, String str4) {
        CustomDialogManager.show((Activity) this.context, str, str2, true, str3, str4, false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.53
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            {
                JSInvokeHandler.this = this;
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() { // from class: com.sinovatech.unicom.basic.ui.JSInvokeHandler.53.1
                    @Override // com.p284qw.soul.permission.callbcak.GoAppDetailCallBack
                    public void onBackFromAppDetail(Intent intent) {
                    }

                    {
                        C728053.this = this;
                    }
                });
            }
        });
    }
}
