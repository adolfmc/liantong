package com.baidu.mapapi.map;

import android.graphics.Point;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2771m {

    /* renamed from: a */
    public final double f6551a;

    /* renamed from: b */
    public final double f6552b;

    /* renamed from: c */
    public final double f6553c;

    /* renamed from: d */
    public final double f6554d;

    /* renamed from: e */
    public final double f6555e;

    /* renamed from: f */
    public final double f6556f;

    public C2771m(double d, double d2, double d3, double d4) {
        this.f6551a = d;
        this.f6552b = d3;
        this.f6553c = d2;
        this.f6554d = d4;
        this.f6555e = (d + d2) / 2.0d;
        this.f6556f = (d3 + d4) / 2.0d;
    }

    /* renamed from: a */
    public boolean m18790a(double d, double d2) {
        return this.f6551a <= d && d <= this.f6553c && this.f6552b <= d2 && d2 <= this.f6554d;
    }

    /* renamed from: a */
    public boolean m18789a(double d, double d2, double d3, double d4) {
        return d < this.f6553c && this.f6551a < d2 && d3 < this.f6554d && this.f6552b < d4;
    }

    /* renamed from: a */
    public boolean m18788a(Point point) {
        return m18790a(point.x, point.y);
    }

    /* renamed from: a */
    public boolean m18787a(C2771m c2771m) {
        return m18789a(c2771m.f6551a, c2771m.f6553c, c2771m.f6552b, c2771m.f6554d);
    }

    /* renamed from: b */
    public boolean m18786b(C2771m c2771m) {
        return c2771m.f6551a >= this.f6551a && c2771m.f6553c <= this.f6553c && c2771m.f6552b >= this.f6552b && c2771m.f6554d <= this.f6554d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("minX: " + this.f6551a);
        sb.append(" minY: " + this.f6552b);
        sb.append(" maxX: " + this.f6553c);
        sb.append(" maxY: " + this.f6554d);
        sb.append(" midX: " + this.f6555e);
        sb.append(" midY: " + this.f6556f);
        return sb.toString();
    }
}
