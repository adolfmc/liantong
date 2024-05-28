package org.codehaus.jackson.map.ser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.map.util.Comparators;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PropertyBuilder {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BasicBeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonSerialize.Inclusion _outputProps;

    public PropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        this._config = serializationConfig;
        this._beanDesc = basicBeanDescription;
        this._outputProps = basicBeanDescription.findSerializationInclusion(serializationConfig.getSerializationInclusion());
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanPropertyWriter buildWriter(String str, JavaType javaType, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, TypeSerializer typeSerializer2, AnnotatedMember annotatedMember, boolean z) {
        JavaType javaType2;
        Method annotated;
        Field field;
        boolean z2;
        JavaType javaType3;
        boolean z3;
        Object obj;
        BeanPropertyWriter beanPropertyWriter;
        if (annotatedMember instanceof AnnotatedField) {
            javaType2 = javaType;
            field = ((AnnotatedField) annotatedMember).getAnnotated();
            annotated = null;
            z2 = z;
        } else {
            javaType2 = javaType;
            annotated = ((AnnotatedMethod) annotatedMember).getAnnotated();
            field = null;
            z2 = z;
        }
        JavaType findSerializationType = findSerializationType(annotatedMember, z2, javaType2);
        if (typeSerializer2 != null) {
            if (findSerializationType == null) {
                findSerializationType = javaType2;
            }
            if (findSerializationType.getContentType() == null) {
                throw new IllegalStateException("Problem trying to create BeanPropertyWriter for property '" + str + "' (of type " + this._beanDesc.getType() + "); serialization type " + findSerializationType + " has no content");
            }
            JavaType withContentTypeHandler = findSerializationType.withContentTypeHandler(typeSerializer2);
            withContentTypeHandler.getContentType();
            javaType3 = withContentTypeHandler;
        } else {
            javaType3 = findSerializationType;
        }
        boolean z4 = false;
        JsonSerialize.Inclusion findSerializationInclusion = this._annotationIntrospector.findSerializationInclusion(annotatedMember, this._outputProps);
        if (findSerializationInclusion != null) {
            switch (findSerializationInclusion) {
                case NON_DEFAULT:
                    Object defaultValue = getDefaultValue(str, annotated, field);
                    if (defaultValue != null) {
                        if (!defaultValue.getClass().isArray()) {
                            z3 = false;
                            obj = defaultValue;
                            break;
                        } else {
                            z3 = false;
                            obj = Comparators.getArrayComparator(defaultValue);
                            break;
                        }
                    } else {
                        obj = defaultValue;
                        z3 = true;
                        break;
                    }
                case NON_EMPTY:
                    obj = getEmptyValueChecker(str, javaType);
                    z3 = true;
                    break;
                case NON_NULL:
                    z4 = true;
                case ALWAYS:
                    if (!javaType.isContainerType()) {
                        z3 = z4;
                        obj = null;
                        break;
                    } else {
                        z3 = z4;
                        obj = getContainerValueChecker(str, javaType);
                        break;
                    }
            }
            beanPropertyWriter = new BeanPropertyWriter(annotatedMember, this._beanDesc.getClassAnnotations(), str, javaType, jsonSerializer, typeSerializer, javaType3, annotated, field, z3, obj);
            Boolean shouldUnwrapProperty = this._annotationIntrospector.shouldUnwrapProperty(annotatedMember);
            return (shouldUnwrapProperty == null || !shouldUnwrapProperty.booleanValue()) ? beanPropertyWriter : beanPropertyWriter.unwrappingWriter();
        }
        z3 = false;
        obj = null;
        beanPropertyWriter = new BeanPropertyWriter(annotatedMember, this._beanDesc.getClassAnnotations(), str, javaType, jsonSerializer, typeSerializer, javaType3, annotated, field, z3, obj);
        Boolean shouldUnwrapProperty2 = this._annotationIntrospector.shouldUnwrapProperty(annotatedMember);
        if (shouldUnwrapProperty2 == null) {
            return beanPropertyWriter;
        }
    }

    protected JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) {
        JsonSerialize.Typing findSerializationTyping;
        Class<?> findSerializationType = this._annotationIntrospector.findSerializationType(annotated);
        if (findSerializationType != null) {
            Class<?> rawClass = javaType.getRawClass();
            if (findSerializationType.isAssignableFrom(rawClass)) {
                javaType = javaType.widenBy(findSerializationType);
            } else if (!rawClass.isAssignableFrom(findSerializationType)) {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + findSerializationType.getName() + " not a super-type of (declared) class " + rawClass.getName());
            } else {
                javaType = this._config.constructSpecializedType(javaType, findSerializationType);
            }
            z = true;
        }
        JavaType modifySecondaryTypesByAnnotation = BeanSerializerFactory.modifySecondaryTypesByAnnotation(this._config, annotated, javaType);
        if (modifySecondaryTypesByAnnotation != javaType) {
            javaType = modifySecondaryTypesByAnnotation;
            z = true;
        }
        if (!z && (findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated)) != null) {
            z = findSerializationTyping == JsonSerialize.Typing.STATIC;
        }
        if (z) {
            return javaType;
        }
        return null;
    }

    protected Object getDefaultBean() {
        if (this._defaultBean == null) {
            this._defaultBean = this._beanDesc.instantiateBean(this._config.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
            if (this._defaultBean == null) {
                Class<?> annotated = this._beanDesc.getClassInfo().getAnnotated();
                throw new IllegalArgumentException("Class " + annotated.getName() + " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation");
            }
        }
        return this._defaultBean;
    }

    protected Object getDefaultValue(String str, Method method, Field field) {
        Object defaultBean = getDefaultBean();
        try {
            if (method != null) {
                return method.invoke(defaultBean, new Object[0]);
            }
            return field.get(defaultBean);
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }

    protected Object getContainerValueChecker(String str, JavaType javaType) {
        if (this._config.isEnabled(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS)) {
            return null;
        }
        if (javaType.isArrayType()) {
            return new EmptyArrayChecker();
        }
        if (Collection.class.isAssignableFrom(javaType.getRawClass())) {
            return new EmptyCollectionChecker();
        }
        return null;
    }

    protected Object getEmptyValueChecker(String str, JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return new EmptyStringChecker();
        }
        if (javaType.isArrayType()) {
            return new EmptyArrayChecker();
        }
        if (Collection.class.isAssignableFrom(rawClass)) {
            return new EmptyCollectionChecker();
        }
        if (Map.class.isAssignableFrom(rawClass)) {
            return new EmptyMapChecker();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r3 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object _throwWrapped(java.lang.Exception r3, java.lang.String r4, java.lang.Object r5) {
        /*
            r2 = this;
        L0:
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto Lb
            java.lang.Throwable r3 = r3.getCause()
            goto L0
        Lb:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L42
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L16
            java.lang.RuntimeException r3 = (java.lang.RuntimeException) r3
            throw r3
        L16:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to get property '"
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = "' of default "
            r0.append(r4)
            java.lang.Class r4 = r5.getClass()
            java.lang.String r4 = r4.getName()
            r0.append(r4)
            java.lang.String r4 = " instance"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        L42:
            java.lang.Error r3 = (java.lang.Error) r3
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.map.ser.PropertyBuilder._throwWrapped(java.lang.Exception, java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class EmptyCollectionChecker {
        public boolean equals(Object obj) {
            return obj == null || ((Collection) obj).size() == 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class EmptyMapChecker {
        public boolean equals(Object obj) {
            return obj == null || ((Map) obj).size() == 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class EmptyArrayChecker {
        public boolean equals(Object obj) {
            return obj == null || Array.getLength(obj) == 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class EmptyStringChecker {
        public boolean equals(Object obj) {
            return obj == null || ((String) obj).length() == 0;
        }
    }
}
