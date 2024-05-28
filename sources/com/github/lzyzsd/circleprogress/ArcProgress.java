package com.github.lzyzsd.circleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.cjt2325.cameralibrary.CameraInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ArcProgress extends View {
    private static final String INSTANCE_ARC_ANGLE = "arc_angle";
    private static final String INSTANCE_BOTTOM_TEXT = "bottom_text";
    private static final String INSTANCE_BOTTOM_TEXT_SIZE = "bottom_text_size";
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_STROKE_WIDTH = "stroke_width";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_SUFFIX_TEXT_PADDING = "suffix_text_padding";
    private static final String INSTANCE_SUFFIX_TEXT_SIZE = "suffix_text_size";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private float arcAngle;
    private float arcBottomHeight;
    private String bottomText;
    private float bottomTextSize;
    private final float default_arc_angle;
    private final float default_bottom_text_size;
    private final int default_finished_color;
    private final int default_max;
    private final float default_stroke_width;
    private final float default_suffix_padding;
    private final String default_suffix_text;
    private final float default_suffix_text_size;
    private final int default_text_color;
    private float default_text_size;
    private final int default_unfinished_color;
    private int finishedStrokeColor;
    private int max;
    private final int min_size;
    private Paint paint;
    private int progress;
    private RectF rectF;
    private float strokeWidth;
    private String suffixText;
    private float suffixTextPadding;
    private float suffixTextSize;
    private int textColor;
    protected Paint textPaint;
    private float textSize;
    private int unfinishedStrokeColor;

    public ArcProgress(Context context) {
        this(context, null);
    }

    public ArcProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArcProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.rectF = new RectF();
        this.progress = 0;
        this.suffixText = "%";
        this.default_finished_color = -1;
        this.default_unfinished_color = Color.rgb(72, 106, 176);
        this.default_text_color = Color.rgb(66, (int) CameraInterface.TYPE_CAPTURE, 241);
        this.default_max = 100;
        this.default_arc_angle = 288.0f;
        this.default_text_size = Utils.sp2px(getResources(), 18.0f);
        this.min_size = (int) Utils.dp2px(getResources(), 100.0f);
        this.default_text_size = Utils.sp2px(getResources(), 40.0f);
        this.default_suffix_text_size = Utils.sp2px(getResources(), 15.0f);
        this.default_suffix_padding = Utils.dp2px(getResources(), 4.0f);
        this.default_suffix_text = "%";
        this.default_bottom_text_size = Utils.sp2px(getResources(), 10.0f);
        this.default_stroke_width = Utils.dp2px(getResources(), 4.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C4383R.styleable.ArcProgress, i, 0);
        initByAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        initPainters();
    }

    protected void initByAttributes(TypedArray typedArray) {
        this.finishedStrokeColor = typedArray.getColor(C4383R.styleable.ArcProgress_arc_finished_color, -1);
        this.unfinishedStrokeColor = typedArray.getColor(C4383R.styleable.ArcProgress_arc_unfinished_color, this.default_unfinished_color);
        this.textColor = typedArray.getColor(C4383R.styleable.ArcProgress_arc_text_color, this.default_text_color);
        this.textSize = typedArray.getDimension(C4383R.styleable.ArcProgress_arc_text_size, this.default_text_size);
        this.arcAngle = typedArray.getFloat(C4383R.styleable.ArcProgress_arc_angle, 288.0f);
        setMax(typedArray.getInt(C4383R.styleable.ArcProgress_arc_max, 100));
        setProgress(typedArray.getInt(C4383R.styleable.ArcProgress_arc_progress, 0));
        this.strokeWidth = typedArray.getDimension(C4383R.styleable.ArcProgress_arc_stroke_width, this.default_stroke_width);
        this.suffixTextSize = typedArray.getDimension(C4383R.styleable.ArcProgress_arc_suffix_text_size, this.default_suffix_text_size);
        this.suffixText = TextUtils.isEmpty(typedArray.getString(C4383R.styleable.ArcProgress_arc_suffix_text)) ? this.default_suffix_text : typedArray.getString(C4383R.styleable.ArcProgress_arc_suffix_text);
        this.suffixTextPadding = typedArray.getDimension(C4383R.styleable.ArcProgress_arc_suffix_text_padding, this.default_suffix_padding);
        this.bottomTextSize = typedArray.getDimension(C4383R.styleable.ArcProgress_arc_bottom_text_size, this.default_bottom_text_size);
        this.bottomText = typedArray.getString(C4383R.styleable.ArcProgress_arc_bottom_text);
    }

    protected void initPainters() {
        this.textPaint = new TextPaint();
        this.textPaint.setColor(this.textColor);
        this.textPaint.setTextSize(this.textSize);
        this.textPaint.setAntiAlias(true);
        this.paint = new Paint();
        this.paint.setColor(this.default_unfinished_color);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(this.strokeWidth);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.view.View
    public void invalidate() {
        initPainters();
        super.invalidate();
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(float f) {
        this.strokeWidth = f;
        invalidate();
    }

    public float getSuffixTextSize() {
        return this.suffixTextSize;
    }

    public void setSuffixTextSize(float f) {
        this.suffixTextSize = f;
        invalidate();
    }

    public String getBottomText() {
        return this.bottomText;
    }

    public void setBottomText(String str) {
        this.bottomText = str;
        invalidate();
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
        if (this.progress > getMax()) {
            this.progress %= getMax();
        }
        invalidate();
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int i) {
        if (i > 0) {
            this.max = i;
            invalidate();
        }
    }

    public float getBottomTextSize() {
        return this.bottomTextSize;
    }

    public void setBottomTextSize(float f) {
        this.bottomTextSize = f;
        invalidate();
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float f) {
        this.textSize = f;
        invalidate();
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
        invalidate();
    }

    public int getFinishedStrokeColor() {
        return this.finishedStrokeColor;
    }

    public void setFinishedStrokeColor(int i) {
        this.finishedStrokeColor = i;
        invalidate();
    }

    public int getUnfinishedStrokeColor() {
        return this.unfinishedStrokeColor;
    }

    public void setUnfinishedStrokeColor(int i) {
        this.unfinishedStrokeColor = i;
        invalidate();
    }

    public float getArcAngle() {
        return this.arcAngle;
    }

    public void setArcAngle(float f) {
        this.arcAngle = f;
        invalidate();
    }

    public String getSuffixText() {
        return this.suffixText;
    }

    public void setSuffixText(String str) {
        this.suffixText = str;
        invalidate();
    }

    public float getSuffixTextPadding() {
        return this.suffixTextPadding;
    }

    public void setSuffixTextPadding(float f) {
        this.suffixTextPadding = f;
        invalidate();
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return this.min_size;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return this.min_size;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(i, i2);
        int size = View.MeasureSpec.getSize(i);
        RectF rectF = this.rectF;
        float f = this.strokeWidth;
        float f2 = size;
        rectF.set(f / 2.0f, f / 2.0f, f2 - (f / 2.0f), View.MeasureSpec.getSize(i2) - (this.strokeWidth / 2.0f));
        this.arcBottomHeight = (f2 / 2.0f) * ((float) (1.0d - Math.cos((((360.0f - this.arcAngle) / 2.0f) / 180.0f) * 3.141592653589793d)));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = 270.0f - (this.arcAngle / 2.0f);
        float max = (this.progress / getMax()) * this.arcAngle;
        float f2 = this.progress == 0 ? 0.01f : f;
        this.paint.setColor(this.unfinishedStrokeColor);
        canvas.drawArc(this.rectF, f, this.arcAngle, false, this.paint);
        this.paint.setColor(this.finishedStrokeColor);
        canvas.drawArc(this.rectF, f2, max, false, this.paint);
        String valueOf = String.valueOf(getProgress());
        if (!TextUtils.isEmpty(valueOf)) {
            this.textPaint.setColor(this.textColor);
            this.textPaint.setTextSize(this.textSize);
            float descent = this.textPaint.descent() + this.textPaint.ascent();
            float height = (getHeight() - descent) / 2.0f;
            canvas.drawText(valueOf, (getWidth() - this.textPaint.measureText(valueOf)) / 2.0f, height, this.textPaint);
            this.textPaint.setTextSize(this.suffixTextSize);
            canvas.drawText(this.suffixText, (getWidth() / 2.0f) + this.textPaint.measureText(valueOf) + this.suffixTextPadding, (height + descent) - (this.textPaint.descent() + this.textPaint.ascent()), this.textPaint);
        }
        if (this.arcBottomHeight == 0.0f) {
            this.arcBottomHeight = (getWidth() / 2.0f) * ((float) (1.0d - Math.cos((((360.0f - this.arcAngle) / 2.0f) / 180.0f) * 3.141592653589793d)));
        }
        if (TextUtils.isEmpty(getBottomText())) {
            return;
        }
        this.textPaint.setTextSize(this.bottomTextSize);
        canvas.drawText(getBottomText(), (getWidth() - this.textPaint.measureText(getBottomText())) / 2.0f, (getHeight() - this.arcBottomHeight) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f), this.textPaint);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putFloat("stroke_width", getStrokeWidth());
        bundle.putFloat("suffix_text_size", getSuffixTextSize());
        bundle.putFloat("suffix_text_padding", getSuffixTextPadding());
        bundle.putFloat("bottom_text_size", getBottomTextSize());
        bundle.putString("bottom_text", getBottomText());
        bundle.putFloat("text_size", getTextSize());
        bundle.putInt("text_color", getTextColor());
        bundle.putInt("progress", getProgress());
        bundle.putInt("max", getMax());
        bundle.putInt("finished_stroke_color", getFinishedStrokeColor());
        bundle.putInt("unfinished_stroke_color", getUnfinishedStrokeColor());
        bundle.putFloat("arc_angle", getArcAngle());
        bundle.putString("suffix", getSuffixText());
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.strokeWidth = bundle.getFloat("stroke_width");
            this.suffixTextSize = bundle.getFloat("suffix_text_size");
            this.suffixTextPadding = bundle.getFloat("suffix_text_padding");
            this.bottomTextSize = bundle.getFloat("bottom_text_size");
            this.bottomText = bundle.getString("bottom_text");
            this.textSize = bundle.getFloat("text_size");
            this.textColor = bundle.getInt("text_color");
            setMax(bundle.getInt("max"));
            setProgress(bundle.getInt("progress"));
            this.finishedStrokeColor = bundle.getInt("finished_stroke_color");
            this.unfinishedStrokeColor = bundle.getInt("unfinished_stroke_color");
            this.suffixText = bundle.getString("suffix");
            initPainters();
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
