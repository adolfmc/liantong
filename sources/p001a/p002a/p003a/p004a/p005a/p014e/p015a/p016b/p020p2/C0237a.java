package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.p2.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0237a extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f406v3;

    /* renamed from: w3 */
    public InterfaceC0136d f407w3;

    public C0237a(AbstractC0263s abstractC0263s) {
        this.f406v3 = (C0178n) abstractC0263s.mo23751a(0);
        this.f407w3 = abstractC0263s.mo23751a(1);
    }

    /* renamed from: a */
    public static C0237a m23841a(Object obj) {
        if (obj instanceof C0237a) {
            return (C0237a) obj;
        }
        if (obj != null) {
            return new C0237a(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f406v3);
        c0140e.m24170a(this.f407w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m23840i() {
        return this.f406v3;
    }

    /* renamed from: j */
    public InterfaceC0136d m23839j() {
        return this.f407w3;
    }

    public C0237a(String str, InterfaceC0136d interfaceC0136d) {
        this(new C0178n(str), interfaceC0136d);
    }

    public C0237a(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f406v3 = c0178n;
        this.f407w3 = interfaceC0136d;
    }
}
