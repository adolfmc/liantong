package org.p415a.p418b;

import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ap */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12425ap extends AbstractC12570t {

    /* renamed from: a */
    private final char[] f25129a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12425ap(char[] cArr) {
        this.f25129a = cArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.mo1620b(30);
        c12567r.m1629a(this.f25129a.length * 2);
        int i = 0;
        while (true) {
            char[] cArr = this.f25129a;
            if (i == cArr.length) {
                return;
            }
            char c = cArr[i];
            c12567r.mo1620b((byte) (c >> '\b'));
            c12567r.mo1620b((byte) c);
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return false;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    protected boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof C12425ap) {
            return C12957a.m436a(this.f25129a, ((C12425ap) abstractC12570t).f25129a);
        }
        return false;
    }

    /* renamed from: b */
    public String m1727b() {
        return new String(this.f25129a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return C12466bz.m1694a(this.f25129a.length * 2) + 1 + (this.f25129a.length * 2);
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return C12957a.m437a(this.f25129a);
    }

    public String toString() {
        return m1727b();
    }
}
