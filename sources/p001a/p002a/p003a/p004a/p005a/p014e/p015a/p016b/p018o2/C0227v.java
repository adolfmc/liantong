package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0153h;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.o2.v */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0227v extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0182o f379v3;

    /* renamed from: w3 */
    public C0153h f380w3;

    /* renamed from: x3 */
    public C0194e0 f381x3;

    public C0227v(byte[] bArr, C0153h c0153h, C0194e0 c0194e0) {
        this.f379v3 = new C0168k1(bArr);
        this.f380w3 = c0153h;
        this.f381x3 = c0194e0;
    }

    /* renamed from: a */
    public static C0227v m23882a(AbstractC0494y abstractC0494y, boolean z) {
        return m23881a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f379v3);
        C0153h c0153h = this.f380w3;
        if (c0153h != null) {
            c0140e.m24170a(c0153h);
        }
        C0194e0 c0194e0 = this.f381x3;
        if (c0194e0 != null) {
            c0140e.m24170a(c0194e0);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0153h m23880i() {
        return this.f380w3;
    }

    /* renamed from: j */
    public AbstractC0182o m23879j() {
        return this.f379v3;
    }

    /* renamed from: k */
    public C0194e0 m23878k() {
        return this.f381x3;
    }

    /* renamed from: a */
    public static C0227v m23881a(Object obj) {
        if (obj != null && !(obj instanceof C0227v)) {
            if (obj instanceof AbstractC0263s) {
                return new C0227v((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid KEKIdentifier: " + obj.getClass().getName());
        }
        return (C0227v) obj;
    }

    public C0227v(AbstractC0263s abstractC0263s) {
        this.f379v3 = (AbstractC0182o) abstractC0263s.mo23751a(0);
        int mo23745o = abstractC0263s.mo23745o();
        if (mo23745o != 1) {
            if (mo23745o != 2) {
                if (mo23745o == 3) {
                    this.f380w3 = (C0153h) abstractC0263s.mo23751a(1);
                    this.f381x3 = C0194e0.m24032a(abstractC0263s.mo23751a(2));
                    return;
                }
                throw new IllegalArgumentException("Invalid KEKIdentifier");
            } else if (abstractC0263s.mo23751a(1) instanceof C0153h) {
                this.f380w3 = (C0153h) abstractC0263s.mo23751a(1);
            } else {
                this.f381x3 = C0194e0.m24032a(abstractC0263s.mo23751a(1));
            }
        }
    }
}
