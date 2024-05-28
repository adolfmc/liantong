package androidx.navigation.p087ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.p083v4.widget.DrawerLayout;
import android.support.p086v7.graphics.drawable.DrawerArrowDrawable;
import androidx.navigation.NavController;
import java.lang.ref.WeakReference;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractAppBarOnDestinationChangedListener implements NavController.OnDestinationChangedListener {
    private ValueAnimator mAnimator;
    private DrawerArrowDrawable mArrowDrawable;
    private final Context mContext;
    @Nullable
    private final WeakReference<DrawerLayout> mDrawerLayoutWeakReference;
    private final Set<Integer> mTopLevelDestinations;

    protected abstract void setNavigationIcon(Drawable drawable, @StringRes int i);

    protected abstract void setTitle(CharSequence charSequence);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractAppBarOnDestinationChangedListener(@NonNull Context context, @NonNull AppBarConfiguration appBarConfiguration) {
        this.mContext = context;
        this.mTopLevelDestinations = appBarConfiguration.getTopLevelDestinations();
        DrawerLayout drawerLayout = appBarConfiguration.getDrawerLayout();
        if (drawerLayout != null) {
            this.mDrawerLayoutWeakReference = new WeakReference<>(drawerLayout);
        } else {
            this.mDrawerLayoutWeakReference = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:androidx.navigation.NavController), (r0 I:androidx.navigation.ui.NavigationUI$3) androidx.navigation.ui.NavigationUI.3.val$navController androidx.navigation.NavController, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.ui.NavigationUI$3, java.lang.Object] */
    private void setActionBarUpIndicator(boolean z) {
        r0.val$navigationView = z;
        ?? obj = new Object();
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.navigation.NavController.OnDestinationChangedListener
    public void onDestinationChanged(@android.support.annotation.NonNull androidx.navigation.NavController r0, @android.support.annotation.NonNull androidx.navigation.NavDestination r1, @android.support.annotation.Nullable android.os.Bundle r2) {
        /*
            r-1 = this;
            r0.val$navController = r1
            r0.val$navigationView = r2
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener.onDestinationChanged(androidx.navigation.NavController, androidx.navigation.NavDestination, android.os.Bundle):void");
    }
}
