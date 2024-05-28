package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ClassDeserializer extends StdScalarDeserializer<Class<?>> {
    public ClassDeserializer() {
        super(Class.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Class<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            String text = jsonParser.getText();
            if (text.indexOf(46) < 0) {
                if ("int".equals(text)) {
                    return Integer.TYPE;
                }
                if ("long".equals(text)) {
                    return Long.TYPE;
                }
                if ("float".equals(text)) {
                    return Float.TYPE;
                }
                if ("double".equals(text)) {
                    return Double.TYPE;
                }
                if ("boolean".equals(text)) {
                    return Boolean.TYPE;
                }
                if ("byte".equals(text)) {
                    return Byte.TYPE;
                }
                if ("char".equals(text)) {
                    return Character.TYPE;
                }
                if ("short".equals(text)) {
                    return Short.TYPE;
                }
                if ("void".equals(text)) {
                    return Void.TYPE;
                }
            }
            try {
                return Class.forName(jsonParser.getText());
            } catch (ClassNotFoundException e) {
                throw deserializationContext.instantiationException(this._valueClass, e);
            }
        }
        throw deserializationContext.mappingException(this._valueClass, currentToken);
    }
}
