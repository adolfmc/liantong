package org.codehaus.jackson.map.type;

import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CollectionType extends CollectionLikeType {
    private CollectionType(Class<?> cls, JavaType javaType, Object obj, Object obj2) {
        super(cls, javaType, obj, obj2);
    }

    @Override // org.codehaus.jackson.map.type.CollectionLikeType, org.codehaus.jackson.type.JavaType
    public JavaType _narrow(Class<?> cls) {
        return new CollectionType(cls, this._elementType, null, null);
    }

    @Override // org.codehaus.jackson.map.type.CollectionLikeType, org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionType(this._class, this._elementType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.map.type.CollectionLikeType, org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionType(this._class, this._elementType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    public static CollectionType construct(Class<?> cls, JavaType javaType) {
        return new CollectionType(cls, javaType, null, null);
    }

    @Override // org.codehaus.jackson.map.type.CollectionLikeType, org.codehaus.jackson.type.JavaType
    public CollectionType withTypeHandler(Object obj) {
        return new CollectionType(this._class, this._elementType, this._valueHandler, obj);
    }

    @Override // org.codehaus.jackson.map.type.CollectionLikeType, org.codehaus.jackson.type.JavaType
    public CollectionType withContentTypeHandler(Object obj) {
        return new CollectionType(this._class, this._elementType.withTypeHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.map.type.CollectionLikeType, org.codehaus.jackson.type.JavaType
    public CollectionType withValueHandler(Object obj) {
        return new CollectionType(this._class, this._elementType, obj, this._typeHandler);
    }

    @Override // org.codehaus.jackson.map.type.CollectionLikeType, org.codehaus.jackson.type.JavaType
    public CollectionType withContentValueHandler(Object obj) {
        return new CollectionType(this._class, this._elementType.withValueHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.map.type.CollectionLikeType, org.codehaus.jackson.type.JavaType
    public String toString() {
        return "[collection type; class " + this._class.getName() + ", contains " + this._elementType + "]";
    }
}
