package comp.android.app.face.p381sz.camera.p382a;

import android.graphics.Bitmap;
import android.support.annotation.RequiresApi;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.cjt2325.cameralibrary.state.PreviewState;
import comp.android.app.face.p381sz.camera.C11753a;
import comp.android.app.face.p381sz.camera.util.C11791g;

/* renamed from: comp.android.app.face.sz.camera.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class C11765d implements InterfaceC11768e {

    /* renamed from: a */
    private C11764c f23953a;

    /* renamed from: b */
    private C11753a f23954b = C11753a.m2236a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C11765d(C11764c c11764c) {
        this.f23953a = c11764c;
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2188a() {
        this.f23954b.m2225a(new C11753a.InterfaceC11761e() { // from class: comp.android.app.face.sz.camera.a.d.1
            @Override // comp.android.app.face.p381sz.camera.C11753a.InterfaceC11761e
            /* renamed from: a */
            public void mo2181a(byte[] bArr, boolean z) {
                C11765d.this.f23953a.m2195g().showPicture(bArr, z);
                C11765d.this.f23953a.m2196a(C11765d.this.f23953a.m2193i());
                C11791g.m2121a("capture");
            }
        });
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2187a(float f, float f2, C11753a.InterfaceC11759c interfaceC11759c) {
        C11791g.m2121a("preview state foucs");
        if (this.f23953a.m2195g().handlerFoucs(f, f2)) {
            this.f23954b.m2230a(this.f23953a.m2194h(), f, f2, interfaceC11759c);
        }
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2186a(float f, int i) {
        C11791g.m2120a(PreviewState.TAG, "zoom");
        this.f23954b.m2234a(f, i);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2185a(Surface surface, float f) {
        this.f23954b.m2229a(surface, f, (C11753a.InterfaceC11758b) null);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2184a(SurfaceHolder surfaceHolder, float f) {
        this.f23954b.m2213b(surfaceHolder, f);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2183a(String str) {
        this.f23954b.m2211b(str);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: a */
    public void mo2182a(final boolean z, long j) {
        this.f23954b.m2217a(z, new C11753a.InterfaceC11760d() { // from class: comp.android.app.face.sz.camera.a.d.2
            @Override // comp.android.app.face.p381sz.camera.C11753a.InterfaceC11760d
            /* renamed from: a */
            public void mo2189a(String str, Bitmap bitmap) {
                if (z) {
                    C11765d.this.f23953a.m2195g().resetState(3);
                    return;
                }
                C11765d.this.f23953a.m2195g().playVideo(bitmap, str);
                C11765d.this.f23953a.m2196a(C11765d.this.f23953a.m2192j());
            }
        });
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    @RequiresApi(api = 24)
    /* renamed from: b */
    public void mo2180b() {
        this.f23954b.m2199l();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: b */
    public void mo2179b(SurfaceHolder surfaceHolder, float f) {
        this.f23954b.m2228a(surfaceHolder, f);
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: c */
    public void mo2178c() {
        this.f23954b.m2202i();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: c */
    public void mo2177c(SurfaceHolder surfaceHolder, float f) {
        C11791g.m2121a("浏览状态下,没有 cancle 事件");
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: d */
    public void mo2176d() {
        this.f23954b.m2201j();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: e */
    public void mo2175e() {
        this.f23954b.m2200k();
    }

    @Override // comp.android.app.face.p381sz.camera.p382a.InterfaceC11768e
    /* renamed from: f */
    public void mo2174f() {
        C11791g.m2121a("浏览状态下,没有 confirm 事件");
    }
}
