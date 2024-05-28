package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class NonTypedScalarSerializerBase<T> extends ScalarSerializerBase<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public NonTypedScalarSerializerBase(Class<T> cls) {
        super(cls);
    }

    @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        serialize(t, jsonGenerator, serializerProvider);
    }
}
