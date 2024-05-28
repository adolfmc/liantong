package p475rx.observers;

import java.util.Arrays;
import p475rx.Subscriber;
import p475rx.exceptions.CompositeException;
import p475rx.exceptions.Exceptions;
import p475rx.exceptions.OnCompletedFailedException;
import p475rx.exceptions.OnErrorFailedException;
import p475rx.exceptions.OnErrorNotImplementedException;
import p475rx.exceptions.UnsubscribeFailedException;
import p475rx.internal.util.RxJavaPluginUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.observers.SafeSubscriber */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SafeSubscriber<T> extends Subscriber<T> {
    private final Subscriber<? super T> actual;
    boolean done;

    public SafeSubscriber(Subscriber<? super T> subscriber) {
        super(subscriber);
        this.done = false;
        this.actual = subscriber;
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        UnsubscribeFailedException unsubscribeFailedException;
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onCompleted();
            try {
                unsubscribe();
            } finally {
            }
        } catch (Throwable th) {
            try {
                Exceptions.throwIfFatal(th);
                RxJavaPluginUtils.handleException(th);
                throw new OnCompletedFailedException(th.getMessage(), th);
            } catch (Throwable th2) {
                try {
                    unsubscribe();
                    throw th2;
                } finally {
                }
            }
        }
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        Exceptions.throwIfFatal(th);
        if (this.done) {
            return;
        }
        this.done = true;
        _onError(th);
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        try {
            if (this.done) {
                return;
            }
            this.actual.onNext(t);
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, this);
        }
    }

    protected void _onError(Throwable th) {
        RxJavaPluginUtils.handleException(th);
        try {
            this.actual.onError(th);
            try {
                unsubscribe();
            } catch (RuntimeException e) {
                RxJavaPluginUtils.handleException(e);
                throw new OnErrorFailedException(e);
            }
        } catch (Throwable th2) {
            if (th2 instanceof OnErrorNotImplementedException) {
                try {
                    unsubscribe();
                    throw ((OnErrorNotImplementedException) th2);
                } catch (Throwable th3) {
                    RxJavaPluginUtils.handleException(th3);
                    throw new RuntimeException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(th, th3)));
                }
            }
            RxJavaPluginUtils.handleException(th2);
            try {
                unsubscribe();
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(th, th2)));
            } catch (Throwable th4) {
                RxJavaPluginUtils.handleException(th4);
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(th, th2, th4)));
            }
        }
    }

    public Subscriber<? super T> getActual() {
        return this.actual;
    }
}
