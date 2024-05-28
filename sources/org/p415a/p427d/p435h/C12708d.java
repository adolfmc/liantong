package org.p415a.p427d.p435h;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12708d extends C12706b {

    /* renamed from: b */
    private static final BigInteger f25896b = BigInteger.valueOf(1);

    /* renamed from: c */
    private static final BigInteger f25897c = BigInteger.valueOf(2);

    /* renamed from: d */
    private BigInteger f25898d;

    public C12708d(BigInteger bigInteger, C12707c c12707c) {
        super(false, c12707c);
        this.f25898d = m1341a(bigInteger, c12707c);
    }

    /* renamed from: a */
    private BigInteger m1341a(BigInteger bigInteger, C12707c c12707c) {
        if (c12707c != null) {
            if (f25897c.compareTo(bigInteger) > 0 || c12707c.m1344a().subtract(f25897c).compareTo(bigInteger) < 0 || !f25896b.equals(bigInteger.modPow(c12707c.m1343b(), c12707c.m1344a()))) {
                throw new IllegalArgumentException("y value does not appear to be in correct group");
            }
            return bigInteger;
        }
        return bigInteger;
    }
}
