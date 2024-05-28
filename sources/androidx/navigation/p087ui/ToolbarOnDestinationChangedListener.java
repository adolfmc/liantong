package androidx.navigation.p087ui;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p086v7.widget.Toolbar;
import java.lang.ref.WeakReference;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: androidx.navigation.ui.ToolbarOnDestinationChangedListener */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
class ToolbarOnDestinationChangedListener extends AbstractAppBarOnDestinationChangedListener {
    private final WeakReference<Toolbar> mToolbarWeakReference;

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener, androidx.navigation.NavController.OnDestinationChangedListener
    public void onDestinationChanged(@android.support.annotation.NonNull androidx.navigation.NavController r-2, @android.support.annotation.NonNull androidx.navigation.NavDestination r-1, @android.support.annotation.Nullable android.os.Bundle r0) {
        /*
            r-3 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p087ui.ToolbarOnDestinationChangedListener.onDestinationChanged(androidx.navigation.NavController, androidx.navigation.NavDestination, android.os.Bundle):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener
    protected void setNavigationIcon(android.graphics.drawable.Drawable r-1, @android.support.annotation.StringRes int r0) {
        /*
            r-2 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p087ui.ToolbarOnDestinationChangedListener.setNavigationIcon(android.graphics.drawable.Drawable, int):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener
    protected void setTitle(java.lang.CharSequence r0) {
        /*
            r-1 = this;
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.p087ui.ToolbarOnDestinationChangedListener.setTitle(java.lang.CharSequence):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ToolbarOnDestinationChangedListener(@NonNull Toolbar toolbar, @NonNull AppBarConfiguration appBarConfiguration) {
        super(toolbar.getContext(), appBarConfiguration);
        this.mToolbarWeakReference = new WeakReference<>(toolbar);
    }
}
