package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RatingBar;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxRatingBar {
    @CheckResult
    @NonNull
    public static InitialValueObservable<Float> ratingChanges(@NonNull RatingBar ratingBar) {
        Preconditions.checkNotNull(ratingBar, "view == null");
        return new RatingBarRatingChangeObservable(ratingBar);
    }

    @CheckResult
    @NonNull
    public static InitialValueObservable<RatingBarChangeEvent> ratingChangeEvents(@NonNull RatingBar ratingBar) {
        Preconditions.checkNotNull(ratingBar, "view == null");
        return new RatingBarRatingChangeEventObservable(ratingBar);
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Float> rating(@NonNull final RatingBar ratingBar) {
        Preconditions.checkNotNull(ratingBar, "view == null");
        return new Consumer<Float>() { // from class: com.jakewharton.rxbinding2.widget.RxRatingBar.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Float f) {
                ratingBar.setRating(f.floatValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Boolean> isIndicator(@NonNull final RatingBar ratingBar) {
        Preconditions.checkNotNull(ratingBar, "view == null");
        return new Consumer<Boolean>() { // from class: com.jakewharton.rxbinding2.widget.RxRatingBar.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) {
                ratingBar.setIsIndicator(bool.booleanValue());
            }
        };
    }

    private RxRatingBar() {
        throw new AssertionError("No instances.");
    }
}
