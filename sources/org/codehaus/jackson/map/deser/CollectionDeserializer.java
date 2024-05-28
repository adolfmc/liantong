package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.Collection;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CollectionDeserializer extends org.codehaus.jackson.map.deser.std.CollectionDeserializer {
    @Deprecated
    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, Constructor<Collection<Object>> constructor) {
        super(javaType, jsonDeserializer, typeDeserializer, constructor);
    }

    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator) {
        super(javaType, jsonDeserializer, typeDeserializer, valueInstantiator);
    }

    protected CollectionDeserializer(CollectionDeserializer collectionDeserializer) {
        super(collectionDeserializer);
    }
}
