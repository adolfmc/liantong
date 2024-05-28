package org.codehaus.jackson.map.jsontype;

import java.util.Collection;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface TypeResolverBuilder<T extends TypeResolverBuilder<T>> {
    TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty);

    TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty);

    T defaultImpl(Class<?> cls);

    Class<?> getDefaultImpl();

    T inclusion(JsonTypeInfo.EnumC13395As enumC13395As);

    T init(JsonTypeInfo.EnumC13396Id enumC13396Id, TypeIdResolver typeIdResolver);

    T typeProperty(String str);
}
