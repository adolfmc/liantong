package jp.wasabeef.glide.transformations.gpu;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSketchFilter;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SketchFilterTransformation extends GPUFilterTransformation {

    /* renamed from: ID */
    private static final String f24878ID = "jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation.1";
    private static final int VERSION = 1;

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation
    public String toString() {
        return "SketchFilterTransformation()";
    }

    public SketchFilterTransformation() {
        super(new GPUImageSketchFilter());
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof SketchFilterTransformation;
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24878ID.hashCode();
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(f24878ID.getBytes(CHARSET));
    }
}
