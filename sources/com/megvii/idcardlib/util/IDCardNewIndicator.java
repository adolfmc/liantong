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
public class IDCardNewIndicator extends View {
    private float CONTENT_RATIO;
    private float IDCARD_RATIO;
    private float RIGHT_RATIO;
    private float SHOW_CONTENT_RATIO;
    private int backColor;
    private Paint mDrawPaint;
    private Rect mDrawRect;
    private Paint mDrawRightPaint;
    private IDCardAttr.IDCardSide mIdCardSide;
    private boolean mIsVertical;
    private Rect mShowDrawRect;
    private Rect mTmpRect;
    private Rect mTmpRect_test;
    private Bitmap rightBitmap;
    private RectF rightRectf;
    private String rightText;
    private int right_height;
    private int right_width;

    private void init() {
        this.rightRectf = new RectF();
        this.mShowDrawRect = new Rect();
        this.mDrawRect = new Rect();
        this.mTmpRect = new Rect();
        this.mTmpRect_test = new Rect();
        this.mDrawRightPaint = new Paint();
        this.mDrawRightPaint.setColor(-1);
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
            activity.runOnUiThread(new Runnable() { // from class: com.megvii.idcardlib.util.IDCardNewIndicator.1
                @Override // java.lang.Runnable
                public void run() {
                    IDCardNewIndicator.this.invalidate();
                }
            });
        }
    }

    public void setRightImage(boolean z) {
        if (z) {
            this.rightText = "请将身份证正";
            this.rightBitmap = BitmapFactory.decodeResource(getResources(), C5301R.C5302drawable.sfz_front);
            return;
        }
        this.rightText = "请将身份证背";
        this.rightBitmap = BitmapFactory.decodeResource(getResources(), C5301R.C5302drawable.sfz_back);
    }

    public IDCardNewIndicator(Context context) {
        super(context);
        this.mShowDrawRect = null;
        this.mDrawRect = null;
        this.mDrawPaint = null;
        this.mDrawRightPaint = null;
        this.IDCARD_RATIO = 1.5851852f;
        this.CONTENT_RATIO = 0.8f;
        this.SHOW_CONTENT_RATIO = (this.CONTENT_RATIO * 13.0f) / 16.0f;
        this.RIGHT_RATIO = 0.2f;
        this.rightRectf = null;
        this.mTmpRect = null;
        this.mTmpRect_test = null;
        this.right_width = 0;
        this.right_height = 0;
        this.backColor = 0;
        init();
    }

    public IDCardNewIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mShowDrawRect = null;
        this.mDrawRect = null;
        this.mDrawPaint = null;
        this.mDrawRightPaint = null;
        this.IDCARD_RATIO = 1.5851852f;
        this.CONTENT_RATIO = 0.8f;
        this.SHOW_CONTENT_RATIO = (this.CONTENT_RATIO * 13.0f) / 16.0f;
        this.RIGHT_RATIO = 0.2f;
        this.rightRectf = null;
        this.mTmpRect = null;
        this.mTmpRect_test = null;
        this.right_width = 0;
        this.right_height = 0;
        this.backColor = 0;
        init();
    }

    public IDCardNewIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowDrawRect = null;
        this.mDrawRect = null;
        this.mDrawPaint = null;
        this.mDrawRightPaint = null;
        this.IDCARD_RATIO = 1.5851852f;
        this.CONTENT_RATIO = 0.8f;
        this.SHOW_CONTENT_RATIO = (this.CONTENT_RATIO * 13.0f) / 16.0f;
        this.RIGHT_RATIO = 0.2f;
        this.rightRectf = null;
        this.mTmpRect = null;
        this.mTmpRect_test = null;
        this.right_width = 0;
        this.right_height = 0;
        this.backColor = 0;
        init();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.right_width = (int) (size * this.RIGHT_RATIO);
        int i3 = this.right_width;
        float f = this.IDCARD_RATIO;
        this.right_height = (int) (i3 / f);
        int i4 = size2 >> 1;
        int i5 = size >> 1;
        int i6 = (int) ((size - i3) * this.CONTENT_RATIO);
        int i7 = (int) (i6 / f);
        Rect rect = this.mDrawRect;
        rect.left = i5 - (i6 / 2);
        rect.top = i4 - (i7 / 2);
        rect.right = i6 + rect.left;
        Rect rect2 = this.mDrawRect;
        rect2.bottom = i7 + rect2.top;
        int i8 = (int) ((size - this.right_width) * this.SHOW_CONTENT_RATIO);
        float f2 = i8;
        int i9 = (int) (f2 / this.IDCARD_RATIO);
        Rect rect3 = this.mShowDrawRect;
        rect3.left = (int) (i5 - (f2 / 2.0f));
        rect3.top = i4 - (i9 / 2);
        rect3.right = i8 + rect3.left;
        Rect rect4 = this.mShowDrawRect;
        rect4.bottom = i9 + rect4.top;
        this.rightRectf.top = this.mShowDrawRect.top;
        this.rightRectf.left = this.mDrawRect.right;
        RectF rectF = this.rightRectf;
        rectF.right = size - 20;
        rectF.bottom = (rectF.width() / this.IDCARD_RATIO) + this.rightRectf.top;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mDrawPaint.setStyle(Paint.Style.FILL);
        this.mDrawPaint.setColor(this.backColor);
        drawViewfinder(canvas);
    }

    public void setCardSideAndOrientation(boolean z, IDCardAttr.IDCardSide iDCardSide) {
        this.mIsVertical = z;
        this.mIdCardSide = iDCardSide;
    }

    private void drawViewfinder(Canvas canvas) {
        int i;
        this.mTmpRect.set(0, 0, getWidth(), this.mShowDrawRect.top);
        canvas.drawRect(this.mTmpRect, this.mDrawPaint);
        this.mTmpRect.set(0, this.mShowDrawRect.bottom, getWidth(), getHeight());
        canvas.drawRect(this.mTmpRect, this.mDrawPaint);
        this.mTmpRect.set(0, this.mShowDrawRect.top, this.mShowDrawRect.left, this.mShowDrawRect.bottom);
        canvas.drawRect(this.mTmpRect, this.mDrawPaint);
        this.mTmpRect.set(this.mShowDrawRect.right, this.mShowDrawRect.top, getWidth(), this.mShowDrawRect.bottom);
        canvas.drawRect(this.mTmpRect, this.mDrawPaint);
        this.mDrawPaint.setStyle(Paint.Style.STROKE);
        this.mDrawPaint.setColor(-10501934);
        this.mDrawPaint.setStrokeWidth(5.0f);
        int height = this.mShowDrawRect.height() / 16;
        canvas.drawLine(this.mShowDrawRect.left, this.mShowDrawRect.top, this.mShowDrawRect.left + height, this.mShowDrawRect.top, this.mDrawPaint);
        canvas.drawLine(this.mShowDrawRect.left, this.mShowDrawRect.top, this.mShowDrawRect.left, this.mShowDrawRect.top + height, this.mDrawPaint);
        canvas.drawLine(this.mShowDrawRect.right, this.mShowDrawRect.top, this.mShowDrawRect.right - height, this.mShowDrawRect.top, this.mDrawPaint);
        canvas.drawLine(this.mShowDrawRect.right, this.mShowDrawRect.top, this.mShowDrawRect.right, this.mShowDrawRect.top + height, this.mDrawPaint);
        canvas.drawLine(this.mShowDrawRect.left, this.mShowDrawRect.bottom, this.mShowDrawRect.left + height, this.mShowDrawRect.bottom, this.mDrawPaint);
        canvas.drawLine(this.mShowDrawRect.left, this.mShowDrawRect.bottom, this.mShowDrawRect.left, this.mShowDrawRect.bottom - height, this.mDrawPaint);
        canvas.drawLine(this.mShowDrawRect.right, this.mShowDrawRect.bottom, this.mShowDrawRect.right - height, this.mShowDrawRect.bottom, this.mDrawPaint);
        canvas.drawLine(this.mShowDrawRect.right, this.mShowDrawRect.bottom, this.mShowDrawRect.right, this.mShowDrawRect.bottom - height, this.mDrawPaint);
        if (this.mIdCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
            i = C5301R.C5302drawable.sfz_front;
        } else {
            i = this.mIdCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_BACK ? C5301R.C5302drawable.sfz_back : 0;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(getContext().getResources(), i);
        canvas.drawBitmap(decodeResource, new Rect(0, 0, decodeResource.getWidth(), decodeResource.getHeight()), new Rect(this.mShowDrawRect.left, this.mShowDrawRect.top, this.mShowDrawRect.left + this.mShowDrawRect.width(), this.mShowDrawRect.top + this.mShowDrawRect.height()), (Paint) null);
    }

    private void onDrawRight(Canvas canvas) {
        canvas.drawBitmap(this.rightBitmap, (Rect) null, this.rightRectf, (Paint) null);
        int i = this.right_width / 6;
        this.mDrawRightPaint.setTextSize((i * 4) / 5);
        canvas.drawText(this.rightText + "面", this.rightRectf.left, this.rightRectf.bottom + i, this.mDrawRightPaint);
        canvas.drawText("置于框内", this.rightRectf.left, this.rightRectf.bottom + ((float) (i * 2)), this.mDrawRightPaint);
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
