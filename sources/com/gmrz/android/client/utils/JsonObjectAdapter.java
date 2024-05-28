package com.gmrz.android.client.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JsonObjectAdapter {
    public static GsonBuilder GsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(JsonObject.class, new JsonDeserializer<JsonObject>() { // from class: com.gmrz.android.client.utils.JsonObjectAdapter.1
            @Override // com.google.gson.JsonDeserializer
            public final /* bridge */ /* synthetic */ JsonObject deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return (JsonObject) jsonElement;
            }
        });
        gsonBuilder.registerTypeAdapter(JsonObject.class, new JsonSerializer<JsonObject>() { // from class: com.gmrz.android.client.utils.JsonObjectAdapter.2
            @Override // com.google.gson.JsonSerializer
            public final /* bridge */ /* synthetic */ JsonElement serialize(JsonObject jsonObject, Type type, JsonSerializationContext jsonSerializationContext) {
                return jsonObject;
            }
        });
        gsonBuilder.disableHtmlEscaping();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder;
    }
}
