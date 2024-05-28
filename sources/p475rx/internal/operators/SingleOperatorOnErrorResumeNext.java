package p475rx.internal.operators;

import p475rx.Single;
import p475rx.SingleSubscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.SingleOperatorOnErrorResumeNext */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SingleOperatorOnErrorResumeNext<T> implements Single.OnSubscribe<T> {
    private final Single<? extends T> originalSingle;
    private final Func1<Throwable, ? extends Single<? extends T>> resumeFunctionInCaseOfError;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    private SingleOperatorOnErrorResumeNext(Single<? extends T> single, Func1<Throwable, ? extends Single<? extends T>> func1) {
        if (single == null) {
            throw new NullPointerException("originalSingle must not be null");
        }
        if (func1 == null) {
            throw new NullPointerException("resumeFunctionInCaseOfError must not be null");
        }
        this.originalSingle = single;
        this.resumeFunctionInCaseOfError = func1;
    }

    public static <T> SingleOperatorOnErrorResumeNext<T> withFunction(Single<? extends T> single, Func1<Throwable, ? extends Single<? extends T>> func1) {
        return new SingleOperatorOnErrorResumeNext<>(single, func1);
    }

    public static <T> SingleOperatorOnErrorResumeNext<T> withOther(Single<? extends T> single, final Single<? extends T> single2) {
        if (single2 == null) {
            throw new NullPointerException("resumeSingleInCaseOfError must not be null");
        }
        return new SingleOperatorOnErrorResumeNext<>(single, new Func1<Throwable, Single<? extends T>>() { // from class: rx.internal.operators.SingleOperatorOnErrorResumeNext.1
            @Override // p475rx.functions.Func1
            public Single<? extends T> call(Throwable th) {
                return Single.this;
            }
        });
    }

    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOperatorOnErrorResumeNext.2
            @Override // p475rx.SingleSubscriber
            public void onSuccess(T t) {
                singleSubscriber.onSuccess(t);
            }

            @Override // p475rx.SingleSubscriber
            public void onError(Throwable th) {
                try {
                    ((Single) SingleOperatorOnErrorResumeNext.this.resumeFunctionInCaseOfError.call(th)).subscribe(singleSubscriber);
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, singleSubscriber);
                }
            }
        };
        singleSubscriber.add(singleSubscriber2);
        this.originalSingle.subscribe((SingleSubscriber<? super Object>) singleSubscriber2);
    }
}
