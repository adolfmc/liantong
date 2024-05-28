package org.codehaus.jackson.map.ser;

import java.util.HashSet;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
@Deprecated
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MapSerializer extends org.codehaus.jackson.map.ser.std.MapSerializer {
    protected MapSerializer() {
        this(null, null, null, false, null, null, null, null);
    }

    @Deprecated
    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        super(hashSet, UNSPECIFIED_TYPE, javaType, z, typeSerializer, null, null, null);
    }

    @Deprecated
    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, BeanProperty beanProperty) {
        super(hashSet, javaType, javaType2, z, typeSerializer, jsonSerializer, null, beanProperty);
    }

    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2, BeanProperty beanProperty) {
        super(hashSet, javaType, javaType2, z, typeSerializer, jsonSerializer, jsonSerializer2, beanProperty);
    }
}
