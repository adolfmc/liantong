package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(18)
/* loaded from: E:\10201592_dexfile_execute.dex */
class ViewGroupUtilsApi18 {
    private static final String TAG = "ViewUtilsApi18";
    private static Method sSuppressLayoutMethod;
    private static boolean sSuppressLayoutMethodFetched;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void suppressLayout(@NonNull ViewGroup viewGroup, boolean z) {
        fetchSuppressLayoutMethod();
        Method method = sSuppressLayoutMethod;
        if (method != null) {
            try {
                method.invoke(viewGroup, Boolean.valueOf(z));
            } catch (IllegalAccessException e) {
                Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", e);
            } catch (InvocationTargetException e2) {
                Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", e2);
            }
        }
    }

    private static void fetchSuppressLayoutMethod() {
        if (sSuppressLayoutMethodFetched) {
            return;
        }
        try {
            sSuppressLayoutMethod = ViewGroup.class.getDeclaredMethod("suppressLayout", Boolean.TYPE);
            sSuppressLayoutMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", e);
        }
        sSuppressLayoutMethodFetched = true;
    }

    private ViewGroupUtilsApi18() {
    }
}
