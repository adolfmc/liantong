package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class CircleOptions extends OverlayOptions {

    /* renamed from: d */
    private static final String f6005d = "CircleOptions";

    /* renamed from: a */
    int f6006a;

    /* renamed from: c */
    Bundle f6008c;

    /* renamed from: e */
    private LatLng f6009e;

    /* renamed from: g */
    private int f6011g;

    /* renamed from: h */
    private Stroke f6012h;

    /* renamed from: k */
    private List<HoleOptions> f6015k;

    /* renamed from: l */
    private HoleOptions f6016l;

    /* renamed from: f */
    private int f6010f = -16777216;

    /* renamed from: i */
    private boolean f6013i = false;

    /* renamed from: j */
    private int f6014j = 0;

    /* renamed from: b */
    boolean f6007b = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        Circle circle = new Circle();
        circle.f6299H = this.f6007b;
        circle.f6298G = this.f6006a;
        circle.f6300I = this.f6008c;
        circle.f5995b = this.f6010f;
        circle.f5994a = this.f6009e;
        circle.f5996c = this.f6011g;
        circle.f5997d = this.f6012h;
        circle.f5998e = this.f6013i;
        circle.f5999f = this.f6014j;
        circle.f6000g = this.f6015k;
        circle.f6001h = this.f6016l;
        return circle;
    }

    public CircleOptions addHoleOption(HoleOptions holeOptions) {
        this.f6016l = holeOptions;
        return this;
    }

    public CircleOptions addHoleOptions(List<HoleOptions> list) {
        this.f6015k = list;
        return this;
    }

    public CircleOptions center(LatLng latLng) {
        if (latLng != null) {
            this.f6009e = latLng;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: circle center can not be null");
    }

    public CircleOptions dottedStroke(boolean z) {
        this.f6013i = z;
        return this;
    }

    public CircleOptions dottedStrokeType(CircleDottedStrokeType circleDottedStrokeType) {
        this.f6014j = circleDottedStrokeType.ordinal();
        return this;
    }

    public CircleOptions extraInfo(Bundle bundle) {
        this.f6008c = bundle;
        return this;
    }

    public CircleOptions fillColor(int i) {
        this.f6010f = i;
        return this;
    }

    public LatLng getCenter() {
        return this.f6009e;
    }

    public Bundle getExtraInfo() {
        return this.f6008c;
    }

    public int getFillColor() {
        return this.f6010f;
    }

    public int getRadius() {
        return this.f6011g;
    }

    public Stroke getStroke() {
        return this.f6012h;
    }

    public int getZIndex() {
        return this.f6006a;
    }

    public boolean isVisible() {
        return this.f6007b;
    }

    public CircleOptions radius(int i) {
        this.f6011g = i;
        return this;
    }

    public CircleOptions stroke(Stroke stroke) {
        this.f6012h = stroke;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.f6007b = z;
        return this;
    }

    public CircleOptions zIndex(int i) {
        this.f6006a = i;
        return this;
    }
}
