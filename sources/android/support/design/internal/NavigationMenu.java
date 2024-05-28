package android.support.design.internal;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p086v7.view.menu.MenuBuilder;
import android.support.p086v7.view.menu.MenuItemImpl;
import android.view.SubMenu;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NavigationMenu extends MenuBuilder {
    public NavigationMenu(Context context) {
        super(context);
    }

    @Override // android.support.p086v7.view.menu.MenuBuilder, android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) addInternal(i, i2, i3, charSequence);
        NavigationSubMenu navigationSubMenu = new NavigationSubMenu(getContext(), this, menuItemImpl);
        menuItemImpl.setSubMenu(navigationSubMenu);
        return navigationSubMenu;
    }
}
