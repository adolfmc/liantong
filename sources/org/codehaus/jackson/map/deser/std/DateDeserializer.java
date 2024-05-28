package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.Date;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DateDeserializer extends StdScalarDeserializer<Date> {
    public DateDeserializer() {
        super(Date.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _parseDate(jsonParser, deserializationContext);
    }
}
