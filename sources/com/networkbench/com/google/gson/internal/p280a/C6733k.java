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
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6733k extends TypeAdapter<Time> {

    /* renamed from: a */
    public static final TypeAdapterFactory f17416a = new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.k.1
        @Override // com.networkbench.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Time.class) {
                return new C6733k();
            }
            return null;
        }
    };

    /* renamed from: b */
    private final DateFormat f17417b = new SimpleDateFormat("hh:mm:ss a");

    @Override // com.networkbench.com.google.gson.TypeAdapter
    /* renamed from: a */
    public synchronized Time read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new Time(this.f17417b.parse(jsonReader.nextString()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    /* renamed from: a */
    public synchronized void write(JsonWriter jsonWriter, Time time) throws IOException {
        jsonWriter.value(time == null ? null : this.f17417b.format((Date) time));
    }
}
