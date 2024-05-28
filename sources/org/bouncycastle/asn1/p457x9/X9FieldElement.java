package org.bouncycastle.asn1.p457x9;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.math.p464ec.ECFieldElement;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.asn1.x9.X9FieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class X9FieldElement extends ASN1Object {
    private static X9IntegerConverter converter = new X9IntegerConverter();

    /* renamed from: f */
    protected ECFieldElement f26366f;

    public X9FieldElement(ECFieldElement eCFieldElement) {
        this.f26366f = eCFieldElement;
    }

    public ECFieldElement getValue() {
        return this.f26366f;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(converter.integerToBytes(this.f26366f.toBigInteger(), converter.getByteLength(this.f26366f)));
    }
}
