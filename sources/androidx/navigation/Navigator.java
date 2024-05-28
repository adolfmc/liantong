package androidx.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import androidx.navigation.NavDestination;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class Navigator<D extends NavDestination> {
    private final CopyOnWriteArrayList<OnNavigatorBackPressListener> mOnBackPressListeners = new CopyOnWriteArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Extras {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface Name {
        String value();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnNavigatorBackPressListener {
        void onPopBackStack(@NonNull Navigator navigator);
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public final void addOnNavigatorBackPressListener(@android.support.annotation.NonNull androidx.navigation.Navigator.OnNavigatorBackPressListener r0) {
        /*
            r-1 = this;
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.Navigator.addOnNavigatorBackPressListener(androidx.navigation.Navigator$OnNavigatorBackPressListener):void");
    }

    @NonNull
    public abstract D createDestination();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void dispatchOnNavigatorBackPress() {
    }

    @Nullable
    public abstract NavDestination navigate(@NonNull D d, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Extras extras);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected void onBackPressAdded() {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected void onBackPressRemoved() {
    }

    public void onRestoreState(@NonNull Bundle bundle) {
    }

    @Nullable
    public Bundle onSaveState() {
        return null;
    }

    public abstract boolean popBackStack();

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public final void removeOnNavigatorBackPressListener(@android.support.annotation.NonNull androidx.navigation.Navigator.OnNavigatorBackPressListener r0) {
        /*
            r-1 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            androidx.navigation.NavigatorProvider.sAnnotationNames = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.Navigator.removeOnNavigatorBackPressListener(androidx.navigation.Navigator$OnNavigatorBackPressListener):void");
    }
}
