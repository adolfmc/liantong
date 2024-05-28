package org.bouncycastle.pqc.crypto.crystals.kyber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class Reduce {
    Reduce() {
    }

    public static short barretReduce(short s) {
        return (short) (s - ((short) (((short) ((((short) 20159) * s) >> 26)) * 3329)));
    }

    public static short conditionalSubQ(short s) {
        short s2 = (short) (s - 3329);
        return (short) (s2 + ((s2 >> 15) & 3329));
    }

    public static short montgomeryReduce(int i) {
        return (short) ((i - (((short) (62209 * i)) * 3329)) >> 16);
    }
}
