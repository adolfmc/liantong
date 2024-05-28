package p475rx.internal.operators;

import p475rx.Single;
import p475rx.SingleSubscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.internal.util.RxJavaPluginUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.SingleDoAfterTerminate */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SingleDoAfterTerminate<T> implements Single.OnSubscribe<T> {
    final Action0 action;
    final Single<T> source;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleDoAfterTerminate(Single<T> single, Action0 action0) {
        this.source = single;
        this.action = action0;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        SingleDoAfterTerminateSubscriber singleDoAfterTerminateSubscriber = new SingleDoAfterTerminateSubscriber(singleSubscriber, this.action);
        singleSubscriber.add(singleDoAfterTerminateSubscriber);
        this.source.subscribe(singleDoAfterTerminateSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.SingleDoAfterTerminate$SingleDoAfterTerminateSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SingleDoAfterTerminateSubscriber<T> extends SingleSubscriber<T> {
        final Action0 action;
        final SingleSubscriber<? super T> actual;

        public SingleDoAfterTerminateSubscriber(SingleSubscriber<? super T> singleSubscriber, Action0 action0) {
            this.actual = singleSubscriber;
            this.action = action0;
        }

        @Override // p475rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                this.actual.onSuccess(t);
            } finally {
                doAction();
            }
        }

        @Override // p475rx.SingleSubscriber
        public void onError(Throwable th) {
            try {
                this.actual.onError(th);
            } finally {
                doAction();
            }
        }

        void doAction() {
            try {
                this.action.call();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPluginUtils.handleException(th);
            }
        }
    }
}
