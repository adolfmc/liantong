package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.j */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0203j extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f303v3;

    /* renamed from: w3 */
    public C0377b f304w3;

    /* renamed from: x3 */
    public C0207l f305x3;

    public C0203j(C0377b c0377b, C0207l c0207l) {
        this.f303v3 = new C0166k(0L);
        this.f304w3 = c0377b;
        this.f305x3 = c0207l;
    }

    /* renamed from: a */
    public static C0203j m23990a(AbstractC0494y abstractC0494y, boolean z) {
        return m23989a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f303v3);
        c0140e.m24170a(this.f304w3);
        c0140e.m24170a(this.f305x3);
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public C0377b m23988i() {
        return this.f304w3;
    }

    /* renamed from: j */
    public C0207l m23987j() {
        return this.f305x3;
    }

    /* renamed from: k */
    public C0166k m23986k() {
        return this.f303v3;
    }

    /* renamed from: a */
    public static C0203j m23989a(Object obj) {
        if (obj instanceof C0203j) {
            return (C0203j) obj;
        }
        if (obj != null) {
            return new C0203j(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0203j(AbstractC0263s abstractC0263s) {
        this.f303v3 = (C0166k) abstractC0263s.mo23751a(0);
        this.f304w3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
        this.f305x3 = C0207l.m23973a(abstractC0263s.mo23751a(2));
    }
}
