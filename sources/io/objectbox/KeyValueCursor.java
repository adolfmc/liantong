package io.objectbox;

import java.io.Closeable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NotThreadSafe
/* loaded from: E:\11617560_dexfile_execute.dex */
public class KeyValueCursor implements Closeable {
    private static final int PUT_FLAG_COMPLETE = 2;
    private static final int PUT_FLAG_FIRST = 1;
    private static final int PUT_FLAG_INSERT_NEW = 4;
    private final long cursor;

    static native void nativeDestroy(long j);

    static native byte[] nativeGetCurrent(long j);

    static native byte[] nativeGetEqualOrGreater(long j, long j2);

    static native byte[] nativeGetFirst(long j);

    static native long nativeGetKey(long j);

    static native void nativeGetKey(long j, long j2);

    static native byte[] nativeGetLast(long j);

    static native byte[] nativeGetLongKey(long j, long j2);

    static native byte[] nativeGetNext(long j);

    static native byte[] nativeGetPrev(long j);

    static native void nativePutLongKey(long j, long j2, byte[] bArr);

    static native boolean nativeRemoveAt(long j, long j2);

    static native boolean nativeSeek(long j, long j2);

    public KeyValueCursor(long j) {
        this.cursor = j;
    }

    public void put(long j, byte[] bArr) {
        nativePutLongKey(this.cursor, j, bArr);
    }

    public byte[] get(long j) {
        return nativeGetLongKey(this.cursor, j);
    }

    public byte[] getNext() {
        return nativeGetNext(this.cursor);
    }

    public byte[] getFirst() {
        return nativeGetFirst(this.cursor);
    }

    public byte[] getLast() {
        return nativeGetLast(this.cursor);
    }

    public byte[] getPrev() {
        return nativeGetPrev(this.cursor);
    }

    public byte[] getEqualOrGreater(long j) {
        return nativeGetEqualOrGreater(this.cursor, j);
    }

    public byte[] getCurrent() {
        return nativeGetCurrent(this.cursor);
    }

    public long getKey() {
        return nativeGetKey(this.cursor);
    }

    public boolean seek(long j) {
        return nativeSeek(this.cursor, j);
    }

    public boolean removeAt(long j) {
        return nativeRemoveAt(this.cursor, j);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        nativeDestroy(this.cursor);
    }
}
