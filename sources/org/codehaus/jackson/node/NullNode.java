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
public final class NullNode extends ValueNode {
    public static final NullNode instance = new NullNode();

    @Override // org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return 0.0d;
    }

    @Override // org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return 0;
    }

    @Override // org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return 0L;
    }

    @Override // org.codehaus.jackson.JsonNode
    public String asText() {
        return "null";
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean isNull() {
        return true;
    }

    private NullNode() {
    }

    public static NullNode getInstance() {
        return instance;
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NULL;
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNull();
    }
}
