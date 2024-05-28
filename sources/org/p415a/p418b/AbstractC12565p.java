package org.p415a.p418b;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.C12975h;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.p */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12565p extends AbstractC12570t implements InterfaceC12566q {

    /* renamed from: a */
    byte[] f25510a;

    public AbstractC12565p(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("string cannot be null");
        }
        this.f25510a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public abstract void mo1597a(C12567r c12567r);

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof AbstractC12565p) {
            return C12957a.m438a(this.f25510a, ((AbstractC12565p) abstractC12570t).f25510a);
        }
        return false;
    }

    @Override // org.p415a.p418b.InterfaceC12566q
    /* renamed from: b */
    public InputStream mo1631b() {
        return new ByteArrayInputStream(this.f25510a);
    }

    /* renamed from: d */
    public byte[] mo1632d() {
        return this.f25510a;
    }

    @Override // org.p415a.p418b.InterfaceC12460bt
    /* renamed from: e */
    public AbstractC12570t mo1593e() {
        return mo1617h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: f */
    public AbstractC12570t mo1592f() {
        return new C12435az(this.f25510a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: g */
    public AbstractC12570t mo1591g() {
        return new C12435az(this.f25510a);
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return C12957a.m441a(mo1632d());
    }

    public String toString() {
        return "#" + C12975h.m386b(C12964f.m416b(this.f25510a));
    }
}
