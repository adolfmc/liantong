package com.p319ss.android.downloadlib.guide.install;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: com.ss.android.downloadlib.guide.install.ClipImageView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ClipImageView extends ImageView {

    /* renamed from: b */
    private RectF f19219b;

    /* renamed from: h */
    private float[] f19220h;

    /* renamed from: hj */
    private Paint f19221hj;

    /* renamed from: mb */
    private boolean f19222mb;

    /* renamed from: ox */
    private Path f19223ox;

    public ClipImageView(Context context) {
        super(context);
        this.f19222mb = true;
        m7270mb(context);
    }

    public ClipImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f19222mb = true;
        m7270mb(context);
    }

    public ClipImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f19222mb = true;
        m7270mb(context);
    }

    /* renamed from: mb */
    protected void m7270mb(Context context) {
        this.f19223ox = new Path();
        this.f19219b = new RectF();
    }

    public void setRadius(float[] fArr) {
        if (fArr == null || fArr.length != 8) {
            return;
        }
        this.f19220h = fArr;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.f19221hj = new Paint(1);
        this.f19221hj.setStyle(Paint.Style.FILL);
        this.f19221hj.setColor(i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.f19222mb) {
            this.f19223ox.reset();
            this.f19219b.set(0.0f, 0.0f, getWidth(), getHeight());
            float[] fArr = this.f19220h;
            if (fArr != null) {
                this.f19223ox.addRoundRect(this.f19219b, fArr, Path.Direction.CW);
            }
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            canvas.clipPath(this.f19223ox);
            Paint paint = this.f19221hj;
            if (paint != null) {
                canvas.drawPath(this.f19223ox, paint);
            }
        }
        super.onDraw(canvas);
    }

    public void setRoundRadius(int i) {
        if (i > 0) {
            float f = i;
            setRadius(new float[]{f, f, f, f, f, f, f, f});
        }
    }

    public void setClip(boolean z) {
        this.f19222mb = z;
    }
}
