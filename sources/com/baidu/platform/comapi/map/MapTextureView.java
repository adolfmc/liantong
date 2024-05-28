package com.baidu.platform.comapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapsdkplatform.comapi.map.C2925d;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.MapBound;
import com.baidu.platform.comapi.bmsdk.BmLayer;
import com.baidu.platform.comapi.map.MapStatus;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import java.util.ArrayList;
import java.util.List;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapTextureView extends TextureView$SurfaceTextureListenerC3057h implements MapRenderModeChangeListener, MapViewInterface, InterfaceC3009ak {

    /* renamed from: a */
    protected C2925d f7686a;

    /* renamed from: b */
    protected MapController f7687b;

    /* renamed from: c */
    protected C3003af f7688c;

    /* renamed from: d */
    protected C3070l f7689d;

    /* renamed from: e */
    protected C3074p f7690e;

    /* renamed from: f */
    int f7691f;

    /* renamed from: g */
    int f7692g;

    /* renamed from: h */
    protected List<Overlay> f7693h;

    /* renamed from: i */
    protected InterfaceC3000ac f7694i;

    /* renamed from: j */
    protected C3011am f7695j;

    /* renamed from: k */
    protected GestureDetector f7696k;

    /* renamed from: l */
    private List<BmLayer> f7697l;

    /* renamed from: m */
    private InterfaceC2992c f7698m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.MapTextureView$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2990a implements GLSurfaceView.EGLContextFactory {

        /* renamed from: b */
        private int f7700b;

        private C2990a() {
            this.f7700b = 12440;
        }

        /* renamed from: a */
        private String m17993a(int i) {
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
                    return m17991b(i);
            }
        }

        /* renamed from: b */
        private String m17991b(int i) {
            return "0x" + Integer.toHexString(i);
        }

        /* renamed from: a */
        public void m17992a(String str, int i) {
            throw new RuntimeException(m17990b(str, i));
        }

        /* renamed from: b */
        public String m17990b(String str, int i) {
            return str + " failed: " + m17993a(i);
        }

        @Override // android.opengl.GLSurfaceView.EGLContextFactory
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{this.f7700b, 2, 12344});
        }

        @Override // android.opengl.GLSurfaceView.EGLContextFactory
        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                m17992a("eglDestroyContex", egl10.eglGetError());
            }
            MapTextureView.this.onRecycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.MapTextureView$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2991b extends GestureDetector.SimpleOnGestureListener {
        private C2991b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            GeoPoint fromPixels;
            super.onLongPress(motionEvent);
            if (MapTextureView.this.f7687b == null || MapTextureView.this.f7687b.getBaseMap() == null || !MapTextureView.this.f7687b.mIsMapLoadFinish) {
                return;
            }
            String GetNearlyObjID = MapTextureView.this.f7687b.getBaseMap().GetNearlyObjID(-1L, (int) motionEvent.getX(), (int) motionEvent.getY(), MapTextureView.this.f7687b.nearlyRadius);
            if (GetNearlyObjID == null || GetNearlyObjID.equals("")) {
                if (MapTextureView.this.f7687b.mListeners != null) {
                    fromPixels = MapTextureView.this.getProjection() != null ? MapTextureView.this.getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY()) : null;
                    if (fromPixels == null) {
                        return;
                    }
                    for (InterfaceC3010al interfaceC3010al : MapTextureView.this.f7687b.mListeners) {
                        if (interfaceC3010al != null && fromPixels != null) {
                            interfaceC3010al.mo17954c(fromPixels);
                        }
                    }
                }
            } else if (MapTextureView.this.f7687b.mListeners != null) {
                fromPixels = MapTextureView.this.getProjection() != null ? MapTextureView.this.getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY()) : null;
                for (InterfaceC3010al interfaceC3010al2 : MapTextureView.this.f7687b.mListeners) {
                    if (interfaceC3010al2 != null) {
                        if (interfaceC3010al2.mo17958b(GetNearlyObjID)) {
                            MapTextureView.this.f7687b.mHasMapObjDraging = true;
                        } else if (fromPixels != null) {
                            interfaceC3010al2.mo17954c(fromPixels);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.MapTextureView$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2992c {
        /* renamed from: a */
        void m17989a(int i);
    }

    public MapTextureView(Context context) {
        super(context);
        this.f7687b = null;
        this.f7688c = null;
        this.f7689d = null;
        this.f7690e = null;
        this.f7697l = new ArrayList();
        this.f7693h = new ArrayList();
        m17996a(context);
    }

    public MapTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7687b = null;
        this.f7688c = null;
        this.f7689d = null;
        this.f7690e = null;
        this.f7697l = new ArrayList();
        this.f7693h = new ArrayList();
        m17996a(context);
    }

    public MapTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7687b = null;
        this.f7688c = null;
        this.f7689d = null;
        this.f7690e = null;
        this.f7697l = new ArrayList();
        this.f7693h = new ArrayList();
        m17996a(context);
    }

    /* renamed from: a */
    private void m17996a(Context context) {
        setEGLContextClientVersion(2);
        this.f7695j = new C3011am();
        this.f7696k = new GestureDetector(context, this.f7695j);
        this.f7695j.m17948a(new C2991b());
    }

    /* renamed from: a */
    private synchronized boolean m17997a(long j, BmLayer bmLayer) {
        if (bmLayer != null) {
            if (this.f7687b != null) {
                AppBaseMap baseMap = this.f7687b.getBaseMap();
                if (baseMap == null) {
                    return false;
                }
                synchronized (this) {
                    if (this.f7697l.contains(bmLayer)) {
                        return false;
                    }
                    this.f7697l.add(bmLayer);
                    return baseMap.addBmLayerBelow(j, bmLayer.m18052a(), 1, 0);
                }
            }
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean addBmLayer(BmLayer bmLayer) {
        return m17997a(0L, bmLayer);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean addBmLayerBelow(Overlay overlay, BmLayer bmLayer) {
        return m17997a(overlay != null ? overlay.mLayerID : 0L, bmLayer);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean addOverlay(Overlay overlay) {
        MapController mapController;
        AppBaseMap baseMap;
        if (overlay == null || (mapController = this.f7687b) == null || (baseMap = mapController.getBaseMap()) == null) {
            return false;
        }
        if (overlay instanceof InnerOverlay) {
            InnerOverlay innerOverlay = (InnerOverlay) overlay;
            if (innerOverlay.mBaseMap == null) {
                innerOverlay.mBaseMap = getController().getBaseMap();
            }
            if (innerOverlay.addedToMapView()) {
                this.f7693h.add(overlay);
                this.f7688c.m17982a(innerOverlay);
                return true;
            }
            return false;
        }
        if (overlay instanceof ItemizedOverlay) {
            ItemizedOverlay itemizedOverlay = (ItemizedOverlay) overlay;
            overlay.mLayerID = baseMap.AddLayer(itemizedOverlay.getUpdateType(), 0, "item");
            if (overlay.mLayerID == 0) {
                return false;
            }
            this.f7693h.add(overlay);
            itemizedOverlay.m18044a();
            baseMap.SetLayersClickable(overlay.mLayerID, true);
            baseMap.ShowLayers(overlay.mLayerID, true);
            baseMap.UpdateLayers(overlay.mLayerID);
            return true;
        }
        return false;
    }

    public void animateTo(MapStatus mapStatus, int i) {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            mapController.setMapStatusWithAnimation(mapStatus, i);
        }
    }

    public void attachBaseMapController(MapController mapController) {
        this.f7690e = new C3074p(this, this);
        this.f7687b = mapController;
        this.f7690e.m17725a(mapController.getBaseMap());
        setEGLContextFactory(new C2990a());
        setRenderer(this.f7690e);
        setRenderMode(0);
        this.f7690e.m17723a(true);
        this.f7688c = new C3003af(this.f7687b.getBaseMap());
        this.f7687b.setOverlayMapCallBack(this.f7688c);
        this.f7687b.setMapViewInterface(this);
        m17995b();
        this.f7687b.setMapRenderModeChangeListener(this);
        this.f7689d = new C3070l(this.f7687b);
        this.f7695j.m17947a(this.f7687b);
    }

    /* renamed from: b */
    protected void m17995b() {
        MapController mapController = this.f7687b;
        if (mapController == null || mapController.getBaseMap() == null) {
            return;
        }
        m17994c();
    }

    /* renamed from: c */
    protected void m17994c() {
        MapController mapController = this.f7687b;
        if (mapController == null || mapController.getBaseMap() == null || this.f7688c == null) {
            return;
        }
        this.f7693h.clear();
        this.f7688c.m17983a();
    }

    public void destroyForMultiViews() {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            mapController.unInitForMultiTextureView();
            this.f7687b = null;
        }
        C3003af c3003af = this.f7688c;
        if (c3003af != null) {
            c3003af.m17983a();
            this.f7688c = null;
        }
        this.f7689d = null;
    }

    public void doCaptureMapView(InterfaceC3039c interfaceC3039c, int i, int i2, Bitmap.Config config) {
        this.f7690e.m17728a(interfaceC3039c, i, i2, config);
    }

    public void doCaptureMapView(InterfaceC3039c interfaceC3039c, Rect rect, Bitmap.Config config) {
        if (rect != null) {
            int i = rect.left;
            int i2 = this.f7692g < rect.bottom ? 0 : this.f7692g - rect.bottom;
            int width = rect.width();
            int height = rect.height();
            if (i < 0 || i2 < 0 || width <= 0 || height <= 0) {
                return;
            }
            if (width > this.f7691f) {
                width = Math.abs(rect.width()) - (rect.right - this.f7691f);
            }
            int i3 = width;
            int abs = height > this.f7692g ? Math.abs(rect.height()) - (rect.bottom - this.f7692g) : height;
            if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                return;
            }
            this.f7690e.m17729a(interfaceC3039c, i, i2, i3, abs, config);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean enable3D() {
        return false;
    }

    public C2925d getBaseMap() {
        return this.f7686a;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public List<BmLayer> getBmlayers() {
        return this.f7697l;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapController getController() {
        return this.f7687b;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapStatus getCurrentMapStatus() {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            return mapController.getCurrentMapStatus();
        }
        return null;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getCurrentZoomLevel() {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            return mapController.getCurrentZoomLevel();
        }
        return 0.0f;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapStatus.GeoBound getGeoRound() {
        MapController mapController = this.f7687b;
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
        MapController mapController = this.f7687b;
        if (mapController == null) {
            return null;
        }
        MapStatus mapStatus = mapController.getMapStatus();
        return new GeoPoint(mapStatus.centerPtY, mapStatus.centerPtX);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public int getMapRotation() {
        MapController mapController = this.f7687b;
        if (mapController == null) {
            return 0;
        }
        return mapController.getMapStatus().rotation;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapStatus getMapStatus() {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            return mapController.getMapStatus();
        }
        return null;
    }

    public synchronized Overlay getOverlay(int i) {
        for (Overlay overlay : this.f7693h) {
            if (overlay.mType == i) {
                return overlay;
            }
        }
        return null;
    }

    public synchronized Overlay getOverlay(Class<?> cls) {
        for (Overlay overlay : this.f7693h) {
            if (overlay.getClass() == cls) {
                return overlay;
            }
        }
        return null;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public List<Overlay> getOverlays() {
        return this.f7693h;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public int getOverlooking() {
        MapController mapController = this.f7687b;
        if (mapController == null) {
            return 0;
        }
        return mapController.getMapStatus().overlooking;
    }

    public Overlay getPopupOverlay() {
        return null;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public Projection getProjection() {
        return this.f7689d;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public MapStatus.WinRound getWinRound() {
        MapController mapController = this.f7687b;
        if (mapController == null) {
            return null;
        }
        return mapController.getMapStatus().winRound;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomLevel() {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            return mapController.getZoomLevel();
        }
        return 0.0f;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomToBound(MapBound mapBound) {
        return getZoomToBound(mapBound, this.f7691f, this.f7692g);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomToBound(MapBound mapBound, int i, int i2) {
        if (this.f7687b == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("left", mapBound.leftBottomPt.getIntX());
        bundle.putInt("bottom", mapBound.leftBottomPt.getIntY());
        bundle.putInt("right", mapBound.rightTopPt.getIntX());
        bundle.putInt("top", mapBound.rightTopPt.getIntY());
        return this.f7687b.getZoomToBound(bundle, i, i2);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomToBoundF(MapBound mapBound) {
        return getZoomToBoundF(mapBound, this.f7691f, this.f7692g);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public float getZoomToBoundF(MapBound mapBound, int i, int i2) {
        if (this.f7687b == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("left", mapBound.leftBottomPt.getIntX());
        bundle.putInt("bottom", mapBound.leftBottomPt.getIntY());
        bundle.putInt("right", mapBound.rightTopPt.getIntX());
        bundle.putInt("top", mapBound.rightTopPt.getIntY());
        return this.f7687b.getZoomToBoundF(bundle);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean isBaseIndoorMap() {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean isSatellite() {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean isStreetRoad() {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean isTraffic() {
        return false;
    }

    public void listenMapRenderMessage(InterfaceC2992c interfaceC2992c) {
        this.f7698m = interfaceC2992c;
    }

    public void onDestroy() {
        C2925d c2925d = this.f7686a;
        if (c2925d != null) {
            if (c2925d.f7277h != null) {
                for (InterfaceC3010al interfaceC3010al : this.f7686a.f7277h) {
                    if (interfaceC3010al != null) {
                        interfaceC3010al.mo17953d();
                    }
                }
            }
            this.f7686a.m18370M();
            this.f7686a = null;
        }
        this.f7687b.unInit();
        this.f7687b = null;
        this.f7688c.m17983a();
        this.f7688c = null;
        this.f7689d = null;
    }

    @Override // com.baidu.platform.comapi.map.MapRenderModeChangeListener
    public void onMapRenderModeChange(int i) {
        InterfaceC3000ac interfaceC3000ac;
        InterfaceC2992c interfaceC2992c = this.f7698m;
        if (interfaceC2992c != null) {
            interfaceC2992c.m17989a(i);
        }
        if (i == 1) {
            requestRender();
        } else if (i == 0) {
            if (getRenderMode() != 0) {
                setRenderMode(0);
            }
        } else if (i != 2 || (interfaceC3000ac = this.f7694i) == null) {
        } else {
            interfaceC3000ac.m17985a();
        }
    }

    @Override // com.baidu.platform.comapi.map.TextureView$SurfaceTextureListenerC3057h
    public void onPause() {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            mapController.getBaseMap().OnPause();
        }
        super.onPause();
    }

    public void onRecycle() {
        MapController mapController = this.f7687b;
        if (mapController == null || mapController.getBaseMap() == null) {
            return;
        }
        this.f7687b.getBaseMap().ResetImageRes();
    }

    @Override // com.baidu.platform.comapi.map.MapRenderModeChangeListener
    public void onRequestRender() {
    }

    @Override // com.baidu.platform.comapi.map.TextureView$SurfaceTextureListenerC3057h
    public void onResume() {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            mapController.getBaseMap().OnResume();
        }
        super.onResume();
    }

    @Override // com.baidu.platform.comapi.map.TextureView$SurfaceTextureListenerC3057h, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        super.onSurfaceTextureAvailable(surfaceTexture, i, i2);
        this.f7691f = i;
        this.f7692g = i2;
        MapController mapController = this.f7687b;
        if (mapController != null) {
            if (mapController.getMapViewSurfaceListener() != null) {
                this.f7687b.getMapViewSurfaceListener().onSurfaceChanged(i, i2);
            }
            MapStatus mapStatus = getMapStatus();
            this.f7691f = Math.abs(mapStatus.winRound.right - mapStatus.winRound.left);
            this.f7692g = Math.abs(mapStatus.winRound.bottom - mapStatus.winRound.top);
        }
        C2925d c2925d = this.f7686a;
        if (c2925d != null) {
            c2925d.m18356a(this.f7691f, this.f7692g);
        }
    }

    @Override // com.baidu.platform.comapi.map.TextureView$SurfaceTextureListenerC3057h, android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        super.onSurfaceTextureDestroyed(surfaceTexture);
        return true;
    }

    @Override // com.baidu.platform.comapi.map.TextureView$SurfaceTextureListenerC3057h, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        super.onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
        this.f7691f = i;
        this.f7692g = i2;
        C3074p c3074p = this.f7690e;
        c3074p.f7986a = i;
        c3074p.f7987b = i2;
        c3074p.f7988c = 0;
        if (this.f7687b != null) {
            MapStatus mapStatus = getMapStatus();
            mapStatus.winRound.left = 0;
            mapStatus.winRound.top = 0;
            mapStatus.winRound.bottom = i2;
            mapStatus.winRound.right = i;
            this.f7687b.setMapStatusWithAnimation(mapStatus, 4, 0);
            if (this.f7687b.getMapViewSurfaceListener() != null) {
                this.f7687b.getMapViewSurfaceListener().onSurfaceChanged(i, i2);
            }
            MapStatus mapStatus2 = getMapStatus();
            this.f7691f = Math.abs(mapStatus2.winRound.right - mapStatus2.winRound.left);
            this.f7692g = Math.abs(mapStatus2.winRound.bottom - mapStatus2.winRound.top);
            this.f7687b.setScreenSize(this.f7691f, this.f7692g);
        }
        C2925d c2925d = this.f7686a;
        if (c2925d != null) {
            c2925d.m18356a(this.f7691f, this.f7692g);
        }
    }

    @Override // com.baidu.platform.comapi.map.TextureView$SurfaceTextureListenerC3057h, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        super.onSurfaceTextureUpdated(surfaceTexture);
    }

    @Override // android.view.View, com.baidu.platform.comapi.map.MapViewInterface
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.f7696k == null || !this.f7696k.onTouchEvent(motionEvent)) {
                if (this.f7687b != null) {
                    if (this.f7687b.handleTouchEvent(motionEvent)) {
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

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void refresh(Overlay overlay) {
        if (overlay == null || this.f7687b == null) {
            return;
        }
        if (overlay instanceof ItemizedOverlay) {
            ItemizedOverlay itemizedOverlay = (ItemizedOverlay) overlay;
            if (itemizedOverlay.m18037b()) {
                if (itemizedOverlay.getAllItem().size() <= 0) {
                    this.f7687b.getBaseMap().ClearLayer(overlay.mLayerID);
                    this.f7687b.getBaseMap().ShowLayers(overlay.mLayerID, false);
                } else {
                    this.f7687b.getBaseMap().ShowLayers(overlay.mLayerID, true);
                }
                this.f7687b.getBaseMap().UpdateLayers(overlay.mLayerID);
                itemizedOverlay.m18038a(false);
            }
        }
        MapController mapController = this.f7687b;
        if (mapController == null || mapController.getBaseMap() == null) {
            return;
        }
        this.f7687b.getBaseMap().UpdateLayers(overlay.mLayerID);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public synchronized boolean removeBmLayer(BmLayer bmLayer) {
        if (bmLayer != null) {
            if (this.f7687b != null) {
                AppBaseMap baseMap = this.f7687b.getBaseMap();
                if (baseMap == null) {
                    return false;
                }
                baseMap.removeBmLayer(bmLayer.m18052a());
                synchronized (this) {
                    this.f7697l.remove(bmLayer);
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean removeOverlay(Overlay overlay) {
        MapController mapController;
        AppBaseMap baseMap;
        if (overlay == null || (mapController = this.f7687b) == null || (baseMap = mapController.getBaseMap()) == null) {
            return false;
        }
        baseMap.ClearLayer(overlay.mLayerID);
        baseMap.ShowLayers(overlay.mLayerID, false);
        baseMap.UpdateLayers(overlay.mLayerID);
        baseMap.RemoveLayer(overlay.mLayerID);
        if (overlay instanceof ItemizedOverlay) {
            this.f7693h.remove(overlay);
        } else if (overlay instanceof InnerOverlay) {
            this.f7693h.remove(overlay);
            this.f7688c.m17981a(overlay);
        }
        overlay.mLayerID = 0L;
        return true;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void saveScreenToLocal(String str) {
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setBaseIndoorMap(boolean z) {
    }

    public void setBaseMap(C2925d c2925d) {
        this.f7686a = c2925d;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setGeoRound(MapStatus.GeoBound geoBound) {
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setMapCenter(GeoPoint geoPoint) {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            MapStatus mapStatus = mapController.getMapStatus();
            mapStatus.centerPtX = geoPoint.getLongitude();
            mapStatus.centerPtY = geoPoint.getLatitude();
            this.f7687b.setMapStatus(mapStatus);
        }
    }

    public void setMapRenderStableListener(InterfaceC3000ac interfaceC3000ac) {
        this.f7694i = interfaceC3000ac;
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setMapStatus(MapStatus mapStatus) {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            mapController.setMapStatus(mapStatus);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setMapTo2D(boolean z) {
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setOverlooking(int i) {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            MapStatus mapStatus = mapController.getMapStatus();
            mapStatus.overlooking = i;
            this.f7687b.setMapStatus(mapStatus);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setRotation(int i) {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            MapStatus mapStatus = mapController.getMapStatus();
            mapStatus.rotation = i;
            this.f7687b.setMapStatus(mapStatus);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setSatellite(boolean z) {
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setStreetRoad(boolean z) {
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setTraffic(boolean z) {
        AppBaseMap baseMap;
        MapController mapController = this.f7687b;
        if (mapController == null || (baseMap = mapController.getBaseMap()) == null) {
            return;
        }
        baseMap.ShowTrafficMap(z);
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setWinRound(MapStatus.WinRound winRound) {
        MapController mapController = this.f7687b;
        if (mapController != null) {
            MapStatus mapStatus = mapController.getMapStatus();
            mapStatus.winRound = winRound;
            this.f7687b.setMapStatus(mapStatus);
        }
    }

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public void setZoomLevel(float f) {
        if (this.f7687b == null) {
            return;
        }
        int i = getController().getFocusedBaseIndoorMapInfo() != null ? 22 : 21;
        if (f < 4.0f) {
            f = 4.0f;
        } else if (f > i) {
            f = 21.0f;
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

    @Override // com.baidu.platform.comapi.map.MapViewInterface
    public boolean switchOverlay(Overlay overlay, Overlay overlay2) {
        MapController mapController;
        AppBaseMap baseMap;
        if (overlay == null || overlay2 == null || (mapController = this.f7687b) == null || (baseMap = mapController.getBaseMap()) == null) {
            return false;
        }
        return baseMap.SwitchLayer(overlay.mLayerID, overlay2.mLayerID);
    }

    public void unListenMapRenderMessage() {
        this.f7698m = null;
    }
}
