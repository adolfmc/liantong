package com.github.lzyzsd.circleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class DonutProgress extends View {
    private static final String INSTANCE_BACKGROUND_COLOR = "inner_background_color";
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_FINISHED_STROKE_WIDTH = "finished_stroke_width";
    private static final String INSTANCE_INNER_BOTTOM_TEXT = "inner_bottom_text";
    private static final String INSTANCE_INNER_BOTTOM_TEXT_COLOR = "inner_bottom_text_color";
    private static final String INSTANCE_INNER_BOTTOM_TEXT_SIZE = "inner_bottom_text_size";
    private static final String INSTANCE_INNER_DRAWABLE = "inner_drawable";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PREFIX = "prefix";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_STARTING_DEGREE = "starting_degree";
    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_TEXT = "text";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private static final String INSTANCE_UNFINISHED_STROKE_WIDTH = "unfinished_stroke_width";
    private int attributeResourceId;
    private final int default_finished_color;
    private final int default_inner_background_color;
    private final int default_inner_bottom_text_color;
    private final float default_inner_bottom_text_size;
    private final int default_max;
    private final int default_startingDegree;
    private final float default_stroke_width;
    private final int default_text_color;
    private final float default_text_size;
    private final int default_unfinished_color;
    private RectF finishedOuterRect;
    private Paint finishedPaint;
    private int finishedStrokeColor;
    private float finishedStrokeWidth;
    private int innerBackgroundColor;
    private String innerBottomText;
    private int innerBottomTextColor;
    private float innerBottomTextHeight;
    protected Paint innerBottomTextPaint;
    private float innerBottomTextSize;
    private Paint innerCirclePaint;
    private int max;
    private final int min_size;
    private String prefixText;
    private float progress;
    private boolean showText;
    private int startingDegree;
    private String suffixText;
    private String text;
    private int textColor;
    protected Paint textPaint;
    private float textSize;
    private RectF unfinishedOuterRect;
    private Paint unfinishedPaint;
    private int unfinishedStrokeColor;
    private float unfinishedStrokeWidth;

    public DonutProgress(Context context) {
        this(context, null);
    }

    public DonutProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DonutProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.finishedOuterRect = new RectF();
        this.unfinishedOuterRect = new RectF();
        this.attributeResourceId = 0;
        this.progress = 0.0f;
        this.prefixText = "";
        this.suffixText = "%";
        this.text = null;
        this.default_finished_color = Color.rgb(66, (int) CameraInterface.TYPE_CAPTURE, 241);
        this.default_unfinished_color = Color.rgb(204, 204, 204);
        this.default_text_color = Color.rgb(66, (int) CameraInterface.TYPE_CAPTURE, 241);
        this.default_inner_bottom_text_color = Color.rgb(66, (int) CameraInterface.TYPE_CAPTURE, 241);
        this.default_inner_background_color = 0;
        this.default_max = 100;
        this.default_startingDegree = 0;
        this.default_text_size = Utils.sp2px(getResources(), 18.0f);
        this.min_size = (int) Utils.dp2px(getResources(), 100.0f);
        this.default_stroke_width = Utils.dp2px(getResources(), 10.0f);
        this.default_inner_bottom_text_size = Utils.sp2px(getResources(), 18.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C4383R.styleable.DonutProgress, i, 0);
        initByAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        initPainters();
    }

    protected void initPainters() {
        if (this.showText) {
            this.textPaint = new TextPaint();
            this.textPaint.setColor(this.textColor);
            this.textPaint.setTextSize(this.textSize);
            this.textPaint.setAntiAlias(true);
            this.innerBottomTextPaint = new TextPaint();
            this.innerBottomTextPaint.setColor(this.innerBottomTextColor);
            this.innerBottomTextPaint.setTextSize(this.innerBottomTextSize);
            this.innerBottomTextPaint.setAntiAlias(true);
        }
        this.finishedPaint = new Paint();
        this.finishedPaint.setColor(this.finishedStrokeColor);
        this.finishedPaint.setStyle(Paint.Style.STROKE);
        this.finishedPaint.setAntiAlias(true);
        this.finishedPaint.setStrokeWidth(this.finishedStrokeWidth);
        this.unfinishedPaint = new Paint();
        this.unfinishedPaint.setColor(this.unfinishedStrokeColor);
        this.unfinishedPaint.setStyle(Paint.Style.STROKE);
        this.unfinishedPaint.setAntiAlias(true);
        this.unfinishedPaint.setStrokeWidth(this.unfinishedStrokeWidth);
        this.innerCirclePaint = new Paint();
        this.innerCirclePaint.setColor(this.innerBackgroundColor);
        this.innerCirclePaint.setAntiAlias(true);
    }

    protected void initByAttributes(TypedArray typedArray) {
        this.finishedStrokeColor = typedArray.getColor(C4383R.styleable.DonutProgress_donut_finished_color, this.default_finished_color);
        this.unfinishedStrokeColor = typedArray.getColor(C4383R.styleable.DonutProgress_donut_unfinished_color, this.default_unfinished_color);
        this.showText = typedArray.getBoolean(C4383R.styleable.DonutProgress_donut_show_text, true);
        this.attributeResourceId = typedArray.getResourceId(C4383R.styleable.DonutProgress_donut_inner_drawable, 0);
        setMax(typedArray.getInt(C4383R.styleable.DonutProgress_donut_max, 100));
        setProgress(typedArray.getFloat(C4383R.styleable.DonutProgress_donut_progress, 0.0f));
        this.finishedStrokeWidth = typedArray.getDimension(C4383R.styleable.DonutProgress_donut_finished_stroke_width, this.default_stroke_width);
        this.unfinishedStrokeWidth = typedArray.getDimension(C4383R.styleable.DonutProgress_donut_unfinished_stroke_width, this.default_stroke_width);
        if (this.showText) {
            if (typedArray.getString(C4383R.styleable.DonutProgress_donut_prefix_text) != null) {
                this.prefixText = typedArray.getString(C4383R.styleable.DonutProgress_donut_prefix_text);
            }
            if (typedArray.getString(C4383R.styleable.DonutProgress_donut_suffix_text) != null) {
                this.suffixText = typedArray.getString(C4383R.styleable.DonutProgress_donut_suffix_text);
            }
            if (typedArray.getString(C4383R.styleable.DonutProgress_donut_text) != null) {
                this.text = typedArray.getString(C4383R.styleable.DonutProgress_donut_text);
            }
            this.textColor = typedArray.getColor(C4383R.styleable.DonutProgress_donut_text_color, this.default_text_color);
            this.textSize = typedArray.getDimension(C4383R.styleable.DonutProgress_donut_text_size, this.default_text_size);
            this.innerBottomTextSize = typedArray.getDimension(C4383R.styleable.DonutProgress_donut_inner_bottom_text_size, this.default_inner_bottom_text_size);
            this.innerBottomTextColor = typedArray.getColor(C4383R.styleable.DonutProgress_donut_inner_bottom_text_color, this.default_inner_bottom_text_color);
            this.innerBottomText = typedArray.getString(C4383R.styleable.DonutProgress_donut_inner_bottom_text);
        }
        this.innerBottomTextSize = typedArray.getDimension(C4383R.styleable.DonutProgress_donut_inner_bottom_text_size, this.default_inner_bottom_text_size);
        this.innerBottomTextColor = typedArray.getColor(C4383R.styleable.DonutProgress_donut_inner_bottom_text_color, this.default_inner_bottom_text_color);
        this.innerBottomText = typedArray.getString(C4383R.styleable.DonutProgress_donut_inner_bottom_text);
        this.startingDegree = typedArray.getInt(C4383R.styleable.DonutProgress_donut_circle_starting_degree, 0);
        this.innerBackgroundColor = typedArray.getColor(C4383R.styleable.DonutProgress_donut_background_color, 0);
    }

    @Override // android.view.View
    public void invalidate() {
        initPainters();
        super.invalidate();
    }

    public boolean isShowText() {
        return this.showText;
    }

    public void setShowText(boolean z) {
        this.showText = z;
    }

    public float getFinishedStrokeWidth() {
        return this.finishedStrokeWidth;
    }

    public void setFinishedStrokeWidth(float f) {
        this.finishedStrokeWidth = f;
        invalidate();
    }

    public float getUnfinishedStrokeWidth() {
        return this.unfinishedStrokeWidth;
    }

    public void setUnfinishedStrokeWidth(float f) {
        this.unfinishedStrokeWidth = f;
        invalidate();
    }

    private float getProgressAngle() {
        return (getProgress() / this.max) * 360.0f;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float f) {
        this.progress = f;
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

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
        invalidate();
    }

    public String getSuffixText() {
        return this.suffixText;
    }

    public void setSuffixText(String str) {
        this.suffixText = str;
        invalidate();
    }

    public String getPrefixText() {
        return this.prefixText;
    }

    public void setPrefixText(String str) {
        this.prefixText = str;
        invalidate();
    }

    public int getInnerBackgroundColor() {
        return this.innerBackgroundColor;
    }

    public void setInnerBackgroundColor(int i) {
        this.innerBackgroundColor = i;
        invalidate();
    }

    public String getInnerBottomText() {
        return this.innerBottomText;
    }

    public void setInnerBottomText(String str) {
        this.innerBottomText = str;
        invalidate();
    }

    public float getInnerBottomTextSize() {
        return this.innerBottomTextSize;
    }

    public void setInnerBottomTextSize(float f) {
        this.innerBottomTextSize = f;
        invalidate();
    }

    public int getInnerBottomTextColor() {
        return this.innerBottomTextColor;
    }

    public void setInnerBottomTextColor(int i) {
        this.innerBottomTextColor = i;
        invalidate();
    }

    public int getStartingDegree() {
        return this.startingDegree;
    }

    public void setStartingDegree(int i) {
        this.startingDegree = i;
        invalidate();
    }

    public int getAttributeResourceId() {
        return this.attributeResourceId;
    }

    public void setAttributeResourceId(int i) {
        this.attributeResourceId = i;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(measure(i), measure(i2));
        this.innerBottomTextHeight = getHeight() - ((getHeight() * 3) / 4);
    }

    private int measure(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int i2 = this.min_size;
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap decodeResource;
        super.onDraw(canvas);
        float max = Math.max(this.finishedStrokeWidth, this.unfinishedStrokeWidth);
        this.finishedOuterRect.set(max, max, getWidth() - max, getHeight() - max);
        this.unfinishedOuterRect.set(max, max, getWidth() - max, getHeight() - max);
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, ((getWidth() - Math.min(this.finishedStrokeWidth, this.unfinishedStrokeWidth)) + Math.abs(this.finishedStrokeWidth - this.unfinishedStrokeWidth)) / 2.0f, this.innerCirclePaint);
        canvas.drawArc(this.finishedOuterRect, getStartingDegree(), getProgressAngle(), false, this.finishedPaint);
        canvas.drawArc(this.unfinishedOuterRect, getProgressAngle() + getStartingDegree(), 360.0f - getProgressAngle(), false, this.unfinishedPaint);
        if (this.showText) {
            String str = this.text;
            if (str == null) {
                str = this.prefixText + this.progress + this.suffixText;
            }
            if (!TextUtils.isEmpty(str)) {
                canvas.drawText(str, (getWidth() - this.textPaint.measureText(str)) / 2.0f, (getWidth() - (this.textPaint.descent() + this.textPaint.ascent())) / 2.0f, this.textPaint);
            }
            if (!TextUtils.isEmpty(getInnerBottomText())) {
                this.innerBottomTextPaint.setTextSize(this.innerBottomTextSize);
                canvas.drawText(getInnerBottomText(), (getWidth() - this.innerBottomTextPaint.measureText(getInnerBottomText())) / 2.0f, (getHeight() - this.innerBottomTextHeight) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f), this.innerBottomTextPaint);
            }
        }
        if (this.attributeResourceId != 0) {
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), this.attributeResourceId), (getWidth() - decodeResource.getWidth()) / 2.0f, (getHeight() - decodeResource.getHeight()) / 2.0f, (Paint) null);
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", getTextColor());
        bundle.putFloat("text_size", getTextSize());
        bundle.putFloat("inner_bottom_text_size", getInnerBottomTextSize());
        bundle.putFloat("inner_bottom_text_color", getInnerBottomTextColor());
        bundle.putString("inner_bottom_text", getInnerBottomText());
        bundle.putInt("inner_bottom_text_color", getInnerBottomTextColor());
        bundle.putInt("finished_stroke_color", getFinishedStrokeColor());
        bundle.putInt("unfinished_stroke_color", getUnfinishedStrokeColor());
        bundle.putInt("max", getMax());
        bundle.putInt("starting_degree", getStartingDegree());
        bundle.putFloat("progress", getProgress());
        bundle.putString("suffix", getSuffixText());
        bundle.putString("prefix", getPrefixText());
        bundle.putString("text", getText());
        bundle.putFloat("finished_stroke_width", getFinishedStrokeWidth());
        bundle.putFloat("unfinished_stroke_width", getUnfinishedStrokeWidth());
        bundle.putInt("inner_background_color", getInnerBackgroundColor());
        bundle.putInt("inner_drawable", getAttributeResourceId());
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.textColor = bundle.getInt("text_color");
            this.textSize = bundle.getFloat("text_size");
            this.innerBottomTextSize = bundle.getFloat("inner_bottom_text_size");
            this.innerBottomText = bundle.getString("inner_bottom_text");
            this.innerBottomTextColor = bundle.getInt("inner_bottom_text_color");
            this.finishedStrokeColor = bundle.getInt("finished_stroke_color");
            this.unfinishedStrokeColor = bundle.getInt("unfinished_stroke_color");
            this.finishedStrokeWidth = bundle.getFloat("finished_stroke_width");
            this.unfinishedStrokeWidth = bundle.getFloat("unfinished_stroke_width");
            this.innerBackgroundColor = bundle.getInt("inner_background_color");
            this.attributeResourceId = bundle.getInt("inner_drawable");
            initPainters();
            setMax(bundle.getInt("max"));
            setStartingDegree(bundle.getInt("starting_degree"));
            setProgress(bundle.getFloat("progress"));
            this.prefixText = bundle.getString("prefix");
            this.suffixText = bundle.getString("suffix");
            this.text = bundle.getString("text");
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setDonut_progress(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setProgress(Integer.parseInt(str));
    }
}
