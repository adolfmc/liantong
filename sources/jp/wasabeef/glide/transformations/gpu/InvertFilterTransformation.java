package jp.wasabeef.glide.transformations.gpu;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorInvertFilter;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class InvertFilterTransformation extends GPUFilterTransformation {

    /* renamed from: ID */
    private static final String f24874ID = "jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation.1";
    private static final int VERSION = 1;

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation
    public String toString() {
        return "InvertFilterTransformation()";
    }

    public InvertFilterTransformation() {
        super(new GPUImageColorInvertFilter());
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof InvertFilterTransformation;
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24874ID.hashCode();
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(f24874ID.getBytes(CHARSET));
    }
}
