package com.baidu.p120ar.photo;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.baidu.p120ar.arplay.core.renderer.TakePictureCallback;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.utils.FileUtils;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.photo.PhotoTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PhotoTask implements TakePictureCallback {
    private String mOutput;
    private PhotoCallback mPhotoCallback;

    public void takePicture(IARRenderer iARRenderer, String str, PhotoCallback photoCallback) {
        if (iARRenderer == null || TextUtils.isEmpty(str) || photoCallback == null) {
            return;
        }
        this.mOutput = str;
        this.mPhotoCallback = photoCallback;
        iARRenderer.getSnapShot(this);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.TakePictureCallback
    public void onPictureTake(boolean z, Bitmap bitmap, long j) {
        if (this.mPhotoCallback == null || TextUtils.isEmpty(this.mOutput)) {
            return;
        }
        FileUtils.deleteFileIfExist(new File(this.mOutput));
        FileUtils.saveBitmap(this.mOutput, bitmap, 100);
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mPhotoCallback.onPictureTake(z, this.mOutput);
    }
}
