package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.impl.FailingSerializer;
import org.codehaus.jackson.map.ser.impl.ReadOnlyClassToSerializerMap;
import org.codehaus.jackson.map.ser.impl.SerializerCache;
import org.codehaus.jackson.map.ser.impl.UnknownSerializer;
import org.codehaus.jackson.map.ser.std.NullSerializer;
import org.codehaus.jackson.map.ser.std.StdKeySerializers;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.RootNameLookup;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StdSerializerProvider extends SerializerProvider {
    static final boolean CACHE_UNKNOWN_MAPPINGS = false;
    protected DateFormat _dateFormat;
    protected JsonSerializer<Object> _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer<Object> _nullKeySerializer;
    protected JsonSerializer<Object> _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected JsonSerializer<Object> _unknownTypeSerializer;
    public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    @Deprecated
    public static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER = new org.codehaus.jackson.map.ser.std.StdKeySerializer();
    public static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();

    public StdSerializerProvider() {
        super(null);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._serializerFactory = null;
        this._serializerCache = new SerializerCache();
        this._knownSerializers = null;
        this._rootNames = new RootNameLookup();
    }

    protected StdSerializerProvider(SerializationConfig serializationConfig, StdSerializerProvider stdSerializerProvider, SerializerFactory serializerFactory) {
        super(serializationConfig);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationConfig == null) {
            throw new NullPointerException();
        }
        this._serializerFactory = serializerFactory;
        this._serializerCache = stdSerializerProvider._serializerCache;
        this._unknownTypeSerializer = stdSerializerProvider._unknownTypeSerializer;
        this._keySerializer = stdSerializerProvider._keySerializer;
        this._nullValueSerializer = stdSerializerProvider._nullValueSerializer;
        this._nullKeySerializer = stdSerializerProvider._nullKeySerializer;
        this._rootNames = stdSerializerProvider._rootNames;
        this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
    }

    protected StdSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        return new StdSerializerProvider(serializationConfig, this, serializerFactory);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void setDefaultKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._keySerializer = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void setNullValueSerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullValueSerializer = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void setNullKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullKeySerializer = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, SerializerFactory serializerFactory) throws IOException, JsonGenerationException {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        createInstance._serializeValue(jsonGenerator, obj);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, JavaType javaType, SerializerFactory serializerFactory) throws IOException, JsonGenerationException {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        createInstance._serializeValue(jsonGenerator, obj, javaType);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSchema generateJsonSchema(Class<?> cls, SerializationConfig serializationConfig, SerializerFactory serializerFactory) throws JsonMappingException {
        if (cls == null) {
            throw new IllegalArgumentException("A class must be provided");
        }
        StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        JsonSerializer<Object> findValueSerializer = createInstance.findValueSerializer(cls, (BeanProperty) null);
        JsonNode schema = findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(createInstance, null) : JsonSchema.getDefaultSchemaNode();
        if (!(schema instanceof ObjectNode)) {
            throw new IllegalArgumentException("Class " + cls.getName() + " would not be serialized as a JSON object and therefore has no schema");
        }
        return new JsonSchema((ObjectNode) schema);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public boolean hasSerializerFor(SerializationConfig serializationConfig, Class<?> cls, SerializerFactory serializerFactory) {
        return createInstance(serializationConfig, serializerFactory)._findExplicitUntypedSerializer(cls, null) != null;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public int cachedSerializersCount() {
        return this._serializerCache.size();
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void flushCachedSerializers() {
        this._serializerCache.flush();
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls);
        if (untypedValueSerializer == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(cls)) == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls))) == null && (untypedValueSerializer = _createAndCacheUntypedSerializer(cls, beanProperty)) == null) {
            return getUnknownTypeSerializer(cls);
        }
        return _handleContextualResolvable(untypedValueSerializer, beanProperty);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        if (untypedValueSerializer == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType)) == null && (untypedValueSerializer = _createAndCacheUntypedSerializer(javaType, beanProperty)) == null) {
            return getUnknownTypeSerializer(javaType.getRawClass());
        }
        return _handleContextualResolvable(untypedValueSerializer, beanProperty);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(cls);
        if (typedValueSerializer != null) {
            return typedValueSerializer;
        }
        JsonSerializer<Object> typedValueSerializer2 = this._serializerCache.typedValueSerializer(cls);
        if (typedValueSerializer2 != null) {
            return typedValueSerializer2;
        }
        JsonSerializer<Object> findValueSerializer = findValueSerializer(cls, beanProperty);
        TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType(cls), beanProperty);
        if (createTypeSerializer != null) {
            findValueSerializer = new WrappedSerializer(createTypeSerializer, findValueSerializer);
        }
        if (z) {
            this._serializerCache.addTypedSerializer(cls, findValueSerializer);
        }
        return findValueSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(javaType);
        if (typedValueSerializer != null) {
            return typedValueSerializer;
        }
        JsonSerializer<Object> typedValueSerializer2 = this._serializerCache.typedValueSerializer(javaType);
        if (typedValueSerializer2 != null) {
            return typedValueSerializer2;
        }
        JsonSerializer<Object> findValueSerializer = findValueSerializer(javaType, beanProperty);
        TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, javaType, beanProperty);
        if (createTypeSerializer != null) {
            findValueSerializer = new WrappedSerializer(createTypeSerializer, findValueSerializer);
        }
        if (z) {
            this._serializerCache.addTypedSerializer(javaType, findValueSerializer);
        }
        return findValueSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> createKeySerializer = this._serializerFactory.createKeySerializer(this._config, javaType, beanProperty);
        if (createKeySerializer == null && (createKeySerializer = this._keySerializer) == null) {
            createKeySerializer = StdKeySerializers.getStdKeySerializer(javaType);
        }
        return createKeySerializer instanceof ContextualSerializer ? ((ContextualSerializer) createKeySerializer).createContextual(this._config, beanProperty) : createKeySerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getNullKeySerializer() {
        return this._nullKeySerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getNullValueSerializer() {
        return this._nullValueSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls) {
        return this._unknownTypeSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public final void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(j);
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(new Date(j)));
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public final void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(date.getTime());
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(date));
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void defaultSerializeDateKey(long j, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(j));
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeFieldName(this._dateFormat.format(new Date(j)));
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void defaultSerializeDateKey(Date date, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(date.getTime()));
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeFieldName(this._dateFormat.format(date));
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonProcessingException {
        JsonSerializer<Object> findTypedValueSerializer;
        boolean isEnabled;
        if (obj == null) {
            findTypedValueSerializer = getNullValueSerializer();
            isEnabled = false;
        } else {
            findTypedValueSerializer = findTypedValueSerializer(obj.getClass(), true, (BeanProperty) null);
            isEnabled = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(obj.getClass(), this._config));
            }
        }
        try {
            findTypedValueSerializer.serialize(obj, jsonGenerator, this);
            if (isEnabled) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = "[no message for " + e2.getClass().getName() + "]";
            }
            throw new JsonMappingException(message, e2);
        }
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType) throws IOException, JsonProcessingException {
        JsonSerializer<Object> jsonSerializer;
        boolean z;
        if (obj == null) {
            jsonSerializer = getNullValueSerializer();
            z = false;
        } else {
            if (!javaType.getRawClass().isAssignableFrom(obj.getClass())) {
                _reportIncompatibleRootType(obj, javaType);
            }
            JsonSerializer<Object> findTypedValueSerializer = findTypedValueSerializer(javaType, true, (BeanProperty) null);
            boolean isEnabled = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(javaType, this._config));
            }
            jsonSerializer = findTypedValueSerializer;
            z = isEnabled;
        }
        try {
            jsonSerializer.serialize(obj, jsonGenerator, this);
            if (z) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = "[no message for " + e2.getClass().getName() + "]";
            }
            throw new JsonMappingException(message, e2);
        }
    }

    protected void _reportIncompatibleRootType(Object obj, JavaType javaType) throws IOException, JsonProcessingException {
        if (javaType.isPrimitive() && ClassUtil.wrapperType(javaType.getRawClass()).isAssignableFrom(obj.getClass())) {
            return;
        }
        throw new JsonMappingException("Incompatible types: declared root type (" + javaType + ") vs " + obj.getClass().getName());
    }

    protected JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> cls, BeanProperty beanProperty) {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        JsonSerializer<Object> untypedValueSerializer2 = this._serializerCache.untypedValueSerializer(cls);
        if (untypedValueSerializer2 != null) {
            return untypedValueSerializer2;
        }
        try {
            return _createAndCacheUntypedSerializer(cls, beanProperty);
        } catch (Exception unused) {
            return null;
        }
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        try {
            JsonSerializer<Object> _createUntypedSerializer = _createUntypedSerializer(this._config.constructType(cls), beanProperty);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(cls, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        try {
            JsonSerializer<Object> _createUntypedSerializer = _createUntypedSerializer(javaType, beanProperty);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(javaType, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createUntypedSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return this._serializerFactory.createSerializer(this._config, javaType, beanProperty);
    }

    protected JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<Object> jsonSerializer, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> createContextual;
        if ((jsonSerializer instanceof ContextualSerializer) && (createContextual = ((ContextualSerializer) jsonSerializer).createContextual(this._config, beanProperty)) != jsonSerializer) {
            if (createContextual instanceof ResolvableSerializer) {
                ((ResolvableSerializer) createContextual).resolve(this);
            }
            return createContextual;
        }
        return jsonSerializer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class WrappedSerializer extends JsonSerializer<Object> {
        protected final JsonSerializer<Object> _serializer;
        protected final TypeSerializer _typeSerializer;

        public WrappedSerializer(TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            this._typeSerializer = typeSerializer;
            this._serializer = jsonSerializer;
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, this._typeSerializer);
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public Class<Object> handledType() {
            return Object.class;
        }
    }
}
