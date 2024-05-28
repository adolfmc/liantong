package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CropTransformation extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f24864ID = "jp.wasabeef.glide.transformations.CropTransformation.1";
    private static final int VERSION = 1;
    private CropType cropType;
    private int height;
    private int width;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum CropType {
        TOP,
        CENTER,
        BOTTOM
    }

    public CropTransformation(int i, int i2) {
        this(i, i2, CropType.CENTER);
    }

    public CropTransformation(int i, int i2, CropType cropType) {
        this.cropType = CropType.CENTER;
        this.width = i;
        this.height = i2;
        this.cropType = cropType;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation
    protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        int i3 = this.width;
        if (i3 == 0) {
            i3 = bitmap.getWidth();
        }
        this.width = i3;
        int i4 = this.height;
        if (i4 == 0) {
            i4 = bitmap.getHeight();
        }
        this.height = i4;
        Bitmap bitmap2 = bitmapPool.get(this.width, this.height, bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888);
        bitmap2.setHasAlpha(true);
        float max = Math.max(this.width / bitmap.getWidth(), this.height / bitmap.getHeight());
        float width = bitmap.getWidth() * max;
        float height = max * bitmap.getHeight();
        float f = (this.width - width) / 2.0f;
        float top = getTop(height);
        new Canvas(bitmap2).drawBitmap(bitmap, (Rect) null, new RectF(f, top, width + f, height + top), (Paint) null);
        return bitmap2;
    }

    private float getTop(float f) {
        switch (this.cropType) {
            case TOP:
                return 0.0f;
            case CENTER:
                return (this.height - f) / 2.0f;
            case BOTTOM:
                return this.height - f;
            default:
                return 0.0f;
        }
    }

    public String toString() {
        return "CropTransformation(width=" + this.width + ", height=" + this.height + ", cropType=" + this.cropType + ")";
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof CropTransformation) {
            CropTransformation cropTransformation = (CropTransformation) obj;
            if (cropTransformation.width == this.width && cropTransformation.height == this.height && cropTransformation.cropType == this.cropType) {
                return true;
            }
        }
        return false;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24864ID.hashCode() + (this.width * 100000) + (this.height * 1000) + (this.cropType.ordinal() * 10);
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((f24864ID + this.width + this.height + this.cropType).getBytes(CHARSET));
    }
}
