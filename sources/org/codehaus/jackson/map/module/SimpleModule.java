package org.codehaus.jackson.map.module;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.Module;
import org.codehaus.jackson.map.deser.ValueInstantiator;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SimpleModule extends Module {
    protected final String _name;
    protected final Version _version;
    protected SimpleSerializers _serializers = null;
    protected SimpleDeserializers _deserializers = null;
    protected SimpleSerializers _keySerializers = null;
    protected SimpleKeyDeserializers _keyDeserializers = null;
    protected SimpleAbstractTypeResolver _abstractTypes = null;
    protected SimpleValueInstantiators _valueInstantiators = null;
    protected HashMap<Class<?>, Class<?>> _mixins = null;

    public SimpleModule(String str, Version version) {
        this._name = str;
        this._version = version;
    }

    public void setSerializers(SimpleSerializers simpleSerializers) {
        this._serializers = simpleSerializers;
    }

    public void setDeserializers(SimpleDeserializers simpleDeserializers) {
        this._deserializers = simpleDeserializers;
    }

    public void setKeySerializers(SimpleSerializers simpleSerializers) {
        this._keySerializers = simpleSerializers;
    }

    public void setKeyDeserializers(SimpleKeyDeserializers simpleKeyDeserializers) {
        this._keyDeserializers = simpleKeyDeserializers;
    }

    public void setAbstractTypes(SimpleAbstractTypeResolver simpleAbstractTypeResolver) {
        this._abstractTypes = simpleAbstractTypeResolver;
    }

    public void setValueInstantiators(SimpleValueInstantiators simpleValueInstantiators) {
        this._valueInstantiators = simpleValueInstantiators;
    }

    public SimpleModule addSerializer(JsonSerializer<?> jsonSerializer) {
        if (this._serializers == null) {
            this._serializers = new SimpleSerializers();
        }
        this._serializers.addSerializer(jsonSerializer);
        return this;
    }

    public <T> SimpleModule addSerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        if (this._serializers == null) {
            this._serializers = new SimpleSerializers();
        }
        this._serializers.addSerializer(cls, jsonSerializer);
        return this;
    }

    public <T> SimpleModule addKeySerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        if (this._keySerializers == null) {
            this._keySerializers = new SimpleSerializers();
        }
        this._keySerializers.addSerializer(cls, jsonSerializer);
        return this;
    }

    public <T> SimpleModule addDeserializer(Class<T> cls, JsonDeserializer<? extends T> jsonDeserializer) {
        if (this._deserializers == null) {
            this._deserializers = new SimpleDeserializers();
        }
        this._deserializers.addDeserializer(cls, jsonDeserializer);
        return this;
    }

    public SimpleModule addKeyDeserializer(Class<?> cls, KeyDeserializer keyDeserializer) {
        if (this._keyDeserializers == null) {
            this._keyDeserializers = new SimpleKeyDeserializers();
        }
        this._keyDeserializers.addDeserializer(cls, keyDeserializer);
        return this;
    }

    public <T> SimpleModule addAbstractTypeMapping(Class<T> cls, Class<? extends T> cls2) {
        if (this._abstractTypes == null) {
            this._abstractTypes = new SimpleAbstractTypeResolver();
        }
        this._abstractTypes = this._abstractTypes.addMapping(cls, cls2);
        return this;
    }

    public SimpleModule addValueInstantiator(Class<?> cls, ValueInstantiator valueInstantiator) {
        if (this._valueInstantiators == null) {
            this._valueInstantiators = new SimpleValueInstantiators();
        }
        this._valueInstantiators = this._valueInstantiators.addValueInstantiator(cls, valueInstantiator);
        return this;
    }

    public SimpleModule setMixInAnnotation(Class<?> cls, Class<?> cls2) {
        if (this._mixins == null) {
            this._mixins = new HashMap<>();
        }
        this._mixins.put(cls, cls2);
        return this;
    }

    @Override // org.codehaus.jackson.map.Module
    public String getModuleName() {
        return this._name;
    }

    @Override // org.codehaus.jackson.map.Module
    public void setupModule(Module.SetupContext setupContext) {
        SimpleSerializers simpleSerializers = this._serializers;
        if (simpleSerializers != null) {
            setupContext.addSerializers(simpleSerializers);
        }
        SimpleDeserializers simpleDeserializers = this._deserializers;
        if (simpleDeserializers != null) {
            setupContext.addDeserializers(simpleDeserializers);
        }
        SimpleSerializers simpleSerializers2 = this._keySerializers;
        if (simpleSerializers2 != null) {
            setupContext.addKeySerializers(simpleSerializers2);
        }
        SimpleKeyDeserializers simpleKeyDeserializers = this._keyDeserializers;
        if (simpleKeyDeserializers != null) {
            setupContext.addKeyDeserializers(simpleKeyDeserializers);
        }
        SimpleAbstractTypeResolver simpleAbstractTypeResolver = this._abstractTypes;
        if (simpleAbstractTypeResolver != null) {
            setupContext.addAbstractTypeResolver(simpleAbstractTypeResolver);
        }
        SimpleValueInstantiators simpleValueInstantiators = this._valueInstantiators;
        if (simpleValueInstantiators != null) {
            setupContext.addValueInstantiators(simpleValueInstantiators);
        }
        HashMap<Class<?>, Class<?>> hashMap = this._mixins;
        if (hashMap != null) {
            for (Map.Entry<Class<?>, Class<?>> entry : hashMap.entrySet()) {
                setupContext.setMixInAnnotations(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override // org.codehaus.jackson.map.Module, org.codehaus.jackson.Versioned
    public Version version() {
        return this._version;
    }
}
