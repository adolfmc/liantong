package org.codehaus.jackson.map;

import java.io.IOException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.format.InputAccessor;
import org.codehaus.jackson.format.MatchStrength;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MappingJsonFactory extends JsonFactory {
    @Override // org.codehaus.jackson.JsonFactory
    public String getFormatName() {
        return "JSON";
    }

    public MappingJsonFactory() {
        this(null);
    }

    public MappingJsonFactory(ObjectMapper objectMapper) {
        super(objectMapper);
        if (objectMapper == null) {
            setCodec(new ObjectMapper(this));
        }
    }

    @Override // org.codehaus.jackson.JsonFactory
    public final ObjectMapper getCodec() {
        return (ObjectMapper) this._objectCodec;
    }

    @Override // org.codehaus.jackson.JsonFactory
    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        return hasJSONFormat(inputAccessor);
    }
}
