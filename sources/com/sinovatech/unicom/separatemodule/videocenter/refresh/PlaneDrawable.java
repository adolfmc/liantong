package com.sinovatech.unicom.separatemodule.videocenter.refresh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PlaneDrawable extends RefreshDrawable implements Runnable {
    protected List<Bitmap> bitmaps;
    protected int drawableMinddleWidth;
    private boolean isRunning;
    private Handler mHandler;
    protected int mOffset;
    protected float mPercent;
    protected RectF rectF;

    @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.RefreshDrawable
    public void setColorSchemeColors(int[] iArr) {
    }

    public PlaneDrawable(Context context, PullRefreshLayout pullRefreshLayout) {
        super(context, pullRefreshLayout);
        this.mHandler = new Handler();
        this.bitmaps = new ArrayList();
        this.rectF = new RectF();
        getBitmaps(context);
    }

    private void getBitmaps(Context context) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(2131231626);
        this.drawableMinddleWidth = bitmapDrawable.getMinimumWidth() / 2;
        this.bitmaps.add(bitmapDrawable.getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
        this.bitmaps.add(((BitmapDrawable) context.getResources().getDrawable(2131231626)).getBitmap());
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.RefreshDrawable
    public void setPercent(float f) {
        this.mPercent = f;
        int centerX = getBounds().centerX();
        RectF rectF = this.rectF;
        float f2 = centerX;
        int i = this.drawableMinddleWidth;
        float f3 = this.mPercent;
        rectF.left = f2 - (i * f3);
        rectF.right = f2 + (i * f3);
        rectF.top = (-i) * 2 * f3;
        rectF.bottom = 0.0f;
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.RefreshDrawable
    public void offsetTopAndBottom(int i) {
        this.mOffset += i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.isRunning = true;
        this.mHandler.postDelayed(this, 50L);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.isRunning) {
            this.mHandler.postDelayed(this, 50L);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.isRunning = false;
        this.mHandler.removeCallbacks(this);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int save = canvas.save();
        canvas.translate(0.0f, this.mOffset);
        canvas.drawBitmap(this.bitmaps.get((int) ((System.currentTimeMillis() / 50) % 11)), (Rect) null, this.rectF, (Paint) null);
        canvas.restoreToCount(save);
    }
}
