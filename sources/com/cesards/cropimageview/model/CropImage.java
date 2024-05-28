package com.cesards.cropimageview.model;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.cesards.cropimageview.CropImageView;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class CropImage implements Transformation {
    @NonNull
    final CropImageView cropImageView;

    private float getXTranslation(int i, int i2, float f, boolean z) {
        if (z) {
            return 0.0f;
        }
        switch (i) {
            case 3:
            case 4:
            case 5:
                return i2 - f;
            case 6:
            case 7:
                return (i2 - f) / 2.0f;
            default:
                return 0.0f;
        }
    }

    private float getYTranslation(int i, int i2, float f, boolean z) {
        if (z) {
            switch (i) {
                case 1:
                case 4:
                    return (i2 - f) / 2.0f;
                case 2:
                case 5:
                case 7:
                    return i2 - f;
                case 3:
                case 6:
                default:
                    return 0.0f;
            }
        }
        return 0.0f;
    }

    public CropImage(@NonNull CropImageView cropImageView) {
        this.cropImageView = cropImageView;
    }

    public void computeImageTransformation() {
        try {
            int width = (this.cropImageView.getWidth() - this.cropImageView.getPaddingLeft()) - this.cropImageView.getPaddingRight();
            int height = (this.cropImageView.getHeight() - this.cropImageView.getPaddingTop()) - this.cropImageView.getPaddingBottom();
            int i = 1242;
            int i2 = width == height ? 1242 : 490;
            int cropType = this.cropImageView.getCropType();
            if (cropType == -1 || height <= 0 || width <= 0) {
                return;
            }
            Matrix matrix = getMatrix();
            Drawable drawable = this.cropImageView.getDrawable();
            try {
                i = drawable.getIntrinsicWidth();
                i2 = drawable.getIntrinsicHeight();
            } catch (Exception e) {
                e.printStackTrace();
            }
            float f = i2;
            float f2 = height / f;
            float f3 = i;
            float f4 = width / f3;
            int i3 = (f4 > f2 ? 1 : (f4 == f2 ? 0 : -1));
            if (i3 > 0) {
                f2 = f4;
            }
            matrix.setScale(f2, f2);
            boolean z = i3 > 0;
            matrix.postTranslate(getXTranslation(cropType, width, f3 * f2, z), getYTranslation(cropType, height, f * f2, z));
            this.cropImageView.setImageMatrix(matrix);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
