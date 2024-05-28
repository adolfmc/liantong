package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0180n1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0300t1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0340u0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0496y1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0499z1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x;

/* renamed from: a.a.a.a.a.e.a.b.v2.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0362b extends AbstractC0174m implements InterfaceC0132c, InterfaceC0452x {

    /* renamed from: v3 */
    public InterfaceC0452x f933v3;

    public C0362b(C0300t1 c0300t1) {
        this.f933v3 = c0300t1;
    }

    /* renamed from: a */
    public static C0362b m23547a(Object obj) {
        if (obj != null && !(obj instanceof C0362b)) {
            if (obj instanceof C0300t1) {
                return new C0362b((C0300t1) obj);
            }
            if (obj instanceof C0180n1) {
                return new C0362b((C0180n1) obj);
            }
            if (obj instanceof C0499z1) {
                return new C0362b((C0499z1) obj);
            }
            if (obj instanceof C0496y1) {
                return new C0362b((C0496y1) obj);
            }
            if (obj instanceof C0340u0) {
                return new C0362b((C0340u0) obj);
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0362b) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return ((InterfaceC0136d) this.f933v3).mo23015d();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x
    /* renamed from: e */
    public String mo22978e() {
        return this.f933v3.mo22978e();
    }

    public String toString() {
        return this.f933v3.mo22978e();
    }

    public C0362b(C0180n1 c0180n1) {
        this.f933v3 = c0180n1;
    }

    public C0362b(C0499z1 c0499z1) {
        this.f933v3 = c0499z1;
    }

    public C0362b(C0496y1 c0496y1) {
        this.f933v3 = c0496y1;
    }

    public C0362b(C0340u0 c0340u0) {
        this.f933v3 = c0340u0;
    }

    public C0362b(String str) {
        this.f933v3 = new C0496y1(str);
    }

    /* renamed from: a */
    public static C0362b m23548a(AbstractC0494y abstractC0494y, boolean z) {
        if (z) {
            return m23547a(abstractC0494y.m23004m());
        }
        throw new IllegalArgumentException("choice item must be explicitly tagged");
    }
}
