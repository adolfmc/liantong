package io.objectbox;

import io.objectbox.annotation.apihint.Internal;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataPublisher;
import io.objectbox.reactive.DataPublisherUtils;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Set;
import javax.annotation.Nullable;
import org.greenrobot.essentials.collections.MultimapSet;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ObjectClassPublisher implements DataPublisher<Class>, Runnable {
    final BoxStore boxStore;
    volatile boolean changePublisherRunning;
    final MultimapSet<Integer, DataObserver<Class>> observersByEntityTypeId = MultimapSet.create(MultimapSet.SetType.THREAD_SAFE);
    final Deque<int[]> changesQueue = new ArrayDeque();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectClassPublisher(BoxStore boxStore) {
        this.boxStore = boxStore;
    }

    @Override // io.objectbox.reactive.DataPublisher
    public void subscribe(DataObserver<Class> dataObserver, @Nullable Object obj) {
        if (obj == null) {
            for (int i : this.boxStore.getAllEntityTypeIds()) {
                this.observersByEntityTypeId.putElement(Integer.valueOf(i), dataObserver);
            }
            return;
        }
        this.observersByEntityTypeId.putElement(Integer.valueOf(this.boxStore.getEntityTypeIdOrThrow((Class) obj)), dataObserver);
    }

    @Override // io.objectbox.reactive.DataPublisher
    public void unsubscribe(DataObserver<Class> dataObserver, @Nullable Object obj) {
        if (obj != null) {
            unsubscribe(dataObserver, this.boxStore.getEntityTypeIdOrThrow((Class) obj));
            return;
        }
        for (int i : this.boxStore.getAllEntityTypeIds()) {
            unsubscribe(dataObserver, i);
        }
    }

    private void unsubscribe(DataObserver<Class> dataObserver, int i) {
        DataPublisherUtils.removeObserverFromCopyOnWriteSet((Set) this.observersByEntityTypeId.get((Object) Integer.valueOf(i)), dataObserver);
    }

    @Override // io.objectbox.reactive.DataPublisher
    public void publishSingle(final DataObserver<Class> dataObserver, @Nullable final Object obj) {
        this.boxStore.internalScheduleThread(new Runnable() { // from class: io.objectbox.ObjectClassPublisher.1
            @Override // java.lang.Runnable
            public void run() {
                Object obj2 = obj;
                for (Class cls : obj2 != null ? Collections.singletonList((Class) obj2) : ObjectClassPublisher.this.boxStore.getAllEntityClasses()) {
                    try {
                        dataObserver.onData(cls);
                    } catch (RuntimeException unused) {
                        ObjectClassPublisher.this.handleObserverException(cls);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleObserverException(Class cls) {
        RuntimeException runtimeException = new RuntimeException("Observer failed while processing data for " + cls + ". Consider using an ErrorObserver");
        runtimeException.printStackTrace();
        throw runtimeException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void publish(int[] iArr) {
        synchronized (this.changesQueue) {
            this.changesQueue.add(iArr);
            if (!this.changePublisherRunning) {
                this.changePublisherRunning = true;
                this.boxStore.internalScheduleThread(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        int[] pollFirst;
        while (true) {
            try {
            } finally {
            }
            synchronized (this.changesQueue) {
                pollFirst = this.changesQueue.pollFirst();
                if (pollFirst == null) {
                    this.changePublisherRunning = false;
                    return;
                }
                this.changePublisherRunning = false;
            }
            for (int i : pollFirst) {
                Collection<DataObserver> collection = this.observersByEntityTypeId.get((Object) Integer.valueOf(i));
                if (collection != null && !collection.isEmpty()) {
                    Class entityClassOrThrow = this.boxStore.getEntityClassOrThrow(i);
                    try {
                        for (DataObserver dataObserver : collection) {
                            dataObserver.onData(entityClassOrThrow);
                        }
                    } catch (RuntimeException unused) {
                        handleObserverException(entityClassOrThrow);
                    }
                }
            }
        }
    }
}
