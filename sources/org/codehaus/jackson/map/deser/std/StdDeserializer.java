package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.p467io.NumberInput;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class StdDeserializer<T> extends JsonDeserializer<T> {
    protected final Class<?> _valueClass;

    public JavaType getValueType() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? null : javaType.getRawClass();
    }

    public Class<?> getValueClass() {
        return this._valueClass;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultSerializer(JsonDeserializer<?> jsonDeserializer) {
        return (jsonDeserializer == null || jsonDeserializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE || currentToken == JsonToken.VALUE_NULL) {
            return false;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (jsonParser.getNumberType() == JsonParser.NumberType.INT) {
                return jsonParser.getIntValue() != 0;
            }
            return _parseBooleanFromNumber(jsonParser, deserializationContext);
        } else if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || trim.length() == 0) {
                return Boolean.FALSE.booleanValue();
            }
            throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (jsonParser.getNumberType() == JsonParser.NumberType.INT) {
                return jsonParser.getIntValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
            }
            return Boolean.valueOf(_parseBooleanFromNumber(jsonParser, deserializationContext));
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Boolean) getNullValue();
        } else {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if ("true".equals(trim)) {
                    return Boolean.TRUE;
                }
                if ("false".equals(trim)) {
                    return Boolean.FALSE;
                }
                if (trim.length() == 0) {
                    return (Boolean) getEmptyValue();
                }
                throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final boolean _parseBooleanFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getNumberType() == JsonParser.NumberType.LONG) {
            return (jsonParser.getLongValue() == 0 ? Boolean.FALSE : Boolean.TRUE).booleanValue();
        }
        String text = jsonParser.getText();
        if ("0.0".equals(text) || "0".equals(text)) {
            return Boolean.FALSE.booleanValue();
        }
        return Boolean.TRUE.booleanValue();
    }

    protected Byte _parseByte(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Byte.valueOf(jsonParser.getByteValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                if (trim.length() == 0) {
                    return (Byte) getEmptyValue();
                }
                int parseInt = NumberInput.parseInt(trim);
                if (parseInt < -128 || parseInt > 255) {
                    throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 8-bit value");
                }
                return Byte.valueOf((byte) parseInt);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Byte value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Byte) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Short.valueOf(jsonParser.getShortValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                if (trim.length() == 0) {
                    return (Short) getEmptyValue();
                }
                int parseInt = NumberInput.parseInt(trim);
                if (parseInt < -32768 || parseInt > 32767) {
                    throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
                }
                return Short.valueOf((short) parseInt);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Short value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Short) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (_parseIntPrimitive < -32768 || _parseIntPrimitive > 32767) {
            throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
        }
        return (short) _parseIntPrimitive;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getIntValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                int length = trim.length();
                if (length <= 9) {
                    if (length == 0) {
                        return 0;
                    }
                    return NumberInput.parseInt(trim);
                }
                long parseLong = Long.parseLong(trim);
                if (parseLong < -2147483648L || parseLong > 2147483647L) {
                    Class<?> cls = this._valueClass;
                    throw deserializationContext.weirdStringException(cls, "Overflow: numeric value (" + trim + ") out of range of int (-2147483648 - 2147483647)");
                }
                return (int) parseLong;
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid int value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0;
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(jsonParser.getIntValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                int length = trim.length();
                if (length <= 9) {
                    if (length == 0) {
                        return (Integer) getEmptyValue();
                    }
                    return Integer.valueOf(NumberInput.parseInt(trim));
                }
                long parseLong = Long.parseLong(trim);
                if (parseLong < -2147483648L || parseLong > 2147483647L) {
                    Class<?> cls = this._valueClass;
                    throw deserializationContext.weirdStringException(cls, "Overflow: numeric value (" + trim + ") out of range of Integer (-2147483648 - 2147483647)");
                }
                return Integer.valueOf((int) parseLong);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Integer value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Integer) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Long) getEmptyValue();
            }
            try {
                return Long.valueOf(NumberInput.parseLong(trim));
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Long value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Long) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getLongValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0L;
            }
            try {
                return NumberInput.parseLong(trim);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid long value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0L;
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Float.valueOf(jsonParser.getFloatValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Float) getEmptyValue();
            }
            char charAt = trim.charAt(0);
            if (charAt != '-') {
                if (charAt == 'I') {
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Float.valueOf(Float.POSITIVE_INFINITY);
                    }
                } else if (charAt == 'N' && "NaN".equals(trim)) {
                    return Float.valueOf(Float.NaN);
                }
            } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                return Float.valueOf(Float.NEGATIVE_INFINITY);
            }
            try {
                return Float.valueOf(Float.parseFloat(trim));
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Float value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Float) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getFloatValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0.0f;
            }
            char charAt = trim.charAt(0);
            if (charAt != '-') {
                if (charAt == 'I') {
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Float.POSITIVE_INFINITY;
                    }
                } else if (charAt == 'N' && "NaN".equals(trim)) {
                    return Float.NaN;
                }
            } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                return Float.NEGATIVE_INFINITY;
            }
            try {
                return Float.parseFloat(trim);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid float value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0.0f;
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Double.valueOf(jsonParser.getDoubleValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Double) getEmptyValue();
            }
            char charAt = trim.charAt(0);
            if (charAt != '-') {
                if (charAt == 'I') {
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Double.valueOf(Double.POSITIVE_INFINITY);
                    }
                } else if (charAt == 'N' && "NaN".equals(trim)) {
                    return Double.valueOf(Double.NaN);
                }
            } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            try {
                return Double.valueOf(parseDouble(trim));
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Double value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Double) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getDoubleValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0.0d;
            }
            char charAt = trim.charAt(0);
            if (charAt != '-') {
                if (charAt == 'I') {
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Double.POSITIVE_INFINITY;
                    }
                } else if (charAt == 'N' && "NaN".equals(trim)) {
                    return Double.NaN;
                }
            } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                return Double.NEGATIVE_INFINITY;
            }
            try {
                return parseDouble(trim);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid double value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0.0d;
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return new Date(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            return (Date) getNullValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            try {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (Date) getEmptyValue();
                }
                return deserializationContext.parseDate(trim);
            } catch (IllegalArgumentException e) {
                Class<?> cls = this._valueClass;
                throw deserializationContext.weirdStringException(cls, "not a valid representation (error: " + e.getMessage() + ")");
            }
        }
        throw deserializationContext.mappingException(this._valueClass, currentToken);
    }

    protected static final double parseDouble(String str) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return deserializerProvider.findValueDeserializer(deserializationConfig, javaType, beanProperty);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        if (obj == null) {
            obj = getValueClass();
        }
        if (deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            return;
        }
        reportUnknownProperty(deserializationContext, obj, str);
        jsonParser.skipChildren();
    }

    protected void reportUnknownProperty(DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw deserializationContext.unknownFieldException(obj, str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        final T _nullValue;

        protected PrimitiveOrWrapperDeserializer(Class<T> cls, T t) {
            super((Class<?>) cls);
            this._nullValue = t;
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public final T getNullValue() {
            return this._nullValue;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, bool);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseBoolean(jsonParser, deserializationContext);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdScalarDeserializer, org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        public ByteDeserializer(Class<Byte> cls, Byte b) {
            super(cls, b);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseByte(jsonParser, deserializationContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, sh);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        public CharacterDeserializer(Class<Character> cls, Character ch) {
            super(cls, ch);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                int intValue = jsonParser.getIntValue();
                if (intValue >= 0 && intValue <= 65535) {
                    return Character.valueOf((char) intValue);
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String text = jsonParser.getText();
                if (text.length() == 1) {
                    return Character.valueOf(text.charAt(0));
                }
                if (text.length() == 0) {
                    return getEmptyValue();
                }
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, num);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseInteger(jsonParser, deserializationContext);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdScalarDeserializer, org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        public LongDeserializer(Class<Long> cls, Long l) {
            super(cls, l);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        public FloatDeserializer(Class<Float> cls, Float f) {
            super(cls, f);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        public DoubleDeserializer(Class<Double> cls, Double d) {
            super(cls, d);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseDouble(jsonParser, deserializationContext);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdScalarDeserializer, org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class NumberDeserializer extends StdScalarDeserializer<Number> {
        public NumberDeserializer() {
            super(Number.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Number deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser.getBigIntegerValue();
                }
                return jsonParser.getNumberValue();
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                try {
                    if (trim.indexOf(46) >= 0) {
                        if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            return new BigDecimal(trim);
                        }
                        return new Double(trim);
                    } else if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                        return new BigInteger(trim);
                    } else {
                        long parseLong = Long.parseLong(trim);
                        if (parseLong <= 2147483647L && parseLong >= -2147483648L) {
                            return Integer.valueOf((int) parseLong);
                        }
                        return Long.valueOf(parseLong);
                    }
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid number");
                }
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }

        @Override // org.codehaus.jackson.map.deser.std.StdScalarDeserializer, org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                case VALUE_STRING:
                    return deserialize(jsonParser, deserializationContext);
                default:
                    return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public BigDecimalDeserializer() {
            super(BigDecimal.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue();
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return null;
                }
                try {
                    return new BigDecimal(trim);
                } catch (IllegalArgumentException unused) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
                }
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JacksonStdImpl
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public BigIntegerDeserializer() {
            super(BigInteger.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                switch (jsonParser.getNumberType()) {
                    case INT:
                    case LONG:
                        return BigInteger.valueOf(jsonParser.getLongValue());
                }
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue().toBigInteger();
            } else {
                if (currentToken != JsonToken.VALUE_STRING) {
                    throw deserializationContext.mappingException(this._valueClass, currentToken);
                }
            }
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                return new BigInteger(trim);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class SqlDateDeserializer extends StdScalarDeserializer<java.sql.Date> {
        public SqlDateDeserializer() {
            super(java.sql.Date.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public java.sql.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            return new java.sql.Date(_parseDate.getTime());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
        public StackTraceElementDeserializer() {
            super(StackTraceElement.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.START_OBJECT) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
            String str = "";
            String str2 = "";
            String str3 = "";
            int i = -1;
            while (true) {
                JsonToken nextValue = jsonParser.nextValue();
                if (nextValue != JsonToken.END_OBJECT) {
                    String currentName = jsonParser.getCurrentName();
                    if ("className".equals(currentName)) {
                        str = jsonParser.getText();
                    } else if ("fileName".equals(currentName)) {
                        str3 = jsonParser.getText();
                    } else if ("lineNumber".equals(currentName)) {
                        if (nextValue.isNumeric()) {
                            i = jsonParser.getIntValue();
                        } else {
                            throw JsonMappingException.from(jsonParser, "Non-numeric token (" + nextValue + ") for property 'lineNumber'");
                        }
                    } else if ("methodName".equals(currentName)) {
                        str2 = jsonParser.getText();
                    } else if (!"nativeMethod".equals(currentName)) {
                        handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                    }
                } else {
                    return new StackTraceElement(str, str2, str3, i);
                }
            }
        }
    }
}
