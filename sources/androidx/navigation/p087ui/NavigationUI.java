package androidx.navigation.p087ui;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.p083v4.widget.DrawerLayout;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.p087ui.AppBarConfiguration;
import java.lang.ref.WeakReference;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: androidx.navigation.ui.NavigationUI */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class NavigationUI {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.navigation.ui.NavigationUI$2 */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class View$OnClickListenerC14402 implements View.OnClickListener {
        final /* synthetic */ AppBarConfiguration val$configuration;
        final /* synthetic */ NavController val$navController;

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        @Override // android.view.View.OnClickListener
        public void onClick(android.view.View r0) {
            /*
                r-1 = this;
                r0.<init>()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p087ui.NavigationUI.View$OnClickListenerC14402.onClick(android.view.View):void");
        }

        View$OnClickListenerC14402(NavController navController, AppBarConfiguration appBarConfiguration) {
            this.val$navController = navController;
            this.val$configuration = appBarConfiguration;
        }
    }

    private NavigationUI() {
    }

    public static boolean onNavDestinationSelected(@NonNull MenuItem menuItem, @NonNull NavController navController) {
        NavOptions.Builder popExitAnim = new NavOptions.Builder().setLaunchSingleTop(true).setEnterAnim(C1445R.anim.nav_default_enter_anim).setExitAnim(C1445R.anim.nav_default_exit_anim).setPopEnterAnim(C1445R.anim.nav_default_pop_enter_anim).setPopExitAnim(C1445R.anim.nav_default_pop_exit_anim);
        if ((menuItem.getOrder() & 196608) == 0) {
            popExitAnim.setPopUpTo(findStartDestination(navController.getGraph()).getId(), false);
        }
        try {
            navController.navigate(menuItem.getItemId(), null, popExitAnim.build());
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean navigateUp(@NonNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        return navigateUp(navController, new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build());
    }

    public static boolean navigateUp(@NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        DrawerLayout drawerLayout = appBarConfiguration.getDrawerLayout();
        NavDestination currentDestination = navController.getCurrentDestination();
        Set<Integer> topLevelDestinations = appBarConfiguration.getTopLevelDestinations();
        if (drawerLayout != null && currentDestination != null && matchDestinations(currentDestination, topLevelDestinations)) {
            drawerLayout.openDrawer(8388611);
            return true;
        } else if (navController.navigateUp()) {
            return true;
        } else {
            if (appBarConfiguration.getFallbackOnNavigateUpListener() != null) {
                return appBarConfiguration.getFallbackOnNavigateUpListener().onNavigateUp();
            }
            return false;
        }
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController) {
        setupActionBarWithNavController(appCompatActivity, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        setupActionBarWithNavController(appCompatActivity, navController, new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build());
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new ActionBarOnDestinationChangedListener(appCompatActivity, appBarConfiguration));
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull NavController navController) {
        setupWithNavController(toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        setupWithNavController(toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build());
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull final NavController navController, @NonNull final AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new ToolbarOnDestinationChangedListener(toolbar, appBarConfiguration));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: androidx.navigation.ui.NavigationUI.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:androidx.navigation.NavController), (r0 I:androidx.navigation.ui.NavigationUI$2) androidx.navigation.ui.NavigationUI.2.val$navController androidx.navigation.NavController, block:B:2:0x0000 */
            /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.ui.NavigationUI$2, java.lang.Object] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                r0.val$configuration = view;
                ?? obj = new Object();
            }
        });
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull NavController navController) {
        setupWithNavController(collapsingToolbarLayout, toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        setupWithNavController(collapsingToolbarLayout, toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build());
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new CollapsingToolbarOnDestinationChangedListener(collapsingToolbarLayout, toolbar, appBarConfiguration));
        toolbar.setNavigationOnClickListener(new View$OnClickListenerC14402(navController, appBarConfiguration));
    }

    /* renamed from: androidx.navigation.ui.NavigationUI$3 */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    static class C14413 implements NavigationView.OnNavigationItemSelectedListener {
        final /* synthetic */ NavController val$navController;
        final /* synthetic */ NavigationView val$navigationView;

        C14413(NavController navController, NavigationView navigationView) {
            this.val$navController = navController;
            this.val$navigationView = navigationView;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:java.lang.ref.WeakReference), (r0 I:androidx.navigation.ui.NavigationUI$4) androidx.navigation.ui.NavigationUI.4.val$weakReference java.lang.ref.WeakReference, block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.ui.NavigationUI$4, java.lang.Object] */
        @Override // android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            r0.val$navController = menuItem;
            ?? obj = new Object();
            return;
        }
    }

    public static void setupWithNavController(@NonNull NavigationView navigationView, @NonNull NavController navController) {
        navigationView.setNavigationItemSelectedListener(new C14413(navController, navigationView));
        navController.addOnDestinationChangedListener(new C14424(new WeakReference(navigationView), navController));
    }

    /* renamed from: androidx.navigation.ui.NavigationUI$4 */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    static class C14424 implements NavController.OnDestinationChangedListener {
        final /* synthetic */ NavController val$navController;
        final /* synthetic */ WeakReference val$weakReference;

        C14424(WeakReference weakReference, NavController navController) {
            this.val$weakReference = weakReference;
            this.val$navController = navController;
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        @Override // androidx.navigation.NavController.OnDestinationChangedListener
        public void onDestinationChanged(@android.support.annotation.NonNull androidx.navigation.NavController r-1, @android.support.annotation.NonNull androidx.navigation.NavDestination r0, @android.support.annotation.Nullable android.os.Bundle r1) {
            /*
                r-2 = this;
                r0.val$navController = r1
                r0.<init>()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p087ui.NavigationUI.C14424.onDestinationChanged(androidx.navigation.NavController, androidx.navigation.NavDestination, android.os.Bundle):void");
        }
    }

    static BottomSheetBehavior findBottomSheetBehavior(@NonNull View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                return findBottomSheetBehavior((View) parent);
            }
            return null;
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) behavior;
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: androidx.navigation.ui.NavigationUI$5 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C14435 implements BottomNavigationView.OnNavigationItemSelectedListener {
        final /* synthetic */ NavController val$navController;

        C14435(NavController navController) {
            this.val$navController = navController;
        }

        @Override // android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            return NavigationUI.onNavDestinationSelected(menuItem, this.val$navController);
        }
    }

    public static void setupWithNavController(@NonNull BottomNavigationView bottomNavigationView, @NonNull final NavController navController) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new C14435(navController));
        final WeakReference weakReference = new WeakReference(bottomNavigationView);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() { // from class: androidx.navigation.ui.NavigationUI.6
            /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                java.lang.ArrayIndexOutOfBoundsException
                */
            @Override // androidx.navigation.NavController.OnDestinationChangedListener
            public void onDestinationChanged(@android.support.annotation.NonNull androidx.navigation.NavController r-2, @android.support.annotation.NonNull androidx.navigation.NavDestination r-1, @android.support.annotation.Nullable android.os.Bundle r0) {
                /*
                    r-3 = this;
                    r0.<init>()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p087ui.NavigationUI.C14446.onDestinationChanged(androidx.navigation.NavController, androidx.navigation.NavDestination, android.os.Bundle):void");
            }
        });
    }

    static boolean matchDestination(@NonNull NavDestination navDestination, @IdRes int i) {
        while (navDestination.getId() != i && navDestination.getParent() != null) {
            navDestination = navDestination.getParent();
        }
        return navDestination.getId() == i;
    }

    static boolean matchDestinations(@NonNull NavDestination navDestination, @NonNull Set<Integer> set) {
        while (!set.contains(Integer.valueOf(navDestination.getId()))) {
            navDestination = navDestination.getParent();
            if (navDestination == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r1 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.navigation.NavDestination findStartDestination(@android.support.annotation.NonNull androidx.navigation.NavGraph r1) {
        /*
        L0:
            boolean r0 = r1 instanceof androidx.navigation.NavGraph
            if (r0 == 0) goto Lf
            androidx.navigation.NavGraph r1 = (androidx.navigation.NavGraph) r1
            int r0 = r1.getStartDestination()
            androidx.navigation.NavDestination r1 = r1.findNode(r0)
            goto L0
        Lf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p087ui.NavigationUI.findStartDestination(androidx.navigation.NavGraph):androidx.navigation.NavDestination");
    }
}
