package com.baidu.platform.comapi.map;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.mapsdkplatform.comapi.map.C2925d;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.MapBound;
import com.baidu.platform.comapi.bmsdk.BmLayer;
import com.baidu.platform.comapi.map.C3006ai;
import com.baidu.platform.comapi.map.C3042f;
import com.baidu.platform.comapi.map.MapStatus;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapSurfaceView extends SurfaceHolder$Callback2C3008aj implements View.OnKeyListener, MapRenderModeChangeListener, MapViewInterface, InterfaceC3009ak {

    /* renamed from: A */
    private static final ExecutorService f7654A = Executors.newSingleThreadExecutor();

    /* renamed from: s */
    private static int f7655s;

    /* renamed from: B */
    private int f7656B;

    /* renamed from: C */
    private int f7657C;

    /* renamed from: D */
    private int f7658D;

    /* renamed from: a */
    protected MapController f7659a;

    /* renamed from: b */
    protected C3003af f7660b;

    /* renamed from: c */
    protected C3070l f7661c;

    /* renamed from: d */
    protected C3074p f7662d;

    /* renamed from: e */
    protected volatile boolean f7663e;

    /* renamed from: f */
    protected boolean f7664f;

    /* renamed from: g */
    protected C3011am f7665g;

    /* renamed from: h */
    protected GestureDetector f7666h;

    /* renamed from: i */
    protected InterfaceC3000ac f7667i;

    /* renamed from: j */
    protected C2925d f7668j;

    /* renamed from: l */
    private volatile boolean f7669l;

    /* renamed from: m */
    private volatile boolean f7670m;

    /* renamed from: n */
    private volatile boolean f7671n;

    /* renamed from: o */
    private volatile boolean f7672o;

    /* renamed from: p */
    private boolean f7673p;

    /* renamed from: q */
    private boolean f7674q;

    /* renamed from: r */
    private LocationOverlay f7675r;

    /* renamed from: t */
    private boolean f7676t;

    /* renamed from: u */
    private List<BmLayer> f7677u;

    /* renamed from: v */
    private List<Overlay> f7678v;

    /* renamed from: w */
    private int f7679w;

    /* renamed from: x */
    private int f7680x;

    /* renamed from: y */
    private HashSet<InterfaceC2999ab> f7681y;

    /* renamed from: z */
    private boolean f7682z;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.MapSurfaceView$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2988a implements C3042f.InterfaceC3048f {

        /* renamed from: b */
        private int f7684b;

        private C2988a() {
            this.f7684b = 12440;
        }

        /* synthetic */ C2988a(MapSurfaceView mapSurfaceView, RunnableC3078t runnableC3078t) {
            this();
        }

        /* renamed from: a */
        private String m18001a(int i) {
            switch (i) {
                case 12288:
                    return "EGL_SUCCESS";
                case 12289:
                    return "EGL_NOT_INITIALIZED";
                case 12290:
                    return "EGL_BAD_ACCESS";
                case 12291:
                    return "EGL_BAD_ALLOC";
                case 12292:
                    return "EGL_BAD_ATTRIBUTE";
                case 12293:
                    return "EGL_BAD_CONFIG";
                case 12294:
                    return "EGL_BAD_CONTEXT";
                case 12295:
                    return "EGL_BAD_CURRENT_SURFACE";
                case 12296:
                    return "EGL_BAD_DISPLAY";
                case 12297:
                    return "EGL_BAD_MATCH";
                case 12298:
                    return "EGL_BAD_NATIVE_PIXMAP";
                case 12299:
                    return "EGL_BAD_NATIVE_WINDOW";
                case 12300:
                    return "EGL_BAD_PARAMETER";
                case 12301:
                    return "EGL_BAD_SURFACE";
                case 12302:
                    return "EGL_CONTEXT_LOST";
                default:
                    return m17999b(i);
            }
        }

        /* renamed from: b */
        private String m17999b(int i) {
            return "0x" + Integer.toHexString(i);
        }

        @Override // com.baidu.platform.comapi.map.C3042f.InterfaceC3048f
        /* renamed from: a */
        public EGLContext mo17826a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{this.f7684b, 2, 12344});
        }

        /* renamed from: a */
        public void m18000a(String str, int i) {
            throw new RuntimeException(m17998b(str, i));
        }

        @Override // com.baidu.platform.comapi.map.C3042f.InterfaceC3048f
        /* renamed from: a */
        public void mo17825a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                Log.e("MapContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
                m18000a("eglDestroyContex", egl10.eglGetError());
            }
            MapSurfaceView.this.onRecycle();
        }

        /* renamed from: b */
        public String m17998b(String str, int i) {
            return str + " failed: " + m18001a(i);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.MapSurfaceView$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2989b extends GestureDetector.SimpleOnGestureListener {
        private C2989b() {
        }

        /* synthetic */ C2989b(MapSurfaceView mapSurfaceView, RunnableC3078t runnableC3078t) {
            this();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            GeoPoint fromPixels;
            super.onLongPress(motionEvent);
            if (MapSurfaceView.this.f7659a == null || MapSurfaceView.this.f7659a.getBaseMap() == null || !MapSurfaceView.this.f7659a.mIsMapLoadFinish) {
                return;
            }
            String GetNearlyObjID = MapSurfaceView.this.f7659a.getBaseMap().GetNearlyObjID(-1L, (int) motionEvent.getX(), (int) motionEvent.getY(), MapSurfaceView.this.f7659a.nearlyRadius);
            if (GetNearlyObjID == null || GetNearlyObjID.equals("")) {
                if (MapSurfaceView.this.f7659a.mListeners != null) {
                    fromPixels = MapSurfaceView.this.getProjection() != null ? MapSurfaceView.this.getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY()) : null;
                    if (fromPixels == null) {
                        return;
                    }
                    for (InterfaceC3010al interfaceC3010al : MapSurfaceView.this.f7659a.mListeners) {
                        if (interfaceC3010al != null && fromPixels != null) {
                            interfaceC3010al.mo17954c(fromPixels);
                        }
                    }
                }
            } else if (MapSurfaceView.this.f7659a.mListeners != null) {
                fromPixels = MapSurfaceView.this.getProjection() != null ? MapSurfaceView.this.getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY()) : null;
                for (InterfaceC3010al interfaceC3010al2 : MapSurfaceView.this.f7659a.mListeners) {
                    if (interfaceC3010al2 != null) {
                        if (interfaceC3010al2.mo17958b(GetNearlyObjID)) {
                            MapSurfaceView.this.f7659a.mHasMapObjDraging = true;
                        } else if (fromPixels != null) {
                            interfaceC3010al2.mo17954c(fromPixels);
                        }
                    }
                }
            }
        }
    }

    public MapSurfaceView(Context context) {
        super(context);
        this.f7669l = false;
        this.f7670m = false;
        this.f7671n = false;
        this.f7672o = true;
        this.f7673p = true;
        this.f7674q = true;
        this.f7659a = null;
        this.f7660b = null;
        this.f7661c = null;
        this.f7663e = false;
        this.f7676t = true;
        this.f7677u = new ArrayList();
        this.f7678v = new ArrayList();
        this.f7679w = 0;
        this.f7680x = 0;
        this.f7681y = new HashSet<>();
        this.f7664f = true;
        this.f7682z = true;
        this.f7656B = 0;
        this.f7657C = 0;
        this.f7658D = 0;
        f7655s++;
    }

    public MapSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7669l = false;
        this.f7670m = false;
        this.f7671n = false;
        this.f7672o = true;
        this.f7673p = true;
        this.f7674q = true;
        this.f7659a = null;
        this.f7660b = null;
        this.f7661c = null;
        this.f7663e = false;
        this.f7676t = true;
        this.f7677u = new ArrayList();
        this.f7678v = new ArrayList();
        this.f7679w = 0;
        this.f7680x = 0;
        this.f7681y = new HashSet<>();
        this.f7664f = true;
        this.f7682z = true;
        this.f7656B = 0;
        this.f7657C = 0;
        this.f7658D = 0;
        f7655s++;
    }

    public MapSurfaceView(Context context, C3006ai.EnumC3007a enumC3007a) {
        super(context, enumC3007a);
        this.f7669l = false;
        this.f7670m = false;
        this.f7671n = false;
        this.f7672o = true;
        this.f7673p = true;
        this.f7674q = true;
        this.f7659a = null;
        this.f7660b = null;
        this.f7661c = null;
        this.f7663e = false;
        this.f7676t = true;
        this.f7677u = new ArrayList();
        this.f7678v = new ArrayList();
        this.f7679w = 0;
        this.f7680x = 0;
        this.f7681y = new HashSet<>();
        this.f7664f = true;
        this.f7682z = true;
        this.f7656B = 0;
        this.f7657C = 0;
        this.f7658D = 0;
        f7655s++;
    }

    public MapSurfaceView(Context context, C3006ai.EnumC3007a enumC3007a, boolean z) {
        super(context, enumC3007a, z);
        this.f7669l = false;
        this.f7670m = false;
        this.f7671n = false;
        this.f7672o = true;
        this.f7673p = true;
        this.f7674q = true;
        this.f7659a = null;
        this.f7660b = null;
        this.f7661c = null;
        this.f7663e = false;
        this.f7676t = true;
        this.f7677u = new ArrayList();
        this.f7678v = new ArrayList();
        this.f7679w = 0;
        this.f7680x = 0;
        this.f7681y = new HashSet<>();
        this.f7664f = true;
        this.f7682z = true;
        this.f7656B = 0;
        this.f7657C = 0;
        this.f7658D = 0;
        f7655s++;
    }

    public MapSurfaceView(Context context, boolean z) {
        super(context, z);
        this.f7669l = false;
        this.f7670m = false;
        this.f7671n = false;
        this.f7672o = true;
        this.f7673p = true;
        this.f7674q = true;
        this.f7659a = null;
        this.f7660b = null;
        this.f7661c = null;
        this.f7663e = false;
        this.f7676t = true;
        this.f7677u = new ArrayList();
        this.f7678v = new ArrayList();
        this.f7679w = 0;
        this.f7680x = 0;
        this.f7681y = new HashSet<>();
        this.f7664f = true;
        this.f7682z = true;
        this.f7656B = 0;
        this.f7657C = 0;
        this.f7658D = 0;
        f7655s++;
    }

    /* renamed from: a */
    private synchronized boolean m18006a(long j, BmLayer bmLayer) {
        if (bmLayer != null) {
            if (this.f7659a != null) {
                AppBaseMap baseMap = this.f7659a.getBaseMap();
                if (baseMap == null) {
                    return false;
                }
                synchronized (this) {
                    if (this.f7677u.contains(bmLayer)) {
                        return false;
                    }
                    this.f7677u.add(bmLayer);
                    return baseMap.addBmLayerBelow(j, bmLayer.m18052a(), 1, 0);
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    /* renamed from: a */
    public InterfaceSurfaceHolder$Callback2C3005ah mo17974a(C3006ai.EnumC3007a enumC3007a, boolean z, Context context) {
        InterfaceSurfaceHolder$Callback2C3005ah mo17974a = super.mo17974a(enumC3007a, z, context);
        if (mo17974a instanceof C3042f) {
            C3042f c3042f = (C3042f) mo17974a;
            if (this.f7682z) {
                c3042f.m17858a(new C2988a(this, null));
            }
        }
        return mo17974a;
    }

    /* renamed from: a */
    protected void m18007a() {
        MapController mapController = this.f7659a;
        if (mapController == null || mapController.getBaseMap() == null || this.f7660b == null) {
            return;
        }
        this.f7678v.clear();
        this.f7660b.m17983a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    /* renamed from: a */
    public void mo17975a(Context context, C3006ai.EnumC3007a enumC3007a, boolean z) {
        super.mo17975a(context, enumC3007a, z);
        setBackgroundColor(Color.rgb(244, 242, 240));
        setPixelFormatTransparent(false);
        this.f7665g = new C3011am();
        this.f7666h = new GestureDetector(context, this.f7665g);
        this.f7662d = new C3074p(new WeakReference(this), this);
        setRenderer(this.f7662d);
        setRenderMode(1);
        this.f7665g.m17948a(new C2989b(this, null));
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b.m18459a().m18457a("BasicMap surfaceView initView");
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean addBmLayer(BmLayer bmLayer) {
        return m18006a(0L, bmLayer);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean addBmLayerBelow(Overlay overlay, BmLayer bmLayer) {
        return m18006a(overlay != null ? overlay.mLayerID : 0L, bmLayer);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public synchronized boolean addOverlay(Overlay overlay) {
        if (overlay != null) {
            if (this.f7659a != null) {
                AppBaseMap baseMap = this.f7659a.getBaseMap();
                if (baseMap == null) {
                    return false;
                }
                if (overlay instanceof C3013ao) {
                    return ((InnerOverlay) overlay).addedToMapView();
                } else if (overlay instanceof InnerOverlay) {
                    if (((InnerOverlay) overlay).mBaseMap == null) {
                        ((InnerOverlay) overlay).mBaseMap = getController().getBaseMap();
                    }
                    if (((InnerOverlay) overlay).addedToMapView()) {
                        synchronized (this) {
                            this.f7678v.add(overlay);
                            this.f7660b.m17982a((InnerOverlay) overlay);
                        }
                        return true;
                    }
                    return false;
                } else if (overlay instanceof ItemizedOverlay) {
                    overlay.mLayerID = baseMap.AddLayer(((ItemizedOverlay) overlay).getUpdateType(), 0, "item");
                    if (overlay.mLayerID == 0) {
                        return false;
                    }
                    synchronized (this) {
                        this.f7678v.add(overlay);
                        ((ItemizedOverlay) overlay).m18044a();
                        baseMap.SetLayersClickable(overlay.mLayerID, true);
                        baseMap.ShowLayers(overlay.mLayerID, true);
                        baseMap.UpdateLayers(overlay.mLayerID);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void addSimpleOnGestureListener(GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        this.f7665g.m17948a(simpleOnGestureListener);
    }

    public void addStateListener(InterfaceC2999ab interfaceC2999ab) {
        if (interfaceC2999ab != null) {
            this.f7681y.add(interfaceC2999ab);
        }
    }

    public void animateTo(MapStatus mapStatus, int i) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.setMapStatusWithAnimation(mapStatus, i);
        }
    }

    public void animateTo(MapStatus mapStatus, int i, int i2) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.setMapStatusWithAnimation(mapStatus, i, i2);
        }
    }

    /* renamed from: b */
    protected void m18004b() {
        MapController mapController = this.f7659a;
        if (mapController == null || mapController.getBaseMap() == null) {
            return;
        }
        m18007a();
    }

    public void beginLocationLayerAnimation() {
        LocationOverlay locationOverlay = this.f7675r;
        if (locationOverlay != null) {
            locationOverlay.beginLocationLayerAnimation();
        }
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ Bitmap captureImageFromSurface(int i, int i2, int i3, int i4, Object obj, Bitmap.Config config) {
        return super.captureImageFromSurface(i, i2, i3, i4, obj, config);
    }

    public void clearDefaultLocationLayerData(Bundle bundle) {
        this.f7675r.clearLocationLayerData(bundle);
    }

    public void doCaptureMapView(InterfaceC3039c interfaceC3039c, int i, int i2) {
        this.f7662d.m17730a(interfaceC3039c, i, i2);
    }

    public void doCaptureMapView(InterfaceC3039c interfaceC3039c, int i, int i2, Bitmap.Config config) {
        this.f7662d.m17728a(interfaceC3039c, i, i2, config);
    }

    public void doCaptureMapView(InterfaceC3039c interfaceC3039c, Rect rect, Bitmap.Config config) {
        if (rect != null) {
            int i = rect.left;
            int i2 = this.f7680x < rect.bottom ? 0 : this.f7680x - rect.bottom;
            int width = rect.width();
            int height = rect.height();
            if (i < 0 || i2 < 0 || width <= 0 || height <= 0) {
                return;
            }
            if (width > this.f7679w) {
                width = Math.abs(rect.width()) - (rect.right - this.f7679w);
            }
            int i3 = width;
            int abs = height > this.f7680x ? Math.abs(rect.height()) - (rect.bottom - this.f7680x) : height;
            if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                return;
            }
            this.f7662d.m17729a(interfaceC3039c, i, i2, i3, abs, config);
            requestRender();
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean enable3D() {
        return true;
    }

    public void forceSetTraffic(boolean z) {
        if (this.f7659a != null) {
            this.f7669l = z;
        }
        f7654A.submit(new RunnableC3079u(this));
    }

    public C2925d getBaseMap() {
        return this.f7668j;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public List<BmLayer> getBmlayers() {
        return this.f7677u;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapController getController() {
        return this.f7659a;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapStatus getCurrentMapStatus() {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            return mapController.getCurrentMapStatus();
        }
        return null;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getCurrentZoomLevel() {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            return mapController.getCurrentZoomLevel();
        }
        return 0.0f;
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ int getDebugFlags() {
        return super.getDebugFlags();
    }

    public LocationOverlay getDefaultLocationLay() {
        return this.f7675r;
    }

    public int getFPS() {
        return this.f7740k.mo17844e();
    }

    public float getFZoomToBoundF(MapBound mapBound, MapBound mapBound2) {
        if (this.f7659a == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("left", mapBound.leftBottomPt.getIntX());
        bundle.putInt("bottom", mapBound.leftBottomPt.getIntY());
        bundle.putInt("right", mapBound.rightTopPt.getIntX());
        bundle.putInt("top", mapBound.rightTopPt.getIntY());
        Bundle bundle2 = new Bundle();
        bundle2.putInt("left", mapBound2.leftBottomPt.getIntX());
        bundle2.putInt("bottom", mapBound2.leftBottomPt.getIntY());
        bundle2.putInt("right", mapBound2.rightTopPt.getIntX());
        bundle2.putInt("top", mapBound2.rightTopPt.getIntY());
        return this.f7659a.GetFZoomToBoundF(bundle, bundle2);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapStatus.GeoBound getGeoRound() {
        MapController mapController = this.f7659a;
        if (mapController == null) {
            return null;
        }
        return mapController.getMapStatus().geoRound;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public int getLatitudeSpan() {
        MapStatus mapStatus = getMapStatus();
        C3070l c3070l = (C3070l) getProjection();
        return (int) Math.abs(c3070l.fromPixels(mapStatus.winRound.left, mapStatus.winRound.top).getLatitude() - c3070l.fromPixels(mapStatus.winRound.right - 1, mapStatus.winRound.bottom - 1).getLatitude());
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public int getLongitudeSpan() {
        MapStatus mapStatus = getMapStatus();
        C3070l c3070l = (C3070l) getProjection();
        return (int) Math.abs(c3070l.fromPixels(mapStatus.winRound.right - 1, mapStatus.winRound.bottom - 1).getLongitude() - c3070l.fromPixels(mapStatus.winRound.left, mapStatus.winRound.top).getLongitude());
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public GeoPoint getMapCenter() {
        MapController mapController = this.f7659a;
        if (mapController == null) {
            return null;
        }
        MapStatus mapStatus = mapController.getMapStatus();
        return new GeoPoint(mapStatus.centerPtY, mapStatus.centerPtX);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public int getMapRotation() {
        MapController mapController = this.f7659a;
        if (mapController == null) {
            return 0;
        }
        return mapController.getMapStatus().rotation;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapStatus getMapStatus() {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            return mapController.getMapStatus();
        }
        return null;
    }

    public MapViewListener getMapViewListener() {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            return mapController.getMapViewListener();
        }
        return null;
    }

    public OnLongPressListener getOnLongPressListener() {
        return this.f7665g.m17949a();
    }

    public synchronized Overlay getOverlay(int i) {
        if (i == 21) {
            return null;
        }
        for (Overlay overlay : this.f7678v) {
            if (overlay.mType == i) {
                return overlay;
            }
        }
        return null;
    }

    public synchronized Overlay getOverlay(Class<?> cls) {
        for (Overlay overlay : this.f7678v) {
            if (overlay.getClass() == cls) {
                return overlay;
            }
        }
        return null;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public List<Overlay> getOverlays() {
        return this.f7678v;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public int getOverlooking() {
        MapController mapController = this.f7659a;
        if (mapController == null) {
            return 0;
        }
        return mapController.getMapStatus().overlooking;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public Projection getProjection() {
        return this.f7661c;
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ InterfaceSurfaceHolder$Callback2C3005ah getRenderControl() {
        return super.getRenderControl();
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ int getRenderMode() {
        return super.getRenderMode();
    }

    public ExecutorService getSingleThreadPool() {
        return f7654A;
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ C3006ai.EnumC3007a getViewType() {
        return super.getViewType();
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapStatus.WinRound getWinRound() {
        MapController mapController = this.f7659a;
        if (mapController == null) {
            return null;
        }
        return mapController.getMapStatus().winRound;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomLevel() {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            return mapController.getZoomLevel();
        }
        return 0.0f;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomToBound(MapBound mapBound) {
        com.baidu.platform.comapi.util.SysOSUtil sysOSUtil = com.baidu.platform.comapi.util.SysOSUtil.getInstance();
        return getZoomToBound(mapBound, sysOSUtil.getScreenWidth(), sysOSUtil.getScreenHeight());
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomToBound(MapBound mapBound, int i, int i2) {
        if (this.f7659a == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("left", mapBound.leftBottomPt.getIntX());
        bundle.putInt("bottom", mapBound.leftBottomPt.getIntY());
        bundle.putInt("right", mapBound.rightTopPt.getIntX());
        bundle.putInt("top", mapBound.rightTopPt.getIntY());
        return this.f7659a.getZoomToBound(bundle, i, i2);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomToBoundF(MapBound mapBound) {
        com.baidu.platform.comapi.util.SysOSUtil sysOSUtil = com.baidu.platform.comapi.util.SysOSUtil.getInstance();
        return getZoomToBoundF(mapBound, sysOSUtil.getScreenWidth(), sysOSUtil.getScreenHeight());
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomToBoundF(MapBound mapBound, int i, int i2) {
        if (this.f7659a == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("left", mapBound.leftBottomPt.getIntX());
        bundle.putInt("bottom", mapBound.leftBottomPt.getIntY());
        bundle.putInt("right", mapBound.rightTopPt.getIntX());
        bundle.putInt("top", mapBound.rightTopPt.getIntY());
        return this.f7659a.getZoomToBoundF(bundle);
    }

    public boolean inRangeOfView(float f, float f2) {
        float f3 = 0;
        return f >= f3 && f <= ((float) (this.f7679w + 0)) && f2 >= f3 && f2 <= ((float) (this.f7680x + 0));
    }

    public synchronized boolean insertOverlay(Overlay overlay, int i) {
        if ((overlay instanceof InnerOverlay) && this.f7659a != null) {
            if (((InnerOverlay) overlay).mBaseMap == null) {
                ((InnerOverlay) overlay).mBaseMap = this.f7659a.getBaseMap();
            }
            this.f7678v.add(overlay);
            this.f7660b.m17982a((InnerOverlay) overlay);
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean isBaseIndoorMap() {
        return this.f7672o;
    }

    public boolean isPredictTraffic() {
        return this.f7656B > 0 || this.f7657C > 0 || this.f7658D > 0;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean isSatellite() {
        return this.f7670m;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean isStreetRoad() {
        return this.f7671n;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean isTraffic() {
        return this.f7669l;
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj, android.view.SurfaceView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.updateDrawFPS();
        }
        if (Build.VERSION.SDK_INT < 14) {
            this.f7664f = false;
        }
    }

    public void onBackground() {
        if (this.f7674q) {
            return;
        }
        MapController mapController = this.f7659a;
        if (mapController != null && mapController.getBaseMap() != null) {
            this.f7659a.getBaseMap().OnBackground();
        }
        this.f7674q = true;
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj, android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.updateDrawFPS();
        }
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT < 14) {
            this.f7664f = true;
        }
    }

    public void onForeground() {
        if (this.f7674q) {
            MapController mapController = this.f7659a;
            if (mapController != null && mapController.getBaseMap() != null) {
                this.f7659a.getBaseMap().OnForeground();
            }
            this.f7674q = false;
            if (this.f7740k.mo17854b() == C3006ai.EnumC3007a.VULKAN) {
                C3074p c3074p = this.f7662d;
                if (c3074p != null) {
                    c3074p.m17734a();
                }
                super.onResume();
            }
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (this == view && keyEvent.getAction() == 0) {
            switch (i) {
                case 19:
                    this.f7659a.scrollBy(0, -50);
                    return true;
                case 20:
                    this.f7659a.scrollBy(0, 50);
                    return true;
                case 21:
                    this.f7659a.scrollBy(-50, 0);
                    return true;
                case 22:
                    this.f7659a.scrollBy(50, 0);
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.MapRenderModeChangeListener
    public void onMapRenderModeChange(int i) {
        InterfaceC3000ac interfaceC3000ac;
        if (i == 1) {
            requestRender();
        } else if (i == 0) {
            if (getRenderMode() != 0) {
                setRenderMode(0);
            }
        } else if (i != 2 || (interfaceC3000ac = this.f7667i) == null) {
        } else {
            interfaceC3000ac.m17985a();
        }
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public void onPause() {
        if (this.f7673p) {
            return;
        }
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b.m18459a().m18457a("BasicMap onPause");
        }
        C3074p c3074p = this.f7662d;
        if (c3074p != null) {
            c3074p.m17722b();
        }
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.onPause();
        }
        Iterator<InterfaceC2999ab> it = this.f7681y.iterator();
        while (it.hasNext()) {
            it.next().m17987a(this);
        }
        super.onPause();
        this.f7673p = true;
    }

    public void onRecycle() {
        MapController mapController = this.f7659a;
        if (mapController == null || mapController.getBaseMap() == null) {
            return;
        }
        this.f7659a.getBaseMap().ResetImageRes();
    }

    @Override // com.baidu.platform.comapi.map.MapRenderModeChangeListener
    public void onRequestRender() {
        requestRender();
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public void onResume() {
        if (this.f7673p) {
            if (OpenLogUtil.isMapLogEnable()) {
                C2898b m18459a = C2898b.m18459a();
                m18459a.m18457a("BasicMap onResume isInited = " + this.f7663e);
            }
            if (this.f7663e) {
                C3074p c3074p = this.f7662d;
                if (c3074p != null) {
                    c3074p.m17734a();
                }
                MapController mapController = this.f7659a;
                if (mapController != null) {
                    mapController.onResume();
                }
                Iterator<InterfaceC2999ab> it = this.f7681y.iterator();
                while (it.hasNext()) {
                    it.next().m17986b(this);
                }
                setRenderMode(1);
                super.onResume();
                this.f7673p = false;
            }
        }
    }

    @Override // android.view.View, com.baidu.platform.comapi.map.MapViewInterface
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MapStatus mapStatus = getMapStatus();
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int x = (int) motionEvent.getX(i);
            int y = (int) motionEvent.getY(i);
            if (mapStatus == null || x < mapStatus.winRound.left || x > mapStatus.winRound.right || y < mapStatus.winRound.top || y > mapStatus.winRound.bottom) {
                return false;
            }
        }
        try {
            if (this.f7666h == null || !this.f7666h.onTouchEvent(motionEvent)) {
                if (this.f7659a != null) {
                    if (this.f7659a.handleTouchEvent(motionEvent)) {
                        return true;
                    }
                }
                return super.onTouchEvent(motionEvent);
            }
            return true;
        } catch (Exception unused) {
            return super.onTouchEvent(motionEvent);
        }
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ void queueEvent(Runnable runnable) {
        super.queueEvent(runnable);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void refresh(Overlay overlay) {
        if (overlay == null || this.f7659a == null) {
            return;
        }
        if (overlay instanceof ItemizedOverlay) {
            ItemizedOverlay itemizedOverlay = (ItemizedOverlay) overlay;
            if (itemizedOverlay.m18037b()) {
                if (itemizedOverlay.getAllItem().size() <= 0) {
                    this.f7659a.getBaseMap().ClearLayer(overlay.mLayerID);
                    this.f7659a.getBaseMap().ShowLayers(overlay.mLayerID, false);
                } else {
                    this.f7659a.getBaseMap().ShowLayers(overlay.mLayerID, true);
                }
                this.f7659a.getBaseMap().UpdateLayers(overlay.mLayerID);
                itemizedOverlay.m18038a(false);
            }
        }
        MapController mapController = this.f7659a;
        if (mapController == null || mapController.getBaseMap() == null) {
            return;
        }
        this.f7659a.getBaseMap().UpdateLayers(overlay.mLayerID);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public synchronized boolean removeBmLayer(BmLayer bmLayer) {
        if (bmLayer != null) {
            if (this.f7659a != null) {
                AppBaseMap baseMap = this.f7659a.getBaseMap();
                if (baseMap == null) {
                    return false;
                }
                baseMap.removeBmLayer(bmLayer.m18052a());
                synchronized (this) {
                    this.f7677u.remove(bmLayer);
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public synchronized boolean removeOverlay(Overlay overlay) {
        if (overlay != null) {
            if (this.f7659a != null) {
                AppBaseMap baseMap = this.f7659a.getBaseMap();
                if (baseMap == null) {
                    return false;
                }
                baseMap.ClearLayer(overlay.mLayerID);
                baseMap.ShowLayers(overlay.mLayerID, false);
                baseMap.UpdateLayers(overlay.mLayerID);
                baseMap.RemoveLayer(overlay.mLayerID);
                synchronized (this) {
                    if (overlay instanceof ItemizedOverlay) {
                        this.f7678v.remove(overlay);
                    } else if (overlay instanceof InnerOverlay) {
                        this.f7678v.remove(overlay);
                        this.f7660b.m17981a(overlay);
                    }
                    overlay.mLayerID = 0L;
                }
                return true;
            }
        }
        return false;
    }

    public void removeSimpleOnGestureListener(GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        this.f7665g.m17945b(simpleOnGestureListener);
    }

    public void removeStateListener(InterfaceC2999ab interfaceC2999ab) {
        if (interfaceC2999ab != null) {
            this.f7681y.remove(interfaceC2999ab);
        }
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ void requestRender() {
        super.requestRender();
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void saveScreenToLocal(String str) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.saveScreenToLocal(str);
        }
    }

    public void saveScreenToLocal(String str, Rect rect) {
        C2925d c2925d = this.f7668j;
        if (c2925d == null || c2925d.m18359a() == null) {
            return;
        }
        String str2 = null;
        if (rect != null) {
            int i = rect.left;
            int i2 = this.f7680x < rect.bottom ? 0 : this.f7680x - rect.bottom;
            int width = rect.width();
            int height = rect.height();
            if (i < 0 || i2 < 0 || width <= 0 || height <= 0) {
                return;
            }
            if (width > this.f7679w) {
                width = Math.abs(rect.width()) - (rect.right - this.f7679w);
            }
            if (height > this.f7680x) {
                height = Math.abs(rect.height()) - (rect.bottom - this.f7680x);
            }
            if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                this.f7668j.m18359a().SaveScreenToLocal(str, null);
                return;
            } else if (width != 0 && height != 0) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("x", i);
                    jSONObject.put("y", i2);
                    jSONObject.put("width", width);
                    jSONObject.put("height", height);
                    str2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
                } catch (Exception unused) {
                }
            }
        }
        this.f7668j.m18359a().SaveScreenToLocal(str, str2);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setBaseIndoorMap(boolean z) {
        if (this.f7659a != null) {
            this.f7672o = z;
        }
        f7654A.submit(new RunnableC3082x(this, z));
    }

    public void setBaseMap(C2925d c2925d) {
        this.f7668j = c2925d;
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ void setDebugFlags(int i) {
        super.setDebugFlags(i);
    }

    public void setDefaultLocationLayerData(List<OverlayLocationData> list) {
        this.f7675r.setLocationLayerData(list);
    }

    public void setFPS(int i) {
        this.f7740k.mo17864a(i);
    }

    public void setFirstFrameListener(InterfaceC3041e interfaceC3041e) {
        C3074p c3074p = this.f7662d;
        if (c3074p != null) {
            c3074p.m17727a(interfaceC3041e);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setGeoRound(MapStatus.GeoBound geoBound) {
    }

    public boolean setItsPreTime(int i, int i2, int i3) {
        AppBaseMap baseMap;
        if (this.f7656B == i && this.f7657C == i2 && this.f7658D == i3) {
            return true;
        }
        MapController mapController = this.f7659a;
        if (mapController == null || (baseMap = mapController.getBaseMap()) == null) {
            return false;
        }
        this.f7656B = i;
        this.f7657C = i2;
        this.f7658D = i3;
        return baseMap.SetItsPreTime(i, i2, i3);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setMapCenter(GeoPoint geoPoint) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            MapStatus mapStatus = mapController.getMapStatus();
            mapStatus.centerPtX = geoPoint.getLongitude();
            mapStatus.centerPtY = geoPoint.getLatitude();
            this.f7659a.setMapStatus(mapStatus);
        }
    }

    public void setMapController(MapController mapController) {
        if (this.f7659a != null) {
            return;
        }
        this.f7659a = mapController;
        this.f7662d.m17725a(this.f7659a.getBaseMap());
        this.f7662d.m17723a(true);
        this.f7660b = new C3003af(this.f7659a.getBaseMap());
        this.f7659a.setOverlayMapCallBack(this.f7660b);
        this.f7659a.setMapViewInterface(this);
        m18004b();
        this.f7659a.setMapRenderModeChangeListener(this);
        this.f7663e = true;
        this.f7661c = new C3070l(this.f7659a);
        this.f7665g.m17947a(this.f7659a);
    }

    public void setMapRenderStableListener(InterfaceC3000ac interfaceC3000ac) {
        this.f7667i = interfaceC3000ac;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setMapStatus(MapStatus mapStatus) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.setMapStatus(mapStatus);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setMapTo2D(boolean z) {
    }

    public void setOnLongPressListener(OnLongPressListener onLongPressListener) {
        this.f7665g.m17946a(onLongPressListener);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setOverlooking(int i) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            MapStatus mapStatus = mapController.getMapStatus();
            mapStatus.overlooking = i;
            this.f7659a.setMapStatus(mapStatus);
        }
    }

    public void setPixelFormatTransparent(boolean z) {
        SurfaceHolder holder;
        int i;
        if (z) {
            holder = getHolder();
            i = -3;
        } else {
            holder = getHolder();
            i = -1;
        }
        holder.setFormat(i);
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ void setRenderMode(int i) {
        super.setRenderMode(i);
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj
    public /* bridge */ /* synthetic */ void setRenderer(InterfaceC3015aq interfaceC3015aq) {
        super.setRenderer(interfaceC3015aq);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setRotation(int i) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            MapStatus mapStatus = mapController.getMapStatus();
            mapStatus.rotation = i;
            this.f7659a.setMapStatus(mapStatus);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setSatellite(boolean z) {
        if (this.f7659a != null) {
            this.f7670m = z;
        }
        f7654A.submit(new RunnableC3078t(this));
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setStreetRoad(boolean z) {
        if (this.f7659a != null) {
            this.f7671n = z;
        }
        f7654A.submit(new RunnableC3081w(this));
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setTraffic(boolean z) {
        if (this.f7669l == z) {
            return;
        }
        if (this.f7659a != null) {
            this.f7669l = z;
        }
        f7654A.submit(new RunnableC3080v(this));
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setWinRound(MapStatus.WinRound winRound) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            MapStatus mapStatus = mapController.getMapStatus();
            mapStatus.winRound = winRound;
            this.f7659a.setMapStatus(mapStatus);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setZoomLevel(float f) {
        if (this.f7659a == null) {
            return;
        }
        int i = getController().getFocusedBaseIndoorMapInfo() != null ? 22 : 21;
        if (f < 4.0f) {
            f = 4.0f;
        } else {
            float f2 = i;
            if (f > f2) {
                f = f2;
            }
        }
        MapStatus mapStatus = getMapStatus();
        if (mapStatus != null) {
            mapStatus.level = f;
            animateTo(mapStatus, 300);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setZoomLevel(int i) {
        setZoomLevel(i);
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj, android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.updateDrawFPS();
        }
        C3074p c3074p = this.f7662d;
        if (c3074p != null) {
            c3074p.f7986a = i2;
            c3074p.f7987b = i3;
            c3074p.f7988c = 0;
        }
        this.f7679w = i2;
        this.f7680x = i3;
        super.surfaceChanged(surfaceHolder, i, i2, i3);
        if (this.f7659a != null) {
            MapStatus mapStatus = getMapStatus();
            if (mapStatus != null) {
                mapStatus.winRound.left = 0;
                mapStatus.winRound.top = 0;
                mapStatus.winRound.bottom = i3;
                mapStatus.winRound.right = i2;
                if (this.f7676t) {
                    this.f7676t = false;
                    this.f7659a.setMapStatusWithAnimation(mapStatus, 4, 0);
                } else {
                    this.f7659a.setMapStatus(mapStatus, false);
                }
                if (this.f7659a.getMapViewSurfaceListener() != null) {
                    this.f7659a.getMapViewSurfaceListener().onSurfaceChanged(i2, i3);
                }
            }
            MapStatus mapStatus2 = getMapStatus();
            this.f7679w = Math.abs(mapStatus2.winRound.right - mapStatus2.winRound.left);
            this.f7680x = Math.abs(mapStatus2.winRound.bottom - mapStatus2.winRound.top);
            this.f7659a.setScreenSize(this.f7679w, this.f7680x);
            if (this.f7659a.isNaviMode() && this.f7659a.getNaviMapViewListener() != null) {
                this.f7659a.getNaviMapViewListener().resizeScreen(i2, i3);
            }
        }
        C2925d c2925d = this.f7668j;
        if (c2925d != null) {
            c2925d.m18356a(this.f7679w, this.f7680x);
        }
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj, android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.updateDrawFPS();
        }
        super.surfaceCreated(surfaceHolder);
        if (surfaceHolder == null || surfaceHolder.getSurface().isValid()) {
            return;
        }
        surfaceDestroyed(surfaceHolder);
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj, android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        MapController mapController = this.f7659a;
        if (mapController != null) {
            mapController.updateDrawFPS();
        }
        super.surfaceDestroyed(surfaceHolder);
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj, android.view.SurfaceHolder.Callback2
    @Deprecated
    public /* bridge */ /* synthetic */ void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
        super.surfaceRedrawNeeded(surfaceHolder);
    }

    @Override // com.baidu.platform.comapi.map.SurfaceHolder$Callback2C3008aj, android.view.SurfaceHolder.Callback2
    @TargetApi(26)
    public /* bridge */ /* synthetic */ void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        super.surfaceRedrawNeededAsync(surfaceHolder, runnable);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean switchOverlay(Overlay overlay, Overlay overlay2) {
        MapController mapController;
        AppBaseMap baseMap;
        if (overlay == null || overlay2 == null || (mapController = this.f7659a) == null || (baseMap = mapController.getBaseMap()) == null) {
            return false;
        }
        return baseMap.SwitchLayer(overlay.mLayerID, overlay2.mLayerID);
    }

    public void unInit() {
        C2925d c2925d = this.f7668j;
        if (c2925d != null) {
            if (c2925d.f7277h != null) {
                for (InterfaceC3010al interfaceC3010al : this.f7668j.f7277h) {
                    if (interfaceC3010al != null) {
                        interfaceC3010al.mo17953d();
                    }
                }
            }
            this.f7668j.m18370M();
            this.f7668j = null;
        }
        this.f7659a.unInit();
        this.f7659a = null;
        this.f7660b.m17983a();
        this.f7660b = null;
        this.f7661c = null;
        this.f7662d = null;
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b.m18459a().m18457a("BasicMap surfaceView unInit");
        }
    }
}
