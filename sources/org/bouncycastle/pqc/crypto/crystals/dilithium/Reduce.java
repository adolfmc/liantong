package org.bouncycastle.pqc.crypto.crystals.dilithium;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class Reduce {
    Reduce() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int conditionalAddQ(int i) {
        return i + ((i >> 31) & 8380417);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int montgomeryReduce(long j) {
        return (int) ((j - (((int) (58728449 * j)) * 8380417)) >>> 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int reduce32(int i) {
        return i - (((4194304 + i) >> 23) * 8380417);
    }
}
