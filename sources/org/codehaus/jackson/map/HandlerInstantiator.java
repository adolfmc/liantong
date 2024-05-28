package org.codehaus.jackson.map;

import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class HandlerInstantiator {
    public abstract JsonDeserializer<?> deserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class<? extends JsonDeserializer<?>> cls);

    public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class<? extends KeyDeserializer> cls);

    public abstract JsonSerializer<?> serializerInstance(SerializationConfig serializationConfig, Annotated annotated, Class<? extends JsonSerializer<?>> cls);

    public abstract TypeIdResolver typeIdResolverInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<? extends TypeIdResolver> cls);

    public abstract TypeResolverBuilder<?> typeResolverBuilderInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<? extends TypeResolverBuilder<?>> cls);

    public ValueInstantiator valueInstantiatorInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<? extends ValueInstantiator> cls) {
        return null;
    }
}
