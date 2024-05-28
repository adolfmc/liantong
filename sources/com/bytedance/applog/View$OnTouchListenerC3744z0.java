package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.AnyThread;
import android.support.annotation.ColorInt;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.bytedance.applog.z0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class View$OnTouchListenerC3744z0 extends View implements View.OnTouchListener {

    /* renamed from: A */
    public Bitmap f8966A;

    /* renamed from: B */
    public final Object f8967B;

    /* renamed from: C */
    public boolean f8968C;

    /* renamed from: D */
    public boolean f8969D;

    /* renamed from: a */
    public List<C3745a> f8970a;

    /* renamed from: b */
    public List<C3745a> f8971b;

    /* renamed from: c */
    public boolean f8972c;

    /* renamed from: d */
    public double f8973d;

    /* renamed from: e */
    public double f8974e;

    /* renamed from: f */
    public double f8975f;

    /* renamed from: g */
    public double f8976g;

    /* renamed from: h */
    public int f8977h;

    /* renamed from: i */
    public int f8978i;

    /* renamed from: j */
    public int f8979j;

    /* renamed from: k */
    public double[] f8980k;
    @ColorInt

    /* renamed from: l */
    public int[] f8981l;

    /* renamed from: m */
    public float[] f8982m;

    /* renamed from: n */
    public Paint f8983n;

    /* renamed from: o */
    public boolean f8984o;

    /* renamed from: p */
    public Paint f8985p;

    /* renamed from: q */
    public Paint f8986q;

    /* renamed from: r */
    public int[] f8987r;

    /* renamed from: s */
    public boolean f8988s;

    /* renamed from: t */
    public boolean f8989t;

    /* renamed from: u */
    public float f8990u;

    /* renamed from: v */
    public float f8991v;

    /* renamed from: w */
    public float f8992w;

    /* renamed from: x */
    public float f8993x;

    /* renamed from: y */
    public Integer f8994y;

    /* renamed from: z */
    public Integer f8995z;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.z0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3745a {

        /* renamed from: a */
        public float f8996a;

        /* renamed from: b */
        public float f8997b;

        /* renamed from: c */
        public double f8998c;

        public C3745a(float f, float f2, double d) {
            this.f8996a = f;
            this.f8997b = f2;
            this.f8998c = d;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.z0$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3746b {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.z0$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3747c {
    }

    public View$OnTouchListenerC3744z0(Context context) {
        super(context);
        this.f8972c = false;
        this.f8973d = Double.NEGATIVE_INFINITY;
        this.f8974e = Double.POSITIVE_INFINITY;
        this.f8975f = 0.85d;
        this.f8976g = 200.0d;
        this.f8977h = 0;
        this.f8978i = 0;
        this.f8979j = 255;
        this.f8980k = new double[4];
        this.f8981l = new int[]{-65536, -16711936};
        this.f8982m = new float[]{0.0f, 1.0f};
        this.f8984o = true;
        this.f8987r = null;
        this.f8988s = true;
        this.f8989t = false;
        this.f8990u = 0.0f;
        this.f8991v = 0.0f;
        this.f8992w = 0.0f;
        this.f8993x = 0.0f;
        this.f8994y = null;
        this.f8995z = null;
        this.f8966A = null;
        this.f8967B = new Object();
        this.f8968C = false;
        this.f8969D = false;
        this.f8983n = new Paint();
        this.f8983n.setColor(-16777216);
        this.f8986q = new Paint();
        this.f8986q.setStyle(Paint.Style.FILL);
        this.f8985p = new Paint();
        if (!this.f8984o) {
            this.f8985p.setColor(-65794);
        }
        this.f8970a = new ArrayList();
        this.f8971b = new ArrayList();
        super.setOnTouchListener(this);
        if (this.f8968C) {
            setDrawingCacheEnabled(true);
            setDrawingCacheBackgroundColor(0);
        }
    }

    public static Map<Float, Integer> getDefaultColorStops() {
        HashMap hashMap = new HashMap();
        hashMap.put(Float.valueOf(0.25f), Integer.valueOf(Color.rgb(0, 0, 255)));
        hashMap.put(Float.valueOf(0.55f), Integer.valueOf(Color.rgb(0, 255, 0)));
        hashMap.put(Float.valueOf(0.85f), Integer.valueOf(Color.rgb(255, 255, 0)));
        hashMap.put(Float.valueOf(1.0f), Integer.valueOf(Color.rgb(255, 0, 0)));
        return hashMap;
    }

    @SuppressLint({"WrongThread"})
    @AnyThread
    private int getDrawingHeight() {
        return this.f8995z == null ? getHeight() : Math.min((int) (getHeight() / getScale()), getHeight());
    }

    @SuppressLint({"WrongThread"})
    @AnyThread
    private int getDrawingWidth() {
        return this.f8994y == null ? getWidth() : Math.min((int) (getWidth() / getScale()), getWidth());
    }

    @AnyThread
    private float getScale() {
        float height;
        Integer num;
        if (this.f8994y == null || this.f8995z == null) {
            return 1.0f;
        }
        if ((getWidth() * 1.0f) / getHeight() < (this.f8994y.intValue() * 1.0f) / this.f8995z.intValue()) {
            height = getWidth();
            num = this.f8994y;
        } else {
            height = getHeight();
            num = this.f8995z;
        }
        return height / num.intValue();
    }

    @AnyThread
    /* renamed from: a */
    public void m16994a() {
        this.f8971b.clear();
        this.f8972c = true;
    }

    @SuppressLint({"WrongThread"})
    @AnyThread
    /* renamed from: a */
    public final void m16993a(int i, int i2) {
        double[] dArr = this.f8980k;
        dArr[0] = 10000.0d;
        dArr[1] = 10000.0d;
        dArr[2] = 0.0d;
        dArr[3] = 0.0d;
        this.f8966A = this.f8968C ? getDrawingCache() : Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.f8966A);
        double d = 1.0d - this.f8975f;
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        float scale = getScale();
        float f = this.f8990u / scale;
        float f2 = this.f8991v / scale;
        float f3 = (i - f2) - (this.f8992w / scale);
        float f4 = (i2 - f) - (this.f8993x / scale);
        Iterator<C3745a> it = this.f8970a.iterator();
        while (it.hasNext()) {
            C3745a next = it.next();
            float f5 = (next.f8996a * f3) + f2;
            float f6 = (next.f8997b * f4) + f;
            Iterator<C3745a> it2 = it;
            double max = Math.max(this.f8973d, Math.min(next.f8998c, this.f8974e));
            double d2 = this.f8976g;
            double d3 = f5 - d2;
            float f7 = f3;
            float f8 = f4;
            double d4 = f6 - d2;
            double d5 = this.f8973d;
            double d6 = (max - d5) / (this.f8974e - d5);
            if (d == 1.0d) {
                canvas.drawCircle(f5, f6, (float) d2, this.f8983n);
            } else {
                this.f8986q.setShader(new RadialGradient(f5, f6, (float) (d2 * d), new int[]{Color.argb((int) (d6 * 255.0d), 0, 0, 0), Color.argb(0, 0, 0, 0)}, (float[]) null, Shader.TileMode.CLAMP));
                canvas.drawCircle(f5, f6, (float) (d2 * 2.0d), this.f8986q);
            }
            double[] dArr2 = this.f8980k;
            if (d3 < dArr2[0]) {
                dArr2[0] = d3;
            }
            double[] dArr3 = this.f8980k;
            if (d4 < dArr3[1]) {
                dArr3[1] = d4;
            }
            double d7 = (this.f8976g * 2.0d) + d3;
            double[] dArr4 = this.f8980k;
            if (d7 > dArr4[2]) {
                dArr4[2] = d7;
            }
            double d8 = (this.f8976g * 2.0d) + d4;
            double[] dArr5 = this.f8980k;
            if (d8 > dArr5[3]) {
                dArr5[3] = d8;
            }
            f3 = f7;
            it = it2;
            f4 = f8;
        }
    }

    @AnyThread
    /* renamed from: a */
    public void m16992a(C3745a c3745a) {
        this.f8971b.add(c3745a);
        this.f8972c = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (r17.f8989t != false) goto L13;
     */
    @android.support.annotation.AnyThread
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m16991a(boolean r18, int r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            boolean r1 = r0.f8969D
            if (r1 == 0) goto Lc
            int r1 = java.lang.Math.min(r19, r20)
            r2 = r1
            goto L10
        Lc:
            r2 = r19
            r1 = r20
        L10:
            r3 = 0
            if (r18 != 0) goto L1d
            boolean r4 = r0.f8988s
            if (r4 == 0) goto L18
            goto L1d
        L18:
            boolean r4 = r0.f8989t
            if (r4 == 0) goto L7d
            goto L7a
        L1d:
            android.graphics.Bitmap$Config r4 = android.graphics.Bitmap.Config.ARGB_8888
            r5 = 1
            r6 = 256(0x100, float:3.59E-43)
            android.graphics.Bitmap r7 = android.graphics.Bitmap.createBitmap(r6, r5, r4)
            android.graphics.Canvas r8 = new android.graphics.Canvas
            r8.<init>(r7)
            android.graphics.LinearGradient r4 = new android.graphics.LinearGradient
            int[] r14 = r0.f8981l
            float[] r15 = r0.f8982m
            android.graphics.Shader$TileMode r16 = android.graphics.Shader.TileMode.CLAMP
            r10 = 0
            r11 = 0
            r12 = 1132462080(0x43800000, float:256.0)
            r13 = 1065353216(0x3f800000, float:1.0)
            r9 = r4
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)
            android.graphics.Paint r13 = new android.graphics.Paint
            r13.<init>()
            android.graphics.Paint$Style r5 = android.graphics.Paint.Style.FILL
            r13.setStyle(r5)
            r13.setShader(r4)
            r9 = 0
            r11 = 1132462080(0x43800000, float:256.0)
            r12 = 1065353216(0x3f800000, float:1.0)
            r8.drawLine(r9, r10, r11, r12, r13)
            int[] r4 = new int[r6]
            r0.f8987r = r4
            int[] r8 = r0.f8987r
            r9 = 0
            r10 = 256(0x100, float:3.59E-43)
            r11 = 0
            r12 = 0
            r13 = 256(0x100, float:3.59E-43)
            r14 = 1
            r7.getPixels(r8, r9, r10, r11, r12, r13, r14)
            boolean r4 = r0.f8972c
            if (r4 == 0) goto L7a
            java.util.List<com.bytedance.applog.z0$a> r4 = r0.f8970a
            r4.clear()
            java.util.List<com.bytedance.applog.z0$a> r4 = r0.f8970a
            java.util.List<com.bytedance.applog.z0$a> r5 = r0.f8971b
            r4.addAll(r5)
            java.util.List<com.bytedance.applog.z0$a> r4 = r0.f8971b
            r4.clear()
            r0.f8972c = r3
        L7a:
            r0.m16993a(r2, r1)
        L7d:
            r0.f8988s = r3
            r0.f8989t = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.View$OnTouchListenerC3744z0.m16991a(boolean, int, int):void");
    }

    /* renamed from: b */
    public void m16990b() {
        this.f8988s = true;
        invalidate();
    }

    @AnyThread
    public double getBlur() {
        return this.f8975f;
    }

    public List<C3745a> getData() {
        return this.f8970a;
    }

    public List<C3745a> getDataBuffer() {
        return this.f8971b;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        synchronized (this.f8967B) {
            m16991a(false, getDrawingWidth(), getDrawingHeight());
        }
        if (this.f8970a.size() == 0) {
            return;
        }
        double[] dArr = this.f8980k;
        int i = (int) dArr[0];
        int i2 = (int) dArr[1];
        int i3 = (int) dArr[2];
        int i4 = (int) dArr[3];
        int drawingWidth = getDrawingWidth();
        int drawingHeight = getDrawingHeight();
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i + i3 > drawingWidth) {
            i3 = drawingWidth - i;
        }
        if (i2 + i4 > drawingHeight) {
            i4 = drawingHeight - i2;
        }
        int[] iArr = new int[i3];
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i2 + i5;
            this.f8966A.getPixels(iArr, 0, i3, i, i6, i3, 1);
            for (int i7 = 0; i7 < i3; i7++) {
                int i8 = (iArr[i7] >> 24) & 255;
                int i9 = this.f8977h;
                if (i9 <= 0 && i8 < (i9 = this.f8979j) && i8 >= (i9 = this.f8978i)) {
                    i9 = i8;
                }
                if (i8 == 0) {
                    iArr[i7] = (this.f8987r[i8] & 16777215) | 0;
                } else {
                    iArr[i7] = (this.f8987r[i8] & 16777215) | ((i9 & 255) << 24);
                }
            }
            this.f8966A.setPixels(iArr, 0, i3, i, i6, i3, 1);
        }
        if (!this.f8984o) {
            canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.f8985p);
        }
        Bitmap bitmap = this.f8966A;
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), this.f8966A.getHeight()), new Rect(0, 0, getWidth(), getHeight()), (Paint) null);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f8994y == null || this.f8995z == null) {
            this.f8989t = true;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @AnyThread
    public void setBlur(double d) {
        if (d > 1.0d || d < 0.0d) {
            throw new IllegalArgumentException("Blur must be between 0 and 1.");
        }
        this.f8975f = d;
    }

    public void setBottomPadding(int i) {
        this.f8993x = i;
    }

    @AnyThread
    public void setColorStops(Map<Float, Integer> map) {
        if (map.size() < 2) {
            throw new IllegalArgumentException("There must be at least 2 color stops");
        }
        this.f8981l = new int[map.size()];
        this.f8982m = new float[map.size()];
        int i = 0;
        for (Float f : map.keySet()) {
            this.f8981l[i] = map.get(f).intValue();
            this.f8982m[i] = f.floatValue();
            i++;
        }
        if (this.f8984o) {
            return;
        }
        this.f8985p.setColor(this.f8981l[0]);
    }

    public void setLeftPadding(int i) {
        this.f8991v = i;
    }

    public void setMarkerCallback(InterfaceC3746b interfaceC3746b) {
    }

    public void setMaxDrawingHeight(int i) {
        this.f8995z = Integer.valueOf(i);
    }

    public void setMaxDrawingWidth(int i) {
        this.f8994y = Integer.valueOf(i);
    }

    @AnyThread
    public void setMaximum(double d) {
        this.f8974e = d;
    }

    @AnyThread
    public void setMaximumOpactity(int i) {
        this.f8979j = i;
    }

    @AnyThread
    public void setMinimum(double d) {
        this.f8973d = d;
    }

    @AnyThread
    public void setMinimumOpactity(int i) {
        this.f8978i = i;
    }

    public void setOnMapClickListener(InterfaceC3747c interfaceC3747c) {
    }

    @Override // android.view.View
    @Deprecated
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
    }

    public void setOvalForOnePoint(boolean z) {
        this.f8969D = z;
    }

    @AnyThread
    public void setRadius(double d) {
        this.f8976g = d;
    }

    public void setRightPadding(int i) {
        this.f8992w = i;
    }

    public void setTopPadding(int i) {
        this.f8990u = i;
    }

    public void setUseDrawingCache(boolean z) {
        this.f8968C = z;
        invalidate();
    }

    @AnyThread
    public void setmOpacity(int i) {
        this.f8977h = i;
    }
}
