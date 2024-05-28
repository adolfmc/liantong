package org.codehaus.jackson.map.jsontype.impl;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class TypeSerializerBase extends TypeSerializer {
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    @Override // org.codehaus.jackson.map.TypeSerializer
    public String getPropertyName() {
        return null;
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public abstract JsonTypeInfo.EnumC13395As getTypeInclusion();

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeSerializerBase(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this._idResolver = typeIdResolver;
        this._property = beanProperty;
    }

    @Override // org.codehaus.jackson.map.TypeSerializer
    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }
}
