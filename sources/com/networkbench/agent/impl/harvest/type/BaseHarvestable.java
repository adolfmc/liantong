package com.networkbench.agent.impl.harvest.type;

import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import com.networkbench.com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BaseHarvestable implements Harvestable {

    /* renamed from: k */
    protected final Type f16329k = new TypeToken<Map>() { // from class: com.networkbench.agent.impl.harvest.type.BaseHarvestable.1
    }.getType();
    private final HarvestableType type;

    @Override // com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        return null;
    }

    @Override // com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        return null;
    }

    @Override // com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonPrimitive asJsonPrimitive() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: h */
    public String m9931h(String str) {
        return str == null ? "" : str;
    }

    public BaseHarvestable(HarvestableType harvestableType) {
        this.type = harvestableType;
    }

    @Override // com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonElement asJson() {
        switch (this.type.ordinal()) {
            case 0:
                return asJsonObject();
            case 1:
                return asJsonArray();
            case 2:
                return asJsonPrimitive();
            default:
                return null;
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.Harvestable
    public HarvestableType getType() {
        return this.type;
    }

    @Override // com.networkbench.agent.impl.harvest.type.Harvestable
    public String toJsonString() {
        return asJson().toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: g */
    public void m9932g(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Missing Harvestable field.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m9933a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Null field in Harvestable object");
        }
    }
}
