package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.util.TokenBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
    public TokenBufferDeserializer() {
        super(TokenBuffer.class);
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.copyCurrentStructure(jsonParser);
        return tokenBuffer;
    }
}
