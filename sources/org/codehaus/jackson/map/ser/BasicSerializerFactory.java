package org.codehaus.jackson.map.ser;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.TimeZone;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializable;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.ser.StdSerializers;
import org.codehaus.jackson.map.ser.std.CalendarSerializer;
import org.codehaus.jackson.map.ser.std.DateSerializer;
import org.codehaus.jackson.map.ser.std.EnumMapSerializer;
import org.codehaus.jackson.map.ser.std.IndexedStringListSerializer;
import org.codehaus.jackson.map.ser.std.InetAddressSerializer;
import org.codehaus.jackson.map.ser.std.JsonValueSerializer;
import org.codehaus.jackson.map.ser.std.NullSerializer;
import org.codehaus.jackson.map.ser.std.ObjectArraySerializer;
import org.codehaus.jackson.map.ser.std.SerializableSerializer;
import org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer;
import org.codehaus.jackson.map.ser.std.StdArraySerializers;
import org.codehaus.jackson.map.ser.std.StdContainerSerializers;
import org.codehaus.jackson.map.ser.std.StdJdkSerializers;
import org.codehaus.jackson.map.ser.std.StringCollectionSerializer;
import org.codehaus.jackson.map.ser.std.StringSerializer;
import org.codehaus.jackson.map.ser.std.TimeZoneSerializer;
import org.codehaus.jackson.map.ser.std.TokenBufferSerializer;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class BasicSerializerFactory extends SerializerFactory {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers;
    protected static final HashMap<String, JsonSerializer<?>> _concrete = new HashMap<>();
    protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy = new HashMap<>();
    protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;

    @Override // org.codehaus.jackson.map.SerializerFactory
    public abstract JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    protected abstract Iterable<Serializers> customSerializers();

    static {
        _concrete.put(String.class.getName(), new StringSerializer());
        org.codehaus.jackson.map.ser.std.ToStringSerializer toStringSerializer = org.codehaus.jackson.map.ser.std.ToStringSerializer.instance;
        _concrete.put(StringBuffer.class.getName(), toStringSerializer);
        _concrete.put(StringBuilder.class.getName(), toStringSerializer);
        _concrete.put(Character.class.getName(), toStringSerializer);
        _concrete.put(Character.TYPE.getName(), toStringSerializer);
        _concrete.put(Boolean.TYPE.getName(), new StdSerializers.BooleanSerializer(true));
        _concrete.put(Boolean.class.getName(), new StdSerializers.BooleanSerializer(false));
        StdSerializers.IntegerSerializer integerSerializer = new StdSerializers.IntegerSerializer();
        _concrete.put(Integer.class.getName(), integerSerializer);
        _concrete.put(Integer.TYPE.getName(), integerSerializer);
        _concrete.put(Long.class.getName(), StdSerializers.LongSerializer.instance);
        _concrete.put(Long.TYPE.getName(), StdSerializers.LongSerializer.instance);
        _concrete.put(Byte.class.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Byte.TYPE.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Short.class.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Short.TYPE.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Float.class.getName(), StdSerializers.FloatSerializer.instance);
        _concrete.put(Float.TYPE.getName(), StdSerializers.FloatSerializer.instance);
        _concrete.put(Double.class.getName(), StdSerializers.DoubleSerializer.instance);
        _concrete.put(Double.TYPE.getName(), StdSerializers.DoubleSerializer.instance);
        StdSerializers.NumberSerializer numberSerializer = new StdSerializers.NumberSerializer();
        _concrete.put(BigInteger.class.getName(), numberSerializer);
        _concrete.put(BigDecimal.class.getName(), numberSerializer);
        _concrete.put(Calendar.class.getName(), CalendarSerializer.instance);
        DateSerializer dateSerializer = DateSerializer.instance;
        _concrete.put(Date.class.getName(), dateSerializer);
        _concrete.put(Timestamp.class.getName(), dateSerializer);
        _concrete.put(java.sql.Date.class.getName(), new StdSerializers.SqlDateSerializer());
        _concrete.put(Time.class.getName(), new StdSerializers.SqlTimeSerializer());
        for (Map.Entry<Class<?>, Object> entry : new StdJdkSerializers().provide()) {
            Object value = entry.getValue();
            if (value instanceof JsonSerializer) {
                _concrete.put(entry.getKey().getName(), (JsonSerializer) value);
            } else if (value instanceof Class) {
                _concreteLazy.put(entry.getKey().getName(), (Class) value);
            } else {
                throw new IllegalStateException("Internal error: unrecognized value of type " + entry.getClass().getName());
            }
        }
        _concreteLazy.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
        _arraySerializers = new HashMap<>();
        _arraySerializers.put(boolean[].class.getName(), new StdArraySerializers.BooleanArraySerializer());
        _arraySerializers.put(byte[].class.getName(), new StdArraySerializers.ByteArraySerializer());
        _arraySerializers.put(char[].class.getName(), new StdArraySerializers.CharArraySerializer());
        _arraySerializers.put(short[].class.getName(), new StdArraySerializers.ShortArraySerializer());
        _arraySerializers.put(int[].class.getName(), new StdArraySerializers.IntArraySerializer());
        _arraySerializers.put(long[].class.getName(), new StdArraySerializers.LongArraySerializer());
        _arraySerializers.put(float[].class.getName(), new StdArraySerializers.FloatArraySerializer());
        _arraySerializers.put(double[].class.getName(), new StdArraySerializers.DoubleArraySerializer());
    }

    @Override // org.codehaus.jackson.map.SerializerFactory
    public TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        Collection<NamedType> collectAndResolveSubtypes;
        AnnotatedClass classInfo = ((BasicBeanDescription) serializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findTypeResolver = annotationIntrospector.findTypeResolver(serializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            findTypeResolver = serializationConfig.getDefaultTyper(javaType);
            collectAndResolveSubtypes = null;
        } else {
            collectAndResolveSubtypes = serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, serializationConfig, annotationIntrospector);
        }
        if (findTypeResolver == null) {
            return null;
        }
        return findTypeResolver.buildTypeSerializer(serializationConfig, javaType, collectAndResolveSubtypes, beanProperty);
    }

    public final JsonSerializer<?> getNullSerializer() {
        return NullSerializer.instance;
    }

    public final JsonSerializer<?> findSerializerByLookup(JavaType javaType, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        String name = javaType.getRawClass().getName();
        JsonSerializer<?> jsonSerializer = _concrete.get(name);
        if (jsonSerializer != null) {
            return jsonSerializer;
        }
        Class<? extends JsonSerializer<?>> cls = _concreteLazy.get(name);
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (Exception e) {
                throw new IllegalStateException("Failed to instantiate standard serializer (of type " + cls.getName() + "): " + e.getMessage(), e);
            }
        }
        return null;
    }

    public final JsonSerializer<?> findSerializerByPrimaryType(JavaType javaType, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (JsonSerializable.class.isAssignableFrom(rawClass)) {
            if (JsonSerializableWithType.class.isAssignableFrom(rawClass)) {
                return SerializableWithTypeSerializer.instance;
            }
            return SerializableSerializer.instance;
        }
        AnnotatedMethod findJsonValueMethod = basicBeanDescription.findJsonValueMethod();
        if (findJsonValueMethod != null) {
            Method annotated = findJsonValueMethod.getAnnotated();
            if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                ClassUtil.checkAndFixAccess(annotated);
            }
            return new JsonValueSerializer(annotated, findSerializerFromAnnotation(serializationConfig, findJsonValueMethod, beanProperty), beanProperty);
        } else if (InetAddress.class.isAssignableFrom(rawClass)) {
            return InetAddressSerializer.instance;
        } else {
            if (TimeZone.class.isAssignableFrom(rawClass)) {
                return TimeZoneSerializer.instance;
            }
            if (Charset.class.isAssignableFrom(rawClass)) {
                return org.codehaus.jackson.map.ser.std.ToStringSerializer.instance;
            }
            JsonSerializer<?> findSerializer = this.optionalHandlers.findSerializer(serializationConfig, javaType);
            if (findSerializer != null) {
                return findSerializer;
            }
            if (Number.class.isAssignableFrom(rawClass)) {
                return StdSerializers.NumberSerializer.instance;
            }
            if (Enum.class.isAssignableFrom(rawClass)) {
                return org.codehaus.jackson.map.ser.std.EnumSerializer.construct(rawClass, serializationConfig, basicBeanDescription);
            }
            if (Calendar.class.isAssignableFrom(rawClass)) {
                return CalendarSerializer.instance;
            }
            if (Date.class.isAssignableFrom(rawClass)) {
                return DateSerializer.instance;
            }
            return null;
        }
    }

    public final JsonSerializer<?> findSerializerByAddonType(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (Iterator.class.isAssignableFrom(rawClass)) {
            return buildIteratorSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty, z);
        }
        if (Iterable.class.isAssignableFrom(rawClass)) {
            return buildIterableSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty, z);
        }
        if (CharSequence.class.isAssignableFrom(rawClass)) {
            return org.codehaus.jackson.map.ser.std.ToStringSerializer.instance;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonSerializer<Object> findSerializerFromAnnotation(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) throws JsonMappingException {
        Object findSerializer = serializationConfig.getAnnotationIntrospector().findSerializer(annotated);
        if (findSerializer == null) {
            return null;
        }
        if (findSerializer instanceof JsonSerializer) {
            JsonSerializer<Object> jsonSerializer = (JsonSerializer) findSerializer;
            return jsonSerializer instanceof ContextualSerializer ? ((ContextualSerializer) jsonSerializer).createContextual(serializationConfig, beanProperty) : jsonSerializer;
        } else if (!(findSerializer instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned value of type " + findSerializer.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead");
        } else {
            Class<? extends JsonSerializer<?>> cls = (Class) findSerializer;
            if (!JsonSerializer.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonSerializer>");
            }
            JsonSerializer<Object> serializerInstance = serializationConfig.serializerInstance(annotated, cls);
            return serializerInstance instanceof ContextualSerializer ? ((ContextualSerializer) serializerInstance).createContextual(serializationConfig, beanProperty) : serializerInstance;
        }
    }

    public JsonSerializer<?> buildContainerSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        boolean usesStaticTyping;
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, javaType.getContentType(), beanProperty);
        if (createTypeSerializer != null) {
            usesStaticTyping = false;
        } else {
            usesStaticTyping = !z ? usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty) : z;
        }
        JsonSerializer<Object> findContentSerializer = findContentSerializer(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (javaType.isMapLikeType()) {
            MapLikeType mapLikeType = (MapLikeType) javaType;
            JsonSerializer<Object> findKeySerializer = findKeySerializer(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
            if (mapLikeType.isTrueMapType()) {
                return buildMapSerializer(serializationConfig, (MapType) mapLikeType, basicBeanDescription, beanProperty, usesStaticTyping, findKeySerializer, createTypeSerializer, findContentSerializer);
            }
            return buildMapLikeSerializer(serializationConfig, mapLikeType, basicBeanDescription, beanProperty, usesStaticTyping, findKeySerializer, createTypeSerializer, findContentSerializer);
        } else if (javaType.isCollectionLikeType()) {
            CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
            if (collectionLikeType.isTrueCollectionType()) {
                return buildCollectionSerializer(serializationConfig, (CollectionType) collectionLikeType, basicBeanDescription, beanProperty, usesStaticTyping, createTypeSerializer, findContentSerializer);
            }
            return buildCollectionLikeSerializer(serializationConfig, collectionLikeType, basicBeanDescription, beanProperty, usesStaticTyping, createTypeSerializer, findContentSerializer);
        } else if (javaType.isArrayType()) {
            return buildArraySerializer(serializationConfig, (ArrayType) javaType, basicBeanDescription, beanProperty, usesStaticTyping, createTypeSerializer, findContentSerializer);
        } else {
            return null;
        }
    }

    protected JsonSerializer<?> buildCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        for (Serializers serializers : customSerializers()) {
            JsonSerializer<?> findCollectionLikeSerializer = serializers.findCollectionLikeSerializer(serializationConfig, collectionLikeType, basicBeanDescription, beanProperty, typeSerializer, jsonSerializer);
            if (findCollectionLikeSerializer != null) {
                return findCollectionLikeSerializer;
            }
        }
        return null;
    }

    protected JsonSerializer<?> buildCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        for (Serializers serializers : customSerializers()) {
            JsonSerializer<?> findCollectionSerializer = serializers.findCollectionSerializer(serializationConfig, collectionType, basicBeanDescription, beanProperty, typeSerializer, jsonSerializer);
            if (findCollectionSerializer != null) {
                return findCollectionSerializer;
            }
        }
        Class<?> rawClass = collectionType.getRawClass();
        if (EnumSet.class.isAssignableFrom(rawClass)) {
            return buildEnumSetSerializer(serializationConfig, collectionType, basicBeanDescription, beanProperty, z, typeSerializer, jsonSerializer);
        }
        Class<?> rawClass2 = collectionType.getContentType().getRawClass();
        if (isIndexedList(rawClass)) {
            if (rawClass2 == String.class) {
                return new IndexedStringListSerializer(beanProperty, jsonSerializer);
            }
            return StdContainerSerializers.indexedListSerializer(collectionType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
        } else if (rawClass2 == String.class) {
            return new StringCollectionSerializer(beanProperty, jsonSerializer);
        } else {
            return StdContainerSerializers.collectionSerializer(collectionType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
        }
    }

    protected JsonSerializer<?> buildEnumSetSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        JavaType contentType = javaType.getContentType();
        if (!contentType.isEnumType()) {
            contentType = null;
        }
        return StdContainerSerializers.enumSetSerializer(contentType, beanProperty);
    }

    protected boolean isIndexedList(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    protected JsonSerializer<?> buildMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        for (Serializers serializers : customSerializers()) {
            JsonSerializer<?> findMapLikeSerializer = serializers.findMapLikeSerializer(serializationConfig, mapLikeType, basicBeanDescription, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
            if (findMapLikeSerializer != null) {
                return findMapLikeSerializer;
            }
        }
        return null;
    }

    protected JsonSerializer<?> buildMapSerializer(SerializationConfig serializationConfig, MapType mapType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        for (Serializers serializers : customSerializers()) {
            JsonSerializer<?> findMapSerializer = serializers.findMapSerializer(serializationConfig, mapType, basicBeanDescription, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
            if (findMapSerializer != null) {
                return findMapSerializer;
            }
        }
        if (EnumMap.class.isAssignableFrom(mapType.getRawClass())) {
            return buildEnumMapSerializer(serializationConfig, mapType, basicBeanDescription, beanProperty, z, typeSerializer, jsonSerializer2);
        }
        return org.codehaus.jackson.map.ser.std.MapSerializer.construct(serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo()), mapType, z, typeSerializer, beanProperty, jsonSerializer, jsonSerializer2);
    }

    protected JsonSerializer<?> buildEnumMapSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        JavaType keyType = javaType.getKeyType();
        return new EnumMapSerializer(javaType.getContentType(), z, keyType.isEnumType() ? EnumValues.construct(keyType.getRawClass(), serializationConfig.getAnnotationIntrospector()) : null, typeSerializer, beanProperty, jsonSerializer);
    }

    protected JsonSerializer<?> buildArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        Class<?> rawClass = arrayType.getRawClass();
        if (String[].class == rawClass) {
            return new StdArraySerializers.StringArraySerializer(beanProperty);
        }
        JsonSerializer<?> jsonSerializer2 = _arraySerializers.get(rawClass.getName());
        return jsonSerializer2 != null ? jsonSerializer2 : new ObjectArraySerializer(arrayType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
    }

    protected JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, containedType, beanProperty);
        return StdContainerSerializers.iteratorSerializer(containedType, usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty), createTypeSerializer, beanProperty);
    }

    protected JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, containedType, beanProperty);
        return StdContainerSerializers.iterableSerializer(containedType, usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty), createTypeSerializer, beanProperty);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends JavaType> T modifyTypeByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        Class<?> findSerializationType = serializationConfig.getAnnotationIntrospector().findSerializationType(annotated);
        if (findSerializationType != null) {
            try {
                t = (T) t.widenBy(findSerializationType);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Failed to widen type " + t + " with concrete-type annotation (value " + findSerializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage());
            }
        }
        return (T) modifySecondaryTypesByAnnotation(serializationConfig, annotated, t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends JavaType> T modifySecondaryTypesByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        if (t.isContainerType()) {
            Class<?> findSerializationKeyType = annotationIntrospector.findSerializationKeyType(annotated, t.getKeyType());
            if (findSerializationKeyType != null) {
                if (!(t instanceof MapType)) {
                    throw new IllegalArgumentException("Illegal key-type annotation: type " + t + " is not a Map type");
                }
                try {
                    t = (T) ((MapType) t).widenKey(findSerializationKeyType);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Failed to narrow key type " + t + " with key-type annotation (" + findSerializationKeyType.getName() + "): " + e.getMessage());
                }
            }
            Class<?> findSerializationContentType = annotationIntrospector.findSerializationContentType(annotated, t.getContentType());
            if (findSerializationContentType != null) {
                try {
                    return (T) t.widenContentsBy(findSerializationContentType);
                } catch (IllegalArgumentException e2) {
                    throw new IllegalArgumentException("Failed to narrow content type " + t + " with content-type annotation (" + findSerializationContentType.getName() + "): " + e2.getMessage());
                }
            }
            return t;
        }
        return t;
    }

    protected static JsonSerializer<Object> findKeySerializer(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class<? extends JsonSerializer<?>> findKeySerializer = annotationIntrospector.findKeySerializer(annotated);
        if ((findKeySerializer == null || findKeySerializer == JsonSerializer.None.class) && beanProperty != null) {
            findKeySerializer = annotationIntrospector.findKeySerializer(beanProperty.getMember());
        }
        if (findKeySerializer == null || findKeySerializer == JsonSerializer.None.class) {
            return null;
        }
        return serializationConfig.serializerInstance(annotated, findKeySerializer);
    }

    protected static JsonSerializer<Object> findContentSerializer(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class<? extends JsonSerializer<?>> findContentSerializer = annotationIntrospector.findContentSerializer(annotated);
        if ((findContentSerializer == null || findContentSerializer == JsonSerializer.None.class) && beanProperty != null) {
            findContentSerializer = annotationIntrospector.findContentSerializer(beanProperty.getMember());
        }
        if (findContentSerializer == null || findContentSerializer == JsonSerializer.None.class) {
            return null;
        }
        return serializationConfig.serializerInstance(annotated, findContentSerializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean usesStaticTyping(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        if (typeSerializer != null) {
            return false;
        }
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        JsonSerialize.Typing findSerializationTyping = annotationIntrospector.findSerializationTyping(basicBeanDescription.getClassInfo());
        if (findSerializationTyping != null) {
            if (findSerializationTyping == JsonSerialize.Typing.STATIC) {
                return true;
            }
        } else if (serializationConfig.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING)) {
            return true;
        }
        if (beanProperty != null) {
            JavaType type = beanProperty.getType();
            if (type.isContainerType()) {
                if (annotationIntrospector.findSerializationContentType(beanProperty.getMember(), beanProperty.getType()) != null) {
                    return true;
                }
                if ((type instanceof MapType) && annotationIntrospector.findSerializationKeyType(beanProperty.getMember(), beanProperty.getType()) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
