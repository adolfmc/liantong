package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Matrix;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class McElieceCCA2PublicKey extends ASN1Object {
    private final AlgorithmIdentifier digest;

    /* renamed from: g */
    private final GF2Matrix f27078g;

    /* renamed from: n */
    private final int f27079n;

    /* renamed from: t */
    private final int f27080t;

    public McElieceCCA2PublicKey(int i, int i2, GF2Matrix gF2Matrix, AlgorithmIdentifier algorithmIdentifier) {
        this.f27079n = i;
        this.f27080t = i2;
        this.f27078g = new GF2Matrix(gF2Matrix.getEncoded());
        this.digest = algorithmIdentifier;
    }

    private McElieceCCA2PublicKey(ASN1Sequence aSN1Sequence) {
        this.f27079n = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).intValueExact();
        this.f27080t = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).intValueExact();
        this.f27078g = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
        this.digest = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(3));
    }

    public static McElieceCCA2PublicKey getInstance(Object obj) {
        if (obj instanceof McElieceCCA2PublicKey) {
            return (McElieceCCA2PublicKey) obj;
        }
        if (obj != null) {
            return new McElieceCCA2PublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getDigest() {
        return this.digest;
    }

    public GF2Matrix getG() {
        return this.f27078g;
    }

    public int getN() {
        return this.f27079n;
    }

    public int getT() {
        return this.f27080t;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f27079n));
        aSN1EncodableVector.add(new ASN1Integer(this.f27080t));
        aSN1EncodableVector.add(new DEROctetString(this.f27078g.getEncoded()));
        aSN1EncodableVector.add(this.digest);
        return new DERSequence(aSN1EncodableVector);
    }
}
