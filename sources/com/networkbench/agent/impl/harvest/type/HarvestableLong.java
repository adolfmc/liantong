package com.networkbench.agent.impl.harvest.type;

import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HarvestableLong extends HarvestableValue {
    private long value;

    public HarvestableLong() {
    }

    public HarvestableLong(long j) {
        this();
        this.value = j;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableValue, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonPrimitive asJsonPrimitive() {
        return new JsonPrimitive((Number) Long.valueOf(this.value));
    }
}
