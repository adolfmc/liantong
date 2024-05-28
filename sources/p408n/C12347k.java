package p408n;

import p395i.AbstractC12045a;
import p395i.C12048b;
import p399k.AbstractC12266i;
import p408n.C12348l;

/* renamed from: n.k */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12347k extends AbstractC12266i {

    /* renamed from: a */
    public final /* synthetic */ String f24982a;

    /* renamed from: b */
    public final /* synthetic */ boolean f24983b;

    /* renamed from: c */
    public final /* synthetic */ C12048b.InterfaceC12052d f24984c;

    /* renamed from: d */
    public final /* synthetic */ int f24985d = -1;

    /* renamed from: e */
    public final /* synthetic */ int f24986e = 0;

    /* renamed from: f */
    public final /* synthetic */ int f24987f = -1;

    /* renamed from: g */
    public final /* synthetic */ C12348l.C12349a f24988g;

    public C12347k(C12348l.C12349a c12349a, String str, boolean z, C12048b.InterfaceC12052d interfaceC12052d) {
        this.f24988g = c12349a;
        this.f24982a = str;
        this.f24983b = z;
        this.f24984c = interfaceC12052d;
    }

    @Override // p399k.AbstractC12258a
    /* renamed from: a */
    public final String mo1815a() {
        return this.f24982a;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: b */
    public final void mo1814b() {
        AbstractC12045a abstractC12045a;
        if (!this.f24983b || (abstractC12045a = this.f24988g.f24992a) == null) {
            return;
        }
        abstractC12045a.dismiss();
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: c */
    public final int mo1813c() {
        int i = this.f24987f;
        if (i > 0) {
            return i;
        }
        return 150;
    }

    @Override // p399k.AbstractC12266i, p399k.AbstractC12264g
    /* renamed from: d */
    public final int mo1812d() {
        int i = this.f24986e;
        if (i != 0) {
            return i;
        }
        return -1703897;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: e */
    public final int mo1811e() {
        int i = this.f24985d;
        if (i > 0) {
            return i;
        }
        return 50;
    }

    @Override // p399k.AbstractC12266i
    /* renamed from: f */
    public final C12048b.InterfaceC12052d mo1810f() {
        return this.f24984c;
    }
}
