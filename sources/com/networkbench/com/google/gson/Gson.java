package com.networkbench.com.google.gson;

import com.networkbench.com.google.gson.internal.ConstructorConstructor;
import com.networkbench.com.google.gson.internal.Excluder;
import com.networkbench.com.google.gson.internal.Primitives;
import com.networkbench.com.google.gson.internal.Streams;
import com.networkbench.com.google.gson.internal.p280a.C6711a;
import com.networkbench.com.google.gson.internal.p280a.C6712b;
import com.networkbench.com.google.gson.internal.p280a.C6714c;
import com.networkbench.com.google.gson.internal.p280a.C6716d;
import com.networkbench.com.google.gson.internal.p280a.C6718e;
import com.networkbench.com.google.gson.internal.p280a.C6720f;
import com.networkbench.com.google.gson.internal.p280a.C6722g;
import com.networkbench.com.google.gson.internal.p280a.C6724h;
import com.networkbench.com.google.gson.internal.p280a.C6727i;
import com.networkbench.com.google.gson.internal.p280a.C6731j;
import com.networkbench.com.google.gson.internal.p280a.C6733k;
import com.networkbench.com.google.gson.internal.p280a.C6736m;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import com.networkbench.com.google.gson.stream.JsonWriter;
import com.networkbench.com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class Gson {
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";

    /* renamed from: a */
    static final boolean f17251a = false;

    /* renamed from: b */
    final JsonDeserializationContext f17252b;

    /* renamed from: c */
    final JsonSerializationContext f17253c;
    private final ThreadLocal<Map<TypeToken<?>, C6664a<?>>> calls;
    private final ConstructorConstructor constructorConstructor;
    private final List<TypeAdapterFactory> factories;
    private final boolean generateNonExecutableJson;
    private final boolean htmlSafe;
    private boolean inConstructorPhase;
    private Set<TypeAdapter<?>> preconfiguredGeneratedTypeAdapters;
    private final boolean prettyPrinting;
    private final ThreadLocal<Set<TypeAdapter<?>>> runtimeGeneratedTypeAdapters;
    private final boolean serializeNulls;
    private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache;

    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Gson(Excluder excluder, FieldNamingStrategy fieldNamingStrategy, Map<Type, InstanceCreator<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, LongSerializationPolicy longSerializationPolicy, List<TypeAdapterFactory> list) {
        this.calls = new ThreadLocal<>();
        this.typeTokenCache = Collections.synchronizedMap(new HashMap());
        this.inConstructorPhase = true;
        this.preconfiguredGeneratedTypeAdapters = new HashSet();
        this.runtimeGeneratedTypeAdapters = new ThreadLocal<>();
        this.f17252b = new JsonDeserializationContext() { // from class: com.networkbench.com.google.gson.Gson.1
            @Override // com.networkbench.com.google.gson.JsonDeserializationContext
            public <T> T deserialize(JsonElement jsonElement, Type type) throws JsonParseException {
                return (T) Gson.this.fromJson(jsonElement, type);
            }
        };
        this.f17253c = new JsonSerializationContext() { // from class: com.networkbench.com.google.gson.Gson.2
            @Override // com.networkbench.com.google.gson.JsonSerializationContext
            public JsonElement serialize(Object obj) {
                return Gson.this.toJsonTree(obj);
            }

            @Override // com.networkbench.com.google.gson.JsonSerializationContext
            public JsonElement serialize(Object obj, Type type) {
                return Gson.this.toJsonTree(obj, type);
            }
        };
        this.constructorConstructor = new ConstructorConstructor(map);
        this.serializeNulls = z;
        this.generateNonExecutableJson = z3;
        this.htmlSafe = z4;
        this.prettyPrinting = z5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(C6736m.f17437Q);
        arrayList.add(C6724h.f17397a);
        arrayList.add(excluder);
        arrayList.addAll(list);
        arrayList.add(C6736m.f17462x);
        arrayList.add(C6736m.f17451m);
        arrayList.add(C6736m.f17445g);
        arrayList.add(C6736m.f17447i);
        arrayList.add(C6736m.f17449k);
        arrayList.add(C6736m.m8583a(Long.TYPE, Long.class, longAdapter(longSerializationPolicy)));
        arrayList.add(C6736m.m8583a(Double.TYPE, Double.class, doubleAdapter(z6)));
        arrayList.add(C6736m.m8583a(Float.TYPE, Float.class, floatAdapter(z6)));
        arrayList.add(C6736m.f17456r);
        arrayList.add(C6736m.f17458t);
        arrayList.add(C6736m.f17464z);
        arrayList.add(C6736m.f17422B);
        arrayList.add(C6736m.m8584a(BigDecimal.class, C6736m.f17460v));
        arrayList.add(C6736m.m8584a(BigInteger.class, C6736m.f17461w));
        arrayList.add(C6736m.f17424D);
        arrayList.add(C6736m.f17426F);
        arrayList.add(C6736m.f17430J);
        arrayList.add(C6736m.f17435O);
        arrayList.add(C6736m.f17428H);
        arrayList.add(C6736m.f17442d);
        arrayList.add(C6716d.f17379a);
        arrayList.add(C6736m.f17433M);
        arrayList.add(C6733k.f17416a);
        arrayList.add(C6731j.f17414a);
        arrayList.add(C6736m.f17431K);
        arrayList.add(C6712b.f17373a);
        arrayList.add(C6736m.f17438R);
        arrayList.add(C6736m.f17440b);
        arrayList.add(new C6714c(this.constructorConstructor));
        arrayList.add(new C6722g(this.constructorConstructor, z2));
        arrayList.add(new C6711a(this.constructorConstructor));
        arrayList.add(new C6727i(this.constructorConstructor, fieldNamingStrategy, excluder));
        this.factories = Collections.unmodifiableList(arrayList);
        this.preconfiguredGeneratedTypeAdapters = Collections.unmodifiableSet(this.preconfiguredGeneratedTypeAdapters);
        this.inConstructorPhase = false;
    }

    private TypeAdapter<Number> doubleAdapter(boolean z) {
        if (z) {
            return C6736m.f17454p;
        }
        return new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.Gson.3
            @Override // com.networkbench.com.google.gson.TypeAdapter
            /* renamed from: a */
            public Double read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Double.valueOf(jsonReader.nextDouble());
            }

            @Override // com.networkbench.com.google.gson.TypeAdapter
            /* renamed from: a */
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                    return;
                }
                Gson.this.checkValidFloatingPoint(number.doubleValue());
                jsonWriter.value(number);
            }
        };
    }

    private TypeAdapter<Number> floatAdapter(boolean z) {
        if (z) {
            return C6736m.f17453o;
        }
        return new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.Gson.4
            @Override // com.networkbench.com.google.gson.TypeAdapter
            /* renamed from: a */
            public Float read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Float.valueOf((float) jsonReader.nextDouble());
            }

            @Override // com.networkbench.com.google.gson.TypeAdapter
            /* renamed from: a */
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                    return;
                }
                Gson.this.checkValidFloatingPoint(number.floatValue());
                jsonWriter.value(number);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkValidFloatingPoint(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return C6736m.f17452n;
        }
        return new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.Gson.5
            @Override // com.networkbench.com.google.gson.TypeAdapter
            /* renamed from: a */
            public Number read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Long.valueOf(jsonReader.nextLong());
            }

            @Override // com.networkbench.com.google.gson.TypeAdapter
            /* renamed from: a */
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(number.toString());
                }
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> TypeAdapter<T> getAdapter(TypeToken<T> typeToken) {
        TypeAdapter<T> typeAdapter = (TypeAdapter<T>) this.typeTokenCache.get(typeToken);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map<TypeToken<?>, C6664a<?>> map = this.calls.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.calls.set(map);
            z = true;
        }
        C6664a<?> c6664a = map.get(typeToken);
        if (c6664a != null) {
            return c6664a;
        }
        try {
            C6664a<?> c6664a2 = new C6664a<>();
            map.put(typeToken, c6664a2);
            for (TypeAdapterFactory typeAdapterFactory : this.factories) {
                TypeAdapter typeAdapter2 = (TypeAdapter<T>) typeAdapterFactory.create(this, typeToken);
                if (typeAdapter2 != null) {
                    c6664a2.m8667a(typeAdapter2);
                    this.typeTokenCache.put(typeToken, typeAdapter2);
                    return typeAdapter2;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + typeToken);
        } finally {
            map.remove(typeToken);
            if (z) {
                this.calls.remove();
            }
        }
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory typeAdapterFactory, TypeToken<T> typeToken) {
        boolean z = false;
        for (TypeAdapterFactory typeAdapterFactory2 : this.factories) {
            if (z) {
                TypeAdapter<T> create = typeAdapterFactory2.create(this, typeToken);
                if (create != null) {
                    return create;
                }
            } else if (typeAdapterFactory2 == typeAdapterFactory) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + typeToken);
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> cls) {
        return getAdapter(TypeToken.get((Class) cls));
    }

    public JsonElement toJsonTree(Object obj) {
        if (obj == null) {
            return JsonNull.INSTANCE;
        }
        return toJsonTree(obj, obj.getClass());
    }

    public JsonElement toJsonTree(Object obj, Type type) {
        C6720f c6720f = new C6720f();
        toJson(obj, type, c6720f);
        return c6720f.m8607a();
    }

    public String toJson(Object obj) {
        if (obj == null) {
            return toJson((JsonElement) JsonNull.INSTANCE);
        }
        return toJson(obj, obj.getClass());
    }

    public String toJson(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        toJson(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void toJson(Object obj, Appendable appendable) throws JsonIOException {
        if (obj != null) {
            toJson(obj, obj.getClass(), appendable);
        } else {
            toJson((JsonElement) JsonNull.INSTANCE, appendable);
        }
    }

    public void toJson(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            toJson(obj, type, newJsonWriter(Streams.writerForAppendable(appendable)));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public void toJson(Object obj, Type type, JsonWriter jsonWriter) throws JsonIOException {
        TypeAdapter adapter = getAdapter(TypeToken.get(type));
        boolean isLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.serializeNulls);
        try {
            try {
                adapter.write(jsonWriter, obj);
            } catch (IOException e) {
                throw new JsonIOException(e);
            }
        } finally {
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        }
    }

    public String toJson(JsonElement jsonElement) {
        StringWriter stringWriter = new StringWriter();
        toJson(jsonElement, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public void toJson(JsonElement jsonElement, Appendable appendable) throws JsonIOException {
        try {
            toJson(jsonElement, newJsonWriter(Streams.writerForAppendable(appendable)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonWriter newJsonWriter(Writer writer) throws IOException {
        if (this.generateNonExecutableJson) {
            writer.write(")]}'\n");
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.prettyPrinting) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }

    public void toJson(JsonElement jsonElement, JsonWriter jsonWriter) throws JsonIOException {
        boolean isLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.serializeNulls);
        try {
            try {
                Streams.write(jsonElement, jsonWriter);
            } catch (IOException e) {
                throw new JsonIOException(e);
            }
        } finally {
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        }
    }

    public <T> T fromJson(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) Primitives.wrap(cls).cast(fromJson(str, (Type) cls));
    }

    public <T> T fromJson(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) fromJson(new StringReader(str), type);
    }

    public <T> T fromJson(Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        JsonReader jsonReader = new JsonReader(reader);
        Object fromJson = fromJson(jsonReader, cls);
        assertFullConsumption(fromJson, jsonReader);
        return (T) Primitives.wrap(cls).cast(fromJson);
    }

    public <T> T fromJson(Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        JsonReader jsonReader = new JsonReader(reader);
        T t = (T) fromJson(jsonReader, type);
        assertFullConsumption(t, jsonReader);
        return t;
    }

    private static void assertFullConsumption(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() == JsonToken.END_DOCUMENT) {
                    return;
                }
                throw new JsonIOException("JSON document was not fully consumed.");
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException(e);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    public <T> T fromJson(JsonReader jsonReader, Type type) throws JsonIOException, JsonSyntaxException {
        boolean isLenient = jsonReader.isLenient();
        boolean z = true;
        jsonReader.setLenient(true);
        try {
            try {
                try {
                    jsonReader.peek();
                    z = false;
                    T read = getAdapter(TypeToken.get(type)).read(jsonReader);
                    jsonReader.setLenient(isLenient);
                    return read;
                } catch (EOFException e) {
                    if (!z) {
                        throw new JsonSyntaxException(e);
                    }
                    jsonReader.setLenient(isLenient);
                    return null;
                } catch (IllegalStateException e2) {
                    throw new JsonSyntaxException(e2);
                }
            } catch (IOException e3) {
                throw new JsonSyntaxException(e3);
            }
        } catch (Throwable th) {
            jsonReader.setLenient(isLenient);
            throw th;
        }
    }

    public <T> T fromJson(JsonElement jsonElement, Class<T> cls) throws JsonSyntaxException {
        return (T) Primitives.wrap(cls).cast(fromJson(jsonElement, (Type) cls));
    }

    public <T> T fromJson(JsonElement jsonElement, Type type) throws JsonSyntaxException {
        if (jsonElement == null) {
            return null;
        }
        return (T) fromJson(new C6718e(jsonElement), type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.Gson$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6664a<T> extends TypeAdapter<T> {

        /* renamed from: a */
        private TypeAdapter<T> f17259a;

        C6664a() {
        }

        /* renamed from: a */
        public void m8667a(TypeAdapter<T> typeAdapter) {
            if (this.f17259a != null) {
                throw new AssertionError();
            }
            this.f17259a = typeAdapter;
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        public T read(JsonReader jsonReader) throws IOException {
            TypeAdapter<T> typeAdapter = this.f17259a;
            if (typeAdapter == null) {
                throw new IllegalStateException();
            }
            return typeAdapter.read(jsonReader);
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            TypeAdapter<T> typeAdapter = this.f17259a;
            if (typeAdapter == null) {
                throw new IllegalStateException();
            }
            typeAdapter.write(jsonWriter, t);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + "factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }
}
