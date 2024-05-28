package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ArcOptions extends OverlayOptions {

    /* renamed from: d */
    private static final String f5892d = "ArcOptions";

    /* renamed from: a */
    int f5893a;

    /* renamed from: c */
    Bundle f5895c;

    /* renamed from: g */
    private LatLng f5898g;

    /* renamed from: h */
    private LatLng f5899h;

    /* renamed from: i */
    private LatLng f5900i;

    /* renamed from: e */
    private int f5896e = -16777216;

    /* renamed from: f */
    private int f5897f = 5;

    /* renamed from: b */
    boolean f5894b = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        Arc arc = new Arc();
        arc.f6299H = this.f5894b;
        arc.f6298G = this.f5893a;
        arc.f6300I = this.f5895c;
        arc.f5887a = this.f5896e;
        arc.f5888b = this.f5897f;
        arc.f5889c = this.f5898g;
        arc.f5890d = this.f5899h;
        arc.f5891e = this.f5900i;
        return arc;
    }

    public ArcOptions color(int i) {
        this.f5896e = i;
        return this;
    }

    public ArcOptions extraInfo(Bundle bundle) {
        this.f5895c = bundle;
        return this;
    }

    public int getColor() {
        return this.f5896e;
    }

    public LatLng getEndPoint() {
        return this.f5900i;
    }

    public Bundle getExtraInfo() {
        return this.f5895c;
    }

    public LatLng getMiddlePoint() {
        return this.f5899h;
    }

    public LatLng getStartPoint() {
        return this.f5898g;
    }

    public int getWidth() {
        return this.f5897f;
    }

    public int getZIndex() {
        return this.f5893a;
    }

    public boolean isVisible() {
        return this.f5894b;
    }

    public ArcOptions points(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng == null || latLng2 == null || latLng3 == null) {
            throw new IllegalArgumentException("BDMapSDKException: start and middle and end points can not be null");
        }
        if (latLng == latLng2 || latLng == latLng3 || latLng2 == latLng3) {
            throw new IllegalArgumentException("BDMapSDKException: start and middle and end points can not be same");
        }
        this.f5898g = latLng;
        this.f5899h = latLng2;
        this.f5900i = latLng3;
        return this;
    }

    public ArcOptions visible(boolean z) {
        this.f5894b = z;
        return this;
    }

    public ArcOptions width(int i) {
        if (i > 0) {
            this.f5897f = i;
        }
        return this;
    }

    public ArcOptions zIndex(int i) {
        this.f5893a = i;
        return this;
    }
}
