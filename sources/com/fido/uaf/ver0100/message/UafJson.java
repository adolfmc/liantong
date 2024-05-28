package com.fido.uaf.ver0100.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafJson {
    public String stringify() {
        Gson create = new GsonBuilder().create();
        return !(create instanceof Gson) ? create.toJson(this) : NBSGsonInstrumentation.toJson(create, this);
    }

    protected Object fromJson(String str) {
        Gson create = new GsonBuilder().create();
        Class<?> cls = getClass();
        return !(create instanceof Gson) ? create.fromJson(str, (Class<Object>) cls) : NBSGsonInstrumentation.fromJson(create, str, (Class<Object>) cls);
    }
}
