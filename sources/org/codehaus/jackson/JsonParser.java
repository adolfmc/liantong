package org.codehaus.jackson;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import org.codehaus.jackson.type.TypeReference;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class JsonParser implements Closeable, Versioned {
    private static final int MAX_BYTE_I = 255;
    private static final int MAX_SHORT_I = 32767;
    private static final int MIN_BYTE_I = -128;
    private static final int MIN_SHORT_I = -32768;
    protected JsonToken _currToken;
    protected int _features;
    protected JsonToken _lastClearedToken;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum NumberType {
        INT,
        LONG,
        BIG_INTEGER,
        FLOAT,
        DOUBLE,
        BIG_DECIMAL
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException, JsonParseException;

    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName() throws IOException, JsonParseException;

    public abstract BigDecimal getDecimalValue() throws IOException, JsonParseException;

    public abstract double getDoubleValue() throws IOException, JsonParseException;

    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return null;
    }

    public abstract float getFloatValue() throws IOException, JsonParseException;

    public Object getInputSource() {
        return null;
    }

    public abstract int getIntValue() throws IOException, JsonParseException;

    public abstract long getLongValue() throws IOException, JsonParseException;

    public abstract NumberType getNumberType() throws IOException, JsonParseException;

    public abstract Number getNumberValue() throws IOException, JsonParseException;

    public abstract JsonStreamContext getParsingContext();

    public abstract String getText() throws IOException, JsonParseException;

    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    public abstract int getTextLength() throws IOException, JsonParseException;

    public abstract int getTextOffset() throws IOException, JsonParseException;

    public abstract JsonLocation getTokenLocation();

    public boolean getValueAsBoolean(boolean z) throws IOException, JsonParseException {
        return z;
    }

    public double getValueAsDouble(double d) throws IOException, JsonParseException {
        return d;
    }

    public int getValueAsInt(int i) throws IOException, JsonParseException {
        return i;
    }

    public long getValueAsLong(long j) throws IOException, JsonParseException {
        return j;
    }

    public boolean hasTextCharacters() {
        return false;
    }

    public abstract boolean isClosed();

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        return -1;
    }

    public int releaseBuffered(Writer writer) throws IOException {
        return -1;
    }

    public abstract void setCodec(ObjectCodec objectCodec);

    public abstract JsonParser skipChildren() throws IOException, JsonParseException;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum Feature {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false),
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true);
        
        final boolean _defaultState;

        public static int collectDefaults() {
            Feature[] values;
            int i = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        Feature(boolean z) {
            this._defaultState = z;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i) {
            return (i & getMask()) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParser() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParser(int i) {
        this._features = i;
    }

    public void setSchema(FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Parser of type " + getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }

    @Override // org.codehaus.jackson.Versioned
    public Version version() {
        return Version.unknownVersion();
    }

    public JsonParser enable(Feature feature) {
        this._features = feature.getMask() | this._features;
        return this;
    }

    public JsonParser disable(Feature feature) {
        this._features = (~feature.getMask()) & this._features;
        return this;
    }

    public JsonParser configure(Feature feature, boolean z) {
        if (z) {
            enableFeature(feature);
        } else {
            disableFeature(feature);
        }
        return this;
    }

    public boolean isEnabled(Feature feature) {
        return (feature.getMask() & this._features) != 0;
    }

    public void setFeature(Feature feature, boolean z) {
        configure(feature, z);
    }

    public void enableFeature(Feature feature) {
        enable(feature);
    }

    public void disableFeature(Feature feature) {
        disable(feature);
    }

    public final boolean isFeatureEnabled(Feature feature) {
        return isEnabled(feature);
    }

    public JsonToken nextValue() throws IOException, JsonParseException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        return nextToken() == JsonToken.FIELD_NAME && serializableString.getValue().equals(getCurrentName());
    }

    public String nextTextValue() throws IOException, JsonParseException {
        if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        }
        return null;
    }

    public int nextIntValue(int i) throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
    }

    public long nextLongValue(long j) throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
    }

    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        switch (nextToken()) {
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public void clearCurrentToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            this._lastClearedToken = jsonToken;
            this._currToken = null;
        }
    }

    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    public boolean isExpectedStartArrayToken() {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public byte getByteValue() throws IOException, JsonParseException {
        int intValue = getIntValue();
        if (intValue < -128 || intValue > 255) {
            throw _constructError("Numeric value (" + getText() + ") out of range of Java byte");
        }
        return (byte) intValue;
    }

    public short getShortValue() throws IOException, JsonParseException {
        int intValue = getIntValue();
        if (intValue < -32768 || intValue > 32767) {
            throw _constructError("Numeric value (" + getText() + ") out of range of Java short");
        }
        return (short) intValue;
    }

    public boolean getBooleanValue() throws IOException, JsonParseException {
        if (getCurrentToken() == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (getCurrentToken() == JsonToken.VALUE_FALSE) {
            return false;
        }
        throw new JsonParseException("Current token (" + this._currToken + ") not of boolean type", getCurrentLocation());
    }

    public byte[] getBinaryValue() throws IOException, JsonParseException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public int getValueAsInt() throws IOException, JsonParseException {
        return getValueAsInt(0);
    }

    public long getValueAsLong() throws IOException, JsonParseException {
        return getValueAsLong(0L);
    }

    public double getValueAsDouble() throws IOException, JsonParseException {
        return getValueAsDouble(0.0d);
    }

    public boolean getValueAsBoolean() throws IOException, JsonParseException {
        return getValueAsBoolean(false);
    }

    public <T> T readValueAs(Class<T> cls) throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return (T) codec.readValue(this, cls);
    }

    public <T> T readValueAs(TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return (T) codec.readValue(this, typeReference);
    }

    public <T> Iterator<T> readValuesAs(Class<T> cls) throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValues(this, cls);
    }

    public <T> Iterator<T> readValuesAs(TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValues(this, typeReference);
    }

    public JsonNode readValueAsTree() throws IOException, JsonProcessingException {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree");
        }
        return codec.readTree(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParseException _constructError(String str) {
        return new JsonParseException(str, getCurrentLocation());
    }
}
