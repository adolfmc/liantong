package com.sinovatech.unicom.p318ui;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import com.cjt2325.cameralibrary.CameraInterface;
import com.fort.andjni.JniLib;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.connection.DownloadOkHttp3Connection;
import com.loopj.android.http.AsyncHttpClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.sinovatech.unicom.basic.broadcast.ScreenBroadCastManager;
import com.sinovatech.unicom.basic.p314po.OEMInfoEntity;
import com.sinovatech.unicom.common.ActivityLifecycleGlobalListener;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu;
import com.tencent.p348mm.opensdk.openapi.IWXAPI;
import io.objectbox.BoxStore;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Authenticator;
import okhttp3.Cookie;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.sinovatech.unicom.ui.App */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class App extends Application {
    public static final String KEY_DEX2_SHA1 = "dex2-SHA1-Digest";
    private static final String TAG = "App";
    private static String areaJson = null;
    private static AsyncHttpClient asyncHttpClient = null;
    private static BoxStore boxStore = null;
    public static String cacheImageFilePath = null;
    public static List<HttpCallBackListener> callBackList = null;
    public static boolean homeCardBg = false;
    private static OEMInfoEntity infoEntity = null;
    public static App instance = null;
    public static boolean isAliPay = false;
    public static boolean isCheckFinger = false;
    public static boolean isCityToHome = false;
    public static boolean isClickWelcomeXieyi = false;
    public static boolean isShowFingerdialog = false;
    public static boolean isTopProcess = true;
    public static IWXAPI iwxapi = null;
    public static String mainTagFromOtherActivity = null;
    public static String originalUrl = "";
    private static SharePreferenceUtil preferences = null;
    public static String quitUrl = "";
    public static boolean reEnterAfterCloseApplication_ForCitySelected = false;
    public static boolean reEnterAfterCloseApplication_ForGesturePassword = false;
    public static boolean reLoadAdvertise = false;
    public static boolean reLoadDefaultMenuData = false;
    public static boolean reRefreshUserAccount = false;
    public static boolean realexit = false;
    public static boolean refreshMenuOnHomeResume = false;
    public static boolean refreshXiaohongdianClear = false;
    public static boolean refreshXiaohongdianClear2 = false;
    public static boolean refreshXiaoxiOnUserResume = true;
    public static boolean refreshYuleOnHomeResume;
    public static boolean refreshbackisRequest;
    public static ScreenBroadCastManager sbManager;
    public static boolean toutiaoBalck;
    public static String webviewTitle;
    public static int yunshanfuPayType;
    private ActivityLifecycleGlobalListener activityLifecycleGlobalListener;
    public ManagerHuiDu.HuiduConfig huiduConfig;
    public HashMap<String, String> prefetchCumpStatus = new HashMap<>();
    public static HashMap<String, Drawable> cardBgMap = new HashMap<>();
    private static LoginStateConst LoginState = LoginStateConst.UNLOGIN;
    public static String xinLaoUserOpenUrl = "";
    public static String callbackWXURL = "";
    public static String weixinMsg = "";
    public static boolean weixinPayFlag = false;
    public static boolean fromGalleryForGesture = false;
    public static String externURLFromBrowser = "";
    public static Uri externURLQQMiniGame = null;
    public static String externURL = "";
    public static String quickAccessURL = "";
    public static String quickAccessTitle = "";
    public static String exterWelcomURL = "";
    public static boolean isHandleWoKouLing = true;
    public static boolean formWechat = false;
    public static HashMap<String, DownloadTask> gameDownloadTaskMap = new HashMap<>();
    public static HashMap<String, DownloadTask> fileDownlaodTaskMap = new HashMap<>();
    public static HashMap<String, Map<String, Object>> navigateParamsCacheMap = new HashMap<>();
    private static String pvLogSessionId = "";
    public static String pvLogSessionIdTime = "";
    public static String qidongFlag = "0";
    public static boolean isWelcomeStartApp = true;
    public static List<String> appNeedRefreshList = new ArrayList();
    public static String YICHANGCHONGQIFLAG = "";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.ui.App$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C97081 implements Consumer<Throwable> {
        final /* synthetic */ App this$0;

        C97081(App app) {
            JniLib.m15918cV(this, app, 124);
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Throwable th) throws Exception {
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.App$DevicIdInterceptor */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class DevicIdInterceptor implements Interceptor {
        public DevicIdInterceptor() {
            JniLib.m15918cV(this, 129);
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Object m15920cL = JniLib.m15920cL(this, chain, 128);
            if (m15920cL == null) {
                return null;
            }
            return (Response) m15920cL;
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.App$OkHttpUserAgentInterceptor */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class OkHttpUserAgentInterceptor implements Interceptor {
        public OkHttpUserAgentInterceptor() {
            JniLib.m15918cV(this, 131);
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Object m15920cL = JniLib.m15920cL(this, chain, 130);
            if (m15920cL == null) {
                return null;
            }
            return (Response) m15920cL;
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.App$PrePublicInterceptor */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class PrePublicInterceptor implements Interceptor {
        public PrePublicInterceptor() {
            JniLib.m15918cV(this, 133);
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Object m15920cL = JniLib.m15920cL(this, chain, 132);
            if (m15920cL == null) {
                return null;
            }
            return (Response) m15920cL;
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.App$RemoveIFMatchHeaderIntercepter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class RemoveIFMatchHeaderIntercepter implements Interceptor {
        public RemoveIFMatchHeaderIntercepter() {
            JniLib.m15918cV(this, 135);
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Object m15920cL = JniLib.m15920cL(this, chain, 134);
            if (m15920cL == null) {
                return null;
            }
            return (Response) m15920cL;
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.App$TestAuthenticator */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class TestAuthenticator implements Interceptor {
        final String basic;

        public TestAuthenticator() {
            JniLib.m15918cV(this, 139);
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Object m15920cL = JniLib.m15920cL(this, chain, 138);
            if (m15920cL == null) {
                return null;
            }
            return (Response) m15920cL;
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.App$TestAuthenticator2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class TestAuthenticator2 implements Authenticator {
        final String basic;

        public TestAuthenticator2() {
            JniLib.m15918cV(this, 137);
        }

        @Override // okhttp3.Authenticator
        @Nullable
        public Request authenticate(Route route, Response response) throws IOException {
            Object m15920cL = JniLib.m15920cL(this, route, response, 136);
            if (m15920cL == null) {
                return null;
            }
            return (Request) m15920cL;
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.App$TiyanBeataInterceptor */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class TiyanBeataInterceptor implements Interceptor {
        public TiyanBeataInterceptor() {
            JniLib.m15918cV(this, 141);
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Object m15920cL = JniLib.m15920cL(this, chain, 140);
            if (m15920cL == null) {
                return null;
            }
            return (Response) m15920cL;
        }
    }

    public static void addOkHttpLister(HttpCallBackListener httpCallBackListener) {
        JniLib.m15918cV(httpCallBackListener, 149);
    }

    public static void clearPersistentCookiesList() {
        JniLib.m15918cV(150);
    }

    private void disableAPIDialog() {
        JniLib.m15918cV(this, 151);
    }

    private String get2thDexSHA1(Context context) {
        Object m15920cL = JniLib.m15920cL(this, context, 152);
        if (m15920cL == null) {
            return null;
        }
        return (String) m15920cL;
    }

    public static String getAreaJson() {
        Object m15920cL = JniLib.m15920cL(153);
        if (m15920cL == null) {
            return null;
        }
        return (String) m15920cL;
    }

    public static AsyncHttpClient getAsyncHttpClient() {
        Object m15920cL = JniLib.m15920cL(154);
        if (m15920cL == null) {
            return null;
        }
        return (AsyncHttpClient) m15920cL;
    }

    public static AsyncHttpClient getAsyncHttpClient(int i, int i2, int i3) {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 155);
        if (m15920cL == null) {
            return null;
        }
        return (AsyncHttpClient) m15920cL;
    }

    public static AsyncHttpClient getAsyncHttpClient(int i, int i2, int i3, int i4) {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), 156);
        if (m15920cL == null) {
            return null;
        }
        return (AsyncHttpClient) m15920cL;
    }

    public static BoxStore getBoxStore() {
        Object m15920cL = JniLib.m15920cL(157);
        if (m15920cL == null) {
            return null;
        }
        return (BoxStore) m15920cL;
    }

    public static OEMInfoEntity getInfoEntity() {
        Object m15920cL = JniLib.m15920cL(158);
        if (m15920cL == null) {
            return null;
        }
        return (OEMInfoEntity) m15920cL;
    }

    public static App getInstance() {
        Object m15920cL = JniLib.m15920cL(159);
        if (m15920cL == null) {
            return null;
        }
        return (App) m15920cL;
    }

    public static LoginStateConst getLogined() {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf((int) C0567f.f1819h));
        if (m15920cL == null) {
            return null;
        }
        return (LoginStateConst) m15920cL;
    }

    public static String getPvLogSessionId() {
        Object m15920cL = JniLib.m15920cL(161);
        if (m15920cL == null) {
            return null;
        }
        return (String) m15920cL;
    }

    public static SharePreferenceUtil getSharePreferenceUtil() {
        Object m15920cL = JniLib.m15920cL(162);
        if (m15920cL == null) {
            return null;
        }
        return (SharePreferenceUtil) m15920cL;
    }

    public static IWXAPI getWXAPI() {
        Object m15920cL = JniLib.m15920cL(163);
        if (m15920cL == null) {
            return null;
        }
        return (IWXAPI) m15920cL;
    }

    private void initARouter() {
        JniLib.m15918cV(this, 164);
    }

    private void initAreaJson() {
        JniLib.m15918cV(this, 165);
    }

    private void initGlobalOkDownloadConfig() {
        JniLib.m15918cV(this, 166);
    }

    public static boolean isSuccessful(int i) {
        return JniLib.m15917cZ(Integer.valueOf(i), 167);
    }

    private boolean needWait(Context context) {
        return JniLib.m15917cZ(this, context, 168);
    }

    public static void removeOkHttpLister(HttpCallBackListener httpCallBackListener) {
        JniLib.m15918cV(httpCallBackListener, 169);
    }

    public static void setInfoEntity(OEMInfoEntity oEMInfoEntity) {
        JniLib.m15918cV(oEMInfoEntity, 170);
    }

    public static void setLogined(LoginStateConst loginStateConst) {
        JniLib.m15918cV(loginStateConst, 171);
    }

    public static void setPvLogSessionId() {
        JniLib.m15918cV(172);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        JniLib.m15918cV(this, context, 142);
    }

    public String getVersion() {
        Object m15920cL = JniLib.m15920cL(this, 143);
        if (m15920cL == null) {
            return null;
        }
        return (String) m15920cL;
    }

    public String getVersionName(Context context) {
        Object m15920cL = JniLib.m15920cL(this, context, 144);
        if (m15920cL == null) {
            return null;
        }
        return (String) m15920cL;
    }

    public void installFinish(Context context) {
        JniLib.m15918cV(this, context, Integer.valueOf((int) CameraInterface.TYPE_CAPTURE));
    }

    @Override // android.app.Application
    public void onCreate() {
        JniLib.m15918cV(this, 146);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        JniLib.m15918cV(this, 147);
    }

    @Override // android.app.Application
    public void onTerminate() {
        JniLib.m15918cV(this, 148);
    }

    public static ArrayList<Cookie> getPersistentCookiesList() {
        return asyncHttpClient.getOKHttpCacheCookies();
    }

    public static boolean hasLogined() {
        boolean z = LoginState == LoginStateConst.DID_LOGIN;
        if (z) {
            return z;
        }
        if (preferences == null) {
            preferences = new SharePreferenceUtil(getInstance());
        }
        String string = preferences.getString("login_state");
        if ((LoginStateConst.DID_LOGIN + "").equals(string)) {
            setLogined(LoginStateConst.DID_LOGIN);
            return true;
        }
        return z;
    }

    /* renamed from: com.sinovatech.unicom.ui.App$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97092 extends Thread {
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            JniLib.m15918cV(this, 125);
        }

        C97092() {
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    public void waitForDexopt(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getPackageName(), LoadResActivity.class.getName()));
        intent.addFlags(268435456);
        context.startActivity(intent);
        long currentTimeMillis = System.currentTimeMillis();
        long j = Build.VERSION.SDK_INT < 12 ? 20000L : 10000L;
        while (needWait(context)) {
            try {
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (System.currentTimeMillis() - currentTimeMillis >= j) {
                return;
            }
            Thread.sleep(200L);
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.App$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class RunnableC97103 implements Runnable {
        final /* synthetic */ App this$0;

        RunnableC97103(App app) {
            JniLib.m15918cV(this, app, 126);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String unused = App.areaJson = FileTools.readFile(this.this$0.getResources().getAssets().open("broadband_area.json"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.ui.App$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97114 implements DownloadConnection.Factory {
        final /* synthetic */ App this$0;

        C97114(App app) {
            JniLib.m15918cV(this, app, 127);
        }

        @Override // com.liulishuo.okdownload.core.connection.DownloadConnection.Factory
        public DownloadConnection create(String str) throws IOException {
            return new DownloadOkHttp3Connection.Factory().setBuilder(NBSOkHttp3Instrumentation.init().newBuilder().retryOnConnectionFailure(true).readTimeout(2147483L, TimeUnit.SECONDS).writeTimeout(2147483L, TimeUnit.SECONDS).connectTimeout(2147483L, TimeUnit.SECONDS).callTimeout(2147483L, TimeUnit.SECONDS).addNetworkInterceptor(new OkHttpUserAgentInterceptor()).addNetworkInterceptor(new RemoveIFMatchHeaderIntercepter())).create(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getOkHttpUserAgent() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String property = System.getProperty("http.agent");
            int length = property.length();
            for (int i = 0; i < length; i++) {
                char charAt = property.charAt(i);
                if (charAt > 31 && charAt < 127) {
                    stringBuffer.append(charAt);
                }
                stringBuffer.append(String.format("\\u%04x", Integer.valueOf(charAt)));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
