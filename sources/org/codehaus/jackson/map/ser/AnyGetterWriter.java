package org.codehaus.jackson.map.ser;

import java.lang.reflect.Method;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AnyGetterWriter {
    protected final Method _anyGetter;
    protected final org.codehaus.jackson.map.ser.std.MapSerializer _serializer;

    public AnyGetterWriter(AnnotatedMethod annotatedMethod, org.codehaus.jackson.map.ser.std.MapSerializer mapSerializer) {
        this._anyGetter = annotatedMethod.getAnnotated();
        this._serializer = mapSerializer;
    }

    public void getAndSerialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object invoke = this._anyGetter.invoke(obj, new Object[0]);
        if (invoke == null) {
            return;
        }
        if (!(invoke instanceof Map)) {
            throw new JsonMappingException("Value returned by 'any-getter' (" + this._anyGetter.getName() + "()) not java.util.Map but " + invoke.getClass().getName());
        }
        this._serializer.serializeFields((Map) invoke, jsonGenerator, serializerProvider);
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        this._serializer.resolve(serializerProvider);
    }
}
