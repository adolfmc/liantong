package com.networkbench.agent.impl.harvest;

import android.text.TextUtils;
import com.networkbench.agent.impl.p243c.C6305i;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.C6516c;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6634d;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6644m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HarvestConfiguration {
    public static final int ANR_ACTIONS_MAX_COUNT = 20;
    public static final int ANR_THRESHOLD = 5000;
    private static final String FILTER_TYPE_TAG = "*";
    public static final int SLOW_START_THRESHOLD = 3000;
    public static final long SLOW_USER_ACTION_THRESHOLD = 3000;
    public static final int S_DOM_THR = 2100;
    public static final int S_FIRSTPAINT_THR = 1400;
    public static final int S_FIRSTSCREEN_THR = 2800;
    public static final int S_PAGE_THR = 7000;
    private static HarvestConfiguration defaultHarvestConfiguration;
    private int actionAge;
    private int actionFailureThreshold;
    private int actions;
    private String apmsIssue;
    private int betaonFlag;
    private String brsAgent;
    private String cdnHeaderName;
    private int crashTrails;
    private String deviceId;
    private boolean dnsconn;
    private boolean enableBrsAgent;
    private boolean enableErrTrace;
    private int enabled;
    private int errRspSize;
    private int errs;
    private int feature;
    private long hotStartThreshold;
    private ArrayList<C6305i.C6306a> ignoreErrRules;
    private String ignoredErrorRulesToSting;
    private int intervalOnIdle;
    private boolean pluginSwitch;
    private int slowDomThreshold;
    private int slowFirstPaintThreshold;
    private int slowFirstScreenThreshold;
    private int slowInteractionThreshold;
    private int slowPageDurationThreshold;
    private int slowPageLoadThreshold;
    private int slowPageThreshold;
    private long slowStartThreshold;
    private long slowUserActionThreshold;
    private int stackDepth;
    private String token;
    private String tyId;
    private int uiPages;
    private int uiTraceRetries;
    private int uiTraceSize;
    private float uiTraceThreshold;
    private int uiTraces;
    private int urlFilterMode;
    private ArrayList<C6305i.C6307b> urlRules;
    private String urlRulesToString;
    private String whiteList;
    private static InterfaceC6393e log = C6394f.m10150a();

    /* renamed from: a */
    static List<String> f16269a = new ArrayList();
    public static int HOT_START_THRESHOLD = 180;
    private long interval = 60;
    private boolean enableNdk = true;
    private int anrThreshold = 5000;
    private int anrAction = 20;
    private ConcurrentHashMap<UrlFilter, C6305i.C6308c> urlParamArray = new ConcurrentHashMap<>();
    private boolean controllerEnable = false;
    private float uiTraceMaxTime = 100000.0f;
    private int userActions = 10;
    private int tyPlatformValue = 0;
    private String tyIdNew = "";
    private int controllerInterval = 60;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum FILTERTYPE {
        NONE,
        FIRST_FILTER,
        MIDDLE_FILTER,
        LAST_FILTER
    }

    public String getApmsIssue() {
        return this.apmsIssue;
    }

    public void setApmsIssue(String str) {
        this.apmsIssue = str;
    }

    public String getBrsAgent() {
        return this.brsAgent;
    }

    public void setBrsAgent(String str) {
        this.brsAgent = str;
    }

    public boolean isEnableBrsAgent() {
        return this.enableBrsAgent;
    }

    public void setEnableBrsAgent(boolean z) {
        this.enableBrsAgent = z;
    }

    public boolean isEnableNdk() {
        return this.enableNdk;
    }

    public void setEnableNdk(boolean z) {
        this.enableNdk = z;
        C6638h.m8963w().m9046a(z);
    }

    public String encryptAesToHeader() throws Exception {
        if (C6638h.m8963w().m8982m() == null) {
            throw new C6632b("encryptContent is not init");
        }
        return C6638h.m8963w().m8982m().m9099c(new String(C6638h.m8963w().m8982m().m9106a()));
    }

    public String encryptContentAES(String str) {
        if (C6638h.m8963w().m8982m() == null || !C6638h.m8963w().f17182z) {
            return str;
        }
        try {
            return "{cipher}" + C6638h.m8963w().m8982m().m9105a(str);
        } catch (Throwable th) {
            log.mo10121a("encryptContentAES error ", th);
            return str;
        }
    }

    public void setAnrThreshold(int i) {
        this.anrThreshold = i;
    }

    public int getAnrThreshold() {
        return this.anrThreshold;
    }

    public void setAnrAction(int i) {
        this.anrAction = i;
    }

    public int getAnrAction() {
        return this.anrAction;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class UrlFilter {
        public FILTERTYPE filtertype;
        public String url;

        public UrlFilter() {
        }

        public boolean isAvalidUrl(String str) throws NullPointerException {
            if (this.filtertype == FILTERTYPE.FIRST_FILTER && str.endsWith(this.url)) {
                return true;
            }
            if (this.filtertype == FILTERTYPE.LAST_FILTER && str.startsWith(this.url)) {
                return true;
            }
            if (this.filtertype == FILTERTYPE.MIDDLE_FILTER && str.contains(this.url)) {
                return true;
            }
            return this.filtertype == FILTERTYPE.NONE && str.contains(this.url);
        }
    }

    public static List<String> getCellInfoConfig() {
        return f16269a;
    }

    private static List<String> getCellInfoType(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.getString(i));
            } catch (Throwable th) {
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10116d("cellInfoJsonArray error :" + th.getMessage());
            }
        }
        return arrayList;
    }

    public ConcurrentHashMap<UrlFilter, C6305i.C6308c> getUrlParamArray() {
        return this.urlParamArray;
    }

    public boolean isPluginSwitch() {
        return this.pluginSwitch;
    }

    public void setPluginSwitch(boolean z) {
        this.pluginSwitch = z;
    }

    public int getSlowPageThreshold() {
        return this.slowPageThreshold;
    }

    public void setSlowPageThreshold(int i) {
        this.slowPageThreshold = i;
    }

    public int getSlowFirstPaintThreshold() {
        return this.slowFirstPaintThreshold;
    }

    public void setSlowFirstPaintThreshold(int i) {
        this.slowFirstPaintThreshold = i;
    }

    public int getSlowFirstScreenThreshold() {
        return this.slowFirstScreenThreshold;
    }

    public void setSlowFirstScreenThreshold(int i) {
        this.slowFirstScreenThreshold = i;
    }

    public int getSlowDomThreshold() {
        return this.slowDomThreshold;
    }

    public void setSlowDomThreshold(int i) {
        this.slowDomThreshold = i;
    }

    public int getEnabled() {
        return this.enabled;
    }

    public void setEnabled(int i) {
        this.enabled = i;
    }

    public int getUiPages() {
        return this.uiPages;
    }

    public void setUiPages(int i) {
        this.uiPages = i;
    }

    public HarvestConfiguration() {
        setDefaultValues();
    }

    public void setDefaultValues() {
        this.interval = 60L;
        this.intervalOnIdle = 20;
        this.actions = 1000;
        this.actionAge = 600;
        this.enableErrTrace = true;
        this.errs = 100;
        this.errRspSize = 2048;
        this.stackDepth = 10;
        this.dnsconn = false;
        this.urlFilterMode = 0;
        this.urlRules = new ArrayList<>();
        this.ignoreErrRules = new ArrayList<>();
        this.uiTraceThreshold = 300.0f;
        this.uiTraces = 1;
        this.uiTraceSize = 65534;
        this.uiTraceRetries = 1;
        this.crashTrails = 20;
        this.controllerInterval = 60;
        this.controllerEnable = false;
        this.cdnHeaderName = "";
        this.slowPageThreshold = 7000;
        this.slowFirstPaintThreshold = 1400;
        this.slowFirstScreenThreshold = 2800;
        this.slowDomThreshold = 2100;
        this.hotStartThreshold = HOT_START_THRESHOLD;
        this.slowStartThreshold = 3000L;
        this.slowUserActionThreshold = 3000L;
        this.actionFailureThreshold = 100;
        this.slowPageLoadThreshold = 1000;
        this.slowPageDurationThreshold = 3000;
        this.uiPages = 100;
        this.pluginSwitch = false;
        this.anrAction = 20;
        this.tyId = "";
        this.tyIdNew = "";
        this.apmsIssue = "";
    }

    public static HarvestConfiguration getDefaultHarvestConfiguration() {
        HarvestConfiguration harvestConfiguration = defaultHarvestConfiguration;
        if (harvestConfiguration != null) {
            return harvestConfiguration;
        }
        defaultHarvestConfiguration = new HarvestConfiguration();
        return defaultHarvestConfiguration;
    }

    public String getCdnHeaderName() {
        return this.cdnHeaderName;
    }

    public void setCdnHeaderName(String str) {
        if (str == null) {
            this.cdnHeaderName = "";
        }
        this.cdnHeaderName = str;
    }

    public void reconfigure(HarvestConfiguration harvestConfiguration) {
        this.deviceId = harvestConfiguration.getTingyunId();
        this.token = harvestConfiguration.getToken();
        this.interval = harvestConfiguration.getInterval();
        this.intervalOnIdle = harvestConfiguration.getIntervalOnIdle();
        this.actions = harvestConfiguration.getActions();
        this.actionAge = harvestConfiguration.getActionAge();
        this.enableErrTrace = harvestConfiguration.isEnableErrTrace();
        this.errs = harvestConfiguration.getErrs();
        this.errRspSize = harvestConfiguration.getErrRspSize();
        this.stackDepth = harvestConfiguration.getStackDepth();
        this.dnsconn = harvestConfiguration.isDnsconn();
        this.urlFilterMode = harvestConfiguration.getUrlFilterMode();
        if (harvestConfiguration.getUrlRules() != null) {
            this.urlRules = harvestConfiguration.getUrlRules();
        }
        if (harvestConfiguration.getIgnoreErrRules() != null) {
            this.ignoreErrRules = harvestConfiguration.getIgnoreErrRules();
        }
        this.uiTraceThreshold = harvestConfiguration.getUiTraceThreshold();
        this.uiTraces = harvestConfiguration.getUiTraces();
        this.uiTraceSize = harvestConfiguration.getUiTraceSize();
        this.uiTraceRetries = harvestConfiguration.getUiTraceRetries();
        this.ignoreErrRules = harvestConfiguration.getIgnoreErrRules();
        this.urlParamArray = harvestConfiguration.getUrlParamArray();
        this.crashTrails = harvestConfiguration.getCrashTrails();
        this.betaonFlag = harvestConfiguration.getBetaonFlag();
        this.controllerInterval = harvestConfiguration.getControllerInterval();
        this.whiteList = harvestConfiguration.getWhiteList();
        this.cdnHeaderName = harvestConfiguration.getCdnHeaderName();
        this.slowDomThreshold = harvestConfiguration.getSlowDomThreshold();
        this.slowFirstScreenThreshold = harvestConfiguration.getSlowFirstScreenThreshold();
        this.slowFirstPaintThreshold = harvestConfiguration.getSlowFirstPaintThreshold();
        this.slowPageThreshold = harvestConfiguration.getSlowPageThreshold();
        this.hotStartThreshold = harvestConfiguration.getHotStartThreshold();
        this.slowStartThreshold = harvestConfiguration.getSlowStartThreshold();
        this.userActions = harvestConfiguration.getUserActions();
        this.slowUserActionThreshold = harvestConfiguration.getSlowUserActionThreshold();
        this.urlRulesToString = harvestConfiguration.getUrlRulesToString();
        this.ignoredErrorRulesToSting = harvestConfiguration.getIgnoredErrorRulestoString();
        this.actionFailureThreshold = harvestConfiguration.getActionFailureThreshold();
        this.enabled = harvestConfiguration.getEnabled();
        this.controllerEnable = harvestConfiguration.getControllerEnable();
        this.feature = harvestConfiguration.getFeature();
        this.anrThreshold = harvestConfiguration.getAnrThreshold();
        this.slowPageLoadThreshold = harvestConfiguration.getSlowPageLoadThreshold();
        this.slowPageDurationThreshold = harvestConfiguration.getSlowPageDurationThreshold();
        this.uiPages = harvestConfiguration.getUiPages();
        this.anrAction = harvestConfiguration.getAnrAction();
        this.pluginSwitch = harvestConfiguration.isPluginSwitch();
        this.brsAgent = harvestConfiguration.getBrsAgent();
        this.enableBrsAgent = harvestConfiguration.isEnableBrsAgent();
        this.enableNdk = harvestConfiguration.isEnableNdk();
        this.tyId = harvestConfiguration.getTyId();
        this.tyIdNew = harvestConfiguration.getTyIdNew();
        this.tyPlatformValue = harvestConfiguration.getTyPlatformValue();
        this.apmsIssue = harvestConfiguration.getApmsIssue();
    }

    public void reconfigurePb(JSONObject jSONObject) throws Exception {
        reconfigure(new HarvestConfiguration().parseHarvestConfigFromResult(jSONObject));
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long j) {
        this.interval = j;
    }

    public int getIntervalOnIdle() {
        return this.intervalOnIdle;
    }

    public void setIntervalOnIdle(int i) {
        this.intervalOnIdle = i;
    }

    public int getActions() {
        return this.actions;
    }

    public void setActions(int i) {
        this.actions = i;
    }

    public int getActionAge() {
        return this.actionAge;
    }

    public void setActionAge(int i) {
        this.actionAge = i;
    }

    public boolean isEnableErrTrace() {
        return this.enableErrTrace;
    }

    public void setEnableErrTrace(boolean z) {
        this.enableErrTrace = z;
    }

    public int getErrs() {
        return this.errs;
    }

    public void setErrs(int i) {
        this.errs = i;
    }

    public int getErrRspSize() {
        return this.errRspSize;
    }

    public void setErrRspSize(int i) {
        this.errRspSize = i;
    }

    public int getStackDepth() {
        return this.stackDepth;
    }

    public void setStackDepth(int i) {
        this.stackDepth = i;
    }

    public boolean isDnsconn() {
        return this.dnsconn;
    }

    public void setDnsconn(boolean z) {
        this.dnsconn = z;
    }

    public int getUrlFilterMode() {
        return this.urlFilterMode;
    }

    public void setUrlFilterMode(int i) {
        this.urlFilterMode = i;
    }

    public ArrayList<C6305i.C6307b> getUrlRules() {
        return this.urlRules;
    }

    public String getUrlRulesToString() {
        return this.urlRulesToString;
    }

    public void setUrlRulesToString(String str) {
        this.urlRulesToString = str;
    }

    public String getIgnoredErrorRulestoString() {
        return this.ignoredErrorRulesToSting;
    }

    public void setIgnoredErrorRulesToString(String str) {
        if (str == null) {
            this.ignoredErrorRulesToSting = "";
        }
        this.ignoredErrorRulesToSting = str;
    }

    public int getSlowPageLoadThreshold() {
        return this.slowPageLoadThreshold;
    }

    public void setSlowPageLoadThreshold(int i) {
        this.slowPageLoadThreshold = i;
    }

    public int getSlowPageDurationThreshold() {
        return this.slowPageDurationThreshold;
    }

    public void setSlowPageDurationThreshold(int i) {
        this.slowPageDurationThreshold = i;
    }

    public void setUrlRules(String str) {
        setUrlRulesToString(str);
        ArrayList<C6305i.C6307b> arrayList = new ArrayList<>();
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                int i2 = jSONObject.getInt("matchMode");
                String string = jSONObject.getString("rule");
                C6305i.C6307b c6307b = new C6305i.C6307b();
                c6307b.f15825a = i2;
                c6307b.f15826b = string;
                arrayList.add(c6307b);
                this.urlRules = arrayList;
            }
        } catch (JSONException e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("set UrlRules info" + e.toString());
        }
    }

    public ArrayList<C6305i.C6306a> getIgnoreErrRules() {
        return this.ignoreErrRules;
    }

    public void setIgnoreErrRules(String str) {
        setIgnoredErrorRulesToString(str);
        ArrayList<C6305i.C6306a> arrayList = new ArrayList<>();
        try {
        } catch (JSONException e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("set ignoreErrors info" + e.toString());
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONArray jSONArray = new JSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            int i2 = jSONObject.getInt("matchMode");
            String string = jSONObject.getString("rule");
            String string2 = jSONObject.getString("errs");
            C6305i.C6306a c6306a = new C6305i.C6306a();
            c6306a.f15822a = i2;
            c6306a.f15823b = string;
            c6306a.f15824c = string2;
            arrayList.add(c6306a);
        }
        this.ignoreErrRules = arrayList;
    }

    public float getUiTraceThreshold() {
        return this.uiTraceThreshold;
    }

    public void setUiTraceThreshold(float f) {
        this.uiTraceThreshold = f;
    }

    public int getUiTraceSize() {
        return this.uiTraceSize;
    }

    public void setUiTraceSize(int i) {
        this.uiTraceSize = i;
    }

    public int getUiTraceRetries() {
        return this.uiTraceRetries;
    }

    public void setUiTraceRetries(int i) {
        this.uiTraceRetries = i;
    }

    public int getUiTraces() {
        return this.uiTraces;
    }

    public void setUiTraces(int i) {
        this.uiTraces = i;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getTingyunId() {
        return this.deviceId;
    }

    public void setTingyunId(String str) {
        this.deviceId = str;
    }

    public int hashCode() {
        int i = (((((((((((this.actionAge + 31) * 31) + this.actions) * 31) + (this.dnsconn ? 1231 : 1237)) * 31) + (this.enableErrTrace ? 1231 : 1237)) * 31) + this.errRspSize) * 31) + this.errs) * 31;
        ArrayList<C6305i.C6306a> arrayList = this.ignoreErrRules;
        int hashCode = arrayList == null ? 0 : arrayList.hashCode();
        long j = this.interval;
        int floatToIntBits = (((((((((((((((((i + hashCode) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.intervalOnIdle) * 31) + this.stackDepth) * 31) + this.uiTraceRetries) * 31) + this.uiTraceSize) * 31) + Float.floatToIntBits(this.uiTraceThreshold)) * 31) + this.uiTraces) * 31) + this.urlFilterMode) * 31;
        ArrayList<C6305i.C6307b> arrayList2 = this.urlRules;
        return floatToIntBits + (arrayList2 != null ? arrayList2.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            HarvestConfiguration harvestConfiguration = (HarvestConfiguration) obj;
            if (this.actionAge == harvestConfiguration.actionAge && this.actions == harvestConfiguration.actions && this.dnsconn == harvestConfiguration.dnsconn && this.enableErrTrace == harvestConfiguration.enableErrTrace && this.errRspSize == harvestConfiguration.errRspSize && this.errs == harvestConfiguration.errs) {
                ArrayList<C6305i.C6306a> arrayList = this.ignoreErrRules;
                if (arrayList == null) {
                    if (harvestConfiguration.ignoreErrRules != null) {
                        return false;
                    }
                } else if (!arrayList.equals(harvestConfiguration.ignoreErrRules)) {
                    return false;
                }
                if (this.interval == harvestConfiguration.interval && this.intervalOnIdle == harvestConfiguration.intervalOnIdle && this.stackDepth == harvestConfiguration.stackDepth && this.uiTraceRetries == harvestConfiguration.uiTraceRetries && this.uiTraceSize == harvestConfiguration.uiTraceSize && Float.floatToIntBits(this.uiTraceThreshold) == Float.floatToIntBits(harvestConfiguration.uiTraceThreshold) && this.uiTraces == harvestConfiguration.uiTraces && this.urlFilterMode == harvestConfiguration.urlFilterMode && this.controllerInterval == harvestConfiguration.controllerInterval) {
                    ArrayList<C6305i.C6307b> arrayList2 = this.urlRules;
                    if (arrayList2 == null) {
                        if (harvestConfiguration.urlRules != null) {
                            return false;
                        }
                    } else if (!arrayList2.equals(harvestConfiguration.urlRules)) {
                        return false;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("did:" + this.deviceId + ",");
        sb.append("token:" + this.token + ",");
        sb.append("interval:" + this.interval + ",");
        sb.append("intervalOnIdle:" + this.intervalOnIdle + ",");
        sb.append("urlFilterMode:" + this.urlFilterMode + ",");
        sb.append("uiTraces:" + this.uiTraces + ",");
        sb.append("uiTraceSize:" + this.uiTraceSize + ",");
        sb.append("uiTraceRetries:" + this.uiTraceRetries + ",");
        sb.append("uiTraceThreshold:" + this.uiTraceThreshold + ",");
        sb.append("crashTrails:" + this.crashTrails + ",");
        sb.append("controllerInterval:" + this.controllerInterval + ",");
        sb.append("cdnHeaderName:" + this.cdnHeaderName + ",");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("hotStartThreshold:");
        sb2.append(this.hotStartThreshold);
        sb.append(sb2.toString());
        sb.append("slowStartThreshold : " + this.slowStartThreshold);
        sb.append("slowUserActionThreshold :" + this.slowUserActionThreshold);
        sb.append("userActions : " + this.userActions);
        sb.append("features :" + this.feature);
        sb.append("apms  :  " + this.apmsIssue);
        sb.append("pluginSwitch : " + this.pluginSwitch);
        ArrayList<C6305i.C6307b> arrayList = this.urlRules;
        if (arrayList != null) {
            Iterator<C6305i.C6307b> it = arrayList.iterator();
            sb.append("urlRules:");
            while (it.hasNext()) {
                C6305i.C6307b next = it.next();
                sb.append("matchMode:" + next.f15825a + ",");
                sb.append("rule:" + next.f15826b + ",");
            }
        }
        if (this.urlParamArray != null) {
            sb.append("urlParamArray:");
            for (Map.Entry<UrlFilter, C6305i.C6308c> entry : this.urlParamArray.entrySet()) {
                String str = entry.getKey().url;
                sb.append("url" + str + ", item:" + entry.getValue().toString() + ";");
            }
        }
        if (this.ignoreErrRules != null) {
            sb.append("ignoreErrRules:");
            Iterator<C6305i.C6306a> it2 = this.ignoreErrRules.iterator();
            while (it2.hasNext()) {
                C6305i.C6306a next2 = it2.next();
                sb.append("errorCode" + next2.f15824c + ", rule:" + next2.f15823b + ",matchMode:" + next2.f15822a);
            }
        }
        return sb.toString();
    }

    public int getCrashTrails() {
        return this.crashTrails;
    }

    public void setCrashTrails(int i) {
        this.crashTrails = i;
    }

    public int getBetaonFlag() {
        return this.betaonFlag;
    }

    public void setBetaonFlag(int i) {
        this.betaonFlag = i;
    }

    public double getUiTraceMaxTime() {
        return this.uiTraceMaxTime;
    }

    public void setTyId(String str) {
        this.tyId = str;
        C6638h.m8963w().m8975o(str);
    }

    public String getTyId() {
        return this.tyId;
    }

    public int getTyPlatformValue() {
        return this.tyPlatformValue;
    }

    public HarvestConfiguration setTyPlatformValue(int i) {
        this.tyPlatformValue = i;
        return this;
    }

    public String getTyIdNew() {
        return this.tyIdNew;
    }

    public void setSlowInteractionThreshold(int i) {
        this.slowInteractionThreshold = i;
        C6638h.m8963w().m8988k(i);
    }

    public String getWhiteList() {
        return this.whiteList;
    }

    public void setWhiteList(String str) {
        this.whiteList = str;
    }

    public int getControllerInterval() {
        return this.controllerInterval;
    }

    public void setControllerInterval(int i) {
        this.controllerInterval = i;
    }

    public long getHotStartThreshold() {
        return this.hotStartThreshold;
    }

    public void setHotStartThreshold(long j) {
        this.hotStartThreshold = j;
    }

    public long getSlowStartThreshold() {
        return this.slowStartThreshold;
    }

    public void setSlowStartThreshold(long j) {
        this.slowStartThreshold = j;
    }

    public long getSlowUserActionThreshold() {
        return this.slowUserActionThreshold;
    }

    public void setSlowUserActionThreshold(long j) {
        this.slowUserActionThreshold = j;
    }

    public int getUserActions() {
        return this.userActions;
    }

    public void setUserActions(int i) {
        this.userActions = i;
    }

    public HarvestConfiguration parseHarvestConfigFromResult(JSONObject jSONObject) throws Exception {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        String[] filterStrParam;
        String[] filterStrParam2;
        String[] filterStrParam3;
        if (jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("cfg");
        log.mo10122a("cfgObj:" + jSONObject2.toString());
        try {
            String string = jSONObject.getString("did");
            if (!TextUtils.isEmpty(string)) {
                setTingyunId(string);
            }
            try {
                setToken(jSONObject.getString("token"));
            } catch (Throwable unused) {
            }
            try {
                setEnabled(jSONObject.getInt("enabled"));
            } catch (Throwable unused2) {
            }
            log.mo10122a("parseHarvestConfigFromResult   isEncrypt : " + C6638h.m8963w().f17182z);
            if (C6638h.m8963w().f17182z) {
                String string2 = jSONObject.getString("tySecretKey");
                C6638h.m8963w().m9011e(string2);
                log.mo10122a("parseHarvestConfigFromResult   isEncrypt : " + C6638h.m8963w().f17182z);
                log.mo10122a("parseHarvestConfigFromResult   tySecretKey : " + C6638h.m8963w().f17125A);
                if (C6638h.m8963w().f17182z) {
                    C6638h.m8963w().m9053a(new C6634d(string2));
                }
            }
            log.mo10122a("did:" + string + ", token:" + this.token + ", enabled:" + this.enabled + "");
        } catch (Throwable unused3) {
        }
        try {
            int i = jSONObject2.getInt("interval");
            if (i <= 0) {
                i = 60;
            }
            setInterval(i);
            setIntervalOnIdle(jSONObject2.getInt("intervalOnIdle"));
            setActions(jSONObject2.getInt("actions"));
            setActionAge(jSONObject2.getInt("actionAge"));
            setEnableErrTrace(jSONObject2.getBoolean("enableErrTrace"));
            setErrs(jSONObject2.getInt("errs"));
            setErrRspSize(jSONObject2.getInt("errRspSize"));
            setStackDepth(jSONObject2.getInt("stackDepth"));
        } catch (Throwable unused4) {
        }
        try {
            setAnrAction(jSONObject2.getInt("anrActions"));
        } catch (Throwable unused5) {
        }
        try {
            setUiTraces(jSONObject2.getInt("uiTraces"));
            setUiTraceSize(jSONObject2.getInt("uiTraceSize"));
            setUiTraceRetries(jSONObject2.getInt("uiTraceRetries"));
            setUiTraceThreshold(jSONObject2.getInt("uiTraceThreshold"));
            setAnrThreshold(jSONObject2.optInt(ConfigurationName.anrThresholdName, 5001));
        } catch (Throwable unused6) {
            log.mo10116d("Error while unpacking UITrace JSON response during connect");
        }
        try {
            setSlowPageThreshold(jSONObject2.getInt("slowPageThreshold"));
            setSlowFirstPaintThreshold(jSONObject2.getInt("slowFirstPaintThreshold"));
            setSlowFirstScreenThreshold(jSONObject2.getInt("slowFirstScreenThreshold"));
            setSlowDomThreshold(jSONObject2.getInt("slowDomThreshold"));
        } catch (Throwable th) {
            log.mo10115e("error parse webview param error" + th.getMessage());
        }
        try {
            setHotStartThreshold(jSONObject2.getInt("hotStartThreshold"));
            setSlowStartThreshold(jSONObject2.getInt("slowStartThreshold"));
        } catch (Throwable th2) {
            log.mo10115e("error parse 2.2.7协议 param error" + th2.getMessage());
        }
        try {
            if (isNull_Or_Exist(jSONObject2, "slowUserActionThreshold")) {
                setSlowUserActionThreshold(jSONObject2.getLong("slowUserActionThreshold"));
                C6396h.m10132j("setSlowUserActionThreshold : " + getSlowUserActionThreshold());
            }
            if (isNull_Or_Exist(jSONObject2, "userActions")) {
                setUserActions(jSONObject2.getInt("userActions"));
                C6396h.m10132j("setUserActions : " + getUserActions());
            }
        } catch (Throwable th3) {
            log.mo10116d("Error while unpacking JSON response during connect" + th3.getMessage());
        }
        try {
            setCrashTrails(jSONObject2.getInt("crashTrails"));
        } catch (Throwable unused7) {
            log.mo10116d("Error while unpacking JSON response during connect");
        }
        try {
            setBetaonFlag(jSONObject2.getInt("betaOn"));
        } catch (Throwable unused8) {
        }
        try {
            setFeature(jSONObject2.getInt(ConfigurationName.features));
        } catch (Throwable unused9) {
        }
        try {
            setTyId(jSONObject.getString("tyId"));
        } catch (Throwable unused10) {
        }
        try {
            this.tyIdNew = jSONObject.getString("tyIdNew");
            if (this.tyIdNew.startsWith("{") && this.tyIdNew.endsWith("}")) {
                this.tyIdNew = "";
            }
            C6638h.m8963w().m8972p(this.tyIdNew);
        } catch (Throwable th4) {
            log.mo10115e("tyIdNew error:" + th4.getMessage());
        }
        try {
            this.tyPlatformValue = jSONObject.getInt("tyPlatform");
            C6638h.m8963w().m8992j(this.tyPlatformValue);
            setEnableBrsAgent(this.tyPlatformValue == 1);
            C6638h.m8963w().m8980m(this.tyPlatformValue == 1);
        } catch (Throwable th5) {
            log.mo10115e("typlatform error:" + th5.getMessage());
        }
        try {
            int i2 = jSONObject2.getInt("slowInteractionThreshold");
            setSlowInteractionThreshold(i2);
            log.mo10122a("slowInteractionThreshold:" + i2);
        } catch (Throwable th6) {
            log.mo10121a("Error while unpacking JSON response during connect", th6);
        }
        try {
            f16269a = getCellInfoType(jSONObject2.getJSONArray("netCellInfo"));
        } catch (Throwable unused11) {
            log.mo10115e("error whie parse cellinfo config");
        }
        try {
            setPluginSwitch(jSONObject2.optBoolean("enableExtension", false));
            log.mo10122a("parseHarvestConfigFromResult  enableExtension:" + isPluginSwitch());
        } catch (Throwable th7) {
            log.mo10115e("error whie unpacking cellinfo config" + th7.getMessage());
        }
        try {
            this.urlFilterMode = jSONObject2.getInt("urlFilterMode");
            setUrlFilterMode(this.urlFilterMode);
            setUrlRules(this.urlFilterMode != 0 ? jSONObject2.getJSONArray("urlRules").toString() : "");
        } catch (Throwable unused12) {
        }
        try {
            if (jSONObject2.has("urlParams") && (jSONArray2 = jSONObject2.getJSONArray("urlParams")) != null) {
                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                    JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i3);
                    C6305i.C6308c c6308c = new C6305i.C6308c();
                    String string3 = jSONObject3.getString("url");
                    log.mo10122a("urlparam url:" + string3);
                    if (jSONObject3.has("get") && (filterStrParam3 = filterStrParam(jSONObject3.getString("get"))) != null) {
                        c6308c.f15827a = filterStrParam3;
                    }
                    if (jSONObject3.has("post") && (filterStrParam2 = filterStrParam(jSONObject3.getString("post"))) != null) {
                        c6308c.f15828b = filterStrParam2;
                    }
                    if (jSONObject3.has("headers") && (filterStrParam = filterStrParam(jSONObject3.getString("headers"))) != null) {
                        c6308c.f15829c = filterStrParam;
                    }
                    if (jSONObject3.has("resHeaders")) {
                        String[] filterStrParam4 = filterStrParam(jSONObject3.getString("resHeaders"));
                        C6396h.m10126p("parseHarvestConfigFromResult  resHeaders: " + filterStrParam4);
                        if (filterStrParam4 != null) {
                            c6308c.f15830d = filterStrParam4;
                        }
                    }
                    log.mo10122a("add param filer: url:" + string3 + ", urlparam: " + c6308c.toString());
                    if (jSONArray2 != null) {
                        try {
                            this.urlParamArray.put(getUrlFilterKey(string3), c6308c);
                        } catch (Exception unused13) {
                        }
                    }
                }
            }
        } catch (Throwable th8) {
            log.mo10120a("Error while unpacking Json response during connect (urlParams)", th8);
        }
        try {
            if (isNull_Or_Exist(jSONObject2, "ignoredErrRules") && (jSONArray = jSONObject2.getJSONArray("ignoredErrRules")) != null) {
                setIgnoreErrRules(jSONArray.toString());
            }
        } catch (Throwable unused14) {
        }
        try {
            if (isNull_Or_Exist(jSONObject2, "cdnHeaderName")) {
                setCdnHeaderName(jSONObject2.getString("cdnHeaderName"));
            }
        } catch (Throwable th9) {
            log.mo10122a("Error while unpacking Json response during connect " + th9.getMessage());
        }
        try {
            if (isNull_Or_Exist(jSONObject2, "actionFailureThreshold")) {
                setActionFailureThreshold(jSONObject2.getInt("actionFailureThreshold"));
            }
            if (isNull_Or_Exist(jSONObject2, "slowPageLoadThreshold")) {
                setSlowPageLoadThreshold(jSONObject2.getInt("slowPageLoadThreshold"));
            }
            if (isNull_Or_Exist(jSONObject2, "slowPageDurationThreshold")) {
                setSlowPageDurationThreshold(jSONObject2.getInt("slowPageDurationThreshold"));
            }
            if (isNull_Or_Exist(jSONObject2, "uiPages")) {
                setUiPages(jSONObject2.getInt("uiPages"));
            }
        } catch (Throwable th10) {
            log.mo10122a("Error while unpacking JSON response during connect" + th10.getMessage());
        }
        try {
            setControllerInterval(jSONObject.getJSONObject("ctl").getInt("interval"));
        } catch (Throwable th11) {
            log.mo10122a("Error while unpacking JSON response during connect" + th11.getMessage());
        }
        try {
            if (isNull_Or_Exist(jSONObject2, "brsAgent")) {
                String string4 = jSONObject2.getString("brsAgent");
                if (string4.startsWith("{") && string4.endsWith("}")) {
                    setEnableBrsAgent(false);
                } else {
                    setBrsAgent(string4);
                    if (C6638h.m8963w().m9040af()) {
                        C6516c.m9565b(string4);
                    }
                }
            } else {
                C6516c.f16607a = true;
            }
            if (isNull_Or_Exist(jSONObject2, "enableNdk")) {
                setEnableNdk(jSONObject2.getBoolean("enableNdk"));
            }
            if (isNull_Or_Exist(jSONObject2, "apms")) {
                JSONArray jSONArray3 = jSONObject2.getJSONArray("apms");
                C6396h.m10126p("JSONArray apms : " + jSONArray3.toString());
                this.apmsIssue = jSONArray3.toString();
                C6638h.m8963w().m9024b(this.apmsIssue);
            }
        } catch (Throwable th12) {
            log.mo10122a("Error while unpacking JSON response during connect" + th12.getMessage());
        }
        return this;
    }

    private void setTYIdNew(String str) {
        this.tyIdNew = str;
    }

    public UrlFilter getUrlFilterKey(String str) throws Exception {
        FILTERTYPE urlFilterType = getUrlFilterType(str, "*");
        UrlFilter urlFilter = new UrlFilter();
        urlFilter.filtertype = urlFilterType;
        if (urlFilterType == FILTERTYPE.FIRST_FILTER) {
            if (str.length() > 1) {
                urlFilter.url = str.substring(1, str.length()).trim();
            }
        } else if (urlFilterType == FILTERTYPE.LAST_FILTER) {
            if (str.length() > 1) {
                urlFilter.url = str.substring(0, str.length() - 1).trim();
            }
        } else if (urlFilterType == FILTERTYPE.MIDDLE_FILTER) {
            if (str.length() > 2) {
                urlFilter.url = str.substring(1, str.length() - 1).trim();
            }
        } else if (urlFilterType.ordinal() == FILTERTYPE.NONE.ordinal()) {
            urlFilter.url = str.trim();
        }
        if (TextUtils.isEmpty(urlFilter.url)) {
            return null;
        }
        return urlFilter;
    }

    public FILTERTYPE getUrlFilterType(String str, String str2) throws NullPointerException {
        FILTERTYPE filtertype = FILTERTYPE.NONE;
        if (str.startsWith(str2)) {
            return str.endsWith(str2) ? FILTERTYPE.MIDDLE_FILTER : FILTERTYPE.FIRST_FILTER;
        }
        return str.endsWith(str2) ? FILTERTYPE.LAST_FILTER : filtertype;
    }

    private boolean isNull_Or_Exist(JSONObject jSONObject, String str) {
        return jSONObject.has(str) && !jSONObject.isNull(str);
    }

    private String[] filterStrParam(String str) {
        String[] m8886c;
        if (TextUtils.isEmpty(str) || (m8886c = C6644m.m8886c(str, ",")) == null || m8886c.length <= 0) {
            return null;
        }
        return m8886c;
    }

    public void setActionFailureThreshold(int i) {
        this.actionFailureThreshold = i;
    }

    public int getActionFailureThreshold() {
        return this.actionFailureThreshold;
    }

    public int getFeature() {
        return this.feature;
    }

    public void setFeature(int i) {
        this.feature = i;
    }

    public boolean getControllerEnable() {
        return this.controllerEnable;
    }

    public int calcState(long j, long j2) {
        int i = j2 >= ((long) getSlowPageDurationThreshold()) ? 2 : 0;
        if (j >= getSlowPageLoadThreshold()) {
            return 1;
        }
        return i;
    }
}
