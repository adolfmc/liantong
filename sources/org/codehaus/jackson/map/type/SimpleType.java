package org.codehaus.jackson.map.type;

import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SimpleType extends TypeBase {
    protected final String[] _typeNames;
    protected final JavaType[] _typeParameters;

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SimpleType(Class<?> cls) {
        this(cls, null, null, null, null);
    }

    @Deprecated
    protected SimpleType(Class<?> cls, String[] strArr, JavaType[] javaTypeArr) {
        this(cls, strArr, javaTypeArr, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SimpleType(Class<?> cls, String[] strArr, JavaType[] javaTypeArr, Object obj, Object obj2) {
        super(cls, 0, obj, obj2);
        if (strArr == null || strArr.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr;
        this._typeParameters = javaTypeArr;
    }

    public static SimpleType constructUnsafe(Class<?> cls) {
        return new SimpleType(cls, null, null, null, null);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType _narrow(Class<?> cls) {
        return new SimpleType(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
    }

    public static SimpleType construct(Class<?> cls) {
        if (Map.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Can not construct SimpleType for a Map (class: " + cls.getName() + ")");
        } else if (Collection.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Can not construct SimpleType for a Collection (class: " + cls.getName() + ")");
        } else if (cls.isArray()) {
            throw new IllegalArgumentException("Can not construct SimpleType for an array (class: " + cls.getName() + ")");
        } else {
            return new SimpleType(cls);
        }
    }

    @Override // org.codehaus.jackson.type.JavaType
    public SimpleType withTypeHandler(Object obj) {
        return new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType withContentTypeHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    @Override // org.codehaus.jackson.type.JavaType
    public SimpleType withValueHandler(Object obj) {
        return obj == this._valueHandler ? this : new SimpleType(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public SimpleType withContentValueHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    @Override // org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        JavaType[] javaTypeArr;
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        JavaType[] javaTypeArr2 = this._typeParameters;
        if (javaTypeArr2 != null && javaTypeArr2.length > 0) {
            sb.append('<');
            boolean z = true;
            for (JavaType javaType : this._typeParameters) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(javaType.toCanonical());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        JavaType[] javaTypeArr = this._typeParameters;
        if (javaTypeArr == null) {
            return 0;
        }
        return javaTypeArr.length;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        JavaType[] javaTypeArr;
        if (i < 0 || (javaTypeArr = this._typeParameters) == null || i >= javaTypeArr.length) {
            return null;
        }
        return javaTypeArr[i];
    }

    @Override // org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this._typeNames) == null || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    @Override // org.codehaus.jackson.map.type.TypeBase, org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    @Override // org.codehaus.jackson.map.type.TypeBase, org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        if (this._typeParameters != null) {
            sb.append('<');
            for (JavaType javaType : this._typeParameters) {
                sb = javaType.getGenericSignature(sb);
            }
            sb.append('>');
        }
        sb.append(';');
        return sb;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ");
        sb.append(buildCanonicalName());
        sb.append(']');
        return sb.toString();
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            SimpleType simpleType = (SimpleType) obj;
            if (simpleType._class != this._class) {
                return false;
            }
            JavaType[] javaTypeArr = this._typeParameters;
            JavaType[] javaTypeArr2 = simpleType._typeParameters;
            if (javaTypeArr == null) {
                return javaTypeArr2 == null || javaTypeArr2.length == 0;
            } else if (javaTypeArr2 != null && javaTypeArr.length == javaTypeArr2.length) {
                int length = javaTypeArr.length;
                for (int i = 0; i < length; i++) {
                    if (!javaTypeArr[i].equals(javaTypeArr2[i])) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
