package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.p086v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.sinovatech.unicom.p318ui.C9718R;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RoundImageView extends AppCompatImageView {
    private static final int BODER_RADIUS_DEFAULT = 10;
    private static final String STATE_BORDER_RADIUS = "state_border_radius";
    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    private Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private int mBorderRadius;
    private Matrix mMatrix;
    private int mRadius;
    private RectF mRoundRect;
    private int mWidth;
    private int type;

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBitmapPaint.setAntiAlias(true);
        if (Build.VERSION.SDK_INT < 23) {
            setLayerType(1, this.mBitmapPaint);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.RoundImageView);
        this.mBorderRadius = obtainStyledAttributes.getDimensionPixelSize(0, (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics()));
        this.type = obtainStyledAttributes.getInt(1, 0);
        obtainStyledAttributes.recycle();
    }

    public RoundImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.type == 0) {
            this.mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            int i3 = this.mWidth;
            this.mRadius = i3 / 2;
            setMeasuredDimension(i3, i3);
        }
    }

    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap drawableToBitamp = drawableToBitamp(drawable);
        this.mBitmapShader = new BitmapShader(drawableToBitamp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int i = this.type;
        float f = 1.0f;
        if (i == 0) {
            f = (this.mWidth * 1.0f) / Math.min(drawableToBitamp.getWidth(), drawableToBitamp.getHeight());
        } else if (i == 1 && (drawableToBitamp.getWidth() != getWidth() || drawableToBitamp.getHeight() != getHeight())) {
            f = Math.max((getWidth() * 1.0f) / drawableToBitamp.getWidth(), (getHeight() * 1.0f) / drawableToBitamp.getHeight());
        }
        this.mMatrix.setScale(f, f);
        this.mBitmapShader.setLocalMatrix(this.mMatrix);
        this.mBitmapPaint.setShader(this.mBitmapShader);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        setUpShader();
        if (this.type == 1) {
            RectF rectF = this.mRoundRect;
            int i = this.mBorderRadius;
            canvas.drawRoundRect(rectF, i, i, this.mBitmapPaint);
            return;
        }
        int i2 = this.mRadius;
        canvas.drawCircle(i2, i2, i2, this.mBitmapPaint);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.type == 1) {
            this.mRoundRect = new RectF(0.0f, 0.0f, i, i2);
        }
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, this.type);
        bundle.putInt(STATE_BORDER_RADIUS, this.mBorderRadius);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.mBorderRadius = bundle.getInt(STATE_BORDER_RADIUS);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setBorderRadius(int i) {
        int dp2px = dp2px(i);
        if (this.mBorderRadius != dp2px) {
            this.mBorderRadius = dp2px;
            invalidate();
        }
    }

    public void setType(int i) {
        if (this.type != i) {
            this.type = i;
            int i2 = this.type;
            if (i2 != 1 && i2 != 0) {
                this.type = 0;
            }
            requestLayout();
        }
    }

    public int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }
}
