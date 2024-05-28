package org.codehaus.jackson.map;

import java.io.IOException;
import org.codehaus.jackson.JsonProcessingException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class DeserializationProblemHandler {
    public boolean handleUnknownProperty(DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
        return false;
    }
}
