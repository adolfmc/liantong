package org.codehaus.jackson.map.deser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.SettableBeanProperty;
import org.codehaus.jackson.util.TokenBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ExternalTypeHandler {
    private final HashMap<String, Integer> _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    protected ExternalTypeHandler(ExtTypedProperty[] extTypedPropertyArr, HashMap<String, Integer> hashMap, String[] strArr, TokenBuffer[] tokenBufferArr) {
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = hashMap;
        this._typeIds = strArr;
        this._tokens = tokenBufferArr;
    }

    protected ExternalTypeHandler(ExternalTypeHandler externalTypeHandler) {
        this._properties = externalTypeHandler._properties;
        this._nameToPropertyIndex = externalTypeHandler._nameToPropertyIndex;
        int length = this._properties.length;
        this._typeIds = new String[length];
        this._tokens = new TokenBuffer[length];
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }

    public boolean handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException, JsonProcessingException {
        Integer num = this._nameToPropertyIndex.get(str);
        boolean z = false;
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (this._properties[intValue].hasTypePropertyName(str)) {
            this._typeIds[intValue] = jsonParser.getText();
            if (obj != null && this._tokens[intValue] != null) {
                z = true;
            }
            if (z) {
                _deserialize(jsonParser, deserializationContext, obj, intValue);
                this._typeIds[intValue] = null;
                this._tokens[intValue] = null;
            }
            return true;
        }
        return false;
    }

    public boolean handleToken(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException, JsonProcessingException {
        Integer num = this._nameToPropertyIndex.get(str);
        boolean z = false;
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (this._properties[intValue].hasTypePropertyName(str)) {
            this._typeIds[intValue] = jsonParser.getText();
            jsonParser.skipChildren();
            if (obj != null && this._tokens[intValue] != null) {
                z = true;
            }
        } else {
            TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
            tokenBuffer.copyCurrentStructure(jsonParser);
            this._tokens[intValue] = tokenBuffer;
            if (obj != null && this._typeIds[intValue] != null) {
                z = true;
            }
        }
        if (z) {
            _deserialize(jsonParser, deserializationContext, obj, intValue);
            this._typeIds[intValue] = null;
            this._tokens[intValue] = null;
        }
        return true;
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        int length = this._properties.length;
        for (int i = 0; i < length; i++) {
            if (this._typeIds[i] == null) {
                if (this._tokens[i] != null) {
                    throw deserializationContext.mappingException("Missing external type id property '" + this._properties[i].getTypePropertyName() + "'");
                }
            } else if (this._tokens[i] == null) {
                SettableBeanProperty property = this._properties[i].getProperty();
                throw deserializationContext.mappingException("Missing property '" + property.getName() + "' for external type id '" + this._properties[i].getTypePropertyName());
            } else {
                _deserialize(jsonParser, deserializationContext, obj, i);
            }
        }
        return obj;
    }

    protected final void _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, int i) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(this._typeIds[i]);
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        asParser.nextToken();
        tokenBuffer.copyCurrentStructure(asParser);
        tokenBuffer.writeEndArray();
        JsonParser asParser2 = tokenBuffer.asParser(jsonParser);
        asParser2.nextToken();
        this._properties[i].getProperty().deserializeAndSet(asParser2, deserializationContext, obj);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Builder {
        private final ArrayList<ExtTypedProperty> _properties = new ArrayList<>();
        private final HashMap<String, Integer> _nameToPropertyIndex = new HashMap<>();

        public void addExternal(SettableBeanProperty settableBeanProperty, String str) {
            Integer valueOf = Integer.valueOf(this._properties.size());
            this._properties.add(new ExtTypedProperty(settableBeanProperty, str));
            this._nameToPropertyIndex.put(settableBeanProperty.getName(), valueOf);
            this._nameToPropertyIndex.put(str, valueOf);
        }

        public ExternalTypeHandler build() {
            ArrayList<ExtTypedProperty> arrayList = this._properties;
            return new ExternalTypeHandler((ExtTypedProperty[]) arrayList.toArray(new ExtTypedProperty[arrayList.size()]), this._nameToPropertyIndex, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final String _typePropertyName;

        public ExtTypedProperty(SettableBeanProperty settableBeanProperty, String str) {
            this._property = settableBeanProperty;
            this._typePropertyName = str;
        }

        public boolean hasTypePropertyName(String str) {
            return str.equals(this._typePropertyName);
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public SettableBeanProperty getProperty() {
            return this._property;
        }
    }
}
