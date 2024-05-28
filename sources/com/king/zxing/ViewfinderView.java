package com.king.zxing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.p083v4.content.ContextCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class ViewfinderView extends View {
    private static final int CURRENT_POINT_OPACITY = 160;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 20;
    private int cornerColor;
    private int cornerRectHeight;
    private int cornerRectWidth;
    private Rect frame;
    private int frameColor;
    private int frameHeight;
    private int frameLineWidth;
    private float frameRatio;
    private int frameWidth;
    private int gridColumn;
    private int gridHeight;
    private boolean isShowResultPoint;
    private String labelText;
    private int labelTextColor;
    private TextLocation labelTextLocation;
    private float labelTextPadding;
    private float labelTextSize;
    private int laserColor;
    private LaserStyle laserStyle;
    private List<ResultPoint> lastPossibleResultPoints;
    private int maskColor;
    private Paint paint;
    private List<ResultPoint> possibleResultPoints;
    private int resultPointColor;
    private int scannerAnimationDelay;
    public int scannerEnd;
    private int scannerLineHeight;
    private int scannerLineMoveDistance;
    public int scannerStart;
    private int screenHeight;
    private int screenWidth;
    private TextPaint textPaint;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum LaserStyle {
        NONE(0),
        LINE(1),
        GRID(2);
        
        private int mValue;

        LaserStyle(int i) {
            this.mValue = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LaserStyle getFromInt(int i) {
            LaserStyle[] values;
            for (LaserStyle laserStyle : values()) {
                if (laserStyle.mValue == i) {
                    return laserStyle;
                }
            }
            return LINE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum TextLocation {
        TOP(0),
        BOTTOM(1);
        
        private int mValue;

        TextLocation(int i) {
            this.mValue = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static TextLocation getFromInt(int i) {
            TextLocation[] values;
            for (TextLocation textLocation : values()) {
                if (textLocation.mValue == i) {
                    return textLocation;
                }
            }
            return TOP;
        }
    }

    public ViewfinderView(Context context) {
        this(context, null);
    }

    public ViewfinderView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewfinderView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scannerStart = 0;
        this.scannerEnd = 0;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5188R.styleable.ViewfinderView);
        this.maskColor = obtainStyledAttributes.getColor(C5188R.styleable.ViewfinderView_maskColor, ContextCompat.getColor(context, C5188R.C5189color.viewfinder_mask));
        this.frameColor = obtainStyledAttributes.getColor(C5188R.styleable.ViewfinderView_frameColor, ContextCompat.getColor(context, C5188R.C5189color.viewfinder_frame));
        this.frameColor = -2130706433;
        this.cornerColor = obtainStyledAttributes.getColor(C5188R.styleable.ViewfinderView_cornerColor, ContextCompat.getColor(context, C5188R.C5189color.viewfinder_corner));
        this.cornerColor = 16777215;
        this.laserColor = obtainStyledAttributes.getColor(C5188R.styleable.ViewfinderView_laserColor, ContextCompat.getColor(context, C5188R.C5189color.viewfinder_laser));
        this.resultPointColor = obtainStyledAttributes.getColor(C5188R.styleable.ViewfinderView_resultPointColor, ContextCompat.getColor(context, C5188R.C5189color.viewfinder_result_point_color));
        this.labelText = obtainStyledAttributes.getString(C5188R.styleable.ViewfinderView_labelText);
        this.labelTextColor = obtainStyledAttributes.getColor(C5188R.styleable.ViewfinderView_labelTextColor, ContextCompat.getColor(context, C5188R.C5189color.viewfinder_text_color));
        this.labelTextSize = obtainStyledAttributes.getDimension(C5188R.styleable.ViewfinderView_labelTextSize, TypedValue.applyDimension(2, 14.0f, getResources().getDisplayMetrics()));
        this.labelTextPadding = obtainStyledAttributes.getDimension(C5188R.styleable.ViewfinderView_labelTextPadding, TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
        this.labelTextLocation = TextLocation.getFromInt(obtainStyledAttributes.getInt(C5188R.styleable.ViewfinderView_labelTextLocation, 0));
        this.isShowResultPoint = obtainStyledAttributes.getBoolean(C5188R.styleable.ViewfinderView_showResultPoint, false);
        this.frameWidth = obtainStyledAttributes.getDimensionPixelSize(C5188R.styleable.ViewfinderView_frameWidth, 0);
        this.frameHeight = obtainStyledAttributes.getDimensionPixelSize(C5188R.styleable.ViewfinderView_frameHeight, 0);
        this.laserStyle = LaserStyle.getFromInt(obtainStyledAttributes.getInt(C5188R.styleable.ViewfinderView_laserStyle, LaserStyle.LINE.mValue));
        this.gridColumn = obtainStyledAttributes.getInt(C5188R.styleable.ViewfinderView_gridColumn, 20);
        this.gridHeight = (int) obtainStyledAttributes.getDimension(C5188R.styleable.ViewfinderView_gridHeight, TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.cornerRectWidth = (int) obtainStyledAttributes.getDimension(C5188R.styleable.ViewfinderView_cornerRectWidth, TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        this.cornerRectHeight = (int) obtainStyledAttributes.getDimension(C5188R.styleable.ViewfinderView_cornerRectHeight, TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.scannerLineMoveDistance = (int) obtainStyledAttributes.getDimension(C5188R.styleable.ViewfinderView_scannerLineMoveDistance, TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.scannerLineHeight = (int) obtainStyledAttributes.getDimension(C5188R.styleable.ViewfinderView_scannerLineHeight, TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics()));
        this.frameLineWidth = (int) obtainStyledAttributes.getDimension(C5188R.styleable.ViewfinderView_frameLineWidth, TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics()));
        this.scannerAnimationDelay = obtainStyledAttributes.getInteger(C5188R.styleable.ViewfinderView_scannerAnimationDelay, 15);
        this.frameRatio = obtainStyledAttributes.getFloat(C5188R.styleable.ViewfinderView_frameRatio, 0.625f);
        obtainStyledAttributes.recycle();
        this.paint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.possibleResultPoints = new ArrayList(5);
        this.lastPossibleResultPoints = null;
        this.screenWidth = getDisplayMetrics().widthPixels;
        this.screenHeight = getDisplayMetrics().heightPixels;
        int min = (int) (Math.min(this.screenWidth, this.screenHeight) * this.frameRatio);
        int i = this.frameWidth;
        if (i <= 0 || i > this.screenWidth) {
            this.frameWidth = min;
        }
        int i2 = this.frameHeight;
        if (i2 <= 0 || i2 > this.screenHeight) {
            this.frameHeight = min;
        }
    }

    private DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    public void setLabelText(String str) {
        this.labelText = str;
    }

    public void setLabelTextColor(@ColorInt int i) {
        this.labelTextColor = i;
    }

    public void setLabelTextColorResource(@ColorRes int i) {
        this.labelTextColor = ContextCompat.getColor(getContext(), i);
    }

    public void setLabelTextSize(float f) {
        this.labelTextSize = f;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingLeft = (((this.screenWidth - this.frameWidth) / 2) + getPaddingLeft()) - getPaddingRight();
        int paddingTop = (((this.screenHeight - this.frameHeight) / 2) + getPaddingTop()) - getPaddingBottom();
        this.frame = new Rect(paddingLeft, paddingTop, this.frameWidth + paddingLeft, this.frameHeight + paddingTop);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.frame == null) {
            return;
        }
        if (this.scannerStart == 0 || this.scannerEnd == 0) {
            this.scannerStart = this.frame.top;
            this.scannerEnd = this.frame.bottom - this.scannerLineHeight;
        }
        drawExterior(canvas, this.frame, canvas.getWidth(), canvas.getHeight());
        drawLaserScanner(canvas, this.frame);
        drawFrame(canvas, this.frame);
        drawCorner(canvas, this.frame);
        drawTextInfo(canvas, this.frame);
        drawResultPoint(canvas, this.frame);
        postInvalidateDelayed(this.scannerAnimationDelay, this.frame.left - 20, this.frame.top - 20, this.frame.right + 20, this.frame.bottom + 20);
    }

    private void drawTextInfo(Canvas canvas, Rect rect) {
        if (TextUtils.isEmpty(this.labelText)) {
            return;
        }
        this.textPaint.setColor(this.labelTextColor);
        this.textPaint.setTextSize(this.labelTextSize);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        StaticLayout staticLayout = new StaticLayout(this.labelText, this.textPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        if (this.labelTextLocation == TextLocation.BOTTOM) {
            canvas.translate(rect.left + (rect.width() / 2), rect.bottom + this.labelTextPadding);
            staticLayout.draw(canvas);
            return;
        }
        canvas.translate(rect.left + (rect.width() / 2), (rect.top - this.labelTextPadding) - staticLayout.getHeight());
        staticLayout.draw(canvas);
    }

    private void drawCorner(Canvas canvas, Rect rect) {
        canvas.drawRect(rect.left, rect.top, rect.left + this.cornerRectWidth, rect.top + this.cornerRectHeight, this.paint);
        canvas.drawRect(rect.left, rect.top, rect.left + this.cornerRectHeight, rect.top + this.cornerRectWidth, this.paint);
        canvas.drawRect(rect.right - this.cornerRectWidth, rect.top, rect.right, rect.top + this.cornerRectHeight, this.paint);
        canvas.drawRect(rect.right - this.cornerRectHeight, rect.top, rect.right, rect.top + this.cornerRectWidth, this.paint);
        canvas.drawRect(rect.left, rect.bottom - this.cornerRectWidth, rect.left + this.cornerRectHeight, rect.bottom, this.paint);
        canvas.drawRect(rect.left, rect.bottom - this.cornerRectHeight, rect.left + this.cornerRectWidth, rect.bottom, this.paint);
        canvas.drawRect(rect.right - this.cornerRectWidth, rect.bottom - this.cornerRectHeight, rect.right, rect.bottom, this.paint);
        canvas.drawRect(rect.right - this.cornerRectHeight, rect.bottom - this.cornerRectWidth, rect.right, rect.bottom, this.paint);
    }

    private void drawLaserScanner(Canvas canvas, Rect rect) {
        if (this.laserStyle != null) {
            this.paint.setColor(this.laserColor);
            switch (this.laserStyle) {
                case LINE:
                    drawLineScanner(canvas, rect);
                    break;
                case GRID:
                    drawGridScanner(canvas, rect);
                    break;
            }
            this.paint.setShader(null);
        }
    }

    private void drawLineScanner(Canvas canvas, Rect rect) {
        this.paint.setShader(new LinearGradient(rect.left, this.scannerStart, rect.left, this.scannerStart + this.scannerLineHeight, shadeColor(this.laserColor), this.laserColor, Shader.TileMode.MIRROR));
        if (this.scannerStart <= this.scannerEnd) {
            int i = rect.right;
            int i2 = this.scannerLineHeight;
            canvas.drawOval(new RectF(rect.left + (this.scannerLineHeight * 2), this.scannerStart, i - (i2 * 2), this.scannerStart + i2), this.paint);
            this.scannerStart += this.scannerLineMoveDistance;
            return;
        }
        this.scannerStart = rect.top;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0087, code lost:
        if (r0 > r2) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0063 A[LOOP:0: B:9:0x005f->B:11:0x0063, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0097 A[LOOP:1: B:18:0x0090->B:20:0x0097, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b1 A[EDGE_INSN: B:27:0x00b1->B:21:0x00b1 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawGridScanner(android.graphics.Canvas r14, android.graphics.Rect r15) {
        /*
            Method dump skipped, instructions count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.king.zxing.ViewfinderView.drawGridScanner(android.graphics.Canvas, android.graphics.Rect):void");
    }

    public int shadeColor(int i) {
        String hexString = Integer.toHexString(i);
        return Integer.valueOf("01" + hexString.substring(2), 16).intValue();
    }

    private void drawFrame(Canvas canvas, Rect rect) {
        this.paint.setColor(this.frameColor);
        canvas.drawRect(rect.left, rect.top, rect.right, rect.top + this.frameLineWidth, this.paint);
        canvas.drawRect(rect.left, rect.top, rect.left + this.frameLineWidth, rect.bottom, this.paint);
        canvas.drawRect(rect.right - this.frameLineWidth, rect.top, rect.right, rect.bottom, this.paint);
        canvas.drawRect(rect.left, rect.bottom - this.frameLineWidth, rect.right, rect.bottom, this.paint);
    }

    private void drawExterior(Canvas canvas, Rect rect, int i, int i2) {
        this.paint.setColor(this.maskColor);
        float f = i;
        canvas.drawRect(0.0f, 0.0f, f, rect.top, this.paint);
        canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom, this.paint);
        canvas.drawRect(rect.right, rect.top, f, rect.bottom, this.paint);
        canvas.drawRect(0.0f, rect.bottom, f, i2, this.paint);
    }

    private void drawResultPoint(Canvas canvas, Rect rect) {
        if (this.isShowResultPoint) {
            List<ResultPoint> list = this.possibleResultPoints;
            List<ResultPoint> list2 = this.lastPossibleResultPoints;
            if (list.isEmpty()) {
                this.lastPossibleResultPoints = null;
            } else {
                this.possibleResultPoints = new ArrayList(5);
                this.lastPossibleResultPoints = list;
                this.paint.setAlpha(160);
                this.paint.setColor(this.resultPointColor);
                synchronized (list) {
                    for (ResultPoint resultPoint : list) {
                        canvas.drawCircle(resultPoint.getX(), resultPoint.getY(), 10.0f, this.paint);
                    }
                }
            }
            if (list2 != null) {
                this.paint.setAlpha(80);
                this.paint.setColor(this.resultPointColor);
                synchronized (list2) {
                    for (ResultPoint resultPoint2 : list2) {
                        canvas.drawCircle(resultPoint2.getX(), resultPoint2.getY(), 10.0f, this.paint);
                    }
                }
            }
        }
    }

    public void drawViewfinder() {
        invalidate();
    }

    public boolean isShowResultPoint() {
        return this.isShowResultPoint;
    }

    public void setLaserStyle(LaserStyle laserStyle) {
        this.laserStyle = laserStyle;
    }

    public void setShowResultPoint(boolean z) {
        this.isShowResultPoint = z;
    }

    public void addPossibleResultPoint(ResultPoint resultPoint) {
        if (this.isShowResultPoint) {
            List<ResultPoint> list = this.possibleResultPoints;
            synchronized (list) {
                list.add(resultPoint);
                int size = list.size();
                if (size > 20) {
                    list.subList(0, size - 10).clear();
                }
            }
        }
    }
}
