package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import org.codehaus.jackson.map.type.ArrayType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ArrayDeserializer extends ObjectArrayDeserializer {
    @Deprecated
    public ArrayDeserializer(ArrayType arrayType, JsonDeserializer<Object> jsonDeserializer) {
        this(arrayType, jsonDeserializer, null);
    }

    public ArrayDeserializer(ArrayType arrayType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(arrayType, jsonDeserializer, typeDeserializer);
    }
}
