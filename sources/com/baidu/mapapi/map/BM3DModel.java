package com.baidu.mapapi.map;

import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.mapapi.map.BM3DModelOptions;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BM3DModel extends Overlay {

    /* renamed from: a */
    String f5901a;

    /* renamed from: b */
    String f5902b;

    /* renamed from: c */
    LatLng f5903c;

    /* renamed from: f */
    float f5906f;

    /* renamed from: g */
    float f5907g;

    /* renamed from: h */
    float f5908h;

    /* renamed from: i */
    float f5909i;

    /* renamed from: j */
    float f5910j;

    /* renamed from: k */
    float f5911k;

    /* renamed from: d */
    float f5904d = 1.0f;

    /* renamed from: e */
    boolean f5905e = false;

    /* renamed from: l */
    BM3DModelOptions.BM3DModelType f5912l = BM3DModelOptions.BM3DModelType.BM3DModelTypeObj;

    public BM3DModel() {
        this.type = EnumC2933i.BM3DModel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        super.mo18867a(bundle);
        if (TextUtils.isEmpty(this.f5901a)) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel modelPath can not be null");
        }
        bundle.putString("modelPath", this.f5901a);
        if (TextUtils.isEmpty(this.f5902b)) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel mModelName can not be null");
        }
        bundle.putString("modelName", this.f5902b);
        LatLng latLng = this.f5903c;
        if (latLng != null) {
            GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
            bundle.putDouble("location_x", ll2mc.getLongitudeE6());
            bundle.putDouble("location_y", ll2mc.getLatitudeE6());
            bundle.putInt("modelType", this.f5912l.ordinal());
            bundle.putFloat("scale", this.f5904d);
            bundle.putInt("zoomFixed", this.f5905e ? 1 : 0);
            bundle.putFloat("rotateX", this.f5906f);
            bundle.putFloat("rotateY", this.f5907g);
            bundle.putFloat("rotateZ", this.f5908h);
            bundle.putFloat("offsetX", this.f5909i);
            bundle.putFloat("offsetY", this.f5910j);
            bundle.putFloat("offsetZ", this.f5911k);
            return bundle;
        }
        throw new IllegalArgumentException("BDMapSDKException: BM3DModel mPosition can not be null");
    }

    public BM3DModelOptions.BM3DModelType getBM3DModelType() {
        return this.f5912l;
    }

    public String getModelName() {
        return this.f5902b;
    }

    public String getModelPath() {
        return this.f5901a;
    }

    public float getOffsetX() {
        return this.f5909i;
    }

    public float getOffsetY() {
        return this.f5910j;
    }

    public float getOffsetZ() {
        return this.f5911k;
    }

    public LatLng getPosition() {
        return this.f5903c;
    }

    public float getRotateX() {
        return this.f5906f;
    }

    public float getRotateY() {
        return this.f5907g;
    }

    public float getRotateZ() {
        return this.f5908h;
    }

    public float getScale() {
        return this.f5904d;
    }

    public boolean isZoomFixed() {
        return this.f5905e;
    }

    public void setBM3DModelType(BM3DModelOptions.BM3DModelType bM3DModelType) {
        this.f5912l = bM3DModelType;
        this.listener.mo18796c(this);
    }

    public void setModelName(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel modelName can not be null");
        }
        this.f5902b = str;
        this.listener.mo18796c(this);
    }

    public void setModelPath(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel modelPath can not be null");
        }
        this.f5901a = str;
        this.listener.mo18796c(this);
    }

    public void setOffset(float f, float f2, float f3) {
        this.f5909i = f;
        this.f5910j = f2;
        this.f5911k = f3;
        this.listener.mo18796c(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel position can not be null");
        }
        this.f5903c = latLng;
        this.listener.mo18796c(this);
    }

    public void setRotate(float f, float f2, float f3) {
        this.f5906f = f;
        this.f5907g = f2;
        this.f5908h = f3;
        this.listener.mo18796c(this);
    }

    public void setScale(float f) {
        this.f5904d = f;
        this.listener.mo18796c(this);
    }

    public void setZoomFixed(boolean z) {
        this.f5905e = z;
        this.listener.mo18796c(this);
    }
}
