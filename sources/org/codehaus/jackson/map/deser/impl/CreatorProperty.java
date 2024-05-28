package org.codehaus.jackson.map.deser.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.SettableBeanProperty;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CreatorProperty extends SettableBeanProperty {
    protected final AnnotatedParameter _annotated;
    protected final Object _injectableValueId;

    @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
    public void set(Object obj, Object obj2) throws IOException {
    }

    @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
    public /* bridge */ /* synthetic */ SettableBeanProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
        return withValueDeserializer((JsonDeserializer<Object>) jsonDeserializer);
    }

    public CreatorProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i, Object obj) {
        super(str, javaType, typeDeserializer, annotations);
        this._annotated = annotatedParameter;
        this._propertyIndex = i;
        this._injectableValueId = obj;
    }

    protected CreatorProperty(CreatorProperty creatorProperty, JsonDeserializer<Object> jsonDeserializer) {
        super(creatorProperty, jsonDeserializer);
        this._annotated = creatorProperty._annotated;
        this._injectableValueId = creatorProperty._injectableValueId;
    }

    @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
    public CreatorProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        return new CreatorProperty(this, jsonDeserializer);
    }

    public Object findInjectableValue(DeserializationContext deserializationContext, Object obj) {
        Object obj2 = this._injectableValueId;
        if (obj2 == null) {
            throw new IllegalStateException("Property '" + getName() + "' (type " + getClass().getName() + ") has no injectable value id configured");
        }
        return deserializationContext.findInjectableValue(obj2, this, obj);
    }

    public void inject(DeserializationContext deserializationContext, Object obj) throws IOException {
        set(obj, findInjectableValue(deserializationContext, obj));
    }

    @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        AnnotatedParameter annotatedParameter = this._annotated;
        if (annotatedParameter == null) {
            return null;
        }
        return (A) annotatedParameter.getAnnotation(cls);
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
    public Object getInjectableValueId() {
        return this._injectableValueId;
    }
}
