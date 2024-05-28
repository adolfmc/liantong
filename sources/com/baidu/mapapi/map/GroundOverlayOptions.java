package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class GroundOverlayOptions extends OverlayOptions {

    /* renamed from: a */
    int f6057a;

    /* renamed from: c */
    Bundle f6059c;

    /* renamed from: d */
    private BitmapDescriptor f6060d;

    /* renamed from: e */
    private LatLng f6061e;

    /* renamed from: f */
    private int f6062f;

    /* renamed from: g */
    private int f6063g;

    /* renamed from: j */
    private LatLngBounds f6066j;

    /* renamed from: h */
    private float f6064h = 0.5f;

    /* renamed from: i */
    private float f6065i = 0.5f;

    /* renamed from: k */
    private float f6067k = 1.0f;

    /* renamed from: b */
    boolean f6058b = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        LatLngBounds latLngBounds;
        int i;
        LatLng latLng;
        int i2;
        GroundOverlay groundOverlay = new GroundOverlay();
        groundOverlay.f6299H = this.f6058b;
        groundOverlay.f6298G = this.f6057a;
        groundOverlay.f6300I = this.f6059c;
        BitmapDescriptor bitmapDescriptor = this.f6060d;
        if (bitmapDescriptor != null) {
            groundOverlay.f6049b = bitmapDescriptor;
            if (this.f6066j == null && (latLng = this.f6061e) != null) {
                int i3 = this.f6062f;
                if (i3 <= 0 || (i2 = this.f6063g) <= 0) {
                    throw new IllegalArgumentException("BDMapSDKException: when you add ground overlay, the width and height must greater than 0");
                }
                groundOverlay.f6050c = latLng;
                groundOverlay.f6053f = this.f6064h;
                groundOverlay.f6054g = this.f6065i;
                groundOverlay.f6051d = i3;
                groundOverlay.f6052e = i2;
                i = 2;
            } else if (this.f6061e != null || (latLngBounds = this.f6066j) == null) {
                throw new IllegalStateException("BDMapSDKException: when you add ground overlay, you must set one of position or bounds");
            } else {
                groundOverlay.f6055h = latLngBounds;
                i = 1;
            }
            groundOverlay.f6048a = i;
            groundOverlay.f6056i = this.f6067k;
            return groundOverlay;
        }
        throw new IllegalStateException("BDMapSDKException: when you add ground overlay, you must set the image");
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f6064h = f;
            this.f6065i = f2;
        }
        return this;
    }

    public GroundOverlayOptions dimensions(int i) {
        this.f6062f = i;
        this.f6063g = Integer.MAX_VALUE;
        return this;
    }

    public GroundOverlayOptions dimensions(int i, int i2) {
        this.f6062f = i;
        this.f6063g = i2;
        return this;
    }

    public GroundOverlayOptions extraInfo(Bundle bundle) {
        this.f6059c = bundle;
        return this;
    }

    public float getAnchorX() {
        return this.f6064h;
    }

    public float getAnchorY() {
        return this.f6065i;
    }

    public LatLngBounds getBounds() {
        return this.f6066j;
    }

    public Bundle getExtraInfo() {
        return this.f6059c;
    }

    public int getHeight() {
        int i = this.f6063g;
        return i == Integer.MAX_VALUE ? (int) ((this.f6062f * this.f6060d.f5990a.getHeight()) / this.f6060d.f5990a.getWidth()) : i;
    }

    public BitmapDescriptor getImage() {
        return this.f6060d;
    }

    public LatLng getPosition() {
        return this.f6061e;
    }

    public float getTransparency() {
        return this.f6067k;
    }

    public int getWidth() {
        return this.f6062f;
    }

    public int getZIndex() {
        return this.f6057a;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.f6060d = bitmapDescriptor;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: image can not be null");
    }

    public boolean isVisible() {
        return this.f6058b;
    }

    public GroundOverlayOptions position(LatLng latLng) {
        if (latLng != null) {
            this.f6061e = latLng;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: position can not be null");
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds != null) {
            this.f6066j = latLngBounds;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: bounds can not be null");
    }

    public GroundOverlayOptions transparency(float f) {
        if (f <= 1.0f && f >= 0.0f) {
            this.f6067k = f;
        }
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.f6058b = z;
        return this;
    }

    public GroundOverlayOptions zIndex(int i) {
        this.f6057a = i;
        return this;
    }
}
