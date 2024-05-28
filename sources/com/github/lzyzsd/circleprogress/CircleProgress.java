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
public class CircleProgress extends View {
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PREFIX = "prefix";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private final int default_finished_color;
    private final int default_max;
    private final int default_text_color;
    private final float default_text_size;
    private final int default_unfinished_color;
    private int finishedColor;
    private int max;
    private final int min_size;
    private Paint paint;
    private String prefixText;
    private int progress;
    private RectF rectF;
    private String suffixText;
    private int textColor;
    private Paint textPaint;
    private float textSize;
    private int unfinishedColor;

    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.rectF = new RectF();
        this.progress = 0;
        this.prefixText = "";
        this.suffixText = "%";
        this.default_finished_color = Color.rgb(66, (int) CameraInterface.TYPE_CAPTURE, 241);
        this.default_unfinished_color = Color.rgb(204, 204, 204);
        this.default_text_color = -1;
        this.default_max = 100;
        this.paint = new Paint();
        this.default_text_size = Utils.sp2px(getResources(), 18.0f);
        this.min_size = (int) Utils.dp2px(getResources(), 100.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C4383R.styleable.CircleProgress, i, 0);
        initByAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        initPainters();
    }

    protected void initByAttributes(TypedArray typedArray) {
        this.finishedColor = typedArray.getColor(C4383R.styleable.CircleProgress_circle_finished_color, this.default_finished_color);
        this.unfinishedColor = typedArray.getColor(C4383R.styleable.CircleProgress_circle_unfinished_color, this.default_unfinished_color);
        this.textColor = typedArray.getColor(C4383R.styleable.CircleProgress_circle_text_color, -1);
        this.textSize = typedArray.getDimension(C4383R.styleable.CircleProgress_circle_text_size, this.default_text_size);
        setMax(typedArray.getInt(C4383R.styleable.CircleProgress_circle_max, 100));
        setProgress(typedArray.getInt(C4383R.styleable.CircleProgress_circle_progress, 0));
        if (typedArray.getString(C4383R.styleable.CircleProgress_circle_prefix_text) != null) {
            setPrefixText(typedArray.getString(C4383R.styleable.CircleProgress_circle_prefix_text));
        }
        if (typedArray.getString(C4383R.styleable.CircleProgress_circle_suffix_text) != null) {
            setSuffixText(typedArray.getString(C4383R.styleable.CircleProgress_circle_suffix_text));
        }
    }

    protected void initPainters() {
        this.textPaint = new TextPaint();
        this.textPaint.setColor(this.textColor);
        this.textPaint.setTextSize(this.textSize);
        this.textPaint.setAntiAlias(true);
        this.paint.setAntiAlias(true);
    }

    @Override // android.view.View
    public void invalidate() {
        initPainters();
        super.invalidate();
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

    public int getFinishedColor() {
        return this.finishedColor;
    }

    public void setFinishedColor(int i) {
        this.finishedColor = i;
        invalidate();
    }

    public int getUnfinishedColor() {
        return this.unfinishedColor;
    }

    public void setUnfinishedColor(int i) {
        this.unfinishedColor = i;
        invalidate();
    }

    public String getPrefixText() {
        return this.prefixText;
    }

    public void setPrefixText(String str) {
        this.prefixText = str;
        invalidate();
    }

    public String getSuffixText() {
        return this.suffixText;
    }

    public void setSuffixText(String str) {
        this.suffixText = str;
        invalidate();
    }

    public String getDrawText() {
        return getPrefixText() + getProgress() + getSuffixText();
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return this.min_size;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return this.min_size;
    }

    public float getProgressPercentage() {
        return getProgress() / getMax();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.rectF.set(0.0f, 0.0f, View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        setMeasuredDimension(i, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float progress = (getProgress() / getMax()) * getHeight();
        float width = getWidth() / 2.0f;
        float acos = (float) ((Math.acos((width - progress) / width) * 180.0d) / 3.141592653589793d);
        float f = acos * 2.0f;
        this.paint.setColor(getUnfinishedColor());
        canvas.drawArc(this.rectF, acos + 90.0f, 360.0f - f, false, this.paint);
        canvas.save();
        canvas.rotate(180.0f, getWidth() / 2, getHeight() / 2);
        this.paint.setColor(getFinishedColor());
        canvas.drawArc(this.rectF, 270.0f - acos, f, false, this.paint);
        canvas.restore();
        String drawText = getDrawText();
        if (TextUtils.isEmpty(drawText)) {
            return;
        }
        canvas.drawText(drawText, (getWidth() - this.textPaint.measureText(drawText)) / 2.0f, (getWidth() - (this.textPaint.descent() + this.textPaint.ascent())) / 2.0f, this.textPaint);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", getTextColor());
        bundle.putFloat("text_size", getTextSize());
        bundle.putInt("finished_stroke_color", getFinishedColor());
        bundle.putInt("unfinished_stroke_color", getUnfinishedColor());
        bundle.putInt("max", getMax());
        bundle.putInt("progress", getProgress());
        bundle.putString("suffix", getSuffixText());
        bundle.putString("prefix", getPrefixText());
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.textColor = bundle.getInt("text_color");
            this.textSize = bundle.getFloat("text_size");
            this.finishedColor = bundle.getInt("finished_stroke_color");
            this.unfinishedColor = bundle.getInt("unfinished_stroke_color");
            initPainters();
            setMax(bundle.getInt("max"));
            setProgress(bundle.getInt("progress"));
            this.prefixText = bundle.getString("prefix");
            this.suffixText = bundle.getString("suffix");
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
