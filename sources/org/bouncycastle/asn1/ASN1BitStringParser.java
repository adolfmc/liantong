package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ASN1BitStringParser extends ASN1Encodable, InMemoryRepresentable {
    InputStream getBitStream() throws IOException;

    InputStream getOctetStream() throws IOException;

    int getPadBits();
}
