package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import java.security.MessageDigest;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CropSquareTransformation extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f24863ID = "jp.wasabeef.glide.transformations.CropSquareTransformation.1";
    private static final int VERSION = 1;
    private int size;

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation
    protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        this.size = Math.max(i, i2);
        int i3 = this.size;
        return TransformationUtils.centerCrop(bitmapPool, bitmap, i3, i3);
    }

    public String toString() {
        return "CropSquareTransformation(size=" + this.size + ")";
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return (obj instanceof CropSquareTransformation) && ((CropSquareTransformation) obj).size == this.size;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24863ID.hashCode() + (this.size * 10);
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((f24863ID + this.size).getBytes(CHARSET));
    }
}
