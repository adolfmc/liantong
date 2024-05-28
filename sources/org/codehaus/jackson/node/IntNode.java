package org.codehaus.jackson.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.p467io.NumberOutput;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class IntNode extends NumericNode {
    private static final IntNode[] CANONICALS = new IntNode[12];
    static final int MAX_CANONICAL = 10;
    static final int MIN_CANONICAL = -1;
    final int _value;

    @Override // org.codehaus.jackson.JsonNode
    public boolean isInt() {
        return true;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean isIntegralNumber() {
        return true;
    }

    static {
        for (int i = 0; i < 12; i++) {
            CANONICALS[i] = new IntNode(i - 1);
        }
    }

    public IntNode(int i) {
        this._value = i;
    }

    public static IntNode valueOf(int i) {
        if (i > 10 || i < -1) {
            return new IntNode(i);
        }
        return CANONICALS[i - (-1)];
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // org.codehaus.jackson.node.NumericNode, org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return JsonParser.NumberType.INT;
    }

    @Override // org.codehaus.jackson.node.NumericNode, org.codehaus.jackson.JsonNode
    public Number getNumberValue() {
        return Integer.valueOf(this._value);
    }

    @Override // org.codehaus.jackson.node.NumericNode, org.codehaus.jackson.JsonNode
    public int getIntValue() {
        return this._value;
    }

    @Override // org.codehaus.jackson.node.NumericNode, org.codehaus.jackson.JsonNode
    public long getLongValue() {
        return this._value;
    }

    @Override // org.codehaus.jackson.node.NumericNode, org.codehaus.jackson.JsonNode
    public double getDoubleValue() {
        return this._value;
    }

    @Override // org.codehaus.jackson.node.NumericNode, org.codehaus.jackson.JsonNode
    public BigDecimal getDecimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    @Override // org.codehaus.jackson.node.NumericNode, org.codehaus.jackson.JsonNode
    public BigInteger getBigIntegerValue() {
        return BigInteger.valueOf(this._value);
    }

    @Override // org.codehaus.jackson.node.NumericNode, org.codehaus.jackson.JsonNode
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return this._value != 0;
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(this._value);
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == getClass() && ((IntNode) obj)._value == this._value;
    }

    public int hashCode() {
        return this._value;
    }
}
