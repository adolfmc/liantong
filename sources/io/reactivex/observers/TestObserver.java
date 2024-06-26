package io.reactivex.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TestObserver<T> extends BaseTestConsumer<T, TestObserver<T>> implements CompletableObserver, MaybeObserver<T>, Observer<T>, SingleObserver<T>, Disposable {
    private final Observer<? super T> actual;

    /* renamed from: qs */
    private QueueDisposable<T> f24806qs;
    private final AtomicReference<Disposable> subscription;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    enum EmptyObserver implements Observer<Object> {
        INSTANCE;

        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
        }

        @Override // io.reactivex.Observer
        public void onNext(Object obj) {
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
        }
    }

    public static <T> TestObserver<T> create() {
        return new TestObserver<>();
    }

    public static <T> TestObserver<T> create(Observer<? super T> observer) {
        return new TestObserver<>(observer);
    }

    public TestObserver() {
        this(EmptyObserver.INSTANCE);
    }

    public TestObserver(Observer<? super T> observer) {
        this.subscription = new AtomicReference<>();
        this.actual = observer;
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        this.lastThread = Thread.currentThread();
        if (disposable == null) {
            this.errors.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (!this.subscription.compareAndSet(null, disposable)) {
            disposable.dispose();
            if (this.subscription.get() != DisposableHelper.DISPOSED) {
                this.errors.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + disposable));
            }
        } else {
            if (this.initialFusionMode != 0 && (disposable instanceof QueueDisposable)) {
                this.f24806qs = (QueueDisposable) disposable;
                int requestFusion = this.f24806qs.requestFusion(this.initialFusionMode);
                this.establishedFusionMode = requestFusion;
                if (requestFusion == 1) {
                    this.checkSubscriptionOnce = true;
                    this.lastThread = Thread.currentThread();
                    while (true) {
                        try {
                            T poll = this.f24806qs.poll();
                            if (poll != null) {
                                this.values.add(poll);
                            } else {
                                this.completions++;
                                this.subscription.lazySet(DisposableHelper.DISPOSED);
                                return;
                            }
                        } catch (Throwable th) {
                            this.errors.add(th);
                            return;
                        }
                    }
                }
            }
            this.actual.onSubscribe(disposable);
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        if (!this.checkSubscriptionOnce) {
            this.checkSubscriptionOnce = true;
            if (this.subscription.get() == null) {
                this.errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.lastThread = Thread.currentThread();
        if (this.establishedFusionMode != 2) {
            this.values.add(t);
            if (t == null) {
                this.errors.add(new NullPointerException("onNext received a null value"));
            }
            this.actual.onNext(t);
            return;
        }
        while (true) {
            try {
                T poll = this.f24806qs.poll();
                if (poll == null) {
                    return;
                }
                this.values.add(poll);
            } catch (Throwable th) {
                this.errors.add(th);
                this.f24806qs.dispose();
                return;
            }
        }
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        if (!this.checkSubscriptionOnce) {
            this.checkSubscriptionOnce = true;
            if (this.subscription.get() == null) {
                this.errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.lastThread = Thread.currentThread();
            if (th == null) {
                this.errors.add(new NullPointerException("onError received a null Throwable"));
            } else {
                this.errors.add(th);
            }
            this.actual.onError(th);
        } finally {
            this.done.countDown();
        }
    }

    @Override // io.reactivex.CompletableObserver
    public void onComplete() {
        if (!this.checkSubscriptionOnce) {
            this.checkSubscriptionOnce = true;
            if (this.subscription.get() == null) {
                this.errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.lastThread = Thread.currentThread();
            this.completions++;
            this.actual.onComplete();
        } finally {
            this.done.countDown();
        }
    }

    public final boolean isCancelled() {
        return isDisposed();
    }

    public final void cancel() {
        dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public final void dispose() {
        DisposableHelper.dispose(this.subscription);
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.subscription.get());
    }

    public final boolean hasSubscription() {
        return this.subscription.get() != null;
    }

    @Override // io.reactivex.observers.BaseTestConsumer
    public final TestObserver<T> assertSubscribed() {
        if (this.subscription.get() != null) {
            return this;
        }
        throw fail("Not subscribed!");
    }

    @Override // io.reactivex.observers.BaseTestConsumer
    public final TestObserver<T> assertNotSubscribed() {
        if (this.subscription.get() != null) {
            throw fail("Subscribed!");
        }
        if (this.errors.isEmpty()) {
            return this;
        }
        throw fail("Not subscribed but errors found");
    }

    public final TestObserver<T> assertOf(Consumer<? super TestObserver<T>> consumer) {
        try {
            consumer.accept(this);
            return this;
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    final TestObserver<T> setInitialFusionMode(int i) {
        this.initialFusionMode = i;
        return this;
    }

    final TestObserver<T> assertFusionMode(int i) {
        int i2 = this.establishedFusionMode;
        if (i2 != i) {
            if (this.f24806qs != null) {
                throw new AssertionError("Fusion mode different. Expected: " + fusionModeToString(i) + ", actual: " + fusionModeToString(i2));
            }
            throw fail("Upstream is not fuseable");
        }
        return this;
    }

    static String fusionModeToString(int i) {
        switch (i) {
            case 0:
                return "NONE";
            case 1:
                return "SYNC";
            case 2:
                return "ASYNC";
            default:
                return "Unknown(" + i + ")";
        }
    }

    final TestObserver<T> assertFuseable() {
        if (this.f24806qs != null) {
            return this;
        }
        throw new AssertionError("Upstream is not fuseable.");
    }

    final TestObserver<T> assertNotFuseable() {
        if (this.f24806qs == null) {
            return this;
        }
        throw new AssertionError("Upstream is fuseable.");
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        onNext(t);
        onComplete();
    }
}
