package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.deser.SettableBeanProperty;
import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.deser.impl.PropertyBasedCreator;
import org.codehaus.jackson.map.deser.impl.PropertyValueBuffer;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements ResolvableDeserializer {
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final boolean _hasDefaultCreator;
    protected HashSet<String> _ignorableProperties;
    protected final KeyDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public MapDeserializer(JavaType javaType, Constructor<Map<Object, Object>> constructor, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(Map.class);
        this._mapType = javaType;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        StdValueInstantiator stdValueInstantiator = new StdValueInstantiator((DeserializationConfig) null, javaType);
        if (constructor != null) {
            stdValueInstantiator.configureFromObjectSettings(new AnnotatedConstructor(constructor, null, null), null, null, null, null);
        }
        this._hasDefaultCreator = constructor != null;
        this._valueInstantiator = stdValueInstantiator;
    }

    public MapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(Map.class);
        this._mapType = javaType;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = valueInstantiator;
        if (valueInstantiator.canCreateFromObjectWith()) {
            this._propertyBasedCreator = new PropertyBasedCreator(valueInstantiator);
        } else {
            this._propertyBasedCreator = null;
        }
        this._hasDefaultCreator = valueInstantiator.canCreateUsingDefault();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MapDeserializer(MapDeserializer mapDeserializer) {
        super(mapDeserializer._valueClass);
        this._mapType = mapDeserializer._mapType;
        this._keyDeserializer = mapDeserializer._keyDeserializer;
        this._valueDeserializer = mapDeserializer._valueDeserializer;
        this._valueTypeDeserializer = mapDeserializer._valueTypeDeserializer;
        this._valueInstantiator = mapDeserializer._valueInstantiator;
        this._propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        this._delegateDeserializer = mapDeserializer._delegateDeserializer;
        this._hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        this._ignorableProperties = mapDeserializer._ignorableProperties;
    }

    public void setIgnorableProperties(String[] strArr) {
        this._ignorableProperties = (strArr == null || strArr.length == 0) ? null : ArrayBuilders.arrayToSet(strArr);
    }

    @Override // org.codehaus.jackson.map.ResolvableDeserializer
    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) throws JsonMappingException {
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType();
            if (delegateType == null) {
                throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._mapType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            }
            this._delegateDeserializer = findDeserializer(deserializationConfig, deserializerProvider, delegateType, new BeanProperty.Std(null, delegateType, null, this._valueInstantiator.getDelegateCreator()));
        }
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        if (propertyBasedCreator != null) {
            for (SettableBeanProperty settableBeanProperty : propertyBasedCreator.getCreatorProperties()) {
                if (!settableBeanProperty.hasValueDeserializer()) {
                    this._propertyBasedCreator.assignDeserializer(settableBeanProperty, findDeserializer(deserializationConfig, deserializerProvider, settableBeanProperty.getType(), settableBeanProperty));
                }
            }
        }
    }

    @Override // org.codehaus.jackson.map.deser.std.ContainerDeserializerBase
    public JavaType getContentType() {
        return this._mapType.getContentType();
    }

    @Override // org.codehaus.jackson.map.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingCreator(jsonParser, deserializationContext);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Map) this._valueInstantiator.createUsingDelegate(jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (!this._hasDefaultCreator) {
            throw deserializationContext.instantiationException(getMapClass(), "No default constructor found");
        }
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT && currentToken != JsonToken.FIELD_NAME && currentToken != JsonToken.END_OBJECT) {
            if (currentToken == JsonToken.VALUE_STRING) {
                return (Map) this._valueInstantiator.createFromString(jsonParser.getText());
            }
            throw deserializationContext.mappingException(getMapClass());
        }
        Map<Object, Object> map = (Map) this._valueInstantiator.createUsingDefault();
        _readAndBind(jsonParser, deserializationContext, map);
        return map;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT && currentToken != JsonToken.FIELD_NAME) {
            throw deserializationContext.mappingException(getMapClass());
        }
        _readAndBind(jsonParser, deserializationContext, map);
        return map;
    }

    @Override // org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public final Class<?> getMapClass() {
        return this._mapType.getRawClass();
    }

    @Override // org.codehaus.jackson.map.deser.std.StdDeserializer
    public JavaType getValueType() {
        return this._mapType;
    }

    protected final void _readAndBind(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException, JsonProcessingException {
        Object deserializeWithType;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            Object deserializeKey = keyDeserializer.deserializeKey(currentName, deserializationContext);
            JsonToken nextToken = jsonParser.nextToken();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet != null && hashSet.contains(currentName)) {
                jsonParser.skipChildren();
            } else {
                if (nextToken == JsonToken.VALUE_NULL) {
                    deserializeWithType = null;
                } else if (typeDeserializer == null) {
                    deserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                } else {
                    deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                }
                map.put(deserializeKey, deserializeWithType);
            }
            currentToken = jsonParser.nextToken();
        }
    }

    public Map<Object, Object> _deserializeUsingCreator(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext);
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (true) {
            Object obj = null;
            if (currentToken == JsonToken.FIELD_NAME) {
                String currentName = jsonParser.getCurrentName();
                JsonToken nextToken = jsonParser.nextToken();
                HashSet<String> hashSet = this._ignorableProperties;
                if (hashSet != null && hashSet.contains(currentName)) {
                    jsonParser.skipChildren();
                } else {
                    SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
                    if (findCreatorProperty != null) {
                        if (startBuilding.assignParameter(findCreatorProperty.getPropertyIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                            jsonParser.nextToken();
                            try {
                                Map<Object, Object> map = (Map) propertyBasedCreator.build(startBuilding);
                                _readAndBind(jsonParser, deserializationContext, map);
                                return map;
                            } catch (Exception e) {
                                wrapAndThrow(e, this._mapType.getRawClass());
                                return null;
                            }
                        }
                    } else {
                        Object deserializeKey = this._keyDeserializer.deserializeKey(jsonParser.getCurrentName(), deserializationContext);
                        if (nextToken != JsonToken.VALUE_NULL) {
                            if (typeDeserializer == null) {
                                obj = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                            } else {
                                obj = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                            }
                        }
                        startBuilding.bufferMapProperty(deserializeKey, obj);
                    }
                }
                currentToken = jsonParser.nextToken();
            } else {
                try {
                    return (Map) propertyBasedCreator.build(startBuilding);
                } catch (Exception e2) {
                    wrapAndThrow(e2, this._mapType.getRawClass());
                    return null;
                }
            }
        }
    }

    protected void wrapAndThrow(Throwable th, Object obj) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        if ((th instanceof IOException) && !(th instanceof JsonMappingException)) {
            throw ((IOException) th);
        }
        throw JsonMappingException.wrapWithPath(th, obj, (String) null);
    }
}
