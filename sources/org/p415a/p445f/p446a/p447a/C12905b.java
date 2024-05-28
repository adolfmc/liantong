package org.p415a.p445f.p446a.p447a;

import java.io.IOException;
import org.p415a.p424c.C12620q;
import org.p415a.p424c.C12626w;
import org.p415a.p424c.C12628y;
import org.p415a.p424c.InterfaceC12606c;
import org.p415a.p427d.InterfaceC12724i;
import org.p415a.p427d.p428a.C12634d;
import org.p415a.p427d.p428a.C12636f;
import org.p415a.p445f.C12934f;
import org.p415a.p445f.p446a.InterfaceC12903a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12905b implements InterfaceC12903a {
    @Override // org.p415a.p445f.p446a.InterfaceC12903a
    /* renamed from: a */
    public byte[] mo504a(C12626w c12626w) {
        InterfaceC12724i interfaceC12724i;
        InterfaceC12606c m1539c = c12626w.m1539c();
        if (c12626w.m1541a() <= 3) {
            C12628y c12628y = (C12628y) m1539c;
            try {
                interfaceC12724i = new C12634d();
                byte[] a = new C12620q(c12628y.m1534c()).mo1536a();
                interfaceC12724i.mo1311a(a, 2, a.length - 2);
                byte[] a2 = new C12620q(c12628y.m1535b()).mo1536a();
                interfaceC12724i.mo1311a(a2, 2, a2.length - 2);
            } catch (IOException e) {
                throw new C12934f("can't encode key components: " + e.getMessage(), e);
            }
        } else {
            try {
                byte[] m1538d = c12626w.m1538d();
                C12636f c12636f = new C12636f();
                c12636f.mo1313a((byte) -103);
                c12636f.mo1313a((byte) (m1538d.length >> 8));
                c12636f.mo1313a((byte) m1538d.length);
                c12636f.mo1311a(m1538d, 0, m1538d.length);
                interfaceC12724i = c12636f;
            } catch (IOException e2) {
                throw new C12934f("can't encode key components: " + e2.getMessage(), e2);
            }
        }
        byte[] bArr = new byte[interfaceC12724i.mo1314a()];
        interfaceC12724i.mo1312a(bArr, 0);
        return bArr;
    }
}
