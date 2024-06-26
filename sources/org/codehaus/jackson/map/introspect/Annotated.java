package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Annotated {
    protected abstract AnnotationMap getAllAnnotations();

    public abstract AnnotatedElement getAnnotated();

    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    public abstract Type getGenericType();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getModifiers();

    public abstract String getName();

    public abstract Class<?> getRawType();

    public abstract Annotated withAnnotations(AnnotationMap annotationMap);

    public final <A extends Annotation> boolean hasAnnotation(Class<A> cls) {
        return getAnnotation(cls) != null;
    }

    public final Annotated withFallBackAnnotationsFrom(Annotated annotated) {
        return withAnnotations(AnnotationMap.merge(getAllAnnotations(), annotated.getAllAnnotations()));
    }

    public final boolean isPublic() {
        return Modifier.isPublic(getModifiers());
    }

    public JavaType getType(TypeBindings typeBindings) {
        return typeBindings.resolveType(getGenericType());
    }
}
