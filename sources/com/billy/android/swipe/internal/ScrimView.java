package com.billy.android.swipe.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.View;
import com.billy.android.swipe.SmartSwipe;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ScrimView extends View {
    public static float MAX_PROGRESS = 1.0f;
    public static float MIN_PROGRESS;
    private int mBaseAlpha;
    private Rect mBounds;
    private int mDirection;
    private final Paint mPaint;
    private int mScrimColor;
    private int mShadowColor;
    private int mShadowDirection;
    private Paint mShadowPaint;
    private Rect mShadowRect;
    private int mSize;

    public ScrimView(Context context) {
        super(context);
        this.mSize = 60;
        this.mBounds = new Rect();
        this.mShadowRect = new Rect();
        this.mShadowColor = Integer.MIN_VALUE;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mShadowPaint = new Paint();
        this.mShadowPaint.setDither(true);
        this.mShadowPaint.setAntiAlias(true);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Rect rect = this.mBounds;
        rect.right = i;
        rect.bottom = i2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mScrimColor != 0) {
            canvas.drawRect(this.mBounds, this.mPaint);
        }
        if (this.mSize <= 0 || this.mShadowColor == 0 || (this.mDirection & 15) <= 0) {
            return;
        }
        canvas.save();
        int i = this.mShadowDirection;
        if (i == 2) {
            canvas.translate(this.mBounds.right - this.mSize, 0.0f);
        } else if (i == 8) {
            canvas.translate(0.0f, this.mBounds.bottom - this.mSize);
        }
        canvas.clipRect(this.mShadowRect);
        canvas.drawPaint(this.mShadowPaint);
        canvas.restore();
    }

    public void setProgress(float f) {
        this.mPaint.setColor((((int) (this.mBaseAlpha * SmartSwipe.ensureBetween(f, MIN_PROGRESS, MAX_PROGRESS))) << 24) | (this.mScrimColor & 16777215));
    }

    public void setScrimColor(int i) {
        this.mScrimColor = i;
        this.mBaseAlpha = (this.mScrimColor & (-16777216)) >>> 24;
    }

    public void setDirection(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        this.mDirection = i;
        this.mShadowColor = i2;
        this.mShadowDirection = i3;
        this.mSize = i4;
        if (this.mShadowColor == 0) {
            return;
        }
        int i12 = this.mShadowDirection;
        int i13 = 0;
        if (i12 != 4 && i12 != 8) {
            switch (i12) {
                case 1:
                case 2:
                    i9 = this.mSize;
                    i7 = i6;
                    i8 = i7;
                    break;
                default:
                    return;
            }
        } else {
            i7 = this.mSize;
            i8 = 0;
            i9 = i5;
        }
        Rect rect = this.mShadowRect;
        rect.right = i9;
        rect.bottom = i7;
        int i14 = (this.mShadowColor & (-16777216)) >>> 24;
        float[] fArr = new float[31];
        int[] iArr = new int[31];
        int i15 = this.mShadowDirection;
        boolean z = true;
        boolean z2 = i15 == 1 || i15 == 4;
        for (int i16 = 0; i16 <= 30; i16++) {
            fArr[i16] = (i16 * 1.0f) / 30;
        }
        for (int i17 = 0; i17 <= 30; i17++) {
            float f = fArr[z2 ? 30 - i17 : i17];
            iArr[i17] = (((int) ((i14 * f) * f)) << 24) | (this.mShadowColor & 16777215);
        }
        if (i != 1 && i != 2) {
            z = false;
        }
        if (z) {
            i11 = i7 >> 1;
            i10 = i11;
        } else {
            i13 = i9 >> 1;
            i9 = i13;
            i10 = i7;
            i11 = i8;
        }
        this.mShadowPaint.setShader(new LinearGradient(i13, i11, i9, i10, iArr, fArr, Shader.TileMode.CLAMP));
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }
}
