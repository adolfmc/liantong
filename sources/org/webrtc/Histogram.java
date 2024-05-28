package org.webrtc;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Histogram {
    private final long handle;

    private static native void nativeAddSample(long j, int i);

    private static native long nativeCreateCounts(String str, int i, int i2, int i3);

    private static native long nativeCreateEnumeration(String str, int i);

    private Histogram(long j) {
        this.handle = j;
    }

    public static Histogram createCounts(String str, int i, int i2, int i3) {
        return new Histogram(nativeCreateCounts(str, i, i2, i3));
    }

    public static Histogram createEnumeration(String str, int i) {
        return new Histogram(nativeCreateEnumeration(str, i));
    }

    public void addSample(int i) {
        nativeAddSample(this.handle, i);
    }
}
