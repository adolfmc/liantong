package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0260r1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0187b {

    /* renamed from: a */
    public Hashtable f242a;

    public C0187b(Hashtable hashtable) {
        this.f242a = new Hashtable();
        this.f242a = m24071a(hashtable);
    }

    /* renamed from: a */
    private void m24072a(C0178n c0178n, C0185a c0185a) {
        Vector vector;
        Object obj = this.f242a.get(c0178n);
        if (obj == null) {
            this.f242a.put(c0178n, c0185a);
            return;
        }
        if (obj instanceof C0185a) {
            vector = new Vector();
            vector.addElement(obj);
            vector.addElement(c0185a);
        } else {
            vector = (Vector) obj;
            vector.addElement(c0185a);
        }
        this.f242a.put(c0178n, vector);
    }

    /* renamed from: b */
    public C0140e m24069b(C0164j1 c0164j1) {
        return m24068b(new C0178n(c0164j1.m24113n()));
    }

    /* renamed from: c */
    public C0189c m24067c() {
        return new C0189c(m24070b());
    }

    /* renamed from: d */
    public Hashtable m24065d() {
        return m24071a(this.f242a);
    }

    /* renamed from: b */
    public C0140e m24068b(C0178n c0178n) {
        C0140e c0140e = new C0140e();
        Object obj = this.f242a.get(c0178n);
        if (obj instanceof Vector) {
            Enumeration elements = ((Vector) obj).elements();
            while (elements.hasMoreElements()) {
                c0140e.m24170a((C0185a) elements.nextElement());
            }
        } else if (obj != null) {
            c0140e.m24170a((C0185a) obj);
        }
        return c0140e;
    }

    /* renamed from: c */
    public C0187b m24066c(C0178n c0178n) {
        C0187b c0187b = new C0187b(this.f242a);
        c0187b.f242a.remove(c0178n);
        return c0187b;
    }

    public C0187b(C0140e c0140e) {
        this.f242a = new Hashtable();
        for (int i = 0; i != c0140e.m24172a(); i++) {
            C0185a m24085a = C0185a.m24085a(c0140e.m24171a(i));
            m24072a(m24085a.m24084i(), m24085a);
        }
    }

    /* renamed from: b */
    public C0140e m24070b() {
        C0140e c0140e = new C0140e();
        Enumeration elements = this.f242a.elements();
        while (elements.hasMoreElements()) {
            Object nextElement = elements.nextElement();
            if (nextElement instanceof Vector) {
                Enumeration elements2 = ((Vector) nextElement).elements();
                while (elements2.hasMoreElements()) {
                    c0140e.m24170a(C0185a.m24085a(elements2.nextElement()));
                }
            } else {
                c0140e.m24170a(C0185a.m24085a(nextElement));
            }
        }
        return c0140e;
    }

    public C0187b(AbstractC0338u abstractC0338u) {
        this.f242a = new Hashtable();
        for (int i = 0; i != abstractC0338u.m23576o(); i++) {
            C0185a m24085a = C0185a.m24085a(abstractC0338u.m23584a(i));
            m24072a(m24085a.m24084i(), m24085a);
        }
    }

    /* renamed from: a */
    public C0185a m24075a(C0164j1 c0164j1) {
        return m24074a(new C0178n(c0164j1.m24113n()));
    }

    /* renamed from: a */
    public C0185a m24074a(C0178n c0178n) {
        Object obj = this.f242a.get(c0178n);
        if (obj instanceof Vector) {
            return (C0185a) ((Vector) obj).elementAt(0);
        }
        return (C0185a) obj;
    }

    /* renamed from: a */
    public int m24076a() {
        Enumeration elements = this.f242a.elements();
        int i = 0;
        while (elements.hasMoreElements()) {
            Object nextElement = elements.nextElement();
            i = nextElement instanceof Vector ? i + ((Vector) nextElement).size() : i + 1;
        }
        return i;
    }

    /* renamed from: a */
    private Hashtable m24071a(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            hashtable2.put(nextElement, hashtable.get(nextElement));
        }
        return hashtable2;
    }

    /* renamed from: a */
    public C0187b m24073a(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        C0187b c0187b = new C0187b(this.f242a);
        c0187b.m24072a(c0178n, new C0185a(c0178n, (AbstractC0338u) new C0260r1(interfaceC0136d)));
        return c0187b;
    }

    public C0187b(C0185a c0185a) {
        this.f242a = new Hashtable();
        m24072a(c0185a.m24084i(), c0185a);
    }

    public C0187b(C0189c c0189c) {
        this(AbstractC0338u.m23581a((Object) c0189c.mo23015d()));
    }
}
