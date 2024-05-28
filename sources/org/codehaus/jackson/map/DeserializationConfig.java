package org.codehaus.jackson.map;

import java.text.DateFormat;
import java.util.HashMap;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.LinkedNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DeserializationConfig extends MapperConfig.Impl<Feature, DeserializationConfig> {
    protected final JsonNodeFactory _nodeFactory;
    protected LinkedNode<DeserializationProblemHandler> _problemHandlers;
    protected boolean _sortPropertiesAlphabetically;

    @Override // org.codehaus.jackson.map.MapperConfig.Impl, org.codehaus.jackson.map.MapperConfig
    public /* bridge */ /* synthetic */ boolean isEnabled(MapperConfig.ConfigFeature configFeature) {
        return super.isEnabled(configFeature);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public /* bridge */ /* synthetic */ MapperConfig withClassIntrospector(ClassIntrospector classIntrospector) {
        return withClassIntrospector((ClassIntrospector<? extends BeanDescription>) classIntrospector);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public /* bridge */ /* synthetic */ MapperConfig withTypeResolverBuilder(TypeResolverBuilder typeResolverBuilder) {
        return withTypeResolverBuilder((TypeResolverBuilder<?>) typeResolverBuilder);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public /* bridge */ /* synthetic */ MapperConfig withVisibilityChecker(VisibilityChecker visibilityChecker) {
        return withVisibilityChecker((VisibilityChecker<?>) visibilityChecker);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum Feature implements MapperConfig.ConfigFeature {
        USE_ANNOTATIONS(true),
        AUTO_DETECT_SETTERS(true),
        AUTO_DETECT_CREATORS(true),
        AUTO_DETECT_FIELDS(true),
        USE_GETTERS_AS_SETTERS(true),
        CAN_OVERRIDE_ACCESS_MODIFIERS(true),
        USE_BIG_DECIMAL_FOR_FLOATS(false),
        USE_BIG_INTEGER_FOR_INTS(false),
        USE_JAVA_ARRAY_FOR_JSON_ARRAY(false),
        READ_ENUMS_USING_TO_STRING(false),
        FAIL_ON_UNKNOWN_PROPERTIES(true),
        FAIL_ON_NULL_FOR_PRIMITIVES(false),
        FAIL_ON_NUMBERS_FOR_ENUMS(false),
        WRAP_EXCEPTIONS(true),
        ACCEPT_SINGLE_VALUE_AS_ARRAY(false),
        UNWRAP_ROOT_VALUE(false),
        ACCEPT_EMPTY_STRING_AS_NULL_OBJECT(false);
        
        final boolean _defaultState;

        Feature(boolean z) {
            this._defaultState = z;
        }

        @Override // org.codehaus.jackson.map.MapperConfig.ConfigFeature
        public boolean enabledByDefault() {
            return this._defaultState;
        }

        @Override // org.codehaus.jackson.map.MapperConfig.ConfigFeature
        public int getMask() {
            return 1 << ordinal();
        }
    }

    public DeserializationConfig(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator) {
        super(classIntrospector, annotationIntrospector, visibilityChecker, subtypeResolver, propertyNamingStrategy, typeFactory, handlerInstantiator, collectFeatureDefaults(Feature.class));
        this._nodeFactory = JsonNodeFactory.instance;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig) {
        this(deserializationConfig, deserializationConfig._base);
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, HashMap<ClassKey, Class<?>> hashMap, SubtypeResolver subtypeResolver) {
        this(deserializationConfig, deserializationConfig._base);
        this._mixInAnnotations = hashMap;
        this._subtypeResolver = subtypeResolver;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, MapperConfig.Base base) {
        super(deserializationConfig, base, deserializationConfig._subtypeResolver);
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._sortPropertiesAlphabetically = deserializationConfig._sortPropertiesAlphabetically;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, JsonNodeFactory jsonNodeFactory) {
        super(deserializationConfig);
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = jsonNodeFactory;
        this._sortPropertiesAlphabetically = deserializationConfig._sortPropertiesAlphabetically;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, int i) {
        super(deserializationConfig, i);
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._sortPropertiesAlphabetically = deserializationConfig._sortPropertiesAlphabetically;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DeserializationConfig passSerializationFeatures(int i) {
        this._sortPropertiesAlphabetically = (i & SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY.getMask()) != 0;
        return this;
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector) {
        return new DeserializationConfig(this, this._base.withClassIntrospector(classIntrospector));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new DeserializationConfig(this, this._base.withAnnotationIntrospector(annotationIntrospector));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        return new DeserializationConfig(this, this._base.withVisibilityChecker(visibilityChecker));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
        return new DeserializationConfig(this, this._base.withVisibility(jsonMethod, visibility));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder) {
        return new DeserializationConfig(this, this._base.withTypeResolverBuilder(typeResolverBuilder));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withSubtypeResolver(SubtypeResolver subtypeResolver) {
        DeserializationConfig deserializationConfig = new DeserializationConfig(this);
        deserializationConfig._subtypeResolver = subtypeResolver;
        return deserializationConfig;
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        return new DeserializationConfig(this, this._base.withPropertyNamingStrategy(propertyNamingStrategy));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withTypeFactory(TypeFactory typeFactory) {
        return typeFactory == this._base.getTypeFactory() ? this : new DeserializationConfig(this, this._base.withTypeFactory(typeFactory));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withDateFormat(DateFormat dateFormat) {
        return dateFormat == this._base.getDateFormat() ? this : new DeserializationConfig(this, this._base.withDateFormat(dateFormat));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        return handlerInstantiator == this._base.getHandlerInstantiator() ? this : new DeserializationConfig(this, this._base.withHandlerInstantiator(handlerInstantiator));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new DeserializationConfig(this, this._base.withInsertedAnnotationIntrospector(annotationIntrospector));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new DeserializationConfig(this, this._base.withAppendedAnnotationIntrospector(annotationIntrospector));
    }

    public DeserializationConfig withNodeFactory(JsonNodeFactory jsonNodeFactory) {
        return new DeserializationConfig(this, jsonNodeFactory);
    }

    @Override // org.codehaus.jackson.map.MapperConfig.Impl
    public DeserializationConfig with(Feature... featureArr) {
        int i = this._featureFlags;
        for (Feature feature : featureArr) {
            i |= feature.getMask();
        }
        return new DeserializationConfig(this, i);
    }

    @Override // org.codehaus.jackson.map.MapperConfig.Impl
    public DeserializationConfig without(Feature... featureArr) {
        int i = this._featureFlags;
        for (Feature feature : featureArr) {
            i &= ~feature.getMask();
        }
        return new DeserializationConfig(this, i);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    @Deprecated
    public void fromAnnotations(Class<?> cls) {
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        this._base = this._base.withVisibilityChecker(annotationIntrospector.findAutoDetectVisibility(AnnotatedClass.construct(cls, annotationIntrospector, null), getDefaultVisibilityChecker()));
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig createUnshared(SubtypeResolver subtypeResolver) {
        HashMap<ClassKey, Class<?>> hashMap = this._mixInAnnotations;
        this._mixInAnnotationsShared = true;
        return new DeserializationConfig(this, hashMap, subtypeResolver);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public AnnotationIntrospector getAnnotationIntrospector() {
        if (isEnabled(Feature.USE_ANNOTATIONS)) {
            return super.getAnnotationIntrospector();
        }
        return NopAnnotationIntrospector.instance;
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public <T extends BeanDescription> T introspectClassAnnotations(JavaType javaType) {
        return (T) getClassIntrospector().forClassAnnotations(this, javaType, this);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public <T extends BeanDescription> T introspectDirectClassAnnotations(JavaType javaType) {
        return (T) getClassIntrospector().forDirectClassAnnotations(this, javaType, this);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public boolean isAnnotationProcessingEnabled() {
        return isEnabled(Feature.USE_ANNOTATIONS);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public boolean canOverrideAccessModifiers() {
        return isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    @Override // org.codehaus.jackson.map.MapperConfig
    public boolean shouldSortPropertiesAlphabetically() {
        return this._sortPropertiesAlphabetically;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.codehaus.jackson.map.introspect.VisibilityChecker<?>, org.codehaus.jackson.map.introspect.VisibilityChecker] */
    @Override // org.codehaus.jackson.map.MapperConfig
    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker<?> defaultVisibilityChecker = super.getDefaultVisibilityChecker();
        VisibilityChecker<?> visibilityChecker = defaultVisibilityChecker;
        if (!isEnabled(Feature.AUTO_DETECT_SETTERS)) {
            visibilityChecker = defaultVisibilityChecker.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        if (!isEnabled(Feature.AUTO_DETECT_CREATORS)) {
            visibilityChecker2 = visibilityChecker.withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return !isEnabled(Feature.AUTO_DETECT_FIELDS) ? visibilityChecker2.withFieldVisibility(JsonAutoDetect.Visibility.NONE) : visibilityChecker2;
    }

    public boolean isEnabled(Feature feature) {
        return (feature.getMask() & this._featureFlags) != 0;
    }

    @Override // org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void enable(Feature feature) {
        super.enable((DeserializationConfig) feature);
    }

    @Override // org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void disable(Feature feature) {
        super.disable((DeserializationConfig) feature);
    }

    @Override // org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void set(Feature feature, boolean z) {
        super.set((DeserializationConfig) feature, z);
    }

    public LinkedNode<DeserializationProblemHandler> getProblemHandlers() {
        return this._problemHandlers;
    }

    public void addHandler(DeserializationProblemHandler deserializationProblemHandler) {
        if (LinkedNode.contains(this._problemHandlers, deserializationProblemHandler)) {
            return;
        }
        this._problemHandlers = new LinkedNode<>(deserializationProblemHandler, this._problemHandlers);
    }

    public void clearHandlers() {
        this._problemHandlers = null;
    }

    public Base64Variant getBase64Variant() {
        return Base64Variants.getDefaultVariant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._nodeFactory;
    }

    public <T extends BeanDescription> T introspect(JavaType javaType) {
        return (T) getClassIntrospector().forDeserialization(this, javaType, this);
    }

    public <T extends BeanDescription> T introspectForCreation(JavaType javaType) {
        return (T) getClassIntrospector().forCreation(this, javaType, this);
    }

    public JsonDeserializer<Object> deserializerInstance(Annotated annotated, Class<? extends JsonDeserializer<?>> cls) {
        JsonDeserializer<?> deserializerInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (deserializerInstance = handlerInstantiator.deserializerInstance(this, annotated, cls)) == null) ? (JsonDeserializer) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : deserializerInstance;
    }

    public KeyDeserializer keyDeserializerInstance(Annotated annotated, Class<? extends KeyDeserializer> cls) {
        KeyDeserializer keyDeserializerInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (keyDeserializerInstance = handlerInstantiator.keyDeserializerInstance(this, annotated, cls)) == null) ? (KeyDeserializer) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : keyDeserializerInstance;
    }

    public ValueInstantiator valueInstantiatorInstance(Annotated annotated, Class<? extends ValueInstantiator> cls) {
        ValueInstantiator valueInstantiatorInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (valueInstantiatorInstance = handlerInstantiator.valueInstantiatorInstance(this, annotated, cls)) == null) ? (ValueInstantiator) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : valueInstantiatorInstance;
    }
}
