package androidx.navigation;

import android.annotation.SuppressLint;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import androidx.navigation.Navigator;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"TypeParameterUnusedInFormals"})
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class NavigatorProvider {
    private static final HashMap<Class, String> sAnnotationNames = new HashMap<>();
    private final HashMap<String, Navigator<? extends NavDestination>> mNavigators = new HashMap<>();

    private static boolean validateName(String str) {
        return (str == null || str.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static String getNameForNavigator(@NonNull Class<? extends Navigator> cls) {
        String str = sAnnotationNames.get(cls);
        if (str == null) {
            Navigator.Name name = (Navigator.Name) cls.getAnnotation(Navigator.Name.class);
            str = name != null ? name.value() : null;
            if (!validateName(str)) {
                throw new IllegalArgumentException("No @Navigator.Name annotation found for " + cls.getSimpleName());
            }
            sAnnotationNames.put(cls, str);
        }
        return str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final Navigator<? extends NavDestination> addNavigator(@NonNull Navigator<? extends NavDestination> navigator) {
        return getNavigator(getNameForNavigator(navigator));
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.CallSuper
    @android.support.annotation.Nullable
    public androidx.navigation.Navigator<? extends androidx.navigation.NavDestination> addNavigator(@android.support.annotation.NonNull java.lang.String r0, @android.support.annotation.NonNull androidx.navigation.Navigator<? extends androidx.navigation.NavDestination> r1) {
        /*
            r-1 = this;
            java.lang.String r1 = getNameForNavigator(r1)
            androidx.navigation.Navigator r1 = r0.getNavigator(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavigatorProvider.addNavigator(java.lang.String, androidx.navigation.Navigator):androidx.navigation.Navigator");
    }

    @NonNull
    public final <T extends Navigator<?>> T getNavigator(@NonNull Class<T> cls) {
        return (T) getNavigator(getNameForNavigator(cls));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CallSuper
    @NonNull
    public <T extends Navigator<?>> T getNavigator(@NonNull String str) {
        return (T) str.mNavigators;
    }

    Map<String, Navigator<? extends NavDestination>> getNavigators() {
        return this.mNavigators;
    }
}
