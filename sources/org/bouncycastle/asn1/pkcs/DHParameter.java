package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DHParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f26303g;

    /* renamed from: l */
    ASN1Integer f26304l;

    /* renamed from: p */
    ASN1Integer f26305p;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f26305p = new ASN1Integer(bigInteger);
        this.f26303g = new ASN1Integer(bigInteger2);
        this.f26304l = i != 0 ? new ASN1Integer(i) : null;
    }

    private DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f26305p = ASN1Integer.getInstance(objects.nextElement());
        this.f26303g = ASN1Integer.getInstance(objects.nextElement());
        this.f26304l = objects.hasMoreElements() ? (ASN1Integer) objects.nextElement() : null;
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getG() {
        return this.f26303g.getPositiveValue();
    }

    public BigInteger getL() {
        ASN1Integer aSN1Integer = this.f26304l;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f26305p.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f26305p);
        aSN1EncodableVector.add(this.f26303g);
        if (getL() != null) {
            aSN1EncodableVector.add(this.f26304l);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
