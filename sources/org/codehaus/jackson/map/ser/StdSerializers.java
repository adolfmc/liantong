package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.ser.std.DateSerializer;
import org.codehaus.jackson.map.ser.std.NonTypedScalarSerializerBase;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StdSerializers {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    @Deprecated
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CalendarSerializer extends org.codehaus.jackson.map.ser.std.CalendarSerializer {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    @Deprecated
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SerializableSerializer extends org.codehaus.jackson.map.ser.std.SerializableSerializer {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    @Deprecated
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SerializableWithTypeSerializer extends org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    @Deprecated
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class UtilDateSerializer extends DateSerializer {
    }

    protected StdSerializers() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class BooleanSerializer extends NonTypedScalarSerializerBase<Boolean> {
        final boolean _forPrimitive;

        public BooleanSerializer(boolean z) {
            super(Boolean.class);
            this._forPrimitive = z;
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Boolean bool, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeBoolean(bool.booleanValue());
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("boolean", !this._forPrimitive);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    @Deprecated
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class StringSerializer extends NonTypedScalarSerializerBase<String> {
        public StringSerializer() {
            super(String.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString(str);
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class IntegerSerializer extends NonTypedScalarSerializerBase<Integer> {
        public IntegerSerializer() {
            super(Integer.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Integer num, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(num.intValue());
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class IntLikeSerializer extends org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Number> {
        static final IntLikeSerializer instance = new IntLikeSerializer();

        public IntLikeSerializer() {
            super(Number.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(number.intValue());
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class LongSerializer extends org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Long> {
        static final LongSerializer instance = new LongSerializer();

        public LongSerializer() {
            super(Long.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Long l, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(l.longValue());
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class FloatSerializer extends org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Float> {
        static final FloatSerializer instance = new FloatSerializer();

        public FloatSerializer() {
            super(Float.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Float f, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(f.floatValue());
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DoubleSerializer extends NonTypedScalarSerializerBase<Double> {
        static final DoubleSerializer instance = new DoubleSerializer();

        public DoubleSerializer() {
            super(Double.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeNumber(d.doubleValue());
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class NumberSerializer extends org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Number> {
        public static final NumberSerializer instance = new NumberSerializer();

        public NumberSerializer() {
            super(Number.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (number instanceof BigDecimal) {
                jsonGenerator.writeNumber((BigDecimal) number);
            } else if (number instanceof BigInteger) {
                jsonGenerator.writeNumber((BigInteger) number);
            } else if (number instanceof Integer) {
                jsonGenerator.writeNumber(number.intValue());
            } else if (number instanceof Long) {
                jsonGenerator.writeNumber(number.longValue());
            } else if (number instanceof Double) {
                jsonGenerator.writeNumber(number.doubleValue());
            } else if (number instanceof Float) {
                jsonGenerator.writeNumber(number.floatValue());
            } else if ((number instanceof Byte) || (number instanceof Short)) {
                jsonGenerator.writeNumber(number.intValue());
            } else {
                jsonGenerator.writeNumber(number.toString());
            }
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SqlDateSerializer extends org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Date> {
        public SqlDateSerializer() {
            super(Date.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString(date.toString());
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SqlTimeSerializer extends org.codehaus.jackson.map.ser.std.ScalarSerializerBase<Time> {
        public SqlTimeSerializer() {
            super(Time.class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Time time, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString(time.toString());
        }

        @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }
    }
}
