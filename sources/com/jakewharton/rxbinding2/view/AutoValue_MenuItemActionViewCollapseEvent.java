package com.jakewharton.rxbinding2.view;

import android.support.annotation.NonNull;
import android.view.MenuItem;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AutoValue_MenuItemActionViewCollapseEvent extends MenuItemActionViewCollapseEvent {
    private final MenuItem menuItem;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MenuItemActionViewCollapseEvent(MenuItem menuItem) {
        if (menuItem == null) {
            throw new NullPointerException("Null menuItem");
        }
        this.menuItem = menuItem;
    }

    @Override // com.jakewharton.rxbinding2.view.MenuItemActionViewEvent
    @NonNull
    public MenuItem menuItem() {
        return this.menuItem;
    }

    public String toString() {
        return "MenuItemActionViewCollapseEvent{menuItem=" + this.menuItem + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MenuItemActionViewCollapseEvent) {
            return this.menuItem.equals(((MenuItemActionViewCollapseEvent) obj).menuItem());
        }
        return false;
    }

    public int hashCode() {
        return this.menuItem.hashCode() ^ 1000003;
    }
}
