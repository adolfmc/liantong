package comp.android.app.face.p381sz.camera.p382a;

import android.content.Context;
import android.view.Surface;
import android.view.SurfaceHolder;
import comp.android.app.face.p381sz.camera.C11753a;
import comp.android.app.face.p381sz.camera.p383b.InterfaceC11777a;

/* renamed from: comp.android.app.face.sz.camera.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11764c implements InterfaceC11768e {

    /* renamed from: a */
    private Context f23947a;

    /* renamed from: c */
    private InterfaceC11777a f23949c;

    /* renamed from: d */
    private InterfaceC11768e f23950d = new C11765d(this);

    /* renamed from: e */
    private InterfaceC11768e f23951e = new C11762a(this);

    /* renamed from: f */
    private InterfaceC11768e f23952f = new C11763b(this);

    /* renamed from: b */
    private InterfaceC11768e f23948b = this.f23950d;

    public C11764c(Context context, InterfaceC11777a interfaceC11777a, C11753a.InterfaceC11757a interfaceC11757a) {
        this.f23947a = context;
        this.f23949c = interfaceC11777a;
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2188a() {
        this.f23948b.mo2188a();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2187a(float f, float f2, C11753a.InterfaceC11759c interfaceC11759c) {
        this.f23948b.mo2187a(f, f2, interfaceC11759c);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2186a(float f, int i) {
        this.f23948b.mo2186a(f, i);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2185a(Surface surface, float f) {
        this.f23948b = this.f23950d;
        this.f23948b.mo2185a(surface, f);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2184a(SurfaceHolder surfaceHolder, float f) {
        this.f23948b.mo2184a(surfaceHolder, f);
    }

    /* renamed from: a */
    public void m2196a(InterfaceC11768e interfaceC11768e) {
        this.f23948b = interfaceC11768e;
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2183a(String str) {
        this.f23948b.mo2183a(str);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2182a(boolean z, long j) {
        this.f23948b.mo2182a(z, j);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: b */
    public void mo2180b() {
        this.f23948b.mo2180b();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: b */
    public void mo2179b(SurfaceHolder surfaceHolder, float f) {
        this.f23948b.mo2179b(surfaceHolder, f);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: c */
    public void mo2178c() {
        this.f23948b.mo2178c();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: c */
    public void mo2177c(SurfaceHolder surfaceHolder, float f) {
        this.f23948b.mo2177c(surfaceHolder, f);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: d */
    public void mo2176d() {
        this.f23948b.mo2176d();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: e */
    public void mo2175e() {
        this.f23948b.mo2175e();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: f */
    public void mo2174f() {
        this.f23948b.mo2174f();
    }

    /* renamed from: g */
    public InterfaceC11777a m2195g() {
        return this.f23949c;
    }

    /* renamed from: h */
    public Context m2194h() {
        return this.f23947a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public InterfaceC11768e m2193i() {
        return this.f23951e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public InterfaceC11768e m2192j() {
        return this.f23952f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: k */
    public InterfaceC11768e m2191k() {
        return this.f23950d;
    }
}
