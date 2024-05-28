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
import org.codehaus.jackson.util.JsonParserSequence;
import org.codehaus.jackson.util.TokenBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer {
    protected final String _typePropertyName;

    @Deprecated
    public AsPropertyTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        this(javaType, typeIdResolver, beanProperty, null, str);
    }

    public AsPropertyTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls, String str) {
        super(javaType, typeIdResolver, beanProperty, cls);
        this._typePropertyName = str;
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.EnumC13395As getTypeInclusion() {
        return JsonTypeInfo.EnumC13395As.PROPERTY;
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, org.codehaus.jackson.map.TypeDeserializer
    public String getPropertyName() {
        return this._typePropertyName;
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        } else if (currentToken == JsonToken.START_ARRAY) {
            return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, null);
        } else {
            if (currentToken != JsonToken.FIELD_NAME) {
                return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, null);
            }
        }
        TokenBuffer tokenBuffer = null;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (this._typePropertyName.equals(currentName)) {
                JsonDeserializer<Object> _findDeserializer = _findDeserializer(deserializationContext, jsonParser.getText());
                if (tokenBuffer != null) {
                    jsonParser = JsonParserSequence.createFlattened(tokenBuffer.asParser(jsonParser), jsonParser);
                }
                jsonParser.nextToken();
                return _findDeserializer.deserialize(jsonParser, deserializationContext);
            }
            if (tokenBuffer == null) {
                tokenBuffer = new TokenBuffer(null);
            }
            tokenBuffer.writeFieldName(currentName);
            tokenBuffer.copyCurrentStructure(jsonParser);
            currentToken = jsonParser.nextToken();
        }
        return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, tokenBuffer);
    }

    protected Object _deserializeTypedUsingDefaultImpl(JsonParser jsonParser, DeserializationContext deserializationContext, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        if (this._defaultImpl != null) {
            JsonDeserializer<Object> _findDefaultImplDeserializer = _findDefaultImplDeserializer(deserializationContext);
            if (tokenBuffer != null) {
                tokenBuffer.writeEndObject();
                jsonParser = tokenBuffer.asParser(jsonParser);
                jsonParser.nextToken();
            }
            return _findDefaultImplDeserializer.deserialize(jsonParser, deserializationContext);
        }
        Object _deserializeIfNatural = _deserializeIfNatural(jsonParser, deserializationContext);
        if (_deserializeIfNatural != null) {
            return _deserializeIfNatural;
        }
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromAny(jsonParser, deserializationContext);
        }
        JsonToken jsonToken = JsonToken.FIELD_NAME;
        throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "missing property '" + this._typePropertyName + "' that is to contain type id  (for class " + baseTypeName() + ")");
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromArray(jsonParser, deserializationContext);
        }
        return deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    protected Object _deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken()) {
            case VALUE_STRING:
                if (this._baseType.getRawClass().isAssignableFrom(String.class)) {
                    return jsonParser.getText();
                }
                return null;
            case VALUE_NUMBER_INT:
                if (this._baseType.getRawClass().isAssignableFrom(Integer.class)) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                return null;
            case VALUE_NUMBER_FLOAT:
                if (this._baseType.getRawClass().isAssignableFrom(Double.class)) {
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
                return null;
            case VALUE_TRUE:
                if (this._baseType.getRawClass().isAssignableFrom(Boolean.class)) {
                    return Boolean.TRUE;
                }
                return null;
            case VALUE_FALSE:
                if (this._baseType.getRawClass().isAssignableFrom(Boolean.class)) {
                    return Boolean.FALSE;
                }
                return null;
            default:
                return null;
        }
    }
}
