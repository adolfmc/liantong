package org.codehaus.jackson.map.ser;

import java.util.List;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class BeanSerializerModifier {
    public List<BeanPropertyWriter> changeProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        return list;
    }

    public JsonSerializer<?> modifySerializer(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, JsonSerializer<?> jsonSerializer) {
        return jsonSerializer;
    }

    public List<BeanPropertyWriter> orderProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        return list;
    }

    public BeanSerializerBuilder updateBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanSerializerBuilder beanSerializerBuilder) {
        return beanSerializerBuilder;
    }
}
