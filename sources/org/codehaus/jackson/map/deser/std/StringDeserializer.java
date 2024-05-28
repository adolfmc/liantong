package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StringDeserializer extends StdScalarDeserializer<String> {
    public StringDeserializer() {
        super(String.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            return jsonParser.getText();
        }
        if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object embeddedObject = jsonParser.getEmbeddedObject();
            if (embeddedObject == null) {
                return null;
            }
            if (embeddedObject instanceof byte[]) {
                return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false);
            }
            return embeddedObject.toString();
        } else if (currentToken.isScalarValue()) {
            return jsonParser.getText();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    @Override // org.codehaus.jackson.map.deser.std.StdScalarDeserializer, org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public String deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return deserialize(jsonParser, deserializationContext);
    }
}
