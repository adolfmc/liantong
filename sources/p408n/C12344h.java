package p408n;

import p395i.AbstractC12045a;
import p395i.C12048b;
import p399k.AbstractC12265h;
import p408n.C12348l;

/* renamed from: n.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12344h extends AbstractC12265h {

    /* renamed from: a */
    public final /* synthetic */ String f24963a;

    /* renamed from: b */
    public final /* synthetic */ C12048b.InterfaceC12050b f24964b;

    /* renamed from: c */
    public final /* synthetic */ int f24965c = -1;

    /* renamed from: d */
    public final /* synthetic */ int f24966d = 0;

    /* renamed from: e */
    public final /* synthetic */ int f24967e = -1;

    /* renamed from: f */
    public final /* synthetic */ C12348l.C12349a f24968f;

    public C12344h(C12348l.C12349a c12349a, String str, C12048b.InterfaceC12050b interfaceC12050b) {
        this.f24968f = c12349a;
        this.f24963a = str;
        this.f24964b = interfaceC12050b;
    }

    @Override // p399k.AbstractC12258a
    /* renamed from: a */
    public final String mo1815a() {
        return this.f24963a;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: b */
    public final void mo1814b() {
        AbstractC12045a abstractC12045a = this.f24968f.f24992a;
        if (abstractC12045a != null) {
            abstractC12045a.dismiss();
        }
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: c */
    public final int mo1813c() {
        int i = this.f24967e;
        if (i > 0) {
            return i;
        }
        return 150;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: d */
    public final int mo1812d() {
        int i = this.f24966d;
        if (i != 0) {
            return i;
        }
        return -10066330;
    }

    @Override // p399k.AbstractC12264g
    /* renamed from: e */
    public final int mo1811e() {
        int i = this.f24965c;
        if (i > 0) {
            return i;
        }
        return 50;
    }

    @Override // p399k.AbstractC12265h
    /* renamed from: f */
    public final C12048b.InterfaceC12050b mo1816f() {
        return this.f24964b;
    }
}
