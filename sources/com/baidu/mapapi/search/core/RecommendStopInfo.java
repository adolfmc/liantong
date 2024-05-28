package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RecommendStopInfo implements Parcelable {
    public static final Parcelable.Creator<RecommendStopInfo> CREATOR = new C2803k();

    /* renamed from: a */
    private String f6691a;

    /* renamed from: b */
    private LatLng f6692b;

    /* renamed from: c */
    private float f6693c;

    /* renamed from: d */
    private String f6694d;

    /* renamed from: e */
    private String f6695e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.f6691a;
    }

    public void setName(String str) {
        this.f6691a = str;
    }

    public LatLng getLocation() {
        return this.f6692b;
    }

    public void setLocation(LatLng latLng) {
        this.f6692b = latLng;
    }

    public float getDistance() {
        return this.f6693c;
    }

    public void setDistance(float f) {
        this.f6693c = f;
    }

    public String getId() {
        return this.f6694d;
    }

    public void setId(String str) {
        this.f6694d = str;
    }

    public String getAddress() {
        return this.f6695e;
    }

    public void setAddress(String str) {
        this.f6695e = str;
    }

    public RecommendStopInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RecommendStopInfo(Parcel parcel) {
        this.f6691a = parcel.readString();
        this.f6692b = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.f6693c = parcel.readFloat();
        this.f6695e = parcel.readString();
        this.f6694d = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6691a);
        parcel.writeParcelable(this.f6692b, i);
        parcel.writeFloat(this.f6693c);
        parcel.writeString(this.f6695e);
        parcel.writeString(this.f6694d);
    }

    public String toString() {
        return "RecommendStopInfo{mName='" + this.f6691a + "', mLocation=" + this.f6692b + ", mDistance=" + this.f6693c + ", mId='" + this.f6694d + "', mAddress='" + this.f6695e + "'}";
    }
}
