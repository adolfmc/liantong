package org.bouncycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Tables8kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f26765H;

    /* renamed from: T */
    private long[][][] f26766T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.f26766T == null) {
            this.f26766T = (long[][][]) Array.newInstance(long.class, 2, 256, 2);
        } else if (GCMUtil.areEqual(this.f26765H, bArr) != 0) {
            return;
        }
        this.f26765H = new byte[16];
        GCMUtil.copy(bArr, this.f26765H);
        for (int i = 0; i < 2; i++) {
            long[][][] jArr = this.f26766T;
            long[][] jArr2 = jArr[i];
            if (i == 0) {
                GCMUtil.asLongs(this.f26765H, jArr2[1]);
                GCMUtil.multiplyP7(jArr2[1], jArr2[1]);
            } else {
                GCMUtil.multiplyP8(jArr[i - 1][1], jArr2[1]);
            }
            for (int i2 = 2; i2 < 256; i2 += 2) {
                GCMUtil.divideP(jArr2[i2 >> 1], jArr2[i2]);
                GCMUtil.xor(jArr2[i2], jArr2[1], jArr2[i2 + 1]);
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[][][] jArr = this.f26766T;
        long[][] jArr2 = jArr[0];
        long[][] jArr3 = jArr[1];
        long[] jArr4 = jArr2[bArr[14] & 255];
        long[] jArr5 = jArr3[bArr[15] & 255];
        long j = jArr4[0] ^ jArr5[0];
        long j2 = jArr5[1] ^ jArr4[1];
        for (int i = 12; i >= 0; i -= 2) {
            long[] jArr6 = jArr2[bArr[i] & 255];
            long[] jArr7 = jArr3[bArr[i + 1] & 255];
            long j3 = j2 << 48;
            j2 = (jArr6[1] ^ jArr7[1]) ^ ((j2 >>> 16) | (j << 48));
            j = (((((j >>> 16) ^ (jArr6[0] ^ jArr7[0])) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2)) ^ (j3 >>> 7);
        }
        Pack.longToBigEndian(j, bArr, 0);
        Pack.longToBigEndian(j2, bArr, 8);
    }
}
