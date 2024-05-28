package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import p475rx.Completable;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.subscriptions.Subscriptions;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
final class CompletableHelper {
    CompletableHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CallAdapter<Completable> createCallAdapter(Scheduler scheduler) {
        return new CompletableCallAdapter(scheduler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CompletableCallOnSubscribe implements Completable.CompletableOnSubscribe {
        private final Call originalCall;

        CompletableCallOnSubscribe(Call call) {
            this.originalCall = call;
        }

        @Override // p475rx.functions.Action1
        public void call(Completable.CompletableSubscriber completableSubscriber) {
            final Call mo25661clone = this.originalCall.mo25661clone();
            Subscription create = Subscriptions.create(new Action0() { // from class: retrofit2.adapter.rxjava.CompletableHelper.CompletableCallOnSubscribe.1
                @Override // p475rx.functions.Action0
                public void call() {
                    mo25661clone.cancel();
                }
            });
            completableSubscriber.onSubscribe(create);
            try {
                Response execute = mo25661clone.execute();
                if (!create.isUnsubscribed()) {
                    if (execute.isSuccessful()) {
                        completableSubscriber.onCompleted();
                    } else {
                        completableSubscriber.onError(new HttpException(execute));
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                if (create.isUnsubscribed()) {
                    return;
                }
                completableSubscriber.onError(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class CompletableCallAdapter implements CallAdapter<Completable> {
        private final Scheduler scheduler;

        CompletableCallAdapter(Scheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return Void.class;
        }

        @Override // retrofit2.CallAdapter
        public Completable adapt(Call call) {
            Completable create = Completable.create(new CompletableCallOnSubscribe(call));
            Scheduler scheduler = this.scheduler;
            return scheduler != null ? create.subscribeOn(scheduler) : create;
        }
    }
}
