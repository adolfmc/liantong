package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VehicleInfo implements Parcelable {
    public static final Parcelable.Creator<VehicleInfo> CREATOR = new C2810s();

    /* renamed from: a */
    private String f6728a;

    /* renamed from: b */
    private int f6729b;

    /* renamed from: c */
    private String f6730c;

    /* renamed from: d */
    private int f6731d;

    /* renamed from: e */
    private int f6732e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getUid() {
        return this.f6728a;
    }

    public void setUid(String str) {
        this.f6728a = str;
    }

    public int getPassStationNum() {
        return this.f6729b;
    }

    public void setPassStationNum(int i) {
        this.f6729b = i;
    }

    public String getTitle() {
        return this.f6730c;
    }

    public void setTitle(String str) {
        this.f6730c = str;
    }

    public int getZonePrice() {
        return this.f6731d;
    }

    public void setZonePrice(int i) {
        this.f6731d = i;
    }

    public int getTotalPrice() {
        return this.f6732e;
    }

    public void setTotalPrice(int i) {
        this.f6732e = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6728a);
        parcel.writeInt(this.f6729b);
        parcel.writeString(this.f6730c);
        parcel.writeInt(this.f6731d);
        parcel.writeInt(this.f6732e);
    }

    public VehicleInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VehicleInfo(Parcel parcel) {
        this.f6728a = parcel.readString();
        this.f6729b = parcel.readInt();
        this.f6730c = parcel.readString();
        this.f6731d = parcel.readInt();
        this.f6732e = parcel.readInt();
    }
}
