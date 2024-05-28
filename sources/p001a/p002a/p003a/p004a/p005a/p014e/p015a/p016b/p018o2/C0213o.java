package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0235p0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.o */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0213o extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f336v3;

    /* renamed from: w3 */
    public C0377b f337w3;

    /* renamed from: x3 */
    public AbstractC0182o f338x3;

    public C0213o(C0178n c0178n, C0377b c0377b, AbstractC0182o abstractC0182o) {
        this.f336v3 = c0178n;
        this.f337w3 = c0377b;
        this.f338x3 = abstractC0182o;
    }

    /* renamed from: a */
    public static C0213o m23945a(Object obj) {
        if (obj instanceof C0213o) {
            return (C0213o) obj;
        }
        if (obj != null) {
            return new C0213o(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f336v3);
        c0140e.m24170a(this.f337w3);
        AbstractC0182o abstractC0182o = this.f338x3;
        if (abstractC0182o != null) {
            c0140e.m24170a(new C0235p0(false, 0, abstractC0182o));
        }
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public C0377b m23944i() {
        return this.f337w3;
    }

    /* renamed from: j */
    public C0178n m23943j() {
        return this.f336v3;
    }

    /* renamed from: k */
    public AbstractC0182o m23942k() {
        return this.f338x3;
    }

    public C0213o(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() >= 2) {
            this.f336v3 = (C0178n) abstractC0263s.mo23751a(0);
            this.f337w3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
            if (abstractC0263s.mo23745o() > 2) {
                this.f338x3 = AbstractC0182o.m24090a((AbstractC0494y) abstractC0263s.mo23751a(2), false);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Truncated Sequence Found");
    }
}
