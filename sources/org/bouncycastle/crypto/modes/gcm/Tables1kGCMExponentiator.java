package org.bouncycastle.crypto.modes.gcm;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Tables1kGCMExponentiator implements GCMExponentiator {
    private List lookupPowX2;

    private long[] getPowX2(int i) {
        int size = this.lookupPowX2.size() - 1;
        if (size < i) {
            long[] jArr = (long[]) this.lookupPowX2.get(size);
            while (true) {
                long[] jArr2 = new long[2];
                GCMUtil.square(jArr, jArr2);
                this.lookupPowX2.add(jArr2);
                size++;
                if (size >= i) {
                    break;
                }
                jArr = jArr2;
            }
        }
        return (long[]) this.lookupPowX2.get(i);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        long[] oneAsLongs = GCMUtil.oneAsLongs();
        int i = 0;
        while (j > 0) {
            if ((1 & j) != 0) {
                GCMUtil.multiply(oneAsLongs, getPowX2(i));
            }
            i++;
            j >>>= 1;
        }
        GCMUtil.asBytes(oneAsLongs, bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        long[] asLongs = GCMUtil.asLongs(bArr);
        List list = this.lookupPowX2;
        if (list == null || 0 == GCMUtil.areEqual(asLongs, (long[]) list.get(0))) {
            this.lookupPowX2 = new ArrayList(8);
            this.lookupPowX2.add(asLongs);
        }
    }
}
