package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.util.C3097i;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3074p implements InterfaceC3015aq {

    /* renamed from: d */
    public static boolean f7985d;

    /* renamed from: a */
    public int f7986a;

    /* renamed from: b */
    public int f7987b;

    /* renamed from: g */
    private InterfaceC3009ak f7991g;

    /* renamed from: h */
    private WeakReference<MapSurfaceView> f7992h;

    /* renamed from: i */
    private WeakReference<TextureView$SurfaceTextureListenerC3057h> f7993i;

    /* renamed from: j */
    private InterfaceC3039c f7994j;

    /* renamed from: l */
    private int f7996l;

    /* renamed from: m */
    private int f7997m;

    /* renamed from: n */
    private int f7998n;

    /* renamed from: o */
    private int f7999o;

    /* renamed from: p */
    private Bitmap.Config f8000p;

    /* renamed from: q */
    private InterfaceC3041e f8001q;

    /* renamed from: r */
    private boolean f8002r;

    /* renamed from: s */
    private int f8003s;

    /* renamed from: t */
    private int f8004t;

    /* renamed from: u */
    private int f8005u;

    /* renamed from: e */
    private AppBaseMap f7989e = null;

    /* renamed from: f */
    private boolean f7990f = false;

    /* renamed from: k */
    private volatile boolean f7995k = false;

    /* renamed from: v */
    private long f8006v = 0;

    /* renamed from: w */
    private boolean f8007w = false;

    /* renamed from: x */
    private volatile boolean f8008x = false;

    /* renamed from: c */
    public int f7988c = 0;

    public C3074p(TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h, InterfaceC3009ak interfaceC3009ak) {
        this.f7993i = new WeakReference<>(textureView$SurfaceTextureListenerC3057h);
        this.f7991g = interfaceC3009ak;
    }

    public C3074p(WeakReference<MapSurfaceView> weakReference, InterfaceC3009ak interfaceC3009ak) {
        this.f7991g = interfaceC3009ak;
        this.f7992h = weakReference;
    }

    /* renamed from: b */
    private void m17721b(Object obj) {
        TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h;
        int i;
        int i2;
        MapSurfaceView mapSurfaceView;
        int i3;
        int i4;
        if (this.f7994j == null) {
            return;
        }
        WeakReference<MapSurfaceView> weakReference = this.f7992h;
        if (weakReference != null && (mapSurfaceView = weakReference.get()) != null && (i3 = this.f7996l) > 0 && (i4 = this.f7997m) > 0) {
            C3097i.m17679a(new RunnableC3076r(this, mapSurfaceView.captureImageFromSurface(this.f7998n, this.f7999o, i3, i4, obj, this.f8000p)), 0L);
        }
        WeakReference<TextureView$SurfaceTextureListenerC3057h> weakReference2 = this.f7993i;
        if (weakReference2 == null || (textureView$SurfaceTextureListenerC3057h = weakReference2.get()) == null || (i = this.f7996l) <= 0 || (i2 = this.f7997m) <= 0) {
            return;
        }
        C3097i.m17679a(new RunnableC3077s(this, textureView$SurfaceTextureListenerC3057h.captureImageFromSurface(this.f7998n, this.f7999o, i, i2, obj, this.f8000p)), 0L);
    }

    /* renamed from: c */
    private boolean m17720c() {
        return this.f7989e != null && this.f7990f;
    }

    /* renamed from: a */
    public void m17734a() {
        this.f8008x = false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3015aq
    /* renamed from: a */
    public void mo17733a(int i, int i2) {
        AppBaseMap appBaseMap = this.f7989e;
        if (appBaseMap != null) {
            appBaseMap.renderResize(i, i2);
        }
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b m18459a = C2898b.m18459a();
            m18459a.m18457a("BasicMap onSurfaceChanged width = " + i + "; height = " + i2);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3015aq
    /* renamed from: a */
    public void mo17732a(SurfaceHolder surfaceHolder) {
        Surface surface = surfaceHolder != null ? surfaceHolder.getSurface() : null;
        AppBaseMap appBaseMap = this.f7989e;
        if (appBaseMap != null) {
            appBaseMap.surfaceDestroyed(surface);
        }
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b.m18459a().m18457a("BasicMap onSurfaceDestroyed");
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3015aq
    /* renamed from: a */
    public void mo17731a(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.f8002r = false;
        this.f8003s = 0;
        this.f8005u = 0;
        this.f8004t = 0;
        if (m17720c()) {
            this.f7989e.renderInit(i, i2, surfaceHolder != null ? surfaceHolder.getSurface() : null, i3);
            if (OpenLogUtil.isMapLogEnable()) {
                C2898b.m18459a().m18457a("BasicMap onSurfaceCreated ok");
            }
        }
    }

    /* renamed from: a */
    public void m17730a(InterfaceC3039c interfaceC3039c, int i, int i2) {
        this.f7995k = true;
        this.f7994j = interfaceC3039c;
        this.f7996l = i;
        this.f7997m = i2;
        this.f8000p = null;
    }

    /* renamed from: a */
    public void m17729a(InterfaceC3039c interfaceC3039c, int i, int i2, int i3, int i4, Bitmap.Config config) {
        this.f7995k = true;
        this.f7994j = interfaceC3039c;
        this.f7998n = i;
        this.f7999o = i2;
        this.f7996l = i3;
        this.f7997m = i4;
        this.f8000p = config;
    }

    /* renamed from: a */
    public void m17728a(InterfaceC3039c interfaceC3039c, int i, int i2, Bitmap.Config config) {
        this.f7995k = true;
        this.f7994j = interfaceC3039c;
        this.f7996l = i;
        this.f7997m = i2;
        this.f8000p = config;
    }

    /* renamed from: a */
    public void m17727a(InterfaceC3041e interfaceC3041e) {
        this.f8001q = interfaceC3041e;
    }

    /* renamed from: a */
    public void m17725a(AppBaseMap appBaseMap) {
        this.f7989e = appBaseMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3015aq
    /* renamed from: a */
    public void mo17724a(Object obj) {
        InterfaceC3041e interfaceC3041e;
        TextureView$SurfaceTextureListenerC3057h textureView$SurfaceTextureListenerC3057h;
        MapSurfaceView mapSurfaceView;
        MapSurfaceView mapSurfaceView2;
        if (m17720c()) {
            if (!this.f8007w) {
                this.f8007w = true;
                WeakReference<MapSurfaceView> weakReference = this.f7992h;
                if (weakReference != null && (mapSurfaceView2 = weakReference.get()) != null) {
                    mapSurfaceView2.post(new RunnableC3075q(this, mapSurfaceView2));
                }
            }
            if (f7985d) {
                f7985d = false;
            } else if (!this.f8008x) {
                int Draw = this.f7989e.Draw();
                WeakReference<MapSurfaceView> weakReference2 = this.f7992h;
                if (weakReference2 != null && (mapSurfaceView = weakReference2.get()) != null) {
                    if (Draw == 1) {
                        mapSurfaceView.requestRender();
                    } else if (mapSurfaceView.getRenderMode() != 0) {
                        mapSurfaceView.setRenderMode(0);
                    }
                }
                WeakReference<TextureView$SurfaceTextureListenerC3057h> weakReference3 = this.f7993i;
                if (weakReference3 != null && (textureView$SurfaceTextureListenerC3057h = weakReference3.get()) != null) {
                    if (Draw == 1) {
                        textureView$SurfaceTextureListenerC3057h.requestRender();
                    } else if (textureView$SurfaceTextureListenerC3057h.getRenderMode() != 0) {
                        textureView$SurfaceTextureListenerC3057h.setRenderMode(0);
                    }
                }
                if (this.f7995k) {
                    this.f7995k = false;
                    if (this.f7994j != null) {
                        m17721b(obj);
                    }
                }
                if (!this.f8002r) {
                    this.f8003s++;
                    if (this.f8003s == 2 && (interfaceC3041e = this.f8001q) != null) {
                        interfaceC3041e.m17866a();
                        if (OpenLogUtil.isMapLogEnable()) {
                            C2898b.m18459a().m18457a("BasicMap onDrawFirstFrame");
                        }
                    }
                    this.f8002r = this.f8003s == 2;
                }
                WeakReference<MapSurfaceView> weakReference4 = this.f7992h;
                if (weakReference4 == null || weakReference4.get().getBaseMap() == null || this.f7992h.get().getBaseMap().f7277h == null) {
                    return;
                }
                for (InterfaceC3010al interfaceC3010al : this.f7992h.get().getBaseMap().f7277h) {
                    if (this.f7992h.get().getBaseMap() == null) {
                        return;
                    }
                    C2948x m18372K = this.f7992h.get().getBaseMap().m18372K();
                    if (interfaceC3010al != null) {
                        interfaceC3010al.mo17965a((GL10) null, m18372K);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public void m17723a(boolean z) {
        this.f7990f = z;
    }

    /* renamed from: b */
    public void m17722b() {
        this.f8008x = true;
    }
}
