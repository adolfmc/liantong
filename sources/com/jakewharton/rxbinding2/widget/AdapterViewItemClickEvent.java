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
public abstract class AdapterViewItemClickEvent {
    @NonNull
    public abstract View clickedView();

    /* renamed from: id */
    public abstract long mo13746id();

    public abstract int position();

    @NonNull
    public abstract AdapterView<?> view();

    @CheckResult
    @NonNull
    public static AdapterViewItemClickEvent create(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        return new AutoValue_AdapterViewItemClickEvent(adapterView, view, i, j);
    }
}
