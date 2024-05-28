package com.networkbench.agent.impl.harvest.type;

import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface Harvestable {
    JsonElement asJson();

    JsonArray asJsonArray();

    JsonObject asJsonObject();

    JsonPrimitive asJsonPrimitive();

    HarvestableType getType();

    String toJsonString();
}
