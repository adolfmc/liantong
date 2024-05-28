package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class JavaTypeDeserializer extends StdScalarDeserializer<JavaType> {
    public JavaTypeDeserializer() {
        super(JavaType.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public JavaType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return getEmptyValue();
            }
            return deserializationContext.getTypeFactory().constructFromCanonical(trim);
        } else if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return (JavaType) jsonParser.getEmbeddedObject();
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }
}
