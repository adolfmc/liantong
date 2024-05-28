package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\9227576_dexfile_execute.dex */
public class JsonNodeDeserializer extends org.codehaus.jackson.map.deser.std.JsonNodeDeserializer {
    @Deprecated
    public static final JsonNodeDeserializer instance = new JsonNodeDeserializer();

    @Deprecated
    protected final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return deserializeObject(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }

    @Deprecated
    protected final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return deserializeArray(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }

    @Deprecated
    protected final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return deserializeAny(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }
}
