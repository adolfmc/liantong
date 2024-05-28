package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AbstractDeserializer extends JsonDeserializer<Object> {
    protected final boolean _acceptBoolean;
    protected final boolean _acceptDouble;
    protected final boolean _acceptInt;
    protected final boolean _acceptString;
    protected final JavaType _baseType;

    public AbstractDeserializer(JavaType javaType) {
        this._baseType = javaType;
        Class<?> rawClass = javaType.getRawClass();
        this._acceptString = rawClass.isAssignableFrom(String.class);
        boolean z = false;
        this._acceptBoolean = rawClass == Boolean.TYPE || rawClass.isAssignableFrom(Boolean.class);
        this._acceptInt = rawClass == Integer.TYPE || rawClass.isAssignableFrom(Integer.class);
        this._acceptDouble = (rawClass == Double.TYPE || rawClass.isAssignableFrom(Double.class)) ? true : z;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        Object _deserializeIfNatural = _deserializeIfNatural(jsonParser, deserializationContext);
        return _deserializeIfNatural != null ? _deserializeIfNatural : typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        throw deserializationContext.instantiationException(this._baseType.getRawClass(), "abstract types can only be instantiated with additional type information");
    }

    protected Object _deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken()) {
            case VALUE_STRING:
                if (this._acceptString) {
                    return jsonParser.getText();
                }
                return null;
            case VALUE_NUMBER_INT:
                if (this._acceptInt) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                return null;
            case VALUE_NUMBER_FLOAT:
                if (this._acceptDouble) {
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
                return null;
            case VALUE_TRUE:
                if (this._acceptBoolean) {
                    return Boolean.TRUE;
                }
                return null;
            case VALUE_FALSE:
                if (this._acceptBoolean) {
                    return Boolean.FALSE;
                }
                return null;
            default:
                return null;
        }
    }
}
