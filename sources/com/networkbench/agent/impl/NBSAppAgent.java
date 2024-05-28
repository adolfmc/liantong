package com.networkbench.agent.impl;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimingLogger;
import android.view.View;
import android.webkit.WebView;
import com.networkbench.agent.impl.crash.C6323d;
import com.networkbench.agent.impl.crash.C6325e;
import com.networkbench.agent.impl.crash.C6328g;
import com.networkbench.agent.impl.crash.C6330i;
import com.networkbench.agent.impl.crash.NativeCrashInterface;
import com.networkbench.agent.impl.crash.p249a.C6311a;
import com.networkbench.agent.impl.crash.p249a.C6314d;
import com.networkbench.agent.impl.harvest.ApplicationInformation;
import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConnection;
import com.networkbench.agent.impl.harvest.HarvestData;
import com.networkbench.agent.impl.harvest.InitUrlConnection;
import com.networkbench.agent.impl.harvest.p261b.C6458a;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.instrumentation.NBSWebChromeClient;
import com.networkbench.agent.impl.instrumentation.NBSWebChromeX5Client;
import com.networkbench.agent.impl.instrumentation.TingyunErrorEventFeedBack;
import com.networkbench.agent.impl.p243c.p244a.C6250b;
import com.networkbench.agent.impl.p243c.p244a.C6258i;
import com.networkbench.agent.impl.p243c.p244a.C6259j;
import com.networkbench.agent.impl.p243c.p247d.C6273e;
import com.networkbench.agent.impl.p243c.p247d.C6274f;
import com.networkbench.agent.impl.p243c.p248e.C6287e;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6395g;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.p267m.C6495b;
import com.networkbench.agent.impl.p267m.C6500f;
import com.networkbench.agent.impl.p268n.C6516c;
import com.networkbench.agent.impl.tracing.CustomTracer;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6646o;
import com.networkbench.agent.impl.util.C6648q;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.agent.impl.util.NBSAndroidAgentImpl;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSAppAgent {
    public static final int ANR_ENABLED = 64;
    public static final boolean BUSINESSS = true;
    public static final int CDN_ENDBLED = 256;
    public static final boolean CONTROLLER_MODE = false;
    public static final int CRASH_ENABLED = 4;
    public static final int CROSS_APP_ENABLED = 32;
    public static final boolean DEBUG_MODE = false;
    public static final int HTTP_NETWORK_ENABLED = 1;
    public static int LOG_LEVEL_DEBUG = 4;
    public static int LOG_LEVEL_ERROR = 8;
    public static int LOG_LEVEL_VERBOSE = 2;
    public static int LOG_LEVEL_WARNING = 16;
    public static final boolean NETWORK_ONLY = false;
    public static final int SOCKET_DATA_ENABLED = 16;
    public static final int UI_ENABLED = 2;
    public static final int USER_ACTION_ENABLED = 128;
    public static final int VIEWIDTAG = 214748364;
    public static final int WEBVIEW_ENABLED = 8;
    public static int LOG_LEVEL_INFO = 1;
    public static int LOG_LEVEL_FLAG = LOG_LEVEL_INFO;
    private static InterfaceC6393e log = C6394f.m10150a();
    private static boolean onlyMainProcess = false;
    private static volatile NBSAppAgent appAgent = null;
    private static Map<String, NBSTransactionState> webTimings = new ConcurrentHashMap();
    public static String schemeIgnore = "";
    private boolean ssl = true;
    private boolean locationServicesEnabled = false;
    private boolean crashReportEnabled = true;
    private boolean anrReportEnabled = true;
    private volatile boolean apmIsRunning = false;
    private int ratio = 100;

    private boolean isInstrumented() {
        return true;
    }

    private NBSAppAgent withLoggingEnabled(boolean z) {
        return this;
    }

    private NBSAppAgent() {
    }

    private NBSAppAgent(String str) {
        C6638h.m8963w().m8999h(str);
    }

    public static NBSAppAgent setLicenseKey(String str) {
        if (appAgent == null) {
            appAgent = new NBSAppAgent(str);
        }
        return appAgent;
    }

    public static void setLogEnable(boolean z) {
        C6638h.m8963w().m9010e(z);
    }

    public NBSAppAgent withLocationServiceEnabled(boolean z) {
        this.locationServicesEnabled = z;
        return this;
    }

    public NBSAppAgent withCrashReportEnabled(boolean z) {
        this.crashReportEnabled = z;
        return this;
    }

    @Deprecated
    public NBSAppAgent withAnrEnabled(boolean z) {
        this.anrReportEnabled = z;
        return this;
    }

    public NBSAppAgent setRequestIdHeader(String str) {
        if (TextUtils.isEmpty(str)) {
            log.mo10115e("setRequestIdHeader is empty or null");
        } else {
            C6638h.m8963w().f17178d = str;
        }
        return this;
    }

    public NBSAppAgent withOnlyMainProcEnabled(boolean z) {
        onlyMainProcess = z;
        return this;
    }

    public NBSAppAgent withSampleRatio(int i) {
        this.ratio = i;
        return this;
    }

    public NBSAppAgent encryptionRequired(boolean z) {
        C6638h.m8963w().f17182z = z;
        return this;
    }

    public boolean isSslEnabled() {
        return this.ssl;
    }

    public NBSAppAgent setHttpEnabled(boolean z) {
        this.ssl = !z;
        return this;
    }

    public NBSAppAgent enableLogging(boolean z) {
        C6638h.f17115o = z;
        return this;
    }

    public static void setLogging(int i) {
        if (i <= 0) {
            return;
        }
        C6330i.f15937b = i;
    }

    public static void setLogging(String str) {
        if (str == null) {
            return;
        }
        C6330i.f15936a = str;
    }

    public static void setLogging(int i, String str) {
        if (str == null || i <= 0) {
            return;
        }
        C6330i.f15937b = i;
        C6330i.f15936a = str;
    }

    public static void leaveBreadcrumb(String str) {
        if (Harvest.addActionAndInteraction("<_TY_C_API>" + str) && C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" leaveBreadcrumb = " + str);
        }
    }

    public static void setUserIdentifier(String str) {
        if (str == null) {
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10127o(" setUserIdentifier = " + ((Object) null));
                return;
            }
            return;
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
            log.mo10122a(" userId must be m than 0,less than 64 ,remove it ");
        }
        C6638h.m8963w().m9003g(str);
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" setUserIdentifier = " + str);
        }
    }

    public static void setPageLoadingEndTime(Class cls) {
        if (cls == null) {
            return;
        }
        C6274f.m10698a(cls);
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" setPageLoadingEndTime className = " + cls);
        }
    }

    public NBSAppAgent setChannelID(String str) {
        ApplicationInformation.customSetChannelId = str == null ? "" : str;
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" setChannelID channelID = " + str);
        }
        return this;
    }

    public NBSAppAgent closeLogForUpdateHint() {
        C6638h.m8963w().f17181x = false;
        return this;
    }

    public static void setUserCrashMessage(String str, String str2) {
        C6325e.m10396a(str, str2);
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" setUserCrashMessage key = " + str + "    ----value = " + str2);
        }
    }

    public synchronized void startInApplication(Context context) {
        log.mo10122a("NBSAgent starts at application! ");
        start(context);
    }

    public NBSAppAgent setStartOption(int i) {
        C6638h.m8963w().m9058a(i, false);
        return this;
    }

    private NBSAppAgent setStartOption(int i, boolean z) {
        C6638h.m8963w().m9058a(i, z);
        return this;
    }

    public NBSAppAgent isCustomAppStart(boolean z) {
        C6638h.m8963w().m8971p(z);
        return this;
    }

    public static void setCustomOnResumeEndIns(String str) {
        NBSAppInstrumentation.HybridOnResumeEndIns(str);
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" setCustomOnResumeEndIns className = " + str);
        }
    }

    @RequiresApi(api = 4)
    public synchronized void start(Context context) {
        if (this.apmIsRunning) {
            log.mo10119b("NBSAgent is already running.");
            return;
        }
        TimingLogger timingLogger = new TimingLogger("NBSAgent", "NBSAppAgent start");
        C6394f.m10149a(new C6395g());
        if (System.currentTimeMillis() < context.getSharedPreferences(C6638h.m8981m(context.getPackageName()), 0).getLong("disabledTimeout", 0L)) {
            log.mo10119b("NBSAgent disabled.");
        } else if (hitPercent()) {
            boolean m8729b = C6653u.m8729b(context);
            C6638h.f17114n = m8729b ? 0 : 1;
            if (!m8729b && TextUtils.isEmpty(C6287e.f15694c)) {
                log.mo10122a("is not main process!  is not deviceId !  NBSAgent not start!");
            } else if (onlyMainProcess && !m8729b) {
                log.mo10122a("is not main process! NBSAgent not start!");
            } else {
                log.mo10119b("NBSAgent start.");
                timingLogger.addSplit("setLog");
                if (isInstrumented()) {
                    log.mo10119b("NBSAgent enabled.");
                    log.mo10119b(MessageFormat.format("NBSAgent V{0}", NBSAgent.getVersion()));
                    C6638h.m8963w().m9025b(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS));
                    C6638h.m8963w().m9056a(context);
                    C6638h.m8963w().m8990j(this.locationServicesEnabled);
                    C6638h.m8963w().m8983l(this.ssl);
                    if (C6653u.m8720c(context)) {
                        log.mo10119b("Android app is debugMode !");
                        C6638h.m8963w().m9058a(511, true);
                    }
                    setStartOption(12, true);
                    if (C6638h.m8963w().m8979n()) {
                        C6638h.m8963w().m9064W();
                    }
                    NBSAndroidAgentImpl nBSAndroidAgentImpl = new NBSAndroidAgentImpl(context);
                    NBSAgent.setImpl(nBSAndroidAgentImpl);
                    if (this.crashReportEnabled) {
                        enableCrashReporting(context);
                        if (C6459b.m9936d()) {
                            C6458a.m9944a(C6458a.f16323b, 0);
                            NativeCrashInterface.initNativeCrash();
                            C6396h.m10125q("NativeCrashInterface.initNativeCrash :  true  !");
                            C6458a.m9944a(C6458a.f16323b, 1);
                        }
                    }
                    nBSAndroidAgentImpl.m9177a(context);
                    this.apmIsRunning = true;
                    timingLogger.addSplit("instrument");
                    timingLogger.dumpToLog();
                    return;
                }
                log.mo10119b("NBSAgent not enabled.");
            }
        }
    }

    private boolean hitPercent() {
        return new Random().nextInt(100) + 1 <= this.ratio;
    }

    public NBSAppAgent setMultiRedirectHost(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            log.mo10119b("setMultiRedirectHost error");
            return this;
        }
        HarvestConnection.redirectHostList = strArr;
        HarvestConnection.redirectHost = HarvestConnection.redirectHostList[0];
        return this;
    }

    public NBSAppAgent setRedirectHost(String str) {
        HarvestConnection.redirectHostList = null;
        HarvestConnection.redirectHost = str;
        return this;
    }

    @Deprecated
    public NBSAppAgent setDefaultCert(boolean z) {
        HarvestConnection.IsCertEnabled = z;
        return this;
    }

    public static void setUserProfile(String str, String str2, Long l, String str3, String str4, Map<String, Object> map) {
        C6500f.m9742a().m9740a(str, str2, l, str3, str4, map);
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" setUserProfile userID = " + str + "\n userName " + str2 + "\n signupTime " + l + "\n provice " + str3 + "\n city " + str4 + "\n Map " + C6653u.m8735a(map).toString());
        }
    }

    public static void onEvent(String str, String str2, Map<String, Object> map) {
        C6495b.m9761a().m9760a(str, str2, map);
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" setUserProfile eventId = " + str + "\n eventTag " + str2 + "\n Map " + C6653u.m8735a(map).toString());
        }
    }

    private void enableCrashReporting(Context context) {
        C6328g.m10392a(context);
        C6323d.m10408a(C6328g.m10394a());
    }

    public static void beginTracer(String str) {
        try {
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10127o(" beginTracer  = " + str);
            }
            CustomTracer.beginTracer(C6653u.m8731b(), str);
        } catch (Throwable th) {
            C6396h.m10131k("NBSAppAgent  beginTracer has an error : " + th);
        }
    }

    public static void endTracer(String str) {
        try {
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10127o(" endTracer  = " + str);
            }
            CustomTracer.endTracer(C6653u.m8731b(), str);
        } catch (Throwable th) {
            C6396h.m10131k("NBSAppAgent  beginTracer has an error : " + th);
        }
    }

    public static void onEvent(String str) {
        C6495b.m9761a().m9760a(str, null, null);
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o(" onEvent  = " + str);
        }
    }

    public static void setWebviewProgressControlVol(int i) {
        NBSWebChromeX5Client.progressControl = i;
        NBSWebChromeClient.progressControl = i;
    }

    @RequiresApi(api = 4)
    public static void setViewId(View view, String str) {
        if (str == null) {
            return;
        }
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        if (view != null) {
            view.setTag(214748364, str);
        }
    }

    public NBSAppAgent setVersionName(String str) {
        C6638h.m8963w().f17180f = str;
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o("setVersionName  : versionName = " + str);
        }
        return this;
    }

    public static void reportError(String str, Exception exc, Map<String, Object> map) {
        try {
            log.mo10122a("reportError(String message, Exception e, Map<String, Object> metaData)");
            reportErrorType(str, exc, map, 2);
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10143a(exc, "reportErrorType   message = " + str + "\nmetaData = " + C6653u.m8735a(map).toString(), new Object[0]);
            }
        } catch (Throwable unused) {
            C6458a.m9944a(C6458a.f16322a, 0);
            log.mo10116d("error happened in reportError(String message, Exception e, Map<String, Object> metaData)");
        }
    }

    public static void reportError(String str, Map<String, Object> map) {
        try {
            log.mo10122a("reportError(String message, Map<String, Object> metaData)");
            reportErrorType(str, new Exception(C6653u.m8707e(2)), map, 1);
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10127o("reportErrorType   message = " + str + "\nmetaData = " + C6653u.m8735a(map).toString());
            }
        } catch (Throwable th) {
            C6458a.m9944a(C6458a.f16322a, 0);
            log.mo10121a("error happened in reportError(String message, Map<String, Object> metaData)", th);
        }
    }

    private static void reportErrorType(String str, Exception exc, Map<String, Object> map, int i) {
        if (onlyMainProcess && !C6653u.m8729b(C6328g.f15928b)) {
            log.mo10122a("非主进程不采集自定义崩溃数据...");
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10140b("非主进程不采集自定义崩溃数据...", new Object[0]);
                return;
            }
            return;
        }
        try {
            if (C6459b.m9937c()) {
                log.mo10122a("reportErrorType");
                C6311a.m10483c().m10486a(new C6314d.C6316a().m10459a(str).m10457a(exc).m10456a(map).m10454b(i).m10460a(2).m10455b().m10461a());
                if (C6638h.m8963w().m8979n()) {
                    C6396h.m10141b("reportErrorType type = " + i + "\n message = " + str + "\nmetaData = " + C6653u.m8735a(map).toString(), exc);
                }
            }
        } catch (Throwable th) {
            C6458a.m9944a(C6458a.f16322a, 0);
            log.mo10121a("error happened in reportError(String message, Exception e, Map<String, Object> metaData)", th);
        }
    }

    public static void startWebRequestTiming(Map<String, Object> map) {
        try {
            String str = (String) map.get("requestTag");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            NBSTransactionState nBSTransactionState = new NBSTransactionState();
            nBSTransactionState.setUrl((String) map.get("url"));
            nBSTransactionState.resetStartTimeStamp(((Long) map.get("startTime")).longValue());
            webTimings.put(str, nBSTransactionState);
        } catch (Throwable th) {
            log.mo10121a("startWebRequestTiming error", th);
        }
    }

    public static void stopWebRequestTiming(Map<String, Object> map) {
        NBSTransactionState nBSTransactionState;
        try {
            String str = (String) map.get("requestTag");
            if (TextUtils.isEmpty(str) || (nBSTransactionState = webTimings.get(str)) == null) {
                return;
            }
            int intValue = ((Integer) map.get("dns")).intValue();
            if (intValue >= 0) {
                nBSTransactionState.setDnsElapse(intValue);
            }
            nBSTransactionState.setIpAddress(map.get("ip") == null ? "" : (String) map.get("ip"));
            nBSTransactionState.setStatusCode(map.get("responseCode") == null ? 0 : ((Integer) map.get("responseCode")).intValue());
            nBSTransactionState.endWithEndTimeStamp(map.get("endTime") == null ? 0L : ((Long) map.get("endTime")).longValue());
            if (nBSTransactionState.isError()) {
                nBSTransactionState.setErrorDataInfo("", new HashMap(), "");
            }
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("reportNetwork add transactionState to queue:" + nBSTransactionState.toString());
            C6412c c6412c = new C6412c(nBSTransactionState);
            if (nBSTransactionState.isError()) {
                c6412c.m10036a("");
            }
            C6648q.m8781a(c6412c);
            webTimings.remove(str);
        } catch (Throwable th) {
            log.mo10121a("error stopWebRequestTiming ", th);
        }
    }

    public static void reportErrorFlutter(String str, Map<String, Object> map, String str2, int i) {
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10122a("reportErrorFlutter messge:" + str + ", errorStackTrace:" + str2 + ", type:" + i);
        reportErrorWebInner(str, map, str2, i, "flutter");
    }

    public static void reportErrorWeb(String str, Map<String, Object> map, String str2, int i) {
        reportErrorWebInner(str, map, str2, i, "com.facebook.react.JavaScript");
    }

    private static void reportErrorWebInner(String str, Map<String, Object> map, String str2, int i, String str3) {
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10122a("reportErrorWeb messge:" + str + ", errorStackTrace:" + str2 + ", type:" + i);
        try {
            log.mo10122a("reportErrorWeb");
            if (C6459b.m9937c()) {
                log.mo10122a("NBSGlobalSettings.getInstance().isUserAction_Enable() is true");
                C6311a.m10483c().m10486a(new C6314d.C6316a().m10459a(str).m10458a(str2, str3).m10456a(map).m10454b(i).m10460a(3).m10455b().m10461a());
                if (C6638h.m8963w().m8979n()) {
                    C6396h.m10127o("reportErrorType type = " + i + "\n message = " + str + "\nmetaData = " + C6653u.m8735a(map).toString() + "\nerrorStackTrace = " + str2);
                }
            }
        } catch (Throwable th) {
            C6458a.m9944a(C6458a.f16322a, 0);
            log.mo10121a("error happened in reportError(String message, Exception e, Map<String, Object> metaData)", th);
        }
    }

    public static void enterAction(String str, int i, String str2) {
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10122a("enterAction key:" + i + ", actionName:" + str + ", enterJsStamp:" + str2);
        if (C6638h.m8963w().m9038ah()) {
            C6259j.m10767a(C6295m.EnumC6298c.Clicked, str, true, -1, Long.valueOf(str2).longValue(), i);
        }
    }

    public static void enterActionFlutter(String str, int i, String str2) {
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10122a("enterActionFlutter key:" + i + ", actionName:" + str + ", enterJsStamp:" + str2);
        if (C6638h.m8963w().m9038ah()) {
            C6259j.m10767a(C6295m.EnumC6298c.CustomAction, str, true, -1, Long.valueOf(str2).longValue(), i);
        }
    }

    public static void leaveAction(int i, String str) {
        if (C6638h.m8963w().m9038ah()) {
            C6259j.m10769a(i, Long.valueOf(str).longValue());
        }
    }

    public static void leaveAction(int i, String str, String str2, Map<String, Object> map) {
        if (C6638h.m8963w().m9038ah()) {
            C6259j.m10768a(i, Long.valueOf(str).longValue(), str2, map);
        }
    }

    public static void leaveActionFlutterPage(int i, String str, String str2, Map<String, Object> map) {
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10122a("leaveActionFlutterPage happened leaveJsStamp:" + str);
        C6258i m10768a = C6259j.m10768a(i, Long.valueOf(str).longValue(), str2, null);
        C6259j.f15587b = m10768a;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            log.mo10122a("leaveActionFlutterPage happened in mainLooper");
        } else {
            log.mo10122a("leaveActionFlutterPage happened not in mainLooper");
        }
        try {
            log.mo10122a("leaveActionFlutterPage add to data");
            C6259j.f15587b = null;
            if (m10768a != null) {
                C6273e c6273e = new C6273e(0, m10768a.f15575g, m10768a);
                if (!c6273e.m10702f()) {
                    C6396h.m10131k("add leaveActionFlutterPage data in harvest data pageData: " + c6273e.asJson().toString());
                    HarvestData.successPageDatas.mo10631a((HarvestableArray) c6273e);
                    return;
                }
                C6396h.m10131k("not add data in harvest data because crossTheBorder is true");
            }
        } catch (Throwable unused) {
            log.mo10116d("thread execute error");
        }
    }

    public static void setCustomPageName(String str) {
        NBSAppInstrumentation.setCustomPageName(str);
        NBSFragmentSession.custPageName = str;
    }

    public NBSAppAgent setProxy(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        InitUrlConnection.proxy = str;
        InitUrlConnection.port = i;
        return this;
    }

    public NBSAppAgent isHookWebChromeClient(boolean z) {
        C6638h.m8963w().m9018c(z);
        return this;
    }

    public static void customActionStart(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.i("NBSAgent", "actionName 为空 , 自定义action失效.");
        } else if (C6638h.m8963w().m9038ah()) {
            C6250b.m10856a(str);
        }
    }

    public static void customActionEnd(String str, String str2, Map<String, Object> map) {
        if (TextUtils.isEmpty(str)) {
            Log.i("NBSAgent", "actionName 为空 , 自定义action失效.");
        } else if (C6638h.m8963w().m9038ah()) {
            C6250b.m10855a(str, str2, map);
        }
    }

    public static String getTingyunDeviceId() {
        return new C6646o(C6638h.m8963w().m9076K()).m8822j();
    }

    public static Map<String, Boolean> getSwitchConfig() {
        HashMap hashMap = new HashMap();
        hashMap.put("enabled", Boolean.valueOf(C6638h.m8963w().m8976o()));
        hashMap.put("crashReporting", Boolean.valueOf(C6638h.m8963w().m9042ad()));
        hashMap.put("webRequest", Boolean.valueOf(C6638h.m8963w().m9063X()));
        hashMap.put("action", Boolean.valueOf(C6638h.m8963w().m9045aa()));
        hashMap.put("ui", Boolean.valueOf(C6638h.m8963w().m9038ah()));
        hashMap.put("logLevel", false);
        return hashMap;
    }

    public static String getCrossProcessHeaderValue() {
        return C6638h.m8963w().m9032an() ? C6638h.m8963w().m9033am() : "";
    }

    public static void setAgentErrorEventFeedBack(TingyunErrorEventFeedBack tingyunErrorEventFeedBack) {
        C6328g.m10394a().m10389a(tingyunErrorEventFeedBack);
    }

    public NBSAppAgent setIngoreScheme(String str) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        schemeIgnore = str;
        return this;
    }

    public static String getLastCrashUUID() {
        return C6653u.m8691k();
    }

    public static void setBusinessLine(String str, String str2) {
        C6638h.m8963w().m9049a(ConfigurationName.dataTagKey, str2);
    }

    public static void wrapLoadDataWithBaseURL(WebView webView, String str, String str2, String str3, String str4, String str5) {
        webView.loadDataWithBaseURL(str, C6459b.m9934f() ? C6516c.m9564c(str2) : str2, str3, str4, str5);
    }

    public static void setWebViewUrlRealAddress(String str) {
        C6638h.m8963w().m9019c(str);
    }
}
