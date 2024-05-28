package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MultiPoint extends Overlay {

    /* renamed from: a */
    List<MultiPointItem> f6275a;

    /* renamed from: b */
    BitmapDescriptor f6276b;

    /* renamed from: c */
    int f6277c;

    /* renamed from: d */
    int f6278d;

    /* renamed from: e */
    float f6279e;

    /* renamed from: f */
    float f6280f;

    public MultiPoint() {
        this.type = EnumC2933i.multiPoint;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        super.mo18867a(bundle);
        List<MultiPointItem> list = this.f6275a;
        if (list != null && list.size() > 0) {
            GeoPoint ll2mc = CoordUtil.ll2mc(this.f6275a.get(0).getPoint());
            bundle.putDouble("location_x", ll2mc.getLongitudeE6());
            bundle.putDouble("location_y", ll2mc.getLatitudeE6());
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.f6275a.size(); i++) {
                MultiPointItem multiPointItem = this.f6275a.get(i);
                if (multiPointItem != null) {
                    arrayList.add(multiPointItem.getPoint());
                }
            }
            Overlay.m18892a(arrayList, bundle);
        }
        BitmapDescriptor bitmapDescriptor = this.f6276b;
        if (bitmapDescriptor != null) {
            bundle.putBundle("image_info", bitmapDescriptor.m18976b());
        }
        bundle.putInt("isClickable", 1);
        bundle.putFloat("anchor_x", this.f6279e);
        bundle.putFloat("anchor_y", this.f6280f);
        bundle.putFloat("pointsize_x", this.f6277c);
        bundle.putFloat("pointsize_y", this.f6278d);
        return bundle;
    }

    public void anchor(float f, float f2) {
        if (f < 0.0f || f > 1.0f || f2 < 0.0f || f2 > 1.0f) {
            return;
        }
        this.f6279e = f;
        this.f6280f = f2;
        this.listener.mo18796c(this);
    }

    public float getAnchorX() {
        return this.f6279e;
    }

    public float getAnchorY() {
        return this.f6280f;
    }

    public BitmapDescriptor getIcon() {
        return this.f6276b;
    }

    public List<MultiPointItem> getMultiPointItems() {
        return this.f6275a;
    }

    public int getPointSizeHeight() {
        return this.f6278d;
    }

    public int getPointSizeWidth() {
        return this.f6277c;
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's icon can not be null");
        }
        this.f6276b = bitmapDescriptor;
        if (this.f6277c == 0) {
            this.f6277c = bitmapDescriptor.getBitmap().getWidth();
        }
        if (this.f6278d == 0) {
            this.f6278d = bitmapDescriptor.getBitmap().getHeight();
        }
        this.listener.mo18796c(this);
    }

    public void setMultiPointItems(List<MultiPointItem> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: multiPointItems list can not be null");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: multiPointItems list can not contains null");
        }
        this.f6275a = list;
        this.listener.mo18796c(this);
    }

    public void setPointSize(int i, int i2) {
        if (this.f6277c <= 0 || this.f6278d <= 0) {
            throw new IllegalArgumentException("BDMapSDKException: MultiPoint setPointSize can not be 0 Or can't less than 0");
        }
        this.f6277c = i;
        this.f6278d = i2;
        this.listener.mo18796c(this);
    }
}
