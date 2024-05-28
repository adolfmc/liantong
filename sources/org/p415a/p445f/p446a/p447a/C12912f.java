package org.p415a.p445f.p446a.p447a;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.p415a.p418b.p423e.C12547d;
import org.p415a.p424c.C12613j;
import org.p415a.p424c.C12620q;
import org.p415a.p427d.C12725j;
import org.p415a.p427d.C12727l;
import org.p415a.p427d.InterfaceC12630a;
import org.p415a.p427d.InterfaceC12728m;
import org.p415a.p427d.InterfaceC12734s;
import org.p415a.p427d.p432e.C12694a;
import org.p415a.p427d.p432e.C12695b;
import org.p415a.p427d.p435h.C12705a;
import org.p415a.p427d.p435h.C12709e;
import org.p415a.p427d.p435h.C12710f;
import org.p415a.p427d.p435h.C12713i;
import org.p415a.p427d.p435h.C12714j;
import org.p415a.p427d.p435h.C12719o;
import org.p415a.p427d.p435h.C12721q;
import org.p415a.p445f.C12934f;
import org.p415a.p445f.C12944o;
import org.p415a.p445f.p446a.AbstractC12925i;
import org.p415a.p445f.p446a.C12923g;
import org.p415a.p445f.p446a.C12926j;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12912f extends AbstractC12925i {

    /* renamed from: a */
    private SecureRandom f26160a;

    /* renamed from: b */
    private C12911e f26161b;

    public C12912f(C12944o c12944o) {
        super(c12944o);
        this.f26161b = new C12911e();
    }

    @Override // org.p415a.p445f.p446a.AbstractC12925i
    /* renamed from: a */
    public byte[] mo475a(C12944o c12944o, byte[] bArr) {
        try {
            if (c12944o.m452b() != 18) {
                InterfaceC12630a m505d = C12904a.m505d(c12944o.m452b());
                C12705a m497a = this.f26161b.m497a(c12944o);
                if (this.f26160a == null) {
                    this.f26160a = new SecureRandom();
                }
                m505d.mo1398a(true, new C12721q(m497a, this.f26160a));
                return m505d.mo1397a(bArr, 0, bArr.length);
            }
            C12613j c12613j = (C12613j) c12944o.m451c().m1539c();
            C12547d m494a = C12914g.m494a(c12613j.m1547e());
            C12709e c12709e = new C12709e(m494a.m1669a(), m494a.m1668b(), m494a.m1667c());
            C12694a c12694a = new C12694a();
            c12694a.m1363a(new C12710f(c12709e, this.f26160a));
            C12725j m1361a = new C12695b(c12694a, new InterfaceC12728m() { // from class: org.a.f.a.a.f.1
                @Override // org.p415a.p427d.InterfaceC12728m
                /* renamed from: a */
                public byte[] mo496a(C12705a c12705a) {
                    return ((C12714j) c12705a).m1335b().m862a(false);
                }
            }).m1361a();
            C12719o c12719o = new C12719o(new C12915h(new C12908d().m499a(c12613j.m1553b()), c12613j.m1552c()).m491a(C12914g.m495a(c12613j.m1548d(), m494a.m1669a()).m867a(((C12713i) m1361a.m1310a().m1468b()).m1337b()).m852m(), C12926j.m472a(c12944o.m451c(), new C12905b())));
            InterfaceC12734s m506c = C12904a.m506c(c12613j.m1552c());
            m506c.mo1303a(true, new C12721q(c12719o, this.f26160a));
            byte[] m478a = C12923g.m478a(bArr);
            byte[] mo1302a = m506c.mo1302a(m478a, 0, m478a.length);
            byte[] a = new C12620q(new BigInteger(1, m1361a.m1309b())).mo1536a();
            byte[] bArr2 = new byte[a.length + 1 + mo1302a.length];
            System.arraycopy(a, 0, bArr2, 0, a.length);
            bArr2[a.length] = (byte) mo1302a.length;
            System.arraycopy(mo1302a, 0, bArr2, a.length + 1, mo1302a.length);
            return bArr2;
        } catch (IOException e) {
            throw new C12934f("exception encrypting session info: " + e.getMessage(), e);
        } catch (C12727l e2) {
            throw new C12934f("exception encrypting session info: " + e2.getMessage(), e2);
        }
    }
}
