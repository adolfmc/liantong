package com.baidu.mapapi.map;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MultiPointOption extends OverlayOptions {

    /* renamed from: a */
    private List<MultiPointItem> f6283a;

    /* renamed from: b */
    private BitmapDescriptor f6284b;

    /* renamed from: c */
    private int f6285c;

    /* renamed from: d */
    private int f6286d;

    /* renamed from: e */
    private float f6287e = 0.5f;

    /* renamed from: f */
    private float f6288f = 0.5f;

    /* renamed from: g */
    private boolean f6289g = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        MultiPoint multiPoint = new MultiPoint();
        multiPoint.f6276b = this.f6284b;
        List<MultiPointItem> list = this.f6283a;
        if (list != null) {
            multiPoint.f6275a = list;
            multiPoint.f6278d = this.f6286d;
            multiPoint.f6277c = this.f6285c;
            multiPoint.f6279e = this.f6287e;
            multiPoint.f6280f = this.f6288f;
            multiPoint.f6299H = this.f6289g;
            return multiPoint;
        }
        throw new IllegalStateException("BDMapSDKException: when you add mMultiPointItems, you must set the mMultiPointItems");
    }

    public float getAnchorX() {
        return this.f6287e;
    }

    public float getAnchorY() {
        return this.f6288f;
    }

    public BitmapDescriptor getIcon() {
        return this.f6284b;
    }

    public List<MultiPointItem> getMultiPointItems() {
        return this.f6283a;
    }

    public int getPointSizeHeight() {
        return this.f6286d;
    }

    public int getPointSizeWidth() {
        return this.f6285c;
    }

    public boolean isVisible() {
        return this.f6289g;
    }

    public MultiPointOption setAnchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f6287e = f;
            this.f6288f = f2;
        }
        return this;
    }

    public MultiPointOption setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            if (this.f6285c == 0) {
                this.f6285c = bitmapDescriptor.getBitmap().getWidth();
            }
            if (this.f6286d == 0) {
                this.f6286d = bitmapDescriptor.getBitmap().getHeight();
            }
            this.f6284b = bitmapDescriptor;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: MultiPoint icon can not be null");
    }

    public MultiPointOption setMultiPointItems(List<MultiPointItem> list) {
        if (list != null) {
            if (list.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: multiPointItems list can not contains null");
            }
            this.f6283a = list;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: multiPointItems list can not be null");
    }

    public MultiPointOption setPointSize(int i, int i2) {
        if (this.f6285c <= 0 || this.f6286d <= 0) {
            throw new IllegalArgumentException("BDMapSDKException: MultiPoint setPointSize can not be 0 Or can't less than 0");
        }
        this.f6285c = i;
        this.f6286d = i2;
        return this;
    }

    public MultiPointOption visible(boolean z) {
        this.f6289g = z;
        return this;
    }
}
