package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PriceInfo implements Parcelable {
    public static final Parcelable.Creator<PriceInfo> CREATOR = new C2802j();

    /* renamed from: a */
    private int f6689a;

    /* renamed from: b */
    private double f6690b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PriceInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PriceInfo(Parcel parcel) {
        this.f6689a = parcel.readInt();
        this.f6690b = parcel.readDouble();
    }

    public int getTicketType() {
        return this.f6689a;
    }

    public void setTicketType(int i) {
        this.f6689a = i;
    }

    public double getTicketPrice() {
        return this.f6690b;
    }

    public void setTicketPrice(double d) {
        this.f6690b = d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f6689a);
        parcel.writeDouble(this.f6690b);
    }
}
