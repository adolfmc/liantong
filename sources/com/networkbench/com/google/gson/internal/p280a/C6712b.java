package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.TypeAdapterFactory;
import com.networkbench.com.google.gson.internal.C$Gson$Types;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6712b<E> extends TypeAdapter<Object> {

    /* renamed from: a */
    public static final TypeAdapterFactory f17373a = new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.b.1
        @Override // com.networkbench.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Type type = typeToken.getType();
            if ((type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray())) {
                Type arrayComponentType = C$Gson$Types.getArrayComponentType(type);
                return new C6712b(gson, gson.getAdapter(TypeToken.get(arrayComponentType)), C$Gson$Types.getRawType(arrayComponentType));
            }
            return null;
        }
    };

    /* renamed from: b */
    private final Class<E> f17374b;

    /* renamed from: c */
    private final TypeAdapter<E> f17375c;

    public C6712b(Gson gson, TypeAdapter<E> typeAdapter, Class<E> cls) {
        this.f17375c = new C6735l(gson, typeAdapter, cls);
        this.f17374b = cls;
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(this.f17375c.read(jsonReader));
        }
        jsonReader.endArray();
        Object newInstance = Array.newInstance((Class<?>) this.f17374b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.networkbench.com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f17375c.write(jsonWriter, Array.get(obj, i));
        }
        jsonWriter.endArray();
    }
}
