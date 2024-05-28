package org.codehaus.jackson.node;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class POJONode extends ValueNode {
    protected final Object _value;

    @Override // org.codehaus.jackson.JsonNode
    public boolean isPojo() {
        return true;
    }

    public POJONode(Object obj) {
        this._value = obj;
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    @Override // org.codehaus.jackson.JsonNode
    public byte[] getBinaryValue() throws IOException {
        Object obj = this._value;
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        return super.getBinaryValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public String asText() {
        Object obj = this._value;
        return obj == null ? "null" : obj.toString();
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        Object obj = this._value;
        return (obj == null || !(obj instanceof Boolean)) ? z : ((Boolean) obj).booleanValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).intValue() : i;
    }

    @Override // org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).longValue() : j;
    }

    @Override // org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).doubleValue() : d;
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        Object obj = this._value;
        if (obj == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeObject(obj);
        }
    }

    public Object getPojo() {
        return this._value;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            POJONode pOJONode = (POJONode) obj;
            Object obj2 = this._value;
            if (obj2 == null) {
                return pOJONode._value == null;
            }
            return obj2.equals(pOJONode._value);
        }
        return false;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.JsonNode
    public String toString() {
        return String.valueOf(this._value);
    }
}
