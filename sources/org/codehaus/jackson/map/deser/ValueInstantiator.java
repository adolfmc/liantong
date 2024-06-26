package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ValueInstantiator {
    public boolean canCreateFromBoolean() {
        return false;
    }

    public boolean canCreateFromDouble() {
        return false;
    }

    public boolean canCreateFromInt() {
        return false;
    }

    public boolean canCreateFromLong() {
        return false;
    }

    public boolean canCreateFromObjectWith() {
        return false;
    }

    public boolean canCreateFromString() {
        return false;
    }

    public AnnotatedWithParams getDefaultCreator() {
        return null;
    }

    public AnnotatedWithParams getDelegateCreator() {
        return null;
    }

    public JavaType getDelegateType() {
        return null;
    }

    public SettableBeanProperty[] getFromObjectArguments() {
        return null;
    }

    public abstract String getValueTypeDesc();

    public AnnotatedWithParams getWithArgsCreator() {
        return null;
    }

    public boolean canInstantiate() {
        return canCreateUsingDefault() || canCreateUsingDelegate() || canCreateFromObjectWith() || canCreateFromString() || canCreateFromInt() || canCreateFromLong() || canCreateFromDouble() || canCreateFromBoolean();
    }

    public boolean canCreateUsingDefault() {
        return getDefaultCreator() != null;
    }

    public boolean canCreateUsingDelegate() {
        return getDelegateType() != null;
    }

    public Object createUsingDefault() throws IOException, JsonProcessingException {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + "; no default creator found");
    }

    public Object createFromObjectWith(Object[] objArr) throws IOException, JsonProcessingException {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " with arguments");
    }

    public Object createUsingDelegate(Object obj) throws IOException, JsonProcessingException {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " using delegate");
    }

    public Object createFromString(String str) throws IOException, JsonProcessingException {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON String");
    }

    public Object createFromInt(int i) throws IOException, JsonProcessingException {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON int number");
    }

    public Object createFromLong(long j) throws IOException, JsonProcessingException {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON long number");
    }

    public Object createFromDouble(double d) throws IOException, JsonProcessingException {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON floating-point number");
    }

    public Object createFromBoolean(boolean z) throws IOException, JsonProcessingException {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON boolean value");
    }
}
