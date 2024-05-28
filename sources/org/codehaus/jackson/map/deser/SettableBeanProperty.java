package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.InternCache;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class SettableBeanProperty implements BeanProperty {
    protected final Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected NullProvider _nullProvider;
    protected final String _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected TypeDeserializer _valueTypeDeserializer;

    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException;

    @Override // org.codehaus.jackson.map.BeanProperty
    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    public Object getInjectableValueId() {
        return null;
    }

    @Override // org.codehaus.jackson.map.BeanProperty
    public abstract AnnotatedMember getMember();

    public abstract void set(Object obj, Object obj2) throws IOException;

    public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer);

    /* JADX INFO: Access modifiers changed from: protected */
    public SettableBeanProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations) {
        this._propertyIndex = -1;
        if (str == null || str.length() == 0) {
            this._propName = "";
        } else {
            this._propName = InternCache.instance.intern(str);
        }
        this._type = javaType;
        this._contextAnnotations = annotations;
        this._valueTypeDeserializer = typeDeserializer;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty) {
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._nullProvider = settableBeanProperty._nullProvider;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SettableBeanProperty(SettableBeanProperty settableBeanProperty, JsonDeserializer<Object> jsonDeserializer) {
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        this._valueDeserializer = jsonDeserializer;
        if (jsonDeserializer == null) {
            this._nullProvider = null;
            return;
        }
        Object nullValue = jsonDeserializer.getNullValue();
        this._nullProvider = nullValue != null ? new NullProvider(this._type, nullValue) : null;
    }

    @Deprecated
    public void setValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        if (this._valueDeserializer != null) {
            throw new IllegalStateException("Already had assigned deserializer for property '" + getName() + "' (class " + getDeclaringClass().getName() + ")");
        }
        this._valueDeserializer = jsonDeserializer;
        Object nullValue = this._valueDeserializer.getNullValue();
        this._nullProvider = nullValue == null ? null : new NullProvider(this._type, nullValue);
    }

    public void setManagedReferenceName(String str) {
        this._managedReferenceName = str;
    }

    public void assignIndex(int i) {
        if (this._propertyIndex != -1) {
            throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + i);
        }
        this._propertyIndex = i;
    }

    @Override // org.codehaus.jackson.map.BeanProperty, org.codehaus.jackson.map.util.Named
    public final String getName() {
        return this._propName;
    }

    @Override // org.codehaus.jackson.map.BeanProperty
    public JavaType getType() {
        return this._type;
    }

    @Override // org.codehaus.jackson.map.BeanProperty
    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return (A) this._contextAnnotations.get(cls);
    }

    protected final Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    @Deprecated
    public String getPropertyName() {
        return this._propName;
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public boolean hasValueTypeDeserializer() {
        return this._valueTypeDeserializer != null;
    }

    public JsonDeserializer<Object> getValueDeserializer() {
        return this._valueDeserializer;
    }

    public TypeDeserializer getValueTypeDeserializer() {
        return this._valueTypeDeserializer;
    }

    public int getPropertyIndex() {
        return this._propertyIndex;
    }

    @Deprecated
    public int getProperytIndex() {
        return getPropertyIndex();
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            NullProvider nullProvider = this._nullProvider;
            if (nullProvider == null) {
                return null;
            }
            return nullProvider.nullValue(deserializationContext);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            return this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    protected void _throwAsIOE(Exception exc, Object obj) throws IOException {
        if (exc instanceof IllegalArgumentException) {
            String name = obj == null ? "[NULL]" : obj.getClass().getName();
            StringBuilder sb = new StringBuilder("Problem deserializing property '");
            sb.append(getPropertyName());
            sb.append("' (expected type: ");
            sb.append(getType());
            sb.append("; actual type: ");
            sb.append(name);
            sb.append(")");
            String message = exc.getMessage();
            if (message != null) {
                sb.append(", problem: ");
                sb.append(message);
            } else {
                sb.append(" (no error message provided)");
            }
            throw new JsonMappingException(sb.toString(), null, exc);
        }
        _throwAsIOE(exc);
    }

    protected IOException _throwAsIOE(Exception exc) throws IOException {
        if (exc instanceof IOException) {
            throw ((IOException) exc);
        }
        boolean z = exc instanceof RuntimeException;
        Exception exc2 = exc;
        if (z) {
            throw ((RuntimeException) exc);
        }
        while (exc2.getCause() != null) {
            exc2 = exc2.getCause();
        }
        throw new JsonMappingException(exc2.getMessage(), null, exc2);
    }

    public String toString() {
        return "[property '" + getName() + "']";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class MethodProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _setter;

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public /* bridge */ /* synthetic */ SettableBeanProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        public MethodProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._setter = annotatedMethod.getAnnotated();
        }

        protected MethodProperty(MethodProperty methodProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(methodProperty, jsonDeserializer);
            this._annotated = methodProperty._annotated;
            this._setter = methodProperty._setter;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public MethodProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new MethodProperty(this, jsonDeserializer);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            try {
                this._setter.invoke(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SetterlessProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _getter;

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public /* bridge */ /* synthetic */ SettableBeanProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        public SetterlessProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._getter = annotatedMethod.getAnnotated();
        }

        protected SetterlessProperty(SetterlessProperty setterlessProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(setterlessProperty, jsonDeserializer);
            this._annotated = setterlessProperty._annotated;
            this._getter = setterlessProperty._getter;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public SetterlessProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new SetterlessProperty(this, jsonDeserializer);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                return;
            }
            try {
                Object invoke = this._getter.invoke(obj, new Object[0]);
                if (invoke == null) {
                    throw new JsonMappingException("Problem deserializing 'setterless' property '" + getName() + "': get method returned null");
                }
                this._valueDeserializer.deserialize(jsonParser, deserializationContext, invoke);
            } catch (Exception e) {
                _throwAsIOE(e);
            }
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            throw new UnsupportedOperationException("Should never call 'set' on setterless property");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class FieldProperty extends SettableBeanProperty {
        protected final AnnotatedField _annotated;
        protected final Field _field;

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public /* bridge */ /* synthetic */ SettableBeanProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        public FieldProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedField annotatedField) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedField;
            this._field = annotatedField.getAnnotated();
        }

        protected FieldProperty(FieldProperty fieldProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(fieldProperty, jsonDeserializer);
            this._annotated = fieldProperty._annotated;
            this._field = fieldProperty._field;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public FieldProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new FieldProperty(this, jsonDeserializer);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            try {
                this._field.set(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ManagedReferenceProperty extends SettableBeanProperty {
        protected final SettableBeanProperty _backProperty;
        protected final boolean _isContainer;
        protected final SettableBeanProperty _managedProperty;
        protected final String _referenceName;

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public /* bridge */ /* synthetic */ SettableBeanProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        public ManagedReferenceProperty(String str, SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2, Annotations annotations, boolean z) {
            super(settableBeanProperty.getName(), settableBeanProperty.getType(), settableBeanProperty._valueTypeDeserializer, annotations);
            this._referenceName = str;
            this._managedProperty = settableBeanProperty;
            this._backProperty = settableBeanProperty2;
            this._isContainer = z;
        }

        protected ManagedReferenceProperty(ManagedReferenceProperty managedReferenceProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(managedReferenceProperty, jsonDeserializer);
            this._referenceName = managedReferenceProperty._referenceName;
            this._isContainer = managedReferenceProperty._isContainer;
            this._managedProperty = managedReferenceProperty._managedProperty;
            this._backProperty = managedReferenceProperty._backProperty;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public ManagedReferenceProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new ManagedReferenceProperty(this, jsonDeserializer);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._managedProperty.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._managedProperty.getMember();
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            set(obj, this._managedProperty.deserialize(jsonParser, deserializationContext));
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            Object[] objArr;
            this._managedProperty.set(obj, obj2);
            if (obj2 != null) {
                if (this._isContainer) {
                    if (obj2 instanceof Object[]) {
                        for (Object obj3 : (Object[]) obj2) {
                            if (obj3 != null) {
                                this._backProperty.set(obj3, obj);
                            }
                        }
                        return;
                    } else if (obj2 instanceof Collection) {
                        for (Object obj4 : (Collection) obj2) {
                            if (obj4 != null) {
                                this._backProperty.set(obj4, obj);
                            }
                        }
                        return;
                    } else if (obj2 instanceof Map) {
                        for (Object obj5 : ((Map) obj2).values()) {
                            if (obj5 != null) {
                                this._backProperty.set(obj5, obj);
                            }
                        }
                        return;
                    } else {
                        throw new IllegalStateException("Unsupported container type (" + obj2.getClass().getName() + ") when resolving reference '" + this._referenceName + "'");
                    }
                }
                this._backProperty.set(obj2, obj);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class InnerClassProperty extends SettableBeanProperty {
        protected final Constructor<?> _creator;
        protected final SettableBeanProperty _delegate;

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public /* bridge */ /* synthetic */ SettableBeanProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
        }

        public InnerClassProperty(SettableBeanProperty settableBeanProperty, Constructor<?> constructor) {
            super(settableBeanProperty);
            this._delegate = settableBeanProperty;
            this._creator = constructor;
        }

        protected InnerClassProperty(InnerClassProperty innerClassProperty, JsonDeserializer<Object> jsonDeserializer) {
            super(innerClassProperty, jsonDeserializer);
            this._delegate = innerClassProperty._delegate.withValueDeserializer(jsonDeserializer);
            this._creator = innerClassProperty._creator;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public InnerClassProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new InnerClassProperty(this, jsonDeserializer);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._delegate.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._delegate.getMember();
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
            Object obj2 = null;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                if (this._nullProvider != null) {
                    obj2 = this._nullProvider.nullValue(deserializationContext);
                }
            } else if (this._valueTypeDeserializer != null) {
                obj2 = this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
            } else {
                try {
                    obj2 = this._creator.newInstance(obj);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e, "Failed to instantiate class " + this._creator.getDeclaringClass().getName() + ", problem: " + e.getMessage());
                }
                this._valueDeserializer.deserialize(jsonParser, deserializationContext, obj2);
            }
            set(obj, obj2);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) throws IOException {
            this._delegate.set(obj, obj2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class NullProvider {
        private final boolean _isPrimitive;
        private final Object _nullValue;
        private final Class<?> _rawType;

        protected NullProvider(JavaType javaType, Object obj) {
            this._nullValue = obj;
            this._isPrimitive = javaType.isPrimitive();
            this._rawType = javaType.getRawClass();
        }

        public Object nullValue(DeserializationContext deserializationContext) throws JsonProcessingException {
            if (this._isPrimitive && deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                throw deserializationContext.mappingException("Can not map JSON null into type " + this._rawType.getName() + " (set DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)");
            }
            return this._nullValue;
        }
    }
}
