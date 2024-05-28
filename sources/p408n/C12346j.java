package p408n;

import p395i.AbstractC12045a;
import p395i.C12048b;
import p399k.AbstractC12266i;
import p408n.C12348l;

/* renamed from: n.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12346j extends AbstractC12266i {

    /* renamed from: a */
    public final /* synthetic */ String f24976a;

    /* renamed from: b */
    public final /* synthetic */ C12048b.InterfaceC12052d f24977b;

    /* renamed from: c */
    public final /* synthetic */ int f24978c = -1;

    /* renamed from: d */
    public final /* synthetic */ int f24979d = 0;

    /* renamed from: e */
    public final /* synthetic */ int f24980e = -1;

    /* renamed from: f */
    public final /* synthetic */ C12348l.C12349a f24981f;

    public C12346j(C12348l.C12349a c12349a, String str, C12048b.InterfaceC12052d interfaceC12052d) {
        this.f24981f = c12349a;
        this.f24976a = str;
        this.f24977b = interfaceC12052d;
    }

    @Override // p399k.AbstractC12258a
    /* renamed from: a */
    public final String mo1815a() {
        return this.f24976a;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: b */
    public final void mo1814b() {
        AbstractC12045a abstractC12045a = this.f24981f.f24992a;
        if (abstractC12045a != null) {
            abstractC12045a.dismiss();
        }
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: c */
    public final int mo1813c() {
        int i = this.f24980e;
        if (i > 0) {
            return i;
        }
        return 150;
    }

    @Override // p399k.AbstractC12266i, p399k.AbstractC12264g
    /* renamed from: d */
    public final int mo1812d() {
        int i = this.f24979d;
        if (i != 0) {
            return i;
        }
        return -1703897;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: e */
    public final int mo1811e() {
        int i = this.f24978c;
        if (i > 0) {
            return i;
        }
        return 50;
    }

    @Override // p399k.AbstractC12266i
    /* renamed from: f */
    public final C12048b.InterfaceC12052d mo1810f() {
        return this.f24977b;
    }
}
