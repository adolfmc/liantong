package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.io.IOException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0125a0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0153h;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;

/* renamed from: a.a.a.a.a.e.a.b.w2.k1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0406k1 {

    /* renamed from: h */
    public static final AbstractC0263s[] f1153h;

    /* renamed from: b */
    public C0377b f1155b;

    /* renamed from: c */
    public C0364d f1156c;

    /* renamed from: d */
    public C0391f1 f1157d;

    /* renamed from: a */
    public C0166k f1154a = new C0166k(1);

    /* renamed from: e */
    public C0391f1 f1158e = null;

    /* renamed from: f */
    public C0446z f1159f = null;

    /* renamed from: g */
    public C0140e f1160g = new C0140e();

    static {
        AbstractC0263s[] abstractC0263sArr = new AbstractC0263s[11];
        f1153h = abstractC0263sArr;
        abstractC0263sArr[0] = m23305a(0);
        f1153h[1] = m23305a(1);
        f1153h[2] = m23305a(2);
        f1153h[3] = m23305a(3);
        f1153h[4] = m23305a(4);
        f1153h[5] = m23305a(5);
        f1153h[6] = m23305a(6);
        f1153h[7] = m23305a(7);
        f1153h[8] = m23305a(8);
        f1153h[9] = m23305a(9);
        f1153h[10] = m23305a(10);
    }

    /* renamed from: a */
    public void m23295a(C0377b c0377b) {
        this.f1155b = c0377b;
    }

    /* renamed from: b */
    public void m23290b(C0125a0 c0125a0) {
        this.f1157d = new C0391f1(c0125a0);
    }

    /* renamed from: a */
    public void m23292a(C0430s1 c0430s1) {
        this.f1156c = C0364d.m23537a(c0430s1.mo23015d());
    }

    /* renamed from: b */
    public void m23289b(C0391f1 c0391f1) {
        this.f1157d = c0391f1;
    }

    /* renamed from: a */
    public void m23296a(C0364d c0364d) {
        this.f1156c = c0364d;
    }

    /* renamed from: a */
    public void m23304a(C0125a0 c0125a0) {
        this.f1158e = new C0391f1(c0125a0);
    }

    /* renamed from: a */
    public void m23294a(C0391f1 c0391f1) {
        this.f1158e = c0391f1;
    }

    /* renamed from: a */
    public void m23297a(AbstractC0263s abstractC0263s) {
        this.f1160g.m24170a(abstractC0263s);
    }

    /* renamed from: a */
    public void m23302a(C0166k c0166k, C0125a0 c0125a0, int i) {
        m23301a(c0166k, new C0391f1(c0125a0), i);
    }

    /* renamed from: a */
    public void m23301a(C0166k c0166k, C0391f1 c0391f1, int i) {
        m23300a(c0166k, c0391f1, i, null);
    }

    /* renamed from: a */
    public void m23300a(C0166k c0166k, C0391f1 c0391f1, int i, C0153h c0153h) {
        if (i == 0) {
            if (c0153h != null) {
                C0140e c0140e = new C0140e();
                c0140e.m24170a(m23303a(c0153h));
                m23299a(c0166k, c0391f1, new C0184o1(c0140e));
                return;
            }
            m23298a(c0166k, c0391f1, (C0446z) null);
            return;
        }
        C0140e c0140e2 = new C0140e();
        AbstractC0263s[] abstractC0263sArr = f1153h;
        if (i >= abstractC0263sArr.length) {
            c0140e2.m24170a(m23305a(i));
        } else if (i >= 0) {
            c0140e2.m24170a(abstractC0263sArr[i]);
        } else {
            throw new IllegalArgumentException("invalid reason value: " + i);
        }
        if (c0153h != null) {
            c0140e2.m24170a(m23303a(c0153h));
        }
        m23299a(c0166k, c0391f1, new C0184o1(c0140e2));
    }

    /* renamed from: a */
    private void m23299a(C0166k c0166k, C0391f1 c0391f1, AbstractC0263s abstractC0263s) {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(c0166k);
        c0140e.m24170a(c0391f1);
        if (abstractC0263s != null) {
            c0140e.m24170a(abstractC0263s);
        }
        m23297a(new C0184o1(c0140e));
    }

    /* renamed from: a */
    public void m23298a(C0166k c0166k, C0391f1 c0391f1, C0446z c0446z) {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(c0166k);
        c0140e.m24170a(c0391f1);
        if (c0446z != null) {
            c0140e.m24170a(c0446z);
        }
        m23297a(new C0184o1(c0140e));
    }

    /* renamed from: a */
    public void m23293a(C0424q1 c0424q1) {
        m23291a(C0446z.m23093a(c0424q1));
    }

    /* renamed from: a */
    public void m23291a(C0446z c0446z) {
        this.f1159f = c0446z;
    }

    /* renamed from: a */
    public C0447z0 m23306a() {
        if (this.f1155b != null && this.f1156c != null && this.f1157d != null) {
            C0140e c0140e = new C0140e();
            c0140e.m24170a(this.f1154a);
            c0140e.m24170a(this.f1155b);
            c0140e.m24170a(this.f1156c);
            c0140e.m24170a(this.f1157d);
            C0391f1 c0391f1 = this.f1158e;
            if (c0391f1 != null) {
                c0140e.m24170a(c0391f1);
            }
            if (this.f1160g.m24172a() != 0) {
                c0140e.m24170a(new C0184o1(this.f1160g));
            }
            C0446z c0446z = this.f1159f;
            if (c0446z != null) {
                c0140e.m24170a(new C0360v1(0, c0446z));
            }
            return new C0447z0(new C0184o1(c0140e));
        }
        throw new IllegalStateException("Not all mandatory fields set in V2 TBSCertList generator.");
    }

    /* renamed from: a */
    public static AbstractC0263s m23305a(int i) {
        C0140e c0140e = new C0140e();
        C0410m m23266a = C0410m.m23266a(i);
        try {
            c0140e.m24170a(C0444y.f1441G3);
            c0140e.m24170a(new C0168k1(m23266a.m24101g()));
            return new C0184o1(c0140e);
        } catch (IOException e) {
            throw new IllegalArgumentException("error encoding reason: " + e);
        }
    }

    /* renamed from: a */
    public static AbstractC0263s m23303a(C0153h c0153h) {
        C0140e c0140e = new C0140e();
        try {
            c0140e.m24170a(C0444y.f1443I3);
            c0140e.m24170a(new C0168k1(c0153h.m24101g()));
            return new C0184o1(c0140e);
        } catch (IOException e) {
            throw new IllegalArgumentException("error encoding reason: " + e);
        }
    }
}
