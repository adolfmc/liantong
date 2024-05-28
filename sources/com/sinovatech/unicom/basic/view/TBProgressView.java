package com.sinovatech.unicom.basic.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.IntRange;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.Danmaku;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TBProgressView extends View {
    int[] bgColors;
    float density;
    int foregroundColor;
    int foregroundColor1;
    int[] foregroundColors;
    LinearGradient mForegroundLinearGradient;
    LinearGradient mLinearGradient;
    private PorterDuffXfermode mSrcATopXfermode;

    /* renamed from: oa */
    private ObjectAnimator f18443oa;
    Paint paint;
    int pinkColor;
    float[] positions;
    int progress;
    RectF rectF;
    int whiteColor;

    public TBProgressView(Context context) {
        this(context, null);
    }

    public TBProgressView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TBProgressView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pinkColor = Color.parseColor(Danmaku.COLOR_WHITE);
        this.whiteColor = Color.parseColor(Danmaku.COLOR_WHITE);
        this.foregroundColor = Color.parseColor(Danmaku.COLOR_WHITE);
        this.foregroundColor1 = Color.parseColor(Danmaku.COLOR_WHITE);
        this.progress = 100;
        init();
    }

    private void init() {
        this.density = Resources.getSystem().getDisplayMetrics().density;
        this.paint = new Paint(1);
        this.paint.setStrokeWidth(this.density);
        this.paint.setFilterBitmap(true);
        this.rectF = new RectF();
        this.bgColors = new int[]{this.whiteColor, this.pinkColor};
        this.foregroundColors = new int[]{this.foregroundColor1, this.foregroundColor};
        this.positions = new float[]{0.5f, 0.51f};
        this.mSrcATopXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
        this.f18443oa = ObjectAnimator.ofInt(this, "progress", 0, 100);
        this.f18443oa.setDuration(2000L).setInterpolator(new LinearInterpolator());
        this.f18443oa.setRepeatCount(-1);
        this.f18443oa.setRepeatMode(2);
    }

    @Keep
    public void setProgress(@IntRange(from = 0, m22209to = 100) int i) {
        this.progress = i;
        invalidate();
    }

    public void startShowAnimate() {
        ObjectAnimator objectAnimator = this.f18443oa;
        if (objectAnimator != null) {
            objectAnimator.start();
        }
    }

    public void stopShowAnimate() {
        ObjectAnimator objectAnimator = this.f18443oa;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    public void setBgColors(int i, int i2) {
        int[] iArr = this.bgColors;
        iArr[0] = i;
        iArr[1] = i2;
        invalidate();
    }

    public void setForegroundColors(int i, int i2) {
        int[] iArr = this.foregroundColors;
        iArr[0] = i;
        iArr[1] = i2;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        float f = width / 10;
        this.mLinearGradient = new LinearGradient(0.0f, 0.0f, f, f, this.bgColors, this.positions, Shader.TileMode.REPEAT);
        this.mForegroundLinearGradient = new LinearGradient(0.0f, 0.0f, f, f, this.foregroundColors, this.positions, Shader.TileMode.REPEAT);
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint.setShader(this.mLinearGradient);
        float f2 = height;
        this.rectF.set(0.0f, 0.0f, width, f2);
        int saveLayer = canvas.saveLayer(null, null, 31);
        canvas.translate(getPaddingLeft(), getPaddingTop());
        float f3 = height / 2;
        canvas.drawRoundRect(this.rectF, f3, f3, this.paint);
        this.paint.setShader(null);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.foregroundColor);
        canvas.drawRoundRect(this.rectF, f3, f3, this.paint);
        this.rectF.set(0.0f, 0.0f, (width * this.progress) / 100, f2);
        this.paint.setXfermode(this.mSrcATopXfermode);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setShader(this.mForegroundLinearGradient);
        canvas.drawRoundRect(this.rectF, f3, f3, this.paint);
        canvas.translate(-getPaddingLeft(), -getPaddingTop());
        canvas.restoreToCount(saveLayer);
        this.paint.setXfermode(null);
        this.paint.setShader(null);
    }
}
