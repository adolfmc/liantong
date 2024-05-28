package org.bouncycastle.crypto.modes.gcm;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BasicGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private long[] f26759H;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        this.f26759H = GCMUtil.asLongs(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        GCMUtil.multiply(bArr, this.f26759H);
    }
}
