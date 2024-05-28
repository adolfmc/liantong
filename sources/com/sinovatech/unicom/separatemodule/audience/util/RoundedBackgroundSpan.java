package com.sinovatech.unicom.separatemodule.audience.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import com.sinovatech.unicom.common.UIUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RoundedBackgroundSpan extends ReplacementSpan {
    private static int CORNER_RADIUS = UIUtils.dip2px(12.0f);
    private Bitmap icon;
    private Bitmap icon0;
    private float icon0W;
    private float iconW;
    private int userColor;
    private int userEnd;
    private int userStart;
    private int paddingTB = UIUtils.dip2px(2.0f);
    private int paddingLR = UIUtils.dip2px(8.0f);

    public RoundedBackgroundSpan(Context context, int i, int i2, int i3, Bitmap bitmap, float f, Bitmap bitmap2, float f2) {
        this.userColor = 0;
        this.userColor = i;
        this.userEnd = i3;
        this.userStart = i2;
        this.icon = bitmap;
        this.iconW = f;
        this.icon0 = bitmap2;
        this.icon0W = f2;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        canvas.drawColor(0);
        int i6 = this.paddingLR;
        int i7 = this.paddingTB;
        RectF rectF = new RectF(i6 + f, i3 + i7, this.iconW + f + i6, i5 + i7);
        float measureText = measureText(paint, charSequence, i, i2);
        float width = this.icon == null ? 0.0f : rectF.width();
        int i8 = this.paddingLR;
        int i9 = this.paddingTB;
        RectF rectF2 = new RectF(i8 + f + width, i3 + i9, i8 + f + width + this.icon0W, i5 + i9);
        float width2 = this.icon0 == null ? 0.0f : rectF2.width();
        int i10 = this.paddingLR;
        float f2 = i10 + f + width + width2 + 8.0f + measureText + i10;
        int i11 = this.paddingTB;
        RectF rectF3 = new RectF(f, i3, f2, i5 + i11 + i11);
        paint.setColor(16711680);
        int i12 = CORNER_RADIUS;
        canvas.drawRoundRect(rectF3, i12, i12, paint);
        Bitmap bitmap = this.icon;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, rectF, paint);
        }
        Bitmap bitmap2 = this.icon0;
        if (bitmap2 != null) {
            canvas.drawBitmap(bitmap2, (Rect) null, rectF2, paint);
        }
        CharSequence subSequence = charSequence.subSequence(this.userStart, this.userEnd);
        paint.setColor(this.userColor);
        float dip2px = f + this.paddingLR + width + width2 + UIUtils.dip2px(6.0f);
        float f3 = i4;
        canvas.drawText(subSequence, i, subSequence.length(), dip2px, f3, paint);
        paint.setColor(-1);
        canvas.drawText(charSequence, this.userEnd, i2, dip2px + measureText(paint, subSequence, i, subSequence.length()), f3, paint);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return Math.round(paint.measureText(charSequence, i, i2));
    }

    private float measureText(Paint paint, CharSequence charSequence, int i, int i2) {
        return paint.measureText(charSequence, i, i2);
    }
}
