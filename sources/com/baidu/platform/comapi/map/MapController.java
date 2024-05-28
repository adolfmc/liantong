package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.C2981b;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.Point;
import com.baidu.platform.comapi.map.p152a.C2994a;
import com.baidu.platform.comapi.map.p152a.C2995b;
import com.baidu.platform.comapi.map.p152a.C2996c;
import com.baidu.platform.comapi.map.p152a.C2997d;
import com.baidu.platform.comapi.map.p153b.C3029b;
import com.baidu.platform.comapi.map.p153b.C3035d;
import com.baidu.platform.comapi.util.AbstractHandlerC3096h;
import com.baidu.platform.comapi.util.C3087a;
import com.baidu.platform.comapi.util.C3097i;
import com.baidu.platform.comapi.util.SysOSUtil;
import com.baidu.platform.comjni.engine.MessageProxy;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapController {

    /* renamed from: A */
    private static float f7579A = 0.0f;
    public static final String ANDROID_SDK_LAYER_TAG = "android_sdk";

    /* renamed from: B */
    private static boolean f7580B = false;
    public static final String CITY_AREA_TAG = "cityarea";
    public static final String COMPASS_LAYER_TAG = "compass";
    public static final String DEFAULT_LAYER_TAG = "default";
    public static final String DYNAMIC_MAP_LAYER_TAG = "dynamicmap";
    public static final String FOOTSURFACE_LAYER_TAG = "footsurface";
    public static final String HEATMAP_LAYER_TAG = "heatmap";
    public static final String ITEM_LAYER_TAG = "item";
    public static final String ITSROUTE_LAYER_TAG = "itsroute";
    public static final String LOCAL_LIMIT_MAP_LAYER_TAG = "dynamiclimit";
    public static final String LOCATION_LAYER_TAG = "location";
    public static final int MSG_LONGLINK_CONNECT = 1;
    public static final int MSG_LONGLINK_DISCONNECT = 2;
    public static final String POISON_LAYER_TAG = "poison";
    public static final String POPUP_LAYER_TAG = "popup";

    /* renamed from: R */
    private static long f7581R = 0;
    public static final String RTPOPUP_LAYER_TAG = "rtpopup";
    public static final String RT_POPUP_LAYER_TAG = "rtpopup";
    public static final String SHARELOCATION_BUBBLE = "smshare";
    public static final String STREETPOPUP_LAYER_TAG = "streetpopup";
    public static final String STREETROUTE_LAYER_TAG = "streetroute";

    /* renamed from: W */
    private static List<AppBaseMap> f7582W = new ArrayList();
    public static boolean isCompass = false;

    /* renamed from: k */
    private static final String f7583k = "MapController";
    public static boolean mLocIconOnScreen = true;
    public static boolean m_registered_SENSOR_ORIENTATION = false;

    /* renamed from: y */
    private static boolean f7584y = true;

    /* renamed from: z */
    private static float f7585z;

    /* renamed from: O */
    private long f7598O;

    /* renamed from: X */
    private long f7605X;

    /* renamed from: Z */
    private Handler f7607Z;

    /* renamed from: i */
    SoftReference<MapViewInterface> f7619i;

    /* renamed from: j */
    NaviMapViewListener f7620j;

    /* renamed from: l */
    private C3035d f7621l;
    public boolean mHasMapObjDraging;
    public boolean mIsMapLoadFinish;

    /* renamed from: r */
    private MapFirstFrameCallback f7627r;

    /* renamed from: v */
    private Handler f7631v;

    /* renamed from: m */
    private boolean f7622m = true;

    /* renamed from: n */
    private boolean f7623n = true;

    /* renamed from: a */
    int f7608a = 0;

    /* renamed from: o */
    private int f7624o = 1;

    /* renamed from: p */
    private int f7625p = 1;

    /* renamed from: q */
    private boolean f7626q = false;

    /* renamed from: s */
    private boolean f7628s = false;

    /* renamed from: t */
    private AppBaseMap f7629t = null;

    /* renamed from: u */
    private long f7630u = 0;
    public int nearlyRadius = 20;

    /* renamed from: C */
    private boolean f7586C = false;

    /* renamed from: D */
    private boolean f7587D = false;

    /* renamed from: E */
    private boolean f7588E = false;

    /* renamed from: F */
    private boolean f7589F = false;

    /* renamed from: G */
    private C2986a f7590G = new C2986a();

    /* renamed from: H */
    private boolean f7591H = true;

    /* renamed from: I */
    private boolean f7592I = false;

    /* renamed from: J */
    private boolean f7593J = true;

    /* renamed from: K */
    private boolean f7594K = false;

    /* renamed from: L */
    private float f7595L = -1.0f;

    /* renamed from: M */
    private float f7596M = -1.0f;

    /* renamed from: N */
    private float f7597N = 0.0f;

    /* renamed from: P */
    private boolean f7599P = false;

    /* renamed from: Q */
    private boolean f7600Q = false;

    /* renamed from: S */
    private boolean f7601S = true;

    /* renamed from: T */
    private boolean f7602T = true;

    /* renamed from: U */
    private boolean f7603U = true;

    /* renamed from: V */
    private boolean f7604V = true;
    public boolean mIsInertialAnimation = true;

    /* renamed from: b */
    MapViewListener f7612b = null;

    /* renamed from: c */
    CaptureMapListener f7613c = null;

    /* renamed from: d */
    InterfaceC3069k f7614d = null;

    /* renamed from: e */
    InterfaceC3012an f7615e = null;

    /* renamed from: f */
    MapRenderModeChangeListener f7616f = null;

    /* renamed from: g */
    EngineMsgListener f7617g = null;

    /* renamed from: h */
    MapViewSurfaceListener f7618h = null;
    public float mMaxZoomLevel = 21.0f;
    public float mMinZoomLevel = 4.0f;
    public boolean mIsMoving = false;
    public boolean mIsAnimating = false;
    public boolean isSetMapStatusByUsr = false;

    /* renamed from: Y */
    private boolean f7606Y = false;

    /* renamed from: aa */
    private boolean f7609aa = false;

    /* renamed from: ab */
    private C3029b f7610ab = new C3029b(this);

    /* renamed from: ac */
    private MapControlMode f7611ac = MapControlMode.DEFAULT;
    public List<InterfaceC3010al> mListeners = new CopyOnWriteArrayList();

    /* renamed from: w */
    private int f7632w = SysOSUtil.getInstance().getScreenWidth();

    /* renamed from: x */
    private int f7633x = SysOSUtil.getInstance().getScreenHeight();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum HeatMapType {
        CITY(0),
        SCENERY(1),
        CEMETERY(2);
        

        /* renamed from: a */
        private final int f7635a;

        HeatMapType(int i) {
            this.f7635a = i;
        }

        public int getId() {
            return this.f7635a;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum MapControlMode {
        DEFAULT(1),
        INDOOR(2),
        STREET(3),
        STREET_WAITING(4);
        

        /* renamed from: a */
        private final int f7637a;

        MapControlMode(int i) {
            this.f7637a = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface MapFirstFrameCallback {
        void onFirstFrameDrawing(MapController mapController);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum MapLayerType {
        DEFAULT(1),
        SATELLITE(2),
        INDOOR(3),
        STREET(5);
        

        /* renamed from: a */
        private final int f7639a;

        MapLayerType(int i) {
            this.f7639a = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum MapSceneMode {
        DEFAULT(0),
        POI(1),
        ROUTE(2),
        INTERNAL(3),
        INDOOR(7);
        

        /* renamed from: a */
        private final int f7641a;

        MapSceneMode(int i) {
            this.f7641a = i;
        }

        public int getMode() {
            return this.f7641a;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum MapStyleMode {
        DEFAULT(1),
        SEARCH_POI(2),
        SEARCH_ROUTE(3),
        NAV_DAY(4),
        NAV_NIGHT(5),
        WALK_DAY(6),
        INTERNAL(7),
        INTERNAL_SPECIAL(8),
        FOOT_PRINT(9);
        

        /* renamed from: a */
        private final int f7643a;

        MapStyleMode(int i) {
            this.f7643a = i;
        }

        public int getMode() {
            return this.f7643a;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RecommendPoiScene {
        BASE(0),
        INTERNATIONAL(1);
        
        public int value;

        RecommendPoiScene(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RecycleMemoryLevel {
        NORMAL(0),
        FULL(1);
        

        /* renamed from: a */
        private final int f7646a;

        RecycleMemoryLevel(int i) {
            this.f7646a = i;
        }

        public int getLevel() {
            return this.f7646a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.MapController$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2986a {

        /* renamed from: a */
        public boolean f7647a = false;

        /* renamed from: b */
        float f7648b = 0.0f;

        /* renamed from: c */
        GeoPoint f7649c;

        /* renamed from: d */
        Point f7650d;

        C2986a() {
        }

        /* renamed from: a */
        public void m18008a() {
            this.f7647a = false;
            this.f7648b = 0.0f;
            this.f7649c = null;
            this.f7650d = null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.MapController$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class HandlerC2987b extends AbstractHandlerC3096h {
        HandlerC2987b() {
            super(Looper.getMainLooper());
        }

        @Override // com.baidu.platform.comapi.util.AbstractHandlerC3096h
        /* renamed from: a */
        public void mo17681a(Message message) {
            int i;
            boolean z;
            if (message.what == 4000 && MapController.this.f7613c != null) {
                MapController.this.f7613c.onGetCaptureMap(message.arg2 == 1);
            }
            if (message.what == 519 && MapController.this.f7614d != null) {
                MapController.this.f7614d.m17735a();
            }
            if (message.what == 39) {
                if (((Long) message.obj).longValue() != MapController.this.f7630u) {
                    return;
                }
                int i2 = message.arg1;
                if (i2 != 2) {
                    if (i2 == 100) {
                        if (MapController.this.f7599P) {
                            if (MapController.this.f7619i == null || MapController.this.f7619i.get() == null) {
                                return;
                            }
                            C3097i.m17678b().execute(new RunnableC3072n(this, MapController.this.getMapStatus()));
                        }
                        if (MapController.this.f7600Q) {
                            MapController.this.f7600Q = false;
                        }
                        MapController.this.f7587D = false;
                        MapController mapController = MapController.this;
                        mapController.mIsMoving = false;
                        mapController.mIsAnimating = false;
                        if (mapController.getMapViewListener() != null) {
                            MapController.this.getMapViewListener().onMapAnimationFinish();
                        }
                        if (MapController.this.isNaviMode() && MapController.this.f7620j != null) {
                            MapController.this.f7620j.onMapAnimationFinish();
                        }
                        if (MapController.this.mListeners != null) {
                            C2948x mapStatusInner = MapController.this.getMapStatusInner();
                            for (int i3 = 0; i3 < MapController.this.mListeners.size(); i3++) {
                                InterfaceC3010al interfaceC3010al = MapController.this.mListeners.get(i3);
                                if (interfaceC3010al != null) {
                                    interfaceC3010al.mo17955c(mapStatusInner);
                                }
                            }
                        }
                    } else if (i2 == 200) {
                        MapController.this.mIsMoving = false;
                    } else if (i2 != 300) {
                        if (MapController.this.f7616f != null) {
                            MapController.this.f7616f.onMapRenderModeChange(message.arg1);
                        }
                        if (MapController.this.isNaviMode() && MapController.this.f7620j != null) {
                            MapController.this.f7620j.onMapRenderModeChange(message.arg1);
                        }
                    } else if (MapController.this.f7627r != null) {
                        MapController.this.f7627r.onFirstFrameDrawing(MapController.this);
                    }
                } else if (MapController.this.mListeners == null) {
                    return;
                } else {
                    for (int i4 = 0; i4 < MapController.this.mListeners.size(); i4++) {
                        InterfaceC3010al interfaceC3010al2 = MapController.this.mListeners.get(i4);
                        if (interfaceC3010al2 != null) {
                            interfaceC3010al2.mo17957c();
                        }
                    }
                    MapController mapController2 = MapController.this;
                    mapController2.mIsMoving = false;
                    mapController2.mIsAnimating = false;
                }
                if (!MapController.this.mIsMapLoadFinish && MapController.this.f7633x > 0 && MapController.this.f7632w > 0 && MapController.this.getMapView() != null && MapController.this.getMapView().getProjection() != null && MapController.this.getMapView().getProjection().fromPixels(0, 0) != null) {
                    MapController.this.mIsMapLoadFinish = true;
                    C3097i.m17679a(new RunnableC3073o(this), 0L);
                }
                if (MapController.this.mListeners != null) {
                    for (int i5 = 0; i5 < MapController.this.mListeners.size(); i5++) {
                        InterfaceC3010al interfaceC3010al3 = MapController.this.mListeners.get(i5);
                        if (interfaceC3010al3 != null) {
                            interfaceC3010al3.mo17973a();
                        }
                    }
                }
            } else if (message.what == 41) {
                if (((Long) message.obj).longValue() != MapController.this.f7630u || MapController.this.mListeners == null) {
                    return;
                }
                if (MapController.this.mIsMoving || MapController.this.mIsAnimating) {
                    C2948x mapStatusInner2 = MapController.this.getMapStatusInner();
                    for (int i6 = 0; i6 < MapController.this.mListeners.size(); i6++) {
                        InterfaceC3010al interfaceC3010al4 = MapController.this.mListeners.get(i6);
                        if (interfaceC3010al4 != null) {
                            interfaceC3010al4.mo17960b(mapStatusInner2);
                        }
                    }
                }
                if (MapController.this.isSetMapStatusByUsr) {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    if (MapController.this.f7607Z != null) {
                        MapController.this.f7607Z.sendMessage(obtain);
                    }
                    MapController.this.isSetMapStatusByUsr = false;
                }
            } else if (message.what == 2082) {
                int i7 = message.arg1;
                if (message.arg1 == 1003) {
                    z = true;
                    i = 0;
                } else {
                    i = i7;
                    z = false;
                }
                if (OpenLogUtil.isMapLogEnable()) {
                    C2898b.m18459a().m18457a("onMapRenderValidFrame isValid = " + z + "; errorCode = " + i);
                }
                if (MapController.this.mListeners != null) {
                    for (int i8 = 0; i8 < MapController.this.mListeners.size(); i8++) {
                        InterfaceC3010al interfaceC3010al5 = MapController.this.mListeners.get(i8);
                        if (interfaceC3010al5 != null) {
                            interfaceC3010al5.mo17963a(z, i);
                        }
                    }
                }
            }
            if (message.what == 512) {
                int i9 = message.arg1;
                if (MapController.this.getMapViewListener() != null) {
                    MapController.this.getMapViewListener().onClickedPopup(i9);
                }
            }
            if (message.what == 50) {
                if (OpenLogUtil.isMapLogEnable()) {
                    C2898b.m18459a().m18457a("EngineMeassage IndoorMap msg.what = " + message.what + "; msg.arg1 = " + message.arg1);
                }
                if (MapController.this.f7617g != null) {
                    if (message.arg1 == 1) {
                        MapController.this.f7617g.onEnterIndoorMapMode(MapController.this.getFocusedBaseIndoorMapInfo());
                    } else if (message.arg1 == 0) {
                        MapController.this.f7617g.onExitIndoorMapMode();
                    }
                }
                if (MapController.this.mListeners == null) {
                    return;
                }
                IndoorMapInfo focusedBaseIndoorMapInfo = MapController.this.getFocusedBaseIndoorMapInfo();
                for (int i10 = 0; i10 < MapController.this.mListeners.size(); i10++) {
                    InterfaceC3010al interfaceC3010al6 = MapController.this.mListeners.get(i10);
                    if (interfaceC3010al6 != null) {
                        if (message.arg1 != 0) {
                            if (message.arg1 == 1) {
                                if (MapController.this.getMapStatus().level >= 18.0f && focusedBaseIndoorMapInfo != null) {
                                    interfaceC3010al6.mo17964a(true);
                                    MapController.this.mMaxZoomLevel = 22.0f;
                                }
                            }
                        }
                        interfaceC3010al6.mo17964a(false);
                        MapController.this.mMaxZoomLevel = 21.0f;
                    }
                }
            }
            if (message.what == 51) {
                MapController.this.setNetStatus(message.arg1);
            }
            if (message.what != 65301 || MapController.this.f7617g == null) {
                return;
            }
            if (message.arg1 == 1) {
                MapController.this.getMapBarData();
            } else if (message.arg1 == 0) {
                C3087a.m17703a().m17700a(new C2993a());
            }
        }
    }

    public MapController() {
        this.f7631v = null;
        this.f7607Z = null;
        this.f7631v = new HandlerC2987b();
        this.f7607Z = new HandlerC3071m(this);
        m18019d();
    }

    public static native int CleanAfterDBClick(long j, float f, float f2);

    public static int GetAdaptKeyCode(int i) {
        switch (i) {
            case 19:
                return 17;
            case 20:
                return 19;
            case 21:
                return 16;
            case 22:
                return 18;
            default:
                return 0;
        }
    }

    public static native int MapProc(long j, int i, int i2, int i3, int i4, int i5, double d, double d2, double d3, double d4);

    /* renamed from: a */
    private MapStatus m18028a(boolean z) {
        Bundle GetMapStatus;
        if (m18027b() && (GetMapStatus = this.f7629t.GetMapStatus(z)) != null) {
            MapStatus mapStatus = new MapStatus();
            mapStatus.level = (float) GetMapStatus.getDouble("level");
            mapStatus.rotation = (int) GetMapStatus.getDouble("rotation");
            mapStatus.overlooking = (int) GetMapStatus.getDouble("overlooking");
            mapStatus.centerPtX = GetMapStatus.getDouble("centerptx");
            mapStatus.centerPtY = GetMapStatus.getDouble("centerpty");
            mapStatus.centerPtZ = GetMapStatus.getDouble("centerptz");
            mapStatus.winRound.left = GetMapStatus.getInt("left");
            mapStatus.winRound.right = GetMapStatus.getInt("right");
            mapStatus.winRound.top = GetMapStatus.getInt("top");
            mapStatus.winRound.bottom = GetMapStatus.getInt("bottom");
            mapStatus.geoRound.left = GetMapStatus.getLong("gleft");
            mapStatus.geoRound.right = GetMapStatus.getLong("gright");
            mapStatus.geoRound.top = GetMapStatus.getLong("gtop");
            mapStatus.geoRound.bottom = GetMapStatus.getLong("gbottom");
            mapStatus.xOffset = GetMapStatus.getFloat("xoffset");
            mapStatus.yOffset = GetMapStatus.getFloat("yoffset");
            mapStatus.bfpp = GetMapStatus.getInt("bfpp") == 1;
            mapStatus.panoId = GetMapStatus.getString("panoid");
            mapStatus.streetIndicateAngle = GetMapStatus.getFloat("siangle");
            mapStatus.isBirdEye = GetMapStatus.getInt("isbirdeye") == 1;
            mapStatus.streetExt = GetMapStatus.getInt("ssext");
            mapStatus.roadOffsetX = GetMapStatus.getFloat("roadOffsetX");
            mapStatus.roadOffsetY = GetMapStatus.getFloat("roadOffsetY");
            mapStatus.bOverlookSpringback = GetMapStatus.getInt("boverlookback") == 1;
            mapStatus.minOverlooking = (int) GetMapStatus.getFloat("minoverlook");
            mapStatus.xScreenOffset = GetMapStatus.getFloat("xScreenOffset");
            mapStatus.yScreenOffset = GetMapStatus.getFloat("yScreenOffset");
            if (mapStatus.geoRound.left <= -20037508) {
                mapStatus.geoRound.left = -20037508L;
            }
            if (mapStatus.geoRound.right >= 20037508) {
                mapStatus.geoRound.right = 20037508L;
            }
            if (mapStatus.geoRound.top >= 20037508) {
                mapStatus.geoRound.top = 20037508L;
            }
            if (mapStatus.geoRound.bottom <= -20037508) {
                mapStatus.geoRound.bottom = -20037508L;
            }
            return mapStatus;
        }
        return new MapStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18034a() {
        this.mIsMoving = false;
        if (!this.mIsAnimating) {
            if (this.mListeners == null) {
                return;
            }
            C2948x mapStatusInner = getMapStatusInner();
            for (int i = 0; i < this.mListeners.size(); i++) {
                InterfaceC3010al interfaceC3010al = this.mListeners.get(i);
                if (interfaceC3010al != null) {
                    interfaceC3010al.mo17955c(mapStatusInner);
                }
            }
        }
        this.isSetMapStatusByUsr = false;
    }

    /* renamed from: a */
    private void m18031a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        f7585z = x;
        f7579A = y;
        MapMsgProc(4, 0, x | (y << 16));
        f7580B = true;
        this.f7605X = motionEvent.getDownTime();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0064, code lost:
        r7 = (org.json.JSONObject) new org.json.JSONObject(r8).getJSONArray("dataset").get(0);
        r8 = r7.getInt("itemindex");
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007b, code lost:
        r2 = r7.optInt("clickindex", -1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0081, code lost:
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x008e, code lost:
        r11 = r3;
        r9 = -1;
        r3 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c1  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m18032a(int r18, int r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            boolean r1 = r17.m18027b()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            java.lang.ref.SoftReference<com.baidu.platform.comapi.map.MapViewInterface> r1 = r0.f7619i
            if (r1 == 0) goto Lc9
            java.lang.Object r1 = r1.get()
            if (r1 != 0) goto L16
            goto Lc9
        L16:
            java.lang.ref.SoftReference<com.baidu.platform.comapi.map.MapViewInterface> r1 = r0.f7619i
            java.lang.Object r1 = r1.get()
            com.baidu.platform.comapi.map.MapViewInterface r1 = (com.baidu.platform.comapi.map.MapViewInterface) r1
            r3 = 0
            r5 = 1
            r6 = -1
            java.util.List r7 = r1.getOverlays()     // Catch: org.json.JSONException -> L8d
            int r7 = r7.size()     // Catch: org.json.JSONException -> L8d
            int r7 = r7 - r5
        L2b:
            if (r7 < 0) goto L86
            java.util.List r8 = r1.getOverlays()     // Catch: org.json.JSONException -> L8d
            java.lang.Object r8 = r8.get(r7)     // Catch: org.json.JSONException -> L8d
            com.baidu.platform.comapi.map.Overlay r8 = (com.baidu.platform.comapi.map.Overlay) r8     // Catch: org.json.JSONException -> L8d
            int r9 = r8.mType     // Catch: org.json.JSONException -> L8d
            r10 = 27
            if (r9 == r10) goto L3e
            goto L83
        L3e:
            long r3 = r8.mLayerID     // Catch: org.json.JSONException -> L8d
            int r8 = r0.nearlyRadius     // Catch: org.json.JSONException -> L8d
            double r8 = (double) r8     // Catch: org.json.JSONException -> L8d
            double r10 = r17.getZoomUnitsInMeter()     // Catch: org.json.JSONException -> L8d
            double r8 = r8 * r10
            int r8 = (int) r8     // Catch: org.json.JSONException -> L8d
            com.baidu.platform.comjni.map.basemap.AppBaseMap r9 = r0.f7629t     // Catch: org.json.JSONException -> L8d
            if (r9 == 0) goto L83
            com.baidu.platform.comjni.map.basemap.AppBaseMap r11 = r0.f7629t     // Catch: org.json.JSONException -> L8d
            r12 = r3
            r14 = r19
            r15 = r20
            r16 = r8
            java.lang.String r8 = r11.GetNearlyObjID(r12, r14, r15, r16)     // Catch: org.json.JSONException -> L8d
            if (r8 == 0) goto L83
            java.lang.String r9 = ""
            boolean r9 = r8.equals(r9)     // Catch: org.json.JSONException -> L8d
            if (r9 != 0) goto L83
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: org.json.JSONException -> L8d
            r7.<init>(r8)     // Catch: org.json.JSONException -> L8d
            java.lang.String r8 = "dataset"
            org.json.JSONArray r7 = r7.getJSONArray(r8)     // Catch: org.json.JSONException -> L8d
            java.lang.Object r7 = r7.get(r2)     // Catch: org.json.JSONException -> L8d
            org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch: org.json.JSONException -> L8d
            java.lang.String r8 = "itemindex"
            int r8 = r7.getInt(r8)     // Catch: org.json.JSONException -> L8d
            java.lang.String r9 = "clickindex"
            int r2 = r7.optInt(r9, r6)     // Catch: org.json.JSONException -> L8e
            r7 = r5
            goto L89
        L83:
            int r7 = r7 + (-1)
            goto L2b
        L86:
            r7 = r2
            r2 = r6
            r8 = r2
        L89:
            r9 = r2
            r11 = r3
            r3 = r7
            goto L91
        L8d:
            r8 = r6
        L8e:
            r11 = r3
            r9 = r6
            r3 = r2
        L91:
            r2 = r18
            if (r2 != r5) goto Lc8
            com.baidu.platform.comapi.map.MapViewListener r2 = r17.getMapViewListener()
            if (r2 == 0) goto Lc8
            com.baidu.platform.comapi.map.MapViewInterface r2 = r17.getMapView()
            if (r2 == 0) goto Lc8
            com.baidu.platform.comapi.map.MapViewInterface r2 = r17.getMapView()
            com.baidu.platform.comapi.map.Projection r2 = r2.getProjection()
            if (r2 == 0) goto Lc8
            com.baidu.platform.comapi.map.Projection r1 = r1.getProjection()
            r2 = r19
            r4 = r20
            com.baidu.platform.comapi.basestruct.GeoPoint r10 = r1.fromPixels(r2, r4)
            if (r9 == r6) goto Lc1
            com.baidu.platform.comapi.map.MapViewListener r7 = r17.getMapViewListener()
            r7.onClickedItem(r8, r9, r10, r11)
            goto Lc8
        Lc1:
            com.baidu.platform.comapi.map.MapViewListener r1 = r17.getMapViewListener()
            r1.onClickedItem(r8, r10, r11)
        Lc8:
            return r3
        Lc9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.MapController.m18032a(int, int, int):boolean");
    }

    /* renamed from: b */
    private boolean m18027b() {
        return this.f7588E && this.f7629t != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0226 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x025a A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x028b A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x029c A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02ac A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02bc A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02cd A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02de A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02ef A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0302 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0312 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0322 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0333 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0345 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0356 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0367 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0385 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x03a3 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x03b4 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x03c4 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x03d4 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03e5 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x03f3 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03fe A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0407 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0412 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x041b A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0426 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x042f  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x043a A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x044e A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0457 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0462 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x046b A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0476 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0486 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x049b A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x04b1 A[Catch: JSONException -> 0x0109, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:242:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x04c6 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x04d0 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x04db A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x04ef A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0504 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0511 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:268:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x06be A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:386:0x06eb A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:389:0x06f9 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0707 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0721 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:403:0x072f A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:406:0x073d A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:409:0x074b A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0756 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0764 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:418:0x076f A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:441:0x06c1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x019b A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:49:0x00f6, B:97:0x0195, B:99:0x019b, B:381:0x06c1, B:102:0x01be, B:104:0x01cb, B:108:0x01dc, B:110:0x01e5, B:111:0x01ec, B:113:0x01f2, B:115:0x0203, B:116:0x0209, B:123:0x021e, B:125:0x0226, B:132:0x0256, B:138:0x0283, B:140:0x028b, B:141:0x0294, B:143:0x029c, B:144:0x02a4, B:146:0x02ac, B:147:0x02b4, B:149:0x02bc, B:150:0x02c4, B:152:0x02cd, B:153:0x02d6, B:155:0x02de, B:156:0x02e6, B:158:0x02ef, B:159:0x02f8, B:161:0x0302, B:162:0x030a, B:164:0x0312, B:165:0x031a, B:167:0x0322, B:168:0x032a, B:170:0x0333, B:171:0x033c, B:173:0x0345, B:174:0x034e, B:176:0x0356, B:177:0x035e, B:179:0x0367, B:180:0x037c, B:182:0x0385, B:183:0x039a, B:185:0x03a3, B:186:0x03ac, B:188:0x03b4, B:189:0x03bc, B:191:0x03c4, B:192:0x03cc, B:194:0x03d4, B:195:0x03dc, B:197:0x03e5, B:199:0x03f6, B:201:0x03fe, B:203:0x040a, B:205:0x0412, B:207:0x041e, B:209:0x0426, B:210:0x042c, B:212:0x0432, B:214:0x043a, B:215:0x0440, B:217:0x0446, B:219:0x044e, B:221:0x045a, B:223:0x0462, B:225:0x046e, B:227:0x0476, B:228:0x047e, B:230:0x0486, B:231:0x048c, B:233:0x0492, B:235:0x049b, B:236:0x04a2, B:238:0x04a8, B:240:0x04b1, B:244:0x04be, B:246:0x04c6, B:248:0x04d3, B:250:0x04db, B:251:0x04e1, B:253:0x04e7, B:255:0x04ef, B:256:0x04f5, B:258:0x04fb, B:260:0x0504, B:263:0x0511, B:265:0x0532, B:380:0x06be, B:304:0x059c, B:312:0x05b5, B:325:0x05d5, B:327:0x05db, B:384:0x06e5, B:386:0x06eb, B:388:0x06f1, B:389:0x06f9, B:391:0x06ff, B:392:0x0707, B:396:0x0711, B:397:0x0719, B:398:0x071d, B:399:0x0721, B:401:0x0727, B:402:0x072b, B:403:0x072f, B:405:0x0735, B:406:0x073d, B:408:0x0743, B:409:0x074b, B:411:0x0751, B:412:0x0756, B:414:0x075c, B:415:0x0764, B:417:0x076a, B:418:0x076f, B:420:0x0775, B:332:0x05ee, B:334:0x05f4, B:339:0x0604, B:340:0x0607, B:342:0x060d, B:345:0x061b, B:347:0x0621, B:352:0x0632, B:356:0x063f, B:358:0x0645, B:364:0x065e, B:365:0x0664, B:367:0x066c, B:368:0x0674, B:372:0x0691, B:247:0x04d0, B:224:0x046b, B:220:0x0457, B:206:0x041b, B:202:0x0407, B:198:0x03f3, B:131:0x024f, B:128:0x023d, B:133:0x025a, B:135:0x0266, B:137:0x026e, B:117:0x020c, B:119:0x0214, B:82:0x013b, B:85:0x014c, B:88:0x015f, B:91:0x0169, B:93:0x017c, B:95:0x0188), top: B:425:0x00f4 }] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m18026b(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 2028
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.MapController.m18026b(int, int):boolean");
    }

    /* renamed from: c */
    private void m18023c() {
        this.f7592I = false;
        this.f7597N = 0.0f;
        this.f7595L = -1.0f;
        this.f7596M = -1.0f;
    }

    /* renamed from: c */
    private boolean m18022c(int i, int i2) {
        SoftReference<MapViewInterface> softReference = this.f7619i;
        if (softReference != null && softReference.get() != null) {
            int zoomUnitsInMeter = (int) (this.nearlyRadius * getZoomUnitsInMeter());
            MapViewInterface mapViewInterface = this.f7619i.get();
            for (int size = mapViewInterface.getBmlayers().size() - 1; size >= 0; size--) {
                if (mapViewInterface.getBmlayers().get(size).m18053a(i, i2, zoomUnitsInMeter)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: d */
    private void m18019d() {
        MessageProxy.registerMessageHandler(4000, this.f7631v);
        MessageProxy.registerMessageHandler(519, this.f7631v);
        MessageProxy.registerMessageHandler(39, this.f7631v);
        MessageProxy.registerMessageHandler(512, this.f7631v);
        MessageProxy.registerMessageHandler(65297, this.f7631v);
        MessageProxy.registerMessageHandler(65298, this.f7631v);
        MessageProxy.registerMessageHandler(50, this.f7631v);
        MessageProxy.registerMessageHandler(51, this.f7631v);
        MessageProxy.registerMessageHandler(65301, this.f7631v);
        MessageProxy.registerMessageHandler(41, this.f7631v);
        MessageProxy.registerMessageHandler(2082, this.f7631v);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003a A[LOOP:0: B:19:0x003a->B:29:0x0061, LOOP_START, PHI: r1 
      PHI: (r1v3 int) = (r1v0 int), (r1v4 int) binds: [B:18:0x0038, B:29:0x0061] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009f A[ORIG_RETURN, RETURN] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m18018d(int r9, int r10) {
        /*
            r8 = this;
            boolean r0 = r8.m18027b()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            com.baidu.platform.comjni.map.basemap.AppBaseMap r2 = r8.f7629t
            r3 = -1
            int r7 = r8.nearlyRadius
            r5 = r9
            r6 = r10
            java.lang.String r0 = r2.GetNearlyObjID(r3, r5, r6, r7)
            r2 = 0
            if (r0 == 0) goto L64
            java.lang.String r3 = ""
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L64
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L31
            r3.<init>(r0)     // Catch: org.json.JSONException -> L31
            java.lang.String r0 = "px"
            r3.put(r0, r9)     // Catch: org.json.JSONException -> L2f
            java.lang.String r9 = "py"
            r3.put(r9, r10)     // Catch: org.json.JSONException -> L2f
            goto L36
        L2f:
            r9 = move-exception
            goto L33
        L31:
            r9 = move-exception
            r3 = r2
        L33:
            r9.printStackTrace()
        L36:
            java.util.List<com.baidu.platform.comapi.map.al> r9 = r8.mListeners
            if (r9 == 0) goto L9f
        L3a:
            java.util.List<com.baidu.platform.comapi.map.al> r9 = r8.mListeners
            int r9 = r9.size()
            if (r1 >= r9) goto L9f
            java.util.List<com.baidu.platform.comapi.map.al> r9 = r8.mListeners
            java.lang.Object r9 = r9.get(r1)
            com.baidu.platform.comapi.map.al r9 = (com.baidu.platform.comapi.map.InterfaceC3010al) r9
            if (r3 == 0) goto L61
            if (r9 == 0) goto L61
            boolean r10 = r3 instanceof org.json.JSONObject
            if (r10 != 0) goto L57
            java.lang.String r10 = r3.toString()
            goto L5e
        L57:
            r10 = r3
            org.json.JSONObject r10 = (org.json.JSONObject) r10
            java.lang.String r10 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r10)
        L5e:
            r9.mo17966a(r10)
        L61:
            int r1 = r1 + 1
            goto L3a
        L64:
            java.util.List<com.baidu.platform.comapi.map.al> r0 = r8.mListeners
            if (r0 == 0) goto L9f
            com.baidu.platform.comapi.map.MapViewInterface r0 = r8.getMapView()
            if (r0 == 0) goto L9e
            com.baidu.platform.comapi.map.MapViewInterface r0 = r8.getMapView()
            com.baidu.platform.comapi.map.Projection r0 = r0.getProjection()
            if (r0 != 0) goto L79
            goto L9e
        L79:
            com.baidu.platform.comapi.map.MapViewInterface r0 = r8.getMapView()
            com.baidu.platform.comapi.map.Projection r0 = r0.getProjection()
            com.baidu.platform.comapi.basestruct.GeoPoint r9 = r0.fromPixels(r9, r10)
        L85:
            java.util.List<com.baidu.platform.comapi.map.al> r10 = r8.mListeners
            int r10 = r10.size()
            if (r1 >= r10) goto L9f
            java.util.List<com.baidu.platform.comapi.map.al> r10 = r8.mListeners
            java.lang.Object r10 = r10.get(r1)
            com.baidu.platform.comapi.map.al r10 = (com.baidu.platform.comapi.map.InterfaceC3010al) r10
            if (r10 != 0) goto L98
            goto L9b
        L98:
            r10.mo17967a(r9)
        L9b:
            int r1 = r1 + 1
            goto L85
        L9e:
            return r1
        L9f:
            r9 = 1
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.MapController.m18018d(int, int):boolean");
    }

    /* renamed from: e */
    private void m18016e() {
        MessageProxy.unRegisterMessageHandler(4000, this.f7631v);
        MessageProxy.unRegisterMessageHandler(519, this.f7631v);
        MessageProxy.unRegisterMessageHandler(39, this.f7631v);
        MessageProxy.unRegisterMessageHandler(512, this.f7631v);
        MessageProxy.unRegisterMessageHandler(65297, this.f7631v);
        MessageProxy.unRegisterMessageHandler(65298, this.f7631v);
        MessageProxy.unRegisterMessageHandler(50, this.f7631v);
        MessageProxy.unRegisterMessageHandler(51, this.f7631v);
        MessageProxy.unRegisterMessageHandler(65301, this.f7631v);
        MessageProxy.unRegisterMessageHandler(41, this.f7631v);
        MessageProxy.unRegisterMessageHandler(2082, this.f7631v);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x021c A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0226 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x022f A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0239 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0242 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x024c A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0260 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0274 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0289 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x029e A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02a7 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ba A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00dd A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x010b A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011c A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x012d A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0140 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0150 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0160 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0171 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0182 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0193 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01b1 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01cf A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01e0 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01f0 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0200 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0212 A[Catch: JSONException -> 0x02b9, TryCatch #0 {JSONException -> 0x02b9, blocks: (B:12:0x002a, B:14:0x0046, B:15:0x004c, B:134:0x02ac, B:24:0x0063, B:27:0x0070, B:29:0x007e, B:30:0x0085, B:32:0x008b, B:35:0x009e, B:40:0x00b2, B:42:0x00ba, B:49:0x00d9, B:55:0x0102, B:57:0x010b, B:58:0x0114, B:60:0x011c, B:61:0x0124, B:63:0x012d, B:64:0x0136, B:66:0x0140, B:67:0x0148, B:69:0x0150, B:70:0x0158, B:72:0x0160, B:73:0x0168, B:75:0x0171, B:76:0x017a, B:78:0x0182, B:79:0x018a, B:81:0x0193, B:82:0x01a8, B:84:0x01b1, B:85:0x01c6, B:87:0x01cf, B:88:0x01d8, B:90:0x01e0, B:91:0x01e8, B:93:0x01f0, B:94:0x01f8, B:96:0x0200, B:97:0x0208, B:99:0x0212, B:101:0x021e, B:103:0x0226, B:105:0x0231, B:107:0x0239, B:109:0x0244, B:111:0x024c, B:112:0x0252, B:114:0x0258, B:116:0x0260, B:117:0x0266, B:119:0x026c, B:121:0x0274, B:122:0x027a, B:124:0x0280, B:126:0x0289, B:127:0x0290, B:129:0x0296, B:131:0x029e, B:133:0x02a9, B:132:0x02a7, B:108:0x0242, B:104:0x022f, B:100:0x021c, B:48:0x00d4, B:45:0x00cc, B:50:0x00dd, B:52:0x00e5, B:54:0x00ed, B:36:0x00a5, B:39:0x00b0, B:21:0x005c, B:135:0x02b0, B:18:0x0053), top: B:140:0x002a, inners: #1 }] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m18015e(int r12, int r13) {
        /*
            Method dump skipped, instructions count: 698
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.MapController.m18015e(int, int):boolean");
    }

    public static int getScaleDis(int i) {
        switch (i) {
            case 1:
                return 10000000;
            case 2:
                return 5000000;
            case 3:
                return 2000000;
            case 4:
                return 1000000;
            case 5:
                return 500000;
            case 6:
                return 200000;
            case 7:
                return 100000;
            case 8:
                return 50000;
            case 9:
                return 25000;
            case 10:
                return 20000;
            case 11:
                return 10000;
            case 12:
                return 5000;
            case 13:
                return 2000;
            case 14:
                return 1000;
            case 15:
                return 500;
            case 16:
                return 200;
            case 17:
                return 100;
            case 18:
                return 50;
            case 19:
                return 20;
            case 20:
                return 10;
            case 21:
                return 5;
            case 22:
                return 2;
            default:
                return 0;
        }
    }

    public float GetFZoomToBoundF(Bundle bundle, Bundle bundle2) {
        if (m18027b()) {
            return this.f7629t.GetFZoomToBoundF(bundle, bundle2);
        }
        return 0.0f;
    }

    public int MapMsgProc(int i, int i2, int i3) {
        return MapMsgProc(i, i2, i3, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d);
    }

    public int MapMsgProc(int i, int i2, int i3, int i4, int i5, double d, double d2, double d3, double d4) {
        if (m18027b()) {
            return MapProc(this.f7630u, i, i2, i3, i4, i5, d, d2, d3, d4);
        }
        return -1;
    }

    public void SetStyleMode(int i) {
        setMapScene(i);
    }

    /* renamed from: a */
    void m18033a(int i, int i2) {
        if (m18027b()) {
            this.f7629t.MoveToScrPoint(i, i2);
        }
    }

    public void addOneOverlayItem(Bundle bundle) {
        this.f7629t.addOneOverlayItem(bundle);
    }

    public void addStreetCustomMarker(Bundle bundle, Bitmap bitmap) {
        if (m18027b()) {
            this.f7629t.AddStreetCustomMarker(bundle, bitmap);
        }
    }

    public void animateTo(GeoPoint geoPoint, int i) {
        if (m18027b()) {
            MapStatus mapStatus = getMapStatus();
            mapStatus.centerPtX = geoPoint.getLongitude();
            mapStatus.centerPtY = geoPoint.getLatitude();
            setMapStatusWithAnimation(mapStatus, i);
        }
    }

    public boolean cleanCache(MapLayerType mapLayerType) {
        AppBaseMap appBaseMap = this.f7629t;
        return appBaseMap != null && appBaseMap.CleanCache(mapLayerType.f7639a);
    }

    public void clearUniversalLayer() {
        if (m18027b()) {
            this.f7629t.clearUniversalLayer();
        }
    }

    public boolean createByDuplicateAppBaseMap(long j) {
        this.f7629t = new AppBaseMap();
        if (!this.f7629t.CreateByDuplicate(j)) {
            this.f7629t = null;
            this.f7630u = 0L;
            return false;
        }
        this.f7609aa = true;
        this.f7630u = this.f7629t.GetId();
        List<AppBaseMap> list = f7582W;
        if (list != null) {
            list.add(this.f7629t);
        }
        return true;
    }

    public void enablePOIAnimation(boolean z) {
        if (m18027b()) {
            this.f7629t.enablePOIAnimation(z);
        }
    }

    public void forceSetMapScene(int i) {
        this.f7624o = i;
        if (m18027b()) {
            this.f7629t.setMapScene(this.f7624o);
        }
    }

    public boolean forceSetMapThemeScene(int i, int i2, Bundle bundle) {
        this.f7625p = i;
        this.f7624o = i2;
        if (m18027b()) {
            return this.f7629t.setMapThemeScene(i, i2, bundle);
        }
        return false;
    }

    public float getAdapterZoomUnitsEx() {
        if (m18027b()) {
            return this.f7629t.GetAdapterZoomUnitsEx();
        }
        return 0.0f;
    }

    public AppBaseMap getBaseMap() {
        return this.f7629t;
    }

    public int getCacheSize(MapLayerType mapLayerType) {
        AppBaseMap appBaseMap = this.f7629t;
        if (appBaseMap == null) {
            return 0;
        }
        return appBaseMap.GetCacheSize(mapLayerType.f7639a);
    }

    public CaptureMapListener getCaptureMapListener() {
        return this.f7613c;
    }

    public String getCityInfoByID(int i) {
        AppBaseMap appBaseMap = this.f7629t;
        if (appBaseMap != null) {
            return appBaseMap.GetCityInfoByID(i);
        }
        return null;
    }

    public MapStatus getCurrentMapStatus() {
        return m18028a(false);
    }

    public float getCurrentZoomLevel() {
        Bundle GetMapStatus;
        AppBaseMap appBaseMap = this.f7629t;
        if (appBaseMap == null || (GetMapStatus = appBaseMap.GetMapStatus(false)) == null) {
            return 4.0f;
        }
        return (float) GetMapStatus.getDouble("level");
    }

    public IndoorMapInfo getFocusedBaseIndoorMapInfo() {
        String[] strArr;
        int[] iArr;
        if (m18027b()) {
            String GetFocusedBaseIndoorMapInfo = this.f7629t.GetFocusedBaseIndoorMapInfo();
            if (!TextUtils.isEmpty(GetFocusedBaseIndoorMapInfo)) {
                try {
                    JSONObject jSONObject = new JSONObject(GetFocusedBaseIndoorMapInfo);
                    String optString = jSONObject.optString("focusindoorid");
                    String optString2 = jSONObject.optString("curfloor");
                    int optInt = jSONObject.optInt("idrtype");
                    JSONArray optJSONArray = jSONObject.optJSONArray("floorlist");
                    if (optJSONArray != null) {
                        strArr = new String[optJSONArray.length()];
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            arrayList.add(optJSONArray.getString(i));
                        }
                        arrayList.toArray(strArr);
                    } else {
                        strArr = null;
                    }
                    JSONArray optJSONArray2 = jSONObject.optJSONArray("floorattribute");
                    if (optJSONArray2 != null) {
                        iArr = new int[optJSONArray2.length()];
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            iArr[i2] = optJSONArray2.optInt(i2);
                        }
                    } else {
                        iArr = null;
                    }
                    return new IndoorMapInfo(optString, optString2, strArr, iArr, optInt, jSONObject.optInt("idrguide"), jSONObject.optString("idrsearch"));
                } catch (JSONException unused) {
                }
            }
            return null;
        }
        return null;
    }

    public C3035d getGestureMonitor() {
        if (this.f7621l == null) {
            this.f7621l = new C3035d(this);
        }
        return this.f7621l;
    }

    public Bundle getGestureOptInfoForLog() {
        Bundle bundle = null;
        if (this.f7590G.f7647a) {
            MapStatus mapStatus = getMapStatus();
            int intX = this.f7590G.f7650d.getIntX();
            int intY = this.f7590G.f7650d.getIntY();
            int i = (Math.sqrt((intX * intX) + (intY * intY)) > 100.0d ? 1 : (Math.sqrt((intX * intX) + (intY * intY)) == 100.0d ? 0 : -1));
            boolean z = true;
            boolean z2 = i > 0;
            if (this.f7590G.f7648b <= 0.0f || Math.abs(mapStatus.level - this.f7590G.f7648b) < 0.5d) {
                z = false;
            }
            if (z2 || z) {
                bundle = new Bundle();
                bundle.putDouble("pre_x", this.f7590G.f7649c.getLongitude());
                bundle.putDouble("pre_y", this.f7590G.f7649c.getLatitude());
                bundle.putFloat("pre_level", this.f7590G.f7648b);
            }
            this.f7590G.m18008a();
            return bundle;
        }
        return null;
    }

    public InterfaceC3069k getHideIndoorPopupListener() {
        return this.f7614d;
    }

    public EngineMsgListener getIndoorMapListener() {
        return this.f7617g;
    }

    public List<InterfaceC3010al> getListeners() {
        return this.mListeners;
    }

    public boolean getMapBarData() {
        if (m18027b()) {
            Bundle bundle = new Bundle();
            this.f7629t.getMapBarData(bundle);
            byte[] bArr = new byte[0];
            String string = bundle.containsKey("uid") ? bundle.getString("uid") : null;
            String string2 = bundle.containsKey("searchbound") ? bundle.getString("searchbound") : null;
            String string3 = bundle.containsKey("curfloor") ? bundle.getString("curfloor") : null;
            if (bundle.containsKey("barinfo")) {
                bArr = bundle.getByteArray("barinfo");
            }
            C3087a.m17703a().m17700a(new C3019b(string, string2, string3, bArr));
            return true;
        }
        return false;
    }

    public boolean getMapBarShowData() {
        if (m18027b()) {
            return this.f7629t.getMapBarData(new Bundle());
        }
        return false;
    }

    public boolean getMapClickEnable() {
        return this.f7586C;
    }

    public MapControlMode getMapControlMode() {
        return this.f7611ac;
    }

    public long getMapId() {
        return this.f7630u;
    }

    public MapRenderModeChangeListener getMapRenderModeChangeListener() {
        return this.f7616f;
    }

    public int getMapScene() {
        if (m18027b()) {
            return this.f7629t.getMapScene();
        }
        return 0;
    }

    public MapStatus getMapStatus() {
        return m18028a(true);
    }

    public C2948x getMapStatusInner() {
        if (m18027b()) {
            Bundle GetMapStatus = this.f7629t.GetMapStatus();
            C2948x c2948x = new C2948x();
            c2948x.m18191a(GetMapStatus);
            return c2948x;
        }
        return null;
    }

    public int getMapTheme() {
        if (m18027b()) {
            return this.f7629t.getMapTheme();
        }
        return 0;
    }

    public MapViewInterface getMapView() {
        SoftReference<MapViewInterface> softReference = this.f7619i;
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    public MapViewListener getMapViewListener() {
        return this.f7612b;
    }

    public MapViewSurfaceListener getMapViewSurfaceListener() {
        return this.f7618h;
    }

    public NaviMapViewListener getNaviMapViewListener() {
        return this.f7620j;
    }

    public String getProjectionPt(String str) {
        if (m18027b()) {
            return this.f7629t.getProjectionPt(str);
        }
        return null;
    }

    public int getScaleLevel(int i, int i2) {
        if (m18027b()) {
            return this.f7629t.getScaleLevel(i, i2);
        }
        return 0;
    }

    public int getSceneLayerScene() {
        return this.f7624o;
    }

    public int getSceneLayerTheme() {
        return this.f7625p;
    }

    public int getScreenHeight() {
        MapStatus mapStatus = getMapStatus();
        this.f7633x = mapStatus.winRound.bottom - mapStatus.winRound.top;
        return this.f7633x;
    }

    public int getScreenWidth() {
        MapStatus mapStatus = getMapStatus();
        this.f7632w = mapStatus.winRound.right - mapStatus.winRound.left;
        return this.f7632w;
    }

    public InterfaceC3012an getStreetArrowClickListener() {
        return this.f7615e;
    }

    public int getVMPMapCityCode() {
        if (this.f7629t != null) {
            Bundle bundle = new Bundle();
            bundle.putString("querytype", "map");
            this.f7629t.GetVMPMapCityInfo(bundle);
            return bundle.getInt("code");
        }
        return 0;
    }

    public int getVMPMapCityItsInfo() {
        if (this.f7629t != null) {
            Bundle bundle = new Bundle();
            bundle.putString("querytype", "its");
            this.f7629t.GetVMPMapCityInfo(bundle);
            return bundle.getInt("rst");
        }
        return 0;
    }

    public int getVMPMapCityLevel() {
        if (this.f7629t != null) {
            Bundle bundle = new Bundle();
            bundle.putString("querytype", "map");
            this.f7629t.GetVMPMapCityInfo(bundle);
            return bundle.getInt("level");
        }
        return 0;
    }

    public int getVMPMapCitySatInfo() {
        if (this.f7629t != null) {
            Bundle bundle = new Bundle();
            bundle.putString("querytype", "sat");
            this.f7629t.GetVMPMapCityInfo(bundle);
            return bundle.getInt("rst");
        }
        return 0;
    }

    public float getZoomLevel() {
        Bundle GetMapStatus;
        AppBaseMap appBaseMap = this.f7629t;
        if (appBaseMap == null || (GetMapStatus = appBaseMap.GetMapStatus()) == null) {
            return 4.0f;
        }
        return (float) GetMapStatus.getDouble("level");
    }

    public float getZoomToBound(Bundle bundle, int i, int i2) {
        if (m18027b()) {
            return this.f7629t.GetZoomToBound(bundle, i, i2);
        }
        return 0.0f;
    }

    public float getZoomToBoundF(Bundle bundle) {
        if (m18027b()) {
            return this.f7629t.GetZoomToBoundF(bundle);
        }
        return 0.0f;
    }

    public double getZoomUnitsInMeter() {
        Bundle GetMapStatus;
        AppBaseMap baseMap = getBaseMap();
        if (baseMap != null && (GetMapStatus = baseMap.GetMapStatus()) != null) {
            double d = GetMapStatus.getFloat("adapterZoomUnits");
            if (d > 1.0E-4d) {
                return d;
            }
        }
        return Math.pow(2.0d, 18.0f - getZoomLevel());
    }

    public void handleClick(MotionEvent motionEvent) {
        MapMsgProc(20738, 0, ((int) motionEvent.getX()) | (((int) motionEvent.getY()) << 16));
    }

    public void handleDoubleClickZoom(MotionEvent motionEvent) {
        if (System.currentTimeMillis() - this.f7598O < 100) {
            return;
        }
        mapStatusChangeStart();
        this.mIsAnimating = true;
        float y = motionEvent.getY();
        float f = this.f7596M - y;
        MapMsgProc(8193, 3, (int) ((f / (getScreenHeight() / 9.0f)) * 10000.0f));
        this.f7597N = f;
        this.f7596M = y;
        C3087a.m17703a().m17700a(new C2997d());
        if (!isNaviMode() || getNaviMapViewListener() == null) {
            return;
        }
        getNaviMapViewListener().onAction(521, null);
    }

    public void handleDoubleDownClick(MotionEvent motionEvent) {
        this.f7592I = true;
        this.f7595L = motionEvent.getX();
        this.f7596M = motionEvent.getY();
        this.f7598O = System.currentTimeMillis();
        C3087a.m17703a().m17700a(new C2997d());
    }

    public void handleDoubleTouch(MotionEvent motionEvent) {
        SoftReference<MapViewInterface> softReference;
        GeoPoint fromPixels;
        float f;
        NaviMapViewListener naviMapViewListener;
        if (System.currentTimeMillis() - this.f7598O > 150) {
            return;
        }
        if (isNaviMode() && (naviMapViewListener = this.f7620j) != null) {
            naviMapViewListener.onAction(513, motionEvent);
        } else if (this.f7591H && (softReference = this.f7619i) != null && softReference.get() != null && this.f7619i.get().getProjection() != null) {
            MapStatus mapStatus = getMapStatus();
            float x = motionEvent.getX() - (mapStatus.winRound.left + (getScreenWidth() / 2));
            float y = (motionEvent.getY() - (mapStatus.winRound.top + (getScreenHeight() / 2))) * (-1.0f);
            float f2 = 0.0f;
            if (isCompass || this.f7594K) {
                fromPixels = this.f7619i.get().getProjection().fromPixels(mapStatus.winRound.left + (getScreenWidth() / 2), mapStatus.winRound.top + (getScreenHeight() / 2));
                x = 0.0f;
                y = 0.0f;
            } else {
                fromPixels = this.f7619i.get().getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY());
            }
            if (fromPixels != null) {
                f2 = (float) fromPixels.getLongitudeE6();
                f = (float) fromPixels.getLatitudeE6();
            } else {
                f = 0.0f;
            }
            this.f7599P = true;
            android.graphics.Point point = new android.graphics.Point((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
            if (fromPixels != null && this.mListeners != null) {
                for (int i = 0; i < this.mListeners.size(); i++) {
                    InterfaceC3010al interfaceC3010al = this.mListeners.get(i);
                    if (interfaceC3010al != null) {
                        interfaceC3010al.mo17959b(fromPixels);
                        if (interfaceC3010al.mo17971a(point, getMapStatusInner())) {
                            return;
                        }
                    }
                }
            }
            getGestureMonitor().m17879b(this.f7619i.get().getZoomLevel() + 1.0f);
            mapStatusChangeStart();
            MapMsgProc(8195, ((int) motionEvent.getX()) | (((int) motionEvent.getY()) << 16), (this.f7632w / 2) | ((this.f7633x / 2) << 16), 0, 0, f2, f, x, y);
            f7581R = System.currentTimeMillis();
            procGestureForLog(false, null);
        }
    }

    @SuppressLint({"FloatMath"})
    public boolean handleFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (f7584y && this.f7604V && this.f7623n) {
            float sqrt = (float) ((((float) Math.sqrt((f * f) + (f2 * f2))) / (SysOSUtil.getInstance().getDensityDPI() / 310.0f)) * 1.3d);
            if (getMapControlMode() != MapControlMode.STREET && sqrt < 300.0f) {
                this.f7587D = false;
                return false;
            }
            this.f7587D = true;
            if (this.mListeners != null) {
                C2948x mapStatusInner = getMapStatusInner();
                for (int i = 0; i < this.mListeners.size(); i++) {
                    InterfaceC3010al interfaceC3010al = this.mListeners.get(i);
                    if (interfaceC3010al != null && interfaceC3010al.mo17969a(motionEvent2, f, f2, mapStatusInner)) {
                        this.f7606Y = false;
                        return false;
                    }
                }
            }
            getGestureMonitor().m17883a();
            mapStatusChangeStart();
            MapMsgProc(34, (int) sqrt, (((int) motionEvent2.getY()) << 16) | ((int) motionEvent2.getX()));
            if (getMapViewListener() != null) {
                C3087a.m17703a().m17700a(new C2996c());
            }
            this.f7606Y = false;
            if (this.mListeners != null) {
                for (int i2 = 0; i2 < this.mListeners.size(); i2++) {
                    InterfaceC3010al interfaceC3010al2 = this.mListeners.get(i2);
                    if (interfaceC3010al2 != null) {
                        interfaceC3010al2.mo17970a(motionEvent2);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean handleKeyEvent(int i, KeyEvent keyEvent) {
        int GetAdaptKeyCode = GetAdaptKeyCode(i);
        if (GetAdaptKeyCode == 0) {
            return false;
        }
        MapMsgProc(1, GetAdaptKeyCode, 0);
        return true;
    }

    public void handleLongClick(MotionEvent motionEvent) {
        MapMsgProc(20737, 0, ((int) motionEvent.getX()) | (((int) motionEvent.getY()) << 16));
    }

    public int handleMapModeGet() {
        return MapMsgProc(4113, 0, 0);
    }

    public boolean handlePopupClick(int i, int i2) {
        return false;
    }

    public void handleRightClick() {
        MapMsgProc(20739, 0, 0);
    }

    public void handleStreetscapeDoubleTouch(MotionEvent motionEvent) {
        float f;
        SoftReference<MapViewInterface> softReference = this.f7619i;
        if (softReference == null || softReference.get() == null || this.f7619i.get().getProjection() == null) {
            return;
        }
        MapStatus mapStatus = getMapStatus();
        GeoPoint fromPixels = this.f7619i.get().getProjection().fromPixels(mapStatus.winRound.left + (this.f7632w / 2), mapStatus.winRound.top + (this.f7633x / 2));
        float f2 = 0.0f;
        if (fromPixels != null) {
            f2 = (float) fromPixels.getLongitudeE6();
            f = (float) fromPixels.getLatitudeE6();
        } else {
            f = 0.0f;
        }
        MapMsgProc(8195, (((int) motionEvent.getY()) << 16) | ((int) motionEvent.getX()), ((this.f7633x / 2) << 16) | (this.f7632w / 2), 0, 0, f2, f, 0.0d, 0.0d);
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        if (m18027b()) {
            if (!this.f7587D) {
                this.f7610ab.m17902a(motionEvent);
            }
            if (motionEvent.getPointerCount() == 2) {
                this.f7622m = true;
                f7584y = false;
                m18023c();
                procGestureForLog(false, null);
            }
            if (motionEvent.getAction() != 2 && this.f7592I) {
                this.f7622m = true;
                m18023c();
            }
            switch (motionEvent.getAction()) {
                case 0:
                    this.f7622m = true;
                    m18031a(motionEvent);
                    break;
                case 1:
                    f7584y = true;
                    this.f7622m = true;
                    handleTouchUp(motionEvent);
                    break;
                case 2:
                    if (this.f7592I) {
                        handleDoubleClickZoom(motionEvent);
                        break;
                    } else if (this.f7604V) {
                        handleTouchMove(motionEvent);
                        break;
                    }
                    break;
                default:
                    return false;
            }
            if (this.mListeners != null) {
                for (int i = 0; i < this.mListeners.size(); i++) {
                    InterfaceC3010al interfaceC3010al = this.mListeners.get(i);
                    if (interfaceC3010al != null) {
                        interfaceC3010al.mo17970a(motionEvent);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean handleTouchMove(MotionEvent motionEvent) {
        if (f7584y && System.currentTimeMillis() - f7581R >= 300) {
            if (this.mHasMapObjDraging) {
                if (getMapView() != null && getMapView().getProjection() != null) {
                    GeoPoint fromPixels = getMapView().getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY());
                    if (this.mListeners != null) {
                        for (int i = 0; i < this.mListeners.size(); i++) {
                            InterfaceC3010al interfaceC3010al = this.mListeners.get(i);
                            if (interfaceC3010al != null && fromPixels != null) {
                                interfaceC3010al.mo17951d(fromPixels);
                            }
                        }
                    }
                }
                return true;
            }
            float abs = Math.abs(motionEvent.getX() - f7585z);
            float abs2 = Math.abs(motionEvent.getY() - f7579A);
            double density = SysOSUtil.getInstance().getDensity();
            if (density > 1.5d) {
                density *= 1.5d;
            }
            float f = (float) density;
            if (!f7580B || abs / f > 3.0f || abs2 / f > 3.0f) {
                f7580B = false;
                if (isCompass) {
                    C3087a.m17703a().m17700a(new C2994a());
                }
                procGestureForLog(true, new Point(abs, abs2));
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                float x2 = f7585z - motionEvent.getX();
                float y2 = f7579A - motionEvent.getY();
                android.graphics.Point point = new android.graphics.Point((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                android.graphics.Point point2 = new android.graphics.Point((int) (motionEvent.getRawX() + x2), (int) (motionEvent.getRawY() + y2));
                if (x < 0) {
                    x = 0;
                }
                if (y < 0) {
                    y = 0;
                }
                if (this.mListeners != null) {
                    C2948x mapStatusInner = getMapStatusInner();
                    for (int i2 = 0; i2 < this.mListeners.size(); i2++) {
                        InterfaceC3010al interfaceC3010al2 = this.mListeners.get(i2);
                        if (interfaceC3010al2 != null && interfaceC3010al2.mo17961b(point2, point, mapStatusInner)) {
                            break;
                        }
                    }
                }
                if (this.f7622m) {
                    getGestureMonitor().m17880b();
                    this.f7622m = false;
                }
                mapStatusChangeStart();
                MapMsgProc(3, 0, x | (y << 16));
                C3087a.m17703a().m17700a(new C2995b(false, true));
                this.f7587D = false;
                this.f7628s = true;
                this.f7606Y = true;
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean handleTouchSingleClick(MotionEvent motionEvent) {
        NaviMapViewListener naviMapViewListener;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        m18018d(x, y);
        if (m18015e(x, y) || handlePopupClick(x, y) || m18022c(x, y) || m18032a(1, x, y)) {
            return true;
        }
        if (this.f7586C && m18026b(x, y)) {
            return true;
        }
        if (isNaviMode() && (naviMapViewListener = this.f7620j) != null) {
            naviMapViewListener.onAction(514, motionEvent);
        }
        if (getMapViewListener() != null) {
            getMapViewListener().onClickedBackground((int) motionEvent.getX(), (int) motionEvent.getY());
            return false;
        }
        return false;
    }

    public boolean handleTouchUp(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (this.mHasMapObjDraging) {
            if (this.mListeners != null && getMapView() != null && getMapView().getProjection() != null) {
                GeoPoint fromPixels = getMapView().getProjection().fromPixels(x, y);
                for (int i = 0; i < this.mListeners.size(); i++) {
                    InterfaceC3010al interfaceC3010al = this.mListeners.get(i);
                    if (interfaceC3010al != null && fromPixels != null) {
                        interfaceC3010al.mo17950e(fromPixels);
                    }
                }
            }
            this.mHasMapObjDraging = false;
            return true;
        }
        if (f7584y) {
            MapMsgProc(5, 0, x | (y << 16));
        }
        if (!this.f7587D && getMapViewListener() != null) {
            getMapViewListener().onMapAnimationFinish();
        }
        if (!this.f7587D && isNaviMode() && getNaviMapViewListener() != null) {
            getNaviMapViewListener().onMapAnimationFinish();
        }
        boolean z = motionEvent.getEventTime() - this.f7605X < 300 && Math.abs(motionEvent.getX() - f7585z) < 10.0f && Math.abs(motionEvent.getY() - f7579A) < 10.0f;
        if (!this.f7587D && ((!z || this.f7606Y) && !this.f7599P && !this.f7600Q && !this.mIsAnimating && this.mListeners != null)) {
            C2948x mapStatusInner = getMapStatusInner();
            for (int i2 = 0; i2 < this.mListeners.size(); i2++) {
                InterfaceC3010al interfaceC3010al2 = this.mListeners.get(i2);
                if (interfaceC3010al2 != null) {
                    interfaceC3010al2.mo17955c(mapStatusInner);
                }
            }
            this.mIsMoving = false;
        }
        this.f7606Y = false;
        this.f7587D = false;
        C3087a.m17703a().m17700a(new C2995b(true, false));
        C3087a.m17703a().m17700a(new C2996c());
        return true;
    }

    public boolean handleTrackballEvent(MotionEvent motionEvent) {
        if (m18027b()) {
            if (motionEvent.getAction() == 2) {
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                int i = rawX > 0.0f ? 18 : rawX < 0.0f ? 16 : 0;
                if (rawY > 0.0f) {
                    i = 19;
                } else if (rawY < 0.0f) {
                    i = 17;
                }
                if (i == 0) {
                    return false;
                }
                MapMsgProc(1, i, 0);
            }
            return true;
        }
        return false;
    }

    public boolean handleZoomTo(int i) {
        int i2;
        switch (i) {
            case 0:
                i2 = 4097;
                break;
            case 1:
                i2 = 4096;
                break;
            default:
                return false;
        }
        MapMsgProc(i2, -1, 0);
        return false;
    }

    public boolean importMapTheme(int i) {
        if (m18027b()) {
            return this.f7629t.importMapTheme(i);
        }
        return false;
    }

    public void initAppBaseMap() {
        if (f7582W.size() == 0) {
            initBaseMap();
        } else {
            createByDuplicateAppBaseMap(f7582W.get(0).GetId());
        }
    }

    public void initBaseMap() {
        this.f7629t = new AppBaseMap();
        this.f7629t.Create();
        this.f7630u = this.f7629t.GetId();
        List<AppBaseMap> list = f7582W;
        if (list != null) {
            list.add(this.f7629t);
        }
    }

    public void initMapResources(Bundle bundle) {
        if (this.f7588E || bundle == null || this.f7629t == null) {
            return;
        }
        boolean z = SysOSUtil.getInstance().getDensityDPI() >= 180;
        this.nearlyRadius = (SysOSUtil.getInstance().getDensityDPI() * 25) / 240;
        String string = bundle.getString("modulePath");
        String string2 = bundle.getString("appSdcardPath");
        String string3 = bundle.getString("appCachePath");
        String string4 = bundle.getString("appSecondCachePath");
        String string5 = bundle.getString("engineErrorPath");
        int i = bundle.getInt("mapTmpMax");
        int i2 = bundle.getInt("domTmpMax");
        int i3 = bundle.getInt("itsTmpMax");
        int i4 = bundle.getInt("ssgTmpMax");
        String str = z ? "/h/" : "/l/";
        String str2 = string + "/cfg";
        String str3 = string2 + "/vmp";
        String str4 = str2 + "/a/";
        String str5 = str3 + str;
        String str6 = str3 + str;
        String str7 = string3 + "/tmp/";
        Bundle bundle2 = new Bundle();
        bundle2.putString("cfgdataroot", str4);
        bundle2.putString("vmpdataroot", str5);
        bundle2.putString("tmpdataroot", str7);
        bundle2.putString("tmpdatapast", string4 + "/tmp/");
        bundle2.putString("importroot", str6);
        bundle2.putString("stylerespath", str2 + "/a/");
        if (string5 != null && string5.length() > 0) {
            bundle2.putString("engineerrorpath", string5);
        }
        bundle2.putInt("cx", this.f7632w);
        bundle2.putInt("cy", this.f7633x);
        bundle2.putInt("ndpi", SysOSUtil.getInstance().getDensityDPI());
        bundle2.putFloat("fdpi", SysOSUtil.getInstance().getDensityDPI());
        bundle2.putInt("maptmpmax", i);
        bundle2.putInt("domtmpmax", i2);
        bundle2.putInt("itstmpmax", i3);
        bundle2.putInt("ssgtmpmax", i4);
        bundle2.putInt("pathchange", 0);
        if (bundle.containsKey("maptheme")) {
            bundle2.putInt("maptheme", bundle.getInt("maptheme"));
        }
        if (bundle.containsKey("mapscene")) {
            bundle2.putInt("mapscene", bundle.getInt("mapscene"));
        }
        if (bundle.containsKey("fontsizelevel")) {
            bundle2.putInt("fontsizelevel", bundle.getInt("fontsizelevel"));
        }
        if (!C2981b.m18066f()) {
            C2981b.m18067e();
        }
        if (this.f7629t.initWithOptions(bundle2, false)) {
            this.f7629t.SetMapStatus(bundle);
            this.f7588E = true;
            return;
        }
        Log.e(f7583k, "MapControl init fail!");
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b.m18459a().m18457a("MapControl init fail");
        }
    }

    public boolean is3DGestureEnable() {
        return this.f7601S;
    }

    public boolean isBaseIndoorMapMode() {
        if (m18027b()) {
            return this.f7629t.IsBaseIndoorMapMode();
        }
        return false;
    }

    public boolean isCanTouchMove() {
        return this.f7604V;
    }

    public boolean isDoubleClickZoom() {
        return this.f7591H;
    }

    public boolean isDuplicate() {
        return this.f7609aa;
    }

    public boolean isEnableDMoveZoom() {
        return this.f7592I;
    }

    public boolean isEnableIndoor3D() {
        if (m18027b()) {
            return this.f7629t.isEnableIndoor3D();
        }
        return true;
    }

    public boolean isEnableZoom() {
        return this.f7603U;
    }

    public boolean isEnlargeCenterWithDoubleClickEnabled() {
        return this.f7594K;
    }

    public boolean isFlingEnabled() {
        return this.f7623n;
    }

    public boolean isInFocusBarBorder(GeoPoint geoPoint, double d) {
        return m18027b() && geoPoint != null && this.f7629t.IsPointInFocusBarBorder(geoPoint.getLongitude(), geoPoint.getLatitude(), d);
    }

    public boolean isInFocusIndoorBuilding(GeoPoint geoPoint) {
        return m18027b() && geoPoint != null && this.f7629t.IsPointInFocusIDRBorder(geoPoint.getLongitude(), geoPoint.getLatitude());
    }

    public boolean isMapAnimationRunning() {
        if (m18027b()) {
            return this.f7629t.isAnimationRunning();
        }
        return false;
    }

    public boolean isMovedMap() {
        return this.f7628s;
    }

    public boolean isNaviMode() {
        if (m18027b()) {
            return this.f7629t.isNaviMode();
        }
        return false;
    }

    public boolean isOverlookGestureEnable() {
        return this.f7602T;
    }

    public boolean isPressedOnPopup(int i, int i2) {
        return false;
    }

    public boolean isStreetArrowShown() {
        if (m18027b()) {
            return this.f7629t.IsStreetArrowShown();
        }
        return false;
    }

    public boolean isStreetCustomMarkerShown() {
        if (m18027b()) {
            return this.f7629t.IsStreetCustomMarkerShown();
        }
        return false;
    }

    public boolean isStreetPOIMarkerShown() {
        if (m18027b()) {
            return this.f7629t.IsStreetPOIMarkerShown();
        }
        return false;
    }

    public boolean isStreetRoadClickable() {
        if (m18027b()) {
            return this.f7629t.IsStreetRoadClickable();
        }
        return false;
    }

    public boolean isTwoTouchClickZoomEnabled() {
        return this.f7593J;
    }

    public void mapStatusChangeStart() {
        if (this.mIsMoving) {
            return;
        }
        this.mIsMoving = true;
        this.mIsAnimating = false;
        if (this.mListeners != null) {
            C2948x mapStatusInner = getMapStatusInner();
            for (int i = 0; i < this.mListeners.size(); i++) {
                InterfaceC3010al interfaceC3010al = this.mListeners.get(i);
                if (interfaceC3010al != null) {
                    interfaceC3010al.mo17968a(mapStatusInner);
                }
            }
        }
    }

    public void onPause() {
        if (m18027b()) {
            this.f7629t.OnPause();
        }
    }

    public void onResume() {
        if (m18027b()) {
            this.f7629t.OnResume();
        }
    }

    public void procGestureForLog(boolean z, Point point) {
        if (!this.f7590G.f7647a) {
            MapStatus mapStatus = getMapStatus();
            C2986a c2986a = this.f7590G;
            c2986a.f7647a = true;
            c2986a.f7648b = mapStatus.level;
            this.f7590G.f7649c = new GeoPoint(mapStatus.centerPtX, mapStatus.centerPtY);
            this.f7590G.f7650d = new Point(0, 0);
        }
        if (z) {
            int abs = Math.abs(point.getIntX());
            int abs2 = Math.abs(point.getIntY());
            this.f7590G.f7650d.setIntX(this.f7590G.f7650d.getIntX() + abs);
            this.f7590G.f7650d.setIntY(this.f7590G.f7650d.getIntY() + abs2);
        }
    }

    public void recycleMemory(RecycleMemoryLevel recycleMemoryLevel) {
        if (m18027b()) {
            this.f7629t.recycleMemory(recycleMemoryLevel.getLevel());
        }
    }

    public void registMapViewListener(InterfaceC3010al interfaceC3010al) {
        List<InterfaceC3010al> list;
        if (interfaceC3010al == null || (list = this.mListeners) == null) {
            return;
        }
        list.add(interfaceC3010al);
    }

    public void removeOneOverlayItem(Bundle bundle) {
        this.f7629t.removeOneOverlayItem(bundle);
    }

    public void removeStreetAllCustomMarker() {
        if (m18027b()) {
            this.f7629t.RemoveStreetAllCustomMarker();
        }
    }

    public void removeStreetCustomMarker(String str) {
        if (m18027b()) {
            this.f7629t.RemoveStreetCustomMaker(str);
        }
    }

    public void saveScreenToLocal(String str) {
        saveScreenToLocal(str, 0, 0, 0, 0);
    }

    public void saveScreenToLocal(String str, int i, int i2, int i3, int i4) {
        if (!m18027b() || TextUtils.isEmpty(str)) {
            return;
        }
        String str2 = null;
        if (i3 != 0 && i4 != 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("x", i);
                jSONObject.put("y", i2);
                jSONObject.put("width", i3);
                jSONObject.put("height", i4);
                str2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            } catch (Exception unused) {
            }
        }
        this.f7629t.SaveScreenToLocal(str, str2);
    }

    public void scrollBy(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return;
        }
        MapStatus mapStatus = getMapStatus();
        m18033a(mapStatus.winRound.left + (this.f7632w / 2) + i, mapStatus.winRound.top + (this.f7633x / 2) + i2);
    }

    public void set3DGestureEnable(boolean z) {
        this.f7601S = z;
    }

    public void setActingTwoClickZoom(boolean z) {
        this.f7600Q = z;
    }

    public void setAllStreetCustomMarkerVisibility(boolean z) {
        if (m18027b()) {
            this.f7629t.SetAllStreetCustomMarkerVisibility(z);
        }
    }

    public void setCanTouchMove(boolean z) {
        this.f7604V = z;
    }

    public void setCaptureMapListener(CaptureMapListener captureMapListener) {
        this.f7613c = captureMapListener;
    }

    public void setDoubleClickZoom(boolean z) {
        this.f7591H = z;
    }

    public void setEnableIndoor3D(boolean z) {
        if (m18027b()) {
            this.f7629t.setEnableIndoor3D(z);
        }
    }

    public void setEnableZoom(boolean z) {
        this.f7603U = z;
    }

    public void setEngineMsgListener(EngineMsgListener engineMsgListener) {
        this.f7617g = engineMsgListener;
    }

    public void setEnlargeCenterWithDoubleClickEnable(boolean z) {
        this.f7594K = z;
    }

    public void setFlingEnable(boolean z) {
        this.f7623n = z;
    }

    public void setHideIndoorPopupListener(InterfaceC3069k interfaceC3069k) {
        this.f7614d = interfaceC3069k;
    }

    public void setInertialAnimation(boolean z) {
        this.mIsInertialAnimation = z;
        boolean z2 = this.mIsInertialAnimation;
        this.f7599P = z2;
        this.f7600Q = z2;
    }

    public boolean setLayerSceneMode(long j, MapSceneMode mapSceneMode) {
        if (m18027b()) {
            return this.f7629t.SetLayerSceneMode(j, mapSceneMode.getMode());
        }
        return false;
    }

    public void setMapClickEnable(boolean z) {
        this.f7586C = z;
    }

    public int setMapControlMode(MapControlMode mapControlMode) {
        if (m18027b()) {
            this.f7611ac = mapControlMode;
            return this.f7629t.SetMapControlMode(mapControlMode.f7637a);
        }
        return -1;
    }

    public void setMapFirstFrameCallback(MapFirstFrameCallback mapFirstFrameCallback) {
        this.f7627r = mapFirstFrameCallback;
    }

    public void setMapRenderModeChangeListener(MapRenderModeChangeListener mapRenderModeChangeListener) {
        this.f7616f = mapRenderModeChangeListener;
    }

    public void setMapScene(int i) {
        if (i == getMapScene()) {
            return;
        }
        this.f7624o = i;
        if (m18027b()) {
            this.f7629t.setMapScene(this.f7624o);
        }
    }

    public void setMapStatus(Bundle bundle) {
        if (m18027b()) {
            this.f7629t.SetMapStatus(bundle);
        }
    }

    public void setMapStatus(MapStatus mapStatus) {
        if (!m18027b() || mapStatus == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("level", mapStatus.level);
        bundle.putDouble("rotation", mapStatus.rotation);
        bundle.putDouble("overlooking", mapStatus.overlooking);
        bundle.putDouble("centerptx", mapStatus.centerPtX);
        bundle.putDouble("centerpty", mapStatus.centerPtY);
        bundle.putDouble("centerptz", mapStatus.centerPtZ);
        bundle.putInt("left", mapStatus.winRound.left);
        bundle.putInt("right", mapStatus.winRound.right);
        bundle.putInt("top", mapStatus.winRound.top);
        bundle.putInt("bottom", mapStatus.winRound.bottom);
        bundle.putLong("gleft", mapStatus.geoRound.left);
        bundle.putLong("gbottom", mapStatus.geoRound.bottom);
        bundle.putLong("gtop", mapStatus.geoRound.top);
        bundle.putLong("gright", mapStatus.geoRound.right);
        bundle.putFloat("yoffset", mapStatus.yOffset);
        bundle.putFloat("xoffset", mapStatus.xOffset);
        bundle.putInt("animatime", mapStatus.animationTime);
        bundle.putInt("animation", mapStatus.hasAnimation);
        bundle.putInt("animationType", mapStatus.animationType);
        bundle.putInt("bfpp", mapStatus.bfpp ? 1 : 0);
        bundle.putString("panoid", mapStatus.panoId);
        bundle.putInt("autolink", 0);
        bundle.putFloat("siangle", mapStatus.streetIndicateAngle);
        bundle.putInt("isbirdeye", mapStatus.isBirdEye ? 1 : 0);
        bundle.putInt("ssext", mapStatus.streetExt);
        bundle.putFloat("roadOffsetX", mapStatus.roadOffsetX);
        bundle.putFloat("roadOffsetY", mapStatus.roadOffsetY);
        mapStatusChangeStart();
        this.f7629t.SetMapStatus(bundle);
    }

    public void setMapStatus(MapStatus mapStatus, boolean z) {
        if (!m18027b() || mapStatus == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("level", mapStatus.level);
        bundle.putDouble("rotation", mapStatus.rotation);
        bundle.putDouble("overlooking", mapStatus.overlooking);
        bundle.putDouble("centerptx", mapStatus.centerPtX);
        bundle.putDouble("centerpty", mapStatus.centerPtY);
        bundle.putDouble("centerptz", mapStatus.centerPtZ);
        bundle.putInt("left", mapStatus.winRound.left);
        bundle.putInt("right", mapStatus.winRound.right);
        bundle.putInt("top", mapStatus.winRound.top);
        bundle.putInt("bottom", mapStatus.winRound.bottom);
        bundle.putLong("gleft", mapStatus.geoRound.left);
        bundle.putLong("gbottom", mapStatus.geoRound.bottom);
        bundle.putLong("gtop", mapStatus.geoRound.top);
        bundle.putLong("gright", mapStatus.geoRound.right);
        bundle.putFloat("yoffset", mapStatus.yOffset);
        bundle.putFloat("xoffset", mapStatus.xOffset);
        bundle.putInt("animation", 0);
        bundle.putInt("animatime", 0);
        bundle.putInt("bfpp", mapStatus.bfpp ? 1 : 0);
        bundle.putString("panoid", mapStatus.panoId);
        bundle.putInt("autolink", z ? 1 : 0);
        bundle.putFloat("siangle", mapStatus.streetIndicateAngle);
        bundle.putInt("isbirdeye", mapStatus.isBirdEye ? 1 : 0);
        bundle.putInt("ssext", mapStatus.streetExt);
        this.f7629t.SetMapStatus(bundle);
    }

    public void setMapStatusWithAnimation(MapStatus mapStatus, int i) {
        if (!m18027b() || this.f7629t == null || mapStatus == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("level", mapStatus.level);
        bundle.putDouble("rotation", mapStatus.rotation);
        bundle.putDouble("overlooking", mapStatus.overlooking);
        bundle.putDouble("centerptx", mapStatus.centerPtX);
        bundle.putDouble("centerpty", mapStatus.centerPtY);
        bundle.putDouble("centerptz", mapStatus.centerPtZ);
        bundle.putInt("left", mapStatus.winRound.left);
        bundle.putInt("right", mapStatus.winRound.right);
        bundle.putInt("top", mapStatus.winRound.top);
        bundle.putInt("bottom", mapStatus.winRound.bottom);
        bundle.putLong("gleft", mapStatus.geoRound.left);
        bundle.putLong("gright", mapStatus.geoRound.right);
        bundle.putLong("gbottom", mapStatus.geoRound.bottom);
        bundle.putLong("gtop", mapStatus.geoRound.top);
        bundle.putFloat("xoffset", mapStatus.xOffset);
        bundle.putFloat("yoffset", mapStatus.yOffset);
        bundle.putInt("animation", 1);
        bundle.putInt("animatime", i);
        bundle.putInt("bfpp", mapStatus.bfpp ? 1 : 0);
        bundle.putString("panoid", mapStatus.panoId);
        bundle.putInt("autolink", 0);
        bundle.putFloat("siangle", mapStatus.streetIndicateAngle);
        bundle.putInt("isbirdeye", mapStatus.isBirdEye ? 1 : 0);
        bundle.putInt("ssext", mapStatus.streetExt);
        bundle.putFloat("roadOffsetX", mapStatus.roadOffsetX);
        bundle.putFloat("roadOffsetY", mapStatus.roadOffsetY);
        mapStatusChangeStart();
        this.mIsAnimating = true;
        this.f7629t.SetMapStatus(bundle);
    }

    public void setMapStatusWithAnimation(MapStatus mapStatus, int i, int i2) {
        if (!m18027b() || this.f7629t == null || mapStatus == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("animationType", i);
        bundle.putInt("animatime", i2);
        bundle.putInt("left", mapStatus.winRound.left);
        bundle.putInt("right", mapStatus.winRound.right);
        bundle.putInt("top", mapStatus.winRound.top);
        bundle.putInt("bottom", mapStatus.winRound.bottom);
        if (i != 4) {
            bundle.putDouble("level", mapStatus.level);
            bundle.putDouble("rotation", mapStatus.rotation);
            bundle.putDouble("overlooking", mapStatus.overlooking);
            bundle.putDouble("centerptx", mapStatus.centerPtX);
            bundle.putDouble("centerpty", mapStatus.centerPtY);
            bundle.putDouble("centerptz", mapStatus.centerPtZ);
            bundle.putLong("gleft", mapStatus.geoRound.left);
            bundle.putLong("gright", mapStatus.geoRound.right);
            bundle.putLong("gbottom", mapStatus.geoRound.bottom);
            bundle.putLong("gtop", mapStatus.geoRound.top);
            bundle.putFloat("xoffset", mapStatus.xOffset);
            bundle.putFloat("yoffset", mapStatus.yOffset);
            bundle.putInt("bfpp", mapStatus.bfpp ? 1 : 0);
            bundle.putString("panoid", mapStatus.panoId);
            bundle.putInt("autolink", 0);
            bundle.putFloat("siangle", mapStatus.streetIndicateAngle);
            bundle.putInt("isbirdeye", mapStatus.isBirdEye ? 1 : 0);
            bundle.putInt("ssext", mapStatus.streetExt);
            bundle.putFloat("roadOffsetX", mapStatus.roadOffsetX);
            bundle.putFloat("roadOffsetY", mapStatus.roadOffsetY);
        }
        this.f7629t.SetNewMapStatus(bundle);
    }

    public void setMapStatusWithAnimation(MapStatus mapStatus, int i, boolean z) {
        if (!m18027b() || this.f7629t == null || mapStatus == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("level", mapStatus.level);
        bundle.putDouble("rotation", mapStatus.rotation);
        bundle.putDouble("overlooking", mapStatus.overlooking);
        bundle.putDouble("centerptx", mapStatus.centerPtX);
        bundle.putDouble("centerpty", mapStatus.centerPtY);
        bundle.putDouble("centerptz", mapStatus.centerPtZ);
        bundle.putInt("left", mapStatus.winRound.left);
        bundle.putInt("right", mapStatus.winRound.right);
        bundle.putInt("top", mapStatus.winRound.top);
        bundle.putInt("bottom", mapStatus.winRound.bottom);
        bundle.putLong("gleft", mapStatus.geoRound.left);
        bundle.putLong("gright", mapStatus.geoRound.right);
        bundle.putLong("gbottom", mapStatus.geoRound.bottom);
        bundle.putLong("gtop", mapStatus.geoRound.top);
        bundle.putFloat("xoffset", mapStatus.xOffset);
        bundle.putFloat("yoffset", mapStatus.yOffset);
        bundle.putInt("animation", 1);
        bundle.putInt("animatime", i);
        bundle.putInt("bfpp", mapStatus.bfpp ? 1 : 0);
        bundle.putString("panoid", mapStatus.panoId);
        bundle.putInt("autolink", z ? 1 : 0);
        bundle.putFloat("siangle", mapStatus.streetIndicateAngle);
        bundle.putInt("isbirdeye", mapStatus.isBirdEye ? 1 : 0);
        bundle.putInt("ssext", mapStatus.streetExt);
        bundle.putFloat("roadOffsetX", mapStatus.roadOffsetX);
        bundle.putFloat("roadOffsetY", mapStatus.roadOffsetY);
        this.f7629t.SetMapStatus(bundle);
    }

    public boolean setMapTheme(int i, Bundle bundle) {
        if (m18027b()) {
            if (this.f7629t.getMapTheme() == i) {
                return true;
            }
            this.f7625p = i;
            return this.f7629t.setMapTheme(i, bundle);
        }
        return false;
    }

    public boolean setMapThemeScene(int i, int i2, Bundle bundle) {
        if (m18027b()) {
            if (this.f7629t.getMapTheme() == i && this.f7629t.getMapScene() == i2) {
                return true;
            }
            this.f7625p = i;
            this.f7624o = i2;
            return this.f7629t.setMapThemeScene(i, i2, bundle);
        }
        return false;
    }

    public void setMapViewInterface(MapViewInterface mapViewInterface) {
        this.f7619i = new SoftReference<>(mapViewInterface);
    }

    public void setMapViewListener(MapViewListener mapViewListener) {
        this.f7612b = mapViewListener;
    }

    public void setMapViewSurfaceListener(MapViewSurfaceListener mapViewSurfaceListener) {
        this.f7618h = mapViewSurfaceListener;
    }

    public void setMaxAndMinZoomLevel(float f, float f2) {
        this.mMaxZoomLevel = f;
        this.mMinZoomLevel = f2;
    }

    public void setNaviMapViewListener(NaviMapViewListener naviMapViewListener) {
        this.f7620j = naviMapViewListener;
    }

    public void setNetStatus(int i) {
        EngineMsgListener engineMsgListener = this.f7617g;
        if (engineMsgListener == null) {
            return;
        }
        if (i == 1) {
            engineMsgListener.onLongLinkConnect();
        } else if (i == 2 && this.f7608a != i) {
            engineMsgListener.onLongLinkDisConnect();
        }
        this.f7608a = i;
    }

    public void setOverlayMapCallBack(C3003af c3003af) {
        AppBaseMap appBaseMap;
        if (c3003af == null || (appBaseMap = this.f7629t) == null) {
            return;
        }
        appBaseMap.SetCallback(c3003af);
    }

    public void setOverlookGestureEnable(boolean z) {
        this.f7602T = z;
    }

    public void setRecommendPOIScene(RecommendPoiScene recommendPoiScene) {
        if (m18027b()) {
            this.f7629t.setRecommendPOIScene(recommendPoiScene.value);
        }
    }

    public void setScreenSize(int i, int i2) {
        this.f7632w = i;
        this.f7633x = i2;
    }

    public void setStreetArrowClickListener(InterfaceC3012an interfaceC3012an) {
        this.f7615e = interfaceC3012an;
    }

    public void setStreetArrowShow(boolean z) {
        if (m18027b()) {
            this.f7629t.SetStreetArrowShow(z);
        }
    }

    public void setStreetMarkerClickable(String str, boolean z) {
        if (m18027b()) {
            this.f7629t.SetStreetMarkerClickable(str, z);
        }
    }

    public void setStreetRoadClickable(boolean z) {
        if (m18027b()) {
            this.f7629t.SetStreetRoadClickable(z);
        }
    }

    public void setStyleMode(MapStyleMode mapStyleMode) {
        if (m18027b()) {
            this.f7629t.SetStyleMode(mapStyleMode.getMode());
        }
    }

    public void setTargetStreetCustomMarkerVisibility(boolean z, String str) {
        if (m18027b()) {
            this.f7629t.SetTargetStreetCustomMarkerVisibility(z, str);
        }
    }

    public void setTravelMode(boolean z) {
        this.f7626q = z;
    }

    public void setTwoTouchClickZoomEnabled(boolean z) {
        this.f7593J = z;
    }

    public void setUniversalFilter(String str) {
        if (m18027b()) {
            this.f7629t.setUniversalFilter(str);
        }
    }

    public void showBaseIndoorMap(boolean z) {
        if (m18027b()) {
            this.f7629t.ShowBaseIndoorMap(z);
        }
    }

    public void showStreetPOIMarker(boolean z) {
        if (m18027b()) {
            this.f7629t.ShowStreetPOIMarker(z);
        }
    }

    public void showUniversalLayer(Bundle bundle) {
        if (m18027b()) {
            this.f7629t.showUniversalLayer(bundle);
        }
    }

    public void startIndoorAnimation() {
        if (m18027b()) {
            this.f7629t.StartIndoorAnimation();
        }
    }

    public boolean switchBaseIndoorMapFloor(String str, String str2) {
        if (m18027b()) {
            return this.f7629t.SwitchBaseIndoorMapFloor(str, str2);
        }
        return false;
    }

    public void unInit() {
        AppBaseMap appBaseMap;
        m18016e();
        Handler handler = this.f7631v;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f7631v = null;
        }
        List<AppBaseMap> list = f7582W;
        if (list != null) {
            list.remove(this.f7629t);
        }
        List<InterfaceC3010al> list2 = this.mListeners;
        if (list2 != null) {
            list2.clear();
        }
        if (this.f7588E && (appBaseMap = this.f7629t) != null) {
            appBaseMap.Release();
            this.f7629t = null;
            this.f7588E = false;
        }
        if (this.f7616f != null) {
            this.f7616f = null;
        }
    }

    public void unInitForMultiTextureView() {
        AppBaseMap appBaseMap;
        if (!this.f7588E || (appBaseMap = this.f7629t) == null) {
            return;
        }
        appBaseMap.Release();
        this.f7629t = null;
        this.f7588E = false;
    }

    public void updateDrawFPS() {
        if (m18027b()) {
            this.f7629t.updateDrawFPS();
        }
    }

    public void updateOneOverlayItem(Bundle bundle) {
        this.f7629t.updateOneOverlayItem(bundle);
    }
}
