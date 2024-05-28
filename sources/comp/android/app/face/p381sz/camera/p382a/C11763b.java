package comp.android.app.face.p381sz.camera.p382a;

import android.view.Surface;
import android.view.SurfaceHolder;
import comp.android.app.face.p381sz.camera.C11753a;
import comp.android.app.face.p381sz.camera.util.C11791g;

/* renamed from: comp.android.app.face.sz.camera.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11763b implements InterfaceC11768e {

    /* renamed from: b */
    private C11764c f23945b;

    /* renamed from: a */
    private final String f23944a = "BorrowVideoState";

    /* renamed from: c */
    private C11753a f23946c = C11753a.m2236a();

    public C11763b(C11764c c11764c) {
        this.f23945b = c11764c;
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2188a() {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2187a(float f, float f2, C11753a.InterfaceC11759c interfaceC11759c) {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2186a(float f, int i) {
        C11791g.m2120a("BorrowVideoState", "zoom");
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2185a(Surface surface, float f) {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2184a(SurfaceHolder surfaceHolder, float f) {
        this.f23946c.m2213b(surfaceHolder, f);
        C11764c c11764c = this.f23945b;
        c11764c.m2196a(c11764c.m2191k());
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2183a(String str) {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2182a(boolean z, long j) {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: b */
    public void mo2180b() {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: b */
    public void mo2179b(SurfaceHolder surfaceHolder, float f) {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: c */
    public void mo2178c() {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: c */
    public void mo2177c(SurfaceHolder surfaceHolder, float f) {
        this.f23945b.m2195g().resetState(2);
        C11764c c11764c = this.f23945b;
        c11764c.m2196a(c11764c.m2191k());
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: d */
    public void mo2176d() {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: e */
    public void mo2175e() {
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: f */
    public void mo2174f() {
        this.f23945b.m2195g().confirmState(2);
        C11764c c11764c = this.f23945b;
        c11764c.m2196a(c11764c.m2191k());
    }
}
