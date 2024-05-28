package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TaxiInfo implements Parcelable {
    public static final Parcelable.Creator<TaxiInfo> CREATOR = new C2806o();

    /* renamed from: a */
    private float f6711a;

    /* renamed from: b */
    private String f6712b;

    /* renamed from: c */
    private int f6713c;

    /* renamed from: d */
    private int f6714d;

    /* renamed from: e */
    private float f6715e;

    /* renamed from: f */
    private float f6716f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TaxiInfo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaxiInfo(Parcel parcel) {
        this.f6711a = parcel.readFloat();
        this.f6712b = parcel.readString();
        this.f6713c = parcel.readInt();
        this.f6714d = parcel.readInt();
        this.f6715e = parcel.readFloat();
        this.f6716f = parcel.readFloat();
    }

    public float getTotalPrice() {
        return this.f6711a;
    }

    public void setTotalPrice(float f) {
        this.f6711a = f;
    }

    public String getDesc() {
        return this.f6712b;
    }

    public void setDesc(String str) {
        this.f6712b = str;
    }

    public int getDistance() {
        return this.f6713c;
    }

    public void setDistance(int i) {
        this.f6713c = i;
    }

    public int getDuration() {
        return this.f6714d;
    }

    public void setDuration(int i) {
        this.f6714d = i;
    }

    public float getPerKMPrice() {
        return this.f6715e;
    }

    public void setPerKMPrice(float f) {
        this.f6715e = f;
    }

    public float getStartPrice() {
        return this.f6716f;
    }

    public void setStartPrice(float f) {
        this.f6716f = f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f6711a);
        parcel.writeString(this.f6712b);
        parcel.writeInt(this.f6713c);
        parcel.writeInt(this.f6714d);
        parcel.writeFloat(this.f6715e);
        parcel.writeFloat(this.f6716f);
    }
}
