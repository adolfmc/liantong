package org.codehaus.jackson.map.type;

import java.lang.reflect.Array;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ArrayType extends TypeBase {
    protected final JavaType _componentType;
    protected final Object _emptyArray;

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
    public boolean isAbstract() {
        return false;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isArrayType() {
        return true;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isConcrete() {
        return true;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return true;
    }

    private ArrayType(JavaType javaType, Object obj, Object obj2, Object obj3) {
        super(obj.getClass(), javaType.hashCode(), obj2, obj3);
        this._componentType = javaType;
        this._emptyArray = obj;
    }

    @Deprecated
    public static ArrayType construct(JavaType javaType) {
        return construct(javaType, null, null);
    }

    public static ArrayType construct(JavaType javaType, Object obj, Object obj2) {
        return new ArrayType(javaType, Array.newInstance(javaType.getRawClass(), 0), null, null);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public ArrayType withTypeHandler(Object obj) {
        return obj == this._typeHandler ? this : new ArrayType(this._componentType, this._emptyArray, this._valueHandler, obj);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public ArrayType withContentTypeHandler(Object obj) {
        return obj == this._componentType.getTypeHandler() ? this : new ArrayType(this._componentType.withTypeHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public ArrayType withValueHandler(Object obj) {
        return obj == this._valueHandler ? this : new ArrayType(this._componentType, this._emptyArray, obj, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public ArrayType withContentValueHandler(Object obj) {
        return obj == this._componentType.getValueHandler() ? this : new ArrayType(this._componentType.withValueHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        return this._class.getName();
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType _narrow(Class<?> cls) {
        if (!cls.isArray()) {
            throw new IllegalArgumentException("Incompatible narrowing operation: trying to narrow " + toString() + " to class " + cls.getName());
        }
        return construct(TypeFactory.defaultInstance().constructType(cls.getComponentType()), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType getContentType() {
        return this._componentType;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.type.TypeBase, org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        sb.append('[');
        return this._componentType.getGenericSignature(sb);
    }

    @Override // org.codehaus.jackson.map.type.TypeBase, org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append('[');
        return this._componentType.getErasedSignature(sb);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public String toString() {
        return "[array type, component type: " + this._componentType + "]";
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            return this._componentType.equals(((ArrayType) obj)._componentType);
        }
        return false;
    }
}
