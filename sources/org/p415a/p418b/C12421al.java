package org.p415a.p418b;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.al */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12421al extends AbstractC12576z {
    public C12421al(boolean z, int i, InterfaceC12554f interfaceC12554f) {
        super(z, i, interfaceC12554f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12576z, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        Enumeration m1606b;
        c12567r.m1628a(C0567f.f1819h, this.f25521a);
        c12567r.mo1620b(128);
        if (!this.f25522b) {
            if (this.f25523c) {
                c12567r.mo1625a(this.f25524d);
            } else {
                if (this.f25524d instanceof AbstractC12565p) {
                    m1606b = this.f25524d instanceof C12414af ? ((C12414af) this.f25524d).m1729j() : new C12414af(((AbstractC12565p) this.f25524d).mo1632d()).m1729j();
                } else if (this.f25524d instanceof AbstractC12571u) {
                    m1606b = ((AbstractC12571u) this.f25524d).mo1613d();
                } else if (!(this.f25524d instanceof AbstractC12573w)) {
                    throw new C12557i("not implemented: " + this.f25524d.getClass().getName());
                } else {
                    m1606b = ((AbstractC12573w) this.f25524d).m1606b();
                }
                while (m1606b.hasMoreElements()) {
                    c12567r.mo1625a((InterfaceC12554f) m1606b.nextElement());
                }
            }
        }
        c12567r.mo1620b(0);
        c12567r.mo1620b(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        if (this.f25522b || this.f25523c) {
            return true;
        }
        return this.f25524d.mo1617h().mo1592f().mo1611a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        int m1692b;
        if (this.f25522b) {
            return C12466bz.m1692b(this.f25521a) + 1;
        }
        int mo1618c = this.f25524d.mo1617h().mo1618c();
        if (this.f25523c) {
            m1692b = C12466bz.m1692b(this.f25521a) + C12466bz.m1694a(mo1618c);
        } else {
            mo1618c--;
            m1692b = C12466bz.m1692b(this.f25521a);
        }
        return m1692b + mo1618c;
    }
}
