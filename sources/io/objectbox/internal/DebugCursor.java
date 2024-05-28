package io.objectbox.internal;

import io.objectbox.InternalAccess;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Beta;
import java.io.Closeable;

@Beta
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DebugCursor implements Closeable {
    private boolean closed;
    private final long handle;

    /* renamed from: tx */
    private final Transaction f24390tx;

    static native long nativeCreate(long j);

    static native void nativeDestroy(long j);

    static native byte[] nativeGet(long j, byte[] bArr);

    static native byte[] nativeSeekOrNext(long j, byte[] bArr);

    public static DebugCursor create(Transaction transaction) {
        return new DebugCursor(transaction, nativeCreate(InternalAccess.getHandle(transaction)));
    }

    public DebugCursor(Transaction transaction, long j) {
        this.f24390tx = transaction;
        this.handle = j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.closed) {
            this.closed = true;
            if (this.f24390tx != null && !this.f24390tx.getStore().isClosed()) {
                nativeDestroy(this.handle);
            }
        }
    }

    protected void finalize() throws Throwable {
        if (this.closed) {
            return;
        }
        close();
        super.finalize();
    }

    public byte[] get(byte[] bArr) {
        return nativeGet(this.handle, bArr);
    }

    public byte[] seekOrNext(byte[] bArr) {
        return nativeSeekOrNext(this.handle, bArr);
    }
}
