package com.networkbench.com.google.gson;

import com.networkbench.com.google.gson.internal.p280a.C6718e;
import com.networkbench.com.google.gson.internal.p280a.C6720f;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class TypeAdapter<T> {
    public abstract T read(JsonReader jsonReader) throws IOException;

    public abstract void write(JsonWriter jsonWriter, T t) throws IOException;

    public final void toJson(Writer writer, T t) throws IOException {
        write(new JsonWriter(writer), t);
    }

    public final TypeAdapter<T> nullSafe() {
        return new TypeAdapter<T>() { // from class: com.networkbench.com.google.gson.TypeAdapter.1
            @Override // com.networkbench.com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, T t) throws IOException {
                if (t == null) {
                    jsonWriter.nullValue();
                } else {
                    TypeAdapter.this.write(jsonWriter, t);
                }
            }

            @Override // com.networkbench.com.google.gson.TypeAdapter
            public T read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return (T) TypeAdapter.this.read(jsonReader);
            }
        };
    }

    public final String toJson(T t) throws IOException {
        StringWriter stringWriter = new StringWriter();
        toJson(stringWriter, t);
        return stringWriter.toString();
    }

    public final JsonElement toJsonTree(T t) {
        try {
            C6720f c6720f = new C6720f();
            write(c6720f, t);
            return c6720f.m8607a();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public final T fromJson(Reader reader) throws IOException {
        return read(new JsonReader(reader));
    }

    public final T fromJson(String str) throws IOException {
        return fromJson(new StringReader(str));
    }

    public final T fromJsonTree(JsonElement jsonElement) {
        try {
            return read(new C6718e(jsonElement));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }
}
