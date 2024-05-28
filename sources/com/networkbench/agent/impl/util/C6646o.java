package com.networkbench.agent.impl.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.harvest.HarvestAdapter;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.o */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6646o extends HarvestAdapter {

    /* renamed from: a */
    private static final InterfaceC6393e f17222a = C6394f.m10150a();

    /* renamed from: c */
    private static final String f17223c = "_main";

    /* renamed from: b */
    private final Context f17224b;

    /* renamed from: e */
    private Float f17226e;

    /* renamed from: f */
    private final SharedPreferences f17227f;

    /* renamed from: g */
    private final SharedPreferences.Editor f17228g;

    /* renamed from: d */
    private HarvestConfiguration f17225d = new HarvestConfiguration();

    /* renamed from: h */
    private final Lock f17229h = new ReentrantLock();

    @Override // com.networkbench.agent.impl.harvest.HarvestAdapter, com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestDisabled() {
    }

    public C6646o(Context context) {
        this.f17227f = context.getSharedPreferences(C6653u.m8692j(context.getPackageName()), 0);
        this.f17228g = this.f17227f.edit();
        this.f17224b = context;
    }

    /* renamed from: a */
    public void m8859a(HarvestConfiguration harvestConfiguration) {
        if (this.f17225d.equals(harvestConfiguration)) {
            return;
        }
        this.f17225d = harvestConfiguration;
        m8817k(harvestConfiguration.getToken());
        m8820j(harvestConfiguration.getTingyunId());
        m8848b(harvestConfiguration.getInterval());
        m8818k(harvestConfiguration.getIntervalOnIdle());
        m8836e(harvestConfiguration.getActions());
        m8840d(harvestConfiguration.getActionAge());
        m8851a(harvestConfiguration.isEnableErrTrace());
        m8833f(harvestConfiguration.getStackDepth());
        m8830g(harvestConfiguration.getErrRspSize());
        m8824i(harvestConfiguration.getErrs());
        m8862a(harvestConfiguration.getUiTraceThreshold());
        m8821j(harvestConfiguration.getControllerInterval());
        m8843c(harvestConfiguration.getHotStartThreshold());
        m8839d(harvestConfiguration.getSlowStartThreshold());
        m8827h(harvestConfiguration.getUrlFilterMode());
        m8795v(harvestConfiguration.getUrlRulesToString());
        m8797u(harvestConfiguration.getIgnoredErrorRulestoString());
        m8844c(harvestConfiguration.getEnabled());
        m8861a(harvestConfiguration.getFeature());
        m8849b(C6638h.m8963w().m9084C());
        m8856a(ConfigurationName.anrThresholdName, harvestConfiguration.getAnrThreshold());
        m8856a(ConfigurationName.betaOn, harvestConfiguration.getBetaonFlag());
        m8856a("uiPages", harvestConfiguration.getUiPages());
        m8803r(harvestConfiguration.getBrsAgent());
        m8852a(ConfigurationName.enableBrsAgent, harvestConfiguration.isEnableBrsAgent());
        m8852a(ConfigurationName.enableNdk, harvestConfiguration.isEnableNdk());
        m8853a(ConfigurationName.tyId, harvestConfiguration.getTyId());
        m8853a(ConfigurationName.tyIdNew, harvestConfiguration.getTyIdNew());
        m8853a(ConfigurationName.apmsIssue, harvestConfiguration.getApmsIssue());
        m8856a(ConfigurationName.tyPlatform, harvestConfiguration.getTyPlatformValue());
        if (m8805q("deviceId")) {
            m8807p("deviceId");
        }
    }

    /* renamed from: p */
    private void m8807p(String str) {
        this.f17229h.lock();
        try {
            this.f17228g.remove(str);
            this.f17228g.commit();
        } finally {
            this.f17229h.unlock();
        }
    }

    /* renamed from: q */
    private boolean m8805q(String str) {
        return this.f17227f.contains(str);
    }

    /* renamed from: a */
    public String m8858a(String str) {
        if (this.f17227f.contains(str)) {
            return this.f17227f.getString(str, "");
        }
        return null;
    }

    /* renamed from: r */
    private void m8803r(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        m8853a(ConfigurationName.brsAgent, str);
        m8853a(ConfigurationName.brsAgentMD5, C6647p.m8784d(str).toLowerCase());
    }

    /* renamed from: a */
    public void m8861a(int i) {
        if (C6653u.m8729b(this.f17224b)) {
            String str = ConfigurationName.features;
            m8855a(str, i, this.f17224b.getPackageName() + "_main");
            return;
        }
        m8855a(ConfigurationName.features, i, ConfigurationName.processName);
    }

    /* renamed from: a */
    public boolean m8863a() {
        Context context = this.f17224b;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f17224b.getPackageName());
        sb.append("_main");
        return context.getSharedPreferences(C6653u.m8692j(sb.toString()), 0).contains(C6636f.m9093b(ConfigurationName.features));
    }

    /* renamed from: a */
    private void m8855a(String str, int i, String str2) {
        SharedPreferences.Editor edit = this.f17224b.getSharedPreferences(C6653u.m8692j(str2), 0).edit();
        String m9093b = C6636f.m9093b(str);
        C6396h.m10125q("saveFeatureWithProcess path:" + C6653u.m8692j(ConfigurationName.processName));
        edit.putInt(m9093b, i);
        edit.commit();
    }

    /* renamed from: b */
    public void m8849b(int i) {
        m8856a(ConfigurationName.appVersion, i);
    }

    /* renamed from: b */
    public int m8850b() {
        return this.f17227f.getInt(C6636f.m9093b(ConfigurationName.appVersion), -1);
    }

    /* renamed from: c */
    public void m8844c(int i) {
        if (C6653u.m8729b(this.f17224b)) {
            String str = ConfigurationName.sdkEnabled;
            m8855a(str, i, this.f17224b.getPackageName() + "_main");
            return;
        }
        m8855a(ConfigurationName.sdkEnabled, i, ConfigurationName.processName);
    }

    /* renamed from: c */
    public int m8845c() {
        if (C6653u.m8729b(this.f17224b)) {
            String str = ConfigurationName.sdkEnabled;
            return m8846b(str, this.f17224b.getPackageName() + "_main");
        }
        return m8846b(ConfigurationName.sdkEnabled, ConfigurationName.processName);
    }

    /* renamed from: d */
    public int m8841d() {
        return m8829g(ConfigurationName.betaOn);
    }

    /* renamed from: e */
    public void m8837e() {
        try {
            if (m8801s("token")) {
                this.f17225d.setToken(m8825i());
            }
            if (m8805q("deviceId")) {
                C6638h.m8963w().m8978n(m8858a("deviceId"));
            }
            if (m8801s("deviceId")) {
                this.f17225d.setTingyunId(m8822j());
                C6638h.m8963w().m8978n(m8822j());
            }
            if (m8801s("harvestIntervalInSeconds")) {
                this.f17225d.setInterval(m8793x());
            }
            if (m8801s("maxActionAgeInSeconds")) {
                this.f17225d.setActionAge(m8792y());
            }
            if (m8801s("maxActionCount")) {
                this.f17225d.setActions(m8804r());
            }
            if (m8801s("stackTraceLimit")) {
                this.f17225d.setStackDepth(m8802s());
            }
            if (m8801s("responseBodyLimit")) {
                this.f17225d.setErrRspSize(m8800t());
            }
            if (m8801s("collectNetworkErrors")) {
                this.f17225d.setEnableErrTrace(m8812n());
            }
            if (m8801s("errorLimit")) {
                this.f17225d.setErrs(m8798u());
            }
            if (m8801s("urlFilterMode")) {
                this.f17225d.setUrlFilterMode(m8796v());
            }
            if (m8801s("activityTraceThreshold")) {
                this.f17225d.setUiTraceThreshold(m8794w());
            }
            if (m8801s("harvestIntervalOnIdleInSeconds")) {
                this.f17225d.setIntervalOnIdle(m8864J());
            }
            if (m8801s("controllerInterval")) {
                this.f17225d.setControllerInterval(m8873A());
            }
            if (m8801s("hotStartThreshold")) {
                this.f17225d.setHotStartThreshold(m8871C());
            }
            if (m8801s("slowStartThreshold")) {
                this.f17225d.setSlowStartThreshold(m8870D());
            }
            if (m8801s("urlRules")) {
                this.f17225d.setUrlRules(m8868F());
            }
            if (m8801s("ignoreErrRules")) {
                this.f17225d.setIgnoreErrRules(m8867G());
            }
            if (m8801s("uiPages")) {
                this.f17225d.setUiPages(m8865I());
            }
            if (m8799t(ConfigurationName.features)) {
                C6638h.m8963w().m9004g(m8866H());
                this.f17225d.setFeature(m8866H());
            }
            if (m8799t(ConfigurationName.sdkEnabled)) {
                C6638h.m8963w().m9006f(m8845c() != 0);
            }
            if (m8801s(ConfigurationName.anrThresholdName)) {
                this.f17225d.setAnrThreshold(m8869E());
            }
            if (m8801s(ConfigurationName.betaOn)) {
                C6638h.m8963w().m9000h(m8829g(ConfigurationName.betaOn));
            }
            if (m8801s(ConfigurationName.enableBrsAgent)) {
                C6638h.m8963w().m8980m(m8838d(ConfigurationName.enableBrsAgent));
            }
            if (m8801s(ConfigurationName.brsAgent)) {
                C6638h.m8963w().m9051a(m8842c(ConfigurationName.brsAgent));
            } else {
                C6638h.m8963w().m9051a("");
            }
            if (m8801s(ConfigurationName.enableNdk)) {
                C6638h.m8963w().m9046a(m8838d(ConfigurationName.enableNdk));
            }
            if (m8801s(ConfigurationName.tyId)) {
                C6638h.m8963w().m8975o(m8842c(ConfigurationName.tyId));
            }
            if (m8801s(ConfigurationName.tyIdNew)) {
                C6638h.m8963w().m8972p(m8842c(ConfigurationName.tyIdNew));
            }
            if (m8801s(ConfigurationName.tyPlatform)) {
                C6638h.m8963w().m8992j(m8829g(ConfigurationName.tyPlatform));
                C6638h.m8963w().m8980m(m8829g(ConfigurationName.tyPlatform) == 1);
            }
            if (m8801s(ConfigurationName.apmsIssue)) {
                C6638h.m8963w().m9024b(m8842c(ConfigurationName.apmsIssue));
            }
            InterfaceC6393e interfaceC6393e = f17222a;
            interfaceC6393e.mo10122a("Loaded configuration: " + this.f17225d);
        } catch (Throwable unused) {
            this.f17228g.clear().commit();
        }
    }

    /* renamed from: f */
    public HarvestConfiguration m8834f() {
        return this.f17225d;
    }

    /* renamed from: s */
    private boolean m8801s(String str) {
        return this.f17227f.contains(C6636f.m9093b(str));
    }

    /* renamed from: t */
    private boolean m8799t(String str) {
        String str2;
        if (C6653u.m8729b(this.f17224b)) {
            str2 = this.f17224b.getPackageName() + "_main";
        } else {
            str2 = ConfigurationName.processName;
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return this.f17224b.getSharedPreferences(C6653u.m8692j(str2), 0).contains(C6636f.m9093b(str));
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestAdapter, com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestConnected() {
        m8859a(HarvestConfiguration.getDefaultHarvestConfiguration());
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestAdapter, com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestComplete() {
        m8859a(HarvestConfiguration.getDefaultHarvestConfiguration());
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestAdapter, com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestDisconnected() {
        f17222a.mo10122a("Clearing harvest configuration.");
        m8872B();
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestAdapter, com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestDeviceIdError() {
        f17222a.mo10122a("onHarvestError  初始化出现错误 清除掉 缓存的feature状态!");
    }

    /* renamed from: a */
    public void m8853a(String str, String str2) {
        this.f17229h.lock();
        try {
            this.f17228g.putString(C6636f.m9093b(str), C6636f.m9093b(str2));
            this.f17228g.commit();
        } finally {
            this.f17229h.unlock();
        }
    }

    /* renamed from: b */
    public void m8847b(String str) {
        m8853a("crashUUID", str);
    }

    /* renamed from: g */
    public String m8831g() {
        return m8842c("crashUUID");
    }

    /* renamed from: a */
    public void m8852a(String str, boolean z) {
        this.f17229h.lock();
        try {
            this.f17228g.putBoolean(C6636f.m9093b(str), z);
            this.f17228g.commit();
        } finally {
            this.f17229h.unlock();
        }
    }

    /* renamed from: a */
    public void m8856a(String str, int i) {
        this.f17229h.lock();
        try {
            this.f17228g.putInt(C6636f.m9093b(str), i);
            this.f17228g.commit();
        } finally {
            this.f17229h.unlock();
        }
    }

    /* renamed from: a */
    public void m8854a(String str, long j) {
        this.f17229h.lock();
        try {
            this.f17228g.putLong(C6636f.m9093b(str), j);
            this.f17228g.commit();
        } finally {
            this.f17229h.unlock();
        }
    }

    /* renamed from: a */
    public void m8857a(String str, float f) {
        this.f17229h.lock();
        try {
            this.f17228g.putFloat(C6636f.m9093b(str), f);
            this.f17228g.commit();
        } finally {
            this.f17229h.unlock();
        }
    }

    /* renamed from: c */
    public String m8842c(String str) {
        String m9093b = C6636f.m9093b(str);
        if (this.f17227f.contains(m9093b)) {
            return C6636f.m9092c(this.f17227f.getString(m9093b, null));
        }
        return null;
    }

    /* renamed from: d */
    public boolean m8838d(String str) {
        return this.f17227f.getBoolean(C6636f.m9093b(str), false);
    }

    /* renamed from: e */
    public boolean m8835e(String str) {
        return this.f17227f.getBoolean(C6636f.m9093b(str), true);
    }

    /* renamed from: f */
    public long m8832f(String str) {
        return this.f17227f.getLong(C6636f.m9093b(str), 0L);
    }

    /* renamed from: g */
    public int m8829g(String str) {
        return this.f17227f.getInt(C6636f.m9093b(str), 0);
    }

    /* renamed from: h */
    public Float m8826h(String str) {
        String m9093b = C6636f.m9093b(str);
        if (!this.f17227f.contains(m9093b)) {
            return Float.valueOf(0.0f);
        }
        return Float.valueOf(((int) (this.f17227f.getFloat(m9093b, 0.0f) * 100.0f)) / 100.0f);
    }

    /* renamed from: h */
    public String m8828h() {
        return m8842c("NBSNewLensAgentDisabledVersion");
    }

    /* renamed from: i */
    public void m8823i(String str) {
        m8853a("NBSNewLensAgentDisabledVersion", str);
    }

    /* renamed from: i */
    public String m8825i() {
        return m8842c("token");
    }

    /* renamed from: j */
    public String m8822j() {
        return m8842c(ConfigurationName.deviceId);
    }

    /* renamed from: j */
    public void m8820j(String str) {
        m8853a(ConfigurationName.deviceId, str);
    }

    /* renamed from: k */
    public void m8817k(String str) {
        m8853a("token", str);
    }

    /* renamed from: k */
    public String m8819k() {
        return m8842c("agentVersion");
    }

    /* renamed from: l */
    public void m8815l(String str) {
        m8853a("agentVersion", str);
    }

    /* renamed from: l */
    public String m8816l() {
        return m8842c("crossProcessId");
    }

    /* renamed from: m */
    public void m8813m(String str) {
        m8853a("crossProcessId", str);
    }

    /* renamed from: m */
    public String m8814m() {
        return m8842c("androidIdBugWorkAround");
    }

    /* renamed from: n */
    public void m8811n(String str) {
        m8853a("androidIdBugWorkAround", str);
    }

    /* renamed from: n */
    public boolean m8812n() {
        return m8838d("collectNetworkErrors");
    }

    /* renamed from: a */
    public void m8851a(boolean z) {
        m8852a("collectNetworkErrors", z);
    }

    /* renamed from: o */
    public long m8810o() {
        return m8832f("serverTimestamp");
    }

    /* renamed from: a */
    public void m8860a(long j) {
        m8854a("serverTimestamp", j);
    }

    /* renamed from: p */
    public long m8808p() {
        return m8832f("harvestIntervalInSeconds");
    }

    /* renamed from: b */
    public void m8848b(long j) {
        m8854a("harvestIntervalInSeconds", j);
    }

    /* renamed from: k */
    private void m8818k(int i) {
        m8856a("harvestIntervalOnIdleInSeconds", i);
    }

    /* renamed from: J */
    private int m8864J() {
        return m8829g("harvestIntervalOnIdleInSeconds");
    }

    /* renamed from: q */
    public int m8806q() {
        return m8829g("maxActionAgeInSeconds");
    }

    /* renamed from: d */
    public void m8840d(int i) {
        m8856a("maxActionAgeInSeconds", i);
    }

    /* renamed from: r */
    public int m8804r() {
        return m8829g("maxActionCount");
    }

    /* renamed from: e */
    public void m8836e(int i) {
        m8856a("maxActionCount", i);
    }

    /* renamed from: s */
    public int m8802s() {
        return m8829g("stackTraceLimit");
    }

    /* renamed from: f */
    public void m8833f(int i) {
        m8856a("stackTraceLimit", i);
    }

    /* renamed from: t */
    public int m8800t() {
        return m8829g("responseBodyLimit");
    }

    /* renamed from: g */
    public void m8830g(int i) {
        m8856a("responseBodyLimit", i);
    }

    /* renamed from: u */
    public int m8798u() {
        return m8829g("errorLimit");
    }

    /* renamed from: v */
    public int m8796v() {
        return m8829g("urlFilterMode");
    }

    /* renamed from: h */
    public void m8827h(int i) {
        m8856a("urlFilterMode", i);
    }

    /* renamed from: i */
    public void m8824i(int i) {
        m8856a("errorLimit", i);
    }

    /* renamed from: a */
    public void m8862a(float f) {
        this.f17226e = Float.valueOf(f);
        m8857a("activityTraceThreshold", f);
    }

    /* renamed from: w */
    public float m8794w() {
        if (this.f17226e == null) {
            this.f17226e = m8826h("activityTraceThreshold");
        }
        Float f = this.f17226e;
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    /* renamed from: x */
    public long m8793x() {
        return m8808p();
    }

    /* renamed from: y */
    public int m8792y() {
        return m8806q();
    }

    /* renamed from: z */
    public String m8791z() {
        return m8842c("userName");
    }

    /* renamed from: o */
    public void m8809o(String str) {
        m8853a("userName", str);
    }

    /* renamed from: j */
    public void m8821j(int i) {
        m8856a("controllerInterval", i);
    }

    /* renamed from: A */
    public int m8873A() {
        return m8829g("controllerInterval");
    }

    /* renamed from: B */
    public void m8872B() {
        this.f17229h.lock();
        try {
            m8817k("");
            this.f17225d.setDefaultValues();
        } finally {
            this.f17229h.unlock();
        }
    }

    /* renamed from: C */
    public long m8871C() {
        return this.f17227f.getLong(C6636f.m9093b("hotStartThreshold"), HarvestConfiguration.HOT_START_THRESHOLD);
    }

    /* renamed from: D */
    public long m8870D() {
        return this.f17227f.getLong(C6636f.m9093b("slowStartThreshold"), 3000L);
    }

    /* renamed from: E */
    public int m8869E() {
        return this.f17227f.getInt(C6636f.m9093b(ConfigurationName.anrThresholdName), 5000);
    }

    /* renamed from: c */
    public void m8843c(long j) {
        m8854a("hotStartThreshold", j);
    }

    /* renamed from: d */
    public void m8839d(long j) {
        m8854a("slowStartThreshold", j);
    }

    /* renamed from: u */
    private void m8797u(String str) {
        m8853a("ignoreErrRules", str);
    }

    /* renamed from: v */
    private void m8795v(String str) {
        m8853a("urlRules", str);
    }

    /* renamed from: F */
    public String m8868F() {
        return m8842c("urlRules");
    }

    /* renamed from: G */
    public String m8867G() {
        return m8842c("ignoreErrRules");
    }

    /* renamed from: H */
    public int m8866H() {
        if (C6653u.m8729b(this.f17224b)) {
            String str = ConfigurationName.features;
            return m8846b(str, this.f17224b.getPackageName() + "_main");
        }
        return m8846b(ConfigurationName.features, ConfigurationName.processName);
    }

    /* renamed from: b */
    private int m8846b(String str, String str2) {
        return this.f17224b.getSharedPreferences(C6653u.m8692j(str2), 0).getInt(C6636f.m9093b(str), 0);
    }

    /* renamed from: I */
    public int m8865I() {
        return m8829g("uiPages");
    }
}
