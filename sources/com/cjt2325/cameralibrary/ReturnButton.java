package com.cjt2325.cameralibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ReturnButton extends View {
    private int center_X;
    private int center_Y;
    private Paint paint;
    Path path;
    private int size;
    private float strokeWidth;

    public ReturnButton(Context context, int i) {
        this(context);
        this.size = i;
        int i2 = i / 2;
        this.center_X = i2;
        this.center_Y = i2;
        this.strokeWidth = i / 15.0f;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setColor(-1);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.strokeWidth);
        this.path = new Path();
    }

    public ReturnButton(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.size;
        setMeasuredDimension(i3, i3 / 2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.path;
        float f = this.strokeWidth;
        path.moveTo(f, f / 2.0f);
        this.path.lineTo(this.center_X, this.center_Y - (this.strokeWidth / 2.0f));
        Path path2 = this.path;
        float f2 = this.strokeWidth;
        path2.lineTo(this.size - f2, f2 / 2.0f);
        canvas.drawPath(this.path, this.paint);
    }
}
