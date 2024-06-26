package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class ImageViewUtils {
    private static final String TAG = "ImageViewUtils";
    private static Method sAnimateTransformMethod;
    private static boolean sAnimateTransformMethodFetched;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void startAnimateTransform(ImageView imageView) {
        if (Build.VERSION.SDK_INT < 21) {
            ImageView.ScaleType scaleType = imageView.getScaleType();
            imageView.setTag(C1058R.C1061id.save_scale_type, scaleType);
            if (scaleType == ImageView.ScaleType.MATRIX) {
                imageView.setTag(C1058R.C1061id.save_image_matrix, imageView.getImageMatrix());
            } else {
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
            }
            imageView.setImageMatrix(MatrixUtils.IDENTITY_MATRIX);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void animateTransform(ImageView imageView, Matrix matrix) {
        if (Build.VERSION.SDK_INT < 21) {
            imageView.setImageMatrix(matrix);
            return;
        }
        fetchAnimateTransformMethod();
        Method method = sAnimateTransformMethod;
        if (method != null) {
            try {
                method.invoke(imageView, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    private static void fetchAnimateTransformMethod() {
        if (sAnimateTransformMethodFetched) {
            return;
        }
        try {
            sAnimateTransformMethod = ImageView.class.getDeclaredMethod("animateTransform", Matrix.class);
            sAnimateTransformMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.i("ImageViewUtils", "Failed to retrieve animateTransform method", e);
        }
        sAnimateTransformMethodFetched = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reserveEndAnimateTransform(final ImageView imageView, Animator animator) {
        if (Build.VERSION.SDK_INT < 21) {
            animator.addListener(new AnimatorListenerAdapter() { // from class: android.support.transition.ImageViewUtils.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    ImageView.ScaleType scaleType = (ImageView.ScaleType) imageView.getTag(C1058R.C1061id.save_scale_type);
                    imageView.setScaleType(scaleType);
                    imageView.setTag(C1058R.C1061id.save_scale_type, null);
                    if (scaleType == ImageView.ScaleType.MATRIX) {
                        ImageView imageView2 = imageView;
                        imageView2.setImageMatrix((Matrix) imageView2.getTag(C1058R.C1061id.save_image_matrix));
                        imageView.setTag(C1058R.C1061id.save_image_matrix, null);
                    }
                    animator2.removeListener(this);
                }
            });
        }
    }

    private ImageViewUtils() {
    }
}
