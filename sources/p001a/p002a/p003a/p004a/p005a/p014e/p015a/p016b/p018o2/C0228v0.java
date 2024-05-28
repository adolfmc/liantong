package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0146f1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0234p;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0298t;

/* renamed from: a.a.a.a.a.e.a.b.o2.v0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0228v0 {

    /* renamed from: a */
    public C0166k f382a;

    /* renamed from: b */
    public C0146f1 f383b;

    /* renamed from: c */
    public C0186a0 f384c;

    /* renamed from: d */
    public InterfaceC0234p f385d;

    /* renamed from: e */
    public C0223t f386e;

    /* renamed from: f */
    public InterfaceC0298t f387f;

    public C0228v0(InterfaceC0298t interfaceC0298t) {
        this.f387f = interfaceC0298t;
        this.f382a = C0151g1.m24147a(interfaceC0298t.mo23612a());
        InterfaceC0136d mo23612a = interfaceC0298t.mo23612a();
        if (mo23612a instanceof C0146f1) {
            this.f383b = C0146f1.m24157a(mo23612a);
            mo23612a = interfaceC0298t.mo23612a();
        }
        if ((mo23612a instanceof C0186a0) || (mo23612a instanceof InterfaceC0298t)) {
            this.f384c = C0186a0.m24081a(mo23612a.mo23015d());
            mo23612a = interfaceC0298t.mo23612a();
        }
        if (mo23612a instanceof InterfaceC0234p) {
            this.f385d = (InterfaceC0234p) mo23612a;
        }
    }

    /* renamed from: a */
    public static C0228v0 m23876a(Object obj) {
        if (obj instanceof AbstractC0263s) {
            return new C0228v0(((AbstractC0263s) obj).m23746n());
        }
        if (obj instanceof InterfaceC0298t) {
            return new C0228v0((InterfaceC0298t) obj);
        }
        return null;
    }

    /* renamed from: b */
    public C0146f1 m23875b() {
        return this.f383b;
    }

    /* renamed from: c */
    public C0186a0 m23874c() {
        return this.f384c;
    }

    /* renamed from: d */
    public C0223t m23873d() {
        if (this.f386e == null) {
            this.f386e = C0223t.m23895a(this.f387f.mo23612a().mo23015d());
        }
        return this.f386e;
    }

    /* renamed from: e */
    public AbstractC0258r m23872e() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f382a);
        C0146f1 c0146f1 = this.f383b;
        if (c0146f1 != null) {
            c0140e.m24170a(c0146f1);
        }
        C0186a0 c0186a0 = this.f384c;
        if (c0186a0 != null) {
            c0140e.m24170a(c0186a0);
        }
        InterfaceC0234p interfaceC0234p = this.f385d;
        if (interfaceC0234p != null) {
            c0140e.m24170a(interfaceC0234p);
        }
        c0140e.m24170a(this.f386e);
        return new C0167k0(c0140e);
    }

    /* renamed from: a */
    public InterfaceC0234p m23877a() {
        return this.f385d;
    }
}
