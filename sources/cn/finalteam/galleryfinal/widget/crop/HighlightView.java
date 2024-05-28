package cn.finalteam.galleryfinal.widget.crop;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class HighlightView {
    private static final int DEFAULT_HIGHLIGHT_COLOR = -13388315;
    public static final int GROW_BOTTOM_EDGE = 16;
    public static final int GROW_LEFT_EDGE = 2;
    public static final int GROW_NONE = 1;
    public static final int GROW_RIGHT_EDGE = 4;
    public static final int GROW_TOP_EDGE = 8;
    private static final float HANDLE_RADIUS_DP = 12.0f;
    public static final int MOVE = 32;
    private static final float OUTLINE_DP = 2.0f;
    RectF cropRect;
    Rect drawRect;
    private float handleRadius;
    private int highlightColor;
    private RectF imageRect;
    private float initialAspectRatio;
    private boolean isFocused;
    private boolean maintainAspectRatio;
    Matrix matrix;
    private float outlineWidth;
    private boolean showCircle;
    private boolean showThirds;
    private View viewContext;
    private final Paint outsidePaint = new Paint();
    private final Paint outlinePaint = new Paint();
    private final Paint handlePaint = new Paint();
    private ModifyMode modifyMode = ModifyMode.None;
    private HandleMode handleMode = HandleMode.Changing;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum HandleMode {
        Changing,
        Always,
        Never
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    enum ModifyMode {
        None,
        Move,
        Grow
    }

    public HighlightView(View view, int i) {
        this.viewContext = view;
        initStyles(i);
    }

    private void initStyles(int i) {
        this.showThirds = true;
        this.showCircle = false;
        this.handleMode = HandleMode.Always;
        this.highlightColor = i;
    }

    public void setup(Matrix matrix, Rect rect, RectF rectF, boolean z) {
        this.matrix = new Matrix(matrix);
        this.cropRect = rectF;
        this.imageRect = new RectF(rect);
        this.maintainAspectRatio = z;
        this.initialAspectRatio = this.cropRect.width() / this.cropRect.height();
        this.drawRect = computeLayout();
        this.outsidePaint.setARGB(125, 50, 50, 50);
        this.outlinePaint.setStyle(Paint.Style.STROKE);
        this.outlinePaint.setAntiAlias(true);
        this.outlineWidth = dpToPx(OUTLINE_DP);
        this.handlePaint.setColor(this.highlightColor);
        this.handlePaint.setStyle(Paint.Style.FILL);
        this.handlePaint.setAntiAlias(true);
        this.handleRadius = dpToPx(HANDLE_RADIUS_DP);
        this.modifyMode = ModifyMode.None;
    }

    private float dpToPx(float f) {
        return f * this.viewContext.getResources().getDisplayMetrics().density;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void draw(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        this.outlinePaint.setStrokeWidth(this.outlineWidth);
        if (!hasFocus()) {
            this.outlinePaint.setColor(-16777216);
            canvas.drawRect(this.drawRect, this.outlinePaint);
            return;
        }
        Rect rect = new Rect();
        this.viewContext.getDrawingRect(rect);
        path.addRect(new RectF(this.drawRect), Path.Direction.CW);
        this.outlinePaint.setColor(this.highlightColor);
        if (isClipPathSupported(canvas)) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawRect(rect, this.outsidePaint);
        } else {
            drawOutsideFallback(canvas);
        }
        canvas.restore();
        canvas.drawPath(path, this.outlinePaint);
        if (this.showThirds) {
            drawThirds(canvas);
        }
        if (this.showCircle) {
            drawCircle(canvas);
        }
        if (this.handleMode == HandleMode.Always || (this.handleMode == HandleMode.Changing && this.modifyMode == ModifyMode.Grow)) {
            drawHandles(canvas);
        }
    }

    private void drawOutsideFallback(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), this.drawRect.top, this.outsidePaint);
        canvas.drawRect(0.0f, this.drawRect.bottom, canvas.getWidth(), canvas.getHeight(), this.outsidePaint);
        canvas.drawRect(0.0f, this.drawRect.top, this.drawRect.left, this.drawRect.bottom, this.outsidePaint);
        canvas.drawRect(this.drawRect.right, this.drawRect.top, canvas.getWidth(), this.drawRect.bottom, this.outsidePaint);
    }

    @SuppressLint({"NewApi"})
    private boolean isClipPathSupported(Canvas canvas) {
        if (Build.VERSION.SDK_INT == 17) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14 || Build.VERSION.SDK_INT > 15) {
            return true;
        }
        return !canvas.isHardwareAccelerated();
    }

    private void drawHandles(Canvas canvas) {
        int i = this.drawRect.left + ((this.drawRect.right - this.drawRect.left) / 2);
        float f = this.drawRect.top + ((this.drawRect.bottom - this.drawRect.top) / 2);
        canvas.drawCircle(this.drawRect.left, f, this.handleRadius, this.handlePaint);
        float f2 = i;
        canvas.drawCircle(f2, this.drawRect.top, this.handleRadius, this.handlePaint);
        canvas.drawCircle(this.drawRect.right, f, this.handleRadius, this.handlePaint);
        canvas.drawCircle(f2, this.drawRect.bottom, this.handleRadius, this.handlePaint);
    }

    private void drawThirds(Canvas canvas) {
        this.outlinePaint.setStrokeWidth(1.0f);
        float f = (this.drawRect.right - this.drawRect.left) / 3;
        float f2 = (this.drawRect.bottom - this.drawRect.top) / 3;
        canvas.drawLine(this.drawRect.left + f, this.drawRect.top, this.drawRect.left + f, this.drawRect.bottom, this.outlinePaint);
        float f3 = f * OUTLINE_DP;
        canvas.drawLine(this.drawRect.left + f3, this.drawRect.top, this.drawRect.left + f3, this.drawRect.bottom, this.outlinePaint);
        canvas.drawLine(this.drawRect.left, this.drawRect.top + f2, this.drawRect.right, this.drawRect.top + f2, this.outlinePaint);
        float f4 = f2 * OUTLINE_DP;
        canvas.drawLine(this.drawRect.left, this.drawRect.top + f4, this.drawRect.right, this.drawRect.top + f4, this.outlinePaint);
    }

    private void drawCircle(Canvas canvas) {
        this.outlinePaint.setStrokeWidth(1.0f);
        canvas.drawOval(new RectF(this.drawRect), this.outlinePaint);
    }

    public void setMode(ModifyMode modifyMode) {
        if (modifyMode != this.modifyMode) {
            this.modifyMode = modifyMode;
            this.viewContext.invalidate();
        }
    }

    public int getHit(float f, float f2) {
        Rect computeLayout = computeLayout();
        boolean z = false;
        boolean z2 = f2 >= ((float) computeLayout.top) - 20.0f && f2 < ((float) computeLayout.bottom) + 20.0f;
        if (f >= computeLayout.left - 20.0f && f < computeLayout.right + 20.0f) {
            z = true;
        }
        int i = (Math.abs(((float) computeLayout.left) - f) >= 20.0f || !z2) ? 1 : 3;
        if (Math.abs(computeLayout.right - f) < 20.0f && z2) {
            i |= 4;
        }
        if (Math.abs(computeLayout.top - f2) < 20.0f && z) {
            i |= 8;
        }
        if (Math.abs(computeLayout.bottom - f2) < 20.0f && z) {
            i |= 16;
        }
        if (i == 1 && computeLayout.contains((int) f, (int) f2)) {
            return 32;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleMotion(int i, float f, float f2) {
        Rect computeLayout = computeLayout();
        if (i == 32) {
            moveBy(f * (this.cropRect.width() / computeLayout.width()), f2 * (this.cropRect.height() / computeLayout.height()));
            return;
        }
        if ((i & 6) == 0) {
            f = 0.0f;
        }
        if ((i & 24) == 0) {
            f2 = 0.0f;
        }
        growBy(((i & 2) != 0 ? -1 : 1) * f * (this.cropRect.width() / computeLayout.width()), ((i & 8) == 0 ? 1 : -1) * f2 * (this.cropRect.height() / computeLayout.height()));
    }

    void moveBy(float f, float f2) {
        Rect rect = new Rect(this.drawRect);
        this.cropRect.offset(f, f2);
        this.cropRect.offset(Math.max(0.0f, this.imageRect.left - this.cropRect.left), Math.max(0.0f, this.imageRect.top - this.cropRect.top));
        this.cropRect.offset(Math.min(0.0f, this.imageRect.right - this.cropRect.right), Math.min(0.0f, this.imageRect.bottom - this.cropRect.bottom));
        this.drawRect = computeLayout();
        rect.union(this.drawRect);
        float f3 = this.handleRadius;
        rect.inset(-((int) f3), -((int) f3));
        this.viewContext.invalidate(rect);
    }

    void growBy(float f, float f2) {
        if (this.maintainAspectRatio) {
            if (f != 0.0f) {
                f2 = f / this.initialAspectRatio;
            } else if (f2 != 0.0f) {
                f = this.initialAspectRatio * f2;
            }
        }
        RectF rectF = new RectF(this.cropRect);
        if (f > 0.0f && rectF.width() + (f * OUTLINE_DP) > this.imageRect.width()) {
            f = (this.imageRect.width() - rectF.width()) / OUTLINE_DP;
            if (this.maintainAspectRatio) {
                f2 = f / this.initialAspectRatio;
            }
        }
        if (f2 > 0.0f && rectF.height() + (f2 * OUTLINE_DP) > this.imageRect.height()) {
            f2 = (this.imageRect.height() - rectF.height()) / OUTLINE_DP;
            if (this.maintainAspectRatio) {
                f = this.initialAspectRatio * f2;
            }
        }
        rectF.inset(-f, -f2);
        if (rectF.width() < 25.0f) {
            rectF.inset((-(25.0f - rectF.width())) / OUTLINE_DP, 0.0f);
        }
        float f3 = this.maintainAspectRatio ? 25.0f / this.initialAspectRatio : 25.0f;
        if (rectF.height() < f3) {
            rectF.inset(0.0f, (-(f3 - rectF.height())) / OUTLINE_DP);
        }
        if (rectF.left < this.imageRect.left) {
            rectF.offset(this.imageRect.left - rectF.left, 0.0f);
        } else if (rectF.right > this.imageRect.right) {
            rectF.offset(-(rectF.right - this.imageRect.right), 0.0f);
        }
        if (rectF.top < this.imageRect.top) {
            rectF.offset(0.0f, this.imageRect.top - rectF.top);
        } else if (rectF.bottom > this.imageRect.bottom) {
            rectF.offset(0.0f, -(rectF.bottom - this.imageRect.bottom));
        }
        this.cropRect.set(rectF);
        this.drawRect = computeLayout();
        this.viewContext.invalidate();
    }

    public Rect getScaledCropRect(float f) {
        return new Rect((int) (this.cropRect.left * f), (int) (this.cropRect.top * f), (int) (this.cropRect.right * f), (int) (this.cropRect.bottom * f));
    }

    private Rect computeLayout() {
        RectF rectF = new RectF(this.cropRect.left, this.cropRect.top, this.cropRect.right, this.cropRect.bottom);
        this.matrix.mapRect(rectF);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public void invalidate() {
        this.drawRect = computeLayout();
    }

    public boolean hasFocus() {
        return this.isFocused;
    }

    public void setFocus(boolean z) {
        this.isFocused = z;
    }
}
