package org.p415a.p424c;

import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.am */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12591am extends AbstractC12611h {

    /* renamed from: a */
    private byte[] f25578a;

    public C12591am(C12592b c12592b) {
        this.f25578a = c12592b.m1577a();
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        c12608e.m1566a(13, this.f25578a, true);
    }

    public boolean equals(Object obj) {
        if (obj instanceof C12591am) {
            return C12957a.m438a(this.f25578a, ((C12591am) obj).f25578a);
        }
        return false;
    }

    public int hashCode() {
        return C12957a.m441a(this.f25578a);
    }
}
