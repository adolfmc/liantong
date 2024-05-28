package jp.wasabeef.glide.transformations.gpu;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BrightnessFilterTransformation extends GPUFilterTransformation {

    /* renamed from: ID */
    private static final String f24871ID = "jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation.1";
    private static final int VERSION = 1;
    private float brightness;

    public BrightnessFilterTransformation() {
        this(0.0f);
    }

    public BrightnessFilterTransformation(float f) {
        super(new GPUImageBrightnessFilter());
        this.brightness = f;
        ((GPUImageBrightnessFilter) getFilter()).setBrightness(this.brightness);
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation
    public String toString() {
        return "BrightnessFilterTransformation(brightness=" + this.brightness + ")";
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return (obj instanceof BrightnessFilterTransformation) && ((BrightnessFilterTransformation) obj).brightness == this.brightness;
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24871ID.hashCode() + ((int) ((this.brightness + 1.0f) * 10.0f));
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((f24871ID + this.brightness).getBytes(CHARSET));
    }
}
