package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RadioGroup;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxRadioGroup {
    @CheckResult
    @NonNull
    public static InitialValueObservable<Integer> checkedChanges(@NonNull RadioGroup radioGroup) {
        Preconditions.checkNotNull(radioGroup, "view == null");
        return new RadioGroupCheckedChangeObservable(radioGroup);
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Integer> checked(@NonNull final RadioGroup radioGroup) {
        Preconditions.checkNotNull(radioGroup, "view == null");
        return new Consumer<Integer>() { // from class: com.jakewharton.rxbinding2.widget.RxRadioGroup.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                if (num.intValue() == -1) {
                    radioGroup.clearCheck();
                } else {
                    radioGroup.check(num.intValue());
                }
            }
        };
    }

    private RxRadioGroup() {
        throw new AssertionError("No instances.");
    }
}
