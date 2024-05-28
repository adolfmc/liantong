package com.networkbench.agent.impl.harvest;

import android.text.TextUtils;
import com.networkbench.agent.impl.crash.p250b.C6318a;
import com.networkbench.agent.impl.crash.p250b.C6320b;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p243c.C6302f;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p267m.C6496c;
import com.networkbench.agent.impl.p267m.C6502h;
import com.networkbench.agent.impl.p268n.p269a.C6504a;
import com.networkbench.agent.impl.p268n.p269a.C6508d;
import com.networkbench.agent.impl.p268n.p269a.C6514f;
import com.networkbench.agent.impl.plugin.RunnableC6591i;
import com.networkbench.agent.impl.plugin.p274e.C6561c;
import com.networkbench.agent.impl.plugin.p274e.C6562d;
import com.networkbench.agent.impl.plugin.p274e.C6565g;
import com.networkbench.agent.impl.socket.AbstractC6619p;
import com.networkbench.agent.impl.socket.C6620q;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6648q;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Harvest {
    public static volatile String currentActivityName;
    public C6320b actionAndInteractions;

    /* renamed from: b */
    protected HarvestData f16268b;
    private HarvestConfiguration configuration = HarvestConfiguration.getDefaultHarvestConfiguration();
    private HarvestConnection harvestConnection;
    private HarvestTimer harvestTimer;
    private Harvester harvester;
    private static final InterfaceC6393e log = C6394f.m10150a();

    /* renamed from: a */
    protected static Harvest f16267a = new Harvest();
    private static final Collection<HarvestLifecycleAware> unregisteredLifecycleListeners = new ArrayList();
    private static final HarvestableCache activityTraceCache = new HarvestableCache();
    public static C6502h mSessionInfo = new C6502h();

    public static void initialize() {
        f16267a.initializeHarvester();
        registerUnregisteredListeners();
    }

    public void initializeHarvester() {
        createHarvester();
        this.harvester.setConfiguration(f16267a.getConfiguration());
    }

    public static void setPeriod(long j) {
        f16267a.getHarvestTimer().setPeriod(j);
    }

    public static void start() {
        f16267a.getHarvestTimer().start();
        mSessionInfo.m9727a((int) TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS));
    }

    public static void stop() {
        Harvest harvest = f16267a;
        if (harvest != null && harvest.getHarvestTimer() != null) {
            f16267a.getHarvestTimer().stop();
        } else {
            log.mo10115e("instance == null || instance.getHarvestTimer() == null");
        }
    }

    public static void harvestNow() {
        try {
            RunnableC6591i pluginTimer = f16267a.getPluginTimer();
            if (pluginTimer != null) {
                pluginTimer.m9303d();
                pluginTimer.m9302e();
            }
            HarvestTimer harvestTimer = f16267a.getHarvestTimer();
            if (harvestTimer != null) {
                harvestTimer.tickNow();
            }
            mSessionInfo.m9724b((int) TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS));
            C6496c.m9753a(mSessionInfo);
            mSessionInfo.m9721d();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("harvestNow has a Exception: " + th.toString());
        }
    }

    public static void setInstance(Harvest harvest) {
        f16267a = harvest;
    }

    public void createHarvester() {
        if (this.harvestConnection == null) {
            this.harvestConnection = new HarvestConnection();
        }
        if (this.f16268b == null) {
            this.f16268b = new HarvestData();
        }
        this.harvester = new Harvester();
        this.harvester.setHarvestConnection(this.harvestConnection);
        this.harvester.setHarvestData(this.f16268b);
        this.harvestTimer = new HarvestTimer(this.harvester);
    }

    public void shutdownHarvester() {
        this.harvestTimer = null;
        this.harvester = null;
        this.f16268b.reset();
    }

    public static void shutdown() {
        if (isInitialized()) {
            stop();
            f16267a.shutdownHarvester();
        }
    }

    public static void addHttpError(ActionData actionData, C6281e c6281e) {
        if (C6565g.f16791c && c6281e.m10651g().startsWith("http")) {
            C6561c c6561c = new C6561c(actionData, c6281e);
            C6565g.m9380a(c6561c);
            c6561c.m9372d();
            return;
        }
        addHttpErrorForScene(c6281e);
        addHttpTransaction(actionData);
    }

    public static void addHttpErrorForScene(C6281e c6281e) {
        try {
            Harvester harvester = f16267a.getHarvester();
            if (!f16267a.shouldCollectNetworkErrors() || isDisabled() || !isHttp_network_enabled() || f16267a.getHarvestData() == null) {
                return;
            }
            C6302f httpErrors = f16267a.getHarvestData().getHttpErrors();
            if (harvester != null) {
                harvester.expireHttpErrors();
            }
            if (httpErrors.m10524c() >= f16267a.getConfiguration().getErrs()) {
                httpErrors.m10528a(0);
            }
            httpErrors.m10527a(c6281e);
        } catch (Exception unused) {
        }
    }

    public static int addHttpTransaction(ActionData actionData) {
        try {
            if (isDisabled() || !isHttp_network_enabled() || f16267a.getHarvestData() == null) {
                return -2;
            }
            if (actionData.calcTotalTime() >= C6638h.f17103a) {
                return 0;
            }
            ActionDatas actionDatas = f16267a.getHarvestData().getActionDatas();
            f16267a.getHarvester().expireHttpTransactions();
            int actions = f16267a.getConfiguration().getActions();
            if (C6565g.f16790b && actionData.getStatusCode() == 200 && actionData.getUrl().startsWith("http")) {
                C6562d c6562d = new C6562d(actionData);
                C6565g.m9380a(c6562d);
                c6562d.m9372d();
            } else if (actionDatas.count() >= actions) {
                return -1;
            } else {
                actionDatas.add(actionData);
            }
            C6396h.m10137e("addHttpTransaction  SceneRegisterObserver.after_net : " + C6565g.f16790b);
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static void addHttpTransactionForScene(ActionData actionData) {
        if (f16267a.getHarvestData().getActionDatas().count() >= f16267a.getConfiguration().getActions()) {
            C6648q.m8781a(actionData);
        } else if (f16267a.getHarvestData() != null) {
            f16267a.getHarvestData().getActionDatas().add(actionData);
        }
    }

    public static void addSocketDatasInfo(AbstractC6619p abstractC6619p) {
        if (!isDisabled() && C6638h.m8963w().m9062Y()) {
            C6620q socketDatas = f16267a.getHarvestData().getSocketDatas();
            int actions = f16267a.getConfiguration().getActions();
            if (socketDatas.m9205c() >= actions) {
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10115e("Maximum number of transactions (" + actions + ") reached. socketdata dropped.");
            } else if (abstractC6619p.m9210m() > 150000) {
                log.mo10122a("timeelapsed is over 150000 ");
            } else if (abstractC6619p != null) {
                socketDatas.m9208a(abstractC6619p);
            }
        }
    }

    public static void addHarvestListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            log.mo10116d("Harvest: Argument to addHarvestListener cannot be null.");
        } else if (!isInitialized()) {
            if (isUnregisteredListener(harvestLifecycleAware)) {
                return;
            }
            addUnregisteredListener(harvestLifecycleAware);
        } else {
            f16267a.getHarvester().addHarvestListener(harvestLifecycleAware);
        }
    }

    public static void removeHarvestListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            log.mo10116d("Harvest: Argument to removeHarvestListener cannot be null.");
        } else if (!isInitialized()) {
            if (isUnregisteredListener(harvestLifecycleAware)) {
                removeUnregisteredListener(harvestLifecycleAware);
            }
        } else {
            f16267a.getHarvester().removeHarvestListener(harvestLifecycleAware);
        }
    }

    public static boolean isInitialized() {
        Harvest harvest = f16267a;
        return (harvest == null || harvest.getHarvester() == null) ? false : true;
    }

    public static int getActivityTraceCacheSize() {
        return activityTraceCache.getSize();
    }

    public static boolean shouldCollectActivityTraces() {
        if (isDisabled()) {
            return false;
        }
        return !isInitialized() || f16267a.getConfiguration().getUiTraces() > 0;
    }

    private static void addUnregisteredListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            return;
        }
        synchronized (unregisteredLifecycleListeners) {
            unregisteredLifecycleListeners.add(harvestLifecycleAware);
        }
    }

    private static void removeUnregisteredListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            return;
        }
        synchronized (unregisteredLifecycleListeners) {
            unregisteredLifecycleListeners.remove(harvestLifecycleAware);
        }
    }

    private static void registerUnregisteredListeners() {
        for (HarvestLifecycleAware harvestLifecycleAware : unregisteredLifecycleListeners) {
            addHarvestListener(harvestLifecycleAware);
        }
        unregisteredLifecycleListeners.clear();
    }

    private static boolean isUnregisteredListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            return false;
        }
        return unregisteredLifecycleListeners.contains(harvestLifecycleAware);
    }

    public HarvestTimer getHarvestTimer() {
        return this.harvestTimer;
    }

    public RunnableC6591i getPluginTimer() {
        return this.harvester.getPluginTimer();
    }

    public static Harvest getInstance() {
        return f16267a;
    }

    public Harvester getHarvester() {
        return this.harvester;
    }

    public HarvestData getHarvestData() {
        return this.f16268b;
    }

    public HarvestConfiguration getConfiguration() {
        return this.configuration;
    }

    public HarvestConnection getHarvestConnection() {
        return this.harvestConnection;
    }

    public boolean shouldCollectNetworkErrors() {
        return this.configuration.isEnableErrTrace();
    }

    public void setConfiguration(HarvestConfiguration harvestConfiguration) {
        try {
            this.configuration.reconfigure(harvestConfiguration);
            try {
                this.harvestTimer.setPeriod(TimeUnit.MILLISECONDS.convert(this.configuration.getInterval(), TimeUnit.SECONDS));
            } catch (Throwable unused) {
            }
            C6638h.m8963w().m9057a(this.configuration.getInterval());
            C6638h.m8963w().m8977n(this.configuration.isPluginSwitch());
            this.harvester.setConfiguration(this.configuration);
        } catch (Throwable unused2) {
        }
    }

    public static void setHarvestConfiguration(HarvestConfiguration harvestConfiguration) {
        if (!isInitialized()) {
            log.mo10116d("Cannot configure Harvester before initialization.");
            new Exception().printStackTrace();
            return;
        }
        f16267a.setConfiguration(harvestConfiguration);
    }

    public static boolean isDisabled() {
        try {
            if (isInitialized() && f16267a.getHarvester() != null) {
                return f16267a.getHarvester().isDisabled();
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public static synchronized void addActionAndInteraction(String str, String str2, String str3) {
        synchronized (Harvest.class) {
            if (C6638h.m8963w().m9043ac() < 0) {
                return;
            }
            C6318a c6318a = new C6318a(currentActivityName, str, str3, str2);
            if (f16267a.actionAndInteractions != null) {
                f16267a.actionAndInteractions.m10428a(c6318a);
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10122a("add actionAndInteraction " + currentActivityName + "," + str + "," + str2 + "," + str3);
            }
        }
    }

    public static synchronized boolean addActionAndInteraction(String str) {
        synchronized (Harvest.class) {
            if (TextUtils.isEmpty(str)) {
                if (C6638h.m8963w().m8979n()) {
                    C6396h.m10140b(" leaveBreadcrumb is Empty ! ", new Object[0]);
                }
                return false;
            }
            if (str.length() > 110) {
                str = str.substring(0, 110);
                if (C6638h.m8963w().m8979n()) {
                    C6396h.m10140b(" leaveBreadcrumb 超过限定超短, 截取前100单位  : " + str, new Object[0]);
                }
            }
            if (C6638h.m8963w().m9068S() <= 0) {
                if (C6638h.m8963w().m8979n()) {
                    C6396h.m10140b("Breadcrumb was Add failed  because the MobileAgentApp not init", new Object[0]);
                }
                return false;
            }
            C6318a c6318a = new C6318a(str, null, null, null);
            if (f16267a.actionAndInteractions != null) {
                f16267a.actionAndInteractions.m10428a(c6318a);
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10122a("Breadcrumb: " + str + " was added to the breadcrumb list");
            }
            return true;
        }
    }

    public static boolean isHttp_network_enabled() {
        try {
            return C6638h.m8963w().m9063X();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isUI_enabled() {
        return C6638h.m8963w().m9038ah();
    }

    public static boolean isUser_action_enabled() {
        return C6638h.m8963w().m9045aa();
    }

    public static boolean isAnr_enabled() {
        return C6638h.m8963w().m9061Z();
    }

    public static boolean isCrash_enabled() {
        return C6638h.m8963w().m9042ad();
    }

    public static boolean isCdn_enabled() {
        return C6638h.m8963w().m9044ab();
    }

    public static boolean isWebView_enabled() {
        return C6638h.m8963w().m9040af();
    }

    public void initCrashActions() {
        if (this.actionAndInteractions == null) {
            this.actionAndInteractions = new C6320b();
        }
    }

    public static int getActionFailureThreshold() {
        return f16267a.getConfiguration().getActionFailureThreshold();
    }

    public static void addPagePerfData(C6508d c6508d) {
        if (!isDisabled() && isWebView_enabled()) {
            C6514f webViewPerfMetrics = f16267a.getHarvestData().getWebViewPerfMetrics();
            int actions = f16267a.getConfiguration().getActions();
            if (webViewPerfMetrics.m9577b().m9698b() >= actions) {
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10115e("Maximum number of webviewTransaction (" + actions + ") reached. WebViewTransaction dropped.");
                return;
            }
            log.mo10122a("addPagePerfData begin");
            webViewPerfMetrics.m9577b().m9699a((HarvestableArray) c6508d);
        }
    }

    public static void addJsError(C6504a c6504a) {
        if (!isDisabled() && isWebView_enabled()) {
            C6514f webViewPerfMetrics = f16267a.getHarvestData().getWebViewPerfMetrics();
            int actions = f16267a.getConfiguration().getActions();
            if (webViewPerfMetrics.m9577b().m9698b() >= actions) {
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10115e("Maximum number of webviewTransaction (" + actions + ") reached. WebViewTransaction dropped.");
                return;
            }
            InterfaceC6393e interfaceC6393e2 = log;
            interfaceC6393e2.mo10122a("add JsError begin: jserror:" + c6504a.toString());
            webViewPerfMetrics.m9576c().m9699a((HarvestableArray) c6504a);
        }
    }
}
