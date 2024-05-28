package androidx.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Navigation {
    private Navigation() {
    }

    @NonNull
    public static NavController findNavController(@NonNull Activity activity, @IdRes int i) {
        NavController findViewNavController = findViewNavController(ActivityCompat.requireViewById(activity, i));
        if (findViewNavController != null) {
            return findViewNavController;
        }
        throw new IllegalStateException("Activity " + activity + " does not have a NavController set on " + i);
    }

    @NonNull
    public static NavController findNavController(@NonNull View view) {
        NavController findViewNavController = findViewNavController(view);
        if (findViewNavController != null) {
            return findViewNavController;
        }
        throw new IllegalStateException("View " + view + " does not have a NavController set");
    }

    @NonNull
    public static View.OnClickListener createNavigateOnClickListener(@IdRes int i) {
        return createNavigateOnClickListener(i, null);
    }

    @NonNull
    public static View.OnClickListener createNavigateOnClickListener(@IdRes final int i, @Nullable final Bundle bundle) {
        return new View.OnClickListener() { // from class: androidx.navigation.Navigation.1
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
                throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.Navigation.View$OnClickListenerC14181.onClick(android.view.View):void");
            }
        };
    }

    public static void setViewNavController(@NonNull View view, @Nullable NavController navController) {
        view.setTag(C1419R.C1422id.nav_controller_view_tag, navController);
    }

    @Nullable
    private static NavController findViewNavController(@NonNull View view) {
        while (view != null) {
            NavController viewNavController = getViewNavController(view);
            if (viewNavController != null) {
                return viewNavController;
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    @Nullable
    private static NavController getViewNavController(@NonNull View view) {
        Object tag = view.getTag(C1419R.C1422id.nav_controller_view_tag);
        if (tag instanceof WeakReference) {
            return (NavController) ((WeakReference) tag).get();
        }
        if (tag instanceof NavController) {
            return (NavController) tag;
        }
        return null;
    }
}
