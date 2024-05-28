package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SearchView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxSearchView {
    @CheckResult
    @NonNull
    public static InitialValueObservable<SearchViewQueryTextEvent> queryTextChangeEvents(@NonNull SearchView searchView) {
        Preconditions.checkNotNull(searchView, "view == null");
        return new SearchViewQueryTextChangeEventsObservable(searchView);
    }

    @CheckResult
    @NonNull
    public static InitialValueObservable<CharSequence> queryTextChanges(@NonNull SearchView searchView) {
        Preconditions.checkNotNull(searchView, "view == null");
        return new SearchViewQueryTextChangesObservable(searchView);
    }

    @CheckResult
    @NonNull
    public static Consumer<? super CharSequence> query(@NonNull final SearchView searchView, final boolean z) {
        Preconditions.checkNotNull(searchView, "view == null");
        return new Consumer<CharSequence>() { // from class: com.jakewharton.rxbinding2.widget.RxSearchView.1
            @Override // io.reactivex.functions.Consumer
            public void accept(CharSequence charSequence) {
                searchView.setQuery(charSequence, z);
            }
        };
    }

    private RxSearchView() {
        throw new AssertionError("No instances.");
    }
}
