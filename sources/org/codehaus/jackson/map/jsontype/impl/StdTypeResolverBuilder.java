package org.codehaus.jackson.map.jsontype.impl;

import java.util.Collection;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StdTypeResolverBuilder implements TypeResolverBuilder<StdTypeResolverBuilder> {
    protected TypeIdResolver _customIdResolver;
    protected Class<?> _defaultImpl;
    protected JsonTypeInfo.EnumC13396Id _idType;
    protected JsonTypeInfo.EnumC13395As _includeAs;
    protected String _typeProperty;

    @Override // org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public /* bridge */ /* synthetic */ StdTypeResolverBuilder defaultImpl(Class cls) {
        return defaultImpl2((Class<?>) cls);
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public Class<?> getDefaultImpl() {
        return this._defaultImpl;
    }

    public static StdTypeResolverBuilder noTypeInfoBuilder() {
        return new StdTypeResolverBuilder().init(JsonTypeInfo.EnumC13396Id.NONE, (TypeIdResolver) null);
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public StdTypeResolverBuilder init(JsonTypeInfo.EnumC13396Id enumC13396Id, TypeIdResolver typeIdResolver) {
        if (enumC13396Id == null) {
            throw new IllegalArgumentException("idType can not be null");
        }
        this._idType = enumC13396Id;
        this._customIdResolver = typeIdResolver;
        this._typeProperty = enumC13396Id.getDefaultPropertyName();
        return this;
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty) {
        if (this._idType == JsonTypeInfo.EnumC13396Id.NONE) {
            return null;
        }
        TypeIdResolver idResolver = idResolver(serializationConfig, javaType, collection, true, false);
        switch (this._includeAs) {
            case WRAPPER_ARRAY:
                return new AsArrayTypeSerializer(idResolver, beanProperty);
            case PROPERTY:
                return new AsPropertyTypeSerializer(idResolver, beanProperty, this._typeProperty);
            case WRAPPER_OBJECT:
                return new AsWrapperTypeSerializer(idResolver, beanProperty);
            case EXTERNAL_PROPERTY:
                return new AsExternalTypeSerializer(idResolver, beanProperty, this._typeProperty);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
        }
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty) {
        if (this._idType == JsonTypeInfo.EnumC13396Id.NONE) {
            return null;
        }
        TypeIdResolver idResolver = idResolver(deserializationConfig, javaType, collection, false, true);
        switch (this._includeAs) {
            case WRAPPER_ARRAY:
                return new AsArrayTypeDeserializer(javaType, idResolver, beanProperty, this._defaultImpl);
            case PROPERTY:
                return new AsPropertyTypeDeserializer(javaType, idResolver, beanProperty, this._defaultImpl, this._typeProperty);
            case WRAPPER_OBJECT:
                return new AsWrapperTypeDeserializer(javaType, idResolver, beanProperty, this._defaultImpl);
            case EXTERNAL_PROPERTY:
                return new AsExternalTypeDeserializer(javaType, idResolver, beanProperty, this._defaultImpl, this._typeProperty);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
        }
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public StdTypeResolverBuilder inclusion(JsonTypeInfo.EnumC13395As enumC13395As) {
        if (enumC13395As == null) {
            throw new IllegalArgumentException("includeAs can not be null");
        }
        this._includeAs = enumC13395As;
        return this;
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public StdTypeResolverBuilder typeProperty(String str) {
        if (str == null || str.length() == 0) {
            str = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = str;
        return this;
    }

    @Override // org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    /* renamed from: defaultImpl  reason: avoid collision after fix types in other method */
    public StdTypeResolverBuilder defaultImpl2(Class<?> cls) {
        this._defaultImpl = cls;
        return this;
    }

    public String getTypeProperty() {
        return this._typeProperty;
    }

    protected TypeIdResolver idResolver(MapperConfig<?> mapperConfig, JavaType javaType, Collection<NamedType> collection, boolean z, boolean z2) {
        TypeIdResolver typeIdResolver = this._customIdResolver;
        if (typeIdResolver != null) {
            return typeIdResolver;
        }
        if (this._idType == null) {
            throw new IllegalStateException("Can not build, 'init()' not yet called");
        }
        switch (this._idType) {
            case CLASS:
                return new ClassNameIdResolver(javaType, mapperConfig.getTypeFactory());
            case MINIMAL_CLASS:
                return new MinimalClassNameIdResolver(javaType, mapperConfig.getTypeFactory());
            case NAME:
                return TypeNameIdResolver.construct(mapperConfig, javaType, collection, z, z2);
            case NONE:
                return null;
            default:
                throw new IllegalStateException("Do not know how to construct standard type id resolver for idType: " + this._idType);
        }
    }
}
