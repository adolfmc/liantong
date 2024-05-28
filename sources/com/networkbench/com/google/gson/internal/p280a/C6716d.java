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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6716d extends TypeAdapter<Date> {

    /* renamed from: a */
    public static final TypeAdapterFactory f17379a = new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.d.1
        @Override // com.networkbench.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Date.class) {
                return new C6716d();
            }
            return null;
        }
    };

    /* renamed from: b */
    private final DateFormat f17380b = DateFormat.getDateTimeInstance(2, 2, Locale.US);

    /* renamed from: c */
    private final DateFormat f17381c = DateFormat.getDateTimeInstance(2, 2);

    /* renamed from: d */
    private final DateFormat f17382d = m8615a();

    /* renamed from: a */
    private static DateFormat m8615a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    /* renamed from: a */
    public Date read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return m8612a(jsonReader.nextString());
    }

    /* renamed from: a */
    private synchronized Date m8612a(String str) {
        try {
            try {
                try {
                } catch (ParseException unused) {
                    return this.f17380b.parse(str);
                }
            } catch (ParseException e) {
                throw new JsonSyntaxException(str, e);
            }
        } catch (ParseException unused2) {
            return this.f17382d.parse(str);
        }
        return this.f17381c.parse(str);
    }

    @Override // com.networkbench.com.google.gson.TypeAdapter
    /* renamed from: a */
    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(this.f17380b.format(date));
        }
    }
}
