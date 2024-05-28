package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EnumSetSerializer extends AsArraySerializerBase<EnumSet<? extends Enum<?>>> {
    @Override // org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    public EnumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        super(EnumSet.class, javaType, true, null, beanProperty, null);
    }

    @Override // org.codehaus.jackson.map.ser.std.AsArraySerializerBase
    public void serializeContents(EnumSet<? extends Enum<?>> enumSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            Enum r1 = (Enum) it.next();
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider.findValueSerializer(r1.getDeclaringClass(), this._property);
            }
            jsonSerializer.serialize(r1, jsonGenerator, serializerProvider);
        }
    }
}
