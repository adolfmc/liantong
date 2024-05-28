package org.codehaus.jackson.map.deser.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.deser.SettableAnyProperty;
import org.codehaus.jackson.map.deser.SettableBeanProperty;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class PropertyValue {
    public final PropertyValue next;
    public final Object value;

    public abstract void assign(Object obj) throws IOException, JsonProcessingException;

    protected PropertyValue(PropertyValue propertyValue, Object obj) {
        this.next = propertyValue;
        this.value = obj;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class Regular extends PropertyValue {
        final SettableBeanProperty _property;

        public Regular(PropertyValue propertyValue, Object obj, SettableBeanProperty settableBeanProperty) {
            super(propertyValue, obj);
            this._property = settableBeanProperty;
        }

        @Override // org.codehaus.jackson.map.deser.impl.PropertyValue
        public void assign(Object obj) throws IOException, JsonProcessingException {
            this._property.set(obj, this.value);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class Any extends PropertyValue {
        final SettableAnyProperty _property;
        final String _propertyName;

        public Any(PropertyValue propertyValue, Object obj, SettableAnyProperty settableAnyProperty, String str) {
            super(propertyValue, obj);
            this._property = settableAnyProperty;
            this._propertyName = str;
        }

        @Override // org.codehaus.jackson.map.deser.impl.PropertyValue
        public void assign(Object obj) throws IOException, JsonProcessingException {
            this._property.set(obj, this._propertyName, this.value);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class Map extends PropertyValue {
        final Object _key;

        public Map(PropertyValue propertyValue, Object obj, Object obj2) {
            super(propertyValue, obj);
            this._key = obj2;
        }

        @Override // org.codehaus.jackson.map.deser.impl.PropertyValue
        public void assign(Object obj) throws IOException, JsonProcessingException {
            ((java.util.Map) obj).put(this._key, this.value);
        }
    }
}
