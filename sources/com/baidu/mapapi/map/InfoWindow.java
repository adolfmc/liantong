package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class InfoWindow {

    /* renamed from: a */
    String f6114a;

    /* renamed from: b */
    BitmapDescriptor f6115b;

    /* renamed from: c */
    View f6116c;

    /* renamed from: d */
    LatLng f6117d;

    /* renamed from: e */
    OnInfoWindowClickListener f6118e;

    /* renamed from: f */
    InterfaceC2745a f6119f;

    /* renamed from: g */
    int f6120g;

    /* renamed from: h */
    boolean f6121h;

    /* renamed from: i */
    int f6122i;

    /* renamed from: j */
    boolean f6123j;

    /* renamed from: k */
    boolean f6124k;

    /* renamed from: l */
    boolean f6125l;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnInfoWindowClickListener {
        void onInfoWindowClick();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapapi.map.InfoWindow$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2745a {
        /* renamed from: a */
        void mo18794a(InfoWindow infoWindow);

        /* renamed from: b */
        void mo18793b(InfoWindow infoWindow);
    }

    public InfoWindow(View view, LatLng latLng, int i) {
        this.f6114a = "";
        this.f6121h = false;
        this.f6122i = SysOSUtil.getDensityDpi();
        this.f6123j = false;
        this.f6124k = false;
        this.f6125l = false;
        if (view == null || latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: view and position can not be null");
        }
        this.f6116c = view;
        this.f6117d = latLng;
        this.f6120g = i;
        this.f6124k = true;
    }

    public InfoWindow(View view, LatLng latLng, int i, boolean z, int i2) {
        this.f6114a = "";
        this.f6121h = false;
        this.f6122i = SysOSUtil.getDensityDpi();
        this.f6123j = false;
        this.f6124k = false;
        this.f6125l = false;
        if (view == null || latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: view and position can not be null");
        }
        this.f6116c = view;
        this.f6117d = latLng;
        this.f6120g = i;
        this.f6121h = z;
        this.f6122i = i2;
        this.f6124k = true;
    }

    public InfoWindow(BitmapDescriptor bitmapDescriptor, LatLng latLng, int i, OnInfoWindowClickListener onInfoWindowClickListener) {
        this.f6114a = "";
        this.f6121h = false;
        this.f6122i = SysOSUtil.getDensityDpi();
        this.f6123j = false;
        this.f6124k = false;
        this.f6125l = false;
        if (bitmapDescriptor == null || latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: bitmapDescriptor and position can not be null");
        }
        this.f6115b = bitmapDescriptor;
        this.f6117d = latLng;
        this.f6118e = onInfoWindowClickListener;
        this.f6120g = i;
        this.f6125l = true;
    }

    public BitmapDescriptor getBitmapDescriptor() {
        return this.f6115b;
    }

    public LatLng getPosition() {
        return this.f6117d;
    }

    public String getTag() {
        return this.f6114a;
    }

    public View getView() {
        return this.f6116c;
    }

    public int getYOffset() {
        return this.f6120g;
    }

    public void setBitmapDescriptor(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            return;
        }
        this.f6115b = bitmapDescriptor;
        this.f6119f.mo18793b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            return;
        }
        this.f6117d = latLng;
        this.f6119f.mo18793b(this);
    }

    public void setTag(String str) {
        this.f6114a = str;
    }

    public void setView(View view) {
        if (view == null) {
            return;
        }
        this.f6116c = view;
        this.f6119f.mo18793b(this);
    }

    public void setYOffset(int i) {
        this.f6120g = i;
        this.f6119f.mo18793b(this);
    }
}
