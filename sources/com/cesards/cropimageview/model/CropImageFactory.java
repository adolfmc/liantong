package com.cesards.cropimageview.model;

import android.os.Build;
import android.support.annotation.NonNull;
import com.cesards.cropimageview.CropImageView;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CropImageFactory {
    public CropImage getCropImage(@NonNull CropImageView cropImageView) {
        return Build.VERSION.SDK_INT < 18 ? new PreApi18CropImage(cropImageView) : new API18Image(cropImageView);
    }
}
