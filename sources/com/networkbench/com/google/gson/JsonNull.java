package com.networkbench.com.google.gson;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.networkbench.com.google.gson.JsonElement
    /* renamed from: a */
    public JsonNull mo8661b() {
        return INSTANCE;
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }

    public boolean equals(Object obj) {
        return this == obj || (obj instanceof JsonNull);
    }
}
