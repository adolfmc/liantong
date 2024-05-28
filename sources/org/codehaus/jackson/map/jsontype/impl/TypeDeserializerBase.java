package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import java.util.HashMap;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class TypeDeserializerBase extends TypeDeserializer {
    protected final JavaType _baseType;
    protected final JavaType _defaultImpl;
    protected JsonDeserializer<Object> _defaultImplDeserializer;
    protected final HashMap<String, JsonDeserializer<Object>> _deserializers;
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public String getPropertyName() {
        return null;
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public abstract JsonTypeInfo.EnumC13395As getTypeInclusion();

    @Deprecated
    protected TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this(javaType, typeIdResolver, beanProperty, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls) {
        this._baseType = javaType;
        this._idResolver = typeIdResolver;
        this._property = beanProperty;
        this._deserializers = new HashMap<>();
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            this._defaultImpl = javaType.forcedNarrowBy(cls);
        }
    }

    public String baseTypeName() {
        return this._baseType.getRawClass().getName();
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public Class<?> getDefaultImpl() {
        JavaType javaType = this._defaultImpl;
        if (javaType == null) {
            return null;
        }
        return javaType.getRawClass();
    }

    public String toString() {
        return '[' + getClass().getName() + "; base-type:" + this._baseType + "; id-resolver: " + this._idResolver + ']';
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonDeserializer<Object> _findDeserializer(DeserializationContext deserializationContext, String str) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = this._deserializers.get(str);
            if (jsonDeserializer == null) {
                JavaType typeFromId = this._idResolver.typeFromId(str);
                if (typeFromId == null) {
                    if (this._defaultImpl == null) {
                        throw deserializationContext.unknownTypeException(this._baseType, str);
                    }
                    jsonDeserializer = _findDefaultImplDeserializer(deserializationContext);
                } else {
                    if (this._baseType != null && this._baseType.getClass() == typeFromId.getClass()) {
                        typeFromId = this._baseType.narrowBy(typeFromId.getRawClass());
                    }
                    jsonDeserializer = deserializationContext.getDeserializerProvider().findValueDeserializer(deserializationContext.getConfig(), typeFromId, this._property);
                }
                this._deserializers.put(str, jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonDeserializer<Object> _findDefaultImplDeserializer(DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer;
        JavaType javaType = this._defaultImpl;
        if (javaType == null) {
            return null;
        }
        synchronized (javaType) {
            if (this._defaultImplDeserializer == null) {
                this._defaultImplDeserializer = deserializationContext.getDeserializerProvider().findValueDeserializer(deserializationContext.getConfig(), this._defaultImpl, this._property);
            }
            jsonDeserializer = this._defaultImplDeserializer;
        }
        return jsonDeserializer;
    }
}
