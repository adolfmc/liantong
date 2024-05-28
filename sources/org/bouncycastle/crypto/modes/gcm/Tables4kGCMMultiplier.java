package org.bouncycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Tables4kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f26761H;

    /* renamed from: T */
    private long[][] f26762T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.f26762T == null) {
            this.f26762T = (long[][]) Array.newInstance(long.class, 256, 2);
        } else if (GCMUtil.areEqual(this.f26761H, bArr) != 0) {
            return;
        }
        this.f26761H = new byte[16];
        GCMUtil.copy(bArr, this.f26761H);
        GCMUtil.asLongs(this.f26761H, this.f26762T[1]);
        long[][] jArr = this.f26762T;
        GCMUtil.multiplyP7(jArr[1], jArr[1]);
        for (int i = 2; i < 256; i += 2) {
            long[][] jArr2 = this.f26762T;
            GCMUtil.divideP(jArr2[i >> 1], jArr2[i]);
            long[][] jArr3 = this.f26762T;
            GCMUtil.xor(jArr3[i], jArr3[1], jArr3[i + 1]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] jArr = this.f26762T[bArr[15] & 255];
        long j = jArr[0];
        long j2 = jArr[1];
        for (int i = 14; i >= 0; i--) {
            long[] jArr2 = this.f26762T[bArr[i] & 255];
            long j3 = j2 << 56;
            j2 = ((j2 >>> 8) | (j << 56)) ^ jArr2[1];
            j = (((((j >>> 8) ^ jArr2[0]) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2)) ^ (j3 >>> 7);
        }
        Pack.longToBigEndian(j, bArr, 0);
        Pack.longToBigEndian(j2, bArr, 8);
    }
}
