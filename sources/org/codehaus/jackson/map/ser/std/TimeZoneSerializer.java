package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.util.TimeZone;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TimeZoneSerializer extends ScalarSerializerBase<TimeZone> {
    public static final TimeZoneSerializer instance = new TimeZoneSerializer();

    public TimeZoneSerializer() {
        super(TimeZone.class);
    }

    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serialize(TimeZone timeZone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeString(timeZone.getID());
    }

    @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(TimeZone timeZone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForScalar(timeZone, jsonGenerator, TimeZone.class);
        serialize(timeZone, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(timeZone, jsonGenerator);
    }
}
