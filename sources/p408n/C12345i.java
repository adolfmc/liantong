package p408n;

import p395i.AbstractC12045a;
import p395i.C12048b;
import p399k.AbstractC12265h;
import p408n.C12348l;

/* renamed from: n.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12345i extends AbstractC12265h {

    /* renamed from: a */
    public final /* synthetic */ String f24969a;

    /* renamed from: b */
    public final /* synthetic */ boolean f24970b;

    /* renamed from: c */
    public final /* synthetic */ C12048b.InterfaceC12050b f24971c;

    /* renamed from: d */
    public final /* synthetic */ int f24972d = -1;

    /* renamed from: e */
    public final /* synthetic */ int f24973e = 0;

    /* renamed from: f */
    public final /* synthetic */ int f24974f = -1;

    /* renamed from: g */
    public final /* synthetic */ C12348l.C12349a f24975g;

    public C12345i(C12348l.C12349a c12349a, String str, boolean z, C12048b.InterfaceC12050b interfaceC12050b) {
        this.f24975g = c12349a;
        this.f24969a = str;
        this.f24970b = z;
        this.f24971c = interfaceC12050b;
    }

    @Override // p399k.AbstractC12258a
    /* renamed from: a */
    public final String mo1815a() {
        return this.f24969a;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: b */
    public final void mo1814b() {
        AbstractC12045a abstractC12045a = this.f24975g.f24992a;
        if (abstractC12045a == null || !this.f24970b) {
            return;
        }
        abstractC12045a.dismiss();
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: c */
    public final int mo1813c() {
        int i = this.f24974f;
        if (i > 0) {
            return i;
        }
        return 150;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: d */
    public final int mo1812d() {
        int i = this.f24973e;
        if (i != 0) {
            return i;
        }
        return -10066330;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: e */
    public final int mo1811e() {
        int i = this.f24972d;
        if (i > 0) {
            return i;
        }
        return 50;
    }

    @Override // p399k.AbstractC12265h
    /* renamed from: f */
    public final C12048b.InterfaceC12050b mo1816f() {
        return this.f24971c;
    }
}
