package org.codehaus.jackson.node;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class BaseJsonNode extends JsonNode implements JsonSerializableWithType {
    @Override // org.codehaus.jackson.JsonNode
    public abstract JsonToken asToken();

    @Override // org.codehaus.jackson.JsonNode
    public ObjectNode findParent(String str) {
        return null;
    }

    @Override // org.codehaus.jackson.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        return list;
    }

    @Override // org.codehaus.jackson.JsonNode
    public JsonNode findValue(String str) {
        return null;
    }

    @Override // org.codehaus.jackson.JsonNode
    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        return list;
    }

    @Override // org.codehaus.jackson.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        return list;
    }

    @Override // org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return null;
    }

    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException;

    public abstract void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException;

    @Override // org.codehaus.jackson.JsonNode
    public final JsonNode findPath(String str) {
        JsonNode findValue = findValue(str);
        return findValue == null ? MissingNode.getInstance() : findValue;
    }

    @Override // org.codehaus.jackson.JsonNode
    public JsonParser traverse() {
        return new TreeTraversingParser(this);
    }
}
