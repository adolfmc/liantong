package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p021q2;

import java.util.Enumeration;
import java.util.Hashtable;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p024t2.C0303c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p024t2.InterfaceC0337d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2.C0487j;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.q2.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0256a {

    /* renamed from: a */
    public static final Hashtable f472a = new Hashtable();

    /* renamed from: b */
    public static final Hashtable f473b = new Hashtable();

    static {
        m23758a("B-571", InterfaceC0337d.f845F);
        m23758a("B-409", InterfaceC0337d.f843D);
        m23758a("B-283", InterfaceC0337d.f861n);
        m23758a("B-233", InterfaceC0337d.f867t);
        m23758a("B-163", InterfaceC0337d.f859l);
        m23758a("K-571", InterfaceC0337d.f844E);
        m23758a("K-409", InterfaceC0337d.f842C);
        m23758a("K-283", InterfaceC0337d.f860m);
        m23758a("K-233", InterfaceC0337d.f866s);
        m23758a("K-163", InterfaceC0337d.f849b);
        m23758a("P-521", InterfaceC0337d.f841B);
        m23758a("P-384", InterfaceC0337d.f840A);
        m23758a("P-256", InterfaceC0337d.f847H);
        m23758a("P-224", InterfaceC0337d.f873z);
        m23758a("P-192", InterfaceC0337d.f846G);
    }

    /* renamed from: a */
    public static void m23758a(String str, C0178n c0178n) {
        f472a.put(str, c0178n);
        f473b.put(c0178n, str);
    }

    /* renamed from: b */
    public static C0178n m23756b(String str) {
        return (C0178n) f472a.get(C0678j.m22438d(str));
    }

    /* renamed from: b */
    public static String m23757b(C0178n c0178n) {
        return (String) f473b.get(c0178n);
    }

    /* renamed from: a */
    public static C0487j m23759a(String str) {
        C0178n c0178n = (C0178n) f472a.get(C0678j.m22438d(str));
        if (c0178n != null) {
            return m23760a(c0178n);
        }
        return null;
    }

    /* renamed from: a */
    public static C0487j m23760a(C0178n c0178n) {
        return C0303c.m23591a(c0178n);
    }

    /* renamed from: a */
    public static Enumeration m23761a() {
        return f472a.keys();
    }
}
