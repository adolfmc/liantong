package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class PropertyValuesHolderUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        if (Build.VERSION.SDK_INT >= 21) {
            return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
        }
        return PropertyValuesHolder.ofFloat(new PathProperty(property, path), 0.0f, 1.0f);
    }

    private PropertyValuesHolderUtils() {
    }
}
