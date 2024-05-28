package androidx.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import androidx.navigation.Navigator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Navigator.Name("NoOp")
/* loaded from: E:\10201592_dexfile_execute.dex */
class NoOpNavigator extends Navigator<NavDestination> {
    @Override // androidx.navigation.Navigator
    @Nullable
    public NavDestination navigate(@NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        return navDestination;
    }

    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        return true;
    }

    NoOpNavigator() {
    }

    @Override // androidx.navigation.Navigator
    @NonNull
    public NavDestination createDestination() {
        return new NavDestination(this);
    }
}
