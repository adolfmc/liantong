package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.s2.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0275e extends AbstractC0174m {

    /* renamed from: v3 */
    public C0277f f588v3;

    /* renamed from: w3 */
    public C0377b f589w3;

    /* renamed from: x3 */
    public C0359v0 f590x3;

    public C0275e() {
        this.f588v3 = null;
        this.f589w3 = null;
        this.f590x3 = null;
    }

    /* renamed from: a */
    public static C0275e m23711a(Object obj) {
        if (obj instanceof C0275e) {
            return (C0275e) obj;
        }
        if (obj != null) {
            return new C0275e(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f588v3);
        c0140e.m24170a(this.f589w3);
        c0140e.m24170a(this.f590x3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0277f m23710i() {
        return this.f588v3;
    }

    /* renamed from: j */
    public C0359v0 m23709j() {
        return this.f590x3;
    }

    /* renamed from: k */
    public C0377b m23708k() {
        return this.f589w3;
    }

    public C0275e(C0277f c0277f, C0377b c0377b, C0359v0 c0359v0) {
        this.f588v3 = null;
        this.f589w3 = null;
        this.f590x3 = null;
        this.f588v3 = c0277f;
        this.f589w3 = c0377b;
        this.f590x3 = c0359v0;
    }

    public C0275e(AbstractC0263s abstractC0263s) {
        this.f588v3 = null;
        this.f589w3 = null;
        this.f590x3 = null;
        this.f588v3 = C0277f.m23699a(abstractC0263s.mo23751a(0));
        this.f589w3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
        this.f590x3 = (C0359v0) abstractC0263s.mo23751a(2);
    }
}
