package com.baidu.p120ar.arrender;

import android.content.Context;
import android.graphics.PointF;
import android.opengl.Matrix;
import com.baidu.p120ar.DuMixInput;
import com.baidu.p120ar.DuMixOutput;
import com.baidu.p120ar.arplay.core.engine.rotate.Orientation;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arplay.core.renderer.OutputFillMode;
import com.baidu.p120ar.bean.MirriorType;
import com.baidu.p120ar.bean.RotationType;
import com.baidu.p120ar.bean.ScaleType;
import com.baidu.p120ar.bean.Size;
import com.baidu.p120ar.utils.SystemInfoUtils;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.ARRenderHelper */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARRenderHelper {
    public static final String DEFAULT_GLOBAL_FILTER = "filter_pipeline = function()\n\n    fm = ae.FilterManager:get_instance();\n\n    global_copy_filter = fm:create_filter(\"Tex2DFilter\", \"globalTex2DFilter\", true);\n    gl_makeup_filer = fm:create_filter(\"BeautyMakeupFilter\",\"globalBeautyMakeupFilter\",true);\n\n    skin_filter = fm:create_filter(\"SkinFilter\", \"globalSkinFilter\", true);\n    engine_filter = fm:create_filter(\"EngineFilter\", \"globalEngineFilter\", true);\n    fm:update_property_int(engine_filter, \"is_enable\", 0);\n    face_filter = fm:create_filter(\"FaceFilter\", \"globalFaceFilter\", true);\n    lut_filter = fm:create_filter(\"LUTFilter\", \"globalLutFilter\", true);\n    tune_color_filter = fm:create_filter(\"TuneColorFilter\", \"globalTuneColorFilter\", true);\n    fm:reset_pipeline();\n    fm:connect_filters_by_id(skin_filter, gl_makeup_filer);\n    fm:connect_filters_by_id(skin_filter, global_copy_filter);\n    fm:connect_filters_by_id(global_copy_filter, gl_makeup_filer);\n    fm:connect_filters_by_id(gl_makeup_filer, face_filter);\n    fm:connect_filters_by_id(face_filter, tune_color_filter);\n    fm:connect_filters_by_id(tune_color_filter, engine_filter);\n    fm:connect_filters_by_id(engine_filter, lut_filter);\n\n    fm:connect_filter_to_camera(skin_filter);\n    fm:connect_filter_to_output(lut_filter);\n\nend\nfilter_pipeline()\n\n";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Size calResulotion(int i, int i2, int i3, int i4) {
        Size size = new Size(i, i2);
        if (i <= 0 || i2 <= 0 || i3 <= 0 || i4 <= 0) {
            return size;
        }
        float f = i;
        float f2 = i2;
        float f3 = f / f2;
        float f4 = i3 / i4;
        if (f3 < f4) {
            size.setWidth((int) (f2 * f4));
            size.setHeight(i2);
        } else if (f3 > f4) {
            size.setWidth(i);
            size.setHeight((int) (f / f4));
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Size calWindowSize(int i, int i2, int i3, int i4) {
        Size size = new Size(i, i2);
        if (i <= 0 || i2 <= 0 || i3 <= 0 || i4 <= 0) {
            return size;
        }
        float f = i;
        float f2 = i2;
        float f3 = f / f2;
        float f4 = i3 / i4;
        if (f3 > f4) {
            size.setWidth((int) (f2 * f4));
            size.setHeight(i2);
        } else if (f3 < f4) {
            size.setWidth(i);
            size.setHeight((int) (f / f4));
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PixelRotation getInputPixelRotation(boolean z, RotationType rotationType, MirriorType mirriorType) {
        if (z) {
            return PixelRotation.FlipVertical;
        }
        return getPixelRotation(rotationType, mirriorType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PixelRotation getPixelRotation(RotationType rotationType, MirriorType mirriorType) {
        PixelRotation pixelRotation = PixelRotation.NoRotation;
        switch (rotationType) {
            case ROTATE_0:
                if (mirriorType == MirriorType.NO_MIRRIOR) {
                    return PixelRotation.NoRotation;
                }
                if (mirriorType == MirriorType.VERTICAL_MIRRIOR) {
                    return PixelRotation.FlipVertical;
                }
                return mirriorType == MirriorType.HORIZONTAL_MIRRIOR ? PixelRotation.FlipHorizontal : pixelRotation;
            case ROTATE_90:
                if (mirriorType == MirriorType.NO_MIRRIOR) {
                    return PixelRotation.RotateRight;
                }
                if (mirriorType == MirriorType.VERTICAL_MIRRIOR) {
                    return PixelRotation.RotateRightFlipVertical;
                }
                return mirriorType == MirriorType.HORIZONTAL_MIRRIOR ? PixelRotation.RotateRightFlipHorizontal : pixelRotation;
            case ROTATE_180:
                if (mirriorType == MirriorType.NO_MIRRIOR) {
                    return PixelRotation.Rotate180;
                }
                if (mirriorType == MirriorType.VERTICAL_MIRRIOR) {
                    return PixelRotation.FlipHorizontal;
                }
                return mirriorType == MirriorType.HORIZONTAL_MIRRIOR ? PixelRotation.FlipVertical : pixelRotation;
            case ROTATE_270:
                if (mirriorType == MirriorType.NO_MIRRIOR) {
                    return PixelRotation.RotateLeft;
                }
                if (mirriorType == MirriorType.VERTICAL_MIRRIOR) {
                    return PixelRotation.RotateRightFlipHorizontal;
                }
                return mirriorType == MirriorType.HORIZONTAL_MIRRIOR ? PixelRotation.RotateRightFlipVertical : pixelRotation;
            default:
                return pixelRotation;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HashMap getOrientationLuaMsg(Orientation orientation) {
        HashMap hashMap = new HashMap();
        switch (orientation) {
            case PORTRAIT:
                hashMap.put("orient", "portrait");
                break;
            case LANDSCAPE:
                hashMap.put("orient", "landscape_right");
                break;
            case LANDSCAPE_REVERSE:
                hashMap.put("orient", "landscape_left");
                break;
            default:
                hashMap.put("orient", "portrait");
                break;
        }
        return hashMap;
    }

    static int getFaceVideoOrientation(int i) {
        int abs = Math.abs(i % 360);
        if (abs != 0) {
            if (abs != 90) {
                if (abs != 180) {
                    return abs != 270 ? 3 : 2;
                }
                return 0;
            }
            return 1;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void convertAlgo2ScreenPoint(PointF pointF, boolean z, DuMixInput duMixInput, DuMixOutput duMixOutput, boolean z2) {
        float inputWidth;
        float outputWidth;
        if (pointF == null || duMixInput == null || duMixOutput == null || duMixOutput.getScaleType() == ScaleType.FIT_XY) {
            return;
        }
        if (z) {
            inputWidth = duMixInput.getInputHeight() / duMixInput.getInputWidth();
        } else {
            inputWidth = duMixInput.getInputWidth() / duMixInput.getInputHeight();
        }
        if (z2) {
            outputWidth = duMixOutput.getOutputHeight() / duMixOutput.getOutputWidth();
        } else {
            outputWidth = duMixOutput.getOutputWidth() / duMixOutput.getOutputHeight();
        }
        if (inputWidth == outputWidth) {
            return;
        }
        float f = pointF.x;
        float f2 = pointF.y;
        if (inputWidth >= outputWidth) {
            switch (duMixOutput.getScaleType()) {
                case CENTER_CROP:
                    f = ((((f * 2.0f) - 1.0f) * inputWidth) + outputWidth) / (outputWidth * 2.0f);
                    break;
            }
        } else {
            switch (duMixOutput.getScaleType()) {
                case CENTER_CROP:
                    float f3 = 1.0f / outputWidth;
                    f2 = ((((f2 * 2.0f) - 1.0f) * (1.0f / inputWidth)) + f3) / (f3 * 2.0f);
                    break;
            }
        }
        pointF.set(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OutputFillMode getOutputFillMode(ScaleType scaleType) {
        switch (scaleType) {
            case CENTER_CROP:
                return OutputFillMode.KeepRatioCrop;
            case CENTER_INSIDE:
                return OutputFillMode.KeepRatioFill;
            case FIT_XY:
                return OutputFillMode.KeepRatioFill;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateInputMatrix(Context context, float[] fArr, boolean z) {
        Matrix.setIdentityM(fArr, 0);
        if (SystemInfoUtils.isNexus6P() || SystemInfoUtils.isMateX(context)) {
            Matrix.rotateM(fArr, 0, 90.0f, 0.0f, 0.0f, 1.0f);
            Matrix.translateM(fArr, 0, 0.0f, -1.0f, 0.0f);
            if (z) {
                return;
            }
            Matrix.rotateM(fArr, 0, 180.0f, 0.0f, 1.0f, 0.0f);
            Matrix.translateM(fArr, 0, -1.0f, 0.0f, 0.0f);
        } else if (SystemInfoUtils.isNexus5X()) {
            Matrix.rotateM(fArr, 0, 270.0f, 0.0f, 0.0f, 1.0f);
            Matrix.translateM(fArr, 0, -1.0f, 0.0f, 0.0f);
            if (z) {
                return;
            }
            Matrix.rotateM(fArr, 0, 180.0f, 0.0f, 1.0f, 0.0f);
            Matrix.translateM(fArr, 0, -1.0f, 0.0f, 0.0f);
        } else if (z) {
            Matrix.rotateM(fArr, 0, 270.0f, 0.0f, 0.0f, 1.0f);
            Matrix.translateM(fArr, 0, -1.0f, 0.0f, 0.0f);
        } else {
            Matrix.rotateM(fArr, 0, 90.0f, 0.0f, 0.0f, 1.0f);
            Matrix.translateM(fArr, 0, 0.0f, -1.0f, 0.0f);
            Matrix.rotateM(fArr, 0, 180.0f, 0.0f, 1.0f, 0.0f);
            Matrix.translateM(fArr, 0, -1.0f, 0.0f, 0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void adaptSpecialPhonePixelRotate(Context context, boolean z, PixelReadParams pixelReadParams) {
        if (SystemInfoUtils.isNexus6P()) {
            if (pixelReadParams.getIsPortrait()) {
                if (z) {
                    pixelReadParams.setPixelRotate(PixelRotation.RotateRightFlipVertical);
                } else {
                    pixelReadParams.setPixelRotate(PixelRotation.RotateRight);
                }
            } else if (z) {
                pixelReadParams.setPixelRotate(PixelRotation.Rotate180);
            } else {
                pixelReadParams.setPixelRotate(PixelRotation.NoRotation);
            }
        } else if (SystemInfoUtils.isNexus5X()) {
            if (pixelReadParams.getIsPortrait()) {
                if (z) {
                    pixelReadParams.setPixelRotate(PixelRotation.RotateRightFlipHorizontal);
                } else {
                    pixelReadParams.setPixelRotate(PixelRotation.RotateLeft);
                }
            } else if (z) {
                pixelReadParams.setPixelRotate(PixelRotation.NoRotation);
            } else {
                pixelReadParams.setPixelRotate(PixelRotation.Rotate180);
            }
        } else if (SystemInfoUtils.isMateX(context) && pixelReadParams.getIsPortrait() && z) {
            pixelReadParams.setPixelRotate(PixelRotation.RotateRightFlipVertical);
        }
    }
}
