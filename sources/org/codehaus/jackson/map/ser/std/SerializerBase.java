package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class SerializerBase<T> extends JsonSerializer<T> implements SchemaAware {
    protected final Class<T> _handledType;

    @Override // org.codehaus.jackson.map.JsonSerializer
    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    /* JADX INFO: Access modifiers changed from: protected */
    public SerializerBase(Class<T> cls) {
        this._handledType = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SerializerBase(JavaType javaType) {
        this._handledType = (Class<T>) javaType.getRawClass();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public SerializerBase(Class<?> cls, boolean z) {
        this._handledType = cls;
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public final Class<T> handledType() {
        return this._handledType;
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        return createSchemaNode("string");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectNode createObjectNode() {
        return JsonNodeFactory.instance.objectNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectNode createSchemaNode(String str) {
        ObjectNode createObjectNode = createObjectNode();
        createObjectNode.put("type", str);
        return createObjectNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectNode createSchemaNode(String str, boolean z) {
        ObjectNode createSchemaNode = createSchemaNode(str);
        if (!z) {
            createSchemaNode.put("required", !z);
        }
        return createSchemaNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultSerializer(JsonSerializer<?> jsonSerializer) {
        return (jsonSerializer == null || jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, String str) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        boolean z = serializerProvider == null || serializerProvider.isEnabled(SerializationConfig.Feature.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z || !(th instanceof JsonMappingException)) {
                throw ((IOException) th);
            }
        } else if (!z && (th instanceof RuntimeException)) {
            throw ((RuntimeException) th);
        }
        throw JsonMappingException.wrapWithPath(th, obj, str);
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, int i) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        boolean z = serializerProvider == null || serializerProvider.isEnabled(SerializationConfig.Feature.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z || !(th instanceof JsonMappingException)) {
                throw ((IOException) th);
            }
        } else if (!z && (th instanceof RuntimeException)) {
            throw ((RuntimeException) th);
        }
        throw JsonMappingException.wrapWithPath(th, obj, i);
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, String str) throws IOException {
        wrapAndThrow((SerializerProvider) null, th, obj, str);
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, int i) throws IOException {
        wrapAndThrow((SerializerProvider) null, th, obj, i);
    }
}
