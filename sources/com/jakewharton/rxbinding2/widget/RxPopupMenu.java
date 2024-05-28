package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.PopupMenu;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class RxPopupMenu {
    @CheckResult
    @NonNull
    public static Observable<MenuItem> itemClicks(@NonNull PopupMenu popupMenu) {
        Preconditions.checkNotNull(popupMenu, "view == null");
        return new PopupMenuItemClickObservable(popupMenu);
    }

    @CheckResult
    @NonNull
    public static Observable<Object> dismisses(@NonNull PopupMenu popupMenu) {
        Preconditions.checkNotNull(popupMenu, "view == null");
        return new PopupMenuDismissObservable(popupMenu);
    }

    private RxPopupMenu() {
        throw new AssertionError("No instances.");
    }
}
