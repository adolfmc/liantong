package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.deser.BeanDeserializer;
import org.codehaus.jackson.map.deser.SettableBeanProperty;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
    }

    protected ThrowableDeserializer(BeanDeserializer beanDeserializer, boolean z) {
        super(beanDeserializer, z);
    }

    @Override // org.codehaus.jackson.map.deser.BeanDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public JsonDeserializer<Object> unwrappingDeserializer() {
        return getClass() != ThrowableDeserializer.class ? this : new ThrowableDeserializer(this, true);
    }

    @Override // org.codehaus.jackson.map.deser.BeanDeserializer
    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._beanType.isAbstract()) {
            throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
        }
        boolean canCreateFromString = this._valueInstantiator.canCreateFromString();
        boolean canCreateUsingDefault = this._valueInstantiator.canCreateUsingDefault();
        if (!canCreateFromString && !canCreateUsingDefault) {
            throw new JsonMappingException("Can not deserialize Throwable of type " + this._beanType + " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
        }
        int i = 0;
        Object obj = null;
        Object[] objArr = null;
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            jsonParser.nextToken();
            if (find != null) {
                if (obj != null) {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                } else {
                    if (objArr == null) {
                        int size = this._beanProperties.size();
                        objArr = new Object[size + size];
                    }
                    int i2 = i + 1;
                    objArr[i] = find;
                    i = i2 + 1;
                    objArr[i2] = find.deserialize(jsonParser, deserializationContext);
                }
            } else if ("message".equals(currentName) && canCreateFromString) {
                obj = this._valueInstantiator.createFromString(jsonParser.getText());
                if (objArr != null) {
                    for (int i3 = 0; i3 < i; i3 += 2) {
                        ((SettableBeanProperty) objArr[i3]).set(obj, objArr[i3 + 1]);
                    }
                    objArr = null;
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                jsonParser.skipChildren();
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
            } else {
                handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
            }
            jsonParser.nextToken();
        }
        if (obj == null) {
            if (canCreateFromString) {
                obj = this._valueInstantiator.createFromString(null);
            } else {
                obj = this._valueInstantiator.createUsingDefault();
            }
            if (objArr != null) {
                for (int i4 = 0; i4 < i; i4 += 2) {
                    ((SettableBeanProperty) objArr[i4]).set(obj, objArr[i4 + 1]);
                }
            }
        }
        return obj;
    }
}
