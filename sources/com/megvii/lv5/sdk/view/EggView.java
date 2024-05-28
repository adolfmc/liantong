package com.megvii.lv5.sdk.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.megvii.lv5.C5388b3;
import com.megvii.lv5.C5439f3;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.InterfaceC5526o1;
import com.megvii.lv5.RunnableC5422e3;
import com.megvii.lv5.sdk.C5559R;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class EggView extends View {

    /* renamed from: A */
    public Bitmap f13545A;

    /* renamed from: B */
    public String f13546B;

    /* renamed from: C */
    public Context f13547C;

    /* renamed from: D */
    public float f13548D;

    /* renamed from: E */
    public float f13549E;

    /* renamed from: F */
    public float f13550F;

    /* renamed from: G */
    public float f13551G;

    /* renamed from: H */
    public String f13552H;

    /* renamed from: I */
    public InterfaceC5526o1 f13553I;

    /* renamed from: J */
    public ValueAnimator f13554J;

    /* renamed from: K */
    public ValueAnimator f13555K;

    /* renamed from: L */
    public EnumC5604a f13556L;

    /* renamed from: a */
    public int f13557a;

    /* renamed from: b */
    public int f13558b;

    /* renamed from: c */
    public int f13559c;

    /* renamed from: d */
    public int f13560d;

    /* renamed from: e */
    public int f13561e;

    /* renamed from: f */
    public int f13562f;

    /* renamed from: g */
    public int f13563g;

    /* renamed from: h */
    public int f13564h;

    /* renamed from: i */
    public int f13565i;

    /* renamed from: j */
    public int f13566j;

    /* renamed from: k */
    public int f13567k;

    /* renamed from: l */
    public int f13568l;

    /* renamed from: m */
    public int f13569m;

    /* renamed from: n */
    public int f13570n;

    /* renamed from: o */
    public int f13571o;

    /* renamed from: p */
    public int f13572p;

    /* renamed from: q */
    public int f13573q;

    /* renamed from: r */
    public int f13574r;

    /* renamed from: s */
    public int f13575s;

    /* renamed from: t */
    public Paint f13576t;

    /* renamed from: u */
    public Paint f13577u;

    /* renamed from: v */
    public Paint f13578v;

    /* renamed from: w */
    public Paint f13579w;

    /* renamed from: x */
    public Paint f13580x;

    /* renamed from: y */
    public Bitmap f13581y;

    /* renamed from: z */
    public Bitmap f13582z;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.EggView$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum EnumC5604a {
        FarMirror,
        Moving,
        Guide_Strong,
        NearMirror,
        Progressing,
        WhiteBlance,
        Finish
    }

    public EggView(Context context) {
        this(context, null);
    }

    public EggView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EggView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13552H = "#FFFFFF";
        this.f13553I = null;
        this.f13556L = EnumC5604a.FarMirror;
        this.f13547C = context;
        m13035a();
    }

    /* renamed from: a */
    public final void m13035a() {
        this.f13558b = C5388b3.m13608a(this.f13547C, 2.0f);
        this.f13557a = C5388b3.m13608a(this.f13547C, 5.0f);
        this.f13559c = C5388b3.m13608a(this.f13547C, 10.0f);
        Paint paint = new Paint();
        this.f13576t = paint;
        paint.setAntiAlias(true);
        this.f13576t.setStyle(Paint.Style.FILL);
        this.f13576t.setColor(-16711936);
        Paint paint2 = new Paint();
        this.f13579w = paint2;
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.f13578v = paint3;
        paint3.setAntiAlias(true);
        this.f13578v.setStyle(Paint.Style.FILL);
        this.f13578v.setColor(Color.parseColor("#7F000000"));
        Paint paint4 = new Paint();
        this.f13580x = paint4;
        paint4.setAntiAlias(true);
        this.f13580x.setStyle(Paint.Style.FILL);
        this.f13580x.setAlpha(127);
        Paint paint5 = new Paint();
        this.f13577u = paint5;
        paint5.setAntiAlias(true);
        this.f13577u.setStyle(Paint.Style.STROKE);
        this.f13577u.setStrokeWidth(this.f13557a);
        this.f13577u.setColor(Color.parseColor("#3B94FC"));
        this.f13581y = BitmapFactory.decodeResource(getResources(), C5667z2.m12879a(this.f13547C).m12877b(this.f13547C.getResources().getString(C5559R.string.key_liveness_distance_mirror_light)));
        this.f13582z = BitmapFactory.decodeResource(getResources(), C5667z2.m12879a(this.f13547C).m12877b(this.f13547C.getResources().getString(C5559R.string.key_liveness_distance_move_image2)));
        this.f13545A = BitmapFactory.decodeResource(getResources(), C5667z2.m12879a(this.f13547C).m12877b(this.f13547C.getResources().getString(C5559R.string.key_liveness_distance_move_image1)));
        this.f13546B = getResources().getString(C5667z2.m12879a(this.f13547C).m12875d(this.f13547C.getResources().getString(C5559R.string.key_liveness_home_closer_prompt_image_text)));
    }

    /* renamed from: b */
    public void m13034b() {
        ValueAnimator valueAnimator = this.f13554J;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f13554J.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new C5439f3(this));
        ofFloat.setDuration(1500L);
        ofFloat.setRepeatCount(-1);
        this.f13554J = ofFloat;
        ofFloat.start();
    }

    /* renamed from: c */
    public void m13033c() {
        ValueAnimator valueAnimator = this.f13554J;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f13554J.cancel();
            this.f13554J = null;
        }
        ValueAnimator valueAnimator2 = this.f13555K;
        if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
            return;
        }
        this.f13555K.cancel();
        this.f13555K = null;
    }

    public int getCenterY() {
        return this.f13566j;
    }

    public int getEggFrameTop() {
        return (this.f13574r - this.f13558b) - this.f13557a;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        Paint paint;
        String str;
        int i2;
        int i3;
        int i4;
        Rect rect;
        if (this.f13572p == 0) {
            this.f13572p = getWidth();
        }
        if (this.f13573q == 0) {
            this.f13573q = getHeight();
        }
        float f = this.f13572p;
        float f2 = f / 2.0f;
        int i5 = (int) (0.55f * f2);
        this.f13568l = i5;
        int i6 = (int) (f2 * 0.82f);
        this.f13569m = i6;
        float f3 = this.f13573q;
        float f4 = 0.48f * f3;
        float f5 = i5;
        int i7 = (int) (f4 - ((f5 * 0.32999998f) / 2.0f));
        this.f13564h = i7;
        float f6 = 0.53f * f3;
        int i8 = (int) (f6 - ((i6 * 0.32999998f) / 2.0f));
        this.f13565i = i8;
        this.f13562f = (int) (f * 0.5f);
        EnumC5604a enumC5604a = this.f13556L;
        EnumC5604a enumC5604a2 = EnumC5604a.FarMirror;
        if (enumC5604a == enumC5604a2 || enumC5604a == EnumC5604a.Moving) {
            if (enumC5604a == enumC5604a2) {
                this.f13550F = 0.0f;
            }
            float f7 = this.f13550F;
            this.f13567k = (int) (f5 + ((i6 - i5) * f7));
            this.f13563g = (int) (i7 + ((i8 - i7) * f7));
            i = (int) f4;
        } else {
            this.f13567k = i6;
            this.f13563g = i8;
            i = (int) f6;
        }
        this.f13566j = i;
        int i9 = this.f13567k;
        float f8 = i9;
        int i10 = (int) (0.32f * f8);
        this.f13560d = i10;
        int i11 = (int) (f8 * 0.65f);
        this.f13561e = i11;
        int i12 = i10 + i9;
        this.f13570n = i12;
        int i13 = i9 + i11;
        this.f13571o = i13;
        int i14 = this.f13563g;
        this.f13574r = i14 - i12;
        this.f13575s = i14 + i13;
        EnumC5604a enumC5604a3 = EnumC5604a.Guide_Strong;
        if (enumC5604a == enumC5604a3 || enumC5604a == EnumC5604a.WhiteBlance || enumC5604a == EnumC5604a.Finish) {
            canvas.drawRect(0.0f, 0.0f, f, f3, this.f13578v);
        }
        if (this.f13556L == enumC5604a3) {
            int i15 = (int) (this.f13567k * 1.5f);
            float f9 = i15;
            Rect rect2 = new Rect(0, 0, this.f13582z.getWidth(), this.f13581y.getHeight());
            int i16 = this.f13562f;
            int i17 = i15 / 2;
            int i18 = this.f13566j;
            int height = ((int) (((this.f13582z.getHeight() * 1.0f) / this.f13582z.getWidth()) * f9)) / 2;
            canvas.drawBitmap(this.f13582z, rect2, new Rect(i16 - i17, i18 - height, i16 + i17, i18 + height), this.f13579w);
            int i19 = this.f13562f;
            float f10 = (this.f13551G * f9) / 5.0f;
            int i20 = this.f13566j;
            canvas.drawBitmap(this.f13545A, rect2, new Rect((int) ((i19 - i17) + f10), i20 - height, (int) (i19 + i17 + f10), i20 + height), this.f13579w);
            this.f13579w.setTextSize((int) ((20 * this.f13547C.getResources().getDisplayMetrics().scaledDensity) + 0.5f));
            this.f13579w.setColor(-1);
            canvas.drawText(this.f13546B, this.f13562f - (this.f13579w.measureText(this.f13546B) / 2.0f), rect.bottom + (Math.abs(this.f13579w.ascent() + this.f13579w.descent()) / 2.0f) + C5388b3.m13608a(this.f13547C, 15.0f), this.f13579w);
        }
        EnumC5604a enumC5604a4 = this.f13556L;
        EnumC5604a enumC5604a5 = EnumC5604a.Progressing;
        if (enumC5604a4 == enumC5604a5) {
            Paint paint2 = this.f13580x;
            String str2 = this.f13552H;
            paint2.setColor(Color.parseColor("#7F" + str2.substring(1, str2.length())));
            canvas.drawRect(0.0f, 0.0f, (float) this.f13572p, (float) this.f13573q, this.f13580x);
        }
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, this.f13572p, this.f13573q, this.f13576t);
        this.f13576t.setColor(Color.parseColor(this.f13552H));
        canvas.drawRect(0.0f, 0.0f, this.f13572p, this.f13573q, this.f13576t);
        this.f13576t.setColor(-16776961);
        this.f13576t.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        int i21 = this.f13562f;
        int i22 = this.f13567k;
        int i23 = this.f13563g;
        int i24 = this.f13570n;
        canvas.drawArc(new RectF(i21 - i22, i23 - i24, i21 + i22, i23 + i24), 180.0f, 180.0f, false, this.f13576t);
        int i25 = this.f13562f;
        int i26 = this.f13567k;
        int i27 = this.f13563g;
        int i28 = this.f13571o;
        canvas.drawArc(new RectF(i25 - i26, i27 - i28, i25 + i26, i27 + i28), 0.0f, 180.0f, false, this.f13576t);
        this.f13576t.setXfermode(null);
        canvas.restoreToCount(saveLayer);
        EnumC5604a enumC5604a6 = this.f13556L;
        if (enumC5604a6 == EnumC5604a.Finish || enumC5604a6 == EnumC5604a.Moving) {
            return;
        }
        int saveLayer2 = canvas.saveLayer(0.0f, 0.0f, this.f13572p, this.f13573q, this.f13577u);
        if (this.f13556L == enumC5604a5) {
            paint = this.f13577u;
            str = "#B2AAC6";
        } else {
            paint = this.f13577u;
            str = "#3B94FC";
        }
        paint.setColor(Color.parseColor(str));
        int i29 = this.f13562f;
        int i30 = this.f13567k;
        int i31 = this.f13558b;
        int i32 = this.f13557a;
        int i33 = this.f13563g;
        int i34 = this.f13570n;
        canvas.drawArc(new RectF(((i29 - i30) - i31) - i32, ((i33 - i34) - i31) - i32, i29 + i30 + i31 + i32, i33 + i34 + i31 + i32), 180.0f, 180.0f, false, this.f13577u);
        int i35 = this.f13562f;
        int i36 = this.f13567k;
        int i37 = this.f13558b;
        int i38 = this.f13557a;
        int i39 = this.f13563g;
        int i40 = this.f13571o;
        canvas.drawArc(new RectF(((i35 - i36) - i37) - i38, ((i39 - i40) - i37) - i38, i35 + i36 + i37 + i38, i39 + i40 + i37 + i38), 0.0f, 180.0f, false, this.f13577u);
        this.f13577u.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        EnumC5604a enumC5604a7 = this.f13556L;
        if (enumC5604a7 == enumC5604a2 || enumC5604a7 == EnumC5604a.NearMirror || enumC5604a7 == enumC5604a3) {
            int i41 = (this.f13567k * 2) / 3;
            float f11 = (this.f13575s - this.f13574r) + (this.f13559c * 2) + i41;
            Rect rect3 = new Rect(0, 0, this.f13581y.getWidth(), this.f13581y.getHeight());
            float f12 = ((i2 - i41) - i3) + (f11 * this.f13548D);
            canvas.drawBitmap(this.f13581y, rect3, new Rect(0, (int) f12, this.f13572p, (int) (f12 + i41)), this.f13577u);
        } else if (enumC5604a7 == enumC5604a5) {
            this.f13577u.setColor(-1);
            this.f13577u.setStyle(Paint.Style.FILL_AND_STROKE);
            int i42 = this.f13575s;
            int i43 = this.f13557a;
            float f13 = i42 + i43 + this.f13558b;
            canvas.drawRect(new RectF(0.0f, f13 - (((i42 - this.f13574r) + ((i43 + i4) * 2)) * this.f13549E), this.f13572p, f13), this.f13577u);
            if (this.f13549E >= 1.0f && this.f13553I != null) {
                postDelayed(new RunnableC5422e3(this), 50L);
            }
            this.f13577u.setStyle(Paint.Style.STROKE);
        }
        this.f13577u.setXfermode(null);
        canvas.restoreToCount(saveLayer2);
    }

    public void setAnimationState(EnumC5604a enumC5604a) {
        ValueAnimator valueAnimator = this.f13554J;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f13554J.cancel();
            this.f13554J = null;
        }
        ValueAnimator valueAnimator2 = this.f13555K;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            this.f13555K.cancel();
            this.f13555K = null;
        }
        this.f13556L = enumC5604a;
        invalidate();
    }

    public void setBgColor(String str) {
        this.f13552H = str;
        invalidate();
    }

    public void setGrowRatio(float f) {
        this.f13550F = f;
        invalidate();
    }

    public void setProgressCallback(InterfaceC5526o1 interfaceC5526o1) {
        this.f13553I = interfaceC5526o1;
    }

    public void setProgressRatio(float f) {
        this.f13549E = f;
        invalidate();
    }
}
