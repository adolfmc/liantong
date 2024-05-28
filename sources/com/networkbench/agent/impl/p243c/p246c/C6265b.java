package com.networkbench.agent.impl.p243c.p246c;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.c.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6265b extends HarvestableObject {

    /* renamed from: a */
    AbstractC6590h f15610a;

    public C6265b(AbstractC6590h abstractC6590h) {
        this.f15610a = abstractC6590h;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject asJsonObject = this.f15610a.asJsonObject();
        if (asJsonObject != null) {
            asJsonObject.add("taskId", new JsonPrimitive(this.f15610a.f16889h.f15613a));
            asJsonObject.add("plugin", new JsonPrimitive(this.f15610a.f16889h.f15615c));
            asJsonObject.add("pluginVer", new JsonPrimitive(NBSAgent.getTaskDataVersion()));
        }
        return asJsonObject;
    }
}
