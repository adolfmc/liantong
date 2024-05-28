package jp.wasabeef.glide.transformations.gpu;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageKuwaharaFilter;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class KuwaharaFilterTransformation extends GPUFilterTransformation {

    /* renamed from: ID */
    private static final String f24875ID = "jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation.1";
    private static final int VERSION = 1;
    private int radius;

    public KuwaharaFilterTransformation() {
        this(25);
    }

    public KuwaharaFilterTransformation(int i) {
        super(new GPUImageKuwaharaFilter());
        this.radius = i;
        ((GPUImageKuwaharaFilter) getFilter()).setRadius(this.radius);
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation
    public String toString() {
        return "KuwaharaFilterTransformation(radius=" + this.radius + ")";
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof KuwaharaFilterTransformation;
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24875ID.hashCode() + (this.radius * 10);
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((f24875ID + this.radius).getBytes(CHARSET));
    }
}
