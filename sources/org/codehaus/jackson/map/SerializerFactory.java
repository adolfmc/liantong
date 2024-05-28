package org.codehaus.jackson.map;

import org.codehaus.jackson.map.ser.BeanSerializerModifier;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class SerializerFactory {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class Config {
        public abstract boolean hasKeySerializers();

        public abstract boolean hasSerializerModifiers();

        public abstract boolean hasSerializers();

        public abstract Iterable<Serializers> keySerializers();

        public abstract Iterable<BeanSerializerModifier> serializerModifiers();

        public abstract Iterable<Serializers> serializers();

        public abstract Config withAdditionalKeySerializers(Serializers serializers);

        public abstract Config withAdditionalSerializers(Serializers serializers);

        public abstract Config withSerializerModifier(BeanSerializerModifier beanSerializerModifier);
    }

    public abstract JsonSerializer<Object> createKeySerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract Config getConfig();

    public abstract SerializerFactory withConfig(Config config);

    public final SerializerFactory withAdditionalSerializers(Serializers serializers) {
        return withConfig(getConfig().withAdditionalSerializers(serializers));
    }

    public final SerializerFactory withAdditionalKeySerializers(Serializers serializers) {
        return withConfig(getConfig().withAdditionalKeySerializers(serializers));
    }

    public final SerializerFactory withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
        return withConfig(getConfig().withSerializerModifier(beanSerializerModifier));
    }

    @Deprecated
    public final JsonSerializer<Object> createSerializer(JavaType javaType, SerializationConfig serializationConfig) {
        try {
            return createSerializer(serializationConfig, javaType, null);
        } catch (JsonMappingException e) {
            throw new RuntimeJsonMappingException(e);
        }
    }

    @Deprecated
    public final TypeSerializer createTypeSerializer(JavaType javaType, SerializationConfig serializationConfig) {
        try {
            return createTypeSerializer(serializationConfig, javaType, null);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }
}
