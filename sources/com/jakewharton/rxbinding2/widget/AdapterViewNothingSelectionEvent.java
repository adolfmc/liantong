package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AdapterView;
import com.google.auto.value.AutoValue;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@AutoValue
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AdapterViewNothingSelectionEvent extends AdapterViewSelectionEvent {
    @CheckResult
    @NonNull
    public static AdapterViewSelectionEvent create(@NonNull AdapterView<?> adapterView) {
        return new AutoValue_AdapterViewNothingSelectionEvent(adapterView);
    }
}
