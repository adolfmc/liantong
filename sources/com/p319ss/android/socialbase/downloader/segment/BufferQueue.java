package com.p319ss.android.socialbase.downloader.segment;

import android.support.annotation.NonNull;

/* renamed from: com.ss.android.socialbase.downloader.segment.BufferQueue */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class BufferQueue implements IBufferPool, IInput, IOutput {
    private static final int MIN_BUFFER_COUNT = 64;
    private static final int MIN_BUFFER_SIZE = 8192;
    private int bufferCount;
    private final int bufferSize;
    private volatile boolean closed;
    private final int maxBufferCount;
    private Buffer rHead;
    private Buffer rSafe;
    private Buffer rTail;
    private Buffer wHead;
    private Buffer wTail;
    private final Object wLock = new Object();
    private final Object rLock = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public BufferQueue(int i, int i2) {
        i = i < 64 ? 64 : i;
        i2 = i2 < 8192 ? 8192 : i2;
        this.maxBufferCount = i;
        this.bufferSize = i2;
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.IInput
    @NonNull
    public Buffer read() throws StreamClosedException, InterruptedException {
        Buffer buffer;
        Buffer buffer2 = this.rSafe;
        if (buffer2 != null) {
            this.rSafe = buffer2.next;
            buffer2.next = null;
            return buffer2;
        }
        synchronized (this.rLock) {
            buffer = this.rHead;
            while (buffer == null) {
                if (this.closed) {
                    throw new StreamClosedException("read");
                }
                this.rLock.wait();
                buffer = this.rHead;
            }
            this.rSafe = buffer.next;
            this.rTail = null;
            this.rHead = null;
            buffer.next = null;
        }
        return buffer;
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.IBufferPool
    public void recycle(@NonNull Buffer buffer) {
        synchronized (this.wLock) {
            Buffer buffer2 = this.wTail;
            if (buffer2 == null) {
                this.wTail = buffer;
                this.wHead = buffer;
            } else {
                buffer2.next = buffer;
                this.wTail = buffer;
            }
            this.wLock.notify();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.IBufferPool
    @NonNull
    public Buffer obtain() throws StreamClosedException, InterruptedException {
        synchronized (this.wLock) {
            if (this.closed) {
                throw new StreamClosedException("obtain");
            }
            Buffer buffer = this.wHead;
            if (buffer == null) {
                if (this.bufferCount < this.maxBufferCount) {
                    this.bufferCount++;
                    return new Buffer(this.bufferSize);
                }
                do {
                    this.wLock.wait();
                    if (this.closed) {
                        throw new StreamClosedException("obtain");
                    }
                    buffer = this.wHead;
                } while (buffer == null);
            }
            this.wHead = buffer.next;
            if (buffer == this.wTail) {
                this.wTail = null;
            }
            buffer.next = null;
            return buffer;
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.IOutput
    public void write(@NonNull Buffer buffer) {
        synchronized (this.rLock) {
            Buffer buffer2 = this.rTail;
            if (buffer2 == null) {
                this.rTail = buffer;
                this.rHead = buffer;
                this.rLock.notify();
            } else {
                buffer2.next = buffer;
                this.rTail = buffer;
            }
        }
    }

    public void close() {
        this.closed = true;
        synchronized (this.wLock) {
            this.wLock.notifyAll();
        }
        synchronized (this.rLock) {
            this.rLock.notifyAll();
        }
    }
}
