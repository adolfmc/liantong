package org.p415a.p418b;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.InterfaceC12970d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.u */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12571u extends AbstractC12570t implements InterfaceC12970d<InterfaceC12554f> {

    /* renamed from: a */
    protected Vector f25515a = new Vector();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC12571u() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC12571u(C12555g c12555g) {
        for (int i = 0; i != c12555g.m1661a(); i++) {
            this.f25515a.addElement(c12555g.m1660a(i));
        }
    }

    /* renamed from: a */
    private InterfaceC12554f m1615a(Enumeration enumeration) {
        return (InterfaceC12554f) enumeration.nextElement();
    }

    /* renamed from: a */
    public InterfaceC12554f mo1616a(int i) {
        return (InterfaceC12554f) this.f25515a.elementAt(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public abstract void mo1597a(C12567r c12567r);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return true;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof AbstractC12571u) {
            AbstractC12571u abstractC12571u = (AbstractC12571u) abstractC12570t;
            if (mo1612e() != abstractC12571u.mo1612e()) {
                return false;
            }
            Enumeration mo1613d = mo1613d();
            Enumeration mo1613d2 = abstractC12571u.mo1613d();
            while (mo1613d.hasMoreElements()) {
                InterfaceC12554f m1615a = m1615a(mo1613d);
                InterfaceC12554f m1615a2 = m1615a(mo1613d2);
                AbstractC12570t mo1617h = m1615a.mo1617h();
                AbstractC12570t mo1617h2 = m1615a2.mo1617h();
                if (mo1617h != mo1617h2 && !mo1617h.equals(mo1617h2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public InterfaceC12554f[] m1614b() {
        InterfaceC12554f[] interfaceC12554fArr = new InterfaceC12554f[mo1612e()];
        for (int i = 0; i != mo1612e(); i++) {
            interfaceC12554fArr[i] = mo1616a(i);
        }
        return interfaceC12554fArr;
    }

    /* renamed from: d */
    public Enumeration mo1613d() {
        return this.f25515a.elements();
    }

    /* renamed from: e */
    public int mo1612e() {
        return this.f25515a.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: f */
    public AbstractC12570t mo1592f() {
        C12444bd c12444bd = new C12444bd();
        c12444bd.f25515a = this.f25515a;
        return c12444bd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: g */
    public AbstractC12570t mo1591g() {
        C12456bp c12456bp = new C12456bp();
        c12456bp.f25515a = this.f25515a;
        return c12456bp;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        Enumeration mo1613d = mo1613d();
        int mo1612e = mo1612e();
        while (mo1613d.hasMoreElements()) {
            mo1612e = (mo1612e * 17) ^ m1615a(mo1613d).hashCode();
        }
        return mo1612e;
    }

    @Override // java.lang.Iterable
    public Iterator<InterfaceC12554f> iterator() {
        return new C12957a.C12958a(m1614b());
    }

    public String toString() {
        return this.f25515a.toString();
    }
}
