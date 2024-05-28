package com.example.asus.detectionandalign.p194b.p195a;

import com.example.asus.detectionandalign.p194b.p195a.p196a.InterfaceC4285a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.example.asus.detectionandalign.b.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4286b implements InterfaceC4285a, Cloneable {

    /* renamed from: a */
    protected double f10063a;

    /* renamed from: b */
    protected double f10064b;

    public C4286b() {
    }

    public C4286b(double d, double d2) {
        this.f10063a = d;
        this.f10064b = d2;
    }

    @Deprecated
    /* renamed from: a */
    public static C4286b m15982a(double d, double d2) {
        return new C4286b(d, d2);
    }

    /* renamed from: a */
    public double m15983a() {
        return this.f10063a;
    }

    /* renamed from: a */
    public double m15981a(C4286b c4286b) {
        return m15979b(c4286b.f10063a, c4286b.f10064b);
    }

    /* renamed from: b */
    public double m15980b() {
        return this.f10064b;
    }

    /* renamed from: b */
    public double m15979b(double d, double d2) {
        return Math.hypot(this.f10063a - d, this.f10064b - d2);
    }

    /* renamed from: c */
    public Collection<C4286b> m15978c() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(this);
        return arrayList;
    }

    @Deprecated
    /* renamed from: d */
    public C4286b clone() {
        return new C4286b(this.f10063a, this.f10064b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C4286b) {
            C4286b c4286b = (C4286b) obj;
            return C4284a.m15984a(this.f10063a, c4286b.f10063a) && C4284a.m15984a(this.f10064b, c4286b.f10064b);
        }
        return false;
    }

    @Override // java.lang.Iterable
    public Iterator<C4286b> iterator() {
        return m15978c().iterator();
    }

    public String toString() {
        return new String("Point2D(" + this.f10063a + ", " + this.f10064b + ")");
    }
}
