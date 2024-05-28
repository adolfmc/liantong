package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class DotOptions extends OverlayOptions {

    /* renamed from: a */
    int f6020a;

    /* renamed from: c */
    Bundle f6022c;

    /* renamed from: d */
    private LatLng f6023d;

    /* renamed from: e */
    private int f6024e = -16777216;

    /* renamed from: f */
    private int f6025f = 5;

    /* renamed from: b */
    boolean f6021b = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        Dot dot = new Dot();
        dot.f6299H = this.f6021b;
        dot.f6298G = this.f6020a;
        dot.f6300I = this.f6022c;
        dot.f6018b = this.f6024e;
        dot.f6017a = this.f6023d;
        dot.f6019c = this.f6025f;
        return dot;
    }

    public DotOptions center(LatLng latLng) {
        if (latLng != null) {
            this.f6023d = latLng;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: dot center can not be null");
    }

    public DotOptions color(int i) {
        this.f6024e = i;
        return this;
    }

    public DotOptions extraInfo(Bundle bundle) {
        this.f6022c = bundle;
        return this;
    }

    public LatLng getCenter() {
        return this.f6023d;
    }

    public int getColor() {
        return this.f6024e;
    }

    public Bundle getExtraInfo() {
        return this.f6022c;
    }

    public int getRadius() {
        return this.f6025f;
    }

    public int getZIndex() {
        return this.f6020a;
    }

    public boolean isVisible() {
        return this.f6021b;
    }

    public DotOptions radius(int i) {
        if (i > 0) {
            this.f6025f = i;
        }
        return this;
    }

    public DotOptions visible(boolean z) {
        this.f6021b = z;
        return this;
    }

    public DotOptions zIndex(int i) {
        this.f6020a = i;
        return this;
    }
}
