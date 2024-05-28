package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.util.Enumeration;
import java.util.Vector;

/* renamed from: a.a.a.a.a.e.a.b.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0140e {

    /* renamed from: a */
    public Vector f163a = new Vector();

    /* renamed from: a */
    public void m24170a(InterfaceC0136d interfaceC0136d) {
        this.f163a.addElement(interfaceC0136d);
    }

    /* renamed from: a */
    public void m24169a(C0140e c0140e) {
        Enumeration elements = c0140e.f163a.elements();
        while (elements.hasMoreElements()) {
            this.f163a.addElement(elements.nextElement());
        }
    }

    /* renamed from: a */
    public InterfaceC0136d m24171a(int i) {
        return (InterfaceC0136d) this.f163a.elementAt(i);
    }

    /* renamed from: a */
    public int m24172a() {
        return this.f163a.size();
    }
}
