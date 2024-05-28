package org.codehaus.jackson.map.deser.impl;

import java.io.IOException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ValueInjector extends BeanProperty.Std {
    protected final Object _valueId;

    public ValueInjector(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember, Object obj) {
        super(str, javaType, annotations, annotatedMember);
        this._valueId = obj;
    }

    public Object findValue(DeserializationContext deserializationContext, Object obj) {
        return deserializationContext.findInjectableValue(this._valueId, this, obj);
    }

    public void inject(DeserializationContext deserializationContext, Object obj) throws IOException {
        this._member.setValue(obj, findValue(deserializationContext, obj));
    }
}
