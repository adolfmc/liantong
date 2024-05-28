package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class AnnotatedField extends AnnotatedMember {
    protected final Field _field;

    public AnnotatedField(Field field, AnnotationMap annotationMap) {
        super(annotationMap);
        this._field = field;
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public AnnotatedField withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedField(this._field, annotationMap);
    }

    public void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public Field getAnnotated() {
        return this._field;
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._field.getModifiers();
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return this._field.getName();
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this._annotations.get(cls);
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._field.getGenericType();
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public Class<?> getRawType() {
        return this._field.getType();
    }

    @Override // org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class<?> getDeclaringClass() {
        return this._field.getDeclaringClass();
    }

    @Override // org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._field;
    }

    @Override // org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        try {
            this._field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to setValue() for field " + getFullName() + ": " + e.getMessage(), e);
        }
    }

    public String getFullName() {
        return getDeclaringClass().getName() + "#" + getName();
    }

    public int getAnnotationCount() {
        return this._annotations.size();
    }

    public String toString() {
        return "[field " + getName() + ", annotations: " + this._annotations + "]";
    }
}
