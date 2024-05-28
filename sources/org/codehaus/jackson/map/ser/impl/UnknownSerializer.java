package org.codehaus.jackson.map.ser.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.std.SerializerBase;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class UnknownSerializer extends SerializerBase<Object> {
    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        return null;
    }

    public UnknownSerializer() {
        super(Object.class);
    }

    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonMappingException {
        if (serializerProvider.isEnabled(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS)) {
            failForEmpty(obj);
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeEndObject();
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        if (serializerProvider.isEnabled(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS)) {
            failForEmpty(obj);
        }
        typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
        typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
    }

    protected void failForEmpty(Object obj) throws JsonMappingException {
        throw new JsonMappingException("No serializer found for class " + obj.getClass().getName() + " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS) )");
    }
}
