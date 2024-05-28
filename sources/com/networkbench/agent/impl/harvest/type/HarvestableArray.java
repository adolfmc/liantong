package com.networkbench.agent.impl.harvest.type;

import com.networkbench.com.google.gson.JsonArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class HarvestableArray extends BaseHarvestable {
    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public abstract JsonArray asJsonArray();

    public HarvestableArray() {
        super(HarvestableType.ARRAY);
    }
}
