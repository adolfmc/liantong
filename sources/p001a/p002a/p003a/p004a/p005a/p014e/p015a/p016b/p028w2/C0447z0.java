package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0138d1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0454x1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;

/* renamed from: a.a.a.a.a.e.a.b.w2.z0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0447z0 extends AbstractC0174m {

    /* renamed from: A3 */
    public AbstractC0263s f1473A3;

    /* renamed from: B3 */
    public C0446z f1474B3;

    /* renamed from: v3 */
    public C0166k f1475v3;

    /* renamed from: w3 */
    public C0377b f1476w3;

    /* renamed from: x3 */
    public C0364d f1477x3;

    /* renamed from: y3 */
    public C0391f1 f1478y3;

    /* renamed from: z3 */
    public C0391f1 f1479z3;

    /* renamed from: a.a.a.a.a.e.a.b.w2.z0$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class C0449b extends AbstractC0174m {

        /* renamed from: v3 */
        public AbstractC0263s f1480v3;

        /* renamed from: w3 */
        public C0446z f1481w3;

        public C0449b(AbstractC0263s abstractC0263s) {
            if (abstractC0263s.mo23745o() >= 2 && abstractC0263s.mo23745o() <= 3) {
                this.f1480v3 = abstractC0263s;
                return;
            }
            throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
        }

        /* renamed from: a */
        public static C0449b m23074a(Object obj) {
            if (obj instanceof C0449b) {
                return (C0449b) obj;
            }
            if (obj != null) {
                return new C0449b(AbstractC0263s.m23749a(obj));
            }
            return null;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
        /* renamed from: d */
        public AbstractC0258r mo23015d() {
            return this.f1480v3;
        }

        /* renamed from: i */
        public C0446z m23073i() {
            if (this.f1481w3 == null && this.f1480v3.mo23745o() == 3) {
                this.f1481w3 = C0446z.m23093a(this.f1480v3.mo23751a(2));
            }
            return this.f1481w3;
        }

        /* renamed from: j */
        public C0391f1 m23072j() {
            return C0391f1.m23393a(this.f1480v3.mo23751a(1));
        }

        /* renamed from: k */
        public C0166k m23071k() {
            return C0151g1.m24147a(this.f1480v3.mo23751a(0));
        }

        /* renamed from: l */
        public boolean m23070l() {
            return this.f1480v3.mo23745o() == 3;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a.a.a.a.a.e.a.b.w2.z0$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C0450c implements Enumeration {
        public C0450c() {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return null;
        }
    }

    /* renamed from: a.a.a.a.a.e.a.b.w2.z0$d */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0451d implements Enumeration {

        /* renamed from: a */
        public final Enumeration f1483a;

        public C0451d(Enumeration enumeration) {
            this.f1483a = enumeration;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f1483a.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return C0449b.m23074a(this.f1483a.nextElement());
        }
    }

    public C0447z0(AbstractC0263s abstractC0263s) {
        int i;
        if (abstractC0263s.mo23745o() >= 3 && abstractC0263s.mo23745o() <= 7) {
            int i2 = 0;
            if (abstractC0263s.mo23751a(0) instanceof C0166k) {
                this.f1475v3 = C0151g1.m24147a(abstractC0263s.mo23751a(0));
                i2 = 1;
            } else {
                this.f1475v3 = null;
            }
            int i3 = i2 + 1;
            this.f1476w3 = C0377b.m23460a(abstractC0263s.mo23751a(i2));
            int i4 = i3 + 1;
            this.f1477x3 = C0364d.m23537a(abstractC0263s.mo23751a(i3));
            int i5 = i4 + 1;
            this.f1478y3 = C0391f1.m23393a(abstractC0263s.mo23751a(i4));
            if (i5 >= abstractC0263s.mo23745o() || !((abstractC0263s.mo23751a(i5) instanceof C0454x1) || (abstractC0263s.mo23751a(i5) instanceof C0138d1) || (abstractC0263s.mo23751a(i5) instanceof C0391f1))) {
                i = i5;
            } else {
                i = i5 + 1;
                this.f1479z3 = C0391f1.m23393a(abstractC0263s.mo23751a(i5));
            }
            if (i < abstractC0263s.mo23745o() && !(abstractC0263s.mo23751a(i) instanceof C0360v1)) {
                this.f1473A3 = AbstractC0263s.m23749a(abstractC0263s.mo23751a(i));
                i++;
            }
            if (i >= abstractC0263s.mo23745o() || !(abstractC0263s.mo23751a(i) instanceof C0360v1)) {
                return;
            }
            this.f1474B3 = C0446z.m23093a(AbstractC0263s.m23750a((AbstractC0494y) abstractC0263s.mo23751a(i), true));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }

    /* renamed from: a */
    public static C0447z0 m23085a(AbstractC0494y abstractC0494y, boolean z) {
        return m23084a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0166k c0166k = this.f1475v3;
        if (c0166k != null) {
            c0140e.m24170a(c0166k);
        }
        c0140e.m24170a(this.f1476w3);
        c0140e.m24170a(this.f1477x3);
        c0140e.m24170a(this.f1478y3);
        C0391f1 c0391f1 = this.f1479z3;
        if (c0391f1 != null) {
            c0140e.m24170a(c0391f1);
        }
        AbstractC0263s abstractC0263s = this.f1473A3;
        if (abstractC0263s != null) {
            c0140e.m24170a(abstractC0263s);
        }
        C0446z c0446z = this.f1474B3;
        if (c0446z != null) {
            c0140e.m24170a(new C0360v1(0, c0446z));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0446z m23083i() {
        return this.f1474B3;
    }

    /* renamed from: j */
    public C0364d m23082j() {
        return this.f1477x3;
    }

    /* renamed from: k */
    public C0391f1 m23081k() {
        return this.f1479z3;
    }

    /* renamed from: l */
    public Enumeration m23080l() {
        AbstractC0263s abstractC0263s = this.f1473A3;
        if (abstractC0263s == null) {
            return new C0450c();
        }
        return new C0451d(abstractC0263s.mo23747m());
    }

    /* renamed from: m */
    public C0449b[] m23079m() {
        AbstractC0263s abstractC0263s = this.f1473A3;
        if (abstractC0263s == null) {
            return new C0449b[0];
        }
        int mo23745o = abstractC0263s.mo23745o();
        C0449b[] c0449bArr = new C0449b[mo23745o];
        for (int i = 0; i < mo23745o; i++) {
            c0449bArr[i] = C0449b.m23074a(this.f1473A3.mo23751a(i));
        }
        return c0449bArr;
    }

    /* renamed from: n */
    public C0377b m23078n() {
        return this.f1476w3;
    }

    /* renamed from: o */
    public C0391f1 m23077o() {
        return this.f1478y3;
    }

    /* renamed from: p */
    public C0166k m23076p() {
        return this.f1475v3;
    }

    /* renamed from: q */
    public int m23075q() {
        C0166k c0166k = this.f1475v3;
        if (c0166k == null) {
            return 1;
        }
        return c0166k.m24145n().intValue() + 1;
    }

    /* renamed from: a */
    public static C0447z0 m23084a(Object obj) {
        if (obj instanceof C0447z0) {
            return (C0447z0) obj;
        }
        if (obj != null) {
            return new C0447z0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }
}
