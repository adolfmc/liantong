package p475rx.internal.operators;

import java.util.Arrays;
import p475rx.Single;
import p475rx.SingleSubscriber;
import p475rx.exceptions.CompositeException;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action1;
import p475rx.functions.Func0;
import p475rx.functions.Func1;
import p475rx.plugins.RxJavaPlugins;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.SingleOnSubscribeUsing */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SingleOnSubscribeUsing<T, Resource> implements Single.OnSubscribe<T> {
    final Action1<? super Resource> disposeAction;
    final boolean disposeEagerly;
    final Func0<Resource> resourceFactory;
    final Func1<? super Resource, ? extends Single<? extends T>> singleFactory;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleOnSubscribeUsing(Func0<Resource> func0, Func1<? super Resource, ? extends Single<? extends T>> func1, Action1<? super Resource> action1, boolean z) {
        this.resourceFactory = func0;
        this.singleFactory = func1;
        this.disposeAction = action1;
        this.disposeEagerly = z;
    }

    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        try {
            final Resource call = this.resourceFactory.call();
            try {
                Single<? extends T> call2 = this.singleFactory.call(call);
                if (call2 == null) {
                    handleSubscriptionTimeError(singleSubscriber, call, new NullPointerException("The single"));
                    return;
                }
                SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOnSubscribeUsing.1
                    @Override // p475rx.SingleSubscriber
                    public void onSuccess(T t) {
                        if (SingleOnSubscribeUsing.this.disposeEagerly) {
                            try {
                                SingleOnSubscribeUsing.this.disposeAction.call((Object) call);
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                singleSubscriber.onError(th);
                                return;
                            }
                        }
                        singleSubscriber.onSuccess(t);
                        if (SingleOnSubscribeUsing.this.disposeEagerly) {
                            return;
                        }
                        try {
                            SingleOnSubscribeUsing.this.disposeAction.call((Object) call);
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            RxJavaPlugins.getInstance().getErrorHandler().handleError(th2);
                        }
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // p475rx.SingleSubscriber
                    public void onError(Throwable th) {
                        SingleOnSubscribeUsing.this.handleSubscriptionTimeError(singleSubscriber, call, th);
                    }
                };
                singleSubscriber.add(singleSubscriber2);
                call2.subscribe((SingleSubscriber<? super Object>) singleSubscriber2);
            } catch (Throwable th) {
                handleSubscriptionTimeError(singleSubscriber, call, th);
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            singleSubscriber.onError(th2);
        }
    }

    void handleSubscriptionTimeError(SingleSubscriber<? super T> singleSubscriber, Resource resource, Throwable th) {
        Exceptions.throwIfFatal(th);
        if (this.disposeEagerly) {
            try {
                this.disposeAction.call(resource);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(Arrays.asList(th, th2));
            }
        }
        singleSubscriber.onError(th);
        if (this.disposeEagerly) {
            return;
        }
        try {
            this.disposeAction.call(resource);
        } catch (Throwable th3) {
            Exceptions.throwIfFatal(th3);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th3);
        }
    }
}
