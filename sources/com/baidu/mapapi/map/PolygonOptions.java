package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class PolygonOptions extends OverlayOptions {

    /* renamed from: a */
    int f6311a;

    /* renamed from: c */
    Bundle f6313c;

    /* renamed from: d */
    private Stroke f6314d;

    /* renamed from: f */
    private List<LatLng> f6316f;

    /* renamed from: g */
    private List<HoleOptions> f6317g;

    /* renamed from: h */
    private HoleOptions f6318h;

    /* renamed from: j */
    private String f6320j;

    /* renamed from: k */
    private EncodePointType f6321k;

    /* renamed from: e */
    private int f6315e = -16777216;

    /* renamed from: i */
    private boolean f6319i = false;

    /* renamed from: l */
    private int f6322l = 0;

    /* renamed from: b */
    boolean f6312b = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        Polygon polygon = new Polygon();
        polygon.f6299H = this.f6312b;
        polygon.f6298G = this.f6311a;
        polygon.f6300I = this.f6313c;
        List<LatLng> list = this.f6316f;
        if (list == null || list.size() < 2) {
            String str = this.f6320j;
            if (str == null || str.length() <= 0) {
                throw new IllegalStateException("BDMapSDKException: when you add polyline, you must at least supply 2 points");
            }
            polygon.f6307g = this.f6320j;
            polygon.f6308h = this.f6321k;
        }
        polygon.f6303c = this.f6316f;
        polygon.f6302b = this.f6315e;
        polygon.f6301a = this.f6314d;
        polygon.f6304d = this.f6317g;
        polygon.f6305e = this.f6318h;
        polygon.f6306f = this.f6319i;
        polygon.f6309i = this.f6322l;
        return polygon;
    }

    public PolygonOptions addHoleOption(HoleOptions holeOptions) {
        this.f6318h = holeOptions;
        return this;
    }

    public PolygonOptions addHoleOptions(List<HoleOptions> list) {
        this.f6317g = list;
        return this;
    }

    public PolygonOptions dottedStroke(boolean z) {
        this.f6319i = z;
        return this;
    }

    public PolygonOptions dottedStrokeType(PolylineDottedLineType polylineDottedLineType) {
        this.f6322l = polylineDottedLineType.ordinal();
        return this;
    }

    public PolygonOptions extraInfo(Bundle bundle) {
        this.f6313c = bundle;
        return this;
    }

    public PolygonOptions fillColor(int i) {
        this.f6315e = i;
        return this;
    }

    public Bundle getExtraInfo() {
        return this.f6313c;
    }

    public int getFillColor() {
        return this.f6315e;
    }

    public List<LatLng> getPoints() {
        return this.f6316f;
    }

    public Stroke getStroke() {
        return this.f6314d;
    }

    public int getZIndex() {
        return this.f6311a;
    }

    public boolean isVisible() {
        return this.f6312b;
    }

    public PolygonOptions points(String str, EncodePointType encodePointType) {
        this.f6320j = str;
        this.f6321k = encodePointType;
        return this;
    }

    public PolygonOptions points(List<LatLng> list) {
        if (list != null) {
            if (list.size() > 2) {
                if (list.contains(null)) {
                    throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
                }
                int i = 0;
                while (i < list.size()) {
                    int i2 = i + 1;
                    for (int i3 = i2; i3 < list.size(); i3++) {
                        if (list.get(i) == list.get(i3)) {
                            throw new IllegalArgumentException("BDMapSDKException: points list can not has same points");
                        }
                    }
                    i = i2;
                }
                this.f6316f = list;
                return this;
            }
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than three");
        }
        throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
    }

    public PolygonOptions stroke(Stroke stroke) {
        this.f6314d = stroke;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.f6312b = z;
        return this;
    }

    public PolygonOptions zIndex(int i) {
        this.f6311a = i;
        return this;
    }
}
