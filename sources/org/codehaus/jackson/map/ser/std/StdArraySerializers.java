package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.node.ObjectNode;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StdArraySerializers {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class ArraySerializerBase<T> extends ContainerSerializerBase<T> {
        protected final BeanProperty _property;
        protected final TypeSerializer _valueTypeSerializer;

        protected abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

        /* JADX INFO: Access modifiers changed from: protected */
        public ArraySerializerBase(Class<T> cls, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(cls);
            this._valueTypeSerializer = typeSerializer;
            this._property = beanProperty;
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeStartArray();
            serializeContents(t, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
            serializeContents(t, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class StringArraySerializer extends ArraySerializerBase<String[]> implements ResolvableSerializer {
        protected JsonSerializer<Object> _elementSerializer;

        @Override // org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public StringArraySerializer(BeanProperty beanProperty) {
            super(String[].class, null, beanProperty);
        }

        @Override // org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            int length = strArr.length;
            if (length == 0) {
                return;
            }
            JsonSerializer<Object> jsonSerializer = this._elementSerializer;
            if (jsonSerializer != null) {
                serializeContentsSlow(strArr, jsonGenerator, serializerProvider, jsonSerializer);
                return;
            }
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    jsonGenerator.writeNull();
                } else {
                    jsonGenerator.writeString(strArr[i]);
                }
            }
        }

        private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(strArr[i], jsonGenerator, serializerProvider);
                }
            }
        }

        @Override // org.codehaus.jackson.map.ResolvableSerializer
        public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
            JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(String.class, this._property);
            if (findValueSerializer == null || findValueSerializer.getClass().getAnnotation(JacksonStdImpl.class) != null) {
                return;
            }
            this._elementSerializer = findValueSerializer;
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("string"));
            return createSchemaNode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class BooleanArraySerializer extends ArraySerializerBase<boolean[]> {
        @Override // org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public BooleanArraySerializer() {
            super(boolean[].class, null, null);
        }

        @Override // org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (boolean z : zArr) {
                jsonGenerator.writeBoolean(z);
            }
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("boolean"));
            return createSchemaNode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ByteArraySerializer extends SerializerBase<byte[]> {
        public ByteArraySerializer() {
            super(byte[].class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeBinary(bArr);
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            typeSerializer.writeTypePrefixForScalar(bArr, jsonGenerator);
            jsonGenerator.writeBinary(bArr);
            typeSerializer.writeTypeSuffixForScalar(bArr, jsonGenerator);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("string"));
            return createSchemaNode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ShortArraySerializer extends ArraySerializerBase<short[]> {
        public ShortArraySerializer() {
            this(null);
        }

        public ShortArraySerializer(TypeSerializer typeSerializer) {
            super(short[].class, typeSerializer, null);
        }

        @Override // org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new ShortArraySerializer(typeSerializer);
        }

        @Override // org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (short s : sArr) {
                jsonGenerator.writeNumber((int) s);
            }
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("integer"));
            return createSchemaNode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CharArraySerializer extends SerializerBase<char[]> {
        public CharArraySerializer() {
            super(char[].class);
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                jsonGenerator.writeStartArray();
                _writeArrayContents(jsonGenerator, cArr);
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(cArr, 0, cArr.length);
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                typeSerializer.writeTypePrefixForArray(cArr, jsonGenerator);
                _writeArrayContents(jsonGenerator, cArr);
                typeSerializer.writeTypeSuffixForArray(cArr, jsonGenerator);
                return;
            }
            typeSerializer.writeTypePrefixForScalar(cArr, jsonGenerator);
            jsonGenerator.writeString(cArr, 0, cArr.length);
            typeSerializer.writeTypeSuffixForScalar(cArr, jsonGenerator);
        }

        private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) throws IOException, JsonGenerationException {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator.writeString(cArr, i, 1);
            }
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            ObjectNode createSchemaNode2 = createSchemaNode("string");
            createSchemaNode2.put("type", "string");
            createSchemaNode.put("items", createSchemaNode2);
            return createSchemaNode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class IntArraySerializer extends ArraySerializerBase<int[]> {
        @Override // org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public IntArraySerializer() {
            super(int[].class, null, null);
        }

        @Override // org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (int i : iArr) {
                jsonGenerator.writeNumber(i);
            }
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("integer"));
            return createSchemaNode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class LongArraySerializer extends ArraySerializerBase<long[]> {
        public LongArraySerializer() {
            this(null);
        }

        public LongArraySerializer(TypeSerializer typeSerializer) {
            super(long[].class, typeSerializer, null);
        }

        @Override // org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new LongArraySerializer(typeSerializer);
        }

        @Override // org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (long j : jArr) {
                jsonGenerator.writeNumber(j);
            }
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number", true));
            return createSchemaNode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class FloatArraySerializer extends ArraySerializerBase<float[]> {
        public FloatArraySerializer() {
            this(null);
        }

        public FloatArraySerializer(TypeSerializer typeSerializer) {
            super(float[].class, typeSerializer, null);
        }

        @Override // org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new FloatArraySerializer(typeSerializer);
        }

        @Override // org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (float f : fArr) {
                jsonGenerator.writeNumber(f);
            }
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number"));
            return createSchemaNode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DoubleArraySerializer extends ArraySerializerBase<double[]> {
        @Override // org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public DoubleArraySerializer() {
            super(double[].class, null, null);
        }

        @Override // org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (double d : dArr) {
                jsonGenerator.writeNumber(d);
            }
        }

        @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number"));
            return createSchemaNode;
        }
    }
}
