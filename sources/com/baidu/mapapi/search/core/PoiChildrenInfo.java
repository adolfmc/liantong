package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiChildrenInfo implements Parcelable {
    public static final Parcelable.Creator<PoiChildrenInfo> CREATOR = new C2798f();

    /* renamed from: a */
    private String f6669a;

    /* renamed from: b */
    private String f6670b;

    /* renamed from: c */
    private String f6671c;

    /* renamed from: d */
    private String f6672d;

    /* renamed from: e */
    private LatLng f6673e;

    /* renamed from: f */
    private String f6674f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PoiChildrenInfo() {
    }

    public String getUid() {
        return this.f6669a;
    }

    public void setUid(String str) {
        this.f6669a = str;
    }

    public String getName() {
        return this.f6670b;
    }

    public void setName(String str) {
        this.f6670b = str;
    }

    public String getShowName() {
        return this.f6671c;
    }

    public void setShowName(String str) {
        this.f6671c = str;
    }

    public String getTag() {
        return this.f6672d;
    }

    public void setTag(String str) {
        this.f6672d = str;
    }

    public LatLng getLocation() {
        return this.f6673e;
    }

    public void setLocation(LatLng latLng) {
        this.f6673e = latLng;
    }

    public String getAddress() {
        return this.f6674f;
    }

    public void setAddress(String str) {
        this.f6674f = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PoiChildrenInfo(Parcel parcel) {
        this.f6669a = parcel.readString();
        this.f6670b = parcel.readString();
        this.f6671c = parcel.readString();
        this.f6672d = parcel.readString();
        this.f6673e = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.f6674f = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6669a);
        parcel.writeString(this.f6670b);
        parcel.writeString(this.f6671c);
        parcel.writeString(this.f6672d);
        parcel.writeParcelable(this.f6673e, i);
        parcel.writeString(this.f6674f);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PoiChildrenInfo: ");
        stringBuffer.append("uid = ");
        stringBuffer.append(this.f6669a);
        stringBuffer.append("; name = ");
        stringBuffer.append(this.f6670b);
        stringBuffer.append("; showName = ");
        stringBuffer.append(this.f6671c);
        stringBuffer.append("; tag = ");
        stringBuffer.append(this.f6672d);
        stringBuffer.append("; location = ");
        LatLng latLng = this.f6673e;
        if (latLng != null) {
            stringBuffer.append(latLng.toString());
        } else {
            stringBuffer.append("null");
        }
        stringBuffer.append("; address = ");
        stringBuffer.append(this.f6674f);
        return stringBuffer.toString();
    }
}
