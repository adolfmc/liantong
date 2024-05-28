package com.networkbench.com.google.gson;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.networkbench.com.google.gson.LongSerializationPolicy.1
        @Override // com.networkbench.com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive((Number) l);
        }
    },
    STRING { // from class: com.networkbench.com.google.gson.LongSerializationPolicy.2
        @Override // com.networkbench.com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive(String.valueOf(l));
        }
    };

    public abstract JsonElement serialize(Long l);
}
