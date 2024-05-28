package com.baidu.mapsdkplatform.comapi.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.map.EncodePointType;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapLayer;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.mapsdkplatform.comjni.p146a.p147a.InterfaceC2960a;
import com.baidu.platform.comapi.C2981b;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.C3040d;
import com.baidu.platform.comapi.map.InterfaceC3010al;
import com.baidu.platform.comapi.map.LocationOverlay;
import com.baidu.platform.comapi.map.MapController;
import com.baidu.platform.comapi.map.MapSurfaceView;
import com.baidu.platform.comapi.map.MapTextureView;
import com.baidu.platform.comapi.map.MapViewInterface;
import com.baidu.platform.comapi.map.OverlayLocationData;
import com.baidu.platform.comapi.util.SysOSUtil;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
@SuppressLint({"NewApi"})
/* renamed from: com.baidu.mapsdkplatform.comapi.map.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2925d implements InterfaceC2960a {

    /* renamed from: D */
    private static int f7248D = 0;

    /* renamed from: E */
    private static int f7249E = 0;

    /* renamed from: d */
    public static float f7250d = 1096.0f;

    /* renamed from: j */
    static long f7251j = 0;

    /* renamed from: l */
    private static final String f7252l = "d";

    /* renamed from: A */
    private C2919aa f7253A;

    /* renamed from: B */
    private C2936l f7254B;

    /* renamed from: C */
    private InterfaceC2937m f7255C;

    /* renamed from: F */
    private int f7256F;

    /* renamed from: G */
    private int f7257G;

    /* renamed from: R */
    private LocationOverlay f7268R;

    /* renamed from: S */
    private C3040d f7269S;

    /* renamed from: i */
    AppBaseMap f7278i;

    /* renamed from: m */
    private boolean f7280m;

    /* renamed from: n */
    private boolean f7281n;

    /* renamed from: v */
    private C2921ac f7289v;

    /* renamed from: w */
    private InterfaceC2920ab f7290w;

    /* renamed from: x */
    private Context f7291x;

    /* renamed from: y */
    private List<AbstractC2924c> f7292y;

    /* renamed from: z */
    private HashMap<MapLayer, AbstractC2924c> f7293z;

    /* renamed from: a */
    public float f7271a = 21.0f;

    /* renamed from: b */
    public float f7272b = 4.0f;

    /* renamed from: c */
    public float f7273c = 21.0f;

    /* renamed from: o */
    private boolean f7282o = true;

    /* renamed from: p */
    private boolean f7283p = false;

    /* renamed from: q */
    private boolean f7284q = false;

    /* renamed from: r */
    private boolean f7285r = false;

    /* renamed from: s */
    private boolean f7286s = true;

    /* renamed from: e */
    boolean f7274e = true;

    /* renamed from: f */
    boolean f7275f = true;

    /* renamed from: g */
    boolean f7276g = false;

    /* renamed from: t */
    private boolean f7287t = true;

    /* renamed from: u */
    private boolean f7288u = false;

    /* renamed from: H */
    private boolean f7258H = false;

    /* renamed from: I */
    private boolean f7259I = false;

    /* renamed from: J */
    private long f7260J = 0;

    /* renamed from: K */
    private long f7261K = 0;

    /* renamed from: L */
    private boolean f7262L = false;

    /* renamed from: M */
    private Queue<C2926a> f7263M = new LinkedList();

    /* renamed from: k */
    public MapStatusUpdate f7279k = null;

    /* renamed from: N */
    private boolean f7264N = false;

    /* renamed from: O */
    private boolean f7265O = false;

    /* renamed from: P */
    private boolean f7266P = false;

    /* renamed from: T */
    private boolean f7270T = false;

    /* renamed from: h */
    public List<InterfaceC3010al> f7277h = new CopyOnWriteArrayList();

    /* renamed from: Q */
    private MapController f7267Q = new MapController();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.map.d$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2926a {

        /* renamed from: a */
        public Bundle f7294a;

        public C2926a(Bundle bundle) {
            this.f7294a = bundle;
        }
    }

    public C2925d(Context context, MapSurfaceView mapSurfaceView, C2946v c2946v, String str, int i) {
        this.f7291x = context;
        this.f7267Q.initAppBaseMap();
        m18336a(this.f7267Q);
        mapSurfaceView.setMapController(this.f7267Q);
        this.f7278i = this.f7267Q.getBaseMap();
        m18331a("com.baidu.platform.comapi.wnplatform.walkmap.WNaviBaiduMap", "setId", this.f7267Q.getMapId());
        m18361V();
        m18341a(c2946v);
        this.f7267Q.getBaseMap().SetSDKLayerCallback(this);
        this.f7267Q.onResume();
    }

    public C2925d(Context context, MapTextureView mapTextureView, C2946v c2946v, String str, int i) {
        this.f7291x = context;
        this.f7267Q.initAppBaseMap();
        m18336a(this.f7267Q);
        mapTextureView.attachBaseMapController(this.f7267Q);
        this.f7278i = this.f7267Q.getBaseMap();
        m18361V();
        this.f7278i = this.f7267Q.getBaseMap();
        m18341a(c2946v);
        this.f7267Q.getBaseMap().SetSDKLayerCallback(this);
        this.f7267Q.onResume();
    }

    /* renamed from: T */
    private void m18363T() {
        try {
            f7248D = (int) (SysOSUtil.getInstance().getDensity() * 40.0f);
            f7249E = (int) (SysOSUtil.getInstance().getDensity() * 40.0f);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("x", f7248D);
            jSONObject2.put("y", f7248D);
            jSONObject2.put("hidetime", 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put("dataset", jSONArray);
            if (this.f7269S != null) {
                this.f7269S.setData(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                this.f7269S.UpdateOverlay();
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: U */
    private void m18362U() {
        if (!this.f7284q && !this.f7281n && !this.f7280m && !this.f7285r) {
            this.f7271a = this.f7273c;
            MapController mapController = this.f7267Q;
            if (mapController != null) {
                mapController.mMaxZoomLevel = this.f7271a;
                return;
            }
            return;
        }
        if (this.f7271a > 20.0f) {
            this.f7271a = 20.0f;
            MapController mapController2 = this.f7267Q;
            if (mapController2 != null) {
                mapController2.mMaxZoomLevel = 20.0f;
            }
        }
        if (m18378E().f7369a > 20.0f) {
            C2948x m18378E = m18378E();
            m18378E.f7369a = 20.0f;
            m18340a(m18378E);
        }
    }

    /* renamed from: V */
    private void m18361V() {
        this.f7292y = new CopyOnWriteArrayList();
        this.f7293z = new HashMap<>();
        this.f7253A = new C2919aa();
        m18343a(this.f7253A);
        this.f7293z.put(MapLayer.MAP_LAYER_OVERLAY, this.f7253A);
        m18286o(false);
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap != null) {
            appBaseMap.setDEMEnable(false);
        }
    }

    /* renamed from: W */
    private void m18360W() {
        MapController mapController = this.f7267Q;
        if (mapController == null || mapController.mIsMoving) {
            return;
        }
        MapController mapController2 = this.f7267Q;
        mapController2.mIsMoving = true;
        mapController2.mIsAnimating = false;
        if (this.f7277h != null) {
            C2948x m18378E = m18378E();
            for (int i = 0; i < this.f7277h.size(); i++) {
                InterfaceC3010al interfaceC3010al = this.f7277h.get(i);
                if (interfaceC3010al != null) {
                    interfaceC3010al.mo17968a(m18378E);
                }
            }
        }
    }

    /* renamed from: a */
    private long m18349a(MapLayer mapLayer) {
        AppBaseMap appBaseMap;
        String str;
        if (this.f7278i == null) {
            return -1L;
        }
        switch (C2927e.f7295a[mapLayer.ordinal()]) {
            case 1:
                LocationOverlay locationOverlay = this.f7268R;
                if (locationOverlay != null) {
                    return locationOverlay.mLayerID;
                }
                return -1L;
            case 2:
                C2919aa c2919aa = this.f7253A;
                if (c2919aa != null) {
                    return c2919aa.f7244a;
                }
                return -1L;
            case 3:
                appBaseMap = this.f7278i;
                str = "poiindoormarklayer";
                break;
            case 4:
                appBaseMap = this.f7278i;
                str = "basepoi";
                break;
            default:
                return -1L;
        }
        return appBaseMap.getLayerIDByTag(str);
    }

    /* renamed from: a */
    private void m18343a(AbstractC2924c abstractC2924c) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        abstractC2924c.f7244a = appBaseMap.AddLayer(abstractC2924c.f7246c, abstractC2924c.f7247d, abstractC2924c.f7245b);
        synchronized (this.f7292y) {
            this.f7292y.add(abstractC2924c);
        }
    }

    /* renamed from: a */
    private void m18341a(C2946v c2946v) {
        if (c2946v == null) {
            c2946v = new C2946v();
        }
        C2948x c2948x = c2946v.f7357a;
        this.f7286s = c2946v.f7362f;
        this.f7287t = c2946v.f7360d;
        this.f7274e = c2946v.f7361e;
        this.f7275f = c2946v.f7363g;
        m18385A(this.f7286s);
        m18264z(this.f7287t);
        m18278s(this.f7274e);
        m18276t(this.f7275f);
        this.f7278i.SetMapStatus(c2948x.m18190a(this));
        this.f7278i.SetMapControlMode(EnumC2945u.DEFAULT.ordinal());
        this.f7282o = c2946v.f7358b;
        if (c2946v.f7358b) {
            if (this.f7269S == null) {
                this.f7269S = new C3040d(this.f7278i);
                MapViewInterface mapView = this.f7267Q.getMapView();
                if (mapView != null) {
                    mapView.addOverlay(this.f7269S);
                    m18363T();
                }
            }
            this.f7278i.ShowLayers(this.f7269S.mLayerID, true);
            this.f7278i.ResetImageRes();
        }
        int i = c2946v.f7359c;
        if (i == 2) {
            m18329a(true);
        }
        if (i == 3) {
            if (m18382C()) {
                m18381C(false);
            }
            if (m18380D()) {
                m18379D(false);
            }
            m18296j(false);
            m18286o(false);
        }
    }

    /* renamed from: a */
    private void m18336a(MapController mapController) {
        if (!C2981b.m18073a()) {
            synchronized (C2981b.class) {
            }
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("level", 12.0d);
        bundle.putDouble("centerptx", 1.295815798E7d);
        bundle.putDouble("centerpty", 4825999.74d);
        bundle.putDouble("centerptz", 0.0d);
        bundle.putInt("left", 0);
        bundle.putInt("top", 0);
        int screenHeight = SysOSUtil.getInstance().getScreenHeight();
        bundle.putInt("right", SysOSUtil.getInstance().getScreenWidth());
        bundle.putInt("bottom", screenHeight);
        bundle.putString("modulePath", SysOSUtil.getInstance().getOutputDirPath());
        bundle.putString("appSdcardPath", SysOSUtil.getInstance().getExternalFilesDir());
        bundle.putString("appCachePath", SysOSUtil.getInstance().getOutputCache());
        bundle.putString("appSecondCachePath", SysOSUtil.getInstance().getOutputCache());
        bundle.putInt("mapTmpMax", EnvironmentUtilities.getMapTmpStgMax());
        bundle.putInt("domTmpMax", EnvironmentUtilities.getDomTmpStgMax());
        bundle.putInt("itsTmpMax", EnvironmentUtilities.getItsTmpStgMax());
        bundle.putInt("ssgTmpMax", EnvironmentUtilities.getSsgTmpStgMax());
        mapController.initMapResources(bundle);
    }

    /* renamed from: a */
    private void m18331a(String str, String str2, long j) {
        try {
            Class<?> cls = Class.forName(str);
            cls.getMethod(str2, Long.TYPE).invoke(cls.newInstance(), Long.valueOf(j));
        } catch (Exception unused) {
        }
    }

    /* renamed from: i */
    private boolean m18300i(Bundle bundle) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return false;
        }
        return appBaseMap.addSDKTileData(bundle);
    }

    /* renamed from: j */
    private boolean m18297j(Bundle bundle) {
        AppBaseMap appBaseMap;
        if (bundle == null || (appBaseMap = this.f7278i) == null) {
            return false;
        }
        boolean updateSDKTile = appBaseMap.updateSDKTile(bundle);
        if (updateSDKTile) {
            m18299i(updateSDKTile);
            this.f7278i.UpdateLayers(this.f7289v.f7244a);
        }
        return updateSDKTile;
    }

    /* renamed from: A */
    public void m18385A(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController == null) {
            return;
        }
        mapController.setOverlookGestureEnable(z);
        this.f7286s = z;
    }

    /* renamed from: A */
    public boolean m18386A() {
        return this.f7286s;
    }

    /* renamed from: B */
    void m18384B() {
        MapController mapController = this.f7267Q;
        if (mapController == null || mapController.mIsMoving || this.f7267Q.mIsAnimating) {
            return;
        }
        this.f7267Q.mIsAnimating = true;
        if (this.f7277h == null) {
            return;
        }
        C2948x m18378E = m18378E();
        for (int i = 0; i < this.f7277h.size(); i++) {
            InterfaceC3010al interfaceC3010al = this.f7277h.get(i);
            if (interfaceC3010al != null) {
                interfaceC3010al.mo17968a(m18378E);
            }
        }
    }

    /* renamed from: B */
    public void m18383B(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController != null) {
            mapController.isSetMapStatusByUsr = z;
        }
    }

    /* renamed from: C */
    public void m18381C(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap != null) {
            appBaseMap.ShowLayers(appBaseMap.getLayerIDByTag("basepoi"), z);
        }
    }

    /* renamed from: C */
    public boolean m18382C() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap != null) {
            return appBaseMap.LayersIsShow(appBaseMap.getLayerIDByTag("basepoi"));
        }
        return false;
    }

    /* renamed from: D */
    public void m18379D(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap != null) {
            appBaseMap.ShowLayers(appBaseMap.getLayerIDByTag("poiindoormarklayer"), z);
        }
    }

    /* renamed from: D */
    public boolean m18380D() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap != null) {
            return appBaseMap.LayersIsShow(appBaseMap.getLayerIDByTag("poiindoormarklayer"));
        }
        return false;
    }

    /* renamed from: E */
    public C2948x m18378E() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return null;
        }
        Bundle GetMapStatus = appBaseMap.GetMapStatus();
        C2948x c2948x = new C2948x();
        c2948x.m18191a(GetMapStatus);
        return c2948x;
    }

    /* renamed from: F */
    public LatLngBounds m18377F() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return null;
        }
        Bundle mapStatusLimits = appBaseMap.getMapStatusLimits();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        int i = mapStatusLimits.getInt("maxCoorx");
        int i2 = mapStatusLimits.getInt("minCoorx");
        builder.include(CoordUtil.mc2ll(new GeoPoint(mapStatusLimits.getInt("minCoory"), i))).include(CoordUtil.mc2ll(new GeoPoint(mapStatusLimits.getInt("maxCoory"), i2)));
        return builder.build();
    }

    /* renamed from: G */
    public MapStatusUpdate m18376G() {
        return this.f7279k;
    }

    /* renamed from: H */
    public int m18375H() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap != null) {
            appBaseMap.getFontSizeLevel();
            return 1;
        }
        return 1;
    }

    /* renamed from: I */
    public int m18374I() {
        return this.f7256F;
    }

    /* renamed from: J */
    public int m18373J() {
        return this.f7257G;
    }

    /* renamed from: K */
    public C2948x m18372K() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return null;
        }
        Bundle GetMapStatus = appBaseMap.GetMapStatus(false);
        C2948x c2948x = new C2948x();
        c2948x.m18191a(GetMapStatus);
        return c2948x;
    }

    /* renamed from: L */
    public double m18371L() {
        return m18378E().f7381m;
    }

    /* renamed from: M */
    public void m18370M() {
    }

    /* renamed from: N */
    public float[] m18369N() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return null;
        }
        return appBaseMap.getProjectionMatrix();
    }

    /* renamed from: O */
    public float[] m18368O() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return null;
        }
        return appBaseMap.getViewMatrix();
    }

    /* renamed from: P */
    public String m18367P() {
        return this.f7270T ? "" : "GS(2022)460号";
    }

    /* renamed from: Q */
    public String m18366Q() {
        return this.f7270T ? "" : "甲测资字11111342";
    }

    /* renamed from: R */
    public String m18365R() {
        return this.f7270T ? "" : "长地万方\nMapbox\nMapKin\n樂客LocalKing PalmCit\nESO DigitalGlobal spaceview\nOSRM Copyright ©2017, Project OSRMcontributors, all rights reserved\nHERE© 2019 HERE, all rights reserved\nOpenStreetMap© OpenStreetMapContributor;(OSMF)";
    }

    /* renamed from: S */
    public int m18364S() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return 0;
        }
        return appBaseMap.getMapLanguage();
    }

    /* renamed from: a */
    public float m18355a(int i, int i2, int i3, int i4, int i5, int i6) {
        if (this.f7267Q.mIsMapLoadFinish) {
            if (this.f7278i == null) {
                return 0.0f;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("left", i);
            bundle.putInt("right", i3);
            bundle.putInt("bottom", i4);
            bundle.putInt("top", i2);
            bundle.putInt("hasHW", 1);
            bundle.putInt("width", i5);
            bundle.putInt("height", i6);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("left", 0);
            bundle2.putInt("bottom", i6);
            bundle2.putInt("right", i5);
            bundle2.putInt("top", 0);
            return this.f7278i.GetFZoomToBoundF(bundle, bundle2);
        }
        return 12.0f;
    }

    @Override // com.baidu.mapsdkplatform.comjni.p146a.p147a.InterfaceC2960a, com.baidu.platform.comjni.map.basemap.InterfaceC3098a
    /* renamed from: a */
    public int mo17646a(Bundle bundle, long j, int i) {
        C2936l c2936l = this.f7254B;
        if (c2936l != null && j == c2936l.f7244a && this.f7255C != null) {
            int i2 = bundle.getInt("zoom");
            bundle.putBundle("param", this.f7255C.mo18227a(bundle.getInt("index"), i2));
            return this.f7254B.f7398e;
        }
        C2921ac c2921ac = this.f7289v;
        if (c2921ac == null || j != c2921ac.f7244a) {
            return 0;
        }
        bundle.putBundle("param", this.f7290w.mo18398a(bundle.getInt("x"), bundle.getInt("y"), bundle.getInt("zoom"), this.f7291x));
        return this.f7289v.f7398e;
    }

    /* renamed from: a */
    public Point m18338a(GeoPoint geoPoint) {
        com.baidu.platform.comapi.basestruct.Point pixels = this.f7267Q.getMapView().getProjection().toPixels(geoPoint, null);
        return pixels != null ? new Point(pixels.getIntX(), pixels.getIntY()) : new Point();
    }

    /* renamed from: a */
    public Point m18337a(GeoPoint geoPoint, int i) {
        com.baidu.platform.comapi.basestruct.Point pixels = this.f7267Q.getMapView().getProjection().toPixels(geoPoint, i, null);
        return pixels != null ? new Point(pixels.getIntX(), pixels.getIntY()) : new Point();
    }

    /* renamed from: a */
    public AppBaseMap m18359a() {
        return this.f7278i;
    }

    /* renamed from: a */
    public ArrayList<LatLng> m18334a(String str) {
        return new C2950y().m18189a(str);
    }

    /* renamed from: a */
    public void m18358a(float f, float f2) {
        this.f7271a = f;
        this.f7273c = f;
        this.f7272b = f2;
        MapController mapController = this.f7267Q;
        if (mapController != null) {
            mapController.setMaxAndMinZoomLevel(f, f2);
        }
        if (this.f7278i != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("maxLevel", (int) f);
            bundle.putInt("minLevel", (int) f2);
            this.f7278i.setMaxAndMinZoomLevel(bundle);
        }
    }

    /* renamed from: a */
    public void m18357a(int i) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        appBaseMap.CleanCache(i);
    }

    /* renamed from: a */
    public void m18356a(int i, int i2) {
        this.f7256F = i;
        this.f7257G = i2;
    }

    /* renamed from: a */
    public void m18354a(long j, long j2, long j3, long j4, boolean z) {
        if (this.f7278i == null) {
        }
    }

    /* renamed from: a */
    public void m18353a(Bitmap bitmap) {
        Bundle bundle;
        if (this.f7278i == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("type", 0);
            jSONObject2.put("x", f7248D);
            jSONObject2.put("y", f7249E);
            jSONObject2.put("hidetime", 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put("dataset", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (bitmap == null) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle();
            Bundle bundle3 = new Bundle();
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
            bitmap.copyPixelsToBuffer(allocate);
            bundle3.putByteArray("imgData", allocate.array());
            bundle3.putString("imgKey", bitmap.hashCode() + "_" + System.currentTimeMillis());
            bundle3.putInt("imgH", bitmap.getHeight());
            bundle3.putInt("imgW", bitmap.getWidth());
            bundle3.putInt("hasIcon", 1);
            bundle2.putBundle("iconData", bundle3);
            bundle = bundle2;
        }
        if (this.f7269S != null) {
            boolean z = jSONObject instanceof JSONObject;
            if (!TextUtils.isEmpty(!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject))) {
                this.f7269S.setData(!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            }
            if (bundle != null) {
                this.f7269S.setParam(bundle);
            }
            this.f7269S.UpdateOverlay();
        }
    }

    /* renamed from: a */
    public void m18350a(Bundle bundle, String str) {
        ArrayList<LatLng> m18334a;
        if (bundle == null || str == null || str.length() <= 0 || (m18334a = m18334a(str)) == null) {
            return;
        }
        int size = m18334a.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            GeoPoint ll2mc = CoordUtil.ll2mc(m18334a.get(i));
            dArr[i] = ll2mc.getLongitudeE6();
            dArr2[i] = ll2mc.getLatitudeE6();
        }
        bundle.putDoubleArray("x_array", dArr);
        bundle.putDoubleArray("y_array", dArr2);
        GeoPoint ll2mc2 = CoordUtil.ll2mc(m18334a.get(0));
        bundle.putDouble("location_x", ll2mc2.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc2.getLatitudeE6());
        if (bundle.getInt("has_dotted_stroke") == 1) {
            bundle.putDouble("dotted_stroke_location_x", ll2mc2.getLongitudeE6());
            bundle.putDouble("dotted_stroke_location_y", ll2mc2.getLatitudeE6());
        }
    }

    /* renamed from: a */
    public void m18348a(MapLayer mapLayer, MapLayer mapLayer2) {
        if (this.f7278i == null) {
            return;
        }
        long m18349a = m18349a(mapLayer);
        long m18349a2 = m18349a(mapLayer2);
        if (m18349a == -1 || m18349a2 == -1) {
            return;
        }
        this.f7278i.SwitchLayer(m18349a, m18349a2);
    }

    /* renamed from: a */
    public void m18347a(MapLayer mapLayer, boolean z) {
        if (this.f7278i == null) {
            return;
        }
        long m18349a = m18349a(mapLayer);
        if (m18349a == -1) {
            return;
        }
        this.f7278i.SetLayersClickable(m18349a, z);
    }

    /* renamed from: a */
    public void m18346a(MapStatusUpdate mapStatusUpdate) {
        this.f7279k = mapStatusUpdate;
    }

    /* renamed from: a */
    public void m18345a(LatLngBounds latLngBounds) {
        if (latLngBounds == null || this.f7278i == null) {
            return;
        }
        LatLng latLng = latLngBounds.northeast;
        LatLng latLng2 = latLngBounds.southwest;
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        GeoPoint ll2mc2 = CoordUtil.ll2mc(latLng2);
        int longitudeE6 = (int) ll2mc2.getLongitudeE6();
        int latitudeE6 = (int) ll2mc.getLatitudeE6();
        Bundle bundle = new Bundle();
        bundle.putInt("maxCoorx", (int) ll2mc.getLongitudeE6());
        bundle.putInt("minCoory", (int) ll2mc2.getLatitudeE6());
        bundle.putInt("minCoorx", longitudeE6);
        bundle.putInt("maxCoory", latitudeE6);
        this.f7278i.setMapStatusLimits(bundle);
    }

    /* renamed from: a */
    public void m18344a(InterfaceC2920ab interfaceC2920ab) {
        this.f7290w = interfaceC2920ab;
    }

    /* renamed from: a */
    public void m18342a(InterfaceC2937m interfaceC2937m) {
        this.f7255C = interfaceC2937m;
    }

    /* renamed from: a */
    public void m18340a(C2948x c2948x) {
        if (this.f7278i == null || c2948x == null) {
            return;
        }
        Bundle m18190a = c2948x.m18190a(this);
        m18190a.putInt("animation", 0);
        m18190a.putInt("animatime", 0);
        m18360W();
        this.f7278i.SetMapStatus(m18190a);
    }

    /* renamed from: a */
    public void m18339a(C2948x c2948x, int i) {
        if (this.f7278i == null || c2948x == null) {
            return;
        }
        Bundle m18190a = c2948x.m18190a(this);
        m18190a.putInt("animation", 1);
        m18190a.putInt("animatime", i);
        if (this.f7262L) {
            this.f7263M.add(new C2926a(m18190a));
            return;
        }
        m18384B();
        this.f7278i.SetMapStatus(m18190a);
    }

    /* renamed from: a */
    public void m18335a(InterfaceC3010al interfaceC3010al) {
        if (interfaceC3010al == null || this.f7277h == null) {
            return;
        }
        this.f7267Q.registMapViewListener(interfaceC3010al);
        this.f7277h.add(interfaceC3010al);
    }

    /* renamed from: a */
    public void m18333a(String str, Bundle bundle) {
        LocationOverlay locationOverlay = this.f7268R;
        if (locationOverlay == null) {
            return;
        }
        locationOverlay.setData(str);
        this.f7268R.setParam(bundle);
        this.f7268R.UpdateOverlay();
    }

    /* renamed from: a */
    public void m18330a(List<OverlayLocationData> list) {
        LocationOverlay locationOverlay = this.f7268R;
        if (locationOverlay == null) {
            return;
        }
        locationOverlay.setLocationLayerData(list);
    }

    /* renamed from: a */
    public void m18329a(boolean z) {
        int i;
        Bundle bundle;
        if (this.f7278i == null) {
            return;
        }
        this.f7281n = z;
        m18362U();
        this.f7278i.ShowSatelliteMap(this.f7281n);
        MapController mapController = this.f7267Q;
        if (mapController != null) {
            if (z) {
                i = 2;
                bundle = new Bundle();
            } else {
                i = 1;
                bundle = new Bundle();
            }
            mapController.setMapTheme(i, bundle);
        }
    }

    /* renamed from: a */
    public void m18328a(Bundle[] bundleArr) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || bundleArr == null) {
            return;
        }
        appBaseMap.addOverlayItems(bundleArr, bundleArr.length);
    }

    @Override // com.baidu.mapsdkplatform.comjni.p146a.p147a.InterfaceC2960a, com.baidu.platform.comjni.map.basemap.InterfaceC3098a
    /* renamed from: a */
    public boolean mo17647a(long j) {
        synchronized (this.f7292y) {
            Iterator<AbstractC2924c> it = this.f7292y.iterator();
            while (it.hasNext()) {
                if (it.next().f7244a == j) {
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public boolean m18352a(Point point) {
        if (point != null && this.f7278i != null && point.x >= 0 && point.y >= 0) {
            f7248D = point.x;
            f7249E = point.y;
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("x", f7248D);
                jSONObject2.put("y", f7249E);
                jSONObject2.put("hidetime", 1000);
                jSONArray.put(jSONObject2);
                jSONObject.put("dataset", jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (this.f7269S != null) {
                boolean z = jSONObject instanceof JSONObject;
                if (!TextUtils.isEmpty(!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject))) {
                    this.f7269S.setData(!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                }
                this.f7269S.UpdateOverlay();
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean m18351a(Bundle bundle) {
        if (this.f7278i == null) {
            return false;
        }
        this.f7289v = new C2921ac();
        long AddLayer = this.f7278i.AddLayer(this.f7289v.f7246c, this.f7289v.f7247d, this.f7289v.f7245b);
        if (AddLayer != 0) {
            this.f7289v.f7244a = AddLayer;
            synchronized (this.f7292y) {
                this.f7292y.add(this.f7289v);
            }
            bundle.putLong("sdktileaddr", AddLayer);
            if (m18300i(bundle) && m18297j(bundle)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean m18332a(String str, String str2) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return false;
        }
        return appBaseMap.SwitchBaseIndoorMapFloor(str, str2);
    }

    /* renamed from: b */
    public float m18327b() {
        MapController mapController = this.f7267Q;
        return mapController != null ? mapController.mMaxZoomLevel : this.f7271a;
    }

    /* renamed from: b */
    public LatLngBounds m18324b(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        int i = bundle.getInt("type");
        String string = bundle.getString("encodedPoints");
        LatLngBounds build = new LatLngBounds.Builder().build();
        if (string == null || string.length() <= 0 || i != EnumC2933i.prism.ordinal()) {
            return build;
        }
        ArrayList<LatLng> m18334a = m18334a(string);
        if (m18334a == null) {
            return null;
        }
        int size = m18334a.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i2 = 0; i2 < size; i2++) {
            GeoPoint ll2mc = CoordUtil.ll2mc(m18334a.get(i2));
            dArr[i2] = ll2mc.getLongitudeE6();
            dArr2[i2] = ll2mc.getLatitudeE6();
        }
        Point point = new Point();
        GeoPoint ll2mc2 = CoordUtil.ll2mc(m18334a.get(0));
        Rect rect = new Rect((int) ll2mc2.getLongitudeE6(), (int) ll2mc2.getLatitudeE6(), (int) ll2mc2.getLongitudeE6(), (int) ll2mc2.getLatitudeE6());
        for (int i3 = 1; i3 < dArr.length; i3++) {
            point.x = (int) dArr[i3];
            point.y = (int) dArr2[i3];
            rect.set(Math.min(rect.left, point.x), Math.max(rect.top, point.y), Math.max(rect.right, point.x), Math.min(rect.bottom, point.y));
        }
        GeoPoint geoPoint = new GeoPoint(rect.bottom, rect.left);
        GeoPoint geoPoint2 = new GeoPoint(rect.top, rect.right);
        return new LatLngBounds.Builder().include(CoordUtil.mc2ll(geoPoint)).include(CoordUtil.mc2ll(geoPoint2)).build();
    }

    /* renamed from: b */
    public GeoPoint m18325b(int i, int i2) {
        return this.f7267Q.getMapView().getProjection().fromPixels(i, i2);
    }

    /* renamed from: b */
    public void m18326b(int i) {
        C2936l c2936l;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (c2936l = this.f7254B) == null) {
            return;
        }
        appBaseMap.setHeatMapFrameAnimationIndex(c2936l.f7244a, i);
    }

    /* renamed from: b */
    public void m18323b(String str, String str2) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        appBaseMap.initCustomStyle(str, str2);
    }

    /* renamed from: b */
    public void m18322b(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap != null) {
            appBaseMap.setDEMEnable(z);
        }
    }

    /* renamed from: b */
    public void m18321b(Bundle[] bundleArr) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        appBaseMap.removeOverlayItems(bundleArr);
    }

    /* renamed from: c */
    public void m18320c() {
        if (this.f7278i == null) {
            return;
        }
        synchronized (this.f7292y) {
            for (AbstractC2924c abstractC2924c : this.f7292y) {
                this.f7278i.ShowLayers(abstractC2924c.f7244a, false);
            }
        }
    }

    /* renamed from: c */
    public void m18319c(int i) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap != null) {
            appBaseMap.setFontSizeLevel(i);
        }
    }

    /* renamed from: c */
    public void m18318c(Bundle bundle) {
        if (this.f7278i == null) {
            return;
        }
        m18306g(bundle);
        m18315d(bundle);
        this.f7278i.addOneOverlayItem(bundle);
    }

    /* renamed from: c */
    public void m18317c(boolean z) {
        this.f7288u = z;
    }

    /* renamed from: d */
    public void m18316d() {
        if (this.f7278i == null) {
            return;
        }
        synchronized (this.f7292y) {
            for (AbstractC2924c abstractC2924c : this.f7292y) {
                if (!(abstractC2924c instanceof C2923b) && !(abstractC2924c instanceof C2936l)) {
                    this.f7278i.ShowLayers(abstractC2924c.f7244a, true);
                }
                this.f7278i.ShowLayers(abstractC2924c.f7244a, false);
            }
        }
        this.f7278i.ShowTrafficMap(false);
    }

    /* renamed from: d */
    public void m18315d(Bundle bundle) {
        if (bundle != null && bundle.containsKey("encodedPoints") && bundle.containsKey("encodePointType") && bundle.getInt("encodePointType") == EncodePointType.BUILDINGINFO.ordinal()) {
            m18350a(bundle, bundle.getString("encodedPoints"));
        }
    }

    /* renamed from: d */
    public void m18314d(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        this.f7278i.ShowLayers(appBaseMap.getLayerIDByTag("opgrid"), z);
    }

    /* renamed from: e */
    public void m18313e() {
        C2921ac c2921ac;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (c2921ac = this.f7289v) == null) {
            return;
        }
        appBaseMap.RemoveLayer(c2921ac.f7244a);
        this.f7292y.remove(this.f7289v);
    }

    /* renamed from: e */
    public void m18312e(Bundle bundle) {
        if (this.f7278i == null) {
            return;
        }
        m18306g(bundle);
        m18315d(bundle);
        this.f7278i.updateOneOverlayItem(bundle);
    }

    /* renamed from: e */
    public void m18311e(boolean z) {
        boolean z2;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        if (z) {
            if (this.f7264N) {
                return;
            }
            appBaseMap.SwitchLayer(appBaseMap.getLayerIDByTag("indoorlayer"), this.f7253A.f7244a);
            z2 = true;
        } else if (!this.f7264N) {
            return;
        } else {
            appBaseMap.SwitchLayer(this.f7253A.f7244a, this.f7278i.getLayerIDByTag("indoorlayer"));
            z2 = false;
        }
        this.f7264N = z2;
    }

    /* renamed from: f */
    public void m18309f(Bundle bundle) {
        if (this.f7278i == null) {
            return;
        }
        m18306g(bundle);
        this.f7278i.removeOneOverlayItem(bundle);
    }

    /* renamed from: f */
    public void m18308f(boolean z) {
        LocationOverlay locationOverlay;
        boolean z2;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        if (z) {
            if (this.f7265O || this.f7268R == null) {
                return;
            }
            appBaseMap.SwitchLayer(this.f7253A.f7244a, this.f7268R.mLayerID);
            z2 = true;
        } else if (!this.f7265O || (locationOverlay = this.f7268R) == null) {
            return;
        } else {
            appBaseMap.SwitchLayer(locationOverlay.mLayerID, this.f7253A.f7244a);
            z2 = false;
        }
        this.f7265O = z2;
    }

    /* renamed from: f */
    public boolean m18310f() {
        AppBaseMap appBaseMap;
        C2921ac c2921ac = this.f7289v;
        if (c2921ac == null || (appBaseMap = this.f7278i) == null) {
            return false;
        }
        return appBaseMap.cleanSDKTileDataCache(c2921ac.f7244a);
    }

    /* renamed from: g */
    public void m18306g(Bundle bundle) {
        int i;
        int i2;
        if (bundle.get("param") == null ? (i = bundle.getInt("type")) != EnumC2933i.ground.ordinal() && i < EnumC2933i.arc.ordinal() : (i2 = (bundle = (Bundle) bundle.get("param")).getInt("type")) != EnumC2933i.ground.ordinal() && i2 < EnumC2933i.arc.ordinal()) {
            EnumC2933i.popup.ordinal();
        }
        bundle.putLong("layer_addr", this.f7253A.f7244a);
    }

    /* renamed from: g */
    public boolean m18307g() {
        return this.f7280m;
    }

    /* renamed from: g */
    public boolean m18305g(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return false;
        }
        long layerIDByTag = appBaseMap.getLayerIDByTag("carnavinode");
        long layerIDByTag2 = this.f7278i.getLayerIDByTag("android_sdk");
        if (layerIDByTag == 0 || layerIDByTag2 == 0) {
            return false;
        }
        if (z) {
            if (this.f7266P) {
                return false;
            }
            boolean SwitchLayer = this.f7278i.SwitchLayer(layerIDByTag, layerIDByTag2);
            this.f7266P = true;
            return SwitchLayer;
        } else if (this.f7266P) {
            boolean SwitchLayer2 = this.f7278i.SwitchLayer(layerIDByTag2, layerIDByTag);
            this.f7266P = false;
            return SwitchLayer2;
        } else {
            return false;
        }
    }

    /* renamed from: h */
    public String m18304h() {
        if (this.f7278i == null) {
        }
        return null;
    }

    /* renamed from: h */
    public void m18303h(Bundle bundle) {
        C2936l c2936l;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (c2936l = this.f7254B) == null) {
            return;
        }
        appBaseMap.initHeatMapData(c2936l.f7244a, bundle);
    }

    /* renamed from: h */
    public void m18302h(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        if (this.f7269S == null) {
            this.f7269S = new C3040d(appBaseMap);
            MapViewInterface mapView = this.f7267Q.getMapView();
            if (mapView != null) {
                mapView.addOverlay(this.f7269S);
                m18363T();
            }
        }
        this.f7278i.ShowLayers(this.f7269S.mLayerID, z);
    }

    /* renamed from: i */
    public void m18299i(boolean z) {
        C2921ac c2921ac;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (c2921ac = this.f7289v) == null) {
            return;
        }
        appBaseMap.ShowLayers(c2921ac.f7244a, z);
    }

    /* renamed from: i */
    public boolean m18301i() {
        return this.f7285r;
    }

    /* renamed from: j */
    public void m18296j(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        appBaseMap.ShowLayers(appBaseMap.getLayerIDByTag("basemap"), z);
    }

    /* renamed from: j */
    public boolean m18298j() {
        if (this.f7278i == null) {
        }
        return false;
    }

    /* renamed from: k */
    public void m18294k(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        this.f7285r = z;
        appBaseMap.ShowHotMap(this.f7285r, 0);
    }

    /* renamed from: k */
    public boolean m18295k() {
        return this.f7281n;
    }

    /* renamed from: l */
    public void m18292l(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        this.f7280m = z;
        appBaseMap.ShowTrafficMap(this.f7280m);
    }

    /* renamed from: l */
    public boolean m18293l() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return false;
        }
        return appBaseMap.LayersIsShow(appBaseMap.getLayerIDByTag("basemap"));
    }

    /* renamed from: m */
    public void m18290m(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        appBaseMap.setDrawHouseHeightEnable(z);
    }

    /* renamed from: m */
    public boolean m18291m() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return false;
        }
        return appBaseMap.getDrawHouseHeightEnable();
    }

    /* renamed from: n */
    public void m18289n() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        appBaseMap.ClearSDKLayer(this.f7253A.f7244a);
    }

    /* renamed from: n */
    public void m18288n(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        this.f7282o = z;
        if (this.f7269S == null) {
            this.f7269S = new C3040d(appBaseMap);
            MapViewInterface mapView = this.f7267Q.getMapView();
            if (mapView != null) {
                mapView.addOverlay(this.f7269S);
                m18363T();
            }
        }
        this.f7278i.ShowLayers(this.f7269S.mLayerID, z);
    }

    /* renamed from: o */
    public void m18287o() {
        C2936l c2936l;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (c2936l = this.f7254B) == null) {
            return;
        }
        appBaseMap.startHeatMapFrameAnimation(c2936l.f7244a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (r1 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001c, code lost:
        r1.mMaxZoomLevel = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
        if (r1 != null) goto L10;
     */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m18286o(boolean r3) {
        /*
            r2 = this;
            com.baidu.platform.comjni.map.basemap.AppBaseMap r0 = r2.f7278i
            if (r0 != 0) goto L5
            return
        L5:
            if (r3 == 0) goto L12
            r0 = 1102053376(0x41b00000, float:22.0)
            r2.f7271a = r0
            r2.f7273c = r0
            com.baidu.platform.comapi.map.MapController r1 = r2.f7267Q
            if (r1 == 0) goto L1e
            goto L1c
        L12:
            r0 = 1101529088(0x41a80000, float:21.0)
            r2.f7271a = r0
            r2.f7273c = r0
            com.baidu.platform.comapi.map.MapController r1 = r2.f7267Q
            if (r1 == 0) goto L1e
        L1c:
            r1.mMaxZoomLevel = r0
        L1e:
            com.baidu.platform.comjni.map.basemap.AppBaseMap r0 = r2.f7278i
            r0.ShowBaseIndoorMap(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.map.C2925d.m18286o(boolean):void");
    }

    /* renamed from: p */
    public void m18285p() {
        C2936l c2936l;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (c2936l = this.f7254B) == null) {
            return;
        }
        appBaseMap.stopHeatMapFrameAnimation(c2936l.f7244a);
    }

    /* renamed from: p */
    public void m18284p(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        this.f7270T = z;
        appBaseMap.setCustomStyleEnable(z);
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b m18459a = C2898b.m18459a();
            m18459a.m18457a("CustomMap setMapCustomEnable enable = " + z);
        }
    }

    /* renamed from: q */
    public void m18283q() {
        C2936l c2936l;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (c2936l = this.f7254B) == null) {
            return;
        }
        appBaseMap.clearHeatMapLayerCache(c2936l.f7244a);
        this.f7278i.UpdateLayers(this.f7254B.f7244a);
    }

    /* renamed from: q */
    public void m18282q(boolean z) {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return;
        }
        this.f7283p = z;
        LocationOverlay locationOverlay = this.f7268R;
        if (locationOverlay != null) {
            appBaseMap.ShowLayers(locationOverlay.mLayerID, z);
            return;
        }
        MapViewInterface mapView = this.f7267Q.getMapView();
        if (mapView != null) {
            this.f7268R = new LocationOverlay(this.f7278i);
            mapView.addOverlay(this.f7268R);
        }
    }

    /* renamed from: r */
    public MapBaseIndoorMapInfo m18281r() {
        String GetFocusedBaseIndoorMapInfo;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (GetFocusedBaseIndoorMapInfo = appBaseMap.GetFocusedBaseIndoorMapInfo()) == null) {
            return null;
        }
        String str = "";
        String str2 = "";
        ArrayList arrayList = new ArrayList(1);
        try {
            JSONObject jSONObject = new JSONObject(GetFocusedBaseIndoorMapInfo);
            str = jSONObject.optString("focusindoorid");
            str2 = jSONObject.optString("curfloor");
            JSONArray optJSONArray = jSONObject.optJSONArray("floorlist");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.get(i).toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new MapBaseIndoorMapInfo(str, str2, arrayList);
    }

    /* renamed from: r */
    public void m18280r(boolean z) {
        if (this.f7278i == null) {
            return;
        }
        if (this.f7254B == null) {
            this.f7254B = new C2936l();
            m18343a(this.f7254B);
        }
        this.f7284q = z;
        this.f7278i.ShowLayers(this.f7254B.f7244a, z);
    }

    /* renamed from: s */
    public void m18278s(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController == null) {
            return;
        }
        mapController.setCanTouchMove(z);
        this.f7274e = z;
    }

    /* renamed from: s */
    public boolean m18279s() {
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null) {
            return false;
        }
        return appBaseMap.IsBaseIndoorMapMode();
    }

    /* renamed from: t */
    public void m18276t(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController == null) {
            return;
        }
        mapController.setEnableZoom(z);
        this.f7275f = z;
    }

    /* renamed from: t */
    public boolean m18277t() {
        return this.f7282o;
    }

    /* renamed from: u */
    public void m18275u() {
        this.f7268R.clearLocationLayerData(null);
    }

    /* renamed from: u */
    public void m18274u(boolean z) {
        this.f7267Q.setInertialAnimation(z);
    }

    /* renamed from: v */
    public void m18272v(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController == null) {
            return;
        }
        mapController.setDoubleClickZoom(z);
    }

    /* renamed from: v */
    public boolean m18273v() {
        return this.f7283p;
    }

    /* renamed from: w */
    public void m18271w() {
        C2936l c2936l;
        AppBaseMap appBaseMap = this.f7278i;
        if (appBaseMap == null || (c2936l = this.f7254B) == null) {
            return;
        }
        appBaseMap.UpdateLayers(c2936l.f7244a);
    }

    /* renamed from: w */
    public void m18270w(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController == null) {
            return;
        }
        mapController.setTwoTouchClickZoomEnabled(z);
    }

    /* renamed from: x */
    public void m18268x(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController == null) {
            return;
        }
        mapController.setEnlargeCenterWithDoubleClickEnable(z);
    }

    /* renamed from: x */
    public boolean m18269x() {
        return this.f7274e;
    }

    /* renamed from: y */
    public void m18266y(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController == null) {
            return;
        }
        mapController.setFlingEnable(z);
    }

    /* renamed from: y */
    public boolean m18267y() {
        return this.f7275f;
    }

    /* renamed from: z */
    public void m18264z(boolean z) {
        MapController mapController = this.f7267Q;
        if (mapController == null) {
            return;
        }
        mapController.set3DGestureEnable(z);
        this.f7287t = z;
    }

    /* renamed from: z */
    public boolean m18265z() {
        return this.f7287t;
    }
}
