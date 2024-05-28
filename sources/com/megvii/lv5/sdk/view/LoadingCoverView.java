package com.megvii.lv5.sdk.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.megvii.lv5.C5465h3;
import com.megvii.lv5.C5477i3;
import com.megvii.lv5.C5537q;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.InterfaceC5526o1;
import com.megvii.lv5.sdk.C5559R;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LoadingCoverView extends View {

    /* renamed from: A */
    public float f13633A;

    /* renamed from: B */
    public float f13634B;

    /* renamed from: C */
    public float f13635C;

    /* renamed from: D */
    public float f13636D;

    /* renamed from: E */
    public int f13637E;

    /* renamed from: F */
    public float f13638F;

    /* renamed from: G */
    public float f13639G;

    /* renamed from: H */
    public float f13640H;

    /* renamed from: I */
    public float f13641I;

    /* renamed from: J */
    public int f13642J;

    /* renamed from: K */
    public Paint f13643K;

    /* renamed from: L */
    public int f13644L;

    /* renamed from: M */
    public int f13645M;

    /* renamed from: N */
    public int f13646N;

    /* renamed from: O */
    public float f13647O;

    /* renamed from: P */
    public float f13648P;

    /* renamed from: Q */
    public float f13649Q;

    /* renamed from: R */
    public float f13650R;

    /* renamed from: S */
    public PathMeasure f13651S;

    /* renamed from: T */
    public Path f13652T;

    /* renamed from: U */
    public Path f13653U;

    /* renamed from: V */
    public float f13654V;

    /* renamed from: W */
    public ImageView f13655W;

    /* renamed from: a */
    public String f13656a;

    /* renamed from: a0 */
    public ImageView f13657a0;

    /* renamed from: b */
    public Paint f13658b;

    /* renamed from: b0 */
    public boolean f13659b0;

    /* renamed from: c */
    public Paint f13660c;

    /* renamed from: c0 */
    public boolean f13661c0;

    /* renamed from: d */
    public Paint f13662d;

    /* renamed from: d0 */
    public boolean f13663d0;

    /* renamed from: e */
    public Paint f13664e;

    /* renamed from: e0 */
    public int f13665e0;

    /* renamed from: f */
    public PathMeasure f13666f;

    /* renamed from: f0 */
    public ValueAnimator f13667f0;

    /* renamed from: g */
    public Path f13668g;

    /* renamed from: g0 */
    public float f13669g0;

    /* renamed from: h */
    public Path f13670h;

    /* renamed from: h0 */
    public float f13671h0;

    /* renamed from: i */
    public Path f13672i;

    /* renamed from: i0 */
    public InterfaceC5526o1 f13673i0;

    /* renamed from: j */
    public Path f13674j;

    /* renamed from: k */
    public Path f13675k;

    /* renamed from: l */
    public int f13676l;

    /* renamed from: m */
    public int f13677m;

    /* renamed from: n */
    public Paint f13678n;

    /* renamed from: o */
    public float f13679o;

    /* renamed from: p */
    public EnumC5610a f13680p;

    /* renamed from: q */
    public int f13681q;

    /* renamed from: r */
    public float f13682r;

    /* renamed from: s */
    public Path f13683s;

    /* renamed from: t */
    public PathMeasure f13684t;

    /* renamed from: u */
    public float f13685u;

    /* renamed from: v */
    public float f13686v;

    /* renamed from: w */
    public Path f13687w;

    /* renamed from: x */
    public PathMeasure f13688x;

    /* renamed from: y */
    public float f13689y;

    /* renamed from: z */
    public float f13690z;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.LoadingCoverView$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum EnumC5610a {
        START,
        END,
        PAUSE,
        IDLE,
        PROGRESSING
    }

    public LoadingCoverView(Context context) {
        this(context, null);
    }

    public LoadingCoverView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingCoverView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13656a = "LoadingView";
        this.f13679o = 0.0f;
        this.f13680p = EnumC5610a.IDLE;
        this.f13690z = 10.0f;
        this.f13633A = 26.0f;
        this.f13649Q = 0.0f;
        this.f13650R = 0.0f;
        this.f13655W = null;
        this.f13657a0 = null;
        this.f13659b0 = true;
        this.f13661c0 = false;
        this.f13663d0 = false;
        this.f13665e0 = 0;
        m13023a(context, attributeSet);
    }

    /* renamed from: a */
    public final Path m13022a(Path path, float f) {
        String str = "makeRoundPath: " + path + " roundInterval " + f;
        Path path2 = new Path();
        path2.reset();
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, true);
        float length = pathMeasure.getLength();
        float f2 = length / this.f13681q;
        float[] fArr = new float[2];
        float[] fArr2 = new float[2];
        float[] fArr3 = new float[2];
        float f3 = length - f;
        pathMeasure.getPosTan(f3, fArr, null);
        pathMeasure.getPosTan(0.0f, fArr2, null);
        pathMeasure.getPosTan(0.0f + f, fArr3, null);
        path2.moveTo(fArr[0], fArr[1]);
        float[] fArr4 = fArr2;
        path2.cubicTo(fArr[0], fArr[1], fArr2[0], fArr2[1], fArr3[0], fArr3[1]);
        int i = 0;
        while (i < this.f13681q - 1) {
            int i2 = i + 1;
            float f4 = i2 * f2;
            pathMeasure.getPosTan(f4 - f, fArr, null);
            float[] fArr5 = fArr4;
            pathMeasure.getPosTan(f4, fArr5, null);
            pathMeasure.getPosTan(f4 + f, fArr3, null);
            path2.lineTo(fArr[0], fArr[1]);
            path2.cubicTo(fArr[0], fArr[1], fArr5[0], fArr5[1], fArr3[0], fArr3[1]);
            fArr4 = fArr5;
            i = i2;
        }
        float[] fArr6 = new float[2];
        pathMeasure.getPosTan(f3, fArr6, null);
        path2.lineTo(fArr6[0], fArr6[1]);
        path2.close();
        return path2;
    }

    /* renamed from: a */
    public final Path m13021a(Point[] pointArr) {
        Path path = new Path();
        path.reset();
        path.moveTo(pointArr[0].x, pointArr[0].y);
        for (int i = 1; i < pointArr.length; i++) {
            path.lineTo(pointArr[i].x, pointArr[i].y);
        }
        path.close();
        return path;
    }

    /* renamed from: a */
    public void m13026a() {
        this.f13663d0 = false;
        postInvalidate();
    }

    /* renamed from: a */
    public void m13025a(float f, boolean z) {
        String str = "handleMessage 1111: progress=" + f;
        float max = Math.max(Math.min(100.0f, f), 0.0f);
        if (max > 100.0f || max < 0.0f) {
            return;
        }
        String str2 = "handleMessage: progress=" + max;
        if (max == 0.0f) {
            Path path = this.f13652T;
            if (path != null) {
                path.reset();
            }
            this.f13649Q = 0.0f;
            this.f13650R = 0.0f;
            this.f13650R = 0.0f;
        } else {
            this.f13650R = max;
            this.f13671h0 = max - this.f13649Q;
            String str3 = "setProgress: changeProgress = " + this.f13671h0;
            String str4 = "setProgress: finalAnimaProgress = " + this.f13650R;
            String str5 = "setProgress: mProgress = " + this.f13649Q;
            this.f13649Q = max;
        }
        invalidate();
    }

    /* renamed from: a */
    public final void m13023a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5559R.styleable.Megvii_LoadingCoverView);
        this.f13637E = obtainStyledAttributes.getColor(C5559R.styleable.Megvii_LoadingCoverView_loadingColor, Color.parseColor("#3B94FC"));
        this.f13642J = obtainStyledAttributes.getColor(C5559R.styleable.Megvii_LoadingCoverView_foregroundColor, Color.parseColor("#ffffff"));
        this.f13644L = obtainStyledAttributes.getColor(C5559R.styleable.Megvii_LoadingCoverView_progressStrokeColor, Color.parseColor("#3B94FC"));
        this.f13645M = obtainStyledAttributes.getColor(C5559R.styleable.Megvii_LoadingCoverView_hintStrokeColor, Color.parseColor("#F2F4F5"));
        this.f13646N = obtainStyledAttributes.getColor(C5559R.styleable.Megvii_LoadingCoverView_hatBgColor, context.getResources().getColor(C5667z2.m12879a(context).m12878a(context.getResources().getString(C5559R.string.key_liveness_home_action_hat_color))));
        this.f13638F = obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_loadingStrokeWidth, 3.0f);
        this.f13647O = obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_progressStrokeWidth, 9.0f);
        String str = "loadingLineWith:" + this.f13638F + "px";
        this.f13639G = obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_roundInterval, -1.0f);
        float dimension = obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_loadingFollowCircleRadius, -1.0f);
        if (dimension != -1.0f) {
            this.f13690z = dimension;
        }
        float dimension2 = obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_loadingMarginSpace, -1.0f);
        if (dimension2 != -1.0f) {
            this.f13633A = dimension2;
        }
        this.f13640H = obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_loadingOutLineRadius, -1.0f);
        this.f13641I = obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_loadingPositionOffsetX, 0.0f);
        obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_loadingPositionOffsetY, 0.0f);
        this.f13648P = obtainStyledAttributes.getDimension(C5559R.styleable.Megvii_LoadingCoverView_progressMarginSpace, 18.0f);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.f13658b = paint;
        paint.setStrokeWidth(10.0f);
        this.f13658b.setStyle(Paint.Style.FILL);
        this.f13658b.setColor(Color.parseColor("#000000"));
        this.f13658b.setAntiAlias(true);
        this.f13658b.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        Paint paint2 = new Paint();
        this.f13678n = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.f13678n.setStrokeWidth(this.f13638F);
        this.f13678n.setColor(this.f13637E);
        this.f13678n.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.f13643K = paint3;
        paint3.setStyle(Paint.Style.STROKE);
        this.f13643K.setStrokeWidth(this.f13647O);
        this.f13643K.setColor(this.f13644L);
        this.f13643K.setAntiAlias(true);
        Paint paint4 = new Paint();
        this.f13660c = paint4;
        paint4.setStyle(Paint.Style.STROKE);
        this.f13660c.setStrokeWidth(this.f13647O);
        this.f13660c.setColor(this.f13645M);
        this.f13660c.setAntiAlias(true);
        Paint paint5 = new Paint();
        this.f13662d = paint5;
        paint5.setStyle(Paint.Style.FILL);
        this.f13662d.setStrokeWidth(this.f13647O);
        this.f13662d.setColor(this.f13646N);
        this.f13662d.setAntiAlias(true);
        this.f13662d.setAlpha(128);
        Paint paint6 = new Paint();
        this.f13664e = paint6;
        paint6.setColor(this.f13642J);
        this.f13664e.setStyle(Paint.Style.FILL);
        this.f13664e.setAntiAlias(true);
        this.f13684t = new PathMeasure();
        this.f13688x = new PathMeasure();
    }

    /* renamed from: a */
    public final Point[] m13024a(int i, float f) {
        float f2 = (float) (6.283185307179586d / i);
        Point[] pointArr = new Point[i];
        for (int i2 = 0; i2 < i; i2++) {
            double d = i2 * f2;
            double d2 = f;
            pointArr[i2] = new Point((int) (Math.cos(d) * d2), (int) (Math.sin(d) * d2));
        }
        return pointArr;
    }

    /* renamed from: b */
    public final void m13020b() {
    }

    /* renamed from: b */
    public void m13019b(float f, boolean z) {
        this.f13661c0 = false;
        ValueAnimator valueAnimator = this.f13667f0;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f13667f0 = null;
        }
        this.f13659b0 = true;
        m13025a(f, z);
    }

    /* renamed from: c */
    public void m13018c() {
        this.f13663d0 = true;
        postInvalidate();
    }

    /* renamed from: d */
    public void m13017d() {
        this.f13664e.setColor(Color.rgb(255, 255, 255));
        ImageView imageView = this.f13655W;
        if (imageView != null) {
            imageView.setColorFilter(Color.rgb(255, 255, 255));
            this.f13655W.setImageDrawable(getResources().getDrawable(C5559R.C5561drawable.megliveness_color_frame));
        }
        invalidate();
        this.f13659b0 = false;
        m13026a();
        this.f13661c0 = true;
        this.f13680p = EnumC5610a.START;
        if (this.f13667f0 == null) {
            this.f13667f0 = new ValueAnimator();
        }
        this.f13667f0.setFloatValues(0.0f, 1.0f);
        this.f13667f0.setDuration(4000L);
        this.f13667f0.setInterpolator(new LinearInterpolator());
        this.f13667f0.setRepeatCount(-1);
        this.f13667f0.addUpdateListener(new C5465h3(this));
        this.f13667f0.addListener(new C5477i3(this));
        this.f13667f0.start();
    }

    /* renamed from: e */
    public void m13016e() {
        this.f13659b0 = true;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.f13667f0;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f13667f0 = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0142, code lost:
        if (r11 > r4) goto L23;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDraw(android.graphics.Canvas r20) {
        /*
            Method dump skipped, instructions count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.view.LoadingCoverView.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        String str = "onMeasure>>>  width" + View.MeasureSpec.getSize(i) + " height" + View.MeasureSpec.getSize(i2);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        C5537q c5537q;
        super.onSizeChanged(i, i2, i3, i4);
        this.f13676l = i;
        this.f13677m = i2;
        String str = "initViewSize >>> width:" + this.f13676l + " viewHeight:" + this.f13677m;
        float f = this.f13640H;
        if (f == -1.0f) {
            f = Math.min(this.f13677m, this.f13676l) >> 1;
        }
        this.f13634B = f;
        this.f13654V = C5537q.f13180g.f13184d == 0 ? 0.8f : 0.78f;
        this.f13635C = (((this.f13676l * 0.85f) / 2.0f) * this.f13654V) / 0.87f;
        String str2 = "initViewSize: action = " + c5537q.f13184d;
        this.f13636D = this.f13635C + this.f13633A + (this.f13638F / 2.0f);
        String str3 = "initViewSize >>> outLoadingRadius:" + this.f13634B;
        String str4 = "initViewSize >>> progressShapeRadius:" + this.f13636D;
        m13020b();
        float f2 = this.f13635C;
        this.f13682r = f2;
        this.f13681q = 6;
        Point[] m13024a = m13024a(6, f2);
        float f3 = this.f13682r / 8.0f;
        float f4 = this.f13639G;
        if (f4 != -1.0f) {
            f3 = f4;
        }
        this.f13670h = m13022a(m13021a(m13024a), f3);
        Path m13021a = m13021a(m13024a);
        String str5 = "makeRoundPath: " + m13021a + " roundInterval " + f3;
        Path path = new Path();
        path.reset();
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(m13021a, true);
        float length = pathMeasure.getLength();
        float f5 = length / this.f13681q;
        float[] fArr = new float[2];
        float[] fArr2 = new float[2];
        float[] fArr3 = new float[2];
        float f6 = length - f3;
        pathMeasure.getPosTan(f6, fArr, null);
        pathMeasure.getPosTan(0.0f, fArr2, null);
        pathMeasure.getPosTan(0.0f + f3, fArr3, null);
        path.moveTo(fArr[0], fArr[1]);
        path.cubicTo(fArr[0], fArr[1], fArr2[0], fArr2[1], fArr3[0], fArr3[1]);
        for (int i5 = 0; i5 < this.f13681q - 1; i5 += 4) {
            float f7 = (i5 + 1) * f5;
            pathMeasure.getPosTan(f7 - f3, fArr, null);
            pathMeasure.getPosTan(f7, fArr2, null);
            pathMeasure.getPosTan(f7 + f3, fArr3, null);
            path.lineTo(fArr[0], fArr[1]);
            path.cubicTo(fArr[0], fArr[1], fArr2[0], fArr2[1], fArr3[0], fArr3[1]);
        }
        float[] fArr4 = new float[2];
        pathMeasure.getPosTan(f6, fArr4, null);
        path.lineTo(fArr4[0], fArr4[1]);
        path.close();
        this.f13672i = path;
        this.f13681q = 6;
        Point[] m13024a2 = m13024a(6, this.f13636D + (this.f13648P / 2.0f));
        float f8 = this.f13636D / 8.0f;
        float f9 = this.f13639G;
        if (f9 != -1.0f) {
            f8 = f9;
        }
        this.f13675k = m13022a(m13021a(m13024a2), f8);
        this.f13681q = 6;
        Point[] m13024a3 = m13024a(6, this.f13636D + (this.f13648P / 2.0f));
        float f10 = this.f13636D / 8.0f;
        float f11 = this.f13639G;
        if (f11 != -1.0f) {
            f10 = f11;
        }
        this.f13674j = m13022a(m13021a(m13024a3), f10);
        this.f13681q = 6;
        Point[] m13024a4 = m13024a(6, this.f13636D + (this.f13648P / 2.0f));
        float f12 = this.f13682r / 8.0f;
        float f13 = this.f13639G;
        if (f13 != -1.0f) {
            f12 = f13;
        }
        Path m13022a = m13022a(m13021a(m13024a4), f12);
        this.f13683s = m13022a;
        this.f13684t.setPath(m13022a, true);
        float length2 = this.f13684t.getLength();
        this.f13685u = length2;
        this.f13686v = length2 / 2.0f;
        float f14 = this.f13634B;
        this.f13681q = 6;
        Point[] m13024a5 = m13024a(6, f14);
        float f15 = this.f13682r / 8.0f;
        float f16 = this.f13639G;
        if (f16 != -1.0f) {
            f15 = f16;
        }
        Path m13022a2 = m13022a(m13021a(m13024a5), f15);
        this.f13687w = m13022a2;
        this.f13688x.setPath(m13022a2, true);
        this.f13689y = this.f13688x.getLength();
        Path path2 = new Path();
        this.f13653U = path2;
        int i6 = this.f13677m;
        int i7 = this.f13676l;
        path2.addRect(-i6, (-i7) / 2, i6 / 2, i7 / 2, Path.Direction.CW);
    }

    public void setForegroundColor(int i) {
        this.f13664e.setColor(i);
        postInvalidate();
    }

    public void setForegroundColor(String str) {
        this.f13664e.setColor(Color.parseColor(str));
        ImageView imageView = this.f13655W;
        if (imageView != null) {
            imageView.setColorFilter(Color.parseColor(str));
            this.f13655W.setImageDrawable(getResources().getDrawable(C5559R.C5561drawable.megliveness_color_frame));
        }
        invalidate();
    }

    public void setHintColor(int i) {
        Resources resources;
        int i2;
        if (i == 0) {
            resources = getResources();
            i2 = C5559R.C5560color.megvii_liveness_polygon_hint_color_white;
        } else {
            resources = getResources();
            i2 = C5559R.C5560color.megvii_liveness_polygon_hint_color_black;
        }
        this.f13660c.setColor(resources.getColor(i2));
        invalidate();
    }

    public void setProgress(float f) {
        m13025a(f, true);
        invalidate();
    }

    public void setProgressLineColor(int i) {
        this.f13643K.setColor(i);
    }

    public void setmProgressCallback(InterfaceC5526o1 interfaceC5526o1) {
        this.f13673i0 = interfaceC5526o1;
    }
}
