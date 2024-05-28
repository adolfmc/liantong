package org.codehaus.jackson.map.ser;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.impl.UnwrappingBeanSerializer;
import org.codehaus.jackson.map.ser.std.BeanSerializerBase;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BeanSerializer extends BeanSerializerBase {
    public BeanSerializer(JavaType javaType, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(javaType, beanPropertyWriterArr, beanPropertyWriterArr2, anyGetterWriter, obj);
    }

    public BeanSerializer(Class<?> cls, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(cls, beanPropertyWriterArr, beanPropertyWriterArr2, anyGetterWriter, obj);
    }

    protected BeanSerializer(BeanSerializer beanSerializer) {
        super(beanSerializer);
    }

    protected BeanSerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
    }

    public static BeanSerializer createDummy(Class<?> cls) {
        return new BeanSerializer(cls, NO_PROPS, (BeanPropertyWriter[]) null, (AnyGetterWriter) null, (Object) null);
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public JsonSerializer<Object> unwrappingSerializer() {
        return new UnwrappingBeanSerializer(this);
    }

    @Override // org.codehaus.jackson.map.ser.std.BeanSerializerBase, org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public String toString() {
        return "BeanSerializer for " + handledType().getName();
    }
}
