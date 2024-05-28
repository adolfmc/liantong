package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MapStatus implements Parcelable {
    public static final Parcelable.Creator<MapStatus> CREATOR = new C2775p();

    /* renamed from: a */
    C2948x f6144a;

    /* renamed from: b */
    private double f6145b;
    public final LatLngBounds bound;

    /* renamed from: c */
    private double f6146c;
    public final float overlook;
    public final float rotate;
    public final LatLng target;
    public final Point targetScreen;
    public WinRound winRound;
    public final float zoom;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class Builder {

        /* renamed from: a */
        private float f6147a;

        /* renamed from: b */
        private LatLng f6148b;

        /* renamed from: c */
        private float f6149c;

        /* renamed from: d */
        private float f6150d;

        /* renamed from: e */
        private Point f6151e;

        /* renamed from: f */
        private LatLngBounds f6152f;

        /* renamed from: g */
        private double f6153g;

        /* renamed from: h */
        private double f6154h;

        /* renamed from: i */
        private final float f6155i;

        public Builder() {
            this.f6147a = -2.14748365E9f;
            this.f6148b = null;
            this.f6149c = -2.14748365E9f;
            this.f6150d = -2.14748365E9f;
            this.f6151e = null;
            this.f6152f = null;
            this.f6153g = 0.0d;
            this.f6154h = 0.0d;
            this.f6155i = 15.0f;
        }

        public Builder(MapStatus mapStatus) {
            this.f6147a = -2.14748365E9f;
            this.f6148b = null;
            this.f6149c = -2.14748365E9f;
            this.f6150d = -2.14748365E9f;
            this.f6151e = null;
            this.f6152f = null;
            this.f6153g = 0.0d;
            this.f6154h = 0.0d;
            this.f6155i = 15.0f;
            this.f6147a = mapStatus.rotate;
            this.f6148b = mapStatus.target;
            this.f6149c = mapStatus.overlook;
            this.f6150d = mapStatus.zoom;
            this.f6151e = mapStatus.targetScreen;
            this.f6153g = mapStatus.m18931a();
            this.f6154h = mapStatus.m18929b();
        }

        /* renamed from: a */
        private float m18926a(float f) {
            if (15.0f == f) {
                return 15.5f;
            }
            return f;
        }

        public MapStatus build() {
            return new MapStatus(this.f6147a, this.f6148b, this.f6149c, this.f6150d, this.f6151e, this.f6152f);
        }

        public Builder overlook(float f) {
            this.f6149c = f;
            return this;
        }

        public Builder rotate(float f) {
            this.f6147a = f;
            return this;
        }

        public Builder target(LatLng latLng) {
            this.f6148b = latLng;
            return this;
        }

        public Builder targetScreen(Point point) {
            this.f6151e = point;
            return this;
        }

        public Builder zoom(float f) {
            this.f6150d = m18926a(f);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapStatus(float f, LatLng latLng, float f2, float f3, Point point, double d, double d2, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        this.f6145b = d;
        this.f6146c = d2;
        this.bound = latLngBounds;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapStatus(float f, LatLng latLng, float f2, float f3, Point point, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        LatLng latLng2 = this.target;
        if (latLng2 != null) {
            this.f6145b = CoordUtil.ll2mc(latLng2).getLongitudeE6();
            this.f6146c = CoordUtil.ll2mc(this.target).getLatitudeE6();
        }
        this.bound = latLngBounds;
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, C2948x c2948x, double d, double d2, LatLngBounds latLngBounds, WinRound winRound) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        this.f6144a = c2948x;
        this.f6145b = d;
        this.f6146c = d2;
        this.bound = latLngBounds;
        this.winRound = winRound;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MapStatus(Parcel parcel) {
        this.rotate = parcel.readFloat();
        this.target = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.overlook = parcel.readFloat();
        this.zoom = parcel.readFloat();
        this.targetScreen = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.bound = (LatLngBounds) parcel.readParcelable(LatLngBounds.class.getClassLoader());
        this.f6145b = parcel.readDouble();
        this.f6146c = parcel.readDouble();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static MapStatus m18930a(C2948x c2948x) {
        if (c2948x == null) {
            return null;
        }
        float f = c2948x.f7370b;
        double d = c2948x.f7373e;
        double d2 = c2948x.f7372d;
        LatLng mc2ll = CoordUtil.mc2ll(new GeoPoint(d, d2));
        float f2 = c2948x.f7371c;
        float f3 = c2948x.f7369a;
        Point point = new Point(c2948x.f7374f, c2948x.f7375g);
        LatLng mc2ll2 = CoordUtil.mc2ll(new GeoPoint(c2948x.f7379k.f7392e.getDoubleY(), c2948x.f7379k.f7392e.getDoubleX()));
        LatLng mc2ll3 = CoordUtil.mc2ll(new GeoPoint(c2948x.f7379k.f7393f.getDoubleY(), c2948x.f7379k.f7393f.getDoubleX()));
        LatLng mc2ll4 = CoordUtil.mc2ll(new GeoPoint(c2948x.f7379k.f7395h.getDoubleY(), c2948x.f7379k.f7395h.getDoubleX()));
        LatLng mc2ll5 = CoordUtil.mc2ll(new GeoPoint(c2948x.f7379k.f7394g.getDoubleY(), c2948x.f7379k.f7394g.getDoubleX()));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(mc2ll2);
        builder.include(mc2ll3);
        builder.include(mc2ll4);
        builder.include(mc2ll5);
        return new MapStatus(f, mc2ll, f2, f3, point, c2948x, d2, d, builder.build(), c2948x.f7378j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public double m18931a() {
        return this.f6145b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public double m18929b() {
        return this.f6146c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public C2948x m18928b(C2948x c2948x) {
        if (c2948x == null) {
            return null;
        }
        float f = this.rotate;
        if (f != -2.14748365E9f) {
            c2948x.f7370b = (int) f;
        }
        float f2 = this.zoom;
        if (f2 != -2.14748365E9f) {
            c2948x.f7369a = f2;
        }
        float f3 = this.overlook;
        if (f3 != -2.14748365E9f) {
            c2948x.f7371c = (int) f3;
        }
        if (this.target != null) {
            c2948x.f7372d = this.f6145b;
            c2948x.f7373e = this.f6146c;
        }
        Point point = this.targetScreen;
        if (point != null) {
            c2948x.f7374f = point.x;
            c2948x.f7375g = this.targetScreen.y;
        }
        return c2948x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public C2948x m18927c() {
        return m18928b(new C2948x());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.target != null) {
            sb.append("target lat: " + this.target.latitude + "\n");
            sb.append("target lng: " + this.target.longitude + "\n");
        }
        if (this.targetScreen != null) {
            sb.append("target screen x: " + this.targetScreen.x + "\n");
            sb.append("target screen y: " + this.targetScreen.y + "\n");
        }
        sb.append("zoom: " + this.zoom + "\n");
        sb.append("rotate: " + this.rotate + "\n");
        sb.append("overlook: " + this.overlook + "\n");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.rotate);
        parcel.writeParcelable(this.target, i);
        parcel.writeFloat(this.overlook);
        parcel.writeFloat(this.zoom);
        parcel.writeParcelable(this.targetScreen, i);
        parcel.writeParcelable(this.bound, i);
        parcel.writeDouble(this.f6145b);
        parcel.writeDouble(this.f6146c);
    }
}
