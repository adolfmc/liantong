package com.unicom.pay.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import p470p0.C13659r;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPPayPwdResultView extends View {

    /* renamed from: t */
    public static final /* synthetic */ int f20475t = 0;

    /* renamed from: a */
    public float f20476a;

    /* renamed from: b */
    public Paint f20477b;

    /* renamed from: c */
    public Paint f20478c;

    /* renamed from: d */
    public PathMeasure f20479d;

    /* renamed from: e */
    public Path f20480e;

    /* renamed from: f */
    public float f20481f;

    /* renamed from: g */
    public Path f20482g;

    /* renamed from: h */
    public int f20483h;

    /* renamed from: i */
    public RectF f20484i;

    /* renamed from: j */
    public float f20485j;

    /* renamed from: k */
    public float f20486k;

    /* renamed from: l */
    public float f20487l;

    /* renamed from: m */
    public float f20488m;

    /* renamed from: n */
    public ValueAnimator f20489n;

    /* renamed from: o */
    public InterfaceC10717d f20490o;

    /* renamed from: p */
    public boolean f20491p;

    /* renamed from: q */
    public boolean f20492q;

    /* renamed from: r */
    public RectF f20493r;

    /* renamed from: s */
    public LinearGradient f20494s;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.WPPayPwdResultView$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10714a extends AnimatorListenerAdapter {
        public C10714a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            WPPayPwdResultView.this.f20492q = false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.WPPayPwdResultView$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10715b extends AnimatorListenerAdapter {
        public C10715b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            WPPayPwdResultView wPPayPwdResultView = WPPayPwdResultView.this;
            int i = WPPayPwdResultView.f20475t;
            wPPayPwdResultView.m6041e();
        }
    }

    /* renamed from: com.unicom.pay.widget.WPPayPwdResultView$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10716c extends AnimatorListenerAdapter {
        public C10716c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            WPPayPwdResultView wPPayPwdResultView = WPPayPwdResultView.this;
            wPPayPwdResultView.f20491p = false;
            InterfaceC10717d interfaceC10717d = wPPayPwdResultView.f20490o;
            if (interfaceC10717d != null) {
                interfaceC10717d.m6040a();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.WPPayPwdResultView$d */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10717d {
        /* renamed from: a */
        void m6040a();
    }

    public WPPayPwdResultView(Context context) {
        super(context);
        this.f20483h = 0;
        this.f20485j = 0.0f;
        this.f20487l = 0.0f;
        this.f20488m = 0.0f;
        m6047a(context);
    }

    public WPPayPwdResultView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f20483h = 0;
        this.f20485j = 0.0f;
        this.f20487l = 0.0f;
        this.f20488m = 0.0f;
        m6047a(context);
    }

    public WPPayPwdResultView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f20483h = 0;
        this.f20485j = 0.0f;
        this.f20487l = 0.0f;
        this.f20488m = 0.0f;
        m6047a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m6048a(ValueAnimator valueAnimator) {
        int animatedFraction = (int) ((valueAnimator.getAnimatedFraction() * 155.0f) + 100.0f);
        if (animatedFraction <= 255) {
            this.f20477b.setAlpha(animatedFraction);
        }
        setCircleRadius(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m6045b(ValueAnimator valueAnimator) {
        float f = this.f20485j;
        float f2 = this.f20488m;
        int i = (f > f2 ? 1 : (f == f2 ? 0 : -1));
        if (i == 0) {
            this.f20487l += 6.0f;
        }
        float f3 = this.f20487l;
        if (f3 >= 280.0f || i > 0) {
            this.f20485j = f + 6.0f;
            if (f3 > 20.0f) {
                this.f20487l = f3 - 6.0f;
            }
        }
        float f4 = this.f20485j;
        if (f4 > f2 + 280.0f) {
            this.f20488m = f4;
            this.f20485j = f4;
            this.f20487l = 20.0f;
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public /* synthetic */ void m6043c(ValueAnimator valueAnimator) {
        setRightPathPercent(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    private void setRightPathPercent(float f) {
        this.f20481f = f;
        invalidate();
    }

    /* renamed from: a */
    public final int m6049a(int i, boolean z) {
        int paddingTop;
        int paddingBottom;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (z) {
            paddingTop = getPaddingLeft();
            paddingBottom = getPaddingRight();
        } else {
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
        }
        int i2 = paddingBottom + paddingTop;
        if (mode == 1073741824) {
            return size;
        }
        int i3 = (((int) this.f20476a) * 2) + i2;
        return mode == Integer.MIN_VALUE ? Math.min(i3, size) : i3;
    }

    /* renamed from: a */
    public final void m6050a() {
        ValueAnimator valueAnimator = this.f20489n;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.f20485j = 0.0f;
        this.f20488m = 0.0f;
        invalidate();
    }

    /* renamed from: a */
    public final void m6047a(Context context) {
        Paint paint = new Paint();
        this.f20477b = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f20477b.setAntiAlias(true);
        this.f20477b.setColor(Color.parseColor("#79CCA7"));
        Paint paint2 = new Paint();
        this.f20478c = paint2;
        paint2.setAntiAlias(true);
        this.f20478c.setStrokeCap(Paint.Cap.ROUND);
        this.f20478c.setStrokeJoin(Paint.Join.ROUND);
        this.f20478c.setStyle(Paint.Style.STROKE);
        this.f20478c.setColor(-1);
        this.f20478c.setStrokeWidth(15.0f);
        this.f20478c.setDither(true);
        this.f20479d = new PathMeasure();
        this.f20480e = new Path();
        this.f20482g = new Path();
        this.f20493r = new RectF();
        this.f20476a = C13659r.m168b(context) / 15;
    }

    /* renamed from: b */
    public final void m6046b() {
        ValueAnimator valueAnimator = this.f20489n;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        setState(1);
    }

    /* renamed from: c */
    public final void m6044c() {
        this.f20481f = 0.0f;
        invalidate();
        if (this.f20491p) {
            return;
        }
        float f = this.f20476a;
        this.f20491p = true;
        ValueAnimator duration = ValueAnimator.ofFloat(0.5f * f, f).setDuration(500L);
        duration.setInterpolator(new OvershootInterpolator());
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.unicom.pay.widget.-$$Lambda$WPPayPwdResultView$LHImbhJ8LFjN2S2iIv9lSd0sN8k
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                WPPayPwdResultView.this.m6048a(valueAnimator);
            }
        });
        duration.addListener(new C10715b());
        duration.start();
    }

    /* renamed from: d */
    public final void m6042d() {
        if (this.f20492q) {
            return;
        }
        this.f20494s = new LinearGradient(0.0f, 0.0f, this.f20493r.right, 0.0f, Color.parseColor("#3AD0AD"), Color.parseColor("#C1F0E5"), Shader.TileMode.CLAMP);
        this.f20492q = true;
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(800L);
        this.f20489n = duration;
        duration.setInterpolator(new AccelerateDecelerateInterpolator());
        this.f20489n.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.unicom.pay.widget.-$$Lambda$WPPayPwdResultView$_FkNKisafowRj3jH0PpEro6KzGs
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                WPPayPwdResultView.this.m6045b(valueAnimator);
            }
        });
        this.f20489n.addListener(new C10714a());
        this.f20489n.setRepeatCount(1000);
        this.f20489n.setRepeatMode(1);
        this.f20489n.start();
    }

    /* renamed from: e */
    public final void m6041e() {
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(500L);
        duration.setInterpolator(new OvershootInterpolator());
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.unicom.pay.widget.-$$Lambda$WPPayPwdResultView$C4hH2_aydS4vYGLamv1gySLBFak
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                WPPayPwdResultView.this.m6043c(valueAnimator);
            }
        });
        duration.addListener(new C10716c());
        duration.start();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f20482g.reset();
        int i = this.f20483h;
        if (i == 0) {
            float f = this.f20486k + 4.0f;
            this.f20486k = f;
            canvas.rotate(f, this.f20484i.centerX(), this.f20484i.centerY());
            this.f20478c.setShader(this.f20494s);
            canvas.drawArc(this.f20484i, this.f20485j, this.f20487l, false, this.f20478c);
        } else if (i != 1) {
        } else {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.f20476a, this.f20477b);
            this.f20479d.setPath(this.f20480e, false);
            this.f20482g.reset();
            PathMeasure pathMeasure = this.f20479d;
            pathMeasure.getSegment(0.0f, pathMeasure.getLength() * this.f20481f, this.f20482g, true);
            this.f20478c.setColor(-1);
            this.f20478c.setShader(null);
            canvas.drawPath(this.f20482g, this.f20478c);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int m6049a = m6049a(i, true);
        int m6049a2 = m6049a(i2, false);
        this.f20493r.set(0.0f, 0.0f, m6049a, m6049a2);
        setMeasuredDimension(m6049a, m6049a2);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f20480e.moveTo((this.f20476a / 2.0f) + getPaddingLeft(), getPaddingTop() + this.f20476a);
        this.f20480e.lineTo(((this.f20476a * 5.0f) / 6.0f) + getPaddingLeft(), ((this.f20476a * 4.0f) / 3.0f) + getPaddingTop());
        this.f20480e.lineTo((((((float) Math.sqrt(2.0d)) / 3.0f) + 1.0f) * this.f20476a) + getPaddingLeft(), ((1.1666666f - (((float) Math.sqrt(2.0d)) / 3.0f)) * this.f20476a) + getPaddingTop());
        this.f20484i = new RectF(getPaddingLeft(), getPaddingTop(), (this.f20476a * 2.0f) + getPaddingLeft(), (this.f20476a * 2.0f) + getPaddingTop());
    }

    public void setAnimateScale(float f) {
    }

    public void setCircleColor(int i) {
    }

    public void setCircleRadius(float f) {
        this.f20476a = f;
        invalidate();
    }

    public void setOnCompletedListener(InterfaceC10717d interfaceC10717d) {
        this.f20490o = interfaceC10717d;
    }

    public void setState(int i) {
        this.f20483h = i;
        if (i == 0) {
            m6042d();
        } else if (i != 1) {
        } else {
            m6044c();
        }
    }
}
