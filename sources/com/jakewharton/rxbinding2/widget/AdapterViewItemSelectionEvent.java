package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import com.google.auto.value.AutoValue;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@AutoValue
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AdapterViewItemSelectionEvent extends AdapterViewSelectionEvent {
    /* renamed from: id */
    public abstract long mo13744id();

    public abstract int position();

    @NonNull
    public abstract View selectedView();

    @CheckResult
    @NonNull
    public static AdapterViewSelectionEvent create(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        return new AutoValue_AdapterViewItemSelectionEvent(adapterView, view, i, j);
    }
}
