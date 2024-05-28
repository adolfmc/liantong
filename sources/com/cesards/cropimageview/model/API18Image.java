package com.cesards.cropimageview.model;

import android.graphics.Matrix;
import com.cesards.cropimageview.CropImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class API18Image extends CropImage {
    /* JADX INFO: Access modifiers changed from: package-private */
    public API18Image(CropImageView cropImageView) {
        super(cropImageView);
    }

    @Override // com.cesards.cropimageview.model.Transformation
    public Matrix getMatrix() {
        return this.cropImageView.getImageMatrix();
    }
}
