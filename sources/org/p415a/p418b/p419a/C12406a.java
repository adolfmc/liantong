package org.p415a.p418b.p419a;

import java.math.BigInteger;
import java.util.Hashtable;
import org.p415a.p418b.C12563o;
import org.p415a.p418b.p423e.AbstractC12548e;
import org.p415a.p418b.p423e.C12547d;
import org.p415a.p418b.p423e.C12549f;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p448g.C12975h;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12406a {

    /* renamed from: a */
    static AbstractC12548e f25107a = new AbstractC12548e() { // from class: org.a.b.a.a.1
        @Override // org.p415a.p418b.p423e.AbstractC12548e
        /* renamed from: a */
        public C12547d mo1459a() {
            BigInteger m1735b = C12406a.m1735b("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C03");
            BigInteger m1735b2 = C12406a.m1735b("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C00");
            BigInteger m1735b3 = C12406a.m1735b("EE353FCA5428A9300D4ABA754A44C00FDFEC0C9AE4B1A1803075ED967B7BB73F");
            BigInteger m1735b4 = C12406a.m1735b("F1FD178C0B3AD58F10126DE8CE42435B53DC67E140D2BF941FFDD459C6D655E1");
            BigInteger valueOf = BigInteger.valueOf(1L);
            AbstractC12850d m1734b = C12406a.m1734b(new AbstractC12850d.C12855e(m1735b, m1735b2, m1735b3, m1735b4, valueOf));
            return new C12547d(m1734b, new C12549f(m1734b, C12964f.m419a("04B6B3D4C356C139EB31183D4749D423958C27D2DCAF98B70164C97A2DD98F5CFF6142E0F7C8B204911F9271F0F3ECEF8C2701C307E8E4C9E183115A1554062CFB")), m1735b4, valueOf, null);
        }
    };

    /* renamed from: b */
    static final Hashtable f25108b = new Hashtable();

    /* renamed from: c */
    static final Hashtable f25109c = new Hashtable();

    /* renamed from: d */
    static final Hashtable f25110d = new Hashtable();

    static {
        m1738a("FRP256v1", InterfaceC12408b.f25111a, f25107a);
    }

    /* renamed from: a */
    public static C12547d m1737a(C12563o c12563o) {
        AbstractC12548e abstractC12548e = (AbstractC12548e) f25109c.get(c12563o);
        if (abstractC12548e == null) {
            return null;
        }
        return abstractC12548e.m1665b();
    }

    /* renamed from: a */
    static void m1738a(String str, C12563o c12563o, AbstractC12548e abstractC12548e) {
        f25108b.put(C12975h.m387b(str), c12563o);
        f25110d.put(c12563o, str);
        f25109c.put(c12563o, abstractC12548e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static BigInteger m1735b(String str) {
        return new BigInteger(1, C12964f.m419a(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static AbstractC12850d m1734b(AbstractC12850d abstractC12850d) {
        return abstractC12850d;
    }
}
