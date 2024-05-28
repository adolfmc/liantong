package retrofit2.adapter.rxjava;

import p475rx.Observable;
import p475rx.Subscriber;
import retrofit2.Response;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorMapResponseToBodyOrError<T> implements Observable.Operator<T, Response<T>> {
    private static final OperatorMapResponseToBodyOrError<Object> INSTANCE = new OperatorMapResponseToBodyOrError<>();

    OperatorMapResponseToBodyOrError() {
    }

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <R> OperatorMapResponseToBodyOrError<R> instance() {
        return (OperatorMapResponseToBodyOrError<R>) INSTANCE;
    }

    public Subscriber<? super Response<T>> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<Response<T>>(subscriber) { // from class: retrofit2.adapter.rxjava.OperatorMapResponseToBodyOrError.1
            @Override // p475rx.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((Response) ((Response) obj));
            }

            public void onNext(Response<T> response) {
                if (response.isSuccessful()) {
                    subscriber.onNext(response.body());
                } else {
                    subscriber.onError(new HttpException(response));
                }
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }
        };
    }
}
