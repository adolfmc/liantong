package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.Map;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MapDeserializer extends org.codehaus.jackson.map.deser.std.MapDeserializer {
    @Deprecated
    public MapDeserializer(JavaType javaType, Constructor<Map<Object, Object>> constructor, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType, constructor, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    public MapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType, valueInstantiator, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    protected MapDeserializer(MapDeserializer mapDeserializer) {
        super(mapDeserializer);
    }
}
