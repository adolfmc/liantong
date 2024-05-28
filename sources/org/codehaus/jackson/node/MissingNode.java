package org.codehaus.jackson.node;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class MissingNode extends BaseJsonNode {
    private static final MissingNode instance = new MissingNode();

    @Override // org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return z;
    }

    @Override // org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return d;
    }

    @Override // org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return i;
    }

    @Override // org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return j;
    }

    @Override // org.codehaus.jackson.JsonNode
    public String asText() {
        return "";
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean isMissingNode() {
        return true;
    }

    @Override // org.codehaus.jackson.JsonNode
    public JsonNode path(int i) {
        return this;
    }

    @Override // org.codehaus.jackson.JsonNode
    public JsonNode path(String str) {
        return this;
    }

    @Override // org.codehaus.jackson.JsonNode
    public String toString() {
        return "";
    }

    private MissingNode() {
    }

    public static MissingNode getInstance() {
        return instance;
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.NOT_AVAILABLE;
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNull();
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        jsonGenerator.writeNull();
    }
}
