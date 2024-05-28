package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.EnumMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.util.EnumResolver;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> {
    protected final Class<?> _enumClass;
    protected final JsonDeserializer<Enum<?>> _keyDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;

    @Deprecated
    public EnumMapDeserializer(EnumResolver<?> enumResolver, JsonDeserializer<Object> jsonDeserializer) {
        this(enumResolver.getEnumClass(), new EnumDeserializer(enumResolver), jsonDeserializer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EnumMapDeserializer(Class<?> cls, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<Object> jsonDeserializer2) {
        super(EnumMap.class);
        this._enumClass = cls;
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public EnumMap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw deserializationContext.mappingException(EnumMap.class);
        }
        EnumMap<?, ?> constructMap = constructMap();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            Enum<?> deserialize = this._keyDeserializer.deserialize(jsonParser, deserializationContext);
            if (deserialize == null) {
                throw deserializationContext.weirdStringException(this._enumClass, "value not one of declared Enum instance names");
            }
            constructMap.put((EnumMap<?, ?>) deserialize, (Enum<?>) (jsonParser.nextToken() == JsonToken.VALUE_NULL ? null : this._valueDeserializer.deserialize(jsonParser, deserializationContext)));
        }
        return constructMap;
    }

    @Override // org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    private EnumMap<?, ?> constructMap() {
        return new EnumMap<>(this._enumClass);
    }
}
