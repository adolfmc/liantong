package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonNull;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6718e extends JsonReader {

    /* renamed from: a */
    private static final Reader f17383a = new Reader() { // from class: com.networkbench.com.google.gson.internal.a.e.1
        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }
    };

    /* renamed from: b */
    private static final Object f17384b = new Object();

    /* renamed from: c */
    private final List<Object> f17385c;

    public C6718e(JsonElement jsonElement) {
        super(f17383a);
        this.f17385c = new ArrayList();
        this.f17385c.add(jsonElement);
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public void beginArray() throws IOException {
        m8610a(JsonToken.BEGIN_ARRAY);
        this.f17385c.add(((JsonArray) m8609b()).iterator());
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public void endArray() throws IOException {
        m8610a(JsonToken.END_ARRAY);
        m8608c();
        m8608c();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public void beginObject() throws IOException {
        m8610a(JsonToken.BEGIN_OBJECT);
        this.f17385c.add(((JsonObject) m8609b()).entrySet().iterator());
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public void endObject() throws IOException {
        m8610a(JsonToken.END_OBJECT);
        m8608c();
        m8608c();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public boolean hasNext() throws IOException {
        JsonToken peek = peek();
        return (peek == JsonToken.END_OBJECT || peek == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public JsonToken peek() throws IOException {
        if (this.f17385c.isEmpty()) {
            return JsonToken.END_DOCUMENT;
        }
        Object m8609b = m8609b();
        if (m8609b instanceof Iterator) {
            List<Object> list = this.f17385c;
            boolean z = list.get(list.size() - 2) instanceof JsonObject;
            Iterator it = (Iterator) m8609b;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            } else if (z) {
                return JsonToken.NAME;
            } else {
                this.f17385c.add(it.next());
                return peek();
            }
        } else if (m8609b instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        } else {
            if (m8609b instanceof JsonArray) {
                return JsonToken.BEGIN_ARRAY;
            }
            if (m8609b instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) m8609b;
                if (jsonPrimitive.isString()) {
                    return JsonToken.STRING;
                }
                if (jsonPrimitive.isBoolean()) {
                    return JsonToken.BOOLEAN;
                }
                if (jsonPrimitive.isNumber()) {
                    return JsonToken.NUMBER;
                }
                throw new AssertionError();
            } else if (m8609b instanceof JsonNull) {
                return JsonToken.NULL;
            } else {
                if (m8609b == f17384b) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    /* renamed from: b */
    private Object m8609b() {
        List<Object> list = this.f17385c;
        return list.get(list.size() - 1);
    }

    /* renamed from: c */
    private Object m8608c() {
        List<Object> list = this.f17385c;
        return list.remove(list.size() - 1);
    }

    /* renamed from: a */
    private void m8610a(JsonToken jsonToken) throws IOException {
        if (peek() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + peek());
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public String nextName() throws IOException {
        m8610a(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) m8609b()).next();
        this.f17385c.add(entry.getValue());
        return (String) entry.getKey();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public String nextString() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.STRING && peek != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + peek);
        }
        return ((JsonPrimitive) m8608c()).getAsString();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public boolean nextBoolean() throws IOException {
        m8610a(JsonToken.BOOLEAN);
        return ((JsonPrimitive) m8608c()).getAsBoolean();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public void nextNull() throws IOException {
        m8610a(JsonToken.NULL);
        m8608c();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public double nextDouble() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER && peek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
        }
        double asDouble = ((JsonPrimitive) m8609b()).getAsDouble();
        if (!isLenient() && (Double.isNaN(asDouble) || Double.isInfinite(asDouble))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + asDouble);
        }
        m8608c();
        return asDouble;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public long nextLong() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER && peek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
        }
        long asLong = ((JsonPrimitive) m8609b()).getAsLong();
        m8608c();
        return asLong;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public int nextInt() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER && peek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
        }
        int asInt = ((JsonPrimitive) m8609b()).getAsInt();
        m8608c();
        return asInt;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f17385c.clear();
        this.f17385c.add(f17384b);
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public void skipValue() throws IOException {
        if (peek() == JsonToken.NAME) {
            nextName();
        } else {
            m8608c();
        }
    }

    @Override // com.networkbench.com.google.gson.stream.JsonReader
    public String toString() {
        return getClass().getSimpleName();
    }

    /* renamed from: a */
    public void m8611a() throws IOException {
        m8610a(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) m8609b()).next();
        this.f17385c.add(entry.getValue());
        this.f17385c.add(new JsonPrimitive((String) entry.getKey()));
    }
}
