package com.unicom.pay.utils.jwt;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class WPJWTDeserializer implements JsonDeserializer<C10707d> {
    /* renamed from: a */
    public final String m6059a(JsonObject jsonObject) {
        if (jsonObject.has("iss")) {
            return jsonObject.get("iss").getAsString();
        }
        return null;
    }

    /* renamed from: a */
    public final Date m6058a(JsonObject jsonObject, String str) {
        if (jsonObject.has(str)) {
            return new Date(jsonObject.get(str).getAsLong() * 1000);
        }
        return null;
    }

    @Override // com.google.gson.JsonDeserializer
    public final C10707d deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        if (jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
            throw new C10703b("The token's payload had an invalid JSON format.");
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        m6059a(asJsonObject);
        if (asJsonObject.has("sub")) {
            asJsonObject.get("sub").getAsString();
        }
        Date m6058a = m6058a(asJsonObject, "exp");
        m6058a(asJsonObject, "nbf");
        m6058a(asJsonObject, "iat");
        if (asJsonObject.has("jti")) {
            asJsonObject.get("jti").getAsString();
        }
        Collections.emptyList();
        if (asJsonObject.has("aud")) {
            JsonElement jsonElement2 = asJsonObject.get("aud");
            if (jsonElement2.isJsonArray()) {
                JsonArray asJsonArray = jsonElement2.getAsJsonArray();
                ArrayList arrayList = new ArrayList(asJsonArray.size());
                for (int i = 0; i < asJsonArray.size(); i++) {
                    arrayList.add(asJsonArray.get(i).getAsString());
                }
            } else {
                Collections.singletonList(jsonElement2.getAsString());
            }
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            entry.getValue();
            hashMap.put(entry.getKey(), new C10702a());
        }
        return new C10707d(m6058a, hashMap);
    }
}
