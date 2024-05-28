package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.TypeAdapterFactory;
import com.networkbench.com.google.gson.internal.C$Gson$Types;
import com.networkbench.com.google.gson.internal.ConstructorConstructor;
import com.networkbench.com.google.gson.internal.ObjectConstructor;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6714c implements TypeAdapterFactory {

    /* renamed from: a */
    private final ConstructorConstructor f17376a;

    public C6714c(ConstructorConstructor constructorConstructor) {
        this.f17376a = constructorConstructor;
    }

    @Override // com.networkbench.com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        if (Collection.class.isAssignableFrom(rawType)) {
            Type collectionElementType = C$Gson$Types.getCollectionElementType(type, rawType);
            return new C6715a(gson, collectionElementType, gson.getAdapter(TypeToken.get(collectionElementType)), this.f17376a.get(typeToken));
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.a.c$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class C6715a<E> extends TypeAdapter<Collection<E>> {

        /* renamed from: a */
        private final TypeAdapter<E> f17377a;

        /* renamed from: b */
        private final ObjectConstructor<? extends Collection<E>> f17378b;

        public C6715a(Gson gson, Type type, TypeAdapter<E> typeAdapter, ObjectConstructor<? extends Collection<E>> objectConstructor) {
            this.f17377a = new C6735l(gson, typeAdapter, type);
            this.f17378b = objectConstructor;
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Collection<E> read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Collection<E> construct = this.f17378b.construct();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                construct.add(this.f17377a.read(jsonReader));
            }
            jsonReader.endArray();
            return construct;
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Collection<E> collection) throws IOException {
            if (collection == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginArray();
            for (E e : collection) {
                this.f17377a.write(jsonWriter, e);
            }
            jsonWriter.endArray();
        }
    }
}
