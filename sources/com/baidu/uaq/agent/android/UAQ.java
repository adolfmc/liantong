package com.baidu.uaq.agent.android;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;
import com.baidu.uaq.agent.android.AgentConfig;
import com.baidu.uaq.agent.android.customtransmission.APMAgent;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import p001a.p058b.p062b.p063a.p064a.C0719b;
import p001a.p058b.p062b.p063a.p064a.C0748d;
import p001a.p058b.p062b.p063a.p064a.C0753e;
import p001a.p058b.p062b.p063a.p064a.C0762h;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0730g;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0731h;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p070c.C0738a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0750b;
import p001a.p058b.p062b.p063a.p064a.p072d.C0752d;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\567196_dexfile_execute.dex */
public class UAQ {
    public static final InterfaceC3321a LOG = C0749a.f2299a;
    public static volatile UAQ instance;
    public AgentConfig savedConfig;
    public boolean started = false;
    public boolean isDisableCollect = false;
    public boolean needBasicInfo = true;
    public AgentConfig agentConfig = new AgentConfig.Builder().build();

    public static UAQ getInstance() {
        if (instance == null) {
            synchronized (UAQ.class) {
                if (instance == null) {
                    instance = new UAQ();
                }
            }
        }
        return instance;
    }

    public static String getVersion() {
        return C0719b.m22323c() + "." + C0719b.m22324b();
    }

    private boolean isInstrumented() {
        return false;
    }

    public static void onLiveEvent(String str) {
        InterfaceC3321a interfaceC3321a = LOG;
        interfaceC3321a.mo17428D("Get Live Event: " + str);
        if (str.isEmpty()) {
            return;
        }
        C0730g c0730g = new C0730g(str);
        if (C0731h.f2234b.getConfig().isEnableTrace()) {
            C0762h.m22248a(c0730g);
        }
    }

    @Deprecated
    public static UAQ setCUID(String str) {
        getInstance().setConfig(new AgentConfig.Builder().APIKey("0b32398f92284103a76f03680104c775").cuid(str).channel("APM_TEST_CHANNEL").logEnabled(true).build());
        return getInstance();
    }

    public static void setxxDebug(boolean z) {
    }

    @Deprecated
    public static void shutdown() {
        getInstance().shutdown_v2();
    }

    @Deprecated
    public static UAQ usingCollectorAddress(String str) {
        return getInstance();
    }

    @Deprecated
    public static UAQ usingSsl(boolean z) {
        return getInstance();
    }

    @Deprecated
    public static UAQ withApplicationToken(String str) {
        return getInstance();
    }

    @Deprecated
    public static UAQ withLogLevel(int i) {
        return getInstance();
    }

    @Deprecated
    public static UAQ withLogPersist(boolean z) {
        return getInstance();
    }

    @Deprecated
    public static UAQ withLoggingEnabled(boolean z) {
        return getInstance();
    }

    @Deprecated
    public void agentExceptionNow() {
        C0735a.m22302a(new RuntimeException("This is a demonstration agent exception of UAQ"));
    }

    public void disableCollect() {
        this.agentConfig = this.agentConfig.newBuilder().build();
    }

    public void enableCollect(AgentConfig agentConfig) {
        if (agentConfig == null) {
            throw new NullPointerException("savedConfig == null.");
        }
        this.agentConfig = agentConfig;
    }

    public AgentConfig getConfig() {
        return this.agentConfig;
    }

    public AgentConfig getSavedConfig() {
        return this.savedConfig;
    }

    @Deprecated
    public void harvestNow() {
    }

    public boolean isDisableCollect() {
        return this.isDisableCollect;
    }

    public boolean isNeedBasicInfo() {
        return this.needBasicInfo;
    }

    public boolean isStarted() {
        return this.started;
    }

    public synchronized void reconfig(AgentConfig agentConfig) {
        if (agentConfig == null) {
            throw new NullPointerException("agentConfig == null.");
        }
        this.agentConfig = agentConfig;
    }

    public UAQ setConfig(AgentConfig agentConfig) {
        if (agentConfig != null) {
            this.agentConfig = agentConfig;
            return instance;
        }
        throw new NullPointerException("agentConfig == null.");
    }

    public void setDisableCollect(boolean z) {
        this.isDisableCollect = z;
    }

    public void setNeedBasicInfo(boolean z) {
        this.needBasicInfo = z;
    }

    public void setSavedConfig(AgentConfig agentConfig) {
        this.savedConfig = agentConfig;
    }

    @Deprecated
    public synchronized void shutdown_v2() {
        LOG.mo17428D("Agent try to shutdown");
        if (!isStarted()) {
            C0719b.m22325a(C0753e.f2302a);
            LOG.mo17428D("Agent finish shutdown");
            return;
        }
        disableCollect();
        this.started = false;
        C0719b.m22326a().shutdown();
        C0719b.m22325a(C0753e.f2302a);
        LOG.mo17428D("Agent finish shutdown");
    }

    public synchronized APMAgent startAPM(Context context) {
        APMAgent aPMAgent;
        aPMAgent = new APMAgent();
        aPMAgent.setAgent(aPMAgent);
        long currentTimeMillis = System.currentTimeMillis();
        if (!(C0719b.m22326a() instanceof C0748d)) {
            C0749a.f2299a.m22270a(this.agentConfig.isLogEnabled() ? new C0750b() : new C0752d());
            LOG.setLevel(this.agentConfig.getLogLevel());
            C0748d.m22271a(context);
        }
        C0762h.m22249a();
        C0738a.f2254b.m22298a(context);
        long currentTimeMillis2 = System.currentTimeMillis();
        InterfaceC3321a interfaceC3321a = LOG;
        StringBuilder sb = new StringBuilder();
        sb.append("Start UAQ ");
        sb.append(C0719b.m22323c());
        sb.append(".");
        sb.append(C0719b.m22324b());
        sb.append(", using time: ");
        sb.append(currentTimeMillis2 - currentTimeMillis);
        sb.append("ms");
        interfaceC3321a.mo17428D(sb.toString());
        Log.d("Baidu UAQ APM", "Start UAQ APM instance success!");
        return aPMAgent;
    }
}
