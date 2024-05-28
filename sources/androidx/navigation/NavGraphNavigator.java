package androidx.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import androidx.navigation.Navigator;
import java.util.ArrayDeque;

@Navigator.Name("navigation")
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class NavGraphNavigator extends Navigator<NavGraph> {
    private static final String KEY_BACK_STACK_IDS = "androidx-nav-graph:navigator:backStackIds";
    private ArrayDeque<Integer> mBackStack = new ArrayDeque<>();
    private final NavigatorProvider mNavigatorProvider;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, androidx.navigation.NavGraph] */
    private boolean isAlreadyTop(NavGraph navGraph) {
        return navGraph.createDestination();
    }

    public NavGraphNavigator(@NonNull NavigatorProvider navigatorProvider) {
        this.mNavigatorProvider = navigatorProvider;
    }

    @Override // androidx.navigation.Navigator
    @NonNull
    public NavGraph createDestination() {
        return new NavGraph(this);
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.navigation.Navigator
    @android.support.annotation.Nullable
    public androidx.navigation.NavDestination navigate(@android.support.annotation.NonNull androidx.navigation.NavGraph r-2, @android.support.annotation.Nullable android.os.Bundle r-1, @android.support.annotation.Nullable androidx.navigation.NavOptions r0, @android.support.annotation.Nullable androidx.navigation.Navigator.Extras r1) {
        /*
            r-3 = this;
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavGraphNavigator.navigate(androidx.navigation.NavGraph, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):androidx.navigation.NavDestination");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.navigation.Navigator
    public void onRestoreState(@Nullable Bundle bundle) {
        super(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.navigation.NavType), (r1 I:boolean) type: DIRECT call: androidx.navigation.NavType.<init>(boolean):void, block:B:2:0x0000 */
    @Override // androidx.navigation.Navigator
    @Nullable
    public Bundle onSaveState() {
        new NavType(this);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.navigation.NavType), (r1 I:boolean) type: DIRECT call: androidx.navigation.NavType.<init>(boolean):void, block:B:2:0x0000 */
    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        new NavType(this);
        return;
    }
}
