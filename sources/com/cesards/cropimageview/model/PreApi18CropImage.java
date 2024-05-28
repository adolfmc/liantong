package com.cesards.cropimageview.model;

import android.graphics.Matrix;
import com.cesards.cropimageview.CropImageView;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PreApi18CropImage extends CropImage {
    private Matrix matrix;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreApi18CropImage(CropImageView cropImageView) {
        super(cropImageView);
        init(cropImageView);
    }

    private void init(CropImageView cropImageView) {
        if (cropImageView.getCropType() != -1) {
            this.matrix = new Matrix();
        }
    }

    @Override // com.cesards.cropimageview.model.Transformation
    public Matrix getMatrix() {
        Matrix matrix = this.matrix;
        return matrix == null ? this.cropImageView.getImageMatrix() : matrix;
    }
}
