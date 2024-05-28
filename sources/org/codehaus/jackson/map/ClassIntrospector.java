package org.codehaus.jackson.map;

import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ClassIntrospector<T extends BeanDescription> {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface MixInResolver {
        Class<?> findMixInClassFor(Class<?> cls);
    }

    public abstract T forClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract T forCreation(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract T forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract T forDirectClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract T forSerialization(SerializationConfig serializationConfig, JavaType javaType, MixInResolver mixInResolver);

    @Deprecated
    public T forClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, MixInResolver mixInResolver) {
        return forClassAnnotations(mapperConfig, mapperConfig.constructType(cls), mixInResolver);
    }

    @Deprecated
    public T forDirectClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, MixInResolver mixInResolver) {
        return forDirectClassAnnotations(mapperConfig, mapperConfig.constructType(cls), mixInResolver);
    }
}
