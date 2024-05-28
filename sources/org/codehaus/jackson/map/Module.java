package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.map.deser.ValueInstantiators;
import org.codehaus.jackson.map.ser.BeanSerializerModifier;
import org.codehaus.jackson.map.type.TypeModifier;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Module implements Versioned {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface SetupContext {
        void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver);

        void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier);

        void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier);

        void addDeserializers(Deserializers deserializers);

        void addKeyDeserializers(KeyDeserializers keyDeserializers);

        void addKeySerializers(Serializers serializers);

        void addSerializers(Serializers serializers);

        void addTypeModifier(TypeModifier typeModifier);

        void addValueInstantiators(ValueInstantiators valueInstantiators);

        void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

        DeserializationConfig getDeserializationConfig();

        Version getMapperVersion();

        SerializationConfig getSerializationConfig();

        void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

        boolean isEnabled(JsonGenerator.Feature feature);

        boolean isEnabled(JsonParser.Feature feature);

        boolean isEnabled(DeserializationConfig.Feature feature);

        boolean isEnabled(SerializationConfig.Feature feature);

        void setMixInAnnotations(Class<?> cls, Class<?> cls2);
    }

    public abstract String getModuleName();

    public abstract void setupModule(SetupContext setupContext);

    @Override // org.codehaus.jackson.Versioned
    public abstract Version version();
}
