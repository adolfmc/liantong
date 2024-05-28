package com.baidu.mapapi.map;

import android.graphics.Point;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.InterfaceC3010al;
import com.baidu.platform.comapi.map.MapSurfaceView;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.r */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2777r implements InterfaceC3010al {

    /* renamed from: a */
    final /* synthetic */ MapView f6568a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2777r(MapView mapView) {
        this.f6568a = mapView;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17973a() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17970a(MotionEvent motionEvent) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17968a(C2948x c2948x) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17967a(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17966a(String str) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17965a(GL10 gl10, C2948x c2948x) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17964a(boolean z) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public void mo17963a(boolean z, int i) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public boolean mo17972a(Point point, Point point2, C2948x c2948x) {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public boolean mo17971a(Point point, C2948x c2948x) {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: a */
    public boolean mo17969a(MotionEvent motionEvent, float f, float f2, C2948x c2948x) {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public void mo17962b() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public void mo17960b(C2948x c2948x) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public void mo17959b(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public boolean mo17961b(Point point, Point point2, C2948x c2948x) {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: b */
    public boolean mo17958b(String str) {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: c */
    public void mo17957c() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: c */
    public void mo17955c(C2948x c2948x) {
        MapSurfaceView mapSurfaceView;
        MapSurfaceView mapSurfaceView2;
        MapSurfaceView mapSurfaceView3;
        MapSurfaceView mapSurfaceView4;
        MapSurfaceView mapSurfaceView5;
        MapSurfaceView mapSurfaceView6;
        float f;
        SparseIntArray sparseIntArray;
        MapSurfaceView mapSurfaceView7;
        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        ImageView imageView2;
        MapSurfaceView mapSurfaceView8;
        mapSurfaceView = this.f6568a.f6179e;
        if (mapSurfaceView != null) {
            mapSurfaceView2 = this.f6568a.f6179e;
            if (mapSurfaceView2.getController() == null) {
                return;
            }
            mapSurfaceView3 = this.f6568a.f6179e;
            float zoomLevel = mapSurfaceView3.getZoomLevel();
            mapSurfaceView4 = this.f6568a.f6179e;
            if (zoomLevel < mapSurfaceView4.getController().mMinZoomLevel) {
                mapSurfaceView8 = this.f6568a.f6179e;
                zoomLevel = mapSurfaceView8.getController().mMinZoomLevel;
            } else {
                mapSurfaceView5 = this.f6568a.f6179e;
                if (zoomLevel > mapSurfaceView5.getController().mMaxZoomLevel) {
                    mapSurfaceView6 = this.f6568a.f6179e;
                    zoomLevel = mapSurfaceView6.getController().mMaxZoomLevel;
                }
            }
            f = this.f6568a.f6194u;
            if (Math.abs(f - zoomLevel) > 0.0f) {
                sparseIntArray = MapView.f6176q;
                int i = sparseIntArray.get(Math.round(zoomLevel));
                mapSurfaceView7 = this.f6568a.f6179e;
                int zoomUnitsInMeter = (int) (i / mapSurfaceView7.getController().getZoomUnitsInMeter());
                imageView = this.f6568a.f6189o;
                if (imageView != null) {
                    imageView2 = this.f6568a.f6189o;
                    int i2 = zoomUnitsInMeter / 2;
                    imageView2.setPadding(i2, 0, i2, 0);
                }
                String format = i >= 1000 ? String.format(" %d公里 ", Integer.valueOf(i / 1000)) : String.format(" %d米 ", Integer.valueOf(i));
                textView = this.f6568a.f6187m;
                if (textView != null) {
                    textView4 = this.f6568a.f6187m;
                    textView4.setText(format);
                }
                textView2 = this.f6568a.f6188n;
                if (textView2 != null) {
                    textView3 = this.f6568a.f6188n;
                    textView3.setText(format);
                }
                this.f6568a.f6194u = zoomLevel;
            }
            this.f6568a.m18906b();
            this.f6568a.requestLayout();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: c */
    public void mo17954c(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: c */
    public boolean mo17956c(Point point, Point point2, C2948x c2948x) {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: d */
    public void mo17953d() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: d */
    public void mo17951d(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: d */
    public boolean mo17952d(Point point, Point point2, C2948x c2948x) {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC3010al
    /* renamed from: e */
    public void mo17950e(GeoPoint geoPoint) {
    }
}
