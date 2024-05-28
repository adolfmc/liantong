package android.support.p086v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p086v7.view.menu.MenuBuilder;
import android.view.MenuItem;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.MenuItemHoverListener */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface MenuItemHoverListener {
    void onItemHoverEnter(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem);

    void onItemHoverExit(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem);
}
