package com.baidu.platform.comapi.map.p153b;

import android.view.MotionEvent;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3020a {

    /* renamed from: a */
    public static final C3021a f7780a = new C3021a(new C3022b(0.0d, 0.0d), new C3022b(1.0d, 0.0d));

    /* renamed from: b */
    public static final C3021a f7781b = new C3021a(new C3022b(0.0d, 0.0d), new C3022b(0.0d, 1.0d));

    /* renamed from: c */
    public static final C3021a f7782c = new C3021a(new C3022b(0.0d, 1.0d), new C3022b(0.0d, 0.0d));

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.b.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3021a {

        /* renamed from: a */
        public C3022b f7783a;

        /* renamed from: b */
        public C3022b f7784b;

        public C3021a(C3022b c3022b, C3022b c3022b2) {
            this.f7783a = c3022b;
            this.f7784b = c3022b2;
        }

        /* renamed from: a */
        public static C3021a m17914a(MotionEvent motionEvent) {
            return new C3021a(new C3022b(motionEvent.getX(0), motionEvent.getY(0)), new C3022b(motionEvent.getX(1), motionEvent.getY(1)));
        }

        /* renamed from: a */
        public C3022b m17916a() {
            return new C3022b((this.f7783a.f7785a + this.f7784b.f7785a) / 2.0d, (this.f7783a.f7786b + this.f7784b.f7786b) / 2.0d);
        }

        /* renamed from: b */
        public double m17912b() {
            return Math.sqrt(((this.f7783a.f7785a - this.f7784b.f7785a) * (this.f7783a.f7785a - this.f7784b.f7785a)) + ((this.f7783a.f7786b - this.f7784b.f7786b) * (this.f7783a.f7786b - this.f7784b.f7786b)));
        }

        /* renamed from: c */
        public C3024d m17910c() {
            return new C3024d(this.f7784b.f7785a - this.f7783a.f7785a, this.f7784b.f7786b - this.f7783a.f7786b);
        }

        public String toString() {
            return getClass().getSimpleName() + "  a : " + this.f7783a.toString() + " b : " + this.f7784b.toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.b.a$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3022b {

        /* renamed from: a */
        public double f7785a;

        /* renamed from: b */
        public double f7786b;

        public C3022b(double d, double d2) {
            this.f7785a = d;
            this.f7786b = d2;
        }

        public String toString() {
            return getClass().getSimpleName() + " x : " + this.f7785a + " y : " + this.f7786b;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.b.a$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3023c {

        /* renamed from: a */
        public final double f7787a;

        /* renamed from: b */
        public final double f7788b;

        /* renamed from: c */
        public final C3024d f7789c;

        public C3023c(C3021a c3021a, C3021a c3021a2) {
            this.f7789c = new C3024d(c3021a.m17916a(), c3021a2.m17916a());
            this.f7788b = c3021a2.m17912b() / c3021a.m17912b();
            this.f7787a = C3024d.m17903a(c3021a.m17910c(), c3021a2.m17910c());
        }

        public String toString() {
            return getClass().getSimpleName() + " rotate : " + this.f7787a + " scale : " + (this.f7788b * 100.0d) + " move : " + this.f7789c.toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.b.a$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C3024d {

        /* renamed from: a */
        public double f7790a;

        /* renamed from: b */
        public double f7791b;

        public C3024d(double d, double d2) {
            this.f7790a = d;
            this.f7791b = d2;
        }

        public C3024d(C3022b c3022b, C3022b c3022b2) {
            this.f7790a = c3022b2.f7785a - c3022b.f7785a;
            this.f7791b = c3022b2.f7786b - c3022b.f7786b;
        }

        /* renamed from: a */
        public static double m17903a(C3024d c3024d, C3024d c3024d2) {
            double atan2 = Math.atan2(c3024d.f7791b, c3024d.f7790a) - Math.atan2(c3024d2.f7791b, c3024d2.f7790a);
            if (atan2 > 3.141592653589793d) {
                atan2 -= 6.283185307179586d;
            } else if (atan2 < -3.141592653589793d) {
                atan2 += 6.283185307179586d;
            }
            return (atan2 * 180.0d) / 3.141592653589793d;
        }

        public String toString() {
            return getClass().getSimpleName() + " x : " + this.f7790a + " y : " + this.f7791b;
        }
    }
}
