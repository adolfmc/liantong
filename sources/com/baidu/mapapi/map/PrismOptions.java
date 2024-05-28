package com.baidu.mapapi.map;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.BuildingInfo;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PrismOptions extends OverlayOptions {

    /* renamed from: b */
    private float f6376b;

    /* renamed from: c */
    private List<LatLng> f6377c;

    /* renamed from: f */
    private BuildingInfo f6380f;

    /* renamed from: g */
    private BitmapDescriptor f6381g;

    /* renamed from: h */
    private boolean f6382h;

    /* renamed from: i */
    private int f6383i;

    /* renamed from: j */
    private boolean f6384j;

    /* renamed from: d */
    private int f6378d = -16777216;

    /* renamed from: e */
    private int f6379e = -16777216;

    /* renamed from: a */
    boolean f6375a = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        List<LatLng> list;
        Prism prism = new Prism();
        prism.f6299H = this.f6375a;
        prism.f6372g = this.f6381g;
        prism.f6366a = this.f6376b;
        prism.f6371f = this.f6382h;
        prism.f6374i = this.f6384j;
        prism.f6373h = this.f6383i;
        if (this.f6380f != null || ((list = this.f6377c) != null && list.size() > 3)) {
            prism.f6367b = this.f6377c;
            prism.f6369d = this.f6379e;
            prism.f6368c = this.f6378d;
            prism.f6370e = this.f6380f;
            return prism;
        }
        throw new IllegalStateException("BDMapSDKException: when you add prism, you must at least supply 4 points");
    }

    public PrismOptions customSideImage(BitmapDescriptor bitmapDescriptor) {
        this.f6381g = bitmapDescriptor;
        return this;
    }

    public BuildingInfo getBuildingInfo() {
        return this.f6380f;
    }

    public BitmapDescriptor getCustomSideImage() {
        return this.f6381g;
    }

    public float getHeight() {
        return this.f6376b;
    }

    public List<LatLng> getPoints() {
        return this.f6377c;
    }

    public int getShowLevel() {
        return this.f6383i;
    }

    public int getSideFaceColor() {
        return this.f6379e;
    }

    public int getTopFaceColor() {
        return this.f6378d;
    }

    public boolean isAnimation() {
        return this.f6384j;
    }

    public boolean isVisible() {
        return this.f6375a;
    }

    public PrismOptions setAnimation(boolean z) {
        this.f6384j = z;
        return this;
    }

    public PrismOptions setBuildingInfo(BuildingInfo buildingInfo) {
        this.f6380f = buildingInfo;
        return this;
    }

    public PrismOptions setHeight(float f) {
        this.f6376b = f;
        return this;
    }

    public PrismOptions setPoints(List<LatLng> list) {
        this.f6377c = list;
        return this;
    }

    public PrismOptions setShowLevel(int i) {
        this.f6383i = i;
        return this;
    }

    public PrismOptions setSideFaceColor(int i) {
        this.f6379e = i;
        return this;
    }

    public PrismOptions setTopFaceColor(int i) {
        this.f6378d = i;
        return this;
    }

    public PrismOptions showMarker(boolean z) {
        this.f6382h = z;
        return this;
    }

    public PrismOptions visible(boolean z) {
        this.f6375a = z;
        return this;
    }
}
