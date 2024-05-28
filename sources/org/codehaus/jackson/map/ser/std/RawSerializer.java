package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RawSerializer<T> extends SerializerBase<T> {
    public RawSerializer(Class<?> cls) {
        super(cls, false);
    }

    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeRawValue(t.toString());
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        typeSerializer.writeTypePrefixForScalar(t, jsonGenerator);
        serialize(t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(t, jsonGenerator);
    }

    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("string", true);
    }
}
