package org.p415a.p445f.p446a.p447a;

import java.math.BigInteger;
import org.p415a.p418b.C12563o;
import org.p415a.p418b.p423e.C12521a;
import org.p415a.p418b.p423e.C12547d;
import org.p415a.p427d.C12693e;
import org.p415a.p427d.InterfaceC12680d;
import org.p415a.p427d.p429b.C12643a;
import org.p415a.p427d.p434g.C12702b;
import org.p415a.p427d.p434g.C12703c;
import org.p415a.p427d.p435h.C12719o;
import org.p415a.p427d.p435h.C12720p;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p448g.C12966b;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12914g {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C12547d m494a(C12563o c12563o) {
        C12547d m1464a = C12643a.m1464a(c12563o);
        return m1464a == null ? C12521a.m1673a(c12563o) : m1464a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C12693e m493a(boolean z, InterfaceC12680d interfaceC12680d, boolean z2, byte[] bArr) {
        C12693e c12693e = z2 ? new C12693e(new C12702b(interfaceC12680d, interfaceC12680d.mo1349b() * 8)) : new C12693e(new C12703c(interfaceC12680d));
        C12719o c12719o = new C12719o(bArr);
        if (z2) {
            c12693e.m1369a(z, new C12720p(c12719o, new byte[interfaceC12680d.mo1349b()]));
        } else {
            c12693e.m1369a(z, c12719o);
        }
        return c12693e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AbstractC12860g m495a(BigInteger bigInteger, AbstractC12850d abstractC12850d) {
        return abstractC12850d.m931a(C12966b.m408a(bigInteger));
    }
}
