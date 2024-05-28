package org.p415a.p418b.p423e;

import java.math.BigInteger;
import org.p415a.p418b.AbstractC12562n;
import org.p415a.p418b.AbstractC12570t;
import org.p415a.p418b.C12444bd;
import org.p415a.p418b.C12555g;
import org.p415a.p418b.C12560l;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p437a.C12844b;
import org.p415a.p436e.p443b.InterfaceC12887g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.e.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12547d extends AbstractC12562n implements InterfaceC12553j {

    /* renamed from: ap */
    private static final BigInteger f25414ap = BigInteger.valueOf(1);

    /* renamed from: aq */
    private C12551h f25415aq;

    /* renamed from: ar */
    private AbstractC12850d f25416ar;

    /* renamed from: as */
    private C12549f f25417as;

    /* renamed from: at */
    private BigInteger f25418at;

    /* renamed from: au */
    private BigInteger f25419au;

    /* renamed from: av */
    private byte[] f25420av;

    public C12547d(AbstractC12850d abstractC12850d, C12549f c12549f, BigInteger bigInteger, BigInteger bigInteger2) {
        this(abstractC12850d, c12549f, bigInteger, bigInteger2, null);
    }

    public C12547d(AbstractC12850d abstractC12850d, C12549f c12549f, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        C12551h c12551h;
        this.f25416ar = abstractC12850d;
        this.f25417as = c12549f;
        this.f25418at = bigInteger;
        this.f25419au = bigInteger2;
        this.f25420av = bArr;
        if (C12844b.m951b(abstractC12850d)) {
            c12551h = new C12551h(abstractC12850d.m924f().mo724a());
        } else if (!C12844b.m959a(abstractC12850d)) {
            throw new IllegalArgumentException("'curve' is of an unsupported type");
        } else {
            int[] mo726b = ((InterfaceC12887g) abstractC12850d.m924f()).mo725c().mo726b();
            if (mo726b.length == 3) {
                c12551h = new C12551h(mo726b[2], mo726b[1]);
            } else if (mo726b.length != 5) {
                throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
            } else {
                c12551h = new C12551h(mo726b[4], mo726b[1], mo726b[2], mo726b[3]);
            }
        }
        this.f25415aq = c12551h;
    }

    /* renamed from: a */
    public AbstractC12850d m1669a() {
        return this.f25416ar;
    }

    /* renamed from: b */
    public AbstractC12860g m1668b() {
        return this.f25417as.m1664a();
    }

    /* renamed from: c */
    public BigInteger m1667c() {
        return this.f25418at;
    }

    /* renamed from: d */
    public BigInteger m1666d() {
        return this.f25419au;
    }

    @Override // org.p415a.p418b.AbstractC12562n, org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public AbstractC12570t mo1617h() {
        C12555g c12555g = new C12555g();
        c12555g.m1659a(new C12560l(f25414ap));
        c12555g.m1659a(this.f25415aq);
        c12555g.m1659a(new C12546c(this.f25416ar, this.f25420av));
        c12555g.m1659a(this.f25417as);
        c12555g.m1659a(new C12560l(this.f25418at));
        BigInteger bigInteger = this.f25419au;
        if (bigInteger != null) {
            c12555g.m1659a(new C12560l(bigInteger));
        }
        return new C12444bd(c12555g);
    }
}
