package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.FieldNamingStrategy;
import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.Gson$$$Internal;
import com.networkbench.com.google.gson.JsonSyntaxException;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.TypeAdapterFactory;
import com.networkbench.com.google.gson.annotations.Adapt;
import com.networkbench.com.google.gson.annotations.SerializedName;
import com.networkbench.com.google.gson.internal.C$Gson$Types;
import com.networkbench.com.google.gson.internal.ConstructorConstructor;
import com.networkbench.com.google.gson.internal.Excluder;
import com.networkbench.com.google.gson.internal.ObjectConstructor;
import com.networkbench.com.google.gson.internal.Primitives;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6727i implements TypeAdapterFactory {

    /* renamed from: a */
    private final ConstructorConstructor f17400a;

    /* renamed from: b */
    private final FieldNamingStrategy f17401b;

    /* renamed from: c */
    private final Excluder f17402c;

    public C6727i(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder) {
        this.f17400a = constructorConstructor;
        this.f17401b = fieldNamingStrategy;
        this.f17402c = excluder;
    }

    /* renamed from: a */
    public boolean m8594a(Field field, boolean z) {
        return (this.f17402c.excludeClass(field.getType(), z) || this.f17402c.excludeField(field, z)) ? false : true;
    }

    /* renamed from: a */
    private String m8595a(Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        return serializedName == null ? this.f17401b.translateName(field) : serializedName.value();
    }

    @Override // com.networkbench.com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            C6729a c6729a = new C6729a(this.f17400a.get(typeToken), m8599a(gson, (TypeToken<?>) typeToken, (Class<?>) rawType));
            Gson$$$Internal.addGeneratedTypeAdapter(gson, c6729a);
            return c6729a;
        }
        return null;
    }

    /* renamed from: a */
    private AbstractC6730b m8597a(final Gson gson, final Field field, String str, final TypeToken<?> typeToken, boolean z, boolean z2) {
        final boolean isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        return new AbstractC6730b(str, z, z2) { // from class: com.networkbench.com.google.gson.internal.a.i.1

            /* renamed from: a */
            final TypeAdapter<?> f17403a;

            {
                this.f17403a = C6727i.this.m8598a(gson, field, typeToken);
            }

            @Override // com.networkbench.com.google.gson.internal.p280a.C6727i.AbstractC6730b
            /* renamed from: a */
            void mo8592a(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException {
                new C6735l(gson, this.f17403a, typeToken.getType()).write(jsonWriter, field.get(obj));
            }

            @Override // com.networkbench.com.google.gson.internal.p280a.C6727i.AbstractC6730b
            /* renamed from: a */
            void mo8593a(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
                Object read = this.f17403a.read(jsonReader);
                if (read == null && isPrimitive) {
                    return;
                }
                field.set(obj, read);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public TypeAdapter<?> m8598a(Gson gson, Field field, TypeToken<?> typeToken) {
        TypeAdapter<?> adapter = gson.getAdapter(typeToken);
        if (Gson$$$Internal.isGeneratedTypeAdapter(gson, adapter) && field.isAnnotationPresent(Adapt.class)) {
            return C6711a.m8618a(gson, this.f17400a, (Adapt) field.getAnnotation(Adapt.class));
        }
        return adapter;
    }

    /* renamed from: a */
    private Map<String, AbstractC6730b> m8599a(Gson gson, TypeToken<?> typeToken, Class<?> cls) {
        Field[] declaredFields;
        AbstractC6730b abstractC6730b;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = typeToken.getType();
        TypeToken<?> typeToken2 = typeToken;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            for (Field field : cls2.getDeclaredFields()) {
                boolean m8594a = m8594a(field, true);
                boolean m8594a2 = m8594a(field, false);
                if (m8594a || m8594a2) {
                    field.setAccessible(true);
                    AbstractC6730b m8597a = m8597a(gson, field, m8595a(field), TypeToken.get(C$Gson$Types.resolve(typeToken2.getType(), cls2, field.getGenericType())), m8594a, m8594a2);
                    if (((AbstractC6730b) linkedHashMap.put(m8597a.f17411g, m8597a)) != null) {
                        throw new IllegalArgumentException(type + " declares multiple JSON fields named " + abstractC6730b.f17411g);
                    }
                }
            }
            typeToken2 = TypeToken.get(C$Gson$Types.resolve(typeToken2.getType(), cls2, cls2.getGenericSuperclass()));
            cls2 = typeToken2.getRawType();
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.a.i$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static abstract class AbstractC6730b {

        /* renamed from: g */
        final String f17411g;

        /* renamed from: h */
        final boolean f17412h;

        /* renamed from: i */
        final boolean f17413i;

        /* renamed from: a */
        abstract void mo8593a(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        /* renamed from: a */
        abstract void mo8592a(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        protected AbstractC6730b(String str, boolean z, boolean z2) {
            this.f17411g = str;
            this.f17412h = z;
            this.f17413i = z2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.a.i$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6729a<T> extends TypeAdapter<T> {

        /* renamed from: a */
        private final ObjectConstructor<T> f17409a;

        /* renamed from: b */
        private final Map<String, AbstractC6730b> f17410b;

        private C6729a(ObjectConstructor<T> objectConstructor, Map<String, AbstractC6730b> map) {
            this.f17409a = objectConstructor;
            this.f17410b = map;
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        public T read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            T construct = this.f17409a.construct();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    AbstractC6730b abstractC6730b = this.f17410b.get(jsonReader.nextName());
                    if (abstractC6730b != null && abstractC6730b.f17413i) {
                        abstractC6730b.mo8593a(jsonReader, construct);
                    }
                    jsonReader.skipValue();
                }
                jsonReader.endObject();
                return construct;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (AbstractC6730b abstractC6730b : this.f17410b.values()) {
                    if (abstractC6730b.f17412h) {
                        jsonWriter.name(abstractC6730b.f17411g);
                        abstractC6730b.mo8592a(jsonWriter, t);
                    }
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }
    }
}
