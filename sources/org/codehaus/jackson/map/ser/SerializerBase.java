package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class SerializerBase<T> extends org.codehaus.jackson.map.ser.std.SerializerBase<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public SerializerBase(Class<T> cls) {
        super(cls);
    }

    protected SerializerBase(JavaType javaType) {
        super(javaType);
    }

    protected SerializerBase(Class<?> cls, boolean z) {
        super(cls, z);
    }
}
