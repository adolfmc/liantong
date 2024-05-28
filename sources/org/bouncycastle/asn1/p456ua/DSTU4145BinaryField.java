package org.bouncycastle.asn1.p456ua;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.asn1.ua.DSTU4145BinaryField */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DSTU4145BinaryField extends ASN1Object {

    /* renamed from: j */
    private int f26308j;

    /* renamed from: k */
    private int f26309k;

    /* renamed from: l */
    private int f26310l;

    /* renamed from: m */
    private int f26311m;

    public DSTU4145BinaryField(int i, int i2) {
        this(i, i2, 0, 0);
    }

    public DSTU4145BinaryField(int i, int i2, int i3, int i4) {
        this.f26311m = i;
        this.f26309k = i2;
        this.f26308j = i3;
        this.f26310l = i4;
    }

    private DSTU4145BinaryField(ASN1Sequence aSN1Sequence) {
        this.f26311m = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).intPositiveValueExact();
        if (aSN1Sequence.getObjectAt(1) instanceof ASN1Integer) {
            this.f26309k = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).intPositiveValueExact();
        } else if (!(aSN1Sequence.getObjectAt(1) instanceof ASN1Sequence)) {
            throw new IllegalArgumentException("object parse error");
        } else {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            this.f26309k = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).intPositiveValueExact();
            this.f26308j = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(1)).intPositiveValueExact();
            this.f26310l = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(2)).intPositiveValueExact();
        }
    }

    public static DSTU4145BinaryField getInstance(Object obj) {
        if (obj instanceof DSTU4145BinaryField) {
            return (DSTU4145BinaryField) obj;
        }
        if (obj != null) {
            return new DSTU4145BinaryField(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int getK1() {
        return this.f26309k;
    }

    public int getK2() {
        return this.f26308j;
    }

    public int getK3() {
        return this.f26310l;
    }

    public int getM() {
        return this.f26311m;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(new ASN1Integer(this.f26311m));
        if (this.f26308j == 0) {
            aSN1EncodableVector.add(new ASN1Integer(this.f26309k));
        } else {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(3);
            aSN1EncodableVector2.add(new ASN1Integer(this.f26309k));
            aSN1EncodableVector2.add(new ASN1Integer(this.f26308j));
            aSN1EncodableVector2.add(new ASN1Integer(this.f26310l));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
