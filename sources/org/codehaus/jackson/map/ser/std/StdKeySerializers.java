package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StdKeySerializers {
    protected static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
    protected static final JsonSerializer<Object> DEFAULT_STRING_SERIALIZER = new StringKeySerializer();

    private StdKeySerializers() {
    }

    public static JsonSerializer<Object> getStdKeySerializer(JavaType javaType) {
        if (javaType == null) {
            return DEFAULT_KEY_SERIALIZER;
        }
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return DEFAULT_STRING_SERIALIZER;
        }
        if (rawClass == Object.class) {
            return DEFAULT_KEY_SERIALIZER;
        }
        if (Date.class.isAssignableFrom(rawClass)) {
            return DateKeySerializer.instance;
        }
        if (Calendar.class.isAssignableFrom(rawClass)) {
            return CalendarKeySerializer.instance;
        }
        return DEFAULT_KEY_SERIALIZER;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class StringKeySerializer extends SerializerBase<String> {
        public StringKeySerializer() {
            super(String.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeFieldName(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class DateKeySerializer extends SerializerBase<Date> {
        protected static final JsonSerializer<?> instance = new DateKeySerializer();

        public DateKeySerializer() {
            super(Date.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            serializerProvider.defaultSerializeDateKey(date, jsonGenerator);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class CalendarKeySerializer extends SerializerBase<Calendar> {
        protected static final JsonSerializer<?> instance = new CalendarKeySerializer();

        public CalendarKeySerializer() {
            super(Calendar.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            serializerProvider.defaultSerializeDateKey(calendar.getTimeInMillis(), jsonGenerator);
        }
    }
}
