package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.EnumSet;
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
public class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> {
    protected final Class<Enum> _enumClass;
    protected final JsonDeserializer<Enum<?>> _enumDeserializer;

    public EnumSetDeserializer(EnumResolver enumResolver) {
        this(enumResolver.getEnumClass(), new EnumDeserializer(enumResolver));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EnumSetDeserializer(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        super(EnumSet.class);
        this._enumClass = cls;
        this._enumDeserializer = jsonDeserializer;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public EnumSet<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            throw deserializationContext.mappingException(EnumSet.class);
        }
        EnumSet<?> constructSet = constructSet();
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return constructSet;
            }
            if (nextToken == JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._enumClass);
            }
            constructSet.add(this._enumDeserializer.deserialize(jsonParser, deserializationContext));
        }
    }

    @Override // org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumClass);
    }
}
