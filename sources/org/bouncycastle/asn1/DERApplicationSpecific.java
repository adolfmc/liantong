package org.bouncycastle.asn1;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DERApplicationSpecific extends ASN1ApplicationSpecific {
    public DERApplicationSpecific(int i, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, i, aSN1Encodable);
    }

    public DERApplicationSpecific(int i, ASN1EncodableVector aSN1EncodableVector) {
        super(new DERTaggedObject(false, 64, i, (ASN1Encodable) DERFactory.createSequence(aSN1EncodableVector)));
    }

    public DERApplicationSpecific(int i, byte[] bArr) {
        super(new DERTaggedObject(false, 64, i, (ASN1Encodable) new DEROctetString(bArr)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERApplicationSpecific(ASN1TaggedObject aSN1TaggedObject) {
        super(aSN1TaggedObject);
    }

    public DERApplicationSpecific(boolean z, int i, ASN1Encodable aSN1Encodable) throws IOException {
        super(new DERTaggedObject(z, 64, i, aSN1Encodable));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecific, org.bouncycastle.asn1.ASN1TaggedObject, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecific, org.bouncycastle.asn1.ASN1TaggedObject, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return this;
    }
}
