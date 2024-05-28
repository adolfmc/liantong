package org.bouncycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECGOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f26287a;

    /* renamed from: b */
    ASN1Integer f26288b;

    /* renamed from: p */
    ASN1Integer f26289p;

    /* renamed from: q */
    ASN1Integer f26290q;

    /* renamed from: x */
    ASN1Integer f26291x;

    /* renamed from: y */
    ASN1Integer f26292y;

    public ECGOST3410ParamSetParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i, BigInteger bigInteger5) {
        this.f26287a = new ASN1Integer(bigInteger);
        this.f26288b = new ASN1Integer(bigInteger2);
        this.f26289p = new ASN1Integer(bigInteger3);
        this.f26290q = new ASN1Integer(bigInteger4);
        this.f26291x = new ASN1Integer(i);
        this.f26292y = new ASN1Integer(bigInteger5);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f26287a = (ASN1Integer) objects.nextElement();
        this.f26288b = (ASN1Integer) objects.nextElement();
        this.f26289p = (ASN1Integer) objects.nextElement();
        this.f26290q = (ASN1Integer) objects.nextElement();
        this.f26291x = (ASN1Integer) objects.nextElement();
        this.f26292y = (ASN1Integer) objects.nextElement();
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof ECGOST3410ParamSetParameters)) {
            return (ECGOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getA() {
        return this.f26287a.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f26289p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f26290q.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(6);
        aSN1EncodableVector.add(this.f26287a);
        aSN1EncodableVector.add(this.f26288b);
        aSN1EncodableVector.add(this.f26289p);
        aSN1EncodableVector.add(this.f26290q);
        aSN1EncodableVector.add(this.f26291x);
        aSN1EncodableVector.add(this.f26292y);
        return new DERSequence(aSN1EncodableVector);
    }
}
