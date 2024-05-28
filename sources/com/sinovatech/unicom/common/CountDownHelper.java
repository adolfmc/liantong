package com.sinovatech.unicom.common;

import android.support.p086v7.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CountDownHelper {
    private AppCompatActivity context;

    public static Observable<Integer> countdown(final int i) {
        if (i < 0) {
            i = 0;
        }
        return Observable.interval(0L, 1L, TimeUnit.SECONDS).map(new Function<Long, Integer>() { // from class: com.sinovatech.unicom.common.CountDownHelper.1
            @Override // io.reactivex.functions.Function
            public Integer apply(Long l) throws Exception {
                return Integer.valueOf(i - l.intValue());
            }
        }).take(i + 1).subscribeOn(Schedulers.m1934io()).unsubscribeOn(Schedulers.m1934io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
    }
}
