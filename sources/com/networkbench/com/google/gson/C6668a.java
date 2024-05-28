package com.networkbench.com.google.gson;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6668a implements JsonDeserializer<Date>, JsonSerializer<Date> {

    /* renamed from: a */
    private final DateFormat f17262a;

    /* renamed from: b */
    private final DateFormat f17263b;

    /* renamed from: c */
    private final DateFormat f17264c;

    C6668a() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C6668a(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    C6668a(int i) {
        this(DateFormat.getDateInstance(i, Locale.US), DateFormat.getDateInstance(i));
    }

    public C6668a(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    C6668a(DateFormat dateFormat, DateFormat dateFormat2) {
        this.f17262a = dateFormat;
        this.f17263b = dateFormat2;
        this.f17264c = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        this.f17264c.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override // com.networkbench.com.google.gson.JsonSerializer
    /* renamed from: a */
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonPrimitive jsonPrimitive;
        synchronized (this.f17263b) {
            jsonPrimitive = new JsonPrimitive(this.f17262a.format(date));
        }
        return jsonPrimitive;
    }

    @Override // com.networkbench.com.google.gson.JsonDeserializer
    /* renamed from: a */
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (!(jsonElement instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }
        Date m8659a = m8659a(jsonElement);
        if (type == Date.class) {
            return m8659a;
        }
        if (type == Timestamp.class) {
            return new Timestamp(m8659a.getTime());
        }
        if (type == java.sql.Date.class) {
            return new java.sql.Date(m8659a.getTime());
        }
        throw new IllegalArgumentException(getClass() + " cannot deserialize to " + type);
    }

    /* renamed from: a */
    private Date m8659a(JsonElement jsonElement) {
        Date parse;
        synchronized (this.f17263b) {
            try {
                try {
                    try {
                        parse = this.f17263b.parse(jsonElement.getAsString());
                    } catch (ParseException unused) {
                        return this.f17264c.parse(jsonElement.getAsString());
                    }
                } catch (ParseException e) {
                    throw new JsonSyntaxException(jsonElement.getAsString(), e);
                }
            } catch (ParseException unused2) {
                return this.f17262a.parse(jsonElement.getAsString());
            }
        }
        return parse;
    }

    public String toString() {
        return C6668a.class.getSimpleName() + '(' + this.f17263b.getClass().getSimpleName() + ')';
    }
}
