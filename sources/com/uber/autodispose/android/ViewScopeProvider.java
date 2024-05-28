package com.uber.autodispose.android;

import android.view.View;
import com.uber.autodispose.ScopeProvider;
import io.reactivex.Maybe;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ViewScopeProvider implements ScopeProvider {
    private final View view;

    public static ScopeProvider from(View view) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        return new ViewScopeProvider(view);
    }

    private ViewScopeProvider(View view) {
        this.view = view;
    }

    @Override // com.uber.autodispose.ScopeProvider
    public Maybe<?> requestScope() {
        return new DetachEventMaybe(this.view);
    }
}
