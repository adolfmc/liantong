package org.codehaus.jackson.type;

import com.mabeijianxi.smallvideorecord2.MediaRecorderBase;
import java.lang.reflect.Modifier;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class JavaType {
    protected final Class<?> _class;
    protected final int _hashCode;
    protected Object _valueHandler = null;
    protected Object _typeHandler = null;

    protected abstract JavaType _narrow(Class<?> cls);

    public JavaType containedType(int i) {
        return null;
    }

    public int containedTypeCount() {
        return 0;
    }

    public String containedTypeName(int i) {
        return null;
    }

    public abstract boolean equals(Object obj);

    public JavaType getContentType() {
        return null;
    }

    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    public JavaType getKeyType() {
        return null;
    }

    public boolean isArrayType() {
        return false;
    }

    public boolean isCollectionLikeType() {
        return false;
    }

    public abstract boolean isContainerType();

    public boolean isMapLikeType() {
        return false;
    }

    public abstract JavaType narrowContentsBy(Class<?> cls);

    public abstract String toCanonical();

    public abstract String toString();

    public abstract JavaType widenContentsBy(Class<?> cls);

    public abstract JavaType withContentTypeHandler(Object obj);

    public abstract JavaType withTypeHandler(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaType(Class<?> cls, int i) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + i;
    }

    public JavaType withValueHandler(Object obj) {
        setValueHandler(obj);
        return this;
    }

    public JavaType withContentValueHandler(Object obj) {
        getContentType().setValueHandler(obj);
        return this;
    }

    @Deprecated
    public void setValueHandler(Object obj) {
        if (obj != null && this._valueHandler != null) {
            throw new IllegalStateException("Trying to reset value handler for type [" + toString() + "]; old handler of type " + this._valueHandler.getClass().getName() + ", new handler of type " + obj.getClass().getName());
        }
        this._valueHandler = obj;
    }

    public JavaType narrowBy(Class<?> cls) {
        Class<?> cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        _assertSubclass(cls, cls2);
        JavaType _narrow = _narrow(cls);
        if (this._valueHandler != _narrow.getValueHandler()) {
            _narrow = _narrow.withValueHandler(this._valueHandler);
        }
        return this._typeHandler != _narrow.getTypeHandler() ? _narrow.withTypeHandler(this._typeHandler) : _narrow;
    }

    public JavaType forcedNarrowBy(Class<?> cls) {
        if (cls == this._class) {
            return this;
        }
        JavaType _narrow = _narrow(cls);
        if (this._valueHandler != _narrow.getValueHandler()) {
            _narrow = _narrow.withValueHandler(this._valueHandler);
        }
        return this._typeHandler != _narrow.getTypeHandler() ? _narrow.withTypeHandler(this._typeHandler) : _narrow;
    }

    public JavaType widenBy(Class<?> cls) {
        Class<?> cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        _assertSubclass(cls2, cls);
        return _widen(cls);
    }

    protected JavaType _widen(Class<?> cls) {
        return _narrow(cls);
    }

    public final Class<?> getRawClass() {
        return this._class;
    }

    public final boolean hasRawClass(Class<?> cls) {
        return this._class == cls;
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public boolean isConcrete() {
        return (this._class.getModifiers() & MediaRecorderBase.VIDEO_BITRATE_MEDIUM) == 0 || this._class.isPrimitive();
    }

    public boolean isThrowable() {
        return Throwable.class.isAssignableFrom(this._class);
    }

    public final boolean isEnumType() {
        return this._class.isEnum();
    }

    public final boolean isInterface() {
        return this._class.isInterface();
    }

    public final boolean isPrimitive() {
        return this._class.isPrimitive();
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this._class.getModifiers());
    }

    public boolean hasGenericTypes() {
        return containedTypeCount() > 0;
    }

    public <T> T getValueHandler() {
        return (T) this._valueHandler;
    }

    public <T> T getTypeHandler() {
        return (T) this._typeHandler;
    }

    public String getGenericSignature() {
        StringBuilder sb = new StringBuilder(40);
        getGenericSignature(sb);
        return sb.toString();
    }

    public String getErasedSignature() {
        StringBuilder sb = new StringBuilder(40);
        getErasedSignature(sb);
        return sb.toString();
    }

    protected void _assertSubclass(Class<?> cls, Class<?> cls2) {
        if (this._class.isAssignableFrom(cls)) {
            return;
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not assignable to " + this._class.getName());
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
