package org.p415a.p445f.p446a.p447a;

import org.p415a.p418b.p423e.C12547d;
import org.p415a.p424c.AbstractC12615l;
import org.p415a.p424c.C12612i;
import org.p415a.p424c.C12616m;
import org.p415a.p424c.C12626w;
import org.p415a.p424c.C12628y;
import org.p415a.p427d.p435h.C12705a;
import org.p415a.p427d.p435h.C12707c;
import org.p415a.p427d.p435h.C12708d;
import org.p415a.p427d.p435h.C12712h;
import org.p415a.p427d.p435h.C12714j;
import org.p415a.p427d.p435h.C12716l;
import org.p415a.p427d.p435h.C12718n;
import org.p415a.p427d.p435h.C12722r;
import org.p415a.p445f.C12934f;
import org.p415a.p445f.C12944o;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12911e {
    /* renamed from: a */
    public C12705a m497a(C12944o c12944o) {
        C12626w m451c = c12944o.m451c();
        try {
            int m1540b = m451c.m1540b();
            switch (m1540b) {
                case 1:
                case 2:
                case 3:
                    C12628y c12628y = (C12628y) m451c.m1539c();
                    return new C12722r(false, c12628y.m1534c(), c12628y.m1535b());
                default:
                    switch (m1540b) {
                        case 16:
                        case 20:
                            C12616m c12616m = (C12616m) m451c.m1539c();
                            return new C12718n(c12616m.m1544d(), new C12716l(c12616m.m1546b(), c12616m.m1545c()));
                        case 17:
                            C12612i c12612i = (C12612i) m451c.m1539c();
                            return new C12708d(c12612i.m1554e(), new C12707c(c12612i.m1556c(), c12612i.m1555d(), c12612i.m1557b()));
                        case 18:
                        case 19:
                            AbstractC12615l abstractC12615l = (AbstractC12615l) m451c.m1539c();
                            C12547d m494a = C12914g.m494a(abstractC12615l.m1547e());
                            return new C12714j(C12914g.m495a(abstractC12615l.m1548d(), m494a.m1669a()), new C12712h(abstractC12615l.m1547e(), m494a.m1669a(), m494a.m1668b(), m494a.m1667c(), m494a.m1666d()));
                        default:
                            throw new C12934f("unknown public key algorithm encountered");
                    }
            }
        } catch (C12934f e) {
            throw e;
        } catch (Exception e2) {
            throw new C12934f("exception constructing public key", e2);
        }
    }
}
