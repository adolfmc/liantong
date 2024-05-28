package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.net.InetAddress;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class InetAddressSerializer extends ScalarSerializerBase<InetAddress> {
    public static final InetAddressSerializer instance = new InetAddressSerializer();

    public InetAddressSerializer() {
        super(InetAddress.class);
    }

    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serialize(InetAddress inetAddress, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        String trim = inetAddress.toString().trim();
        int indexOf = trim.indexOf(47);
        if (indexOf >= 0) {
            if (indexOf == 0) {
                trim = trim.substring(1);
            } else {
                trim = trim.substring(0, indexOf);
            }
        }
        jsonGenerator.writeString(trim);
    }

    @Override // org.codehaus.jackson.map.ser.std.ScalarSerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(InetAddress inetAddress, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForScalar(inetAddress, jsonGenerator, InetAddress.class);
        serialize(inetAddress, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(inetAddress, jsonGenerator);
    }
}
