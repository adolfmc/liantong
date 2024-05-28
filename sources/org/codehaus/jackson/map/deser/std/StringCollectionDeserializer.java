package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.Collection;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements ResolvableDeserializer {
    protected final JavaType _collectionType;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final boolean _isDefaultDeserializer;
    protected final JsonDeserializer<String> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;

    /* JADX WARN: Multi-variable type inference failed */
    public StringCollectionDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, ValueInstantiator valueInstantiator) {
        super(javaType.getRawClass());
        this._collectionType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueInstantiator = valueInstantiator;
        this._isDefaultDeserializer = isDefaultSerializer(jsonDeserializer);
    }

    protected StringCollectionDeserializer(StringCollectionDeserializer stringCollectionDeserializer) {
        super(stringCollectionDeserializer._valueClass);
        this._collectionType = stringCollectionDeserializer._collectionType;
        this._valueDeserializer = stringCollectionDeserializer._valueDeserializer;
        this._valueInstantiator = stringCollectionDeserializer._valueInstantiator;
        this._isDefaultDeserializer = stringCollectionDeserializer._isDefaultDeserializer;
    }

    @Override // org.codehaus.jackson.map.ResolvableDeserializer
    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) throws JsonMappingException {
        AnnotatedWithParams delegateCreator = this._valueInstantiator.getDelegateCreator();
        if (delegateCreator != null) {
            JavaType delegateType = this._valueInstantiator.getDelegateType();
            this._delegateDeserializer = findDeserializer(deserializationConfig, deserializerProvider, delegateType, new BeanProperty.Std(null, delegateType, null, delegateCreator));
        }
    }

    @Override // org.codehaus.jackson.map.deser.std.ContainerDeserializerBase
    public JavaType getContentType() {
        return this._collectionType.getContentType();
    }

    @Override // org.codehaus.jackson.map.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        return deserialize(jsonParser, deserializationContext, (Collection) this._valueInstantiator.createUsingDefault());
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, collection);
        }
        if (!this._isDefaultDeserializer) {
            return deserializeUsingCustom(jsonParser, deserializationContext, collection);
        }
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            collection.add(nextToken == JsonToken.VALUE_NULL ? null : jsonParser.getText());
        }
    }

    private Collection<String> deserializeUsingCustom(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            collection.add(nextToken == JsonToken.VALUE_NULL ? null : jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
    }

    @Override // org.codehaus.jackson.map.deser.std.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private final Collection<String> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        String text;
        if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            throw deserializationContext.mappingException(this._collectionType.getRawClass());
        }
        JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            text = null;
        } else {
            text = jsonDeserializer == null ? jsonParser.getText() : jsonDeserializer.deserialize(jsonParser, deserializationContext);
        }
        collection.add(text);
        return collection;
    }
}
