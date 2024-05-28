package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DLApplicationSpecific extends ASN1ApplicationSpecific {
    public DLApplicationSpecific(int i, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, i, aSN1Encodable);
    }

    public DLApplicationSpecific(int i, ASN1EncodableVector aSN1EncodableVector) {
        super(new DLTaggedObject(false, 64, i, (ASN1Encodable) DLFactory.createSequence(aSN1EncodableVector)));
    }

    public DLApplicationSpecific(int i, byte[] bArr) {
        super(new DLTaggedObject(false, 64, i, (ASN1Encodable) new DEROctetString(Arrays.clone(bArr))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLApplicationSpecific(ASN1TaggedObject aSN1TaggedObject) {
        super(aSN1TaggedObject);
    }

    public DLApplicationSpecific(boolean z, int i, ASN1Encodable aSN1Encodable) throws IOException {
        super(new DLTaggedObject(z, 64, i, aSN1Encodable));
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecific, org.bouncycastle.asn1.ASN1TaggedObject, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDLObject() {
        return this;
    }
}
