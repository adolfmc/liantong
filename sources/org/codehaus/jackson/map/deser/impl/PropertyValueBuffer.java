package org.codehaus.jackson.map.deser.impl;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.SettableAnyProperty;
import org.codehaus.jackson.map.deser.SettableBeanProperty;
import org.codehaus.jackson.map.deser.impl.PropertyValue;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class PropertyValueBuffer {
    private PropertyValue _buffered;
    final DeserializationContext _context;
    final Object[] _creatorParameters;
    private int _paramsNeeded;
    final JsonParser _parser;

    public PropertyValueBuffer(JsonParser jsonParser, DeserializationContext deserializationContext, int i) {
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._paramsNeeded = i;
        this._creatorParameters = new Object[i];
    }

    public void inject(SettableBeanProperty[] settableBeanPropertyArr) {
        int length = settableBeanPropertyArr.length;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
            if (settableBeanProperty != null) {
                this._creatorParameters[i] = this._context.findInjectableValue(settableBeanProperty.getInjectableValueId(), settableBeanProperty, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object[] getParameters(Object[] objArr) {
        Object obj;
        if (objArr != null) {
            int length = this._creatorParameters.length;
            for (int i = 0; i < length; i++) {
                Object[] objArr2 = this._creatorParameters;
                if (objArr2[i] == null && (obj = objArr[i]) != null) {
                    objArr2[i] = obj;
                }
            }
        }
        return this._creatorParameters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PropertyValue buffered() {
        return this._buffered;
    }

    public boolean assignParameter(int i, Object obj) {
        this._creatorParameters[i] = obj;
        int i2 = this._paramsNeeded - 1;
        this._paramsNeeded = i2;
        return i2 <= 0;
    }

    public void bufferProperty(SettableBeanProperty settableBeanProperty, Object obj) {
        this._buffered = new PropertyValue.Regular(this._buffered, obj, settableBeanProperty);
    }

    public void bufferAnyProperty(SettableAnyProperty settableAnyProperty, String str, Object obj) {
        this._buffered = new PropertyValue.Any(this._buffered, obj, settableAnyProperty, str);
    }

    public void bufferMapProperty(Object obj, Object obj2) {
        this._buffered = new PropertyValue.Map(this._buffered, obj2, obj);
    }
}
