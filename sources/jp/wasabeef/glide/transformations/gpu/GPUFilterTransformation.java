package jp.wasabeef.glide.transformations.gpu;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter;
import jp.wasabeef.glide.transformations.BitmapTransformation;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class GPUFilterTransformation extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f24873ID = "jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation.1";
    private static final byte[] ID_BYTES = f24873ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private GPUImageFilter gpuImageFilter;

    public GPUFilterTransformation(GPUImageFilter gPUImageFilter) {
        this.gpuImageFilter = gPUImageFilter;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation
    public Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        GPUImage gPUImage = new GPUImage(context);
        gPUImage.setImage(bitmap);
        gPUImage.setFilter(this.gpuImageFilter);
        return gPUImage.getBitmapWithFilterApplied();
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public <T> T getFilter() {
        return (T) this.gpuImageFilter;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof GPUFilterTransformation;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24873ID.hashCode();
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
