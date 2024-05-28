package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SHA384Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 48;

    public SHA384Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public SHA384Digest(CryptoServicePurpose cryptoServicePurpose) {
        super(cryptoServicePurpose);
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
        reset();
    }

    public SHA384Digest(SHA384Digest sHA384Digest) {
        super(sHA384Digest);
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
    }

    public SHA384Digest(byte[] bArr) {
        super(CryptoServicePurpose.values()[bArr[bArr.length - 1]]);
        restoreState(bArr);
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA384Digest(this);
    }

    @Override // org.bouncycastle.crypto.digests.LongDigest
    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, 256, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.longToBigEndian(this.f26444H1, bArr, i);
        Pack.longToBigEndian(this.f26445H2, bArr, i + 8);
        Pack.longToBigEndian(this.f26446H3, bArr, i + 16);
        Pack.longToBigEndian(this.f26447H4, bArr, i + 24);
        Pack.longToBigEndian(this.f26448H5, bArr, i + 32);
        Pack.longToBigEndian(this.f26449H6, bArr, i + 40);
        reset();
        return 48;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-384";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 48;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[getEncodedStateSize() + 1];
        super.populateState(bArr);
        bArr[bArr.length - 1] = (byte) this.purpose.ordinal();
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.LongDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f26444H1 = -3766243637369397544L;
        this.f26445H2 = 7105036623409894663L;
        this.f26446H3 = -7973340178411365097L;
        this.f26447H4 = 1526699215303891257L;
        this.f26448H5 = 7436329637833083697L;
        this.f26449H6 = -8163818279084223215L;
        this.f26450H7 = -2662702644619276377L;
        this.f26451H8 = 5167115440072839076L;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        super.copyIn((SHA384Digest) memoable);
    }
}
