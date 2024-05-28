package com.baidu.platform.comapi.map.p153b;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.MapController;
import com.baidu.platform.comapi.map.MapViewInterface;
import java.util.IllegalFormatException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3035d {

    /* renamed from: a */
    private float f7831a;

    /* renamed from: b */
    private StringBuffer f7832b = new StringBuffer();

    /* renamed from: c */
    private StringBuffer f7833c = new StringBuffer();

    /* renamed from: d */
    private MapController f7834d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.b.d$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC3036a {
        ZOOM_OUT,
        ZOOM_IN,
        FLING,
        MOVE,
        ROTATE,
        DOUBLE_CLICK_ZOOM_IN,
        TWO_CLICK_ZOOM_OUT,
        MOVE_OVERLOOK
    }

    public C3035d(MapController mapController) {
        this.f7834d = mapController;
    }

    /* renamed from: a */
    private void m17881a(EnumC3036a enumC3036a) {
        MapViewInterface mapView;
        String m17878b;
        MapController mapController = this.f7834d;
        if (mapController == null || (mapView = mapController.getMapView()) == null) {
            return;
        }
        GeoPoint mapCenter = mapView.getMapCenter();
        try {
            m17878b = String.format("(%s,%d,%d,%d,%d)", m17878b(enumC3036a), Double.valueOf(mapCenter.getLongitudeE6()), Double.valueOf(mapCenter.getLatitudeE6()), Integer.valueOf((int) mapView.getZoomLevel()), Long.valueOf(System.currentTimeMillis()));
        } catch (IllegalFormatException unused) {
            m17878b = m17878b(enumC3036a);
        }
        StringBuffer stringBuffer = this.f7832b;
        if (stringBuffer == null) {
            return;
        }
        stringBuffer.append(m17878b);
        StringBuffer stringBuffer2 = this.f7833c;
        if (stringBuffer2 == null) {
            return;
        }
        stringBuffer2.append(m17878b(enumC3036a));
    }

    /* renamed from: b */
    private String m17878b(EnumC3036a enumC3036a) {
        switch (enumC3036a) {
            case MOVE:
            case FLING:
                return "0";
            case ZOOM_OUT:
                return "1";
            case ZOOM_IN:
                return "2";
            case ROTATE:
                return "3";
            case DOUBLE_CLICK_ZOOM_IN:
                return "4";
            case TWO_CLICK_ZOOM_OUT:
                return "5";
            case MOVE_OVERLOOK:
                return "6";
            default:
                return "";
        }
    }

    /* renamed from: d */
    private void m17874d(float f) {
        this.f7831a = f;
    }

    /* renamed from: e */
    private boolean m17873e(float f) {
        return f > this.f7831a;
    }

    /* renamed from: f */
    private boolean m17872f(float f) {
        return f < this.f7831a;
    }

    /* renamed from: a */
    public void m17883a() {
        m17881a(EnumC3036a.FLING);
    }

    /* renamed from: a */
    public void m17882a(float f) {
        if (m17873e(f)) {
            m17881a(EnumC3036a.ZOOM_OUT);
        }
        if (m17872f(f)) {
            m17881a(EnumC3036a.ZOOM_IN);
        }
        m17874d(f);
    }

    /* renamed from: b */
    public void m17880b() {
        m17881a(EnumC3036a.MOVE);
    }

    /* renamed from: b */
    public void m17879b(float f) {
        m17881a(EnumC3036a.DOUBLE_CLICK_ZOOM_IN);
        m17874d(f);
    }

    /* renamed from: c */
    public void m17877c() {
        m17881a(EnumC3036a.ROTATE);
    }

    /* renamed from: c */
    public void m17876c(float f) {
        m17881a(EnumC3036a.TWO_CLICK_ZOOM_OUT);
        m17874d(f);
    }

    /* renamed from: d */
    public void m17875d() {
        m17881a(EnumC3036a.MOVE_OVERLOOK);
    }
}
