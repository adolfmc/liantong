package com.sinovatech.unicom.basic.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WaveView extends View {
    private static final float DEFAULT_AMPLITUDE_RATIO = 0.1f;
    private static final float DEFAULT_WATER_LEVEL_RATIO = 0.5f;
    private static final float DEFAULT_WAVE_HSARDEHEIGHT_RATIO = 0.35f;
    private static final float DEFAULT_WAVE_LENGTH_RATIO = 1.0f;
    private static final int DEFAULT_WAVE_PROGRESS_VALUE = 100;
    private static final float DEFAULT_WAVE_SHIFT_RATIO = 0.0f;
    private static final int DEFAULT_WAVE_SPEED = 8000;
    private Bitmap bitmapBuffer;
    private long count;
    private float mAmplitudeRatio;
    private AnimatorSet mAnimatorSet;
    private int mCanvasHeight;
    private int mCanvasSize;
    private int mCanvasWidth;
    private Context mContext;
    private float mDefaultWaterLevel;
    private int mProgressValue;
    private Matrix mShaderMatrix;
    private float mWaterLevelRatio;
    private int mWaveBgColor;
    private int mWaveColor;
    private Paint mWavePaint;
    private BitmapShader mWaveShader;
    private float mWaveShiftRatio;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mWaveColor = 436207615;
        this.mWaveBgColor = 0;
        this.mWaterLevelRatio = 1.0f;
        this.mWaveShiftRatio = 0.0f;
        this.mProgressValue = 100;
        this.count = 1L;
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.mContext = context;
        this.mShaderMatrix = new Matrix();
        this.mWavePaint = new Paint();
        this.mWavePaint.setAntiAlias(true);
        setLayerType(1, this.mWavePaint);
        initAnimation();
        setProgressValue(100);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.mCanvasSize == 0 || this.count < 100) {
            this.mCanvasSize = canvas.getWidth();
            if (canvas.getHeight() < this.mCanvasSize) {
                this.mCanvasSize = canvas.getHeight();
            }
            this.mCanvasSize /= 2;
        }
        if (this.mWaveShader != null) {
            if (this.mWavePaint.getShader() == null) {
                this.mWavePaint.setShader(this.mWaveShader);
            }
            this.mShaderMatrix.setScale(1.0f, DEFAULT_WAVE_HSARDEHEIGHT_RATIO, 0.0f, this.mDefaultWaterLevel);
            this.mShaderMatrix.postTranslate(this.mWaveShiftRatio * getWidth(), (0.5f - this.mWaterLevelRatio) * getHeight());
            this.mWaveShader.setLocalMatrix(this.mShaderMatrix);
            int i = this.mCanvasSize;
            canvas.drawCircle(i, i, i, this.mWavePaint);
            return;
        }
        createShader();
    }

    private void createShader() {
        if (this.bitmapBuffer == null || haveBoundsChanged()) {
            Bitmap bitmap = this.bitmapBuffer;
            if (bitmap != null) {
                bitmap.recycle();
            }
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            if (measuredWidth <= 0 || measuredHeight <= 0) {
                return;
            }
            double d = 6.283185307179586d / measuredWidth;
            float f = measuredHeight;
            float f2 = DEFAULT_AMPLITUDE_RATIO * f;
            this.mDefaultWaterLevel = f * 0.5f;
            float f3 = measuredWidth;
            Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setStrokeWidth(2.0f);
            paint.setAntiAlias(true);
            int i = measuredWidth + 1;
            int i2 = measuredHeight + 1;
            float[] fArr = new float[i];
            paint.setColor(adjustAlpha(this.mWaveColor, 0.3f));
            int i3 = 0;
            while (i3 < i) {
                double d2 = d;
                float sin = (float) (this.mDefaultWaterLevel + (f2 * Math.sin(i3 * d)));
                float f4 = i3;
                int i4 = i3;
                float[] fArr2 = fArr;
                canvas.drawLine(f4, sin, f4, i2, paint);
                fArr2[i4] = sin;
                i3 = i4 + 1;
                fArr = fArr2;
                d = d2;
            }
            float[] fArr3 = fArr;
            paint.setColor(this.mWaveColor);
            int i5 = (int) (f3 / 4.0f);
            for (int i6 = 0; i6 < i; i6++) {
                float f5 = i6;
                canvas.drawLine(f5, fArr3[(i6 + i5) % i], f5, i2, paint);
            }
            this.mWaveShader = new BitmapShader(createBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            this.mWavePaint.setShader(this.mWaveShader);
        }
    }

    private boolean haveBoundsChanged() {
        return (getMeasuredWidth() == this.bitmapBuffer.getWidth() && getMeasuredHeight() == this.bitmapBuffer.getHeight()) ? false : true;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measureWidth = measureWidth(i);
        int measureHeight = measureHeight(i2);
        if (measureWidth >= measureHeight) {
            measureWidth = measureHeight;
        }
        setMeasuredDimension(measureWidth, measureWidth);
    }

    private int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return (mode == 1073741824 || mode == Integer.MIN_VALUE) ? size : this.mCanvasWidth;
    }

    private int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            size = this.mCanvasHeight;
        }
        return size + 2;
    }

    public void setWaveColor(int i) {
        this.mWaveColor = i;
        createShader();
        invalidate();
    }

    public void setProgressValue(int i) {
        this.mProgressValue = i;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "waterLevelRatio", this.mWaterLevelRatio, this.mProgressValue / 100.0f);
        ofFloat.setDuration(1000L);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat);
        animatorSet.start();
    }

    public void setWaveShiftRatio(float f) {
        this.count++;
        if (this.count % 10 != 0 || this.mWaveShiftRatio == f) {
            return;
        }
        this.mWaveShiftRatio = f;
        invalidate();
    }

    public float getWaveShiftRatio() {
        return this.mWaveShiftRatio;
    }

    public void setWaterLevelRatio(float f) {
        if (this.mWaterLevelRatio != f) {
            this.mWaterLevelRatio = f;
            invalidate();
        }
    }

    public float getWaterLevelRatio() {
        return this.mWaterLevelRatio;
    }

    public void startAnimation() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.start();
        }
    }

    private void initAnimation() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "waveShiftRatio", 0.0f, 1.0f);
        ofFloat.setRepeatCount(-1);
        ofFloat.setDuration(8000L);
        ofFloat.setInterpolator(new LinearInterpolator());
        this.mAnimatorSet = new AnimatorSet();
        this.mAnimatorSet.play(ofFloat);
    }

    public void cancel() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.end();
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        startAnimation();
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        cancel();
        super.onDetachedFromWindow();
    }

    private int adjustAlpha(int i, float f) {
        return Color.argb(Math.round(Color.alpha(i) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    @TargetApi(19)
    public void pauseAnimation() {
        AnimatorSet animatorSet;
        if (Build.VERSION.SDK_INT < 19 || (animatorSet = this.mAnimatorSet) == null) {
            return;
        }
        animatorSet.pause();
    }

    @TargetApi(19)
    public void resumeAnimation() {
        AnimatorSet animatorSet;
        if (Build.VERSION.SDK_INT < 19 || (animatorSet = this.mAnimatorSet) == null) {
            return;
        }
        animatorSet.resume();
    }
}
