package org.codehaus.jackson.map.ser.impl;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class UnwrappingBeanPropertyWriter extends BeanPropertyWriter {
    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        super(beanPropertyWriter);
    }

    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, JsonSerializer<Object> jsonSerializer) {
        super(beanPropertyWriter, jsonSerializer);
    }

    @Override // org.codehaus.jackson.map.ser.BeanPropertyWriter
    public BeanPropertyWriter withSerializer(JsonSerializer<Object> jsonSerializer) {
        if (getClass() != UnwrappingBeanPropertyWriter.class) {
            throw new IllegalStateException("UnwrappingBeanPropertyWriter sub-class does not override 'withSerializer()'; needs to!");
        }
        if (!jsonSerializer.isUnwrappingSerializer()) {
            jsonSerializer = jsonSerializer.unwrappingSerializer();
        }
        return new UnwrappingBeanPropertyWriter(this, jsonSerializer);
    }

    @Override // org.codehaus.jackson.map.ser.BeanPropertyWriter
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2 = get(obj);
        if (obj2 == null) {
            return;
        }
        if (obj2 == obj) {
            _reportSelfReference(obj);
        }
        if (this._suppressableValue == null || !this._suppressableValue.equals(obj2)) {
            JsonSerializer<Object> jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class<?> cls = obj2.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                jsonSerializer = serializerFor == null ? _findAndAddDynamic(propertySerializerMap, cls, serializerProvider) : serializerFor;
            }
            if (!jsonSerializer.isUnwrappingSerializer()) {
                jsonGenerator.writeFieldName(this._name);
            }
            if (this._typeSerializer == null) {
                jsonSerializer.serialize(obj2, jsonGenerator, serializerProvider);
            } else {
                jsonSerializer.serializeWithType(obj2, jsonGenerator, serializerProvider, this._typeSerializer);
            }
        }
    }

    @Override // org.codehaus.jackson.map.ser.BeanPropertyWriter
    public JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        JsonSerializer<Object> findValueSerializer;
        if (this._nonTrivialBaseType != null) {
            findValueSerializer = serializerProvider.findValueSerializer(serializerProvider.constructSpecializedType(this._nonTrivialBaseType, cls), this);
        } else {
            findValueSerializer = serializerProvider.findValueSerializer(cls, this);
        }
        if (!findValueSerializer.isUnwrappingSerializer()) {
            findValueSerializer = findValueSerializer.unwrappingSerializer();
        }
        this._dynamicSerializers = this._dynamicSerializers.newWith(cls, findValueSerializer);
        return findValueSerializer;
    }
}
