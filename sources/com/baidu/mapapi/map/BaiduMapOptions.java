package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.C2946v;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BaiduMapOptions implements Parcelable {
    public static final Parcelable.Creator<BaiduMapOptions> CREATOR = new C2770l();

    /* renamed from: a */
    MapStatus f5978a;

    /* renamed from: b */
    boolean f5979b;

    /* renamed from: c */
    int f5980c;

    /* renamed from: d */
    boolean f5981d;

    /* renamed from: e */
    boolean f5982e;

    /* renamed from: f */
    boolean f5983f;

    /* renamed from: g */
    boolean f5984g;

    /* renamed from: h */
    boolean f5985h;

    /* renamed from: i */
    boolean f5986i;

    /* renamed from: j */
    LogoPosition f5987j;

    /* renamed from: k */
    Point f5988k;

    /* renamed from: l */
    Point f5989l;

    public BaiduMapOptions() {
        this.f5978a = new MapStatus(0.0f, new LatLng(39.914935d, 116.403119d), 0.0f, 12.0f, null, null);
        this.f5979b = false;
        this.f5980c = 1;
        this.f5981d = true;
        this.f5982e = true;
        this.f5983f = true;
        this.f5984g = true;
        this.f5985h = true;
        this.f5986i = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaiduMapOptions(Parcel parcel) {
        this.f5978a = new MapStatus(0.0f, new LatLng(39.914935d, 116.403119d), 0.0f, 12.0f, null, null);
        this.f5979b = false;
        this.f5980c = 1;
        this.f5981d = true;
        this.f5982e = true;
        this.f5983f = true;
        this.f5984g = true;
        this.f5985h = true;
        this.f5986i = true;
        this.f5978a = (MapStatus) parcel.readParcelable(MapStatus.class.getClassLoader());
        this.f5979b = parcel.readByte() != 0;
        this.f5980c = parcel.readInt();
        this.f5981d = parcel.readByte() != 0;
        this.f5982e = parcel.readByte() != 0;
        this.f5983f = parcel.readByte() != 0;
        this.f5984g = parcel.readByte() != 0;
        this.f5985h = parcel.readByte() != 0;
        this.f5986i = parcel.readByte() != 0;
        this.f5988k = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.f5989l = (Point) parcel.readParcelable(Point.class.getClassLoader());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C2946v m18979a() {
        return new C2946v().m18197a(this.f5978a.m18927c()).m18196a(this.f5979b).m18198a(this.f5980c).m18195b(this.f5981d).m18194c(this.f5982e).m18193d(this.f5983f).m18192e(this.f5984g);
    }

    public BaiduMapOptions compassEnabled(boolean z) {
        this.f5979b = z;
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaiduMapOptions logoPosition(LogoPosition logoPosition) {
        this.f5987j = logoPosition;
        return this;
    }

    public BaiduMapOptions mapStatus(MapStatus mapStatus) {
        if (mapStatus != null) {
            this.f5978a = mapStatus;
        }
        return this;
    }

    public BaiduMapOptions mapType(int i) {
        this.f5980c = i;
        return this;
    }

    public BaiduMapOptions overlookingGesturesEnabled(boolean z) {
        this.f5983f = z;
        return this;
    }

    public BaiduMapOptions rotateGesturesEnabled(boolean z) {
        this.f5981d = z;
        return this;
    }

    public BaiduMapOptions scaleControlEnabled(boolean z) {
        this.f5986i = z;
        return this;
    }

    public BaiduMapOptions scaleControlPosition(Point point) {
        this.f5988k = point;
        return this;
    }

    public BaiduMapOptions scrollGesturesEnabled(boolean z) {
        this.f5982e = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f5978a, i);
        parcel.writeByte(this.f5979b ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f5980c);
        parcel.writeByte(this.f5981d ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f5982e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f5983f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f5984g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f5985h ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f5986i ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.f5988k, i);
        parcel.writeParcelable(this.f5989l, i);
    }

    public BaiduMapOptions zoomControlsEnabled(boolean z) {
        this.f5985h = z;
        return this;
    }

    public BaiduMapOptions zoomControlsPosition(Point point) {
        this.f5989l = point;
        return this;
    }

    public BaiduMapOptions zoomGesturesEnabled(boolean z) {
        this.f5984g = z;
        return this;
    }
}
