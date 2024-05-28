package androidx.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class NavBackStackEntry {
    private final Bundle mArgs;
    private final NavDestination mDestination;

    NavBackStackEntry(@NonNull NavDestination navDestination, @Nullable Bundle bundle) {
        this.mDestination = navDestination;
        this.mArgs = bundle;
    }

    @NonNull
    public NavDestination getDestination() {
        return this.mDestination;
    }

    @Nullable
    public Bundle getArguments() {
        return this.mArgs;
    }
}
