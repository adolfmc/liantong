package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;
import com.google.auto.value.AutoValue;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@AutoValue
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class SeekBarProgressChangeEvent extends SeekBarChangeEvent {
    public abstract boolean fromUser();

    public abstract int progress();

    @CheckResult
    @NonNull
    public static SeekBarProgressChangeEvent create(@NonNull SeekBar seekBar, int i, boolean z) {
        return new AutoValue_SeekBarProgressChangeEvent(seekBar, i, z);
    }
}
