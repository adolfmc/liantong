package comp.android.app.face.p381sz.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import comp.android.app.face.p381sz.camera.listener.CaptureListener;
import comp.android.app.face.p381sz.camera.util.C11791g;

/* renamed from: comp.android.app.face.sz.camera.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11769b extends View {

    /* renamed from: a */
    private int f23958a;

    /* renamed from: b */
    private int f23959b;

    /* renamed from: c */
    private int f23960c;

    /* renamed from: d */
    private int f23961d;

    /* renamed from: e */
    private int f23962e;

    /* renamed from: f */
    private float f23963f;

    /* renamed from: g */
    private Paint f23964g;

    /* renamed from: h */
    private float f23965h;

    /* renamed from: i */
    private int f23966i;

    /* renamed from: j */
    private int f23967j;

    /* renamed from: k */
    private float f23968k;

    /* renamed from: l */
    private float f23969l;

    /* renamed from: m */
    private float f23970m;

    /* renamed from: n */
    private float f23971n;

    /* renamed from: o */
    private float f23972o;

    /* renamed from: p */
    private int f23973p;

    /* renamed from: q */
    private float f23974q;

    /* renamed from: r */
    private int f23975r;

    /* renamed from: s */
    private int f23976s;

    /* renamed from: t */
    private int f23977t;

    /* renamed from: u */
    private RectF f23978u;

    /* renamed from: v */
    private RunnableC11775a f23979v;

    /* renamed from: w */
    private CaptureListener f23980w;

    /* renamed from: x */
    private CountDownTimerC11776b f23981x;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: comp.android.app.face.sz.camera.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC11775a implements Runnable {
        private RunnableC11775a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C11769b.this.f23958a = 3;
            C11769b c11769b = C11769b.this;
            c11769b.m2171a(c11769b.f23971n, C11769b.this.f23971n + C11769b.this.f23966i, C11769b.this.f23972o, C11769b.this.f23972o - C11769b.this.f23967j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: comp.android.app.face.sz.camera.b$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class CountDownTimerC11776b extends CountDownTimer {
        CountDownTimerC11776b(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            C11769b.this.m2170a(0L);
            C11769b.this.m2161c();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            C11769b.this.m2170a(j);
        }
    }

    public C11769b(Context context, int i) {
        super(context);
        this.f23960c = -300503530;
        this.f23961d = -287515428;
        this.f23962e = -1;
        this.f23973p = i;
        this.f23970m = i / 2.0f;
        float f = this.f23970m;
        this.f23971n = f;
        this.f23972o = f * 0.75f;
        this.f23965h = i / 15;
        this.f23966i = i / 5;
        this.f23967j = i / 8;
        this.f23964g = new Paint();
        this.f23964g.setAntiAlias(true);
        this.f23974q = 0.0f;
        this.f23979v = new RunnableC11775a();
        this.f23958a = 1;
        this.f23959b = 259;
        C11791g.m2121a("CaptureButtom start");
        this.f23975r = 10000;
        C11791g.m2121a("CaptureButtom end");
        this.f23976s = 1500;
        int i2 = this.f23973p;
        int i3 = this.f23966i;
        this.f23968k = ((i3 * 2) + i2) / 2;
        this.f23969l = (i2 + (i3 * 2)) / 2;
        float f2 = this.f23968k;
        float f3 = this.f23970m;
        float f4 = this.f23965h;
        float f5 = this.f23969l;
        this.f23978u = new RectF(f2 - ((i3 + f3) - (f4 / 2.0f)), f5 - ((i3 + f3) - (f4 / 2.0f)), f2 + ((i3 + f3) - (f4 / 2.0f)), f5 + ((f3 + i3) - (f4 / 2.0f)));
        int i4 = this.f23975r;
        this.f23981x = new CountDownTimerC11776b(i4, i4 / 360);
    }

    /* renamed from: a */
    private void m2172a(float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, 0.75f * f, f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: comp.android.app.face.sz.camera.b.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                C11769b.this.f23972o = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                C11769b.this.invalidate();
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: comp.android.app.face.sz.camera.b.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                C11769b.this.f23980w.takePictures();
                C11769b.this.f23958a = 5;
            }
        });
        ofFloat.setDuration(100L);
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2171a(float f, float f2, float f3, float f4) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(f3, f4);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: comp.android.app.face.sz.camera.b.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                C11769b.this.f23971n = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                C11769b.this.invalidate();
            }
        });
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: comp.android.app.face.sz.camera.b.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                C11769b.this.f23972o = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                C11769b.this.invalidate();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: comp.android.app.face.sz.camera.b.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (C11769b.this.f23958a == 3) {
                    if (C11769b.this.f23980w != null) {
                        C11769b.this.f23980w.recordStart();
                    }
                    C11769b.this.f23958a = 4;
                    C11769b.this.f23981x.start();
                }
            }
        });
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setDuration(100L);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2170a(long j) {
        int i = this.f23975r;
        this.f23977t = (int) (i - j);
        this.f23974q = 360.0f - ((((float) j) / i) * 360.0f);
        invalidate();
    }

    /* renamed from: b */
    private void m2164b() {
        int i;
        removeCallbacks(this.f23979v);
        int i2 = this.f23958a;
        if (i2 != 2) {
            if (i2 != 4) {
                return;
            }
            this.f23981x.cancel();
            m2161c();
        } else if (this.f23980w == null || !((i = this.f23959b) == 257 || i == 259)) {
            this.f23958a = 1;
        } else {
            m2172a(this.f23972o);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m2161c() {
        CaptureListener captureListener = this.f23980w;
        if (captureListener != null) {
            int i = this.f23977t;
            if (i < this.f23976s) {
                captureListener.recordShort(i);
            } else {
                captureListener.recordEnd(i);
            }
        }
        m2159d();
    }

    /* renamed from: d */
    private void m2159d() {
        this.f23958a = 5;
        this.f23974q = 0.0f;
        invalidate();
        float f = this.f23971n;
        float f2 = this.f23970m;
        m2171a(f, f2, this.f23972o, 0.75f * f2);
    }

    /* renamed from: a */
    public void m2173a() {
        this.f23958a = 1;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f23964g.setStyle(Paint.Style.FILL);
        this.f23964g.setColor(this.f23961d);
        canvas.drawCircle(this.f23968k, this.f23969l, this.f23971n, this.f23964g);
        this.f23964g.setColor(this.f23962e);
        canvas.drawCircle(this.f23968k, this.f23969l, this.f23972o, this.f23964g);
        if (this.f23958a == 4) {
            this.f23964g.setColor(this.f23960c);
            this.f23964g.setStyle(Paint.Style.STROKE);
            this.f23964g.setStrokeWidth(this.f23965h);
            canvas.drawArc(this.f23978u, -90.0f, this.f23974q, false, this.f23964g);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.f23973p;
        int i4 = this.f23966i;
        setMeasuredDimension((i4 * 2) + i3, i3 + (i4 * 2));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        switch (motionEvent.getAction()) {
            case 0:
                C11791g.m2121a("state = " + this.f23958a);
                if (motionEvent.getPointerCount() <= 1 && this.f23958a == 1) {
                    this.f23963f = motionEvent.getY();
                    this.f23958a = 2;
                    int i2 = this.f23959b;
                    if (i2 == 258 || i2 == 259) {
                        postDelayed(this.f23979v, 500L);
                        break;
                    }
                }
                break;
            case 1:
                m2164b();
                break;
            case 2:
                if (this.f23980w != null && this.f23958a == 4 && ((i = this.f23959b) == 258 || i == 259)) {
                    this.f23980w.recordZoom(this.f23963f - motionEvent.getY());
                    break;
                }
                break;
        }
        return true;
    }

    public void setButtonFeatures(int i) {
        this.f23959b = i;
    }

    public void setCaptureLisenter(CaptureListener captureListener) {
        this.f23980w = captureListener;
    }

    public void setDuration(int i) {
        this.f23975r = i;
        this.f23981x = new CountDownTimerC11776b(i, i / 360);
    }

    public void setMinDuration(int i) {
        this.f23976s = i;
    }
}
