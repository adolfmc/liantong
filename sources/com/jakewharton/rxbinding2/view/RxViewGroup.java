package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxViewGroup {
    @CheckResult
    @NonNull
    public static Observable<ViewGroupHierarchyChangeEvent> changeEvents(@NonNull ViewGroup viewGroup) {
        Preconditions.checkNotNull(viewGroup, "viewGroup == null");
        return new ViewGroupHierarchyChangeEventObservable(viewGroup);
    }

    private RxViewGroup() {
        throw new AssertionError("No instances.");
    }
}
