package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: JsonNodeDeserializer.java */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class BaseNodeDeserializer<N extends JsonNode> extends StdDeserializer<N> {
    protected void _handleDuplicateField(String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws JsonProcessingException {
    }

    public BaseNodeDeserializer(Class<N> cls) {
        super((Class<?>) cls);
    }

    @Override // org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected void _reportProblem(JsonParser jsonParser, String str) throws JsonMappingException {
        throw new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        JsonNode deserializeObject;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            switch (jsonParser.nextToken()) {
                case START_OBJECT:
                    deserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
                case START_ARRAY:
                    deserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
                case VALUE_STRING:
                    deserializeObject = jsonNodeFactory.textNode(jsonParser.getText());
                    break;
                default:
                    deserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
            }
            JsonNode put = objectNode.put(currentName, deserializeObject);
            if (put != null) {
                _handleDuplicateField(currentName, objectNode, put, deserializeObject);
            }
            currentToken = jsonParser.nextToken();
        }
        return objectNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            switch (jsonParser.nextToken()) {
                case START_OBJECT:
                    arrayNode.add(deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case START_ARRAY:
                    arrayNode.add(deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case VALUE_STRING:
                    arrayNode.add(jsonNodeFactory.textNode(jsonParser.getText()));
                    break;
                case END_ARRAY:
                    return arrayNode;
                default:
                    arrayNode.add(deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken()) {
            case START_OBJECT:
                return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            case START_ARRAY:
                return deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            case VALUE_STRING:
                return jsonNodeFactory.textNode(jsonParser.getText());
            case END_ARRAY:
            default:
                throw deserializationContext.mappingException(getValueClass());
            case FIELD_NAME:
                return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            case VALUE_EMBEDDED_OBJECT:
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return jsonNodeFactory.nullNode();
                }
                if (embeddedObject.getClass() == byte[].class) {
                    return jsonNodeFactory.binaryNode((byte[]) embeddedObject);
                }
                return jsonNodeFactory.POJONode(embeddedObject);
            case VALUE_NUMBER_INT:
                JsonParser.NumberType numberType = jsonParser.getNumberType();
                if (numberType == JsonParser.NumberType.BIG_INTEGER || deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
                }
                if (numberType == JsonParser.NumberType.INT) {
                    return jsonNodeFactory.numberNode(jsonParser.getIntValue());
                }
                return jsonNodeFactory.numberNode(jsonParser.getLongValue());
            case VALUE_NUMBER_FLOAT:
                if (jsonParser.getNumberType() == JsonParser.NumberType.BIG_DECIMAL || deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
                }
                return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
            case VALUE_TRUE:
                return jsonNodeFactory.booleanNode(true);
            case VALUE_FALSE:
                return jsonNodeFactory.booleanNode(false);
            case VALUE_NULL:
                return jsonNodeFactory.nullNode();
        }
    }
}
