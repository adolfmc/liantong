package com.example.asus.detectionandalign.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.example.asus.detectionandalign.C4243R;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DrawFaceFrameView extends View {

    /* renamed from: a */
    private float f10040a;

    /* renamed from: b */
    private float f10041b;

    /* renamed from: c */
    private float f10042c;

    /* renamed from: d */
    private float f10043d;

    /* renamed from: e */
    private float[] f10044e;

    /* renamed from: f */
    private float f10045f;

    /* renamed from: g */
    private float f10046g;

    public DrawFaceFrameView(Context context) {
        super(context);
        this.f10045f = 0.0f;
        this.f10046g = 0.0f;
    }

    public DrawFaceFrameView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10045f = 0.0f;
        this.f10046g = 0.0f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(26.0f);
        paint.setColor(getResources().getColor(C4243R.C4244color.colorPrimary));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new RectF(this.f10040a, this.f10041b, this.f10042c, this.f10043d), paint);
        float[] fArr = this.f10044e;
        if (fArr != null) {
            canvas.drawPoints(fArr, paint);
        }
        if (this.f10045f != 0.0f || this.f10046g != 0.0f) {
            new ObjectAnimator();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "translationX", this.f10045f, this.f10040a);
            new ObjectAnimator();
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "translationY", this.f10046g, this.f10041b);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat, ofFloat2);
            animatorSet.setDuration(100L);
            animatorSet.start();
        }
        this.f10045f = this.f10040a;
        this.f10046g = this.f10041b;
    }
}
