package com.networkbench.agent.impl.plugin.p274e;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.e.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6563e {
    /* renamed from: a */
    public static JsonObject m9385a(AbstractC6590h abstractC6590h) {
        JsonObject asJsonObject = abstractC6590h.asJsonObject();
        asJsonObject.add("plugin", new JsonPrimitive(abstractC6590h.f16889h.f15615c));
        asJsonObject.add("pluginVer", new JsonPrimitive(NBSAgent.getTaskDataVersion()));
        C6396h.m10137e("createUnknownData : " + asJsonObject.toString());
        return asJsonObject;
    }
}
