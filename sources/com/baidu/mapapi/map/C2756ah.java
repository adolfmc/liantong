package com.baidu.mapapi.map;

import android.graphics.Point;
import android.util.SparseArray;
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
/* renamed from: com.baidu.mapapi.map.ah */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2756ah implements InterfaceC3010al {

    /* renamed from: a */
    final /* synthetic */ WearMapView f6534a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2756ah(WearMapView wearMapView) {
        this.f6534a = wearMapView;
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
        SparseArray sparseArray;
        MapSurfaceView mapSurfaceView7;
        ImageView imageView;
        TextView textView;
        TextView textView2;
        MapSurfaceView mapSurfaceView8;
        mapSurfaceView = this.f6534a.f6495f;
        if (mapSurfaceView != null) {
            mapSurfaceView2 = this.f6534a.f6495f;
            if (mapSurfaceView2.getBaseMap() == null) {
                return;
            }
            mapSurfaceView3 = this.f6534a.f6495f;
            float zoomLevel = mapSurfaceView3.getZoomLevel();
            mapSurfaceView4 = this.f6534a.f6495f;
            if (zoomLevel < mapSurfaceView4.getController().mMinZoomLevel) {
                mapSurfaceView8 = this.f6534a.f6495f;
                zoomLevel = mapSurfaceView8.getController().mMinZoomLevel;
            } else {
                mapSurfaceView5 = this.f6534a.f6495f;
                if (zoomLevel > mapSurfaceView5.getController().mMaxZoomLevel) {
                    mapSurfaceView6 = this.f6534a.f6495f;
                    zoomLevel = mapSurfaceView6.getController().mMaxZoomLevel;
                }
            }
            f = this.f6534a.f6486A;
            if (Math.abs(f - zoomLevel) > 0.0f) {
                sparseArray = WearMapView.f6485x;
                int intValue = ((Integer) sparseArray.get(Math.round(zoomLevel))).intValue();
                mapSurfaceView7 = this.f6534a.f6495f;
                imageView = this.f6534a.f6507r;
                int zoomUnitsInMeter = ((int) (intValue / mapSurfaceView7.getController().getZoomUnitsInMeter())) / 2;
                imageView.setPadding(zoomUnitsInMeter, 0, zoomUnitsInMeter, 0);
                String format = intValue >= 1000 ? String.format(" %d公里 ", Integer.valueOf(intValue / 1000)) : String.format(" %d米 ", Integer.valueOf(intValue));
                textView = this.f6534a.f6505p;
                textView.setText(format);
                textView2 = this.f6534a.f6506q;
                textView2.setText(format);
                this.f6534a.f6486A = zoomLevel;
            }
            this.f6534a.requestLayout();
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
