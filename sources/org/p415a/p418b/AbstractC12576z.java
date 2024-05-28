package org.p415a.p418b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.z */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12576z extends AbstractC12570t implements InterfaceC12409aa {

    /* renamed from: a */
    int f25521a;

    /* renamed from: b */
    boolean f25522b = false;

    /* renamed from: c */
    boolean f25523c;

    /* renamed from: d */
    InterfaceC12554f f25524d;

    public AbstractC12576z(boolean z, int i, InterfaceC12554f interfaceC12554f) {
        this.f25523c = true;
        this.f25524d = null;
        if (interfaceC12554f instanceof InterfaceC12520e) {
            this.f25523c = true;
        } else {
            this.f25523c = z;
        }
        this.f25521a = i;
        if (!this.f25523c) {
            boolean z2 = interfaceC12554f.mo1617h() instanceof AbstractC12573w;
        }
        this.f25524d = interfaceC12554f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public abstract void mo1597a(C12567r c12567r);

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof AbstractC12576z) {
            AbstractC12576z abstractC12576z = (AbstractC12576z) abstractC12570t;
            if (this.f25521a == abstractC12576z.f25521a && this.f25522b == abstractC12576z.f25522b && this.f25523c == abstractC12576z.f25523c) {
                InterfaceC12554f interfaceC12554f = this.f25524d;
                return interfaceC12554f == null ? abstractC12576z.f25524d == null : interfaceC12554f.mo1617h().equals(abstractC12576z.f25524d.mo1617h());
            }
            return false;
        }
        return false;
    }

    /* renamed from: b */
    public int m1595b() {
        return this.f25521a;
    }

    /* renamed from: d */
    public AbstractC12570t m1594d() {
        InterfaceC12554f interfaceC12554f = this.f25524d;
        if (interfaceC12554f != null) {
            return interfaceC12554f.mo1617h();
        }
        return null;
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
        return new C12449bi(this.f25523c, this.f25521a, this.f25524d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: g */
    public AbstractC12570t mo1591g() {
        return new C12458br(this.f25523c, this.f25521a, this.f25524d);
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        int i = this.f25521a;
        InterfaceC12554f interfaceC12554f = this.f25524d;
        return interfaceC12554f != null ? i ^ interfaceC12554f.hashCode() : i;
    }

    public String toString() {
        return "[" + this.f25521a + "]" + this.f25524d;
    }
}
