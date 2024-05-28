package androidx.navigation.p087ui;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.p086v7.app.AppCompatActivity;
import java.lang.ref.WeakReference;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: androidx.navigation.ui.ActionBarOnDestinationChangedListener */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
class ActionBarOnDestinationChangedListener extends AbstractAppBarOnDestinationChangedListener {
    private final AppCompatActivity mActivity;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActionBarOnDestinationChangedListener(@NonNull AppCompatActivity appCompatActivity, @NonNull AppBarConfiguration appBarConfiguration) {
        super(appCompatActivity.getDrawerToggleDelegate().getActionBarThemedContext(), appBarConfiguration);
        this.mActivity = appCompatActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0004: INVOKE  
      (r1 I:androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener)
      (r0 I:android.content.Context)
      (r4 I:androidx.navigation.ui.AppBarConfiguration)
     type: DIRECT call: androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener.<init>(android.content.Context, androidx.navigation.ui.AppBarConfiguration):void, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener, androidx.navigation.ui.CollapsingToolbarOnDestinationChangedListener] */
    @Override // androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener
    protected void setNavigationIcon(Drawable drawable, @StringRes int i) {
        ?? abstractAppBarOnDestinationChangedListener = new AbstractAppBarOnDestinationChangedListener(getContext(), i);
        ((CollapsingToolbarOnDestinationChangedListener) abstractAppBarOnDestinationChangedListener).mCollapsingToolbarLayoutWeakReference = new WeakReference<>(this);
        ((CollapsingToolbarOnDestinationChangedListener) abstractAppBarOnDestinationChangedListener).mToolbarWeakReference = new WeakReference<>(drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0004: INVOKE  
      (r1 I:androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener)
      (r0 I:android.content.Context)
      (r4 I:androidx.navigation.ui.AppBarConfiguration)
     type: DIRECT call: androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener.<init>(android.content.Context, androidx.navigation.ui.AppBarConfiguration):void, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0000: INVOKE  (r0 I:android.content.Context) = (r2 I:android.support.design.widget.CollapsingToolbarLayout) type: VIRTUAL call: android.support.design.widget.CollapsingToolbarLayout.getContext():android.content.Context, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener, androidx.navigation.ui.CollapsingToolbarOnDestinationChangedListener] */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.support.design.widget.CollapsingToolbarLayout, java.lang.Object] */
    @Override // androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener
    protected void setTitle(CharSequence charSequence) {
        ?? context;
        ?? abstractAppBarOnDestinationChangedListener = new AbstractAppBarOnDestinationChangedListener(context.getContext(), charSequence);
        ((CollapsingToolbarOnDestinationChangedListener) abstractAppBarOnDestinationChangedListener).mCollapsingToolbarLayoutWeakReference = new WeakReference<>(context);
        ((CollapsingToolbarOnDestinationChangedListener) abstractAppBarOnDestinationChangedListener).mToolbarWeakReference = new WeakReference<>(this);
    }
}
