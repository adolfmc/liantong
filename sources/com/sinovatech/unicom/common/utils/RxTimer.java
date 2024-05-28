package com.sinovatech.unicom.common.utils;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RxTimer {
    private Disposable mDisposable;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface RxAction {
        void action(long j);
    }

    public void timer(long j, final RxAction rxAction) {
        Observable.timer(j, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() { // from class: com.sinovatech.unicom.common.utils.RxTimer.1
            @Override // io.reactivex.Observer
            public void onSubscribe(@NonNull Disposable disposable) {
                RxTimer.this.mDisposable = disposable;
            }

            @Override // io.reactivex.Observer
            public void onNext(@NonNull Long l) {
                RxAction rxAction2 = rxAction;
                if (rxAction2 != null) {
                    rxAction2.action(l.longValue());
                }
            }

            @Override // io.reactivex.Observer
            public void onError(@NonNull Throwable th) {
                RxTimer.this.cancel();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                RxTimer.this.cancel();
            }
        });
    }

    public void interval(long j, final RxAction rxAction) {
        Observable.interval(j, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() { // from class: com.sinovatech.unicom.common.utils.RxTimer.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(@NonNull Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NonNull Disposable disposable) {
                RxTimer.this.mDisposable = disposable;
            }

            @Override // io.reactivex.Observer
            public void onNext(@NonNull Long l) {
                RxAction rxAction2 = rxAction;
                if (rxAction2 != null) {
                    rxAction2.action(l.longValue());
                }
            }
        });
    }

    public void cancel() {
        Disposable disposable = this.mDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.mDisposable.dispose();
    }
}
