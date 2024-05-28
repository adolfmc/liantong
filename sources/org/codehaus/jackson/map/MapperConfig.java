package org.codehaus.jackson.map;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ClassIntrospector;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.StdDateFormat;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class MapperConfig<T extends MapperConfig<T>> implements ClassIntrospector.MixInResolver {
    protected static final DateFormat DEFAULT_DATE_FORMAT = StdDateFormat.instance;
    protected Base _base;
    protected HashMap<ClassKey, Class<?>> _mixInAnnotations;
    protected boolean _mixInAnnotationsShared;
    protected SubtypeResolver _subtypeResolver;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface ConfigFeature {
        boolean enabledByDefault();

        int getMask();
    }

    public abstract boolean canOverrideAccessModifiers();

    public abstract T createUnshared(SubtypeResolver subtypeResolver);

    @Deprecated
    public abstract void fromAnnotations(Class<?> cls);

    public abstract <DESC extends BeanDescription> DESC introspectClassAnnotations(JavaType javaType);

    public abstract <DESC extends BeanDescription> DESC introspectDirectClassAnnotations(JavaType javaType);

    public abstract boolean isAnnotationProcessingEnabled();

    public abstract boolean isEnabled(ConfigFeature configFeature);

    public abstract boolean shouldSortPropertiesAlphabetically();

    public abstract T withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract T withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract T withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector);

    public abstract T withDateFormat(DateFormat dateFormat);

    public abstract T withHandlerInstantiator(HandlerInstantiator handlerInstantiator);

    public abstract T withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract T withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy);

    public abstract T withSubtypeResolver(SubtypeResolver subtypeResolver);

    public abstract T withTypeFactory(TypeFactory typeFactory);

    public abstract T withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder);

    public abstract T withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility);

    public abstract T withVisibilityChecker(VisibilityChecker<?> visibilityChecker);

    protected MapperConfig(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator) {
        this._base = new Base(classIntrospector, annotationIntrospector, visibilityChecker, propertyNamingStrategy, typeFactory, null, DEFAULT_DATE_FORMAT, handlerInstantiator);
        this._subtypeResolver = subtypeResolver;
        this._mixInAnnotationsShared = true;
    }

    protected MapperConfig(MapperConfig<T> mapperConfig) {
        this(mapperConfig, mapperConfig._base, mapperConfig._subtypeResolver);
    }

    protected MapperConfig(MapperConfig<T> mapperConfig, Base base, SubtypeResolver subtypeResolver) {
        this._base = base;
        this._subtypeResolver = subtypeResolver;
        this._mixInAnnotationsShared = true;
        this._mixInAnnotations = mapperConfig._mixInAnnotations;
    }

    public ClassIntrospector<? extends BeanDescription> getClassIntrospector() {
        return this._base.getClassIntrospector();
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._base.getAnnotationIntrospector();
    }

    @Deprecated
    public final void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(AnnotationIntrospector.Pair.create(annotationIntrospector, getAnnotationIntrospector()));
    }

    @Deprecated
    public final void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(AnnotationIntrospector.Pair.create(getAnnotationIntrospector(), annotationIntrospector));
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        return this._base.getVisibilityChecker();
    }

    public final PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._base.getPropertyNamingStrategy();
    }

    public final HandlerInstantiator getHandlerInstantiator() {
        return this._base.getHandlerInstantiator();
    }

    public final void setMixInAnnotations(Map<Class<?>, Class<?>> map) {
        HashMap<ClassKey, Class<?>> hashMap;
        if (map == null || map.size() <= 0) {
            hashMap = null;
        } else {
            hashMap = new HashMap<>(map.size());
            for (Map.Entry<Class<?>, Class<?>> entry : map.entrySet()) {
                hashMap.put(new ClassKey(entry.getKey()), entry.getValue());
            }
        }
        this._mixInAnnotationsShared = false;
        this._mixInAnnotations = hashMap;
    }

    public final void addMixInAnnotations(Class<?> cls, Class<?> cls2) {
        HashMap<ClassKey, Class<?>> hashMap = this._mixInAnnotations;
        if (hashMap == null) {
            this._mixInAnnotationsShared = false;
            this._mixInAnnotations = new HashMap<>();
        } else if (this._mixInAnnotationsShared) {
            this._mixInAnnotationsShared = false;
            this._mixInAnnotations = new HashMap<>(hashMap);
        }
        this._mixInAnnotations.put(new ClassKey(cls), cls2);
    }

    @Override // org.codehaus.jackson.map.ClassIntrospector.MixInResolver
    public final Class<?> findMixInClassFor(Class<?> cls) {
        HashMap<ClassKey, Class<?>> hashMap = this._mixInAnnotations;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(cls));
    }

    public final int mixInCount() {
        HashMap<ClassKey, Class<?>> hashMap = this._mixInAnnotations;
        if (hashMap == null) {
            return 0;
        }
        return hashMap.size();
    }

    public final TypeResolverBuilder<?> getDefaultTyper(JavaType javaType) {
        return this._base.getTypeResolverBuilder();
    }

    public final SubtypeResolver getSubtypeResolver() {
        if (this._subtypeResolver == null) {
            this._subtypeResolver = new StdSubtypeResolver();
        }
        return this._subtypeResolver;
    }

    public final TypeFactory getTypeFactory() {
        return this._base.getTypeFactory();
    }

    public final JavaType constructType(Class<?> cls) {
        return getTypeFactory().constructType(cls, (TypeBindings) null);
    }

    public final JavaType constructType(TypeReference<?> typeReference) {
        return getTypeFactory().constructType(typeReference.getType(), (TypeBindings) null);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return getTypeFactory().constructSpecializedType(javaType, cls);
    }

    public final DateFormat getDateFormat() {
        return this._base.getDateFormat();
    }

    public <DESC extends BeanDescription> DESC introspectClassAnnotations(Class<?> cls) {
        return (DESC) introspectClassAnnotations(constructType(cls));
    }

    public <DESC extends BeanDescription> DESC introspectDirectClassAnnotations(Class<?> cls) {
        return (DESC) introspectDirectClassAnnotations(constructType(cls));
    }

    public TypeResolverBuilder<?> typeResolverBuilderInstance(Annotated annotated, Class<? extends TypeResolverBuilder<?>> cls) {
        TypeResolverBuilder<?> typeResolverBuilderInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (typeResolverBuilderInstance = handlerInstantiator.typeResolverBuilderInstance(this, annotated, cls)) == null) ? (TypeResolverBuilder) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : typeResolverBuilderInstance;
    }

    public TypeIdResolver typeIdResolverInstance(Annotated annotated, Class<? extends TypeIdResolver> cls) {
        TypeIdResolver typeIdResolverInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (typeIdResolverInstance = handlerInstantiator.typeIdResolverInstance(this, annotated, cls)) == null) ? (TypeIdResolver) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : typeIdResolverInstance;
    }

    @Deprecated
    public final void setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(annotationIntrospector);
    }

    @Deprecated
    public void setDateFormat(DateFormat dateFormat) {
        if (dateFormat == null) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }
        this._base = this._base.withDateFormat(dateFormat);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Base {
        protected final AnnotationIntrospector _annotationIntrospector;
        protected final ClassIntrospector<? extends BeanDescription> _classIntrospector;
        protected final DateFormat _dateFormat;
        protected final HandlerInstantiator _handlerInstantiator;
        protected final PropertyNamingStrategy _propertyNamingStrategy;
        protected final TypeFactory _typeFactory;
        protected final TypeResolverBuilder<?> _typeResolverBuilder;
        protected final VisibilityChecker<?> _visibilityChecker;

        public Base(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, TypeResolverBuilder<?> typeResolverBuilder, DateFormat dateFormat, HandlerInstantiator handlerInstantiator) {
            this._classIntrospector = classIntrospector;
            this._annotationIntrospector = annotationIntrospector;
            this._visibilityChecker = visibilityChecker;
            this._propertyNamingStrategy = propertyNamingStrategy;
            this._typeFactory = typeFactory;
            this._typeResolverBuilder = typeResolverBuilder;
            this._dateFormat = dateFormat;
            this._handlerInstantiator = handlerInstantiator;
        }

        public Base withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector) {
            return new Base(classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return new Base(this._classIntrospector, annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return withAnnotationIntrospector(AnnotationIntrospector.Pair.create(annotationIntrospector, this._annotationIntrospector));
        }

        public Base withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return withAnnotationIntrospector(AnnotationIntrospector.Pair.create(this._annotationIntrospector, annotationIntrospector));
        }

        public Base withVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
            return new Base(this._classIntrospector, this._annotationIntrospector, visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        /* JADX WARN: Type inference failed for: r3v0, types: [org.codehaus.jackson.map.introspect.VisibilityChecker] */
        public Base withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker.withVisibility(jsonMethod, visibility), this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withTypeFactory(TypeFactory typeFactory) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withDateFormat(DateFormat dateFormat) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, dateFormat, this._handlerInstantiator);
        }

        public Base withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, handlerInstantiator);
        }

        public ClassIntrospector<? extends BeanDescription> getClassIntrospector() {
            return this._classIntrospector;
        }

        public AnnotationIntrospector getAnnotationIntrospector() {
            return this._annotationIntrospector;
        }

        public VisibilityChecker<?> getVisibilityChecker() {
            return this._visibilityChecker;
        }

        public PropertyNamingStrategy getPropertyNamingStrategy() {
            return this._propertyNamingStrategy;
        }

        public TypeFactory getTypeFactory() {
            return this._typeFactory;
        }

        public TypeResolverBuilder<?> getTypeResolverBuilder() {
            return this._typeResolverBuilder;
        }

        public DateFormat getDateFormat() {
            return this._dateFormat;
        }

        public HandlerInstantiator getHandlerInstantiator() {
            return this._handlerInstantiator;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class Impl<CFG extends ConfigFeature, T extends Impl<CFG, T>> extends MapperConfig<T> {
        protected int _featureFlags;

        public abstract T with(CFG... cfgArr);

        public abstract T without(CFG... cfgArr);

        /* JADX INFO: Access modifiers changed from: protected */
        public Impl(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator, int i) {
            super(classIntrospector, annotationIntrospector, visibilityChecker, subtypeResolver, propertyNamingStrategy, typeFactory, handlerInstantiator);
            this._featureFlags = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Impl(Impl<CFG, T> impl) {
            super(impl);
            this._featureFlags = impl._featureFlags;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Impl(Impl<CFG, T> impl, int i) {
            super(impl);
            this._featureFlags = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Impl(Impl<CFG, T> impl, Base base, SubtypeResolver subtypeResolver) {
            super(impl, base, subtypeResolver);
            this._featureFlags = impl._featureFlags;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static <F extends Enum<F> & ConfigFeature> int collectFeatureDefaults(Class<F> cls) {
            int i = 0;
            for (Enum r3 : (Enum[]) cls.getEnumConstants()) {
                ConfigFeature configFeature = (ConfigFeature) r3;
                if (configFeature.enabledByDefault()) {
                    i |= configFeature.getMask();
                }
            }
            return i;
        }

        @Override // org.codehaus.jackson.map.MapperConfig
        public boolean isEnabled(ConfigFeature configFeature) {
            return (configFeature.getMask() & this._featureFlags) != 0;
        }

        @Deprecated
        public void enable(CFG cfg) {
            this._featureFlags = cfg.getMask() | this._featureFlags;
        }

        @Deprecated
        public void disable(CFG cfg) {
            this._featureFlags = (~cfg.getMask()) & this._featureFlags;
        }

        @Deprecated
        public void set(CFG cfg, boolean z) {
            if (z) {
                enable(cfg);
            } else {
                disable(cfg);
            }
        }
    }
}
