package org.codehaus.jackson.map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ContextualSerializer<T> {
    JsonSerializer<T> createContextual(SerializationConfig serializationConfig, BeanProperty beanProperty) throws JsonMappingException;
}
