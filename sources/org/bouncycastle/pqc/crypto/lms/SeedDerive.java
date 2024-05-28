package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class SeedDerive {

    /* renamed from: I */
    private final byte[] f27214I;
    private final Digest digest;

    /* renamed from: j */
    private int f27215j;
    private final byte[] masterSeed;

    /* renamed from: q */
    private int f27216q;

    public SeedDerive(byte[] bArr, byte[] bArr2, Digest digest) {
        this.f27214I = bArr;
        this.masterSeed = bArr2;
        this.digest = digest;
    }

    public void deriveSeed(byte[] bArr, boolean z) {
        deriveSeed(bArr, z, 0);
    }

    public void deriveSeed(byte[] bArr, boolean z, int i) {
        deriveSeed(bArr, i);
        if (z) {
            this.f27215j++;
        }
    }

    public byte[] deriveSeed(byte[] bArr, int i) {
        if (bArr.length >= this.digest.getDigestSize()) {
            Digest digest = this.digest;
            byte[] bArr2 = this.f27214I;
            digest.update(bArr2, 0, bArr2.length);
            this.digest.update((byte) (this.f27216q >>> 24));
            this.digest.update((byte) (this.f27216q >>> 16));
            this.digest.update((byte) (this.f27216q >>> 8));
            this.digest.update((byte) this.f27216q);
            this.digest.update((byte) (this.f27215j >>> 8));
            this.digest.update((byte) this.f27215j);
            this.digest.update((byte) -1);
            Digest digest2 = this.digest;
            byte[] bArr3 = this.masterSeed;
            digest2.update(bArr3, 0, bArr3.length);
            this.digest.doFinal(bArr, i);
            return bArr;
        }
        throw new IllegalArgumentException("target length is less than digest size.");
    }

    public byte[] getI() {
        return this.f27214I;
    }

    public int getJ() {
        return this.f27215j;
    }

    public byte[] getMasterSeed() {
        return this.masterSeed;
    }

    public int getQ() {
        return this.f27216q;
    }

    public void setJ(int i) {
        this.f27215j = i;
    }

    public void setQ(int i) {
        this.f27216q = i;
    }
}
