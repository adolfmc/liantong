package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RatingBar;
import com.google.auto.value.AutoValue;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@AutoValue
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class RatingBarChangeEvent {
    public abstract boolean fromUser();

    public abstract float rating();

    @NonNull
    public abstract RatingBar view();

    @CheckResult
    @NonNull
    public static RatingBarChangeEvent create(@NonNull RatingBar ratingBar, float f, boolean z) {
        return new AutoValue_RatingBarChangeEvent(ratingBar, f, z);
    }
}
