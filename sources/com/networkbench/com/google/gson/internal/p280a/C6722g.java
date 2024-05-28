package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonPrimitive;
import com.networkbench.com.google.gson.JsonSyntaxException;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.TypeAdapterFactory;
import com.networkbench.com.google.gson.internal.C$Gson$Types;
import com.networkbench.com.google.gson.internal.ConstructorConstructor;
import com.networkbench.com.google.gson.internal.JsonReaderInternalAccess;
import com.networkbench.com.google.gson.internal.ObjectConstructor;
import com.networkbench.com.google.gson.internal.Streams;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6722g implements TypeAdapterFactory {

    /* renamed from: a */
    private final ConstructorConstructor f17391a;

    /* renamed from: b */
    private final boolean f17392b;

    public C6722g(ConstructorConstructor constructorConstructor, boolean z) {
        this.f17391a = constructorConstructor;
        this.f17392b = z;
    }

    @Override // com.networkbench.com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        if (Map.class.isAssignableFrom(typeToken.getRawType())) {
            Type[] mapKeyAndValueTypes = C$Gson$Types.getMapKeyAndValueTypes(type, C$Gson$Types.getRawType(type));
            return new C6723a(gson, mapKeyAndValueTypes[0], m8604a(gson, mapKeyAndValueTypes[0]), mapKeyAndValueTypes[1], gson.getAdapter(TypeToken.get(mapKeyAndValueTypes[1])), this.f17391a.get(typeToken));
        }
        return null;
    }

    /* renamed from: a */
    private TypeAdapter<?> m8604a(Gson gson, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return C6736m.f17444f;
        }
        return gson.getAdapter(TypeToken.get(type));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.a.g$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    final class C6723a<K, V> extends TypeAdapter<Map<K, V>> {

        /* renamed from: b */
        private final TypeAdapter<K> f17394b;

        /* renamed from: c */
        private final TypeAdapter<V> f17395c;

        /* renamed from: d */
        private final ObjectConstructor<? extends Map<K, V>> f17396d;

        public C6723a(Gson gson, Type type, TypeAdapter<K> typeAdapter, Type type2, TypeAdapter<V> typeAdapter2, ObjectConstructor<? extends Map<K, V>> objectConstructor) {
            this.f17394b = new C6735l(gson, typeAdapter, type);
            this.f17395c = new C6735l(gson, typeAdapter2, type2);
            this.f17396d = objectConstructor;
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Map<K, V> read(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Map<K, V> construct = this.f17396d.construct();
            if (peek == JsonToken.BEGIN_ARRAY) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginArray();
                    K read = this.f17394b.read(jsonReader);
                    if (construct.put(read, this.f17395c.read(jsonReader)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + read);
                    }
                    jsonReader.endArray();
                }
                jsonReader.endArray();
            } else {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    JsonReaderInternalAccess.INSTANCE.promoteNameToValue(jsonReader);
                    K read2 = this.f17394b.read(jsonReader);
                    if (construct.put(read2, this.f17395c.read(jsonReader)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + read2);
                    }
                }
                jsonReader.endObject();
            }
            return construct;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
            if (map != null) {
                if (!C6722g.this.f17392b) {
                    jsonWriter.beginObject();
                    for (Map.Entry<K, V> entry : map.entrySet()) {
                        jsonWriter.name(String.valueOf(entry.getKey()));
                        this.f17395c.write(jsonWriter, entry.getValue());
                    }
                    jsonWriter.endObject();
                    return;
                }
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                boolean z = false;
                for (Map.Entry<K, V> entry2 : map.entrySet()) {
                    JsonElement jsonTree = this.f17394b.toJsonTree(entry2.getKey());
                    arrayList.add(jsonTree);
                    arrayList2.add(entry2.getValue());
                    z |= jsonTree.isJsonArray() || jsonTree.isJsonObject();
                }
                if (z) {
                    jsonWriter.beginArray();
                    while (i < arrayList.size()) {
                        jsonWriter.beginArray();
                        Streams.write((JsonElement) arrayList.get(i), jsonWriter);
                        this.f17395c.write(jsonWriter, arrayList2.get(i));
                        jsonWriter.endArray();
                        i++;
                    }
                    jsonWriter.endArray();
                    return;
                }
                jsonWriter.beginObject();
                while (i < arrayList.size()) {
                    jsonWriter.name(m8602a((JsonElement) arrayList.get(i)));
                    this.f17395c.write(jsonWriter, arrayList2.get(i));
                    i++;
                }
                jsonWriter.endObject();
                return;
            }
            jsonWriter.nullValue();
        }

        /* renamed from: a */
        private String m8602a(JsonElement jsonElement) {
            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (asJsonPrimitive.isNumber()) {
                    return String.valueOf(asJsonPrimitive.getAsNumber());
                }
                if (asJsonPrimitive.isBoolean()) {
                    return Boolean.toString(asJsonPrimitive.getAsBoolean());
                }
                if (asJsonPrimitive.isString()) {
                    return asJsonPrimitive.getAsString();
                }
                throw new AssertionError();
            } else if (jsonElement.isJsonNull()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }
}
