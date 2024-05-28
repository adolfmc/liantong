package io.objectbox.android;

import android.arch.lifecycle.LiveData;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscription;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ObjectBoxLiveData<T> extends LiveData<List<T>> {
    private final DataObserver<List<T>> listener = new C120691();
    private final Query<T> query;
    private DataSubscription subscription;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: io.objectbox.android.ObjectBoxLiveData$1 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C120691 implements DataObserver<List<T>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C120691() {
        }

        @Override // io.objectbox.reactive.DataObserver
        public /* bridge */ /* synthetic */ void onData(Object obj) {
            onData((List) ((List) obj));
        }

        public void onData(List<T> list) {
            ObjectBoxLiveData.this.postValue(list);
        }
    }

    public ObjectBoxLiveData(Query<T> query) {
        this.query = query;
    }

    @Override // android.arch.lifecycle.LiveData
    public void onActive() {
        if (this.subscription == null) {
            this.subscription = this.query.subscribe().observer(this.listener);
        }
    }

    @Override // android.arch.lifecycle.LiveData
    public void onInactive() {
        if (hasObservers()) {
            return;
        }
        this.subscription.cancel();
        this.subscription = null;
    }
}
