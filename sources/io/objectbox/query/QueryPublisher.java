package io.objectbox.query;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataPublisher;
import io.objectbox.reactive.DataPublisherUtils;
import io.objectbox.reactive.DataSubscription;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class QueryPublisher<T> implements DataPublisher<List<T>> {
    private final Box<T> box;
    private DataObserver<Class<T>> objectClassObserver;
    private DataSubscription objectClassSubscription;
    private final Set<DataObserver<List<T>>> observers = new CopyOnWriteArraySet();
    private final Query<T> query;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueryPublisher(Query<T> query, Box<T> box) {
        this.query = query;
        this.box = box;
    }

    @Override // io.objectbox.reactive.DataPublisher
    public synchronized void subscribe(DataObserver<List<T>> dataObserver, @Nullable Object obj) {
        BoxStore store = this.box.getStore();
        if (this.objectClassObserver == null) {
            this.objectClassObserver = new DataObserver<Class<T>>() { // from class: io.objectbox.query.QueryPublisher.1
                @Override // io.objectbox.reactive.DataObserver
                public /* bridge */ /* synthetic */ void onData(Object obj2) {
                    onData((Class) ((Class) obj2));
                }

                public void onData(Class<T> cls) {
                    QueryPublisher.this.publish();
                }
            };
        }
        if (this.observers.isEmpty()) {
            if (this.objectClassSubscription != null) {
                throw new IllegalStateException("Existing subscription found");
            }
            this.objectClassSubscription = store.subscribe(this.box.getEntityClass()).weak().onlyChanges().observer(this.objectClassObserver);
        }
        this.observers.add(dataObserver);
    }

    @Override // io.objectbox.reactive.DataPublisher
    public void publishSingle(final DataObserver<List<T>> dataObserver, @Nullable Object obj) {
        this.box.getStore().internalScheduleThread(new Runnable() { // from class: io.objectbox.query.QueryPublisher.2
            @Override // java.lang.Runnable
            public void run() {
                dataObserver.onData(QueryPublisher.this.query.find());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void publish() {
        this.box.getStore().internalScheduleThread(new Runnable() { // from class: io.objectbox.query.QueryPublisher.3
            @Override // java.lang.Runnable
            public void run() {
                List<T> find = QueryPublisher.this.query.find();
                for (DataObserver dataObserver : QueryPublisher.this.observers) {
                    dataObserver.onData(find);
                }
            }
        });
    }

    @Override // io.objectbox.reactive.DataPublisher
    public synchronized void unsubscribe(DataObserver<List<T>> dataObserver, @Nullable Object obj) {
        DataPublisherUtils.removeObserverFromCopyOnWriteSet(this.observers, dataObserver);
        if (this.observers.isEmpty()) {
            this.objectClassSubscription.cancel();
            this.objectClassSubscription = null;
        }
    }
}
