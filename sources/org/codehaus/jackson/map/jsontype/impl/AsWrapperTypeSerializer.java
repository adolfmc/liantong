package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AsWrapperTypeSerializer extends TypeSerializerBase {
    public AsWrapperTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        super(typeIdResolver, beanProperty);
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.TypeSerializerBase, org.codehaus.jackson.map.TypeSerializer
    public JsonTypeInfo.EnumC13395As getTypeInclusion() {
        return JsonTypeInfo.EnumC13395As.WRAPPER_OBJECT;
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart(this._idResolver.idFromValue(obj));
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart(this._idResolver.idFromValueAndType(obj, cls));
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart(this._idResolver.idFromValue(obj));
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart(this._idResolver.idFromValueAndType(obj, cls));
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(this._idResolver.idFromValue(obj));
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(this._idResolver.idFromValueAndType(obj, cls));
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeEndObject();
    }
}
