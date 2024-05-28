package io.objectbox.reactive;

import io.objectbox.annotation.apihint.Internal;
import java.lang.ref.WeakReference;

@Internal
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WeakDataObserver<T> implements DataObserver<T>, DelegatingObserver {
    private DataSubscription subscription;
    private final WeakReference<DataObserver<T>> weakDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WeakDataObserver(DataObserver<T> dataObserver) {
        this.weakDelegate = new WeakReference<>(dataObserver);
    }

    @Override // io.objectbox.reactive.DataObserver
    public void onData(T t) {
        DataObserver<T> dataObserver = this.weakDelegate.get();
        if (dataObserver != null) {
            dataObserver.onData(t);
        } else {
            this.subscription.cancel();
        }
    }

    @Override // io.objectbox.reactive.DelegatingObserver
    public DataObserver<T> getObserverDelegate() {
        return this.weakDelegate.get();
    }

    public boolean equals(Object obj) {
        if (obj instanceof WeakDataObserver) {
            DataObserver<T> dataObserver = this.weakDelegate.get();
            if (dataObserver == null || dataObserver != ((WeakDataObserver) obj).weakDelegate.get()) {
                return super.equals(obj);
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        DataObserver<T> dataObserver = this.weakDelegate.get();
        if (dataObserver != null) {
            return dataObserver.hashCode();
        }
        return super.hashCode();
    }

    public void setSubscription(DataSubscription dataSubscription) {
        this.subscription = dataSubscription;
    }
}
