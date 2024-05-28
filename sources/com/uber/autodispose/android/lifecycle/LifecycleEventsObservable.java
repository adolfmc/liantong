package com.uber.autodispose.android.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.RestrictTo;
import com.uber.autodispose.android.internal.AutoDisposeAndroidUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.subjects.BehaviorSubject;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class LifecycleEventsObservable extends Observable<Lifecycle.Event> {
    private final BehaviorSubject<Lifecycle.Event> eventsObservable = BehaviorSubject.create();
    private final Lifecycle lifecycle;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LifecycleEventsObservable(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lifecycle.Event getValue() {
        return this.eventsObservable.getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void backfillEvents() {
        Lifecycle.Event event;
        switch (this.lifecycle.getCurrentState()) {
            case INITIALIZED:
                event = Lifecycle.Event.ON_CREATE;
                break;
            case CREATED:
                event = Lifecycle.Event.ON_START;
                break;
            case STARTED:
            case RESUMED:
                event = Lifecycle.Event.ON_RESUME;
                break;
            default:
                event = Lifecycle.Event.ON_DESTROY;
                break;
        }
        this.eventsObservable.onNext(event);
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Lifecycle.Event> observer) {
        ArchLifecycleObserver archLifecycleObserver = new ArchLifecycleObserver(this.lifecycle, observer, this.eventsObservable);
        observer.onSubscribe(archLifecycleObserver);
        if (!AutoDisposeAndroidUtil.isMainThread()) {
            observer.onError(new IllegalStateException("Lifecycles can only be bound to on the main thread!"));
            return;
        }
        this.lifecycle.addObserver(archLifecycleObserver);
        if (archLifecycleObserver.isDisposed()) {
            this.lifecycle.removeObserver(archLifecycleObserver);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static final class ArchLifecycleObserver extends MainThreadDisposable implements LifecycleObserver {
        private final BehaviorSubject<Lifecycle.Event> eventsObservable;
        private final Lifecycle lifecycle;
        private final Observer<? super Lifecycle.Event> observer;

        ArchLifecycleObserver(Lifecycle lifecycle, Observer<? super Lifecycle.Event> observer, BehaviorSubject<Lifecycle.Event> behaviorSubject) {
            this.lifecycle = lifecycle;
            this.observer = observer;
            this.eventsObservable = behaviorSubject;
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.lifecycle.removeObserver(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public void onStateChange(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            if (isDisposed()) {
                return;
            }
            if (event != Lifecycle.Event.ON_CREATE || this.eventsObservable.getValue() != event) {
                this.eventsObservable.onNext(event);
            }
            this.observer.onNext(event);
        }
    }
}
