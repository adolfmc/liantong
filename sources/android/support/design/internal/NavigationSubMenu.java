package android.support.design.internal;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p086v7.view.menu.MenuBuilder;
import android.support.p086v7.view.menu.MenuItemImpl;
import android.support.p086v7.view.menu.SubMenuBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NavigationSubMenu extends SubMenuBuilder {
    public NavigationSubMenu(Context context, NavigationMenu navigationMenu, MenuItemImpl menuItemImpl) {
        super(context, navigationMenu, menuItemImpl);
    }

    @Override // android.support.p086v7.view.menu.MenuBuilder
    public void onItemsChanged(boolean z) {
        super.onItemsChanged(z);
        ((MenuBuilder) getParentMenu()).onItemsChanged(z);
    }
}
