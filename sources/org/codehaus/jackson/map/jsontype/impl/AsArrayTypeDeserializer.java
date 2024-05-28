package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AsArrayTypeDeserializer extends TypeDeserializerBase {
    @Deprecated
    public AsArrayTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this(javaType, typeIdResolver, beanProperty, null);
    }

    public AsArrayTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls) {
        super(javaType, typeIdResolver, beanProperty, cls);
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.EnumC13395As getTypeInclusion() {
        return JsonTypeInfo.EnumC13395As.WRAPPER_ARRAY;
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
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
        boolean isExpectedStartArrayToken = jsonParser.isExpectedStartArrayToken();
        Object deserialize = _findDeserializer(deserializationContext, _locateTypeId(jsonParser, deserializationContext)).deserialize(jsonParser, deserializationContext);
        if (!isExpectedStartArrayToken || jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return deserialize;
        }
        throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value");
    }

    protected final String _locateTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            if ((this._idResolver instanceof TypeIdResolverBase) && this._defaultImpl != null) {
                return ((TypeIdResolverBase) this._idResolver).idFromBaseType();
            }
            JsonToken jsonToken = JsonToken.START_ARRAY;
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + baseTypeName());
        } else if (jsonParser.nextToken() != JsonToken.VALUE_STRING) {
            if ((this._idResolver instanceof TypeIdResolverBase) && this._defaultImpl != null) {
                return ((TypeIdResolverBase) this._idResolver).idFromBaseType();
            }
            JsonToken jsonToken2 = JsonToken.VALUE_STRING;
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken2, "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
        } else {
            String text = jsonParser.getText();
            jsonParser.nextToken();
            return text;
        }
    }
}
