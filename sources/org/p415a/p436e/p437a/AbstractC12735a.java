package org.p415a.p436e.p437a;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12735a implements InterfaceC12859f {
    @Override // org.p415a.p436e.p437a.InterfaceC12859f
    /* renamed from: a */
    public AbstractC12860g mo869a(AbstractC12860g abstractC12860g, BigInteger bigInteger) {
        int signum = bigInteger.signum();
        if (signum == 0 || abstractC12860g.m851n()) {
            return abstractC12860g.m861c().mo901e();
        }
        AbstractC12860g mo732b = mo732b(abstractC12860g, bigInteger.abs());
        if (signum <= 0) {
            mo732b = mo732b.mo832q();
        }
        return C12844b.m958a(mo732b);
    }

    /* renamed from: b */
    protected abstract AbstractC12860g mo732b(AbstractC12860g abstractC12860g, BigInteger bigInteger);
}
