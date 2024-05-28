package org.bouncycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DHKEKGenerator implements DerivationFunction {
    private ASN1ObjectIdentifier algorithm;
    private final Digest digest;
    private int keySize;
    private byte[] partyAInfo;

    /* renamed from: z */
    private byte[] f26380z;

    public DHKEKGenerator(Digest digest) {
        this.digest = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        if (bArr.length - i2 >= i) {
            long j = i2;
            int digestSize = this.digest.getDigestSize();
            if (j <= 8589934591L) {
                long j2 = digestSize;
                int i3 = (int) (((j + j2) - 1) / j2);
                byte[] bArr2 = new byte[this.digest.getDigestSize()];
                int i4 = 0;
                int i5 = i2;
                int i6 = i;
                int i7 = 0;
                int i8 = 1;
                while (i7 < i3) {
                    Digest digest = this.digest;
                    byte[] bArr3 = this.f26380z;
                    digest.update(bArr3, i4, bArr3.length);
                    ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                    ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                    aSN1EncodableVector2.add(this.algorithm);
                    aSN1EncodableVector2.add(new DEROctetString(Pack.intToBigEndian(i8)));
                    aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
                    byte[] bArr4 = this.partyAInfo;
                    if (bArr4 != null) {
                        aSN1EncodableVector.add(new DERTaggedObject(true, i4, (ASN1Encodable) new DEROctetString(bArr4)));
                    }
                    aSN1EncodableVector.add(new DERTaggedObject(true, 2, (ASN1Encodable) new DEROctetString(Pack.intToBigEndian(this.keySize))));
                    try {
                        byte[] encoded = new DERSequence(aSN1EncodableVector).getEncoded("DER");
                        this.digest.update(encoded, 0, encoded.length);
                        this.digest.doFinal(bArr2, 0);
                        if (i5 > digestSize) {
                            System.arraycopy(bArr2, 0, bArr, i6, digestSize);
                            i6 += digestSize;
                            i5 -= digestSize;
                        } else {
                            System.arraycopy(bArr2, 0, bArr, i6, i5);
                        }
                        i8++;
                        i7++;
                        i4 = 0;
                    } catch (IOException e) {
                        throw new IllegalArgumentException("unable to encode parameter info: " + e.getMessage());
                    }
                }
                this.digest.reset();
                return (int) j;
            }
            throw new IllegalArgumentException("Output length too large");
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters) derivationParameters;
        this.algorithm = dHKDFParameters.getAlgorithm();
        this.keySize = dHKDFParameters.getKeySize();
        this.f26380z = dHKDFParameters.getZ();
        this.partyAInfo = dHKDFParameters.getExtraInfo();
    }
}
