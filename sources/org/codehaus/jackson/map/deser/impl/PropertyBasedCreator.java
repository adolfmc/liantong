package org.codehaus.jackson.map.deser.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.SettableBeanProperty;
import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.util.ClassUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class PropertyBasedCreator {
    protected Object[] _defaultValues;
    protected final HashMap<String, SettableBeanProperty> _properties = new HashMap<>();
    protected final SettableBeanProperty[] _propertiesWithInjectables;
    protected final int _propertyCount;
    protected final ValueInstantiator _valueInstantiator;

    public PropertyBasedCreator(ValueInstantiator valueInstantiator) {
        this._valueInstantiator = valueInstantiator;
        SettableBeanProperty[] fromObjectArguments = valueInstantiator.getFromObjectArguments();
        int length = fromObjectArguments.length;
        this._propertyCount = length;
        Object[] objArr = null;
        SettableBeanProperty[] settableBeanPropertyArr = null;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = fromObjectArguments[i];
            this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
            if (settableBeanProperty.getType().isPrimitive()) {
                objArr = objArr == null ? new Object[length] : objArr;
                objArr[i] = ClassUtil.defaultValue(settableBeanProperty.getType().getRawClass());
            }
            if (settableBeanProperty.getInjectableValueId() != null) {
                settableBeanPropertyArr = settableBeanPropertyArr == null ? new SettableBeanProperty[length] : settableBeanPropertyArr;
                settableBeanPropertyArr[i] = settableBeanProperty;
            }
        }
        this._defaultValues = objArr;
        this._propertiesWithInjectables = settableBeanPropertyArr;
    }

    public Collection<SettableBeanProperty> getCreatorProperties() {
        return this._properties.values();
    }

    public SettableBeanProperty findCreatorProperty(String str) {
        return this._properties.get(str);
    }

    public void assignDeserializer(SettableBeanProperty settableBeanProperty, JsonDeserializer<Object> jsonDeserializer) {
        SettableBeanProperty withValueDeserializer = settableBeanProperty.withValueDeserializer(jsonDeserializer);
        this._properties.put(withValueDeserializer.getName(), withValueDeserializer);
        Object nullValue = jsonDeserializer.getNullValue();
        if (nullValue != null) {
            if (this._defaultValues == null) {
                this._defaultValues = new Object[this._properties.size()];
            }
            this._defaultValues[withValueDeserializer.getPropertyIndex()] = nullValue;
        }
    }

    public PropertyValueBuffer startBuilding(JsonParser jsonParser, DeserializationContext deserializationContext) {
        PropertyValueBuffer propertyValueBuffer = new PropertyValueBuffer(jsonParser, deserializationContext, this._propertyCount);
        SettableBeanProperty[] settableBeanPropertyArr = this._propertiesWithInjectables;
        if (settableBeanPropertyArr != null) {
            propertyValueBuffer.inject(settableBeanPropertyArr);
        }
        return propertyValueBuffer;
    }

    public Object build(PropertyValueBuffer propertyValueBuffer) throws IOException {
        Object createFromObjectWith = this._valueInstantiator.createFromObjectWith(propertyValueBuffer.getParameters(this._defaultValues));
        for (PropertyValue buffered = propertyValueBuffer.buffered(); buffered != null; buffered = buffered.next) {
            buffered.assign(createFromObjectWith);
        }
        return createFromObjectWith;
    }
}
