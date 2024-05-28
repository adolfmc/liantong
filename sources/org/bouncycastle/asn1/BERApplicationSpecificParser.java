package org.bouncycastle.asn1;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BERApplicationSpecificParser extends BERTaggedObjectParser implements ASN1ApplicationSpecificParser {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BERApplicationSpecificParser(int i, ASN1StreamParser aSN1StreamParser) {
        super(64, i, aSN1StreamParser);
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecificParser
    public ASN1Encodable readObject() throws IOException {
        return parseExplicitBaseObject();
    }
}
