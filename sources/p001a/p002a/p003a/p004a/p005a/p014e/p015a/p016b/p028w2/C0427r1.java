package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.r1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0427r1 {

    /* renamed from: a */
    public Hashtable f1310a = new Hashtable();

    /* renamed from: b */
    public Vector f1311b = new Vector();

    /* renamed from: a */
    public void m23186a(C0164j1 c0164j1, boolean z, InterfaceC0136d interfaceC0136d) {
        m23184a(new C0178n(c0164j1.m24113n()), z, interfaceC0136d);
    }

    /* renamed from: b */
    public boolean m23182b() {
        return this.f1311b.isEmpty();
    }

    /* renamed from: c */
    public void m23181c() {
        this.f1310a = new Hashtable();
        this.f1311b = new Vector();
    }

    /* renamed from: a */
    public void m23185a(C0164j1 c0164j1, boolean z, byte[] bArr) {
        m23183a(new C0178n(c0164j1.m24113n()), z, bArr);
    }

    /* renamed from: a */
    public void m23184a(C0178n c0178n, boolean z, InterfaceC0136d interfaceC0136d) {
        try {
            m23183a(c0178n, z, interfaceC0136d.mo23015d().m24102a("DER"));
        } catch (IOException e) {
            throw new IllegalArgumentException("error encoding value: " + e);
        }
    }

    /* renamed from: a */
    public void m23183a(C0178n c0178n, boolean z, byte[] bArr) {
        if (!this.f1310a.containsKey(c0178n)) {
            this.f1311b.addElement(c0178n);
            this.f1310a.put(c0178n, new C0421p1(z, new C0168k1(bArr)));
            return;
        }
        throw new IllegalArgumentException("extension " + c0178n + " already added");
    }

    /* renamed from: a */
    public C0424q1 m23187a() {
        return new C0424q1(this.f1311b, this.f1310a);
    }
}
