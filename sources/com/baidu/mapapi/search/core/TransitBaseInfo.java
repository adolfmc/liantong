package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TransitBaseInfo implements Parcelable {
    public static final Parcelable.Creator<TransitBaseInfo> CREATOR = new C2808q();

    /* renamed from: a */
    private String f6719a;

    /* renamed from: b */
    private String f6720b;

    /* renamed from: c */
    private String f6721c;

    /* renamed from: d */
    private String f6722d;

    /* renamed from: e */
    private String f6723e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransitBaseInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransitBaseInfo(Parcel parcel) {
        this.f6719a = parcel.readString();
        this.f6720b = parcel.readString();
        this.f6721c = parcel.readString();
        this.f6722d = parcel.readString();
        this.f6723e = parcel.readString();
    }

    public String getName() {
        return this.f6719a;
    }

    public void setName(String str) {
        this.f6719a = str;
    }

    public String getDepartureStation() {
        return this.f6720b;
    }

    public void setDepartureStation(String str) {
        this.f6720b = str;
    }

    public String getArriveStation() {
        return this.f6721c;
    }

    public void setArriveStation(String str) {
        this.f6721c = str;
    }

    public String getDepartureTime() {
        return this.f6722d;
    }

    public void setDepartureTime(String str) {
        this.f6722d = str;
    }

    public String getArriveTime() {
        return this.f6723e;
    }

    public void setArriveTime(String str) {
        this.f6723e = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6719a);
        parcel.writeString(this.f6720b);
        parcel.writeString(this.f6721c);
        parcel.writeString(this.f6722d);
        parcel.writeString(this.f6723e);
    }
}
