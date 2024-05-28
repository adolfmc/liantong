package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.type.TypeFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class AnnotatedParameter extends AnnotatedMember {
    protected final int _index;
    protected final AnnotatedWithParams _owner;
    protected final Type _type;

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public AnnotatedElement getAnnotated() {
        return null;
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return "";
    }

    public AnnotatedParameter(AnnotatedWithParams annotatedWithParams, Type type, AnnotationMap annotationMap, int i) {
        super(annotationMap);
        this._owner = annotatedWithParams;
        this._type = type;
        this._index = i;
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public AnnotatedParameter withAnnotations(AnnotationMap annotationMap) {
        return annotationMap == this._annotations ? this : this._owner.replaceParameterAnnotations(this._index, annotationMap);
    }

    public void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._owner.getModifiers();
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this._annotations.get(cls);
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._type;
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public Class<?> getRawType() {
        Type type = this._type;
        if (type instanceof Class) {
            return (Class) type;
        }
        return TypeFactory.defaultInstance().constructType(this._type).getRawClass();
    }

    @Override // org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class<?> getDeclaringClass() {
        return this._owner.getDeclaringClass();
    }

    @Override // org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._owner.getMember();
    }

    @Override // org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot call setValue() on constructor parameter of " + getDeclaringClass().getName());
    }

    public Type getParameterType() {
        return this._type;
    }

    public AnnotatedWithParams getOwner() {
        return this._owner;
    }

    public int getIndex() {
        return this._index;
    }

    public String toString() {
        return "[parameter #" + getIndex() + ", annotations: " + this._annotations + "]";
    }
}
