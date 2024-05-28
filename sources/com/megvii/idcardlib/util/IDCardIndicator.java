package com.megvii.idcardlib.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.megvii.idcardlib.C5301R;
import com.megvii.idcardquality.bean.IDCardAttr;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IDCardIndicator extends View {
    private float CONTENT_RATIO;
    private float IDCARD_RATIO;
    private float SHOW_CONTENT_RATIO;
    private int backColor;
    private int faculaeHeight;
    private Paint faculaePaint;
    private int faculaeWidth;
    private Paint mDrawPaint;
    private Rect mDrawRect;
    private IDCardAttr.IDCardSide mIdCardSide;
    private boolean mIsVertical;
    private Rect mShowRect;
    private Rect mTmpRect;

    private void drawFaculae(Canvas canvas) {
    }

    private void init() {
        this.mShowRect = new Rect();
        this.mDrawRect = new Rect();
        this.mTmpRect = new Rect();
        this.mDrawPaint = new Paint();
        this.mDrawPaint.setDither(true);
        this.mDrawPaint.setAntiAlias(true);
        this.mDrawPaint.setStrokeWidth(10.0f);
        this.mDrawPaint.setStyle(Paint.Style.STROKE);
        this.mDrawPaint.setColor(-16776961);
    }

    public void setBackColor(Activity activity, int i) {
        if (this.backColor != i) {
            this.backColor = i;
            activity.runOnUiThread(new Runnable() { // from class: com.megvii.idcardlib.util.IDCardIndicator.1
                @Override // java.lang.Runnable
                public void run() {
                    IDCardIndicator.this.invalidate();
                }
            });
        }
    }

    public IDCardIndicator(Context context) {
        super(context);
        this.mShowRect = null;
        this.mDrawRect = null;
        this.mDrawPaint = null;
        this.IDCARD_RATIO = 1.5851852f;
        this.CONTENT_RATIO = 1.0f;
        this.SHOW_CONTENT_RATIO = (this.CONTENT_RATIO * 13.0f) / 16.0f;
        this.mTmpRect = null;
        this.backColor = 0;
        init();
    }

    public IDCardIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mShowRect = null;
        this.mDrawRect = null;
        this.mDrawPaint = null;
        this.IDCARD_RATIO = 1.5851852f;
        this.CONTENT_RATIO = 1.0f;
        this.SHOW_CONTENT_RATIO = (this.CONTENT_RATIO * 13.0f) / 16.0f;
        this.mTmpRect = null;
        this.backColor = 0;
        init();
    }

    public IDCardIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowRect = null;
        this.mDrawRect = null;
        this.mDrawPaint = null;
        this.IDCARD_RATIO = 1.5851852f;
        this.CONTENT_RATIO = 1.0f;
        this.SHOW_CONTENT_RATIO = (this.CONTENT_RATIO * 13.0f) / 16.0f;
        this.mTmpRect = null;
        this.backColor = 0;
        init();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i5 = size >> 1;
        int i6 = size2 >> 1;
        float f = size;
        float f2 = size2;
        float f3 = this.IDCARD_RATIO;
        if (f / f2 < f3) {
            i4 = (int) (f * this.SHOW_CONTENT_RATIO);
            i3 = (int) (i4 / f3);
        } else {
            i3 = (int) (f2 * this.SHOW_CONTENT_RATIO);
            i4 = (int) (i3 * f3);
        }
        Rect rect = this.mShowRect;
        int i7 = i4 / 2;
        rect.left = i5 - i7;
        int i8 = i3 / 2;
        rect.top = i6 - i8;
        rect.right = i5 + i7;
        rect.bottom = i6 + i8;
        getActualRect(size, size2);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setCardSideAndOrientation(boolean z, IDCardAttr.IDCardSide iDCardSide) {
        this.mIsVertical = z;
        this.mIdCardSide = iDCardSide;
    }

    public void setContentRatio(boolean z) {
        this.mIsVertical = z;
        if (z) {
            this.CONTENT_RATIO = 1.0f;
        } else {
            this.CONTENT_RATIO = 0.8f;
        }
        this.SHOW_CONTENT_RATIO = (this.CONTENT_RATIO * 13.0f) / 16.0f;
        invalidate();
    }

    private void getActualRect(int i, int i2) {
        int i3;
        int i4;
        int i5 = i >> 1;
        int i6 = i2 >> 1;
        float f = i;
        float f2 = i2;
        float f3 = this.IDCARD_RATIO;
        if (f / f2 < f3) {
            i4 = (int) (f * this.CONTENT_RATIO);
            i3 = (int) (i4 / f3);
        } else {
            i3 = (int) (f2 * this.CONTENT_RATIO);
            i4 = (int) (i3 * f3);
        }
        Rect rect = this.mDrawRect;
        int i7 = i4 / 2;
        rect.left = i5 - i7;
        int i8 = i3 / 2;
        rect.top = i6 - i8;
        rect.right = i5 + i7;
        rect.bottom = i6 + i8;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mDrawPaint.setStyle(Paint.Style.FILL);
        this.mDrawPaint.setColor(this.backColor);
        this.mTmpRect.set(0, 0, getWidth(), this.mShowRect.top);
        canvas.drawRect(this.mTmpRect, this.mDrawPaint);
        this.mTmpRect.set(0, this.mShowRect.bottom, getWidth(), getHeight());
        canvas.drawRect(this.mTmpRect, this.mDrawPaint);
        this.mTmpRect.set(0, this.mShowRect.top, this.mShowRect.left, this.mShowRect.bottom);
        canvas.drawRect(this.mTmpRect, this.mDrawPaint);
        this.mTmpRect.set(this.mShowRect.right, this.mShowRect.top, getWidth(), this.mShowRect.bottom);
        canvas.drawRect(this.mTmpRect, this.mDrawPaint);
        drawViewfinder(canvas);
        drawFaculae(canvas);
        super.onDraw(canvas);
    }

    private void drawViewfinder(Canvas canvas) {
        int i;
        this.mDrawPaint.setStyle(Paint.Style.STROKE);
        this.mDrawPaint.setColor(-16722945);
        this.mDrawPaint.setStrokeWidth(4.0f);
        int height = this.mShowRect.height() / 16;
        canvas.drawLine(this.mShowRect.left, this.mShowRect.top, this.mShowRect.left + height, this.mShowRect.top, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.left, this.mShowRect.top, this.mShowRect.left, this.mShowRect.top + height, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.right, this.mShowRect.top, this.mShowRect.right - height, this.mShowRect.top, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.right, this.mShowRect.top, this.mShowRect.right, this.mShowRect.top + height, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.left, this.mShowRect.bottom, this.mShowRect.left + height, this.mShowRect.bottom, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.left, this.mShowRect.bottom, this.mShowRect.left, this.mShowRect.bottom - height, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.right, this.mShowRect.bottom, this.mShowRect.right - height, this.mShowRect.bottom, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.right, this.mShowRect.bottom, this.mShowRect.right, this.mShowRect.bottom - height, this.mDrawPaint);
        this.mDrawPaint.setColor(-1140850689);
        this.mDrawPaint.setStrokeWidth(2.0f);
        canvas.drawLine(this.mShowRect.left + height, this.mShowRect.top, this.mShowRect.right - height, this.mShowRect.top, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.left, this.mShowRect.top + height, this.mShowRect.left, this.mShowRect.bottom - height, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.right, this.mShowRect.top + height, this.mShowRect.right, this.mShowRect.bottom - height, this.mDrawPaint);
        canvas.drawLine(this.mShowRect.left + height, this.mShowRect.bottom, this.mShowRect.right - height, this.mShowRect.bottom, this.mDrawPaint);
        if (this.mIdCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
            i = C5301R.C5302drawable.sfz_front;
        } else {
            i = this.mIdCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_BACK ? C5301R.C5302drawable.sfz_back : 0;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(getContext().getResources(), i);
        canvas.drawBitmap(decodeResource, new Rect(0, 0, decodeResource.getWidth(), decodeResource.getHeight()), new Rect(this.mShowRect.left, this.mShowRect.top, this.mShowRect.left + this.mShowRect.width(), this.mShowRect.top + this.mShowRect.height()), (Paint) null);
    }

    public RectF getShowPosition() {
        RectF rectF = new RectF();
        rectF.left = this.mShowRect.left / getWidth();
        rectF.top = this.mShowRect.top / getHeight();
        rectF.right = this.mShowRect.right / getWidth();
        rectF.bottom = this.mShowRect.bottom / getHeight();
        return rectF;
    }

    public RectF getPosition() {
        RectF rectF = new RectF();
        rectF.left = this.mDrawRect.left / getWidth();
        rectF.top = this.mDrawRect.top / getHeight();
        rectF.right = this.mDrawRect.right / getWidth();
        rectF.bottom = this.mDrawRect.bottom / getHeight();
        return rectF;
    }

    public Rect getMargin() {
        Rect rect = new Rect();
        rect.left = this.mDrawRect.left;
        rect.top = this.mDrawRect.top;
        rect.right = getWidth() - this.mDrawRect.right;
        rect.bottom = getHeight() - this.mDrawRect.bottom;
        return rect;
    }
}
