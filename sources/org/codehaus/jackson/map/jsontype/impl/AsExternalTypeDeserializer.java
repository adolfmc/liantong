package org.codehaus.jackson.map.jsontype.impl;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AsExternalTypeDeserializer extends AsArrayTypeDeserializer {
    protected final String _typePropertyName;

    public AsExternalTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls, String str) {
        super(javaType, typeIdResolver, beanProperty, cls);
        this._typePropertyName = str;
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.EnumC13395As getTypeInclusion() {
        return JsonTypeInfo.EnumC13395As.EXTERNAL_PROPERTY;
    }

    @Override // org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, org.codehaus.jackson.map.TypeDeserializer
    public String getPropertyName() {
        return this._typePropertyName;
    }
}
