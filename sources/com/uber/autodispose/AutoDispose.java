package com.uber.autodispose;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.subscribers.TestSubscriber;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class AutoDispose {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface ScopeHandler {
        @CheckReturnValue
        Function<Completable, CompletableSubscribeProxy> forCompletable();

        @CheckReturnValue
        <T> Function<Flowable<? extends T>, FlowableSubscribeProxy<T>> forFlowable();

        @CheckReturnValue
        <T> Function<Maybe<? extends T>, MaybeSubscribeProxy<T>> forMaybe();

        @CheckReturnValue
        <T> Function<Observable<? extends T>, ObservableSubscribeProxy<T>> forObservable();

        @CheckReturnValue
        <T> Function<Single<? extends T>, SingleSubscribeProxy<T>> forSingle();
    }

    @CheckReturnValue
    @Deprecated
    public static ScopeHandler with(Maybe<?> maybe) {
        return new MaybeScopeHandlerImpl(maybe);
    }

    @CheckReturnValue
    @Deprecated
    public static ScopeHandler with(ScopeProvider scopeProvider) {
        return new ScopeProviderHandlerImpl(scopeProvider);
    }

    @CheckReturnValue
    @Deprecated
    public static ScopeHandler with(LifecycleScopeProvider<?> lifecycleScopeProvider) {
        return new LifecycleScopeProviderHandlerImpl(lifecycleScopeProvider);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static final class MaybeScopeHandlerImpl implements ScopeHandler {
        final Maybe<?> scope;

        MaybeScopeHandlerImpl(Maybe<?> maybe) {
            this.scope = maybe;
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Flowable<? extends T>, FlowableSubscribeProxy<T>> forFlowable() {
            return new FlowableScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Observable<? extends T>, ObservableSubscribeProxy<T>> forObservable() {
            return new ObservableScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Maybe<? extends T>, MaybeSubscribeProxy<T>> forMaybe() {
            return new MaybeScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Single<? extends T>, SingleSubscribeProxy<T>> forSingle() {
            return new SingleScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public Function<Completable, CompletableSubscribeProxy> forCompletable() {
            return new CompletableScoper(this.scope);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static final class ScopeProviderHandlerImpl implements ScopeHandler {
        final ScopeProvider scope;

        ScopeProviderHandlerImpl(ScopeProvider scopeProvider) {
            this.scope = scopeProvider;
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Flowable<? extends T>, FlowableSubscribeProxy<T>> forFlowable() {
            return new FlowableScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Observable<? extends T>, ObservableSubscribeProxy<T>> forObservable() {
            return new ObservableScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Maybe<? extends T>, MaybeSubscribeProxy<T>> forMaybe() {
            return new MaybeScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Single<? extends T>, SingleSubscribeProxy<T>> forSingle() {
            return new SingleScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public Function<Completable, CompletableSubscribeProxy> forCompletable() {
            return new CompletableScoper(this.scope);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static final class LifecycleScopeProviderHandlerImpl implements ScopeHandler {
        final LifecycleScopeProvider<?> scope;

        LifecycleScopeProviderHandlerImpl(LifecycleScopeProvider<?> lifecycleScopeProvider) {
            this.scope = lifecycleScopeProvider;
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Flowable<? extends T>, FlowableSubscribeProxy<T>> forFlowable() {
            return new FlowableScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Observable<? extends T>, ObservableSubscribeProxy<T>> forObservable() {
            return new ObservableScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Maybe<? extends T>, MaybeSubscribeProxy<T>> forMaybe() {
            return new MaybeScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public <T> Function<Single<? extends T>, SingleSubscribeProxy<T>> forSingle() {
            return new SingleScoper(this.scope);
        }

        @Override // com.uber.autodispose.AutoDispose.ScopeHandler
        public Function<Completable, CompletableSubscribeProxy> forCompletable() {
            return new CompletableScoper(this.scope);
        }
    }

    public static <T> AutoDisposeConverter<T> autoDisposable(final ScopeProvider scopeProvider) {
        AutoDisposeUtil.checkNotNull(scopeProvider, "provider == null");
        return autoDisposable(Maybe.defer(new Callable<MaybeSource<?>>() { // from class: com.uber.autodispose.AutoDispose.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MaybeSource<?> call() {
                return ScopeProvider.this.requestScope();
            }
        }));
    }

    public static <T> AutoDisposeConverter<T> autoDisposable(LifecycleScopeProvider<?> lifecycleScopeProvider) {
        return autoDisposable(ScopeUtil.deferredResolvedLifecycle((LifecycleScopeProvider) AutoDisposeUtil.checkNotNull(lifecycleScopeProvider, "provider == null")));
    }

    public static <T> AutoDisposeConverter<T> autoDisposable(final Maybe<?> maybe) {
        AutoDisposeUtil.checkNotNull(maybe, "scope == null");
        return new AutoDisposeConverter<T>() { // from class: com.uber.autodispose.AutoDispose.2
            @Override // io.reactivex.parallel.ParallelFlowableConverter
            public ParallelFlowableSubscribeProxy<T> apply(final ParallelFlowable<T> parallelFlowable) {
                return new ParallelFlowableSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.2.1
                    @Override // com.uber.autodispose.ParallelFlowableSubscribeProxy
                    public void subscribe(Subscriber<? super T>[] subscriberArr) {
                        new AutoDisposeParallelFlowable(parallelFlowable, Maybe.this).subscribe(subscriberArr);
                    }
                };
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.CompletableConverter
            public CompletableSubscribeProxy apply(final Completable completable) {
                return new CompletableSubscribeProxy() { // from class: com.uber.autodispose.AutoDispose.2.2
                    @Override // com.uber.autodispose.CompletableSubscribeProxy
                    public Disposable subscribe() {
                        return new AutoDisposeCompletable(completable, Maybe.this).subscribe();
                    }

                    @Override // com.uber.autodispose.CompletableSubscribeProxy
                    public Disposable subscribe(Action action) {
                        return new AutoDisposeCompletable(completable, Maybe.this).subscribe(action);
                    }

                    @Override // com.uber.autodispose.CompletableSubscribeProxy
                    public Disposable subscribe(Action action, Consumer<? super Throwable> consumer) {
                        return new AutoDisposeCompletable(completable, Maybe.this).subscribe(action, consumer);
                    }

                    @Override // com.uber.autodispose.CompletableSubscribeProxy
                    public void subscribe(CompletableObserver completableObserver) {
                        new AutoDisposeCompletable(completable, Maybe.this).subscribe(completableObserver);
                    }

                    @Override // com.uber.autodispose.CompletableSubscribeProxy
                    public <E extends CompletableObserver> E subscribeWith(E e) {
                        return (E) new AutoDisposeCompletable(completable, Maybe.this).subscribeWith(e);
                    }

                    @Override // com.uber.autodispose.CompletableSubscribeProxy
                    public TestObserver<Void> test() {
                        TestObserver<Void> testObserver = new TestObserver<>();
                        subscribe(testObserver);
                        return testObserver;
                    }

                    @Override // com.uber.autodispose.CompletableSubscribeProxy
                    public TestObserver<Void> test(boolean z) {
                        TestObserver<Void> testObserver = new TestObserver<>();
                        if (z) {
                            testObserver.cancel();
                        }
                        subscribe(testObserver);
                        return testObserver;
                    }
                };
            }

            @Override // io.reactivex.FlowableConverter
            public FlowableSubscribeProxy<T> apply(final Flowable<T> flowable) {
                return new FlowableSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.2.3
                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public Disposable subscribe() {
                        return new AutoDisposeFlowable(flowable, Maybe.this).subscribe();
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer) {
                        return new AutoDisposeFlowable(flowable, Maybe.this).subscribe(consumer);
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
                        return new AutoDisposeFlowable(flowable, Maybe.this).subscribe(consumer, consumer2);
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
                        return new AutoDisposeFlowable(flowable, Maybe.this).subscribe(consumer, consumer2, action);
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Consumer<? super Subscription> consumer3) {
                        return new AutoDisposeFlowable(flowable, Maybe.this).subscribe(consumer, consumer2, action, consumer3);
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public void subscribe(Subscriber<T> subscriber) {
                        new AutoDisposeFlowable(flowable, Maybe.this).subscribe(subscriber);
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public <E extends Subscriber<? super T>> E subscribeWith(E e) {
                        return (E) new AutoDisposeFlowable(flowable, Maybe.this).subscribeWith(e);
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public TestSubscriber<T> test() {
                        TestSubscriber<T> testSubscriber = new TestSubscriber<>();
                        subscribe(testSubscriber);
                        return testSubscriber;
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public TestSubscriber<T> test(long j) {
                        TestSubscriber<T> testSubscriber = new TestSubscriber<>(j);
                        subscribe(testSubscriber);
                        return testSubscriber;
                    }

                    @Override // com.uber.autodispose.FlowableSubscribeProxy
                    public TestSubscriber<T> test(long j, boolean z) {
                        TestSubscriber<T> testSubscriber = new TestSubscriber<>(j);
                        if (z) {
                            testSubscriber.cancel();
                        }
                        subscribe(testSubscriber);
                        return testSubscriber;
                    }
                };
            }

            @Override // io.reactivex.MaybeConverter
            public MaybeSubscribeProxy<T> apply(final Maybe<T> maybe2) {
                return new MaybeSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.2.4
                    @Override // com.uber.autodispose.MaybeSubscribeProxy
                    public Disposable subscribe() {
                        return new AutoDisposeMaybe(maybe2, Maybe.this).subscribe();
                    }

                    @Override // com.uber.autodispose.MaybeSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer) {
                        return new AutoDisposeMaybe(maybe2, Maybe.this).subscribe(consumer);
                    }

                    @Override // com.uber.autodispose.MaybeSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
                        return new AutoDisposeMaybe(maybe2, Maybe.this).subscribe(consumer, consumer2);
                    }

                    @Override // com.uber.autodispose.MaybeSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
                        return new AutoDisposeMaybe(maybe2, Maybe.this).subscribe(consumer, consumer2, action);
                    }

                    @Override // com.uber.autodispose.MaybeSubscribeProxy
                    public void subscribe(MaybeObserver<T> maybeObserver) {
                        new AutoDisposeMaybe(maybe2, Maybe.this).subscribe(maybeObserver);
                    }

                    @Override // com.uber.autodispose.MaybeSubscribeProxy
                    public <E extends MaybeObserver<? super T>> E subscribeWith(E e) {
                        return (E) new AutoDisposeMaybe(maybe2, Maybe.this).subscribeWith(e);
                    }

                    @Override // com.uber.autodispose.MaybeSubscribeProxy
                    public TestObserver<T> test() {
                        TestObserver<T> testObserver = new TestObserver<>();
                        subscribe(testObserver);
                        return testObserver;
                    }

                    @Override // com.uber.autodispose.MaybeSubscribeProxy
                    public TestObserver<T> test(boolean z) {
                        TestObserver<T> testObserver = new TestObserver<>();
                        if (z) {
                            testObserver.cancel();
                        }
                        subscribe(testObserver);
                        return testObserver;
                    }
                };
            }

            @Override // io.reactivex.ObservableConverter
            public ObservableSubscribeProxy<T> apply(final Observable<T> observable) {
                return new ObservableSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.2.5
                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public Disposable subscribe() {
                        return new AutoDisposeObservable(observable, Maybe.this).subscribe();
                    }

                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer) {
                        return new AutoDisposeObservable(observable, Maybe.this).subscribe(consumer);
                    }

                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
                        return new AutoDisposeObservable(observable, Maybe.this).subscribe(consumer, consumer2);
                    }

                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
                        return new AutoDisposeObservable(observable, Maybe.this).subscribe(consumer, consumer2, action);
                    }

                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Consumer<? super Disposable> consumer3) {
                        return new AutoDisposeObservable(observable, Maybe.this).subscribe(consumer, consumer2, action, consumer3);
                    }

                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public void subscribe(Observer<T> observer) {
                        new AutoDisposeObservable(observable, Maybe.this).subscribe(observer);
                    }

                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public <E extends Observer<? super T>> E subscribeWith(E e) {
                        return (E) new AutoDisposeObservable(observable, Maybe.this).subscribeWith(e);
                    }

                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public TestObserver<T> test() {
                        TestObserver<T> testObserver = new TestObserver<>();
                        subscribe(testObserver);
                        return testObserver;
                    }

                    @Override // com.uber.autodispose.ObservableSubscribeProxy
                    public TestObserver<T> test(boolean z) {
                        TestObserver<T> testObserver = new TestObserver<>();
                        if (z) {
                            testObserver.dispose();
                        }
                        subscribe(testObserver);
                        return testObserver;
                    }
                };
            }

            @Override // io.reactivex.SingleConverter
            public SingleSubscribeProxy<T> apply(final Single<T> single) {
                return new SingleSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.2.6
                    @Override // com.uber.autodispose.SingleSubscribeProxy
                    public Disposable subscribe() {
                        return new AutoDisposeSingle(single, Maybe.this).subscribe();
                    }

                    @Override // com.uber.autodispose.SingleSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer) {
                        return new AutoDisposeSingle(single, Maybe.this).subscribe(consumer);
                    }

                    @Override // com.uber.autodispose.SingleSubscribeProxy
                    public Disposable subscribe(BiConsumer<? super T, ? super Throwable> biConsumer) {
                        return new AutoDisposeSingle(single, Maybe.this).subscribe(biConsumer);
                    }

                    @Override // com.uber.autodispose.SingleSubscribeProxy
                    public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
                        return new AutoDisposeSingle(single, Maybe.this).subscribe(consumer, consumer2);
                    }

                    @Override // com.uber.autodispose.SingleSubscribeProxy
                    public void subscribe(SingleObserver<T> singleObserver) {
                        new AutoDisposeSingle(single, Maybe.this).subscribe(singleObserver);
                    }

                    @Override // com.uber.autodispose.SingleSubscribeProxy
                    public <E extends SingleObserver<? super T>> E subscribeWith(E e) {
                        return (E) new AutoDisposeSingle(single, Maybe.this).subscribeWith(e);
                    }

                    @Override // com.uber.autodispose.SingleSubscribeProxy
                    public TestObserver<T> test() {
                        TestObserver<T> testObserver = new TestObserver<>();
                        subscribe(testObserver);
                        return testObserver;
                    }

                    @Override // com.uber.autodispose.SingleSubscribeProxy
                    public TestObserver<T> test(boolean z) {
                        TestObserver<T> testObserver = new TestObserver<>();
                        if (z) {
                            testObserver.dispose();
                        }
                        subscribe(testObserver);
                        return testObserver;
                    }
                };
            }
        };
    }

    private AutoDispose() {
        throw new AssertionError("No instances");
    }
}
