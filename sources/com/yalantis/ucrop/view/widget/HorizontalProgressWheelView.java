package com.yalantis.ucrop.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.p083v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.yalantis.ucrop.C11657R;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class HorizontalProgressWheelView extends View {
    private final Rect mCanvasClipBounds;
    private float mLastTouchedPosition;
    private int mMiddleLineColor;
    private int mProgressLineHeight;
    private int mProgressLineMargin;
    private Paint mProgressLinePaint;
    private int mProgressLineWidth;
    private Paint mProgressMiddleLinePaint;
    private boolean mScrollStarted;
    private ScrollingListener mScrollingListener;
    private float mTotalScrollDistance;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface ScrollingListener {
        void onScroll(float f, float f2);

        void onScrollEnd();

        void onScrollStart();
    }

    public HorizontalProgressWheelView(Context context) {
        this(context, null);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCanvasClipBounds = new Rect();
        init();
    }

    @TargetApi(21)
    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCanvasClipBounds = new Rect();
    }

    public void setScrollingListener(ScrollingListener scrollingListener) {
        this.mScrollingListener = scrollingListener;
    }

    public void setMiddleLineColor(@ColorInt int i) {
        this.mMiddleLineColor = i;
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.mLastTouchedPosition = motionEvent.getX();
                break;
            case 1:
                ScrollingListener scrollingListener = this.mScrollingListener;
                if (scrollingListener != null) {
                    this.mScrollStarted = false;
                    scrollingListener.onScrollEnd();
                    break;
                }
                break;
            case 2:
                float x = motionEvent.getX() - this.mLastTouchedPosition;
                if (x != 0.0f) {
                    if (!this.mScrollStarted) {
                        this.mScrollStarted = true;
                        ScrollingListener scrollingListener2 = this.mScrollingListener;
                        if (scrollingListener2 != null) {
                            scrollingListener2.onScrollStart();
                        }
                    }
                    onScrollEvent(motionEvent, x);
                    break;
                }
                break;
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.getClipBounds(this.mCanvasClipBounds);
        int width = this.mCanvasClipBounds.width();
        int i = this.mProgressLineWidth;
        int i2 = this.mProgressLineMargin;
        int i3 = width / (i + i2);
        float f = this.mTotalScrollDistance % (i2 + i);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i3 / 4;
            if (i4 < i5) {
                this.mProgressLinePaint.setAlpha((int) ((i4 / i5) * 255.0f));
            } else if (i4 > (i3 * 3) / 4) {
                this.mProgressLinePaint.setAlpha((int) (((i3 - i4) / i5) * 255.0f));
            } else {
                this.mProgressLinePaint.setAlpha(255);
            }
            float f2 = -f;
            canvas.drawLine(this.mCanvasClipBounds.left + f2 + ((this.mProgressLineWidth + this.mProgressLineMargin) * i4), this.mCanvasClipBounds.centerY() - (this.mProgressLineHeight / 4.0f), f2 + this.mCanvasClipBounds.left + ((this.mProgressLineWidth + this.mProgressLineMargin) * i4), this.mCanvasClipBounds.centerY() + (this.mProgressLineHeight / 4.0f), this.mProgressLinePaint);
        }
        canvas.drawLine(this.mCanvasClipBounds.centerX(), this.mCanvasClipBounds.centerY() - (this.mProgressLineHeight / 2.0f), this.mCanvasClipBounds.centerX(), (this.mProgressLineHeight / 2.0f) + this.mCanvasClipBounds.centerY(), this.mProgressMiddleLinePaint);
    }

    private void onScrollEvent(MotionEvent motionEvent, float f) {
        this.mTotalScrollDistance -= f;
        postInvalidate();
        this.mLastTouchedPosition = motionEvent.getX();
        ScrollingListener scrollingListener = this.mScrollingListener;
        if (scrollingListener != null) {
            scrollingListener.onScroll(-f, this.mTotalScrollDistance);
        }
    }

    private void init() {
        this.mMiddleLineColor = ContextCompat.getColor(getContext(), C11657R.C11658color.ucrop_color_widget_rotate_mid_line);
        this.mProgressLineWidth = getContext().getResources().getDimensionPixelSize(C11657R.dimen.ucrop_width_horizontal_wheel_progress_line);
        this.mProgressLineHeight = getContext().getResources().getDimensionPixelSize(C11657R.dimen.ucrop_height_horizontal_wheel_progress_line);
        this.mProgressLineMargin = getContext().getResources().getDimensionPixelSize(C11657R.dimen.ucrop_margin_horizontal_wheel_progress_line);
        this.mProgressLinePaint = new Paint(1);
        this.mProgressLinePaint.setStyle(Paint.Style.STROKE);
        this.mProgressLinePaint.setStrokeWidth(this.mProgressLineWidth);
        this.mProgressLinePaint.setColor(getResources().getColor(C11657R.C11658color.ucrop_color_progress_wheel_line));
        this.mProgressMiddleLinePaint = new Paint(this.mProgressLinePaint);
        this.mProgressMiddleLinePaint.setColor(this.mMiddleLineColor);
        this.mProgressMiddleLinePaint.setStrokeCap(Paint.Cap.ROUND);
        this.mProgressMiddleLinePaint.setStrokeWidth(getContext().getResources().getDimensionPixelSize(C11657R.dimen.ucrop_width_middle_wheel_progress_line));
    }
}
