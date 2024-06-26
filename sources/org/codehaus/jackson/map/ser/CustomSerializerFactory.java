package org.codehaus.jackson.map.ser;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CustomSerializerFactory extends BeanSerializerFactory {
    protected HashMap<ClassKey, JsonSerializer<?>> _directClassMappings;
    protected JsonSerializer<?> _enumSerializerOverride;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings;
    protected HashMap<ClassKey, JsonSerializer<?>> _transitiveClassMappings;

    public CustomSerializerFactory() {
        this(null);
    }

    public CustomSerializerFactory(SerializerFactory.Config config) {
        super(config);
        this._directClassMappings = null;
        this._transitiveClassMappings = null;
        this._interfaceMappings = null;
    }

    @Override // org.codehaus.jackson.map.ser.BeanSerializerFactory, org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory withConfig(SerializerFactory.Config config) {
        if (getClass() != CustomSerializerFactory.class) {
            throw new IllegalStateException("Subtype of CustomSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with additional serializer definitions");
        }
        return new CustomSerializerFactory(config);
    }

    public <T> void addGenericMapping(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap<>();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
            return;
        }
        if (this._transitiveClassMappings == null) {
            this._transitiveClassMappings = new HashMap<>();
        }
        this._transitiveClassMappings.put(classKey, jsonSerializer);
    }

    public <T> void addSpecificMapping(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            throw new IllegalArgumentException("Can not add specific mapping for an interface (" + cls.getName() + ")");
        } else if (Modifier.isAbstract(cls.getModifiers())) {
            throw new IllegalArgumentException("Can not add specific mapping for an abstract class (" + cls.getName() + ")");
        } else {
            if (this._directClassMappings == null) {
                this._directClassMappings = new HashMap<>();
            }
            this._directClassMappings.put(classKey, jsonSerializer);
        }
    }

    public void setEnumSerializer(JsonSerializer<?> jsonSerializer) {
        this._enumSerializerOverride = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.ser.BeanSerializerFactory, org.codehaus.jackson.map.ser.BasicSerializerFactory, org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> findCustomSerializer = findCustomSerializer(javaType.getRawClass(), serializationConfig);
        return findCustomSerializer != null ? findCustomSerializer : super.createSerializer(serializationConfig, javaType, beanProperty);
    }

    protected JsonSerializer<?> findCustomSerializer(Class<?> cls, SerializationConfig serializationConfig) {
        JsonSerializer<?> jsonSerializer;
        JsonSerializer<?> jsonSerializer2;
        ClassKey classKey = new ClassKey(cls);
        HashMap<ClassKey, JsonSerializer<?>> hashMap = this._directClassMappings;
        if (hashMap == null || (jsonSerializer2 = hashMap.get(classKey)) == null) {
            if (!cls.isEnum() || (jsonSerializer = this._enumSerializerOverride) == null) {
                if (this._transitiveClassMappings != null) {
                    for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                        classKey.reset(cls2);
                        JsonSerializer<?> jsonSerializer3 = this._transitiveClassMappings.get(classKey);
                        if (jsonSerializer3 != null) {
                            return jsonSerializer3;
                        }
                    }
                }
                if (this._interfaceMappings != null) {
                    classKey.reset(cls);
                    JsonSerializer<?> jsonSerializer4 = this._interfaceMappings.get(classKey);
                    if (jsonSerializer4 != null) {
                        return jsonSerializer4;
                    }
                    while (cls != null) {
                        JsonSerializer<?> _findInterfaceMapping = _findInterfaceMapping(cls, classKey);
                        if (_findInterfaceMapping != null) {
                            return _findInterfaceMapping;
                        }
                        cls = cls.getSuperclass();
                    }
                    return null;
                }
                return null;
            }
            return jsonSerializer;
        }
        return jsonSerializer2;
    }

    protected JsonSerializer<?> _findInterfaceMapping(Class<?> cls, ClassKey classKey) {
        Class<?>[] interfaces;
        for (Class<?> cls2 : cls.getInterfaces()) {
            classKey.reset(cls2);
            JsonSerializer<?> jsonSerializer = this._interfaceMappings.get(classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
            JsonSerializer<?> _findInterfaceMapping = _findInterfaceMapping(cls2, classKey);
            if (_findInterfaceMapping != null) {
                return _findInterfaceMapping;
            }
        }
        return null;
    }
}
