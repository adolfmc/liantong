package org.webrtc;

import android.support.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class RefCountDelegate implements RefCounted {
    private final AtomicInteger refCount = new AtomicInteger(1);
    @Nullable
    private final Runnable releaseCallback;

    public RefCountDelegate(@Nullable Runnable runnable) {
        this.releaseCallback = runnable;
    }

    @Override // org.webrtc.RefCounted
    public void retain() {
        if (this.refCount.incrementAndGet() < 2) {
            throw new IllegalStateException("retain() called on an object with refcount < 1");
        }
    }

    @Override // org.webrtc.RefCounted
    public void release() {
        Runnable runnable;
        int decrementAndGet = this.refCount.decrementAndGet();
        if (decrementAndGet < 0) {
            throw new IllegalStateException("release() called on an object with refcount < 1");
        }
        if (decrementAndGet != 0 || (runnable = this.releaseCallback) == null) {
            return;
        }
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean safeRetain() {
        int i = this.refCount.get();
        while (i != 0) {
            if (this.refCount.weakCompareAndSet(i, i + 1)) {
                return true;
            }
            i = this.refCount.get();
        }
        return false;
    }
}
