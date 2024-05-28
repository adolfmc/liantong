package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.math.BigDecimal;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CircleProgressAlpha extends View {
    private int CircleColor;
    private Bitmap bitmap;
    private int circleAlpha;
    private int currentValue;
    private int height;
    private int maxValue;
    private onProgressListener monProgress;
    private float paddingscale;
    private Paint paint;
    private int preAlpha;
    private int preColor;
    private int proAlpha;
    private int progressColor;
    private int radius;
    private Rect rec;
    RectF rectf;
    @Deprecated
    float scale;
    private int socktwidth;
    private int startAngle;
    private int textColor;
    private int textSize;
    private int value;
    private int width;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface onProgressListener {
        void onProgress(int i);
    }

    public CircleProgressAlpha(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.socktwidth = dp2px(6);
        this.paint = new Paint();
        this.rec = new Rect();
        this.value = 0;
        this.textSize = dp2px(70);
        this.scale = 0.15f;
        this.preColor = Color.parseColor("#ffe7e7e7");
        this.progressColor = Color.parseColor("#ffff7745");
        this.paddingscale = 0.8f;
        this.CircleColor = Color.parseColor("#ffffff");
        this.textColor = this.progressColor;
        this.startAngle = SubsamplingScaleImageView.ORIENTATION_270;
        this.rectf = new RectF();
        this.circleAlpha = 255;
        this.preAlpha = 255;
        this.proAlpha = 255;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.width = getWidth();
        int height = getHeight();
        this.height = height;
        int i = this.height;
        int i2 = this.width;
        if (i > i2) {
            height = i2;
        }
        this.radius = (int) ((height * this.paddingscale) / 2.0f);
        this.paint.setAntiAlias(true);
        this.paint.setColor(this.preColor);
        this.paint.setAlpha(this.preAlpha);
        this.paint.setStrokeWidth(this.socktwidth);
        this.paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(this.width / 2, this.height / 2, this.radius, this.paint);
        RectF rectF = this.rectf;
        int i3 = this.width;
        int i4 = this.radius;
        int i5 = this.height;
        rectF.set((i3 - (i4 * 2)) / 2.0f, (i5 - (i4 * 2)) / 2.0f, ((i3 - (i4 * 2)) / 2.0f) + (i4 * 2), ((i5 - (i4 * 2)) / 2.0f) + (i4 * 2));
        this.paint.setColor(this.progressColor);
        this.paint.setAlpha(this.proAlpha);
        canvas.drawArc(this.rectf, this.startAngle, this.value * 0.36f, false, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.CircleColor);
        this.paint.setAlpha(this.circleAlpha);
        canvas.drawCircle(this.width / 2, this.height / 2, this.radius - this.socktwidth, this.paint);
        if (this.bitmap != null) {
            RectF rectF2 = this.rectf;
            float width = (int) (this.rectf.width() * this.scale);
            float height2 = (int) (this.rectf.height() * this.scale);
            rectF2.set(rectF2.left + width, this.rectf.top + height2, this.rectf.right - width, this.rectf.bottom - height2);
            canvas.drawBitmap(this.bitmap, (Rect) null, this.rectf, (Paint) null);
        }
        super.onDraw(canvas);
    }

    public int dp2px(int i) {
        return (int) ((getResources().getDisplayMetrics().density * i) + 0.5d);
    }

    public void setValue(int i) {
        if (i > 1000) {
            return;
        }
        this.value = i;
        invalidate();
        onProgressListener onprogresslistener = this.monProgress;
        if (onprogresslistener != null) {
            onprogresslistener.onProgress(i);
        }
    }

    public void setMaxValue(int i) {
        this.maxValue = i;
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public void setCurrentValue(int i) {
        int i2 = this.maxValue;
        if (i2 <= 0) {
            setValue(0);
            return;
        }
        int i3 = i % i2;
        this.currentValue = i3;
        int intValue = new BigDecimal(i3).divide(new BigDecimal(this.maxValue), 3, 4).multiply(new BigDecimal(1000)).intValue();
        if (intValue <= 990) {
            setValue(intValue);
        } else {
            setValue(1000);
        }
    }

    public CircleProgressAlpha setProdressWidth(int i) {
        this.socktwidth = i;
        return this;
    }

    public CircleProgressAlpha setTextSize(int i) {
        this.textSize = i;
        return this;
    }

    public CircleProgressAlpha setTextColor(int i) {
        this.textColor = i;
        return this;
    }

    public CircleProgressAlpha setPreProgress(int i) {
        this.preColor = i;
        return this;
    }

    public CircleProgressAlpha setProgress(int i) {
        this.progressColor = i;
        return this;
    }

    public CircleProgressAlpha setCircleBackgroud(int i) {
        this.CircleColor = i;
        return this;
    }

    public CircleProgressAlpha setPaddingscale(float f) {
        this.paddingscale = f;
        return this;
    }

    public CircleProgressAlpha setStartAngle(int i) {
        this.startAngle = i;
        return this;
    }

    public void setCircleAlpha(int i) {
        this.circleAlpha = i;
    }

    public void setPreAlpha(int i) {
        this.preAlpha = i;
    }

    public void setProAlpha(int i) {
        this.proAlpha = i;
    }
}
