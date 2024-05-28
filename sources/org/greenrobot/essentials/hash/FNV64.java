package org.greenrobot.essentials.hash;

import java.util.zip.Checksum;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FNV64 implements Checksum {
    private static final long INITIAL_VALUE = -3750763034362895579L;
    private static final long MULTIPLIER = 1099511628211L;
    private long hash = -3750763034362895579L;

    @Override // java.util.zip.Checksum
    public void update(int i) {
        this.hash ^= i & 255;
        this.hash *= 1099511628211L;
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            this.hash ^= 255 & bArr[i];
            this.hash *= 1099511628211L;
            i++;
        }
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return this.hash;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.hash = -3750763034362895579L;
    }
}
