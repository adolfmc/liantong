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
public abstract class SeekBarStopChangeEvent extends SeekBarChangeEvent {
    @CheckResult
    @NonNull
    public static SeekBarStopChangeEvent create(@NonNull SeekBar seekBar) {
        return new AutoValue_SeekBarStopChangeEvent(seekBar);
    }
}
