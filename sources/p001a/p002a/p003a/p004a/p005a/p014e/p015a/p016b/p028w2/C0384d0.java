package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Vector;

/* renamed from: a.a.a.a.a.e.a.b.w2.d0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0384d0 {

    /* renamed from: a */
    public Vector f1046a = new Vector();

    /* renamed from: a */
    public C0384d0 m23417a(C0381c0 c0381c0) {
        C0378b0[] m23426i = c0381c0.m23426i();
        for (int i = 0; i != m23426i.length; i++) {
            this.f1046a.addElement(m23426i[i]);
        }
        return this;
    }

    /* renamed from: a */
    public C0384d0 m23418a(C0378b0 c0378b0) {
        this.f1046a.addElement(c0378b0);
        return this;
    }

    /* renamed from: a */
    public C0381c0 m23419a() {
        int size = this.f1046a.size();
        C0378b0[] c0378b0Arr = new C0378b0[size];
        for (int i = 0; i != size; i++) {
            c0378b0Arr[i] = (C0378b0) this.f1046a.elementAt(i);
        }
        return new C0381c0(c0378b0Arr);
    }
}
