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
public class AsExternalTypeSerializer extends TypeSerializerBase {
    protected final String _typePropertyName;

    public AsExternalTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.TypeSerializerBase, org.codehaus.jackson.map.TypeSerializer
    public String getPropertyName() {
        return this._typePropertyName;
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.TypeSerializerBase, org.codehaus.jackson.map.TypeSerializer
    public JsonTypeInfo.EnumC13395As getTypeInclusion() {
        return JsonTypeInfo.EnumC13395As.EXTERNAL_PROPERTY;
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator, cls);
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator, cls);
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator, cls);
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeSuffix(obj, jsonGenerator);
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeSuffix(obj, jsonGenerator);
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeSuffix(obj, jsonGenerator);
    }

    protected final void _writePrefix(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
    }

    protected final void _writePrefix(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
    }

    protected final void _writeSuffix(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeEndObject();
        jsonGenerator.writeStringField(this._typePropertyName, this._idResolver.idFromValue(obj));
    }
}
