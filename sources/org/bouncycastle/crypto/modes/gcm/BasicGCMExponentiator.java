package org.bouncycastle.crypto.modes.gcm;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BasicGCMExponentiator implements GCMExponentiator {

    /* renamed from: x */
    private long[] f26758x;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        long[] oneAsLongs = GCMUtil.oneAsLongs();
        if (j <= 0) {
            GCMUtil.asBytes(oneAsLongs, bArr);
        }
        long[] jArr = new long[2];
        GCMUtil.copy(this.f26758x, jArr);
        do {
            if ((1 & j) != 0) {
                GCMUtil.multiply(oneAsLongs, jArr);
            }
            GCMUtil.square(jArr, jArr);
            j >>>= 1;
        } while (j > 0);
        GCMUtil.asBytes(oneAsLongs, bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        this.f26758x = GCMUtil.asLongs(bArr);
    }
}
