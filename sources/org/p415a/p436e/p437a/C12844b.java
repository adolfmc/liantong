package org.p415a.p436e.p437a;

import java.math.BigInteger;
import org.p415a.p436e.p443b.InterfaceC12882b;
import org.p415a.p436e.p443b.InterfaceC12887g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12844b {
    /* renamed from: a */
    public static AbstractC12860g m958a(AbstractC12860g abstractC12860g) {
        if (abstractC12860g.m850o()) {
            return abstractC12860g;
        }
        throw new IllegalArgumentException("Invalid point");
    }

    /* renamed from: a */
    public static AbstractC12860g m957a(AbstractC12860g abstractC12860g, BigInteger bigInteger) {
        BigInteger abs = bigInteger.abs();
        AbstractC12860g mo901e = abstractC12860g.m861c().mo901e();
        int bitLength = abs.bitLength();
        if (bitLength > 0) {
            if (abs.testBit(0)) {
                mo901e = abstractC12860g;
            }
            for (int i = 1; i < bitLength; i++) {
                abstractC12860g = abstractC12860g.mo831r();
                if (abs.testBit(i)) {
                    mo901e = mo901e.mo839b(abstractC12860g);
                }
            }
        }
        return bigInteger.signum() < 0 ? mo901e.mo832q() : mo901e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AbstractC12860g m956a(AbstractC12860g abstractC12860g, BigInteger bigInteger, AbstractC12860g abstractC12860g2, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() < 0;
        boolean z2 = bigInteger2.signum() < 0;
        BigInteger abs = bigInteger.abs();
        BigInteger abs2 = bigInteger2.abs();
        int max = Math.max(2, Math.min(16, AbstractC12877t.m748a(abs.bitLength())));
        int max2 = Math.max(2, Math.min(16, AbstractC12877t.m748a(abs2.bitLength())));
        C12876s m743a = AbstractC12877t.m743a(abstractC12860g, max, true);
        C12876s m743a2 = AbstractC12877t.m743a(abstractC12860g2, max2, true);
        return m952a(z ? m743a.m751b() : m743a.m754a(), z ? m743a.m754a() : m743a.m751b(), AbstractC12877t.m737b(max, abs), z2 ? m743a2.m751b() : m743a2.m754a(), z2 ? m743a2.m754a() : m743a2.m751b(), AbstractC12877t.m737b(max2, abs2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AbstractC12860g m955a(AbstractC12860g abstractC12860g, BigInteger bigInteger, InterfaceC12865h interfaceC12865h, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() < 0;
        boolean z2 = bigInteger2.signum() < 0;
        BigInteger abs = bigInteger.abs();
        BigInteger abs2 = bigInteger2.abs();
        int max = Math.max(2, Math.min(16, AbstractC12877t.m748a(Math.max(abs.bitLength(), abs2.bitLength()))));
        AbstractC12860g m742a = AbstractC12877t.m742a(abstractC12860g, max, true, interfaceC12865h);
        C12876s m744a = AbstractC12877t.m744a(abstractC12860g);
        C12876s m744a2 = AbstractC12877t.m744a(m742a);
        return m952a(z ? m744a.m751b() : m744a.m754a(), z ? m744a.m754a() : m744a.m751b(), AbstractC12877t.m737b(max, abs), z2 ? m744a2.m751b() : m744a2.m754a(), z2 ? m744a2.m754a() : m744a2.m751b(), AbstractC12877t.m737b(max, abs2));
    }

    /* renamed from: a */
    private static AbstractC12860g m952a(AbstractC12860g[] abstractC12860gArr, AbstractC12860g[] abstractC12860gArr2, byte[] bArr, AbstractC12860g[] abstractC12860gArr3, AbstractC12860g[] abstractC12860gArr4, byte[] bArr2) {
        AbstractC12860g abstractC12860g;
        int max = Math.max(bArr.length, bArr2.length);
        AbstractC12860g mo901e = abstractC12860gArr[0].m861c().mo901e();
        int i = max - 1;
        int i2 = 0;
        AbstractC12860g abstractC12860g2 = mo901e;
        while (i >= 0) {
            byte b = i < bArr.length ? bArr[i] : (byte) 0;
            byte b2 = i < bArr2.length ? bArr2[i] : (byte) 0;
            if ((b | b2) == 0) {
                i2++;
            } else {
                if (b != 0) {
                    abstractC12860g = mo901e.mo839b((b < 0 ? abstractC12860gArr2 : abstractC12860gArr)[Math.abs((int) b) >>> 1]);
                } else {
                    abstractC12860g = mo901e;
                }
                if (b2 != 0) {
                    abstractC12860g = abstractC12860g.mo839b((b2 < 0 ? abstractC12860gArr4 : abstractC12860gArr3)[Math.abs((int) b2) >>> 1]);
                }
                if (i2 > 0) {
                    abstractC12860g2 = abstractC12860g2.mo841b(i2);
                    i2 = 0;
                }
                abstractC12860g2 = abstractC12860g2.mo836d(abstractC12860g);
            }
            i--;
        }
        return i2 > 0 ? abstractC12860g2.mo841b(i2) : abstractC12860g2;
    }

    /* renamed from: a */
    public static void m953a(AbstractC12856e[] abstractC12856eArr, int i, int i2, AbstractC12856e abstractC12856e) {
        AbstractC12856e[] abstractC12856eArr2 = new AbstractC12856e[i2];
        int i3 = 0;
        abstractC12856eArr2[0] = abstractC12856eArr[i];
        while (true) {
            i3++;
            if (i3 >= i2) {
                break;
            }
            abstractC12856eArr2[i3] = abstractC12856eArr2[i3 - 1].mo878c(abstractC12856eArr[i + i3]);
        }
        int i4 = i3 - 1;
        if (abstractC12856e != null) {
            abstractC12856eArr2[i4] = abstractC12856eArr2[i4].mo878c(abstractC12856e);
        }
        AbstractC12856e mo871f = abstractC12856eArr2[i4].mo871f();
        while (i4 > 0) {
            int i5 = i4 - 1;
            int i6 = i4 + i;
            AbstractC12856e abstractC12856e2 = abstractC12856eArr[i6];
            abstractC12856eArr[i6] = abstractC12856eArr2[i5].mo878c(mo871f);
            mo871f = mo871f.mo878c(abstractC12856e2);
            i4 = i5;
        }
        abstractC12856eArr[i] = mo871f;
    }

    /* renamed from: a */
    public static boolean m959a(AbstractC12850d abstractC12850d) {
        return m954a(abstractC12850d.m924f());
    }

    /* renamed from: a */
    public static boolean m954a(InterfaceC12882b interfaceC12882b) {
        return interfaceC12882b.mo723b() > 1 && interfaceC12882b.mo724a().equals(InterfaceC12849c.f26071e) && (interfaceC12882b instanceof InterfaceC12887g);
    }

    /* renamed from: b */
    public static boolean m951b(AbstractC12850d abstractC12850d) {
        return m950b(abstractC12850d.m924f());
    }

    /* renamed from: b */
    public static boolean m950b(InterfaceC12882b interfaceC12882b) {
        return interfaceC12882b.mo723b() == 1;
    }
}
