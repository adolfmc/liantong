package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.MenuItem;
import android.widget.Toolbar;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(21)
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxToolbar {
    @CheckResult
    @NonNull
    public static Observable<MenuItem> itemClicks(@NonNull Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new ToolbarItemClickObservable(toolbar);
    }

    @CheckResult
    @NonNull
    public static Observable<Object> navigationClicks(@NonNull Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new ToolbarNavigationClickObservable(toolbar);
    }

    @CheckResult
    @NonNull
    public static Consumer<? super CharSequence> title(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Consumer<CharSequence>() { // from class: com.jakewharton.rxbinding2.widget.RxToolbar.1
            @Override // io.reactivex.functions.Consumer
            public void accept(CharSequence charSequence) {
                toolbar.setTitle(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Integer> titleRes(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Consumer<Integer>() { // from class: com.jakewharton.rxbinding2.widget.RxToolbar.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                toolbar.setTitle(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super CharSequence> subtitle(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Consumer<CharSequence>() { // from class: com.jakewharton.rxbinding2.widget.RxToolbar.3
            @Override // io.reactivex.functions.Consumer
            public void accept(CharSequence charSequence) {
                toolbar.setSubtitle(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Consumer<? super Integer> subtitleRes(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Consumer<Integer>() { // from class: com.jakewharton.rxbinding2.widget.RxToolbar.4
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                toolbar.setSubtitle(num.intValue());
            }
        };
    }

    private RxToolbar() {
        throw new AssertionError("No instances.");
    }
}
