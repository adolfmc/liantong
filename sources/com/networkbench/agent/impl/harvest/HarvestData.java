package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.C6302f;
import com.networkbench.agent.impl.p243c.p244a.C6257h;
import com.networkbench.agent.impl.p243c.p246c.C6266c;
import com.networkbench.agent.impl.p243c.p247d.C6276h;
import com.networkbench.agent.impl.p243c.p248e.C6283b;
import com.networkbench.agent.impl.p243c.p248e.C6285d;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.p269a.C6505b;
import com.networkbench.agent.impl.p268n.p269a.C6514f;
import com.networkbench.agent.impl.socket.C6620q;
import com.networkbench.com.google.gson.JsonArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HarvestData extends HarvestableArray {
    private double harvestTimeDelta;
    private static final InterfaceC6393e log = C6394f.m10150a();
    private static C6285d appStartDatas = new C6285d();
    private static C6276h pageDatas = new C6276h();
    public static C6276h successPageDatas = new C6276h();
    private static C6266c pluginData = new C6266c();
    private static C6283b appHotStartData = new C6283b();
    private NetworkPerfMetrics networkPerfMetrics = new NetworkPerfMetrics();
    private C6514f webviewPerfMetricsV2 = new C6514f();
    private C6257h nbsEventActions = new C6257h();

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        return new JsonArray();
    }

    public void reset() {
        this.networkPerfMetrics.reset();
        this.webviewPerfMetricsV2.m9578a();
        pluginData.m10741c();
    }

    public static C6276h getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(C6276h c6276h) {
        pageDatas = c6276h;
    }

    public C6302f getHttpErrors() {
        return this.networkPerfMetrics.getErrorDatas();
    }

    public C6620q getSocketDatas() {
        return this.networkPerfMetrics.getSocketdatas();
    }

    public ActionDatas getActionDatas() {
        return this.networkPerfMetrics.getActionDatas();
    }

    public C6257h getNbsEventActions() {
        return this.nbsEventActions;
    }

    public NetworkPerfMetrics getNetworkPerfMetrics() {
        return this.networkPerfMetrics;
    }

    public static C6285d getAppStartDatas() {
        return appStartDatas;
    }

    public C6505b getWebViewTransactions() {
        return this.webviewPerfMetricsV2.m9577b();
    }

    public C6505b getJsErrors() {
        return this.webviewPerfMetricsV2.m9576c();
    }

    public C6514f getWebViewPerfMetrics() {
        return this.webviewPerfMetricsV2;
    }

    public C6266c getPluginData() {
        return pluginData;
    }

    public void setPluginData(C6266c c6266c) {
        pluginData = c6266c;
    }

    public static C6283b getAppHotStartData() {
        return appHotStartData;
    }

    public static void setAppHotStartData(C6283b c6283b) {
        appHotStartData = c6283b;
    }
}
