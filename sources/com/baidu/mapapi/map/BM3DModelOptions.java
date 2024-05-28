package com.baidu.mapapi.map;

import android.text.TextUtils;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BM3DModelOptions extends OverlayOptions {

    /* renamed from: a */
    private String f5913a;

    /* renamed from: b */
    private String f5914b;

    /* renamed from: c */
    private LatLng f5915c;

    /* renamed from: f */
    private float f5918f;

    /* renamed from: g */
    private float f5919g;

    /* renamed from: h */
    private float f5920h;

    /* renamed from: i */
    private float f5921i;

    /* renamed from: j */
    private float f5922j;

    /* renamed from: k */
    private float f5923k;

    /* renamed from: d */
    private float f5916d = 1.0f;

    /* renamed from: e */
    private boolean f5917e = false;

    /* renamed from: l */
    private boolean f5924l = true;

    /* renamed from: m */
    private BM3DModelType f5925m = BM3DModelType.BM3DModelTypeObj;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum BM3DModelType {
        BM3DModelTypeObj,
        BM3DModelTypeglTF
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        BM3DModel bM3DModel = new BM3DModel();
        if (TextUtils.isEmpty(this.f5913a)) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel modelPath can not be null");
        }
        bM3DModel.f5901a = this.f5913a;
        if (TextUtils.isEmpty(this.f5914b)) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel mModelName can not be null");
        }
        bM3DModel.f5902b = this.f5914b;
        LatLng latLng = this.f5915c;
        if (latLng != null) {
            bM3DModel.f5903c = latLng;
            bM3DModel.f5904d = this.f5916d;
            bM3DModel.f5905e = this.f5917e;
            bM3DModel.f5906f = this.f5918f;
            bM3DModel.f5907g = this.f5919g;
            bM3DModel.f5908h = this.f5920h;
            bM3DModel.f5909i = this.f5921i;
            bM3DModel.f5910j = this.f5922j;
            bM3DModel.f5911k = this.f5923k;
            bM3DModel.f6299H = this.f5924l;
            bM3DModel.f5912l = this.f5925m;
            return bM3DModel;
        }
        throw new IllegalArgumentException("BDMapSDKException: BM3DModel mPosition can not be null");
    }

    public BM3DModelType getBM3DModelType() {
        return this.f5925m;
    }

    public String getModelName() {
        return this.f5914b;
    }

    public String getModelPath() {
        return this.f5913a;
    }

    public float getOffsetX() {
        return this.f5921i;
    }

    public float getOffsetY() {
        return this.f5922j;
    }

    public float getOffsetZ() {
        return this.f5923k;
    }

    public LatLng getPosition() {
        return this.f5915c;
    }

    public float getRotateX() {
        return this.f5918f;
    }

    public float getRotateY() {
        return this.f5919g;
    }

    public float getRotateZ() {
        return this.f5920h;
    }

    public float getScale() {
        return this.f5916d;
    }

    public boolean isVisible() {
        return this.f5924l;
    }

    public boolean isZoomFixed() {
        return this.f5917e;
    }

    public BM3DModelOptions setBM3DModelType(BM3DModelType bM3DModelType) {
        this.f5925m = bM3DModelType;
        return this;
    }

    public BM3DModelOptions setModelName(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel modelName can not be null");
        }
        this.f5914b = str;
        return this;
    }

    public BM3DModelOptions setModelPath(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("BDMapSDKException: BM3DModel modelPath can not be null");
        }
        this.f5913a = str;
        return this;
    }

    public BM3DModelOptions setOffset(float f, float f2, float f3) {
        this.f5921i = f;
        this.f5922j = f2;
        this.f5923k = f3;
        return this;
    }

    public BM3DModelOptions setPosition(LatLng latLng) {
        if (latLng != null) {
            this.f5915c = latLng;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: BM3DModel position can not be null");
    }

    public BM3DModelOptions setRotate(float f, float f2, float f3) {
        this.f5918f = f;
        this.f5919g = f2;
        this.f5920h = f3;
        return this;
    }

    public BM3DModelOptions setScale(float f) {
        this.f5916d = f;
        return this;
    }

    public BM3DModelOptions setZoomFixed(boolean z) {
        this.f5917e = z;
        return this;
    }

    public BM3DModelOptions visible(boolean z) {
        this.f5924l = z;
        return this;
    }
}
