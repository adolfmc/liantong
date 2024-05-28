package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonParser;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class NumericNode extends ValueNode {
    @Override // org.codehaus.jackson.JsonNode
    public abstract String asText();

    @Override // org.codehaus.jackson.JsonNode
    public abstract BigInteger getBigIntegerValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract BigDecimal getDecimalValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract double getDoubleValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract int getIntValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract long getLongValue();

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public abstract JsonParser.NumberType getNumberType();

    @Override // org.codehaus.jackson.JsonNode
    public abstract Number getNumberValue();

    @Override // org.codehaus.jackson.JsonNode
    public final boolean isNumber() {
        return true;
    }

    @Override // org.codehaus.jackson.JsonNode
    public int asInt() {
        return getIntValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return getIntValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public long asLong() {
        return getLongValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return getLongValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public double asDouble() {
        return getDoubleValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return getDoubleValue();
    }
}
