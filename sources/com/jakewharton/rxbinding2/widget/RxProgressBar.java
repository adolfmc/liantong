package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxProgressBar {
    @CheckResult
    @NonNull
    public static Consumer<? super Integer> incrementProgressBy(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() { // from class: com.jakewharton.rxbinding2.widget.RxProgressBar.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                progressBar.incrementProgressBy(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Integer> incrementSecondaryProgressBy(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() { // from class: com.jakewharton.rxbinding2.widget.RxProgressBar.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                progressBar.incrementSecondaryProgressBy(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Boolean> indeterminate(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Boolean>() { // from class: com.jakewharton.rxbinding2.widget.RxProgressBar.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) {
                progressBar.setIndeterminate(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Integer> max(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() { // from class: com.jakewharton.rxbinding2.widget.RxProgressBar.4
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                progressBar.setMax(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Integer> progress(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() { // from class: com.jakewharton.rxbinding2.widget.RxProgressBar.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                progressBar.setProgress(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Integer> secondaryProgress(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() { // from class: com.jakewharton.rxbinding2.widget.RxProgressBar.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                progressBar.setSecondaryProgress(num.intValue());
            }
        };
    }

    private RxProgressBar() {
        throw new AssertionError("No instances.");
    }
}
