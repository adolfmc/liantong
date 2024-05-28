package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.CheckedTextView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxCheckedTextView {
    @CheckResult
    @NonNull
    public static Consumer<? super Boolean> check(@NonNull final CheckedTextView checkedTextView) {
        Preconditions.checkNotNull(checkedTextView, "view == null");
        return new Consumer<Boolean>() { // from class: com.jakewharton.rxbinding2.widget.RxCheckedTextView.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) {
                checkedTextView.setChecked(bool.booleanValue());
            }
        };
    }

    private RxCheckedTextView() {
        throw new AssertionError("No instances.");
    }
}
