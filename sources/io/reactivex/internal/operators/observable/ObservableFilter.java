package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.observers.BasicFuseableObserver;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableFilter<T> extends AbstractObservableWithUpstream<T, T> {
    final Predicate<? super T> predicate;

    public ObservableFilter(ObservableSource<T> observableSource, Predicate<? super T> predicate) {
        super(observableSource);
        this.predicate = predicate;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new FilterObserver(observer, this.predicate));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class FilterObserver<T> extends BasicFuseableObserver<T, T> {

        /* renamed from: filter  reason: collision with root package name */
        final Predicate<? super T> f27871filter;

        FilterObserver(Observer<? super T> observer, Predicate<? super T> predicate) {
            super(observer);
            this.f27871filter = predicate;
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.sourceMode == 0) {
                try {
                    if (this.f27871filter.test(t)) {
                        this.actual.onNext(t);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    fail(th);
                    return;
                }
            }
            this.actual.onNext(null);
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Exception {
            T poll;
            do {
                poll = this.f24406qs.poll();
                if (poll == null) {
                    break;
                }
            } while (!this.f27871filter.test(poll));
            return poll;
        }
    }
}
