package com.sinovatech.unicom.separatemodule.audience.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RoomDescBgSpan extends ReplacementSpan {
    private int bgColor;
    private int textColor;

    public RoomDescBgSpan(int i, int i2) {
        this.bgColor = i;
        this.textColor = i2;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return ((int) paint.measureText(charSequence, i, i2)) + 60;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        int color = paint.getColor();
        paint.setColor(this.bgColor);
        canvas.drawRoundRect(new RectF(f, i3 + 1, ((int) paint.measureText(charSequence, i, i2)) + 40 + f, i5 - 1), 20.0f, 20.0f, paint);
        paint.setColor(this.textColor);
        canvas.drawText(charSequence, i, i2, f + 20.0f, i4, paint);
        paint.setColor(color);
    }
}
