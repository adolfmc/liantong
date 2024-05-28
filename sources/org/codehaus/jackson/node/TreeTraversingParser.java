package org.codehaus.jackson.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.impl.JsonParserMinimalBase;
import org.codehaus.jackson.node.NodeCursor;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TreeTraversingParser extends JsonParserMinimalBase {
    protected boolean _closed;
    protected JsonToken _nextToken;
    protected NodeCursor _nodeCursor;
    protected ObjectCodec _objectCodec;
    protected boolean _startContainer;

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public boolean hasTextCharacters() {
        return false;
    }

    public TreeTraversingParser(JsonNode jsonNode) {
        this(jsonNode, null);
    }

    public TreeTraversingParser(JsonNode jsonNode, ObjectCodec objectCodec) {
        super(0);
        this._objectCodec = objectCodec;
        if (jsonNode.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            this._nodeCursor = new NodeCursor.Array(jsonNode, null);
        } else if (jsonNode.isObject()) {
            this._nextToken = JsonToken.START_OBJECT;
            this._nodeCursor = new NodeCursor.Object(jsonNode, null);
        } else {
            this._nodeCursor = new NodeCursor.RootValue(jsonNode, null);
        }
    }

    @Override // org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override // org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this._closed) {
            return;
        }
        this._closed = true;
        this._nodeCursor = null;
        this._currToken = null;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken jsonToken = this._nextToken;
        if (jsonToken != null) {
            this._currToken = jsonToken;
            this._nextToken = null;
            return this._currToken;
        } else if (this._startContainer) {
            this._startContainer = false;
            if (!this._nodeCursor.currentHasChildren()) {
                this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                return this._currToken;
            }
            this._nodeCursor = this._nodeCursor.iterateChildren();
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        } else {
            NodeCursor nodeCursor = this._nodeCursor;
            if (nodeCursor == null) {
                this._closed = true;
                return null;
            }
            this._currToken = nodeCursor.nextToken();
            if (this._currToken != null) {
                if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                    this._startContainer = true;
                }
                return this._currToken;
            }
            this._currToken = this._nodeCursor.endToken();
            this._nodeCursor = this._nodeCursor.getParent();
            return this._currToken;
        }
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public boolean isClosed() {
        return this._closed;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public String getCurrentName() {
        NodeCursor nodeCursor = this._nodeCursor;
        if (nodeCursor == null) {
            return null;
        }
        return nodeCursor.getCurrentName();
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public JsonStreamContext getParsingContext() {
        return this._nodeCursor;
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonLocation getTokenLocation() {
        return JsonLocation.f27381NA;
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonLocation getCurrentLocation() {
        return JsonLocation.f27381NA;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public String getText() {
        if (this._closed) {
            return null;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                return this._nodeCursor.getCurrentName();
            case VALUE_STRING:
                return currentNode().getTextValue();
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return String.valueOf(currentNode().getNumberValue());
            case VALUE_EMBEDDED_OBJECT:
                JsonNode currentNode = currentNode();
                if (currentNode != null && currentNode.isBinary()) {
                    return currentNode.asText();
                }
                break;
        }
        if (this._currToken == null) {
            return null;
        }
        return this._currToken.asString();
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() throws IOException, JsonParseException {
        return getText().toCharArray();
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public int getTextLength() throws IOException, JsonParseException {
        return getText().length();
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        JsonNode currentNumericNode = currentNumericNode();
        if (currentNumericNode == null) {
            return null;
        }
        return currentNumericNode.getNumberType();
    }

    @Override // org.codehaus.jackson.JsonParser
    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        return currentNumericNode().getBigIntegerValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        return currentNumericNode().getDecimalValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public double getDoubleValue() throws IOException, JsonParseException {
        return currentNumericNode().getDoubleValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public float getFloatValue() throws IOException, JsonParseException {
        return (float) currentNumericNode().getDoubleValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public long getLongValue() throws IOException, JsonParseException {
        return currentNumericNode().getLongValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public int getIntValue() throws IOException, JsonParseException {
        return currentNumericNode().getIntValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public Number getNumberValue() throws IOException, JsonParseException {
        return currentNumericNode().getNumberValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public Object getEmbeddedObject() {
        JsonNode currentNode;
        if (this._closed || (currentNode = currentNode()) == null) {
            return null;
        }
        if (currentNode.isPojo()) {
            return ((POJONode) currentNode).getPojo();
        }
        if (currentNode.isBinary()) {
            return ((BinaryNode) currentNode).getBinaryValue();
        }
        return null;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        JsonNode currentNode = currentNode();
        if (currentNode != null) {
            byte[] binaryValue = currentNode.getBinaryValue();
            if (binaryValue != null) {
                return binaryValue;
            }
            if (currentNode.isPojo()) {
                Object pojo = ((POJONode) currentNode).getPojo();
                if (pojo instanceof byte[]) {
                    return (byte[]) pojo;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    protected JsonNode currentNode() {
        NodeCursor nodeCursor;
        if (this._closed || (nodeCursor = this._nodeCursor) == null) {
            return null;
        }
        return nodeCursor.currentNode();
    }

    protected JsonNode currentNumericNode() throws JsonParseException {
        JsonNode currentNode = currentNode();
        if (currentNode == null || !currentNode.isNumber()) {
            JsonToken asToken = currentNode == null ? null : currentNode.asToken();
            throw _constructError("Current token (" + asToken + ") not numeric, can not use numeric value accessors");
        }
        return currentNode;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase
    public void _handleEOF() throws JsonParseException {
        _throwInternal();
    }
}
