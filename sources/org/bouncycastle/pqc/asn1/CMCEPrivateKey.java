package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CMCEPrivateKey extends ASN1Object {

    /* renamed from: C */
    private byte[] f27072C;
    private CMCEPublicKey PublicKey;
    private byte[] alpha;
    private byte[] delta;

    /* renamed from: g */
    private byte[] f27073g;

    /* renamed from: s */
    private byte[] f27074s;
    private int version;

    public CMCEPrivateKey(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        this(i, bArr, bArr2, bArr3, bArr4, bArr5, null);
    }

    public CMCEPrivateKey(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, CMCEPublicKey cMCEPublicKey) {
        this.version = i;
        if (i != 0) {
            throw new IllegalArgumentException("unrecognized version");
        }
        this.delta = Arrays.clone(bArr);
        this.f27072C = Arrays.clone(bArr2);
        this.f27073g = Arrays.clone(bArr3);
        this.alpha = Arrays.clone(bArr4);
        this.f27074s = Arrays.clone(bArr5);
        this.PublicKey = cMCEPublicKey;
    }

    private CMCEPrivateKey(ASN1Sequence aSN1Sequence) {
        this.version = BigIntegers.intValueExact(ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue());
        if (this.version != 0) {
            throw new IllegalArgumentException("unrecognized version");
        }
        this.delta = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
        this.f27072C = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets());
        this.f27073g = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(3)).getOctets());
        this.alpha = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(4)).getOctets());
        this.f27074s = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(5)).getOctets());
        if (aSN1Sequence.size() == 7) {
            this.PublicKey = CMCEPublicKey.getInstance(aSN1Sequence.getObjectAt(6));
        }
    }

    public static CMCEPrivateKey getInstance(Object obj) {
        if (obj instanceof CMCEPrivateKey) {
            return (CMCEPrivateKey) obj;
        }
        if (obj != null) {
            return new CMCEPrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getAlpha() {
        return Arrays.clone(this.alpha);
    }

    public byte[] getC() {
        return Arrays.clone(this.f27072C);
    }

    public byte[] getDelta() {
        return Arrays.clone(this.delta);
    }

    public byte[] getG() {
        return Arrays.clone(this.f27073g);
    }

    public CMCEPublicKey getPublicKey() {
        return this.PublicKey;
    }

    public byte[] getS() {
        return Arrays.clone(this.f27074s);
    }

    public int getVersion() {
        return this.version;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.version));
        aSN1EncodableVector.add(new DEROctetString(this.delta));
        aSN1EncodableVector.add(new DEROctetString(this.f27072C));
        aSN1EncodableVector.add(new DEROctetString(this.f27073g));
        aSN1EncodableVector.add(new DEROctetString(this.alpha));
        aSN1EncodableVector.add(new DEROctetString(this.f27074s));
        CMCEPublicKey cMCEPublicKey = this.PublicKey;
        if (cMCEPublicKey != null) {
            aSN1EncodableVector.add(new CMCEPublicKey(cMCEPublicKey.getT()));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
