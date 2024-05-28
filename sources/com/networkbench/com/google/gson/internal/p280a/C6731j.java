package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.JsonSyntaxException;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.TypeAdapterFactory;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6731j extends TypeAdapter<Date> {

    /* renamed from: a */
    public static final TypeAdapterFactory f17414a = new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.j.1
        @Override // com.networkbench.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Date.class) {
                return new C6731j();
            }
            return null;
        }
    };

    /* renamed from: b */
    private final DateFormat f17415b = new SimpleDateFormat("MMM d, yyyy");

    @Override // com.networkbench.com.google.gson.TypeAdapter
    /* renamed from: a */
    public synchronized Date read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new Date(this.f17415b.parse(jsonReader.nextString()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    /* renamed from: a */
    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        jsonWriter.value(date == null ? null : this.f17415b.format((java.util.Date) date));
    }
}
