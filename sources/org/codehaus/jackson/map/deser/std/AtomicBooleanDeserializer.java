package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
    public AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public AtomicBoolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return new AtomicBoolean(_parseBooleanPrimitive(jsonParser, deserializationContext));
    }
}
