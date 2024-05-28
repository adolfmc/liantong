package org.p415a.p418b;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.InterfaceC12970d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.w */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12573w extends AbstractC12570t implements InterfaceC12970d<InterfaceC12554f> {

    /* renamed from: a */
    private Vector f25516a = new Vector();

    /* renamed from: b */
    private boolean f25517b = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC12573w() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC12573w(C12555g c12555g, boolean z) {
        for (int i = 0; i != c12555g.m1661a(); i++) {
            this.f25516a.addElement(c12555g.m1660a(i));
        }
        if (z) {
            m1603j();
        }
    }

    /* renamed from: a */
    private InterfaceC12554f m1609a(Enumeration enumeration) {
        InterfaceC12554f interfaceC12554f = (InterfaceC12554f) enumeration.nextElement();
        return interfaceC12554f == null ? C12433ax.f25141a : interfaceC12554f;
    }

    /* renamed from: a */
    private boolean m1607a(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        for (int i = 0; i != min; i++) {
            if (bArr[i] != bArr2[i]) {
                return (bArr[i] & 255) < (bArr2[i] & 255);
            }
        }
        return min == bArr.length;
    }

    /* renamed from: a */
    private byte[] m1608a(InterfaceC12554f interfaceC12554f) {
        try {
            return interfaceC12554f.mo1617h().m1644a("DER");
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    /* renamed from: a */
    public InterfaceC12554f m1610a(int i) {
        return (InterfaceC12554f) this.f25516a.elementAt(i);
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
        if (abstractC12570t instanceof AbstractC12573w) {
            AbstractC12573w abstractC12573w = (AbstractC12573w) abstractC12570t;
            if (m1605d() != abstractC12573w.m1605d()) {
                return false;
            }
            Enumeration m1606b = m1606b();
            Enumeration m1606b2 = abstractC12573w.m1606b();
            while (m1606b.hasMoreElements()) {
                InterfaceC12554f m1609a = m1609a(m1606b);
                InterfaceC12554f m1609a2 = m1609a(m1606b2);
                AbstractC12570t mo1617h = m1609a.mo1617h();
                AbstractC12570t mo1617h2 = m1609a2.mo1617h();
                if (mo1617h != mo1617h2 && !mo1617h.equals(mo1617h2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public Enumeration m1606b() {
        return this.f25516a.elements();
    }

    /* renamed from: d */
    public int m1605d() {
        return this.f25516a.size();
    }

    /* renamed from: e */
    public InterfaceC12554f[] m1604e() {
        InterfaceC12554f[] interfaceC12554fArr = new InterfaceC12554f[m1605d()];
        for (int i = 0; i != m1605d(); i++) {
            interfaceC12554fArr[i] = m1610a(i);
        }
        return interfaceC12554fArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: f */
    public AbstractC12570t mo1592f() {
        if (this.f25517b) {
            C12446bf c12446bf = new C12446bf();
            ((AbstractC12573w) c12446bf).f25516a = this.f25516a;
            return c12446bf;
        }
        Vector vector = new Vector();
        for (int i = 0; i != this.f25516a.size(); i++) {
            vector.addElement(this.f25516a.elementAt(i));
        }
        C12446bf c12446bf2 = new C12446bf();
        ((AbstractC12573w) c12446bf2).f25516a = vector;
        c12446bf2.m1603j();
        return c12446bf2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: g */
    public AbstractC12570t mo1591g() {
        C12457bq c12457bq = new C12457bq();
        ((AbstractC12573w) c12457bq).f25516a = this.f25516a;
        return c12457bq;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        Enumeration m1606b = m1606b();
        int m1605d = m1605d();
        while (m1606b.hasMoreElements()) {
            m1605d = (m1605d * 17) ^ m1609a(m1606b).hashCode();
        }
        return m1605d;
    }

    @Override // java.lang.Iterable
    public Iterator<InterfaceC12554f> iterator() {
        return new C12957a.C12958a(m1604e());
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* renamed from: j */
    protected void m1603j() {
        /*
            r9 = this;
            boolean r0 = r9.f25517b
            if (r0 != 0) goto L5d
            r0 = 1
            r9.f25517b = r0
            java.util.Vector r1 = r9.f25516a
            int r1 = r1.size()
            if (r1 <= r0) goto L5d
            java.util.Vector r1 = r9.f25516a
            int r1 = r1.size()
            int r1 = r1 - r0
            r2 = r1
            r1 = r0
        L18:
            if (r1 == 0) goto L5d
            java.util.Vector r1 = r9.f25516a
            r3 = 0
            java.lang.Object r1 = r1.elementAt(r3)
            org.a.b.f r1 = (org.p415a.p418b.InterfaceC12554f) r1
            byte[] r1 = r9.m1608a(r1)
            r5 = r1
            r1 = r3
            r4 = r1
        L2a:
            if (r3 == r2) goto L5a
            java.util.Vector r6 = r9.f25516a
            int r7 = r3 + 1
            java.lang.Object r6 = r6.elementAt(r7)
            org.a.b.f r6 = (org.p415a.p418b.InterfaceC12554f) r6
            byte[] r6 = r9.m1608a(r6)
            boolean r8 = r9.m1607a(r5, r6)
            if (r8 == 0) goto L42
            r5 = r6
            goto L58
        L42:
            java.util.Vector r1 = r9.f25516a
            java.lang.Object r1 = r1.elementAt(r3)
            java.util.Vector r4 = r9.f25516a
            java.lang.Object r6 = r4.elementAt(r7)
            r4.setElementAt(r6, r3)
            java.util.Vector r4 = r9.f25516a
            r4.setElementAt(r1, r7)
            r4 = r0
            r1 = r3
        L58:
            r3 = r7
            goto L2a
        L5a:
            r2 = r1
            r1 = r4
            goto L18
        L5d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p418b.AbstractC12573w.m1603j():void");
    }

    public String toString() {
        return this.f25516a.toString();
    }
}
