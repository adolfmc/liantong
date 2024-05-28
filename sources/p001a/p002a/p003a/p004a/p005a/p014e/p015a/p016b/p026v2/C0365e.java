package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2;

import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.p027g.C0368b;

/* renamed from: a.a.a.a.a.e.a.b.v2.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0365e {

    /* renamed from: a */
    public InterfaceC0366f f940a;

    /* renamed from: b */
    public Vector f941b;

    public C0365e() {
        this(C0368b.f955a);
    }

    /* renamed from: a */
    public C0365e m23531a(C0178n c0178n, String str) {
        m23532a(c0178n, this.f940a.mo23524a(c0178n, str));
        return this;
    }

    public C0365e(InterfaceC0366f interfaceC0366f) {
        this.f941b = new Vector();
        this.f940a = interfaceC0366f;
    }

    /* renamed from: a */
    public C0365e m23532a(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f941b.addElement(new C0363c(c0178n, interfaceC0136d));
        return this;
    }

    /* renamed from: a */
    public C0365e m23530a(C0361a c0361a) {
        this.f941b.addElement(new C0363c(c0361a));
        return this;
    }

    /* renamed from: a */
    public C0365e m23528a(C0178n[] c0178nArr, String[] strArr) {
        int length = strArr.length;
        InterfaceC0136d[] interfaceC0136dArr = new InterfaceC0136d[length];
        for (int i = 0; i != length; i++) {
            interfaceC0136dArr[i] = this.f940a.mo23524a(c0178nArr[i], strArr[i]);
        }
        return m23529a(c0178nArr, interfaceC0136dArr);
    }

    /* renamed from: a */
    public C0365e m23529a(C0178n[] c0178nArr, InterfaceC0136d[] interfaceC0136dArr) {
        C0361a[] c0361aArr = new C0361a[c0178nArr.length];
        for (int i = 0; i != c0178nArr.length; i++) {
            c0361aArr[i] = new C0361a(c0178nArr[i], interfaceC0136dArr[i]);
        }
        return m23527a(c0361aArr);
    }

    /* renamed from: a */
    public C0365e m23527a(C0361a[] c0361aArr) {
        this.f941b.addElement(new C0363c(c0361aArr));
        return this;
    }

    /* renamed from: a */
    public C0364d m23533a() {
        int size = this.f941b.size();
        C0363c[] c0363cArr = new C0363c[size];
        for (int i = 0; i != size; i++) {
            c0363cArr[i] = (C0363c) this.f941b.elementAt(i);
        }
        return new C0364d(this.f940a, c0363cArr);
    }
}
