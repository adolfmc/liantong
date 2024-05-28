package androidx.navigation;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NavigationRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.TaskStackBuilder;
import androidx.navigation.Navigator;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class NavDeepLinkBuilder {
    private Bundle mArgs;
    private final Context mContext;
    private int mDestId;
    private NavGraph mGraph;
    private final Intent mIntent;

    public NavDeepLinkBuilder(@NonNull Context context) {
        this.mContext = context;
        Context context2 = this.mContext;
        if (context2 instanceof Activity) {
            this.mIntent = new Intent(context2, context2.getClass());
        } else {
            Intent launchIntentForPackage = context2.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
            this.mIntent = launchIntentForPackage == null ? new Intent() : launchIntentForPackage;
        }
        this.mIntent.addFlags(268468224);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavDeepLinkBuilder(@NonNull NavController navController) {
        this(navController.getContext());
        this.mGraph = navController.getGraph();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0000: IGET  (r0 I:android.content.Intent) = (r1 I:androidx.navigation.NavDeepLinkBuilder) androidx.navigation.NavDeepLinkBuilder.mIntent android.content.Intent, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.NavDeepLinkBuilder, void] */
    private void fillInIntent() {
        ?? r1;
        r1.mIntent.setComponent(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0000: IGET  (r0 I:android.content.Intent) = (r1 I:androidx.navigation.NavDeepLinkBuilder) androidx.navigation.NavDeepLinkBuilder.mIntent android.content.Intent, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.NavDeepLinkBuilder, android.app.PendingIntent] */
    @NonNull
    public PendingIntent createPendingIntent() {
        ?? r1;
        r1.mIntent.setComponent(this);
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0000: IGET  (r0 I:android.content.Intent) = (r1 I:androidx.navigation.NavDeepLinkBuilder) androidx.navigation.NavDeepLinkBuilder.mIntent android.content.Intent, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.NavDeepLinkBuilder, android.support.v4.app.TaskStackBuilder] */
    @NonNull
    public TaskStackBuilder createTaskStackBuilder() {
        ?? r1;
        r1.mIntent.setComponent(this);
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public NavDeepLinkBuilder setArguments(@Nullable Bundle bundle) {
        this.mIntent.setComponent(bundle);
        return this;
    }

    @NonNull
    public NavDeepLinkBuilder setComponentName(@NonNull ComponentName componentName) {
        this.mIntent.setComponent(componentName);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public NavDeepLinkBuilder setGraph(@NavigationRes int i) {
        this.mGraph = i;
        if (this.mDestId != 0) {
            fillInIntent();
        }
        return this;
    }

    @NonNull
    public NavDeepLinkBuilder setGraph(@NonNull NavGraph navGraph) {
        this.mGraph = navGraph;
        if (this.mDestId != 0) {
            fillInIntent();
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public NavDeepLinkBuilder setComponentName(@NonNull Class<? extends Activity> cls) {
        this.mDestId = cls;
        if (this.mGraph != null) {
            fillInIntent();
        }
        return this;
    }

    @NonNull
    public NavDeepLinkBuilder setDestination(@IdRes int i) {
        this.mDestId = i;
        if (this.mGraph != null) {
            fillInIntent();
        }
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class PermissiveNavigatorProvider extends NavigatorProvider {
        private final Navigator<NavDestination> mDestNavigator = new C14051();

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: androidx.navigation.NavDeepLinkBuilder$PermissiveNavigatorProvider$1 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C14051 extends Navigator<NavDestination> {
            C14051() {
            }

            @Override // androidx.navigation.Navigator
            @NonNull
            public NavDestination createDestination() {
                return new NavDestination("permissive");
            }

            @Override // androidx.navigation.Navigator
            @Nullable
            public NavDestination navigate(@NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
                throw new IllegalStateException("navigate is not supported");
            }

            @Override // androidx.navigation.Navigator
            public boolean popBackStack() {
                throw new IllegalStateException("popBackStack is not supported");
            }
        }

        PermissiveNavigatorProvider() {
            addNavigator(new NavGraphNavigator(this));
        }

        @Override // androidx.navigation.NavigatorProvider
        @NonNull
        public Navigator<? extends NavDestination> getNavigator(@NonNull String str) {
            try {
                return super.getNavigator(str);
            } catch (IllegalStateException unused) {
                return this.mDestNavigator;
            }
        }
    }
}
