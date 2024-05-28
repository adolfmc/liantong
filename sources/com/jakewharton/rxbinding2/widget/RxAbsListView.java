package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AbsListView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxAbsListView {
    @CheckResult
    @NonNull
    public static Observable<AbsListViewScrollEvent> scrollEvents(@NonNull AbsListView absListView) {
        Preconditions.checkNotNull(absListView, "absListView == null");
        return new AbsListViewScrollEventObservable(absListView);
    }

    private RxAbsListView() {
        throw new AssertionError("No instances.");
    }
}
