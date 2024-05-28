package io.objectbox.reactive;

import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DataSubscriptionList implements DataSubscription {
    private boolean canceled;
    private final List<DataSubscription> subscriptions = new ArrayList();

    public synchronized void add(DataSubscription dataSubscription) {
        this.subscriptions.add(dataSubscription);
        this.canceled = false;
    }

    @Override // io.objectbox.reactive.DataSubscription
    public synchronized void cancel() {
        this.canceled = true;
        for (DataSubscription dataSubscription : this.subscriptions) {
            dataSubscription.cancel();
        }
        this.subscriptions.clear();
    }

    @Override // io.objectbox.reactive.DataSubscription
    public synchronized boolean isCanceled() {
        return this.canceled;
    }

    public synchronized int getActiveSubscriptionCount() {
        return this.subscriptions.size();
    }
}
