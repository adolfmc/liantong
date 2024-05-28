package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextSwitcher;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxTextSwitcher {
    @CheckResult
    @NonNull
    public static Consumer<? super CharSequence> text(@NonNull final TextSwitcher textSwitcher) {
        Preconditions.checkNotNull(textSwitcher, "view == null");
        return new Consumer<CharSequence>() { // from class: com.jakewharton.rxbinding2.widget.RxTextSwitcher.1
            @Override // io.reactivex.functions.Consumer
            public void accept(CharSequence charSequence) {
                textSwitcher.setText(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super CharSequence> currentText(@NonNull final TextSwitcher textSwitcher) {
        Preconditions.checkNotNull(textSwitcher, "view == null");
        return new Consumer<CharSequence>() { // from class: com.jakewharton.rxbinding2.widget.RxTextSwitcher.2
            @Override // io.reactivex.functions.Consumer
            public void accept(CharSequence charSequence) {
                textSwitcher.setCurrentText(charSequence);
            }
        };
    }

    private RxTextSwitcher() {
        throw new AssertionError("No instances.");
    }
}
