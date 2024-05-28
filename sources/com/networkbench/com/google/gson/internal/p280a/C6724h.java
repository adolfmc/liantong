package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.TypeAdapterFactory;
import com.networkbench.com.google.gson.internal.LinkedTreeMap;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6724h extends TypeAdapter<Object> {

    /* renamed from: a */
    public static final TypeAdapterFactory f17397a = new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.h.1
        @Override // com.networkbench.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Object.class) {
                return new C6724h(gson);
            }
            return null;
        }
    };

    /* renamed from: b */
    private final Gson f17398b;

    private C6724h(Gson gson) {
        this.f17398b = gson;
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(read(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case BEGIN_OBJECT:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    linkedTreeMap.put(jsonReader.nextName(), read(jsonReader));
                }
                jsonReader.endObject();
                return linkedTreeMap;
            case STRING:
                return jsonReader.nextString();
            case NUMBER:
                return Double.valueOf(jsonReader.nextDouble());
            case BOOLEAN:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case NULL:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter adapter = this.f17398b.getAdapter(obj.getClass());
        if (adapter instanceof C6724h) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
            return;
        }
        adapter.write(jsonWriter, obj);
    }
}
