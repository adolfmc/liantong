package org.codehaus.jackson.map.ser.impl;

import java.util.HashMap;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ser.impl.SerializerCache;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ReadOnlyClassToSerializerMap {
    protected final SerializerCache.TypeKey _cacheKey = new SerializerCache.TypeKey(getClass(), false);
    protected final JsonSerializerMap _map;

    private ReadOnlyClassToSerializerMap(JsonSerializerMap jsonSerializerMap) {
        this._map = jsonSerializerMap;
    }

    public ReadOnlyClassToSerializerMap instance() {
        return new ReadOnlyClassToSerializerMap(this._map);
    }

    public static ReadOnlyClassToSerializerMap from(HashMap<SerializerCache.TypeKey, JsonSerializer<Object>> hashMap) {
        return new ReadOnlyClassToSerializerMap(new JsonSerializerMap(hashMap));
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        this._cacheKey.resetTyped(javaType);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        this._cacheKey.resetTyped(cls);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        this._cacheKey.resetUntyped(cls);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        this._cacheKey.resetUntyped(javaType);
        return this._map.find(this._cacheKey);
    }
}
