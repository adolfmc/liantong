package org.codehaus.jackson.map.ser.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.BeanSerializerBase;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class UnwrappingBeanSerializer extends BeanSerializerBase {
    @Override // org.codehaus.jackson.map.JsonSerializer
    public boolean isUnwrappingSerializer() {
        return true;
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public JsonSerializer<Object> unwrappingSerializer() {
        return this;
    }

    public UnwrappingBeanSerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
    }

    @Override // org.codehaus.jackson.map.ser.std.BeanSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
    }

    public String toString() {
        return "UnwrappingBeanSerializer for " + handledType().getName();
    }
}
