package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.d.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0646a {
    /* renamed from: a */
    public static AbstractC0655f m22625a(AbstractC0655f abstractC0655f, BigInteger bigInteger, AbstractC0655f abstractC0655f2, BigInteger bigInteger2) {
        int max = Math.max(bigInteger.bitLength(), bigInteger2.bitLength());
        AbstractC0655f mo22569a = abstractC0655f.mo22569a(abstractC0655f2);
        AbstractC0655f mo22603d = abstractC0655f.m22580b().mo22603d();
        for (int i = max - 1; i >= 0; i--) {
            mo22603d = mo22603d.mo22565i();
            if (bigInteger.testBit(i)) {
                if (bigInteger2.testBit(i)) {
                    mo22603d = mo22603d.mo22569a(mo22569a);
                } else {
                    mo22603d = mo22603d.mo22569a(abstractC0655f);
                }
            } else if (bigInteger2.testBit(i)) {
                mo22603d = mo22603d.mo22569a(abstractC0655f2);
            }
        }
        return mo22603d;
    }

    /* renamed from: b */
    public static AbstractC0655f m22624b(AbstractC0655f abstractC0655f, BigInteger bigInteger, AbstractC0655f abstractC0655f2, BigInteger bigInteger2) {
        if (abstractC0655f.m22580b().equals(abstractC0655f2.m22580b())) {
            return m22625a(abstractC0655f, bigInteger, abstractC0655f2, bigInteger2);
        }
        throw new IllegalArgumentException("P and Q must be on same curve");
    }

    /* renamed from: c */
    public static AbstractC0655f m22623c(AbstractC0655f abstractC0655f, BigInteger bigInteger, AbstractC0655f abstractC0655f2, BigInteger bigInteger2) {
        AbstractC0648c m22580b = abstractC0655f.m22580b();
        if (m22580b.equals(abstractC0655f2.m22580b())) {
            if ((m22580b instanceof AbstractC0648c.C0649a) && ((AbstractC0648c.C0649a) m22580b).m22609m()) {
                return abstractC0655f.m22581a(bigInteger).mo22569a(abstractC0655f2.m22581a(bigInteger2));
            }
            return m22625a(abstractC0655f, bigInteger, abstractC0655f2, bigInteger2);
        }
        throw new IllegalArgumentException("P and Q must be on same curve");
    }
}
