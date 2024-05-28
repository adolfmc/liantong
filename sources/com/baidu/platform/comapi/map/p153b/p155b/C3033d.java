package com.baidu.platform.comapi.map.p153b.p155b;

import android.graphics.Point;
import android.util.Pair;
import android.view.MotionEvent;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.InterfaceC3010al;
import com.baidu.platform.comapi.map.MapController;
import com.baidu.platform.comapi.map.MapStatus;
import com.baidu.platform.comapi.map.MapViewInterface;
import com.baidu.platform.comapi.map.p152a.C2994a;
import com.baidu.platform.comapi.map.p152a.C2997d;
import com.baidu.platform.comapi.map.p153b.C3020a;
import com.baidu.platform.comapi.map.p153b.p154a.C3027b;
import com.baidu.platform.comapi.util.C3087a;
import com.baidu.platform.comapi.util.SysOSUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.b.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3033d extends AbstractC3030a {

    /* renamed from: b */
    private GeoPoint f7818b;

    /* renamed from: c */
    private int f7819c;

    /* renamed from: d */
    private float f7820d;

    /* renamed from: e */
    private Queue<C3020a.C3023c> f7821e;

    /* renamed from: f */
    private C3020a.C3023c f7822f;

    /* renamed from: g */
    private C3020a.C3023c f7823g;

    /* renamed from: h */
    private boolean f7824h;

    /* renamed from: i */
    private C3027b f7825i;

    /* renamed from: j */
    private boolean f7826j;

    /* renamed from: k */
    private double f7827k;

    /* renamed from: l */
    private boolean f7828l;

    /* renamed from: m */
    private long f7829m;

    public C3033d(MapController mapController) {
        super(mapController);
        this.f7821e = new LinkedList();
        this.f7824h = false;
        this.f7826j = false;
        this.f7827k = 0.0d;
        this.f7828l = false;
        this.f7829m = 0L;
    }

    /* renamed from: a */
    private int m17893a() {
        if (this.f7826j) {
            LinkedList linkedList = new LinkedList();
            linkedList.addAll(this.f7821e);
            if (linkedList.size() < 2) {
                return 0;
            }
            int i = (int) (((C3020a.C3023c) linkedList.get(linkedList.size() - 2)).f7787a * 8.0d);
            if (i >= 180) {
                return 179;
            }
            if (i <= -180) {
                return -179;
            }
            return i;
        }
        return 0;
    }

    /* renamed from: a */
    private void m17892a(MapStatus mapStatus) {
        mapStatus.level = this.f7820d + ((float) (Math.log(this.f7822f.f7788b) / Math.log(2.0d)));
        mapStatus.level = mapStatus.level >= 4.0f ? mapStatus.level : 4.0f;
    }

    /* renamed from: a */
    private void m17891a(MapStatus mapStatus, int i) {
        if (i != 0) {
            mapStatus.rotation = (mapStatus.rotation + i) % 360;
            this.f7808a.setMapStatusWithAnimation(mapStatus, 600);
        }
    }

    /* renamed from: b */
    private void m17886b(MapStatus mapStatus) {
        if (this.f7818b != null) {
            if (Math.abs(this.f7823g.f7789c.f7790a) > 0.0d || Math.abs(this.f7823g.f7789c.f7791b) > 0.0d) {
                C3020a.C3022b m17916a = this.f7825i.f7797a.m17916a();
                C3020a.C3022b m17916a2 = this.f7825i.f7799c.m17916a();
                double sqrt = Math.sqrt(((m17916a2.f7785a - m17916a.f7785a) * (m17916a2.f7785a - m17916a.f7785a)) + ((m17916a2.f7786b - m17916a.f7786b) * (m17916a2.f7786b - m17916a.f7786b)));
                if (!MapController.isCompass || sqrt >= 100.0d) {
                    if (!MapController.isCompass && !this.f7828l) {
                        mapStatus.centerPtX = this.f7818b.getLongitude();
                        mapStatus.centerPtY = this.f7818b.getLatitude();
                        C3020a.C3022b m17916a3 = this.f7825i.f7799c.m17916a();
                        mapStatus.xOffset = (float) (m17916a3.f7785a - ((mapStatus.winRound.left + (this.f7808a.getScreenWidth() / 2)) + mapStatus.xScreenOffset));
                        mapStatus.yOffset = ((float) (m17916a3.f7786b - ((mapStatus.winRound.top + (this.f7808a.getScreenHeight() / 2)) + mapStatus.yScreenOffset))) * (-1.0f);
                        return;
                    }
                    this.f7828l = false;
                    C3087a.m17703a().m17700a(new C2994a());
                    MapViewInterface mapView = this.f7808a.getMapView();
                    if (mapView == null) {
                        return;
                    }
                    C3020a.C3022b m17916a4 = this.f7825i.f7799c.m17916a();
                    this.f7818b = mapView.getProjection().fromPixels((int) m17916a4.f7785a, (int) m17916a4.f7786b);
                }
            }
        }
    }

    /* renamed from: c */
    private void m17885c(MapStatus mapStatus) {
        double abs = Math.abs(new C3020a.C3023c(new C3020a.C3021a(this.f7825i.f7798b.f7783a, this.f7825i.f7799c.f7783a), this.f7825i.f7798b).f7787a);
        double abs2 = Math.abs(new C3020a.C3023c(new C3020a.C3021a(this.f7825i.f7798b.f7784b, this.f7825i.f7799c.f7784b), this.f7825i.f7798b).f7787a);
        double d = this.f7827k;
        boolean z = false;
        if (d != 0.0d && d * this.f7823g.f7788b < 0.0d) {
            return;
        }
        if (this.f7826j) {
            mapStatus.rotation = (int) ((this.f7819c + this.f7822f.f7787a) % 360.0d);
        } else {
            boolean z2 = (this.f7823g.f7788b < 1.0d && abs > 60.0d) || (this.f7823g.f7788b > 1.0d && Math.abs(abs - 180.0d) > 60.0d);
            if ((this.f7823g.f7788b > 1.0d && abs2 > 60.0d) || (this.f7823g.f7788b < 1.0d && Math.abs(abs2 - 180.0d) > 60.0d)) {
                z = true;
            }
            if (z2 || z) {
                if (Math.abs(this.f7822f.f7787a) > (MapController.isCompass ? 30 : 10)) {
                    this.f7826j = true;
                    this.f7808a.getGestureMonitor().m17877c();
                    this.f7819c = (int) (this.f7819c - this.f7822f.f7787a);
                    if (MapController.isCompass) {
                        this.f7828l = true;
                        C3087a.m17703a().m17700a(new C2994a());
                    }
                }
            }
        }
        this.f7827k = this.f7823g.f7788b;
    }

    /* renamed from: a */
    public void m17890a(MapStatus mapStatus, C3027b c3027b, Pair<C3020a.C3024d, C3020a.C3024d> pair) {
        double d;
        int i;
        double d2;
        double d3;
        if (pair != null) {
            int m17893a = m17893a();
            if (((C3020a.C3024d) pair.first).f7790a * ((C3020a.C3024d) pair.second).f7790a > 0.0d && ((C3020a.C3024d) pair.first).f7791b * ((C3020a.C3024d) pair.second).f7791b > 0.0d) {
                m17891a(mapStatus, m17893a);
            } else if (Math.abs(((C3020a.C3024d) pair.first).f7790a - ((C3020a.C3024d) pair.second).f7790a) < 1.0d || Math.abs(((C3020a.C3024d) pair.first).f7791b - ((C3020a.C3024d) pair.second).f7791b) < 1.0d) {
                m17891a(mapStatus, m17893a);
            } else {
                double abs = Math.abs(new C3020a.C3023c(new C3020a.C3021a(c3027b.f7798b.f7783a, c3027b.f7799c.f7783a), c3027b.f7798b).f7787a);
                double abs2 = Math.abs(new C3020a.C3023c(new C3020a.C3021a(c3027b.f7798b.f7784b, c3027b.f7799c.f7784b), c3027b.f7798b).f7787a);
                double d4 = this.f7827k;
                if (d4 != 0.0d && d4 * this.f7823g.f7788b < 0.0d) {
                    m17891a(mapStatus, m17893a);
                    return;
                }
                float sqrt = ((float) Math.sqrt((((C3020a.C3024d) pair.first).f7790a * ((C3020a.C3024d) pair.first).f7790a) + (((C3020a.C3024d) pair.second).f7790a * ((C3020a.C3024d) pair.second).f7790a) + (((C3020a.C3024d) pair.first).f7791b * ((C3020a.C3024d) pair.first).f7791b) + (((C3020a.C3024d) pair.second).f7791b * ((C3020a.C3024d) pair.second).f7791b))) * 2.0f;
                if (sqrt > (SysOSUtil.getInstance().getDensityDPI() * 100) / 320) {
                    mapStatus.hasAnimation = 1;
                    mapStatus.animationTime = 600;
                    C3020a.C3023c c3023c = null;
                    C3020a.C3023c c3023c2 = null;
                    int i2 = 0;
                    int i3 = 0;
                    int i4 = 0;
                    while (i2 < this.f7821e.size()) {
                        C3020a.C3023c poll = this.f7821e.poll();
                        if (poll != null) {
                            if (this.f7821e.isEmpty()) {
                                int i5 = m17893a;
                                d3 = 1.0d;
                                if (Math.abs(poll.f7788b - 1.0d) < 0.01d) {
                                    m17891a(mapStatus, i5);
                                    return;
                                }
                                i = i5;
                            } else {
                                i = m17893a;
                                d3 = 1.0d;
                            }
                            C3020a.C3023c c3023c3 = c3023c;
                            d2 = abs2;
                            if (poll.f7788b > d3) {
                                i3++;
                                c3023c = c3023c3;
                                c3023c2 = poll;
                            } else {
                                i4++;
                                c3023c = poll;
                            }
                        } else {
                            i = m17893a;
                            d2 = abs2;
                        }
                        i2++;
                        m17893a = i;
                        abs2 = d2;
                    }
                    int i6 = m17893a;
                    C3020a.C3023c c3023c4 = c3023c;
                    double d5 = abs2;
                    if (i3 < i4) {
                        c3023c2 = c3023c4;
                    }
                    if (c3023c2 != null) {
                        d = 1.0d;
                        if (Math.abs(c3023c2.f7788b - 1.0d) < 0.01d) {
                            m17891a(mapStatus, i6);
                            return;
                        }
                    } else {
                        d = 1.0d;
                    }
                    boolean z = (c3023c2.f7788b < d && abs > 60.0d) || (c3023c2.f7788b > d && Math.abs(abs - 180.0d) > 60.0d);
                    boolean z2 = (c3023c2.f7788b > 1.0d && d5 > 60.0d) || (c3023c2.f7788b < 1.0d && Math.abs(d5 - 180.0d) > 60.0d);
                    if (z || z2) {
                        if (Math.abs(this.f7822f.f7787a) > (MapController.isCompass ? 30 : 15)) {
                            m17891a(mapStatus, i6);
                            return;
                        }
                    }
                    this.f7824h = c3023c2.f7788b > 1.0d;
                    float densityDPI = sqrt / (800000 / SysOSUtil.getInstance().getDensityDPI());
                    if (densityDPI > 2.0f) {
                        densityDPI = 2.0f;
                    }
                    mapStatus.level = !this.f7824h ? mapStatus.level - densityDPI : mapStatus.level + densityDPI;
                    mapStatus.level = mapStatus.level >= 4.0f ? mapStatus.level : 4.0f;
                    if (i6 != 0) {
                        mapStatus.rotation = (mapStatus.rotation + i6) % 360;
                    }
                    this.f7808a.setMapStatus(mapStatus);
                    this.f7808a.mIsAnimating = true;
                }
            }
        }
    }

    @Override // com.baidu.platform.comapi.map.p153b.p155b.AbstractC3030a
    /* renamed from: a */
    public void mo17889a(C3027b c3027b) {
        MapViewInterface mapView = this.f7808a.getMapView();
        if (mapView == null) {
            return;
        }
        MapStatus mapStatus = this.f7808a.getMapStatus();
        C3020a.C3022b m17916a = c3027b.f7797a.m17916a();
        this.f7818b = mapView.getProjection().fromPixels((int) m17916a.f7785a, (int) m17916a.f7786b);
        this.f7820d = this.f7808a.getZoomLevel();
        this.f7819c = mapStatus.rotation;
        this.f7827k = 0.0d;
    }

    @Override // com.baidu.platform.comapi.map.p153b.p155b.AbstractC3030a
    /* renamed from: a */
    public void mo17888a(C3027b c3027b, Pair<C3020a.C3024d, C3020a.C3024d> pair) {
        double d;
        double d2;
        MapViewInterface mapView = this.f7808a.getMapView();
        if (mapView == null) {
            return;
        }
        MapStatus mapStatus = this.f7808a.getMapStatus();
        int x = (int) c3027b.f7800d.getX();
        int y = (int) c3027b.f7800d.getY();
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        GeoPoint fromPixels = mapView.getProjection().fromPixels((int) ((this.f7808a.getScreenWidth() / 2) + mapStatus.winRound.left + mapStatus.xScreenOffset), (int) ((this.f7808a.getScreenHeight() / 2) + mapStatus.winRound.top + mapStatus.yScreenOffset));
        if (fromPixels != null) {
            double longitude = fromPixels.getLongitude();
            d2 = fromPixels.getLatitude();
            d = longitude;
        } else {
            d = 0.0d;
            d2 = 0.0d;
        }
        this.f7808a.MapMsgProc(5, 1, (y << 16) | x, 0, 0, d, d2, 0.0d, 0.0d);
        this.f7808a.getGestureMonitor().m17882a(this.f7808a.getZoomLevel());
        if (System.currentTimeMillis() - this.f7829m <= 100 && this.f7808a.mIsInertialAnimation && this.f7808a.isEnableZoom()) {
            m17890a(this.f7808a.getMapStatus(), c3027b, pair);
        }
    }

    @Override // com.baidu.platform.comapi.map.p153b.p155b.AbstractC3030a
    /* renamed from: a */
    public void mo17887a(C3027b c3027b, MotionEvent motionEvent) {
        this.f7825i = c3027b;
        this.f7822f = new C3020a.C3023c(c3027b.f7797a, c3027b.f7799c);
        this.f7823g = new C3020a.C3023c(c3027b.f7798b, c3027b.f7799c);
        List<InterfaceC3010al> listeners = this.f7808a.getListeners();
        if (listeners != null) {
            C2948x mapStatusInner = this.f7808a.getMapStatusInner();
            float x = motionEvent.getX(1) - motionEvent.getX(0);
            float y = motionEvent.getY(1) - motionEvent.getY(0);
            Point point = new Point((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
            Point point2 = new Point((int) (motionEvent.getRawX() + x), (int) (motionEvent.getRawY() + y));
            for (int i = 0; i < listeners.size(); i++) {
                InterfaceC3010al interfaceC3010al = listeners.get(i);
                if (interfaceC3010al != null && interfaceC3010al.mo17972a(point, point2, mapStatusInner)) {
                    return;
                }
            }
        }
        MapStatus mapStatus = this.f7808a.getMapStatus();
        if (this.f7808a.isEnableZoom()) {
            m17892a(mapStatus);
        }
        if (this.f7808a.is3DGestureEnable() && this.f7808a.getMapControlMode() != MapController.MapControlMode.STREET) {
            if (mapStatus.overlooking == 0 && this.f7808a.isCanTouchMove()) {
                m17886b(mapStatus);
            }
            m17885c(mapStatus);
        }
        this.f7808a.setMapStatus(mapStatus);
        if (this.f7808a.isNaviMode() && this.f7808a.getNaviMapViewListener() != null) {
            this.f7808a.getNaviMapViewListener().onAction(520, null);
        }
        this.f7808a.mapStatusChangeStart();
        if (this.f7821e.size() >= 10) {
            this.f7821e.poll();
        }
        this.f7821e.offer(this.f7823g);
        C3087a.m17703a().m17700a(new C2997d());
        this.f7829m = System.currentTimeMillis();
    }
}
