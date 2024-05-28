package org.bouncycastle.pqc.crypto.newhope;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class Reduce {
    static final int QInv = 12287;
    static final int RLog = 18;
    static final int RMask = 262143;

    Reduce() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short barrett(short s) {
        int i = s & 65535;
        return (short) (i - (((i * 5) >>> 16) * 12289));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short montgomery(int i) {
        return (short) (((((i * 12287) & 262143) * 12289) + i) >>> 18);
    }
}
