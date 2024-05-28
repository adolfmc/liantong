package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.google.auto.value.AutoValue;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@AutoValue
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class TextViewBeforeTextChangeEvent {
    public abstract int after();

    public abstract int count();

    public abstract int start();

    @NonNull
    public abstract CharSequence text();

    @NonNull
    public abstract TextView view();

    @CheckResult
    @NonNull
    public static TextViewBeforeTextChangeEvent create(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        return new AutoValue_TextViewBeforeTextChangeEvent(textView, charSequence, i, i2, i3);
    }
}
