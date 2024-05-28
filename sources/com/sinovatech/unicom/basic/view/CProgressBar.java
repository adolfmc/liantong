package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CProgressBar extends ProgressBar {
    public static int CUSTOME = 0;
    public static int GPRSFLOWTYPE = 1;
    public static int PERCENTTYPE = 2;
    private Paint mPaint;
    public String text;
    public int textType;

    public CProgressBar(Context context) {
        super(context);
        this.textType = 1;
        initText();
    }

    public CProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textType = 1;
        initText();
    }

    public CProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textType = 1;
        initText();
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i) {
        setText(i);
        super.setProgress(i);
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        int width = getWidth() / 2;
        rect.centerX();
        canvas.drawText(this.text, dp2px(10), (getHeight() / 2) - rect.centerY(), this.mPaint);
    }

    private int dp2px(int i) {
        return (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void initText() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(-10240);
        this.mPaint.setTextSize(dp2px(0));
        this.mPaint.setFakeBoldText(true);
        this.mPaint.setTextAlign(Paint.Align.LEFT);
    }

    private void setText(int i) {
        int i2 = this.textType;
        if (i2 == 0) {
            this.text = "";
        } else if (i2 == 1) {
            this.text = i + "/" + getMax();
        } else if (i2 == 2) {
            this.text = String.valueOf((int) (((i * 1.0f) / getMax()) * 100.0f)) + "%";
        }
    }
}
