package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.io.IOException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0372w0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.p1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0421p1 {

    /* renamed from: a */
    public boolean f1267a;

    /* renamed from: b */
    public AbstractC0182o f1268b;

    /* renamed from: c */
    public static final C0178n f1243c = new C0178n("2.5.29.9");

    /* renamed from: d */
    public static final C0178n f1244d = new C0178n("2.5.29.14");

    /* renamed from: e */
    public static final C0178n f1245e = new C0178n("2.5.29.15");

    /* renamed from: f */
    public static final C0178n f1246f = new C0178n("2.5.29.16");

    /* renamed from: g */
    public static final C0178n f1247g = new C0178n("2.5.29.17");

    /* renamed from: h */
    public static final C0178n f1248h = new C0178n("2.5.29.18");

    /* renamed from: i */
    public static final C0178n f1249i = new C0178n("2.5.29.19");

    /* renamed from: j */
    public static final C0178n f1250j = new C0178n("2.5.29.20");

    /* renamed from: k */
    public static final C0178n f1251k = new C0178n("2.5.29.21");

    /* renamed from: l */
    public static final C0178n f1252l = new C0178n("2.5.29.23");

    /* renamed from: m */
    public static final C0178n f1253m = new C0178n("2.5.29.24");

    /* renamed from: n */
    public static final C0178n f1254n = new C0178n("2.5.29.27");

    /* renamed from: o */
    public static final C0178n f1255o = new C0178n("2.5.29.28");

    /* renamed from: p */
    public static final C0178n f1256p = new C0178n("2.5.29.29");

    /* renamed from: q */
    public static final C0178n f1257q = new C0178n("2.5.29.30");

    /* renamed from: r */
    public static final C0178n f1258r = new C0178n("2.5.29.31");

    /* renamed from: s */
    public static final C0178n f1259s = new C0178n("2.5.29.32");

    /* renamed from: t */
    public static final C0178n f1260t = new C0178n("2.5.29.33");

    /* renamed from: u */
    public static final C0178n f1261u = new C0178n("2.5.29.35");

    /* renamed from: v */
    public static final C0178n f1262v = new C0178n("2.5.29.36");

    /* renamed from: w */
    public static final C0178n f1263w = new C0178n("2.5.29.37");

    /* renamed from: x */
    public static final C0178n f1264x = new C0178n("2.5.29.46");

    /* renamed from: y */
    public static final C0178n f1265y = new C0178n("2.5.29.54");

    /* renamed from: z */
    public static final C0178n f1266z = new C0178n("1.3.6.1.5.5.7.1.1");

    /* renamed from: A */
    public static final C0178n f1236A = new C0178n("1.3.6.1.5.5.7.1.11");

    /* renamed from: B */
    public static final C0178n f1237B = new C0178n("1.3.6.1.5.5.7.1.12");

    /* renamed from: C */
    public static final C0178n f1238C = new C0178n("1.3.6.1.5.5.7.1.2");

    /* renamed from: D */
    public static final C0178n f1239D = new C0178n("1.3.6.1.5.5.7.1.3");

    /* renamed from: E */
    public static final C0178n f1240E = new C0178n("1.3.6.1.5.5.7.1.4");

    /* renamed from: F */
    public static final C0178n f1241F = new C0178n("2.5.29.56");

    /* renamed from: G */
    public static final C0178n f1242G = new C0178n("2.5.29.55");

    public C0421p1(C0372w0 c0372w0, AbstractC0182o abstractC0182o) {
        this.f1267a = c0372w0.m23484m();
        this.f1268b = abstractC0182o;
    }

    /* renamed from: a */
    public InterfaceC0136d m23211a() {
        return m23210a(this);
    }

    /* renamed from: b */
    public AbstractC0182o m23209b() {
        return this.f1268b;
    }

    /* renamed from: c */
    public boolean m23208c() {
        return this.f1267a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0421p1) {
            C0421p1 c0421p1 = (C0421p1) obj;
            return c0421p1.m23209b().equals(m23209b()) && c0421p1.m23208c() == m23208c();
        }
        return false;
    }

    public int hashCode() {
        if (m23208c()) {
            return m23209b().hashCode();
        }
        return ~m23209b().hashCode();
    }

    /* renamed from: a */
    public static AbstractC0258r m23210a(C0421p1 c0421p1) {
        try {
            return AbstractC0258r.m23755a(c0421p1.m23209b().mo24088m());
        } catch (IOException e) {
            throw new IllegalArgumentException("can't convert extension: " + e);
        }
    }

    public C0421p1(boolean z, AbstractC0182o abstractC0182o) {
        this.f1267a = z;
        this.f1268b = abstractC0182o;
    }
}
