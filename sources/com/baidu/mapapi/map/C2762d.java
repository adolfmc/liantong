package com.baidu.mapapi.map;

import android.graphics.Point;
import android.view.MotionEvent;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.C2925d;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.InterfaceC3010al;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2762d implements InterfaceC3010al {

    /* renamed from: a */
    final /* synthetic */ BaiduMap f6542a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2762d(BaiduMap baiduMap) {
        this.f6542a = baiduMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17973a() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17970a(MotionEvent motionEvent) {
        BaiduMap.OnMapTouchListener onMapTouchListener;
        BaiduMap.OnMapTouchListener onMapTouchListener2;
        onMapTouchListener = this.f6542a.f5969r;
        if (onMapTouchListener != null) {
            onMapTouchListener2 = this.f6542a.f5969r;
            onMapTouchListener2.onTouch(motionEvent);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17968a(C2948x c2948x) {
        BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener;
        BaiduMap.OnSynchronizationListener onSynchronizationListener;
        BaiduMap.OnSynchronizationListener onSynchronizationListener2;
        BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener2;
        BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener3;
        int i = (BaiduMap.mapStatusReason & 256) == 256 ? 3 : (BaiduMap.mapStatusReason & 16) == 16 ? 2 : 1;
        onMapStatusChangeListener = this.f6542a.f5968q;
        if (onMapStatusChangeListener != null) {
            MapStatus m18930a = MapStatus.m18930a(c2948x);
            onMapStatusChangeListener2 = this.f6542a.f5968q;
            onMapStatusChangeListener2.onMapStatusChangeStart(m18930a);
            onMapStatusChangeListener3 = this.f6542a.f5968q;
            onMapStatusChangeListener3.onMapStatusChangeStart(m18930a, i);
        }
        onSynchronizationListener = this.f6542a.f5936I;
        if (onSynchronizationListener != null) {
            onSynchronizationListener2 = this.f6542a.f5936I;
            onSynchronizationListener2.onMapStatusChangeReason(i);
        }
        BaiduMap.mapStatusReason = 0;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17967a(GeoPoint geoPoint) {
        BaiduMap.OnMapClickListener onMapClickListener;
        BaiduMap.OnMapClickListener onMapClickListener2;
        onMapClickListener = this.f6542a.f5971t;
        if (onMapClickListener != null) {
            LatLng mc2ll = CoordUtil.mc2ll(geoPoint);
            onMapClickListener2 = this.f6542a.f5971t;
            onMapClickListener2.onMapClick(mc2ll);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17966a(String str) {
        C2925d c2925d;
        List<Overlay> list;
        CopyOnWriteArrayList copyOnWriteArrayList;
        CopyOnWriteArrayList copyOnWriteArrayList2;
        List<Overlay> list2;
        CopyOnWriteArrayList copyOnWriteArrayList3;
        CopyOnWriteArrayList copyOnWriteArrayList4;
        Map map;
        List<Overlay> list3;
        CopyOnWriteArrayList copyOnWriteArrayList5;
        CopyOnWriteArrayList copyOnWriteArrayList6;
        Map map2;
        C2925d c2925d2;
        C2925d c2925d3;
        C2925d c2925d4;
        BaiduMap.OnMyLocationClickListener onMyLocationClickListener;
        BaiduMap.OnMyLocationClickListener onMyLocationClickListener2;
        BaiduMap.OnMapClickListener onMapClickListener;
        BaiduMap.OnMapClickListener onMapClickListener2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            c2925d = this.f6542a.f5961j;
            GeoPoint m18325b = c2925d.m18325b(jSONObject.optInt("px"), jSONObject.optInt("py"));
            JSONArray optJSONArray = jSONObject.optJSONArray("dataset");
            JSONObject jSONObject2 = null;
            int i = -1;
            if (optJSONArray != null && (jSONObject2 = optJSONArray.optJSONObject(0)) != null) {
                i = jSONObject2.optInt("ty");
            }
            if (i == 17) {
                onMapClickListener = this.f6542a.f5971t;
                if (onMapClickListener != null) {
                    MapPoi mapPoi = new MapPoi();
                    mapPoi.m18932a(jSONObject2);
                    onMapClickListener2 = this.f6542a.f5971t;
                    onMapClickListener2.onMapPoiClick(mapPoi);
                    return;
                }
                return;
            }
            if (i != 18) {
                if (i == 19) {
                    c2925d2 = this.f6542a.f5961j;
                    if (c2925d2 != null) {
                        c2925d3 = this.f6542a.f5961j;
                        C2948x m18378E = c2925d3.m18378E();
                        if (m18378E == null) {
                            return;
                        }
                        m18378E.f7371c = 0;
                        m18378E.f7370b = 0;
                        BaiduMap.mapStatusReason |= 16;
                        c2925d4 = this.f6542a.f5961j;
                        c2925d4.m18339a(m18378E, 300);
                        return;
                    }
                    return;
                } else if (i == 90909) {
                    String optString = jSONObject2 != null ? jSONObject2.optString("marker_id") : "";
                    map = this.f6542a.f5941N;
                    Set<String> keySet = map.keySet();
                    if (!keySet.isEmpty() && keySet.contains(optString)) {
                        for (String str2 : keySet) {
                            if (str2 != null && str2.equals(optString)) {
                                map2 = this.f6542a.f5941N;
                                InfoWindow infoWindow = (InfoWindow) map2.get(str2);
                                if (infoWindow != null && infoWindow.f6118e != null) {
                                    infoWindow.f6118e.onInfoWindowClick();
                                    return;
                                }
                            }
                        }
                        return;
                    }
                    list3 = this.f6542a.f5962k;
                    for (Overlay overlay : list3) {
                        if ((overlay instanceof Marker) && overlay.f6297F.equals(optString)) {
                            copyOnWriteArrayList5 = this.f6542a.f5976y;
                            if (!copyOnWriteArrayList5.isEmpty()) {
                                copyOnWriteArrayList6 = this.f6542a.f5976y;
                                Iterator it = copyOnWriteArrayList6.iterator();
                                while (it.hasNext()) {
                                    ((BaiduMap.OnMarkerClickListener) it.next()).onMarkerClick((Marker) overlay);
                                }
                                return;
                            }
                            mo17967a(m18325b);
                        }
                    }
                    return;
                } else if (i == 90910) {
                    String optString2 = jSONObject2 != null ? jSONObject2.optString("polyline_id") : "";
                    list2 = this.f6542a.f5962k;
                    for (Overlay overlay2 : list2) {
                        if ((overlay2 instanceof Polyline) && overlay2.f6297F.equals(optString2)) {
                            copyOnWriteArrayList3 = this.f6542a.f5977z;
                            if (copyOnWriteArrayList3.isEmpty()) {
                                mo17967a(m18325b);
                            } else {
                                copyOnWriteArrayList4 = this.f6542a.f5977z;
                                Iterator it2 = copyOnWriteArrayList4.iterator();
                                while (it2.hasNext()) {
                                    ((BaiduMap.OnPolylineClickListener) it2.next()).onPolylineClick((Polyline) overlay2);
                                }
                            }
                        }
                    }
                    return;
                } else if (i == 90911) {
                    String optString3 = jSONObject2 != null ? jSONObject2.optString("multipoint_id") : "";
                    list = this.f6542a.f5962k;
                    for (Overlay overlay3 : list) {
                        if ((overlay3 instanceof MultiPoint) && overlay3.f6297F.equals(optString3)) {
                            copyOnWriteArrayList = this.f6542a.f5928A;
                            if (copyOnWriteArrayList.isEmpty()) {
                                mo17967a(m18325b);
                            } else {
                                copyOnWriteArrayList2 = this.f6542a.f5928A;
                                Iterator it3 = copyOnWriteArrayList2.iterator();
                                while (it3.hasNext()) {
                                    BaiduMap.OnMultiPointClickListener onMultiPointClickListener = (BaiduMap.OnMultiPointClickListener) it3.next();
                                    MultiPoint multiPoint = (MultiPoint) overlay3;
                                    List<MultiPointItem> multiPointItems = multiPoint.getMultiPointItems();
                                    if (jSONObject2 != null) {
                                        int optInt = jSONObject2.optInt("multipoint_index");
                                        if (multiPointItems != null && optInt >= 0 && multiPointItems.size() > optInt) {
                                            onMultiPointClickListener.onMultiPointClick(multiPoint, multiPointItems.get(optInt));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return;
                } else {
                    return;
                }
            }
            onMyLocationClickListener = this.f6542a.f5930C;
            if (onMyLocationClickListener != null) {
                onMyLocationClickListener2 = this.f6542a.f5930C;
                onMyLocationClickListener2.onMyLocationClick();
                return;
            }
            mo17967a(m18325b);
        } catch (Exception unused) {
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17965a(GL10 gl10, C2948x c2948x) {
        Map map;
        BaiduMap.OnMapDrawFrameCallback onMapDrawFrameCallback;
        BaiduMap.OnMapDrawFrameCallback onMapDrawFrameCallback2;
        Map map2;
        Map map3;
        map = this.f6542a.f5941N;
        if (map != null) {
            map2 = this.f6542a.f5941N;
            if (!map2.values().isEmpty()) {
                map3 = this.f6542a.f5941N;
                for (InfoWindow infoWindow : map3.values()) {
                    if (infoWindow != null && infoWindow.f6116c != null) {
                        infoWindow.f6116c.post(new RunnableC2763e(this, infoWindow));
                    }
                }
            }
        }
        onMapDrawFrameCallback = this.f6542a.f5932E;
        if (onMapDrawFrameCallback != null) {
            MapStatus m18930a = MapStatus.m18930a(c2948x);
            onMapDrawFrameCallback2 = this.f6542a.f5932E;
            onMapDrawFrameCallback2.onMapDrawFrame(m18930a);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17964a(boolean z) {
        BaiduMap.OnBaseIndoorMapListener onBaseIndoorMapListener;
        BaiduMap.OnBaseIndoorMapListener onBaseIndoorMapListener2;
        onBaseIndoorMapListener = this.f6542a.f5933F;
        if (onBaseIndoorMapListener != null) {
            MapBaseIndoorMapInfo focusedBaseIndoorMapInfo = this.f6542a.getFocusedBaseIndoorMapInfo();
            onBaseIndoorMapListener2 = this.f6542a.f5933F;
            onBaseIndoorMapListener2.onBaseIndoorMapMode(z, focusedBaseIndoorMapInfo);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17963a(boolean z, int i) {
        BaiduMap.OnMapRenderValidDataListener onMapRenderValidDataListener;
        String m19021a;
        BaiduMap.OnMapRenderValidDataListener onMapRenderValidDataListener2;
        onMapRenderValidDataListener = this.f6542a.f5934G;
        if (onMapRenderValidDataListener != null) {
            m19021a = this.f6542a.m19021a(i);
            onMapRenderValidDataListener2 = this.f6542a.f5934G;
            onMapRenderValidDataListener2.onMapRenderValidData(z, i, m19021a);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public boolean mo17972a(Point point, Point point2, C2948x c2948x) {
        BaiduMap.onMapGestureListener onmapgesturelistener;
        BaiduMap.onMapGestureListener onmapgesturelistener2;
        MapStatus m18930a = MapStatus.m18930a(c2948x);
        onmapgesturelistener = this.f6542a.f5970s;
        if (onmapgesturelistener != null) {
            BaiduMap.mapStatusReason = 1;
            onmapgesturelistener2 = this.f6542a.f5970s;
            return onmapgesturelistener2.onMapKneading(point, point2, m18930a);
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public boolean mo17971a(Point point, C2948x c2948x) {
        BaiduMap.onMapGestureListener onmapgesturelistener;
        BaiduMap.onMapGestureListener onmapgesturelistener2;
        MapStatus m18930a = MapStatus.m18930a(c2948x);
        onmapgesturelistener = this.f6542a.f5970s;
        if (onmapgesturelistener != null) {
            BaiduMap.mapStatusReason = 1;
            onmapgesturelistener2 = this.f6542a.f5970s;
            return onmapgesturelistener2.onMapDoubleTouch(point, m18930a);
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public boolean mo17969a(MotionEvent motionEvent, float f, float f2, C2948x c2948x) {
        BaiduMap.onMapGestureListener onmapgesturelistener;
        BaiduMap.onMapGestureListener onmapgesturelistener2;
        MapStatus m18930a = MapStatus.m18930a(c2948x);
        onmapgesturelistener = this.f6542a.f5970s;
        if (onmapgesturelistener != null) {
            BaiduMap.mapStatusReason = 1;
            onmapgesturelistener2 = this.f6542a.f5970s;
            return onmapgesturelistener2.onMapFling(motionEvent, f, f2, m18930a);
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public void mo17962b() {
        C2925d c2925d;
        BaiduMap.OnMapLoadedCallback onMapLoadedCallback;
        BaiduMap.OnMapLoadedCallback onMapLoadedCallback2;
        BaiduMap baiduMap = this.f6542a;
        c2925d = baiduMap.f5961j;
        baiduMap.f5957f = new Projection(c2925d);
        this.f6542a.f5949V = true;
        onMapLoadedCallback = this.f6542a.f5972u;
        if (onMapLoadedCallback != null) {
            onMapLoadedCallback2 = this.f6542a.f5972u;
            onMapLoadedCallback2.onMapLoaded();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public void mo17960b(C2948x c2948x) {
        BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener;
        BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener2;
        onMapStatusChangeListener = this.f6542a.f5968q;
        if (onMapStatusChangeListener != null) {
            MapStatus m18930a = MapStatus.m18930a(c2948x);
            onMapStatusChangeListener2 = this.f6542a.f5968q;
            onMapStatusChangeListener2.onMapStatusChange(m18930a);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public void mo17959b(GeoPoint geoPoint) {
        BaiduMap.OnMapDoubleClickListener onMapDoubleClickListener;
        BaiduMap.OnMapDoubleClickListener onMapDoubleClickListener2;
        onMapDoubleClickListener = this.f6542a.f5974w;
        if (onMapDoubleClickListener != null) {
            LatLng mc2ll = CoordUtil.mc2ll(geoPoint);
            BaiduMap.mapStatusReason |= 1;
            onMapDoubleClickListener2 = this.f6542a.f5974w;
            onMapDoubleClickListener2.onMapDoubleClick(mc2ll);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public boolean mo17961b(Point point, Point point2, C2948x c2948x) {
        BaiduMap.onMapGestureListener onmapgesturelistener;
        BaiduMap.onMapGestureListener onmapgesturelistener2;
        MapStatus m18930a = MapStatus.m18930a(c2948x);
        onmapgesturelistener = this.f6542a.f5970s;
        if (onmapgesturelistener != null) {
            BaiduMap.mapStatusReason = 1;
            onmapgesturelistener2 = this.f6542a.f5970s;
            return onmapgesturelistener2.onMapScroll(point, point2, m18930a);
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public boolean mo17958b(String str) {
        JSONObject optJSONObject;
        Map map;
        List<Overlay> list;
        Projection projection;
        Marker marker;
        Projection projection2;
        Marker marker2;
        BaiduMap.OnMarkerDragListener onMarkerDragListener;
        BaiduMap.OnMarkerDragListener onMarkerDragListener2;
        Marker marker3;
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("dataset");
            if (optJSONArray == null || (optJSONObject = optJSONArray.optJSONObject(0)) == null || optJSONObject.optInt("ty") != 90909) {
                return false;
            }
            String optString = optJSONObject.optString("marker_id");
            map = this.f6542a.f5941N;
            Set keySet = map.keySet();
            if (keySet.isEmpty() || !keySet.contains(optString)) {
                list = this.f6542a.f5962k;
                for (Overlay overlay : list) {
                    if ((overlay instanceof Marker) && overlay.f6297F.equals(optString)) {
                        Marker marker4 = (Marker) overlay;
                        if (marker4.f6224f) {
                            this.f6542a.f5943P = marker4;
                            projection = this.f6542a.f5957f;
                            marker = this.f6542a.f5943P;
                            Point screenLocation = projection.toScreenLocation(marker.f6219a);
                            Point point = new Point(screenLocation.x, screenLocation.y - 60);
                            projection2 = this.f6542a.f5957f;
                            LatLng fromScreenLocation = projection2.fromScreenLocation(point);
                            marker2 = this.f6542a.f5943P;
                            marker2.setPosition(fromScreenLocation);
                            onMarkerDragListener = this.f6542a.f5929B;
                            if (onMarkerDragListener != null) {
                                onMarkerDragListener2 = this.f6542a.f5929B;
                                marker3 = this.f6542a.f5943P;
                                onMarkerDragListener2.onMarkerDragStart(marker3);
                            }
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: c */
    public void mo17957c() {
        BaiduMap.OnMapRenderCallback onMapRenderCallback;
        BaiduMap.OnMapRenderCallback onMapRenderCallback2;
        onMapRenderCallback = this.f6542a.f5973v;
        if (onMapRenderCallback != null) {
            onMapRenderCallback2 = this.f6542a.f5973v;
            onMapRenderCallback2.onMapRenderFinished();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: c */
    public void mo17955c(C2948x c2948x) {
        BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener;
        BaiduMap.onMapGestureListener onmapgesturelistener;
        BaiduMap.onMapGestureListener onmapgesturelistener2;
        BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener2;
        MapStatus m18930a = MapStatus.m18930a(c2948x);
        onMapStatusChangeListener = this.f6542a.f5968q;
        if (onMapStatusChangeListener != null) {
            onMapStatusChangeListener2 = this.f6542a.f5968q;
            onMapStatusChangeListener2.onMapStatusChangeFinish(m18930a);
        }
        onmapgesturelistener = this.f6542a.f5970s;
        if (onmapgesturelistener != null) {
            onmapgesturelistener2 = this.f6542a.f5970s;
            onmapgesturelistener2.onMapStatusChangeFinish(m18930a);
        }
        BaiduMap.mapStatusReason = 0;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: c */
    public void mo17954c(GeoPoint geoPoint) {
        BaiduMap.OnMapLongClickListener onMapLongClickListener;
        BaiduMap.OnMapLongClickListener onMapLongClickListener2;
        onMapLongClickListener = this.f6542a.f5975x;
        if (onMapLongClickListener != null) {
            LatLng mc2ll = CoordUtil.mc2ll(geoPoint);
            onMapLongClickListener2 = this.f6542a.f5975x;
            onMapLongClickListener2.onMapLongClick(mc2ll);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: c */
    public boolean mo17956c(Point point, Point point2, C2948x c2948x) {
        BaiduMap.onMapGestureListener onmapgesturelistener;
        BaiduMap.onMapGestureListener onmapgesturelistener2;
        MapStatus m18930a = MapStatus.m18930a(c2948x);
        onmapgesturelistener = this.f6542a.f5970s;
        if (onmapgesturelistener != null) {
            BaiduMap.mapStatusReason = 1;
            onmapgesturelistener2 = this.f6542a.f5970s;
            return onmapgesturelistener2.onMapOverLooking(point, point2, m18930a);
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: d */
    public void mo17953d() {
        C2925d c2925d;
        Lock lock;
        Lock lock2;
        HeatMap heatMap;
        HeatMap heatMap2;
        C2925d c2925d2;
        c2925d = this.f6542a.f5961j;
        if (c2925d != null) {
            c2925d2 = this.f6542a.f5961j;
            c2925d2.m18317c(false);
        }
        lock = this.f6542a.f5939L;
        lock.lock();
        try {
            heatMap = this.f6542a.f5938K;
            if (heatMap != null) {
                BaiduMap baiduMap = this.f6542a;
                heatMap2 = this.f6542a.f5938K;
                baiduMap.m19014a(heatMap2);
            }
        } finally {
            lock2 = this.f6542a.f5939L;
            lock2.unlock();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: d */
    public void mo17951d(GeoPoint geoPoint) {
        Marker marker;
        Marker marker2;
        Projection projection;
        Projection projection2;
        Marker marker3;
        BaiduMap.OnMarkerDragListener onMarkerDragListener;
        Marker marker4;
        BaiduMap.OnMarkerDragListener onMarkerDragListener2;
        Marker marker5;
        marker = this.f6542a.f5943P;
        if (marker != null) {
            marker2 = this.f6542a.f5943P;
            if (marker2.f6224f) {
                LatLng mc2ll = CoordUtil.mc2ll(geoPoint);
                projection = this.f6542a.f5957f;
                Point screenLocation = projection.toScreenLocation(mc2ll);
                Point point = new Point(screenLocation.x, screenLocation.y - 60);
                projection2 = this.f6542a.f5957f;
                LatLng fromScreenLocation = projection2.fromScreenLocation(point);
                marker3 = this.f6542a.f5943P;
                marker3.setPosition(fromScreenLocation);
                onMarkerDragListener = this.f6542a.f5929B;
                if (onMarkerDragListener != null) {
                    marker4 = this.f6542a.f5943P;
                    if (marker4.f6224f) {
                        onMarkerDragListener2 = this.f6542a.f5929B;
                        marker5 = this.f6542a.f5943P;
                        onMarkerDragListener2.onMarkerDrag(marker5);
                    }
                }
            }
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: d */
    public boolean mo17952d(Point point, Point point2, C2948x c2948x) {
        BaiduMap.onMapGestureListener onmapgesturelistener;
        BaiduMap.onMapGestureListener onmapgesturelistener2;
        MapStatus m18930a = MapStatus.m18930a(c2948x);
        onmapgesturelistener = this.f6542a.f5970s;
        if (onmapgesturelistener != null) {
            BaiduMap.mapStatusReason = 1;
            onmapgesturelistener2 = this.f6542a.f5970s;
            return onmapgesturelistener2.onMapTwoClick(point, point2, m18930a);
        }
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: e */
    public void mo17950e(GeoPoint geoPoint) {
        Marker marker;
        Marker marker2;
        Projection projection;
        Projection projection2;
        Marker marker3;
        BaiduMap.OnMarkerDragListener onMarkerDragListener;
        Marker marker4;
        BaiduMap.OnMarkerDragListener onMarkerDragListener2;
        Marker marker5;
        marker = this.f6542a.f5943P;
        if (marker != null) {
            marker2 = this.f6542a.f5943P;
            if (marker2.f6224f) {
                LatLng mc2ll = CoordUtil.mc2ll(geoPoint);
                projection = this.f6542a.f5957f;
                Point screenLocation = projection.toScreenLocation(mc2ll);
                Point point = new Point(screenLocation.x, screenLocation.y - 60);
                projection2 = this.f6542a.f5957f;
                LatLng fromScreenLocation = projection2.fromScreenLocation(point);
                marker3 = this.f6542a.f5943P;
                marker3.setPosition(fromScreenLocation);
                onMarkerDragListener = this.f6542a.f5929B;
                if (onMarkerDragListener != null) {
                    marker4 = this.f6542a.f5943P;
                    if (marker4.f6224f) {
                        onMarkerDragListener2 = this.f6542a.f5929B;
                        marker5 = this.f6542a.f5943P;
                        onMarkerDragListener2.onMarkerDragEnd(marker5);
                    }
                }
                this.f6542a.f5943P = null;
            }
        }
    }
}
