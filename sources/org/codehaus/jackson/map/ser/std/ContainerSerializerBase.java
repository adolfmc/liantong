package org.codehaus.jackson.map.ser.std;

import org.codehaus.jackson.map.TypeSerializer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ContainerSerializerBase<T> extends SerializerBase<T> {
    public abstract ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer);

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializerBase(Class<T> cls) {
        super(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializerBase(Class<?> cls, boolean z) {
        super(cls, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContainerSerializerBase<?> withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : _withValueTypeSerializer(typeSerializer);
    }
}
