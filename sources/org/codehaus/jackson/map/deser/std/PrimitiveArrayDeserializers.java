package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.HashMap;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ObjectBuffer;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PrimitiveArrayDeserializers {
    static final PrimitiveArrayDeserializers instance = new PrimitiveArrayDeserializers();
    HashMap<JavaType, JsonDeserializer<Object>> _allDeserializers = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: protected */
    public PrimitiveArrayDeserializers() {
        add(Boolean.TYPE, new BooleanDeser());
        add(Byte.TYPE, new ByteDeser());
        add(Short.TYPE, new ShortDeser());
        add(Integer.TYPE, new IntDeser());
        add(Long.TYPE, new LongDeser());
        add(Float.TYPE, new FloatDeser());
        add(Double.TYPE, new DoubleDeser());
        add(String.class, new StringDeser());
        add(Character.TYPE, new CharDeser());
    }

    public static HashMap<JavaType, JsonDeserializer<Object>> getAll() {
        return instance._allDeserializers;
    }

    private void add(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        this._allDeserializers.put(TypeFactory.defaultInstance().constructType(cls), jsonDeserializer);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static abstract class Base<T> extends StdDeserializer<T> {
        protected Base(Class<T> cls) {
            super((Class<?>) cls);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class StringDeser extends Base<String[]> {
        public StringDeser() {
            super(String[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            int i = 0;
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken != JsonToken.END_ARRAY) {
                    String text = nextToken == JsonToken.VALUE_NULL ? null : jsonParser.getText();
                    if (i >= resetAndStart.length) {
                        resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                        i = 0;
                    }
                    resetAndStart[i] = text;
                    i++;
                } else {
                    String[] strArr = (String[]) leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, String.class);
                    deserializationContext.returnObjectBuffer(leaseObjectBuffer);
                    return strArr;
                }
            }
        }

        private final String[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                    return null;
                }
                throw deserializationContext.mappingException(this._valueClass);
            }
            String[] strArr = new String[1];
            strArr[0] = jsonParser.getCurrentToken() != JsonToken.VALUE_NULL ? jsonParser.getText() : null;
            return strArr;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class CharDeser extends Base<char[]> {
        public CharDeser() {
            super(char[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public char[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                char[] textCharacters = jsonParser.getTextCharacters();
                int textOffset = jsonParser.getTextOffset();
                int textLength = jsonParser.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            } else if (jsonParser.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        if (nextToken != JsonToken.VALUE_STRING) {
                            throw deserializationContext.mappingException(Character.TYPE);
                        }
                        String text = jsonParser.getText();
                        if (text.length() != 1) {
                            throw JsonMappingException.from(jsonParser, "Can not convert a JSON String of length " + text.length() + " into a char element of char array");
                        }
                        sb.append(text.charAt(0));
                    } else {
                        return sb.toString().toCharArray();
                    }
                }
            } else {
                if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object embeddedObject = jsonParser.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                throw deserializationContext.mappingException(this._valueClass);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class BooleanDeser extends Base<boolean[]> {
        public BooleanDeser() {
            super(boolean[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
            boolean[] resetAndStart = booleanBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = booleanBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseBooleanPrimitive;
                i++;
            }
            return booleanBuilder.completeAndClearBuffer(resetAndStart, i);
        }

        private final boolean[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class ByteDeser extends Base<byte[]> {
        public ByteDeser() {
            super(byte[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public byte[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            byte byteValue;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
            }
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (embeddedObject instanceof byte[]) {
                    return (byte[]) embeddedObject;
                }
            }
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ByteBuilder byteBuilder = deserializationContext.getArrayBuilders().getByteBuilder();
            byte[] resetAndStart = byteBuilder.resetAndStart();
            int i = 0;
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken != JsonToken.END_ARRAY) {
                    if (nextToken == JsonToken.VALUE_NUMBER_INT || nextToken == JsonToken.VALUE_NUMBER_FLOAT) {
                        byteValue = jsonParser.getByteValue();
                    } else if (nextToken != JsonToken.VALUE_NULL) {
                        throw deserializationContext.mappingException(this._valueClass.getComponentType());
                    } else {
                        byteValue = 0;
                    }
                    if (i >= resetAndStart.length) {
                        resetAndStart = byteBuilder.appendCompletedChunk(resetAndStart, i);
                        i = 0;
                    }
                    resetAndStart[i] = byteValue;
                    i++;
                } else {
                    return byteBuilder.completeAndClearBuffer(resetAndStart, i);
                }
            }
        }

        private final byte[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            byte byteValue;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                byteValue = jsonParser.getByteValue();
            } else if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass.getComponentType());
            } else {
                byteValue = 0;
            }
            return new byte[]{byteValue};
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class ShortDeser extends Base<short[]> {
        public ShortDeser() {
            super(short[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
            short[] resetAndStart = shortBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = shortBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseShortPrimitive;
                i++;
            }
            return shortBuilder.completeAndClearBuffer(resetAndStart, i);
        }

        private final short[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class IntDeser extends Base<int[]> {
        public IntDeser() {
            super(int[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
            int[] resetAndStart = intBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = intBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseIntPrimitive;
                i++;
            }
            return intBuilder.completeAndClearBuffer(resetAndStart, i);
        }

        private final int[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class LongDeser extends Base<long[]> {
        public LongDeser() {
            super(long[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
            long[] resetAndStart = longBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = longBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseLongPrimitive;
                i++;
            }
            return longBuilder.completeAndClearBuffer(resetAndStart, i);
        }

        private final long[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class FloatDeser extends Base<float[]> {
        public FloatDeser() {
            super(float[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
            float[] resetAndStart = floatBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = floatBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseFloatPrimitive;
                i++;
            }
            return floatBuilder.completeAndClearBuffer(resetAndStart, i);
        }

        private final float[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class DoubleDeser extends Base<double[]> {
        public DoubleDeser() {
            super(double[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
            double[] resetAndStart = doubleBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = doubleBuilder.appendCompletedChunk(resetAndStart, i);
                    i = 0;
                }
                resetAndStart[i] = _parseDoublePrimitive;
                i++;
            }
            return doubleBuilder.completeAndClearBuffer(resetAndStart, i);
        }

        private final double[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }
}
