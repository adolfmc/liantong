package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NopAnnotationIntrospector extends AnnotationIntrospector {
    public static final NopAnnotationIntrospector instance = new NopAnnotationIntrospector();

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Class<JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String findDeserializablePropertyName(AnnotatedField annotatedField) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Object findDeserializer(Annotated annotated) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String findEnumValue(Enum<?> r1) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String findGettablePropertyName(AnnotatedMethod annotatedMethod) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Class<KeyDeserializer> findKeyDeserializer(Annotated annotated) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String[] findPropertiesToIgnore(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String findPropertyNameForParam(AnnotatedParameter annotatedParameter) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String findRootName(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String findSerializablePropertyName(AnnotatedField annotatedField) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?> findSerializationType(Annotated annotated) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Class<?>[] findSerializationViews(Annotated annotated) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public Object findSerializer(Annotated annotated) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public String findSettablePropertyName(AnnotatedMethod annotatedMethod) {
        return null;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return false;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isHandled(Annotation annotation) {
        return false;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor) {
        return false;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableField(AnnotatedField annotatedField) {
        return false;
    }

    @Override // org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableMethod(AnnotatedMethod annotatedMethod) {
        return false;
    }
}
