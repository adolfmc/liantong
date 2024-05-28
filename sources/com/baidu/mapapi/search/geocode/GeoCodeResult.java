package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GeoCodeResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<GeoCodeResult> CREATOR = new C2812a();

    /* renamed from: a */
    private LatLng f6735a;

    /* renamed from: b */
    private String f6736b;

    /* renamed from: c */
    private int f6737c;

    /* renamed from: d */
    private int f6738d;

    /* renamed from: e */
    private String f6739e;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GeoCodeResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GeoCodeResult(Parcel parcel) {
        this.f6735a = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f6736b = parcel.readString();
        this.f6737c = parcel.readInt();
        this.f6738d = parcel.readInt();
        this.f6739e = parcel.readString();
    }

    public LatLng getLocation() {
        return this.f6735a;
    }

    public void setLocation(LatLng latLng) {
        this.f6735a = latLng;
    }

    @Deprecated
    public String getAddress() {
        return this.f6736b;
    }

    @Deprecated
    public void setAddress(String str) {
        this.f6736b = str;
    }

    public int getPrecise() {
        return this.f6737c;
    }

    public void setPrecise(int i) {
        this.f6737c = i;
    }

    public int getConfidence() {
        return this.f6738d;
    }

    public void setConfidence(int i) {
        this.f6738d = i;
    }

    public String getLevel() {
        return this.f6739e;
    }

    public void setLevel(String str) {
        this.f6739e = str;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f6735a);
        parcel.writeString(this.f6736b);
        parcel.writeInt(this.f6737c);
        parcel.writeInt(this.f6738d);
        parcel.writeString(this.f6739e);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GeoCodeResult: \n");
        stringBuffer.append("location = ");
        stringBuffer.append(this.f6735a);
        stringBuffer.append("; precise = ");
        stringBuffer.append(this.f6737c);
        stringBuffer.append("; confidence = ");
        stringBuffer.append(this.f6738d);
        stringBuffer.append("; level = ");
        stringBuffer.append(this.f6739e);
        return stringBuffer.toString();
    }
}
