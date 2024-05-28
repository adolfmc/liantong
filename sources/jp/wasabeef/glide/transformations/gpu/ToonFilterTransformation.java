package jp.wasabeef.glide.transformations.gpu;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageToonFilter;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ToonFilterTransformation extends GPUFilterTransformation {

    /* renamed from: ID */
    private static final String f24880ID = "jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation.1";
    private static final int VERSION = 1;
    private float quantizationLevels;
    private float threshold;

    public ToonFilterTransformation() {
        this(0.2f, 10.0f);
    }

    public ToonFilterTransformation(float f, float f2) {
        super(new GPUImageToonFilter());
        this.threshold = f;
        this.quantizationLevels = f2;
        GPUImageToonFilter gPUImageToonFilter = (GPUImageToonFilter) getFilter();
        gPUImageToonFilter.setThreshold(this.threshold);
        gPUImageToonFilter.setQuantizationLevels(this.quantizationLevels);
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation
    public String toString() {
        return "ToonFilterTransformation(threshold=" + this.threshold + ",quantizationLevels=" + this.quantizationLevels + ")";
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof ToonFilterTransformation) {
            ToonFilterTransformation toonFilterTransformation = (ToonFilterTransformation) obj;
            if (toonFilterTransformation.threshold == this.threshold && toonFilterTransformation.quantizationLevels == this.quantizationLevels) {
                return true;
            }
        }
        return false;
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24880ID.hashCode() + ((int) (this.threshold * 1000.0f)) + ((int) (this.quantizationLevels * 10.0f));
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((f24880ID + this.threshold + this.quantizationLevels).getBytes(CHARSET));
    }
}
