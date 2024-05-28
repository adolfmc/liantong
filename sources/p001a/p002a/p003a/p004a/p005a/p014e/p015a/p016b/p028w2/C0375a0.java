package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Hashtable;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.a0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0375a0 {

    /* renamed from: a */
    public Hashtable f997a = new Hashtable();

    /* renamed from: b */
    public Vector f998b = new Vector();

    /* renamed from: a */
    public void m23479a(C0178n c0178n, boolean z, InterfaceC0136d interfaceC0136d) {
        m23478a(c0178n, z, interfaceC0136d.mo23015d().m24102a("DER"));
    }

    /* renamed from: b */
    public boolean m23477b() {
        return this.f998b.isEmpty();
    }

    /* renamed from: c */
    public void m23476c() {
        this.f997a = new Hashtable();
        this.f998b = new Vector();
    }

    /* renamed from: a */
    public void m23478a(C0178n c0178n, boolean z, byte[] bArr) {
        if (!this.f997a.containsKey(c0178n)) {
            this.f998b.addElement(c0178n);
            this.f997a.put(c0178n, new C0444y(c0178n, z, new C0168k1(bArr)));
            return;
        }
        throw new IllegalArgumentException("extension " + c0178n + " already added");
    }

    /* renamed from: a */
    public C0446z m23480a() {
        C0444y[] c0444yArr = new C0444y[this.f998b.size()];
        for (int i = 0; i != this.f998b.size(); i++) {
            c0444yArr[i] = (C0444y) this.f997a.get(this.f998b.elementAt(i));
        }
        return new C0446z(c0444yArr);
    }
}
