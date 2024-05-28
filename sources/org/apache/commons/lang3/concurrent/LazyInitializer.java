package org.apache.commons.lang3.concurrent;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class LazyInitializer<T> implements ConcurrentInitializer<T> {
    private static final Object NO_INIT = new Object();
    private volatile T object = (T) NO_INIT;

    protected abstract T initialize() throws ConcurrentException;

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() throws ConcurrentException {
        T t = this.object;
        if (t == NO_INIT) {
            synchronized (this) {
                t = this.object;
                if (t == NO_INIT) {
                    t = initialize();
                    this.object = t;
                }
            }
        }
        return t;
    }
}
