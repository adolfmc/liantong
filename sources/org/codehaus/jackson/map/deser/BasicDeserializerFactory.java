package org.codehaus.jackson.map.deser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualDeserializer;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerFactory;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.std.AtomicReferenceDeserializer;
import org.codehaus.jackson.map.deser.std.EnumMapDeserializer;
import org.codehaus.jackson.map.deser.std.EnumSetDeserializer;
import org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import org.codehaus.jackson.map.deser.std.PrimitiveArrayDeserializers;
import org.codehaus.jackson.map.deser.std.StringCollectionDeserializer;
import org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class BasicDeserializerFactory extends DeserializerFactory {
    protected static final HashMap<JavaType, JsonDeserializer<Object>> _arrayDeserializers;
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;
    static final HashMap<ClassKey, JsonDeserializer<Object>> _simpleDeserializers = StdDeserializers.constructAll();
    static final HashMap<JavaType, KeyDeserializer> _keyDeserializers = org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructAll();
    static final HashMap<String, Class<? extends Map>> _mapFallbacks = new HashMap<>();

    protected abstract JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;

    protected abstract JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty) throws JsonMappingException;

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public abstract ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) throws JsonMappingException;

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public abstract JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException;

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public abstract DeserializerFactory withConfig(DeserializerFactory.Config config);

    /* JADX WARN: Multi-variable type inference failed */
    static {
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
        try {
            _mapFallbacks.put(Class.forName("java.util.ConcurrentNavigableMap").getName(), Class.forName("java.util.ConcurrentSkipListMap"));
        } catch (ClassNotFoundException unused) {
        }
        _collectionFallbacks = new HashMap<>();
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
        _arrayDeserializers = PrimitiveArrayDeserializers.getAll();
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createArrayDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, ArrayType arrayType, BeanProperty beanProperty) throws JsonMappingException {
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        if (jsonDeserializer == null) {
            JsonDeserializer<?> jsonDeserializer2 = _arrayDeserializers.get(contentType);
            if (jsonDeserializer2 != null) {
                JsonDeserializer<?> _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, null, null);
                return _findCustomArrayDeserializer != null ? _findCustomArrayDeserializer : jsonDeserializer2;
            } else if (contentType.isPrimitive()) {
                throw new IllegalArgumentException("Internal error: primitive type (" + arrayType + ") passed, no array deserializer found");
            }
        }
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer<?> _findCustomArrayDeserializer2 = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, findTypeDeserializer, jsonDeserializer);
        if (_findCustomArrayDeserializer2 != null) {
            return _findCustomArrayDeserializer2;
        }
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
        }
        return new ObjectArrayDeserializer(arrayType, jsonDeserializer, findTypeDeserializer);
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createCollectionDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionType collectionType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer;
        BasicBeanDescription basicBeanDescription;
        CollectionType collectionType2 = (CollectionType) mapAbstractType(deserializationConfig, collectionType);
        Class<?> rawClass = collectionType2.getRawClass();
        BasicBeanDescription basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspectForCreation(collectionType2);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        CollectionType collectionType3 = (CollectionType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), collectionType2, null);
        JavaType contentType = collectionType3.getContentType();
        JsonDeserializer<Object> jsonDeserializer2 = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        TypeDeserializer typeDeserializer2 = findTypeDeserializer;
        JsonDeserializer<?> _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType3, deserializationConfig, deserializerProvider, basicBeanDescription2, beanProperty, findTypeDeserializer, jsonDeserializer2);
        if (_findCustomCollectionDeserializer != null) {
            return _findCustomCollectionDeserializer;
        }
        if (jsonDeserializer2 != null) {
            jsonDeserializer = jsonDeserializer2;
        } else if (EnumSet.class.isAssignableFrom(rawClass)) {
            return new EnumSetDeserializer(contentType.getRawClass(), createEnumDeserializer(deserializationConfig, deserializerProvider, contentType, beanProperty));
        } else {
            jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
        }
        if (collectionType3.isInterface() || collectionType3.isAbstract()) {
            Class<? extends Collection> cls = _collectionFallbacks.get(rawClass.getName());
            if (cls == null) {
                throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + collectionType3);
            }
            collectionType3 = (CollectionType) deserializationConfig.constructSpecializedType(collectionType3, cls);
            basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(collectionType3);
        } else {
            basicBeanDescription = basicBeanDescription2;
        }
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationConfig, basicBeanDescription);
        if (contentType.getRawClass() == String.class) {
            return new StringCollectionDeserializer(collectionType3, jsonDeserializer, findValueInstantiator);
        }
        return new org.codehaus.jackson.map.deser.std.CollectionDeserializer(collectionType3, jsonDeserializer, typeDeserializer2, findValueInstantiator);
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionLikeType collectionLikeType, BeanProperty beanProperty) throws JsonMappingException {
        CollectionLikeType collectionLikeType2 = (CollectionLikeType) mapAbstractType(deserializationConfig, collectionLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(collectionLikeType2.getRawClass());
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        CollectionLikeType collectionLikeType3 = (CollectionLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), collectionLikeType2, null);
        JavaType contentType = collectionLikeType3.getContentType();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomCollectionLikeDeserializer(collectionLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, jsonDeserializer);
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createMapDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapType mapType, BeanProperty beanProperty) throws JsonMappingException {
        BasicBeanDescription basicBeanDescription;
        MapType mapType2;
        MapType mapType3 = (MapType) mapAbstractType(deserializationConfig, mapType);
        BasicBeanDescription basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType3);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        MapType mapType4 = (MapType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), mapType3, null);
        JavaType keyType = mapType4.getKeyType();
        JavaType contentType = mapType4.getContentType();
        JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer findKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer<?> _findCustomMapDeserializer = _findCustomMapDeserializer(mapType4, deserializationConfig, deserializerProvider, basicBeanDescription2, beanProperty, findKeyDeserializer, findTypeDeserializer, jsonDeserializer);
        if (_findCustomMapDeserializer != null) {
            return _findCustomMapDeserializer;
        }
        JsonDeserializer<Object> findValueDeserializer = jsonDeserializer == null ? deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty) : jsonDeserializer;
        Class<?> rawClass = mapType4.getRawClass();
        if (EnumMap.class.isAssignableFrom(rawClass)) {
            Class<?> rawClass2 = keyType.getRawClass();
            if (rawClass2 == null || !rawClass2.isEnum()) {
                throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
            }
            return new EnumMapDeserializer(keyType.getRawClass(), createEnumDeserializer(deserializationConfig, deserializerProvider, keyType, beanProperty), findValueDeserializer);
        }
        if (mapType4.isInterface() || mapType4.isAbstract()) {
            Class<? extends Map> cls = _mapFallbacks.get(rawClass.getName());
            if (cls == null) {
                throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + mapType4);
            }
            MapType mapType5 = (MapType) deserializationConfig.constructSpecializedType(mapType4, cls);
            basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType5);
            mapType2 = mapType5;
        } else {
            basicBeanDescription = basicBeanDescription2;
            mapType2 = mapType4;
        }
        org.codehaus.jackson.map.deser.std.MapDeserializer mapDeserializer = new org.codehaus.jackson.map.deser.std.MapDeserializer(mapType2, findValueInstantiator(deserializationConfig, basicBeanDescription), findKeyDeserializer, findValueDeserializer, findTypeDeserializer);
        mapDeserializer.setIgnorableProperties(deserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo()));
        return mapDeserializer;
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapLikeType mapLikeType, BeanProperty beanProperty) throws JsonMappingException {
        MapLikeType mapLikeType2 = (MapLikeType) mapAbstractType(deserializationConfig, mapLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapLikeType2);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        MapLikeType mapLikeType3 = (MapLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), mapLikeType2, null);
        JavaType keyType = mapLikeType3.getKeyType();
        JavaType contentType = mapLikeType3.getContentType();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer findKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomMapLikeDeserializer(mapLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, findKeyDeserializer, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, jsonDeserializer);
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createEnumDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(javaType);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, deserializationConfig, basicBeanDescription, beanProperty);
        if (_findCustomEnumDeserializer != null) {
            return _findCustomEnumDeserializer;
        }
        for (AnnotatedMethod annotatedMethod : basicBeanDescription.getFactoryMethods()) {
            if (deserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() == 1 && annotatedMethod.getRawType().isAssignableFrom(rawClass)) {
                    return org.codehaus.jackson.map.deser.std.EnumDeserializer.deserializerForCreator(deserializationConfig, rawClass, annotatedMethod);
                }
                throw new IllegalArgumentException("Unsuitable method (" + annotatedMethod + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
            }
        }
        return new org.codehaus.jackson.map.deser.std.EnumDeserializer(constructEnumResolver(rawClass, deserializationConfig));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanProperty);
        return _findCustomTreeNodeDeserializer != null ? _findCustomTreeNodeDeserializer : org.codehaus.jackson.map.deser.std.JsonNodeDeserializer.getDeserializer(rawClass);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<Object> findStdBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JavaType unknownType;
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<Object> jsonDeserializer = _simpleDeserializers.get(new ClassKey(rawClass));
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        if (AtomicReference.class.isAssignableFrom(rawClass)) {
            JavaType[] findTypeParameters = deserializationConfig.getTypeFactory().findTypeParameters(javaType, AtomicReference.class);
            if (findTypeParameters == null || findTypeParameters.length < 1) {
                unknownType = TypeFactory.unknownType();
            } else {
                unknownType = findTypeParameters[0];
            }
            return new AtomicReferenceDeserializer(unknownType, beanProperty);
        }
        JsonDeserializer<?> findDeserializer = this.optionalHandlers.findDeserializer(javaType, deserializationConfig, deserializerProvider);
        if (findDeserializer != null) {
            return findDeserializer;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.codehaus.jackson.map.DeserializerFactory
    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedClass classInfo = ((BasicBeanDescription) deserializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findTypeResolver = annotationIntrospector.findTypeResolver(deserializationConfig, classInfo, javaType);
        Collection<NamedType> collection = null;
        if (findTypeResolver == null) {
            findTypeResolver = deserializationConfig.getDefaultTyper(javaType);
            if (findTypeResolver == null) {
                return null;
            }
        } else {
            collection = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, deserializationConfig, annotationIntrospector);
        }
        Class<?> defaultImpl = findTypeResolver.getDefaultImpl();
        TypeResolverBuilder<?> typeResolverBuilder = findTypeResolver;
        if (defaultImpl == null) {
            typeResolverBuilder = findTypeResolver;
            if (javaType.isAbstract()) {
                JavaType mapAbstractType = mapAbstractType(deserializationConfig, javaType);
                typeResolverBuilder = findTypeResolver;
                if (mapAbstractType != null) {
                    typeResolverBuilder = findTypeResolver;
                    if (mapAbstractType.getRawClass() != javaType.getRawClass()) {
                        typeResolverBuilder = findTypeResolver.defaultImpl(mapAbstractType.getRawClass());
                    }
                }
            }
        }
        return typeResolverBuilder.buildTypeDeserializer(deserializationConfig, javaType, collection, beanProperty);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, javaType, beanProperty);
        }
        return findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        if (findPropertyContentTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, contentType, beanProperty);
        }
        return findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector), beanProperty);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty) throws JsonMappingException {
        Object findDeserializer = deserializationConfig.getAnnotationIntrospector().findDeserializer(annotated);
        if (findDeserializer != null) {
            return _constructDeserializer(deserializationConfig, annotated, beanProperty, findDeserializer);
        }
        return null;
    }

    JsonDeserializer<Object> _constructDeserializer(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty, Object obj) throws JsonMappingException {
        if (obj instanceof JsonDeserializer) {
            JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) obj;
            return jsonDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationConfig, beanProperty) : jsonDeserializer;
        } else if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + obj.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead");
        } else {
            Class<? extends JsonDeserializer<?>> cls = (Class) obj;
            if (!JsonDeserializer.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonDeserializer>");
            }
            JsonDeserializer<Object> deserializerInstance = deserializationConfig.deserializerInstance(annotated, cls);
            return deserializerInstance instanceof ContextualDeserializer ? ((ContextualDeserializer) deserializerInstance).createContextual(deserializationConfig, beanProperty) : deserializerInstance;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v28 */
    public <T extends JavaType> T modifyTypeByAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, T t, String str) throws JsonMappingException {
        Class<? extends KeyDeserializer> findKeyDeserializer;
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class<?> findDeserializationType = annotationIntrospector.findDeserializationType(annotated, t, str);
        if (findDeserializationType != null) {
            try {
                t = (T) t.narrowBy(findDeserializationType);
            } catch (IllegalArgumentException e) {
                throw new JsonMappingException("Failed to narrow type " + t + " with concrete-type annotation (value " + findDeserializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage(), null, e);
            }
        }
        boolean isContainerType = t.isContainerType();
        JavaType javaType = t;
        if (isContainerType) {
            Class<?> findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, t.getKeyType(), str);
            T t2 = t;
            if (findDeserializationKeyType != null) {
                if (!(t instanceof MapLikeType)) {
                    throw new JsonMappingException("Illegal key-type annotation: type " + t + " is not a Map(-like) type");
                }
                try {
                    t2 = (T) t.narrowKey(findDeserializationKeyType);
                } catch (IllegalArgumentException e2) {
                    throw new JsonMappingException("Failed to narrow key type " + t + " with key-type annotation (" + findDeserializationKeyType.getName() + "): " + e2.getMessage(), null, e2);
                }
            }
            JavaType keyType = t2.getKeyType();
            if (keyType != null && keyType.getValueHandler() == null && (findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated)) != null && findKeyDeserializer != KeyDeserializer.None.class) {
                keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotated, findKeyDeserializer));
            }
            Class<?> findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated, t2.getContentType(), str);
            T t3 = t2;
            if (findDeserializationContentType != null) {
                try {
                    t3 = t2.narrowContentsBy(findDeserializationContentType);
                } catch (IllegalArgumentException e3) {
                    throw new JsonMappingException("Failed to narrow content type " + t2 + " with content-type annotation (" + findDeserializationContentType.getName() + "): " + e3.getMessage(), null, e3);
                }
            }
            Object valueHandler = t3.getContentType().getValueHandler();
            javaType = t3;
            if (valueHandler == null) {
                Class<? extends JsonDeserializer<?>> findContentDeserializer = annotationIntrospector.findContentDeserializer(annotated);
                javaType = t3;
                if (findContentDeserializer != null) {
                    javaType = t3;
                    if (findContentDeserializer != JsonDeserializer.None.class) {
                        t3.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotated, findContentDeserializer));
                        javaType = t3;
                    }
                }
            }
        }
        return (T) javaType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaType resolveType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        TypeDeserializer findTypeDeserializer;
        TypeDeserializer findPropertyContentTypeDeserializer;
        Class<? extends KeyDeserializer> findKeyDeserializer;
        if (javaType.isContainerType()) {
            AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
            JavaType keyType = javaType.getKeyType();
            if (keyType != null && (findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotatedMember)) != null && findKeyDeserializer != KeyDeserializer.None.class) {
                keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotatedMember, findKeyDeserializer));
            }
            Class<? extends JsonDeserializer<?>> findContentDeserializer = annotationIntrospector.findContentDeserializer(annotatedMember);
            if (findContentDeserializer != null && findContentDeserializer != JsonDeserializer.None.class) {
                javaType.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotatedMember, findContentDeserializer));
            }
            if ((annotatedMember instanceof AnnotatedMember) && (findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty)) != null) {
                javaType = javaType.withContentTypeHandler(findPropertyContentTypeDeserializer);
            }
        }
        if (annotatedMember instanceof AnnotatedMember) {
            findTypeDeserializer = findPropertyTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty);
        } else {
            findTypeDeserializer = findTypeDeserializer(deserializationConfig, javaType, null);
        }
        return findTypeDeserializer != null ? javaType.withTypeHandler(findTypeDeserializer) : javaType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public org.codehaus.jackson.map.util.EnumResolver<?> constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING)) {
            return org.codehaus.jackson.map.util.EnumResolver.constructUnsafeUsingToString(cls);
        }
        return org.codehaus.jackson.map.util.EnumResolver.constructUnsafe(cls, deserializationConfig.getAnnotationIntrospector());
    }
}
