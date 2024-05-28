package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0146f1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;

/* renamed from: a.a.a.a.a.e.a.b.o2.u0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0226u0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f374v3;

    /* renamed from: w3 */
    public C0146f1 f375w3;

    /* renamed from: x3 */
    public C0186a0 f376x3;

    /* renamed from: y3 */
    public AbstractC0182o f377y3;

    /* renamed from: z3 */
    public C0223t f378z3;

    public C0226u0(C0146f1 c0146f1, C0186a0 c0186a0, AbstractC0182o abstractC0182o, C0223t c0223t) {
        this.f374v3 = new C0166k(1L);
        this.f375w3 = c0146f1;
        this.f376x3 = c0186a0;
        this.f377y3 = abstractC0182o;
        this.f378z3 = c0223t;
    }

    /* renamed from: a */
    public static C0226u0 m23887a(Object obj) {
        if (obj instanceof C0226u0) {
            return (C0226u0) obj;
        }
        if (obj != null) {
            return new C0226u0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f374v3);
        C0146f1 c0146f1 = this.f375w3;
        if (c0146f1 != null) {
            c0140e.m24170a(c0146f1);
        }
        C0186a0 c0186a0 = this.f376x3;
        if (c0186a0 != null) {
            c0140e.m24170a(c0186a0);
        }
        AbstractC0182o abstractC0182o = this.f377y3;
        if (abstractC0182o != null) {
            c0140e.m24170a(abstractC0182o);
        }
        c0140e.m24170a(this.f378z3);
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public AbstractC0182o m23886i() {
        return this.f377y3;
    }

    /* renamed from: j */
    public C0146f1 m23885j() {
        return this.f375w3;
    }

    /* renamed from: k */
    public C0186a0 m23884k() {
        return this.f376x3;
    }

    /* renamed from: l */
    public C0223t m23883l() {
        return this.f378z3;
    }

    public C0226u0(AbstractC0263s abstractC0263s) {
        this.f374v3 = C0151g1.m24147a(abstractC0263s.mo23751a(0));
        int i = 1;
        if (abstractC0263s.mo23751a(1) instanceof C0146f1) {
            this.f375w3 = C0146f1.m24157a(abstractC0263s.mo23751a(1));
            i = 2;
        }
        if ((abstractC0263s.mo23751a(i) instanceof C0186a0) || (abstractC0263s.mo23751a(i) instanceof AbstractC0263s)) {
            this.f376x3 = C0186a0.m24081a(abstractC0263s.mo23751a(i));
            i++;
        }
        if (abstractC0263s.mo23751a(i) instanceof AbstractC0182o) {
            this.f377y3 = AbstractC0182o.m24089a(abstractC0263s.mo23751a(i));
            i++;
        }
        this.f378z3 = C0223t.m23895a(abstractC0263s.mo23751a(i));
    }
}
