package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonNull;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6720f extends JsonWriter {

    /* renamed from: a */
    private static final Writer f17386a = new Writer() { // from class: com.networkbench.com.google.gson.internal.a.f.1
        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }
    };

    /* renamed from: b */
    private static final JsonPrimitive f17387b = new JsonPrimitive("closed");

    /* renamed from: c */
    private final List<JsonElement> f17388c;

    /* renamed from: d */
    private String f17389d;

    /* renamed from: e */
    private JsonElement f17390e;

    @Override // com.networkbench.com.google.gson.stream.JsonWriter, java.io.Flushable
    public void flush() throws IOException {
    }

    public C6720f() {
        super(f17386a);
        this.f17388c = new ArrayList();
        this.f17390e = JsonNull.INSTANCE;
    }

    /* renamed from: a */
    public JsonElement m8607a() {
        if (!this.f17388c.isEmpty()) {
            throw new IllegalStateException("Expected one JSON element but was " + this.f17388c);
        }
        return this.f17390e;
    }

    /* renamed from: b */
    private JsonElement m8605b() {
        List<JsonElement> list = this.f17388c;
        return list.get(list.size() - 1);
    }

    /* renamed from: a */
    private void m8606a(JsonElement jsonElement) {
        if (this.f17389d != null) {
            if (!jsonElement.isJsonNull() || getSerializeNulls()) {
                ((JsonObject) m8605b()).add(this.f17389d, jsonElement);
            }
            this.f17389d = null;
        } else if (this.f17388c.isEmpty()) {
            this.f17390e = jsonElement;
        } else {
            JsonElement m8605b = m8605b();
            if (m8605b instanceof JsonArray) {
                ((JsonArray) m8605b).add(jsonElement);
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter beginArray() throws IOException {
        JsonArray jsonArray = new JsonArray();
        m8606a(jsonArray);
        this.f17388c.add(jsonArray);
        return this;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter endArray() throws IOException {
        if (this.f17388c.isEmpty() || this.f17389d != null) {
            throw new IllegalStateException();
        }
        if (m8605b() instanceof JsonArray) {
            List<JsonElement> list = this.f17388c;
            list.remove(list.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter beginObject() throws IOException {
        JsonObject jsonObject = new JsonObject();
        m8606a(jsonObject);
        this.f17388c.add(jsonObject);
        return this;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter endObject() throws IOException {
        if (this.f17388c.isEmpty() || this.f17389d != null) {
            throw new IllegalStateException();
        }
        if (m8605b() instanceof JsonObject) {
            List<JsonElement> list = this.f17388c;
            list.remove(list.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter name(String str) throws IOException {
        if (this.f17388c.isEmpty() || this.f17389d != null) {
            throw new IllegalStateException();
        }
        if (m8605b() instanceof JsonObject) {
            this.f17389d = str;
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        m8606a(new JsonPrimitive(str));
        return this;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter nullValue() throws IOException {
        m8606a(JsonNull.INSTANCE);
        return this;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter value(boolean z) throws IOException {
        m8606a(new JsonPrimitive(Boolean.valueOf(z)));
        return this;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter value(double d) throws IOException {
        if (!isLenient() && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new IllegalArgumentException("JSON forbids NaN and infinities: " + d);
        }
        m8606a(new JsonPrimitive((Number) Double.valueOf(d)));
        return this;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter value(long j) throws IOException {
        m8606a(new JsonPrimitive((Number) Long.valueOf(j)));
        return this;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter
    public JsonWriter value(Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        if (!isLenient()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        m8606a(new JsonPrimitive(number));
        return this;
    }

    @Override // com.networkbench.com.google.gson.stream.JsonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.f17388c.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.f17388c.add(f17387b);
    }
}
