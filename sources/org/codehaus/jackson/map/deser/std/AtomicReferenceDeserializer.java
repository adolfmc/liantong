package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements ResolvableDeserializer {
    protected final BeanProperty _property;
    protected final JavaType _referencedType;
    protected JsonDeserializer<?> _valueDeserializer;

    public AtomicReferenceDeserializer(JavaType javaType, BeanProperty beanProperty) {
        super(AtomicReference.class);
        this._referencedType = javaType;
        this._property = beanProperty;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public AtomicReference<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return new AtomicReference<>(this._valueDeserializer.deserialize(jsonParser, deserializationContext));
    }

    @Override // org.codehaus.jackson.map.ResolvableDeserializer
    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) throws JsonMappingException {
        this._valueDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, this._referencedType, this._property);
    }
}
