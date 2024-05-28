package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p243c.C6302f;
import com.networkbench.agent.impl.p243c.C6303g;
import com.networkbench.agent.impl.plugin.p270a.C6531a;
import com.networkbench.agent.impl.socket.C6620q;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NetworkPerfMetrics extends HarvestableObject {
    private C6531a cellInfoCollect;
    private ActionDatas actionDatas = new ActionDatas();
    private C6302f errorDatas = new C6302f();
    private C6620q socketdatas = new C6620q();
    private C6303g hijackData = new C6303g();

    public int getCellInfoCollect() {
        return this.cellInfoCollect != null ? 1 : 0;
    }

    public void setCellInfoCollect(C6531a c6531a) {
        this.cellInfoCollect = c6531a;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        C6531a c6531a;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("networkPerfMetrics"));
        jsonObject.add("bg", new JsonPrimitive((Number) Integer.valueOf(C6638h.f17114n)));
        jsonObject.add("interval", new JsonPrimitive((Number) Long.valueOf(C6638h.m8963w().m8966t())));
        jsonObject.add("dev", NBSAgent.getDeviceData().asJson());
        jsonObject.add("actions", this.actionDatas.asJson());
        jsonObject.add("sockets", this.socketdatas.asJson());
        jsonObject.add("metrics", NBSAgent.getSystemInfo().asJson());
        jsonObject.add("errs", this.errorDatas.asJson());
        jsonObject.add("hijack", this.hijackData.asJson());
        if (isSingleCellInfoShouldCollect() && (c6531a = this.cellInfoCollect) != null) {
            jsonObject.add("cellInfo", c6531a.asJson());
        }
        return jsonObject;
    }

    private boolean isSingleCellInfoShouldCollect() {
        try {
            List<String> cellInfoConfig = HarvestConfiguration.getCellInfoConfig();
            if (cellInfoConfig != null) {
                if (cellInfoConfig.size() > 0) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public ActionDatas getActionDatas() {
        return this.actionDatas;
    }

    public C6302f getErrorDatas() {
        return this.errorDatas;
    }

    public C6620q getSocketdatas() {
        return this.socketdatas;
    }

    public C6303g getHijackData() {
        return this.hijackData;
    }

    public void reset() {
        this.errorDatas.m10529a();
        this.actionDatas.clear();
        this.socketdatas.m9209a();
        this.hijackData.m10522a();
    }
}
