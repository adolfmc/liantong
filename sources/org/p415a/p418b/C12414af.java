package org.p415a.p418b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.af */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12414af extends AbstractC12565p {

    /* renamed from: b */
    private AbstractC12565p[] f25117b;

    public C12414af(byte[] bArr) {
        super(bArr);
    }

    public C12414af(AbstractC12565p[] abstractC12565pArr) {
        super(m1730a(abstractC12565pArr));
        this.f25117b = abstractC12565pArr;
    }

    /* renamed from: a */
    private static byte[] m1730a(AbstractC12565p[] abstractC12565pArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != abstractC12565pArr.length; i++) {
            try {
                byteArrayOutputStream.write(((C12435az) abstractC12565pArr[i]).mo1632d());
            } catch (IOException e) {
                throw new IllegalArgumentException("exception converting octets " + e.toString());
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(abstractC12565pArr[i].getClass().getName() + " found in input should only contain DEROctetString");
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: k */
    private Vector m1728k() {
        Vector vector = new Vector();
        int i = 0;
        while (i < this.f25510a.length) {
            int i2 = i + 1000;
            byte[] bArr = new byte[(i2 > this.f25510a.length ? this.f25510a.length : i2) - i];
            System.arraycopy(this.f25510a, i, bArr, 0, bArr.length);
            vector.addElement(new C12435az(bArr));
            i = i2;
        }
        return vector;
    }

    @Override // org.p415a.p418b.AbstractC12565p, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.mo1620b(36);
        c12567r.mo1620b(128);
        Enumeration m1729j = m1729j();
        while (m1729j.hasMoreElements()) {
            c12567r.mo1625a((InterfaceC12554f) m1729j.nextElement());
        }
        c12567r.mo1620b(0);
        c12567r.mo1620b(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        Enumeration m1729j = m1729j();
        int i = 0;
        while (m1729j.hasMoreElements()) {
            i += ((InterfaceC12554f) m1729j.nextElement()).mo1617h().mo1618c();
        }
        return i + 2 + 2;
    }

    @Override // org.p415a.p418b.AbstractC12565p
    /* renamed from: d */
    public byte[] mo1632d() {
        return this.f25510a;
    }

    /* renamed from: j */
    public Enumeration m1729j() {
        return this.f25117b == null ? m1728k().elements() : new Enumeration() { // from class: org.a.b.af.1

            /* renamed from: a */
            int f25118a = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f25118a < C12414af.this.f25117b.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                AbstractC12565p[] abstractC12565pArr = C12414af.this.f25117b;
                int i = this.f25118a;
                this.f25118a = i + 1;
                return abstractC12565pArr[i];
            }
        };
    }
}
