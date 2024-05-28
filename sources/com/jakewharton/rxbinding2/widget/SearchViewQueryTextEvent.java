package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SearchView;
import com.google.auto.value.AutoValue;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@AutoValue
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class SearchViewQueryTextEvent {
    public abstract boolean isSubmitted();

    @NonNull
    public abstract CharSequence queryText();

    @NonNull
    public abstract SearchView view();

    @CheckResult
    @NonNull
    public static SearchViewQueryTextEvent create(@NonNull SearchView searchView, @NonNull CharSequence charSequence, boolean z) {
        return new AutoValue_SearchViewQueryTextEvent(searchView, charSequence, z);
    }
}
