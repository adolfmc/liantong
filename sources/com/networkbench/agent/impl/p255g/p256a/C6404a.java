package com.networkbench.agent.impl.p255g.p256a;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.ActionDatas;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.EnumC6421f;
import com.networkbench.agent.impl.p255g.InterfaceC6403a;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6648q;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6404a extends C6407d {

    /* renamed from: a */
    private static final InterfaceC6393e f16185a = C6394f.m10150a();

    public C6404a() {
        super(EnumC6421f.Network);
    }

    @Override // com.networkbench.agent.impl.p255g.p256a.C6407d, com.networkbench.agent.impl.p255g.p256a.InterfaceC6406c
    /* renamed from: a */
    public void mo10071a(InterfaceC6403a interfaceC6403a) {
        C6410a c6410a = (C6410a) interfaceC6403a;
        if (Harvest.addHttpTransaction(m10073a(c6410a)) == -1) {
            C6648q.m8781a(c6410a);
        }
    }

    /* renamed from: a */
    public ActionData m10073a(C6410a c6410a) {
        ActionData actionData = new ActionData();
        actionData.setUrl(c6410a.m10060d());
        actionData.setStatusCode(c6410a.m10061c().m10952s());
        actionData.setErrorCode(c6410a.m10061c().m10951t());
        actionData.setTotalTime(c6410a.m10061c().m10988B());
        actionData.setCarrier(NBSAgent.getActiveNetworkCarrier());
        actionData.setBytesReceived(c6410a.m10061c().m10948w());
        actionData.setBytesSent(c6410a.m10061c().m10949v());
        actionData.setConnectType(c6410a.m10061c().m10967g());
        actionData.setAppData(c6410a.m10061c().m10947x());
        actionData.setAppDataNew(c6410a.m10061c().m10946y());
        actionData.setTimestamp(Long.valueOf(c6410a.mo10065b()));
        actionData.setUrlParams(c6410a.m10061c().m10950u());
        actionData.setRequestMethod(c6410a.m10061c().m10954q());
        actionData.setHttpLibType(c6410a.m10061c().m10945z());
        actionData.setTime_to_connect(c6410a.m10061c().m10958m());
        actionData.setIP(c6410a.m10061c().m10959l());
        actionData.setTime_to_dns(c6410a.m10061c().m10960k());
        if (actionData.getUrl().toLowerCase().startsWith("https:")) {
            actionData.setTime_ssl_handshake(c6410a.m10061c().m10957n());
        }
        actionData.setTime_first_package(c6410a.m10061c().m10956o());
        actionData.setCdnVendorName(c6410a.m10061c().m10961j());
        actionData.setAppPhase(c6410a.m10061c().m10955p());
        actionData.setContentType(c6410a.m10061c().m10963i());
        actionData.setTimeToQueueTime(c6410a.m10061c().m10977c());
        actionData.setRemainPkTime(c6410a.m10061c().m10980b());
        actionData.requestHeaderParam = c6410a.m10061c().f15375b;
        actionData.responseHeaderParam = c6410a.m10061c().f15376c;
        actionData.setDataTag(c6410a.m10061c().m10969f());
        C6396h.m10126p("requestHeaderParam : " + actionData.requestHeaderParam.size());
        C6396h.m10126p("responseHeaderParam : " + actionData.responseHeaderParam.size());
        actionData.correctDataInfo();
        return actionData;
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestAdapter, com.networkbench.agent.impl.harvest.HarvestLifecycleAware
    public void onHarvestFilter() {
        ActionDatas actionDatas = Harvest.getInstance().getHarvestData().getActionDatas();
        if (actionDatas != null) {
            ArrayList<ActionData> arrayList = new ArrayList();
            for (ActionData actionData : actionDatas.getActionDatas()) {
                if (C6642k.m8903e(actionData.getUrl())) {
                    arrayList.add(actionData);
                }
                if (C6642k.m8921a(actionData.getStatusCode(), actionData.getUrl())) {
                    actionData.setStatusCode(200);
                    actionData.setErrorCode(0);
                }
            }
            for (ActionData actionData2 : arrayList) {
                actionDatas.remove(actionData2);
            }
        }
    }
}
