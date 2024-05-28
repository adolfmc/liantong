package com.baidu.mapapi.map;

import android.graphics.Point;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.InterfaceC3010al;
import com.baidu.platform.comapi.map.MapTextureView;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.ab */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2750ab implements InterfaceC3010al {

    /* renamed from: a */
    final /* synthetic */ TextureMapView f6521a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2750ab(TextureMapView textureMapView) {
        this.f6521a = textureMapView;
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
        MapTextureView mapTextureView;
        MapTextureView mapTextureView2;
        MapTextureView mapTextureView3;
        MapTextureView mapTextureView4;
        MapTextureView mapTextureView5;
        MapTextureView mapTextureView6;
        float f;
        SparseArray sparseArray;
        MapTextureView mapTextureView7;
        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        ImageView imageView2;
        MapTextureView mapTextureView8;
        mapTextureView = this.f6521a.f6439b;
        if (mapTextureView != null) {
            mapTextureView2 = this.f6521a.f6439b;
            if (mapTextureView2.getController() == null) {
                return;
            }
            mapTextureView3 = this.f6521a.f6439b;
            float zoomLevel = mapTextureView3.getZoomLevel();
            mapTextureView4 = this.f6521a.f6439b;
            if (zoomLevel < mapTextureView4.getController().mMinZoomLevel) {
                mapTextureView8 = this.f6521a.f6439b;
                zoomLevel = mapTextureView8.getController().mMinZoomLevel;
            } else {
                mapTextureView5 = this.f6521a.f6439b;
                if (zoomLevel > mapTextureView5.getController().mMaxZoomLevel) {
                    mapTextureView6 = this.f6521a.f6439b;
                    zoomLevel = mapTextureView6.getController().mMaxZoomLevel;
                }
            }
            f = this.f6521a.f6451r;
            if (Math.abs(f - zoomLevel) > 0.0f) {
                sparseArray = TextureMapView.f6436q;
                int intValue = ((Integer) sparseArray.get(Math.round(zoomLevel))).intValue();
                mapTextureView7 = this.f6521a.f6439b;
                int zoomUnitsInMeter = (int) (intValue / mapTextureView7.getController().getZoomUnitsInMeter());
                imageView = this.f6521a.f6449o;
                if (imageView != null) {
                    imageView2 = this.f6521a.f6449o;
                    int i = zoomUnitsInMeter / 2;
                    imageView2.setPadding(i, 0, i, 0);
                }
                String format = intValue >= 1000 ? String.format(" %d公里 ", Integer.valueOf(intValue / 1000)) : String.format(" %d米 ", Integer.valueOf(intValue));
                textView = this.f6521a.f6447m;
                if (textView != null) {
                    textView4 = this.f6521a.f6447m;
                    textView4.setText(format);
                }
                textView2 = this.f6521a.f6448n;
                if (textView2 != null) {
                    textView3 = this.f6521a.f6448n;
                    textView3.setText(format);
                }
                this.f6521a.f6451r = zoomLevel;
            }
            this.f6521a.m18853b();
            this.f6521a.requestLayout();
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
