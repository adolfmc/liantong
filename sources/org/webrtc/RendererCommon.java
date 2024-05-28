package org.webrtc;

import android.graphics.Point;
import android.opengl.Matrix;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RendererCommon {
    private static float BALANCED_VISIBLE_FRACTION = 0.5625f;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface GlDrawer {
        void drawOes(int i, float[] fArr, int i2, int i3, int i4, int i5, int i6, int i7);

        void drawRgb(int i, float[] fArr, int i2, int i3, int i4, int i5, int i6, int i7);

        void drawYuv(int[] iArr, float[] fArr, int i, int i2, int i3, int i4, int i5, int i6);

        void release();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface RendererEvents {
        void onFirstFrameRendered();

        void onFrameResolutionChanged(int i, int i2, int i3);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum ScalingType {
        SCALE_ASPECT_FIT,
        SCALE_ASPECT_FILL,
        SCALE_ASPECT_BALANCED
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class VideoLayoutMeasure {
        private float visibleFractionMatchOrientation = RendererCommon.convertScalingTypeToVisibleFraction(ScalingType.SCALE_ASPECT_BALANCED);
        private float visibleFractionMismatchOrientation = RendererCommon.convertScalingTypeToVisibleFraction(ScalingType.SCALE_ASPECT_BALANCED);

        public void setScalingType(ScalingType scalingType) {
            setScalingType(scalingType, scalingType);
        }

        public void setScalingType(ScalingType scalingType, ScalingType scalingType2) {
            this.visibleFractionMatchOrientation = RendererCommon.convertScalingTypeToVisibleFraction(scalingType);
            this.visibleFractionMismatchOrientation = RendererCommon.convertScalingTypeToVisibleFraction(scalingType2);
        }

        public void setVisibleFraction(float f, float f2) {
            this.visibleFractionMatchOrientation = f;
            this.visibleFractionMismatchOrientation = f2;
        }

        public Point measure(int i, int i2, int i3, int i4) {
            float f;
            int defaultSize = View.getDefaultSize(Integer.MAX_VALUE, i);
            int defaultSize2 = View.getDefaultSize(Integer.MAX_VALUE, i2);
            if (i3 == 0 || i4 == 0 || defaultSize == 0 || defaultSize2 == 0) {
                return new Point(defaultSize, defaultSize2);
            }
            float f2 = i3 / i4;
            if ((f2 > 1.0f) == (((float) defaultSize) / ((float) defaultSize2) > 1.0f)) {
                f = this.visibleFractionMatchOrientation;
            } else {
                f = this.visibleFractionMismatchOrientation;
            }
            Point displaySize = RendererCommon.getDisplaySize(f, f2, defaultSize, defaultSize2);
            if (View.MeasureSpec.getMode(i) == 1073741824) {
                displaySize.x = defaultSize;
            }
            if (View.MeasureSpec.getMode(i2) == 1073741824) {
                displaySize.y = defaultSize2;
            }
            return displaySize;
        }
    }

    public static float[] getLayoutMatrix(boolean z, float f, float f2) {
        float f3;
        float f4;
        if (f2 > f) {
            f4 = f / f2;
            f3 = 1.0f;
        } else {
            f3 = f2 / f;
            f4 = 1.0f;
        }
        if (z) {
            f3 *= -1.0f;
        }
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        Matrix.scaleM(fArr, 0, f3, f4, 1.0f);
        adjustOrigin(fArr);
        return fArr;
    }

    public static android.graphics.Matrix convertMatrixToAndroidGraphicsMatrix(float[] fArr) {
        float[] fArr2 = {fArr[0], fArr[4], fArr[12], fArr[1], fArr[5], fArr[13], fArr[3], fArr[7], fArr[15]};
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        matrix.setValues(fArr2);
        return matrix;
    }

    public static float[] convertMatrixFromAndroidGraphicsMatrix(android.graphics.Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return new float[]{fArr[0], fArr[3], 0.0f, fArr[6], fArr[1], fArr[4], 0.0f, fArr[7], 0.0f, 0.0f, 1.0f, 0.0f, fArr[2], fArr[5], 0.0f, fArr[8]};
    }

    public static Point getDisplaySize(ScalingType scalingType, float f, int i, int i2) {
        return getDisplaySize(convertScalingTypeToVisibleFraction(scalingType), f, i, i2);
    }

    private static void adjustOrigin(float[] fArr) {
        fArr[12] = fArr[12] - ((fArr[0] + fArr[4]) * 0.5f);
        fArr[13] = fArr[13] - ((fArr[1] + fArr[5]) * 0.5f);
        fArr[12] = fArr[12] + 0.5f;
        fArr[13] = fArr[13] + 0.5f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float convertScalingTypeToVisibleFraction(ScalingType scalingType) {
        switch (scalingType) {
            case SCALE_ASPECT_FIT:
                return 1.0f;
            case SCALE_ASPECT_FILL:
                return 0.0f;
            case SCALE_ASPECT_BALANCED:
                return BALANCED_VISIBLE_FRACTION;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Point getDisplaySize(float f, float f2, int i, int i2) {
        if (f == 0.0f || f2 == 0.0f) {
            return new Point(i, i2);
        }
        return new Point(Math.min(i, Math.round((i2 / f) * f2)), Math.min(i2, Math.round((i / f) / f2)));
    }
}
