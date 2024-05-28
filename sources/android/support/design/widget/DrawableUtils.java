package android.support.design.widget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DrawableUtils {
    private static final String LOG_TAG = "DrawableUtils";
    private static Method setConstantStateMethod;
    private static boolean setConstantStateMethodFetched;

    private DrawableUtils() {
    }

    public static boolean setContainerConstantState(DrawableContainer drawableContainer, Drawable.ConstantState constantState) {
        return setContainerConstantStateV9(drawableContainer, constantState);
    }

    private static boolean setContainerConstantStateV9(DrawableContainer drawableContainer, Drawable.ConstantState constantState) {
        if (!setConstantStateMethodFetched) {
            try {
                setConstantStateMethod = DrawableContainer.class.getDeclaredMethod("setConstantState", DrawableContainer.DrawableContainerState.class);
                setConstantStateMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
            }
            setConstantStateMethodFetched = true;
        }
        Method method = setConstantStateMethod;
        if (method != null) {
            try {
                method.invoke(drawableContainer, constantState);
                return true;
            } catch (Exception unused2) {
                Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
            }
        }
        return false;
    }
}
