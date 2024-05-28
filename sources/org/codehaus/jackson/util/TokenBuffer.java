package org.codehaus.jackson.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.impl.JsonParserMinimalBase;
import org.codehaus.jackson.impl.JsonReadContext;
import org.codehaus.jackson.impl.JsonWriteContext;
import org.codehaus.jackson.p467io.SerializedString;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TokenBuffer extends JsonGenerator {
    protected static final int DEFAULT_PARSER_FEATURES = JsonParser.Feature.collectDefaults();
    protected int _appendOffset;
    protected boolean _closed;
    protected Segment _first;
    protected Segment _last;
    protected ObjectCodec _objectCodec;
    protected int _generatorFeatures = DEFAULT_PARSER_FEATURES;
    protected JsonWriteContext _writeContext = JsonWriteContext.createRootContext();

    @Override // org.codehaus.jackson.JsonGenerator
    public void flush() throws IOException {
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    public TokenBuffer(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        Segment segment = new Segment();
        this._last = segment;
        this._first = segment;
        this._appendOffset = 0;
    }

    public JsonParser asParser() {
        return asParser(this._objectCodec);
    }

    public JsonParser asParser(ObjectCodec objectCodec) {
        return new Parser(this._first, objectCodec);
    }

    public JsonParser asParser(JsonParser jsonParser) {
        Parser parser = new Parser(this._first, jsonParser.getCodec());
        parser.setLocation(jsonParser.getTokenLocation());
        return parser;
    }

    public void serialize(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        Segment segment = this._first;
        int i = -1;
        while (true) {
            i++;
            if (i >= 16) {
                segment = segment.next();
                if (segment == null) {
                    return;
                }
                i = 0;
            }
            JsonToken type = segment.type(i);
            if (type == null) {
                return;
            }
            switch (type) {
                case START_OBJECT:
                    jsonGenerator.writeStartObject();
                    break;
                case END_OBJECT:
                    jsonGenerator.writeEndObject();
                    break;
                case START_ARRAY:
                    jsonGenerator.writeStartArray();
                    break;
                case END_ARRAY:
                    jsonGenerator.writeEndArray();
                    break;
                case FIELD_NAME:
                    Object obj = segment.get(i);
                    if (obj instanceof SerializableString) {
                        jsonGenerator.writeFieldName((SerializableString) obj);
                        break;
                    } else {
                        jsonGenerator.writeFieldName((String) obj);
                        break;
                    }
                case VALUE_STRING:
                    Object obj2 = segment.get(i);
                    if (obj2 instanceof SerializableString) {
                        jsonGenerator.writeString((SerializableString) obj2);
                        break;
                    } else {
                        jsonGenerator.writeString((String) obj2);
                        break;
                    }
                case VALUE_NUMBER_INT:
                    Number number = (Number) segment.get(i);
                    if (number instanceof BigInteger) {
                        jsonGenerator.writeNumber((BigInteger) number);
                        break;
                    } else if (number instanceof Long) {
                        jsonGenerator.writeNumber(number.longValue());
                        break;
                    } else {
                        jsonGenerator.writeNumber(number.intValue());
                        break;
                    }
                case VALUE_NUMBER_FLOAT:
                    Object obj3 = segment.get(i);
                    if (obj3 instanceof BigDecimal) {
                        jsonGenerator.writeNumber((BigDecimal) obj3);
                        break;
                    } else if (obj3 instanceof Float) {
                        jsonGenerator.writeNumber(((Float) obj3).floatValue());
                        break;
                    } else if (obj3 instanceof Double) {
                        jsonGenerator.writeNumber(((Double) obj3).doubleValue());
                        break;
                    } else if (obj3 == null) {
                        jsonGenerator.writeNull();
                        break;
                    } else if (obj3 instanceof String) {
                        jsonGenerator.writeNumber((String) obj3);
                        break;
                    } else {
                        throw new JsonGenerationException("Unrecognized value type for VALUE_NUMBER_FLOAT: " + obj3.getClass().getName() + ", can not serialize");
                    }
                case VALUE_TRUE:
                    jsonGenerator.writeBoolean(true);
                    break;
                case VALUE_FALSE:
                    jsonGenerator.writeBoolean(false);
                    break;
                case VALUE_NULL:
                    jsonGenerator.writeNull();
                    break;
                case VALUE_EMBEDDED_OBJECT:
                    jsonGenerator.writeObject(segment.get(i));
                    break;
                default:
                    throw new RuntimeException("Internal error: should never end up through this code path");
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[TokenBuffer: ");
        JsonParser asParser = asParser();
        int i = 0;
        while (true) {
            try {
                JsonToken nextToken = asParser.nextToken();
                if (nextToken == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(nextToken.toString());
                }
                i++;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        if (i >= 100) {
            sb.append(" ... (truncated ");
            sb.append(i - 100);
            sb.append(" entries)");
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        this._generatorFeatures = feature.getMask() | this._generatorFeatures;
        return this;
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        this._generatorFeatures = (~feature.getMask()) & this._generatorFeatures;
        return this;
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this._generatorFeatures) != 0;
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public final JsonWriteContext getOutputContext() {
        return this._writeContext;
    }

    @Override // org.codehaus.jackson.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._closed = true;
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public boolean isClosed() {
        return this._closed;
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public final void writeStartArray() throws IOException, JsonGenerationException {
        _append(JsonToken.START_ARRAY);
        this._writeContext = this._writeContext.createChildArrayContext();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public final void writeEndArray() throws IOException, JsonGenerationException {
        _append(JsonToken.END_ARRAY);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public final void writeStartObject() throws IOException, JsonGenerationException {
        _append(JsonToken.START_OBJECT);
        this._writeContext = this._writeContext.createChildObjectContext();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public final void writeEndObject() throws IOException, JsonGenerationException {
        _append(JsonToken.END_OBJECT);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public final void writeFieldName(String str) throws IOException, JsonGenerationException {
        _append(JsonToken.FIELD_NAME, str);
        this._writeContext.writeFieldName(str);
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        _append(JsonToken.FIELD_NAME, serializableString);
        this._writeContext.writeFieldName(serializableString.getValue());
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializedString serializedString) throws IOException, JsonGenerationException {
        _append(JsonToken.FIELD_NAME, serializedString);
        this._writeContext.writeFieldName(serializedString.getValue());
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeString(String str) throws IOException, JsonGenerationException {
        if (str == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, str);
        }
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        writeString(new String(cArr, i, i2));
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        if (serializableString == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, serializableString);
        }
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeRaw(char c) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        _reportUnsupportedOperation();
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeNumber(int i) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeNumber(long j) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeNumber(double d) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeNumber(float f) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        if (bigDecimal == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException {
        if (bigInteger == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_INT, bigInteger);
        }
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeNumber(String str) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, str);
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        _append(z ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeNull() throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NULL);
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeObject(Object obj) throws IOException, JsonProcessingException {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeTree(JsonNode jsonNode) throws IOException, JsonProcessingException {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, jsonNode);
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        writeObject(bArr2);
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void copyCurrentEvent(JsonParser jsonParser) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken()) {
            case START_OBJECT:
                writeStartObject();
                return;
            case END_OBJECT:
                writeEndObject();
                return;
            case START_ARRAY:
                writeStartArray();
                return;
            case END_ARRAY:
                writeEndArray();
                return;
            case FIELD_NAME:
                writeFieldName(jsonParser.getCurrentName());
                return;
            case VALUE_STRING:
                if (jsonParser.hasTextCharacters()) {
                    writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                    return;
                } else {
                    writeString(jsonParser.getText());
                    return;
                }
            case VALUE_NUMBER_INT:
                switch (jsonParser.getNumberType()) {
                    case INT:
                        writeNumber(jsonParser.getIntValue());
                        return;
                    case BIG_INTEGER:
                        writeNumber(jsonParser.getBigIntegerValue());
                        return;
                    default:
                        writeNumber(jsonParser.getLongValue());
                        return;
                }
            case VALUE_NUMBER_FLOAT:
                switch (jsonParser.getNumberType()) {
                    case BIG_DECIMAL:
                        writeNumber(jsonParser.getDecimalValue());
                        return;
                    case FLOAT:
                        writeNumber(jsonParser.getFloatValue());
                        return;
                    default:
                        writeNumber(jsonParser.getDoubleValue());
                        return;
                }
            case VALUE_TRUE:
                writeBoolean(true);
                return;
            case VALUE_FALSE:
                writeBoolean(false);
                return;
            case VALUE_NULL:
                writeNull();
                return;
            case VALUE_EMBEDDED_OBJECT:
                writeObject(jsonParser.getEmbeddedObject());
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
    }

    @Override // org.codehaus.jackson.JsonGenerator
    public void copyCurrentStructure(JsonParser jsonParser) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.FIELD_NAME) {
            writeFieldName(jsonParser.getCurrentName());
            currentToken = jsonParser.nextToken();
        }
        int i = C134181.$SwitchMap$org$codehaus$jackson$JsonToken[currentToken.ordinal()];
        if (i == 1) {
            writeStartObject();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                copyCurrentStructure(jsonParser);
            }
            writeEndObject();
        } else if (i == 3) {
            writeStartArray();
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                copyCurrentStructure(jsonParser);
            }
            writeEndArray();
        } else {
            copyCurrentEvent(jsonParser);
        }
    }

    protected final void _append(JsonToken jsonToken) {
        Segment append = this._last.append(this._appendOffset, jsonToken);
        if (append == null) {
            this._appendOffset++;
            return;
        }
        this._last = append;
        this._appendOffset = 1;
    }

    protected final void _append(JsonToken jsonToken, Object obj) {
        Segment append = this._last.append(this._appendOffset, jsonToken, obj);
        if (append == null) {
            this._appendOffset++;
            return;
        }
        this._last = append;
        this._appendOffset = 1;
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Parser extends JsonParserMinimalBase {
        protected transient ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected JsonLocation _location;
        protected JsonReadContext _parsingContext;
        protected Segment _segment;
        protected int _segmentPtr;

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public int getTextOffset() {
            return 0;
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public boolean hasTextCharacters() {
            return false;
        }

        public Parser(Segment segment, ObjectCodec objectCodec) {
            super(0);
            this._location = null;
            this._segment = segment;
            this._segmentPtr = -1;
            this._codec = objectCodec;
            this._parsingContext = JsonReadContext.createRootContext(-1, -1);
        }

        public void setLocation(JsonLocation jsonLocation) {
            this._location = jsonLocation;
        }

        @Override // org.codehaus.jackson.JsonParser
        public ObjectCodec getCodec() {
            return this._codec;
        }

        @Override // org.codehaus.jackson.JsonParser
        public void setCodec(ObjectCodec objectCodec) {
            this._codec = objectCodec;
        }

        public JsonToken peekNextToken() throws IOException, JsonParseException {
            if (this._closed) {
                return null;
            }
            Segment segment = this._segment;
            int i = this._segmentPtr + 1;
            if (i >= 16) {
                i = 0;
                segment = segment == null ? null : segment.next();
            }
            if (segment == null) {
                return null;
            }
            return segment.type(i);
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this._closed) {
                return;
            }
            this._closed = true;
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public JsonToken nextToken() throws IOException, JsonParseException {
            Segment segment;
            if (this._closed || (segment = this._segment) == null) {
                return null;
            }
            int i = this._segmentPtr + 1;
            this._segmentPtr = i;
            if (i >= 16) {
                this._segmentPtr = 0;
                this._segment = segment.next();
                if (this._segment == null) {
                    return null;
                }
            }
            this._currToken = this._segment.type(this._segmentPtr);
            if (this._currToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                this._parsingContext.setCurrentName(_currentObject instanceof String ? (String) _currentObject : _currentObject.toString());
            } else if (this._currToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
            } else if (this._currToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
            } else if (this._currToken == JsonToken.END_OBJECT || this._currToken == JsonToken.END_ARRAY) {
                this._parsingContext = this._parsingContext.getParent();
                if (this._parsingContext == null) {
                    this._parsingContext = JsonReadContext.createRootContext(-1, -1);
                }
            }
            return this._currToken;
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public boolean isClosed() {
            return this._closed;
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public JsonStreamContext getParsingContext() {
            return this._parsingContext;
        }

        @Override // org.codehaus.jackson.JsonParser
        public JsonLocation getTokenLocation() {
            return getCurrentLocation();
        }

        @Override // org.codehaus.jackson.JsonParser
        public JsonLocation getCurrentLocation() {
            JsonLocation jsonLocation = this._location;
            return jsonLocation == null ? JsonLocation.f27381NA : jsonLocation;
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public String getCurrentName() {
            return this._parsingContext.getCurrentName();
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public String getText() {
            if (this._currToken == JsonToken.VALUE_STRING || this._currToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof String) {
                    return (String) _currentObject;
                }
                if (_currentObject == null) {
                    return null;
                }
                return _currentObject.toString();
            } else if (this._currToken == null) {
                return null;
            } else {
                switch (this._currToken) {
                    case VALUE_NUMBER_INT:
                    case VALUE_NUMBER_FLOAT:
                        Object _currentObject2 = _currentObject();
                        if (_currentObject2 == null) {
                            return null;
                        }
                        return _currentObject2.toString();
                    default:
                        return this._currToken.asString();
                }
            }
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public char[] getTextCharacters() {
            String text = getText();
            if (text == null) {
                return null;
            }
            return text.toCharArray();
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public int getTextLength() {
            String text = getText();
            if (text == null) {
                return 0;
            }
            return text.length();
        }

        @Override // org.codehaus.jackson.JsonParser
        public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigInteger) {
                return (BigInteger) numberValue;
            }
            if (C134181.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[getNumberType().ordinal()] == 3) {
                return ((BigDecimal) numberValue).toBigInteger();
            }
            return BigInteger.valueOf(numberValue.longValue());
        }

        @Override // org.codehaus.jackson.JsonParser
        public BigDecimal getDecimalValue() throws IOException, JsonParseException {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigDecimal) {
                return (BigDecimal) numberValue;
            }
            int i = C134181.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[getNumberType().ordinal()];
            if (i != 5) {
                switch (i) {
                    case 1:
                        break;
                    case 2:
                        return new BigDecimal((BigInteger) numberValue);
                    default:
                        return BigDecimal.valueOf(numberValue.doubleValue());
                }
            }
            return BigDecimal.valueOf(numberValue.longValue());
        }

        @Override // org.codehaus.jackson.JsonParser
        public double getDoubleValue() throws IOException, JsonParseException {
            return getNumberValue().doubleValue();
        }

        @Override // org.codehaus.jackson.JsonParser
        public float getFloatValue() throws IOException, JsonParseException {
            return getNumberValue().floatValue();
        }

        @Override // org.codehaus.jackson.JsonParser
        public int getIntValue() throws IOException, JsonParseException {
            if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
                return ((Number) _currentObject()).intValue();
            }
            return getNumberValue().intValue();
        }

        @Override // org.codehaus.jackson.JsonParser
        public long getLongValue() throws IOException, JsonParseException {
            return getNumberValue().longValue();
        }

        @Override // org.codehaus.jackson.JsonParser
        public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
            Number numberValue = getNumberValue();
            if (numberValue instanceof Integer) {
                return JsonParser.NumberType.INT;
            }
            if (numberValue instanceof Long) {
                return JsonParser.NumberType.LONG;
            }
            if (numberValue instanceof Double) {
                return JsonParser.NumberType.DOUBLE;
            }
            if (numberValue instanceof BigDecimal) {
                return JsonParser.NumberType.BIG_DECIMAL;
            }
            if (numberValue instanceof Float) {
                return JsonParser.NumberType.FLOAT;
            }
            if (numberValue instanceof BigInteger) {
                return JsonParser.NumberType.BIG_INTEGER;
            }
            return null;
        }

        @Override // org.codehaus.jackson.JsonParser
        public final Number getNumberValue() throws IOException, JsonParseException {
            _checkIsNumber();
            return (Number) _currentObject();
        }

        @Override // org.codehaus.jackson.JsonParser
        public Object getEmbeddedObject() {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return _currentObject();
            }
            return null;
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
        public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof byte[]) {
                    return (byte[]) _currentObject;
                }
            }
            if (this._currToken != JsonToken.VALUE_STRING) {
                throw _constructError("Current token (" + this._currToken + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
            }
            String text = getText();
            if (text == null) {
                return null;
            }
            ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
            if (byteArrayBuilder == null) {
                byteArrayBuilder = new ByteArrayBuilder(100);
                this._byteBuilder = byteArrayBuilder;
            } else {
                byteArrayBuilder.reset();
            }
            _decodeBase64(text, byteArrayBuilder, base64Variant);
            return byteArrayBuilder.toByteArray();
        }

        protected final Object _currentObject() {
            return this._segment.get(this._segmentPtr);
        }

        protected final void _checkIsNumber() throws JsonParseException {
            if (this._currToken == null || !this._currToken.isNumeric()) {
                throw _constructError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
            }
        }

        @Override // org.codehaus.jackson.impl.JsonParserMinimalBase
        public void _handleEOF() throws JsonParseException {
            _throwInternal();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Segment {
        public static final int TOKENS_PER_SEGMENT = 16;
        private static final JsonToken[] TOKEN_TYPES_BY_INDEX = new JsonToken[16];
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object[] _tokens = new Object[16];

        static {
            JsonToken[] values = JsonToken.values();
            System.arraycopy(values, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, values.length - 1));
        }

        public JsonToken type(int i) {
            long j = this._tokenTypes;
            if (i > 0) {
                j >>= i << 2;
            }
            return TOKEN_TYPES_BY_INDEX[((int) j) & 15];
        }

        public Object get(int i) {
            return this._tokens[i];
        }

        public Segment next() {
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken) {
            if (i < 16) {
                set(i, jsonToken);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken);
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken, Object obj) {
            if (i < 16) {
                set(i, jsonToken, obj);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj);
            return this._next;
        }

        public void set(int i, JsonToken jsonToken) {
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes |= ordinal;
        }

        public void set(int i, JsonToken jsonToken, Object obj) {
            this._tokens[i] = obj;
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes |= ordinal;
        }
    }
}
