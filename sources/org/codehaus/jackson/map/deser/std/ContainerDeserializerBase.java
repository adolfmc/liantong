package org.codehaus.jackson.map.deser.std;

import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ContainerDeserializerBase<T> extends StdDeserializer<T> {
    public abstract JsonDeserializer<Object> getContentDeserializer();

    public abstract JavaType getContentType();

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerDeserializerBase(Class<?> cls) {
        super(cls);
    }
}
