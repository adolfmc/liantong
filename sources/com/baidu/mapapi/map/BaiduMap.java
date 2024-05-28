package com.baidu.mapapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.track.TraceAnimationListener;
import com.baidu.mapapi.map.track.TraceOptions;
import com.baidu.mapapi.map.track.TraceOverlay;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.mapsdkplatform.comapi.map.C2925d;
import com.baidu.mapsdkplatform.comapi.map.C2946v;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.mapsdkplatform.comapi.map.EnumC2947w;
import com.baidu.mapsdkplatform.comapi.map.p145a.C2914c;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.MapSurfaceView;
import com.baidu.platform.comapi.map.MapTextureView;
import com.baidu.platform.comapi.map.OverlayLocationData;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.Danmaku;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaiduMap {
    public static final int MAP_TYPE_NONE = 3;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final float REAL_MAX_ZOOM_LEVEL = 21.0f;
    public static final float REAL_MIN_ZOOM_LEVEL = 4.0f;

    /* renamed from: e */
    private static final String f5927e = "BaiduMap";
    public static int mapStatusReason = 256;

    /* renamed from: B */
    private OnMarkerDragListener f5929B;

    /* renamed from: C */
    private OnMyLocationClickListener f5930C;

    /* renamed from: D */
    private SnapshotReadyCallback f5931D;

    /* renamed from: E */
    private OnMapDrawFrameCallback f5932E;

    /* renamed from: F */
    private OnBaseIndoorMapListener f5933F;

    /* renamed from: G */
    private OnMapRenderValidDataListener f5934G;

    /* renamed from: H */
    private OnHeatMapDrawFrameCallBack f5935H;

    /* renamed from: I */
    private OnSynchronizationListener f5936I;

    /* renamed from: J */
    private TileOverlay f5937J;

    /* renamed from: K */
    private HeatMap f5938K;

    /* renamed from: N */
    private Map<String, InfoWindow> f5941N;

    /* renamed from: O */
    private Map<InfoWindow, Marker> f5942O;

    /* renamed from: P */
    private Marker f5943P;

    /* renamed from: Q */
    private MyLocationData f5944Q;

    /* renamed from: R */
    private MyLocationConfiguration f5945R;

    /* renamed from: S */
    private boolean f5946S;

    /* renamed from: T */
    private boolean f5947T;

    /* renamed from: U */
    private boolean f5948U;

    /* renamed from: V */
    private boolean f5949V;

    /* renamed from: W */
    private Point f5950W;

    /* renamed from: Y */
    private C2914c f5952Y;

    /* renamed from: a */
    MapView f5953a;

    /* renamed from: b */
    TextureMapView f5954b;

    /* renamed from: c */
    WearMapView f5955c;

    /* renamed from: d */
    EnumC2947w f5956d;

    /* renamed from: f */
    private Projection f5957f;

    /* renamed from: g */
    private UiSettings f5958g;

    /* renamed from: h */
    private MapSurfaceView f5959h;

    /* renamed from: i */
    private MapTextureView f5960i;

    /* renamed from: j */
    private C2925d f5961j;

    /* renamed from: k */
    private List<Overlay> f5962k;

    /* renamed from: l */
    private List<Marker> f5963l;

    /* renamed from: m */
    private List<Marker> f5964m;

    /* renamed from: n */
    private List<InfoWindow> f5965n;

    /* renamed from: o */
    private Overlay.InterfaceC2746a f5966o;

    /* renamed from: p */
    private InfoWindow.InterfaceC2745a f5967p;

    /* renamed from: q */
    private OnMapStatusChangeListener f5968q;

    /* renamed from: r */
    private OnMapTouchListener f5969r;

    /* renamed from: s */
    private onMapGestureListener f5970s;

    /* renamed from: t */
    private OnMapClickListener f5971t;

    /* renamed from: u */
    private OnMapLoadedCallback f5972u;

    /* renamed from: v */
    private OnMapRenderCallback f5973v;

    /* renamed from: w */
    private OnMapDoubleClickListener f5974w;

    /* renamed from: x */
    private OnMapLongClickListener f5975x;

    /* renamed from: y */
    private CopyOnWriteArrayList<OnMarkerClickListener> f5976y = new CopyOnWriteArrayList<>();

    /* renamed from: z */
    private CopyOnWriteArrayList<OnPolylineClickListener> f5977z = new CopyOnWriteArrayList<>();

    /* renamed from: A */
    private CopyOnWriteArrayList<OnMultiPointClickListener> f5928A = new CopyOnWriteArrayList<>();

    /* renamed from: L */
    private Lock f5939L = new ReentrantLock();

    /* renamed from: M */
    private Lock f5940M = new ReentrantLock();

    /* renamed from: X */
    private volatile boolean f5951X = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnBaseIndoorMapListener {
        void onBaseIndoorMapMode(boolean z, MapBaseIndoorMapInfo mapBaseIndoorMapInfo);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnHeatMapDrawFrameCallBack {
        void frameIndex(int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);

        void onMapPoiClick(MapPoi mapPoi);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapDoubleClickListener {
        void onMapDoubleClick(LatLng latLng);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapDrawFrameCallback {
        void onMapDrawFrame(MapStatus mapStatus);

        @Deprecated
        void onMapDrawFrame(GL10 gl10, MapStatus mapStatus);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapRenderCallback {
        void onMapRenderFinished();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapRenderValidDataListener {
        void onMapRenderValidData(boolean z, int i, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapStatusChangeListener {
        public static final int REASON_API_ANIMATION = 2;
        public static final int REASON_DEVELOPER_ANIMATION = 3;
        public static final int REASON_GESTURE = 1;

        void onMapStatusChange(MapStatus mapStatus);

        void onMapStatusChangeFinish(MapStatus mapStatus);

        void onMapStatusChangeStart(MapStatus mapStatus);

        void onMapStatusChangeStart(MapStatus mapStatus, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMapTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMultiPointClickListener {
        boolean onMultiPointClick(MultiPoint multiPoint, MultiPointItem multiPointItem);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMyLocationClickListener {
        boolean onMyLocationClick();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnPolylineClickListener {
        boolean onPolylineClick(Polyline polyline);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnSynchronizationListener {
        void onMapStatusChangeReason(int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface onMapGestureListener {
        boolean onMapDoubleTouch(Point point, MapStatus mapStatus);

        boolean onMapFling(MotionEvent motionEvent, float f, float f2, MapStatus mapStatus);

        boolean onMapKneading(Point point, Point point2, MapStatus mapStatus);

        boolean onMapOverLooking(Point point, Point point2, MapStatus mapStatus);

        boolean onMapScroll(Point point, Point point2, MapStatus mapStatus);

        void onMapStatusChangeFinish(MapStatus mapStatus);

        boolean onMapTwoClick(Point point, Point point2, MapStatus mapStatus);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaiduMap(Context context, MapSurfaceView mapSurfaceView, C2946v c2946v) {
        this.f5959h = mapSurfaceView;
        this.f5961j = new C2925d(context, mapSurfaceView, c2946v, (String) null, 0);
        mapSurfaceView.setBaseMap(this.f5961j);
        this.f5956d = EnumC2947w.GLSurfaceView;
        m19003d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaiduMap(Context context, MapTextureView mapTextureView, C2946v c2946v) {
        this.f5960i = mapTextureView;
        this.f5961j = new C2925d(context, mapTextureView, c2946v, (String) null, 0);
        mapTextureView.setBaseMap(this.f5961j);
        this.f5956d = EnumC2947w.TextureView;
        m19003d();
    }

    /* renamed from: a */
    private Point m19009a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        int i2 = 0;
        for (String str2 : str.replaceAll("^\\{", "").replaceAll("\\}$", "").split(",")) {
            String[] split = str2.replaceAll("\"", "").split(":");
            if ("x".equals(split[0])) {
                i = Integer.valueOf(split[1]).intValue();
            }
            if ("y".equals(split[0])) {
                i2 = Integer.valueOf(split[1]).intValue();
            }
        }
        return new Point(i, i2);
    }

    /* renamed from: a */
    private C2948x m19012a(MapStatusUpdate mapStatusUpdate) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return null;
        }
        C2948x m18378E = c2925d.m18378E();
        MapStatus m18919a = mapStatusUpdate.m18919a(this.f5961j, getMapStatus());
        if (m18919a == null) {
            return null;
        }
        return m18919a.m18928b(m18378E);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m19021a(int i) {
        if (i != 0) {
            switch (i) {
                case 1004:
                    return "网络连接错误";
                case 1005:
                    return "请求发送错误";
                case 1006:
                    return "响应数据读取失败";
                case 1007:
                    return "返回响应数据过大，数据溢出";
                case 1008:
                    return "当前网络类型有问题";
                case 1009:
                    return "数据不一致";
                case 1010:
                    return "请求取消";
                case 1011:
                    return "网络超时错误";
                case 1012:
                    return "网络连接超时";
                case 1013:
                    return "网络发送超时";
                case 1014:
                    return "网络接收超时";
                case 1015:
                    return "DNS解析错误";
                case 1016:
                    return "DNS解析超时";
                case 1017:
                    return "网络写错误";
                case 1018:
                    return "SSL握手错误";
                case 1019:
                    return "SSL握手超时";
                default:
                    return "";
            }
        }
        return "数据请求成功";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m19013a(com.baidu.mapapi.map.InfoWindow r8) {
        /*
            r7 = this;
            if (r8 == 0) goto Lb5
            boolean r0 = r7.f5951X
            if (r0 == 0) goto L8
            goto Lb5
        L8:
            java.util.Map<com.baidu.mapapi.map.InfoWindow, com.baidu.mapapi.map.Marker> r0 = r7.f5942O
            java.util.Set r0 = r0.keySet()
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 != 0) goto Lb2
            boolean r0 = r0.contains(r8)
            if (r0 != 0) goto L1d
            goto Lb2
        L1d:
            android.view.View r0 = r8.f6116c
            r1 = 1
            if (r0 == 0) goto L6a
            boolean r3 = r8.f6124k
            if (r3 == 0) goto L6a
            r0.destroyDrawingCache()
            com.baidu.mapapi.map.MapViewLayoutParams$Builder r3 = new com.baidu.mapapi.map.MapViewLayoutParams$Builder
            r3.<init>()
            com.baidu.mapapi.map.MapViewLayoutParams$ELayoutMode r4 = com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode.mapMode
            com.baidu.mapapi.map.MapViewLayoutParams$Builder r3 = r3.layoutMode(r4)
            com.baidu.mapapi.model.LatLng r4 = r8.f6117d
            com.baidu.mapapi.map.MapViewLayoutParams$Builder r3 = r3.position(r4)
            int r4 = r8.f6120g
            com.baidu.mapapi.map.MapViewLayoutParams$Builder r3 = r3.yOffset(r4)
            com.baidu.mapapi.map.MapViewLayoutParams r3 = r3.build()
            int[] r4 = com.baidu.mapapi.map.C2760b.f6540b
            com.baidu.mapsdkplatform.comapi.map.w r5 = r7.f5956d
            int r5 = r5.ordinal()
            r4 = r4[r5]
            switch(r4) {
                case 1: goto L5a;
                case 2: goto L52;
                default: goto L51;
            }
        L51:
            goto L64
        L52:
            com.baidu.mapapi.map.MapView r4 = r7.f5953a
            if (r4 == 0) goto L64
            r4.addView(r0, r3)
            goto L64
        L5a:
            com.baidu.mapapi.map.TextureMapView r4 = r7.f5954b
            if (r4 == 0) goto L64
            r4.addView(r0, r3)
            r0.setLayoutParams(r3)
        L64:
            boolean r0 = r8.f6123j
            if (r0 == 0) goto L6a
            r0 = r2
            goto L6b
        L6a:
            r0 = r1
        L6b:
            com.baidu.mapapi.map.BitmapDescriptor r3 = r7.m19006b(r8)
            java.util.Map<com.baidu.mapapi.map.InfoWindow, com.baidu.mapapi.map.Marker> r4 = r7.f5942O
            java.lang.Object r4 = r4.get(r8)
            com.baidu.mapapi.map.Marker r4 = (com.baidu.mapapi.map.Marker) r4
            if (r4 == 0) goto Lb1
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            com.baidu.mapapi.map.BitmapDescriptor r6 = r8.f6115b
            if (r6 == 0) goto L97
            com.baidu.mapsdkplatform.comapi.map.i r6 = com.baidu.mapsdkplatform.comapi.map.EnumC2933i.popup
            r4.type = r6
            r4.f6220b = r3
            android.view.View r3 = r8.f6116c
            if (r3 == 0) goto L92
            java.lang.String r2 = "draw_with_view"
            r5.putInt(r2, r1)
            goto L97
        L92:
            java.lang.String r1 = "draw_with_view"
            r5.putInt(r1, r2)
        L97:
            com.baidu.mapapi.model.LatLng r1 = r8.f6117d
            r4.f6219a = r1
            int r8 = r8.f6120g
            r4.f6227i = r8
            r4.mo18867a(r5)
            com.baidu.mapsdkplatform.comapi.map.d r8 = r7.f5961j
            if (r8 == 0) goto Lb1
            if (r0 == 0) goto Lb1
            boolean r8 = r7.f5951X
            if (r8 != 0) goto Lb1
            com.baidu.mapsdkplatform.comapi.map.d r8 = r7.f5961j
            r8.m18312e(r5)
        Lb1:
            return
        Lb2:
            r7.showInfoWindow(r8, r2)
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.BaiduMap.m19013a(com.baidu.mapapi.map.InfoWindow):void");
    }

    /* renamed from: a */
    private final void m19011a(MyLocationData myLocationData, MyLocationConfiguration myLocationConfiguration) {
        MapStatus.Builder zoom;
        float f;
        int i;
        String str;
        if (myLocationData == null || myLocationConfiguration == null || !isMyLocationEnabled()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        LatLng latLng = new LatLng(myLocationData.latitude, myLocationData.longitude);
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        try {
            jSONObject.put("type", 0);
            jSONObject2.put("ptx", ll2mc.getLongitudeE6());
            jSONObject2.put("pty", ll2mc.getLatitudeE6());
            jSONObject2.put("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius(latLng, (int) myLocationData.accuracy));
            float f2 = myLocationData.direction;
            if (myLocationConfiguration.enableDirection) {
                f = myLocationData.direction % 360.0f;
                if (f > 180.0f) {
                    f -= 360.0f;
                } else if (f < -180.0f) {
                    f += 360.0f;
                }
            } else {
                f = -1001.0f;
            }
            jSONObject2.put("direction", f);
            jSONObject2.put("iconarrownor", "NormalLocArrow");
            if ("BaiduMapSDK_map_v".equals("BaiduMapSDK_map_for_navi_v")) {
                jSONObject2.put("iconarrownorid", 776);
                jSONObject2.put("iconarrowfocid", 777);
            } else {
                jSONObject2.put("iconarrownorid", 28);
                jSONObject2.put("iconarrowfocid", 29);
            }
            jSONObject2.put("iconarrowfoc", "FocusLocArrow");
            jSONObject2.put("lineid", myLocationConfiguration.accuracyCircleStrokeColor);
            jSONObject2.put("areaid", myLocationConfiguration.accuracyCircleFillColor);
            jSONArray.put(jSONObject2);
            if (myLocationConfiguration.locationMode == MyLocationConfiguration.LocationMode.COMPASS) {
                jSONObject3.put("ptx", ll2mc.getLongitudeE6());
                jSONObject3.put("pty", ll2mc.getLatitudeE6());
                jSONObject3.put("radius", 0);
                jSONObject3.put("direction", 0);
                jSONObject3.put("iconarrownor", "direction_wheel");
                jSONObject3.put("iconarrowfoc", "direction_wheel");
                if ("BaiduMapSDK_map_v".equals("BaiduMapSDK_map_for_navi_v")) {
                    i = 339;
                    jSONObject3.put("iconarrownorid", 339);
                    str = "iconarrowfocid";
                } else {
                    i = 54;
                    jSONObject3.put("iconarrownorid", 54);
                    str = "iconarrowfocid";
                }
                jSONObject3.put(str, i);
                jSONArray.put(jSONObject3);
            }
            jSONObject.put("data", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (myLocationConfiguration.customMarker != null) {
            Bitmap bitmap = myLocationConfiguration.customMarker.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                ArrayList arrayList = new ArrayList();
                OverlayLocationData overlayLocationData = new OverlayLocationData();
                overlayLocationData.setImage(bitmap);
                overlayLocationData.setImgHeight(bitmap.getHeight());
                overlayLocationData.setImgWidth(bitmap.getWidth());
                overlayLocationData.setImgName("icon");
                overlayLocationData.setRotation(myLocationConfiguration.enableDirection ? 1 : 0);
                arrayList.add(overlayLocationData);
                C2925d c2925d = this.f5961j;
                if (c2925d != null) {
                    c2925d.m18330a(arrayList);
                }
            }
        } else {
            C2925d c2925d2 = this.f5961j;
            if (c2925d2 != null) {
                c2925d2.m18275u();
            }
        }
        C2925d c2925d3 = this.f5961j;
        if (c2925d3 != null) {
            c2925d3.m18333a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), (Bundle) null);
        }
        switch (C2760b.f6539a[myLocationConfiguration.locationMode.ordinal()]) {
            case 1:
                zoom = new MapStatus.Builder().rotate(myLocationData.direction).overlook(-45.0f).target(new LatLng(myLocationData.latitude, myLocationData.longitude)).targetScreen(getMapStatus().targetScreen).zoom(getMapStatus().zoom);
                break;
            case 2:
                zoom = new MapStatus.Builder().target(new LatLng(myLocationData.latitude, myLocationData.longitude)).zoom(getMapStatus().zoom).rotate(getMapStatus().rotate).overlook(getMapStatus().overlook).targetScreen(getMapStatus().targetScreen);
                break;
            case 3:
            default:
                return;
        }
        animateMapStatus(MapStatusUpdateFactory.newMapStatus(zoom.build()));
    }

    /* renamed from: b */
    private BitmapDescriptor m19006b(InfoWindow infoWindow) {
        if (infoWindow.f6116c == null || !infoWindow.f6124k) {
            return infoWindow.f6115b;
        }
        if (infoWindow.f6121h) {
            if (infoWindow.f6122i <= 0) {
                infoWindow.f6122i = SysOSUtil.getDensityDpi();
            }
            return BitmapDescriptorFactory.fromViewWithDpi(infoWindow.f6116c, infoWindow.f6122i);
        }
        return BitmapDescriptorFactory.fromView(infoWindow.f6116c);
    }

    /* renamed from: d */
    private void m19003d() {
        this.f5951X = false;
        this.f5962k = new CopyOnWriteArrayList();
        this.f5963l = new CopyOnWriteArrayList();
        this.f5964m = new CopyOnWriteArrayList();
        this.f5941N = new ConcurrentHashMap();
        this.f5942O = new ConcurrentHashMap();
        this.f5965n = new CopyOnWriteArrayList();
        this.f5950W = new Point((int) (SysOSUtil.getDensity() * 40.0f), (int) (SysOSUtil.getDensity() * 40.0f));
        this.f5958g = new UiSettings(this.f5961j);
        this.f5966o = new C2748a(this);
        this.f5967p = new C2761c(this);
        this.f5961j.m18335a(new C2762d(this));
        this.f5961j.m18342a(new C2764f(this));
        this.f5961j.m18344a(new C2765g(this));
        this.f5946S = this.f5961j.m18382C();
        this.f5947T = this.f5961j.m18380D();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m19022a() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18271w();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m19014a(HeatMap heatMap) {
        this.f5939L.lock();
        try {
            if (this.f5938K != null && this.f5961j != null && heatMap == this.f5938K) {
                this.f5938K.m18950b();
                this.f5938K.m18947c();
                this.f5938K.f6073a = null;
                this.f5961j.m18283q();
                this.f5938K = null;
                this.f5961j.m18280r(false);
            }
        } finally {
            this.f5939L.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m19010a(TileOverlay tileOverlay) {
        this.f5940M.lock();
        if (tileOverlay != null) {
            try {
                if (this.f5937J == tileOverlay) {
                    tileOverlay.m18837b();
                    tileOverlay.f6462a = null;
                    if (this.f5961j != null) {
                        this.f5961j.m18313e();
                    }
                }
            } finally {
                this.f5937J = null;
                this.f5940M.unlock();
            }
        }
    }

    public void addHeatMap(HeatMap heatMap) {
        if (heatMap == null || this.f5961j == null) {
            return;
        }
        this.f5939L.lock();
        try {
            if (heatMap == this.f5938K) {
                return;
            }
            if (this.f5938K != null) {
                this.f5938K.m18950b();
                this.f5938K.m18947c();
                this.f5938K.f6073a = null;
                this.f5961j.m18283q();
            }
            this.f5938K = heatMap;
            this.f5938K.f6073a = this;
            Bundle bundle = heatMap.toBundle();
            this.f5961j.m18280r(true);
            this.f5961j.m18303h(bundle);
        } finally {
            this.f5939L.unlock();
        }
    }

    public final Overlay addOverlay(OverlayOptions overlayOptions) {
        if (overlayOptions == null || this.f5951X) {
            return null;
        }
        Overlay mo18866a = overlayOptions.mo18866a();
        mo18866a.listener = this.f5966o;
        if (mo18866a instanceof Marker) {
            Marker marker = (Marker) mo18866a;
            marker.f6214A = this.f5967p;
            if (marker.f6237s != null && marker.f6237s.size() != 0) {
                this.f5963l.add(marker);
                C2925d c2925d = this.f5961j;
                if (c2925d != null) {
                    c2925d.m18317c(true);
                }
            }
            this.f5964m.add(marker);
            if (marker.f6244z != null) {
                showInfoWindow(marker.f6244z, false);
            }
        }
        Bundle bundle = new Bundle();
        mo18866a.mo18867a(bundle);
        if (this.f5961j != null && !this.f5951X) {
            this.f5961j.m18318c(bundle);
        }
        this.f5962k.add(mo18866a);
        return mo18866a;
    }

    public final List<Overlay> addOverlays(List<OverlayOptions> list) {
        if (list == null || this.f5951X) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i = size / 400;
        int i2 = 0;
        while (i2 < i + 1) {
            Bundle[] bundleArr = new Bundle[i == 0 ? size : i2 == i ? size - (i * 400) : 400];
            for (int i3 = 0; i3 < 400; i3++) {
                int i4 = (i2 * 400) + i3;
                if (i4 >= size) {
                    break;
                } else if (this.f5951X) {
                    return null;
                } else {
                    OverlayOptions overlayOptions = list.get(i4);
                    if (overlayOptions != null) {
                        Bundle bundle = new Bundle();
                        Overlay mo18866a = overlayOptions.mo18866a();
                        mo18866a.listener = this.f5966o;
                        if (mo18866a instanceof Marker) {
                            Marker marker = (Marker) mo18866a;
                            marker.f6214A = this.f5967p;
                            if (marker.f6237s != null && marker.f6237s.size() != 0) {
                                this.f5963l.add(marker);
                                C2925d c2925d = this.f5961j;
                                if (c2925d != null) {
                                    c2925d.m18317c(true);
                                }
                            }
                            this.f5964m.add(marker);
                        }
                        this.f5962k.add(mo18866a);
                        arrayList.add(mo18866a);
                        mo18866a.mo18867a(bundle);
                        C2925d c2925d2 = this.f5961j;
                        if (c2925d2 != null) {
                            c2925d2.m18306g(bundle);
                            this.f5961j.m18315d(bundle);
                        }
                        bundleArr[i3] = bundle;
                    }
                }
            }
            C2925d c2925d3 = this.f5961j;
            if (c2925d3 != null) {
                c2925d3.m18328a(bundleArr);
            }
            i2++;
        }
        return arrayList;
    }

    public TileOverlay addTileLayer(TileOverlayOptions tileOverlayOptions) {
        if (tileOverlayOptions == null) {
            return null;
        }
        TileOverlay tileOverlay = this.f5937J;
        if (tileOverlay != null) {
            tileOverlay.m18837b();
            this.f5937J.f6462a = null;
        }
        C2925d c2925d = this.f5961j;
        if (c2925d == null || !c2925d.m18351a(tileOverlayOptions.m18832a())) {
            return null;
        }
        TileOverlay m18830a = tileOverlayOptions.m18830a(this);
        this.f5937J = m18830a;
        return m18830a;
    }

    public final TraceOverlay addTraceOverlay(TraceOptions traceOptions, TraceAnimationListener traceAnimationListener) {
        C2914c c2914c;
        if (traceOptions == null) {
            return null;
        }
        C2914c c2914c2 = this.f5952Y;
        if (c2914c2 == null || c2914c2.m18403d()) {
            if (this.f5956d == EnumC2947w.GLSurfaceView) {
                c2914c = new C2914c(this.f5959h);
            } else if (this.f5956d != EnumC2947w.TextureView) {
                return null;
            } else {
                c2914c = new C2914c(this.f5960i);
            }
            this.f5952Y = c2914c;
            this.f5952Y.m18417a();
        }
        this.f5952Y.m18416a(traceAnimationListener);
        return this.f5952Y.m18415a(traceOptions);
    }

    public final void animateMapStatus(MapStatusUpdate mapStatusUpdate) {
        animateMapStatus(mapStatusUpdate, 300);
    }

    public final void animateMapStatus(MapStatusUpdate mapStatusUpdate, int i) {
        if (mapStatusUpdate == null || i <= 0) {
            return;
        }
        C2948x m19012a = m19012a(mapStatusUpdate);
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        mapStatusReason |= 256;
        if (this.f5949V) {
            c2925d.m18339a(m19012a, i);
        } else {
            c2925d.m18340a(m19012a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m19008b() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return false;
        }
        return c2925d.m18310f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m19005c() {
        this.f5951X = true;
        C2914c c2914c = this.f5952Y;
        if (c2914c != null) {
            c2914c.m18407c();
            this.f5952Y = null;
        }
        hideInfoWindow();
    }

    public void changeLocationLayerOrder(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18308f(z);
    }

    public void cleanCache(int i) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18357a(i);
    }

    public final void clear() {
        if (this.f5951X) {
            return;
        }
        this.f5962k.clear();
        this.f5963l.clear();
        this.f5964m.clear();
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18317c(false);
            this.f5961j.m18289n();
        }
        hideInfoWindow();
    }

    public List<InfoWindow> getAllInfoWindows() {
        return this.f5965n;
    }

    public final Point getCompassPosition() {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            return m19009a(c2925d.m18304h());
        }
        return null;
    }

    public MapBaseIndoorMapInfo getFocusedBaseIndoorMapInfo() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return null;
        }
        return c2925d.m18281r();
    }

    public final int getFontSizeLevel() {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18375H();
            return 1;
        }
        return 1;
    }

    public MapSurfaceView getGLMapView() {
        return this.f5959h;
    }

    public OnHeatMapDrawFrameCallBack getHeatMapDrawFrameCallBack() {
        return this.f5935H;
    }

    @Deprecated
    public final MyLocationConfiguration getLocationConfigeration() {
        return getLocationConfiguration();
    }

    public final MyLocationConfiguration getLocationConfiguration() {
        return this.f5945R;
    }

    public final MyLocationData getLocationData() {
        return this.f5944Q;
    }

    public final String getMapApprovalNumber() {
        C2925d c2925d = this.f5961j;
        return c2925d == null ? "" : c2925d.m18367P();
    }

    public final String getMapCopyrightInfo() {
        C2925d c2925d = this.f5961j;
        return c2925d == null ? "" : c2925d.m18365R();
    }

    public MapLanguage getMapLanguage() {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            return MapLanguage.values()[c2925d.m18364S()];
        }
        return MapLanguage.CHINESE;
    }

    public final String getMapMappingQualificationInfo() {
        C2925d c2925d = this.f5961j;
        return c2925d == null ? "" : c2925d.m18366Q();
    }

    public final MapStatus getMapStatus() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return null;
        }
        return MapStatus.m18930a(c2925d.m18378E());
    }

    public final LatLngBounds getMapStatusLimit() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return null;
        }
        return c2925d.m18377F();
    }

    public MapTextureView getMapTextureView() {
        return this.f5960i;
    }

    public final int getMapType() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return 1;
        }
        if (c2925d.m18293l()) {
            return this.f5961j.m18295k() ? 2 : 1;
        }
        return 3;
    }

    public List<Marker> getMarkersInBounds(LatLngBounds latLngBounds) {
        if (getMapStatus() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (this.f5964m.size() == 0) {
            return null;
        }
        for (Marker marker : this.f5964m) {
            if (latLngBounds.contains(marker.getPosition())) {
                arrayList.add(marker);
            }
        }
        return arrayList;
    }

    public final float getMaxZoomLevel() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return 0.0f;
        }
        return c2925d.m18327b();
    }

    public final float getMinZoomLevel() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return 0.0f;
        }
        return c2925d.f7272b;
    }

    public LatLngBounds getOverlayLatLngBounds(OverlayOptions overlayOptions) {
        if (overlayOptions == null || this.f5961j == null) {
            return null;
        }
        Overlay mo18866a = overlayOptions.mo18866a();
        Bundle bundle = new Bundle();
        mo18866a.mo18867a(bundle);
        return this.f5961j.m18324b(bundle);
    }

    public final Projection getProjection() {
        return this.f5957f;
    }

    public float[] getProjectionMatrix() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return null;
        }
        return c2925d.m18369N();
    }

    public final UiSettings getUiSettings() {
        return this.f5958g;
    }

    public float[] getViewMatrix() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return null;
        }
        return c2925d.m18368O();
    }

    public float getZoomToBound(int i, int i2, int i3, int i4, int i5, int i6) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return 0.0f;
        }
        return c2925d.m18355a(i, i2, i3, i4, i5, i6);
    }

    @Deprecated
    public MapSurfaceView getmGLMapView() {
        return this.f5959h;
    }

    public void hideInfoWindow() {
        View view;
        Collection<InfoWindow> values = this.f5941N.values();
        if (!values.isEmpty()) {
            for (InfoWindow infoWindow : values) {
                if (infoWindow != null && (view = infoWindow.f6116c) != null) {
                    switch (C2760b.f6540b[this.f5956d.ordinal()]) {
                        case 1:
                            TextureMapView textureMapView = this.f5954b;
                            if (textureMapView != null) {
                                textureMapView.removeView(view);
                                break;
                            } else {
                                continue;
                            }
                        case 2:
                            MapView mapView = this.f5953a;
                            if (mapView != null) {
                                mapView.removeView(view);
                                break;
                            } else {
                                continue;
                            }
                    }
                }
            }
        }
        for (Overlay overlay : this.f5962k) {
            Set<String> keySet = this.f5941N.keySet();
            String str = overlay.f6297F;
            if ((overlay instanceof Marker) && !keySet.isEmpty() && keySet.contains(str)) {
                overlay.remove();
            }
        }
        this.f5941N.clear();
        this.f5942O.clear();
        this.f5965n.clear();
    }

    public void hideInfoWindow(InfoWindow infoWindow) {
        Set<InfoWindow> keySet = this.f5942O.keySet();
        if (infoWindow == null || keySet.isEmpty() || !keySet.contains(infoWindow)) {
            return;
        }
        View view = infoWindow.f6116c;
        if (view != null) {
            switch (C2760b.f6540b[this.f5956d.ordinal()]) {
                case 1:
                    TextureMapView textureMapView = this.f5954b;
                    if (textureMapView != null) {
                        textureMapView.removeView(view);
                        break;
                    }
                    break;
                case 2:
                    MapView mapView = this.f5953a;
                    if (mapView != null) {
                        mapView.removeView(view);
                        break;
                    }
                    break;
            }
        }
        Marker marker = this.f5942O.get(infoWindow);
        if (marker != null) {
            marker.remove();
            this.f5941N.remove(marker.f6297F);
        }
        this.f5942O.remove(infoWindow);
        this.f5965n.remove(infoWindow);
    }

    public void hideSDKLayer() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18320c();
    }

    public final boolean isBaiduHeatMapEnabled() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return false;
        }
        return c2925d.m18301i();
    }

    public boolean isBaseIndoorMapMode() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return false;
        }
        return c2925d.m18279s();
    }

    public final boolean isBuildingsEnabled() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return false;
        }
        return c2925d.m18291m();
    }

    public final boolean isMyLocationEnabled() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return false;
        }
        return c2925d.m18273v();
    }

    public final boolean isShowMapPoi() {
        return this.f5946S;
    }

    public final boolean isSupportBaiduHeatMap() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return false;
        }
        return c2925d.m18298j();
    }

    public final boolean isTrafficEnabled() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return false;
        }
        return c2925d.m18307g();
    }

    public final void removeMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        if (this.f5976y.contains(onMarkerClickListener)) {
            this.f5976y.remove(onMarkerClickListener);
        }
    }

    public void removeOverLays(List<Overlay> list) {
        C2925d c2925d;
        if (list == null || this.f5951X) {
            return;
        }
        int size = list.size();
        int i = size / 400;
        int i2 = 0;
        while (i2 < i + 1) {
            Bundle[] bundleArr = new Bundle[i == 0 ? size : i2 == i ? size - (i * 400) : 400];
            for (int i3 = 0; i3 < 400; i3++) {
                int i4 = (i2 * 400) + i3;
                if (i4 >= size) {
                    break;
                } else if (this.f5951X) {
                    return;
                } else {
                    Overlay overlay = list.get(i4);
                    if (overlay != null) {
                        Bundle mo18868a = overlay.mo18868a();
                        C2925d c2925d2 = this.f5961j;
                        if (c2925d2 != null) {
                            c2925d2.m18306g(mo18868a);
                        }
                        bundleArr[i3] = mo18868a;
                        List<Marker> list2 = this.f5964m;
                        if (list2 != null && list2.contains(overlay)) {
                            this.f5964m.remove(overlay);
                        }
                        if (this.f5963l.contains(overlay)) {
                            Marker marker = (Marker) overlay;
                            if (marker.f6237s != null) {
                                this.f5963l.remove(marker);
                                if (this.f5963l.size() == 0 && (c2925d = this.f5961j) != null) {
                                    c2925d.m18317c(false);
                                }
                            }
                        }
                    }
                }
            }
            C2925d c2925d3 = this.f5961j;
            if (c2925d3 != null) {
                c2925d3.m18321b(bundleArr);
            }
            i2++;
        }
        this.f5962k.removeAll(list);
    }

    public final void setBaiduHeatMapEnabled(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18294k(z);
        }
    }

    public final void setBuildingsEnabled(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18290m(z);
        }
    }

    public void setCompassEnable(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18302h(z);
    }

    public void setCompassIcon(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("BDMapSDKException: compass's icon can not be null");
        }
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18353a(bitmap);
    }

    public void setCompassPosition(Point point) {
        if (this.f5961j == null) {
            return;
        }
        if (this.f5961j.m18352a(new Point(point.x, point.y))) {
            this.f5950W = point;
        }
    }

    @Deprecated
    public boolean setCustomTrafficColor(String str, String str2, String str3, String str4) {
        if (this.f5961j == null) {
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4)) {
                this.f5961j.m18354a(Color.parseColor(Danmaku.COLOR_WHITE), Color.parseColor(Danmaku.COLOR_WHITE), Color.parseColor(Danmaku.COLOR_WHITE), Color.parseColor(Danmaku.COLOR_WHITE), false);
                return true;
            }
            return true;
        } else if (str.matches("^#[0-9a-fA-F]{8}$") && str2.matches("^#[0-9a-fA-F]{8}$") && str3.matches("^#[0-9a-fA-F]{8}$") && str4.matches("^#[0-9a-fA-F]{8}$")) {
            this.f5961j.m18354a(Color.parseColor(str), Color.parseColor(str2), Color.parseColor(str3), Color.parseColor(str4), true);
            return true;
        } else {
            Log.e(f5927e, "the string of the input customTrafficColor is error");
            return false;
        }
    }

    public void setDEMEnable(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18322b(z);
        }
    }

    public final void setFontSizeLevel(int i) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18319c(i);
        }
    }

    public void setHeatMapFrameAnimationIndex(int i) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18326b(i);
    }

    public final void setIndoorEnable(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            this.f5948U = z;
            c2925d.m18286o(z);
        }
        OnBaseIndoorMapListener onBaseIndoorMapListener = this.f5933F;
        if (onBaseIndoorMapListener == null || z) {
            return;
        }
        onBaseIndoorMapListener.onBaseIndoorMapMode(false, null);
    }

    public void setLayerClickable(MapLayer mapLayer, boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18347a(mapLayer, z);
    }

    public final void setMapLanguage(MapLanguage mapLanguage) {
    }

    public final void setMapStatus(MapStatusUpdate mapStatusUpdate) {
        if (mapStatusUpdate == null) {
            return;
        }
        C2948x m19012a = m19012a(mapStatusUpdate);
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        mapStatusReason |= 256;
        c2925d.m18383B(true);
        this.f5961j.m18340a(m19012a);
    }

    public final void setMapStatusLimits(LatLngBounds latLngBounds) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18345a(latLngBounds);
        setMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBounds));
    }

    public final void setMapType(int i) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        switch (i) {
            case 1:
                c2925d.m18329a(false);
                this.f5961j.m18381C(this.f5946S);
                this.f5961j.m18379D(this.f5947T);
                this.f5961j.m18296j(true);
                this.f5961j.m18286o(this.f5948U);
                break;
            case 2:
                c2925d.m18329a(true);
                this.f5961j.m18381C(this.f5946S);
                this.f5961j.m18379D(this.f5947T);
                this.f5961j.m18296j(true);
                break;
            case 3:
                if (c2925d.m18382C()) {
                    this.f5961j.m18381C(false);
                }
                if (this.f5961j.m18380D()) {
                    this.f5961j.m18379D(false);
                }
                this.f5961j.m18296j(false);
                this.f5961j.m18286o(false);
                break;
        }
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b m18459a = C2898b.m18459a();
            m18459a.m18457a("BasicMap setMapType type = " + i);
        }
    }

    public final void setMaxAndMinZoomLevel(float f, float f2) {
        C2925d c2925d;
        if (f <= 21.0f && f2 >= 4.0f && f >= f2 && (c2925d = this.f5961j) != null) {
            c2925d.m18358a(f, f2);
        }
    }

    @Deprecated
    public final void setMyLocationConfigeration(MyLocationConfiguration myLocationConfiguration) {
        setMyLocationConfiguration(myLocationConfiguration);
    }

    public final void setMyLocationConfiguration(MyLocationConfiguration myLocationConfiguration) {
        this.f5945R = myLocationConfiguration;
        m19011a(this.f5944Q, this.f5945R);
    }

    public final void setMyLocationData(MyLocationData myLocationData) {
        this.f5944Q = myLocationData;
        if (this.f5945R == null) {
            this.f5945R = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, null);
        }
        m19011a(myLocationData, this.f5945R);
    }

    public final void setMyLocationEnabled(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18282q(z);
        }
    }

    public final void setOnBaseIndoorMapListener(OnBaseIndoorMapListener onBaseIndoorMapListener) {
        this.f5933F = onBaseIndoorMapListener;
    }

    public void setOnHeatMapDrawFrameCallBack(OnHeatMapDrawFrameCallBack onHeatMapDrawFrameCallBack) {
        this.f5935H = onHeatMapDrawFrameCallBack;
    }

    public final void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        this.f5971t = onMapClickListener;
    }

    public final void setOnMapDoubleClickListener(OnMapDoubleClickListener onMapDoubleClickListener) {
        this.f5974w = onMapDoubleClickListener;
    }

    public final void setOnMapDrawFrameCallback(OnMapDrawFrameCallback onMapDrawFrameCallback) {
        this.f5932E = onMapDrawFrameCallback;
    }

    public final void setOnMapGestureListener(onMapGestureListener onmapgesturelistener) {
        this.f5970s = onmapgesturelistener;
    }

    public void setOnMapLoadedCallback(OnMapLoadedCallback onMapLoadedCallback) {
        this.f5972u = onMapLoadedCallback;
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        this.f5975x = onMapLongClickListener;
    }

    public void setOnMapRenderCallbadk(OnMapRenderCallback onMapRenderCallback) {
        this.f5973v = onMapRenderCallback;
    }

    public final void setOnMapRenderValidDataListener(OnMapRenderValidDataListener onMapRenderValidDataListener) {
        this.f5934G = onMapRenderValidDataListener;
    }

    public final void setOnMapStatusChangeListener(OnMapStatusChangeListener onMapStatusChangeListener) {
        this.f5968q = onMapStatusChangeListener;
    }

    public final void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        this.f5969r = onMapTouchListener;
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener == null || this.f5976y.contains(onMarkerClickListener)) {
            return;
        }
        this.f5976y.add(onMarkerClickListener);
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        this.f5929B = onMarkerDragListener;
    }

    public final void setOnMultiPointClickListener(OnMultiPointClickListener onMultiPointClickListener) {
        if (onMultiPointClickListener != null) {
            this.f5928A.add(onMultiPointClickListener);
        }
    }

    public final void setOnMyLocationClickListener(OnMyLocationClickListener onMyLocationClickListener) {
        this.f5930C = onMyLocationClickListener;
    }

    public final void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        if (onPolylineClickListener != null) {
            this.f5977z.add(onPolylineClickListener);
        }
    }

    public final void setOnSynchronizationListener(OnSynchronizationListener onSynchronizationListener) {
        this.f5936I = onSynchronizationListener;
    }

    public void setOverlayUnderPoi(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18311e(z);
    }

    @Deprecated
    public final void setPadding(int i, int i2, int i3, int i4) {
        setViewPadding(i, i2, i3, i4);
    }

    public void setPixelFormatTransparent(boolean z) {
        MapSurfaceView mapSurfaceView = this.f5959h;
        if (mapSurfaceView == null) {
            return;
        }
        mapSurfaceView.setPixelFormatTransparent(z);
    }

    public final void setTrafficEnabled(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18292l(z);
        }
    }

    public final void setViewPadding(int i, int i2, int i3, int i4) {
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || this.f5961j == null) {
            return;
        }
        switch (C2760b.f6540b[this.f5956d.ordinal()]) {
            case 1:
                TextureMapView textureMapView = this.f5954b;
                if (textureMapView == null) {
                    return;
                }
                this.f5961j.m18352a(new Point((int) (i + (this.f5950W.x * (((textureMapView.getWidth() - i) - i3) / this.f5954b.getWidth()))), (int) (i2 + (this.f5950W.y * (((this.f5954b.getHeight() - i2) - i4) / this.f5954b.getHeight())))));
                this.f5954b.setPadding(i, i2, i3, i4);
                this.f5954b.invalidate();
                return;
            case 2:
                MapView mapView = this.f5953a;
                if (mapView == null) {
                    return;
                }
                this.f5961j.m18352a(new Point((int) (i + (this.f5950W.x * (((mapView.getWidth() - i) - i3) / this.f5953a.getWidth()))), (int) (i2 + (this.f5950W.y * (((this.f5953a.getHeight() - i2) - i4) / this.f5953a.getHeight())))));
                this.f5953a.setPadding(i, i2, i3, i4);
                this.f5953a.invalidate();
                return;
            default:
                return;
        }
    }

    public void showInfoWindow(InfoWindow infoWindow) {
        showInfoWindow(infoWindow, true);
    }

    public void showInfoWindow(InfoWindow infoWindow, boolean z) {
        boolean z2;
        Set<InfoWindow> keySet = this.f5942O.keySet();
        if (infoWindow == null || keySet.contains(infoWindow) || this.f5951X) {
            return;
        }
        if (z) {
            hideInfoWindow();
        }
        infoWindow.f6119f = this.f5967p;
        if (infoWindow.f6116c == null || !infoWindow.f6124k) {
            z2 = true;
        } else {
            View view = infoWindow.f6116c;
            view.destroyDrawingCache();
            MapViewLayoutParams build = new MapViewLayoutParams.Builder().layoutMode(MapViewLayoutParams.ELayoutMode.mapMode).position(infoWindow.f6117d).yOffset(infoWindow.f6120g).build();
            switch (C2760b.f6540b[this.f5956d.ordinal()]) {
                case 1:
                    TextureMapView textureMapView = this.f5954b;
                    if (textureMapView != null) {
                        textureMapView.addView(view, build);
                        break;
                    }
                    break;
                case 2:
                    MapView mapView = this.f5953a;
                    if (mapView != null) {
                        mapView.addView(view, build);
                        break;
                    }
                    break;
            }
            z2 = false;
        }
        BitmapDescriptor m19006b = m19006b(infoWindow);
        if (m19006b == null) {
            return;
        }
        Overlay mo18866a = new MarkerOptions().perspective(false).icon(m19006b).position(infoWindow.f6117d).zIndex(Integer.MAX_VALUE).yOffset(infoWindow.f6120g).infoWindow(infoWindow).mo18866a();
        mo18866a.listener = this.f5966o;
        mo18866a.type = EnumC2933i.popup;
        Bundle bundle = new Bundle();
        mo18866a.mo18867a(bundle);
        if (infoWindow.f6116c != null) {
            bundle.putInt("draw_with_view", 1);
        } else {
            bundle.putInt("draw_with_view", 0);
        }
        if (this.f5961j != null && z2 && !this.f5951X) {
            this.f5961j.m18318c(bundle);
            this.f5962k.add(mo18866a);
        }
        Marker marker = (Marker) mo18866a;
        marker.f6214A = this.f5967p;
        this.f5941N.put(marker.f6297F, infoWindow);
        this.f5942O.put(infoWindow, marker);
        this.f5965n.add(infoWindow);
    }

    public void showInfoWindows(List<InfoWindow> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (InfoWindow infoWindow : list) {
            showInfoWindow(infoWindow, false);
        }
    }

    public final void showMapIndoorPoi(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18379D(z);
            this.f5947T = z;
        }
    }

    public final void showMapPoi(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d != null) {
            c2925d.m18381C(z);
            this.f5946S = z;
        }
    }

    public void showOperateLayer(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18314d(z);
    }

    public void showSDKLayer() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18316d();
    }

    public final void snapshot(SnapshotReadyCallback snapshotReadyCallback) {
        this.f5931D = snapshotReadyCallback;
        switch (C2760b.f6540b[this.f5956d.ordinal()]) {
            case 1:
                MapTextureView mapTextureView = this.f5960i;
                if (mapTextureView == null || mapTextureView.getController() == null) {
                    return;
                }
                this.f5960i.doCaptureMapView(new C2766h(this), this.f5960i.getController().getScreenWidth(), this.f5960i.getController().getScreenHeight(), Bitmap.Config.ARGB_8888);
                this.f5960i.requestRender();
                return;
            case 2:
                MapSurfaceView mapSurfaceView = this.f5959h;
                if (mapSurfaceView == null || mapSurfaceView.getController() == null) {
                    return;
                }
                this.f5959h.doCaptureMapView(new C2767i(this), this.f5959h.getController().getScreenWidth(), this.f5959h.getController().getScreenHeight(), Bitmap.Config.ARGB_8888);
                this.f5959h.requestRender();
                return;
            default:
                return;
        }
    }

    public final void snapshotScope(Rect rect, SnapshotReadyCallback snapshotReadyCallback) {
        if (this.f5961j == null) {
            return;
        }
        this.f5931D = snapshotReadyCallback;
        switch (C2760b.f6540b[this.f5956d.ordinal()]) {
            case 1:
                MapTextureView mapTextureView = this.f5960i;
                if (mapTextureView != null) {
                    mapTextureView.doCaptureMapView(new C2768j(this), rect, Bitmap.Config.ARGB_8888);
                    this.f5960i.requestRender();
                    return;
                }
                return;
            case 2:
                MapSurfaceView mapSurfaceView = this.f5959h;
                if (mapSurfaceView != null) {
                    mapSurfaceView.doCaptureMapView(new C2769k(this), rect, Bitmap.Config.ARGB_8888);
                    this.f5959h.requestRender();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void startHeatMapFrameAnimation() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18287o();
    }

    public void stopHeatMapFrameAnimation() {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18285p();
    }

    public MapBaseIndoorMapInfo.SwitchFloorError switchBaseIndoorMapFloor(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return MapBaseIndoorMapInfo.SwitchFloorError.FLOOR_INFO_ERROR;
        }
        MapBaseIndoorMapInfo focusedBaseIndoorMapInfo = getFocusedBaseIndoorMapInfo();
        if (focusedBaseIndoorMapInfo == null) {
            return MapBaseIndoorMapInfo.SwitchFloorError.SWITCH_ERROR;
        }
        if (str2.equals(focusedBaseIndoorMapInfo.f6129a)) {
            ArrayList<String> floors = focusedBaseIndoorMapInfo.getFloors();
            if (floors == null || !floors.contains(str)) {
                return MapBaseIndoorMapInfo.SwitchFloorError.FLOOR_OVERLFLOW;
            }
            C2925d c2925d = this.f5961j;
            return (c2925d == null || !c2925d.m18332a(str, str2)) ? MapBaseIndoorMapInfo.SwitchFloorError.SWITCH_ERROR : MapBaseIndoorMapInfo.SwitchFloorError.SWITCH_OK;
        }
        return MapBaseIndoorMapInfo.SwitchFloorError.FOCUSED_ID_ERROR;
    }

    public void switchLayerOrder(MapLayer mapLayer, MapLayer mapLayer2) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return;
        }
        c2925d.m18348a(mapLayer, mapLayer2);
    }

    public boolean switchOverlayLayerAndNavigationLayer(boolean z) {
        C2925d c2925d = this.f5961j;
        if (c2925d == null) {
            return false;
        }
        return c2925d.m18305g(z);
    }
}
