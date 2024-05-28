package com.sinovatech.unicom.basic.p315ui.utils;

import com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sinovatech.unicom.basic.ui.utils.HomeTabDebounce */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeTabDebounce {
    public static boolean isOpenHomeTabCaiji;
    private Disposable disposable;
    private PublishSubject<Object> subject;

    public HomeTabDebounce(final Consumer<Boolean> consumer) {
        isOpenHomeTabCaiji = WebHostSafelyManager.getInstance().isOpenHomeTabCaiji();
        this.subject = PublishSubject.create();
        this.disposable = this.subject.debounce(200L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.utils.-$$Lambda$HomeTabDebounce$LLIqgzcs-lDrF5e6w3vRLaPFC_4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Consumer.this.accept(Boolean.valueOf(HomeTabDebounce.isOpenHomeTabCaiji));
            }
        });
    }

    public void onStartEvent() {
        if (isOpenHomeTabCaiji) {
            this.subject.onNext(new Object());
        }
    }

    public void clear() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
    }
}
