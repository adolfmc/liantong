package com.megvii.lv5.sdk.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.megvii.lv5.C5388b3;
import com.megvii.lv5.C5484j3;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.sdk.C5559R;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RadarView extends View {

    /* renamed from: A */
    public boolean f13697A;

    /* renamed from: B */
    public int f13698B;

    /* renamed from: C */
    public int f13699C;

    /* renamed from: D */
    public Handler f13700D;

    /* renamed from: E */
    public Runnable f13701E;

    /* renamed from: a */
    public Context f13702a;

    /* renamed from: b */
    public float f13703b;

    /* renamed from: c */
    public float f13704c;

    /* renamed from: d */
    public float f13705d;

    /* renamed from: e */
    public Paint f13706e;

    /* renamed from: f */
    public Paint f13707f;

    /* renamed from: g */
    public Paint f13708g;

    /* renamed from: h */
    public Paint f13709h;

    /* renamed from: i */
    public TextPaint f13710i;

    /* renamed from: j */
    public int f13711j;

    /* renamed from: k */
    public int f13712k;

    /* renamed from: l */
    public int f13713l;

    /* renamed from: m */
    public float f13714m;

    /* renamed from: n */
    public float f13715n;

    /* renamed from: o */
    public float f13716o;

    /* renamed from: p */
    public float f13717p;

    /* renamed from: q */
    public RectF f13718q;

    /* renamed from: r */
    public float f13719r;

    /* renamed from: s */
    public float f13720s;

    /* renamed from: t */
    public float f13721t;

    /* renamed from: u */
    public float f13722u;

    /* renamed from: v */
    public float f13723v;

    /* renamed from: w */
    public Matrix f13724w;

    /* renamed from: x */
    public Shader f13725x;

    /* renamed from: y */
    public String f13726y;

    /* renamed from: z */
    public CopyOnWriteArrayList<C5484j3> f13727z;

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.RadarView$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5611a implements Runnable {
        public RunnableC5611a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RadarView radarView = RadarView.this;
            if (radarView.f13697A) {
                int i = radarView.f13698B + radarView.f13699C;
                radarView.f13698B = i;
                radarView.f13724w.setRotate(i, radarView.f13703b, radarView.f13704c);
                RadarView.this.postInvalidate();
                RadarView radarView2 = RadarView.this;
                int i2 = radarView2.f13698B;
                if (i2 == 360) {
                    i2 = 0;
                }
                radarView2.f13698B = i2;
                radarView2.postDelayed(this, 10L);
            }
        }
    }

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13703b = 0.0f;
        this.f13704c = 0.0f;
        this.f13705d = 0.0f;
        this.f13713l = 0;
        this.f13714m = 0.0f;
        this.f13715n = 0.0f;
        this.f13716o = 0.0f;
        this.f13717p = 0.0f;
        this.f13724w = null;
        this.f13727z = new CopyOnWriteArrayList<>();
        this.f13697A = false;
        this.f13698B = SubsamplingScaleImageView.ORIENTATION_270;
        this.f13699C = 1;
        this.f13700D = new Handler();
        this.f13701E = new RunnableC5611a();
        m13015a(context);
    }

    /* renamed from: a */
    public final void m13015a(Context context) {
        this.f13702a = context;
        Paint paint = new Paint();
        this.f13706e = paint;
        paint.setAntiAlias(true);
        this.f13706e.setStyle(Paint.Style.FILL);
        this.f13706e.setColor(Color.parseColor("#aa0000"));
        Paint paint2 = new Paint();
        this.f13707f = paint2;
        paint2.setAntiAlias(true);
        this.f13707f.setStyle(Paint.Style.STROKE);
        this.f13707f.setColor(Color.parseColor("#51FFFFFF"));
        Paint paint3 = new Paint();
        this.f13708g = paint3;
        paint3.setAntiAlias(true);
        this.f13708g.setStyle(Paint.Style.FILL);
        Paint paint4 = new Paint();
        this.f13709h = paint4;
        paint4.setAntiAlias(true);
        this.f13709h.setStyle(Paint.Style.FILL);
        this.f13709h.setColor(-1);
        TextPaint textPaint = new TextPaint(1);
        this.f13710i = textPaint;
        textPaint.setStyle(Paint.Style.FILL);
        this.f13710i.setColor(-1);
        this.f13710i.setTextAlign(Paint.Align.CENTER);
        this.f13710i.setTextSize(C5388b3.m13608a(this.f13702a, 20.0f));
        this.f13718q = new RectF();
        new Path();
        this.f13727z.add(new C5484j3(25.0f, 210.0f, 0.6f));
        this.f13727z.add(new C5484j3(25.0f, 340.0f, 0.6f));
        this.f13727z.add(new C5484j3(15.0f, 80.0f, 0.3f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f13711j == 0) {
            this.f13711j = getWidth();
        }
        if (this.f13712k == 0) {
            this.f13712k = getHeight();
        }
        float f = this.f13705d;
        if (f != 0.0f) {
            this.f13704c = f;
        }
        if (this.f13703b == 0.0f) {
            this.f13703b = this.f13711j / 2;
        }
        if (this.f13704c == 0.0f) {
            this.f13704c = this.f13712k * 0.37f * 1.08f;
        }
        this.f13713l = C5388b3.m13608a(this.f13702a, 320.0f);
        float f2 = (this.f13711j * 0.85f) / 2.0f;
        this.f13722u = f2;
        this.f13719r = 0.3f * f2;
        this.f13720s = 0.6f * f2;
        this.f13721t = 0.9f * f2;
        this.f13723v = 25.0f;
        if (this.f13715n == 0.0f) {
            float f3 = this.f13704c + (f2 / 2.0f);
            this.f13717p = f3;
            this.f13715n = f3 - C5388b3.m13608a(this.f13702a, 20.0f);
            int i = this.f13711j;
            int i2 = this.f13713l;
            float f4 = (i - i2) / 2;
            this.f13714m = f4;
            this.f13716o = f4 + i2;
        }
        float f5 = this.f13704c;
        canvas.drawLine(0.0f, f5, this.f13711j, f5, this.f13707f);
        float f6 = this.f13703b;
        canvas.drawLine(f6, 0.0f, f6, this.f13712k, this.f13707f);
        canvas.drawCircle(this.f13703b, this.f13704c, this.f13719r, this.f13707f);
        canvas.drawCircle(this.f13703b, this.f13704c, this.f13720s, this.f13707f);
        canvas.drawCircle(this.f13703b, this.f13704c, this.f13721t, this.f13707f);
        this.f13709h.setAlpha(255);
        canvas.drawCircle(this.f13703b, this.f13704c, this.f13723v, this.f13709h);
        if (this.f13697A) {
            canvas.save();
            canvas.translate(this.f13703b, this.f13704c);
            Iterator<C5484j3> it = this.f13727z.iterator();
            while (it.hasNext()) {
                C5484j3 next = it.next();
                if (Math.abs(this.f13698B - next.f12825b) <= 6.0f && next.f12828e == 0) {
                    next.f12828e = next.f12827d;
                }
                int max = Math.max(0, (int) (((90.0f - (((this.f13698B + 360) - next.f12825b) % 360.0f)) / 90.0f) * next.f12827d));
                next.f12828e = max;
                this.f13709h.setAlpha(max);
                canvas.drawCircle(((float) Math.cos((((int) next.f12825b) * 3.141592653589793d) / 180.0d)) * this.f13722u * next.f12826c, ((float) Math.sin((((int) next.f12825b) * 3.141592653589793d) / 180.0d)) * this.f13722u * next.f12826c, next.f12824a, this.f13709h);
            }
            canvas.restore();
        }
        canvas.save();
        this.f13718q.set(this.f13714m, this.f13715n, this.f13716o, this.f13717p);
        Paint.FontMetricsInt fontMetricsInt = this.f13710i.getFontMetricsInt();
        RectF rectF = this.f13718q;
        this.f13726y = getResources().getString(C5667z2.m12879a(this.f13702a).m12875d(this.f13702a.getString(C5559R.string.key_liveness_home_camera_parameter_text)));
        StaticLayout staticLayout = new StaticLayout(this.f13726y, this.f13710i, (int) (this.f13722u * 2.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.translate(this.f13718q.centerX(), this.f13718q.top);
        staticLayout.draw(canvas);
        canvas.translate(-this.f13718q.centerX(), -((((rectF.bottom + rectF.top) - fontMetricsInt.bottom) - fontMetricsInt.top) / 2.0f));
        canvas.restore();
        if (this.f13725x == null) {
            this.f13725x = new SweepGradient(this.f13703b, this.f13704c, new int[]{Color.parseColor("#00FFFFFF"), Color.parseColor("#00FFFFFF"), Color.parseColor("#00FFFFFF"), Color.parseColor("#FFFFFFFF")}, (float[]) null);
        }
        this.f13708g.setShader(this.f13725x);
        if (this.f13724w == null) {
            Matrix matrix = new Matrix();
            this.f13724w = matrix;
            matrix.setRotate(270.0f, this.f13703b, this.f13704c);
        }
        canvas.concat(this.f13724w);
        canvas.drawCircle(this.f13703b, this.f13704c, this.f13722u, this.f13708g);
    }

    public void setCenterYParam(float f) {
        this.f13705d = f;
    }

    public void setScaning(boolean z) {
        if (z == this.f13697A) {
            return;
        }
        this.f13697A = z;
        if (z) {
            this.f13700D.post(this.f13701E);
        }
    }

    public void setSpeed(int i) {
        this.f13699C = i;
        invalidate();
    }
}
