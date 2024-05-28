package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class WotsPlus {
    private final SPHINCSPlusEngine engine;

    /* renamed from: w */
    private final int f27274w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WotsPlus(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
        this.f27274w = this.engine.WOTS_W;
    }

    int[] base_w(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            if (i3 == 0) {
                i5 = bArr[i4];
                i4++;
                i3 += 8;
            }
            i3 -= this.engine.WOTS_LOGW;
            iArr[i6] = (i5 >>> i3) & (i - 1);
            i6++;
        }
        return iArr;
    }

    byte[] chain(byte[] bArr, int i, int i2, byte[] bArr2, ADRS adrs) {
        if (i2 == 0) {
            return Arrays.clone(bArr);
        }
        int i3 = i + i2;
        if (i3 > this.f27274w - 1) {
            return null;
        }
        byte[] chain = chain(bArr, i, i2 - 1, bArr2, adrs);
        adrs.setHashAddress(i3 - 1);
        return this.engine.mo245F(bArr2, adrs, chain);
    }

    public byte[] pkFromSig(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int[] base_w = base_w(bArr2, this.f27274w, this.engine.WOTS_LEN1);
        int i = 0;
        for (int i2 = 0; i2 < this.engine.WOTS_LEN1; i2++) {
            i += (this.f27274w - 1) - base_w[i2];
        }
        int[] concatenate = Arrays.concatenate(base_w, base_w(Arrays.copyOfRange(Pack.intToBigEndian(i << (8 - ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) % 8))), 4 - (((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) + 7) / 8), 4), this.f27274w, this.engine.WOTS_LEN2));
        byte[] bArr4 = new byte[this.engine.f27250N];
        byte[][] bArr5 = new byte[this.engine.WOTS_LEN];
        for (int i3 = 0; i3 < this.engine.WOTS_LEN; i3++) {
            adrs.setChainAddress(i3);
            System.arraycopy(bArr, this.engine.f27250N * i3, bArr4, 0, this.engine.f27250N);
            bArr5[i3] = chain(bArr4, concatenate[i3], (this.f27274w - 1) - concatenate[i3], bArr3, adrs);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr3, adrs2, Arrays.concatenate(bArr5));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] pkGen(byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        byte[][] bArr3 = new byte[this.engine.WOTS_LEN];
        for (int i = 0; i < this.engine.WOTS_LEN; i++) {
            ADRS adrs3 = new ADRS(adrs);
            adrs3.setType(5);
            adrs3.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs3.setChainAddress(i);
            adrs3.setHashAddress(0);
            byte[] PRF = this.engine.PRF(bArr2, bArr, adrs3);
            adrs3.setType(0);
            adrs3.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs3.setChainAddress(i);
            adrs3.setHashAddress(0);
            bArr3[i] = chain(PRF, 0, this.f27274w - 1, bArr2, adrs3);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr3));
    }

    public byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int[] base_w = base_w(bArr, this.f27274w, this.engine.WOTS_LEN1);
        int i = 0;
        for (int i2 = 0; i2 < this.engine.WOTS_LEN1; i2++) {
            i += (this.f27274w - 1) - base_w[i2];
        }
        if (this.engine.WOTS_LOGW % 8 != 0) {
            i <<= 8 - ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) % 8);
        }
        byte[] intToBigEndian = Pack.intToBigEndian(i);
        int[] concatenate = Arrays.concatenate(base_w, base_w(Arrays.copyOfRange(intToBigEndian, ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) + 7) / 8, intToBigEndian.length), this.f27274w, this.engine.WOTS_LEN2));
        byte[][] bArr4 = new byte[this.engine.WOTS_LEN];
        for (int i3 = 0; i3 < this.engine.WOTS_LEN; i3++) {
            adrs2.setType(5);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setChainAddress(i3);
            adrs2.setHashAddress(0);
            byte[] PRF = this.engine.PRF(bArr3, bArr2, adrs2);
            adrs2.setType(0);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setChainAddress(i3);
            adrs2.setHashAddress(0);
            bArr4[i3] = chain(PRF, 0, concatenate[i3], bArr3, adrs2);
        }
        return Arrays.concatenate(bArr4);
    }
}
