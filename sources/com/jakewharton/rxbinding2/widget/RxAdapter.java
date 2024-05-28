package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.Adapter;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxAdapter {
    @CheckResult
    @NonNull
    public static <T extends Adapter> InitialValueObservable<T> dataChanges(@NonNull T t) {
        Preconditions.checkNotNull(t, "adapter == null");
        return new AdapterDataChangeObservable(t);
    }

    private RxAdapter() {
        throw new AssertionError("No instances.");
    }
}
