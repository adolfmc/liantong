package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.TextView;
import com.google.auto.value.AutoValue;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@AutoValue
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class TextViewAfterTextChangeEvent {
    @Nullable
    public abstract Editable editable();

    @NonNull
    public abstract TextView view();

    @CheckResult
    @NonNull
    public static TextViewAfterTextChangeEvent create(@NonNull TextView textView, @Nullable Editable editable) {
        return new AutoValue_TextViewAfterTextChangeEvent(textView, editable);
    }
}
