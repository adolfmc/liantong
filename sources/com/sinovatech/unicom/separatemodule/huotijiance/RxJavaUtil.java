package com.sinovatech.unicom.separatemodule.huotijiance;

import android.annotation.SuppressLint;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RxJavaUtil {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnRxAndroidListener<T> {
        T doInBackground() throws Throwable;

        void onError(Throwable th);

        void onFinish(T t);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnRxLoopListener {
        void onError(Throwable th);

        void onExecute();

        void onFinish();

        Boolean takeWhile() throws Exception;
    }

    public static <T> void run(final OnRxAndroidListener<T> onRxAndroidListener) {
        Observable.create(new ObservableOnSubscribe<T>() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(@NonNull ObservableEmitter<T> observableEmitter) {
                try {
                    Object doInBackground = OnRxAndroidListener.this.doInBackground();
                    if (doInBackground != null) {
                        observableEmitter.onNext(doInBackground);
                    } else {
                        observableEmitter.onError(new NullPointerException("on doInBackground result not null"));
                    }
                } catch (Throwable th) {
                    observableEmitter.onError(th);
                }
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).safeSubscribe((Observer<T>) new Observer<T>() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NonNull Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(@NonNull T t) {
                OnRxAndroidListener.this.onFinish(t);
            }

            @Override // io.reactivex.Observer
            public void onError(@NonNull Throwable th) {
                OnRxAndroidListener.this.onError(th);
            }
        });
    }

    @SuppressLint({"CheckResult"})
    public static Disposable loop(long j, final OnRxLoopListener onRxLoopListener) {
        return (Disposable) Observable.interval(j, TimeUnit.MILLISECONDS).takeWhile(new Predicate<Long>() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.4
            @Override // io.reactivex.functions.Predicate
            public boolean test(Long l) throws Exception {
                return OnRxLoopListener.this.takeWhile().booleanValue();
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<Long>() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.RxJavaUtil.3
            @Override // io.reactivex.Observer
            public void onNext(Long l) {
                OnRxLoopListener.this.onExecute();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                OnRxLoopListener.this.onFinish();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                OnRxLoopListener.this.onError(th);
            }
        });
    }
}
