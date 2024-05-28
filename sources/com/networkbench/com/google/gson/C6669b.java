package com.networkbench.com.google.gson;

import com.networkbench.com.google.gson.internal.C$Gson$Preconditions;
import com.networkbench.com.google.gson.internal.Streams;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6669b<T> extends TypeAdapter<T> {

    /* renamed from: a */
    private final JsonSerializer<T> f17265a;

    /* renamed from: b */
    private final JsonDeserializer<T> f17266b;

    /* renamed from: c */
    private final Gson f17267c;

    /* renamed from: d */
    private final TypeToken<T> f17268d;

    /* renamed from: e */
    private final TypeAdapterFactory f17269e;

    /* renamed from: f */
    private TypeAdapter<T> f17270f;

    private C6669b(JsonSerializer<T> jsonSerializer, JsonDeserializer<T> jsonDeserializer, Gson gson, TypeToken<T> typeToken, TypeAdapterFactory typeAdapterFactory) {
        this.f17265a = jsonSerializer;
        this.f17266b = jsonDeserializer;
        this.f17267c = gson;
        this.f17268d = typeToken;
        this.f17269e = typeAdapterFactory;
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        if (this.f17266b == null) {
            return m8656a().read(jsonReader);
        }
        JsonElement parse = Streams.parse(jsonReader);
        if (parse.isJsonNull()) {
            return null;
        }
        return this.f17266b.deserialize(parse, this.f17268d.getType(), this.f17267c.f17252b);
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        JsonSerializer<T> jsonSerializer = this.f17265a;
        if (jsonSerializer == null) {
            m8656a().write(jsonWriter, t);
        } else if (t == null) {
            jsonWriter.nullValue();
        } else {
            Streams.write(jsonSerializer.serialize(t, this.f17268d.getType(), this.f17267c.f17253c), jsonWriter);
        }
    }

    /* renamed from: a */
    private TypeAdapter<T> m8656a() {
        TypeAdapter<T> typeAdapter = this.f17270f;
        if (typeAdapter != null) {
            return typeAdapter;
        }
        TypeAdapter<T> delegateAdapter = this.f17267c.getDelegateAdapter(this.f17269e, this.f17268d);
        this.f17270f = delegateAdapter;
        return delegateAdapter;
    }

    /* renamed from: a */
    public static TypeAdapterFactory m8655a(TypeToken<?> typeToken, Object obj) {
        return new C6671a(obj, typeToken, false, null);
    }

    /* renamed from: b */
    public static TypeAdapterFactory m8653b(TypeToken<?> typeToken, Object obj) {
        return new C6671a(obj, typeToken, typeToken.getType() == typeToken.getRawType(), null);
    }

    /* renamed from: a */
    public static TypeAdapterFactory m8654a(Class<?> cls, Object obj) {
        return new C6671a(obj, null, false, cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6671a implements TypeAdapterFactory {

        /* renamed from: a */
        private final TypeToken<?> f17271a;

        /* renamed from: b */
        private final boolean f17272b;

        /* renamed from: c */
        private final Class<?> f17273c;

        /* renamed from: d */
        private final JsonSerializer<?> f17274d;

        /* renamed from: e */
        private final JsonDeserializer<?> f17275e;

        private C6671a(Object obj, TypeToken<?> typeToken, boolean z, Class<?> cls) {
            this.f17274d = obj instanceof JsonSerializer ? (JsonSerializer) obj : null;
            this.f17275e = obj instanceof JsonDeserializer ? (JsonDeserializer) obj : null;
            C$Gson$Preconditions.checkArgument((this.f17274d == null && this.f17275e == null) ? false : true);
            this.f17271a = typeToken;
            this.f17272b = z;
            this.f17273c = cls;
        }

        @Override // com.networkbench.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            boolean isAssignableFrom;
            TypeToken<?> typeToken2 = this.f17271a;
            if (typeToken2 != null) {
                isAssignableFrom = typeToken2.equals(typeToken) || (this.f17272b && this.f17271a.getType() == typeToken.getRawType());
            } else {
                isAssignableFrom = this.f17273c.isAssignableFrom(typeToken.getRawType());
            }
            if (isAssignableFrom) {
                return new C6669b(this.f17274d, this.f17275e, gson, typeToken, this);
            }
            return null;
        }
    }
}
