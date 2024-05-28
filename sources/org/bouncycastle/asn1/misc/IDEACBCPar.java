package org.bouncycastle.asn1.misc;

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
public class IDEACBCPar extends ASN1Object {

    /* renamed from: iv */
    ASN1OctetString f26298iv;

    private IDEACBCPar(ASN1Sequence aSN1Sequence) {
        this.f26298iv = aSN1Sequence.size() == 1 ? (ASN1OctetString) aSN1Sequence.getObjectAt(0) : null;
    }

    public IDEACBCPar(byte[] bArr) {
        this.f26298iv = new DEROctetString(Arrays.clone(bArr));
    }

    public static IDEACBCPar getInstance(Object obj) {
        if (obj instanceof IDEACBCPar) {
            return (IDEACBCPar) obj;
        }
        if (obj != null) {
            return new IDEACBCPar(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getIV() {
        ASN1OctetString aSN1OctetString = this.f26298iv;
        if (aSN1OctetString != null) {
            return Arrays.clone(aSN1OctetString.getOctets());
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(1);
        ASN1OctetString aSN1OctetString = this.f26298iv;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(aSN1OctetString);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
