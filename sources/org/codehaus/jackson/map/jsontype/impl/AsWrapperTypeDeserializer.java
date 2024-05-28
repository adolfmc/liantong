package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AsWrapperTypeDeserializer extends TypeDeserializerBase {
    @Deprecated
    public AsWrapperTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this(javaType, typeIdResolver, beanProperty, null);
    }

    public AsWrapperTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls) {
        super(javaType, typeIdResolver, beanProperty, null);
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.EnumC13395As getTypeInclusion() {
        return JsonTypeInfo.EnumC13395As.WRAPPER_OBJECT;
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    private final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            JsonToken jsonToken = JsonToken.START_OBJECT;
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "need JSON Object to contain As.WRAPPER_OBJECT type information for class " + baseTypeName());
        } else if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            JsonToken jsonToken2 = JsonToken.FIELD_NAME;
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken2, "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
        } else {
            JsonDeserializer<Object> _findDeserializer = _findDeserializer(deserializationContext, jsonParser.getText());
            jsonParser.nextToken();
            Object deserialize = _findDeserializer.deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                return deserialize;
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_OBJECT, "expected closing END_OBJECT after type information and deserialized value");
        }
    }
}
