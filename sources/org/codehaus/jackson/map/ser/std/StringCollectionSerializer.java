package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.util.Collection;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StringCollectionSerializer extends StaticListSerializerBase<Collection<String>> implements ResolvableSerializer {
    protected JsonSerializer<String> _serializer;

    @Deprecated
    public StringCollectionSerializer(BeanProperty beanProperty) {
        this(beanProperty, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StringCollectionSerializer(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        super(Collection.class, beanProperty);
        this._serializer = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.ser.std.StaticListSerializerBase
    protected JsonNode contentSchema() {
        return createSchemaNode("string", true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        if (this._serializer == null) {
            JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(String.class, this._property);
            if (isDefaultSerializer(findValueSerializer)) {
                return;
            }
            this._serializer = findValueSerializer;
        }
    }

    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serialize(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartArray();
        if (this._serializer == null) {
            serializeContents(collection, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForArray(collection, jsonGenerator);
        if (this._serializer == null) {
            serializeContents(collection, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForArray(collection, jsonGenerator);
    }

    private final void serializeContents(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (this._serializer != null) {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
            return;
        }
        int i = 0;
        for (String str : collection) {
            if (str == null) {
                try {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, collection, i);
                }
            } else {
                jsonGenerator.writeString(str);
            }
            i++;
        }
    }

    private void serializeUsingCustom(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<String> jsonSerializer = this._serializer;
        for (String str : collection) {
            if (str == null) {
                try {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, collection, 0);
                }
            } else {
                jsonSerializer.serialize(str, jsonGenerator, serializerProvider);
            }
        }
    }
}
