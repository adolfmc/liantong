package com.sinovatech.unicom.separatemodule.huotijiance.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LineProgressView extends View {
    private static final String TAG = "LineProgressView";
    private Paint paint;
    private float progress;

    public LineProgressView(Context context) {
        super(context);
        init();
    }

    public LineProgressView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public LineProgressView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float f) {
        this.progress = f;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth(getHeight());
        this.paint.setColor(-1);
        canvas.drawLine(0.0f, 0.0f, getWidth() * this.progress, 0.0f, this.paint);
    }
}
