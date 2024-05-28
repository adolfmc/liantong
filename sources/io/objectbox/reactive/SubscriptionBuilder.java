package io.objectbox.reactive;

import io.objectbox.annotation.apihint.Internal;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SubscriptionBuilder<T> {
    private DataSubscriptionList dataSubscriptionList;
    private ErrorObserver errorObserver;
    private DataObserver<T> observer;
    private boolean onlyChanges;
    private final DataPublisher<T> publisher;
    private final Object publisherParam;
    private Scheduler scheduler;
    private boolean single;
    private final ExecutorService threadPool;
    private DataTransformer<T, Object> transformer;
    private boolean weak;

    @Internal
    public SubscriptionBuilder(DataPublisher<T> dataPublisher, @Nullable Object obj, ExecutorService executorService) {
        this.publisher = dataPublisher;
        this.publisherParam = obj;
        this.threadPool = executorService;
    }

    public SubscriptionBuilder<T> weak() {
        this.weak = true;
        return this;
    }

    public SubscriptionBuilder<T> single() {
        this.single = true;
        return this;
    }

    public SubscriptionBuilder<T> onlyChanges() {
        this.onlyChanges = true;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <TO> SubscriptionBuilder<TO> transform(DataTransformer<T, TO> dataTransformer) {
        if (this.transformer != null) {
            throw new IllegalStateException("Only one transformer allowed");
        }
        this.transformer = dataTransformer;
        return this;
    }

    public SubscriptionBuilder<T> onError(ErrorObserver errorObserver) {
        if (this.errorObserver != null) {
            throw new IllegalStateException("Only one errorObserver allowed");
        }
        this.errorObserver = errorObserver;
        return this;
    }

    /* renamed from: on */
    public SubscriptionBuilder<T> m1947on(Scheduler scheduler) {
        if (this.scheduler != null) {
            throw new IllegalStateException("Only one scheduler allowed");
        }
        this.scheduler = scheduler;
        return this;
    }

    public DataSubscription observer(DataObserver<T> dataObserver) {
        WeakDataObserver weakDataObserver;
        if (this.weak) {
            weakDataObserver = new WeakDataObserver(dataObserver);
            dataObserver = weakDataObserver;
        } else {
            weakDataObserver = null;
        }
        this.observer = dataObserver;
        DataSubscriptionImpl dataSubscriptionImpl = new DataSubscriptionImpl(this.publisher, this.publisherParam, dataObserver);
        if (weakDataObserver != null) {
            weakDataObserver.setSubscription(dataSubscriptionImpl);
        }
        DataSubscriptionList dataSubscriptionList = this.dataSubscriptionList;
        if (dataSubscriptionList != null) {
            dataSubscriptionList.add(dataSubscriptionImpl);
        }
        if (this.transformer != null || this.scheduler != null || this.errorObserver != null) {
            dataObserver = new ActionObserver(dataSubscriptionImpl);
        }
        if (this.single) {
            if (this.onlyChanges) {
                throw new IllegalStateException("Illegal combination of single() and onlyChanges()");
            }
            this.publisher.publishSingle(dataObserver, this.publisherParam);
        } else {
            this.publisher.subscribe(dataObserver, this.publisherParam);
            if (!this.onlyChanges) {
                this.publisher.publishSingle(dataObserver, this.publisherParam);
            }
        }
        return dataSubscriptionImpl;
    }

    public SubscriptionBuilder<T> dataSubscriptionList(DataSubscriptionList dataSubscriptionList) {
        this.dataSubscriptionList = dataSubscriptionList;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class ActionObserver implements DataObserver<T>, DelegatingObserver<T> {
        private SubscriptionBuilder<T>.ActionObserver.SchedulerRunOnChange schedulerRunOnData;
        private SubscriptionBuilder<T>.ActionObserver.SchedulerRunOnError schedulerRunOnError;
        private final DataSubscriptionImpl subscription;

        public ActionObserver(DataSubscriptionImpl dataSubscriptionImpl) {
            this.subscription = dataSubscriptionImpl;
            if (SubscriptionBuilder.this.scheduler != null) {
                this.schedulerRunOnData = new SchedulerRunOnChange();
                if (SubscriptionBuilder.this.errorObserver != null) {
                    this.schedulerRunOnError = new SchedulerRunOnError();
                }
            }
        }

        @Override // io.objectbox.reactive.DataObserver
        public void onData(T t) {
            if (SubscriptionBuilder.this.transformer != null) {
                transformAndContinue(t);
            } else {
                callOnData(t);
            }
        }

        private void transformAndContinue(final T t) {
            SubscriptionBuilder.this.threadPool.submit(new Runnable() { // from class: io.objectbox.reactive.SubscriptionBuilder.ActionObserver.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    if (ActionObserver.this.subscription.isCanceled()) {
                        return;
                    }
                    try {
                        ActionObserver.this.callOnData(SubscriptionBuilder.this.transformer.transform(t));
                    } catch (Throwable th) {
                        ActionObserver.this.callOnError(th, "Transformer failed without an ErrorObserver set");
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void callOnError(Throwable th, String str) {
            if (SubscriptionBuilder.this.errorObserver != null) {
                if (this.subscription.isCanceled()) {
                    return;
                }
                if (SubscriptionBuilder.this.scheduler != null) {
                    SubscriptionBuilder.this.scheduler.run(this.schedulerRunOnError, th);
                    return;
                } else {
                    SubscriptionBuilder.this.errorObserver.onError(th);
                    return;
                }
            }
            RuntimeException runtimeException = new RuntimeException(str, th);
            runtimeException.printStackTrace();
            throw runtimeException;
        }

        void callOnData(T t) {
            if (this.subscription.isCanceled()) {
                return;
            }
            if (SubscriptionBuilder.this.scheduler != null) {
                SubscriptionBuilder.this.scheduler.run(this.schedulerRunOnData, t);
                return;
            }
            try {
                SubscriptionBuilder.this.observer.onData(t);
            } catch (Error | RuntimeException e) {
                callOnError(e, "Observer failed without an ErrorObserver set");
            }
        }

        @Override // io.objectbox.reactive.DelegatingObserver
        public DataObserver<T> getObserverDelegate() {
            return SubscriptionBuilder.this.observer;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class SchedulerRunOnChange implements RunWithParam<T> {
            SchedulerRunOnChange() {
            }

            @Override // io.objectbox.reactive.RunWithParam
            public void run(T t) {
                if (ActionObserver.this.subscription.isCanceled()) {
                    return;
                }
                try {
                    SubscriptionBuilder.this.observer.onData(t);
                } catch (Error | RuntimeException e) {
                    ActionObserver.this.callOnError(e, "Observer failed without an ErrorObserver set");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class SchedulerRunOnError implements RunWithParam<Throwable> {
            SchedulerRunOnError() {
            }

            @Override // io.objectbox.reactive.RunWithParam
            public void run(Throwable th) {
                if (ActionObserver.this.subscription.isCanceled()) {
                    return;
                }
                SubscriptionBuilder.this.errorObserver.onError(th);
            }
        }
    }
}
