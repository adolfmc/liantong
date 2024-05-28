package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RoundedCornersTransformation extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f24868ID = "jp.wasabeef.glide.transformations.RoundedCornersTransformation.1";
    private static final int VERSION = 1;
    private CornerType cornerType;
    private int diameter;
    private int margin;
    private int radius;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    public RoundedCornersTransformation(int i, int i2) {
        this(i, i2, CornerType.ALL);
    }

    public RoundedCornersTransformation(int i, int i2, CornerType cornerType) {
        this.radius = i;
        this.diameter = this.radius * 2;
        this.margin = i2;
        this.cornerType = cornerType;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation
    protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmap2 = bitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
        bitmap2.setHasAlpha(true);
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        drawRoundRect(canvas, paint, width, height);
        return bitmap2;
    }

    private void drawRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        float f3 = f - i;
        float f4 = f2 - i;
        switch (this.cornerType) {
            case ALL:
                int i2 = this.margin;
                RectF rectF = new RectF(i2, i2, f3, f4);
                int i3 = this.radius;
                canvas.drawRoundRect(rectF, i3, i3, paint);
                return;
            case TOP_LEFT:
                drawTopLeftRoundRect(canvas, paint, f3, f4);
                return;
            case TOP_RIGHT:
                drawTopRightRoundRect(canvas, paint, f3, f4);
                return;
            case BOTTOM_LEFT:
                drawBottomLeftRoundRect(canvas, paint, f3, f4);
                return;
            case BOTTOM_RIGHT:
                drawBottomRightRoundRect(canvas, paint, f3, f4);
                return;
            case TOP:
                drawTopRoundRect(canvas, paint, f3, f4);
                return;
            case BOTTOM:
                drawBottomRoundRect(canvas, paint, f3, f4);
                return;
            case LEFT:
                drawLeftRoundRect(canvas, paint, f3, f4);
                return;
            case RIGHT:
                drawRightRoundRect(canvas, paint, f3, f4);
                return;
            case OTHER_TOP_LEFT:
                drawOtherTopLeftRoundRect(canvas, paint, f3, f4);
                return;
            case OTHER_TOP_RIGHT:
                drawOtherTopRightRoundRect(canvas, paint, f3, f4);
                return;
            case OTHER_BOTTOM_LEFT:
                drawOtherBottomLeftRoundRect(canvas, paint, f3, f4);
                return;
            case OTHER_BOTTOM_RIGHT:
                drawOtherBottomRightRoundRect(canvas, paint, f3, f4);
                return;
            case DIAGONAL_FROM_TOP_LEFT:
                drawDiagonalFromTopLeftRoundRect(canvas, paint, f3, f4);
                return;
            case DIAGONAL_FROM_TOP_RIGHT:
                drawDiagonalFromTopRightRoundRect(canvas, paint, f3, f4);
                return;
            default:
                int i4 = this.margin;
                RectF rectF2 = new RectF(i4, i4, f3, f4);
                int i5 = this.radius;
                canvas.drawRoundRect(rectF2, i5, i5, paint);
                return;
        }
    }

    private void drawTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        int i2 = this.diameter;
        RectF rectF = new RectF(i, i, i + i2, i + i2);
        int i3 = this.radius;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.margin;
        int i5 = this.radius;
        canvas.drawRect(new RectF(i4, i4 + i5, i4 + i5, f2), paint);
        int i6 = this.margin;
        canvas.drawRect(new RectF(this.radius + i6, i6, f, f2), paint);
    }

    private void drawTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.diameter;
        int i2 = this.margin;
        RectF rectF = new RectF(f - i, i2, f, i2 + i);
        int i3 = this.radius;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.margin;
        canvas.drawRect(new RectF(i4, i4, f - this.radius, f2), paint);
        int i5 = this.radius;
        canvas.drawRect(new RectF(f - i5, this.margin + i5, f, f2), paint);
    }

    private void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        int i2 = this.diameter;
        RectF rectF = new RectF(i, f2 - i2, i + i2, f2);
        int i3 = this.radius;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.margin;
        canvas.drawRect(new RectF(i4, i4, i4 + this.diameter, f2 - this.radius), paint);
        int i5 = this.margin;
        canvas.drawRect(new RectF(this.radius + i5, i5, f, f2), paint);
    }

    private void drawBottomRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.diameter;
        RectF rectF = new RectF(f - i, f2 - i, f, f2);
        int i2 = this.radius;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        int i3 = this.margin;
        canvas.drawRect(new RectF(i3, i3, f - this.radius, f2), paint);
        int i4 = this.radius;
        canvas.drawRect(new RectF(f - i4, this.margin, f, f2 - i4), paint);
    }

    private void drawTopRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        RectF rectF = new RectF(i, i, f, i + this.diameter);
        int i2 = this.radius;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        int i3 = this.margin;
        canvas.drawRect(new RectF(i3, i3 + this.radius, f, f2), paint);
    }

    private void drawBottomRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        RectF rectF = new RectF(this.margin, f2 - this.diameter, f, f2);
        int i = this.radius;
        canvas.drawRoundRect(rectF, i, i, paint);
        int i2 = this.margin;
        canvas.drawRect(new RectF(i2, i2, f, f2 - this.radius), paint);
    }

    private void drawLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        RectF rectF = new RectF(i, i, i + this.diameter, f2);
        int i2 = this.radius;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        int i3 = this.margin;
        canvas.drawRect(new RectF(this.radius + i3, i3, f, f2), paint);
    }

    private void drawRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        RectF rectF = new RectF(f - this.diameter, this.margin, f, f2);
        int i = this.radius;
        canvas.drawRoundRect(rectF, i, i, paint);
        int i2 = this.margin;
        canvas.drawRect(new RectF(i2, i2, f - this.radius, f2), paint);
    }

    private void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        RectF rectF = new RectF(this.margin, f2 - this.diameter, f, f2);
        int i = this.radius;
        canvas.drawRoundRect(rectF, i, i, paint);
        RectF rectF2 = new RectF(f - this.diameter, this.margin, f, f2);
        int i2 = this.radius;
        canvas.drawRoundRect(rectF2, i2, i2, paint);
        int i3 = this.margin;
        int i4 = this.radius;
        canvas.drawRect(new RectF(i3, i3, f - i4, f2 - i4), paint);
    }

    private void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        RectF rectF = new RectF(i, i, i + this.diameter, f2);
        int i2 = this.radius;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        RectF rectF2 = new RectF(this.margin, f2 - this.diameter, f, f2);
        int i3 = this.radius;
        canvas.drawRoundRect(rectF2, i3, i3, paint);
        int i4 = this.margin;
        int i5 = this.radius;
        canvas.drawRect(new RectF(i4 + i5, i4, f, f2 - i5), paint);
    }

    private void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        RectF rectF = new RectF(i, i, f, i + this.diameter);
        int i2 = this.radius;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        RectF rectF2 = new RectF(f - this.diameter, this.margin, f, f2);
        int i3 = this.radius;
        canvas.drawRoundRect(rectF2, i3, i3, paint);
        int i4 = this.margin;
        int i5 = this.radius;
        canvas.drawRect(new RectF(i4, i4 + i5, f - i5, f2), paint);
    }

    private void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        RectF rectF = new RectF(i, i, f, i + this.diameter);
        int i2 = this.radius;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        int i3 = this.margin;
        RectF rectF2 = new RectF(i3, i3, i3 + this.diameter, f2);
        int i4 = this.radius;
        canvas.drawRoundRect(rectF2, i4, i4, paint);
        int i5 = this.margin;
        int i6 = this.radius;
        canvas.drawRect(new RectF(i5 + i6, i5 + i6, f, f2), paint);
    }

    private void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.margin;
        int i2 = this.diameter;
        RectF rectF = new RectF(i, i, i + i2, i + i2);
        int i3 = this.radius;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.diameter;
        RectF rectF2 = new RectF(f - i4, f2 - i4, f, f2);
        int i5 = this.radius;
        canvas.drawRoundRect(rectF2, i5, i5, paint);
        int i6 = this.margin;
        canvas.drawRect(new RectF(i6, i6 + this.radius, f - this.diameter, f2), paint);
        int i7 = this.margin;
        canvas.drawRect(new RectF(this.diameter + i7, i7, f, f2 - this.radius), paint);
    }

    private void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.diameter;
        int i2 = this.margin;
        RectF rectF = new RectF(f - i, i2, f, i2 + i);
        int i3 = this.radius;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.margin;
        int i5 = this.diameter;
        RectF rectF2 = new RectF(i4, f2 - i5, i4 + i5, f2);
        int i6 = this.radius;
        canvas.drawRoundRect(rectF2, i6, i6, paint);
        int i7 = this.margin;
        int i8 = this.radius;
        canvas.drawRect(new RectF(i7, i7, f - i8, f2 - i8), paint);
        int i9 = this.margin;
        int i10 = this.radius;
        canvas.drawRect(new RectF(i9 + i10, i9 + i10, f, f2), paint);
    }

    public String toString() {
        return "RoundedTransformation(radius=" + this.radius + ", margin=" + this.margin + ", diameter=" + this.diameter + ", cornerType=" + this.cornerType.name() + ")";
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof RoundedCornersTransformation) {
            RoundedCornersTransformation roundedCornersTransformation = (RoundedCornersTransformation) obj;
            if (roundedCornersTransformation.radius == this.radius && roundedCornersTransformation.diameter == this.diameter && roundedCornersTransformation.margin == this.margin && roundedCornersTransformation.cornerType == this.cornerType) {
                return true;
            }
        }
        return false;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return f24868ID.hashCode() + (this.radius * 10000) + (this.diameter * 1000) + (this.margin * 100) + (this.cornerType.ordinal() * 10);
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((f24868ID + this.radius + this.diameter + this.margin + this.cornerType).getBytes(CHARSET));
    }
}
