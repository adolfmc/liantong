package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.ObjectBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class UntypedObjectDeserializer extends StdDeserializer<Object> {
    private static final Object[] NO_OBJECTS = new Object[0];

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken()) {
            case START_OBJECT:
                return mapObject(jsonParser, deserializationContext);
            case END_OBJECT:
            case END_ARRAY:
            default:
                throw deserializationContext.mappingException(Object.class);
            case START_ARRAY:
                return mapArray(jsonParser, deserializationContext);
            case FIELD_NAME:
                return mapObject(jsonParser, deserializationContext);
            case VALUE_EMBEDDED_OBJECT:
                return jsonParser.getEmbeddedObject();
            case VALUE_STRING:
                return jsonParser.getText();
            case VALUE_NUMBER_INT:
                if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser.getBigIntegerValue();
                }
                return jsonParser.getNumberValue();
            case VALUE_NUMBER_FLOAT:
                if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            case VALUE_NULL:
                return null;
        }
    }

    @Override // org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        int i = C134091.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()];
        if (i != 1 && i != 3) {
            switch (i) {
                case 5:
                    break;
                case 6:
                    return jsonParser.getEmbeddedObject();
                case 7:
                    return jsonParser.getText();
                case 8:
                    if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                        return jsonParser.getBigIntegerValue();
                    }
                    return Integer.valueOf(jsonParser.getIntValue());
                case 9:
                    if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return Double.valueOf(jsonParser.getDoubleValue());
                case 10:
                    return Boolean.TRUE;
                case 11:
                    return Boolean.FALSE;
                case 12:
                    return null;
                default:
                    throw deserializationContext.mappingException(Object.class);
            }
        }
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
            return mapArrayToArray(jsonParser, deserializationContext);
        }
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return new ArrayList(4);
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        int i2 = 0;
        while (true) {
            Object deserialize = deserialize(jsonParser, deserializationContext);
            i++;
            if (i2 >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i2 = 0;
            }
            int i3 = i2 + 1;
            resetAndStart[i2] = deserialize;
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                ArrayList arrayList = new ArrayList(i + (i >> 3) + 1);
                leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i3, arrayList);
                return arrayList;
            }
            i2 = i3;
        }
    }

    protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken != JsonToken.FIELD_NAME) {
            return new LinkedHashMap(4);
        }
        String text = jsonParser.getText();
        jsonParser.nextToken();
        Object deserialize = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(text, deserialize);
            return linkedHashMap;
        }
        String text2 = jsonParser.getText();
        jsonParser.nextToken();
        Object deserialize2 = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(text, deserialize);
            linkedHashMap2.put(text2, deserialize2);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(text, deserialize);
        linkedHashMap3.put(text2, deserialize2);
        do {
            String text3 = jsonParser.getText();
            jsonParser.nextToken();
            linkedHashMap3.put(text3, deserialize(jsonParser, deserializationContext));
        } while (jsonParser.nextToken() != JsonToken.END_OBJECT);
        return linkedHashMap3;
    }

    protected Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return NO_OBJECTS;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        while (true) {
            Object deserialize = deserialize(jsonParser, deserializationContext);
            if (i >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i = 0;
            }
            int i2 = i + 1;
            resetAndStart[i] = deserialize;
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i2);
            }
            i = i2;
        }
    }
}
