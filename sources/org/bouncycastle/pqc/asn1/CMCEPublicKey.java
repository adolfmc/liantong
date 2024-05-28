package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CMCEPublicKey extends ASN1Object {

    /* renamed from: T */
    private byte[] f27075T;

    public CMCEPublicKey(ASN1Sequence aSN1Sequence) {
        this.f27075T = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets());
    }

    public CMCEPublicKey(byte[] bArr) {
        this.f27075T = bArr;
    }

    public static CMCEPublicKey getInstance(Object obj) {
        if (obj instanceof CMCEPrivateKey) {
            return (CMCEPublicKey) obj;
        }
        if (obj != null) {
            return new CMCEPublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getT() {
        return Arrays.clone(this.f27075T);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DEROctetString(this.f27075T));
        return new DERSequence(aSN1EncodableVector);
    }
}
