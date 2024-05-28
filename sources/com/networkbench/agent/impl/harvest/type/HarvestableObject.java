package com.networkbench.agent.impl.harvest.type;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.JsonObject;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class HarvestableObject extends BaseHarvestable {
    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public abstract JsonObject asJsonObject();

    public static HarvestableObject fromMap(final Map<String, Object> map) {
        return new HarvestableObject() { // from class: com.networkbench.agent.impl.harvest.type.HarvestableObject.1
            @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
            public JsonObject asJsonObject() {
                return (JsonObject) new Gson().toJsonTree(map, this.f16329k);
            }
        };
    }

    public static HarvestableObject fromMapString(final Map<String, String> map) {
        return new HarvestableObject() { // from class: com.networkbench.agent.impl.harvest.type.HarvestableObject.2
            @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
            public JsonObject asJsonObject() {
                return (JsonObject) new Gson().toJsonTree(map, this.f16329k);
            }
        };
    }

    public HarvestableObject() {
        super(HarvestableType.OBJECT);
    }
}
