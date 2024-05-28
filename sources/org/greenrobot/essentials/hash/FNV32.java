package org.greenrobot.essentials.hash;

import java.util.zip.Checksum;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FNV32 implements Checksum {
    private static final int INITIAL_VALUE = -2128831035;
    private static final int MULTIPLIER = 16777619;
    private int hash = -2128831035;

    @Override // java.util.zip.Checksum
    public void update(int i) {
        this.hash = (i & 255) ^ this.hash;
        this.hash *= 16777619;
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            this.hash ^= bArr[i] & 255;
            this.hash *= 16777619;
            i++;
        }
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return this.hash & 4294967295L;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.hash = -2128831035;
    }
}
