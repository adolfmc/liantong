package androidx.navigation.p087ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.p086v7.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import java.lang.ref.WeakReference;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: androidx.navigation.ui.CollapsingToolbarOnDestinationChangedListener */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
class CollapsingToolbarOnDestinationChangedListener extends AbstractAppBarOnDestinationChangedListener {
    private final WeakReference<CollapsingToolbarLayout> mCollapsingToolbarLayoutWeakReference;
    private final WeakReference<Toolbar> mToolbarWeakReference;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener, androidx.navigation.ui.ToolbarOnDestinationChangedListener] */
    @Override // androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener, androidx.navigation.NavController.OnDestinationChangedListener
    public void onDestinationChanged(@NonNull NavController abstractAppBarOnDestinationChangedListener, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
        ((ToolbarOnDestinationChangedListener) new AbstractAppBarOnDestinationChangedListener(navDestination.getContext(), bundle)).mToolbarWeakReference = new WeakReference<>(navDestination);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener
    protected void setNavigationIcon(Drawable drawable, @StringRes int i) {
        super(drawable.getContext(), i);
        ((ToolbarOnDestinationChangedListener) this).mToolbarWeakReference = new WeakReference<>(drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0004: INVOKE  
      (r1 I:androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener)
      (r0 I:android.content.Context)
      (r3 I:androidx.navigation.ui.AppBarConfiguration)
     type: DIRECT call: androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener.<init>(android.content.Context, androidx.navigation.ui.AppBarConfiguration):void, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener, androidx.navigation.ui.ToolbarOnDestinationChangedListener] */
    @Override // androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener
    protected void setTitle(CharSequence charSequence) {
        ((ToolbarOnDestinationChangedListener) new AbstractAppBarOnDestinationChangedListener(getContext(), charSequence)).mToolbarWeakReference = new WeakReference<>(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CollapsingToolbarOnDestinationChangedListener(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull AppBarConfiguration appBarConfiguration) {
        super(collapsingToolbarLayout.getContext(), appBarConfiguration);
        this.mCollapsingToolbarLayoutWeakReference = new WeakReference<>(collapsingToolbarLayout);
        this.mToolbarWeakReference = new WeakReference<>(toolbar);
    }
}
