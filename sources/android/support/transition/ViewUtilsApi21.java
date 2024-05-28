package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(21)
/* loaded from: E:\10201592_dexfile_execute.dex */
class ViewUtilsApi21 extends ViewUtilsApi19 {
    private static final String TAG = "ViewUtilsApi21";
    private static Method sSetAnimationMatrixMethod;
    private static boolean sSetAnimationMatrixMethodFetched;
    private static Method sTransformMatrixToGlobalMethod;
    private static boolean sTransformMatrixToGlobalMethodFetched;
    private static Method sTransformMatrixToLocalMethod;
    private static boolean sTransformMatrixToLocalMethodFetched;

    @Override // android.support.transition.ViewUtilsBase
    public void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
        fetchTransformMatrixToGlobalMethod();
        Method method = sTransformMatrixToGlobalMethod;
        if (method != null) {
            try {
                method.invoke(view, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    @Override // android.support.transition.ViewUtilsBase
    public void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
        fetchTransformMatrixToLocalMethod();
        Method method = sTransformMatrixToLocalMethod;
        if (method != null) {
            try {
                method.invoke(view, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    @Override // android.support.transition.ViewUtilsBase
    public void setAnimationMatrix(@NonNull View view, Matrix matrix) {
        fetchSetAnimationMatrix();
        Method method = sSetAnimationMatrixMethod;
        if (method != null) {
            try {
                method.invoke(view, matrix);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getCause());
            } catch (InvocationTargetException unused) {
            }
        }
    }

    private void fetchTransformMatrixToGlobalMethod() {
        if (sTransformMatrixToGlobalMethodFetched) {
            return;
        }
        try {
            sTransformMatrixToGlobalMethod = View.class.getDeclaredMethod("transformMatrixToGlobal", Matrix.class);
            sTransformMatrixToGlobalMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", e);
        }
        sTransformMatrixToGlobalMethodFetched = true;
    }

    private void fetchTransformMatrixToLocalMethod() {
        if (sTransformMatrixToLocalMethodFetched) {
            return;
        }
        try {
            sTransformMatrixToLocalMethod = View.class.getDeclaredMethod("transformMatrixToLocal", Matrix.class);
            sTransformMatrixToLocalMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", e);
        }
        sTransformMatrixToLocalMethodFetched = true;
    }

    private void fetchSetAnimationMatrix() {
        if (sSetAnimationMatrixMethodFetched) {
            return;
        }
        try {
            sSetAnimationMatrixMethod = View.class.getDeclaredMethod("setAnimationMatrix", Matrix.class);
            sSetAnimationMatrixMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.i("ViewUtilsApi21", "Failed to retrieve setAnimationMatrix method", e);
        }
        sSetAnimationMatrixMethodFetched = true;
    }
}
