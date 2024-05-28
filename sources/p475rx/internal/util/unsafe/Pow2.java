package p475rx.internal.util.unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.unsafe.Pow2 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Pow2 {
    public static boolean isPowerOfTwo(int i) {
        return (i & (i + (-1))) == 0;
    }

    private Pow2() {
        throw new IllegalStateException("No instances!");
    }

    public static int roundToPowerOfTwo(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
