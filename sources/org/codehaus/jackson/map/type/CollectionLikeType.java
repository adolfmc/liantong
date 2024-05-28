package org.codehaus.jackson.map.type;

import java.util.Collection;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CollectionLikeType extends TypeBase {
    protected final JavaType _elementType;

    @Override // org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        return 1;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isCollectionLikeType() {
        return true;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return true;
    }

    @Deprecated
    protected CollectionLikeType(Class<?> cls, JavaType javaType) {
        super(cls, javaType.hashCode(), null, null);
        this._elementType = javaType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CollectionLikeType(Class<?> cls, JavaType javaType, Object obj, Object obj2) {
        super(cls, javaType.hashCode(), obj, obj2);
        this._elementType = javaType;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType _narrow(Class<?> cls) {
        return new CollectionLikeType(cls, this._elementType, this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionLikeType(this._class, this._elementType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionLikeType(this._class, this._elementType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    public static CollectionLikeType construct(Class<?> cls, JavaType javaType) {
        return new CollectionLikeType(cls, javaType, null, null);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public CollectionLikeType withTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType, this._valueHandler, obj);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public CollectionLikeType withContentTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType.withTypeHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public CollectionLikeType withValueHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType, obj, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public CollectionLikeType withContentValueHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType.withValueHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType getContentType() {
        return this._elementType;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i == 0) {
            return this._elementType;
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.type.TypeBase, org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    @Override // org.codehaus.jackson.map.type.TypeBase, org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        sb.append('<');
        this._elementType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    @Override // org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._elementType != null) {
            sb.append('<');
            sb.append(this._elementType.toCanonical());
            sb.append('>');
        }
        return sb.toString();
    }

    public boolean isTrueCollectionType() {
        return Collection.class.isAssignableFrom(this._class);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            CollectionLikeType collectionLikeType = (CollectionLikeType) obj;
            return this._class == collectionLikeType._class && this._elementType.equals(collectionLikeType._elementType);
        }
        return false;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public String toString() {
        return "[collection-like type; class " + this._class.getName() + ", contains " + this._elementType + "]";
    }
}
