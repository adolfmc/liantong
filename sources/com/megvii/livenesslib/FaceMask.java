package com.megvii.livenesslib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.megvii.kas.livenessdetection.DetectionFrame;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FaceMask extends View {
    public static final int Threshold = 30;
    private int high_colour;
    private boolean isFraontalCamera;
    Paint localPaint;
    RectF mDrawRect;
    RectF mFaceRect;
    private int normal_colour;

    public FaceMask(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.localPaint = null;
        this.mFaceRect = new RectF();
        this.mDrawRect = null;
        this.normal_colour = -16730881;
        this.high_colour = -65536;
        this.isFraontalCamera = true;
        this.mDrawRect = new RectF();
        this.localPaint = new Paint();
        this.localPaint.setColor(this.normal_colour);
        this.localPaint.setStrokeWidth(5.0f);
        this.localPaint.setStyle(Paint.Style.STROKE);
    }

    public void setFaceInfo(DetectionFrame detectionFrame) {
        if (detectionFrame != null) {
            this.mFaceRect = detectionFrame.getFacePos();
        } else {
            this.mFaceRect = null;
        }
        postInvalidate();
    }

    public void setFrontal(boolean z) {
        this.isFraontalCamera = z;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mFaceRect == null) {
            return;
        }
        if (this.isFraontalCamera) {
            this.mDrawRect.set(getWidth() * (1.0f - this.mFaceRect.right), getHeight() * this.mFaceRect.top, getWidth() * (1.0f - this.mFaceRect.left), getHeight() * this.mFaceRect.bottom);
        } else {
            this.mDrawRect.set(getWidth() * this.mFaceRect.left, getHeight() * this.mFaceRect.top, getWidth() * this.mFaceRect.right, getHeight() * this.mFaceRect.bottom);
        }
        canvas.drawRect(this.mDrawRect, this.localPaint);
    }
}
