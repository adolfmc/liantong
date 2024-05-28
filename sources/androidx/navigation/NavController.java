package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.NavigationRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class NavController {
    private static final String KEY_BACK_STACK_ARGS = "android-support-nav:controller:backStackArgs";
    private static final String KEY_BACK_STACK_IDS = "android-support-nav:controller:backStackIds";
    static final String KEY_DEEP_LINK_EXTRAS = "android-support-nav:controller:deepLinkExtras";
    static final String KEY_DEEP_LINK_IDS = "android-support-nav:controller:deepLinkIds";
    @NonNull
    public static final String KEY_DEEP_LINK_INTENT = "android-support-nav:controller:deepLinkIntent";
    private static final String KEY_NAVIGATOR_STATE = "android-support-nav:controller:navigatorState";
    private static final String KEY_NAVIGATOR_STATE_NAMES = "android-support-nav:controller:navigatorState:names";
    private static final String TAG = "NavController";
    private Activity mActivity;
    private Parcelable[] mBackStackArgsToRestore;
    private int[] mBackStackIdsToRestore;
    final Context mContext;
    private NavGraph mGraph;
    private NavInflater mInflater;
    private Bundle mNavigatorStateToRestore;
    final Deque<NavBackStackEntry> mBackStack = new ArrayDeque();
    private final NavigatorProvider mNavigatorProvider = new C14031();
    final Navigator.OnNavigatorBackPressListener mOnBackPressListener = new C14042();
    private final CopyOnWriteArrayList<OnDestinationChangedListener> mOnDestinationChangedListeners = new CopyOnWriteArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDestinationChangedListener {
        void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle);
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.CallSuper
    public void setGraph(@android.support.annotation.NonNull androidx.navigation.NavGraph r-1, @android.support.annotation.Nullable android.os.Bundle r0) {
        /*
            r-2 = this;
            java.lang.String r0 = "^[a-zA-Z]+[+\\w\\-.]*:"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            androidx.navigation.NavDeepLink.SCHEME_PATTERN = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.setGraph(androidx.navigation.NavGraph, android.os.Bundle):void");
    }

    /* renamed from: androidx.navigation.NavController$1 */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    class C14031 extends NavigatorProvider {
        C14031() {
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        @Override // androidx.navigation.NavigatorProvider
        @android.support.annotation.Nullable
        public androidx.navigation.Navigator<? extends androidx.navigation.NavDestination> addNavigator(@android.support.annotation.NonNull java.lang.String r0, @android.support.annotation.NonNull androidx.navigation.Navigator<? extends androidx.navigation.NavDestination> r1) {
            /*
                r-1 = this;
                androidx.navigation.NavController.this = r1
                r0.<init>()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.C14031.addNavigator(java.lang.String, androidx.navigation.Navigator):androidx.navigation.Navigator");
        }
    }

    /* renamed from: androidx.navigation.NavController$2 */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    class C14042 implements Navigator.OnNavigatorBackPressListener {
        C14042() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.navigation.Navigator.OnNavigatorBackPressListener
        public void onPopBackStack(@NonNull Navigator navigator) {
            NavDeepLinkBuilder.PermissiveNavigatorProvider.this = navigator;
        }
    }

    public NavController(@NonNull Context context) {
        this.mContext = context;
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                break;
            } else if (context instanceof Activity) {
                this.mActivity = (Activity) context;
                break;
            } else {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        NavigatorProvider navigatorProvider = this.mNavigatorProvider;
        navigatorProvider.addNavigator(new NavGraphNavigator(navigatorProvider));
        this.mNavigatorProvider.addNavigator(new ActivityNavigator(this.mContext));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.content.Context, boolean] */
    boolean dispatchOnDestinationChanged() {
        return this.mContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.content.Context, androidx.navigation.NavDestination] */
    NavDestination findDestination(@IdRes int i) {
        return i.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavigatorProvider, androidx.navigation.NavDestination] */
    @Nullable
    public NavDestination getCurrentDestination() {
        return this.mNavigatorProvider;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavigatorProvider, androidx.navigation.NavGraph] */
    @NonNull
    public NavGraph getGraph() {
        return this.mNavigatorProvider;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavInflater, androidx.navigation.NavigatorProvider] */
    @NonNull
    public NavInflater getNavInflater() {
        return this.mNavigatorProvider;
    }

    @NonNull
    public NavigatorProvider getNavigatorProvider() {
        return this.mNavigatorProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean popBackStackInternal(@IdRes int i, boolean z) {
        i.mOnDestinationChangedListeners.remove((Object) z);
        return;
    }

    public void removeOnDestinationChangedListener(@NonNull OnDestinationChangedListener onDestinationChangedListener) {
        this.mOnDestinationChangedListeners.remove(onDestinationChangedListener);
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public void navigate(@android.support.annotation.IdRes int r-1, @android.support.annotation.Nullable android.os.Bundle r0, @android.support.annotation.Nullable androidx.navigation.NavOptions r1, @android.support.annotation.Nullable androidx.navigation.Navigator.Extras r2) {
        /*
            r-2 = this;
            boolean r1 = r0.popBackStackInternal(r1, r2)
            if (r1 == 0) goto Le
            boolean r1 = r0.dispatchOnDestinationChanged()
            if (r1 == 0) goto Le
            r1 = 1
            goto Lf
        Le:
            r1 = 0
        Lf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.navigate(int, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r1 I:boolean) = (r0 I:androidx.navigation.NavController), (r1 I:int), (r2 I:boolean) type: VIRTUAL call: androidx.navigation.NavController.popBackStackInternal(int, boolean):boolean, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavController] */
    public void navigate(@NonNull NavDirections navDirections) {
        ?? popBackStackInternal;
        if (popBackStackInternal.popBackStackInternal(this, navDirections) && popBackStackInternal.dispatchOnDestinationChanged()) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void navigate(@NonNull NavDirections navDirections, @Nullable NavOptions navOptions) {
        if (popBackStackInternal(navDirections, navOptions) && dispatchOnDestinationChanged()) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void navigate(@NonNull NavDirections navDirections, @NonNull Navigator.Extras extras) {
        if (popBackStackInternal(navDirections, extras) && dispatchOnDestinationChanged()) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r1 I:boolean) = (r0 I:androidx.navigation.NavController), (r1 I:int), (r2 I:boolean) type: VIRTUAL call: androidx.navigation.NavController.popBackStackInternal(int, boolean):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0000: INVOKE  (r1 I:boolean) = (r0v0 ?? I:androidx.navigation.NavController), (r1 I:int), (r2 I:boolean) type: VIRTUAL call: androidx.navigation.NavController.popBackStackInternal(int, boolean):boolean, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavController] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    public boolean navigateUp() {
        ?? popBackStackInternal;
        ?? popBackStackInternal2;
        return popBackStackInternal.popBackStackInternal(popBackStackInternal2 == true ? 1 : 0, this) && popBackStackInternal.dispatchOnDestinationChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r1 I:boolean) = (r0 I:androidx.navigation.NavController), (r1 I:int), (r2 I:boolean) type: VIRTUAL call: androidx.navigation.NavController.popBackStackInternal(int, boolean):boolean, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0000: INVOKE  (r1 I:boolean) = (r0v0 ?? I:androidx.navigation.NavController), (r1 I:int), (r2 I:boolean) type: VIRTUAL call: androidx.navigation.NavController.popBackStackInternal(int, boolean):boolean, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavController] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    public boolean popBackStack() {
        ?? popBackStackInternal;
        ?? popBackStackInternal2;
        return popBackStackInternal.popBackStackInternal(popBackStackInternal2 == true ? 1 : 0, this) && popBackStackInternal.dispatchOnDestinationChanged();
    }

    public boolean popBackStack(@IdRes int i, boolean z) {
        return popBackStackInternal(i, z) && dispatchOnDestinationChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CallSuper
    public void restoreState(@Nullable Bundle bundle) {
        setGraph((int) bundle, (Bundle) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0001: INVOKE  (r1 I:androidx.navigation.NavController), (r2 I:int), (r0 I:android.os.Bundle) type: VIRTUAL call: androidx.navigation.NavController.setGraph(int, android.os.Bundle):void, block:B:2:0x0000 */
    @CallSuper
    @Nullable
    public Bundle saveState() {
        NavController graph;
        graph.setGraph((int) this, (Bundle) null);
        return;
    }

    @CallSuper
    public void setGraph(@NavigationRes int i) {
        setGraph(i, (Bundle) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CallSuper
    public void setGraph(@NavigationRes int i, @Nullable Bundle bundle) {
        i.setGraph(bundle, null);
    }

    @CallSuper
    public void setGraph(@NonNull NavGraph navGraph) {
        setGraph(navGraph, (Bundle) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean handleDeepLink(@Nullable Intent intent) {
        navigate((int) intent, (Bundle) null);
        return;
    }

    public void navigate(@IdRes int i) {
        navigate(i, (Bundle) null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle) {
        navigate(i, bundle, null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle, @Nullable NavOptions navOptions) {
        navigate(i, bundle, navOptions, (Navigator.Extras) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.navigation.NavDeepLinkBuilder, java.lang.String] */
    @Nullable
    private String findInvalidDestinationDisplayNameInDeepLink(@NonNull int[] iArr) {
        return new NavDeepLinkBuilder((NavController) iArr);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavDeepLinkBuilder, int] */
    private int getDestinationCountOnBackStack() {
        return new NavDeepLinkBuilder(this);
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    private void navigate(@android.support.annotation.NonNull androidx.navigation.NavDestination r-2, @android.support.annotation.Nullable android.os.Bundle r-1, @android.support.annotation.Nullable androidx.navigation.NavOptions r0, @android.support.annotation.Nullable androidx.navigation.Navigator.Extras r1) {
        /*
            r-3 = this;
            androidx.navigation.NavDeepLinkBuilder r0 = new androidx.navigation.NavDeepLinkBuilder
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.navigate(androidx.navigation.NavDestination, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.navigation.NavDeepLinkBuilder, void] */
    private void onGraphCreated(@Nullable Bundle bundle) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.navigation.NavDeepLinkBuilder, void] */
    public void addOnDestinationChangedListener(@NonNull OnDestinationChangedListener onDestinationChangedListener) {
    }

    @NonNull
    public NavDeepLinkBuilder createDeepLink() {
        return new NavDeepLinkBuilder(this);
    }
}
