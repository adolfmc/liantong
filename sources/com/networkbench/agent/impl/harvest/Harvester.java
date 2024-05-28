package com.networkbench.agent.impl.harvest;

import android.os.Build;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.crash.C6328g;
import com.networkbench.agent.impl.crash.p249a.C6311a;
import com.networkbench.agent.impl.crash.p249a.C6312b;
import com.networkbench.agent.impl.crash.p249a.C6313c;
import com.networkbench.agent.impl.harvest.p260a.C6450m;
import com.networkbench.agent.impl.harvest.p260a.EnumC6455q;
import com.networkbench.agent.impl.harvest.p261b.C6458a;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.harvest.type.Harvestable;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.instrumentation.NetworkLibInit;
import com.networkbench.agent.impl.p241b.C6241k;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p243c.C6302f;
import com.networkbench.agent.impl.p243c.p244a.C6250b;
import com.networkbench.agent.impl.p243c.p244a.C6253e;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p245b.C6262a;
import com.networkbench.agent.impl.p243c.p246c.C6264a;
import com.networkbench.agent.impl.p243c.p247d.C6275g;
import com.networkbench.agent.impl.p243c.p247d.C6276h;
import com.networkbench.agent.impl.p243c.p247d.C6277i;
import com.networkbench.agent.impl.p243c.p248e.C6283b;
import com.networkbench.agent.impl.p243c.p248e.C6287e;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p262i.C6468e;
import com.networkbench.agent.impl.p262i.EnumC6467d;
import com.networkbench.agent.impl.plugin.EnumC6558e;
import com.networkbench.agent.impl.plugin.RunnableC6591i;
import com.networkbench.agent.impl.plugin.p270a.C6533c;
import com.networkbench.agent.impl.plugin.p274e.C6564f;
import com.networkbench.agent.impl.plugin.p274e.C6565g;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6637g;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6640i;
import com.networkbench.agent.impl.util.C6648q;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonParser;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Harvester {

    /* renamed from: a */
    protected boolean f16279a;

    /* renamed from: c */
    RunnableC6591i f16281c;
    private HarvestConnection harvestConnection;
    private HarvestData harvestData;
    private final InterfaceC6393e log = C6394f.m10150a();
    private State state = State.UNINITIALIZED;

    /* renamed from: b */
    protected long f16280b = 60;
    private HarvestConfiguration configuration = HarvestConfiguration.getDefaultHarvestConfiguration();
    private final Collection<HarvestLifecycleAware> harvestListeners = new ArrayList();
    private long freeTime = -this.configuration.getInterval();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum State {
        UNINITIALIZED,
        DISCONNECTED,
        REDIRECTED,
        CONNECTED,
        DISABLED
    }

    public RunnableC6591i getPluginTimer() {
        return this.f16281c;
    }

    public void start() {
        fireOnHarvestStart();
    }

    public void stop() {
        fireOnHarvestStop();
    }

    /* renamed from: a */
    protected void m9983a() {
        this.harvestConnection.setConnectInformation(new ConnectInformation());
        this.harvestConnection.setApplicationToken(C6638h.m8963w().m9075L());
        this.harvestConnection.useSsl(C6638h.m8963w().m9069R());
        m9982a(State.DISCONNECTED);
        m9975f();
    }

    /* renamed from: b */
    protected void m9980b() {
        if (isInitMobileAgentInvoke()) {
            InterfaceC6393e interfaceC6393e = this.log;
            interfaceC6393e.mo10117c("Skipping connect call, saved state is available: " + this.harvestConnection.getApplicationToken());
            fireOnHarvestConnected();
            m9982a(State.CONNECTED);
            m9975f();
            return;
        }
        HarvestResponse redirectHost = this.harvestConnection.getRedirectHost();
        if (redirectHost == null) {
            this.log.mo10116d("Unable to connect to the Redirect.");
        } else if (redirectHost.isOK()) {
            String resultMessage = redirectHost.getResultMessage();
            C6640i.f17186a = resultMessage;
            this.harvestConnection.setCollectorHost(resultMessage);
            this.harvestConnection.setSoDisable(redirectHost.isSoDisabled());
            C6637g.m9090a(C6450m.m9963a().f16296b);
            C6637g.m9087b(C6450m.m9963a().f16295a);
            m9982a(State.REDIRECTED);
            m9975f();
        }
    }

    private boolean isInitMobileAgentInvoke() {
        HarvestConnection harvestConnection = this.harvestConnection;
        return (harvestConnection == null || TextUtils.isEmpty(harvestConnection.getApplicationToken())) ? false : true;
    }

    /* renamed from: c */
    protected void m9978c() {
        if (isInitMobileAgentInvoke()) {
            InterfaceC6393e interfaceC6393e = this.log;
            interfaceC6393e.mo10117c("Skipping connect call, saved state is available: " + this.harvestConnection.getApplicationToken());
            fireOnHarvestConnected();
            m9982a(State.CONNECTED);
            m9975f();
            return;
        }
        restFreeTime();
        HarvestResponse sendConnect = this.harvestConnection.sendConnect();
        if (sendConnect == null) {
            this.log.mo10116d("Unable to connect to the Collector.");
            return;
        }
        HarvestConfiguration configuration = sendConnect.getConfiguration();
        if (configuration == null) {
            processInitError(sendConnect);
            fireOnHarvestDeviceIdError();
            return;
        }
        this.harvestConnection.setApplicationToken(configuration.getToken());
        if (configuration.getEnabled() != 1) {
            this.log.mo10119b("NBSAgent disabled");
            configureHarvester(configuration);
            fireOnHarvestConnected();
            m9982a(State.DISABLED);
            C6638h.m8963w().m9006f(false);
            fireOnHarvestDeviceIdError();
            return;
        }
        configureHarvester(configuration);
        C6638h.m8963w().m9020c(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS));
        InterfaceC6393e interfaceC6393e2 = this.log;
        interfaceC6393e2.mo10122a(" setLastConnectedTime : " + TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS));
        C6638h.m8963w().m8991j(configuration.getToken());
        C6638h.m8963w().m8996i(configuration.getCrashTrails());
        Harvest.getInstance().initCrashActions();
        fireOnHarvestConnected();
        C6638h.f17114n = !C6653u.m8729b(C6638h.m8963w().m9076K()) ? 1 : 0;
        if (isInitNetwork()) {
            C6638h.m8963w().m9002g(NetworkLibInit.installNetworkMonitor());
            C6638h.f17124y.mo10122a("--->init network in : initMobileAgent connect end...");
        }
        if (C6638h.m8963w().m9039ag()) {
            try {
                extensionGetTaskDefs();
            } catch (Throwable th) {
                this.log.mo10121a("extensionGetTaskDefs has error : ", th);
            }
        }
        DeviceData.oldUserId = C6638h.m8963w().m8964v();
        m9982a(State.CONNECTED);
        m9975f();
    }

    private boolean isInitNetwork() {
        return C6638h.m8963w().m9062Y() && Build.VERSION.SDK_INT < 27 && !C6638h.m8963w().m8973p();
    }

    private void processInitError(HarvestResponse harvestResponse) {
        InterfaceC6393e interfaceC6393e = this.log;
        interfaceC6393e.mo10116d("initMobileAgent errorCode is:" + harvestResponse.getErrorCode().f16273a);
        int i = harvestResponse.getErrorCode().f16273a;
        if (i != -1) {
            switch (i) {
                case 460:
                    InterfaceC6393e interfaceC6393e2 = this.log;
                    interfaceC6393e2.mo10119b("errorCode:460, Invalid key(" + C6638h.m8963w().m9086A() + ")");
                    fireOnHarvestDisabled();
                    m9982a(State.DISABLED);
                    return;
                case 461:
                    if (!HarvestConnection.isRedirectSuccess) {
                        m9982a(State.DISCONNECTED);
                    } else {
                        m9982a(State.REDIRECTED);
                    }
                    C6287e.f15695d = 0;
                    this.harvestConnection.setApplicationToken("");
                    return;
                case 462:
                    this.log.mo10116d("invalid data 462");
                    return;
                case 463:
                    this.log.mo10119b("errorCode:463, Invalid device id(did).");
                    if (!HarvestConnection.isRedirectSuccess) {
                        m9982a(State.DISCONNECTED);
                    } else {
                        m9982a(State.REDIRECTED);
                    }
                    this.harvestConnection.setApplicationToken("");
                    C6287e.f15695d = 0;
                    return;
                case 464:
                    m9982a(State.DISCONNECTED);
                    this.harvestConnection.setApplicationToken("");
                    C6287e.f15695d = 0;
                    return;
                case 465:
                    this.log.mo10116d("decrypt data failed 465");
                    return;
                default:
                    switch (i) {
                        case 470:
                            this.log.mo10119b("errorCode:470,Configuration has been overdue.");
                            if (!HarvestConnection.isRedirectSuccess) {
                                m9982a(State.DISCONNECTED);
                            } else {
                                m9982a(State.REDIRECTED);
                            }
                            this.harvestConnection.setApplicationToken("");
                            C6287e.f15695d = 0;
                            return;
                        case 471:
                            this.log.mo10119b("errorCode:471.");
                            return;
                        case 472:
                            this.log.mo10119b("errorCode:472.");
                            return;
                        default:
                            InterfaceC6393e interfaceC6393e3 = this.log;
                            interfaceC6393e3.mo10119b("An unknown error occurred when sent data to the Collector. errorcode is " + harvestResponse.getErrorCode().f16273a);
                            return;
                    }
            }
        }
        InterfaceC6393e interfaceC6393e4 = this.log;
        interfaceC6393e4.mo10116d("errorCode:-1,init error so redirect. HarvestConnection.isRedirectSuccess: " + HarvestConnection.isRedirectSuccess);
        if (HarvestConnection.isRedirectSuccess) {
            return;
        }
        m9982a(State.DISCONNECTED);
    }

    private void processError(HarvestResponse harvestResponse) {
        InterfaceC6393e interfaceC6393e = this.log;
        interfaceC6393e.mo10116d("errorCode is:" + harvestResponse.getErrorCode().f16273a);
        int i = harvestResponse.getErrorCode().f16273a;
        switch (i) {
            case 460:
                InterfaceC6393e interfaceC6393e2 = this.log;
                interfaceC6393e2.mo10119b("errorCode:460, Invalid key(" + C6638h.m8963w().m9086A() + ")");
                fireOnHarvestDisabled();
                m9982a(State.DISABLED);
                return;
            case 461:
                m9982a(State.REDIRECTED);
                C6287e.f15695d = 0;
                this.harvestConnection.setApplicationToken("");
                return;
            case 462:
                this.log.mo10116d("invalid data 462");
                return;
            case 463:
                this.log.mo10119b("errorCode:463, Invalid device id(did).");
                m9982a(State.REDIRECTED);
                this.harvestConnection.setApplicationToken("");
                C6287e.f15695d = 0;
                return;
            case 464:
                m9982a(State.DISCONNECTED);
                this.harvestConnection.setApplicationToken("");
                C6287e.f15695d = 0;
                return;
            case 465:
                this.log.mo10116d("decrypt data failed 465");
                return;
            default:
                switch (i) {
                    case 470:
                        this.log.mo10119b("errorCode:470,Configuration has been overdue.");
                        m9982a(State.REDIRECTED);
                        this.harvestConnection.setApplicationToken("");
                        C6287e.f15695d = 0;
                        return;
                    case 471:
                        this.log.mo10119b("errorCode:471.");
                        return;
                    case 472:
                        this.log.mo10119b("errorCode:472.");
                        return;
                    default:
                        InterfaceC6393e interfaceC6393e3 = this.log;
                        interfaceC6393e3.mo10119b("An unknown error occurred when sent data to the Collector. errorcode is " + harvestResponse.getErrorCode().f16273a);
                        return;
                }
        }
    }

    /* renamed from: d */
    protected void m9977d() {
        try {
            if (C6638h.m8963w().m9062Y()) {
                C6648q.m8783a();
            }
            if (isTimeToDataGather(linkFreeTime())) {
                sendOtherData();
                sendCustomData();
                sendNetworkPrefMetricsAndWebviewPrefMetrics();
                this.freeTime = 0L;
                C6468e.m9914a();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            C6565g.m9384a();
            throw th;
        }
        C6565g.m9384a();
    }

    private void sendPluginTaskCrash() {
        C6369q.m10273a().m10272a(new Runnable() { // from class: com.networkbench.agent.impl.harvest.Harvester.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (Harvester.this.sendPluginData(Harvester.this.harvestData.getPluginData().m10745a().toString())) {
                        Harvester.this.log.mo10122a("send sendPluginTaskCrash success");
                        Harvester.this.harvestData.getPluginData().m10742b();
                    }
                } catch (C6632b e) {
                    InterfaceC6393e interfaceC6393e = Harvester.this.log;
                    interfaceC6393e.mo10122a("sendPluginTaskCrash CustomException:" + e.getMessage());
                } catch (Throwable th) {
                    InterfaceC6393e interfaceC6393e2 = Harvester.this.log;
                    interfaceC6393e2.mo10116d("error send plugin data:" + th.getMessage());
                }
            }
        });
    }

    public void sendPluginData() {
        if (this.harvestData.getPluginData().f15611a.size() <= 0) {
            this.log.mo10122a("sendPluginData 取消, 因为没有集合里面没有plugin数据");
        } else if (sendPluginData(this.harvestData.getPluginData().asJsonObject().toString())) {
            this.harvestData.getPluginData().m10741c();
        }
    }

    public synchronized boolean sendPluginData(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            HarvestResponse sendData = this.harvestConnection.sendData(str, EnumC6455q.EXTENSION_SEND);
            if (sendData != null && !sendData.isUnknown()) {
                if (sendData.isError()) {
                    processError(sendData);
                    return false;
                }
                return true;
            }
            fireOnHarvestSendFailed();
            return false;
        } catch (Exception e) {
            this.log.mo10121a("sendPluginData", e);
            return false;
        }
    }

    private void sendOtherData() {
        HarvestConnection harvestConnection = this.harvestConnection;
        if (HarvestConnection.isSoDisable()) {
            HarvestData harvestData = this.harvestData;
            sendAppHostStart(HarvestData.getAppHotStartData());
        }
        if (!Harvest.isUI_enabled()) {
            fireOnHarvestComplete();
            return;
        }
        HarvestConnection harvestConnection2 = this.harvestConnection;
        if (HarvestConnection.isSoDisable()) {
            sendHttpData(HarvestData.successPageDatas);
            sendHttpData(this.harvestData.getNbsEventActions());
            if (C6638h.f17114n != 1) {
                HarvestData harvestData2 = this.harvestData;
                sendHttpData(HarvestData.getAppStartDatas());
            }
        } else {
            if (C6638h.f17114n != 1) {
                HarvestData harvestData3 = this.harvestData;
                sendPbData(HarvestData.getAppStartDatas());
            }
            HarvestData harvestData4 = this.harvestData;
            sendPbData(HarvestData.getPageDatas());
            sendPbData(this.harvestData.getNbsEventActions());
        }
        fireOnHarvestComplete();
    }

    private void sendCustomData() {
        if (this.state == State.CONNECTED && C6638h.m8963w().m8976o() && C6459b.m9937c()) {
            C6396h.m10125q("harvestData  sendCustomData  data size: " + C6311a.m10483c().m10481e());
            if (C6311a.m10483c().m10481e() <= 0) {
                C6396h.m10132j("sendCustomData, data size:" + C6311a.m10483c().m10481e());
                return;
            }
            refreshSendCustomDataState(true);
            HarvestResponse sendData = this.harvestConnection.sendData(C6311a.m10483c().toJsonString());
            if (sendData == null || sendData.isUnknown()) {
                fireOnHarvestSendFailed();
                refreshSendCustomDataState(false);
                return;
            }
            if (sendData.isError()) {
                processError(sendData);
            }
            C6311a.m10483c().m10482d();
            refreshSendCustomDataState(false);
        }
    }

    private void refreshSendState(boolean z) {
        this.harvestData.getActionDatas().isSendState = z;
        this.harvestData.getHttpErrors().f15798a = z;
        if (z) {
            return;
        }
        this.harvestData.getActionDatas().recoverData();
        this.harvestData.getHttpErrors().m10523d();
    }

    private void refreshSendCustomDataState(boolean z) {
        C6311a.m10483c().f15848c = z;
        if (z) {
            return;
        }
        C6311a.m10483c().m10480f();
    }

    private void sendSaveCustomData() {
        if (C6459b.m9937c()) {
            if (C6313c.m10474c() > 0 || C6313c.m10476b() > 0) {
                JsonArray jsonArray = new JsonArray();
                Map<String, ?> m10473d = C6313c.m10473d();
                C6311a.m10483c().m10488a().clear();
                if (m10473d != null) {
                    InterfaceC6393e interfaceC6393e = this.log;
                    interfaceC6393e.mo10122a("sendSaveCustomData  map : " + m10473d.size());
                    m10473d.putAll(C6313c.m10472e());
                } else {
                    m10473d = C6313c.m10472e();
                }
                InterfaceC6393e interfaceC6393e2 = this.log;
                interfaceC6393e2.mo10122a("sendSaveCustomData  stringMap : " + m10473d.size());
                C6313c.m10471f();
                C6313c.m10478a();
                for (String str : m10473d.keySet()) {
                    jsonArray.add(new JsonParser().parse((String) m10473d.get(str)));
                }
                InterfaceC6393e interfaceC6393e3 = this.log;
                interfaceC6393e3.mo10122a("sendSaveCustomData  : " + jsonArray.toString());
                if (jsonArray.size() > 0) {
                    if (responseDispose(this.harvestConnection.sendData(new C6312b(jsonArray).toJsonString()))) {
                        for (String str2 : m10473d.keySet()) {
                            C6313c.m10475b(str2, (String) m10473d.get(str2));
                        }
                    }
                }
            }
        }
    }

    private void sendAppHostStart(C6283b c6283b) {
        if (!C6638h.m8963w().m9065V()) {
            C6396h.m10128n("sendAppHostStart..非3.0版本不上传热启动计数!!!");
        } else if (c6283b.m10637a() > 0) {
            if (responseDispose(this.harvestConnection.sendData(c6283b.toJsonString()))) {
                return;
            }
            c6283b.m10636b();
        } else {
            C6396h.m10133i("appHotStartData size <=0 , return ");
        }
    }

    public void sendPbData(C6262a c6262a) {
        if (c6262a.m10748c() <= 0) {
            C6396h.m10132j("sendPbData, data  size:" + c6262a.m10748c());
        } else if (responseDispose(this.harvestConnection.sendDataPb(convertHarvestableArrayToString(c6262a), EnumC6467d.UPLOAD_MOBILE_DATA.m9915a(), this.harvestConnection.getApplicationToken(), "token="))) {
        } else {
            c6262a.m10750b();
        }
    }

    public void sendHttpData(C6262a c6262a) {
        C6396h.m10132j("发送数据为 : " + c6262a.f15600b + "  ........此时sdk状态为 state : " + this.state);
        if (this.state != State.CONNECTED) {
            return;
        }
        C6255f.m10803c();
        if (c6262a.m10748c() <= 0) {
            C6396h.m10132j("sendHttpData, data size:" + c6262a.m10748c());
        } else if (responseDispose(this.harvestConnection.sendData(c6262a.toJsonString()))) {
        } else {
            if (c6262a instanceof C6276h) {
                HarvestData.getPageDatas().f15599a.removeAll(c6262a.f15599a);
            }
            c6262a.m10750b();
            C6250b.m10858a();
        }
    }

    public void sendForgeUserActionItemPb(String str) {
        if (Harvest.isUI_enabled()) {
            C6253e c6253e = new C6253e(0L, str);
            c6253e.m10840a(C6253e.EnumC6254a.appCrash.m10821a());
            c6253e.m10833b(0L);
            this.harvestData.getNbsEventActions().m10750b();
            this.harvestData.getNbsEventActions().m10791a(c6253e);
            sendPbData(this.harvestData.getNbsEventActions());
            C6396h.m10132j("sent forge userAction iteme  : " + c6253e.asJsonArray().toString());
        }
    }

    public void sendForgeUserActionItemHttp(String str) {
        if (Harvest.isUI_enabled()) {
            C6253e c6253e = new C6253e(0L, str);
            c6253e.m10840a(C6253e.EnumC6254a.appCrash.m10821a());
            c6253e.m10833b(0L);
            this.harvestData.getNbsEventActions().m10750b();
            this.harvestData.getNbsEventActions().m10791a(c6253e);
            sendHttpData(this.harvestData.getNbsEventActions());
            C6396h.m10132j("sent forge userAction iteme  : " + c6253e.asJsonArray().toString());
        }
    }

    private boolean responseDispose(HarvestResponse harvestResponse) {
        if (harvestResponse == null || harvestResponse.isUnknown()) {
            fireOnHarvestSendFailed();
            return true;
        } else if (harvestResponse.isError()) {
            processError(harvestResponse);
            return true;
        } else {
            return false;
        }
    }

    private boolean isTimeToDataGather(int i) {
        if (i <= 0 && !C6638h.f17112l) {
            long j = this.freeTime + this.f16280b;
            this.freeTime = j;
            if (j < this.configuration.getIntervalOnIdle()) {
                return false;
            }
        }
        return true;
    }

    private int linkFreeTime() {
        int count = this.harvestData.getActionDatas().count() + this.harvestData.getSocketDatas().m9205c() + this.harvestData.getWebViewTransactions().m9698b() + this.harvestData.getNbsEventActions().m10748c();
        HarvestData harvestData = this.harvestData;
        int c = count + HarvestData.getAppStartDatas().m10748c();
        HarvestData harvestData2 = this.harvestData;
        return c + HarvestData.successPageDatas.m10748c() + this.harvestData.getNetworkPerfMetrics().getCellInfoCollect() + C6311a.m10483c().m10481e();
    }

    private void sendNetworkPrefMetricsAndWebviewPrefMetrics() {
        HarvestResponse sendDataPb;
        if (Harvest.isHttp_network_enabled()) {
            NetworkPerfMetrics networkPerfMetrics = this.harvestData.getNetworkPerfMetrics();
            C6533c m9505a = C6533c.m9505a();
            HarvestConfiguration harvestConfiguration = this.configuration;
            networkPerfMetrics.setCellInfoCollect(m9505a.m9488b(HarvestConfiguration.getCellInfoConfig()));
            C6638h.f17107g += networkPerfMetrics.getActionDatas().count();
            refreshSendState(true);
            fireOnHarvestFilter();
            HarvestConnection harvestConnection = this.harvestConnection;
            if (HarvestConnection.isSoDisable()) {
                sendDataPb = this.harvestConnection.sendData(checkAndSendNetworkData());
            } else {
                sendDataPb = this.harvestConnection.sendDataPb(checkAndSendNetworkData(), EnumC6467d.UPLOAD_MOBILE_DATA.m9915a(), this.harvestConnection.getApplicationToken(), "token=");
            }
            if (sendDataPb == null || sendDataPb.isUnknown()) {
                fireOnHarvestSendFailed();
                refreshSendState(false);
                return;
            }
            if (sendDataPb.isError()) {
                processError(sendDataPb);
            } else {
                if (Harvest.mSessionInfo != null) {
                    Harvest.mSessionInfo.m9722c(this.harvestData.getActionDatas().count());
                }
                this.harvestData.getSocketDatas().m9204d();
                this.harvestData.getNetworkPerfMetrics().reset();
                this.harvestData.getWebViewPerfMetrics().m9578a();
            }
            refreshSendState(false);
        }
    }

    private String convertHarvestableArrayToString(Harvestable... harvestableArr) {
        if (harvestableArr == null) {
            throw new IllegalArgumentException();
        }
        JsonArray jsonArray = new JsonArray();
        for (Harvestable harvestable : harvestableArr) {
            jsonArray.add(harvestable.asJson());
        }
        return jsonArray.toString();
    }

    /* renamed from: e */
    protected void m9976e() {
        Harvest.stop();
        fireOnHarvestDisabled();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public void m9975f() {
        this.f16279a = false;
        try {
            expireHarvestData();
            switch (this.state) {
                case UNINITIALIZED:
                    m9983a();
                    return;
                case DISCONNECTED:
                    m9980b();
                    return;
                case REDIRECTED:
                    fireOnHarvestBefore();
                    m9978c();
                    return;
                case CONNECTED:
                    sendData();
                    return;
                case DISABLED:
                    m9976e();
                    return;
                default:
                    throw new IllegalStateException();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void sendData() {
        fireOnHarvestBefore();
        fireOnHarvest();
        fireOnHarvestFinalize();
        C6648q.m8779b();
        m9977d();
    }

    private void extensionGetTaskDefs() {
        C6396h.m10137e("extensionGetTaskDefs");
        HarvestResponse sendData = this.harvestConnection.sendData(createGetTaskDefRequestJson(), EnumC6455q.EXTENSION);
        if (sendData == null || sendData.isUnknown()) {
            C6396h.m10137e("extensionGetTaskDefs response failed!");
        } else if (sendData.isError()) {
        } else {
            if (C6638h.m8963w().m9039ag()) {
                C6264a extensionConfig = sendData.getExtensionConfig();
                if (extensionConfig.f15609b.size() > 0) {
                    this.f16281c = RunnableC6591i.m9308a();
                    this.f16281c.m9307a(extensionConfig.f15608a);
                    this.f16281c.m9306a(this);
                    this.f16281c.m9304c();
                    C6565g.m9383a(extensionConfig);
                }
            } else {
                C6396h.m10137e("extensionGetTaskDefs isPlugin_enabled() :" + C6638h.m8963w().m9039ag());
            }
            if (C6565g.f16796h) {
                C6564f c6564f = new C6564f(EnumC6558e.on_task);
                C6565g.m9380a(c6564f);
                c6564f.m9372d();
            }
        }
    }

    private String createGetTaskDefRequestJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("did", new JsonPrimitive(this.configuration.getTingyunId()));
        jsonObject.add("dev", NBSAgent.getDeviceData().asJsonArray());
        jsonObject.add("app", NBSAgent.getApplicationInformation().asJsonArray());
        return jsonObject.toString();
    }

    /* renamed from: a */
    protected void m9982a(State state) {
        if (this.f16279a) {
            InterfaceC6393e interfaceC6393e = this.log;
            interfaceC6393e.mo10115e("Ignoring multiple transition: " + state);
        } else if (this.state == state) {
        } else {
            switch (this.state) {
                case UNINITIALIZED:
                    if (!stateIn(state, State.DISCONNECTED, State.REDIRECTED, state, State.CONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                case DISCONNECTED:
                    if (!stateIn(state, State.UNINITIALIZED, State.REDIRECTED, State.CONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                case REDIRECTED:
                    if (!stateIn(state, State.UNINITIALIZED, State.DISCONNECTED, State.CONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                case CONNECTED:
                    if (!stateIn(state, State.DISCONNECTED, State.REDIRECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
            changeState(state);
        }
    }

    private void configureHarvester(HarvestConfiguration harvestConfiguration) {
        this.configuration.reconfigure(harvestConfiguration);
        Harvest.setHarvestConfiguration(this.configuration);
    }

    private void changeState(State state) {
        if (state == State.CONNECTED) {
            this.log.mo10119b("connect success");
            C6458a.m9946a();
            if (C6653u.m8729b(C6638h.m8963w().m9076K())) {
                C6328g.m10394a().m10381b();
                C6241k.m10902a().m10896b();
                sendPluginTaskCrash();
            }
            sendSaveCustomData();
        }
        if (this.state == State.CONNECTED) {
            if (state == State.REDIRECTED) {
                fireOnHarvestDisconnected();
            } else if (state == State.DISABLED) {
                fireOnHarvestDisabled();
            }
        }
        this.state = state;
        this.f16279a = true;
    }

    private boolean stateIn(State state, State... stateArr) {
        for (State state2 : stateArr) {
            if (state == state2) {
                return true;
            }
        }
        return false;
    }

    public boolean isDisabled() {
        return State.DISABLED == this.state;
    }

    public void addHarvestListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            this.log.mo10116d("Can't add null harvest listener");
            new Exception().printStackTrace();
            return;
        }
        synchronized (this.harvestListeners) {
            if (this.harvestListeners.contains(harvestLifecycleAware)) {
                return;
            }
            this.harvestListeners.add(harvestLifecycleAware);
        }
    }

    public void removeHarvestListener(HarvestLifecycleAware harvestLifecycleAware) {
        synchronized (this.harvestListeners) {
            if (this.harvestListeners.contains(harvestLifecycleAware)) {
                this.harvestListeners.remove(harvestLifecycleAware);
            }
        }
    }

    public void expireHarvestData() {
        expireHttpErrors();
        expireHttpTransactions();
        expireOverLapData();
    }

    public void expireOverLapData() {
        HarvestData.successPageDatas.m10750b();
        Harvest.getInstance().getHarvestData();
        for (HarvestableArray harvestableArray : HarvestData.getPageDatas().f15599a) {
            if ((harvestableArray instanceof C6275g) && System.currentTimeMillis() - ((C6275g) harvestableArray).m10688h() >= 10000) {
                HarvestData.successPageDatas.mo10631a(harvestableArray);
            }
        }
        try {
            List<HarvestableArray> m10671b = C6277i.m10671b();
            if (m10671b.size() > 0) {
                Harvest.getInstance().getHarvestData();
                HarvestData.getPageDatas().m10681a(m10671b);
                HarvestData.successPageDatas.m10681a(m10671b);
            }
        } catch (Throwable th) {
            this.log.mo10121a("error overlaps:", th);
        }
    }

    public void expireHttpErrors() {
        try {
            C6302f httpErrors = this.harvestData.getHttpErrors();
            synchronized (httpErrors) {
                ArrayList<C6281e> arrayList = new ArrayList();
                long currentTimeMillis = System.currentTimeMillis();
                long convert = TimeUnit.MILLISECONDS.convert(this.configuration.getActionAge(), TimeUnit.SECONDS);
                for (C6281e c6281e : httpErrors.m10526b()) {
                    Long m10644n = c6281e.m10644n();
                    if (c6281e != null && m10644n != null && m10644n.longValue() < currentTimeMillis - convert) {
                        arrayList.add(c6281e);
                    }
                }
                for (C6281e c6281e2 : arrayList) {
                    if (c6281e2.m10657b() != 1 && c6281e2.m10657b() != 2 && c6281e2.m10657b() != 3) {
                        this.harvestData.getHttpErrors().m10525b(c6281e2);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public void expireHttpTransactions() {
        ActionDatas actionDatas = this.harvestData.getActionDatas();
        synchronized (actionDatas) {
            ArrayList<ActionData> arrayList = new ArrayList();
            long currentTimeMillis = System.currentTimeMillis();
            long convert = TimeUnit.MILLISECONDS.convert(this.configuration.getActionAge(), TimeUnit.SECONDS);
            for (ActionData actionData : actionDatas.getActionDatas()) {
                Long timestamp = actionData.getTimestamp();
                if (timestamp != null && timestamp.longValue() < currentTimeMillis - convert) {
                    arrayList.add(actionData);
                }
            }
            for (ActionData actionData2 : arrayList) {
                if (actionData2.getAppPhase() != 1 && actionData2.getAppPhase() != 2 && actionData2.getAppPhase() != 3) {
                    actionDatas.remove(actionData2);
                }
            }
        }
    }

    public void setHarvestConnection(HarvestConnection harvestConnection) {
        this.harvestConnection = harvestConnection;
    }

    public void setHarvestData(HarvestData harvestData) {
        this.harvestData = harvestData;
    }

    private void fireOnHarvestBefore() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestBefore();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestStart() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestStart();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestStop() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestStop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvest() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvest();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestFilter() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestFilter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestFinalize() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestFinalize();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestDisabled() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestDisabled();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestDisconnected() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestDisconnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestError() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestError();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestSendFailed() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestSendFailed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestComplete() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestConnected() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestConnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireOnHarvestDeviceIdError() {
        try {
            for (HarvestLifecycleAware harvestLifecycleAware : getHarvestListeners()) {
                harvestLifecycleAware.onHarvestDeviceIdError();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setConfiguration(HarvestConfiguration harvestConfiguration) {
        this.configuration = harvestConfiguration;
    }

    private Collection<HarvestLifecycleAware> getHarvestListeners() {
        return new ArrayList(this.harvestListeners);
    }

    private String checkAndSendNetworkData() {
        int m9698b = this.harvestData.getWebViewTransactions().m9698b();
        int m9698b2 = this.harvestData.getJsErrors().m9698b();
        C6311a.m10483c().m10487a((int) this.configuration.getInterval());
        if (m9698b + m9698b2 < 1) {
            this.log.mo10115e("stop send webviewPrefMetrics because no data");
            return convertHarvestableArrayToString(this.harvestData.getNetworkPerfMetrics());
        }
        return convertHarvestableArrayToString(this.harvestData.getNetworkPerfMetrics(), this.harvestData.getWebViewPerfMetrics());
    }

    public void restFreeTime() {
        this.freeTime = -this.configuration.getInterval();
    }
}
